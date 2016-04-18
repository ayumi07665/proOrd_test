/**
* PrintOrdersAction  発注書印刷アクションクラス
*
*	作成日    2003/04/18
*	作成者    （ＮＩＩ）岡田 夏美
* 処理概要    処理呼び出しを行い、画面制御を行うクラス。
*
*	[変更履歴]
*        2003/09/05（ＮＩＩ）岡田 夏美
* 			・発注書パターンによって発注書番号・行番号の採番方法を分岐させる
* 			  発注書パターンによってmapping.findForward先を変更する
* 		 2004/04/21（ＮＩＩ）森
* 			・税込定価表示に伴う修正
* 		 2005/05/19（ＮＩＩ）蛭田
* 			・ヘッダーのセットで会社名漢字２、代表会社をセット
* 			・発注書レイアウトパターン修正
* 		 2005/06/27（ＮＩＩ）蛭田
* 			・発注書データ検索メソッドPTN03（K社用）追加
* 		 2005/09/05（ＮＩＩ）蛭田
* 			・伝票発行・訂正伝票発行の場合、発注履歴と発注書データに履歴更新日を書込む（VAP社対応）
* 			・履歴更新日(1～5回)・訂正回数・金額の表示対応
* 		 2005/09/27（ＮＩＩ）蛭田
* 			・訂正回数MAX９回に変更
* 		 2007/07/18（ＮＩＩ）田中
* 			・納品先名称　ＶＡＰ社10文字 その他メーカー15文字に変更
* 		 2007/12/25（ＮＩＩ）田中
* 			・マニュアル発注指示一括貼付　備考２追加対応
* 		 2008/03/10（ＮＩＩ）田中
* 			・単価追加対応
*/

package com.jds.prodord.print.printorders;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.jds.prodord.common.CommonConst;
import com.jds.prodord.common.DataFormatUtils;
import com.jds.prodord.common.SystemException;
import com.jds.prodord.register.LogonForm;

public class PrintOrdersAction extends Action {
	
	
	//TODO 会社追加対応(ﾋﾞｼﾞﾈｽロジッククラス追加)
	/** 発注書パターン：01 (VAP社) ビジネスロジック */
	private static  PrintOrdersDelegate01 bzDelegate01 = new PrintOrdersDelegate01();
	/** 発注書パターン：02 (CR 社) ビジネスロジック */
	private static  PrintOrdersDelegate02 bzDelegate02 = new PrintOrdersDelegate02();
	/** 発注書パターン：03 (K  社) ビジネスロジック */
	private static  PrintOrdersDelegate03 bzDelegate03 = new PrintOrdersDelegate03();
	/** 発注書パターン：04 (FX 社) ビジネスロジック */
	private static  PrintOrdersDelegate04 bzDelegate04 = new PrintOrdersDelegate04();
	/** 発注書パターン：05 (LAN(BAN) 社) ビジネスロジック */
	private static  PrintOrdersDelegate05 bzDelegate05 = new PrintOrdersDelegate05();
	
	
	public ActionForward perform(ActionMapping mapping,ActionForm form,HttpServletRequest req,HttpServletResponse res){
		ActionErrors errors = null;
		HttpSession session = req.getSession();
		PrintOrdersPageList ordersPages = new PrintOrdersPageList();
		
		PrintOrdersVO fmVO = new PrintOrdersVO();
					
		try {
			//メッセージをセッションから取り除く
			session.removeAttribute(CommonConst.INFOMSG_KEY);
			
			//セッションからPrintOrdersQueryVOを取得
			PrintOrdersQueryVO poQueryVO = (PrintOrdersQueryVO)session.getAttribute(CommonConst.PO_QUERY_VO);
			
			//セッションからLogonFormを取得
			LogonForm logonForm =
				(LogonForm) session.getAttribute(CommonConst.user);
			
			//発注書パターンの取得
			String hacSyoPtn = logonForm.getHacSyoPtn();
			
			//発注書出力処理
			PrintOrdersDelegate bzDelegate = bzDelegate01;//デフォルト
			
			//TODO 会社追加対応(パターンの分岐追加)
			//VAP
			if(hacSyoPtn.equals(CommonConst.PTN01))
				bzDelegate = bzDelegate01;
			//CR
			else if(hacSyoPtn.equals(CommonConst.PTN02))
				bzDelegate = bzDelegate02;
			//K
			else if(hacSyoPtn.equals(CommonConst.PTN03))
				bzDelegate = bzDelegate03;
			//FX
			else if(hacSyoPtn.equals(CommonConst.PTN04))
				bzDelegate = bzDelegate04;
			//LAN（BAN)
			else if(hacSyoPtn.equals(CommonConst.PTN05))
				bzDelegate = bzDelegate05;
			
			errors = this.printOrders(bzDelegate,ordersPages,req,res,poQueryVO,logonForm);
			
			if(errors == null){	//処理成功
				req.setAttribute(CommonConst.BEAN_NAME_PRINT,ordersPages);
				session.removeAttribute(CommonConst.PO_QUERY_VO);
			}
					
			//エラーあり
			if(errors != null){
				super.saveErrors(req,errors);
				return mapping.findForward("failure");
			}
			
			//エラーなし
			//2005/05/30 発注書のレイアウトパターン変更(会社識別コード)
			//TODO 会社追加対応(struts-configにforward先追加、JSP追加)
			ActionForward forward = mapping.findForward(logonForm.getKaiskbcod());
			if(forward != null && forward.getName() != null)
				return forward;
				
		} catch (Exception e) {
			return mapping.findForward("failure");
		}

		return mapping.findForward("failure");
	}
	
		
	/**
	 * 検索
	 * 
	 */
	private ActionErrors printOrders(
		PrintOrdersDelegate bzDelegate,
		ArrayList ordersPages,
		HttpServletRequest req,
		HttpServletResponse res,
		PrintOrdersQueryVO poQueryVO,
		LogonForm logonForm) {
		
		//発注書パターンの取得
		String hacSyoPtn = logonForm.getHacSyoPtn();

		ActionErrors errors = null;

		try{			
			//検索実行
			PrintOrdersVO[] resultFmVOs = bzDelegate.doFindPrintData(poQueryVO,logonForm);
			//ページレイアウトの取得
			ArrayList pageLayout = bzDelegate.doGetPageLayout(poQueryVO);
			//1ページの最大行数
			int defaultRowCount = PrintOrdersPage.DEFAULT_ROW_COUNT;
			//作業用
			ArrayList tmpArr = new ArrayList();
			//各ブレイクキーごとに何ページ分のデータがあるかを格納する(発注番号の採番で使用)
			ArrayList numbers = new ArrayList();

			//ページレイアウトの設定(1ページの最大行数を考慮してページ数、行数を算出する)
			for(int i=0, pageCount=0; i < pageLayout.size(); i++, pageCount=0){
				//同じブレイクキーを持つ行数を取り出す
				PrintOrdersBreakKey bk = (PrintOrdersBreakKey)pageLayout.get(i);
				int rowCount = bk.getCount();
				//同じブレイクキーの行数が、defaultRowCountよりも大きかったら
				if(rowCount > defaultRowCount){
					int addPage = rowCount / defaultRowCount;//増やすページ数
					int oddRow = rowCount % defaultRowCount; //半端な行数
					for(int j = 0; j < addPage; j++){
						tmpArr.add(new PrintOrdersBreakKey(bk.getHacCod(), defaultRowCount));
						pageCount++;
					}
					if(oddRow != 0){
						tmpArr.add(new PrintOrdersBreakKey(bk.getHacCod(), oddRow));
						pageCount++;
					}
				}//同じブレイクキーの行数が、defaultRowCount以内
				else{
					tmpArr.add(new PrintOrdersBreakKey(bk.getHacCod(), rowCount));
					pageCount++;
				}
				bk.setPageCount(pageCount);//同じブレイクキーを持つページ数をセット
				numbers.add(bk);
			}
			//pageLayoutにセットし直す(各ページの行数・発注先が格納されている)
			pageLayout.clear();
			pageLayout.addAll((Collection)tmpArr.clone());
													   
			//算出したページレイアウトの長さ分ordersPagesを生成する
			for(int i = 0; i < pageLayout.size(); i++){
				PrintOrdersPage page = new PrintOrdersPage();
				page.setSize(defaultRowCount);//見た目上1ページの行数は常にdefaultRowCount
				ordersPages.add(page);
			}
			
			String[] hacSyoBng_arr = null;
			String[] gyoBng_arr = null;
			//採番前だったら
			if(!poQueryVO.getHasHacSyoBng()){
				//発注書番号取得
				hacSyoBng_arr = bzDelegate.doCreateHacSyoBng(logonForm.getKaiskbcod(),numbers);
				//行番号取得
				gyoBng_arr = bzDelegate.doCreateGyoBng(logonForm.getKaiskbcod(),pageLayout);
			}
			
			//発注書データをordersPagesにセット
			for(int i=0, pageCount=0, rowCount=0; i < resultFmVOs.length; pageCount++){
				//行数を取得
				rowCount = ((PrintOrdersBreakKey)pageLayout.get(pageCount)).getCount();
				for(int j = 0; j < rowCount; j++,i++){
					//採番を行った場合、発注書番号・行番号をVOにセット
					if(!poQueryVO.getHasHacSyoBng()){
						resultFmVOs[i].setHacSyoBng(hacSyoBng_arr[pageCount]);
						resultFmVOs[i].setGyoBng(gyoBng_arr[i]);
					}
					PrintOrdersPage ordersPage = (PrintOrdersPage)ordersPages.get(pageCount);
					if(j == 0)//各ページの最初にはヘッダーをセット
						this.setHeader(ordersPage, resultFmVOs, i, logonForm, poQueryVO);
					//明細をセット
					this.setDetail(ordersPage.getOrdersRow(j), resultFmVOs[i], ordersPage.getRrkUpDte());
				}
			}

			//最終ページは改ページしない
			((PrintOrdersPage)ordersPages.get(ordersPages.size()-1)).setPageBreak(false);
			
			if(!poQueryVO.getHasHacSyoBng()){
				//発注履歴と発注書データに発注書番号・行番号を書き込む(更新日時の取得)
				bzDelegate.doUpDateHac01Hac02(resultFmVOs,true,poQueryVO.getUpdDte(),poQueryVO.getUpdJkk());
			}else if(poQueryVO.isTeiDnpHakkou()){
				//訂正伝票発行の場合、発注履歴と発注書データに履歴更新日を書き込む(更新日時の取得)
				bzDelegate.doUpDateHac01Hac02(resultFmVOs,false,poQueryVO.getUpdDte(),poQueryVO.getUpdJkk());
			}
			
   		}catch(Exception e){
			SystemException se = new SystemException(e);
			se.printStackTrace();
			errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.exception",e.toString()));
			
		}
		return errors;			
			
	}

//=========================================================================================================

	/** ヘッダーのセット */
	private void setHeader(PrintOrdersPage ordersPage, PrintOrdersVO[] resultFmVOs, int index, LogonForm logonForm, PrintOrdersQueryVO poQueryVO){
		
		ordersPage.setHacCod(resultFmVOs[index].getHacCod());
		ordersPage.setSirHacNm(resultFmVOs[index].getSirHacNm());
		ordersPage.setHacSyoDte(DataFormatUtils.setFormatYYMMDD(resultFmVOs[index].getHacSyoDte()));
		ordersPage.setHacSyoBng(resultFmVOs[index].getHacSyoBng().trim());
		
		//[0]にセットされているユーザーの会社情報を毎ページセットする
		ordersPage.setKaiInfo(resultFmVOs[0]);
		//2005/06/14 usrId add
		ordersPage.setUsrId(logonForm.getUser());
		
		//副資材の発注書のときはフラグを立てる
		if(!resultFmVOs[index].getBunCod().equals("0"))
			ordersPage.setFukSziFlg(true);

		//現在日付
		Date sysdate = new Date();
		ordersPage.setCurrentDate((new SimpleDateFormat("yy/MM/dd HH:mm")).format(sysdate));

		/** 履歴更新日・訂正回数のセット 2005/09/05 add **/
		String rrkTbl = resultFmVOs[index].getRrkDte();
		//初回 または 伝票発行 または 訂正伝票発行の場合
		if(rrkTbl.length() == 0 || !poQueryVO.getHasHacSyoBng() || poQueryVO.isTeiDnpHakkou()){
			String today = new SimpleDateFormat("yyMMdd").format(sysdate);
			rrkTbl = DataFormatUtils.makeRrkTblStr(
					resultFmVOs[index].getRrkDte(), Integer.parseInt(today));
		}
		
		//テキストの分解
		String[] str = new String[9]; //MAX9回
		int size = makeRrkArr(rrkTbl, str);

		ordersPage.setRrkTbl(str[0],str[1],str[2],str[3],checkRrkTbl(str, size));
		ordersPage.setTeiNum(size);

		//履歴更新日のセット(DB登録用)
		resultFmVOs[index].setRrkDte(rrkTbl);
		ordersPage.setRrkUpDte(rrkTbl);

	}


	/**
	 * 履歴更新日のテキストを分解して渡された配列に詰め、サイズを返します。
	 * @param rrkTbl
	 * @param str
	 * @return
	 */
	private int makeRrkArr(String rrkTbl, String[] str) {
		final int length = 4;
		int size = rrkTbl.length() / length;
		for(int i = 0; i < size; i++){
			try{
				str[i] = (rrkTbl.substring(i*length,(i+1)*length));
			}catch(IndexOutOfBoundsException e){
				break;
			}
		}
		return size;
	}

	/**
	 * 発注書に出力する履歴更新日付５回目を取得します。
	 * @param rrkTbl
	 * @param size
	 * @return
	 */
	private String checkRrkTbl(String[] rrkTbl,int size){
		String rrkDte ="";
		if(size >=5)
			rrkDte = rrkTbl[size-1];
		return rrkDte;
	}

	
	/** 明細行のセット */
	private void setDetail(PrintOrdersRow ordersRow, PrintOrdersVO resultFmVO,String rrkUpDte){
		
		ordersRow.setGyoBng(resultFmVO.getGyoBng().trim());
		ordersRow.setSinKyuKbn(DataFormatUtils.getKbnString(resultFmVO.getSinKyuKbn()));
		ordersRow.setKigBng(this.formatHjiHnb(resultFmVO.getHjiHnb()));
		if(ordersRow.getKigBng().equals(""))//表示品番が存在しない時は、発注書データの記号番号をそのまま表示する
			ordersRow.setKigBng(resultFmVO.getKigBng());
		ordersRow.setKetCod(resultFmVO.getKetCod());
		ordersRow.setKetNm(resultFmVO.getKetNm());
		ordersRow.setPrsFukSziCod(resultFmVO.getPrsFukSziCod());
		ordersRow.setSetSuu(DataFormatUtils.setFormatString(resultFmVO.getSetSuu()));
		ordersRow.setHacSuu(DataFormatUtils.setFormatString(resultFmVO.getHacSuu()));
		ordersRow.setNki(DataFormatUtils.setFormatYYMMDD(resultFmVO.getNki()));
		if(resultFmVO.getDaiKaiSkbCod().equals(CommonConst.KAICOD_VAP) 
				|| resultFmVO.getDaiKaiSkbCod().equals(CommonConst.KAICOD_BAN))
			ordersRow.setNhnMeiKj(this.getSubstringStr(resultFmVO.getNhnMeiKj(),10));
		else
			ordersRow.setNhnMeiKj(this.getSubstringStr(resultFmVO.getNhnMeiKj(),15));
		ordersRow.setComment(resultFmVO.getComment());
		ordersRow.setTitKj(this.getSubstringStr(resultFmVO.getTitKj(),15));
		ordersRow.setArtKj(this.getSubstringStr(resultFmVO.getArtKj(),15));
		ordersRow.setZikTik(DataFormatUtils.setFormatString(resultFmVO.getZikTik()).trim()); //2004.04.21 add 08/17add
		ordersRow.setTka(DataFormatUtils.setFormatString(resultFmVO.getTka()).trim()); //2014.02.19
		ordersRow.setFukMeiKj(this.getSubstringStr(resultFmVO.getFukMeiKj(),15));	
		ordersRow.setFukSziSuu(DataFormatUtils.setFormatString(resultFmVO.getFukSziSuu()));
		ordersRow.setHbiDte(DataFormatUtils.setFormatYYMMDD(resultFmVO.getHbiDte())); //2005.06.14 add
		ordersRow.setKin(DataFormatUtils.setFormatString(String.valueOf(resultFmVO.getKin())));//2005/09/13 add
		resultFmVO.setRrkDte(rrkUpDte); //履歴更新日付(DB登録用) 2005/09/06 add
		ordersRow.setBiKou2(resultFmVO.getBiKou2());//2007/12/25 add
		ordersRow.setTan2(DataFormatUtils.setFormatString(String.valueOf(resultFmVO.getTan2())));//2008/03/10 add
	}
	
	/**
	 * 指定した桁数を超えた場合はsubstringして返します。
	 * @param str
	 * @param length
	 * @return
	 */
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
	
	/**
	 * 表示品番のフォーマット（-があったらスペースに換える）
	 * @param kigBng
	 * @return
	 */
	private String formatHjiHnb(String kigBng){
		if(kigBng.indexOf("-") != -1){
			String returnKigBng = "";
			String put_space = "";
			String head = "";
			String tail = "";
			
			head = kigBng.substring(0, kigBng.indexOf("-"));
			tail = kigBng.substring(kigBng.indexOf("-") + 1);
			head = head.concat(put_space);
			returnKigBng = head.concat(tail);
			return returnKigBng;
		}else{
			return kigBng;
		}	
	}

}

