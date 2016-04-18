package com.jds.prodord.indicate.manualorderpaste;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.sql.SQLException;
import java.util.*;

import com.jds.prodord.common.*;
import com.jds.prodord.order.prsorder.PrsOrderForm;
import com.jds.prodord.order.prsorder.PrsOrderVO;
/**
 * ManualOrderPasteDelegate  マニュアル発注指示一括貼付実行クラス。<BR>
 * <dt><b>作成日: </b></dt>
 * <dd>2007/09/10</dd>
 * <dt><B>作成者: </b></dt>
 * <dd>(NII)</dd>
 * <dt><B>処理概要: </b></dt>
 * <dd>処理を実行するクラス。</dd>
 * <br>
 * <b>変更履歴</b><br>
 * <table border="1">
 * 	<tr>
 * 		<th width="80">修正日付</th><th width="100">修正者</th><th width="300">修正内容</th>
 * 	</tr>
 * 	<tr>
 * 		<td>yyyy/MM/dd</td>
 * 		<td>(NII)</td>
 * 		<td>・</td>
 * 	</tr>
 * </table>
 */
public class ManualOrderPasteDelegate {
	
	/**
	 * 「貼付」処理を行う
	 * @param fmVO
	 * @throws Exception
	 */
	public void doDataPaste(ManualOrderPasteVO fmVO)throws Exception{

		final String tab = "\t";
		LinkedList dataList = new LinkedList();
		LinkedList formRows = new LinkedList();

		String data = fmVO.getCpPaste();
		BufferedReader br = new BufferedReader(new StringReader(data));

		String txt = "";
		int count = DataFormatUtils.getDataRowCount(data);

		try {
			for (int i = 0; i < count; i++) {

				if((txt = br.readLine()) != null){

					txt = DataFormatUtils.insertSpace(txt, tab);
					if(!txt.trim().equals(""))
						dataList.add(StringUtils.split(txt, tab));

				}

			}
			for (Iterator iter = dataList.iterator(); iter.hasNext();) {

				ManualOrderPasteFormRow row = new ManualOrderPasteFormRow();
				String str[] = (String[])iter.next();
				
				for(int i =0;i<str.length;i++){
					if(i==0){
						row.setKigBng(formatStr(str[i]));
					}else{ 
						//プレス
						if(fmVO.getPrsFks().equals(ManualOrderPasteForm.PRS)){
							switch(i){
								case 1:
								   row.setSuuRyo(formatStr(str[i]));
								   break;
								default:
								   row.setBiKou2(formatStr(str[i]));
								   break;
							}
						//副資材	
						}else{
							switch(i){
								case 1:
								   row.setFukSziCod(formatStr(str[i]));
								   break;
								case 2:
								   row.setSuuRyo(formatStr(str[i]));
								   break;
								default:
								   row.setBiKou2(formatStr(str[i]));
								   break;
							}
						}
					}	
				}

				formRows.add(row);
			}
			
			fmVO.setFormRows(formRows);
			
		} catch (IOException e) {
			System.out.println("貼り付けデータの読み込みでエラーが発生しました。");
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}

	}

	/**
	 * 値がnullならブランク、それ以外ならtrim()して返す。
	 * @param value チェック対象の値
	 * @return 値がnullならブランク、それ以外ならtrim()して返す。
	 */
	public String formatStr(String value) {
		if(value == null)
			return "";
		else
			return value.toUpperCase().trim();
	}

	/**
	 * プレス発注
	 * @param fmVO
	 * @param fmForm
	 * @return
	 * @throws SQLException
	 */
	public ManualOrderPasteResult doPrsHachu(ManualOrderPasteVO fmVO,ManualOrderPasteForm fmForm)throws SQLException{
				 	
		ManualOrderPasteDAO fmDao = null;
		ManualOrderPasteResult result = new ManualOrderPasteResult(fmVO,true,"");

		try{
			fmDao = new ManualOrderPasteDAO();
			fmDao.setAutoCommit(false); //DBのトランザクション開始

			boolean error = false;

			LinkedList formRows = fmVO.getFormRows();
			LinkedList voList = new LinkedList();
			LinkedList errList = new LinkedList();

			OrderUtils orderUtils = new OrderUtils(fmDao);
			String kigHead ="";
			
			int i=0;
			for (Iterator iter = formRows.iterator(); iter.hasNext(); i++) {
				ManualOrderPasteFormRow row = (ManualOrderPasteFormRow) iter.next();
				// 品番が入力されていなかったら、次の行へ
				if (StringUtils.isBlankOrNull(row.getKigBng())) {
					continue;
				}
				String inpKigBng = row.getKigBng();
				
				//記号部の取得
				kigHead = DataFormatUtils.getKigHeader(inpKigBng, kigHead);
				//省略品番の編集
				String kigBng = DataFormatUtils.editKigBng(inpKigBng, kigHead);
				
				PrsOrderVO poVO = fmDao.getHinData(fmVO.getDaiKaiSkbCod(),kigBng, "FTBHIN01");

				//品番マスターなし
				if(!poVO.getExsitHin01()){
					poVO = fmDao.getHinData(fmVO.getDaiKaiSkbCod(),kigBng, "FTBHIN02");

					//HIN02もなかったら
					if(poVO==null){
						//行ごとのエラー処理
						errList.add(new String[]{String.valueOf(i),ManualOrderPasteForm.NOT_HIN_EXIST});
						error =true;
						continue;
					}
				}
				
				poVO.setQueryKaiSkbCod(fmVO.getQueryKaiSkbCod());
				poVO.setUsrId(fmVO.getUsrId());


				poVO.setKbn(fmVO.getKbn());//区分（入力値）

				poVO.setPrsHacSuu(row.getSuuRyo());//発注数（入力値）

				poVO.setBiKou2(row.getBiKou2());//備考２（入力値）

				poVO.setPrsNki(this.calculatePrsNki(poVO.getHbiDte(),poVO.getDaiKaiSkbCod()));//納期
				poVO.setComment("");//コメント
				poVO.setChoksoKbn("0");//直送区分
				poVO.setNhnMei(DataFormatUtils.getSkoNm(fmVO.getBshCod()));//納品先名
				//発注先マスター検索
				poVO.setHacNm(fmDao.getHacNm(poVO.getDaiKaiSkbCod(),poVO.getPrsMkrCod()));

				
				//在庫マスター検索
				fmDao.findZai01(poVO);
				//副資材発注先コード・副資材名称取得
				orderUtils.findFukSziHacSaki(poVO);	

				//発注履歴テーブル検索（プレス）
				fmDao.findHac02Prs(poVO);
				//発注履歴テーブル検索（副資材）
				fmDao.findHac02Fuk(poVO);

				
				//基準行フラグ
				poVO.setBasedRowFlg("1");
				//全品番にチェックをつける　
				poVO.setCheck_prs1(true);
				if(i == 0){
					poVO.setPre_page(PrsOrderForm.ManualOrderPaste);//遷移元ページ
					poVO.setPrs_FukSgn(PrsOrderForm.PRSHACHU);
				}
			

				voList.add(poVO);				

				
			}
			if(error){
				//該当データなし
				result =  new ManualOrderPasteResult(fmVO,false,"");
				result.setErrList(errList);
			}else{
				fmVO.setPrsVOs((PrsOrderVO[]) voList.toArray(new PrsOrderVO[voList.size()]));
				//該当データなし
				result =  new ManualOrderPasteResult(fmVO,true,"");
			}


		}finally{
			if(fmDao != null){
				try{
					fmDao.commit();
					fmDao.close();
				}catch(SQLException sqle2){
					SystemException se = new SystemException(sqle2);
					se.printStackTrace();
				}
			}
		}
		return result;
		
	}
	
	/**
	 * 副資材発注
	 * @param fmVO
	 * @param fmForm
	 * @return
	 * @throws SQLException
	 */
	public ManualOrderPasteResult doFukHachu(ManualOrderPasteVO fmVO,ManualOrderPasteForm fmForm)throws SQLException{
				 	
		ManualOrderPasteDAO fmDao = null;
		ManualOrderPasteResult result = new ManualOrderPasteResult(fmVO,true,"");
		PrsOrderVO[] finded = null;

		try{
			fmDao = new ManualOrderPasteDAO();
			fmDao.setAutoCommit(false); //DBのトランザクション開始
		
			boolean error = false;

			LinkedList formRows = fmVO.getFormRows();
			LinkedList voList = new LinkedList();
			LinkedList errList = new LinkedList();

			OrderUtils orderUtils = new OrderUtils(fmDao);

			String kigHead = "";				

			int i=0;
			for (Iterator iter = formRows.iterator(); iter.hasNext(); i++) {
				ManualOrderPasteFormRow row = (ManualOrderPasteFormRow) iter.next();
				// 品番が入力されていなかったら、次の行へ
				if (StringUtils.isBlankOrNull(row.getKigBng())) {
					continue;
				}
				String inpKigBng = row.getKigBng();
				
				//記号部の取得
				kigHead = DataFormatUtils.getKigHeader(inpKigBng, kigHead);
				//省略品番の編集
				String kigBng = DataFormatUtils.editKigBng(inpKigBng, kigHead);


				PrsOrderVO poVO = fmDao.getHinData(fmVO.getDaiKaiSkbCod(),kigBng, "FTBHIN01");

				//品番マスターなし
				if(!poVO.getExsitHin01()){
					poVO = fmDao.getHinData(fmVO.getDaiKaiSkbCod(),kigBng, "FTBHIN02");

					//HIN02もなかったら
					if(poVO==null){
						//行ごとのエラー処理///メッセージとかなにか
						errList.add(new String[]{String.valueOf(i),ManualOrderPasteForm.NOT_HIN_EXIST});
						error =true;
						continue;
					}
				}
				//付属品構成マスター検索・副資材コードのセットを取得
				HashMap resultMap 
					= orderUtils.findFukSzi_SirSkCodArr(
						poVO.getDaiKaiSkbCod(),
						poVO.getKaiSkbCod(),
						poVO.getKigBng());
					
				ArrayList fukSziCod_arr = (ArrayList) resultMap.get("fukSziCod_arr");
				ArrayList fukSziHacSaki_arr = (ArrayList) resultMap.get("fukSziHacSaki_arr");
				
				if(fukSziCod_arr==null||!fukSziCod_arr.contains(row.getFukSziCod())){
					//行ごとのエラー処理
					errList.add(new String[]{String.valueOf(i),ManualOrderPasteForm.NOT_FKS_EXIST});
					error =true;

					continue;
				}
				

				poVO.setFukSziCod(row.getFukSziCod());//副資材ｺｰﾄﾞ（入力値）

				int idx = fukSziCod_arr.indexOf(row.getFukSziCod());
				poVO.setFukSziHacSaki((String)fukSziHacSaki_arr.get(idx));

				//副資材発注先を発注先コードにセット
				poVO.setPrsMkrCod(poVO.getFukSziHacSaki());
				//副資材マスター検索
				fmDao.getFukSziData(poVO);

				poVO.setQueryKaiSkbCod(fmVO.getQueryKaiSkbCod());
				poVO.setUsrId(fmVO.getUsrId());

				poVO.setKbn(fmVO.getKbn());//区分
				poVO.setPrsHacSuu(row.getSuuRyo());//発注数には入力値
				poVO.setBiKou2(row.getBiKou2());//備考２には入力値
				poVO.setPrsNki(this.calculateFukNki(poVO.getHbiDte(),poVO.getDaiKaiSkbCod()));//納期
				poVO.setComment("");//コメント
				poVO.setChoksoKbn("0");//直送区分

				poVO.setNhnMei(fmDao.getHacNm(poVO.getDaiKaiSkbCod(),poVO.getNhnCod()));//納品先名称取得
				poVO.setHacNm(fmDao.getHacNm(poVO.getDaiKaiSkbCod(),poVO.getFukSziHacSaki()));//発注先名称取得


				//在庫マスター検索
				fmDao.findZai01(poVO);
					
				//発注履歴テーブル検索（プレス）
				fmDao.findHac02Prs(poVO);
				//発注履歴テーブル検索（副資材）
				fmDao.findHac02Fuk(poVO);
				
				//全品番にチェックをつける　
				poVO.setCheck_prs1(true);
				//基準行フラグ
				poVO.setBasedRowFlg("1");
				
				if(i == 0){
					poVO.setPre_page(PrsOrderForm.ManualOrderPaste);//遷移元ページ
					poVO.setPrs_FukSgn(PrsOrderForm.FUKHACHU);
				}

			voList.add(poVO);				

			}		
		
		if(error){
			result =  new ManualOrderPasteResult(fmVO,false,"");
			result.setErrList(errList);
		}else{
			fmVO.setPrsVOs((PrsOrderVO[]) voList.toArray(new PrsOrderVO[voList.size()]));
			result =  new ManualOrderPasteResult(fmVO,true,"");
		}

		}finally{
			if(fmDao != null){
				try{
					fmDao.commit();
					fmDao.close();
				}catch(SQLException sqle2){
					SystemException se = new SystemException(sqle2);
					se.printStackTrace();
				}
			}
		}

		return result;
	}
	
	//--------------------------------------------------------------------
	
	/**
	 * プレス納期の算出
	 * @param hbiDte
	 * @param daiKaiSkbCod
	 * @return
	 */
	public int calculatePrsNki(String hbiDte,String daiKaiSkbCod){
		if(hbiDte.equals("") || hbiDte.equals("0"))
			return 0;
		
		int prsnki = 0;
		//今日の日付
		DateUtils duToday = new DateUtils();
		//2005/05/30 Ｋ社の場合、納期(当日 + ７日)
		if(daiKaiSkbCod.equals(CommonConst.KAICOD_K)||
				daiKaiSkbCod.equals(CommonConst.KAICOD_BAN)){
			duToday.addToDate(Calendar.DATE, 7);//７日後
			prsnki = duToday.getDate6Int();
		}else{

			//発売日
			DateUtils duHbiDte = new DateUtils(hbiDte, DateUtils.PTN_DATE_6);
			duHbiDte.addToDate(Calendar.DATE, -14);//発売日－１４日

			//2006/12/19 ＦＸ社の場合
			if(daiKaiSkbCod.equals(CommonConst.KAICOD_FX)){
				//発売日－１４日が当日より前の日付だったら
				if(duToday.getDate8Int() > duHbiDte.getDate8Int()){
					int hbiDte8 = DateUtils.convertYYYYMMDD(Integer.parseInt(hbiDte));
					if(duToday.getDate8Int() > hbiDte8){
						duToday.addToDate(Calendar.DATE, 14);//１４日後
						prsnki = duToday.getDate6Int();//１４日後
					}else{
						prsnki = duToday.getDate6Int();//当日日付
					}	
				}else{
					prsnki = duHbiDte.getDate6Int();//発売日－１４日
				}
			}else{
				//発売日－１４日が当日より前の日付だったら
				if(duToday.getDate8Int() > duHbiDte.getDate8Int())
					prsnki = duToday.getDate6Int();//当日日付
				else
					prsnki = duHbiDte.getDate6Int();//発売日－１４日
			}
		}
		return prsnki;
	}

	/**
	 * 副資材納期の算出
	 * @param hbiDte
	 * @param daiKaiSkbCod
	 * @return
	 */
	public int calculateFukNki(String hbiDte,String daiKaiSkbCod){
		if(hbiDte.equals("") || hbiDte.equals("0"))
			return 0;
			
		int fuknki = 0;
		//今日の日付
		DateUtils duToday = new DateUtils();
		//発売日
		DateUtils duHbiDte = new DateUtils(hbiDte, DateUtils.PTN_DATE_6);

		//2006/12/19 ＦＸ社の場合
		if(daiKaiSkbCod.equals(CommonConst.KAICOD_FX)){
			duHbiDte.addToDate(Calendar.DATE, -42);//発売日－４２日
			//発売日－４２日が当日より前の日付だったら
			if(duToday.getDate8Int() > duHbiDte.getDate8Int()){
				int hbiDte8 = DateUtils.convertYYYYMMDD(Integer.parseInt(hbiDte));
				if(duToday.getDate8Int() > hbiDte8){
					duToday.addToDate(Calendar.DATE, 7);//７日後
					fuknki = duToday.getDate6Int();//７日後
				}else{
					fuknki = duToday.getDate6Int();//当日日付
				}	
			}else{
				fuknki = duHbiDte.getDate6Int();//発売日－４２日
			}
			//LAN社の場合	
		}else if(daiKaiSkbCod.equals(CommonConst.KAICOD_BAN)){
			duToday.addToDate(Calendar.DATE, 7);//７日後
			fuknki = duToday.getDate6Int();
		}else{
			duHbiDte.addToDate(Calendar.DATE, -21);//発売日－２１日
			//発売日－２１日が当日より前の日付だったら
			if(duToday.getDate8Int() > duHbiDte.getDate8Int())
				fuknki = duToday.getDate6Int();//当日日付
			else
				fuknki = duHbiDte.getDate6Int();//発売日－２１日
		}
		return fuknki;
	}

	
}

