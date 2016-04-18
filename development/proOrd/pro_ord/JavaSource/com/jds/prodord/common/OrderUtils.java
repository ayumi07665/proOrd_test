package com.jds.prodord.common;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

public class OrderUtils {
	
	private Connection conn;

	/**
	 * �n���ꂽDAO�̃R�l�N�V���������o���ăZ�b�g���܂��B
	 * @param dao
	 * @throws SQLException
	 */
	public OrderUtils(CommonDAO dao) throws SQLException{
		conn = dao.getConnection();//�n���ꂽDAO��Connection�����o���ăZ�b�g
	}
	
	public static int PRSHAC = 0;
	public static int FUKHAC = 1;
	/**
	 * �݌Ƀe�[�u��(FTBZAI01)�X�V���\�b�h
	 * 
	 * @param daiKaiSkbCod,kigBng,hacSuu,upddte,updjkk,sgn
	 * @throws java.sql.SQLException 
	 */
	public void updateZai01(
		String daiKaiSkbCod,
		String kigBng,
		int hacSuu,
		int upddte,
		int updjkk,
		int sgn)
		throws SQLException {

		Statement stmt = null;
		
		try{
			
			String sql = "UPDATE FTBZAI01 SET ";
				if(sgn == PRSHAC)
					sql += "PRSMNYKEI = PRSMNYKEI + " + hacSuu
						 + ", PRSHACRUI = PRSHACRUI + " + hacSuu
						 + ", FUKZAISUU = FUKZAISUU - " + hacSuu;
				else if(sgn == FUKHAC)
					sql += "FUKMNYKEI = FUKMNYKEI + " + hacSuu
						 + ", FUKHACRUI = FUKHACRUI + " + hacSuu;
						 
				sql += ",UPDKBN = 'U', UPDDTE = " + upddte + ", UPDJKK = " + updjkk
					 + " WHERE KAISKBCOD = '" + daiKaiSkbCod + "'"
					 + " AND KIGBNG = '" + kigBng + "'";
//System.out.println("Zai01 sql�F"+sql);
			
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
			
		}catch(SQLException sqle){
			conn.rollback();
			SystemException se = new SystemException(sqle);
			se.printStackTrace();
			throw sqle;
		}finally{
			if(stmt != null)
				stmt.close();
		}
	}
	
	/**
	 * �����ލ݌Ƀe�[�u��(FTBHIN12)�X�V���\�b�h�@
	 * 
	 * @param daiKaiSkbCod,kaiSkbCod,kigBng,smpKbn,sabun,upddte,updjkk
	 * @throws java.sql.SQLException 
	 */
	public void updateHin12(
		String daiKaiSkbCod,
		String kaiSkbCod,
		String kigBng,
		String smpKbn,
		int sabun,
		int upddte,
		int updjkk)
		throws SQLException {

		Statement stmt = null;
		
		try{
			
			String sql = "UPDATE FTBHIN12 SET FUKSZISUU = FUKSZISUU + " + sabun
					 + ",UPDKBN = 'U',UPDDTE = " + upddte + ",UPDJKK = " + updjkk
					 + " WHERE DAIKAISKBCOD = '" + daiKaiSkbCod + "'"
					 + " AND KAISKBCOD = '" + kaiSkbCod + "'"
					 + " AND KIGBNG = '" + kigBng + "' AND SMPKBN = '" + smpKbn +"'";
//System.out.println("Hin12 sql�F"+sql);
			
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
			
		}catch(SQLException sqle){
			conn.rollback();
			SystemException se = new SystemException(sqle);
			se.printStackTrace();
			throw sqle;
		}finally{
			if(stmt != null)
				stmt.close();
		}
	}
	
	/**
	 * �����ލ݌Ƀe�[�u��(FTBHIN12)�X�V���\�b�h�A
	 * 
	 * @param daiKaiSkbCod,kaiSkbCod,kigBng,smpKbn,fukSziCod,sabun,upddte,updjkk
	 * @throws java.sql.SQLException 
	 */
	public void updateHin12(
		String daiKaiSkbCod,
		String kaiSkbCod,
		String kigBng,
		String smpKbn,
		String fukSziCod,
		int sabun,
		int upddte,
		int updjkk)
		throws SQLException {

		Statement stmt = null;
		
		try{
			
			String sql = "UPDATE FTBHIN12 SET FUKSZISUU = FUKSZISUU + " + sabun
					 + ",UPDKBN = 'U',UPDDTE = " + upddte + ",UPDJKK = " + updjkk
					 + " WHERE DAIKAISKBCOD = '" + daiKaiSkbCod + "'"
					 + " AND KAISKBCOD = '" + kaiSkbCod + "' AND FUKSZICOD = '" + fukSziCod + "'"
					 + " AND KIGBNG = '" + kigBng + "' AND SMPKBN = '" + smpKbn +"'";
//System.out.println("Hin12 sql�F"+sql);
			
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
			
		}catch(SQLException sqle){
			conn.rollback();
			SystemException se = new SystemException(sqle);
			se.printStackTrace();
			throw sqle;
		}finally{
			if(stmt != null)
				stmt.close();
		}
	}
	
	/**
	 * �����ލ݌Ƀe�[�u��(FTBHIN12)�o�^���\�b�h
	 * @param daiKaiSkbCod,kaiSkbCod,kigBng,fukSziCod,hacSuu,bunCod,smpKbn,upddte,updjkk
	 * @throws java.sql.SQLException 
	 */
	public void insertHin12(
		String daiKaiSkbCod,
		String kaiSkbCod,
		String kigBng,
		String fukSziCod,
		int hacSuu,
		String bunCod,
		String smpKbn,
		int upddte,
		int updjkk)
		throws SQLException {
		String sql = "INSERT INTO FTBHIN12 (DAIKAISKBCOD,KAISKBCOD,KIGBNG"
					 + ",FUKSZICOD,FUKSZISUU,UPDKBN,UPDDTE,UPDJKK,BUNCOD,SMPKBN) "
					 + " VALUES (?,?,?,?,?,?,?,?,?,?)";
					 
		PreparedStatement ps = null;
		
		try{

			ps = conn.prepareStatement(sql);
				
			ps.setString(1,daiKaiSkbCod);
			ps.setString(2,kaiSkbCod);
			ps.setString(3,kigBng);
			ps.setString(4,fukSziCod);
			ps.setInt(5,hacSuu);
			ps.setString(6,"A");
			ps.setInt(7,upddte);
			ps.setInt(8,updjkk);
			ps.setString(9,bunCod);
			ps.setString(10,smpKbn);
				
			ps.executeUpdate();
			
		}catch(SQLException sqle){
			conn.rollback();
			SystemException se = new SystemException(sqle);
			se.printStackTrace();
			throw sqle;
		}finally{
			if(ps != null)
				ps.close();
		}		
	}

	/**
	 * �����ލ݌Ƀe�[�u��(FTBHIN12)�������\�b�h
	 * 
	 * @param daiKaiSkbCod,kaiSkbCod,kigBng,bunCod,fukSziCod,smpKbn
	 * @throws java.sql.SQLException 
	 */

	public String findHin12(
		String daiKaiSkbCod,
		String kaiSkbCod,
		String kigBng,
		String bunCod,
		String fukSziCod,
		String smpKbn)
		throws SQLException {

		String sql = "SELECT FUKSZISUU FROM FTBHIN12"
					+ " WHERE DAIKAISKBCOD = ? AND KAISKBCOD = ? AND KIGBNG = ?"
//2005/06/08 del   	+ " AND BUNCOD = ? AND FUKSZICOD = ? AND SMPKBN = ?";
					+ " AND FUKSZICOD = ? AND SMPKBN = ?";
    	
		String fukSziSuu = "";
		ResultSet rs = null;	
		PreparedStatement ps = null;	
		
		try{

			ps = conn.prepareStatement(sql);
			
			ps.setString(1,daiKaiSkbCod);
			ps.setString(2,kaiSkbCod);
			ps.setString(3,kigBng);
//			ps.setString(4,bunCod);
			ps.setString(4,fukSziCod);
			ps.setString(5,smpKbn);
			
			rs = ps.executeQuery();
			
			if(rs.next()){
				fukSziSuu = rs.getString("FUKSZISUU").trim();
			}else{
				fukSziSuu = null;
			}
			
		}catch(SQLException sqle1){
			SystemException se = new SystemException(sqle1);
			se.printStackTrace();	  
			throw sqle1;	
		}finally{
			try{
				if(rs != null)
					rs.close();
				if(ps != null)
					ps.close();
			}catch(SQLException sqle){
				SystemException se = new SystemException(sqle);
				se.printStackTrace();
			}
		}			
		return fukSziSuu;
	}
	
	/**
	 * �����ލ݌Ƀe�[�u��(FTBHIN12)�����o�^���\�b�h<br>
	 * �n���ꂽ���Ɉ�v����HIN12�̑��݃`�F�b�N���s���A�Ȃ���Γo�^����
	 * @param daiKaiSkbCod,kaiSkbCod,kigBng,fukSziCodArr,smpKbn,bunCodArr,upddte,updjkk
	 * @throws java.sql.SQLException 
	 */
	public void insertHin12List(
		String daiKaiSkbCod,
		String kaiSkbCod,
		String kigBng,
		String smpKbn,
		ArrayList fukSziCodArr,
		ArrayList bunCodArr,
		int upddte,
		int updjkk)
		throws SQLException {
		
		for (int i = 0; i < fukSziCodArr.size(); i++) {
			
			String fukSziSuu =
				findHin12(
					daiKaiSkbCod,
					kaiSkbCod,
					kigBng,
					(String) bunCodArr.get(i),
					(String) fukSziCodArr.get(i),
					smpKbn);
					
			//HIN12�����݂��Ȃ�������o�^
			if(fukSziSuu == null){
				insertHin12(
					daiKaiSkbCod,
					kaiSkbCod,
					kigBng,
					(String) fukSziCodArr.get(i),
					0,
					(String) bunCodArr.get(i),
					smpKbn,
					upddte,
					updjkk);
			}
		}
		
	}
	
	/**
	 * �t���i�\���}�X�^�[(FTBMST08)�������A�����ރR�[�h�E�d�����ArrayList��Ԃ����\�b�h
	 * 
	 * @param daiKaiSkbCod,kaiSkbCod,kigbng
	 * @throws java.sql.SQLException 
	 */
//2004.02.25 add 
	public HashMap findFukSzi_SirSkCodArr(
		String daiKaiSkbCod,
		String kaiSkbCod,
		String kigbng)
		throws SQLException {

		String sql = "SELECT FUKTBL,SIRTBL FROM FTBMST08 WHERE DAIKAISKBCOD = ? "
					+ " AND KAISKBCOD = ? AND KIGBNG = ?"
					;
					
		ArrayList fukSziCod_arr = new ArrayList();
		ArrayList fukSziHacSaki_arr = new ArrayList();
		String fukSziTbl = "";
		String SirskTbl = "";
		ResultSet rs = null;	
		PreparedStatement ps = null;
		boolean exist_mst08 = false;
		HashMap resultMap = new HashMap();
		
		try{

			ps = conn.prepareStatement(sql);
			ps.setString(1,daiKaiSkbCod);
			ps.setString(2,kaiSkbCod);
			ps.setString(3,kigbng);
			
			rs = ps.executeQuery();
		
			if(rs.next()){
				fukSziTbl = rs.getString("FUKTBL").trim();
//				SirskTbl = rs.getString("SIRTBL").trim();
				SirskTbl = rs.getString("SIRTBL");
				exist_mst08 = true;
			}
					
			if(exist_mst08){
				//�����ރR�[�h����
				for(int i = 0; i<fukSziTbl.length(); i+=3){
					try{
						fukSziCod_arr.add(fukSziTbl.substring(i, i+3));
					}catch(IndexOutOfBoundsException e){
						break;
					}
				}
				//�����ރR�[�h�������ł��Ȃ�������
				if(fukSziCod_arr.size() == 0)
					fukSziCod_arr = null;
					
				//�d����R�[�h����
				for(int i = 0; i<SirskTbl.length(); i+=6){
					try{
						if(SirskTbl.substring(i, i+6).trim().equals(""))
							break;
						fukSziHacSaki_arr.add(SirskTbl.substring(i, i+6));
					}catch(IndexOutOfBoundsException e){
						break;
					}
				}
				//�d����R�[�h�������ł��Ȃ�������
				if(fukSziHacSaki_arr.size() == 0)
					fukSziHacSaki_arr = null;

			}else{ //�t���i�\���e�[�u�������݂��Ȃ�������
				fukSziCod_arr = null;
				fukSziHacSaki_arr = null;
			}

			resultMap.put("fukSziCod_arr",fukSziCod_arr);
			resultMap.put("fukSziHacSaki_arr",fukSziHacSaki_arr);
			
		}catch(SQLException sqle1){
			SystemException se = new SystemException(sqle1);
			se.printStackTrace();
			throw sqle1;
	  	
		}finally{
			try{
				if(rs != null)
					rs.close();
				if(ps != null)
					ps.close();
			}catch(SQLException sqle){
				SystemException se = new SystemException(sqle);
				se.printStackTrace();
				throw sqle;
			}
		}		
		return resultMap;
	}
	
	//TODO ��Вǉ��Ή�(�ǂ̃p�^�[���Ŏ擾���邩)
	/**
	 * �����ޔ�����E���́A���ރR�[�h���擾����VO�ɃZ�b�g���܂��B
	 * @param fmVO
	 * @throws SQLException
	 */
	public void findFukSziHacSaki(CommonOrderVO fmVO) throws SQLException {
		
		//***********************************************************************/
		// K�̏ꍇ
		// �����ޔ����͍s�Ȃ�Ȃ�
		//***********************************************************************/
		if (fmVO.getDaiKaiSkbCod().equals(CommonConst.KAICOD_K))
			return;

	
		ResultSet rs = null;
		PreparedStatement ps = null;
		
		//***********************************************************************/
		// VAP�̏ꍇ  
		// �����ޔ����� : �i�ԃ}�X�^�[��FUKSZIMKR(������}�X�^�[�ɑ��݂���ꍇ)
		// �����ރR�[�h : �����ރ}�X�^�[����擾(BUNCOD=1)
		//***********************************************************************/
		if (fmVO.getDaiKaiSkbCod().equals(CommonConst.KAICOD_VAP)){

			//������}�X�^�[����
			String sql =
				"SELECT SIRHACCOD FROM FTBMST03 "
					+ " WHERE KAISKBCOD = ? AND SIRHACCOD = ?";
	
			boolean exist_mst03 = false;
			try {
				ps = conn.prepareStatement(sql);
				ps.setString(1, fmVO.getDaiKaiSkbCod());
				ps.setString(2, fmVO.getFukSziMkr());
	
				rs = ps.executeQuery();
	
				if (rs.next()) {
					//������}�X�^�[�ɑ��݂���΁A������R�[�h�𕛎��ޔ�����Ƃ���		
					fmVO.setFukSziHacSaki(rs.getString("SIRHACCOD").trim());
					exist_mst03 = true;
				}
			} finally {
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();
			}
			try {
				//�����ރR�[�h�A���ރR�[�h�̎擾
				ArrayList fukSziCod_arr = new ArrayList();
				ArrayList bunCod_arr = new ArrayList();

				//�����ރ}�X�^�[����
				sql =
					"SELECT FUKSZINMKJ,FUKSZICOD,BUNCOD FROM FTBFUK01 "
						+ " WHERE DAIKAISKBCOD = ? AND KAISKBCOD = ? AND BUNCOD = '1'"
						+ " ORDER BY FUKSZICOD "; // 2005.09.09 add

				ps = conn.prepareStatement(sql);

				ps.setString(1, fmVO.getDaiKaiSkbCod());
				ps.setString(2, fmVO.getKaiSkbCod()); // 2004.03.04 add

				rs = ps.executeQuery();
				if (rs.next()) {
					fmVO.setFukSziCod(rs.getString("FUKSZICOD").trim());
					fmVO.setFukSziNm(StringUtils.removeSuffix(rs.getString("FUKSZINMKJ"), "�@"));
					fmVO.setBunCod(rs.getString("BUNCOD").trim());
					
					fukSziCod_arr.add(rs.getString("FUKSZICOD").trim());
					bunCod_arr.add(rs.getString("BUNCOD").trim());
				}
				fmVO.setBunCod_arr(bunCod_arr); //���ރR�[�harr��VO�ɃZ�b�g
				fmVO.setFukSziCod_arr(fukSziCod_arr);//�����ރR�[�harr��VO�ɃZ�b�g
				fmVO.setExsitFuk01(true);
				return;
				
			} finally {
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();
			}
		}
		//***********************************************************************/
		// CR, FX, LAN�̏ꍇ 
		// �����ޔ����� : �i�ԃ}�X�^�[��FUKSZIMKR(������}�X�^�[�ɑ��݂���ꍇ)
		// �����ރR�[�h : �����ރ}�X�^�[����擾(BUNCOD=1)
		//***********************************************************************/
		else if (fmVO.getKaiSkbCod().equals(CommonConst.KAICOD_CR)
				|| fmVO.getDaiKaiSkbCod().equals(CommonConst.KAICOD_FX)
				|| fmVO.getKaiSkbCod().equals(CommonConst.KAICOD_LAN)) {
			
			//---������}�X�^�[�ɂȂ������Ƃ�---------------------------------------------------
			//�t���i�\���}�X�^�[�����E�����ރR�[�h�̃Z�b�g���擾
			HashMap resultMap =
				findFukSzi_SirSkCodArr(
					fmVO.getDaiKaiSkbCod(),
					fmVO.getKaiSkbCod(),
					fmVO.getKigBng());
			ArrayList fukSziCod_arr = (ArrayList) resultMap.get("fukSziCod_arr");
			ArrayList fukSziHacSaki_arr = (ArrayList) resultMap.get("fukSziHacSaki_arr");

			if (fukSziCod_arr != null) {
				fmVO.setFukSziPtn(fmVO.getFukSziMkr()); //�����ރR�[�h��VO�ɃZ�b�g
			} else {
				fukSziCod_arr = new ArrayList();
				if(!fmVO.getFukSziMkr().equals("")){
					fukSziCod_arr.add(fmVO.getFukSziMkr());//�����ރ��[�J�[�R�[�h�𕛎��ރR�[�h�Ƃ���
				}	
			}
			fmVO.setFukSziCod_arr(fukSziCod_arr); //�����ރR�[�harr��VO�ɃZ�b�g
			fmVO.setFukSziHacSaki_arr(fukSziHacSaki_arr);//�d����R�[�harr��VO�ɃZ�b�g 2004.02.25 add
			
			//���ރR�[�h�A�����ޖ��̂̎擾
			ArrayList bunCod_arr = new ArrayList();
			ArrayList fukSziNm_arr = new ArrayList();

			for(int i = 0; i<fukSziCod_arr.size(); i++){
				try{	
					//�����ރ}�X�^�[����
					String sql = "SELECT FUKSZIHACCOD,FUKSZINMKJ,BUNCOD FROM FTBFUK01 "
					+ " WHERE DAIKAISKBCOD = ? AND KAISKBCOD = ? "
					+ " AND FUKSZICOD = ?";
					
					
					ps = conn.prepareStatement(sql);
					
					ps.setString(1,fmVO.getDaiKaiSkbCod());
					ps.setString(2,fmVO.getKaiSkbCod());
					ps.setString(3,fukSziCod_arr.get(i)+"");
					
					rs = ps.executeQuery();
					
					if(rs.next()){
						if(i == 0){
							fmVO.setFukSziHacSaki(fukSziHacSaki_arr.get(i) + "");
							fmVO.setFukSziNm(StringUtils.removeSuffix(rs.getString("FUKSZINMKJ"),"�@"));
							fmVO.setFukSziCod(fukSziCod_arr.get(i) + "");
							fmVO.setBunCod(rs.getString("BUNCOD").trim());
							fmVO.setExsitFuk01(true);
						}
						fukSziNm_arr.add(StringUtils.removeSuffix(rs.getString("FUKSZINMKJ"),"�@"));
						bunCod_arr.add(rs.getString("BUNCOD").trim());
					}else{
						fukSziNm_arr.add("");
						bunCod_arr.add("");
					}
					fmVO.setBunCod_arr(bunCod_arr); //���ރR�[�harr��VO�ɃZ�b�g
					fmVO.setFukSziNm_arr(fukSziNm_arr);//�����ޖ���arr��VO�ɃZ�b�g
					
				}finally{
					if(rs != null)
						rs.close();
					if(ps != null)
						ps.close();
				}
			}
		}

	}

	/**
	 * �������f�[�^�̓o�^
	 * @param fmVO
	 * @param upddte
	 * @param updjkk
	 * @param syrsuu
	 * @param seqno
	 * @param dtaExcFlg
	 * @throws SQLException
	 */
	public void insertHac01(
		String daiKaiSkbCod,
		String kaiSkbCod,
		String hacSyoDte,
		String syrSuu,
		String seqNo,
		int dtaExcFlg,
		int updDte,
		int updJkk)
		throws SQLException {
			
		String sql = "INSERT INTO FTBHAC01"
					+ " SELECT " 
					+	"HAC02.DAIKAISKBCOD, " 
					+	"HAC02.KAISKBCOD, " 
					+	"HAC02.HACSYODTE, " 
					+	"HAC02.HACSYOBNG, "
					+	"HAC02.SYRSUU, " 
					+	"HAC02.SEQNO, "
					+	"HAC02.HACCOD, " 
					+	"VALUE(MST03.SIRHACNM1 || MST03.SIRHACNM2, G'�@'), "
					+	"HAC02.SINKYUKBN, " 
					+	"HAC02.GYOBNG, " 
					+	"HAC02.KIGBNG, "
					+	"HAC02.TITKJ, " 
					+	"HAC02.PRSFUKSZICOD, " 
					+	"VALUE(FUK01.FUKSZINMKJ, G'�@'), "
					+	"HAC02.SETSUU, " 
					+	"HAC02.HACSUU, "
					+	"HAC02.NKI, " 
					+	"HAC02.NHNMEIKJ, " 
					+	"HAC02.BUNCOD, " 
					+	"'" + dtaExcFlg + "', " 
					+	"HAC02.CYKKBN, " 
					+	"'A', "
					+	 updDte + ", "  
					+	 updJkk + ", " 
					+	"HAC02.CMT, " 
					+	"HAC02.FUKZAISUU, "
					+	"HAC02.USRID, "		//2005/05/19 add
					+	"HAC02.RRKTBL, "	//2005/08/26 add
					+	"HAC02.TAN, "			//2005/08/26 add
					+	"HAC02.BIKOU2, "			//2007/12/25 add
					+	"HAC02.TAN2"			//2008/03/07 add
					+ " FROM FTBHAC02 HAC02"
					
					+ " LEFT OUTER JOIN FTBMST03 MST03 ON"
					+ " HAC02.DAIKAISKBCOD=MST03.KAISKBCOD"
					+ " AND HAC02.HACCOD=MST03.SIRHACCOD"
					
					+ " LEFT OUTER JOIN FTBFUK01 FUK01 ON"
					+ " HAC02.DAIKAISKBCOD=FUK01.DAIKAISKBCOD"
					+ " AND HAC02.KAISKBCOD=FUK01.KAISKBCOD"
					+ " AND HAC02.PRSFUKSZICOD=FUK01.FUKSZICOD"

					+ " WHERE HAC02.DAIKAISKBCOD='" + daiKaiSkbCod + "'"
					+ " AND HAC02.KAISKBCOD='" + kaiSkbCod + "'"
					+ " AND HAC02.HACSYODTE=" + hacSyoDte 
					+ " AND HAC02.SYRSUU='" + syrSuu + "'"
					+ " AND HAC02.SEQNO='" + seqNo + "'";


		Statement stmt = null;
		try{
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
			
		}finally{
			if(stmt != null)
				stmt.close();
		}			
	}

}

