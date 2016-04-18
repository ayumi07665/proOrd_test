/**
* TeiansuuReferAction  提案数照会指示アクションクラス
*
*	作成日    2003/02/20
*	作成者    （ＮＩＩ）岡田 夏美
* 処理概要    処理呼び出しを行い、画面制御を行うクラス。
*
*	[変更履歴]
*        2003/07/16（ＮＩＩ）岡田 夏美
* 			・ソート条件にソートキー追加。
* 			・品番指定の場合、その他の条件は加えない。記号部省略入力対応 
* 		 2003/09/10（ＮＩＩ）岡田 夏美
* 			・記号番号の記号部省略入力対応
* 		 2003/09/19（ＮＩＩ）岡田 夏美
* 			・対象データのみ表示・非対象データ表示 選択機能追加
* 		 2004/07/02（ＮＩＩ）蛭田
* 			・コピー＆ペースト機能追加
*		  2006/05/10（ＮＩＩ）田端 康教
*			・Ｋ社のランク条件変更対応
* 
*/

package com.jds.prodord.indicate.teiansuurefer;
import com.jds.prodord.common.*;
import javax.servlet.http.*;
import org.apache.struts.action.*;
import java.sql.*;
import java.util.*;
import java.io.*;

public class TeiansuuReferAction extends Action {
		
	/**
	 * ビジネスロジック
	 */
	private static  TeiansuuReferDelegate bzDelegate = new TeiansuuReferDelegate();
	
	
	public ActionForward perform(ActionMapping mapping,ActionForm form,HttpServletRequest req,HttpServletResponse res){
		ActionErrors errors = null;
		HttpSession session = req.getSession();
		//フォームのデータ
		TeiansuuReferForm fmForm = (TeiansuuReferForm)form;
		TeiansuuReferVO fmVO = new TeiansuuReferVO();


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
			//メニューからきたとき
			if(fmForm.getFromMenuSgn()){
				fmForm.clearAll();
		   		com.jds.prodord.register.LogonForm logonForm 
			  			= (com.jds.prodord.register.LogonForm)session.getAttribute(CommonConst.user);
				String daiKaiSkbCod = 	logonForm.getDaikaiskbcod();
				String KaiSkbCod = 	logonForm.getKaiskbcod();
				ArrayList kaiList = logonForm.getKaiSkbCodList();
				fmForm.setQueryDaiKaiSkbCod(daiKaiSkbCod);
				fmForm.setQueryKaiSkbCod(KaiSkbCod);
				fmForm.setQueryKaiSkbCodList(kaiList);
				//選択項目のオプションをセット
				fmForm.setKaiSkbCodOptions(kaiList);
				fmForm.setKetCodOptions(logonForm.getKetNmList());
				fmForm.setMkrBunOptions(logonForm.getMkrBunList());
				fmForm.setRankOptions(daiKaiSkbCod);
			}
			fmForm.setFromMenuSgn(false);
			fmForm.setCheckBoxValue();
			//照会結果画面のBEANをremove
			session.removeAttribute(CommonConst.FORMBEAN_NAME_IKKATSU);
			session.removeAttribute(CommonConst.BEAN_NAME_IKKATSU_LEFT);
			session.removeAttribute(CommonConst.TEIANSUUREFER_VO);
		}
		//ボタンが発注提案照会
		if(fmForm.getCommand().equals(TeiansuuReferForm.COMMAND_TEIANSYOKAI)){
			errors = this.teianSyokaiShiji(fmForm,req,res,fmVO);
			if(errors == null){	//処理成功
				session.setAttribute(CommonConst.TEIANSUUREFER_VO,fmVO);	
				return mapping.findForward("next");	
			}
		//ボタンが提案数自動発注
		}else if(fmForm.getCommand().equals(TeiansuuReferForm.COMMAND_JIDOUHACHU)){
			errors = this.teianSyokaiShiji(fmForm,req,res,fmVO);
			if(errors == null){	//処理成功
				session.setAttribute(CommonConst.TEIANSUUREFER_VO,fmVO);
				return mapping.findForward("next");		
			}		
		//ボタンがクリア
		}else if(fmForm.getCommand().equals(TeiansuuReferForm.COMMAND_CLEAR)){
			fmForm.clearAll();
			fmForm.setRb_default();
			fmForm.setCommand("INIT");
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
	 * 提案数照会指示・提案数自動発注
	 * 
	 */
	private ActionErrors teianSyokaiShiji (TeiansuuReferForm fmForm,HttpServletRequest req,HttpServletResponse res,TeiansuuReferVO fmVO){

		HttpSession session = req.getSession();
		
		ActionErrors errors = null;
		
		ArrayList arr_kai = new ArrayList();
		ArrayList arr_rnk = new ArrayList();
		ArrayList arr_ket = new ArrayList();
		ArrayList arr_mkr = new ArrayList();
		ArrayList arr_hyo = new ArrayList();
		ArrayList arr_kig = new ArrayList();
		ArrayList arr_srt = new ArrayList();
		
		String queryKaiSkbCod = fmForm.getQueryKaiSkbCod();
		String queryDaiKaiSkbCod = fmForm.getQueryDaiKaiSkbCod();
		
		//飛んできたフォームの値をバリューオブジェクトにセット
		fmVO.setDaiKaiSkbCod(queryDaiKaiSkbCod);
		fmVO.setKaiSkbCod(queryKaiSkbCod);
		fmVO.setQueryKaiSkbCodList(fmForm.getQueryKaiSkbCodList());
	
		//コマンド
		fmVO.setCommand(fmForm.getCommand());
		
		//品番がＡＬＬ指定のとき
		if(fmForm.getRb_kigBng() == TeiansuuReferForm.RB_ALL){
			
			//会社識別コード	
			if(fmForm.getRb_kaiSkbCod() == TeiansuuReferForm.RB_ALL){
				fmVO.setKaiSkbCod_arr(fmVO.getQueryKaiSkbCodList());
			}else if(fmForm.getRb_kaiSkbCod() == TeiansuuReferForm.RB_SELECT){
				String[] kai = fmForm.getKaiSkbCod();
				for(int i = 0; i<kai.length; i++){
					arr_kai.add(kai[i]);
				}
				fmVO.setKaiSkbCod_arr(arr_kai);			
			}
			//ランク
			if(fmForm.getRb_rank() == TeiansuuReferForm.RB_ALL){
				String[] RANK = TeiansuuReferForm.makeRank(queryDaiKaiSkbCod);
				for(int i = 0; i<RANK.length; i++){
					arr_rnk.add(RANK[i]);
				}
				fmVO.setRank_arr(arr_rnk);
			}else if(fmForm.getRb_rank() == TeiansuuReferForm.RB_SELECT){
				String[] rank = fmForm.getRank();
				for(int i = 0; i<rank.length; i++){
					arr_rnk.add(rank[i]);
				}
				fmVO.setRank_arr(arr_rnk);			
			}
			//形態コード
			if(fmForm.getRb_ketCod() == TeiansuuReferForm.RB_SELECT){
				String[] ket = fmForm.getKetCod();
				for(int i = 0; i<ket.length; i++){
					arr_ket.add(ket[i]);
				}
				fmVO.setKetCod_arr(arr_ket);			
			}
			//メーカー分類
			if(fmForm.getRb_mkrBun() == TeiansuuReferForm.RB_SELECT){
				String[] mkr = fmForm.getMkrBun();
				for(int i = 0; i<mkr.length; i++){
					arr_mkr.add(mkr[i]);
				}
				fmVO.setMkrBun_arr(arr_mkr);			
			}
			//邦洋区分
			if(fmForm.getRb_hyoKbn() == TeiansuuReferForm.RB_SELECT){
				String[] hyoKbn = fmForm.getHyoKbn();
				for(int i = 0; i<hyoKbn.length; i++){
					arr_hyo.add(hyoKbn[i]);
				}
				fmVO.setHyoKbn_arr(arr_hyo);			
			}
		}
		//品番指定のとき
		if(fmForm.getRb_kigBng() == TeiansuuReferForm.RB_SELECT){
			//会社識別コード
			fmVO.setKaiSkbCod_arr(fmVO.getQueryKaiSkbCodList());
			//記号番号
			ArrayList arr = DataFormatUtils.supplement_kigBng(fmForm.getKigBng_List());//記号部省略入力対応 2003/09/10
			for(int i = 0; i < arr.size(); i++){
				arr_kig.add(DataFormatUtils.mk_srykig(arr.get(i).toString().trim()));
			}
			fmVO.setKigBng_arr(arr_kig);		
		}
		//ソート条件
		fmVO.setSort_rank(fmForm.getSort_rank());
		fmVO.setSort_ketCod(fmForm.getSort_ketCod());
		fmVO.setSort_sortKey(fmForm.getSort_sortKey());
		
		fmVO.setHitaisyo(fmForm.getHitaisyo());
		if(fmForm.getHitaisyo())
			fmVO.setTaisyoRange(fmForm.getTaisyoRange());
			
		fmForm.setCheckBoxValueToArray();
		
		try{
			//提案数照会指示 エラーチェック実行
			TeiansuuReferResult result = bzDelegate.doTeianSyokaiShiji((TeiansuuReferVO)fmVO);
		
			//エラーだったのはないか調べる
			if(result != null && !result.isSuccess()){
				if(errors == null)
					errors = new ActionErrors();

				ArrayList errs_kig = result.getMap(TeiansuuReferResult.KEY_KIGBNG);
				String index = "";
				 //品番が存在しない
				if(errs_kig != null){	
					for(int i = 0; i < errs_kig.size(); i++){
						String[] err_kig = (String[])errs_kig.get(i);
						index = err_kig[0];//エラーのあったIndex
						int type  = Integer.parseInt(err_kig[1]);//エラーの種類
						if(type == 1)//TeiansuuReferDAO.NOT_EXIST
							errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.input.incorrect_no",index,"品番"));
						if(type == 2)//TeiansuuReferDAO.RANK_ERR
							errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.input.error_no",index,"品番","ランク"));
					}
				}
			}else{
				//ブランクの要素があったら詰めてセットし直す
				fmVO.setKigBng_arr(DataFormatUtils.removeBlankElement(fmVO.getKigBng_arr()));
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
	 * 貼り付け
	 * @param fmForm
	 */
	public void dataPaste(TeiansuuReferForm fmForm){
		
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
						
						if(fmForm.getKigBng1().equals("")){
							fmForm.setKigBng1(str);
							continue;
						}
						if(fmForm.getKigBng2().equals("")){
							fmForm.setKigBng2(str);					
							continue;
						}
						if(fmForm.getKigBng3().equals("")){
							fmForm.setKigBng3(str);
							continue;
						}
						if(fmForm.getKigBng4().equals("")){
							fmForm.setKigBng4(str);
							continue;
						}
						if(fmForm.getKigBng5().equals("")){
							fmForm.setKigBng5(str);
							continue;
						}
						if(fmForm.getKigBng6().equals("")){
							fmForm.setKigBng6(str);
							continue;
						}
						if(fmForm.getKigBng7().equals("")){
							fmForm.setKigBng7(str);
							continue;
						}
						if(fmForm.getKigBng8().equals("")){
							fmForm.setKigBng8(str);
							continue;
						}
						if(fmForm.getKigBng9().equals("")){
							fmForm.setKigBng9(str);
							continue;
						}
						if(fmForm.getKigBng10().equals("")){
							fmForm.setKigBng10(str);
							continue;
						}
						if(fmForm.getKigBng11().equals("")){
							fmForm.setKigBng11(str);
							continue;
						}
						if(fmForm.getKigBng12().equals("")){
							fmForm.setKigBng12(str);					
							continue;
						}
						if(fmForm.getKigBng13().equals("")){
							fmForm.setKigBng13(str);
							continue;
						}
						if(fmForm.getKigBng14().equals("")){
							fmForm.setKigBng14(str);
							continue;
						}
						if(fmForm.getKigBng15().equals("")){
							fmForm.setKigBng15(str);
							continue;
						}
					}
				}
			}

		}catch(IOException ex){
			SystemException se = new SystemException(ex);
			se.printStackTrace();
		}catch(Exception e){
			SystemException se = new SystemException(e);
			se.printStackTrace();
		}
	}
	

}
