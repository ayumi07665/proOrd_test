/**
* KetRakDAO  �`�ԃ����N�ʃ}�X�^�[�����e�i���X  �f�[�^�A�N�Z�X�I�u�W�F�N�g�N���X
*
*	�쐬��    2003/05/02
*	�쐬��    �i�m�h�h�j���� ���G
* �����T�v    �`�ԃ����N�ʃ}�X�^�[�iFTBMST05�j�ɃA�N�Z�X����N���X�B
* 
*	[�ύX����]
*		2003/05/22�i�m�h�h�j���c �Ĕ�
* 			�E�X�V���̔r������Ή��B
* 		2003/05/27�i�m�h�h�j�g�c �h�q
* 			�E�\�[�g�����̒ǉ��B
* 		2003/06/10
* 			�E��o�^��̂Ƃ��A�_���폜�ς̃��R�[�h��DELETE���ēo�^�B
* 			�E��폜��̂Ƃ��A�_���폜�w��DELETE�B
*/

package com.jds.prodord.master.ketrak;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.jds.prodord.common.CommonDAO;

public class KetRakDAO extends CommonDAO{

	
	public KetRakDAO() throws SQLException{
    	super();
	}


	/**
	 * �Ɖ�
	 * 
	 */
	public KetRakVO[] find(KetRakVO fmVO)throws SQLException{

		String sql = "SELECT KAISKBCOD,TOMRAK,KETCOD,SSNREDTIM,MINZAISUU,MINROTSUU,MRMSUU,"
					+ "UPDDTE,UPDJKK FROM FTBMST05 "
					+ " WHERE DAIKAISKBCOD = '" + fmVO.getDaiKaiSkbCod() + "'"
					+ " AND KAISKBCOD = '" + fmVO.getKaiSkbCod() + "'" ;
		if(!fmVO.getRank().equals("")){
			sql += " AND TOMRAK = '" + fmVO.getRank() + "'";
		}
		if(!fmVO.getKetCod().equals("")){
			sql += " AND KETCOD = '" + fmVO.getKetCod() + "'";
		}	   
			sql	+= " AND UPDKBN <> 'D' ";
			
		//2003/05/27
		if(!(!fmVO.getRank().equals("") && !fmVO.getKetCod().equals(""))){
			if(!fmVO.getRank().equals(""))
				sql += " ORDER BY KETCOD";
		    if(!fmVO.getKetCod().equals(""))
				sql += " ORDER BY TOMRAK";
		}

		List lis = new ArrayList();

		ResultSet rs = null;
		Statement stmt = null;
		try{
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next()){
				KetRakVO resultVO = new KetRakVO();
				resultVO.setDaiKaiSkbCod(fmVO.getDaiKaiSkbCod());//2003/05/22 add okada
				resultVO.setKaiSkbCod(rs.getString("KAISKBCOD").trim());
				resultVO.setRank(rs.getString("TOMRAK").trim());
				resultVO.setKetCod(rs.getString("KETCOD").trim());
				resultVO.setSsnRedTim(String.valueOf(rs.getInt("SSNREDTIM")));
				resultVO.setMinZaiSuu(String.valueOf(rs.getInt("MINZAISUU")));
				resultVO.setMinRotSuu(String.valueOf(rs.getInt("MINROTSUU")));
				resultVO.setMrmSuu(String.valueOf(rs.getInt("MRMSUU")));

				resultVO.setUpdDte(rs.getInt("UPDDTE"));
				resultVO.setUpdJkk(rs.getInt("UPDJKK"));

				lis.add(resultVO);
			}
		}finally{
			if(rs != null)
				rs.close();
			if(stmt != null)
				stmt.close();
		}	
					
		return (KetRakVO[])lis.toArray(new KetRakVO[lis.size()]);
	
	}
	

	/**
	 * �o�^
	 * 
	 */
	public void insert(KetRakVO fmVo,int upddte,int updjkk)throws SQLException{
		String sql = "INSERT INTO FTBMST05 (DAIKAISKBCOD,KAISKBCOD,TOMRAK,KETCOD"
					 + ",SSNREDTIM,MINZAISUU,MINROTSUU,MRMSUU,UPDKBN,UPDDTE,UPDJKK) "
					 + " VALUES (?,?,?,?,?,?,?,?,?,?,?)";
			
		PreparedStatement ps = null;
		try{
			ps = conn.prepareStatement(sql);
	
			ps.setString(1,fmVo.getDaiKaiSkbCod());
			ps.setString(2,fmVo.getKaiSkbCod());
			ps.setString(3,fmVo.getRank());
			ps.setString(4,fmVo.getKetCod());
			ps.setString(5,fmVo.getSsnRedTim());
			ps.setString(6,fmVo.getMinZaiSuu());
			ps.setString(7,fmVo.getMinRotSuu());
			ps.setString(8,fmVo.getMrmSuu());
			ps.setString(9,"A");
			ps.setInt(10,upddte);
			ps.setInt(11,updjkk);
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
	public void update(KetRakVO fmVo,int upddte,int updjkk)throws SQLException{
			
		String sql = "UPDATE FTBMST05 SET SSNREDTIM = ? ,MINZAISUU = ? "
					+ " ,MINROTSUU = ? ,MRMSUU = ? "
					+ " ,UPDKBN = ? ,UPDDTE = ? ,UPDJKK = ? "
					+ " WHERE DAIKAISKBCOD = ? AND KAISKBCOD = ? AND TOMRAK = ? "
					+ " AND KETCOD = ? ";
		
		PreparedStatement ps = null;
		try{
			ps = conn.prepareStatement(sql);

			ps.setInt(1,Integer.parseInt(fmVo.getSsnRedTim()));
			ps.setInt(2,Integer.parseInt(fmVo.getMinZaiSuu()));
			ps.setInt(3,Integer.parseInt(fmVo.getMinRotSuu()));
			ps.setInt(4,Integer.parseInt(fmVo.getMrmSuu()));
			ps.setString(5,"U");
			ps.setInt(6,upddte);
			ps.setInt(7,updjkk);
	
			ps.setString(8,fmVo.getDaiKaiSkbCod());
			ps.setString(9,fmVo.getKaiSkbCod());
			ps.setString(10,fmVo.getRank());
			ps.setString(11,fmVo.getKetCod());

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
//	public void delete(KetRakVO fmVo,int upddte,int updjkk)throws SQLException{
//		String sql = "UPDATE FTBMST05 SET UPDKBN = ? ,UPDDTE = ? ,UPDJKK = ? "
//					+ " WHERE DAIKAISKBCOD = ? AND KAISKBCOD = ? AND TOMRAK = ? "
//					+ " AND KETCOD = ? ";
//		
//		PreparedStatement ps = null;
//		try{
//			ps = conn.prepareStatement(sql);
//
//			ps.setString(1,"D");
//			ps.setInt(2,upddte);
//			ps.setInt(3,updjkk);
//			
//			ps.setString(4,fmVo.getDaiKaiSkbCod());
//			ps.setString(5,fmVo.getKaiSkbCod());
//			ps.setString(6,fmVo.getRank());
//			ps.setString(7,fmVo.getKetCod());
//			
//			ps.executeUpdate();
//
//		}finally{
//			if(ps != null)
//				ps.close();
//		}			
//	}

	public void delete(KetRakVO fmVo,int upddte,int updjkk)throws SQLException{
		String sql = "DELETE FROM FTBMST05 "
					+ " WHERE DAIKAISKBCOD = ? AND KAISKBCOD = ? AND TOMRAK = ? "
					+ " AND KETCOD = ? ";
//System.out.println("sql�F"+sql);
		PreparedStatement ps = null;
		try{
			ps = conn.prepareStatement(sql);

			ps.setString(1,fmVo.getDaiKaiSkbCod());
			ps.setString(2,fmVo.getKaiSkbCod());
			ps.setString(3,fmVo.getRank());
			ps.setString(4,fmVo.getKetCod());
			
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
	 * �폜/�X�V�p����
	 * 
	 */
	public int selectForUpdate(KetRakVO fmVo)throws SQLException{

		String sql = "SELECT UPDKBN,UPDDTE,UPDJKK FROM FTBMST05 "
					+ " WHERE DAIKAISKBCOD = ? AND KAISKBCOD = ? AND TOMRAK = ? "
					+ " AND KETCOD = ? ";

		ResultSet rs = null;
		PreparedStatement ps = null;
		try{
			ps = conn.prepareStatement(sql);

			ps.setString(1,fmVo.getDaiKaiSkbCod());
			ps.setString(2,fmVo.getKaiSkbCod());
			ps.setString(3,fmVo.getRank());
			ps.setString(4,fmVo.getKetCod());
			
			rs = ps.executeQuery();
			
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
			if(ps != null)
				ps.close();
		}			

	}
	
	/**
	 * �_���폜�̂��̂��폜
	 * 
	 */
	public void deleteMst05(KetRakVO fmVo)throws SQLException{

		String sql = "DELETE FROM FTBMST05"
					+ " WHERE DAIKAISKBCOD = ? AND KAISKBCOD = ? AND TOMRAK = ?  AND KETCOD = ? "
					+ " AND UPDKBN = 'D'";
//System.out.println("sql�F"+sql);
 		ResultSet rs = null;
		PreparedStatement ps = null;
		try{
			ps = conn.prepareStatement(sql);

			ps.setString(1,fmVo.getDaiKaiSkbCod());
			ps.setString(2,fmVo.getKaiSkbCod());
			ps.setString(3,fmVo.getRank());
			ps.setString(4,fmVo.getKetCod());
			
			rs = ps.executeQuery();
			
		}finally{
			if(rs != null)
				rs.close();
			if(ps != null)
				ps.close();
		}			

	}



}

