/**
* ArticleNumberDAO  �i�ԃ}�X�^�[�����e�i���X�f�[�^�A�N�Z�X�I�u�W�F�N�g�N���X
*
*	�쐬��    2003/08/25
*	�쐬��    �i�m�h�h�j����  ����
* �����T�v    �i�ԃ}�X�^�[�ɃA�N�Z�X����N���X�B
*
*        2003/09/11�i�m�h�h�j����  ����
* 			�E�i�ԃ}�X�^�[�ł��X�V�ł���悤�ɏ����ύX�B
* 		 2004/03/29 (NII) �X
* 			�E�i�ԃ}�X�^�̃e�[�u�����ύX
* 
*/


package com.jds.prodord.master.articlenumber;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.jds.prodord.common.CommonDAO;
import com.jds.prodord.common.StringUtils;

public class ArticleNumberDAO extends CommonDAO{

	public ArticleNumberDAO() throws SQLException{
    	super();
	}	

//=====  ��������  =================================================================================

	public ArticleNumberVO find(ArticleNumberVO proto)throws SQLException{

		String query = "";

		String sql = "SELECT KIGBNG,SRYKIG,ARTKJ,TITKJ,KETCOD,HBIDTE,"
					+	"PRSMKRCOD,JYTPRSMKR,FUKSZIMKR,UPDDTE,UPDJKK"
					+ " FROM FTBHIN01"
					+ " WHERE DAIKAISKBCOD = '" + proto.getQueryDaiKaiSkbCod() + "'"
					+ " AND KAISKBCOD = '" + proto.getKaiSkbCod() +" '"
					+ " AND KIGBNG= '" + proto.getKigoBan() + "'"
					+ " AND UPDKBN <> 'D' "
					;

		ResultSet rs = null;
		ArticleNumberVO fmVo = null;
		Statement stmt = null;

		try{
		    stmt = conn.createStatement();

//		System.out.println("��\��ЁF"+ proto.getQueryDaiKaiSkbCod());
//		System.out.println("��Ў��ʁF"+ proto.getKaiSkbCod());
//		System.out.println("�L���ԍ��F"+ proto.getKigoBan());
			
	        rs = stmt.executeQuery(sql);

			if(rs.next()){

				
				fmVo = new ArticleNumberVO();
				
				fmVo.setDaiKaiSkbCod( proto.getQueryDaiKaiSkbCod());	
				fmVo.setKaiSkbCod( proto.getKaiSkbCod());				
				fmVo.setKigoBan(rs.getString("KIGBNG").trim());			
				fmVo.setArtist(StringUtils.removeSuffix(rs.getString("ARTKJ"),"�@"));
				fmVo.setTitle(StringUtils.removeSuffix(rs.getString("TITKJ"),"�@"));
				fmVo.setKetCod(rs.getString("KETCOD").trim());
				fmVo.setHbiDte(rs.getString("HBIDTE"));
				fmVo.setPrsMkrCod(rs.getString("PRSMKRCOD").trim());
				fmVo.setJytPrsMkr(rs.getString("JYTPRSMKR").trim());
				fmVo.setHukSizCod(rs.getString("FUKSZIMKR").trim());
				fmVo.setUpddte(rs.getInt("UPDDTE"));
				fmVo.setUpdjkk(rs.getInt("UPDJKK"));
//				fmVo.setHidUpdDte(Integer.parseInt(rs.getString("UPDDTE").trim()));
//				fmVo.setHidUpdJkk(Integer.parseInt(rs.getString("UPDJKK").trim()));
				fmVo.setFind_flag(true);
				fmVo.setDbName("HIN01");
				
		   }else{
		   		//HIN01�ŊY���f�[�^���Ȃ�������AHIN�O�Q�Ō������Ȃ���
//		System.out.println("*** DAO ***�FHIN�O�Q�Ō���");
				sql = "SELECT KIGBNG,SRYKIG,ARTKJ,TITKJ,KETCOD,HBIDTE,"
					+	"PRSMKRCOD,JYTPRSMKR,FUKSZIMKR,UPDDTE,UPDJKK"
					+ " FROM FTBHIN02"
					+ " WHERE DAIKAISKBCOD = '" + proto.getQueryDaiKaiSkbCod() + "'"
					+ " AND KAISKBCOD = '" + proto.getKaiSkbCod() +" '"
					+ " AND KIGBNG = '" + proto.getKigoBan() + "'"
					+ " AND UPDKBN <> 'D' "
					;

		   		rs = stmt.executeQuery(sql);

		   		if(rs.next()){
//		   			System.out.println("HIN02 : �Y���f�[�^����A�l��Ԃ��܂�");
				
					fmVo = new ArticleNumberVO();
					
					fmVo.setDaiKaiSkbCod( proto.getQueryDaiKaiSkbCod());	
					fmVo.setKaiSkbCod( proto.getKaiSkbCod());				
					fmVo.setKigoBan(rs.getString("KIGBNG").trim());			
					fmVo.setArtist(StringUtils.removeSuffix(rs.getString("ARTKJ"),"�@"));
					fmVo.setTitle(StringUtils.removeSuffix(rs.getString("TITKJ"),"�@"));
					fmVo.setKetCod(rs.getString("KETCOD").trim());
					fmVo.setHbiDte(rs.getString("HBIDTE"));
					fmVo.setPrsMkrCod(rs.getString("PRSMKRCOD").trim());
					fmVo.setJytPrsMkr(rs.getString("JYTPRSMKR").trim());
					fmVo.setHukSizCod(rs.getString("FUKSZIMKR").trim());
					fmVo.setUpddte(rs.getInt("UPDDTE"));
					fmVo.setUpdjkk(rs.getInt("UPDJKK"));
//					fmVo.setHidUpdDte(Integer.parseInt(rs.getString("UPDDTE").trim()));
//					fmVo.setHidUpdJkk(Integer.parseInt(rs.getString("UPDJKK").trim()));
					fmVo.setFind_flag(true);
					fmVo.setDbName("HIN02");

		   		}else{  //�������ʂ��O���̂Ƃ�
//					System.out.println("�Y���f�[�^������܂���");
						fmVo = new ArticleNumberVO();
		
				   		fmVo.setDaiKaiSkbCod(proto.getQueryDaiKaiSkbCod());
				   		fmVo.setKaiSkbCod(proto.getKaiSkbCod());
				   		fmVo.setKigoBan(proto.getKigoBan().trim());
				   		fmVo.setFind_flag(false);
				}

			}

		}finally{
			if(rs != null)
				rs.close();
			if(stmt != null)
				stmt.close();
		}	
		return fmVo;		
	}

//=====  �o�^����  =================================================================================

	public void insert(ArticleNumberVO fmVO,int upddte,int updjkk)throws SQLException{
		String sql = " INSERT INTO FTBHIN02(DAIKAISKBCOD,KAISKBCOD,KIGBNG,SRYKIG "
					+  " ,ARTKJ,TITKJ,HBIDTE,KETCOD,PRSMKRCOD,JYTPRSMKR,FUKSZIMKR "
					+  " ,UPDKBN,UPDDTE,UPDJKK) "
					+  " VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?) "
					;

//		System.out.println("sql"+sql);

		PreparedStatement ps = null;
		try{
		
			ps = conn.prepareStatement(sql);

			ps.setString(1,fmVO.getDaiKaiSkbCod());
			ps.setString(2,fmVO.getKaiSkbCod());
			ps.setString(3,fmVO.getKigoBan());
			ps.setString(4,fmVO.getKigoBan());
			ps.setString(5,fmVO.getArtist());
			ps.setString(6,fmVO.getTitle());
			ps.setString(7,fmVO.getHbiDte());
			ps.setString(8,fmVO.getKetCod());
			ps.setString(9,fmVO.getPrsMkrCod());
			ps.setString(10,fmVO.getJytPrsMkr());
			ps.setString(11,fmVO.getHukSizCod());
			ps.setString(12,"A");
			ps.setInt(13,upddte);
			ps.setInt(14,updjkk);

			ps.executeUpdate();
			
		}finally{
			if(ps != null)
				ps.close();
		}

	}

//=====  �X�V����  =================================================================================

	public void update(ArticleNumberVO fmVo,int upddte,int updjkk)throws SQLException{

		String sql;
//		if(fmVo.getDbName().equals("�i�ԃ}�X�^�[")){  //03/09/11 �ǉ�
		if(fmVo.getDbName().equals("HIN01")){//2003/10/07 �C�� okada
				sql = "UPDATE FTBHIN01 "
					+ " SET ARTKJ = ?,TITKJ = ? ,HBIDTE = ? ,KETCOD = ?, "
					+ " PRSMKRCOD = ?,JYTPRSMKR = ?,FUKSZIMKR = ?, UPDKBN = ? ,UPDDTE = ? ,UPDJKK = ? "
					+ " WHERE DAIKAISKBCOD = ? AND KAISKBCOD = ? AND KIGBNG = ? "
					;
		}else{
				sql = "UPDATE FTBHIN02 "
					+ " SET ARTKJ = ?,TITKJ = ? ,HBIDTE = ? ,KETCOD = ?, "
					+ " PRSMKRCOD = ?,JYTPRSMKR = ?,FUKSZIMKR = ?, UPDKBN = ? ,UPDDTE = ? ,UPDJKK = ? "
					+ " WHERE DAIKAISKBCOD = ? AND KAISKBCOD = ? AND KIGBNG = ? "
					;
		}

//		System.out.println("sql : "+sql);

		PreparedStatement ps = null;
		try{
			ps = conn.prepareStatement(sql);

			ps.setString(1,fmVo.getArtist());
			ps.setString(2,fmVo.getTitle());
			ps.setString(3,fmVo.getHbiDte());
			ps.setString(4,fmVo.getKetCod());
			ps.setString(5,fmVo.getPrsMkrCod());
			ps.setString(6,fmVo.getJytPrsMkr());
			ps.setString(7,fmVo.getHukSizCod());
			ps.setString(8,"U");
			ps.setInt(9,upddte);
			ps.setInt(10,updjkk);

			ps.setString(11,fmVo.getDaiKaiSkbCod());
			ps.setString(12,fmVo.getKaiSkbCod());
			ps.setString(13,fmVo.getKigoBan());

			ps.executeUpdate();

		}finally{
			if(ps != null)
				ps.close();
		}
	}

//=====  �폜����  ===========================================================

	public void delete(ArticleNumberVO fmVo,int upddte,int updjkk)throws SQLException{

		String sql = " DELETE FROM FTBHIN02 "
					+ " WHERE DAIKAISKBCOD = ? AND KAISKBCOD = ? AND KIGBNG = ? "
					;

//		System.out.println("sql : "+sql);

//			System.out.println(fmVo.getDaiKaiSkbCod());
//			System.out.println(fmVo.getKaiSkbCod());
//			System.out.println(fmVo.getKigoBan());
					
		PreparedStatement ps = null;
		try{

			ps = conn.prepareStatement(sql);
			
			ps.setString(1,fmVo.getDaiKaiSkbCod());
			ps.setString(2,fmVo.getKaiSkbCod());
			ps.setString(3,fmVo.getKigoBan());

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
	public static final int CANNOT_DELETE = 5;
	public static final int EXIST_HAC02 = 6;

//-----  �o�^�p��������  --------------------------------------------------------
	public int selectForUpdate(ArticleNumberVO fmVo)throws SQLException{

		String sql = " SELECT UPDKBN,UPDDTE,UPDJKK "
					+ " FROM FTBHIN01 "
					+ " WHERE DAIKAISKBCOD = '" + fmVo.getDaiKaiSkbCod() + "'"
					+ " AND KIGBNG = '" + fmVo.getKigoBan() + "'"
					;

//		System.out.println("sql : "+sql);

		ResultSet rs = null;
		PreparedStatement ps = null;
		try{

			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			if(rs.next()){
				String updKbn = rs.getString("UPDKBN");
				int updDte = rs.getInt("UPDDTE");
				int updJkk = rs.getInt("UPDJKK");

				if(updKbn.trim().equals("D")){
					return LOGICAL_DELETE;
				}

				//������������UPDDTE�ȂǂƍX�V�O������UPDDTE���قȂ�ꍇ
				if(updDte != fmVo.getUpddte() || updJkk != fmVo.getUpdjkk()){
					return MODIFIED;
				}
				
				return NOT_MODIFIED;
			}
			
		}finally{
			if(rs != null)
				rs.close();
			if(ps != null)
				ps.close();
		}
		try{	

			sql = " SELECT UPDKBN,UPDDTE,UPDJKK "
				+ " FROM FTBHIN02 "
				+ " WHERE DAIKAISKBCOD = '" + fmVo.getDaiKaiSkbCod() + "'"
				+ " AND KIGBNG = '" + fmVo.getKigoBan() + "'"
				;

			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			if(rs.next()){
				String updKbn = rs.getString("UPDKBN");
				int updDte = rs.getInt("UPDDTE");
				int updJkk = rs.getInt("UPDJKK");

				if(updKbn.trim().equals("D")){
					return LOGICAL_DELETE;
				}

				//������������UPDDTE�ȂǂƍX�V�O������UPDDTE���قȂ�ꍇ
				if(updDte != fmVo.getUpddte() || updJkk != fmVo.getUpdjkk()){
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

//-----  �X�V�p��������  ---------------------------//03/09/11 �ǉ�
	public int UselectForUpdate(ArticleNumberVO fmVo)throws SQLException{

//		System.out.println("�X�V�E�폜�p  _selectForUpdate  �J�n");

		String sql;
//		if(fmVo.getDbName().equals("�i�ԃ}�X�^�[")){
		if(fmVo.getDbName().equals("HIN01")){//2003/10/07 �C�� okada
				sql = " SELECT UPDKBN,UPDDTE,UPDJKK "
					+ " FROM FTBHIN01 "
					+ " WHERE DAIKAISKBCOD = '" + fmVo.getDaiKaiSkbCod() + "'"
					+ " AND KIGBNG = '" + fmVo.getKigoBan() + "'"
					;
		}else{
				sql = " SELECT UPDKBN,UPDDTE,UPDJKK "
//					+ " FROM FTBHIN01 "
					+ " FROM FTBHIN02 "//2003/10/07 �C�� okada
					+ " WHERE DAIKAISKBCOD = '" + fmVo.getDaiKaiSkbCod() + "'"
					+ " AND KIGBNG = '" + fmVo.getKigoBan() + "'"
					;
		}



		ResultSet rs = null;
		PreparedStatement ps = null;
		try{

				ps = conn.prepareStatement(sql);

				rs = ps.executeQuery();

				if(rs.next()){
					String updKbn = rs.getString("UPDKBN");
					int updDte = rs.getInt("UPDDTE");
					int updJkk = rs.getInt("UPDJKK");

					if(updKbn.trim().equals("D")){
						return LOGICAL_DELETE;
					}

					//������������UPDDTE�ȂǂƍX�V�O������UPDDTE���قȂ�ꍇ
					if(updDte != fmVo.getUpddte() || updJkk != fmVo.getUpdjkk()){
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

//-----  �폜�p��������  --------------------------------------------------------
	public int DselectForUpdate(ArticleNumberVO fmVo)throws SQLException{

//		System.out.println("�X�V�E�폜�p  _selectForUpdate  �J�n");

		String sql = " SELECT UPDKBN,UPDDTE,UPDJKK "
					+ " FROM FTBHIN01 "
					+ " WHERE DAIKAISKBCOD = '" + fmVo.getDaiKaiSkbCod() + "'"
					+ " AND KIGBNG = '" + fmVo.getKigoBan() + "'"
					;


//		System.out.println("DAO selectForUpdate HIN01");
//		System.out.println("sql : "+sql);

		ResultSet rs = null;
		PreparedStatement ps = null;
		try{

			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			if(rs.next()){
				return CANNOT_DELETE;

			}
			
		}finally{
			if(rs != null)
				rs.close();
			if(ps != null)
				ps.close();
		}
		try{
			
			sql = " SELECT UPDKBN,UPDDTE,UPDJKK "
				+ " FROM FTBHIN02 "
				+ " WHERE DAIKAISKBCOD = '" + fmVo.getDaiKaiSkbCod() + "'"
				+ " AND KIGBNG = '" + fmVo.getKigoBan() + "'"
				;

//		System.out.println("DAO selectForUpdate HIN02");
//		System.out.println("sql : "+sql);
		
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
	
	
			if(rs.next()){
				String updKbn = rs.getString("UPDKBN");
				int updDte = rs.getInt("UPDDTE");
				int updJkk = rs.getInt("UPDJKK");

				if(updKbn.trim().equals("D")){
					return LOGICAL_DELETE;
				}

				//������������UPDDTE�ȂǂƍX�V�O������UPDDTE���قȂ�ꍇ
				if(updDte != fmVo.getUpddte() || updJkk != fmVo.getUpdjkk()){
					return MODIFIED;
				}
// 20100812 DEL START
//				return NOT_MODIFIED;
// 20100812 DEL END

			}else{
				return NOT_EXIST;
			}

		}finally{
			if(rs != null)
				rs.close();
			if(ps != null)
				ps.close();
		}
		// 20100812 ADD START
		// ���������e�[�u���ɑΏەi�Ԃ����݂���ꍇ�͍폜�s��
		try{
			
			sql = " SELECT DAIKAISKBCOD,KIGBNG "
				+ " FROM FTBHAC02 "
				+ " WHERE DAIKAISKBCOD = '" + fmVo.getDaiKaiSkbCod() + "'"
				+ " AND KIGBNG = '" + fmVo.getKigoBan() + "'"
				+ " GROUP BY DAIKAISKBCOD,KIGBNG "
				;

			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
	
			if(rs.next()){
				// �폜�s��
				return EXIST_HAC02;
			}else{
				// �폜�\
				return NOT_MODIFIED;
			}

		}finally{
			if(rs != null)
				rs.close();
			if(ps != null)
				ps.close();
		}
		// 20100812 ADD END
	}

}
