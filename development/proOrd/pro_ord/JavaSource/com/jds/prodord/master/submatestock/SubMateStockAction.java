/**                                                                                                                                                                                                                                      /**
* SubMateStockAction  副資材在庫メンテナンス  アクションクラス
*
*	作成日    2003/08/18
*	作成者    （ＮＩＩ）村上  博基 
* 処理概要    処理呼び出しを行い、画面制御を行うクラス。
*
*	[変更履歴]
*	変更日    2003/09/18
*	変更者    （ＮＩＩ）村上  博基 
*      変更内容  検索項目にサンプル区分を追加
* 		 2004/07/02（ＮＩＩ）蛭田
* 			・コピー＆ペースト機能追加
*/

package com.jds.prodord.master.submatestock;

import com.jds.prodord.common.*;
import javax.servlet.http.*;
import org.apache.struts.action.*;
import org.apache.struts.util.*;
import java.sql.*;
import java.util.*;
import java.io.*;

  public class SubMateStockAction extends Action {

	private static  SubMateStockDelegate bzDelegate = new SubMateStockDelegate();


//===================================================================

	public ActionForward perform(ActionMapping mapping,ActionForm form
											,HttpServletRequest req,HttpServletResponse res){
		ActionErrors errors = null;
		HttpSession session = req.getSession();
		//フォームのデータ
		SubMateStockForm fmForm = (SubMateStockForm)form;
		SubMateStockVO fmVO = new SubMateStockVO();
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
			fmForm.clearAll();

	   		com.jds.prodord.register.LogonForm logonForm
		  			= (com.jds.prodord.register.LogonForm)session.getAttribute(CommonConst.user);

			String daiKaiSkbCod = 	logonForm.getDaikaiskbcod();

			ArrayList kaiList = logonForm.getKaiSkbCodList();

			fmForm.setQueryDaiKaiSkbCod(daiKaiSkbCod);
			fmForm.setQueryKaiSkbCodList(kaiList);

			fmForm.setHyoKbn(logonForm.getHyoKbn());
		}

		//***  副資材在庫メンテナンスの実行ボタンを押した時  ***
		if(fmForm.getCommand().equals(SubMateStockForm.COMMAND_JIKKOU)){


			//処理区分が照会
			if(fmForm.getPrcKbn().equals("1")){
					errors = this.kigoban_search(fmForm,req,res); //記号番号存在判定
				if(errors == null)  //処理成功
					errors = this.search(fmForm,req,res); //検索
				if(errors == null)	//処理成功
			 	 	req.setAttribute(CommonConst.INFOMSG_KEY,mr.getMessage("info.select.end"));

			//処理区分が更新
			}else{			  
					errors = this.update(fmForm,req,res);
				if(errors == null)  //処理成功
					req.setAttribute(CommonConst.INFOMSG_KEY,mr.getMessage("info.update.end"));
			}


		//***  副資材在庫メンテナンスのクリアボタンを押した時  ***
		}else if(fmForm.getCommand().equals(SubMateStockForm.COMMAND_CLEAR)){

			fmForm.clearAll();
			fmForm.setCommand("INIT");
			return (new ActionForward(mapping.getInput()));


		// ｢貼り付け｣押下時  2004/06/29 add
		}else if(fmForm.getCommand().equals(SubMateStockForm.COMMAND_PASTE)){
			dataPaste(fmForm);
			fmForm.setCpPaste("");
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

//=====  記号番号存在判定処理  ================================================================
	private ActionErrors kigoban_search (SubMateStockForm fmForm,HttpServletRequest req,HttpServletResponse res){

		ActionErrors errors = null;

		SubMateStockVO fmVO = new SubMateStockVO();

		String daikaiskbcod = fmForm.getQueryDaiKaiSkbCod();
		ArrayList kaiskbcod = fmForm.getQueryKaiSkbCodList();
		ArrayList kigo_arr =  new ArrayList();
		ArrayList kigo_ArrErr = new ArrayList();
		ArrayList search_arr = new ArrayList();

		//飛んできたフォームの値をバリューオブジェクトにセット
		fmVO.setDaiKaiSkbCod(daikaiskbcod);
		fmVO.setQueryKaiSkbCodList(kaiskbcod);
		kigo_arr = DataFormatUtils.supplement_kigBng(fmForm.getKigo_arr());//記号部省略入力対応 2003/09/24
		fmVO.setKigoBan_arr(kigo_arr);

		try{
			//検索実行
			/**
			 * 不具合のその場しのぎの対応です。
			 * search_arrには、 0：エラー品番の入力番号 1：OK品番の記号番号がセットされている
			 * **/
			search_arr = bzDelegate.kigoban_search(fmVO);
			kigo_ArrErr = (ArrayList)search_arr.get(0);

		if(kigo_ArrErr.size() != 0){
			errors = new ActionErrors();			

			for(int i=0;i<kigo_ArrErr.size();i++){
				errors.add("",new ActionError("errors.insert.notfound","記号番号("+(String)kigo_ArrErr.get(i)+"番目)"));
			}
		}else{
			//kigo_arrは、省略・記号番号が入り乱れているが、検索時のSQLは記号番号での検索のためOK品番の記号番号を再セット
			kigo_arr = (ArrayList)search_arr.get(1);	
			//VOの保持を行っていないので、Formに仮置き
			fmForm.setKigbng_arr(kigo_arr);
		}

		}catch(SQLException sqle){
			sqle.printStackTrace();
			if(errors == null)
				errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.exception",sqle.toString()));  //エラーが発生しました
		}

		return errors;
	}



//=====  照会処理  ================================================================

	private ActionErrors search (SubMateStockForm fmForm,HttpServletRequest req,HttpServletResponse res){

		ActionErrors errors = null;

		SubMateStockVO fmVO = new SubMateStockVO();

		String daikaiskbcod = fmForm.getQueryDaiKaiSkbCod();
		ArrayList kaiskbcod = fmForm.getQueryKaiSkbCodList();
		ArrayList lisFmVO = new ArrayList();
		ArrayList kigo_arr =  new ArrayList();

		//飛んできたフォームの値をバリューオブジェクトにセット
		fmVO.setDaiKaiSkbCod(daikaiskbcod);
		fmVO.setQueryKaiSkbCodList(kaiskbcod);
		fmVO.setSmpKbn(fmForm.getSmpKbnSel()); //2003/09/18
		//入力品番の再セット⇒検索時に取得した記号番号をセットするように変更
		fmVO.setKigoBan_arr(fmForm.getKigbng_arr());

		try{
			//検索実行
			SubMateStockVO[] resultFmVOs = bzDelegate.doFind(fmVO);
			fmForm.removeSubMateStockVO();
			
			if(resultFmVOs.length != 0){
				//検索結果をフォームにセット
				fmForm.setSize(Math.max((resultFmVOs.length),SubMateStockForm.DEFAULT_ROW_COUNT));
				fmForm.clearTableField();

				for(int i = 0;i < resultFmVOs.length;i++){

					if(i < resultFmVOs.length-1){
						if(i == 0)
							fmForm.setHidFlag(i,false);
						if(resultFmVOs[i].getKigoBan().equals(resultFmVOs[i+1].getKigoBan())){
							fmForm.setHidFlag(i+1,true);
						}else{
							fmForm.setHidFlag(i+1,false);
						}
					}
					fmForm.setHidDaiKaiSkbCod(i,resultFmVOs[i].getHidDaiKaiSkbCod());
					fmForm.setSmpKbn(i,resultFmVOs[i].getSmpKbn()); //2003/09/18
					fmForm.setKigoBan(i,resultFmVOs[i].getHjiHnb());
					fmForm.setHuksizaiCod(i,resultFmVOs[i].getHuksizaiCod());
					fmForm.setHuksizaiMei(i,resultFmVOs[i].getHuksizaiMei());
					fmForm.setHuksizaiZaiko(i,DataFormatUtils.setFormatString(resultFmVOs[i].getHuksizaiZaiko()));
					fmForm.setHidUpdDte(i,resultFmVOs[i].getHidUpdDte());					
					fmForm.setHidUpdJkk(i,resultFmVOs[i].getHidUpdJkk());					
					fmForm.addSubMateStockVO(resultFmVOs[i]);
				}

			fmForm.setAtai(Integer.toString(resultFmVOs.length));


			
			}else{
				fmForm.setSize(SubMateStockForm.DEFAULT_ROW_COUNT);
				fmForm.clearTableField();
				errors = new ActionErrors();
				errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.select.notexist"));   //対象データが存在しません
			}


		}catch(SQLException sqle){
			sqle.printStackTrace();
			if(errors == null)
				errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.exception",sqle.toString()));  //エラーが発生しました
		}		

		return errors;			
	}



//=====  更新メソッド  ================================================================

	private ActionErrors update(SubMateStockForm fmForm,HttpServletRequest req,HttpServletResponse res){

		ActionErrors errors = null;
		String queryKaiSkbCod = fmForm.getQueryKaiSkbCod();
		String queryDaiKaiSkbCod = fmForm.getQueryDaiKaiSkbCod();

		List lisFmVO = new ArrayList();  //バリューオブジェクトを入れる
			
		for(int i = 0; i < fmForm.getSize();i++){
			SubMateStockVO fmVO = fmForm.getSubMateStockVO(i);
			if(!fmForm.getTeiseiSuu(i).equals("") && !fmForm.getTeiseiSuu(i).equals("0")){
				if(fmForm.getSign(i) == false)
					fmVO.setHuksizaiZaiko(Integer.toString(Integer.parseInt(fmVO.getHuksizaiZaiko()) + Integer.parseInt(fmForm.getTeiseiSuu(i))));
				if(fmForm.getSign(i) == true)
					fmVO.setHuksizaiZaiko(Integer.toString(Integer.parseInt(fmVO.getHuksizaiZaiko()) - Integer.parseInt(fmForm.getTeiseiSuu(i))));
			}else{
				lisFmVO.add(null);
				continue;
			}



			lisFmVO.add(fmVO);	
		}
		
		try{
			//更新実行
			SubMateStockResult[] results = bzDelegate.doUpdate((SubMateStockVO[])lisFmVO.toArray(new SubMateStockVO[lisFmVO.size()]));

			
			//エラーだったのはないか調べる
			for(int i = 0; i < results.length;i++){
				if(results[i] != null && !results[i].isSuccess()){
					if(errors == null)
						errors = new ActionErrors();
						
					 //ほかのユーザーが変更済み
					if(results[i].getDescription().equals(SubMateStockDelegate.ANOTHER_USER_UPDATE)||
					   results[i].getDescription().equals(SubMateStockDelegate.LOGICAL_DELETE)||
   					   results[i].getDescription().equals(SubMateStockDelegate.NOT_EXIST))
						errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.anotheruserupdate"));
				}
			}


			if(errors == null){

				fmForm.setPrcKbn("1");
				for(int i = 0; i < results.length;i++){
					if(results[i] == null)
						continue;

					fmForm.setSubMateStockVO(i, results[i].getVO());
					fmForm.setHuksizaiZaiko(i,DataFormatUtils.setFormatString(results[i].getSubMateStock().getHuksizaiZaiko()));
					fmForm.setSign(i,true);
					fmForm.setTeiseiSuu(i,"");
					fmForm.pointClear();
				}
			}

		}catch(SQLException sqle){
			sqle.printStackTrace();
			errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.exception",sqle.toString()));
		}

		return errors;
	}
//=====  配列へ空の文字列の要素を追加するメソッド  ================================================================

	public ArrayList addBlankElement(ArrayList arr){

	for(int z = arr.size(); z<10; z++){
		arr.add("");
	}
		return arr;
	}
	
	/** 
	 * 貼り付け
	 * @param fmForm
	 */
	public void dataPaste(SubMateStockForm fmForm){
		
		final String tab = "\t";
		String data = fmForm.getCpPaste();
		BufferedReader br = new BufferedReader(new StringReader(data));
		String txt = "";
		int count = DataFormatUtils.getDataRowCount(data);
		

		try {

			for (int i = 0; i < count; i++) {
			
				if((txt = br.readLine()) != null){

					txt = DataFormatUtils.insertSpace(txt, tab);
					StringTokenizer line = new StringTokenizer(txt, tab);	
		
					if(line.hasMoreTokens()){
									
						String str = line.nextToken().trim();
						
						if(fmForm.getKigoBan0().equals("")){
							fmForm.setKigoBan0(str);
							continue;
						}
						if(fmForm.getKigoBan1().equals("")){
							fmForm.setKigoBan1(str);					
							continue;
						}
						if(fmForm.getKigoBan2().equals("")){
							fmForm.setKigoBan2(str);
							continue;
						}
						if(fmForm.getKigoBan3().equals("")){
							fmForm.setKigoBan3(str);
							continue;
						}
						if(fmForm.getKigoBan4().equals("")){
							fmForm.setKigoBan4(str);
							continue;
						}
						if(fmForm.getKigoBan5().equals("")){
							fmForm.setKigoBan5(str);
							continue;
						}
						if(fmForm.getKigoBan6().equals("")){
							fmForm.setKigoBan6(str);
							continue;
						}
						if(fmForm.getKigoBan7().equals("")){
							fmForm.setKigoBan7(str);
							continue;
						}
						if(fmForm.getKigoBan8().equals("")){
							fmForm.setKigoBan8(str);
							continue;
						}
						if(fmForm.getKigoBan9().equals("")){
							fmForm.setKigoBan9(str);
							continue;
						}
					}
				}
			}

		}catch(IOException ex){
			ex.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	

}
