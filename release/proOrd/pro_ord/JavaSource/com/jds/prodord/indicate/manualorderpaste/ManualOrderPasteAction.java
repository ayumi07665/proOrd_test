package com.jds.prodord.indicate.manualorderpaste;
import java.sql.SQLException;
import java.util.*;
import javax.servlet.http.*;
import org.apache.struts.action.*;

import com.jds.prodord.common.CheckCommon;
import com.jds.prodord.common.CommonConst;
import com.jds.prodord.common.SystemException;

/**
 * ManualOrderPasteAction  マニュアル発注指示一括貼付アクションクラス。<BR>
 * <dt><b>作成日: </b></dt>
 * <dd>2007/09/10</dd>
 * <dt><B>作成者: </b></dt>
 * <dd>(NII)</dd>
 * <dt><B>処理概要: </b></dt>
 * <dd>処理呼び出しを行い、画面制御を行うクラス。</dd>
 * <br>
 * <b>変更履歴</b><br>
 * <table border="1">
 * 	<tr>
 * 		<th width="80">修正日付</th><th width="100">修正者</th><th width="300">修正内容</th>
 * 	</tr>
 * 	<tr>
 * 		<td>yyyy/MM/dd</td>
 * 		<td>(NII)</td>
 * 		<td>・</td>
 * 	</tr>
 * </table>
 */
public class ManualOrderPasteAction extends Action {
		
	/**
	 * ビジネスロジック
	 */
	private static  ManualOrderPasteDelegate bzDelegate = new ManualOrderPasteDelegate();
	
	
	/* (非 Javadoc)
	 * @see org.apache.struts.action.Action#perform(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public ActionForward perform(ActionMapping mapping,ActionForm form,HttpServletRequest req,HttpServletResponse res){
		ActionErrors errors = null;
		HttpSession session = req.getSession();
		//フォームのデータ
		ManualOrderPasteForm fmForm = (ManualOrderPasteForm)form;
		ManualOrderPasteVO fmVO = new ManualOrderPasteVO();
		

		//「プレス／副資材」変更
		if(fmForm.getPrsFksChangeFlg().equals("1")){
			fmForm.setFormRows(new LinkedList());
//			fmForm.setCpPaste("");
			fmForm.setCpPasteMode("1");
			fmForm.setPrsFksChangeFlg("0");

			fmForm.setCommand("INIT");
			return (new ActionForward(mapping.getInput()));
		

		}


		if(!fmForm.getCommand().equals("INIT")){
			//サービス時間のチェック
			if(errors == null)
				errors = new ActionErrors();
			int check_result = CheckCommon.checkBatchDate(); //サービス時間のチェック
			switch(check_result){
				//内部エラーのとき
				case CheckCommon.errors_internal:
					errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.internal"));
					break;
				//サービス時間外のとき	
				case CheckCommon.offservice:
					errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.offservice"));
					break;
				//エラーなしのとき	
				case CheckCommon.success:
					errors = null;
			}
			//エラーあり
			if(errors != null){
				super.saveErrors(req,errors);
	    		return (new ActionForward(mapping.getInput()));
			}
		}
		
		//--------以下、フォームの押したボタンから処理分岐を行う
		//最初にメニューからきたとき、照会結果画面から「戻る」ボタンで戻ってきたとき
		if(fmForm.getCommand().equals("INIT")){
			if(fmForm.getFromMenuSgn()){
				fmForm.clearAll();
		   		com.jds.prodord.register.LogonForm logonForm 
			  			= (com.jds.prodord.register.LogonForm)session.getAttribute(CommonConst.user);
				String daiKaiSkbCod = 	logonForm.getDaikaiskbcod();
				String KaiSkbCod = 	logonForm.getKaiskbcod();
				ArrayList kaiList = logonForm.getKaiSkbCodList();
				fmForm.setQueryDaiKaiSkbCod(daiKaiSkbCod);
				fmForm.setQueryKaiSkbCod(KaiSkbCod);
				fmForm.setQueryKaiSkbCodList(kaiList);
				fmForm.setUsrId(logonForm.getUser());
				fmForm.setBshCod(logonForm.getBshCod());
				
				fmForm.setCpPasteMode("1");

				//選択項目のオプションをセット
				fmForm.initOptionLists();
				
			}
			fmForm.setFromMenuSgn(false);
		    //発注書・照会結果画面のBEANをremove
		    session.removeAttribute(CommonConst.FORMBEAN_NAME_PRSORDER);
			session.removeAttribute(CommonConst.BEAN_NAME_PRSORDER_LEFT);
			session.removeAttribute(CommonConst.PRSORDER_VOS);


		//ボタンが貼り付け
		}else if(fmForm.getCommand().equals(fmForm.getMessage("button.paste"))){

				dataPaste(fmForm);
			
				fmForm.setCpPaste("");
				fmForm.setCpPasteMode("0");
				fmForm.setSize(fmForm.ROW_MAX);


		//ボタンが実行
		}else if(fmForm.getCommand().equals(fmForm.getMessage("button.execute"))){
			errors = execute(fmForm,req,res,fmVO);
			if(errors == null){	//処理成功
				session.setAttribute(CommonConst.PRSORDER_VOS,fmVO.getPrsVOs());	
				return mapping.findForward("next");	
			}


		//ボタンがクリア
		}else if(fmForm.getCommand().equals(fmForm.getMessage("button.clear"))){
			fmForm.clearAll();
			fmForm.setCommand("INIT");
		
		}

		
		//--------処理分岐終わり
		
		//エラーあり
		if(errors != null){
			super.saveErrors(req,errors);
		}

		return new ActionForward(mapping.getInput());
	}

//	---------------------------------------------------------------------------------------------------	

	
	/** 
	 * 貼付
	 * @param fmForm
	 */
	public void dataPaste(ManualOrderPasteForm fmForm){
		
		ManualOrderPasteVO fmVO = new ManualOrderPasteVO();
		fmVO.setCpPaste(fmForm.getCpPaste());
		fmVO.setPrsFks(fmForm.getPrsFks());

		try{
			bzDelegate.doDataPaste(fmVO);

			fmForm.setFormRows(fmVO.getFormRows());
			
		}catch(Exception e){
			SystemException se = new SystemException(e);
			se.printStackTrace();
		}
	}


	/**
	 * 実行
	 * @param fmForm
	 * @param req
	 * @param res
	 * @param fmVO
	 * @return
	 */
	private ActionErrors execute(ManualOrderPasteForm fmForm,HttpServletRequest req,HttpServletResponse res,ManualOrderPasteVO fmVO){

		ActionErrors errors = null;
		
		//飛んできたフォームの値をバリューオブジェクトにセット
		fmVO.setDaiKaiSkbCod(fmForm.getQueryDaiKaiSkbCod());
		fmVO.setQueryKaiSkbCod(fmForm.getQueryKaiSkbCod());
		
		fmVO.setBshCod(fmForm.getBshCod());
		fmVO.setUsrId(fmForm.getUsrId());
	
		//プレス／副資材
		fmVO.setPrsFks(fmForm.getPrsFks());
		//区分
		fmVO.setKbn(fmForm.getKbn());

		//明細行
		fmVO.setFormRows(fmForm.getFormRows());

		try{

			ManualOrderPasteResult result = null;
			
			if(fmForm.getPrsFks().equals(ManualOrderPasteForm.PRS)){
				result = bzDelegate.doPrsHachu(fmVO,fmForm);
			}else{
				result = bzDelegate.doFukHachu(fmVO,fmForm);
			}

			//エラーがあったら
			if(!result.isSuccess()){
				errors = new ActionErrors();
				LinkedList errList = result.getErrList();
				for (Iterator iter = errList.iterator(); iter.hasNext();) {
					String err[] =(String[])iter.next();
					errors.add("ROW_"+err[0],new ActionError("errors.insert.notfound",fmForm.getMessage(err[1])));
				}
				return errors;
			}	

			fmVO.setPrsVOs(result.getVO().getPrsVOs());


		}catch(SQLException sqle){
			SystemException se = new SystemException(sqle);
			se.printStackTrace();
			errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.exception",sqle.toString()));
		}		

		return errors;			
	}
	

	
}
