/**
* SubMateStockDAO  �����ލ݌Ƀ����e�i���X  �f�[�^�A�N�Z�X�I�u�W�F�N�g�N���X
*
*	�쐬��    2003/08/18
*	�쐬��    �i�m�h�h�j����  ����
* �����T�v    �����e�[�u���iFTBHIN12,,FTBHIN01,FTBFUK01�j�ɃA�N�Z�X����N���X�B
* 
*	[�ύX����]
*	�ύX��    2003/09/18
*	�ύX��    �i�m�h�h�j����  ���� 
*      �ύX���e  �������ڂɃT���v���敪��ǉ�
* 
*   �ύX��    2003/11/19
*	�ύX��    �i�m�h�h�j���c  �Ĕ� 
*      �ύX���e  �X�V�E�X�V�O�������̏����ɁA�T���v���敪�������Ă��Ȃ������̂��C��
* 		 2004/03/29 (NII) �X
* 			�E�i�ԃ}�X�^�̃e�[�u�����ύX
*/ 

package com.jds.prodord.master.submatestock;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.jds.prodord.common.CommonDAO;
import com.jds.prodord.common.StringUtils;

 public class SubMateStockDAO extends CommonDAO{


	public SubMateStockDAO() throws SQLException{
		super();
	}

//-----  �Ɖ��  -------------------------------------------------------------------

	public SubMateStockVO[] find(SubMateStockVO fmVO)throws SQLException{

		String sql = " SELECT A.KIGBNG,A.SMPKBN,A.FUKSZICOD,A.FUKSZISUU, " 
					//�i�ԃ}�X�^�ɗL���Ε\���i�ԁA�J�X�^���i�Ԃ̏ꍇ�́A�\���i��or�L���ԍ�
				+ "(CASE WHEN B.HJIHNB IS NOT NULL THEN B.HJIHNB " +
						"WHEN (B.HJIHNB IS NULL AND D.HJIHNB IS NOT NULL AND TRIM(D.HJIHNB)<> '') THEN D.HJIHNB " +
						"WHEN (B.HJIHNB IS NULL AND D.HJIHNB IS NOT NULL) THEN D.KIGBNG " +
						"ELSE A.KIGBNG END) HJIHNB," 
				+ "(CASE WHEN B.SRYKIG IS NOT NULL THEN B.SRYKIG WHEN (B.SRYKIG IS NULL AND D.SRYKIG IS NOT NULL) THEN D.SRYKIG ELSE A.KIGBNG END) SRYKIG," 
				+ " VALUE(C.FUKSZINMKJ,G'') FUKSZINMKJ,A.UPDDTE,A.UPDJKK "
				+ " FROM FTBHIN12 A "

					+ " LEFT OUTER JOIN FTBHIN01 B ON "
					+ " A.DAIKAISKBCOD = B.DAIKAISKBCOD AND "
					+ " A.KAISKBCOD = B.KAISKBCOD AND "
					+ " A.KIGBNG = B.KIGBNG "

					+ " LEFT OUTER JOIN FTBHIN02 D ON "
					+ " A.DAIKAISKBCOD = D.DAIKAISKBCOD AND "
					+ " A.KAISKBCOD = D.KAISKBCOD AND "
					+ " A.KIGBNG = D.KIGBNG "

					+ " LEFT OUTER JOIN FTBFUK01 C ON "
					+ " A.DAIKAISKBCOD = C.DAIKAISKBCOD AND "
					+ " A.KAISKBCOD = C.KAISKBCOD AND "
					+ " A.FUKSZICOD = C.FUKSZICOD "

					+ " WHERE A.DAIKAISKBCOD = '"+fmVO.getDaiKaiSkbCod()+"'"
					+ " AND A.SMPKBN = '"+fmVO.getSmpKbn()+"'"
					;

		for (int i=0 ; i<fmVO.getQueryKaiSkbCodList().size(); i++){
			if(i == 0){
				sql +=  " AND A.KAISKBCOD IN ('";
			}
			sql += fmVO.getQueryKaiSkbCodList().get(i).toString();

			if(i == fmVO.getQueryKaiSkbCodList().size() - 1){
				sql  += "')";
			}else{
				sql  += "','";
			}
		}

		for (int i=0 ; i<fmVO.getKigoBan_arr().size(); i++){
			if(i == 0){
				sql +=  " AND A.KIGBNG IN ('";
			}
			sql += fmVO.getKigoBan_arr().get(i).toString();

			if(i == fmVO.getKigoBan_arr().size() - 1){
				sql  += "')";
			}else{
				sql  += "','";
			}
		}

				sql += " ORDER BY A.KIGBNG,A.FUKSZICOD";


		List lis = new ArrayList();
		ResultSet rs = null;
		Statement stmt = null;

	try{
				stmt = conn.createStatement();
				rs = stmt.executeQuery(sql);

				while(rs.next()){
					SubMateStockVO resultVO = new SubMateStockVO();

					resultVO.setHidDaiKaiSkbCod(fmVO.getDaiKaiSkbCod());
					resultVO.setSmpKbn(rs.getString("SMPKBN").trim()); //2003/09/18
					resultVO.setKigoBan(rs.getString("KIGBNG").trim());
					resultVO.setHjiHnb(rs.getString("HJIHNB").trim());
					resultVO.setHuksizaiCod(rs.getString("FUKSZICOD").trim());
					resultVO.setHuksizaiMei(StringUtils.removeSuffix(rs.getString("FUKSZINMKJ"),"�@"));
					resultVO.setHuksizaiZaiko(rs.getString("FUKSZISUU").trim());
					resultVO.setTeiseiSuu("");
					resultVO.setHidUpdDte(rs.getInt("UPDDTE"));
					resultVO.setHidUpdJkk(rs.getInt("UPDJKK"));

					lis.add(resultVO);
				}

		}finally{
			if(rs != null)
				rs.close();
			if(stmt != null)
				stmt.close();
		}

		return (SubMateStockVO[])lis.toArray(new SubMateStockVO[lis.size()]);
	}
//-----  �X�V����  --------------------------------------------------------------

	public void update(SubMateStockVO fmVo,int upddte,int updjkk)throws SQLException{
		String sql = "UPDATE FTBHIN12 SET FUKSZISUU = ? "
					+ " ,UPDKBN = ? ,UPDDTE = ? ,UPDJKK = ? "
					+ " WHERE DAIKAISKBCOD = ? AND KIGBNG = ? AND FUKSZICOD = ? AND SMPKBN = ?"//2003/11/19 SMPKBN �ǉ�
					;

		PreparedStatement ps = null;
		try{
			ps = conn.prepareStatement(sql);

			ps.setString(1,fmVo.getHuksizaiZaiko());
			ps.setString(2,"U");
			ps.setInt(3,upddte);
			ps.setInt(4,updjkk);
			ps.setString(5,fmVo.getHidDaiKaiSkbCod());
			ps.setString(6,fmVo.getKigoBan());
			ps.setString(7,fmVo.getHuksizaiCod());
			ps.setString(8,fmVo.getSmpKbn());

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

//-----  �X�V�E�폜�p��������  --------------------------------------------------------
	public int selectForUpdate(SubMateStockVO fmVo)throws SQLException{

		String sql = "SELECT UPDKBN,UPDDTE,UPDJKK "
					+ " FROM FTBHIN12 "
					+ " WHERE DAIKAISKBCOD = ? AND KIGBNG = ? AND FUKSZICOD = ? AND SMPKBN = ?"//2003/11/19 SMPKBN �ǉ�
					;

		ResultSet rs = null;
		PreparedStatement ps = null;
		try{

			ps = conn.prepareStatement(sql);

			ps.setString(1,fmVo.getHidDaiKaiSkbCod());
			ps.setString(2,fmVo.getKigoBan());
			ps.setString(3,fmVo.getHuksizaiCod());
			ps.setString(4,fmVo.getSmpKbn());

			rs = ps.executeQuery();


			if(rs.next()){
				String updKbn = rs.getString("UPDKBN");
				int updDte = rs.getInt("UPDDTE");
				int updJkk = rs.getInt("UPDJKK");

				if(updKbn.trim().equals("D")){
					return LOGICAL_DELETE;
				}

		//������������UPDDTE�ȂǂƍX�V�O������UPDDTE���قȂ�ꍇ
				if(updDte != fmVo.getHidUpdDte() || updJkk != fmVo.getHidUpdJkk()){
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
