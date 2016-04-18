/**
* NyukoSuuAdjustDelegate  ���ɐ��������s�N���X
*
*	�쐬��    2003/09/30
*	�쐬��    �i�m�h�h�j���c �Ĕ�
* �����T�v    ���ɐ����������s����N���X�B
*
*	[�ύX����]
*       2003/10/28�i�m�h�h�j���c �Ĕ�
* 			�E"���ɐ��ύX�ɂ�銮�["������ǉ��B
*/

package com.jds.prodord.master.nyukosuuadjust;
import com.jds.prodord.common.*;

import java.sql.*;

public class NyukoSuuAdjustDelegate {

	
	/**
	 * �`�ԃ����N�ʏ����}�X�^�[�e�[�u�������e�i���X
	 * 
	 */
	public static final String ANOTHER_USER_UPDATE = "ANOTHER_USER_UPDATE";
	public static final String EXIST = "EXIST";	
	public static final String NOT_EXIST = "NOT_EXIST";
	public static final String LOGICAL_DELETE = "LOGICAL_DELETE";


	/**
	 * ����
	 * 
	 */
	public NyukoSuuAdjustVO[] doFind(NyukoSuuAdjustVO fmVO)throws SQLException{
		NyukoSuuAdjustDAO fmDao = null;

		NyukoSuuAdjustVO[] finded = null;

		try{
			fmDao = new NyukoSuuAdjustDAO();

			finded = fmDao.find(fmVO);

		}finally{
			if(fmDao != null){
				try{
					fmDao.close();
				}catch(SQLException sqle2){
					sqle2.printStackTrace();
				}
			}
		}
		return finded;
	}



	/**
	 * ���ɐ�����
	 * 
	 */
	public NyukoSuuAdjustResult[] doNyukoSuuAdjust(NyukoSuuAdjustVO[] fmVOs)throws SQLException{
		NyukoSuuAdjustDAO fmDao = null;


		NyukoSuuAdjustResult[] results = null;

		boolean endTransaction = false;
		try{
			fmDao = new NyukoSuuAdjustDAO();
			fmDao.setAutoCommit(false);//DB�̃g�����U�N�V�����J�n


			DateUtils dateUtils = new DateUtils();
			int upddte = dateUtils.getDate6Int();
			int updjkk = dateUtils.getTime6Int();
			
			//�܂�����
			results = this.doSelectForUpdate(fmDao,fmVOs);
			
			for(int i = 0; i < results.length;i++){
				//�G���[����������rollback���ďI��
				if((results[i] != null && !results[i].isSuccess())){
					fmDao.rollback();
					endTransaction = true;
					fmDao.close();
					return results;
				}
			}
			
			//�X�V���s
			OrderUtils orderUtils = new OrderUtils(fmDao);
			for(int i = 0;i < fmVOs.length;i++){
				if(fmVOs[i] == null)
					continue;
				try{
					//"���["�I���̂Ƃ�
					if(fmVOs[i].getKnuSgn().equals("1")){
						//���[�T�C�����P�A���ɐ��͂��̂܂�
						fmDao.updateHac02(fmVOs[i],Integer.parseInt(fmVOs[i].getNyoSuu()),upddte,updjkk);
						//�����Ɍv���甭�����Ɠ��ɐ��̍����}�C�i�X
						int sa = Integer.parseInt(fmVOs[i].getHacSuu()) - Integer.parseInt(fmVOs[i].getNyoSuu());
						fmDao.updateZai01(fmVOs[i],sa,upddte,updjkk);
						orderUtils.updateHin12(fmVOs[i].getDaiKaiSkbCod(),fmVOs[i].getKaiSkbCod(),fmVOs[i].getKigBng(),fmVOs[i].getSmpKbn(),sa,upddte,updjkk);
					}//"���ɐ��ύX�ɂ�銮�["�I���̂Ƃ� 2003/10/28 add
					else if(fmVOs[i].getKnuSgn().equals("2")){
						//���[�T�C�����P�A���ɐ����������ōX�V
						fmDao.updateHac02(fmVOs[i],Integer.parseInt(fmVOs[i].getHacSuu()),upddte,updjkk);
						//�����Ɍv���甭�������}�C�i�X
						fmDao.updateZai01(fmVOs[i],Integer.parseInt(fmVOs[i].getHacSuu()),upddte,updjkk);
						//HIN12�̍X�V�͂Ȃ�
					}
				}catch(SQLException sqle){
					sqle.printStackTrace();
					results[i] = new NyukoSuuAdjustResult(fmVOs[i],false,"");
					continue;
				}
				//����ɐ���
				results[i] = new NyukoSuuAdjustResult(fmVOs[i],true,"");
			} 


			fmDao.commit();
			endTransaction = true;
			for(int i = 0;i < fmVOs.length;i++){
				if(fmVOs[i] == null)
					continue;
				fmVOs[i].setUpdDte(upddte);
				fmVOs[i].setUpdJkk(updjkk);
				if(fmVOs[i].getKnuSgn().equals("2"))
					fmVOs[i].setNyoSuu(fmVOs[i].getHacSuu());
			}


		}catch(SQLException sqle){
			if(fmDao != null){
				try{
					fmDao.rollback();
					endTransaction = true;
				}catch(SQLException sqle3){
					sqle3.printStackTrace();
				}
			}
			throw sqle;
		}finally{
			if(fmDao != null){
				if(!endTransaction ){
					//exception ��commit or rollback �ł��Ȃ������Ƃ��� ������ rollback
					try{
						fmDao.rollback();
					}catch(SQLException sqle3){
						sqle3.printStackTrace();
					}
				}
				try{
					fmDao.close();
				}catch(SQLException sqle2){
					sqle2.printStackTrace();
				}
			}
		}
		return results;
	}



	/**
	 * selectForUpdate  �X�V���Ă�
	 * 
	 */
	private NyukoSuuAdjustResult[] doSelectForUpdate(NyukoSuuAdjustDAO fmDao,NyukoSuuAdjustVO[] fmVOs)throws SQLException{
		NyukoSuuAdjustResult[] results = new NyukoSuuAdjustResult[fmVOs.length];

		try{
			boolean error = false;
			for(int i = 0;i < fmVOs.length;i++){
				if(fmVOs[i] == null)
					continue;
			
				int returnCode = fmDao.selectForUpdate(fmVOs[i]);
					
				switch(returnCode){
					case NyukoSuuAdjustDAO.NOT_MODIFIED:
						results[i] = new NyukoSuuAdjustResult(fmVOs[i],true,"");
						break;					
					case NyukoSuuAdjustDAO.MODIFIED:
						results[i] = new NyukoSuuAdjustResult(fmVOs[i],false,ANOTHER_USER_UPDATE);
						break;					
					case NyukoSuuAdjustDAO.NOT_EXIST:
						results[i] = new NyukoSuuAdjustResult(fmVOs[i],false,NOT_EXIST);
						break;						
					case NyukoSuuAdjustDAO.LOGICAL_DELETE:
						results[i] = new NyukoSuuAdjustResult(fmVOs[i],false,LOGICAL_DELETE);
						break;
	
				}
			}

		}catch(SQLException sqle){
			throw sqle;
		}
		return results;
	}

}

