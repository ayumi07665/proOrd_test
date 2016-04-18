package com.jds.prodord.master.fzkhinksipaste;
import com.jds.prodord.common.*;

import javax.servlet.http.*;
import org.apache.struts.action.*;
import org.apache.struts.util.*;
import java.sql.*;
import java.util.*;

/**
* FzkHinKsiPasteAction  付属品構成マスター一括貼り付けアクションクラス
*
*	作成日    2007/09/21
*	作成者    （ＮＩＩ）田中
* 処理概要    処理呼び出しを行い、画面制御を行うクラス。
*
*	[変更履歴]
*/
public class FzkHinKsiPasteAction extends Action {
		
	/**
	 * ビジネスロジック
	 */
	private static  FzkHinKsiPasteDelegate bzDelegate = new FzkHinKsiPasteDelegate();
	
	
	/* (非 Javadoc)
	 * @see org.apache.struts.action.Action#perform(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public ActionForward perform(ActionMapping mapping,ActionForm form,HttpServletRequest req,HttpServletResponse res){
		ActionErrors errors = null;
		HttpSession session = req.getSession();
		//フォームのデータ
		FzkHinKsiPasteForm fmForm = (FzkHinKsiPasteForm)form;
		FzkHinKsiPasteVO fmVO = new FzkHinKsiPasteVO();
		MessageResources mr = super.getResources();//通常メッセージ表示用にリソースファイルからメッセージをとってくるのに使う
		
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
				fmForm.setKaiSkbCod(KaiSkbCod);
				fmForm.setHyoKbn(logonForm.getHyoKbn());
				
				fmForm.setCpPasteMode("1");
				
			}
			fmForm.setFromMenuSgn(false);


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
//				session.setAttribute(CommonConst.PRSORDER_VOS,fmVO.getFzkVOs());	
				if(errors == null){	//処理成功
					req.setAttribute(CommonConst.INFOMSG_KEY,mr.getMessage("info.insert.end"));
				}
//				return mapping.findForward("next");	
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
	public void dataPaste(FzkHinKsiPasteForm fmForm){
		
		FzkHinKsiPasteVO fmVO = new FzkHinKsiPasteVO();
		fmVO.setCpPaste(fmForm.getCpPaste());

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

	private ActionErrors execute(FzkHinKsiPasteForm fmForm,HttpServletRequest req,HttpServletResponse res,FzkHinKsiPasteVO fmVO){

		ActionErrors errors = null;
		
		//飛んできたフォームの値をバリューオブジェクトにセット
		fmVO.setDaiKaiSkbCod(fmForm.getQueryDaiKaiSkbCod());
		fmVO.setQueryKaiSkbCod(fmForm.getQueryKaiSkbCod());
		fmVO.setKaiSkbCod(fmForm.getKaiSkbCod());
		
		//明細行
		fmVO.setFormRows(fmForm.getFormRows());

		try{

			FzkHinKsiPasteResult result = null;
			
			result = bzDelegate.doInsert(fmVO,fmForm);

			//エラーがあったら
			if(!result.isSuccess()){
				errors = new ActionErrors();
				LinkedList errList = result.getErrList();
				for (Iterator iter = errList.iterator(); iter.hasNext();) {
					int err[] =(int[])iter.next();

					String key = null;

					switch(err[1]){
						case FzkHinKsiPasteForm.NOT_HIN_EXIST:
							key = "label.kigbng";
							errors.add("ROW_"+err[0],new ActionError("errors.insert.notfound",fmForm.getMessage(key)));
							break;					
						case FzkHinKsiPasteForm.NOT_FKS_EXIST:
							key = "label.fukshizai";
							errors.add("ROW_"+err[0],new ActionError("errors.insert.notfound",fmForm.getMessage(key)));
							break;					
						case FzkHinKsiPasteForm.NOT_SIR_EXIST:
							key = "label.sirhaccod";
							errors.add("ROW_"+err[0],new ActionError("errors.insert.notfound",fmForm.getMessage(key)));
							break;					
						case FzkHinKsiPasteForm.MST08_EXIST:
							errors.add("ROW_"+err[0],new ActionError("errors.insert.duplicate"));
							break;					
					}
				}
				return errors;
			}	



		}catch(SQLException sqle){
			SystemException se = new SystemException(sqle);
			se.printStackTrace();
			errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.exception",sqle.toString()));
		}		

		return errors;			
	}

}
