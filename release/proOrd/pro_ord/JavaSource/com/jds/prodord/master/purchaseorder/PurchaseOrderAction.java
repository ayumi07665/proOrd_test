/**
* PurchaseOrderAction  仕入発注先マスター照会アクションクラス
*
*	作成日    2003/05/19
*	作成者    （ＮＩＩ）村上  博基 
* 処理概要    処理呼び出しを行い、画面制御を行うクラス。
*/



package com.jds.prodord.master.purchaseorder;

import javax.servlet.http.*;
import org.apache.struts.action.*;
import org.apache.struts.util.*;
import java.sql.*;
import java.util.*;
import com.jds.prodord.common.*;

public class PurchaseOrderAction extends Action {
	
	private static final String INFOMSG_KEY = "INFOMSG_KEY";

	private static  PurchaseOrderDelegate bzDelegate = new PurchaseOrderDelegate();



	public ActionForward perform(ActionMapping mapping,ActionForm form,HttpServletRequest req,HttpServletResponse res){

		ActionErrors errors = null;
		HttpSession session = req.getSession();		//フォームのデータ
		PurchaseOrderForm fmForm = (PurchaseOrderForm)form;
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



	PurchaseOrderVO fmVO = new PurchaseOrderVO();
	
//	System.out.println("Commandの中身:"+fmForm.getCommand());

		//--------以下、フォームの押したボタンから処理分岐を行う

		//***  マスターメニューの仕入発注先ボタンを押した時  ***

		if(fmForm.getCommand().equals("INIT")){
//		System.out.println("マスターメニューより：品番マスター照会ボタンが押されました");
			fmForm.clearAll();

	   		com.jds.prodord.register.LogonForm logonForm
		  			= (com.jds.prodord.register.LogonForm)session.getAttribute("user");

			String daiKaiSkbCod = 	logonForm.getDaikaiskbcod();
			ArrayList kaiList = logonForm.getKaiSkbCodList();

			fmForm.setDaiKaiSkbCod(daiKaiSkbCod);
			fmForm.setKaiSkbCodList(kaiList);			

		}


		//***  仕入発注先マスター照会の実行ボタンを押した時  ***

		if(fmForm.getCommand().equals(PurchaseOrderForm.COMMAND_JITTKOU)){

//			System.out.println("仕入発注先マスター照会より：実行ボタンが押されました");
				errors = this.find(fmForm,req,res);
				
			if(errors == null)
				req.setAttribute(INFOMSG_KEY,mr.getMessage("info.select.end"));


		//***  仕入発注先マスター照会のクリアボタンを押した時  ***

		}else if(fmForm.getCommand().equals(PurchaseOrderForm.COMMAND_CLEAR)){

//			System.out.println("仕入発注先マスター照会より：クリアボタンが押されました");

			fmForm.clearAll();
			fmForm.setCommand("INIT");
			return (new ActionForward(mapping.getInput()));
		}


		//エラーあり
		if(errors != null){
			super.saveErrors(req,errors);
    		return (new ActionForward(mapping.getInput()));
		}

		//エラーなし
		return mapping.findForward("success");		
	}


//===============================================================================

	//*** 検索 ***

	private ActionErrors find (PurchaseOrderForm fmForm,HttpServletRequest req,HttpServletResponse res){

//	System.out.println("検索準備を行います");

		ActionErrors errors = null;
		PurchaseOrderVO queryFmVO = new PurchaseOrderVO();

		//飛んできたフォームの値をバリューオブジェクトにセット
		queryFmVO.setDaiKaiSkbCod(fmForm.getDaiKaiSkbCod());
		queryFmVO.setKaiSkbCodList(fmForm.getKaiSkbCodList());
		queryFmVO.setHattyuCod(fmForm.getHattyuCod());


//		System.out.println("検索処理を実行します");

		try{
			//検索実行
			PurchaseOrderVO resultFmVO = bzDelegate.doFind(queryFmVO);

			fmForm.clearTableField1();

            if(resultFmVO.getFinded_flg()){	

				//検索結果をフォームにセット

				fmForm.setHattyuCod(resultFmVO.getHattyuCod());
				fmForm.setOrderName1(resultFmVO.getOrderName1());
				fmForm.setOrderName2(resultFmVO.getOrderName2());
				fmForm.setOrderAdr1(resultFmVO.getOrderAdr1());
				fmForm.setOrderAdr2(resultFmVO.getOrderAdr2());
				fmForm.setTelNum(resultFmVO.getTelNum());
				fmForm.setPostNum(resultFmVO.getPostNum());

            }
            
	        else{
				//データが存在しません
				errors = new ActionErrors();
				errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.select.notexist"));

	        }

		}catch(SQLException sqle){
			sqle.printStackTrace();

			errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.exception",sqle.toString()));
			
		}
		return errors;

	}

}