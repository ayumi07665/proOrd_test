/**
* PrsOrderAction  プレス・副資材発注画面アクションクラス
*
*	作成日    2003/04/28
*	作成者    （ＮＩＩ）岡田 夏美
* 処理概要    処理呼び出しを行い、画面制御を行うクラス。
*
*	[変更履歴]
*        2003/05/12 （ＮＩＩ）蛭田 敬子
* 			・「発注指示」「伝票発行」｢照会画面へ戻る｣ の追加。
* 		 2003/05/16 （ＮＩＩ）岡田 夏美
* 			・「発注指示」「伝票発行」時に発注先名称の再セット追加。
* 		 2003/06/18 （ＮＩＩ）岡田 夏美
* 			・前100件次100件対応追加。
*			・副資材発注機能追加。
* 		 2003/09/19（ＮＩＩ）岡田 夏美
* 			・表示項目にプレス・副資材 新譜旧譜区分１・２を追加
* 			・プレス・副資材 発注累計 → 入庫累計に変更
* 		 2003/12/19（ＮＩＩ）森
* 			・全品番にデフォルトでチェックを付ける
* 		 2004/01/13（ＮＩＩ）森
* 			・納入先クリアに伴う修正
* 		 2004/03/03（ＮＩＩ）森
* 			・納品先名称に２０桁制限を追加
* 		 2004/04/02 (NII) 森
* 			・副資材発注の修正(分類コード１複数存在に対応)
* 		 2004/04/21 (NII)森
* 			・税込定価表示に伴う修正
*		 2005/02/03（ＮＩＩ）蛭田
*			・場所コードで納品先名を取得するように修正
*		 2005/05/26（ＮＩＩ）蛭田
*			・納品先名が'ＪＡＲＥＤ'の場合、直送区分"0"の修正
*		 2005/09/14（ＮＩＩ）蛭田
*			・副資材発注画面、副資材コードをプルダウン項目に変更（VAP社対応）
*		 2005/09/22（ＮＩＩ）蛭田
*			・納期一括変更機能追加
*		 2007/09/26（ＮＩＩ）今井
*			・「マニュアル発注一括貼付」対応（区分の値をｾｯﾄ）
*		 2007/12/25（ＮＩＩ）田中
*			・「マニュアル発注一括貼付」貼付項目追加対応（備考２）
*/

package com.jds.prodord.order.prsorder;
import com.jds.prodord.common.*;
import com.jds.prodord.indicate.manualorder.*;
import com.jds.prodord.indicate.manualorderpaste.ManualOrderPasteForm;
import com.jds.prodord.print.printorders.*;
import com.jds.prodord.reference.ikkatsurefer.*;

import javax.servlet.http.*;
import org.apache.struts.action.*;
import org.apache.struts.util.*;
import java.sql.*;
import java.util.*;

public class PrsOrderAction extends Action {
	
	/**
	 * ビジネスロジック
	 */
	private static  PrsOrderDelegate bzDelegate = new PrsOrderDelegate();
	
	
	public ActionForward perform(ActionMapping mapping,ActionForm form,HttpServletRequest req,HttpServletResponse res){
		ActionErrors errors = null;
		HttpSession session = req.getSession();
		//フォームのデータ
		PrsOrderForm fmForm = (PrsOrderForm)form;			
		MessageResources mr = super.getResources();//通常メッセージ表示用にリソースファイルからメッセージをとってくるのに使う
		
		PrsOrderLeftFrame leftFrame = new PrsOrderLeftFrame();
		//発注書データ検索条件用のデータを格納するVO
		PrintOrdersQueryVO poQueryVO = new PrintOrdersQueryVO();
		
		//セッションから遷移元画面でセットしたVOを取得
		PrsOrderVO[] resultVOs = (PrsOrderVO[])session.getAttribute(CommonConst.PRSORDER_VOS);
		
		fmForm.setNewWindow("0");//印刷用画面を開くかどうかのフラグを初期化

		//サービス時間のチェック
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
//System.out.println("command : "+fmForm.getCommand());
		//最初に遷移元画面からきたとき
		if(fmForm.getCommand().equals("INIT")){
			//セッションからManualOrderFormを取り出し、コマンドをセット
			ManualOrderForm manualForm = (ManualOrderForm)session.getAttribute(CommonConst.FORMBEAN_NAME_MANUALORDER);
			if(manualForm != null)
				manualForm.setCommand("INIT");
			
	   		com.jds.prodord.register.LogonForm logonForm 
		  			= (com.jds.prodord.register.LogonForm)session.getAttribute(CommonConst.user);

			String daiKaiSkbCod = 	logonForm.getDaikaiskbcod();
			ArrayList kaiList = logonForm.getKaiSkbCodList();
			
			fmForm.setQueryDaiKaiSkbCod(daiKaiSkbCod);
			fmForm.setQueryKaiSkbCod(logonForm.getKaiskbcod());
			fmForm.setQueryKaiSkbCodList(kaiList);
			fmForm.setBshCod(logonForm.getBshCod());//2005/02/03 add
			fmForm.setHyoKbn(logonForm.getHyoKbn());
			//副資材コードプルダウンリストの取得 2005/09/14 add
			errors = this.setFukSziList(fmForm);
			
			session.removeAttribute(CommonConst.INFOMSG_KEY);
				
			//どの画面から遷移してきたかFormにセット
			for(int i = 0; i < resultVOs.length; i++){
				if(resultVOs[i] != null){
					fmForm.setPre_page(resultVOs[i].getPre_page());
					fmForm.setPrs_FukSgn(resultVOs[i].getPrs_FukSgn());
					break;
				}
			}
			//画面にデータをセット
      		errors = this.setData(fmForm,leftFrame,resultVOs,req,res);
			
			if(errors == null){	//処理成功
//System.out.println("******処理成功*****");
				session.setAttribute(CommonConst.BEAN_NAME_PRSORDER_LEFT,leftFrame);
				session.setAttribute(CommonConst.INFOMSG_KEY,mr.getMessage("info.select.end"));	
			}

			//エラーあり
			if(errors != null){
				super.saveErrors(req,errors);
	    		return mapping.findForward("failure");
			}
		}
		
		//「発注指示」押下時
		else if(fmForm.getCommand().equals(PrsOrderForm.COMMAND_HACHUSHIJI)){
//System.out.println("Action--> 発注指示");
			leftFrame = (PrsOrderLeftFrame)session.getAttribute(CommonConst.BEAN_NAME_PRSORDER_LEFT);
			
			errors = this.hacShiji(fmForm,leftFrame,req,res);
			
			if(errors == null){	//処理成功
				session.setAttribute(CommonConst.BEAN_NAME_PRSORDER_LEFT,leftFrame);//左のフレームもセットし直す
				fmForm.setCommand(PrsOrderForm.SUCCESS_HACHUSHIJI);
				return mapping.findForward("next");
			}
		}
		//「発注指示」完了
		else if(fmForm.getCommand().equals(PrsOrderForm.SUCCESS_HACHUSHIJI)){
//System.out.println("Action--> 発注指示完了");
			this.setCheckbox_checked(fmForm);//チェックボックスにチェックを入れなおす
			session.setAttribute(CommonConst.INFOMSG_KEY,mr.getMessage("info.indicate.end"));
			fmForm.setCommand("INIT");
		}
		
		//「伝票発行」押下時
		else if(fmForm.getCommand().equals(PrsOrderForm.COMMAND_DENPYOHAKKOU)){
//System.out.println("Action--> 伝票発行");
			leftFrame = (PrsOrderLeftFrame)session.getAttribute(CommonConst.BEAN_NAME_PRSORDER_LEFT);
			
			errors = this.dnpHakkou(fmForm,leftFrame,poQueryVO,req,res);
			
			if(errors == null){	//処理成功
				session.setAttribute(CommonConst.BEAN_NAME_PRSORDER_LEFT,leftFrame);//左のフレームもセットし直す
				
				com.jds.prodord.register.LogonForm logonForm 
		  			= (com.jds.prodord.register.LogonForm)session.getAttribute("user");
		  			
				//poQueryVOに代表会社と会社識別コードもセット
				poQueryVO.setDaiKaiSkbCod(logonForm.getDaikaiskbcod());
				poQueryVO.setKaiSkbCod(logonForm.getKaiskbcod());
				poQueryVO.setKaiSkbCod_arr(logonForm.getKaiSkbCodList());

				session.setAttribute(CommonConst.PO_QUERY_VO,poQueryVO);//発注書データ検索条件がセットされたpoQueryVOをセッションに格納
				
				fmForm.setCommand(PrsOrderForm.SUCCESS_DENPYOHAKKOU);
				return mapping.findForward("next");
			}
		}
		//「伝票発行」完了
		else if(fmForm.getCommand().equals(PrsOrderForm.SUCCESS_DENPYOHAKKOU)){
//System.out.println("Action--> 伝票発行完了");
			fmForm.setNewWindow("1");
			this.setCheckbox_checked(fmForm);//チェックボックスにチェックを入れなおす
			session.setAttribute(CommonConst.INFOMSG_KEY,mr.getMessage("info.issue.end"));
			fmForm.setCommand("INIT");
		}
		//次100件
		else if(fmForm.getCommand().equals(PrsOrderForm.NEXT)){
			leftFrame = (PrsOrderLeftFrame)session.getAttribute(CommonConst.BEAN_NAME_PRSORDER_LEFT);
			
			errors = this.dispPre_NxtData(fmForm,leftFrame,req,res,1);
			
			if(errors == null){	//処理成功
				session.setAttribute(CommonConst.BEAN_NAME_PRSORDER_LEFT,leftFrame);//左のフレームもセットし直す
				fmForm.setCommand(PrsOrderForm.SUCCESS_PREVNEXT);
				return mapping.findForward("next");
			}
		}
		//前100件
		else if(fmForm.getCommand().equals(PrsOrderForm.PREV)){
			leftFrame = (PrsOrderLeftFrame)session.getAttribute(CommonConst.BEAN_NAME_PRSORDER_LEFT);
			
			errors = this.dispPre_NxtData(fmForm,leftFrame,req,res,-1);
			
			if(errors == null){	//処理成功
				session.setAttribute(CommonConst.BEAN_NAME_PRSORDER_LEFT,leftFrame);//左のフレームもセットし直す
				fmForm.setCommand(PrsOrderForm.SUCCESS_PREVNEXT);
				return mapping.findForward("next");
			}
		}
		//前100件次100件完了
		else if(fmForm.getCommand().equals(PrsOrderForm.SUCCESS_PREVNEXT)){
			this.setCheckbox_checked(fmForm);//チェックボックスにチェックを入れなおす
		}
		
		//「照会画面へ戻る」押下時
		else if(fmForm.getCommand().equals(PrsOrderForm.COMMAND_BACK)){
//System.out.println("Action--> 照会画面へ戻る");
			leftFrame = (PrsOrderLeftFrame)session.getAttribute(CommonConst.BEAN_NAME_PRSORDER_LEFT);
			
			errors = this.back(fmForm,leftFrame,req,res);
			
			if(errors == null){	//処理成功
//				fmForm.setCommand(PrsOrderForm.COMMAND_BACK);
				session.removeAttribute(CommonConst.FORMBEAN_NAME_PRSORDER);
				session.removeAttribute(CommonConst.BEAN_NAME_PRSORDER_LEFT);
				session.removeAttribute(CommonConst.PRSORDER_VOS);
				return mapping.findForward("back");
			}
		}
		//「プレス発注」押下時
		else if(fmForm.getCommand().equals(PrsOrderForm.COMMAND_PRSHACHU)){
			//セッションからManualOrderFormを取り出し、コマンドをセット
			ManualOrderForm manualForm = (ManualOrderForm)session.getAttribute(CommonConst.FORMBEAN_NAME_MANUALORDER);
			manualForm.setCommand(ManualOrderForm.COMMAND_PRSHACHU);
			return mapping.findForward("next_manual");
			
		}//「副資材発注」押下時
		else if(fmForm.getCommand().equals(PrsOrderForm.COMMAND_FKUSHIZAIHACHU)){
			//セッションからManualOrderFormを取り出し、コマンドをセット
			ManualOrderForm manualForm = (ManualOrderForm)session.getAttribute(CommonConst.FORMBEAN_NAME_MANUALORDER);
			manualForm.setCommand(ManualOrderForm.COMMAND_FUKUHACHU);
			return mapping.findForward("next_manual");
			
		}//区分変更ボタン押下時
		else if(fmForm.getCommand().equals(PrsOrderForm.COMMAND_KBNHENKO)){
			this.kbnHenko(fmForm,leftFrame,req,res);
//			this.nynclear(fmForm,leftFrame,req,res); // 2004.01.13 add
			fmForm.setCommand(PrsOrderForm.SUCCESS_KBNHENKO);
			return mapping.findForward("next");
		}//区分変更成功
		else if(fmForm.getCommand().equals(PrsOrderForm.SUCCESS_KBNHENKO)){
			leftFrame = (PrsOrderLeftFrame)session.getAttribute(CommonConst.BEAN_NAME_PRSORDER_LEFT);
			this.setCheckbox_checked(fmForm);//チェックボックスにチェックを入れなおす
			this.setKbn(fmForm,leftFrame);
			fmForm.setPrs_FukSgn(fmForm.getPrs_FukSgn()); // 2004.01.13 add st
			if(fmForm.getPrs_FukSgn().equals(PrsOrderForm.PRSHACHU)){
				this.setNnMei(fmForm,leftFrame); 
			}				// 2004.01.13 add ed
			session.setAttribute(CommonConst.BEAN_NAME_PRSORDER_LEFT,leftFrame);//左のフレームをセットし直す
			fmForm.setCommand("INIT");
			
			if(fmForm.getPre_page().equals(PrsOrderForm.ManualOrder)){
				//セッションからManualOrderFormを取り出し、区分をセット
				ManualOrderForm manualForm = (ManualOrderForm)session.getAttribute(CommonConst.FORMBEAN_NAME_MANUALORDER);
				manualForm.setKbn(fmForm.getKbn());
			}else if(fmForm.getPre_page().equals(PrsOrderForm.ManualOrderPaste)){
				//セッションからManualOrderPasteFormを取り出し、区分をセット
				ManualOrderPasteForm mopForm = (ManualOrderPasteForm)session.getAttribute(CommonConst.FORMBEAN_NAME_MANUALORDERPASTE);
				mopForm.setKbn(fmForm.getKbn());
			}

//2004.01.13 add 納入先クリアに伴う修正 st
		}//納入先クリアボタン押下時
		else if(fmForm.getCommand().equals(PrsOrderForm.COMMAND_NHNCLEAR)){ 
			this.nynclear(fmForm,leftFrame,req,res);
			fmForm.setCommand(PrsOrderForm.SUCCESS_NHNCLEAR);
			return mapping.findForward("next");
		}//納入先クリア成功
		else if(fmForm.getCommand().equals(PrsOrderForm.SUCCESS_NHNCLEAR)){
			leftFrame = (PrsOrderLeftFrame)session.getAttribute(CommonConst.BEAN_NAME_PRSORDER_LEFT);
			this.setCheckbox_checked(fmForm);//チェックボックスにチェックを入れなおす
			this.setNhnMei(fmForm,leftFrame);
			fmForm.setCommand("INIT");
//			2004.01.13 add 納入先クリアに伴う修正 ed

		//納期一括変更ボタン押下時 2005/09/22 add
		}else if(fmForm.getCommand().equals(PrsOrderForm.COMMAND_NKICHANGE)){ 
			this.changeNki(fmForm,leftFrame,req,res);
			fmForm.setCommand(PrsOrderForm.SUCCESS_NKICHANGE);
			return mapping.findForward("next");
		//納期一括変更成功
		}else if(fmForm.getCommand().equals(PrsOrderForm.SUCCESS_NKICHANGE)){
			leftFrame = (PrsOrderLeftFrame)session.getAttribute(CommonConst.BEAN_NAME_PRSORDER_LEFT);
			this.setCheckbox_checked(fmForm);//チェックボックスにチェックを入れなおす
			this.setNki(fmForm,leftFrame);
			fmForm.setCommand("INIT");
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
	 * 副資材コードプルダウンリストのセット
	 * @param fmForm
	 * @return errors
	 */
	private ActionErrors setFukSziList(PrsOrderForm fmForm){
		
		ActionErrors errors = null;
		ArrayList fukSziList = null;
		
		try{
			//副資材一覧取得
			fukSziList = bzDelegate.doFukSziList(fmForm.getQueryDaiKaiSkbCod(),fmForm.getQueryKaiSkbCod());
			fmForm.setFukSziList(fukSziList);
			
		}catch(SQLException sqle){
			SystemException se = new SystemException(sqle);
			se.printStackTrace();
			errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.exception",sqle.toString()));
			
		}
		
		return errors;
	}

	/************************************************************************************************
	 * 		データセット
	 * 
	 ***********************************************************************************************/
	private ActionErrors setData (PrsOrderForm fmForm, PrsOrderLeftFrame leftFrame,
								  PrsOrderVO[] resultFmVOs, HttpServletRequest req,HttpServletResponse res){
//System.out.println("Action setData START");					
		ActionErrors errors = null;
			
		//初期化する
		fmForm.removePrsOrderVO();
		fmForm.removeAllRow();
		leftFrame.removeAllRow();
		
		int rowCount = 0;//行数
		int pageCount = 0;//行数
		int max = PrsOrderForm.MAX_ROW_COUNT;//最大行数
			
		//結果が1ページ内だった場合
		if(resultFmVOs.length <= max){
			fmForm.setSize(resultFmVOs.length);
			leftFrame.setSize(resultFmVOs.length);
			fmForm.setCount(resultFmVOs.length);
			rowCount = resultFmVOs.length;
			pageCount++;
		}
		//結果が1ページを超えた場合
		else{
			fmForm.setSize(max);
			leftFrame.setSize(max);
			fmForm.setCount(max);
			rowCount = max;
			pageCount = resultFmVOs.length / max;
			//半端な行が出たら1ページ足す
			if(resultFmVOs.length % max != 0)
				pageCount++;
		}

		//データをフォームにセット
//System.out.println("resultFmVOs.length : "+resultFmVOs.length);
		for(int i = 0;i < rowCount;i++){
			
		    PrsOrderFormRow formRow_R = fmForm.getFormRow_R(i);
		    PrsOrderLeftRow formRow_L = leftFrame.getFormRow_L(i);
		    
		    this.setData(fmForm,formRow_R,formRow_L,resultFmVOs[i]);
		   
		}
		fmForm.setPrsOrderVO(new ArrayList(Arrays.asList(resultFmVOs)));//全ページ分のVOをセット
		fmForm.setPageCount(pageCount);//ページ数
		fmForm.setCurrentPage(0);//現在のページ
		fmForm.setReferredMaxPage(0);//参照された最大ページ
		fmForm.setAllRowCount(resultFmVOs.length);//全ページの件数
		fmForm.setKbn(resultFmVOs[0].getKbn());//2004.01.13 add 納入先クリアに伴う修正
		
		if(resultFmVOs.length == 0){
			errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.select.notexist_prepage"));
		}
		return errors;			
			
	}
	
	
	/************************************************************************************************
	 * 		発注指示
	 * 
	 ***********************************************************************************************/
	private ActionErrors hacShiji (PrsOrderForm fmForm, PrsOrderLeftFrame leftFrame,
									HttpServletRequest req,HttpServletResponse res){
//System.out.println("Action hacShiji START");	
		ActionErrors errors = null;
		List lisFmVO = new ArrayList();
		
		ArrayList check_prs1_index = new ArrayList();
	    ArrayList check_fuk1_index = new ArrayList();
	    
	    int page = fmForm.getCurrentPage();
		int max = PrsOrderForm.MAX_ROW_COUNT;
	    
	    PrsOrderVO[] currentVOs = fmForm.getVosList(page);//現在のページのVOを取り出す
		
		for(int i = 0; i < fmForm.getSize();i++){	

			PrsOrderFormRow formRow_R = fmForm.getFormRow_R(i);
			
			//チェックされているチェックボックスのインデックスを格納する
			if(formRow_R.getCheck_fuk1())
				check_fuk1_index.add(i+"");
			
			if(!formRow_R.getCheck_prs1()){
				lisFmVO.add(null);	
				continue;
			}

			
			PrsOrderVO fmVO = currentVOs[i];
			fmVO.setPrsHacSuu(formRow_R.getPrsHacSuu().trim());
			fmVO.setPrsNki(formRow_R.getPrsNkiYear().trim(),formRow_R.getPrsNkiMonth().trim(),formRow_R.getPrsNkiDay().trim());
			fmVO.setPrsMkrCod(formRow_R.getPrsMkrCod().trim());		
			fmVO.setComment(formRow_R.getComment().trim());
			fmVO.setBiKou2(formRow_R.getBiKou2().trim());//2007/12/25 add
			fmVO.setNhnMei(formRow_R.getNhnMei().trim());
			fmVO.setCheck_prs1(formRow_R.getCheck_prs1());
			fmVO.setFukSziCod(formRow_R.getFukSziCod().trim());//2005/09/07 add
			fmVO.setFukSziNm(fmForm.getFukSziNmOptionsLabel(formRow_R.getFukSziCod()));

			lisFmVO.add(fmVO);	
		}
		//Formにセット
		fmForm.setCheck_index(check_prs1_index,check_fuk1_index);
		
		ArrayList allVos = fmForm.getPrsOrderVO();
		
		boolean flg = false;
		//処理対象指定なし
		boolean executeChecked = false;
		for(int i = 0; i<allVos.size(); i++){
			if(i == max * page){
				for(int j = 0; j < lisFmVO.size(); j++, i++){
					allVos.set(i, lisFmVO.get(j));
					if(lisFmVO.get(j) != null)
						executeChecked = true;
//if(lisFmVO.get(j) != null){
//PrsOrderVO vo = (PrsOrderVO)lisFmVO.get(j);
//System.out.println("●vo.get("+i+") : "+vo.getKigBng()+" "+vo.getTitKj()+" "+vo.getCheck_prs1());
//}else
//System.out.println("●vo.get("+i+") : null!");
				}
			}
			if(i == allVos.size())
				continue;


			PrsOrderVO vo = (PrsOrderVO)allVos.get(i);
//if(vo != null)
//System.out.println("○vo.get("+i+") : "+vo.getKigBng()+" "+vo.getTitKj()+" "+vo.getCheck_prs1());
//else
//System.out.println("○vo.get("+i+") : null!");
			if(!vo.getCheck_prs1()){
				allVos.set(i, null);
				continue;
			}
			executeChecked = true;
		}		
//		for(int i = 0; i < allVos.size(); i++){
//PrsOrderVO vo = (PrsOrderVO)allVos.get(i);
//if(vo != null)
//System.out.println("vo.get("+i+") : "+vo.getKigBng()+" "+vo.getTitKj());
//else
//System.out.println("vo.get("+i+") : null!");
//		}
		if(!executeChecked){
			errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.input.required","処理対象"));
			return errors;	
		}
		
		try{
			//発注指示実行
//			PrsOrderResult[] results = bzDelegate.doHacShiji((PrsOrderVO[])allVos.toArray(new PrsOrderVO[allVos.size()]),fmForm.getPrs_FukSgn());
			PrsOrderResult[] results = bzDelegate.doHacShiji(
					(PrsOrderVO[]) allVos.toArray(new PrsOrderVO[allVos.size()]),
					fmForm.getPrs_FukSgn(),fmForm.getBshCod());//bshCod 2005/05/26 add

			//エラーだったのはないか調べる
			for(int i = 0; i < results.length;i++){
				if(results[i] != null && !results[i].isSuccess()){
					if(errors == null)
						errors = new ActionErrors();
						
					//発注先コードが存在しない
                    if(results[i].getDescription().equals(PrsOrderDelegate.HACSAKI_NOT_EXIST)){
						String[] s = this.getErrorIndex(i,max);
						errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.insert.notfound_row",s[0]+"ページ目"+s[1],"発注先コード"));
                    }else{
						errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.internal"));
					}
				}
			}
			if(errors == null){
				 //バリューオブジェクト自体も格納
				for(int i = 0; i < results.length;i++){
					if(results[i] == null)
						continue;
						
					fmForm.setPrsOrderVO(i, results[i].getVO());
					if(page * max <= i && i < (page + 1) * max){
						//発注先名を表示しなおす
						fmForm.getFormRow_R(i % max).setHacNm(this.getSubstringStr(results[i].getVO().getHacNm(),10));
						if(fmForm.getPrs_FukSgn().equals(PrsOrderForm.PRSHACHU)){
							//プレス発注情報を書き換える
							this.setPrsHacJoho(fmForm.getFormRow_R(i % max),results[i].getVO());
						}else if(fmForm.getPrs_FukSgn().equals(PrsOrderForm.FUKHACHU)){
							//副資材発注情報を書き換える
							this.setFukHacJoho(fmForm.getFormRow_R(i % max),results[i].getVO());
						}
						//直送区分を書き換える
						fmForm.getFormRow_R(i % max).setChoksoKbn(results[i].getVO().getChoksoKbn());
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
	


	/*************************************************************************************************
	 * 		伝票発行
	 * 
	 ************************************************************************************************/
	private ActionErrors dnpHakkou (PrsOrderForm fmForm, PrsOrderLeftFrame leftFrame,PrintOrdersQueryVO poQueryVO,
									HttpServletRequest req,HttpServletResponse res){
//System.out.println("Action dnpHakkou START");
		ActionErrors errors = null;
		List lisFmVO = new ArrayList();
		
		ArrayList check_prs1_index = new ArrayList();
	    ArrayList check_fuk1_index = new ArrayList();
	    
	    int page = fmForm.getCurrentPage();
		int max = PrsOrderForm.MAX_ROW_COUNT;
		
		PrsOrderVO[] currentVOs = fmForm.getVosList(page);//現在のページのVOを取り出す
		
		for(int i = 0; i < fmForm.getSize();i++){	
			PrsOrderFormRow formRow_R = fmForm.getFormRow_R(i);
			
			//チェックされているチェックボックスのインデックスを格納する
			if(formRow_R.getCheck_fuk1())
				check_fuk1_index.add(i+"");
			
			if(!formRow_R.getCheck_prs1()){
				lisFmVO.add(null);	
				continue;
			}
				
			PrsOrderVO fmVO = currentVOs[i];

			fmVO.setPrsHacSuu(formRow_R.getPrsHacSuu().trim());			
			fmVO.setPrsNki(formRow_R.getPrsNkiYear().trim(),formRow_R.getPrsNkiMonth().trim(),formRow_R.getPrsNkiDay().trim());
			fmVO.setPrsMkrCod(formRow_R.getPrsMkrCod().trim());
			fmVO.setComment(formRow_R.getComment().trim());
			fmVO.setBiKou2(formRow_R.getBiKou2().trim());//2007/12/25 add
			fmVO.setNhnMei(formRow_R.getNhnMei().trim());
			
			fmVO.setCheck_prs1(formRow_R.getCheck_prs1());
			fmVO.setFukSziCod(formRow_R.getFukSziCod().trim());//2005/09/07 add
			fmVO.setFukSziNm(fmForm.getFukSziNmOptionsLabel(formRow_R.getFukSziCod()));
	
			lisFmVO.add(fmVO);	
		}
		//Formにセット
		fmForm.setCheck_index(check_prs1_index,check_fuk1_index);
		
		ArrayList allVos = fmForm.getPrsOrderVO();
		
		boolean flg = false;
		//処理対象指定なし
		boolean executeChecked = false;
		for(int i = 0; i<allVos.size(); i++){
			if(i == max * page){
				for(int j = 0; j < lisFmVO.size(); j++, i++){
					allVos.set(i, lisFmVO.get(j));
					if(lisFmVO.get(j) != null)
						executeChecked = true;
//if(lisFmVO.get(j) != null){
//PrsOrderVO vo = (PrsOrderVO)lisFmVO.get(j);
//System.out.println("●vo.get("+i+") : "+vo.getKigBng()+" "+vo.getTitKj()+" "+vo.getCheck_prs1());
//}else
//System.out.println("●vo.get("+i+") : null!");
				}
			}
			if(i == allVos.size())
				continue;


			PrsOrderVO vo = (PrsOrderVO)allVos.get(i);
//if(vo != null)
//System.out.println("○vo.get("+i+") : "+vo.getKigBng()+" "+vo.getTitKj()+" "+vo.getCheck_prs1());
//else
//System.out.println("○vo.get("+i+") : null!");
			if(!vo.getCheck_prs1()){
				allVos.set(i, null);
				continue;
			}
			executeChecked = true;
		}		
//		for(int i = 0; i < allVos.size(); i++){
//PrsOrderVO vo = (PrsOrderVO)allVos.get(i);
//if(vo != null)
//System.out.println("vo.get("+i+") : "+vo.getKigBng()+" "+vo.getTitKj());
//else
//System.out.println("vo.get("+i+") : null!");
//		}
		if(!executeChecked){
			errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.input.required","処理対象"));
			return errors;	
		}
		
		try{
			//伝票発行実行
//			PrsOrderResult[] results = bzDelegate.doDnpHakkou((PrsOrderVO[])allVos.toArray(new PrsOrderVO[allVos.size()]),fmForm.getPrs_FukSgn());
			PrsOrderResult[] results = bzDelegate.doDnpHakkou(
					(PrsOrderVO[]) allVos.toArray(new PrsOrderVO[allVos.size()]),
					fmForm.getPrs_FukSgn(),fmForm.getBshCod());//bshCod 2005/05/26 add

			
			//エラーだったのはないか調べる
			for(int i = 0; i < results.length;i++){
				if(results[i] != null && !results[i].isSuccess()){
					if(errors == null)
						errors = new ActionErrors();
						
					//発注先コードが存在しない
                    if(results[i].getDescription().equals(PrsOrderDelegate.HACSAKI_NOT_EXIST)){
						String[] s = this.getErrorIndex(i,max);
						errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.insert.notfound_row",s[0]+"ページ目"+s[1],"発注先コード"));
                    }else{
						errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.internal"));
					}
				}
			}
			if(errors == null){
				 //バリューオブジェクト自体も格納
				boolean b = false;
				for(int i = 0; i < results.length;i++){
					if(results[i] == null)
						continue;

					PrsOrderVO fmVO = results[i].getVO();
					fmForm.setPrsOrderVO(i, fmVO);
					if(page * max <= i && i < (page + 1) * max){
						if(fmForm.getPrs_FukSgn().equals(PrsOrderForm.PRSHACHU)){
							//プレス発注情報を書き換える
							this.setPrsHacJoho(fmForm.getFormRow_R(i % max),results[i].getVO());
						}else if(fmForm.getPrs_FukSgn().equals(PrsOrderForm.FUKHACHU)){
							//副資材発注情報を書き換える
							this.setFukHacJoho(fmForm.getFormRow_R(i % max),results[i].getVO());
						}
						//発注先名を表示しなおす
						fmForm.getFormRow_R(i % max).setHacNm(this.getSubstringStr(fmVO.getHacNm(),10));
						//直送区分を書き換える
						fmForm.getFormRow_R(i % max).setChoksoKbn(results[i].getVO().getChoksoKbn());
					}					
					//（nullでない最初の）resultsから処理回数を取出してpoQueryVOにセット
					if(results[i] != null && !b){
						poQueryVO.setSyrSuu(results[i].getSyrSuu());
						b = true;
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
	
	/**
	 * 前100件次100件
	 * 
	 */
	private ActionErrors dispPre_NxtData (PrsOrderForm fmForm, PrsOrderLeftFrame leftFrame,
									HttpServletRequest req,HttpServletResponse res, int addIndex){
//System.out.println("Action dispPrevNextData START");					
		ActionErrors errors = null;
		PrsOrderVO[] resultVOs = null;//前or次ページのVOを格納
		PrsOrderVO[] currentVOs = null;//現在のページのVOを格納

		currentVOs = fmForm.getVosList(fmForm.getCurrentPage());
		//変更されている可能性のある項目をセットしなおす
		for(int i = 0; i < currentVOs.length; i++){
			
			PrsOrderFormRow formRow_R = fmForm.getFormRow_R(i);
			PrsOrderLeftRow formRow_L = leftFrame.getFormRow_L(i);
				
			currentVOs[i].setPrsHacSuu(formRow_R.getPrsHacSuu().trim());
			currentVOs[i].setPrsNki(formRow_R.getPrsNkiYear().trim(),formRow_R.getPrsNkiMonth().trim(),formRow_R.getPrsNkiDay().trim());
			currentVOs[i].setPrsMkrCod(formRow_R.getPrsMkrCod().trim());
			currentVOs[i].setComment(formRow_R.getComment().trim());
			currentVOs[i].setBiKou2(formRow_R.getBiKou2().trim());//2007/12/25 add
			currentVOs[i].setNhnMei(formRow_R.getNhnMei().trim());
			
			currentVOs[i].setCheck_prs1(formRow_R.getCheck_prs1());
			currentVOs[i].setCheck_fuk1(formRow_R.getCheck_fuk1());
			currentVOs[i].setFukSziCod(formRow_R.getFukSziCod().trim());//2005/09/07 add
			currentVOs[i].setFukSziNm(fmForm.getFukSziNmOptionsLabel(formRow_R.getFukSziCod()));
		}
		
		//対象ページのデータを取得
		try{
			ArrayList check_prs1_index = new ArrayList();
	    	ArrayList check_fuk1_index = new ArrayList();
			
			//前or次ページのVOを取得
			resultVOs = fmForm.getVosList(fmForm.getCurrentPage() + addIndex);
			
			if(resultVOs == null)
				throw new IndexOutOfBoundsException();
			
			//初期化する
			fmForm.removeAllRow();
			leftFrame.removeAllRow();
					
			//結果をフォームにセット
			fmForm.setSize(resultVOs.length);
			leftFrame.setSize(resultVOs.length);
			fmForm.setCount(resultVOs.length);
			fmForm.clearTableField();
			leftFrame.clearTableField();
			
			for(int i = 0; i < resultVOs.length; i++){
				PrsOrderFormRow formRow_R = fmForm.getFormRow_R(i);
				PrsOrderLeftRow formRow_L = leftFrame.getFormRow_L(i);

				this.setData(fmForm,formRow_R,formRow_L,resultVOs[i]);

				//チェックされているチェックボックスのインデックスを格納する
				if(formRow_R.getCheck_prs1())
					check_prs1_index.add(i+"");
				if(formRow_R.getCheck_fuk1())
					check_fuk1_index.add(i+"");
			}
			fmForm.setCurrentPage(fmForm.getCurrentPage() + addIndex);//現在のページ
			fmForm.setReferredMaxPage(fmForm.getCurrentPage());//参照された最大ページ
			fmForm.setCheck_index(check_prs1_index,check_fuk1_index);//チェックボックスのインデックスをセット
			
		}catch(IndexOutOfBoundsException e){
			resultVOs = new PrsOrderVO[0];
		}		
		if(resultVOs.length == 0){
			errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.select.notexist"));
		}
			
		return errors;			
			
	}

	/************************************************************************************************
	 * 		提案数照会画面へ戻る
	 * 
	 ***********************************************************************************************/
	private ActionErrors back (PrsOrderForm fmForm, PrsOrderLeftFrame leftFrame,
									HttpServletRequest req,HttpServletResponse res){
//System.out.println("PrsOrderAction back START ---------------------------------------------------");	

		ActionErrors errors = null;
		HttpSession session = req.getSession();
		
		ArrayList check_prs1_index = new ArrayList();
	   	ArrayList check_prs2_index = new ArrayList();
	   	ArrayList check_fuk1_index = new ArrayList();
		ArrayList check_prsHacSuu_index = new ArrayList();
			
			
		//提案数照会画面のデータ
		IkkatsuReferForm ikkatsuForm = new IkkatsuReferForm();
				
		IkkatsuReferLeftFrame ikkatsuLeftFrame = new IkkatsuReferLeftFrame();

		ikkatsuForm = (IkkatsuReferForm)session.getAttribute(CommonConst.FORMBEAN_NAME_IKKATSU);
		ikkatsuLeftFrame = (IkkatsuReferLeftFrame)session.getAttribute(CommonConst.BEAN_NAME_IKKATSU_LEFT);
		
		int page = fmForm.getCurrentPage();
		int page_teian = 0;
		ikkatsuForm.setCurrentPage(page_teian);
		ikkatsuForm.clearReferredMaxPage();
		
		int max = PrsOrderForm.MAX_ROW_COUNT;
		int max_teian = IkkatsuReferForm.MAX_ROW_COUNT;
		
		PrsOrderVO[] currentVOs = fmForm.getVosList(fmForm.getCurrentPage());//現在のページのVOを取り出す
		
		//変更されている可能性のある項目をセットしなおす
		for(int i = 0; i < currentVOs.length; i++){
			
			PrsOrderFormRow formRow_R = fmForm.getFormRow_R(i);
			PrsOrderLeftRow formRow_L = leftFrame.getFormRow_L(i);
				
			currentVOs[i].setPrsHacSuu(formRow_R.getPrsHacSuu().trim());
			currentVOs[i].setPrsNki(formRow_R.getPrsNkiYear().trim(),formRow_R.getPrsNkiMonth().trim(),formRow_R.getPrsNkiDay().trim());
			currentVOs[i].setPrsMkrCod(formRow_R.getPrsMkrCod().trim());
			currentVOs[i].setComment(formRow_R.getComment().trim());
			currentVOs[i].setBiKou2(formRow_R.getBiKou2().trim());//2007/12/25 add
			currentVOs[i].setNhnMei(formRow_R.getNhnMei().trim());
			
			currentVOs[i].setCheck_prs1(formRow_R.getCheck_prs1());
			currentVOs[i].setCheck_fuk1(formRow_R.getCheck_fuk1());
			
		}
			
		ArrayList prsOrderVOs = fmForm.getPrsOrderVO();
		ArrayList ikkatsuVOs = ikkatsuForm.getAllVos();
		
		
		for(int i = 0, j = 0; i < prsOrderVOs.size(); i++){ //========================================
			
			PrsOrderVO prsOrderVO;
			
			if(page * max <= i && i < (page * max) + currentVOs.length){
				prsOrderVO = currentVOs[j];
				j++;
			}else{
				prsOrderVO = (PrsOrderVO)prsOrderVOs.get(i);			
			}
			if(!prsOrderVO.getBasedRowFlg().equals("1"))//基準行フラグ=1のものだけ提案数照会画面に返す
				continue;
			
			IkkatsuReferVO ikkatsuVO = (IkkatsuReferVO)ikkatsuVOs.get(prsOrderVO.getIndex());
			
			if(fmForm.getPrs_FukSgn().equals(PrsOrderForm.PRSHACHU)){
				ikkatsuVO.setPrsHacSuu(prsOrderVO.getPrsHacSuu());			
				ikkatsuVO.setPrsNki(prsOrderVO.getPrsNki());
				ikkatsuVO.setPrsMkrCod(prsOrderVO.getPrsMkrCod());
			}
			if(prsOrderVO.getSyrZmiSgn_prs() == IkkatsuReferDAO.HACZMI
			|| prsOrderVO.getSyrZmiSgn_prs() == IkkatsuReferDAO.SYRZMI){
				ikkatsuVO.setCheck_prs1(false);
				ikkatsuVO.setCheck_prs2(false);
				ikkatsuVO.setCheck_prsHacSuu(false);
			}
			
			//プレス情報
			ikkatsuVO.setPrsHacSuu1(prsOrderVO.getPrsHacSuu1());
			ikkatsuVO.setPrsHacSuu2(prsOrderVO.getPrsHacSuu2());
			ikkatsuVO.setPrsHacNyo1(prsOrderVO.getPrsHacNyo1());
			ikkatsuVO.setPrsHacNyo2(prsOrderVO.getPrsHacNyo2());
			ikkatsuVO.setPrsHacNki1(prsOrderVO.getPrsHacNki1());
			ikkatsuVO.setPrsHacNki2(prsOrderVO.getPrsHacNki2());
			ikkatsuVO.setPrsKbn1(prsOrderVO.getPrsKbn1());
			ikkatsuVO.setPrsKbn2(prsOrderVO.getPrsKbn2());			

			//副資材情報
			ikkatsuVO.setFukHacSuu(prsOrderVO.getFukHacSuu1());//前回発注数をセット
			ikkatsuVO.setFukHacSuu1(prsOrderVO.getFukHacSuu1());
			ikkatsuVO.setFukHacSuu2(prsOrderVO.getFukHacSuu2());
			ikkatsuVO.setFukHacNki1(prsOrderVO.getFukHacNki1());
			ikkatsuVO.setFukHacNki2(prsOrderVO.getFukHacNki2());
			ikkatsuVO.setFukKbn1(prsOrderVO.getFukKbn1());
			ikkatsuVO.setFukKbn2(prsOrderVO.getFukKbn2());
//			ikkatsuVO.setFukZaiSuu(prsOrderVO.getFukZaiSuu());
			
			if(prsOrderVO.getSyrZmiSgn_fuk() == IkkatsuReferDAO.HACZMI
			|| prsOrderVO.getSyrZmiSgn_fuk() == IkkatsuReferDAO.SYRZMI){
				ikkatsuVO.setCheck_fuk1(false);
				ikkatsuVO.setCheck_flg(false);
				ikkatsuVO.setCheck_sgn("");
			}
			
//			//出力済サイン
			ikkatsuVO.setHacZmiSgn(prsOrderVO.getSyrZmiSgn_prs());
//			ikkatsuVO.setHacFlg(true);
			
			
			//チェックされているチェックボックスのインデックスを格納する
//			if(ikkatsuVO.getCheck_prs1())
//				check_prs1_index.add(prsOrderVO.getIndex()+"");
//			if(ikkatsuVO.getCheck_prs2())
//				check_prs2_index.add(prsOrderVO.getIndex()+"");
//			if(ikkatsuVO.getCheck_fuk1())
//				check_fuk1_index.add(prsOrderVO.getIndex()+"");
//			if(ikkatsuVO.getCheck_prsHacSuu())
//				check_prsHacSuu_index.add(prsOrderVO.getIndex()+"");
							
			ikkatsuForm.setIkkatsuReferVO_all(prsOrderVO.getIndex(), ikkatsuVO);	

					
		}//=======================================================================================
		
		ikkatsuForm.setSize(Math.min(max_teian, ikkatsuVOs.size()));
		ikkatsuLeftFrame.setSize(Math.min(max_teian, ikkatsuVOs.size()));
		
		IkkatsuReferVO ikkatsuVO[] = ikkatsuForm.getVosList(ikkatsuForm.getCurrentPage());
		
		//Formの内容をセットしなおす
		for(int i = 0; i < ikkatsuForm.getSize(); i++){//----------------------------
			
			IkkatsuReferFormRow ikkatsuRow_R = ikkatsuForm.getFormRow_R(i);
			IkkatsuReferLeftRow ikkatsuRow_L = ikkatsuLeftFrame.getFormRow_L(i);
			
			//キー項目
		    ikkatsuRow_R.setDaiKaiSkbCod(ikkatsuVO[i].getDaiKaiSkbCod());
		    ikkatsuRow_L.setDaiKaiSkbCod(ikkatsuVO[i].getDaiKaiSkbCod());
		    ikkatsuRow_R.setKaiSkbCod(ikkatsuVO[i].getKaiSkbCod());
		    ikkatsuRow_L.setKaiSkbCod(ikkatsuVO[i].getKaiSkbCod());
		    ikkatsuRow_R.setKigBng(ikkatsuVO[i].getKigBng());
		    ikkatsuRow_L.setKigBng(ikkatsuVO[i].getKigBng());
		    //品番マスター
		    ikkatsuRow_L.setKetCod(ikkatsuVO[i].getKetCod());
		    ikkatsuRow_L.setKetNm(ikkatsuVO[i].getKetNm());
		    ikkatsuRow_L.setHjiHnb(ikkatsuVO[i].getHjiHnb());//表示品番をセット
		    ikkatsuRow_L.setArtKj(ikkatsuVO[i].getArtKj());
		    ikkatsuRow_L.setHbiDte(DataFormatUtils.setFormatYYMMDD(ikkatsuVO[i].getHbiDte()));
		    ikkatsuRow_L.setTitKj(ikkatsuVO[i].getTitKj());
		    ikkatsuRow_L.setTomRak(ikkatsuVO[i].getTomRak());
		    ikkatsuRow_L.setZikTik(ikkatsuVO[i].getZikTik()); //2004.04.21 add 
	    	ikkatsuRow_R.setPrsMkrCod(ikkatsuVO[i].getPrsMkrCod());
	    
	    	//発注履歴テーブル
	    	ikkatsuRow_L.setHacZmiSgn(this.getHacZmiSgnString(ikkatsuVO[i].getHacZmiSgn()));
	    	//在庫テーブル
		    ikkatsuRow_L.setAziSuu(ikkatsuVO[i].getAziSuu());
			ikkatsuRow_L.setTnaSekZan(ikkatsuVO[i].getTnaSekZan());
			ikkatsuRow_L.setJucZan(ikkatsuVO[i].getJucZan());
			ikkatsuRow_L.setMhikSuu(ikkatsuVO[i].getMhikSuu());
			ikkatsuRow_L.setRsvSum(ikkatsuVO[i].getRsvSum());
			
			ikkatsuRow_R.setTodUriSuu(ikkatsuVO[i].getTodUriSuu());
			ikkatsuRow_R.setB1dUri(ikkatsuVO[i].getB1dUri());
			ikkatsuRow_R.setB2dUri(ikkatsuVO[i].getB2dUri());
			ikkatsuRow_R.setB3dUri(ikkatsuVO[i].getB3dUri());
			ikkatsuRow_R.setB4dUri(ikkatsuVO[i].getB4dUri());
			ikkatsuRow_R.setB5dUri(ikkatsuVO[i].getB5dUri());
			ikkatsuRow_R.setTowUri(ikkatsuVO[i].getTowUri());
			ikkatsuRow_R.setB1wUri(ikkatsuVO[i].getB1wUri());
			ikkatsuRow_R.setB2wUri(ikkatsuVO[i].getB2wUri());
			ikkatsuRow_R.setTomUri(ikkatsuVO[i].getTomUri());
			ikkatsuRow_R.setB1mUri(ikkatsuVO[i].getB1mUri());
			ikkatsuRow_R.setB2mUri(ikkatsuVO[i].getB2mUri());
			ikkatsuRow_R.setTomHpn(ikkatsuVO[i].getTomHpn());
			ikkatsuRow_R.setB1mHpn(ikkatsuVO[i].getB1mHpn());
			ikkatsuRow_R.setB2mHpn(ikkatsuVO[i].getB2mHpn());
			ikkatsuRow_R.setTomTna(ikkatsuVO[i].getTomTna());
			ikkatsuRow_R.setB1mTna(ikkatsuVO[i].getB1mTna());
			ikkatsuRow_R.setB2mTna(ikkatsuVO[i].getB2mTna());
			
			ikkatsuRow_L.setAvgUri(ikkatsuVO[i].getAvgUri());
			
			ikkatsuRow_R.setPrsMnyKei(ikkatsuVO[i].getPrsMnyKei());
			ikkatsuRow_R.setPrsHacRui(ikkatsuVO[i].getPrsHacRui());
			ikkatsuRow_R.setFukMnyKei(ikkatsuVO[i].getFukMnyKei());
			ikkatsuRow_R.setFukHacRui(ikkatsuVO[i].getFukHacRui());
			ikkatsuRow_R.setPrsNyoKei(ikkatsuVO[i].getPrsNyoKei());
			ikkatsuRow_R.setFukNyoKei(ikkatsuVO[i].getFukNyoKei());
			ikkatsuRow_L.setFukHacRui(ikkatsuVO[i].getFukHacRui());
			ikkatsuRow_L.setFukZaiSuu(ikkatsuVO[i].getFukZaiSuu());
			ikkatsuRow_L.setFukNyoKei(ikkatsuVO[i].getFukNyoKei());
	    
			//発注履歴テーブル（プレス）
			ikkatsuRow_R.setPrsHacSuu1(ikkatsuVO[i].getPrsHacSuu1());
			ikkatsuRow_R.setPrsHacNyo1(ikkatsuVO[i].getPrsHacNyo1());
			if(!ikkatsuVO[i].getPrsHacNki1().equals(""))
				ikkatsuRow_R.setPrsHacNki1(DataFormatUtils.setFormatYYMMDD(ikkatsuVO[i].getPrsHacNki1()));
			ikkatsuRow_R.setPrsKbn1(DataFormatUtils.getKbnString(ikkatsuVO[i].getPrsKbn1()));
			ikkatsuRow_R.setPrsHacSuu2(ikkatsuVO[i].getPrsHacSuu2());
			ikkatsuRow_R.setPrsHacNyo2(ikkatsuVO[i].getPrsHacNyo2());
			if(!ikkatsuVO[i].getPrsHacNki2().equals(""))
				ikkatsuRow_R.setPrsHacNki2(DataFormatUtils.setFormatYYMMDD(ikkatsuVO[i].getPrsHacNki2()));
			ikkatsuRow_R.setPrsKbn2(DataFormatUtils.getKbnString(ikkatsuVO[i].getPrsKbn2()));
			
			//発注履歴テーブル（副資材）
			ikkatsuRow_R.setFukHacSuu1(ikkatsuVO[i].getFukHacSuu1());
			ikkatsuRow_L.setFukHacSuu1(ikkatsuVO[i].getFukHacSuu1());
			ikkatsuRow_R.setFukHacNyo1(ikkatsuVO[i].getFukHacNyo1());
			if(!ikkatsuVO[i].getFukHacNki1().equals(""))
				ikkatsuRow_R.setFukHacNki1(DataFormatUtils.setFormatYYMMDD(ikkatsuVO[i].getFukHacNki1()));
			ikkatsuRow_R.setFukKbn1(DataFormatUtils.getKbnString(ikkatsuVO[i].getFukKbn1()));
			ikkatsuRow_R.setFukHacSuu2(ikkatsuVO[i].getFukHacSuu2());
			ikkatsuRow_R.setFukHacNyo2(ikkatsuVO[i].getFukHacNyo2());
			if(!ikkatsuVO[i].getFukHacNki2().equals(""))
				ikkatsuRow_R.setFukHacNki2(DataFormatUtils.setFormatYYMMDD(ikkatsuVO[i].getFukHacNki2()));
			ikkatsuRow_R.setFukKbn2(DataFormatUtils.getKbnString(ikkatsuVO[i].getFukKbn2()));
			
			//副資材マスター
			ikkatsuRow_R.setFukMkrCod(ikkatsuVO[i].getFukSziHacSaki());

			//導出項目-------------------------------------------

			ikkatsuRow_L.setTeianSuu(ikkatsuVO[i].getTeianSuu());
			ikkatsuRow_L.setFskZaiSuu(ikkatsuVO[i].getFskZaiSuu());
			ikkatsuRow_R.setPrsHacSuu(ikkatsuVO[i].getPrsHacSuu());
			ikkatsuRow_R.setFukHacSuu(ikkatsuVO[i].getFukHacSuu());

			ikkatsuRow_R.setCheck_prs1(ikkatsuVO[i].getCheck_prs1());
			ikkatsuRow_R.setCheck_prs2(ikkatsuVO[i].getCheck_prs2());
			ikkatsuRow_R.setCheck_fuk1(ikkatsuVO[i].getCheck_fuk1());
			ikkatsuRow_R.setCheck_flg(ikkatsuVO[i].getCheck_flg());
			ikkatsuRow_R.setCheck_prsHacSuu(ikkatsuVO[i].getCheck_prsHacSuu());
			ikkatsuRow_R.setCheck_sgn(ikkatsuVO[i].getCheck_sgn());

			if(ikkatsuVO[i].getPrsNki() != 0){
				ikkatsuRow_R.setPrsNkiYear(ikkatsuVO[i].getPrsNkiYear());
				ikkatsuRow_R.setPrsNkiMonth(ikkatsuVO[i].getPrsNkiMonth());
				ikkatsuRow_R.setPrsNkiDay(ikkatsuVO[i].getPrsNkiDay());
			}
			if(ikkatsuVO[i].getFukNki() != 0){
				ikkatsuRow_R.setFukNkiYear(ikkatsuVO[i].getFukNkiYear());
				ikkatsuRow_R.setFukNkiMonth(ikkatsuVO[i].getFukNkiMonth());
				ikkatsuRow_R.setFukNkiDay(ikkatsuVO[i].getFukNkiDay());
			}

			ikkatsuRow_L.setZaiNisuu(ikkatsuVO[i].getZaiNisuu());
			ikkatsuRow_R.setChoksoKbn(ikkatsuVO[i].getChoksoKbn());
			
			//チェックされているチェックボックスのインデックスを格納する
			if(ikkatsuVO[i].getCheck_prs1())
				check_prs1_index.add(i+"");
			if(ikkatsuVO[i].getCheck_prs2())
				check_prs2_index.add(i+"");
			if(ikkatsuVO[i].getCheck_fuk1())
				check_fuk1_index.add(i+"");
			if(ikkatsuVO[i].getCheck_prsHacSuu())
				check_prsHacSuu_index.add(i+"");
			
		}//-----------------------------------------------------------------------------
		
		ikkatsuForm.setCheck_index(check_prs1_index,check_prs2_index,check_fuk1_index,check_prsHacSuu_index);//チェックボックスのインデックスをセット
		
		session.setAttribute(CommonConst.FORMBEAN_NAME_IKKATSU,ikkatsuForm);
		session.setAttribute(CommonConst.BEAN_NAME_IKKATSU_LEFT,ikkatsuLeftFrame);


		return errors;			
			
	}
	
	/************************************************************************************************
	 * 		区分変更				2003/09/11 add
	 * 
	 ***********************************************************************************************/
	private void kbnHenko (PrsOrderForm fmForm, PrsOrderLeftFrame leftFrame,
									HttpServletRequest req,HttpServletResponse res){
		
		PrsOrderVO[] currentVOs = null;//現在のページのVOを格納
		ArrayList check_prs1_index = new ArrayList();
	    ArrayList check_fuk1_index = new ArrayList();
		
		currentVOs = fmForm.getVosList(fmForm.getCurrentPage());
		//変更されている可能性のある項目をセットしなおす
		for(int i = 0; i < currentVOs.length; i++){
			
			PrsOrderFormRow formRow_R = fmForm.getFormRow_R(i);
			
			//チェックされているチェックボックスのインデックスを格納する
			// 2003.12.19 upd 全品番にチェックを付ける
//			if(formRow_R.getCheck_prs1())
				check_prs1_index.add(i+"");
//			if(formRow_R.getCheck_fuk1())
				check_fuk1_index.add(i+"");
			
			currentVOs[i].setPrsHacSuu(formRow_R.getPrsHacSuu().trim());
			currentVOs[i].setPrsNki(formRow_R.getPrsNkiYear().trim(),formRow_R.getPrsNkiMonth().trim(),formRow_R.getPrsNkiDay().trim());
			currentVOs[i].setPrsMkrCod(formRow_R.getPrsMkrCod().trim());
			currentVOs[i].setComment(formRow_R.getComment().trim());
			currentVOs[i].setBiKou2(formRow_R.getBiKou2().trim());//200712/25 add
			currentVOs[i].setNhnMei(formRow_R.getNhnMei().trim());
			
			currentVOs[i].setCheck_prs1(formRow_R.getCheck_prs1());
			currentVOs[i].setCheck_fuk1(formRow_R.getCheck_fuk1());
			currentVOs[i].setFukSziCod(formRow_R.getFukSziCod().trim());//2005/09/07 add
		}
		//Formにセット
		fmForm.setCheck_index(check_prs1_index,check_fuk1_index);
		
		//区分変更
		String kbn = fmForm.getKbn();							
		for(int i = 0;i < fmForm.getAllRowCount(); i++){

		    fmForm.getPrsOrderVO(i).setKbn(kbn);
			if(fmForm.getPrs_FukSgn().equals(PrsOrderForm.PRSHACHU)){
				fmForm.getPrsOrderVO(i).setNhnMei(DataFormatUtils.getSkoNm(fmForm.getBshCod()));//2005.02.03 add
			}				// 2004.01.13 add ed
			fmForm.getPrsOrderVO(i).setCheck_fuk1(true); //2004.01.15 add
			fmForm.getPrsOrderVO(i).setCheck_prs1(true); //2004.01.15 add

		}
		
							
	}
	private void setKbn (PrsOrderForm fmForm, PrsOrderLeftFrame leftFrame){
		//区分変更
		String kbn = fmForm.getKbn();							
		for(int i = 0;i < leftFrame.getSize(); i++){

		    leftFrame.getFormRow_L(i).setKbn(DataFormatUtils.getKbnString(kbn));
		   
		}
	}
	//2004.01.14 add
	private void setNnMei (PrsOrderForm fmForm, PrsOrderLeftFrame leftFrame){
		//納入先クリア							
		for(int i = 0;i < leftFrame.getSize(); i++){
			//2005.02.03 add
			fmForm.getFormRow_R(i).setNhnMei(DataFormatUtils.getSkoNm(fmForm.getBshCod()));		   
		}
	}

	
	/************************************************************************************************
	 * 		納入先クリア				2004/01/13 add
	 * 
	 ***********************************************************************************************/
	private void nynclear (PrsOrderForm fmForm, PrsOrderLeftFrame leftFrame,
									HttpServletRequest req,HttpServletResponse res){
		
		PrsOrderVO[] currentVOs = null;//現在のページのVOを格納
		ArrayList check_prs1_index = new ArrayList();
		ArrayList check_fuk1_index = new ArrayList();
		
		currentVOs = fmForm.getVosList(fmForm.getCurrentPage());
		//変更されている可能性のある項目をセットしなおす
		for(int i = 0; i < currentVOs.length; i++){
			
			PrsOrderFormRow formRow_R = fmForm.getFormRow_R(i);
			
			//チェックされているチェックボックスのインデックスを格納する
				check_prs1_index.add(i+"");
				check_fuk1_index.add(i+"");
			
			currentVOs[i].setPrsHacSuu(formRow_R.getPrsHacSuu().trim());
			currentVOs[i].setPrsNki(formRow_R.getPrsNkiYear().trim(),formRow_R.getPrsNkiMonth().trim(),formRow_R.getPrsNkiDay().trim());
			currentVOs[i].setPrsMkrCod(formRow_R.getPrsMkrCod().trim());
			currentVOs[i].setComment(formRow_R.getComment().trim());
			currentVOs[i].setBiKou2(formRow_R.getBiKou2().trim());//2007/12/25 add
			currentVOs[i].setNhnMei(formRow_R.getNhnMei().trim());
			
			currentVOs[i].setCheck_prs1(formRow_R.getCheck_prs1());
			currentVOs[i].setCheck_fuk1(formRow_R.getCheck_fuk1());
			currentVOs[i].setFukSziCod(formRow_R.getFukSziCod().trim());//2005/09/07 add
		}
		//Formにセット
		fmForm.setCheck_index(check_prs1_index,check_fuk1_index);
		
		//納入先クリア
		for(int i = 0;i < fmForm.getAllRowCount(); i++){

			fmForm.getPrsOrderVO(i).setNhnMei("別");
			fmForm.getPrsOrderVO(i).setCheck_fuk1(true); //2004.01.15 add
			fmForm.getPrsOrderVO(i).setCheck_prs1(true); //2004.01.15 add

		}
		
							
	}
	private void setNhnMei (PrsOrderForm fmForm, PrsOrderLeftFrame leftFrame){
		//納入先クリア							
		for(int i = 0;i < leftFrame.getSize(); i++){
				fmForm.getFormRow_R(i).setNhnMei("別");
		}
	}

	/************************************************************************************************
	 * 		納期一括変更				2005/09/21 add
	 * 
	 ***********************************************************************************************/
	private void changeNki (PrsOrderForm fmForm, PrsOrderLeftFrame leftFrame,
									HttpServletRequest req,HttpServletResponse res){
		
		PrsOrderVO[] currentVOs = null;//現在のページのVOを格納
		ArrayList check_prs1_index = new ArrayList();
		ArrayList check_fuk1_index = new ArrayList();
		
		currentVOs = fmForm.getVosList(fmForm.getCurrentPage());
		//変更されている可能性のある項目をセットしなおす
		for(int i = 0; i < currentVOs.length; i++){
			
			PrsOrderFormRow formRow_R = fmForm.getFormRow_R(i);
			
			//チェックされているチェックボックスのインデックスを格納する
			check_prs1_index.add(i+"");
			check_fuk1_index.add(i+"");
			
			currentVOs[i].setPrsHacSuu(formRow_R.getPrsHacSuu().trim());
			currentVOs[i].setPrsNki(formRow_R.getPrsNkiYear().trim(),formRow_R.getPrsNkiMonth().trim(),formRow_R.getPrsNkiDay().trim());
			currentVOs[i].setPrsMkrCod(formRow_R.getPrsMkrCod().trim());
			currentVOs[i].setComment(formRow_R.getComment().trim());
			currentVOs[i].setBiKou2(formRow_R.getBiKou2().trim());//2007/12/25 add
			currentVOs[i].setNhnMei(formRow_R.getNhnMei().trim());
			
			currentVOs[i].setCheck_prs1(formRow_R.getCheck_prs1());
			currentVOs[i].setCheck_fuk1(formRow_R.getCheck_fuk1());
			currentVOs[i].setFukSziCod(formRow_R.getFukSziCod().trim());
		}
		//Formにセット
		fmForm.setCheck_index(check_prs1_index,check_fuk1_index);
		
		//納期一括変更
		for(int i = 0;i < fmForm.getAllRowCount(); i++){
			fmForm.getPrsOrderVO(i).setCheck_fuk1(true);
			fmForm.getPrsOrderVO(i).setCheck_prs1(true);
			fmForm.getPrsOrderVO(i).setPrsNki(fmForm.getNki_Y(),fmForm.getNki_M(),fmForm.getNki_D());
		}
	}

	private void setNki (PrsOrderForm fmForm, PrsOrderLeftFrame leftFrame){
		//納期一括変更
		for(int i = 0;i < leftFrame.getSize(); i++){
			fmForm.getFormRow_R(i).setPrsNkiYear(fmForm.getNki_Y());
			fmForm.getFormRow_R(i).setPrsNkiMonth(fmForm.getNki_M());
			fmForm.getFormRow_R(i).setPrsNkiDay(fmForm.getNki_D());
		}
	}


//---------------------------------------------------------------------------------------------------	
	
	/**
	 *  VOのデータをFormにセットするメソッド
	 * 
	 */
	private void setData(PrsOrderForm fmForm, PrsOrderFormRow formRow_R, PrsOrderLeftRow formRow_L, 
				PrsOrderVO resultVO){

		//キー項目
	    formRow_R.setDaiKaiSkbCod(resultVO.getDaiKaiSkbCod());
	    formRow_L.setDaiKaiSkbCod(resultVO.getDaiKaiSkbCod());
	    formRow_R.setKaiSkbCod(resultVO.getKaiSkbCod());
	    formRow_L.setKaiSkbCod(resultVO.getKaiSkbCod());
	    formRow_R.setKigBng(resultVO.getKigBng());
	    formRow_L.setKigBng(resultVO.getKigBng());
	    
	    //品番マスター
	    formRow_L.setKetCod(resultVO.getKetCod());
	    formRow_L.setKetNm(resultVO.getKetNm());
	    formRow_L.setHjiHnb(resultVO.getHjiHnb());//表示品番をセット
	    formRow_L.setArtKj(resultVO.getArtKj());
	    if(!resultVO.getHbiDte().equals(""))
	    	formRow_L.setHbiDte(DataFormatUtils.setFormatYYMMDD(resultVO.getHbiDte()));
	    formRow_L.setTitKj(resultVO.getTitKj());
	    formRow_L.setTomRak(resultVO.getTomRak());
	    formRow_L.setZikTik(resultVO.getZikTik()); //2004.04.21 add 
	    formRow_R.setPrsMkrCod(resultVO.getPrsMkrCod());
	    
	    //副資材パターンコード
		formRow_L.setFukSziPtn(resultVO.getFukSziPtn());

	    formRow_L.setKbn(DataFormatUtils.getKbnString(resultVO.getKbn()));
	    formRow_R.setPrsHacSuu(resultVO.getPrsHacSuu());
			
		if(resultVO.getPrsNki() != 0){
			formRow_R.setPrsNkiYear(resultVO.getPrsNkiYear());
			formRow_R.setPrsNkiMonth(resultVO.getPrsNkiMonth());
			formRow_R.setPrsNkiDay(resultVO.getPrsNkiDay());
		}
		formRow_R.setComment(resultVO.getComment());
		formRow_R.setBiKou2(resultVO.getBiKou2());//2007/12/25 add
		formRow_R.setHacNm(this.getSubstringStr(resultVO.getHacNm(),10));
//		formRow_R.setNhnMei(resultVO.getNhnMei());  upd 04/03/03 
		formRow_R.setNhnMei(this.getSubstringStr(resultVO.getNhnMei(),20));
		formRow_R.setChoksoKbn(resultVO.getChoksoKbn());

		formRow_R.setCheck_prs1(resultVO.getCheck_prs1());
		formRow_R.setCheck_fuk1(resultVO.getCheck_fuk1());
	    			    
	    //在庫テーブル
	    if(resultVO.getExsitZai01()){
			formRow_R.setPrsMnyKei(resultVO.getPrsMnyKei());
			formRow_R.setPrsHacRui(resultVO.getPrsHacRui());
			formRow_R.setFukMnyKei(resultVO.getFukMnyKei());
			formRow_R.setFukHacRui(resultVO.getFukHacRui());
			formRow_R.setFukZaiSuu(resultVO.getFukZaiSuu());
			formRow_L.setFukHacRui(resultVO.getFukHacRui());
			formRow_R.setPrsNyoKei(resultVO.getPrsNyoKei());
			formRow_R.setFukNyoKei(resultVO.getFukNyoKei());
	    }
		
		//発注履歴テーブル（プレス）
		if(resultVO.getExsitHac02Prs()){
			formRow_R.setPrsHacSuu1(resultVO.getPrsHacSuu1());
			formRow_R.setPrsHacNyo1(resultVO.getPrsHacNyo1());
			if(!resultVO.getPrsHacNki1().equals(""))
				formRow_R.setPrsHacNki1(DataFormatUtils.setFormatYYMMDD(resultVO.getPrsHacNki1()));
			formRow_R.setPrsKbn1(DataFormatUtils.getKbnString(resultVO.getPrsKbn1()));
			formRow_R.setPrsHacSuu2(resultVO.getPrsHacSuu2());
			formRow_R.setPrsHacNyo2(resultVO.getPrsHacNyo2());
			if(!resultVO.getPrsHacNki2().equals(""))
				formRow_R.setPrsHacNki2(DataFormatUtils.setFormatYYMMDD(resultVO.getPrsHacNki2()));
			formRow_R.setPrsKbn2(DataFormatUtils.getKbnString(resultVO.getPrsKbn2()));
		}
		//発注履歴テーブル（副資材）
		if(resultVO.getExsitHac02Fuk()){
			formRow_R.setFukHacSuu1(resultVO.getFukHacSuu1());
			formRow_L.setFukHacSuu1(resultVO.getFukHacSuu1());
			if(!resultVO.getFukHacNki1().equals(""))
				formRow_R.setFukHacNki1(DataFormatUtils.setFormatYYMMDD(resultVO.getFukHacNki1()));
			formRow_R.setFukKbn1(DataFormatUtils.getKbnString(resultVO.getFukKbn1()));
			formRow_R.setFukHacSuu2(resultVO.getFukHacSuu2());
			if(!resultVO.getFukHacNki2().equals(""))
				formRow_R.setFukHacNki2(DataFormatUtils.setFormatYYMMDD(resultVO.getFukHacNki2()));
			formRow_R.setFukKbn2(DataFormatUtils.getKbnString(resultVO.getFukKbn2()));
		}
		//副資材コード
		formRow_R.setFukSziCod(resultVO.getFukSziCod());
		//基準行フラグ(行の色変更のために使用)
		formRow_L.setBasedRowFlg(resultVO.getBasedRowFlg());
		/* 品番マスター		: FTBHIN01 | 在庫テーブル			: FTBZAI01	| 発注履歴テーブル : FTBHAC02 
			在庫日数マスター	: FTBMST04 | 形態ランク別条件マスター	: FTBMST05	| 副資材マスター   : FTBFUK01  
			副資材パターンマスター  : FTBFUK02			*/    
				    
	}
	
//---------------------------------------------------------------------------------------------------	
	
	/** チェックボックスにチェックを入れなおすメソッド**/
	private void setCheckbox_checked(PrsOrderForm fmForm){
		
		ArrayList check_prs1_index = fmForm.getCheck_prs1_index();
	    ArrayList check_fuk1_index = fmForm.getCheck_fuk1_index();
	    int index_prs1 = 0;
	    int index_fuk1 = 0;

//System.out.println("check_prs1_index.size() : "+check_prs1_index.size());
//System.out.println("check_fuk1_index.size() : "+check_fuk1_index.size());
	    
		for(int i = 0; i < fmForm.getSize();i++){	
			PrsOrderFormRow formRow_R = fmForm.getFormRow_R(i);
			if(check_prs1_index.size() != index_prs1){
				if(i == Integer.parseInt(check_prs1_index.get(index_prs1)+"")){
//System.out.println("setCheck_prs1(true) : "+i);
					formRow_R.setCheck_prs1(true);
					index_prs1++;
				}
			}
			if(check_fuk1_index.size() != index_fuk1){
				if(i == Integer.parseInt(check_fuk1_index.get(index_fuk1)+"")){
//System.out.println("setCheck_fuk1(true) : "+i);
					formRow_R.setCheck_fuk1(true);
					index_fuk1++;
				}
			}
		
		}
		
	}

	
	/** プレス発注情報をセットしなおす **/
	public void setPrsHacJoho(PrsOrderFormRow formRow_R,PrsOrderVO fmVO){
		formRow_R.setPrsHacSuu1(fmVO.getPrsHacSuu1());
		formRow_R.setPrsHacNyo1(fmVO.getPrsHacNyo1());
		if(!fmVO.getPrsHacNki1().equals(""))
			formRow_R.setPrsHacNki1(DataFormatUtils.setFormatYYMMDD(fmVO.getPrsHacNki1()));
		else
			formRow_R.setPrsHacNki1("");
		formRow_R.setPrsKbn1(DataFormatUtils.getKbnString(fmVO.getPrsKbn1()));
		
		formRow_R.setPrsHacSuu2(fmVO.getPrsHacSuu2());
		formRow_R.setPrsHacNyo2(fmVO.getPrsHacNyo2());
		if(!fmVO.getPrsHacNki2().equals(""))
			formRow_R.setPrsHacNki2(DataFormatUtils.setFormatYYMMDD(fmVO.getPrsHacNki2()));
		else
			formRow_R.setPrsHacNki2("");
		formRow_R.setPrsKbn2(DataFormatUtils.getKbnString(fmVO.getPrsKbn2()));
	}
	/** 副資材発注情報をセットしなおす **/
	public void setFukHacJoho(PrsOrderFormRow formRow_R,PrsOrderVO fmVO){
		formRow_R.setFukHacSuu1(fmVO.getFukHacSuu1());
		if(!fmVO.getFukHacNki1().equals(""))
			formRow_R.setFukHacNki1(DataFormatUtils.setFormatYYMMDD(fmVO.getFukHacNki1()));
		else
			formRow_R.setFukHacNki1("");
		formRow_R.setFukKbn1(DataFormatUtils.getKbnString(fmVO.getFukKbn1()));
		
		formRow_R.setFukHacSuu2(fmVO.getFukHacSuu2());
		if(!fmVO.getFukHacNki2().equals(""))
			formRow_R.setFukHacNki2(DataFormatUtils.setFormatYYMMDD(fmVO.getFukHacNki2()));
		else
			formRow_R.setFukHacNki2("");
		formRow_R.setFukKbn2(DataFormatUtils.getKbnString(fmVO.getFukKbn2()));
		formRow_R.setFukSziCod(fmVO.getFukSziCod().trim()); //2005/09/07 add
	}
	
	private String getSubstringStr(String str, int length){
		if(str == null)
			return "";
		if(str.length() < length)
			return str;
		if(length < 0)
			return "";
		str = str.substring(0,length);
		return str;
	}
	
	/** エラーのあったページ・行番を計算して返す **/
	public String[] getErrorIndex(int index, int max){
		int page = (index+1) / max;
		int _index = (index+1) % max;
		String[] str = {page+1+"",_index+""};
		return str;
	}
	
	/** 発注済サインの表示文字列を返す **/
	private String getHacZmiSgnString(int sgn){
		String haczmisgn = "";
		switch(sgn){
			case 0:
				haczmisgn = PrsOrderLeftFrame.MIHACHU;
				break;
			case 1:
				haczmisgn = PrsOrderLeftFrame.HACZMI;
				break;
			case 2:
				haczmisgn = PrsOrderLeftFrame.SYRZMI;
				break;
		}
		return haczmisgn;
	}

}