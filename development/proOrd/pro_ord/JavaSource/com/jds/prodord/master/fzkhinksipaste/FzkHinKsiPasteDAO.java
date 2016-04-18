package com.jds.prodord.master.fzkhinksipaste;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.jds.prodord.common.CommonDAO;
//import com.jds.prodord.order.prsorder.PrsOrderVO;
/**
* FzkHinKsiPasteDAO  �t���i�\���}�X�^�[�ꊇ�\��t��  �f�[�^�A�N�Z�X�I�u�W�F�N�g�N���X
*
*	�쐬��    2007/09/30
*	�쐬��    �i�m�h�h�j�c��
* �����T�v    �t���i�\���}�X�^�[�iFTBMST08�j�ɃA�N�Z�X����N���X�B
* 
*	[�ύX����]
*/

public class FzkHinKsiPasteDAO extends CommonDAO{

	
	public FzkHinKsiPasteDAO() throws SQLException{
		super();
	}

	
//**�i�ԑ��݃`�F�b�N******************************************************************

//	public FzkHinKsiPasteVO getHinData(String daiKaiSkbCod, String kigBng, String tblNm)throws SQLException{	

//		PreparedStatement ps = null;
//		ResultSet rs = null;
//		FzkHinKsiPasteVO fmVO = new FzkHinKsiPasteVO();

//		String query = "SELECT H.DAIKAISKBCOD,H.KAISKBCOD,H.KIGBNG,H.HJIHNB,H.ARTKJ,H.TITKJ,H.KETCOD,"
//					+ "H.HBIDTE,H.TOMRAK,H.PRSMKRCOD,H.FUKSZIMKR,H.SETSUU,H.JYTPRSMKR,VALUE(K.KETNMKJ,G'') AS KETNMKJ"
//					+ " ,H.ZEIKMITKA FROM "+tblNm+" H" 
//					+ " LEFT OUTER JOIN FTBMST06 K ON"
//					+ " H.KETCOD = K.KETCOD"
//					+ " WHERE H.DAIKAISKBCOD = ? "
//					+ " AND K.DAIKAISKBCOD = ? "
//					+ " AND H.KIGBNG = ? "
//					+ " AND H.UPDKBN <> 'D' ";

//		try{
//			ps = conn.prepareStatement(query);
//			ps.setString(1, daiKaiSkbCod);
//			ps.setString(2, daiKaiSkbCod);
//			ps.setString(3, kigBng);
//			rs = ps.executeQuery();

//			if(rs.next()){
//				fmVO.setDaiKaiSkbCod(rs.getString("DAIKAISKBCOD").trim());
//				fmVO.setKaiSkbCod(rs.getString("KAISKBCOD").trim());
//				fmVO.setKigBng(rs.getString("KIGBNG").trim());
				
//				fmVO.setExsitHin01(true);
				
//			}
//		}finally{
//			if(rs != null)
//				rs.close();
//			if(ps != null)
//				ps.close();
//		}
		
//		return fmVO;
//	}

//	**�i�ԑ��݃`�F�b�N******************************************************************

	  public boolean hasKigBng(String daiKaiSkbCod, String kigBng, String tblNm)throws SQLException{	

		  PreparedStatement ps = null;
		  ResultSet rs = null;
		  FzkHinKsiPasteVO fmVO = new FzkHinKsiPasteVO();

		  String query = "SELECT H.DAIKAISKBCOD,H.KAISKBCOD,H.KIGBNG,H.HJIHNB,H.ARTKJ,H.TITKJ,H.KETCOD,"
					  + "H.HBIDTE,H.TOMRAK,H.PRSMKRCOD,H.FUKSZIMKR,H.SETSUU,H.JYTPRSMKR,VALUE(K.KETNMKJ,G'') AS KETNMKJ"
					  + " ,H.ZEIKMITKA FROM "+tblNm+" H" 
					  + " LEFT OUTER JOIN FTBMST06 K ON"
					  + " H.KETCOD = K.KETCOD"
					  + " WHERE H.DAIKAISKBCOD = ? "
					  + " AND K.DAIKAISKBCOD = ? "
					  + " AND H.KIGBNG = ? "
					  + " AND H.UPDKBN <> 'D' ";

		  try{
			  ps = conn.prepareStatement(query);
			  ps.setString(1, daiKaiSkbCod);
			  ps.setString(2, daiKaiSkbCod);
			  ps.setString(3, kigBng);
			  rs = ps.executeQuery();

			  if(rs.next()){
				  fmVO.setDaiKaiSkbCod(rs.getString("DAIKAISKBCOD").trim());
				  fmVO.setKaiSkbCod(rs.getString("KAISKBCOD").trim());
				  fmVO.setKigBng(rs.getString("KIGBNG").trim());
				
//				  fmVO.setExsitHin01(true);
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
	
	//-----  �����ރR�[�h���݃`�F�b�N  ----------------------------------------

	public boolean hasFukSziCod(FzkHinKsiPasteVO fmVo, String fukSziCod)throws SQLException{
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

	public boolean hassirSkCod(FzkHinKsiPasteVO fmVo, String sirsk)throws SQLException{
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

	public static final int NOT_MODIFIED = 1;
	public static final int MODIFIED = 2;
	public static final int NOT_EXIST = 3;
	public static final int LOGICAL_DELETE = 4;	

	//-----  �t���i�\���}�X�^�[���݃`�F�b�N  ----------------------------------------

	public boolean selectForUpdate(FzkHinKsiPasteVO fmVo, String kigbng)throws SQLException{
		ResultSet rs = null;
		Statement stmt = null;

		String sql = "SELECT UPDKBN,UPDDTE,UPDJKK FROM FTBMST08 "
				+ " WHERE DAIKAISKBCOD = '" + fmVo.getDaiKaiSkbCod() + "'"
				+ " AND KAISKBCOD = '" + fmVo.getQueryKaiSkbCod() + "'"
				+ " AND KIGBNG = '" + kigbng + "'"
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
					return false;
				}


				//������������UPDDTE�ȂǂƍX�V�O������UPDDTE���قȂ�ꍇ
//				if(updDte != fmVo.getUpdDte() || updJkk != fmVo.getUpdJkk()){
//					return MODIFIED;
//				}
				return false;			
			}else{
				return true;
			}
			
		}finally{
			if(rs != null)
				rs.close();
			if(stmt != null)
				stmt.close();
		}			
	}

	/**
	 * �o�^
	 * 
	 */
	public void insert(FzkHinKsiPasteVO fmVo,int upddte,int updjkk)throws SQLException{
		String sql = "INSERT INTO FTBMST08 (DAIKAISKBCOD,KAISKBCOD,KIGBNG,"
					 + "FUKTBL,SIRTBL,UPDKBN,UPDDTE,UPDJKK) "
					 + " VALUES (?,?,?,?,?,?,?,?)";
			
		PreparedStatement ps = null;
		try{
			ps = conn.prepareStatement(sql);
	
			ps.setString(1,fmVo.getDaiKaiSkbCod());
			ps.setString(2,fmVo.getKaiSkbCod());
			ps.setString(3,fmVo.getKigBng());
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


}

