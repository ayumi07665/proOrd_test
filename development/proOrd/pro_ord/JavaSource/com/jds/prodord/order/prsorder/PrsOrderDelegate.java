/**
* PrsOrderDelegate  プレス・副資材発注画面 処理実行クラス
*
*	作成日    2003/04/28
*	作成者    （ＮＩＩ）岡田 夏美
* 処理概要    処理を実行するクラス。
*
*	[変更履歴]
*       2003/04/28 （ＮＩＩ）蛭田 敬子
* 			・「発注指示」「伝票発行」の追加。
*		2003/05/16 （ＮＩＩ）岡田 夏美
* 			・発注先存在チェックに発注先名称の取得追加。
* 		2003/06/18 （ＮＩＩ）岡田 夏美
* 			・前100件次100件対応追加。
*			・副資材発注機能追加。
*		2003/07/02 （ＮＩＩ）蛭田 敬子
* 			・副資材発注のとき、直送区分＝'0'固定追加。
* 		2003/07/28（ＮＩＩ）岡田 夏美
* 			・発注指示・伝票発行時、プレス発注分マイナスする前の副資材在庫数を取得
* 			  発注履歴・発注書データの副資材在庫数にセット
* 		2003/08/06（ＮＩＩ）岡田 夏美 
* 			・発注指示、伝票発行時、在庫テーブルの更新追加
* 		2004/04/02 (NII) 森
* 			・副資材発注の修正(分類コード１複数存在に対応)
* 		2005/05/26（ＮＩＩ）蛭田
* 			・納品先名が'ＪＡＲＥＤ'の場合、直送区分"0"の修正
* 		2005/09/14（ＮＩＩ）蛭田
* 			・副資材発注画面、副資材コードをプルダウン項目に変更（VAP社対応）
* 
*/

package com.jds.prodord.order.prsorder;
import java.sql.*;
import java.util.*;

import com.jds.prodord.common.*;
import com.jds.prodord.reference.ikkatsurefer.*;
public class PrsOrderDelegate {

	public static final String ANOTHER_USER_UPDATE = "ANOTHER_USER_UPDATE";
	public static final String EXIST = "EXIST";
	public static final String LOGICAL_DELETE = "LOGICAL_DELETE";
	public static final String HACSAKI_NOT_EXIST = "HACSAKI_NOT_EXIST";


	

	/**********************************************************************************************
	 * 		発注指示
	 * 
	 *********************************************************************************************/
	public PrsOrderResult[] doHacShiji(PrsOrderVO[] fmVOs, String syrkbn,String bshCod)throws SQLException{
		PrsOrderDAO fmDao = null;
	
		PrsOrderResult[] results = new PrsOrderResult[fmVOs.length];

		boolean endTransaction = false;
		try{
			fmDao = new PrsOrderDAO();
			fmDao.setAutoCommit(false); //DBのトランザクション開始


			//更新日時
			DateUtils dateUtils = new DateUtils();
			int upddte = dateUtils.getDate6Int();
			int updjkk = dateUtils.getTime6Int();

			boolean error = false;
			int[] number = null;
			
			for(int i = 0;i < fmVOs.length;i++){
				if(fmVOs[i] == null)
					continue;
					
				//発注先存在チェック
				fmDao.hasHacSaki(fmVOs[i]);
			    if(fmVOs[i].getHacNm().equals(PrsOrderDAO.NOT_EXIST)){			    	
				    results[i] = new PrsOrderResult(fmVOs[i],false,HACSAKI_NOT_EXIST);
				    fmVOs[i].setHacNm("");
				    error = true;
					continue;
			    }
			}
		 

			//エラーのレコードがなければinsert実行
			if(!error){
				//処理回数の採番
				for(int i = 0;i < fmVOs.length;i++){
					if(fmVOs[i] != null){
						number = NumberingUtils.createNumbers(fmVOs[i].getQueryKaiSkbCod(),"",1,"");
						break;
					}
				}
				OrderUtils orderUtils = new OrderUtils(fmDao);
				java.text.DecimalFormat df = new java.text.DecimalFormat("000000");
				String syrsuu = df.format(number[0]);
				int seqno = 0;
				for(int i = 0;i < fmVOs.length;i++){
					if(fmVOs[i] == null)
						continue;

					seqno++;//fmVOs[i] == nullではなかったら
					try{
						//プレス発注分マイナスする前の副資材在庫数を取得 2003/07/28 add
//						String fukSziSuu = OrderUtils.findHin12(fmVOs[i].getDaiKaiSkbCod(),fmVOs[i].getKaiSkbCod(),fmVOs[i].getKigBng(),"1",fmVOs[i].getFukSziCod(),fmVOs[i].getSmpKbn());
//						fmVOs[i].setFukSziSuu((fukSziSuu == null)? "0" : fukSziSuu);
						if(fmVOs[i].getBasedRowFlg().equals("1")){
							String fukSziSuu = orderUtils.findHin12(fmVOs[i].getDaiKaiSkbCod(),fmVOs[i].getKaiSkbCod(),fmVOs[i].getKigBng(),fmVOs[i].getBunCod(),fmVOs[i].getFukSziCod(),fmVOs[i].getSmpKbn());
							fmVOs[i].setFukSziSuu((fukSziSuu == null)? "0" : fukSziSuu);
						}else{
							fmVOs[i].setFukSziSuu("0");
						}

						
						String syrzmisgn = "0";
						String cykKbn = getCykKbn(fmVOs[i].getNhnMei(),syrkbn,bshCod);
						//発注履歴の登録
						fmDao.insertHac02(fmVOs[i],upddte,updjkk,syrsuu,StringUtils.lpad(seqno+"",3,"0"),syrzmisgn,cykKbn,syrkbn);
						fmVOs[i].setChoksoKbn(cykKbn);//直送区分をセット
						
						if(syrkbn.equals(PrsOrderForm.PRSHACHU)){
							//副資材在庫の存在チェック・登録(複数)
							orderUtils.insertHin12List(
								fmVOs[i].getDaiKaiSkbCod(),
								fmVOs[i].getKaiSkbCod(),
								fmVOs[i].getKigBng(),
								fmVOs[i].getSmpKbn(),
								fmVOs[i].getFukSziCod_arr(),
								fmVOs[i].getBunCod_arr(),
								upddte,
								updjkk);
							//副資材在庫をプレス発注分マイナスする
							orderUtils.updateHin12(fmVOs[i].getDaiKaiSkbCod(),fmVOs[i].getKaiSkbCod(),
												   fmVOs[i].getKigBng(),fmVOs[i].getSmpKbn(),(Integer.parseInt(fmVOs[i].getPrsHacSuu()) * -1),upddte,updjkk);
							//在庫テーブル更新 2003/08/06 add
							if(fmVOs[i].getSmpKbn().equals(""))
								orderUtils.updateZai01(fmVOs[i].getDaiKaiSkbCod(),fmVOs[i].getKigBng(),
											   	   Integer.parseInt(fmVOs[i].getPrsHacSuu()),upddte,updjkk,OrderUtils.PRSHAC);
											   	   
							//IkkatsuReferへ渡す ｢発注済｣ サイン
							fmVOs[i].setSyrZmiSgn_prs(IkkatsuReferDAO.HACZMI);

						}else if(syrkbn.equals(PrsOrderForm.FUKHACHU)){
							//HIN12が存在しなかったら 2003/09/22 add
							if(orderUtils.findHin12(fmVOs[i].getDaiKaiSkbCod(),fmVOs[i].getKaiSkbCod(),fmVOs[i].getKigBng(),fmVOs[i].getBunCod(),fmVOs[i].getFukSziCod(),fmVOs[i].getSmpKbn()) == null)
								orderUtils.insertHin12(fmVOs[i].getDaiKaiSkbCod(),fmVOs[i].getKaiSkbCod(),fmVOs[i].getKigBng(),fmVOs[i].getFukSziCod(),0,fmVOs[i].getBunCod(),fmVOs[i].getSmpKbn(),upddte,updjkk);
							//在庫テーブル更新 2003/08/06 add
							if(fmVOs[i].getSmpKbn().equals(""))
								orderUtils.updateZai01(fmVOs[i].getDaiKaiSkbCod(),fmVOs[i].getKigBng(),
											   	   Integer.parseInt(fmVOs[i].getPrsHacSuu()),upddte,updjkk,OrderUtils.FUKHAC);
							
							fmVOs[i].setSyrZmiSgn_fuk(IkkatsuReferDAO.HACZMI);
						}
				
					}catch(SQLException sqle2){
						SystemException se = new SystemException(sqle2);
						se.printStackTrace();
						error = true;
						results[i] = new PrsOrderResult(fmVOs[i],false,"");
						continue;
					}
					//正常に成功
					results[i] = new PrsOrderResult(fmVOs[i],true,"");
				} 
			}
			//エラーならロールバック
			if(error){
//System.out.println("PrsOrderDelegate --> rollback");
				fmDao.rollback();
				endTransaction = true;
			//エラーがなければコミット
			}else{
//System.out.println("PrsOrderDelegate --> commit");
				fmDao.commit();
				endTransaction = true;
				
				for(int i = 0;i < fmVOs.length;i++){
					if(fmVOs[i] == null)
						continue;
					if(syrkbn.equals(PrsOrderForm.PRSHACHU)){
						//プレス発注情報をセットしなおす
						this.setPrsHacJoho(fmVOs[i]);
					}else if(syrkbn.equals(PrsOrderForm.FUKHACHU)){
						//副資材発注情報をセットしなおす
						this.setFukHacJoho(fmVOs[i]);
					}
					//チェックボックスをクリア
					fmVOs[i].setCheck_prs1(false);
				}
			}
		}finally{
			if(fmDao != null){
				if(!endTransaction ){//exception でcommit or rollback できなかったときは ここで rollback
					try{
						fmDao.rollback();
					}catch(SQLException sqle3){
						SystemException se = new SystemException(sqle3);
						se.printStackTrace();
					}
				}
				try{
					fmDao.close();
				}catch(SQLException sqle2){
					SystemException se = new SystemException(sqle2);
					se.printStackTrace();
				}
			}
		}
		return results;
		
	}

	
	/*************************************************************************************************
	 * 		伝票発行
	 * 
	 ************************************************************************************************/
	public PrsOrderResult[] doDnpHakkou(PrsOrderVO[] fmVOs, String syrkbn,String bshCod)throws SQLException{
		PrsOrderDAO fmDao = null;
	
		PrsOrderResult[] results = new PrsOrderResult[fmVOs.length];

		boolean endTransaction = false;
		try{
			fmDao = new PrsOrderDAO();
			fmDao.setAutoCommit(false); //DBのトランザクション開始


			//更新日時
			DateUtils dateUtils = new DateUtils();
			int upddte = dateUtils.getDate6Int();
			int updjkk = dateUtils.getTime6Int();

			boolean error = false;
			int[] number = null;

			for(int i = 0;i < fmVOs.length;i++){
				if(fmVOs[i] == null)
					continue;
					
				//発注先存在チェック
				fmDao.hasHacSaki(fmVOs[i]);
			    if(fmVOs[i].getHacNm().equals(PrsOrderDAO.NOT_EXIST)){			    	
				    results[i] = new PrsOrderResult(fmVOs[i],false,HACSAKI_NOT_EXIST);
				    fmVOs[i].setHacNm("");
				    error = true;
					continue;
			    }
			}

			//エラーのレコードがなければinsert実行
			if(!error){
				//処理回数の採番
				for(int i = 0;i < fmVOs.length;i++){
					if(fmVOs[i] != null){
						number = NumberingUtils.createNumbers(fmVOs[i].getQueryKaiSkbCod(),"",1,"");
						break;
					}
				}
				OrderUtils orderUtils = new OrderUtils(fmDao);
				java.text.DecimalFormat df = new java.text.DecimalFormat("000000");
				String syrsuu = df.format(number[0]);
				int seqno = 0;
				for(int i = 0;i < fmVOs.length;i++){
					if(fmVOs[i] == null)
						continue;
						
					seqno++;//fmVOs[i] == nullではなかったら行番号++
					try{
						//プレス発注分マイナスする前の副資材在庫数を取得 2003/07/28 add
//						String fukSziSuu = OrderUtils.findHin12(fmVOs[i].getDaiKaiSkbCod(),fmVOs[i].getKaiSkbCod(),fmVOs[i].getKigBng(),"1",fmVOs[i].getFukSziCod(),fmVOs[i].getSmpKbn());
						if(fmVOs[i].getBasedRowFlg().equals("1")){
							String fukSziSuu = orderUtils.findHin12(fmVOs[i].getDaiKaiSkbCod(),fmVOs[i].getKaiSkbCod(),fmVOs[i].getKigBng(),fmVOs[i].getBunCod(),fmVOs[i].getFukSziCod(),fmVOs[i].getSmpKbn());
							fmVOs[i].setFukSziSuu((fukSziSuu == null)? "0" : fukSziSuu);
						}else
							fmVOs[i].setFukSziSuu("0");

						String syrzmisgn = "1";
						String cykKbn = getCykKbn(fmVOs[i].getNhnMei(),syrkbn,bshCod);
						String _seqno = StringUtils.lpad(seqno+"",3,"0");
						//発注履歴テーブルに書き込み
						fmDao.insertHac02(fmVOs[i],upddte,updjkk,syrsuu,_seqno,syrzmisgn,cykKbn,syrkbn);
						fmVOs[i].setChoksoKbn(cykKbn);//直送区分をセット
						//発注書データ書き込み
						orderUtils.insertHac01(
							fmVOs[i].getDaiKaiSkbCod(),//代表会社
							fmVOs[i].getKaiSkbCod(),//会社
							String.valueOf(upddte),//発注書日付
							syrsuu,//処理回数
							_seqno,//SEQNO
							0,//データ交換フラグ
							upddte,
							updjkk);
						
						if(syrkbn.equals(PrsOrderForm.PRSHACHU)){
							//副資材在庫の存在チェック・登録(複数)
							orderUtils.insertHin12List(
								fmVOs[i].getDaiKaiSkbCod(),
								fmVOs[i].getKaiSkbCod(),
								fmVOs[i].getKigBng(),
								fmVOs[i].getSmpKbn(),
								fmVOs[i].getFukSziCod_arr(),
								fmVOs[i].getBunCod_arr(),
								upddte,
								updjkk);
							//副資材在庫をプレス発注分マイナスする
							orderUtils.updateHin12(fmVOs[i].getDaiKaiSkbCod(),fmVOs[i].getKaiSkbCod(),
												   fmVOs[i].getKigBng(),fmVOs[i].getSmpKbn(),(Integer.parseInt(fmVOs[i].getPrsHacSuu()) * -1),upddte,updjkk);
							//在庫テーブル更新 2003/08/06 add
							if(fmVOs[i].getSmpKbn().equals(""))
								orderUtils.updateZai01(fmVOs[i].getDaiKaiSkbCod(),fmVOs[i].getKigBng(),
											   	   Integer.parseInt(fmVOs[i].getPrsHacSuu()),upddte,updjkk,OrderUtils.PRSHAC);
							
							//IkkatsuReferへ渡す ｢出力済｣ サイン
							fmVOs[i].setSyrZmiSgn_prs(IkkatsuReferDAO.SYRZMI);
							
						}else if(syrkbn.equals(PrsOrderForm.FUKHACHU)){
							//HIN12が存在しなかったら 2003/09/22 add
							if(orderUtils.findHin12(fmVOs[i].getDaiKaiSkbCod(),fmVOs[i].getKaiSkbCod(),fmVOs[i].getKigBng(),fmVOs[i].getBunCod(),fmVOs[i].getFukSziCod(),fmVOs[i].getSmpKbn()) == null)
								orderUtils.insertHin12(fmVOs[i].getDaiKaiSkbCod(),fmVOs[i].getKaiSkbCod(),fmVOs[i].getKigBng(),fmVOs[i].getFukSziCod(),0,fmVOs[i].getBunCod(),fmVOs[i].getSmpKbn(),upddte,updjkk);
							//在庫テーブル更新 2003/08/06 add
							if(fmVOs[i].getSmpKbn().equals(""))
								orderUtils.updateZai01(fmVOs[i].getDaiKaiSkbCod(),fmVOs[i].getKigBng(),
											   	   Integer.parseInt(fmVOs[i].getPrsHacSuu()),upddte,updjkk,OrderUtils.FUKHAC);
							
							fmVOs[i].setSyrZmiSgn_fuk(IkkatsuReferDAO.SYRZMI);
						}
						
					}catch(SQLException sqle2){
						SystemException se = new SystemException(sqle2);
						se.printStackTrace();
						error = true;
						results[i] = new PrsOrderResult(fmVOs[i],false,"");
						continue;
					}
					//正常に成功
					results[i] = new PrsOrderResult(fmVOs[i],true,"");
				} 
			}
			//エラーならロールバック
			if(error){
				fmDao.rollback();
				endTransaction = true;
			//エラーがなければコミット
			}else{
				fmDao.commit();
				endTransaction = true;
				boolean flg = false;
				for(int i = 0;i < fmVOs.length;i++){
					if(fmVOs[i] == null)
						continue;
					if(fmVOs[i] != null && !flg){//resultsに処理回数をセット
						results[i].setSyrSuu(number[0]+"");
						flg = true;
					}
					if(syrkbn.equals(PrsOrderForm.PRSHACHU)){
						//プレス発注情報をセットしなおす
						this.setPrsHacJoho(fmVOs[i]);
					}else if(syrkbn.equals(PrsOrderForm.FUKHACHU)){
						//副資材発注情報をセットしなおす
						this.setFukHacJoho(fmVOs[i]);
					}
					//チェックボックスをクリア
					fmVOs[i].setCheck_prs1(false);
				}
			}
		}finally{
			if(fmDao != null){
				if(!endTransaction ){//exception でcommit or rollback できなかったときは ここで rollback
					try{
						fmDao.rollback();
					}catch(SQLException sqle3){
						SystemException se = new SystemException(sqle3);
						se.printStackTrace();
					}
				}
				try{
					fmDao.close();
				}catch(SQLException sqle2){
					SystemException se = new SystemException(sqle2);
					se.printStackTrace();
				}
			}
		}
		return results;
		
	}

	/**
	 * 副資材マスター検索（副資材コードプルダウンリストの取得）
	 * @param daiKaiSkbCod
	 * @param kaiSkbCod
	 * @return fukSziList
	 * @throws SQLException
	 */
	public ArrayList doFukSziList(String daiKaiSkbCod, String kaiSkbCod)
		throws SQLException{
		
		PrsOrderDAO fmDao = null;
		ArrayList fukSziList = null;

		try{
			fmDao = new PrsOrderDAO();

			//副資材マスター検索
			fukSziList = fmDao.getFukSziList(daiKaiSkbCod,kaiSkbCod);

		}finally{
			if(fmDao != null){
				try{
					fmDao.close();
				}catch(SQLException sqle2){
					SystemException se = new SystemException(sqle2);
					se.printStackTrace();
				}
			}
		}
		return fukSziList;
		
	}
	
//------------------------------------------------------------------------------------
	
	/** プレス発注情報をセットしなおす */
	public void setPrsHacJoho(PrsOrderVO fmVO){
		fmVO.setPrsHacSuu2(new String(fmVO.getPrsHacSuu1()));
		fmVO.setPrsHacNyo2(new String(fmVO.getPrsHacNyo1()));
		fmVO.setPrsHacNki2(new String(fmVO.getPrsHacNki1()));
		fmVO.setPrsKbn2(new String(fmVO.getPrsKbn1()));
		fmVO.setPrsHacSuu1(fmVO.getPrsHacSuu());
		fmVO.setPrsHacNyo1("0");
		fmVO.setPrsHacNki1(fmVO.getPrsNki()+"");
		fmVO.setPrsKbn1(fmVO.getKbn());
		
	}
	/** 副資材発注情報をセットしなおす */
	public void setFukHacJoho(PrsOrderVO fmVO){
		fmVO.setFukHacSuu2(new String(fmVO.getFukHacSuu1()));
		fmVO.setFukHacNki2(new String(fmVO.getFukHacNki1()));
		fmVO.setFukKbn2(new String(fmVO.getFukKbn1()));
		fmVO.setFukHacSuu1(fmVO.getPrsHacSuu());
		fmVO.setFukHacNki1(fmVO.getPrsNki()+"");
		fmVO.setFukKbn1(fmVO.getKbn());
	}
	
	/** 
	 * %タカセ%または%ＪＡＲＥＤ% → 直送区分 = "0" それ以外 → 直送区分 = "1"
	 * 副資材発注のとき → 直送区分 = "0"
	 */
	private final String getCykKbn(String str,String syrkbn,String bshCod){
		if(syrkbn.equals(PrsOrderForm.FUKHACHU)){
			return	"0";
		}
		return DataFormatUtils.getCykKbn(str, bshCod);
	}


}

