/**
* IndicatePrintAction  発注書出力指示アクションクラス
*
*	作成日    2003/04/23
*	作成者    （ＮＩＩ）岡田 夏美
* 処理概要    処理呼び出しを行い、画面制御を行うクラス。
*
*	[変更履歴]
*       2003/06/05（ＮＩＩ）蛭田 敬子
* 			・「再出力」の条件項目（発注書日付、発注先コード）追加。
* 		2003/07/16（ＮＩＩ）岡田 夏美
* 			・発注書日付Fromにデフォルトで当日日付セット 追加。
* 		2003/09/05（ＮＩＩ）岡田 夏美
* 			・発注書印刷検索条件用VOへの検索条件セット方法を変更。
* 			（PrintOrdersQueryRow → StringTokenizer）
* 			・発注書番号のフォーマットメソッド追加
* 		2005/05/23（ＮＩＩ）蛭田
* 			・発注書一括出力対応（ユーザーＩＤの追加）
* 
*/

package com.jds.prodord.indicate.indicateprint;
import com.jds.prodord.common.*;
import com.jds.prodord.print.printorders.*;
import com.jds.prodord.register.LogonForm;

import javax.servlet.http.*;
import org.apache.struts.action.*;
import org.apache.struts.util.*;
import java.sql.*;
import java.util.*;

public class IndicatePrintAction extends Action {
		
	/**
	 * ビジネスロジック
	 */
	private static  IndicatePrintDelegate bzDelegate = new IndicatePrintDelegate();
	
	
	public ActionForward perform(ActionMapping mapping,ActionForm form,HttpServletRequest req,HttpServletResponse res){
		ActionErrors errors = null;
		HttpSession session = req.getSession();
		//フォームのデータ
		IndicatePrintForm fmForm = (IndicatePrintForm)form;
		IndicatePrintVO fmVO = new IndicatePrintVO();
		
		MessageResources mr = super.getResources();//通常メッセージ表示用にリソースファイルからメッセージをとってくるのに使う

		//発注書データ検索条件用のデータを格納するVO
		PrintOrdersQueryVO poQueryVO = new PrintOrdersQueryVO();


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
		//ボタンが伝票発行
		if(fmForm.getCommand().equals(IndicatePrintForm.COMMAND_DENPYOHAKKOU)){
			errors = this.dnpHakkou(fmForm,req,res,fmVO,poQueryVO);
			if(errors == null){	//処理成功
				fmForm.setCommand(IndicatePrintForm.SUCCESS_DENPYOHAKKOU);
				fmForm.setSyoriFlg(IndicatePrintForm.DENPYOHAKKOU);
				session.setAttribute(CommonConst.PO_QUERY_VO,poQueryVO);//発注書データ検索条件がセットされたpoQueryVOをセッションに格納
				return mapping.findForward("issue");
			}
		}
		//伝票発行完了
		else if(fmForm.getCommand().equals(IndicatePrintForm.SUCCESS_DENPYOHAKKOU)){
			if(session.getAttribute(CommonConst.PO_QUERY_VO) != null){
				fmForm.setSyoriFlg("");
				fmForm.setNewWindow("1");
				req.setAttribute(CommonConst.INFOMSG_KEY,mr.getMessage("info.issue.end"));
			}else{
				fmForm.setCommand("INIT");
			}
		//ボタンが再出力
		}else if(fmForm.getCommand().equals(IndicatePrintForm.COMMAND_SAISYRYK)){
			errors = this.saiSyryk(fmForm,req,res,fmVO,poQueryVO);
			if(errors == null){	//処理成功
				fmForm.setCommand(IndicatePrintForm.SUCCESS_SAISYRYK);
				fmForm.setSyoriFlg(IndicatePrintForm.SAISYRYK);
				session.setAttribute(CommonConst.PO_QUERY_VO,poQueryVO);//発注書データ検索条件がセットされたpoQueryVOをセッションに格納
				return mapping.findForward("reissue");		
			}
		}
		//再出力完了
		else if(fmForm.getCommand().equals(IndicatePrintForm.SUCCESS_SAISYRYK)){
			if(session.getAttribute(CommonConst.PO_QUERY_VO) != null){
				fmForm.setSyoriFlg("");
				fmForm.setNewWindow("1");
				session.setAttribute(CommonConst.INFOMSG_KEY,mr.getMessage("info.reissue.end"));
			}else{
				fmForm.setCommand("INIT");
			}
		//クリア
		}else if(fmForm.getCommand().equals(IndicatePrintForm.COMMAND_CLEAR)){
			fmForm.clearAll();
			fmForm.setCommand("INIT");
			return (new ActionForward(mapping.getInput()));
		}
		//最初にメニューからきたとき
		if(fmForm.getCommand().equals("INIT")){
			this.init(fmForm,session);
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

//------------------------------------------------------------------------------------------------

	private void init(IndicatePrintForm fmForm, HttpSession session){
		fmForm.clearAll();
		fmForm.setSyoriFlg("");
		fmForm.setNewWindow("0");
		fmForm.setRb_select(IndicatePrintForm.SELECT_ALL);
   		LogonForm logonForm = (LogonForm)session.getAttribute(CommonConst.user);
		String daiKaiSkbCod = 	logonForm.getDaikaiskbcod();
		String KaiSkbCod = 	logonForm.getKaiskbcod();
		ArrayList kaiList = logonForm.getKaiSkbCodList();
		fmForm.setQueryDaiKaiSkbCod(daiKaiSkbCod);
		fmForm.setQueryKaiSkbCod(KaiSkbCod);
		fmForm.setQueryKaiSkbCodList(kaiList);
		fmForm.setHacSyoDte_Frm_today();//発注書日付Fromに当日日付をセット 2003/07/16 add
		fmForm.setUsrId(logonForm.getUser());//2005/05/23 add
		fmForm.setHyoKbn(logonForm.getHyoKbn());
	}


//------------------------------------------------------------------------------------------------
	
	/**
	 * 伝票発行
	 * 
	 */
	private ActionErrors dnpHakkou (IndicatePrintForm fmForm,HttpServletRequest req,HttpServletResponse res,IndicatePrintVO fmVO,
									PrintOrdersQueryVO poQueryVO){

		ActionErrors errors = null;
		
		ArrayList arr_hac = new ArrayList();
		
		String queryKaiSkbCod = fmForm.getQueryKaiSkbCod();
		String queryDaiKaiSkbCod = fmForm.getQueryDaiKaiSkbCod();
		
		//飛んできたフォームの値をバリューオブジェクトにセット
		fmVO.setDaiKaiSkbCod(queryDaiKaiSkbCod);
		fmVO.setKaiSkbCod(queryKaiSkbCod);
		fmVO.setQueryKaiSkbCodList(fmForm.getQueryKaiSkbCodList());
		fmVO.setUsrId(fmForm.getUsrId());//2005/05/23 add
	
		//コマンド
		fmVO.setCommand(fmForm.getCommand());
		
		if(fmForm.getRb_select() == IndicatePrintForm.SELECT_SEL){
			arr_hac.add(fmForm.getHacCod1().trim());
			arr_hac.add(fmForm.getHacCod2().trim());
			arr_hac.add(fmForm.getHacCod3().trim());
			fmVO.setHacCod_arr(arr_hac);
		}
		
		try{
			//伝票発行実行
			IndicatePrintResult[] results = bzDelegate.doDnpHakkou((IndicatePrintVO)fmVO);
			
			//エラーだったのはないか調べる
			boolean err = false;
			for(int i = 0; i < results.length;i++){
				if(results[i] != null && !results[i].isSuccess()){
					if(errors == null)
						errors = new ActionErrors();
	
					ArrayList errs_hac = results[i].getMap(IndicatePrintResult.KEY_HACCOD);
					String index = "";
					 
					if(errs_hac != null){	
						for(int j = 0; j < errs_hac.size(); j++){
							index = (String)errs_hac.get(j);//エラーのあったIndex
							//発注先コードNOT_EXIST
							errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.input.incorrect_no",index,"発注先コード"));
							err = true;
						}
					}
					//対象データが存在しない
                    if(results[i].getDescription().equals(IndicatePrintDelegate.NOT_EXIST)){
						errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.select.notexist"));
						err = true;
                    }else if(results[i].getDescription().equals("")){
						errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.exception"));
						err = true;
						break;
                    }
				}

			}
			if(!err){//エラーなしのとき
				//発注書印刷用VOに値をセット				
				ArrayList tmpArr = new ArrayList();
				
				for(int i = 0; i < results.length;i++){
					IndicatePrintVO vo = results[i].getVO();
					
					//会社識別コード,発注日,発注書番号,発注先コードのセットをArrayListに格納  2003/09/02 修正 okada
					String str = vo.getKaiSkbCod() + "%" +
									 StringUtils.lpad(vo.getHacSyoDte(),6,"0") + "%" +
						 			 StringUtils.lpad(vo.getHacSyoBng(),6," ") + "%" +
						   			 vo.getHacCod();
					if(tmpArr.indexOf(str) == -1){
						tmpArr.add(str);
					}
					
				}
				poQueryVO.setDaiKaiSkbCod(fmVO.getDaiKaiSkbCod());
				poQueryVO.setKaiSkbCod(fmVO.getKaiSkbCod());
				poQueryVO.setKaiSkbCod_arr(fmVO.getQueryKaiSkbCodList());
				poQueryVO.setPartOfQuery_arr(tmpArr);
				poQueryVO.setHasHacSyoBng(false);//すでに発注書番号を持っている⇒false
				
			}	
		
		}catch(SQLException sqle){
			SystemException se = new SystemException(sqle);
			se.printStackTrace();
			errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.exception",sqle.toString()));
		}		
		return errors;			
	}
	
	
	
	
	/**
	 * 再出力
	 * 
	 */
	private ActionErrors saiSyryk (IndicatePrintForm fmForm,HttpServletRequest req,HttpServletResponse res,IndicatePrintVO fmVO,
								   PrintOrdersQueryVO poQueryVO){

		ActionErrors errors = null;
		HttpSession session = req.getSession();
		String hacSyoPtn = ((LogonForm)session.getAttribute(CommonConst.user)).getHacSyoPtn();
		
		String queryKaiSkbCod = fmForm.getQueryKaiSkbCod();
		String queryDaiKaiSkbCod = fmForm.getQueryDaiKaiSkbCod();
		
		//飛んできたフォームの値をバリューオブジェクトにセット
		fmVO.setDaiKaiSkbCod(queryDaiKaiSkbCod);
		fmVO.setKaiSkbCod(queryKaiSkbCod);
		fmVO.setQueryKaiSkbCodList(fmForm.getQueryKaiSkbCodList());
		fmVO.setUsrId(fmForm.getUsrId());//2005/05/23 add
	
		//コマンド
		fmVO.setCommand(fmForm.getCommand());
		
		if(!fmForm.getHacSyoBng_frm().trim().equals("")){
			
			fmVO.setHacSyoBng_frm(DataFormatUtils.makeHacSyoBng(fmForm.getHacSyoBng_frm().trim(),hacSyoPtn));
			//発注書番号を０埋めして再表示
			fmForm.setHacSyoBng_frm(fmVO.getHacSyoBng_frm());
		}
		if(!fmForm.getHacSyoBng_to().trim().equals("")){

			fmVO.setHacSyoBng_to(DataFormatUtils.makeHacSyoBng(fmForm.getHacSyoBng_to().trim(),hacSyoPtn));
			//発注書番号を０埋めして再表示
			fmForm.setHacSyoBng_to(fmVO.getHacSyoBng_to());
		}
		//2003/06/05
			//発注先コード
		if(!fmForm.getHacCod().trim().equals("")){
			fmVO.setHacCod(fmForm.getHacCod().trim());
		}
			//発注書日付
		if(!fmForm.getHacSyoDte_Frm_Y().trim().equals("") && !fmForm.getHacSyoDte_Frm_M().trim().equals("") &&
		   !fmForm.getHacSyoDte_Frm_D().trim().equals("")){
		   	fmVO.setHacSyoDte_frm(fmForm.getHacSyoDte_Frm_Y(),fmForm.getHacSyoDte_Frm_M(),fmForm.getHacSyoDte_Frm_D());
		   	fmVO.setHacSyoDte_to(fmForm.getHacSyoDte_to_Y(),fmForm.getHacSyoDte_to_M(),fmForm.getHacSyoDte_to_D());
		}

		try{
			//再出力
			IndicatePrintResult[] results = bzDelegate.doSaiSyryk((IndicatePrintVO)fmVO);
		
			//エラーだったのはないか調べる
			boolean err = false;
			for(int i = 0; i < results.length;i++){
				if(results[i] != null && !results[i].isSuccess()){
					if(errors == null)
						errors = new ActionErrors();
						
					//対象データが存在しない
	                if(results[i].getDescription().equals(IndicatePrintDelegate.NOT_EXIST)){
						errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.select.notexist"));
						err = true;
	                }else if(results[i].getDescription().equals("")){
						errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.exception"));
						err = true;
						break;
					}
	                if(results[i].getDescription().equals(IndicatePrintDelegate.NOT_HACCOD)){
	                	errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.input.incorrect","発注先コード"));
	                	err = true;
	                }
				}
			}
			if(!err){//エラーなしのとき
				//発注書印刷用VOに値をセット
				ArrayList finded = results[0].getFinded_arr();
				ArrayList tmpArr = new ArrayList();
				
				for(int i = 0; i<finded.size(); i++){
					IndicatePrintVO vo = (IndicatePrintVO)finded.get(i);
					
					//会社識別コード,発注日,発注書番号,発注先コードのセットをArrayListに格納  2003/09/02 修正 okada
					String str = vo.getKaiSkbCod() + "%" +
								 vo.getHacSyoDte() + "%" +
						 		 vo.getHacSyoBng() + "%" +
						   		 vo.getHacCod();
						   		 
					if(tmpArr.indexOf(str) == -1){
						tmpArr.add(str);
					}
					
				}
				poQueryVO.setDaiKaiSkbCod(fmVO.getDaiKaiSkbCod());
				poQueryVO.setKaiSkbCod(fmVO.getKaiSkbCod());
				poQueryVO.setKaiSkbCod_arr(fmVO.getQueryKaiSkbCodList());
				poQueryVO.setPartOfQuery_arr(tmpArr);
				poQueryVO.setHasHacSyoBng(true);//すでに発注書番号を持っている⇒true
				
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
