/**
* TeiansuuReferDAO  ��Đ��Ɖ�w���f�[�^�A�N�Z�X�I�u�W�F�N�g�N���X
*
*	�쐬��    2003/02/20
*	�쐬��    �i�m�h�h�j���c �Ĕ�
* �����T�v    �����e�[�u���iVTBCNL09�j�A��Ѓ}�X�^�[�iFTBKAI01�j�A�i�ԃ}�X�^�[�iFTBHIN01�j�ɃA�N�Z�X����N���X�B
* 
*	[�ύX����]
* 		 2004/03/29 (NII) �X
* 			�E�i�ԃ}�X�^�̃e�[�u�����ύX
*		  2006/05/10�i�m�h�h�j�c�[ �N��
*			�E�j�Ђ̃����N�����ύX�Ή�
* 		
*/

package com.jds.prodord.indicate.teiansuurefer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.jds.prodord.common.CommonDAO;

public class TeiansuuReferDAO extends CommonDAO{

	
	public TeiansuuReferDAO() throws SQLException{
    	super();
	}
	
	public static final int EXIST = 0;//���݂���
	public static final int NOT_EXIST = 1;//���݂��Ȃ�
	public static final int RANK_ERR = 2;//���݂��邪�����N�G���[
//**�i�ԑ��݃`�F�b�N******************************************************************

	public int hasKigBng(TeiansuuReferVO fmVO, String kigBng)throws SQLException{	
		
		String[] RANK = TeiansuuReferForm.makeRank(fmVO.getDaiKaiSkbCod());
			
		if(kigBng.equals(""))
			return EXIST;
		String query = "";
		
		String sql = "SELECT KIGBNG,TOMRAK "
					+ " FROM FTBHIN01 "
					+ " WHERE DAIKAISKBCOD = '" + fmVO.getDaiKaiSkbCod() + "'"
					;
					
		//��Ў��ʃR�[�h
        for(int i=0; i<fmVO.getQueryKaiSkbCodList().size(); i++){
          if(i == 0)
          	sql +=  " AND KAISKBCOD IN ('";
          sql += fmVO.getQueryKaiSkbCodList().get(i).toString();
          if(i == fmVO.getQueryKaiSkbCodList().size() - 1){
            sql  += "')";
          }else{
            sql  += "','";
          }
        }
        
		sql		   += " AND UPDKBN <> 'D' ";
		//�܂��͏ȗ��L���Ō���
		query	= sql + " AND SRYKIG = '" + kigBng + "'";

		ResultSet rs = null;
		Statement stmt = null;
		try{
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			
			if(rs.next()){
				
				boolean err_flg = true;
				String rank = rs.getString("TOMRAK").trim();
				for(int i=0; i<RANK.length; i++){
					if(rank.equals(RANK[i]))
						err_flg = false;
				}
				if(err_flg){
					return RANK_ERR;
				}else{
					kigBng = rs.getString("KIGBNG").trim();//�������ꂽ�L���ԍ����Z�b�g���Ȃ���
					return EXIST;
				}	
			}else{
				//�ȗ��i�ԂŌ������Č��ʂȂ���������A�L���ԍ��Ō������Ȃ���
				query	= sql + " AND KIGBNG = '" + kigBng + "'";
				rs = stmt.executeQuery(query);
				
				if(rs.next()){
					boolean err_flg = true;
					String rank = rs.getString("TOMRAK").trim();
					for(int i=0; i<RANK.length; i++){
						if(rank.equals(RANK[i]))
							err_flg = false;
					}
					if(err_flg){
						return RANK_ERR;
					}else{
						return EXIST;
					}	
				}
				return NOT_EXIST;
			}
			
		}finally{
			if(rs != null)
				rs.close();
			if(stmt != null)
				stmt.close();
		}			

	}


}

