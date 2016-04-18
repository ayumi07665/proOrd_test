/**
* FzkHinKsiDAO  �t���i�\���}�X�^�[�����e�i���X  �f�[�^�A�N�Z�X�I�u�W�F�N�g�N���X
*
*	�쐬��    2004/02/13
*	�쐬��    �i�m�h�h�j�X
* �����T�v    �t���i�\���}�X�^�[�iFTBMST08�j�ɃA�N�Z�X����N���X�B
* 
*	[�ύX����]
* 			 2004/03/29 (NII) �X
* 				�E�i�ԃ}�X�^�̃e�[�u�����ύX
*			 2004/06/23�i�m�h�h�j�g�c
*				�E�����於�̎擾���\�b�h�ǉ�
*
*/

package com.jds.prodord.master.fzkhinksi;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.jds.prodord.common.CommonDAO;
import com.jds.prodord.common.DataFormatUtils;
import com.jds.prodord.common.StringUtils;

public class FzkHinKsiDAO extends CommonDAO{
	
	public FzkHinKsiDAO() throws SQLException{
    	super();
	}

	/**
	 * �Ɖ�
	 * 
	 */
	final static String HIN01 = "FTBHIN01";
	final static String HIN02 = "FTBHIN02";
	//�i�ԃ}�X�^�[����(�P������)****************************************************************
	
	public boolean findHin01_row(FzkHinKsiVO queryVO,FzkHinKsiVO resultVO,String HIN_TABLE)throws SQLException{
		boolean ret = false;
		String sql = "SELECT DAIKAISKBCOD,KAISKBCOD,KIGBNG,HJIHNB,TITKJ,KETCOD,"
					+ "HBIDTE,SETSUU "
					+ " FROM " + HIN_TABLE + ""
					+ " WHERE DAIKAISKBCOD = '" + queryVO.getDaiKaiSkbCod() + "'"
					+ " AND KAISKBCOD = '" + queryVO.getKaiSkbCod() + "'"
					+ " AND KIGBNG = '"+ DataFormatUtils.mk_srykig(queryVO.getHinban()) +"'"
					+ " ORDER BY KIGBNG"
					;
						
//System.out.println("sql : "+sql);
			
		ArrayList finded_Arr = new ArrayList();
		ResultSet rs = null;
		Statement stmt = null;
		try{
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			if(rs.next()){
				resultVO.setDaiKaiSkbCod(rs.getString("DAIKAISKBCOD").trim());
				resultVO.setKaiSkbCod(rs.getString("KAISKBCOD").trim());
				resultVO.setHinban(rs.getString("KIGBNG").trim());
				resultVO.setTitle(StringUtils.removeSuffix(rs.getString("TITKJ"),"�@"));
				resultVO.setKetCod(rs.getString("KETCOD").trim());
				resultVO.setHbidte(rs.getString("HBIDTE").trim());
				resultVO.setSetsuu(rs.getString("SETSUU").trim());
				resultVO.setExsitHin01(true);
				ret = true;
			}

		}finally{
			if(rs != null)
				rs.close();
			if(stmt != null)
				stmt.close();
		}
		return ret;	
	}			

	//�����ޔ�����R�[�h�E�����ޖ��̎擾**********************************************************
	
	public void findFuk01(FzkHinKsiVO resultVO)throws SQLException{
		ResultSet rs = null;
		Statement stmt = null;
		try {
			//�����ރ}�X�^�[����
			String sql = "SELECT FUKSZINMKJ FROM FTBFUK01 "
						+ " WHERE DAIKAISKBCOD = '" + resultVO.getDaiKaiSkbCod() + "'"
						+ " AND KAISKBCOD = '" + resultVO.getKaiSkbCod() + "'"
						+ " AND FUKSZICOD = '" + resultVO.getFuksziCod() + "'"
						;
							
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			if(rs.next()){
				resultVO.setFuksziNm(StringUtils.removeSuffix(rs.getString("FUKSZINMKJ"),"�@"));//�����ޖ���
			}else{
				resultVO.setFuksziNm("�@");//�����ޖ���
			}
		}finally{
			if(rs != null)
				rs.close();
			if(stmt != null)
				stmt.close();
		}
	}	
	
	//�t���i�\���}�X�^�[**********************************************************
	public void findMst08(FzkHinKsiVO resultVO)throws SQLException{
		ResultSet rs = null;
		Statement stmt = null;
		try {
			String sql = "SELECT FUKTBL,SIRTBL,UPDDTE,UPDJKK FROM FTBMST08 "
						+ " WHERE DAIKAISKBCOD = '" + resultVO.getDaiKaiSkbCod() + "'"
						+ " AND KAISKBCOD = '" + resultVO.getKaiSkbCod() + "'"
						+ " AND KIGBNG = '" + resultVO.getHinban() + "'"
						;
							
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			if(rs.next()){
				resultVO.setFuksziCod08String(rs.getString("FUKTBL"));
				resultVO.setSirSk08String(rs.getString("SIRTBL"));
				resultVO.setUpdDte(rs.getInt("UPDDTE"));
				resultVO.setUpdJkk(rs.getInt("UPDJKK"));

				resultVO.setExsitMst08(true);
			}else{
				resultVO.setExsitMst08(false);
			}
		}finally{
			if(rs != null)
				rs.close();
			if(stmt != null)
				stmt.close();
		}
	}	
	


	/**
	 * �Ɖ�
	 * 
	 */
	public void findMST06(FzkHinKsiVO fmVO,FzkHinKsiVO resultVO)throws SQLException{

		String sql = "SELECT KETCOD,KETNMKJ,UPDDTE,UPDJKK,KETNMKJ2,"
				+ "SZITBL FROM FTBMST06 "
				+ " WHERE DAIKAISKBCOD = '" + fmVO.getQueryKaiSkbCod() + "'" //(FTBMST06�ł�)��\��Ё����[�U�[�̉��
				+ " AND KETCOD = '" + resultVO.getKetCod() + "'"
				+ " AND UPDKBN <> 'D' "
				+ " ORDER BY KETCOD";


		ResultSet rs = null;
		Statement stmt = null;
		try{
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			if(rs.next()){
				resultVO.setDaiKaiSkbCod(fmVO.getDaiKaiSkbCod());
				resultVO.setQueryKaiSkbCod(fmVO.getQueryKaiSkbCod());
				resultVO.setKetCod(rs.getString("KETCOD").trim());
				resultVO.setKetNm(StringUtils.removeSuffix(rs.getString("KETNMKJ"),"�@"));
				resultVO.setFuksziCod06String(rs.getString("SZITBL").trim());
			}
		}finally{
			if(rs != null)
				rs.close();
			if(stmt != null)
				stmt.close();
		}	
		
	}
	
	//-----  �����ރR�[�h���݃`�F�b�N  ----------------------------------------

	public boolean hasFukSziCod(FzkHinKsiVO fmVo, String fukSziCod)throws SQLException{
		if(fukSziCod.equals(""))
			return true;
		
		String sql = " SELECT FUKSZICOD,BUNCOD "
					+ " FROM FTBFUK01 "
					+ " WHERE DAIKAISKBCOD = '"+fmVo.getDaiKaiSkbCod()+"'"
					+ " AND FUKSZICOD = '" + fukSziCod + "'";

		ResultSet rs = null;
		PreparedStatement ps = null;

		try{
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			if(rs.next()){
				fmVo.addBunCod(rs.getString("BUNCOD"));
				return true;
			}else{
				return false;
			}
			
		}finally{
			if(rs != null)
				rs.close();
			if(ps != null)
				ps.close();
		}

	}
	//-----  �d����R�[�h���݃`�F�b�N  ----------------------------------------

	public boolean hassirSkCod(FzkHinKsiVO fmVo, String sirsk)throws SQLException{
		if(sirsk.equals(""))
			return true;
		
		String sql = " SELECT SIRHACCOD "
					+ " FROM FTBMST03 "
					+ " WHERE KAISKBCOD = '"+fmVo.getDaiKaiSkbCod()+"'" //mst03�̉�Ў��ʃR�[�h�ɂ͑�\��Ђ������Ă��邽��
					+ " AND SIRHACCOD = '" + sirsk + "'";

		ResultSet rs = null;
		PreparedStatement ps = null;

		try{
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			if(rs.next()){
				return true;
			}else{
				return false;
			}
			
		}finally{
			if(rs != null)
				rs.close();
			if(ps != null)
				ps.close();
		}

	}

	/**
	 * �o�^
	 * 
	 */
	public void insert(FzkHinKsiVO fmVo,int upddte,int updjkk)throws SQLException{
		String sql = "INSERT INTO FTBMST08 (DAIKAISKBCOD,KAISKBCOD,KIGBNG,"
					 + "FUKTBL,SIRTBL,UPDKBN,UPDDTE,UPDJKK) "
					 + " VALUES (?,?,?,?,?,?,?,?)";
			
		PreparedStatement ps = null;
		try{
			ps = conn.prepareStatement(sql);
	
			ps.setString(1,fmVo.getDaiKaiSkbCod());
			ps.setString(2,fmVo.getKaiSkbCod());
			ps.setString(3,fmVo.getHinban());
			ps.setString(4,fmVo.getFuksziCod08String());
			ps.setString(5,fmVo.getSirSk08String());
			ps.setString(6,"A");
			ps.setInt(7,upddte);
			ps.setInt(8,updjkk);
			ps.executeUpdate();

		}finally{
			if(ps != null)
				ps.close();
		}			
	}

	/**
	 * �X�V
	 * 
	 */

	public void update(FzkHinKsiVO fmVo,int upddte,int updjkk)throws SQLException{
			
		String sql = "UPDATE FTBMST08 SET FUKTBL = ? ,SIRTBL = ? "
					+ " ,UPDKBN = ? ,UPDDTE = ? ,UPDJKK = ? "
					+ " WHERE DAIKAISKBCOD = ? AND KAISKBCOD = ? AND KIGBNG = ?";
		
		PreparedStatement ps = null;
		try{
			ps = conn.prepareStatement(sql);

			ps.setString(1,fmVo.getFuksziCod08String().trim());
			ps.setString(2,fmVo.getSirSk08String().trim());
			ps.setString(3,"U");
			ps.setInt(4,upddte);
			ps.setInt(5,updjkk);
	
			ps.setString(6,fmVo.getDaiKaiSkbCod());
			ps.setString(7,fmVo.getKaiSkbCod());
			ps.setString(8,fmVo.getHinban());

			ps.executeUpdate();

		}finally{
			if(ps != null)
				ps.close();
		}
	}
	
	/**
	 * �폜
	 * 
	 */
	public void delete(FzkHinKsiVO fmVo,int upddte,int updjkk)throws SQLException{
			
		String sql = "DELETE FROM FTBMST08 "
				+ " WHERE DAIKAISKBCOD = ? AND KAISKBCOD = ? AND KIGBNG = ?"
				;
		
		PreparedStatement ps = null;
		try{
			ps = conn.prepareStatement(sql);

			ps.setString(1,fmVo.getDaiKaiSkbCod());
			ps.setString(2,fmVo.getKaiSkbCod());
			ps.setString(3,fmVo.getHinban());

			ps.executeUpdate();

		}finally{
			if(ps != null)
				ps.close();
		}
	}


	public static final int NOT_MODIFIED = 1;
	public static final int MODIFIED = 2;
	public static final int NOT_EXIST = 3;
	public static final int LOGICAL_DELETE = 4;	

	/**
	 * �X�V�E�폜�p����
	 * 
	 */

	public int selectForUpdate(FzkHinKsiVO fmVo)throws SQLException{
		ResultSet rs = null;
		Statement stmt = null;

		String sql = "SELECT UPDKBN,UPDDTE,UPDJKK FROM FTBMST08 "
				+ " WHERE DAIKAISKBCOD = '" + fmVo.getDaiKaiSkbCod() + "'"
				+ " AND KAISKBCOD = '" + fmVo.getKaiSkbCod() + "'"
				+ " AND KIGBNG = '" + fmVo.getHinban() + "'"
				;
			
		try{
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			if(rs.next()){
				String updKbn = rs.getString("UPDKBN");
				int updDte = rs.getInt("UPDDTE");
				int updJkk = rs.getInt("UPDJKK");


				while(rs.next()){
				}
				if(updKbn.trim().equals("D")){
					return LOGICAL_DELETE;
				}


				//������������UPDDTE�ȂǂƍX�V�O������UPDDTE���قȂ�ꍇ
				if(updDte != fmVo.getUpdDte() || updJkk != fmVo.getUpdJkk()){
					return MODIFIED;
				}
				return NOT_MODIFIED;			
			}else{
				return NOT_EXIST;
			}
			
		}finally{
			if(rs != null)
				rs.close();
			if(stmt != null)
				stmt.close();
		}			
	}
	
	//�����於�̎擾 2004/06/23 add ********************************************************

	public void findMst03(FzkHinKsiVO resultVO)throws SQLException{
		ResultSet rs = null;
		Statement stmt = null;
		try{
			//������}�X�^�[����
			String sql = "SELECT SIRHACNM1 FROM FTBMST03 "
						+ " WHERE KAISKBCOD = '" + resultVO.getDaiKaiSkbCod() + "'"
						+ " AND SIRHACCOD = '" + resultVO.getSirSk() + "'";
							
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			if(rs.next()){
				resultVO.setHacNm(StringUtils.removeSuffix(rs.getString("SIRHACNM1"),"�@"));
			}else{
				resultVO.setFuksziNm("�@");
			}
		}finally{
			if(rs != null)
				rs.close();
			if(stmt != null)
				stmt.close();
		}				
	}
}

