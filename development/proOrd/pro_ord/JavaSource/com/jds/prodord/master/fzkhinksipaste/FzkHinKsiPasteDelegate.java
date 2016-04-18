package com.jds.prodord.master.fzkhinksipaste;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.sql.SQLException;
import java.util.*;

import com.jds.prodord.common.*;
/**
*FzkHinKsiPasteDelegate  付属品構成マスター一括貼り付け実行クラス
*
*	作成日    2007/09/30
*	作成者    （ＮＩＩ）田中
* 処理概要    付属品構成マスターメンテナンスをするクラス。
*
*	[変更履歴]
*/

public class FzkHinKsiPasteDelegate {
	
	/**
	 * 「貼付」処理を行う
	 * @param fmVO
	 * @throws Exception
	 */
	public void doDataPaste(FzkHinKsiPasteVO fmVO)throws Exception{

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

				FzkHinKsiPasteFormRow row = new FzkHinKsiPasteFormRow();
				String str[] = (String[])iter.next();
				
				for(int i =0;i<str.length;i++){
					if(i==0){
							row.setKigBng(formatStr(str[i]));
					}else	if(i==1){
							row.setFukSziCod(formatStr(str[i]));
					}else{
							row.setSirSk(formatStr(str[i]));
							break;
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
	 * 登録処理
	 * @param fmVO
	 * @param fmForm
	 * @return
	 * @throws SQLException
	 */
	public FzkHinKsiPasteResult doInsert(FzkHinKsiPasteVO fmVO,FzkHinKsiPasteForm fmForm)throws SQLException{
				 	
		FzkHinKsiPasteDAO fmDao = null;
		FzkHinKsiPasteResult result = new FzkHinKsiPasteResult(fmVO,true,"");
		FzkHinKsiPasteVO[] finded = null;

		try{
			fmDao = new FzkHinKsiPasteDAO();
			fmDao.setAutoCommit(false); //DBのトランザクション開始
		
			boolean error = false;

			//登録日時
			DateUtils dateUtils = new DateUtils();
			int upddte = dateUtils.getDate6Int();
			int updjkk = dateUtils.getTime6Int();

			LinkedList formRows = fmVO.getFormRows();
			LinkedList voList = new LinkedList();
			LinkedList errList = new LinkedList();

			OrderUtils orderUtils = new OrderUtils(fmDao);

			String kigHead = "";				
			String oldKigHead = "";				
			String newKigHead = "";				
			String oldKigBng = "";				

						
			StringBuffer sb_fukSziCod = new StringBuffer();
			StringBuffer sb_sirSkCod = new StringBuffer();
			ArrayList line_Arr = new ArrayList();

			int i=0;
			for (Iterator iter = formRows.iterator(); iter.hasNext(); i++) {
				FzkHinKsiPasteFormRow row = (FzkHinKsiPasteFormRow) iter.next();
				// 品番が入力されていなかったら、次の行へ
				if (StringUtils.isBlankOrNull(row.getKigBng())) {
					continue;
				}
				String inpKigBng = row.getKigBng();
				
				//記号部の取得
				kigHead = DataFormatUtils.getKigHeader(inpKigBng, kigHead);
				//省略品番の編集
				String kigBng = DataFormatUtils.editKigBng(inpKigBng, kigHead);

				//品番マスターなし
				if(!fmDao.hasKigBng(fmVO.getDaiKaiSkbCod(),kigBng, "FTBHIN01")){
					if(!fmDao.hasKigBng(fmVO.getDaiKaiSkbCod(),kigBng, "FTBHIN02")){
						//行ごとのエラー処理///メッセージとかなにか
						errList.add(new int[]{i,FzkHinKsiPasteForm.NOT_HIN_EXIST});
						error =true;
						continue;
					}
				}
				//副資材コードなし
				if(!fmDao.hasFukSziCod(fmVO,row.getFukSziCod()+"")){
					//行ごとのエラー処理///メッセージとかなにか
					errList.add(new int[]{i,FzkHinKsiPasteForm.NOT_FKS_EXIST});
					error =true;
					continue;
				}
 				//仕入先コードなし
				if(!fmDao.hassirSkCod(fmVO,row.getSirSk()+"")){
					//行ごとのエラー処理///メッセージとかなにか
					errList.add(new int[]{i,FzkHinKsiPasteForm.NOT_SIR_EXIST});
					error =true;
					continue;
				}
				//付属品構成マスター
				if(!fmDao.selectForUpdate(fmVO,kigBng)){
					//行ごとのエラー処理///メッセージとかなにか
					errList.add(new int[]{i,FzkHinKsiPasteForm.MST08_EXIST});
					error =true;
					continue;
				}
			}		

			//マスターチェックでエラーだったら
			if(error){
				result =  new FzkHinKsiPasteResult(fmVO,false,"");
				result.setErrList(errList);
				return result;
			}		

			
			//品番ブレイク処理

			Iterator iter2 = formRows.iterator();

			if (iter2.hasNext()){
				FzkHinKsiPasteFormRow row = (FzkHinKsiPasteFormRow) iter2.next();

				String inpoldKigBng = row.getKigBng();
				//記号部の取得
				oldKigHead = DataFormatUtils.getKigHeader(inpoldKigBng, kigHead);
				//省略品番の編集
				oldKigBng = DataFormatUtils.editKigBng(inpoldKigBng, kigHead);


				sb_fukSziCod.append(row.getFukSziCod());	
				sb_sirSkCod.append(StringUtils.rpad(row.getSirSk(),6," "));

				fmVO.setFuksziCod08String(sb_fukSziCod.toString());
				fmVO.setSirSk08String(sb_sirSkCod.toString());
				fmVO.setKigBng(oldKigBng);
			}
			while(iter2.hasNext()){
				FzkHinKsiPasteFormRow row = (FzkHinKsiPasteFormRow) iter2.next();
				// 品番が入力されていなかったら、次の行へ
				if (StringUtils.isBlankOrNull(row.getKigBng())) {
					continue;
				}
				String inpnewKigBng = row.getKigBng();
				//記号部の取得
				newKigHead = DataFormatUtils.getKigHeader(inpnewKigBng, kigHead);
				//省略品番の編集
				String newKigBng = DataFormatUtils.editKigBng(inpnewKigBng, kigHead);
				if(oldKigBng.equals(newKigBng)){
					sb_fukSziCod.append(row.getFukSziCod());	
					sb_sirSkCod.append(StringUtils.rpad(row.getSirSk(),6," "));

					fmVO.setFuksziCod08String(sb_fukSziCod.toString());
					fmVO.setSirSk08String(sb_sirSkCod.toString());
					fmVO.setKigBng(oldKigBng);
				}else{
					//ソート
					doSort(fmVO);
					//付属品構成マスター検索
					if(fmDao.selectForUpdate(fmVO,oldKigBng)){
						//登録実行
						fmDao.insert(fmVO,upddte,updjkk);
					}

					oldKigBng = newKigBng;					
					fmVO.setFuksziCod08String("");
					fmVO.setSirSk08String("");

					sb_fukSziCod.delete(0,200);
					sb_sirSkCod.delete(0,200);

					sb_fukSziCod.append(row.getFukSziCod());	
					sb_sirSkCod.append(StringUtils.rpad(row.getSirSk(),6," "));

					fmVO.setFuksziCod08String(sb_fukSziCod.toString());
					fmVO.setSirSk08String(sb_sirSkCod.toString());
					fmVO.setKigBng(oldKigBng);
				}
			}

		doSort(fmVO);
		//付属品構成マスター検索
		if(fmDao.selectForUpdate(fmVO,oldKigBng)){
			//登録実行
			fmDao.insert(fmVO,upddte,updjkk);
		}


		result =  new FzkHinKsiPasteResult(fmVO,true,"");


	}catch(SQLException sqle){
		if(fmDao != null){
			try{
				fmDao.rollback();
			}catch(SQLException sqle3){
				sqle3.printStackTrace();
			}
		}
		throw sqle;
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
	 * @param fmVO
	 */

	 private void doSort(FzkHinKsiPasteVO fmVO) {
		 ArrayList fuk_arr = fmVO.get08FuksziCodArr();
		 ArrayList sir_arr = fmVO.get08SirSkCodArr();
		 TreeMap tm = new TreeMap();
		
		 for(int i = 0; i < fuk_arr.size();i++){
			 HashMap map = new HashMap();
			 map.put("fuk_cod",fuk_arr.get(i));
			 map.put("sir_cod",sir_arr.get(i));
			
			 tm.put(fuk_arr.get(i),map);
		 }
		
		 ArrayList map_arr = new ArrayList();
		 HashMap map_bun1 = new HashMap();
		 Iterator iter = tm.values().iterator(); //昇順
		
		 while (iter.hasNext()) {
			 HashMap map = (HashMap)iter.next();
			 map_arr.add(map);			
		 }

		 StringBuffer sb_fukSziCod = new StringBuffer();
		 StringBuffer sb_sirSkCod = new StringBuffer();
		 for(int i = 0; i < map_arr.size();i++){	
			 HashMap map = (HashMap)map_arr.get(i);		
			 sb_fukSziCod.append(map.get("fuk_cod"));	
			 sb_sirSkCod.append(StringUtils.rpad((String) map.get("sir_cod"),6," "));
		 }
		 fmVO.setFuksziCod08String(sb_fukSziCod.toString());
		 fmVO.setSirSk08String(sb_sirSkCod.toString());

	 }
		
}

