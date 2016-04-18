/**
* IndicatePrintDAO  �������o�͎w���f�[�^�A�N�Z�X�I�u�W�F�N�g�N���X
*
*	�쐬��    2003/04/23
*	�쐬��    �i�m�h�h�j���c �Ĕ�
* �����T�v    ������}�X�^�[�iFTBMST03�j�A���������iFTBHAC02�j�A�������f�[�^�iFTBHAC01�j�ɃA�N�Z�X����N���X�B
* 
*	[�ύX����]
*       2003/06/05�i�m�h�h�j�g�c �h�q
* 			�E�u�ďo�́v�̏������ځi���������t�A������R�[�h�j�ǉ��B
* 		2003/07/07
* 			�E�f�[�^�����t���O�̃Z�b�g�ǉ��B
* 		2003/08/26 �i�m�h�h�j���c �Ĕ�
* 			�E�V�i�ԃ}�X�^�[(HIN02)�Ή� �ǉ�
* 		2004/03/04 (�m�h�h�j�X
* 			�EVAP�̂Ƃ��͕K�������ރR�[�h���\�������悤�ɏC��
* 		2004/03/29 (NII) �X
* 			�E�i�ԃ}�X�^�̃e�[�u�����ύX
* 		2005/05/23�i�m�h�h�j�g�c
* 			�E�������ꊇ�o�͑Ή��i���[�U�[�h�c�̒ǉ��j
* 		2005/09/09�i�m�h�h�j�g�c
* 			�EVAP�̏ꍇ�A�����޺��ޕ����g�p�ɂ�蕛���ޖ��̎擾���A�����޺��ނ������ɒǉ�
*
*/

package com.jds.prodord.indicate.indicateprint;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.jds.prodord.common.CommonDAO;
import com.jds.prodord.common.DataFormatUtils;

public class IndicatePrintDAO extends CommonDAO{

	
	public IndicatePrintDAO() throws SQLException{
    	super();
	}
	

	//������������***********************************************************************
	
	public IndicatePrintVO[] findHac02(IndicatePrintVO queryVO, boolean isSaiSyryk)throws SQLException{

		String sql = "SELECT KAISKBCOD,HACSYODTE,HACSYOBNG,SYRSUU,SEQNO,GYOBNG,HACCOD,"
					+"PRSFUKSZICOD "
					+ " FROM FTBHAC02"
					+ " WHERE DAIKAISKBCOD = '" + queryVO.getDaiKaiSkbCod() + "'"
					;
					

		//��Ў��ʃR�[�h
        for(int i=0; i<queryVO.getQueryKaiSkbCodList().size(); i++){
        	if(i == 0){
        		sql +=  " AND KAISKBCOD IN ('";
        	}
        	sql += queryVO.getQueryKaiSkbCodList().get(i).toString();
          
        	if(i == queryVO.getQueryKaiSkbCodList().size() - 1){
        		sql  += "')";
        	}else{
        		sql  += "','";
        	}
        }

        //�`�[���s
        if(!isSaiSyryk){
            //������R�[�h
    		DataFormatUtils.removeBlankElement(queryVO.getHacCod_arr());
            for(int i=0; i<queryVO.getHacCod_arr().size(); i++){
            	if(i == 0){
            		sql +=  " AND HACCOD IN ('";
            	}
            	sql += queryVO.getHacCod_arr().get(i).toString();
              
            	if(i == queryVO.getHacCod_arr().size() - 1){
            		sql  += "')";
            	}else{
            		sql  += "','";
            	}
            }
        	
        	//�������ԍ�(�u�����N�̂���)
        	sql +=  " AND HACSYOBNG = ''";

        //�Ĕ��s
        }else{
            //������R�[�h
            if(!queryVO.getHacCod().equals("")){
            	sql +=  " AND HACCOD = '" + queryVO.getHacCod() +"'";
            }

        	//�������ԍ�(���͒l���u�����N�ȊO)
        	sql +=  " AND HACSYOBNG <> ''";
	        if(!queryVO.getHacSyoBng_to().equals("")){
	        	sql +=  " AND HACSYOBNG BETWEEN '" + DataFormatUtils.formatHacSyoBng(queryVO.getHacSyoBng_frm()) +"' AND '" + DataFormatUtils.formatHacSyoBng(queryVO.getHacSyoBng_to()) + "'";
	        }else{
	        	if(!queryVO.getHacSyoBng_frm().equals(""))
	        		sql +=  " AND HACSYOBNG = '" + DataFormatUtils.formatHacSyoBng(queryVO.getHacSyoBng_frm()) + "'";
	        }

	        //2003/06/05
			//���������t
			if(!queryVO.getHacSyoDte_to().equals("")){
	        	sql +=  " AND HACSYODTE BETWEEN " + queryVO.getHacSyoDte_frm() +" AND " + queryVO.getHacSyoDte_to() + "";
	        }else{
	        	if(!queryVO.getHacSyoDte_frm().equals(""))
	        		sql +=  " AND HACSYODTE = " + queryVO.getHacSyoDte_frm() + "";
	        }
			//end
        }
		   
		sql +=  " AND USRID = '" + queryVO.getUsrId() +"'";//2005/05/19 USRID add
         
        sql +=  " ORDER BY HACCOD, HACSYODTE, HACSYOBNG, GYOBNG";
			
		ArrayList finded_Arr = new ArrayList();
		ResultSet rs = null;
		Statement stmt = null;
		try{
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);			
			while(rs.next()){
				IndicatePrintVO fmVO = new IndicatePrintVO();
				fmVO.setDaiKaiSkbCod(queryVO.getDaiKaiSkbCod());
				fmVO.setKaiSkbCod(rs.getString("KAISKBCOD").trim());
				fmVO.setHacSyoDte(rs.getString("HACSYODTE").trim());
				fmVO.setHacSyoBng(rs.getString("HACSYOBNG").trim());
				fmVO.setGyoBng(rs.getString("GYOBNG").trim());
				fmVO.setHacCod(rs.getString("HACCOD").trim());
				fmVO.setSyrSuu(rs.getString("SYRSUU").trim());
				fmVO.setSeqNo(rs.getString("SEQNO").trim());
				fmVO.setUsrId(queryVO.getUsrId());//2005/05/23 add
				fmVO.setFukSziCod(rs.getString("PRSFUKSZICOD").trim());//2005/09/09 add
				finded_Arr.add(fmVO);
			}
		}finally{
			if(rs != null)
				rs.close();
			if(stmt != null)
				stmt.close();
		}
		return (IndicatePrintVO[])finded_Arr.toArray(new IndicatePrintVO[finded_Arr.size()]);
							
	}			
	
	//�������f�[�^���݃`�F�b�N****************************************************************
	
	public boolean hasHac01(IndicatePrintVO fmVO)throws SQLException{
		
		String sql = "SELECT KIGBNG FROM FTBHAC01 "
					+ " WHERE DAIKAISKBCOD = ? AND KAISKBCOD = ? AND HACSYODTE = ?"
					+ " AND HACSYOBNG = ? AND SYRSUU = ? AND SEQNO = ? AND GYOBNG = ?"
					+ " AND USRID = ?";

		ResultSet rs = null;	
		PreparedStatement ps = null;	
		
		try{
			ps = conn.prepareStatement(sql);
			
			ps.setString(1,fmVO.getDaiKaiSkbCod());
			ps.setString(2,fmVO.getKaiSkbCod());
			ps.setString(3,fmVO.getHacSyoDte());
			ps.setString(4,DataFormatUtils.formatHacSyoBng(fmVO.getHacSyoBng()));
			ps.setString(5,fmVO.getSyrSuu());
			ps.setString(6,fmVO.getSeqNo());
			ps.setString(7,DataFormatUtils.formatGyoBng(fmVO.getGyoBng()));
			ps.setString(8,fmVO.getUsrId());//2005/05/19 add

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
	
	//���������e�[�u���X�V*******************************************************************

	public void updateHac02(IndicatePrintVO fmVO,int upddte,int updjkk)throws SQLException{
		String sql = "UPDATE FTBHAC02 SET SYRZMISGN = ?,UPDKBN = ?,UPDDTE = ?,UPDJKK = ?"
					 + " WHERE DAIKAISKBCOD = ? AND KAISKBCOD = ?"
				     + " AND HACSYODTE = ? AND SYRSUU = ? AND SEQNO = ?";

		PreparedStatement ps = null;
		try{
			ps = conn.prepareStatement(sql);
	
			ps.setString(1,"1");
			ps.setString(2,"U");
			ps.setInt(3,upddte);
			ps.setInt(4,updjkk);
			ps.setString(5,fmVO.getDaiKaiSkbCod());
			ps.setString(6,fmVO.getKaiSkbCod());
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




