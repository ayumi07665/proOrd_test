/**
* TeiansuuReferDelegate  ��Đ��Ɖ�w�����s�N���X
*
*	�쐬��    2003/02/20
*	�쐬��    �i�m�h�h�j���c �Ĕ�
* �����T�v    ��Đ��Ɖ�w�������s����N���X�B
*
*	[�ύX����]
*		  2006/05/10�i�m�h�h�j�c�[ �N��
*			�E�j�Ђ̃����N�����ύX�Ή�
*/

package com.jds.prodord.indicate.teiansuurefer;


import com.jds.prodord.common.SystemException;

import java.sql.*;
import java.util.*;


public class TeiansuuReferDelegate {

	/**
	 * ��Đ��Ɖ�w��
	 * 
	 */
	public TeiansuuReferResult doTeianSyokaiShiji(TeiansuuReferVO fmVO)throws SQLException{
		TeiansuuReferDAO fmDao = null;
		TeiansuuReferResult result = new TeiansuuReferResult(fmVO,true,"");
		
		

		boolean endTransaction = false;
		try{
			fmDao = new TeiansuuReferDAO();
			fmDao.setAutoCommit(false); //DB�̃g�����U�N�V�����J�n

			
			boolean error = false;
			ArrayList errors_kig = new ArrayList();
			ArrayList kig_arr = fmVO.getKigBng_arr();
			
			//�i�ԑ��݃`�F�b�N
			for(int i = 0; i < kig_arr.size(); i++){
				int errtype_kig = fmDao.hasKigBng(fmVO,kig_arr.get(i)+"");
				if(errtype_kig != TeiansuuReferDAO.EXIST){
					String[] err = {String.valueOf(i+1),errtype_kig+""};//[0]�F�G���[�̂���index,[1]�F�G���[�̎��
					errors_kig.add(err);
				    error = true;
			    }
			}
			//�G���[�������
			if(error){
				result = new TeiansuuReferResult(fmVO,false,"");
				if(errors_kig.size() > 0){
					result.setMap(TeiansuuReferResult.KEY_KIGBNG,errors_kig);
				}				
			}
			//�G���[���Ȃ����
			if(!error){
				//����ɐ���
				result = new TeiansuuReferResult(fmVO,true,"");
			}

		}finally{
			if(fmDao != null){
				try{
					fmDao.commit();
					fmDao.close();
				}catch(SQLException sqle2){
					SystemException se = new SystemException(sqle2);
					se.printStackTrace();
				}
			}
		}
		return result;
		
	}

}

