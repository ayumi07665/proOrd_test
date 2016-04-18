/**
* KetRakDelegate  �`�ԃ����N�ʏ����}�X�^�[�e�[�u�������e�i���X���s�N���X
*
*	�쐬��    2003/05/02
*	�쐬��    �i�m�h�h�j���� ���G
* �����T�v    �`�ԃ����N�ʏ����}�X�^�[�e�[�u�������e�i���X�����s����N���X�B
*
*	[�ύX����]
*       2003/06/02�i�m�h�h�j�g�c �h�q
* 			�E��폜��̕����w��B
* 		2003/06/10
* 			�E��o�^��̂Ƃ��A�_���폜�ς̃��R�[�h��DELETE���ēo�^�B
*/

package com.jds.prodord.master.ketrak;


import com.jds.prodord.common.DateUtils;

import java.sql.*;

public class KetRakDelegate {

	
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
	public KetRakVO[] doFind(KetRakVO fmVO)throws SQLException{
		KetRakDAO fmDao = null;

		KetRakVO[] finded = null;

		try{
			fmDao = new KetRakDAO();

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
	 * �o�^
	 * 
	 */
	public KetRakResult doInsert(KetRakVO fmVO)throws SQLException{
		KetRakDAO fmDao = null;

		KetRakResult result = null;
		boolean	delete_flg = false;

		boolean endTransaction = false;
		try{
			fmDao = new KetRakDAO();
			fmDao.setAutoCommit(false); //DB�̃g�����U�N�V�����J�n

			//�X�V����
			DateUtils dateUtils = new DateUtils();
			int upddte = dateUtils.getDate6Int();
			int updjkk = dateUtils.getTime6Int();

			//�܂�����
			boolean error = false;
				int returnCode = fmDao.selectForUpdate(fmVO);
				switch(returnCode){
					case KetRakDAO.NOT_EXIST://�܂��Ȃ�
						result = new KetRakResult(fmVO,true,"");
						break;					
					case KetRakDAO.NOT_MODIFIED://���łɂ���
					case KetRakDAO.MODIFIED:
						result = new KetRakResult(fmVO,false,EXIST);
						error = true;
						break;					
					case KetRakDAO.LOGICAL_DELETE://�_���폜���R�[�h���c���Ă�
						result = new KetRakResult(fmVO,true,"");
//						error = true;
						delete_flg = true;
						break;					
				}
			

			 
			//�G���[�̃��R�[�h���Ȃ����insert���s
			if(!error){
					try{
						//���R�[�h���_���폜�ς�������DELETE
						if(delete_flg){
//System.out.println("���R�[�h���_���폜�ς�������DELETE");
							fmDao.deleteMst05(fmVO);
						}
						
						fmDao.insert(fmVO,upddte,updjkk);
					}catch(SQLException sqle2){//��Ӑ���ŃG���[�ɂȂ���
						error = true;
						result = new KetRakResult(fmVO,false,"");
					}
					//����ɐ���
					result = new KetRakResult(fmVO,true,"");
			}

			//�G���[�Ȃ烍�[���o�b�N
			if(error){
				fmDao.rollback();
				endTransaction = true;
			//�G���[���Ȃ���΃R�~�b�g
			}else{
				fmDao.commit();
				endTransaction = true;
	  			 //�o�����[�I�u�W�F�N�g�ɍX�V�������Z�b�g
				fmVO.setUpdDte(upddte);
				fmVO.setUpdJkk(updjkk);
			}
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
		return result;
		
	}



	/**
	 * �X�V
	 * 
	 */
	public KetRakResult[] doUpdate(KetRakVO[] fmVOs)throws SQLException{
		KetRakDAO fmDao = null;


		KetRakResult[] results = null;

		boolean endTransaction = false;
		try{
			fmDao = new KetRakDAO();
			fmDao.setAutoCommit(false);//DB�̃g�����U�N�V�����J�n


			DateUtils dateUtils = new DateUtils();
			int upddte = dateUtils.getDate6Int();
			int updjkk = dateUtils.getTime6Int();

			results = this.doSelectForUpdate(fmDao,fmVOs);
			//�܂�����
/**			boolean error = false;
			for(int i = 0; i<fmVOs.length; i++){
				if(fmVOs[i]==null)
					continue;
				
        		results[i] = this.doSelectForUpdate(fmDao,fmVOs[i]);
        		
			}
**/
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
			for(int i = 0;i < fmVOs.length;i++){
				if(fmVOs[i] == null)
					continue;
				fmDao.update(fmVOs[i],upddte,updjkk);
				results[i] = new KetRakResult(fmVOs[i],true,"");
			} 


			fmDao.commit();
			endTransaction = true;
			for(int i = 0;i < fmVOs.length;i++){
				if(fmVOs[i] == null)
					continue;
				fmVOs[i].setUpdDte(upddte);
				fmVOs[i].setUpdJkk(updjkk);
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
	 * �폜
	 * 
	 */
	public KetRakResult[] doDelete(KetRakVO[] fmVOs)throws SQLException{
		KetRakDAO fmDao = null;

		KetRakResult[] results = null;

		boolean endTransaction = false;
		try{
			fmDao = new KetRakDAO();
			fmDao.setAutoCommit(false);//DB�̃g�����U�N�V�����J�n

			DateUtils dateUtils = new DateUtils();
			int upddte = dateUtils.getDate6Int();
			int updjkk = dateUtils.getTime6Int();


				
			results = doSelectForUpdate(fmDao,fmVOs);
			for(int i = 0; i < results.length;i++){
				if(results[i] != null && !results[i].isSuccess()){
					fmDao.rollback();
					endTransaction = true;
					fmDao.close();
					return results;
				}
			}
	
			
			for(int i = 0;i < fmVOs.length;i++){
				if(fmVOs[i] == null)
					continue;
				fmDao.delete(fmVOs[i],upddte,updjkk);
				results[i] = new KetRakResult(fmVOs[i],true,"");
			}
			fmDao.commit();
			endTransaction = true;
			
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
	 * selectForUpdate  �X�V�ƍ폜���Ă�
	 * 
	 */
	private KetRakResult[] doSelectForUpdate(KetRakDAO fmDao,KetRakVO[] fmVOs)throws SQLException{
		KetRakResult[] results = new KetRakResult[fmVOs.length];

		try{
			boolean error = false;
			for(int i = 0;i < fmVOs.length;i++){
				if(fmVOs[i] == null)
					continue;
			
				int returnCode = fmDao.selectForUpdate(fmVOs[i]);
					
				switch(returnCode){
					case KetRakDAO.NOT_MODIFIED:
						results[i] = new KetRakResult(fmVOs[i],true,"");
						break;					
					case KetRakDAO.MODIFIED:
						results[i] = new KetRakResult(fmVOs[i],false,ANOTHER_USER_UPDATE);
						break;					
					case KetRakDAO.NOT_EXIST:
						results[i] = new KetRakResult(fmVOs[i],false,NOT_EXIST);
						break;						
					case KetRakDAO.LOGICAL_DELETE:
						results[i] = new KetRakResult(fmVOs[i],false,LOGICAL_DELETE);
						break;
	
				}
			}

		}catch(SQLException sqle){
			throw sqle;
		}
		return results;
	}

}

