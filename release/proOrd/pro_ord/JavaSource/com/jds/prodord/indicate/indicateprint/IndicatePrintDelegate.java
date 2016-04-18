/**
* IndicatePrintDelegate  �������o�͎w�����s�N���X
*
*	�쐬��    2003/04/23
*	�쐬��    �i�m�h�h�j���c �Ĕ�
* �����T�v    �������o�͎w�������s����N���X�B
*
*	[�ύX����]
*       2003/06/05�i�m�h�h�j�g�c �h�q
* 			�E������R�[�h���݃`�F�b�N�ǉ��B
* 		2003/07/07
* 			�E�ް������׸ށ��`�[���s�̂Ƃ�"0"�^�ďo�͂̂Ƃ��Ŕ��������Ȃ��ꍇ"1"�B
*/

package com.jds.prodord.indicate.indicateprint;

import com.jds.prodord.common.*;
import java.sql.*;
import java.util.*;


public class IndicatePrintDelegate {

	public static final String NOT_EXIST = "NOT_EXIST";
	public static final String NOT_HACCOD = "NOT_HACCOD";


	/**
	 * �`�[���s
	 * 
	 */
	public IndicatePrintResult[] doDnpHakkou(IndicatePrintVO fmVO)throws SQLException{
		IndicatePrintDAO fmDao = null;
		IndicatePrintResult[] results = new IndicatePrintResult[1];

		boolean endTransaction = false;
		try{
			fmDao = new IndicatePrintDAO();
			fmDao.setAutoCommit(false); //DB�̃g�����U�N�V�����J�n
			
			//�X�V����
			DateUtils dateUtils = new DateUtils();
			int upddte = dateUtils.getDate6Int();
			int updjkk = dateUtils.getTime6Int();
			
			IndicatePrintVO[] finded = null;
			
			boolean error = false;
			ArrayList errors_hac = new ArrayList();
			ArrayList hac_arr = fmVO.getHacCod_arr();
			
			//������R�[�h���݃`�F�b�N
			for(int i = 0; i < hac_arr.size(); i++){
				if(hac_arr.get(i).toString().equals(""))
					continue;
				boolean exist = CheckCommon.hasHacCod(fmVO.getDaiKaiSkbCod(),hac_arr.get(i)+"");
				if(!exist){
					String err = String.valueOf(i+1);//�G���[�̂���index
					errors_hac.add(err);
				    error = true;
			    }
			}
			//�G���[�������
			if(error){
				results[0] = new IndicatePrintResult(fmVO,false,"");
				if(errors_hac.size() > 0){
					results[0].setMap(IndicatePrintResult.KEY_HACCOD,errors_hac);
				}				
			}
			//�G���[���Ȃ����
			if(!error){
				//���������������s
				finded = fmDao.findHac02(fmVO,false);
				if(finded.length == 0){//���ʂ��O����������
					results[0] = new IndicatePrintResult(fmVO,false,NOT_EXIST);
					error = true;
				}
			}
			if(!error){
				results = new IndicatePrintResult[finded.length];
				OrderUtils orderUtils = new OrderUtils(fmDao);
				for(int i = 0;i < finded.length;i++){
					if(finded[i] == null)
						continue;
						
					try{
						//���������̍X�V
						fmDao.updateHac02(finded[i],upddte,updjkk);
						if(!fmDao.hasHac01(finded[i])){
							//2003/07/07 �f�[�^�����t���O��'0'�̃Z�b�g
							int dtaExcFlg = 0;
							//�������f�[�^��������
							orderUtils.insertHac01(
								finded[i].getDaiKaiSkbCod(),//��\���
								finded[i].getKaiSkbCod(),//���
								finded[i].getHacSyoDte(),//���������t
								finded[i].getSyrSuu(),//������
								finded[i].getSeqNo(),//SEQNO
								dtaExcFlg,//�f�[�^�����t���O
								upddte,
								updjkk);
						}
					}catch(SQLException sqle2){
						error = true;
						results[i] = new IndicatePrintResult(finded[i],false,"");
						SystemException se = new SystemException(sqle2);
						se.printStackTrace();
						continue;
					}
					//����ɐ���
					results[i] = new IndicatePrintResult(finded[i],true,"");
				}
			}
			//�G���[�Ȃ烍�[���o�b�N
			if(error){
				fmDao.rollback();
				endTransaction = true;
			//�G���[���Ȃ���΃R�~�b�g
			}else{
				fmDao.commit();
				endTransaction = true;
			}

		}finally{
			if(fmDao != null){
				if(!endTransaction ){//exception ��commit or rollback �ł��Ȃ������Ƃ��� ������ rollback
					try{
						fmDao.rollback();
					}catch(SQLException sqle3){
						SystemException se = new SystemException(sqle3);
						se.printStackTrace();
					}
				}
				try{
					fmDao.close();
				}catch(SQLException sqle2){
					SystemException se = new SystemException(sqle2);
					se.printStackTrace();
				}
			}
		}
		return results;
		
	}

	/**
	 * �ďo��
	 * 
	 */
	public IndicatePrintResult[] doSaiSyryk(IndicatePrintVO fmVO)throws SQLException{
		IndicatePrintDAO fmDao = null;
		IndicatePrintResult[] results = new IndicatePrintResult[1];

		boolean endTransaction = false;
		try{
			fmDao = new IndicatePrintDAO();
			fmDao.setAutoCommit(false); //DB�̃g�����U�N�V�����J�n
			
			//�X�V����
			DateUtils dateUtils = new DateUtils();
			int upddte = dateUtils.getDate6Int();
			int updjkk = dateUtils.getTime6Int();
			
			IndicatePrintVO[] finded = null;
			
			boolean error = false;
			boolean exist = true;
			//������R�[�h���݃`�F�b�N
			if(!fmVO.getHacCod().equals(""))
				exist = CheckCommon.hasHacCod(fmVO.getDaiKaiSkbCod(),fmVO.getHacCod());
			
			if(!exist){
			    results[0] = new IndicatePrintResult(fmVO,false,NOT_HACCOD);
			    error = true;
		    }else{		    
				//���������������s
				finded = fmDao.findHac02(fmVO,true);
	
				if(finded.length == 0){//���ʂ��O����������
					results[0] = new IndicatePrintResult(fmVO,false,NOT_EXIST);
					error = true;
				}
			}
			//�G���[���Ȃ����
			if(!error){
				results = new IndicatePrintResult[finded.length];
				OrderUtils orderUtils = new OrderUtils(fmDao);
				for(int i = 0; i < finded.length; i++){
					if(finded[i] == null)
						continue;
					try{
						//�o�͍ςȂ̂ɔ������f�[�^�����݂��Ȃ�������
						if(!fmDao.hasHac01(finded[i])){
							//2003/07/07 �f�[�^�����t���O��'1'�̃Z�b�g
							int dtaExcFlg = 1;
							//�������f�[�^��������
							orderUtils.insertHac01(
								finded[i].getDaiKaiSkbCod(),//��\���
								finded[i].getKaiSkbCod(),//���
								finded[i].getHacSyoDte(),//���������t
								finded[i].getSyrSuu(),//������
								finded[i].getSeqNo(),//SEQNO
								dtaExcFlg,//�f�[�^�����t���O
								upddte,
								updjkk);
						}
					}catch(SQLException sqle2){
						SystemException se = new SystemException(sqle2);
						se.printStackTrace();
						error = true;
						results[i] = new IndicatePrintResult(finded[i],false,"");
						continue;
					}
				}
				
			}
			//�G���[�Ȃ烍�[���o�b�N
			if(error){
				fmDao.rollback();
				endTransaction = true;
			}else{
				//����ɐ���
				results[0] = new IndicatePrintResult(fmVO,true,"");
				results[0].setFinded_arr(new ArrayList(Arrays.asList(finded)));
				//�G���[���Ȃ���΃R�~�b�g
				fmDao.commit();
				endTransaction = true;
			}
			
		}finally{
			if(fmDao != null){
				if(!endTransaction ){//exception ��commit or rollback �ł��Ȃ������Ƃ��� ������ rollback
					try{
						fmDao.rollback();
					}catch(SQLException sqle3){
						SystemException se = new SystemException(sqle3);
						se.printStackTrace();
					}
				}
				try{
					fmDao.close();
				}catch(SQLException sqle2){
					SystemException se = new SystemException(sqle2);
					se.printStackTrace();
				}
			}
		}
		return results;
		
	}


}

