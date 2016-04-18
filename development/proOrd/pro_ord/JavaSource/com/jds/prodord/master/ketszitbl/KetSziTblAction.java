/**
* KetSziTblAction  形態別構成資材マスターメンテナンスアクションクラス
*
*	作成日    2004/01/30
*	作成者    （ＮＩＩ）森
* 処理概要    処理呼び出しを行い、画面制御を行うクラス。
*
*	[変更履歴]
*
*/

package com.jds.prodord.master.ketszitbl;
import com.jds.prodord.common.*;
import javax.servlet.http.*;
import org.apache.struts.action.*;
import org.apache.struts.util.*;
import java.sql.*;
import java.util.*;
public class KetSziTblAction extends Action {
		
	/**
	 * ビジネスロジック
	 */
	private static  KetSziTblDelegate bzDelegate = new KetSziTblDelegate();
	
	
	public ActionForward perform(ActionMapping mapping,ActionForm form,HttpServletRequest req,HttpServletResponse res){
		ActionErrors errors = null;
		HttpSession session = req.getSession();
		//フォームのデータ
		KetSziTblForm fmForm = (KetSziTblForm)form;
		KetSziTblVO fmVO = new KetSziTblVO();
		MessageResources mr = super.getResources();//通常メッセージ表示用にリソースファイルからメッセージをとってくるのに使う
		

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



		//--------以下、フォームの押したボタンから処理分岐を行う
		
		//最初にメニューからきたとき、照会結果画面から「戻る」ボタンで戻ってきたとき
		if(fmForm.getCommand().equals("INIT")){
			fmForm.clearAll();

	   		com.jds.prodord.register.LogonForm logonForm 
		  			= (com.jds.prodord.register.LogonForm)session.getAttribute(CommonConst.user);
			String daiKaiSkbCod = 	logonForm.getDaikaiskbcod();
			String KaiSkbCod = 	logonForm.getKaiskbcod();
			ArrayList kaiList = logonForm.getKaiSkbCodList();
			
			fmForm.setQueryDaiKaiSkbCod(daiKaiSkbCod);
			fmForm.setQueryKaiSkbCod(KaiSkbCod);
			fmForm.setQueryKaiSkbCodList(kaiList);
			
			fmForm.setKaiSkbCodOptions(kaiList);
			fmForm.setKetCodOptions(logonForm.getKetCodList(),logonForm.getKetNm2List());
			fmForm.setHyoKbn(logonForm.getHyoKbn());
		}
		//「クリア」押下時
		else if(fmForm.getCommand().equals(KetSziTblForm.COMMAND_CLEAR)){
			fmForm.clearAll();
		fmForm.setUpdatable(false);
			fmForm.setCommand("INIT");
			session.removeAttribute(CommonConst.INFOMSG_KEY);
			return (new ActionForward(mapping.getInput()));
		}


		//処理区分が照会
		if(fmForm.getPrcKbn().equals("1")){
			errors = this.select(fmForm,req,res);
			if(errors == null)	//処理成功
				req.setAttribute(CommonConst.INFOMSG_KEY,mr.getMessage("info.select.end"));
		}
		//処理区分が更新
		else if(fmForm.getPrcKbn().equals("3")){
			errors = this.update(fmForm,req,res);
			if(errors == null){	//処理成功
				req.setAttribute(CommonConst.INFOMSG_KEY,mr.getMessage("info.update.end"));
				fmForm.setUpdatable(false);
			}		
		}
		
		//--------処理分岐終わり
		
		//エラーあり
		if(errors != null){
			super.saveErrors(req,errors);
    		return (new ActionForward(mapping.getInput()));
		}

		//エラーなし
		return mapping.findForward("success");			
	}

	
	/**
	 * 照会
	 * 
	 */
	private ActionErrors select (KetSziTblForm fmForm,HttpServletRequest req,
								 HttpServletResponse res){

		ActionErrors errors = null;
		KetSziTblVO fmVO = new KetSziTblVO();		
		
		//飛んできたフォームの値をバリューオブジェクトにセット
		String queryKaiSkbCod = fmForm.getQueryKaiSkbCod();
		String queryDaiKaiSkbCod = fmForm.getQueryDaiKaiSkbCod();

		fmVO.setDaiKaiSkbCod(queryDaiKaiSkbCod);
		fmVO.setQueryKaiSkbCod(queryKaiSkbCod);
		fmVO.setQueryKaiSkbCodList(fmForm.getQueryKaiSkbCodList());

		String[] ket = fmForm.getKetCod();
	
		//形態コード
		if(fmForm.getKetCod() != null)
		   fmVO.setKetCodList(fmForm.getKetCod());
		
		try{
			//検索実行
			KetSziTblVO[] resultFmVOs = bzDelegate.doFind(fmVO);
			
			fmForm.removeKetSziTblVO();
			
			if(resultFmVOs.length != 0){
				//検索結果をフォームにセット
				fmForm.setSize(Math.max((resultFmVOs.length),KetSziTblForm.DEFAULT_ROW_COUNT));
				fmForm.clearTableField();
				
				for(int i = 0;i < resultFmVOs.length;i++){
					KetSziTblFormRow formRow = fmForm.getFormRow(i);
					formRow.setKetCod(resultFmVOs[i].getKetCod());
					formRow.setKetNm(resultFmVOs[i].getKetNm());
					formRow.setKetNm2(resultFmVOs[i].getKetNm2());
					formRow.setFuksziCodArr(resultFmVOs[i].getFuksziCodArr());
					fmForm.addKetSziTblVO(resultFmVOs[i]);
				}
				fmForm.setCount(fmForm.getSize());
			}else{
				fmForm.setCount(0);
				fmForm.setSize(KetSziTblForm.DEFAULT_ROW_COUNT);
				fmForm.clearTableField();
				errors = new ActionErrors();
				errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.select.notexist")); 
			}
			
			
		}catch(SQLException sqle){
			sqle.printStackTrace();
			if(errors == null)
				errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.exception",sqle.toString()));
		}		
		return errors;			
	}


	
	/**
	 * 更新
	 * 
	 */
	private ActionErrors update (KetSziTblForm fmForm,HttpServletRequest req,HttpServletResponse res){

		ActionErrors errors = null;
		String queryKaiSkbCod = fmForm.getQueryKaiSkbCod();
		String queryDaiKaiSkbCod = fmForm.getQueryDaiKaiSkbCod();
		
		List lisFmVO = new ArrayList();  //バリューオブジェクトを入れる
		
		for(int i = 0; i < fmForm.getSize();i++){
			KetSziTblVO fmVO = fmForm.getKetSziTblVO(i);
			KetSziTblFormRow formRow = fmForm.getFormRow(i);
			
			if(!formRow.getCheck_bx()){
				lisFmVO.add(null);	
				continue;
			}
			fmVO.setFuksziCodString(formRow.getFuksziCod());
				
			lisFmVO.add(fmVO);
		}

		
		try{
			//更新実行
			KetSziTblResult[] results = bzDelegate.doUpdate((KetSziTblVO[])lisFmVO.toArray(new KetSziTblVO[lisFmVO.size()]));

		
			//エラーだったのはないか調べる
			for(int i = 0; i < results.length;i++){
				if(results[i] != null && !results[i].isSuccess()){
					if(errors == null)
						errors = new ActionErrors();
						
					 //ほかのユーザーが変更済み
					if(results[i].getDescription().equals(KetSziTblDelegate.ANOTHER_USER_UPDATE)||
					   results[i].getDescription().equals(KetSziTblDelegate.LOGICAL_DELETE)||
					   results[i].getDescription().equals(KetSziTblDelegate.NOT_EXIST)){
						errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.anotheruserupdate"));
				   }else if(results[i].getDescription().equals(KetSziTblDelegate.FUKSZICOD_NOT_EXIST)){
				   		errors = new ActionErrors();

						ArrayList errs_fuk = results[i].getMap(KetSziTblResult.KEY_FUKSZICOD);
						String index = "";
				 		//副資材コードが存在しない
						if(errs_fuk != null){	
							for(int j = 0; j < errs_fuk.size(); j++){
								String err_fuk = (String)errs_fuk.get(j);
								index = err_fuk;//エラーのあったIndex
								errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.insert.notfound_row",String.valueOf(i+1),"副資材コード("+ index +"番目)"));
							}
				   		}	
					}
				}
			}
			if(errors == null){ 
				for(int i = 0; i < results.length;i++){
					if(results[i] == null)
						continue;
					fmForm.getFormRow(i).setCheck_bx(false);
					fmForm.setKetSziTblVO(i,results[i].getVO());
				}
			}
		}catch(SQLException sqle){
			sqle.printStackTrace();
			errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.exception",sqle.toString()));
		}	

		return errors;			
	}

	
}
