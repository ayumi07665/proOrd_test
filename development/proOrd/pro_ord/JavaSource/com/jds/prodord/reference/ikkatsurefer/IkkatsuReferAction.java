/**
* IkkatsuReferAction  一括照会画面アクションクラス
*
*	作成日    2003/03/31
*	作成者    （ＮＩＩ）岡田 夏美
* 処理概要    処理呼び出しを行い、画面制御を行うクラス。
*
*	[変更履歴]
*       2003/05/06 （ＮＩＩ）蛭田 敬子
* 			・｢プレスコメント入力｣ 機能追加。
* 		2003/06/16 （ＮＩＩ）岡田 夏美
* 			・前100件次100件対応追加。
* 		2003/06/18 （ＮＩＩ）岡田 夏美
* 			・副資材発注機能追加。
* 		2003/07/01 （ＮＩＩ）蛭田 敬子
* 			・副資材発注のとき、納品先名の取得追加。
* 		2003/07/31（ＮＩＩ）岡田 夏美
* 			・プレスコメント入力・副資材発注実行時、チェックされた行だけでなく、
* 			  全ての行の入力情報を保持するように修正。
* 		2003/09/19（ＮＩＩ）岡田 夏美
* 			・表示項目にプレス・副資材 新譜旧譜区分１・２を追加
* 			・プレス・副資材 発注累計 → 入庫累計に変更
*		2004/03/29 (NII) 森
* 			・品番マスタのテーブル名変更
*		2004/04/02 (NII) 森
* 			・副資材発注の修正(分類コード１複数存在に対応)
*		2004/04/21 (NII)森
* 			・税込定価表示に伴う修正
*		2004/08/20 (ＮＩＩ) 落合
*			・プレスコメント入力実行時、全品番にチェックをつけるように修正
*		2005/02/03（ＮＩＩ）蛭田
*			・場所コードで納品先名を取得するように修正
*		2005/05/23（ＮＩＩ）蛭田
*			・発注書一括出力対応（ユーザーＩＤの追加）
*/

package com.jds.prodord.reference.ikkatsurefer;
import com.jds.prodord.common.*;
import com.jds.prodord.indicate.teiansuurefer.*;
import com.jds.prodord.order.prsorder.*;
import com.jds.prodord.print.printorders.*;

import javax.servlet.http.*;
import org.apache.struts.action.*;
import org.apache.struts.util.*;
import java.sql.*;
import java.util.*;

public class IkkatsuReferAction extends Action {
	
	/**
	 * ビジネスロジック
	 */
	private static  IkkatsuReferDelegate bzDelegate = new IkkatsuReferDelegate();
	
	
	public ActionForward perform(ActionMapping mapping,ActionForm form,HttpServletRequest req,HttpServletResponse res){
		ActionErrors errors = null;
		HttpSession session = req.getSession();
		//フォームのデータ
		IkkatsuReferForm fmForm = (IkkatsuReferForm)form;			
		MessageResources mr = super.getResources();//通常メッセージ表示用にリソースファイルからメッセージをとってくるのに使う
		
		IkkatsuReferLeftFrame leftFrame = new IkkatsuReferLeftFrame();
		IkkatsuReferVO fmVO = new IkkatsuReferVO();
		//発注書データ検索条件用のデータを格納するVO
		PrintOrdersQueryVO poQueryVO = new PrintOrdersQueryVO();
		
		//セッションから提案数照会指示画面でセットしたVOを取得
		TeiansuuReferVO queryVO = (TeiansuuReferVO)session.getAttribute(CommonConst.TEIANSUUREFER_VO);

		fmForm.setNewWindow("0");//印刷用画面を開くかどうかのフラグを初期化
		
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

		//最初に提案数照会指示画面からきたとき
		if(fmForm.getCommand().equals("INIT")){
			
			//2003/05/12 プレス発注画面からきたとき
			if(session.getAttribute(CommonConst.BEAN_NAME_IKKATSU_LEFT) != null){
				this.setCheckbox_checked(fmForm);//チェックボックスにチェックを入れなおす
				return mapping.findForward("success");
			}

	   		com.jds.prodord.register.LogonForm logonForm 
		  			= (com.jds.prodord.register.LogonForm)session.getAttribute(CommonConst.user);
			fmForm.setQueryDaiKaiSkbCod(logonForm.getDaikaiskbcod());
			fmForm.setQueryKaiSkbCod(logonForm.getKaiskbcod());
			queryVO.setBshCod(logonForm.getBshCod());//2005/02/03 add
			queryVO.setUsrId(logonForm.getUser());//2005/05/23 add
			fmForm.setHyoKbn(logonForm.getHyoKbn());//2011/05/26 add
			
			errors = this.find(fmForm,leftFrame,queryVO,req,res);
			
			if(errors == null){	//処理成功
				session.setAttribute(CommonConst.BEAN_NAME_IKKATSU_LEFT,leftFrame);
				session.setAttribute(CommonConst.INFOMSG_KEY,mr.getMessage("info.select.end"));	
			}
			//エラーあり
			if(errors != null){
				super.saveErrors(req,errors);
	    		return mapping.findForward("failure1");
			}
		}
		
		//ボタンが発注指示
		else if(fmForm.getCommand().equals(IkkatsuReferForm.COMMAND_HACHUSHIJI)){
			leftFrame = (IkkatsuReferLeftFrame)session.getAttribute(CommonConst.BEAN_NAME_IKKATSU_LEFT);
			
			errors = this.hacShiji(fmForm,leftFrame,req,res);
			
			if(errors == null){	//処理成功
				session.setAttribute(CommonConst.BEAN_NAME_IKKATSU_LEFT,leftFrame);//左のフレームもセットし直す
				fmForm.setCommand(IkkatsuReferForm.SUCCESS_HACHUSHIJI);
				return mapping.findForward("next");
			}
		}
		//発注指示完了
		else if(fmForm.getCommand().equals(IkkatsuReferForm.SUCCESS_HACHUSHIJI)){
			this.setCheckbox_checked(fmForm);//チェックボックスにチェックを入れなおす
			session.setAttribute(CommonConst.INFOMSG_KEY,mr.getMessage("info.indicate.end"));
		}
		
		//ボタンが伝票発行
		else if(fmForm.getCommand().equals(IkkatsuReferForm.COMMAND_DENPYOHAKKOU)){
			leftFrame = (IkkatsuReferLeftFrame)session.getAttribute(CommonConst.BEAN_NAME_IKKATSU_LEFT);
			
			errors = this.dnpHakkou(fmForm,leftFrame,poQueryVO,req,res);
			
			if(errors == null){	//処理成功
				session.setAttribute(CommonConst.BEAN_NAME_IKKATSU_LEFT,leftFrame);//左のフレームもセットし直す
				
				//poQueryVOに代表会社と会社識別コードもセット
				poQueryVO.setDaiKaiSkbCod(queryVO.getDaiKaiSkbCod());
				poQueryVO.setKaiSkbCod(queryVO.getKaiSkbCod());
				poQueryVO.setKaiSkbCod_arr(queryVO.getKaiSkbCod_arr());
				session.setAttribute(CommonConst.PO_QUERY_VO,poQueryVO);//発注書データ検索条件がセットされたpoQueryVOをセッションに格納
				
				fmForm.setCommand(IkkatsuReferForm.SUCCESS_DENPYOHAKKOU);
				return mapping.findForward("next");
			}
		}
		//伝票発行完了
		else if(fmForm.getCommand().equals(IkkatsuReferForm.SUCCESS_DENPYOHAKKOU)){
			fmForm.setNewWindow("1");
			this.setCheckbox_checked(fmForm);//チェックボックスにチェックを入れなおす
			session.setAttribute(CommonConst.INFOMSG_KEY,mr.getMessage("info.issue.end"));
		}
		//次100件
		else if(fmForm.getCommand().equals(IkkatsuReferForm.NEXT)){
			leftFrame = (IkkatsuReferLeftFrame)session.getAttribute(CommonConst.BEAN_NAME_IKKATSU_LEFT);
			
			errors = this.dispPre_NxtData(fmForm,leftFrame,req,res,1);
			
			if(errors == null){	//処理成功
				session.setAttribute(CommonConst.BEAN_NAME_IKKATSU_LEFT,leftFrame);//左のフレームもセットし直す
				fmForm.setCommand(IkkatsuReferForm.SUCCESS_PREVNEXT);
				return mapping.findForward("next");
			}
		}
		//前100件
		else if(fmForm.getCommand().equals(IkkatsuReferForm.PREV)){
			leftFrame = (IkkatsuReferLeftFrame)session.getAttribute(CommonConst.BEAN_NAME_IKKATSU_LEFT);
			
			errors = this.dispPre_NxtData(fmForm,leftFrame,req,res,-1);
			
			if(errors == null){	//処理成功
				session.setAttribute(CommonConst.BEAN_NAME_IKKATSU_LEFT,leftFrame);//左のフレームもセットし直す
				fmForm.setCommand(IkkatsuReferForm.SUCCESS_PREVNEXT);
				return mapping.findForward("next");
			}
		}
		//前100件次100件完了
		else if(fmForm.getCommand().equals(IkkatsuReferForm.SUCCESS_PREVNEXT)){
			this.setCheckbox_checked(fmForm);//チェックボックスにチェックを入れなおす
		}
		
		//ボタンがプレスコメント入力
		if(fmForm.getCommand().equals(IkkatsuReferForm.COMMAND_PRSCOMMENT)){
			leftFrame = (IkkatsuReferLeftFrame)session.getAttribute(CommonConst.BEAN_NAME_IKKATSU_LEFT);
			errors = this.prsComment(fmForm,leftFrame,fmVO,req,res);

			if(errors == null){	//処理成功
				session.setAttribute(CommonConst.PRSORDER_VOS,fmVO.getPrsVOs());
				return mapping.findForward("next_prs");	
			}
		}
		//ボタンが副資材発注
		if(fmForm.getCommand().equals(IkkatsuReferForm.COMMAND_FKUSHIZAIHACHU)){
			leftFrame = (IkkatsuReferLeftFrame)session.getAttribute(CommonConst.BEAN_NAME_IKKATSU_LEFT);
			errors = this.fukHachu(fmForm,leftFrame,fmVO,req,res);

			if(errors == null){	//処理成功
				session.setAttribute(CommonConst.PRSORDER_VOS,fmVO.getPrsVOs());
				return mapping.findForward("next_prs");	
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
	 * 検索
	 * 
	 */
	private ActionErrors find (IkkatsuReferForm fmForm, IkkatsuReferLeftFrame leftFrame,
									TeiansuuReferVO queryVO,HttpServletRequest req,HttpServletResponse res){

		ActionErrors errors = null;

		try{			
			//初期化する
			fmForm.removeVosList();
			fmForm.removeIkkatsuReferVO();
			fmForm.removeAllRow();
			leftFrame.removeAllRow();
			
			//検索実行
			IkkatsuReferVO[] resultFmVOs = bzDelegate.doFind(queryVO);

			ArrayList vosList = new ArrayList();//ページ単位にVOのリストを格納する
			
			int pageCount = 0;//ページ数
			int max = IkkatsuReferForm.MAX_ROW_COUNT;//最大行数
			
			//結果が1ページ内だった場合
			if(resultFmVOs.length <= max){
				fmForm.setSize(resultFmVOs.length);
				leftFrame.setSize(resultFmVOs.length);
				fmForm.setCount(resultFmVOs.length);
				pageCount++;
			}
			//結果が1ページを超えた場合
			else{
				fmForm.setSize(max);
				leftFrame.setSize(max);
				fmForm.setCount(max);
				pageCount = resultFmVOs.length / max;
				//半端な行が出たら1ページ足す
				if(resultFmVOs.length % max != 0)
					pageCount++;
			}

			fmForm.clearTableField();
			leftFrame.clearTableField();
			
			for(int i = 0; i<pageCount; i++){//ページ分 ArrayListをnewしておく
				vosList.add(new java.util.ArrayList());
			}

			int cnt = 0;

			for(int i = 0; i < pageCount; i++){
				ArrayList vos = (ArrayList)vosList.get(i);
				
				for(int j = cnt; j < max + cnt && j < resultFmVOs.length; j++){
					if(i == 0){
						IkkatsuReferFormRow formRow_R = fmForm.getFormRow_R(j);
				   	 	IkkatsuReferLeftRow formRow_L = leftFrame.getFormRow_L(j);
				    	//検索結果をフォームにセット
				   		this.setData(fmForm,formRow_R,formRow_L,resultFmVOs[j]);
						fmForm.addIkkatsuReferVO(resultFmVOs[j]);
					}
					resultFmVOs[j].setQueryKaiSkbCod(fmForm.getQueryKaiSkbCod());//VOにユーザーの会社識別コードをセットしておく
					vos.add(resultFmVOs[j]);
				}
				cnt += max;
			}
			fmForm.setVosList(vosList);//全ページ分のVOのリスト
			fmForm.setPageCount(pageCount);//ページ数
			fmForm.setCurrentPage(0);//現在のページ
			fmForm.setReferredMaxPage(0);//参照された最大ページ
			fmForm.setAllRowCount(resultFmVOs.length);//全ページの件数


			if(resultFmVOs.length == 0){
				errors = new ActionErrors();
				errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.select.notexist_prepage"));
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
	 * 発注指示
	 * 
	 */
	private ActionErrors hacShiji (IkkatsuReferForm fmForm, IkkatsuReferLeftFrame leftFrame,
									HttpServletRequest req,HttpServletResponse res){

		ActionErrors errors = null;
		List lisFmVO = new ArrayList();
		int page = fmForm.getCurrentPage();
		int max = IkkatsuReferForm.MAX_ROW_COUNT;
		
		IkkatsuReferVO[] currentVOs = fmForm.getVosList(fmForm.getCurrentPage());//現在のページのVOを取り出す
		
		ArrayList check_prs1_index = new ArrayList();
	    ArrayList check_prs2_index = new ArrayList();
	    ArrayList check_fuk1_index = new ArrayList();
	    ArrayList check_prsHacSuu_index = new ArrayList();
		
		for(int i = 0; i < fmForm.getSize();i++){	
			IkkatsuReferFormRow formRow_R = fmForm.getFormRow_R(i);
			
			//チェックされているチェックボックスのインデックスを格納する
			if(formRow_R.getCheck_fuk1())
				check_fuk1_index.add(i+"");
			
			if(!formRow_R.getCheck_prs1()){
				lisFmVO.add(null);
				if(formRow_R.getCheck_prs2())
					check_prs2_index.add(i+"");
				continue;
			}
				
			IkkatsuReferVO fmVO = currentVOs[i];

			fmVO.setPrsHacSuu(formRow_R.getPrsHacSuu().trim());			
			fmVO.setPrsNki(formRow_R.getPrsNkiYear().trim(),formRow_R.getPrsNkiMonth().trim(),formRow_R.getPrsNkiDay().trim());
			fmVO.setPrsMkrCod(formRow_R.getPrsMkrCod().trim());
			
			lisFmVO.add(fmVO);	
		}
		//Formにセット
		fmForm.setCheck_index(check_prs1_index,check_prs2_index,check_fuk1_index,check_prsHacSuu_index);

		ArrayList allVos = fmForm.getAllVos();
		
		boolean flg = false;
		//処理対象指定なし
		boolean executeChecked = false;
		for(int i = 0; i<allVos.size(); i++){
			if(i == max * page){
				for(int j = 0; j < lisFmVO.size(); j++, i++){
					allVos.set(i, lisFmVO.get(j));
					if(lisFmVO.get(j) != null)
						executeChecked = true;
				}
			}
			if(i == allVos.size())
				continue;


			IkkatsuReferVO vo = (IkkatsuReferVO)allVos.get(i);
			if(!vo.getCheck_prs1()){
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
			//発注指示実行
			IkkatsuReferResult[] results = bzDelegate.doHacShiji((IkkatsuReferVO[])allVos.toArray(new IkkatsuReferVO[allVos.size()]));	
			//エラーだったのはないか調べる
			for(int i = 0; i < results.length;i++){
				if(results[i] != null && !results[i].isSuccess()){
					if(errors == null)
						errors = new ActionErrors();
						
					//発注先コードが存在しない
                    if(results[i].getDescription().equals(IkkatsuReferDelegate.HACSAKI_NOT_EXIST)){
                    	String[] s = this.getErrorIndex(i,max);
						errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.insert.notfound_row",s[0]+"ページ目"+s[1],"発注先コード"));
                    }else{
						errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.internal"));
					}
				}
			}
			if(errors == null){
				//バリューオブジェクト自体も格納			
				for(int i = 0; i < results.length; i++){
					if(results[i] == null)
						continue;
						
					fmForm.setIkkatsuReferVO_all(i, results[i].getVO());
					
					if(page * max <= i && i < (page + 1) * max){
						//発注済サインをセットし直す
						leftFrame.getFormRow_L(i % max).setHacZmiSgn(this.getHacZmiSgnString(results[i].getVO().getHacZmiSgn()));
						//プレス発注情報を書き換える
						this.setPrsHacJoho(fmForm.getFormRow_R(i % max),results[i].getVO());
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
	 * 伝票発行
	 * 
	 */
	private ActionErrors dnpHakkou (IkkatsuReferForm fmForm, IkkatsuReferLeftFrame leftFrame,
									PrintOrdersQueryVO poQueryVO,HttpServletRequest req,HttpServletResponse res){

		ActionErrors errors = null;
		List lisFmVO = new ArrayList();
		int page = fmForm.getCurrentPage();
		int max = IkkatsuReferForm.MAX_ROW_COUNT;
		
		IkkatsuReferVO[] currentVOs = fmForm.getVosList(fmForm.getCurrentPage());//現在のページのVOを取り出す
		
		ArrayList check_prs1_index = new ArrayList();
	    ArrayList check_prs2_index = new ArrayList();
	    ArrayList check_fuk1_index = new ArrayList();
		ArrayList check_prsHacSuu_index = new ArrayList();
		
		for(int i = 0; i < fmForm.getSize();i++){	
			IkkatsuReferFormRow formRow_R = fmForm.getFormRow_R(i);
			
			//チェックされているチェックボックスのインデックスを格納する
			if(formRow_R.getCheck_fuk1())
				check_fuk1_index.add(i+"");

			
			if(!formRow_R.getCheck_prs2()){
				lisFmVO.add(null);	
				if(formRow_R.getCheck_prs1())
					check_prs1_index.add(i+"");
				continue;
			}
				
			IkkatsuReferVO fmVO = currentVOs[i];

			fmVO.setPrsHacSuu(formRow_R.getPrsHacSuu().trim());			
			fmVO.setPrsNki(formRow_R.getPrsNkiYear().trim(),formRow_R.getPrsNkiMonth().trim(),formRow_R.getPrsNkiDay().trim());
			fmVO.setPrsMkrCod(formRow_R.getPrsMkrCod().trim());

			lisFmVO.add(fmVO);	
		}
		//Formにセット
		fmForm.setCheck_index(check_prs1_index,check_prs2_index,check_fuk1_index,check_prsHacSuu_index);
		
		ArrayList allVos = fmForm.getAllVos();
		
		boolean flg = false;
		//処理対象指定なし
		boolean executeChecked = false;
		for(int i = 0; i<allVos.size(); i++){
			if(i == max * page){
				for(int j = 0; j < lisFmVO.size(); j++, i++){
					allVos.set(i, lisFmVO.get(j));
					if(lisFmVO.get(j) != null)
						executeChecked = true;
				}
			}
			if(i == allVos.size())
				continue;


			IkkatsuReferVO vo = (IkkatsuReferVO)allVos.get(i);
			if(!vo.getCheck_prs2()){
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
			//伝票発行実行
			IkkatsuReferResult[] results = bzDelegate.doDnpHakkou((IkkatsuReferVO[])allVos.toArray(new IkkatsuReferVO[allVos.size()]));	
			
			//エラーだったのはないか調べる
			for(int i = 0; i < results.length;i++){
				if(results[i] != null && !results[i].isSuccess()){
					if(errors == null)
						errors = new ActionErrors();
						
					//発注先コードが存在しない
                    if(results[i].getDescription().equals(IkkatsuReferDelegate.HACSAKI_NOT_EXIST)){
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
				for(int i = 0; i < results.length; i++){
					if(results[i] == null)
						continue;
						
					IkkatsuReferVO fmVO = results[i].getVO();
					fmForm.setIkkatsuReferVO_all(i, fmVO);
					
					if(page * max <= i && i < (page + 1) * max){
						//発注済サインをセットし直す
						leftFrame.getFormRow_L(i % max).setHacZmiSgn(this.getHacZmiSgnString(fmVO.getHacZmiSgn()));
						//プレス発注情報を書き換える
						this.setPrsHacJoho(fmForm.getFormRow_R(i % max),fmVO);
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
	private ActionErrors dispPre_NxtData (IkkatsuReferForm fmForm, IkkatsuReferLeftFrame leftFrame,
									HttpServletRequest req,HttpServletResponse res, int addIndex){

		ActionErrors errors = null;
		IkkatsuReferVO[] resultVOs = null;//前or次ページのVOを格納
		IkkatsuReferVO[] currentVOs = null;//現在のページのVOを格納
		
		currentVOs = fmForm.getVosList(fmForm.getCurrentPage());
		//変更されている可能性のある項目をセットしなおす
		this.setCurrentVOs(currentVOs, fmForm, leftFrame);//2003/07/31 add

		//対象ページのデータを取得
		try{
			ArrayList check_prs1_index = new ArrayList();
	   	 	ArrayList check_prs2_index = new ArrayList();
	    	ArrayList check_fuk1_index = new ArrayList();
			ArrayList check_prsHacSuu_index = new ArrayList();
			
			//前or次ページのVOを取得
			resultVOs = fmForm.getVosList(fmForm.getCurrentPage() + addIndex);
			
			//初期化する
			fmForm.removeIkkatsuReferVO();
			fmForm.removeAllRow();
			leftFrame.removeAllRow();
					
			//結果をフォームにセット
			fmForm.setSize(resultVOs.length);
			leftFrame.setSize(resultVOs.length);
			fmForm.setCount(resultVOs.length);
			fmForm.clearTableField();
			leftFrame.clearTableField();
			
			for(int i = 0; i < resultVOs.length; i++){
				IkkatsuReferFormRow formRow_R = fmForm.getFormRow_R(i);
				IkkatsuReferLeftRow formRow_L = leftFrame.getFormRow_L(i);
				
				this.setData(fmForm,formRow_R,formRow_L,resultVOs[i]);
				
				//チェックされているチェックボックスのインデックスを格納する
				if(formRow_R.getCheck_prs1())
					check_prs1_index.add(i+"");
				if(formRow_R.getCheck_prs2())
					check_prs2_index.add(i+"");
				if(formRow_R.getCheck_fuk1())
					check_fuk1_index.add(i+"");
				if(formRow_R.getCheck_prsHacSuu())
					check_prsHacSuu_index.add(i+"");
			}
			fmForm.setCurrentPage(fmForm.getCurrentPage() + addIndex);//現在のページ
			fmForm.setReferredMaxPage(fmForm.getCurrentPage());//参照された最大ページ
			fmForm.setIkkatsuReferVO(new ArrayList(Arrays.asList(resultVOs)));//現在のVO
			fmForm.setCheck_index(check_prs1_index,check_prs2_index,check_fuk1_index,check_prsHacSuu_index);//チェックボックスのインデックスをセット
			
		}catch(IndexOutOfBoundsException e){
			resultVOs = new IkkatsuReferVO[0];
		}		
		if(resultVOs.length == 0){
			errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.select.notexist"));
		}
			
		return errors;			
			
	}

	/*** 2003/05/08 *************************************************************************
	 * 		プレスコメント入力	                    							        	*
	 * 												             							*
	 ****************************************************************************************/
	private ActionErrors prsComment (IkkatsuReferForm fmForm,IkkatsuReferLeftFrame leftFrame,
									IkkatsuReferVO fmVO,HttpServletRequest req,HttpServletResponse res){

		ActionErrors errors = null;
		List lisFmVO = new ArrayList();
		int page = fmForm.getCurrentPage();
		int max = IkkatsuReferForm.MAX_ROW_COUNT;

		IkkatsuReferVO[] currentVOs = fmForm.getVosList(fmForm.getCurrentPage());//現在のページのVOを取り出す
		//変更されている可能性のある項目をセットしなおす
		this.setCurrentVOs(currentVOs, fmForm, leftFrame);//2003/07/31 add

		ArrayList check_prs1_index = new ArrayList();
	    ArrayList check_prs2_index = new ArrayList();
	    ArrayList check_fuk1_index = new ArrayList();
	    ArrayList check_prsHacSuu_index = new ArrayList();
		
		for(int i = 0; i < fmForm.getSize();i++){	
			IkkatsuReferFormRow formRow_R = fmForm.getFormRow_R(i);
			
			//チェックされているチェックボックスのインデックスを格納する
			if(formRow_R.getCheck_prs1())
				check_prs1_index.add(i+"");
			if(formRow_R.getCheck_prs2())
				check_prs2_index.add(i+"");
			if(formRow_R.getCheck_fuk1())
				check_fuk1_index.add(i+"");
			if(formRow_R.getCheck_prsHacSuu())
				check_prsHacSuu_index.add(i+"");
			
			if(!formRow_R.getCheck_prs1() && !formRow_R.getCheck_prs2()){
				lisFmVO.add(null);	
				continue;
			}
				
			IkkatsuReferVO vo = currentVOs[i];

			vo.setPrsHacSuu(formRow_R.getPrsHacSuu().trim());			
			vo.setPrsNki(formRow_R.getPrsNkiYear().trim(),formRow_R.getPrsNkiMonth().trim(),formRow_R.getPrsNkiDay().trim());
			vo.setPrsMkrCod(formRow_R.getPrsMkrCod().trim());

			vo.setCheck_prs1(formRow_R.getCheck_prs1());
			vo.setCheck_prs2(formRow_R.getCheck_prs2());
			vo.setCheck_fuk1(formRow_R.getCheck_fuk1());
			vo.setCheck_prsHacSuu(formRow_R.getCheck_prsHacSuu());
			
			lisFmVO.add(vo);	
		}
		//Formにセット
		fmForm.setCheck_index(check_prs1_index,check_prs2_index,check_fuk1_index,check_prsHacSuu_index);
		
		
		ArrayList allVos = fmForm.getAllVos();
		
		boolean flg = false;
		//処理対象指定なし
		boolean executeChecked = false;
		for(int i = 0; i<allVos.size(); i++){
			if(i == max * page){
				for(int j = 0; j < lisFmVO.size(); j++, i++){
					allVos.set(i, lisFmVO.get(j));
					if(lisFmVO.get(j) != null)
						executeChecked = true;
				}
			}
			if(i == allVos.size())
				continue;


			IkkatsuReferVO vo = (IkkatsuReferVO)allVos.get(i);
			if(!vo.getCheck_prs1() && !vo.getCheck_prs2()){
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
			//エラーチェック実行
			IkkatsuReferResult[] results = bzDelegate.doCheck((IkkatsuReferVO[])allVos.toArray(new IkkatsuReferVO[allVos.size()]));	
			
			//エラーだったのはないか調べる
			for(int i = 0; i < results.length;i++){
				if(results[i] != null && !results[i].isSuccess()){
					if(errors == null)
						errors = new ActionErrors();
						
					//発注先コードが存在しない
                    if(results[i].getDescription().equals(IkkatsuReferDelegate.HACSAKI_NOT_EXIST)){
						String[] s = this.getErrorIndex(i,max);
						errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.insert.notfound_row",s[0]+"ページ目"+s[1],"発注先コード"));
                    }
				}
			}
			if(errors == null){
				 //バリューオブジェクト自体も格納
				for(int i = 0; i < results.length;i++){
					if(results[i] == null)
						continue;
					fmForm.setIkkatsuReferVO_all(i, results[i].getVO());
				}
				//プレス発注画面に渡すデータセット				
				fmVO.setPrsVOs(this.setPrsOrderData(results));	
			}
			
			
			
   		}catch(SQLException sqle){
			SystemException se = new SystemException(sqle);
			se.printStackTrace();
			errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.exception",sqle.toString()));
			
		}
		return errors;			
			
	}
	
	/*** 2003/06/17 *************************************************************************
	 * 		副資材発注                    							       				 	*
	 * 												             							*
	 ****************************************************************************************/
	private ActionErrors fukHachu (IkkatsuReferForm fmForm,IkkatsuReferLeftFrame leftFrame,
									IkkatsuReferVO fmVO,HttpServletRequest req,HttpServletResponse res){

		ActionErrors errors = null;
		List lisFmVO = new ArrayList();
		int page = fmForm.getCurrentPage();
		int max = IkkatsuReferForm.MAX_ROW_COUNT;

		IkkatsuReferVO[] currentVOs = fmForm.getVosList(fmForm.getCurrentPage());//現在のページのVOを取り出す
		//変更されている可能性のある項目をセットしなおす
		this.setCurrentVOs(currentVOs, fmForm, leftFrame);//2003/07/31 add

		ArrayList check_prs1_index = new ArrayList();
	    ArrayList check_prs2_index = new ArrayList();
	    ArrayList check_fuk1_index = new ArrayList();
	    ArrayList check_prsHacSuu_index = new ArrayList();
		
		for(int i = 0; i < fmForm.getSize();i++){	
			IkkatsuReferFormRow formRow_R = fmForm.getFormRow_R(i);
			
			//チェックされているチェックボックスのインデックスを格納する
			if(formRow_R.getCheck_prs1())
				check_prs1_index.add(i+"");
			if(formRow_R.getCheck_prs2())
				check_prs2_index.add(i+"");
			if(formRow_R.getCheck_fuk1())
				check_fuk1_index.add(i+"");
			if(formRow_R.getCheck_prsHacSuu())
				check_prsHacSuu_index.add(i+"");
			
			if(!formRow_R.getCheck_fuk1()){
				lisFmVO.add(null);	
				continue;
			}
				
			IkkatsuReferVO vo = currentVOs[i];

			vo.setPrsHacSuu(formRow_R.getPrsHacSuu().trim());			
			vo.setPrsNki(formRow_R.getPrsNkiYear().trim(),formRow_R.getPrsNkiMonth().trim(),formRow_R.getPrsNkiDay().trim());
			vo.setPrsMkrCod(formRow_R.getPrsMkrCod().trim());

			vo.setCheck_prs1(formRow_R.getCheck_prs1());
			vo.setCheck_prs2(formRow_R.getCheck_prs2());
			vo.setCheck_fuk1(formRow_R.getCheck_fuk1());
			vo.setCheck_prsHacSuu(formRow_R.getCheck_prsHacSuu());
			
			lisFmVO.add(vo);	
		}
		//Formにセット
		fmForm.setCheck_index(check_prs1_index,check_prs2_index,check_fuk1_index,check_prsHacSuu_index);
		
		
		ArrayList allVos = fmForm.getAllVos();

		boolean flg = false;
		//処理対象指定なし
		boolean executeChecked = false;
		for(int i = 0; i<allVos.size(); i++){
			if(i == max * page){
				for(int j = 0; j < lisFmVO.size(); j++, i++){
					allVos.set(i, lisFmVO.get(j));
					if(lisFmVO.get(j) != null)
						executeChecked = true;
				}
			}
			if(i == allVos.size())
				continue;


			IkkatsuReferVO vo = (IkkatsuReferVO)allVos.get(i);
			if(!vo.getCheck_fuk1()){
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
			if(errors == null){
				for(int i = 0; i < allVos.size(); i++){
					if(allVos.get(i) == null)
						continue;
					fmForm.setIkkatsuReferVO_all(i, (IkkatsuReferVO)allVos.get(i));
				}
				//プレス副資材発注画面に渡すデータを検索・セット				
				fmVO.setPrsVOs(this.setFukOrderData(bzDelegate.doFukHachu((IkkatsuReferVO[])allVos.toArray(new IkkatsuReferVO[allVos.size()]))));
			}
			
   		}catch(SQLException sqle){
			SystemException se = new SystemException(sqle);
			se.printStackTrace();
			errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.exception",sqle.toString()));
			
		}
		return errors;			
			
	}

	
//---------------------------------------------------------------------------------------------------	
	
	/**
	 *  VOのデータをFormにセットするメソッド
	 * 
	 */
	private void setData(IkkatsuReferForm fmForm, IkkatsuReferFormRow formRow_R, IkkatsuReferLeftRow formRow_L, 
				IkkatsuReferVO resultVO){
		
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
	    formRow_L.setHbiDte(DataFormatUtils.setFormatYYMMDD(resultVO.getHbiDte()));
	    formRow_L.setTitKj(resultVO.getTitKj());
	    formRow_L.setTomRak(resultVO.getTomRak());
		formRow_L.setZikTik(resultVO.getZikTik()); //2004.04.21 add
	    formRow_R.setPrsMkrCod(resultVO.getPrsMkrCod());
	    
	    //発注履歴テーブル
	    formRow_L.setHacZmiSgn(this.getHacZmiSgnString(resultVO.getHacZmiSgn()));
	    			    
	    //在庫テーブル
	    if(resultVO.getExsitZai01()){
		    formRow_L.setAziSuu(resultVO.getAziSuu());
			formRow_L.setTnaSekZan(resultVO.getTnaSekZan());
			formRow_L.setJucZan(resultVO.getJucZan());
			formRow_L.setMhikSuu(resultVO.getMhikSuu());
			formRow_L.setRsvSum(resultVO.getRsvSum());
			
			formRow_R.setTodUriSuu(resultVO.getTodUriSuu());
			formRow_R.setB1dUri(resultVO.getB1dUri());
			formRow_R.setB2dUri(resultVO.getB2dUri());
			formRow_R.setB3dUri(resultVO.getB3dUri());
			formRow_R.setB4dUri(resultVO.getB4dUri());
			formRow_R.setB5dUri(resultVO.getB5dUri());
			formRow_R.setTowUri(resultVO.getTowUri());
			formRow_R.setB1wUri(resultVO.getB1wUri());
			formRow_R.setB2wUri(resultVO.getB2wUri());
			formRow_R.setTomUri(resultVO.getTomUri());
			formRow_R.setB1mUri(resultVO.getB1mUri());
			formRow_R.setB2mUri(resultVO.getB2mUri());
			formRow_R.setTomHpn(resultVO.getTomHpn());
			formRow_R.setB1mHpn(resultVO.getB1mHpn());
			formRow_R.setB2mHpn(resultVO.getB2mHpn());
			formRow_R.setTomTna(resultVO.getTomTna());
			formRow_R.setB1mTna(resultVO.getB1mTna());
			formRow_R.setB2mTna(resultVO.getB2mTna());
			
			formRow_L.setAvgUri(resultVO.getAvgUri());
			
			formRow_R.setPrsMnyKei(resultVO.getPrsMnyKei());
			formRow_R.setPrsHacRui(resultVO.getPrsHacRui());
			formRow_R.setFukMnyKei(resultVO.getFukMnyKei());
			formRow_R.setFukHacRui(resultVO.getFukHacRui());
			formRow_R.setPrsNyoKei(resultVO.getPrsNyoKei());
			formRow_R.setFukNyoKei(resultVO.getFukNyoKei());
			formRow_R.setFukZaiSuu(resultVO.getFukZaiSuu());//入力チェックで使うためformRow_Rにもセット
			
			formRow_L.setFukHacRui(resultVO.getFukHacRui());
			formRow_L.setFukNyoKei(resultVO.getFukNyoKei());
			formRow_L.setFukZaiSuu(resultVO.getFukZaiSuu());
			
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
			formRow_R.setFukHacNyo1(resultVO.getFukHacNyo1());
			if(!resultVO.getFukHacNki1().equals(""))
				formRow_R.setFukHacNki1(DataFormatUtils.setFormatYYMMDD(resultVO.getFukHacNki1()));
			formRow_R.setFukKbn1(DataFormatUtils.getKbnString(resultVO.getFukKbn1()));
			formRow_R.setFukHacSuu2(resultVO.getFukHacSuu2());
			formRow_R.setFukHacNyo2(resultVO.getFukHacNyo2());
			if(!resultVO.getFukHacNki2().equals(""))
				formRow_R.setFukHacNki2(DataFormatUtils.setFormatYYMMDD(resultVO.getFukHacNki2()));
			formRow_R.setFukKbn2(DataFormatUtils.getKbnString(resultVO.getFukKbn2()));
		}
		//副資材マスター
		if(resultVO.getExsitFuk01()){
			formRow_R.setFukMkrCod(resultVO.getFukSziHacSaki());
		}
		//導出項目-------------------------------------------
		if(resultVO.getExsitZai01() && resultVO.getExsitMst05() && resultVO.getExsitMst04()){
			formRow_L.setTeianSuu(resultVO.getTeianSuu());
			formRow_L.setFskZaiSuu(resultVO.getFskZaiSuu());
			formRow_R.setPrsHacSuu(resultVO.getPrsHacSuu());
			formRow_R.setFukHacSuu(resultVO.getFukHacSuu());

			formRow_R.setCheck_prs1(resultVO.getCheck_prs1());
			formRow_R.setCheck_prs2(resultVO.getCheck_prs2());
			formRow_R.setCheck_fuk1(resultVO.getCheck_fuk1());
			formRow_R.setCheck_flg(resultVO.getCheck_flg());
			formRow_R.setCheck_prsHacSuu(resultVO.getCheck_prsHacSuu());
			formRow_R.setCheck_sgn(resultVO.getCheck_sgn());

		}
		if(resultVO.getPrsNki() != 0){
			formRow_R.setPrsNkiYear(resultVO.getPrsNkiYear());
			formRow_R.setPrsNkiMonth(resultVO.getPrsNkiMonth());
			formRow_R.setPrsNkiDay(resultVO.getPrsNkiDay());
		}
		if(resultVO.getFukNki() != 0){
			formRow_R.setFukNkiYear(resultVO.getFukNkiYear());
			formRow_R.setFukNkiMonth(resultVO.getFukNkiMonth());
			formRow_R.setFukNkiDay(resultVO.getFukNkiDay());
		}
		if(resultVO.getExsitZai01())
			formRow_L.setZaiNisuu(resultVO.getZaiNisuu());
		formRow_R.setChoksoKbn(resultVO.getChoksoKbn());
		
		resultVO.setExisted();
		
		/* 品番マスター		: FTBHIN01 | 在庫テーブル			: FTBZAI01	| 発注履歴テーブル : FTBHAC02 
			在庫日数マスター	: FTBMST04 | 形態ランク別条件マスター	: FTBMST05	| 副資材マスター   : FTBFUK01  
			副資材パターンマスター  : FTBFUK02			*/    
				    
	}
	
	/**
	 *  チェックボックスにチェックを入れなおすメソッド
	 * 
	 */
	private void setCheckbox_checked(IkkatsuReferForm fmForm){
		
		ArrayList check_prs1_index = fmForm.getCheck_prs1_index();
	    ArrayList check_prs2_index = fmForm.getCheck_prs2_index();
	    ArrayList check_fuk1_index = fmForm.getCheck_fuk1_index();
	    ArrayList check_prsHacSuu_index = fmForm.getCheck_prsHacSuu_index();
	    int index_prs1 = 0;
	    int index_prs2 = 0;
	    int index_fuk1 = 0;
	    int index_prsHacSuu = 0;
	    
		for(int i = 0; i < fmForm.getSize();i++){	
			IkkatsuReferFormRow formRow_R = fmForm.getFormRow_R(i);
			if(check_prs1_index.size() != index_prs1){
				if(i == Integer.parseInt(check_prs1_index.get(index_prs1)+"")){
					formRow_R.setCheck_prs1(true);
					index_prs1++;
				}
			}
			if(check_prs2_index.size() != index_prs2){
				if(i == Integer.parseInt(check_prs2_index.get(index_prs2)+"")){
					formRow_R.setCheck_prs2(true);
					index_prs2++;
				}
			}
			if(check_fuk1_index.size() != index_fuk1){
				if(i == Integer.parseInt(check_fuk1_index.get(index_fuk1)+"")){
					formRow_R.setCheck_fuk1(true);
					index_fuk1++;
				}
			}
			if(check_prsHacSuu_index.size() != index_prsHacSuu){
				if(i == Integer.parseInt(check_prsHacSuu_index.get(index_prsHacSuu)+"")){
					formRow_R.setCheck_prsHacSuu(true);
					index_prsHacSuu++;
				}
			}
		
		}
		
	}
	
	
	/** 発注済サインの表示文字列を返す **/
	private String getHacZmiSgnString(int sgn){
		String haczmisgn = "";
		switch(sgn){
			case 0:
				haczmisgn = IkkatsuReferLeftFrame.MIHACHU;
				break;
			case 1:
				haczmisgn = IkkatsuReferLeftFrame.HACZMI;
				break;
			case 2:
				haczmisgn = IkkatsuReferLeftFrame.SYRZMI;
				break;
		}
		return haczmisgn;
	}
	
	/** プレス発注情報をセットしなおす **/
	public void setPrsHacJoho(IkkatsuReferFormRow formRow_R,IkkatsuReferVO fmVO){
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
	
	/** エラーのあったページ・行番を計算して返す **/
	public String[] getErrorIndex(int index, int max){
		int page = (index+1) / max;
		int _index = (index+1) % max;
		String[] str = {page+1+"",_index+""};
		return str;
	}
//--------------------------------------------------------------------------------------------------


	/** プレス発注画面に渡すデータをセット。 2003/05/08  2003/06/16 修正（前100件次100件対応）**/
	public PrsOrderVO[] setPrsOrderData(IkkatsuReferResult[] results){

		ArrayList prsVO_arr = new ArrayList();
		boolean flg = false;
		for(int i = 0; i < results.length;i++){
			if(results[i] == null)
				continue;
			IkkatsuReferVO fmVO = results[i].getVO();
			PrsOrderVO prsVO = new PrsOrderVO();
			
			//品番情報
			prsVO.setDaiKaiSkbCod(fmVO.getDaiKaiSkbCod());
			prsVO.setKaiSkbCod(fmVO.getKaiSkbCod());
			prsVO.setQueryKaiSkbCod(fmVO.getQueryKaiSkbCod());
			prsVO.setUsrId(fmVO.getUsrId());//2005.05.23 add
			prsVO.setKigBng(fmVO.getKigBng());
			prsVO.setHjiHnb(fmVO.getHjiHnb());
			prsVO.setArtKj(fmVO.getArtKj());
			prsVO.setHbiDte(fmVO.getHbiDte());
			prsVO.setKetCod(fmVO.getKetCod());
			prsVO.setKetNm(fmVO.getKetNm());
			prsVO.setTitKj(fmVO.getTitKj());
			prsVO.setTomRak(fmVO.getTomRak());
			prsVO.setZikTik(fmVO.getZikTik()); //2004.04.21 add
			prsVO.setKbn("0");
			prsVO.setSetSuu(fmVO.getSetSuu());
			prsVO.setSyrZmiSgn_prs(fmVO.getHacZmiSgn());
			prsVO.setPrsHacSuu(fmVO.getPrsHacSuu());
			prsVO.setPrsNki(fmVO.getPrsNki());
			prsVO.setPrsMkrCod(fmVO.getPrsMkrCod());
			prsVO.setChoksoKbn("0");
			prsVO.setComment("");
			prsVO.setHacNm(fmVO.getHacNm());
			prsVO.setNhnMei(DataFormatUtils.getSkoNm(fmVO.getBshCod()));//2005/02/03 add
			// 2004/08/20 upd 全品番にチェックをつける
			prsVO.setCheck_prs1(true);

			
			//プレス発注情報
			prsVO.setPrsMnyKei(fmVO.getPrsMnyKei());
			prsVO.setPrsHacRui(fmVO.getPrsHacRui());
			prsVO.setPrsNyoKei(fmVO.getPrsNyoKei());
			prsVO.setPrsHacSuu1(fmVO.getPrsHacSuu1());
			prsVO.setPrsHacSuu2(fmVO.getPrsHacSuu2());
			prsVO.setPrsHacNyo1(fmVO.getPrsHacNyo1());
			prsVO.setPrsHacNyo2(fmVO.getPrsHacNyo2());
			prsVO.setPrsHacNki1(fmVO.getPrsHacNki1());
			prsVO.setPrsHacNki2(fmVO.getPrsHacNki2());
			prsVO.setPrsKbn1(fmVO.getPrsKbn1());
			prsVO.setPrsKbn2(fmVO.getPrsKbn2());
			
			//副資材	発注情報
			prsVO.setFukMnyKei(fmVO.getFukMnyKei());
			prsVO.setFukHacRui(fmVO.getFukHacRui());
			prsVO.setFukNyoKei(fmVO.getFukNyoKei());
			prsVO.setFukHacSuu1(fmVO.getFukHacSuu1());
			prsVO.setFukHacSuu2(fmVO.getFukHacSuu2());
			prsVO.setFukHacNki1(fmVO.getFukHacNki1());
			prsVO.setFukHacNki2(fmVO.getFukHacNki2());
			prsVO.setFukZaiSuu(fmVO.getFukZaiSuu());
			prsVO.setFukKbn1(fmVO.getFukKbn1());
			prsVO.setFukKbn2(fmVO.getFukKbn2());
			
			//副資材名称
			prsVO.setFukSziNm(fmVO.getFukSziNm());
			//副資材パターンコード
			prsVO.setFukSziPtn(fmVO.getFukSziPtn());
			//副資材コード
			prsVO.setFukSziCod(fmVO.getFukSziCod());
			//基準行フラグ
			prsVO.setBasedRowFlg(fmVO.getBasedRowFlg());
			//分類コード
			prsVO.setBunCod(fmVO.getBunCod());
			
			//副資材コード
			prsVO.setFukSziCod_arr(fmVO.getFukSziCod_arr());
			//分類コード
			prsVO.setBunCod_arr(fmVO.getBunCod_arr());
			
			//フラグ
			prsVO.setExsitHin01(true);
			prsVO.setExsitHac02Prs(true);
			prsVO.setExsitHac02Fuk(true);
			prsVO.setExsitZai01(true);
			
			//Index
			prsVO.setIndex(i);

			if(!flg){
				//遷移元ページ
				prsVO.setPre_page(PrsOrderForm.IkkatsuRefer);
				prsVO.setPrs_FukSgn(PrsOrderForm.PRSHACHU);
				flg = true;
			}
						
			prsVO_arr.add(prsVO);
		}
		return (PrsOrderVO[])prsVO_arr.toArray(new PrsOrderVO[prsVO_arr.size()]);
	}
	
	/** 副資材発注画面に渡すデータをセット 2003/06/16  **/
	public PrsOrderVO[] setFukOrderData(IkkatsuReferVO[] fmVOs){
		ArrayList prsVO_arr = new ArrayList();
		IkkatsuReferVO fmVO_base = new IkkatsuReferVO();
		boolean flg = false;
		int index = 0;
		for(int i = 0; i < fmVOs.length;i++){
			if(fmVOs[i] == null)
				continue;
			IkkatsuReferVO fmVO = fmVOs[i];
			
			PrsOrderVO prsVO = new PrsOrderVO();
			
			if(fmVO.getBasedRowFlg().equals("1")){//基準行フラグ="1"のものを格納
				fmVO_base = fmVOs[i];
				prsVO.setIndex(index);//提案数照会画面でのIndexをセット
				index++;
			}
			
			//品番情報
			prsVO.setDaiKaiSkbCod(fmVO.getDaiKaiSkbCod());
			prsVO.setKaiSkbCod(fmVO.getKaiSkbCod());
			prsVO.setQueryKaiSkbCod(fmVO_base.getQueryKaiSkbCod());
			prsVO.setUsrId(fmVO.getUsrId());//2005/05/25 add
			prsVO.setKigBng(fmVO_base.getKigBng());
			prsVO.setHjiHnb(fmVO_base.getHjiHnb());
			prsVO.setArtKj(fmVO_base.getArtKj());
			prsVO.setHbiDte(fmVO_base.getHbiDte());
			prsVO.setKetCod(fmVO_base.getKetCod());
			prsVO.setKetNm(fmVO_base.getKetNm());
			prsVO.setTitKj(fmVO_base.getTitKj());
			prsVO.setTomRak(fmVO_base.getTomRak());
			prsVO.setZikTik(fmVO_base.getZikTik()); //2004.04.21 add
			prsVO.setKbn("0");
			prsVO.setSetSuu(fmVO_base.getSetSuu());
			prsVO.setSyrZmiSgn_prs(fmVO_base.getHacZmiSgn());
			prsVO.setPrsHacSuu(fmVO.getFukHacSuu());
			prsVO.setPrsNki(fmVO_base.getFukNki());
			prsVO.setPrsMkrCod(fmVO.getFukSziHacSaki());
			prsVO.setChoksoKbn("0");
			prsVO.setComment("");
			prsVO.setHacNm(fmVO.getHacNm());
			prsVO.setNhnMei(fmVO_base.getNhnMei());//2003/07/01 納品先名取得
			
			//プレス発注情報
			prsVO.setPrsMnyKei(fmVO_base.getPrsMnyKei());
			prsVO.setPrsHacRui(fmVO_base.getPrsHacRui());
			prsVO.setPrsNyoKei(fmVO_base.getPrsNyoKei());
			prsVO.setPrsHacSuu1(fmVO_base.getPrsHacSuu1());
			prsVO.setPrsHacSuu2(fmVO_base.getPrsHacSuu2());
			prsVO.setPrsHacNyo1(fmVO_base.getPrsHacNyo1());
			prsVO.setPrsHacNyo2(fmVO_base.getPrsHacNyo2());
			prsVO.setPrsHacNki1(fmVO_base.getPrsHacNki1());
			prsVO.setPrsHacNki2(fmVO_base.getPrsHacNki2());
			prsVO.setPrsKbn1(fmVO_base.getPrsKbn1());
			prsVO.setPrsKbn2(fmVO_base.getPrsKbn2());
			
			//副資材	発注情報
			prsVO.setFukMnyKei(fmVO_base.getFukMnyKei());
			prsVO.setFukHacRui(fmVO_base.getFukHacRui());
			prsVO.setFukNyoKei(fmVO_base.getFukNyoKei());
			prsVO.setFukHacSuu1(fmVO.getFukHacSuu1());
			prsVO.setFukHacSuu2(fmVO.getFukHacSuu2());
			prsVO.setFukHacNki1(fmVO.getFukHacNki1());
			prsVO.setFukHacNki2(fmVO.getFukHacNki2());
			prsVO.setFukKbn1(fmVO.getFukKbn1());
			prsVO.setFukKbn2(fmVO.getFukKbn2());
			prsVO.setFukZaiSuu(fmVO_base.getFukZaiSuu());
			
			//副資材発注先＆副資材名称
			prsVO.setFukSziNm(fmVO.getFukSziNm());
			//副資材パターンコード
			prsVO.setFukSziPtn(fmVO_base.getFukSziPtn());
			//副資材コード
			prsVO.setFukSziCod(fmVO.getFukSziCod());
			//基準行フラグ
			prsVO.setBasedRowFlg(fmVO.getBasedRowFlg());
			//分類コード
			prsVO.setBunCod(fmVO.getBunCod());
			
			//フラグ
			prsVO.setExsitHin01(true);
			prsVO.setExsitHac02Prs(true);
			prsVO.setExsitHac02Fuk(true);
			prsVO.setExsitZai01(true);
			
			if(!flg){
				//遷移元ページ
				prsVO.setPre_page(PrsOrderForm.IkkatsuRefer);
				prsVO.setPrs_FukSgn(PrsOrderForm.FUKHACHU);
				flg = true;
			}
						
			prsVO_arr.add(prsVO);
		}
		return (PrsOrderVO[])prsVO_arr.toArray(new PrsOrderVO[prsVO_arr.size()]);
	}
	
	/** 変更されている可能性のある項目をVOにセットしなおす *///2003/07/31 add
	private void setCurrentVOs(IkkatsuReferVO[] currentVOs, IkkatsuReferForm fmForm, IkkatsuReferLeftFrame leftFrame){
		for(int i = 0; i < currentVOs.length; i++){
			
			IkkatsuReferFormRow formRow_R = fmForm.getFormRow_R(i);
			IkkatsuReferLeftRow formRow_L = leftFrame.getFormRow_L(i);
					
			currentVOs[i].setPrsHacSuu(formRow_R.getPrsHacSuu());
			currentVOs[i].setPrsNki(formRow_R.getPrsNkiYear(),formRow_R.getPrsNkiMonth(),formRow_R.getPrsNkiDay());
			currentVOs[i].setPrsMkrCod(formRow_R.getPrsMkrCod());
			
			currentVOs[i].setCheck_prs1(formRow_R.getCheck_prs1());
			currentVOs[i].setCheck_prs2(formRow_R.getCheck_prs2());
			currentVOs[i].setCheck_fuk1(formRow_R.getCheck_fuk1());
			currentVOs[i].setCheck_prsHacSuu(formRow_R.getCheck_prsHacSuu());
		}
	}
	
}

