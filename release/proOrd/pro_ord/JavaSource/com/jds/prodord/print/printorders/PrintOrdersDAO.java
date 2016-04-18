/**
* PrintOrdersDAO  ����������f�[�^�A�N�Z�X�I�u�W�F�N�g�N���X
*
*	�쐬��    2003/04/18
*	�쐬��    �i�m�h�h�j���c �Ĕ�
* �����T�v    �������f�[�^(FTBHAC01)�A��Ѓ}�X�^�[(FTBKAI01)�ɃA�N�Z�X����N���X�B
* 
*		2003/07/16�i�m�h�h�j���c �Ĕ�
* 			�E�����ލ݌Ƀ}�X�^�[�������A���������ɕ��ރR�[�h�ǉ��E�����ރR�[�h�폜
* 		2003/07/24�i�m�h�h�j���c �Ĕ�
* 			�E�����ލ݌ɐ��̎擾���@��ύX�iFTBHIN12.FUKSZISUU �� FTBHAC01.FUKZAISUU�j
*		2003/08/26�i�m�h�h�j���c �Ĕ�
* 			�E�V�i�ԃ}�X�^�[(HIN02)�Ή� �ǉ�
* 		2004/03/03�i�m�h�h�j�X
* 			�E�`�Ԗ��̃}�X�^��DAIKAISKBCOD��VAP��CR�œ����Ă���e���Ⴄ����...�C��
* 		2004/03/29�i�m�h�h�j�X
* 			�E�i�ԃ}�X�^�̃e�[�u�����ύX
* 		2004/04/21�i�m�h�h�j�X
* 			�E�ō��艿�\���ɔ����C��
* 		2005/05/19�i�m�h�h�j�g�c
* 			�E��Ѓ}�X�^�[�������A��Ж������Q(KAINMKJ2)�擾�̒ǉ�
* 		2005/06/28�i�m�h�h�j�g�c
* 			�E�y�[�W���C�A�E�g���擾���郁�\�b�h�iK�Зp�j�ǉ� 
* 		2005/08/30�i�m�h�h�j�g�c
* 			�E�������\�����ځA�X�V������E���z�̒ǉ��iVAP�БΉ��j
* 			�E���������E�������f�[�^�̍X�V�i�����`�[���s�p�j��ǉ�
*		2007/02/13�i�m�h�h�j���c �Ĕ�
* 			�EHIN02�̕i�Ԃ̏ꍇ�A�`�Ԗ��̂��o�͂���Ȃ��Ȃ��Ă����̂��C��
*		2007/10/03�i�m�h�h�j
* 			�E���ꔭ����̏ꍇ�A����������ڽ / �����ނ����݂��Ă����̂��C���i�������𕪂���j
*		2007/12/25�i�m�h�h�j�c��
* 			�E�}�j���A�������w���ꊇ�\�t�@���l�Q�ǉ��Ή�
*		2008/03/10�i�m�h�h�j�c��
* 			�E�P���ǉ��Ή�
*		2014/02/20�i�m�h�h�j�X
* 			�E�Ŕ��Ή�
* */

package com.jds.prodord.print.printorders;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.jds.prodord.common.CommonConst;
import com.jds.prodord.common.CommonDAO;
import com.jds.prodord.common.DataFormatUtils;
import com.jds.prodord.common.QueryUtils;
import com.jds.prodord.common.StringUtils;


public class PrintOrdersDAO extends CommonDAO{
	
	
	public PrintOrdersDAO() throws SQLException{
    	super();
	}	
	
	
//**�������f�[�^����*********************************************************************************

	public PrintOrdersVO[] findHac01(
		PrintOrdersQueryVO poQueryVO,
		int date,
		String hacSyoPtn)
		throws SQLException {
				
		 String sql = "SELECT" +
					 	" HAC01.KAISKBCOD," +
					 	" HACSYOBNG," +
					 	" HACSYODTE," +
					 	" SYRSUU," +
					 	" SEQNO," +
					 	" HACCOD," +
					 	" SIRHACNM," +
					 	" SINKYUKBN," +
					 	" GYOBNG," + 
						" HAC01.KIGBNG," +
						" HAC01.TITKJ," +
						" PRSFUKSZICOD," +
						" FUKMEIKJ," +
						" HAC01.SETSUU," +
						" HACSUU," +
						" NKI," +
						" NHNMEIKJ," +
						" BUNCOD,  (CASE WHEN BUNCOD<>'0' THEN '1' ELSE BUNCOD END) PRSFUKSGN ," +
						" CMT," +
						" FUKZAISUU, " + 
						" RRKTBL," +
						" TAN, " +						
						" BIKOU2, " +						
						" TAN2, " +						
						" VALUE(HIN01.DAIKAISKBCOD,VALUE(HIN02.DAIKAISKBCOD,'')) HINFLG," +
						" VALUE(HIN01.HJIHNB,VALUE(HIN02.HJIHNB,'')) AS HJIHNB," +
						" VALUE(HIN01.KETCOD,VALUE(HIN02.KETCOD,'')) AS KETCOD," +
						" VALUE(HIN01.ARTKJ,VALUE(HIN02.ARTKJ,G'')) AS ARTKJ," +
						" VALUE(HIN01.ZEIKMITKA,HIN02.ZEIKMITKA) AS ZEIKMITKA," +
						" VALUE(HIN01.TKA,HIN02.TKA) AS TKA," +	//VAP�̂ݎg�p
						" VALUE(HIN01.HBIDTE,HIN02.HBIDTE) AS HBIDTE," +
						" VALUE(MST06.DAIKAISKBCOD, '') MST06FLG," +
						" VALUE(MST06.KETNMKJ2,G'') AS KETNMKJ2";
						
			sql += " FROM" +
						" FTBHAC01 HAC01";
	
			sql += " LEFT OUTER JOIN FTBHIN01 HIN01 ON" +
						" HAC01.DAIKAISKBCOD = HIN01.DAIKAISKBCOD AND" +
						" HAC01.KAISKBCOD = HIN01.KAISKBCOD AND" +
						" HAC01.KIGBNG = HIN01.KIGBNG";
									
			sql += " LEFT OUTER JOIN FTBHIN02 HIN02 ON" +
						" HAC01.DAIKAISKBCOD = HIN02.DAIKAISKBCOD AND" +
						" HAC01.KAISKBCOD = HIN02.KAISKBCOD AND" +
						" HAC01.KIGBNG = HIN02.KIGBNG";
						   
			sql += " LEFT OUTER JOIN FTBMST06 MST06 ON" +
						" VALUE(HIN01.KETCOD, HIN02.KETCOD) = MST06.KETCOD AND" +
						//MST06��DAIKAISKBCOD�̓��[�U�[�̉��
						" MST06.DAIKAISKBCOD = '" + poQueryVO.getKaiSkbCod() + "'";
						
			sql += " WHERE" +
						" HAC01.DAIKAISKBCOD = '" + poQueryVO.getDaiKaiSkbCod() + "'";
								
				//��Đ��Ɖ�(IkkatsuRefer)�E�v���X�����ޔ���(PrsOrder)��ʂ���
				if (poQueryVO.getPartOfQuery_arr().size() == 0) {
					sql += " AND HAC01.HACSYODTE = " + date
						+  " AND HAC01.SYRSUU = '" + poQueryVO.getSyrSuu() + "'";
					//��Ў��ʃR�[�h
					sql += " AND HAC01.KAISKBCOD "
						+ QueryUtils.editArrayToQuery(poQueryVO.getKaiSkbCod_arr());
				//���������Ɖ�(OrderHistory)�E�������o�͎w��(IndicatePrint)��ʂ���	
				} else {
					sql += poQueryVO.getPartOfQuery("HAC01");
				}


		//2007/10/03
		//�v���X������ނŔ������𕪂���Ή��ŁA
		// (CASE WHEN BUNCOD<>'0' THEN '1' ELSE BUNCOD END) PRSFUKSGN ��ǉ��������A
		// ���=K�̏ꍇ�A��ڽ�����݂̂Ȃ̂ŁA�C���Ȃ�

		if(hacSyoPtn.equals(CommonConst.PTN03))
			sql  += " ORDER BY HACCOD, SINKYUKBN, HACSYOBNG, GYOBNG, SYRSUU, SEQNO";
		else
			sql  += " ORDER BY HACCOD, PRSFUKSGN, HACSYOBNG, GYOBNG, SYRSUU, SEQNO";

		ArrayList finded_Arr = new ArrayList();
		ResultSet rs = null;
		Statement stmt = null;
		try{
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while(rs.next()){
				PrintOrdersVO fmVO = new PrintOrdersVO();
				
				fmVO.setDaiKaiSkbCod(poQueryVO.getDaiKaiSkbCod());
				fmVO.setKaiSkbCod(rs.getString("KAISKBCOD").trim());
				fmVO.setHacSyoDte(rs.getString("HACSYODTE").trim());
				fmVO.setHacSyoBng(rs.getString("HACSYOBNG").trim());
				fmVO.setSyrSuu(rs.getString("SYRSUU").trim());
				fmVO.setSeqNo(rs.getString("SEQNO").trim());
				fmVO.setHacCod(rs.getString("HACCOD").trim());
				fmVO.setSirHacNm(StringUtils.removeSuffix(rs.getString("SIRHACNM"),"�@"));
				fmVO.setSinKyuKbn(rs.getString("SINKYUKBN").trim());
				fmVO.setGyoBng(rs.getString("GYOBNG").trim());
				fmVO.setKigBng(rs.getString("KIGBNG").trim());
				fmVO.setTitKj(StringUtils.removeSuffix(rs.getString("TITKJ"),"�@"));
				fmVO.setPrsFukSziCod(rs.getString("PRSFUKSZICOD").trim());
				fmVO.setFukMeiKj(StringUtils.removeSuffix(rs.getString("FUKMEIKJ"),"�@"));
				fmVO.setSetSuu(rs.getString("SETSUU").trim());
				fmVO.setHacSuu(rs.getString("HACSUU").trim());
				fmVO.setNki(rs.getString("NKI").trim());
				fmVO.setNhnMeiKj(StringUtils.removeSuffix(rs.getString("NHNMEIKJ"),"�@"));
				fmVO.setBunCod(rs.getString("BUNCOD").trim());
				fmVO.setComment(StringUtils.removeSuffix(rs.getString("CMT").trim(),"�@"));	
				fmVO.setFukSziSuu(rs.getString("FUKZAISUU").trim());//2003/07/24 add
				fmVO.setRrkDte(rs.getString("RRKTBL").trim());	//2005/08/29 add
				if(poQueryVO.getDaiKaiSkbCod().equals(CommonConst.KAICOD_FX)){
					fmVO.setKin(rs.getString("TAN").trim());
				}else{
					fmVO.setKin(String.valueOf(rs.getInt("TAN")));
				}
				fmVO.setBiKou2(rs.getString("BIKOU2").trim());
				fmVO.setTan2(rs.getString("TAN2").trim());
				if(!rs.getString("HINFLG").equals("")){
					fmVO.setHjiHnb(rs.getString("HJIHNB").trim());
					fmVO.setKetCod(rs.getString("KETCOD").trim());
					fmVO.setArtKj(StringUtils.removeSuffix(rs.getString("ARTKJ"),"�@"));		
					String zeiKmiTka = rs.getString("ZEIKMITKA");
					fmVO.setZikTik(zeiKmiTka.substring(0, zeiKmiTka.indexOf(".")));//2004.04.21 add
					String tka = rs.getString("TKA");
					fmVO.setTka(tka.substring(0, tka.indexOf(".")));//2014.02.19 add
					
					fmVO.setHbiDte(rs.getString("HBIDTE")); //2005.06.14 add
				}
				if(!rs.getString("MST06FLG").equals("")){
					fmVO.setKetNm(StringUtils.removeSuffix(rs.getString("KETNMKJ2"),"�@"));	
				}
				finded_Arr.add(fmVO);
			}

		}finally{
			if(rs != null)
				rs.close();
			if(stmt != null)
				stmt.close();
		}	
		return (PrintOrdersVO[])finded_Arr.toArray(new PrintOrdersVO[finded_Arr.size()]);
	}
	
	

//**��Ѓ}�X�^�[����***********************************************************************
	
	public void findKai01(PrintOrdersVO fmVO, com.jds.prodord.register.LogonForm logonForm)throws SQLException{

		String sql = "SELECT KAINMKJ,KAIYBNNO,KAIADR1KJ,KAIADR2KJ,KAITELNO,KAIFAXBNG,"
					+"KAILOGO,KAINMKJ2 FROM FTBKAI01"
					+ " WHERE DAIKAISKBCOD = ? AND KAISKBCOD = ? AND USRID = ? AND PWD = ?";

		ResultSet rs = null;	
		PreparedStatement ps = null;	
		try{
			ps = conn.prepareStatement(sql);
			
			ps.setString(1,fmVO.getDaiKaiSkbCod());
			ps.setString(2,logonForm.getKaiskbcod());
			ps.setString(3,logonForm.getUser());
			ps.setString(4,logonForm.getPwd());
			
			rs = ps.executeQuery();
			
			while(rs.next()){
				fmVO.setKaiNmKj(StringUtils.removeSuffix(rs.getString("KAINMKJ"),"�@"));
				fmVO.setKaiYbnNo(rs.getString("KAIYBNNO").trim());
				fmVO.setKaiAdr1Kj(StringUtils.removeSuffix(rs.getString("KAIADR1KJ"),"�@"));	
				fmVO.setKaiAdr2Kj(StringUtils.removeSuffix(rs.getString("KAIADR2KJ"),"�@"));
				fmVO.setKaiTelNo(rs.getString("KAITELNO").trim());	
				fmVO.setKaiFaxBng(rs.getString("KAIFAXBNG").trim());
				fmVO.setKaiLogoSrc(rs.getString("KAILOGO").trim());
				fmVO.setKaiNmKj2(StringUtils.removeSuffix(rs.getString("KAINMKJ2"),"�@"));//2005/05/19 add
			}

		}finally{
			if(rs != null)
				rs.close();
			if(ps != null)
				ps.close();
		}

	}	
	
	//TODO ��Вǉ��Ή�(�p�^�[���ɉ��������\�b�h�ǉ�)
	/**
	 * �y�[�W���C�A�E�g���擾���郁�\�b�h(�p�^�[��01)<br>
	 * �i�y�[�W���C�A�E�g�F�����L�[�����s�����������݂��邩�A�L�[���ƂɌ�����ArrayList�Ɋi�[����j
	 * */
	public ArrayList getPageLayout01(PrintOrdersQueryVO poQueryVO,int date)throws SQLException{
					
		String sql = "SELECT COUNT(*) AS COUNT,HACCOD "
					+ ",(CASE WHEN BUNCOD<>'0' THEN '1' ELSE BUNCOD END) PRSFUKSGN "
					+ " FROM FTBHAC01 "
					+ " WHERE DAIKAISKBCOD = '" + poQueryVO.getDaiKaiSkbCod() + "'";
					
					if(poQueryVO.getPartOfQuery_arr().size() == 0){
						sql += " AND FTBHAC01.HACSYODTE = " + date
						+ " AND SYRSUU = '" + poQueryVO.getSyrSuu() + "'";
						
						//��Ў��ʃR�[�h
				        for(int i=0; i<poQueryVO.getKaiSkbCod_arr().size(); i++){
				          if(i == 0)
				          	sql +=  " AND KAISKBCOD IN ('";
				          sql += poQueryVO.getKaiSkbCod_arr().get(i).toString();
				          if(i == poQueryVO.getKaiSkbCod_arr().size() - 1){
				            sql  += "')";
				          }else{
				            sql  += "','";
				          }
				        }
						
					}else{
						sql += poQueryVO.getPartOfQuery("FTBHAC01");
					}

		sql  += " GROUP BY HACCOD,(CASE WHEN BUNCOD<>'0' THEN '1' ELSE BUNCOD END) , HACSYOBNG "
			 +  " ORDER BY HACCOD,PRSFUKSGN,HACSYOBNG";

		ArrayList layout_Arr = new ArrayList();
		ResultSet rs = null;
		Statement stmt = null;
		try{
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()){
				PrintOrdersBreakKey bk = new PrintOrdersBreakKey();
				bk.setHacCod(rs.getString("HACCOD"));
				bk.setCount(rs.getInt("COUNT"));
				layout_Arr.add(bk);
			}
		}finally{
			if(rs != null)
				rs.close();
			if(stmt != null)
				stmt.close();
		}	
		return layout_Arr;
	}
	
	/**
	 * �y�[�W���C�A�E�g���擾���郁�\�b�h(�p�^�[��02)<br>
	 * �i�y�[�W���C�A�E�g�F�����L�[�����s�����������݂��邩�A�L�[���ƂɌ�����ArrayList�Ɋi�[����j
	 * */
	public ArrayList getPageLayout02(PrintOrdersQueryVO poQueryVO,int date)throws SQLException{
		//�p�^�[��01�Ɠ���
		return getPageLayout01(poQueryVO, date);
		
	}

	/**
	 * �y�[�W���C�A�E�g���擾���郁�\�b�h(�p�^�[��03)<br>
	 * �i�y�[�W���C�A�E�g�F�����L�[�����s�����������݂��邩�A�L�[���ƂɌ�����ArrayList�Ɋi�[����j.<br>
	 * @param poQueryVO
	 * @param date
	 * @return ArrayList
	 * @throws SQLException
	 */
	public ArrayList getPageLayout03(PrintOrdersQueryVO poQueryVO,int date)throws SQLException{

		 String sql = "SELECT COUNT(*) AS COUNT,HACCOD,SINKYUKBN FROM FTBHAC01"
					+ " WHERE DAIKAISKBCOD = '" + poQueryVO.getDaiKaiSkbCod() + "'";
					
			if(poQueryVO.getPartOfQuery_arr().size() == 0){
				sql += " AND HACSYODTE = " + date
					+  " AND SYRSUU = '" + poQueryVO.getSyrSuu() + "'";
				
				//��Ў��ʃR�[�h
				for(int i=0; i<poQueryVO.getKaiSkbCod_arr().size(); i++){
					if(i == 0)
						sql +=  " AND KAISKBCOD IN ('";
					sql += poQueryVO.getKaiSkbCod_arr().get(i).toString();
					if(i == poQueryVO.getKaiSkbCod_arr().size() - 1){
						sql  += "')";
					}else{
						sql  += "','";
					}
				}
				
			}else{
				sql += poQueryVO.getPartOfQuery("FTBHAC01");
			}

		sql  += " GROUP BY HACCOD,SINKYUKBN,HACSYOBNG ORDER BY HACCOD,SINKYUKBN,HACSYOBNG";
		
		ArrayList layout_Arr = new ArrayList();
		ResultSet rs = null;
		Statement stmt = null;
		try{
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()){
				PrintOrdersBreakKey bk = new PrintOrdersBreakKey();
				bk.setHacCod(rs.getString("HACCOD"));
				bk.setCount(rs.getInt("COUNT"));
				bk.setSinKyuKbn(rs.getString("SINKYUKBN"));
				layout_Arr.add(bk);
			}
		}finally{
			if(rs != null)
				rs.close();
			if(stmt != null)
				stmt.close();
		}	
		return layout_Arr;
	}
	
	/**
	 * �y�[�W���C�A�E�g���擾���郁�\�b�h(�p�^�[��04)<br>
	 * �i�y�[�W���C�A�E�g�F�����L�[�����s�����������݂��邩�A�L�[���ƂɌ�����ArrayList�Ɋi�[����j
	 * */
	public ArrayList getPageLayout04(PrintOrdersQueryVO poQueryVO,int date)throws SQLException{
		//�p�^�[��01�Ɠ���
		return getPageLayout01(poQueryVO, date);
		
	}

	/**
	 * �y�[�W���C�A�E�g���擾���郁�\�b�h(�p�^�[��05)<br>
	 * �i�y�[�W���C�A�E�g�F�����L�[�����s�����������݂��邩�A�L�[���ƂɌ�����ArrayList�Ɋi�[����j
	 * */
	public ArrayList getPageLayout05(PrintOrdersQueryVO poQueryVO,int date)throws SQLException{
		//�p�^�[��01�Ɠ���
		return getPageLayout01(poQueryVO, date);
		
	}
	
	//���������e�[�u���X�V*******************************************************************

	public void updateHac02(PrintOrdersVO fmVO,int upddte,int updjkk)throws SQLException{
		String sql = "UPDATE FTBHAC02 SET HACSYOBNG = ?, GYOBNG = ?, UPDKBN = ?, UPDDTE = ?, UPDJKK = ?,"
				   + "RRKTBL = ?"
					 + " WHERE DAIKAISKBCOD = ? AND KAISKBCOD = ?"
					 + " AND KIGBNG = ? AND HACSYODTE = ? AND SYRSUU = ? AND SEQNO = ?";

		PreparedStatement ps = null;
		try{
			ps = conn.prepareStatement(sql);
	
			ps.setString(1,DataFormatUtils.formatHacSyoBng(fmVO.getHacSyoBng()));
			ps.setString(2,DataFormatUtils.formatGyoBng(fmVO.getGyoBng()));
			ps.setString(3,"U");
			ps.setInt(4,upddte);
			ps.setInt(5,updjkk);
			ps.setString(6,fmVO.getRrkDte()); // RRKTBL 2005/08/30 add
			ps.setString(7,fmVO.getDaiKaiSkbCod());
			ps.setString(8,fmVO.getKaiSkbCod());
			ps.setString(9,fmVO.getKigBng());
			ps.setString(10,fmVO.getHacSyoDte());
			ps.setString(11,fmVO.getSyrSuu());
			ps.setString(12,fmVO.getSeqNo());
			
			ps.executeUpdate();

		}finally{
			if(ps != null)
				ps.close();
		}			
	}
	
	/**
	 * ���������e�[�u���X�V�i�����`�[���s�p�j
	 * @param fmVO
	 * @param upddte
	 * @param updjkk
	 * @param rrkTbl
	 * @throws SQLException
	 */
	public void agnUpdateHac02(PrintOrdersVO fmVO,int upddte,int updjkk)throws SQLException{
		String sql = "UPDATE FTBHAC02 SET UPDDTE = ?, UPDJKK = ?, RRKTBL = ?"
					 + " WHERE DAIKAISKBCOD = ? AND KAISKBCOD = ?"
					 + " AND KIGBNG = ? AND HACSYODTE = ? AND SYRSUU = ? AND SEQNO = ?";

		PreparedStatement ps = null;
		try{
			ps = conn.prepareStatement(sql);
	
			ps.setInt(1,upddte);
			ps.setInt(2,updjkk);
			ps.setString(3,fmVO.getRrkDte());
			ps.setString(4,fmVO.getDaiKaiSkbCod());
			ps.setString(5,fmVO.getKaiSkbCod());
			ps.setString(6,fmVO.getKigBng());
			ps.setString(7,fmVO.getHacSyoDte());
			ps.setString(8,fmVO.getSyrSuu());
			ps.setString(9,fmVO.getSeqNo());
			
			ps.executeUpdate();

		}finally{
			if(ps != null)
				ps.close();
		}			
	}

	
	//�������f�[�^�X�V*******************************************************************

	public void updateHac01(PrintOrdersVO fmVO,int upddte,int updjkk)throws SQLException{
		String sql = "UPDATE FTBHAC01 SET HACSYOBNG = ?, GYOBNG = ?, UPDKBN = ?, UPDDTE = ?, UPDJKK = ?,"
				   + "RRKTBL = ?"
					 + " WHERE DAIKAISKBCOD = ? AND KAISKBCOD = ?"
					 + " AND KIGBNG = ? AND HACSYODTE = ? AND SYRSUU = ? AND SEQNO = ?";

		PreparedStatement ps = null;
		try{
			ps = conn.prepareStatement(sql);
	
			ps.setString(1,DataFormatUtils.formatHacSyoBng(fmVO.getHacSyoBng()));
			ps.setString(2,DataFormatUtils.formatGyoBng(fmVO.getGyoBng()));
			ps.setString(3,"U");
			ps.setInt(4,upddte);
			ps.setInt(5,updjkk);
			ps.setString(6,fmVO.getRrkDte()); // RRKTBL 2005/08/30 add
			ps.setString(7,fmVO.getDaiKaiSkbCod());
			ps.setString(8,fmVO.getKaiSkbCod());
			ps.setString(9,fmVO.getKigBng());
			ps.setString(10,fmVO.getHacSyoDte());
			ps.setString(11,fmVO.getSyrSuu());
			ps.setString(12,fmVO.getSeqNo());

			ps.executeUpdate();

		}finally{
			if(ps != null)
				ps.close();
		}			
	}

	/**
	 * �������f�[�^�X�V�i�����`�[���s�p�j
	 * @param fmVO
	 * @param upddte
	 * @param updjkk
	 * @param rrkTbl
	 * @throws SQLException
	 */
	public void agnUpdateHac01(PrintOrdersVO fmVO,int upddte,int updjkk)throws SQLException{
		String sql = "UPDATE FTBHAC01 SET UPDDTE = ?, UPDJKK = ?, RRKTBL = ?"
					 + " WHERE DAIKAISKBCOD = ? AND KAISKBCOD = ?"
					 + " AND KIGBNG = ? AND HACSYODTE = ? AND SYRSUU = ? AND SEQNO = ?";

		PreparedStatement ps = null;
		try{
			ps = conn.prepareStatement(sql);
	
			ps.setInt(1,upddte);
			ps.setInt(2,updjkk);
			ps.setString(3,fmVO.getRrkDte());
			ps.setString(4,fmVO.getDaiKaiSkbCod());
			ps.setString(5,fmVO.getKaiSkbCod());
			ps.setString(6,fmVO.getKigBng());
			ps.setString(7,fmVO.getHacSyoDte());
			ps.setString(8,fmVO.getSyrSuu());
			ps.setString(9,fmVO.getSeqNo());

			ps.executeUpdate();

		}finally{
			if(ps != null)
				ps.close();
		}			
	}


}

