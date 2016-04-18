/**
* ManualOrderAction  マニュアル発注指示アクションクラス
*
*	作成日    2003/04/25
*	作成者    （ＮＩＩ）岡田 夏美
* 処理概要    処理呼び出しを行い、画面制御を行うクラス。
*
*	[変更履歴]
*       2003/05/29（ＮＩＩ）蛭田 敬子
*  			・条件項目（会社・メーカー分類・形態コード・邦洋区分）の追加。
* 		2003/07/23 （ＮＩＩ）岡田 夏美
* 			・ソート条件追加（形態コード、ソートキー）。
* 		2003/09/10（ＮＩＩ）岡田 夏美
* 			・記号番号の記号部省略入力対応
* 		2004/04/02 (NII) 森
* 			・副資材発注の修正(分類コード１複数存在に対応)
* 		2004/07/02（ＮＩＩ）蛭田
* 			・コピー＆ペースト機能追加
*		2005/02/03（ＮＩＩ）蛭田
*			・場所コードで納品先名を取得するように修正
*		2005/05/25（ＮＩＩ）蛭田
*			・発注書一括出力対応（ユーザーＩＤの追加）
* 		2006/05/10（ＮＩＩ）田端 康教
* 			・キング社のランクをＣまで
* 
*/

package com.jds.prodord.indicate.manualorder;
import com.jds.prodord.common.*;
import com.jds.prodord.order.prsorder.*;

import javax.servlet.http.*;
import org.apache.struts.action.*;
import java.sql.*;
import java.util.*;
import java.io.*;

public class ManualOrderAction extends Action {
		
	/**
	 * ビジネスロジック
	 */
	private static  ManualOrderDelegate bzDelegate = new ManualOrderDelegate();
	
	
	public ActionForward perform(ActionMapping mapping,ActionForm form,HttpServletRequest req,HttpServletResponse res){
		ActionErrors errors = null;
		HttpSession session = req.getSession();
		//フォームのデータ
		ManualOrderForm fmForm = (ManualOrderForm)form;
		ManualOrderVO fmVO = new ManualOrderVO();
		

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
		
		//ボタンが貼り付け 2004/06/29 add
		if(fmForm.getPaste().equals("貼り付け")){
			dataPaste(fmForm);
			fmForm.setCpPaste("");
			fmForm.setPaste("");//貼り付けボタンリセット
			return (new ActionForward(mapping.getInput()));
		}

		//--------以下、フォームの押したボタンから処理分岐を行う
		//最初にメニューからきたとき、照会結果画面から「戻る」ボタンで戻ってきたとき
		if(fmForm.getCommand().equals("INIT")){
			if(fmForm.getFromMenuSgn()){
				fmForm.clearAll();
				fmForm.setRb_select(ManualOrderForm.SELECT_HBI);
		   		com.jds.prodord.register.LogonForm logonForm 
			  			= (com.jds.prodord.register.LogonForm)session.getAttribute(CommonConst.user);
				String daiKaiSkbCod = 	logonForm.getDaikaiskbcod();
				String KaiSkbCod = 	logonForm.getKaiskbcod();
				ArrayList kaiList = logonForm.getKaiSkbCodList();
				fmForm.setQueryDaiKaiSkbCod(daiKaiSkbCod);
				fmForm.setQueryKaiSkbCod(KaiSkbCod);
				fmForm.setQueryKaiSkbCodList(kaiList);
				fmForm.setBshCod(logonForm.getBshCod());//2005/02/03 add
				fmForm.setUsrId(logonForm.getUser());//2005/05/23 add
				//選択項目のオプションをセット
				fmForm.setKaiSkbCodOptions(kaiList);
				fmForm.setKetCodOptions(logonForm.getKetNmList());
				fmForm.setMkrBunOptions(logonForm.getMkrBunList());
				
			}
			fmForm.setFromMenuSgn(false);
			fmForm.setCheckBoxValue();
		    //発注書・照会結果画面のBEANをremove
		    session.removeAttribute(CommonConst.FORMBEAN_NAME_PRSORDER);
			session.removeAttribute(CommonConst.BEAN_NAME_PRSORDER_LEFT);
			session.removeAttribute(CommonConst.PRSORDER_VOS);
		}
		//ボタンがプレス発注
		if(fmForm.getCommand().equals(ManualOrderForm.COMMAND_PRSHACHU)){
			errors = this.prsHachu(fmForm,req,res,fmVO);

			if(errors == null){	//処理成功

				session.setAttribute(CommonConst.PRSORDER_VOS,fmVO.getPrsVOs());	
				return mapping.findForward("next");	
			}
		//ボタンが副資材発注
		}else if(fmForm.getCommand().equals(ManualOrderForm.COMMAND_FUKUHACHU)){
			errors = this.fukHachu(fmForm,req,res,fmVO);
			if(errors == null){	//処理成功
				session.setAttribute(CommonConst.PRSORDER_VOS,fmVO.getPrsVOs());	
				return mapping.findForward("next");	
			}

		//ボタンがクリア
		}else if(fmForm.getCommand().equals(ManualOrderForm.COMMAND_CLEAR)){
			fmForm.clearAll();
			fmForm.setCommand("INIT");
			return (new ActionForward(mapping.getInput()));
		
		//ボタンが貼り付け　2004/06/29 add
		}else if(fmForm.getCommand().equals(ManualOrderForm.COMMAND_PASTE)){
			dataPaste(fmForm);
			return (new ActionForward(mapping.getInput()));
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
	 * プレス発注
	 * 
	 */
	private ActionErrors prsHachu(ManualOrderForm fmForm,HttpServletRequest req,HttpServletResponse res,ManualOrderVO fmVO){

		ActionErrors errors = null;
		
		ArrayList arr_kai = new ArrayList();
		ArrayList arr_kig = new ArrayList();
		ArrayList arr_hyo = new ArrayList();
		ArrayList arr_ket = new ArrayList();
		ArrayList arr_mkr = new ArrayList();
		
		String queryKaiSkbCod = fmForm.getQueryKaiSkbCod();
		String queryDaiKaiSkbCod = fmForm.getQueryDaiKaiSkbCod();
		
		//飛んできたフォームの値をバリューオブジェクトにセット
		fmVO.setDaiKaiSkbCod(queryDaiKaiSkbCod);
		fmVO.setQueryKaiSkbCod(queryKaiSkbCod);
		fmVO.setQueryKaiSkbCodList(fmForm.getQueryKaiSkbCodList());
		fmVO.setBshCod(fmForm.getBshCod());//2005/02/03 add
		fmVO.setUsrId(fmForm.getUsrId());//2005/05/23 add
	
		//コマンド
		fmVO.setCommand(fmForm.getCommand());
		
		//区分
		fmVO.setKbn(fmForm.getKbn());


		/** 会社・メーカー・形態・邦洋・発売日  指定のとき **/
		//発売日
		if(fmForm.getRb_select() == ManualOrderForm.SELECT_HBI){
			fmVO.setHbiDte1(fmForm.getHbiDte1());
			fmVO.setHbiDte2(fmForm.getHbiDte2());
			fmVO.setHbiDte3(fmForm.getHbiDte3());
			fmVO.setHbiDte4(fmForm.getHbiDte4());
			fmVO.setHbiDte5(fmForm.getHbiDte5());


			//会社識別コード	
			if(fmForm.getRb_kaiSkbCod() == ManualOrderForm.RB_ALL){
				fmVO.setKaiSkbCod_arr(fmVO.getQueryKaiSkbCodList());
			}else if(fmForm.getRb_kaiSkbCod() == ManualOrderForm.RB_SELECT){
				String[] kai = fmForm.getKaiSkbCod();
				for(int i = 0; i<kai.length; i++){
					arr_kai.add(kai[i]);
				}
				fmVO.setKaiSkbCod_arr(arr_kai);			
			}
		
			//形態コード
			if(fmForm.getRb_ketCod() == ManualOrderForm.RB_SELECT){
				String[] ket = fmForm.getKetCod();
				for(int i = 0; i<ket.length; i++){
					arr_ket.add(ket[i]);
				}
				fmVO.setKetCod_arr(arr_ket);			
			}
			//メーカー分類
			if(fmForm.getRb_mkrBun() == ManualOrderForm.RB_SELECT){
				String[] mkr = fmForm.getMkrBun();
				for(int i = 0; i<mkr.length; i++){
					arr_mkr.add(mkr[i]);
				}
				fmVO.setMkrBun_arr(arr_mkr);			
			}
			//邦洋区分
			if(fmForm.getRb_hyoKbn() == ManualOrderForm.RB_SELECT){
				String[] hyoKbn = fmForm.getHyoKbn();
				for(int i = 0; i<hyoKbn.length; i++){
					arr_hyo.add(hyoKbn[i]);
				}
				fmVO.setHyoKbn_arr(arr_hyo);			
			}
			//ソート条件
			fmVO.setSort_ketCod(fmForm.getSort_ketCod());
			fmVO.setSort_sortKey(fmForm.getSort_sortKey());
		}
		
		/** 追加品番  指定のとき **/
		//追加品番
		if(fmForm.getRb_select() == ManualOrderForm.SELECT_HIN){
			//会社識別コード
			fmVO.setKaiSkbCod_arr(fmVO.getQueryKaiSkbCodList());
			//記号番号
			arr_kig = DataFormatUtils.supplement_kigBng(fmForm.getKigBngStr_List());//記号部省略入力対応 2003/09/10
			fmVO.setKigBng_arr(arr_kig);
		}
		fmForm.setCheckBoxValueToArray();
		
		if(queryDaiKaiSkbCod.equals(com.jds.prodord.common.CommonConst.KAICOD_K)){
		//ランクチェック
			try{
				ManualOrderResult result = bzDelegate.doRankCheck((ManualOrderVO)fmVO);
	
				//エラーだったのはないか調べる
				if(result != null && !result.isSuccess()){
					if(errors == null)
						errors = new ActionErrors();
	
					ArrayList errs_kig = result.getMap(ManualOrderResult.KEY_KIGBNG);
					String index = "";
					 //品番が存在しない
					if(errs_kig != null){	
						for(int i = 0; i < errs_kig.size(); i++){
							String[] err_kig = (String[])errs_kig.get(i);
							index = err_kig[0];//エラーのあったIndex
							int type  = Integer.parseInt(err_kig[1]);//エラーの種類
							if(type == 1)//ManualOrderDAO.NOT_EXIST
								errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.input.incorrect_no",index,"品番"));
							if(type == 2)//ManualOrderDAO.RANK_ERR
								errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.input.error_no",index,"品番","ランク"));
						}
					}
				}
			}catch(SQLException sqle){
				SystemException se = new SystemException(sqle);
				se.printStackTrace();
				errors = new ActionErrors();
				errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.exception",sqle.toString()));
			}
		}
		
		if(errors == null || errors.empty()){
			//ブランクの要素があったら詰めてセットし直す
			fmVO.setKigBng_arr(DataFormatUtils.removeBlankElement(fmVO.getKigBng_arr()));

			try{
				PrsOrderVO[] prsVOs = bzDelegate.doPrsHachu(fmVO,fmForm);
				fmVO.setPrsVOs(prsVOs);

				//検索結果が０件だったら
				if(prsVOs.length == 0){
					errors = new ActionErrors();
					errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.select.notexist"));
				}	


			}catch(SQLException sqle){
				SystemException se = new SystemException(sqle);
				se.printStackTrace();
				errors = new ActionErrors();
				errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.exception",sqle.toString()));
			}		
		}
		return errors;			
	}
	

	/**
	 * 副資材発注
	 * 
	 */
	private ActionErrors fukHachu 
			(ManualOrderForm fmForm,HttpServletRequest req,HttpServletResponse res,ManualOrderVO fmVO){

		HttpSession session = req.getSession();
		ActionErrors errors = null;
		
		ArrayList arr_kai = new ArrayList();
		ArrayList arr_kig = new ArrayList();
		ArrayList arr_ket = new ArrayList();
		ArrayList arr_mkr = new ArrayList();
		ArrayList arr_hyo = new ArrayList();
		
		com.jds.prodord.register.LogonForm logonForm 
				= (com.jds.prodord.register.LogonForm)session.getAttribute(CommonConst.user);

		
		String queryKaiSkbCod = fmForm.getQueryKaiSkbCod();
		String queryDaiKaiSkbCod = fmForm.getQueryDaiKaiSkbCod();
		
		//飛んできたフォームの値をバリューオブジェクトにセット
		fmVO.setDaiKaiSkbCod(queryDaiKaiSkbCod);
		fmVO.setQueryKaiSkbCod(queryKaiSkbCod);
		fmVO.setQueryKaiSkbCodList(fmForm.getQueryKaiSkbCodList());
		fmVO.setUsrId(fmForm.getUsrId());//2005/05/25 add
	
		//コマンド
		fmVO.setCommand(fmForm.getCommand());
		
		//区分
		fmVO.setKbn(fmForm.getKbn());
		

		/** 会社・メーカー・形態・邦洋・発売日  指定のとき **/
		//発売日
		if(fmForm.getRb_select() == ManualOrderForm.SELECT_HBI){
			fmVO.setHbiDte1(fmForm.getHbiDte1());
			fmVO.setHbiDte2(fmForm.getHbiDte2());
			fmVO.setHbiDte3(fmForm.getHbiDte3());
			fmVO.setHbiDte4(fmForm.getHbiDte4());
			fmVO.setHbiDte5(fmForm.getHbiDte5());
			
			//会社識別コード	
			if(fmForm.getRb_kaiSkbCod() == ManualOrderForm.RB_ALL){
				fmVO.setKaiSkbCod_arr(fmVO.getQueryKaiSkbCodList());
			}else if(fmForm.getRb_kaiSkbCod() == ManualOrderForm.RB_SELECT){
				String[] kai = fmForm.getKaiSkbCod();
				for(int i = 0; i<kai.length; i++){
					arr_kai.add(kai[i]);
				}
				fmVO.setKaiSkbCod_arr(arr_kai);			
			}
			
			//形態コード
			if(fmForm.getRb_ketCod() == ManualOrderForm.RB_SELECT){
				String[] ket = fmForm.getKetCod();
				for(int i = 0; i<ket.length; i++){
					arr_ket.add(ket[i]);
				}
				fmVO.setKetCod_arr(arr_ket);			
			}
			//メーカー分類
			if(fmForm.getRb_mkrBun() == ManualOrderForm.RB_SELECT){
				String[] mkr = fmForm.getMkrBun();
				for(int i = 0; i<mkr.length; i++){
					arr_mkr.add(mkr[i]);
				}
				fmVO.setMkrBun_arr(arr_mkr);			
			}
			//邦洋区分
			if(fmForm.getRb_hyoKbn() == ManualOrderForm.RB_SELECT){
				String[] hyoKbn = fmForm.getHyoKbn();
				for(int i = 0; i<hyoKbn.length; i++){
					arr_hyo.add(hyoKbn[i]);
				}
				fmVO.setHyoKbn_arr(arr_hyo);			
			}
			//ソート条件
			fmVO.setSort_ketCod(fmForm.getSort_ketCod());
			fmVO.setSort_sortKey(fmForm.getSort_sortKey());
		}
		

		/** 追加品番  指定のとき **/
		//追加品番
		if(fmForm.getRb_select() == ManualOrderForm.SELECT_HIN){
			//会社識別コード
			fmVO.setKaiSkbCod_arr(fmVO.getQueryKaiSkbCodList());
			//記号番号
			arr_kig = DataFormatUtils.supplement_kigBng(fmForm.getKigBngStr_List());//記号部省略入力対応 2003/09/10
			fmVO.setKigBng_arr(arr_kig);
		}
		fmForm.setCheckBoxValueToArray();
		
		if(queryDaiKaiSkbCod.equals(com.jds.prodord.common.CommonConst.KAICOD_K)){
			//ランクチェック
			try{
				ManualOrderResult result = bzDelegate.doRankCheck((ManualOrderVO)fmVO);

				//エラーだったのはないか調べる
				if(result != null && !result.isSuccess()){
					if(errors == null)
						errors = new ActionErrors();

					ArrayList errs_kig = result.getMap(ManualOrderResult.KEY_KIGBNG);
					String index = "";
					 //品番が存在しない
					if(errs_kig != null){	
						for(int i = 0; i < errs_kig.size(); i++){
							String[] err_kig = (String[])errs_kig.get(i);
							index = err_kig[0];//エラーのあったIndex
							int type  = Integer.parseInt(err_kig[1]);//エラーの種類
							if(type == 1)//ManualOrderDAO.NOT_EXIST
								errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.input.incorrect_no",index,"品番"));
							if(type == 2)//ManualOrderDAO.RANK_ERR
								errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.input.error_no",index,"品番","ランク"));
						}
					}
				}	
			}catch(SQLException sqle){
				SystemException se = new SystemException(sqle);
				se.printStackTrace();
				errors = new ActionErrors();
				errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.exception",sqle.toString()));
			}		
		}

		if(errors == null || errors.empty()){
			//ブランクの要素があったら詰めてセットし直す
			fmVO.setKigBng_arr(DataFormatUtils.removeBlankElement(fmVO.getKigBng_arr()));
			try{
			
				PrsOrderVO[] prsVOs = bzDelegate.doFukHachu(fmVO,fmForm);
				fmVO.setPrsVOs(prsVOs);

				//検索結果が０件だったら
				if(prsVOs.length == 0){
					errors = new ActionErrors();
					errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.select.notexist"));
				}
		

			}catch(SQLException sqle){
				SystemException se = new SystemException(sqle);
				se.printStackTrace();
				errors = new ActionErrors();
				errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.exception",sqle.toString()));
			}		
		}
		return errors;			
	}

	

	
//---------------------------------------------------------------------------------------------------	
	
	/** 
	 * 貼り付け
	 * @param fmForm
	 */
	public void dataPaste(ManualOrderForm fmForm){
		
		final String tab = "\t";
		int formSize = 15;
		
		String data = fmForm.getCpPaste();
		BufferedReader br = new BufferedReader(new StringReader(data));
		ArrayList kigBngList = new ArrayList();
		String txt = "";
		int count = DataFormatUtils.getDataRowCount(data);


		try {

			for (int i = 0; i < count; i++) {

				if((txt = br.readLine()) != null){

					txt = DataFormatUtils.insertSpace(txt, tab);
					StringTokenizer line = new StringTokenizer(txt, tab);	

					if(line.hasMoreTokens())
						kigBngList.add(line.nextToken().trim());

				}

			}

			fmForm.setKigBng_List(kigBngList);

		}catch(IOException ex){
			SystemException se = new SystemException(ex);
			se.printStackTrace();
		}catch(Exception e){
			SystemException se = new SystemException(e);
			se.printStackTrace();
		}
	}
	
}
