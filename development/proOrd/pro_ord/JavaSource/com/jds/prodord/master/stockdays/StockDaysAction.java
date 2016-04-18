/**
* StockDaysAction  在庫日数マスターメンテナンスアクションクラス
*
*	作成日    2003/06/09
*	作成者    （ＮＩＩ）村上  博基 
* 処理概要    処理呼び出しを行い、画面制御を行うクラス。
*
*	[変更履歴]
*        2003/
* 			・
* 
*/

  package com.jds.prodord.master.stockdays;
  import com.jds.prodord.common.*;

  import javax.servlet.http.*;
  import org.apache.struts.action.*;
  import org.apache.struts.util.*;
  import java.sql.*;
  import java.util.*;

  public class StockDaysAction extends Action {

	private static  StockDaysDelegate bzDelegate = new StockDaysDelegate();


//===================================================================

	public ActionForward perform(ActionMapping mapping,ActionForm form
											,HttpServletRequest req,HttpServletResponse res){
		ActionErrors errors = null;
		HttpSession session = req.getSession();
		//フォームのデータ
		StockDaysForm fmForm = (StockDaysForm)form;
		StockDaysVO fmVO = new StockDaysVO();
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

		//***  マスターメニューの在庫日数メンテナンスボタンを押した時  ***

		if(fmForm.getCommand().equals("INIT")){
//		System.out.println("マスターメニューより：在庫日数メンテナンスボタンが押されました");
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


		//***  在庫日数メンテナンスの実行ボタンを押した時  ***

		if(fmForm.getCommand().equals(StockDaysForm.COMMAND_JIKKOU)){

//			System.out.println("在庫日数メンテナンスより：実行ボタンが押されました");

			//処理区分が照会
			if(fmForm.getPrcKbn().equals("1")){
				errors = this.select(fmForm,req,res);
			  if(errors == null){	//処理成功
			  	req.setAttribute(CommonConst.INFOMSG_KEY,mr.getMessage("info.select.end"));
			  }

			//処理区分が登録
			}else if(fmForm.getPrcKbn().equals("2")){
				errors = this.insert(fmForm,req,res);
			  if(errors == null){	//処理成功
					req.setAttribute(CommonConst.INFOMSG_KEY,mr.getMessage("info.insert.end"));
			  }

			//処理区分が変更
			}else if(fmForm.getPrcKbn().equals("3")){
				errors = this.update(fmForm,req,res);
				if(errors == null){	//処理成功
					req.setAttribute(CommonConst.INFOMSG_KEY,mr.getMessage("info.update.end"));
				}

			//処理区分が削除
			}else if(fmForm.getPrcKbn().equals("4")){
				errors = this.delete(fmForm,req,res);
				if(errors == null){	//処理成功
					req.setAttribute(CommonConst.INFOMSG_KEY,mr.getMessage("info.delete.end"));
				}
			}


		//***  在庫日数メンテナンスのクリアボタンを押した時  ***
			}else if(fmForm.getCommand().equals(StockDaysForm.COMMAND_CLEAR)){

//				System.out.println("在庫日数メンテナンスより：クリアボタンが押されました");
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
	
//=====  照会メソッド  ================================================================

	private ActionErrors select (StockDaysForm fmForm,HttpServletRequest req,
								 HttpServletResponse res){

		ActionErrors errors = null;
		StockDaysVO fmVO = new StockDaysVO();	
		String queryKaiSkbCod = fmForm.getQueryKaiSkbCod();
		String queryDaiKaiSkbCod = fmForm.getQueryDaiKaiSkbCod();
		
		
		//飛んできたフォームの値をバリューオブジェクトにセット
		fmVO.setDaiKaiSkbCod(queryDaiKaiSkbCod);
		fmVO.setKaiSkbCod(queryKaiSkbCod);
		fmVO.setQueryKaiSkbCodList(fmForm.getQueryKaiSkbCodList());


		String[] kai = fmForm.getKaiSkbCod();
		String[] ket = fmForm.getKetCod();


		//会社識別コード	
		fmVO.setKaiSkbCod(kai[0]);
		//ランク
		fmVO.setRank(fmForm.getRank());	
		//形態コード
		fmVO.setKetCod(ket[0]);

		try{
			//検索実行
			StockDaysVO[] resultFmVOs = bzDelegate.doFind(fmVO);

			fmForm.removeStockDaysVO();
				
			if(resultFmVOs.length != 0){
				//検索結果をフォームにセット
				fmForm.setSize(Math.max((resultFmVOs.length),StockDaysForm.DEFAULT_ROW_COUNT));
				fmForm.clearTableField();

				for(int i = 0;i < resultFmVOs.length;i++){
					fmForm.setOutKaiSkbCod(i,resultFmVOs[i].getKaiSkbCod());
					fmForm.setOutRank(i,resultFmVOs[i].getRank());
					fmForm.setOutKetCod(i,resultFmVOs[i].getKetCod());
					fmForm.setOutStockDays(i,resultFmVOs[i].getOutStockDays());

					fmForm.addStockDaysVO(resultFmVOs[i]);
				}
			}else{
				fmForm.setSize(StockDaysForm.DEFAULT_ROW_COUNT);
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

	

//=====  登録メソッド  ================================================================

	private ActionErrors insert (StockDaysForm fmForm,HttpServletRequest req,HttpServletResponse res){

		ActionErrors errors = null;
		StockDaysVO fmVO = new StockDaysVO();	
		String queryKaiSkbCod = fmForm.getQueryKaiSkbCod();
		String queryDaiKaiSkbCod = fmForm.getQueryDaiKaiSkbCod();

		String[] kai = fmForm.getKaiSkbCod();
		String[] ket = fmForm.getKetCod();
		List lisFmVO = new ArrayList();  //バリューオブジェクトを入れる

		//飛んできたフォームの値をバリューオブジェクトにセット
		fmVO.setDaiKaiSkbCod(queryDaiKaiSkbCod);
		fmVO.setKaiSkbCod(kai[0]);
		fmVO.setRank(fmForm.getRank());
		fmVO.setKetCod(ket[0]);

		for(int i = 0; i < StockDaysForm.DEFAULT_ROW_COUNT;i++){

			fmVO.setOutStockDays(fmForm.getOutStockDays(i));

		}

		lisFmVO.add(fmVO);

		try{
			//登録実行
			StockDaysResult result = bzDelegate.doInsert(fmVO);
		
			//エラーチェック
			if(result != null && !result.isSuccess()){
				if(errors == null)
					errors = new ActionErrors();

				 //登録しようとしたデータがすでに存在
				if(result.getDescription().equals(StockDaysDelegate.EXIST))	
					errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.insert.duplicate"));
				 //登録しようとしたデータが論理削除中

				else if(result.getDescription().equals(StockDaysDelegate.LOGICAL_DELETE))	
					errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.insert.logicaldelete"));

			}

					
			if(errors == null){
				for(int i = 0;i < StockDaysForm.DEFAULT_ROW_COUNT;i++){

					fmForm.setOutKaiSkbCod(i,fmVO.getKaiSkbCod());
					fmForm.setOutRank(i,fmVO.getRank());
					fmForm.setOutKetCod(i,fmVO.getKetCod());
				
				}
				
			}
			
		}catch(SQLException sqle){
			sqle.printStackTrace();
			errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.exception",sqle.toString()));
		}	
		
		return errors;			
	}



//=====  更新メソッド  ================================================================

	private ActionErrors update (StockDaysForm fmForm,HttpServletRequest req,HttpServletResponse res){

		ActionErrors errors = null;
		String queryKaiSkbCod = fmForm.getQueryKaiSkbCod();
		String queryDaiKaiSkbCod = fmForm.getQueryDaiKaiSkbCod();

		List lisFmVO = new ArrayList();  //バリューオブジェクトを入れる
			
		for(int i = 0; i < fmForm.getSize();i++){

			StockDaysVO fmVOs = fmForm.getStockDaysVO(i);

			if(!fmForm.getStockDays().trim().equals("")){
				fmVOs.setOutStockDays(fmForm.getStockDays());  //一括変更
			}else{
				if(!fmForm.getExecute(i)){  //faleseでなければ
					lisFmVO.add(null);	
					continue;
				}
				fmVOs.setOutStockDays(fmForm.getOutStockDays(i));  //通常変更
			}
			lisFmVO.add(fmVOs);	
		}
		
		try{
			//更新実行

			StockDaysResult[] results = bzDelegate.doUpdate((StockDaysVO[])lisFmVO.toArray(new StockDaysVO[lisFmVO.size()]));

			
			//エラーだったのはないか調べる
			for(int i = 0; i < results.length;i++){
				if(results[i] != null && !results[i].isSuccess()){
					if(errors == null)
						errors = new ActionErrors();
						
					 //ほかのユーザーが変更済み
					if(results[i].getDescription().equals(StockDaysDelegate.ANOTHER_USER_UPDATE)||
					   results[i].getDescription().equals(StockDaysDelegate.LOGICAL_DELETE)||
   					   results[i].getDescription().equals(StockDaysDelegate.NOT_EXIST))
						errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.anotheruserupdate",String.valueOf(i+1)));
				}
			}


			if(errors == null){
				for(int i = 0; i < results.length;i++){
					if(results[i] == null)
						continue;

					fmForm.setExecute(i,false);
					fmForm.setStockDaysVO(i, results[i].getVO());
	
					fmForm.setOutStockDays(i,results[i].getVO().getOutStockDays());
					fmForm.setStockDays("");
				}
			}

		}catch(SQLException sqle){
			sqle.printStackTrace();
			errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.exception",sqle.toString()));
		}

		return errors;
	}


//=====  削除メソッド  ================================================================

	private ActionErrors delete (StockDaysForm fmForm,HttpServletRequest req,HttpServletResponse res){
		ActionErrors errors = null;
		List lisFmVO = new ArrayList();  //バリューオブジェクトを入れる
				
		for(int i = 0; i < fmForm.getSize();i++){
			if(!fmForm.getExecute(i)){//チェックなし
				lisFmVO.add(null);	
				continue;
			}
			//チェックあり
			StockDaysVO fmVO = fmForm.getStockDaysVO(i);
			lisFmVO.add(fmVO);
		}
		try{
			//削除実行
			StockDaysResult[] result = bzDelegate.doDelete((StockDaysVO[])lisFmVO.toArray(new StockDaysVO[lisFmVO.size()]));

			//エラーチェック
		for(int i = 0; i < result.length;i++){
			if(result[i] != null && !result[i].isSuccess()){
				if(errors == null)
					errors = new ActionErrors();

				 //ほかのユーザーが変更済み
				if(result[i].getDescription().equals(StockDaysDelegate.ANOTHER_USER_UPDATE)) 
					errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.anotheruserupdate",String.valueOf(1)));
				 //削除しようとしたデータが論理削除中 か  存在しない
				else if(result[i].getDescription().equals(StockDaysDelegate.LOGICAL_DELETE)||
					    result[i].getDescription().equals(StockDaysDelegate.NOT_EXIST))	

					errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.select.notexist"));

			}
		}
			//削除したレコードを画面から消す
			if(errors == null){
				int index = 0;
				for(int i = 0; i < result.length;i++,index++){
					if(result[i] == null)
						continue;

					fmForm.removeTableField(index);
					fmForm.removeStockDaysVO(index);
					index--;
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


