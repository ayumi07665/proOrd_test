/**
* SubMateMasterAction  副資材マスターメンテナンス  アクションクラス
*
*	作成日    2003/07/16
*	作成者    （ＮＩＩ）村上  博基 
* 処理概要    処理呼び出しを行い、画面制御を行うクラス。
*
*	[変更履歴]
*        2003/
* 			・
* 
*/

  package com.jds.prodord.master.submatemaster;
  import com.jds.prodord.common.*;

  import javax.servlet.http.*;
  import org.apache.struts.action.*;
  import org.apache.struts.util.*;
  import java.sql.*;
  import java.util.*;

  public class SubMateMasterAction extends Action {

	private static  SubMateMasterDelegate bzDelegate = new SubMateMasterDelegate();


//===================================================================

	public ActionForward perform(ActionMapping mapping,ActionForm form
											,HttpServletRequest req,HttpServletResponse res){
		ActionErrors errors = null;
		HttpSession session = req.getSession();
		//フォームのデータ
		SubMateMasterForm fmForm = (SubMateMasterForm)form;
		SubMateMasterVO fmVO = new SubMateMasterVO();
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
//		System.out.println("マスターメニューより：ボタンが押されました");
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
			
			fmForm.setHyoKbn(logonForm.getHyoKbn());
			
//			System.out.println("代表会社："+ daiKaiSkbCod);
//			System.out.println("会社識別："+ KaiSkbCod);

		}


		//*** 副資材マスターメンテの実行ボタンを押した時  ***

		if(fmForm.getCommand().equals(SubMateMasterForm.COMMAND_JIKKOU)){

//			System.out.println("副資材マスターメンテメンテより：実行ボタンが押されました");

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
			}else if(fmForm.getCommand().equals(SubMateMasterForm.COMMAND_CLEAR)){

//				System.out.println("クリアボタンが押されました");
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

	private ActionErrors select (SubMateMasterForm fmForm,HttpServletRequest req,
								 HttpServletResponse res){

		ActionErrors errors = null;
		SubMateMasterVO fmVO = new SubMateMasterVO();
		String queryKaiSkbCod = fmForm.getQueryKaiSkbCod();
		String queryDaiKaiSkbCod = fmForm.getQueryDaiKaiSkbCod();
		
		//飛んできたフォームの値をバリューオブジェクトにセット
		fmVO.setDaiKaiSkbCod(queryDaiKaiSkbCod);
		fmVO.setKaiSkbCod(queryKaiSkbCod);
		fmVO.setQueryKaiSkbCodList(fmForm.getQueryKaiSkbCodList());

		String[] kai = fmForm.getKaiSkbCod();



		//会社識別コード	
		fmVO.setKaiSkbCod(kai[0]);
//			System.out.println("会社識別："+ fmVO.getKaiSkbCod());		
		
		//副資材ｺｰﾄﾞ
		fmVO.setHukSziCod(fmForm.getHukSziCod());	

		try{
			//検索実行
			SubMateMasterVO[] resultFmVOs = bzDelegate.doFind(fmVO);

			fmForm.removeSubMateMasterVO();
				
			if(resultFmVOs.length != 0){
				//検索結果をフォームにセット
				fmForm.setSize(Math.max((resultFmVOs.length),SubMateMasterForm.DEFAULT_ROW_COUNT));
				fmForm.clearTableField();

				for(int i = 0;i < resultFmVOs.length;i++){
					fmForm.setHidDaiKaiSkbCod(i,resultFmVOs[i].getHidDaiKaiSkbCod());
					fmForm.setHidKaiSkbCod(i,resultFmVOs[i].getHidKaiSkbCod());
					fmForm.setOutHukSziCod(i,resultFmVOs[i].getOutHukSziCod());
					fmForm.setOutBunruiCod(i,resultFmVOs[i].getOutBunruiCod());
					fmForm.setOutHatcCod(i,resultFmVOs[i].getOutHatcCod());
					fmForm.setOutHukSziMei(i,resultFmVOs[i].getOutHukSziMei());
					fmForm.setHidUpdDte(i,resultFmVOs[i].getHidUpdDte());
					fmForm.setHidUpdJkk(i,resultFmVOs[i].getHidUpdJkk());
					
					fmForm.addSubMateMasterVO(resultFmVOs[i]);
				}
			}else{
				fmForm.setSize(SubMateMasterForm.DEFAULT_ROW_COUNT);
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

	private ActionErrors insert (SubMateMasterForm fmForm,HttpServletRequest req,HttpServletResponse res){

		ActionErrors errors = null;
		SubMateMasterVO fmVO = new SubMateMasterVO();	
		String queryDaiKaiSkbCod = fmForm.getQueryDaiKaiSkbCod();

		List lisFmVO = new ArrayList();  //バリューオブジェクトを入れる


		//飛んできたフォームの値をバリューオブジェクトにセット
		fmVO.setDaiKaiSkbCod(queryDaiKaiSkbCod);
		fmVO.setQueryKaiSkbCodList(fmForm.getQueryKaiSkbCodList());
		fmVO.setHukSziCod(fmForm.getHukSziCod());
		
		String[] kai = fmForm.getKaiSkbCod();

		//会社識別コード	
		fmVO.setKaiSkbCod(kai[0]);

		lisFmVO.add(fmVO);

		for(int i = 0; i < SubMateMasterForm.DEFAULT_ROW_COUNT;i++){
			fmVO.setOutBunruiCod(fmForm.getOutBunruiCod(i).trim());
			fmVO.setOutHatcCod(fmForm.getOutHatcCod(i).trim());
			fmVO.setOutHukSziMei(fmForm.getOutHukSziMei(i).trim());
		}

		try{
			//登録実行
			SubMateMasterResult result = bzDelegate.doInsert(fmVO);

			//エラーチェック
			if(result != null && !result.isSuccess()){
				if(errors == null)
					errors = new ActionErrors();

				 //登録しようとした副資材コードですでに登録済み
				if(result.getDescription().equals(SubMateMasterDelegate.EXIST))	
					errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.insert.duplicate"));

				 //登録しようとしたデータが論理削除中
				else if(result.getDescription().equals(SubMateMasterDelegate.LOGICAL_DELETE))
					errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.insert.logicaldelete"));

				//発注先がない
				if(result.getDescription().equals(SubMateMasterDelegate.NG)){
					errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.insert.notfound","発注先コード"));
				}
			}
					
			if(errors == null){
				for(int i = 0;i < SubMateMasterForm.DEFAULT_ROW_COUNT;i++){

					fmForm.setOutHukSziCod(i,fmVO.getHukSziCod());
					fmForm.setOutBunruiCod(i,fmVO.getOutBunruiCod());
					fmForm.setOutHatcCod(i,fmVO.getOutHatcCod());
					fmForm.setOutHukSziMei(i,fmVO.getOutHukSziMei());
				
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

	private ActionErrors update (SubMateMasterForm fmForm,HttpServletRequest req,HttpServletResponse res){


		ActionErrors errors = null;
		List lisFmVO = new ArrayList();  //バリューオブジェクトを入れる

		for(int i = 0; i < fmForm.getSize();i++){
			if(!fmForm.getExecute(i)){//チェックなし
				lisFmVO.add(null);	
				continue;
			}

/**			//チェックあり
			SubMateMasterVO fmVO = fmForm.getSubMateMasterVO(i);
			lisFmVO.add(fmVO);

				for(int x = 0; x < SubMateMasterForm.DEFAULT_ROW_COUNT;x++){
					fmVO.setHidDaiKaiSkbCod(fmForm.getHidDaiKaiSkbCod(x).trim());
					fmVO.setHidKaiSkbCod(fmForm.getHidKaiSkbCod(x).trim());
					fmVO.setOutHukSziCod(fmForm.getOutHukSziCod(x).trim());	
					fmVO.setOutBunruiCod(fmForm.getOutBunruiCod(x).trim());
					fmVO.setOutHatcCod(fmForm.getOutHatcCod(x).trim());
					fmVO.setOutHukSziMei(fmForm.getOutHukSziMei(x).trim());
					fmVO.setHidUpdDte(fmForm.getHidUpdDte(x));
					fmVO.setHidUpdJkk(fmForm.getHidUpdJkk(x));
				}
**/
			//チェックあり
			SubMateMasterVO fmVO = fmForm.getSubMateMasterVO(i);

			fmVO.setOutBunruiCod(fmForm.getOutBunruiCod(i).trim());
			fmVO.setOutHatcCod(fmForm.getOutHatcCod(i).trim());
			fmVO.setOutHukSziMei(fmForm.getOutHukSziMei(i).trim());
			
			lisFmVO.add(fmVO);
		}

		try{
			//更新実行
			SubMateMasterResult[] results = bzDelegate.doUpdate((SubMateMasterVO[])lisFmVO.toArray(new SubMateMasterVO[lisFmVO.size()]));

			
			//エラーだったのはないか調べる
			for(int i = 0; i < results.length;i++){
				if(results[i] != null && !results[i].isSuccess()){
					if(errors == null)
						errors = new ActionErrors();
						
					 //ほかのユーザーが変更済み
					if(results[i].getDescription().equals(SubMateMasterDelegate.ANOTHER_USER_UPDATE)||
					   results[i].getDescription().equals(SubMateMasterDelegate.LOGICAL_DELETE)||
   					   results[i].getDescription().equals(SubMateMasterDelegate.NOT_EXIST))
						errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.anotheruserupdate"));

					if(results[i].getDescription().equals(SubMateMasterDelegate.NG))	
						errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.insert.notfound_row",String.valueOf(i+1),"発注先コード"));
				}
			}


			if(errors == null){
				for(int i = 0; i < results.length;i++){
					if(results[i] == null)
						continue;

					fmForm.setExecute(i,false);
					fmForm.setSubMateMasterVO(i, results[i].getVO());
	
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

	private ActionErrors delete (SubMateMasterForm fmForm,HttpServletRequest req,HttpServletResponse res){

		ActionErrors errors = null;
		List lisFmVO = new ArrayList();  
		

		for(int i = 0; i < fmForm.getSize();i++){
			if(!fmForm.getExecute(i)){//チェックなし
				lisFmVO.add(null);	
				continue;
			}
			//チェックあり
			SubMateMasterVO fmVO = fmForm.getSubMateMasterVO(i);
			lisFmVO.add(fmVO);
	
		}

		try{
			//削除実行
			SubMateMasterResult[] result = bzDelegate.doDelete((SubMateMasterVO[])lisFmVO.toArray(new SubMateMasterVO[lisFmVO.size()]));

			//エラーチェック
		for(int i = 0; i < result.length;i++){
			if(result[i] != null && !result[i].isSuccess()){
				if(errors == null)
					errors = new ActionErrors();

				 //ほかのユーザーが変更済み
				if(result[i].getDescription().equals(SubMateMasterDelegate.ANOTHER_USER_UPDATE)) 
					errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.anotheruserupdate"));
				 //削除しようとしたデータが論理削除中 か  存在しない
				else if(result[i].getDescription().equals(SubMateMasterDelegate.LOGICAL_DELETE)||
					    result[i].getDescription().equals(SubMateMasterDelegate.NOT_EXIST))	
					errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.anotheruserupdate"));
//					errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.select.notexist"));

			}
		}
			//削除したレコードを画面から消す
			if(errors == null){
				int index = 0;
				for(int i = 0; i < result.length;i++,index++){
					if(result[i] == null)
						continue;

					fmForm.removeTableField(index);
					fmForm.removeSubMateMasterVO(index);
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


