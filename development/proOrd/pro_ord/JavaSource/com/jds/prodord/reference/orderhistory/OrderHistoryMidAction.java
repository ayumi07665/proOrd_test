/**
* OrderHistoryMidAction  発注履歴照会画面アクションクラス
*
*	作成日    2003/04/18
*	作成者    （ＮＩＩ）蛭田 敬子
* 処理概要    処理呼び出しを行い、画面制御を行うクラス。
*
*	[変更履歴]
*        2003/06/11（ＮＩＩ）岡田 夏美
* 			・訂正伝票発行機能 仕様変更
* 			（チェックされた行のみではなく、同一の発注書番号を持つ全ての行を出力する）
*        2003/07/14（ＮＩＩ）岡田 夏美
* 			・訂正伝票発行時、PrintOrdersQueryVOにセットする項目に、発注先コード追加
* 		 2003/07/18（ＮＩＩ）岡田 夏美
* 			・新譜旧譜区分を更新可能項目に
* 		 2003/08/18（ＮＩＩ）岡田 夏美
* 			・ダウンロード時、"表示品番"項目追加
* 		 2003/09/05（ＮＩＩ）岡田 夏美
* 			・発注書番号のフォーマットメソッド追加
* 		 2004/01/16,20 (ＮＩＩ) 森
* 			・訂正伝票発行時に発注数が変更されていたらコメント自動表示に
* 		 2004/01/22（ＮＩＩ）森
* 			・入庫数取消可能に修正
* 		 2004/04/02 (NII) 森
* 			・副資材発注の修正(分類コード１複数存在に対応)
* 		 2004/08/03 (NII) 落合
* 			・納期変更時、入庫日に納期をセット
*		 2005/05/25（ＮＩＩ）蛭田
*			・納品先名が'ＪＡＲＥＤ'の場合、直送区分"0"の修正
* 		 2005/09/01（ＮＩＩ）蛭田
* 			・明細に単価の項目追加
* 			・訂正送信、訂正伝票発行の場合、同じ発注番号分履歴更新日をVOにセット
* 		 2005/12/01（ＮＩＩ）蛭田
* 			・金額ﾌﾞﾗﾝｸ対応
* 		 2006/01/25（ＮＩＩ）田端
* 			・ダウンロード項目に注残数追加
*        2007/03/01（ＮＩＩ）田中
* 			・ダウンロード項目に副資材コード追加
*        2007/12/05（ＮＩＩ）田中
* 			・ダウンロード項目にタイトル漢字追加
* 		 2007/12/25（ＮＩＩ）田中
* 			・マニュアル発注指示一括貼付　備考２追加対応
* 		 2008/03/07（ＮＩＩ）田中
* 			・単価の追加
* 		 2008/06/02（ＮＩＩ）田中
* 			・ＶＡＰ社　単価・金額の修正
*
*/

package com.jds.prodord.reference.orderhistory;
import com.jds.prodord.common.*;
import com.jds.prodord.print.printorders.*;

import javax.servlet.http.*;
import org.apache.struts.action.*;
import org.apache.struts.util.*;
import java.sql.*;
import java.util.*;
import java.math.BigDecimal;

public class OrderHistoryMidAction extends Action {
	
	
	/**
	 * ビジネスロジック
	 */
	private static  OrderHistoryDelegate bzDelegate = new OrderHistoryDelegate();
	
	
	public ActionForward perform(ActionMapping mapping,ActionForm form,HttpServletRequest req,HttpServletResponse res){
		ActionErrors errors = null;
		HttpSession session = req.getSession();
		//フォームのデータ
		OrderHistoryMidForm midForm = (OrderHistoryMidForm)form;		
		OrderHistoryVO queryVO = new OrderHistoryVO();
		MessageResources mr = super.getResources();//通常メッセージ表示用にリソースファイルからメッセージをとってくるのに使う

		midForm.setNewWindow("0");//印刷用画面を開くかどうかのフラグを初期化
		midForm.setTeiDenflg("0");//訂正伝票発行が行われるかのフラグを初期化

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

		//最初に発注履歴検索画面からきたとき
		if(midForm.getCommand().equals("INIT")){
	   		com.jds.prodord.register.LogonForm logonForm 
		  			= (com.jds.prodord.register.LogonForm)session.getAttribute("user");
			String daiKaiSkbCod = 	logonForm.getDaikaiskbcod();
			ArrayList kaiList = logonForm.getKaiSkbCodList();
			midForm.setQueryDaiKaiSkbCod(daiKaiSkbCod);
			midForm.setQueryKaiSkbCodList(kaiList);
			midForm.setHyoKbn(logonForm.getHyoKbn()); //add 2011/05/30
			
			session.removeAttribute(CommonConst.INFOMSG_KEY);

			/** ｢実行｣ **/
			queryVO = (OrderHistoryVO)session.getAttribute(CommonConst.ORDERHISTORY_VO);
			if(queryVO != null){
				if(queryVO.getPrcKbn().equals(OrderHistoryTopForm.PRCKBN_SEARCH))
					errors = this.find(midForm,queryVO,req,res);
				else if(queryVO.getPrcKbn().equals(OrderHistoryTopForm.PRCKBN_DOWNLOAD)){
					errors = this.download(midForm,queryVO,req,res);
					if(errors == null) //処理成功
						return null;
				}
			/** ｢クリア｣ **/
			}else{
				midForm.removeAllRow();
				midForm.clear_page();
				midForm.removeOrderHistoryVO();
				midForm.removeVosList();
			}
			session.removeAttribute(CommonConst.ORDERHISTORY_VO);
	    }

		/**「訂正伝票発行」押下時	**/ 
		else if(midForm.getCommand().equals(OrderHistoryMidForm.COMMAND_TEIDENHAKKOU)){
			errors = this.dnpHakkou(midForm,poQueryVO,req,res);

			if(errors == null){	//処理成功	
		   		com.jds.prodord.register.LogonForm logonForm 
		  			= (com.jds.prodord.register.LogonForm)session.getAttribute("user");

				//poQueryVOに代表会社と会社識別コードリストもセット
				poQueryVO.setDaiKaiSkbCod(logonForm.getDaikaiskbcod());
				poQueryVO.setKaiSkbCod(logonForm.getKaiskbcod());
				poQueryVO.setKaiSkbCod_arr(logonForm.getKaiSkbCodList());
				//発注書データ検索条件がセットされたpoQueryVOをセッションに格納
				session.setAttribute(CommonConst.PO_QUERY_VO,poQueryVO);
				midForm.setCommand(OrderHistoryMidForm.SUCCESS_TEIDENHAKKOU);
				midForm.setTeiDenflg("1");
				midForm.setNewWindow("1");				
				ArrayList rows = (ArrayList)midForm.getFormRowCloneList(); //2004.01.19 add 
				session.setAttribute("saveRows",rows); 							//2004.01.19 add 
				return mapping.findForward("print");
			}
			//エラーが発生しました。
			session.setAttribute(CommonConst.INFOMSG_KEY,mr.getMessage("errors.exception"));			
		}

		//::::::::::: 伝票発行完了 :::::::::::::::::::::::::::::::::::::::::::::::::::::::::
		else if(midForm.getCommand().equals(OrderHistoryMidForm.SUCCESS_TEIDENHAKKOU)){
			if(session.getAttribute(CommonConst.PO_QUERY_VO) != null){
				ArrayList rows = (ArrayList)session.getAttribute("saveRows"); //2004.01.19 add
				session.removeAttribute("saveRows"); 
				midForm.setFormRowList(rows); //2004.01.19 add 
				midForm.setNewWindow("1");
				midForm.setCheck_false();
				session.setAttribute(CommonConst.INFOMSG_KEY,mr.getMessage("info.issue.end"));
			}

		}
		
		/**「訂正送信」押下時 **/
		else if(midForm.getCommand().equals(OrderHistoryMidForm.COMMAND_TEISOU)){
			
			if(queryVO != null)	
				errors = this.teiSei(midForm,req,res);
				
			if(errors == null){	//処理成功
				session.setAttribute(CommonConst.INFOMSG_KEY,mr.getMessage("info.correct.end"));
			}
		}
		
		/** ｢次100件｣ 押下時 **/
		else if(midForm.getCommand().equals(OrderHistoryMidForm.NEXT)){
			errors = this.dispPre_NxtData(midForm,req,res,1);
			
			if(errors == null){	//処理成功
				return mapping.findForward("success");
			}
		}
		/** ｢前100件｣ 押下時 **/
		else if(midForm.getCommand().equals(OrderHistoryMidForm.PREV)){
			errors = this.dispPre_NxtData(midForm,req,res,-1);
			
			if(errors == null){	//処理成功
				this.setCheckbox_checked(midForm);//チェックボックスにチェックを入れなおす
				return mapping.findForward("success");
			}
		}

		//エラーあり
		if(errors != null){
			super.saveErrors(req,errors);
    		return (new ActionForward(mapping.getInput()));
		}
		
		
		//エラーなし
		return mapping.findForward("success");		
	}

	/******************************************************************************************
	 * 		検索																		      * 
	 * 																				          *
	 ******************************************************************************************/
	private ActionErrors find (OrderHistoryMidForm midForm, OrderHistoryVO queryVO,
									HttpServletRequest req,HttpServletResponse res){
		ActionErrors errors = null;
		HttpSession session = req.getSession();
		MessageResources mr = super.getResources();
		
		StringBuffer str_before = new StringBuffer();
		
		try{
		
			//初期化する
			midForm.removeAllRow();
			midForm.clear_page();
			midForm.removeOrderHistoryVO();
			
			//検索実行
			OrderHistoryResult result = bzDelegate.doFindForRefer(queryVO);
			OrderHistoryVO[] resultFmVOs = result.getResultList();
			
			//検索結果をフォームにセット
			midForm.setSize(resultFmVOs.length);
			midForm.clearTableField();	
			
			String breakKey_before = "";

			ArrayList vosList = new ArrayList();//ページ単位にVOのリストを格納する
			
			int pageCount = 0;//ページ数
			int max = OrderHistoryMidForm.MAX_ROW_COUNT;//最大行数
			
			//結果が1ページ内だった場合
			if(resultFmVOs.length <= max){
				midForm.setSize(resultFmVOs.length);
				midForm.setCount(resultFmVOs.length);
				pageCount++;
			}
			//結果が1ページを超えた場合
			else{
				midForm.setSize(max);
				midForm.setCount(max);
				pageCount = resultFmVOs.length / max;
				//半端な行が出たら1ページ足す
				if(resultFmVOs.length % max != 0)
					pageCount++;
			}
				
			for(int i = 0; i<pageCount; i++){//ページ分 ArrayListをnewしておく
				vosList.add(new ArrayList());
			}

			int cnt = 0;

			for(int i = 0; i < pageCount; i++){
				ArrayList vos = (ArrayList)vosList.get(i);
				
				for(int j = cnt; j < max + cnt && j < resultFmVOs.length; j++){
					if(i == 0){
					    OrderHistoryFormRow formRow = midForm.getFormRow(j);
						
						StringBuffer str = new StringBuffer();
									    
					    //発注先・記号番号
				  		str.append(resultFmVOs[j].getHacCod()).append(resultFmVOs[j].getHjiHnb());

						String breakKey = str.toString();

						if(!breakKey.equals(breakKey_before)){
							formRow.setBreakflg(true);
						}
						breakKey_before = str.toString();
					    //キー項目
					    formRow.setDaiKaiSkbCod(resultFmVOs[j].getDaiKaiSkbCod());
					    formRow.setKaiSkbCod(resultFmVOs[j].getKaiSkbCod());
					    formRow.setKigBng(resultFmVOs[j].getKigBng());
					    formRow.setBshCod(queryVO.getBshCod());//2005/05/25 add
					    //検索結果セット
					    formRow.setHacCod(resultFmVOs[j].getHacCod());//発注先
					    if(!resultFmVOs[j].getSinKyuKbn().equals(""))
					    	formRow.setSinKyuKbn(DataFormatUtils.getKbnString(resultFmVOs[j].getSinKyuKbn()));//区分
					    formRow.setHjiHnb(resultFmVOs[j].getHjiHnb());//表示品番
					    formRow.setHbiDte(DataFormatUtils.setFormatYYMMDD(resultFmVOs[j].getHbiDte()));//発売日
					    formRow.setHacSyoBng(resultFmVOs[j].getHacSyoBng());//発注書番号
					    formRow.setGyoBng(resultFmVOs[j].getGyoBng());//行番号
					    //発注日
					    formRow.setHacSyoDte(DataFormatUtils.setFormatYYMMDD(resultFmVOs[j].getHacSyoDte()));
						//出力済サイン
					    if(!resultFmVOs[j].getSyrZmiSgn().equals(""))
					    	formRow.setSyrZmiSgn(getHacZmiSgnString(Integer.parseInt(resultFmVOs[j].getSyrZmiSgn())));
						//分類コード
						if(!resultFmVOs[j].getBunCod().equals(""))	
							formRow.setBunCod(this.getBunCodString(Integer.parseInt(resultFmVOs[j].getBunCod())));//分類ｺｰﾄﾞ
						if(!resultFmVOs[j].getKin().equals("0") && !resultFmVOs[j].getKin().equals("0.00"))
							formRow.setKin(resultFmVOs[j].getKin());//金額 2005/09/13 add
						if(!resultFmVOs[j].getKinOld().equals("0") && !resultFmVOs[j].getKinOld().equals("0.00"))
							formRow.setKinOld(resultFmVOs[j].getKinOld());//金額Old 2008/06/02 add
					    formRow.setFukSzisuu(DataFormatUtils.setFormatString(resultFmVOs[j].getFukSziSuu()));//ﾌﾟﾚｽ副資材残
					    formRow.setHacSuu(resultFmVOs[j].getHacSuu());//発注数
						formRow.setHacSuuOld(resultFmVOs[j].getHacSuuOld());//発注数Old 2005/06/02 add
					    formRow.setNyoSuu(DataFormatUtils.setFormatString(resultFmVOs[j].getNyoSuu()));//入庫数
						//納期
					    formRow.setNkiYear(resultFmVOs[j].getNkiYear());
					    formRow.setNkiMonth(resultFmVOs[j].getNkiMonth());
					    formRow.setNkiDay(resultFmVOs[j].getNkiDay());
					    formRow.setNhnMeiKj(resultFmVOs[j].getNhnMeiKj());//納品先
					    formRow.setCheck_Mid(resultFmVOs[j].getCheck_Mid());//チェックﾎﾞｯｸｽ
					    formRow.setCmt(resultFmVOs[j].getCmt());//コメント
					    formRow.setCykkbn(resultFmVOs[j].getCykKbn()); //直送区分　2004.01.22 add 
					    //入庫日
					    if(!resultFmVOs[j].getNyoDte().equals("") && !resultFmVOs[j].getNyoDte().equals("0"))	
					    	formRow.setNyoDte(DataFormatUtils.setFormatYYMMDD(resultFmVOs[j].getNyoDte()));
						formRow.setBiKou2(resultFmVOs[j].getBiKou2());//備考２
						if(!resultFmVOs[j].getTan2().equals("0") && !resultFmVOs[j].getTan2().equals("0.00"))
							formRow.setTan2(resultFmVOs[j].getTan2());//単価 2008/03/07 add
						if(!resultFmVOs[j].getTan2Old().equals("0") && !resultFmVOs[j].getTan2Old().equals("0.00"))
							formRow.setTan2Old(resultFmVOs[j].getTan2Old());//単価Old 2008/06/02 add
						midForm.addOrderHistoryVO(resultFmVOs[j]);
					}
					vos.add(resultFmVOs[j]);
				}
				cnt += max;
			}
			if(resultFmVOs.length !=0){
				midForm.setVosList(vosList);//全ページ分のVOのリスト
				midForm.setPageCount(pageCount);//ページ数
				midForm.setCurrentPage(0);//現在のページ
				midForm.setAllRowCount(resultFmVOs.length);//全ページの件数
			}
		

			if(resultFmVOs.length ==0){
				errors = new ActionErrors();
				if(result.getDescription().equals(OrderHistoryDelegate.EXCEED_REINPUT))
					//件数制限を越えました。
					errors.add(
						ActionErrors.GLOBAL_ERROR,
						new ActionError(
							"errors.exceed.reinput",
							DataFormatUtils.setFormatString(String.valueOf(OrderHistoryMidForm.MAX_REFER_COUNT)),
							DataFormatUtils.setFormatString(String.valueOf(result.getCount()))));
				else
					//データが存在しません
					errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.select.notexist"));
			}else{
				//検索されました。
				session.setAttribute(CommonConst.INFOMSG_KEY,mr.getMessage("info.select.end"));
			}
   		}catch(SQLException sqle){
			SystemException se = new SystemException(sqle);
			se.printStackTrace();
			errors = new ActionErrors();
			//エラーが発生しました。
			errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.exception",sqle.toString()));
			
		}
		return errors;			
			
	}
	
	
	/*****************************************************************************************
	 * 		訂正送信																	     *
	 * 																						 *
	 *****************************************************************************************/
	
	private ActionErrors teiSei (OrderHistoryMidForm midForm,HttpServletRequest req,HttpServletResponse res){
		ActionErrors errors = null;
		List lisFmVO = new ArrayList();
		int page = midForm.getCurrentPage();//現在のページ
		int max = OrderHistoryMidForm.MAX_ROW_COUNT;
		OrderHistoryVO[] currentVOs = midForm.getVosList(page);//現在のページのVOを取り出す		

		for(int i = 0; i < midForm.getSize();i++){	
			OrderHistoryFormRow formRow = midForm.getFormRow(i);

			if(!formRow.getCheck_Mid()){
				lisFmVO.add(null);	
				continue;			}
				
			OrderHistoryVO fmVO = currentVOs[i];

			
			//変更をセット
			fmVO.setCheck_Mid(formRow.getCheck_Mid());
			fmVO.setSinKyuKbn(DataFormatUtils.getKbnCod(formRow.getSinKyuKbn()));//2003/07/18 add
			fmVO.setHacSuu(formRow.getHacSuu());
			fmVO.setNki(formRow.getNkiYear().trim(),formRow.getNkiMonth().trim(),formRow.getNkiDay().trim());			
			fmVO.setNhnMeiKj(formRow.getNhnMeiKj().trim());
			fmVO.setCmt(formRow.getCmt().trim());//コメント
			
			if(formRow.getDaiKaiSkbCod().equals(CommonConst.KAICOD_VAP)){
				fmVO.setTan2(formRow.getTan2());
				fmVO.setKin(formRow.getKin());
				//単価入力なし・金額入力あり　単価＝金額／発注数（初回だけ算出）
				if(formRow.getTan2().equals("") && formRow.getKinOld().equals("") && !formRow.getKin().equals("")){
					BigDecimal hacsuu = new BigDecimal(formRow.getHacSuu().trim());
					BigDecimal kin = new BigDecimal(formRow.getKin().trim());
					BigDecimal tan2 = kin.divide(hacsuu, 2, BigDecimal.ROUND_HALF_UP);//小数第3位を四捨五入
	
					fmVO.setTan2(String.valueOf(tan2).trim());	
					fmVO.setKin(formRow.getKin().trim());
				}
				//単価入力あり・金額入力なし　金額＝単価＊発注数（初回だけ算出）
				if(formRow.getKin().equals("") && formRow.getTan2Old().equals("") && !formRow.getTan2().equals("")){
					BigDecimal hacsuu = new BigDecimal(formRow.getHacSuu().trim());
					BigDecimal tan2 = new BigDecimal(formRow.getTan2().trim());
					BigDecimal kin = hacsuu.multiply(tan2);

					fmVO.setTan2(formRow.getTan2().trim());
					fmVO.setKin(String.valueOf(kin).trim());


				}
				//単価入力あり・金額入力あり
				if(!formRow.getTan2().equals("") && !formRow.getKin().equals("")){
					fmVO.setTan2(formRow.getTan2().trim());	
					fmVO.setKin(formRow.getKin().trim());
				}
			}else{
				fmVO.setTan2(formRow.getTan2().trim());
				fmVO.setKin(formRow.getKin().trim());
			}

			fmVO.setBiKou2(formRow.getBiKou2());
			
			lisFmVO.add(fmVO);	
		}

		ArrayList allVos = midForm.getAllVos();//全ページのVOのリスト（結果全体）
		
		boolean flg = false;
		//処理対象指定なし
		boolean executeChecked = false;
		
		for(int i = 0; i<allVos.size(); i++){

			if(i == max * page){
				for(int j = 0; j < lisFmVO.size(); j++, i++){
					allVos.set(i, lisFmVO.get(j));
					
					if(lisFmVO.get(j) != null){
				
						executeChecked = true;
					}
				}
			}

			if(i == allVos.size())
				continue;

			OrderHistoryVO vo = (OrderHistoryVO)allVos.get(i);

			if(!vo.getCheck_Mid()){
				allVos.set(i, null);
				continue;
			}
			executeChecked = true;

		}		

		if(!executeChecked){
			errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.input.required","処理対象"));
			return errors;	
		}
 		
		try{
			//「訂正送信」実行
			OrderHistoryResult[] results = bzDelegate.doTeiseiSousin((OrderHistoryVO[])allVos.toArray(new OrderHistoryVO[allVos.size()]));
			
			//エラーだったのはないか調べる
			for(int i = 0; i < results.length;i++){
				if(results[i] != null && !results[i].isSuccess()){
					if(errors == null)
						errors = new ActionErrors();

					 //ほかのユーザーが変更済み
					if(results[i].getDescription().equals(OrderHistoryDelegate.ANOTHER_USER_UPDATE)){
					    errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.anotheruserupdate"));
					}else{
						errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.internal"));
					}
				}
			}

			if(errors == null){
				 //バリューオブジェクト自体も格納
				HashMap keyMap = null;
				for(int i = 0; i < results.length; i++){
					if(results[i] == null)
						continue;
					if(keyMap == null)
						keyMap = results[i].getKeyMap();//履歴更新日キーの取得 2005/09/15 add

					midForm.setOrderHistoryVO_all(i, results[i].getVO());
						
					if(page * max <= i && i < (page + 1) * max){
						midForm.getFormRow(i % max).setCheck_Mid(false);//チェックを外す
						midForm.getFormRow(i % max).setCmt(results[i].getVO().getCmt());  //2004.01.21 add
						midForm.getFormRow(i % max).setNyoSuu(results[i].getVO().getNyoSuu());  //2004.01.22 add
						if(!results[i].getVO().getNyoDte().equals("") && !results[i].getVO().getNyoDte().equals("0")){
							midForm.getFormRow(i % max).setNyoDte(DataFormatUtils.setFormatYYMMDD(results[i].getVO().getNyoDte()));//2004.08.03 add
						}
						midForm.getFormRow(i % max).setHacSuuOld(results[i].getVO().getHacSuuOld());//発注数Old 2005/06/02 add
						midForm.getFormRow(i % max).setKin(results[i].getVO().getKin()); //金額 2005/09/13 add
						midForm.getFormRow(i % max).setKinOld(results[i].getVO().getKinOld()); //金額Old 2008/06/02 add
						midForm.getFormRow(i % max).setBiKou2(results[i].getVO().getBiKou2());  
						midForm.getFormRow(i % max).setTan2(results[i].getVO().getTan2()); //単価 2008/03/07 add
						midForm.getFormRow(i % max).setTan2Old(results[i].getVO().getTan2Old());//単価Old 2008/06/02 add
					}
				}
				//ﾁｪｯｸした項目と同じ発注番号分、履歴更新日をVOにもセット 2005/09/15 add
				ArrayList fmVOs = midForm.getAllVos();
				Iterator keySet = keyMap.keySet().iterator();
				while (keySet.hasNext()) {
					String key = (String) keySet.next();
					OrderHistoryVO value = (OrderHistoryVO) keyMap.get(key);
					//VOにもセット
					for(int i = 0;i < fmVOs.size();i++){
						OrderHistoryVO vo = (OrderHistoryVO)fmVOs.get(i);
						if(vo.getUpdateKey().equals(key)){
							vo.setRrkTbl(value.getRrkTbl());
							vo.setUpdDte(value.getUpdDte());
							vo.setUpdJkk(value.getUpdJkk());
							midForm.setOrderHistoryVO_all(i, vo);
						}
					}
				}	
			}
			
   		}catch(SQLException sqle){
			SystemException se = new SystemException(sqle);
			se.printStackTrace();
			errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.exception",sqle.toString()));
			
		}
		return errors;			
			
	}
	

	/******************************************************************************************
	 * 		ダウンロード																	  *
	 * 																				          *
	 ******************************************************************************************/
	private ActionErrors download (OrderHistoryMidForm midForm, OrderHistoryVO queryVO,
									HttpServletRequest req,HttpServletResponse res){
		ActionErrors errors = null;
		ArrayList lisCsvVO = new ArrayList();
		
		try{
		
			//検索実行
			OrderHistoryResult result = bzDelegate.doFindForDownload(queryVO);
			OrderHistoryVO[] resultFmVOs = result.getResultList();

			if(resultFmVOs.length == 0){
				errors = new ActionErrors();
				//データが存在しません
				errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.select.notexist"));
				
			}else{
			
				String colNmStr = "発注先,区分,記号番号,表示品番,タイトル漢字,発売日,発注書番号,行番,発注日,出力済,分類ｺｰﾄﾞ,"
								+ "単価,金額,在庫数,発注数,入庫数,入庫日,納期,納品先,コメント欄,注残数,副資材名称,備考２";
	
				for(int i = 0; i < resultFmVOs.length;i++){
					OrderHistoryCsvVO csvVO = new OrderHistoryCsvVO();
					OrderHistoryVO fmVO = resultFmVOs[i];
				
					//データセット
					csvVO.setHacCod(fmVO.getHacCod());//発注先
					if(!fmVO.getSinKyuKbn().trim().equals(""))
						csvVO.setSinKyuKbn(DataFormatUtils.getKbnString(fmVO.getSinKyuKbn().trim()));//区分
					csvVO.setKigBng(fmVO.getKigBng().trim());//記号番号
					csvVO.setHjiHnb(fmVO.getHjiHnb().trim());//表示品番 2003/08/18 add
					csvVO.setTitKj(fmVO.getTitKj().trim());//タイトル漢字
					if(!fmVO.getHbiDte().trim().equals("") && !fmVO.getHbiDte().equals("0"))
						csvVO.setHbiDte(fmVO.getHbiDte().trim());//発売日
					csvVO.setHacSyoBng(fmVO.getHacSyoBng().trim());//発注書番号
					csvVO.setGyoBng(fmVO.getGyoBng().trim());//行番
					if(!fmVO.getHacSyoDte().trim().equals("") && !fmVO.getHacSyoDte().equals("0"))
						csvVO.setHacSyoDte(fmVO.getHacSyoDte().trim());//発注日			
					if(!fmVO.getSyrZmiSgn().trim().equals(""))
						csvVO.setSyrZmiSgn(getHacZmiSgnString(Integer.parseInt(fmVO.getSyrZmiSgn().trim())));//出力済
					if(!fmVO.getBunCod().trim().equals(""))
						csvVO.setBunCod(this.getBunCodString(Integer.parseInt(fmVO.getBunCod().trim())));//分類ｺｰﾄﾞ
					csvVO.setTan2(fmVO.getTan2().trim());//単価 2008/03/07 add
					csvVO.setKin(fmVO.getKin().trim());//金額 2005/09/15 add
					csvVO.setFukSziSuu(fmVO.getFukSziSuu().trim());//在庫数
					csvVO.setHacSuu(fmVO.getHacSuu().trim());//発注数
					csvVO.setNyoSuu(fmVO.getNyoSuu().trim());//入庫数
					if(!fmVO.getNyoDte().trim().equals("") && !fmVO.getNyoDte().equals("0"))
						csvVO.setNyoDte(fmVO.getNyoDte().trim());//入庫日
					if(fmVO.getNki() != 0)
						csvVO.setNki(fmVO.getNki()+"");//納期
					csvVO.setNhnMeiKj(fmVO.getNhnMeiKj().trim());//納品先
					csvVO.setCmt(fmVO.getCmt().trim());//コメント
					csvVO.setCyuzan(fmVO.getCyuzan().trim());//注残数
					csvVO.setPrsFukSziCod(fmVO.getPrsFukSziCod().trim());//副資材コード
					csvVO.setFukSziNmkj(fmVO.getFukSziNmkj().trim());//副資材名称
					csvVO.setBiKou2(fmVO.getBiKou2().trim());//備考２
				
					lisCsvVO.add(csvVO);
				}
			
				errors = CsvUtils.returnCsvStream(res,"LightCsv",midForm.getQueryDaiKaiSkbCod(),colNmStr,lisCsvVO);
			}

		}catch(SQLException sqle){
			SystemException se = new SystemException(sqle);
			se.printStackTrace();
			errors = new ActionErrors();
			//エラーが発生しました。
			errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.exception",sqle.toString()));
			
		}
		return errors;			
			
	}

	/********************************************************************************************
	 * 		訂正伝票発行																		*
	 * 																							*
	 ********************************************************************************************/
	private ActionErrors dnpHakkou (OrderHistoryMidForm midForm,PrintOrdersQueryVO poQueryVO,
									HttpServletRequest req,HttpServletResponse res){

		ActionErrors errors = null;
		List lisFmVO = new ArrayList();
		ArrayList tmpArr = new ArrayList();
		int page = midForm.getCurrentPage();//現在のページ
		int max = OrderHistoryMidForm.MAX_ROW_COUNT;
		OrderHistoryVO[] currentVOs = midForm.getVosList(page);//現在のページのVOを取り出す

        for(int i = 0; i < midForm.getSize();i++){	
			OrderHistoryFormRow formRow = midForm.getFormRow(i);
			
			//チェックなしはぬける
			if(!formRow.getCheck_Mid()){
				lisFmVO.add(null);
				continue;
			}
				
			OrderHistoryVO fmVO = currentVOs[i];

			//変更をセット
			fmVO.setCheck_Mid(formRow.getCheck_Mid());
			fmVO.setSinKyuKbn(DataFormatUtils.getKbnCod(formRow.getSinKyuKbn()));//2003/07/18 add
			fmVO.setNki(formRow.getNkiYear().trim(),formRow.getNkiMonth().trim(),formRow.getNkiDay().trim());			
			fmVO.setHacSuu(formRow.getHacSuu().trim());
			fmVO.setNhnMeiKj(formRow.getNhnMeiKj().trim());
			fmVO.setCmt(formRow.getCmt().trim());//コメント

			if(formRow.getDaiKaiSkbCod().equals(CommonConst.KAICOD_VAP)){
				fmVO.setTan2(formRow.getTan2());
				fmVO.setKin(formRow.getKin());
				//単価入力なし・金額入力あり　単価＝金額／発注数（初回だけ算出）
				if(formRow.getTan2().equals("") && formRow.getKinOld().equals("") && !formRow.getKin().equals("")){
					BigDecimal hacsuu = new BigDecimal(formRow.getHacSuu().trim());
					BigDecimal kin = new BigDecimal(formRow.getKin().trim());
					BigDecimal tan2 = kin.divide(hacsuu, 2, BigDecimal.ROUND_HALF_UP);//小数第3位を四捨五入
	
					fmVO.setTan2(String.valueOf(tan2));	
					fmVO.setKin(formRow.getKin());
				}
				//単価入力あり・金額入力なし　金額＝単価＊発注数（初回だけ算出）
				if(formRow.getKin().equals("") && formRow.getTan2Old().equals("") && !formRow.getTan2().equals("")){
//					fmVO.setTan2(formRow.getTan2());
//					fmVO.setKin(String.valueOf(Double.parseDouble(formRow.getTan2()) * Integer.parseInt(formRow.getHacSuu())));
					BigDecimal hacsuu = new BigDecimal(formRow.getHacSuu().trim());
					BigDecimal tan2 = new BigDecimal(formRow.getTan2().trim());
					BigDecimal kin = hacsuu.multiply(tan2);

					fmVO.setTan2(formRow.getTan2().trim());
					fmVO.setKin(String.valueOf(kin).trim());
				}
				//単価入力あり・金額入力あり
				if(!formRow.getTan2().equals("") && !formRow.getKin().equals("")){
					fmVO.setTan2(formRow.getTan2().trim());	
					fmVO.setKin(formRow.getKin().trim());
				}
			}else{
				fmVO.setTan2(formRow.getTan2().trim());
				fmVO.setKin(formRow.getKin().trim());
			}

			fmVO.setBiKou2(formRow.getBiKou2());

			lisFmVO.add(fmVO);

		}

		ArrayList allVos = midForm.getAllVos();//全ページのVOのリスト（結果全体）
		
		boolean flg = false;
		//処理対象指定なし
		boolean executeChecked = false;
		
		for(int i = 0; i<allVos.size(); i++){

			if(i == max * page){
				for(int j = 0; j < lisFmVO.size(); j++, i++){
					allVos.set(i, lisFmVO.get(j));
					
					if(lisFmVO.get(j) != null){
						executeChecked = true;
						//2004.01.20 add

						String str = ((OrderHistoryVO)allVos.get(i)).getKaiSkbCod() + "%" +
									 ((OrderHistoryVO)allVos.get(i)).getHacSyoDte() + "%" +
									 ((OrderHistoryVO)allVos.get(i)).getHacSyoBng() + "%" +
									 ((OrderHistoryVO)allVos.get(i)).getHacCod();
						if(tmpArr.indexOf(str) == -1){
						   tmpArr.add(str);
						}
					}
						
				}
			}

			if(i == allVos.size())
				continue;


			OrderHistoryVO vo = (OrderHistoryVO)allVos.get(i);

			if(!vo.getCheck_Mid()){
				allVos.set(i, null);
				continue;
			}else{  //2004.01.20 add

				String str = vo.getKaiSkbCod() + "%" +
							 vo.getHacSyoDte() + "%" +
							 vo.getHacSyoBng() + "%" +
							 vo.getHacCod();
				if(tmpArr.indexOf(str) == -1){
					tmpArr.add(str);
				}
			}

			executeChecked = true;

		}		

		poQueryVO.setPartOfQuery_arr(tmpArr);  //2004.01.20 add
		poQueryVO.setHasHacSyoBng(true);//すでに発注書番号を持っている⇒true   //2004.01.20 add
		poQueryVO.setTeiDnpHakkou(true);//訂正伝票発行押下の場合⇒true  //2005.09.14 add


		if(!executeChecked){
			errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.input.required","処理対象"));
			return errors;	
		}
 		
		try{
			//「訂正伝票発行」押下時
			OrderHistoryResult[] results = bzDelegate.doDnpHakkou((OrderHistoryVO[])allVos.toArray(new OrderHistoryVO[allVos.size()]));

			//エラーだったのはないか調べる
			for(int i = 0; i < results.length;i++){
				if(results[i] != null && !results[i].isSuccess()){
					if(errors == null)
						errors = new ActionErrors();
					 
					
					//ほかのユーザーが変更済み
                    if(results[i].getDescription().equals(OrderHistoryDelegate.ANOTHER_USER_UPDATE)){
					    errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.anotheruserupdate"));
                    }else{
						errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.internal"));
					}
				}
			}
			if(errors == null){
				HashMap keyMap = null;
			
				ArrayList queryRows = new ArrayList();
				for(int i = 0; i < results.length; i++){
					if(results[i] == null)
						continue;

					if(keyMap == null)
						keyMap = results[i].getKeyMap();//履歴更新日キーの取得 2005/09/15 add

					//バリューオブジェクト自体も格納
					midForm.setOrderHistoryVO_all(i, results[i].getVO());
					
					if(page * max <= i && i < (page + 1) * max){

						//発注済サインをセットし直す
						midForm.getFormRow(i % max).setSyrZmiSgn(this.getHacZmiSgnString(Integer.parseInt((results[i].getVO().getSyrZmiSgn()))));
						midForm.getFormRow(i % max).setCheck_Mid(false);//チェックを外す
						midForm.getFormRow(i % max).setCmt(results[i].getVO().getCmt());  //2004.01.16 add
						midForm.getFormRow(i % max).setNyoSuu(results[i].getVO().getNyoSuu());  //2004.01.22 add
						if(!results[i].getVO().getNyoDte().equals("") && !results[i].getVO().getNyoDte().equals("0")){
							midForm.getFormRow(i % max).setNyoDte(DataFormatUtils.setFormatYYMMDD(results[i].getVO().getNyoDte()));//2004.08.03 add
						}
						midForm.getFormRow(i % max).setHacSuuOld(results[i].getVO().getHacSuuOld());//発注数Old 2005/06/02 add
						midForm.getFormRow(i % max).setKin(results[i].getVO().getKin());//金額 2005/09/13 add
						midForm.getFormRow(i % max).setKinOld(results[i].getVO().getKinOld());//金額Old 2008/06/02 add
						midForm.getFormRow(i % max).setBiKou2(results[i].getVO().getBiKou2());
						midForm.getFormRow(i % max).setTan2(results[i].getVO().getTan2());//単価 2008/03/07 add
						midForm.getFormRow(i % max).setTan2Old(results[i].getVO().getTan2Old());//単価Old 2008/06/02 add
					}
				}

				//ﾁｪｯｸした項目と同じ発注番号分、履歴更新日をVOにもセット 2005/09/15 add
				ArrayList fmVOs = midForm.getAllVos();
				Iterator keySet = keyMap.keySet().iterator();
				int updDte = 0;
				int updJkk = 0;
				while (keySet.hasNext()) {
					String key = (String) keySet.next();
					OrderHistoryVO value = (OrderHistoryVO) keyMap.get(key);
					//VOにもセット
					for(int i = 0;i < fmVOs.size();i++){
						OrderHistoryVO vo = (OrderHistoryVO)fmVOs.get(i);
						updDte = value.getUpdDte();
						updJkk = value.getUpdJkk();
						if(vo.getUpdateKey().equals(key)){
							vo.setRrkTbl(value.getRrkTbl());
							vo.setUpdDte(updDte);
							vo.setUpdJkk(updJkk);
							midForm.setOrderHistoryVO_all(i, vo);
						}
					}
				}
				//伝票発行後の更新処理でも同じ更新日時をセットするために渡す
				poQueryVO.setUpdDte(updDte);
				poQueryVO.setUpdJkk(updJkk);

			}

   		}catch(SQLException sqle){
			SystemException se = new SystemException(sqle);
			se.printStackTrace();
			errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.exception",sqle.toString()));
			
		}

		return errors;		
			
	}

	/*********************************************************************************************
	 * 		前100件次100件																		 *
	 * 																							 *
	 *********************************************************************************************/
	private ActionErrors dispPre_NxtData (OrderHistoryMidForm midForm, 
									HttpServletRequest req,HttpServletResponse res, int addIndex){
		ActionErrors errors = null;
		OrderHistoryVO[] resultVOs = null;//前or次ページのVOを格納
		OrderHistoryVO[] currentVOs = null;//現在のページのVOを格納
		
		try{
			currentVOs = midForm.getVosList(midForm.getCurrentPage());
		
		}catch(IndexOutOfBoundsException e){
			errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.select.notexist"));
			return errors;
		}
		
		//変更されている可能性のある項目をセットしなおす
		for(int i = 0; i < currentVOs.length; i++){
			
			OrderHistoryFormRow formRow = midForm.getFormRow(i);
					
			currentVOs[i].setCheck_Mid(formRow.getCheck_Mid());
			currentVOs[i].setSinKyuKbn(DataFormatUtils.getKbnCod(formRow.getSinKyuKbn()));//2003/07/18 add
			currentVOs[i].setNki(formRow.getNkiYear().trim(),formRow.getNkiMonth().trim(),formRow.getNkiDay().trim());
			currentVOs[i].setHacSuu(formRow.getHacSuu().trim());
			currentVOs[i].setHacSuuOld(formRow.getHacSuuOld().trim());
			currentVOs[i].setNhnMeiKj(formRow.getNhnMeiKj().trim());
			currentVOs[i].setCmt(formRow.getCmt().trim());
			currentVOs[i].setKin(formRow.getKin()); //2005/09/13 add
			currentVOs[i].setKinOld(formRow.getKinOld()); //2008/06/02 add
			currentVOs[i].setBiKou2(formRow.getBiKou2());
			currentVOs[i].setTan2(formRow.getTan2()); //2008/03/07 add
			currentVOs[i].setTan2Old(formRow.getTan2Old().trim()); //2008/06/02 add

		}

		//対象ページのデータを取得
		try{
			ArrayList check_mid_index = new ArrayList();
			
			//前or次ページのVOを取得
			resultVOs = midForm.getVosList(midForm.getCurrentPage() + addIndex);
			
			//初期化する
			midForm.removeOrderHistoryVO();
			midForm.removeAllRow();
			
			String breakKey_before = "";
					
			//結果をフォームにセット
			midForm.setSize(resultVOs.length);
			midForm.setCount(resultVOs.length);
			midForm.clearTableField();
			
			for(int j = 0; j < resultVOs.length; j++){
				OrderHistoryFormRow formRow = midForm.getFormRow(j);
				
				StringBuffer str = new StringBuffer();
							    
			    //発注先・記号番号
				str.append(resultVOs[j].getHacCod()).append(resultVOs[j].getHjiHnb());
			       
				String breakKey = str.toString();
				if(!breakKey.equals(breakKey_before)){
					formRow.setBreakflg(true);
				}
				breakKey_before = str.toString();
			    //キー項目
			    formRow.setDaiKaiSkbCod(resultVOs[j].getDaiKaiSkbCod());
			    formRow.setKaiSkbCod(resultVOs[j].getKaiSkbCod());
			    formRow.setKigBng(resultVOs[j].getKigBng());
			    
			    //検索結果セット
			    formRow.setHacCod(resultVOs[j].getHacCod());//発注先
			    if(!resultVOs[j].getSinKyuKbn().equals(""))
			    	formRow.setSinKyuKbn(DataFormatUtils.getKbnString(resultVOs[j].getSinKyuKbn()));//区分
			    formRow.setHjiHnb(resultVOs[j].getHjiHnb());//表示品番
			    formRow.setHbiDte(DataFormatUtils.setFormatYYMMDD(resultVOs[j].getHbiDte()));//発売日
			    formRow.setHacSyoBng(resultVOs[j].getHacSyoBng());//発注書番号
			    formRow.setGyoBng(resultVOs[j].getGyoBng());//行番号
			    //発注日
			    formRow.setHacSyoDte(DataFormatUtils.setFormatYYMMDD(resultVOs[j].getHacSyoDte()));
				//出力済サイン
			    if(!resultVOs[j].getSyrZmiSgn().equals(""))
			    	formRow.setSyrZmiSgn(getHacZmiSgnString(Integer.parseInt(resultVOs[j].getSyrZmiSgn())));
				//分類コード
			    if(!resultVOs[j].getBunCod().equals(""))	
			    	formRow.setBunCod(this.getBunCodString(Integer.parseInt(resultVOs[j].getBunCod())));//ﾌﾟﾚｽ副資材ｺｰﾄﾞ
				if(!resultVOs[j].getKin().equals("0") && !resultVOs[j].getKin().equals("0.00"))
				    formRow.setKin(resultVOs[j].getKin());
				if(!resultVOs[j].getKinOld().equals("0") && !resultVOs[j].getKinOld().equals("0.00"))
					formRow.setKinOld(resultVOs[j].getKinOld());
			    formRow.setFukSzisuu(DataFormatUtils.setFormatString(resultVOs[j].getFukSziSuu()));//ﾌﾟﾚｽ副資材残
			    formRow.setHacSuu(resultVOs[j].getHacSuu());//発注数
				formRow.setHacSuuOld(resultVOs[j].getHacSuuOld());//発注数Old
			    formRow.setNyoSuu(DataFormatUtils.setFormatString(resultVOs[j].getNyoSuu()));//入庫数
				//納期
			    formRow.setNkiYear(resultVOs[j].getNkiYear());
			    formRow.setNkiMonth(resultVOs[j].getNkiMonth());
			    formRow.setNkiDay(resultVOs[j].getNkiDay());
			    formRow.setNhnMeiKj(resultVOs[j].getNhnMeiKj());//納品先
			    formRow.setCheck_Mid(resultVOs[j].getCheck_Mid());//チェックﾎﾞｯｸｽ
			    formRow.setCmt(resultVOs[j].getCmt().trim());//コメント
				formRow.setBiKou2(resultVOs[j].getBiKou2().trim());//備考２
				if(!resultVOs[j].getTan2().equals("0") && !resultVOs[j].getTan2().equals("0.00")) //単価 2008/03/07 add
					formRow.setTan2(resultVOs[j].getTan2());
				if(!resultVOs[j].getTan2Old().equals("0") && !resultVOs[j].getTan2Old().equals("0.00")) //単価2Old 2008/06/02 add
					formRow.setTan2Old(resultVOs[j].getTan2Old());
			    //入庫日
				if(!resultVOs[j].getNyoDte().equals("") && !resultVOs[j].getNyoDte().equals("0"))	
			    	formRow.setNyoDte(DataFormatUtils.setFormatYYMMDD(resultVOs[j].getNyoDte()));//入庫日

				//チェックされているチェックボックスのインデックスを格納する
				if(formRow.getCheck_Mid()){
					check_mid_index.add(j+"");
				}

			}
			midForm.setCurrentPage(midForm.getCurrentPage() + addIndex);//現在のページ

			midForm.setOrderHistoryVO(new ArrayList(Arrays.asList(resultVOs)));//現在のVO
			midForm.setCheck_index(check_mid_index);//チェックボックスのインデックスをセット
			
		}catch(IndexOutOfBoundsException e){
			resultVOs = new OrderHistoryVO[0];
		}		
		if(resultVOs.length == 0){
			errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.select.notexist"));
		}
			
		return errors;			
			
	}
	
	
//----------------------------------------------------------------------------------------------------
	
	/**
	 *  チェックボックスにチェックを入れなおすメソッド
	 */
	private void setCheckbox_checked(OrderHistoryMidForm midForm){
		
		ArrayList check_mid_index = midForm.getCheck_Mid_index();
	    int index_mid = 0;

		for(int i = 0; i < midForm.getSize();i++){	
			OrderHistoryFormRow formRow = midForm.getFormRow(i);
			if(check_mid_index.size() != index_mid){
				if(i == Integer.parseInt(check_mid_index.get(index_mid)+"")){
					formRow.setCheck_Mid(true);
					index_mid++;
				}
			}
		}
	}

//---------------------------------------------------------------------------------------------------	
	
	
   /** 発注済サインの表示文字列を返す **/ 
	private String getHacZmiSgnString(int sgn){
		String haczmisgn = "";
		switch(sgn){
			case 0:
				haczmisgn = OrderHistoryMidForm.MISYRYK;
				break;
			case 1:
				haczmisgn = OrderHistoryMidForm.SYRZMI;
				break;
		}
		return haczmisgn;
	}

   /** 分類コードの表示文字列を返す **/ 
	public String getBunCodString(int sgn){
		String buncod = "";
		switch(sgn){
			case 0:
				buncod = OrderHistoryMidForm.PRS;
				break;
			case 1:
			case 2:
				buncod = OrderHistoryMidForm.SZI;
				break;
		}
		return buncod;
	}
	
	
	
}

