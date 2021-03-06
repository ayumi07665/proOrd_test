/**
* PrsOrderDelegate  vXEÞ­æÊ ÀsNX
*
*	ì¬ú    2003/04/28
*	ì¬Ò    imhhjªc Äü
* Tv    ðÀs·éNXB
*
*	[ÏXð]
*       2003/04/28 imhhjgc hq
* 			Eu­w¦vu`[­svÌÇÁB
*		2003/05/16 imhhjªc Äü
* 			E­æ¶Ý`FbNÉ­æ¼ÌÌæ¾ÇÁB
* 		2003/06/18 imhhjªc Äü
* 			EO100100ÎÇÁB
*			EÞ­@\ÇÁB
*		2003/07/02 imhhjgc hq
* 			EÞ­ÌÆ«A¼æª'0'ÅèÇÁB
* 		2003/07/28imhhjªc Äü
* 			E­w¦E`[­sAvX­ª}CiX·éOÌÞÝÉðæ¾
* 			  ­ðE­f[^ÌÞÝÉÉZbg
* 		2003/08/06imhhjªc Äü 
* 			E­w¦A`[­sAÝÉe[uÌXVÇÁ
* 		2004/04/02 (NII) X
* 			EÞ­ÌC³(ªÞR[hP¡¶ÝÉÎ)
* 		2005/05/26imhhjgc
* 			E[iæ¼ª'i`qdc'ÌêA¼æª"0"ÌC³
* 		2005/09/14imhhjgc
* 			EÞ­æÊAÞR[hðv_EÚÉÏXiVAPÐÎj
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
	 * 		­w¦
	 * 
	 *********************************************************************************************/
	public PrsOrderResult[] doHacShiji(PrsOrderVO[] fmVOs, String syrkbn,String bshCod)throws SQLException{
		PrsOrderDAO fmDao = null;
	
		PrsOrderResult[] results = new PrsOrderResult[fmVOs.length];

		boolean endTransaction = false;
		try{
			fmDao = new PrsOrderDAO();
			fmDao.setAutoCommit(false); //DBÌgUNVJn


			//XVú
			DateUtils dateUtils = new DateUtils();
			int upddte = dateUtils.getDate6Int();
			int updjkk = dateUtils.getTime6Int();

			boolean error = false;
			int[] number = null;
			
			for(int i = 0;i < fmVOs.length;i++){
				if(fmVOs[i] == null)
					continue;
					
				//­æ¶Ý`FbN
				fmDao.hasHacSaki(fmVOs[i]);
			    if(fmVOs[i].getHacNm().equals(PrsOrderDAO.NOT_EXIST)){			    	
				    results[i] = new PrsOrderResult(fmVOs[i],false,HACSAKI_NOT_EXIST);
				    fmVOs[i].setHacNm("");
				    error = true;
					continue;
			    }
			}
		 

			//G[ÌR[hªÈ¯êÎinsertÀs
			if(!error){
				//ñÌÌÔ
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

					seqno++;//fmVOs[i] == nullÅÍÈ©Á½ç
					try{
						//vX­ª}CiX·éOÌÞÝÉðæ¾ 2003/07/28 add
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
						//­ðÌo^
						fmDao.insertHac02(fmVOs[i],upddte,updjkk,syrsuu,StringUtils.lpad(seqno+"",3,"0"),syrzmisgn,cykKbn,syrkbn);
						fmVOs[i].setChoksoKbn(cykKbn);//¼æªðZbg
						
						if(syrkbn.equals(PrsOrderForm.PRSHACHU)){
							//ÞÝÉÌ¶Ý`FbNEo^(¡)
							orderUtils.insertHin12List(
								fmVOs[i].getDaiKaiSkbCod(),
								fmVOs[i].getKaiSkbCod(),
								fmVOs[i].getKigBng(),
								fmVOs[i].getSmpKbn(),
								fmVOs[i].getFukSziCod_arr(),
								fmVOs[i].getBunCod_arr(),
								upddte,
								updjkk);
							//ÞÝÉðvX­ª}CiX·é
							orderUtils.updateHin12(fmVOs[i].getDaiKaiSkbCod(),fmVOs[i].getKaiSkbCod(),
												   fmVOs[i].getKigBng(),fmVOs[i].getSmpKbn(),(Integer.parseInt(fmVOs[i].getPrsHacSuu()) * -1),upddte,updjkk);
							//ÝÉe[uXV 2003/08/06 add
							if(fmVOs[i].getSmpKbn().equals(""))
								orderUtils.updateZai01(fmVOs[i].getDaiKaiSkbCod(),fmVOs[i].getKigBng(),
											   	   Integer.parseInt(fmVOs[i].getPrsHacSuu()),upddte,updjkk,OrderUtils.PRSHAC);
											   	   
							//IkkatsuReferÖn· ¢­Ï£ TC
							fmVOs[i].setSyrZmiSgn_prs(IkkatsuReferDAO.HACZMI);

						}else if(syrkbn.equals(PrsOrderForm.FUKHACHU)){
							//HIN12ª¶ÝµÈ©Á½ç 2003/09/22 add
							if(orderUtils.findHin12(fmVOs[i].getDaiKaiSkbCod(),fmVOs[i].getKaiSkbCod(),fmVOs[i].getKigBng(),fmVOs[i].getBunCod(),fmVOs[i].getFukSziCod(),fmVOs[i].getSmpKbn()) == null)
								orderUtils.insertHin12(fmVOs[i].getDaiKaiSkbCod(),fmVOs[i].getKaiSkbCod(),fmVOs[i].getKigBng(),fmVOs[i].getFukSziCod(),0,fmVOs[i].getBunCod(),fmVOs[i].getSmpKbn(),upddte,updjkk);
							//ÝÉe[uXV 2003/08/06 add
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
					//³íÉ¬÷
					results[i] = new PrsOrderResult(fmVOs[i],true,"");
				} 
			}
			//G[Èç[obN
			if(error){
//System.out.println("PrsOrderDelegate --> rollback");
				fmDao.rollback();
				endTransaction = true;
			//G[ªÈ¯êÎR~bg
			}else{
//System.out.println("PrsOrderDelegate --> commit");
				fmDao.commit();
				endTransaction = true;
				
				for(int i = 0;i < fmVOs.length;i++){
					if(fmVOs[i] == null)
						continue;
					if(syrkbn.equals(PrsOrderForm.PRSHACHU)){
						//vX­îñðZbgµÈ¨·
						this.setPrsHacJoho(fmVOs[i]);
					}else if(syrkbn.equals(PrsOrderForm.FUKHACHU)){
						//Þ­îñðZbgµÈ¨·
						this.setFukHacJoho(fmVOs[i]);
					}
					//`FbN{bNXðNA
					fmVOs[i].setCheck_prs1(false);
				}
			}
		}finally{
			if(fmDao != null){
				if(!endTransaction ){//exception Åcommit or rollback Å«È©Á½Æ«Í ±±Å rollback
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
	 * 		`[­s
	 * 
	 ************************************************************************************************/
	public PrsOrderResult[] doDnpHakkou(PrsOrderVO[] fmVOs, String syrkbn,String bshCod)throws SQLException{
		PrsOrderDAO fmDao = null;
	
		PrsOrderResult[] results = new PrsOrderResult[fmVOs.length];

		boolean endTransaction = false;
		try{
			fmDao = new PrsOrderDAO();
			fmDao.setAutoCommit(false); //DBÌgUNVJn


			//XVú
			DateUtils dateUtils = new DateUtils();
			int upddte = dateUtils.getDate6Int();
			int updjkk = dateUtils.getTime6Int();

			boolean error = false;
			int[] number = null;

			for(int i = 0;i < fmVOs.length;i++){
				if(fmVOs[i] == null)
					continue;
					
				//­æ¶Ý`FbN
				fmDao.hasHacSaki(fmVOs[i]);
			    if(fmVOs[i].getHacNm().equals(PrsOrderDAO.NOT_EXIST)){			    	
				    results[i] = new PrsOrderResult(fmVOs[i],false,HACSAKI_NOT_EXIST);
				    fmVOs[i].setHacNm("");
				    error = true;
					continue;
			    }
			}

			//G[ÌR[hªÈ¯êÎinsertÀs
			if(!error){
				//ñÌÌÔ
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
						
					seqno++;//fmVOs[i] == nullÅÍÈ©Á½çsÔ++
					try{
						//vX­ª}CiX·éOÌÞÝÉðæ¾ 2003/07/28 add
//						String fukSziSuu = OrderUtils.findHin12(fmVOs[i].getDaiKaiSkbCod(),fmVOs[i].getKaiSkbCod(),fmVOs[i].getKigBng(),"1",fmVOs[i].getFukSziCod(),fmVOs[i].getSmpKbn());
						if(fmVOs[i].getBasedRowFlg().equals("1")){
							String fukSziSuu = orderUtils.findHin12(fmVOs[i].getDaiKaiSkbCod(),fmVOs[i].getKaiSkbCod(),fmVOs[i].getKigBng(),fmVOs[i].getBunCod(),fmVOs[i].getFukSziCod(),fmVOs[i].getSmpKbn());
							fmVOs[i].setFukSziSuu((fukSziSuu == null)? "0" : fukSziSuu);
						}else
							fmVOs[i].setFukSziSuu("0");

						String syrzmisgn = "1";
						String cykKbn = getCykKbn(fmVOs[i].getNhnMei(),syrkbn,bshCod);
						String _seqno = StringUtils.lpad(seqno+"",3,"0");
						//­ðe[uÉ«Ý
						fmDao.insertHac02(fmVOs[i],upddte,updjkk,syrsuu,_seqno,syrzmisgn,cykKbn,syrkbn);
						fmVOs[i].setChoksoKbn(cykKbn);//¼æªðZbg
						//­f[^«Ý
						orderUtils.insertHac01(
							fmVOs[i].getDaiKaiSkbCod(),//ã\ïÐ
							fmVOs[i].getKaiSkbCod(),//ïÐ
							String.valueOf(upddte),//­út
							syrsuu,//ñ
							_seqno,//SEQNO
							0,//f[^ð·tO
							upddte,
							updjkk);
						
						if(syrkbn.equals(PrsOrderForm.PRSHACHU)){
							//ÞÝÉÌ¶Ý`FbNEo^(¡)
							orderUtils.insertHin12List(
								fmVOs[i].getDaiKaiSkbCod(),
								fmVOs[i].getKaiSkbCod(),
								fmVOs[i].getKigBng(),
								fmVOs[i].getSmpKbn(),
								fmVOs[i].getFukSziCod_arr(),
								fmVOs[i].getBunCod_arr(),
								upddte,
								updjkk);
							//ÞÝÉðvX­ª}CiX·é
							orderUtils.updateHin12(fmVOs[i].getDaiKaiSkbCod(),fmVOs[i].getKaiSkbCod(),
												   fmVOs[i].getKigBng(),fmVOs[i].getSmpKbn(),(Integer.parseInt(fmVOs[i].getPrsHacSuu()) * -1),upddte,updjkk);
							//ÝÉe[uXV 2003/08/06 add
							if(fmVOs[i].getSmpKbn().equals(""))
								orderUtils.updateZai01(fmVOs[i].getDaiKaiSkbCod(),fmVOs[i].getKigBng(),
											   	   Integer.parseInt(fmVOs[i].getPrsHacSuu()),upddte,updjkk,OrderUtils.PRSHAC);
							
							//IkkatsuReferÖn· ¢oÍÏ£ TC
							fmVOs[i].setSyrZmiSgn_prs(IkkatsuReferDAO.SYRZMI);
							
						}else if(syrkbn.equals(PrsOrderForm.FUKHACHU)){
							//HIN12ª¶ÝµÈ©Á½ç 2003/09/22 add
							if(orderUtils.findHin12(fmVOs[i].getDaiKaiSkbCod(),fmVOs[i].getKaiSkbCod(),fmVOs[i].getKigBng(),fmVOs[i].getBunCod(),fmVOs[i].getFukSziCod(),fmVOs[i].getSmpKbn()) == null)
								orderUtils.insertHin12(fmVOs[i].getDaiKaiSkbCod(),fmVOs[i].getKaiSkbCod(),fmVOs[i].getKigBng(),fmVOs[i].getFukSziCod(),0,fmVOs[i].getBunCod(),fmVOs[i].getSmpKbn(),upddte,updjkk);
							//ÝÉe[uXV 2003/08/06 add
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
					//³íÉ¬÷
					results[i] = new PrsOrderResult(fmVOs[i],true,"");
				} 
			}
			//G[Èç[obN
			if(error){
				fmDao.rollback();
				endTransaction = true;
			//G[ªÈ¯êÎR~bg
			}else{
				fmDao.commit();
				endTransaction = true;
				boolean flg = false;
				for(int i = 0;i < fmVOs.length;i++){
					if(fmVOs[i] == null)
						continue;
					if(fmVOs[i] != null && !flg){//resultsÉñðZbg
						results[i].setSyrSuu(number[0]+"");
						flg = true;
					}
					if(syrkbn.equals(PrsOrderForm.PRSHACHU)){
						//vX­îñðZbgµÈ¨·
						this.setPrsHacJoho(fmVOs[i]);
					}else if(syrkbn.equals(PrsOrderForm.FUKHACHU)){
						//Þ­îñðZbgµÈ¨·
						this.setFukHacJoho(fmVOs[i]);
					}
					//`FbN{bNXðNA
					fmVOs[i].setCheck_prs1(false);
				}
			}
		}finally{
			if(fmDao != null){
				if(!endTransaction ){//exception Åcommit or rollback Å«È©Á½Æ«Í ±±Å rollback
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
	 * Þ}X^[õiÞR[hv_EXgÌæ¾j
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

			//Þ}X^[õ
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
	
	/** vX­îñðZbgµÈ¨· */
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
	/** Þ­îñðZbgµÈ¨· */
	public void setFukHacJoho(PrsOrderVO fmVO){
		fmVO.setFukHacSuu2(new String(fmVO.getFukHacSuu1()));
		fmVO.setFukHacNki2(new String(fmVO.getFukHacNki1()));
		fmVO.setFukKbn2(new String(fmVO.getFukKbn1()));
		fmVO.setFukHacSuu1(fmVO.getPrsHacSuu());
		fmVO.setFukHacNki1(fmVO.getPrsNki()+"");
		fmVO.setFukKbn1(fmVO.getKbn());
	}
	
	/** 
	 * %^JZ%Ü½Í%i`qdc% ¨ ¼æª = "0" »êÈO ¨ ¼æª = "1"
	 * Þ­ÌÆ« ¨ ¼æª = "0"
	 */
	private final String getCykKbn(String str,String syrkbn,String bshCod){
		if(syrkbn.equals(PrsOrderForm.FUKHACHU)){
			return	"0";
		}
		return DataFormatUtils.getCykKbn(str, bshCod);
	}


}

