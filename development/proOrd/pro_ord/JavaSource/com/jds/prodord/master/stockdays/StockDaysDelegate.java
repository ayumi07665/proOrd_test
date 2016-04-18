/**
* StockDaysDelegate  �݌ɓ����}�X�^�[�����e�i���X���s�N���X
*
*	�쐬��    2003/06/09
*	�쐬��    �i�m�h�h�j����  ����
* �����T�v    �݌ɓ��������e�i���X�����s����N���X�B
*
*	[�ύX����]
*             ���t ���O
*  �E���e
*/

package com.jds.prodord.master.stockdays;


import com.jds.prodord.common.DateUtils;

import java.sql.*;

public class StockDaysDelegate {

	
//=====  �݌ɓ����}�X�^�[�����e�i���X  =====================================================

	public static final String ANOTHER_USER_UPDATE = "ANOTHER_USER_UPDATE";
	public static final String EXIST = "EXIST";	
	public static final String NOT_EXIST = "NOT_EXIST";
	public static final String LOGICAL_DELETE = "LOGICAL_DELETE";



//-----  ��������  --------------------------------------------------------------

	public StockDaysVO[] doFind(StockDaysVO fmVO)throws SQLException{

		StockDaysDAO fmDao = null;
		StockDaysVO[] finded = null;

		try{
			fmDao = new StockDaysDAO();

			finded = fmDao.find(fmVO);    //�������������s���A���ʂ�finded��

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


//-----  �o�^����  --------------------------------------------------------------

	public StockDaysResult doInsert(StockDaysVO fmVO)throws SQLException{

		StockDaysDAO fmDao = null;
		StockDaysResult result = null;
		boolean endTransaction = false;

		try{
			fmDao = new StockDaysDAO();
			fmDao.setAutoCommit(false); //DB�̃g�����U�N�V�����J�n

			//�X�V����
			DateUtils dateUtils = new DateUtils();
			int upddte = dateUtils.getDate6Int();
			int updjkk = dateUtils.getTime6Int();

			//�܂�����
			boolean error = false;
				int returnCode = fmDao.selectForUpdate(fmVO);
				switch(returnCode){
					case StockDaysDAO.NOT_EXIST://�܂��Ȃ�
						result = new StockDaysResult(fmVO,true,"");
						break;					
					case StockDaysDAO.NOT_MODIFIED://���łɂ���
					case StockDaysDAO.MODIFIED:
						result = new StockDaysResult(fmVO,false,EXIST);
						error = true;
						break;					
					case StockDaysDAO.LOGICAL_DELETE://�_���폜���R�[�h���c���Ă�
						result = new StockDaysResult(fmVO,false,LOGICAL_DELETE);
						error = true;
						break;					
				}
			 
			//�G���[�̃��R�[�h���Ȃ����insert���s
			if(!error){
					try{
						fmDao.insert(fmVO,upddte,updjkk);
					}catch(SQLException sqle2){//��Ӑ���ŃG���[�ɂȂ���
						error = true;
						result = new StockDaysResult(fmVO,false,"");
					}
					//����ɐ���
					result = new StockDaysResult(fmVO,true,"");
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

//-----  �X�V����  --------------------------------------------------------------

	public StockDaysResult[] doUpdate(StockDaysVO[] fmVO)throws SQLException{

		StockDaysDAO fmDao = null;
		StockDaysResult[] result = null;
		boolean endTransaction = false;

		try{
			fmDao = new StockDaysDAO();
			fmDao.setAutoCommit(false);//DB�̃g�����U�N�V�����J�n

			//�X�V����
			DateUtils dateUtils = new DateUtils();
			int upddte = dateUtils.getDate6Int();
			int updjkk = dateUtils.getTime6Int();


			result = this.doSelectForUpdate(fmDao,fmVO);

			//�܂�����
			for(int i = 0; i < result.length;i++){
				//�G���[����������rollback���ďI��
				if((result[i] != null && !result[i].isSuccess())){
					fmDao.rollback();
					endTransaction = true;
					fmDao.close();
					return result;
				}
			}


			//�X�V���s

			for(int i = 0;i < fmVO.length;i++){
				if(fmVO[i] == null)
					continue;
				fmDao.update(fmVO[i],upddte,updjkk);
				result[i] = new StockDaysResult(fmVO[i],true,"");
			} 

			fmDao.commit();
			endTransaction = true;
			for(int i = 0;i < fmVO.length;i++){
				if(fmVO[i] == null)
					continue;
				fmVO[i].setUpdDte(upddte);
				fmVO[i].setUpdJkk(updjkk);
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
		return result;
	}


//-----  �폜����  --------------------------------------------------------------

	public StockDaysResult[] doDelete(StockDaysVO[] fmVO)throws SQLException{

		StockDaysDAO fmDao = null;
		StockDaysResult[] result = null;
		boolean endTransaction = false;

		try{
			fmDao = new StockDaysDAO();
			fmDao.setAutoCommit(false);//DB�̃g�����U�N�V�����J�n

			DateUtils dateUtils = new DateUtils();
			int upddte = dateUtils.getDate6Int();
			int updjkk = dateUtils.getTime6Int();


			result = doSelectForUpdate(fmDao,fmVO);
			for(int i = 0; i < result.length;i++){
				if(result[i] != null && !result[i].isSuccess()){
					fmDao.rollback();
					endTransaction = true;
					fmDao.close();
					return result;
				}
			}
	
			for(int i = 0;i < fmVO.length;i++){
				if(fmVO[i] == null)
					continue;
				fmDao.delete(fmVO[i],upddte,updjkk);
				result[i] = new StockDaysResult(fmVO[i],true,"");
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
		return result;
	}



//-----  selectForUpdate  �o�^�E�X�V�E�폜���Ă�  ---------

	private StockDaysResult[] doSelectForUpdate(StockDaysDAO fmDao,StockDaysVO[] fmVO)throws SQLException{

		StockDaysResult[] result = new StockDaysResult[fmVO.length];

		try{
			boolean error = false;
			for(int i = 0;i < fmVO.length;i++){
				if(fmVO[i] == null)
					continue;
			
				int returnCode = fmDao.selectForUpdate(fmVO[i]);
					
				switch(returnCode){
					case StockDaysDAO.NOT_MODIFIED:
						result[i] = new StockDaysResult(fmVO[i],true,"");
						break;					
					case StockDaysDAO.MODIFIED:
						result[i] = new StockDaysResult(fmVO[i],false,ANOTHER_USER_UPDATE);
						break;					
					case StockDaysDAO.NOT_EXIST:
						result[i] = new StockDaysResult(fmVO[i],false,NOT_EXIST);
						break;						
					case StockDaysDAO.LOGICAL_DELETE:
						result[i] = new StockDaysResult(fmVO[i],false,LOGICAL_DELETE);
						break;

				}
			}

		}catch(SQLException sqle){
			throw sqle;
		}
		return result;
	}
	

}

