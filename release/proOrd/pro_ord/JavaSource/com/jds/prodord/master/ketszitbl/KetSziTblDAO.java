/**
* KetSziTbl  �`�ԕʍ\�����ރ}�X�^�[�����e�i���X  �f�[�^�A�N�Z�X�I�u�W�F�N�g�N���X
*
*	�쐬��    2004/02/03
*	�쐬��    �i�m�h�h�j�X
* �����T�v    �`�Ԗ��̃}�X�^�[�iFTBMST06�j�ɃA�N�Z�X����N���X�B
* 
*	[�ύX����]
*
*/

package com.jds.prodord.master.ketszitbl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.jds.prodord.common.CommonDAO;
import com.jds.prodord.common.StringUtils;

public class KetSziTblDAO extends CommonDAO{

	
	public KetSziTblDAO() throws SQLException{
    	super();
	}


	/**
	 * �Ɖ�
	 * 
	 */
	public KetSziTblVO[] find(KetSziTblVO fmVO)throws SQLException{

		String sql = "SELECT KETCOD,KETNMKJ,UPDDTE,UPDJKK,KETNMKJ2,"
					+ "SZITBL FROM FTBMST06 "
					+ " WHERE DAIKAISKBCOD = '" + fmVO.getQueryKaiSkbCod() + "'"; //(FTBMST06�ł�)��\��Ё����[�U�[�̉��
		//�`�ԃR�[�h
		if((!fmVO.getKetCod().equals("")) || (fmVO.getKetCodList() != null)){
			for(int i=0; i<fmVO.getKetCodList().length; i++){
				if(i == 0){
					sql +=  " AND ";
					sql +=  "KETCOD IN ('";
		  	  	}
		  		sql += fmVO.getKetCodList()[i].toString();
		  		if(i == fmVO.getKetCodList().length - 1){
					sql  += "')";
				  }else{
					sql  += "','";
		  		}
			}
		}	   
			sql	+= " AND UPDKBN <> 'D' ";
			sql += " ORDER BY KETCOD";

		List lis = new ArrayList();

		ResultSet rs = null;
		Statement stmt = null;
		try{
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next()){
				KetSziTblVO resultVO = new KetSziTblVO();
				resultVO.setDaiKaiSkbCod(fmVO.getDaiKaiSkbCod());
				resultVO.setQueryKaiSkbCod(fmVO.getQueryKaiSkbCod());
				resultVO.setKetCod(rs.getString("KETCOD").trim());
				resultVO.setKetNm(StringUtils.removeSuffix(rs.getString("KETNMKJ"),"�@"));
				resultVO.setKetNm2(StringUtils.removeSuffix(rs.getString("KETNMKJ2"),"�@"));
				resultVO.setFuksziCodString(rs.getString("SZITBL").trim());

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
					
		return (KetSziTblVO[])lis.toArray(new KetSziTblVO[lis.size()]);
	
	}
	
	//-----  �����ރR�[�h���݃`�F�b�N  ----------------------------------------

	public boolean hasFukSziCod(KetSziTblVO fmVo, String fukSziCod)throws SQLException{
		if(fukSziCod.equals(""))
			return true;
		
		String sql = " SELECT FUKSZICOD "
					+ " FROM FTBFUK01 "
					+ " WHERE DAIKAISKBCOD = '"+fmVo.getDaiKaiSkbCod()+"'"
					+ " AND FUKSZICOD = '" + fukSziCod + "'";

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
	 * �X�V
	 * 
	 */
	public void update(KetSziTblVO fmVo,int upddte,int updjkk)throws SQLException{
			
		String sql = "UPDATE FTBMST06 SET SZITBL = ? "
					+ " ,UPDKBN = ? ,UPDDTE = ? ,UPDJKK = ? "
					+ " WHERE DAIKAISKBCOD = ? AND KETCOD = ? ";
		
		PreparedStatement ps = null;
		try{
			ps = conn.prepareStatement(sql);

			ps.setString(1,fmVo.getFuksziCodString().trim());
			ps.setString(2,"U");
			ps.setInt(3,upddte);
			ps.setInt(4,updjkk);
	
			ps.setString(5,fmVo.getQueryKaiSkbCod()); //(FTBMST06�ł�)��\��Ё����[�U�[�̉��
			ps.setString(6,fmVo.getKetCod());

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
	 * �X�V�p����
	 * 
	 */
	public int selectForUpdate(KetSziTblVO fmVo)throws SQLException{


		String sql = "SELECT UPDKBN,UPDDTE,UPDJKK FROM FTBMST06 "
					+ " WHERE DAIKAISKBCOD = ? AND KETCOD = ?";


		ResultSet rs = null;
		PreparedStatement ps = null;
		try{
			ps = conn.prepareStatement(sql);

			ps.setString(1,fmVo.getQueryKaiSkbCod());//(FTBMST06�ł�)��\��Ё����[�U�[�̉��
			ps.setString(2,fmVo.getKetCod());
			
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
}

