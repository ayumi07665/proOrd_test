/**
* SubMateMasterDelegate  �����ރ}�X�^�[�����e�i���X  ���s�N���X
*
*	�쐬��    2003/06/24
*	�쐬��    �i�m�h�h�j����  ����
* �����T�v    �݌ɓ��������e�i���X�����s����N���X�B
*
*	[�ύX����]
*             ���t ���O
*  �E���e
*           2004/03/15�i�m�h�h�j�X
* 			�E������R�[�h�̓u�����N�o�^�ɕύX
*			2004/04/05(NII)�X
*			�E�����ރ}�X�^�[�X�V���ɕ����ލ݌Ƀe�[�u��(FTBHIN12)���X�V����
**/

package com.jds.prodord.master.submatemaster;


import com.jds.prodord.common.DateUtils;

import java.sql.*;

public class SubMateMasterDelegate {

	public static final String ANOTHER_USER_UPDATE = "ANOTHER_USER_UPDATE";
	public static final String EXIST = "EXIST";	
	public static final String NOT_EXIST = "NOT_EXIST";
	public static final String LOGICAL_DELETE = "LOGICAL_DELETE";
	public static final String NG = "NG";


//-----  ��������  --------------------------------------------------------------

	public SubMateMasterVO[] doFind(SubMateMasterVO fmVO)throws SQLException{

		SubMateMasterDAO fmDao = null;
		SubMateMasterVO[] finded = null;

		try{
			fmDao = new SubMateMasterDAO();

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

	public SubMateMasterResult doInsert(SubMateMasterVO fmVO)throws SQLException{

		SubMateMasterDAO fmDao = null;
		SubMateMasterResult result = null;
		SubMateMasterResult result_ = null;
		boolean endTransaction = false;
		boolean error = false;
		boolean flag1 = false;
		boolean flag2 = false;


		try{
			fmDao = new SubMateMasterDAO();
			fmDao.setAutoCommit(false); //DB�̃g�����U�N�V�����J�n

			//�X�V����
			DateUtils dateUtils = new DateUtils();
			int upddte = dateUtils.getDate6Int();
			int updjkk = dateUtils.getTime6Int();

			//�܂�����
				int returnCode = fmDao.selectForInsert(fmVO);
				switch(returnCode){
					case SubMateMasterDAO.NOT_EXIST://�܂��Ȃ�
						result = new SubMateMasterResult(fmVO,true,"");
						break;
					case SubMateMasterDAO.NOT_MODIFIED://���łɂ���
					case SubMateMasterDAO.MODIFIED:
						result = new SubMateMasterResult(fmVO,false,EXIST);
						flag1 = true;
						break;
					case SubMateMasterDAO.LOGICAL_DELETE://�_���폜���R�[�h���c���Ă�
						result = new SubMateMasterResult(fmVO,false,LOGICAL_DELETE);
						flag1 = true;
						break;					
				}

				if(!fmVO.getOutHatcCod().trim().equals("")){ //2004.03.15 upd ������u�����N�̏ꍇ������
					//�����������
					int finded = fmDao.findHatcCod(fmVO);
					switch(finded){
						case SubMateMasterDAO.NO_COD://������Ȃ�
							result_ = new SubMateMasterResult(fmVO,false,NG);
							flag2 = true;
							break;

						case SubMateMasterDAO.OK://�����悠��
							result_ = new SubMateMasterResult(fmVO,true,"");
							break;					
					}
				}else{
					result_ = new SubMateMasterResult(fmVO,true,"");
				}


			//�������ʂ̔���
			if(!flag1 && !flag2){
				error = false;
			}else{
				error = true;
			}


			//�G���[�̃��R�[�h���Ȃ����insert���s
			if(!error){
					try{						
						fmDao.insert(fmVO,upddte,updjkk);
					}catch(SQLException sqle2){//��Ӑ���ŃG���[�ɂȂ���
						error = true;
						result = new SubMateMasterResult(fmVO,false,"");
					}
					//����ɐ���
					result = new SubMateMasterResult(fmVO,true,"");
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
		if(!result_.isSuccess())
			return result_;
			
		return result;
	}

//-----  �X�V����  --------------------------------------------------------------

	public SubMateMasterResult[] doUpdate(SubMateMasterVO[] fmVOs)throws SQLException{


		SubMateMasterDAO fmDao = null;
		SubMateMasterResult[] results = null;
		boolean endTransaction = false;

		try{
			fmDao = new SubMateMasterDAO();
			fmDao.setAutoCommit(false);//DB�̃g�����U�N�V�����J�n

			//�X�V����
			DateUtils dateUtils = new DateUtils();
			int upddte = dateUtils.getDate6Int();
			int updjkk = dateUtils.getTime6Int();
			
			boolean error = false; 
			
			//�܂�����
			results = doSelectForUpdate(fmDao,fmVOs);
			for(int i = 0; i < results.length;i++){
				if(results[i] != null && !results[i].isSuccess()){
					error = true;
				}
			}
			
			if(!error){
				for(int i = 0; i < fmVOs.length;i++){
					if(fmVOs[i] == null)
						continue;
					if(!fmVOs[i].getOutHatcCod().trim().equals("")){ //2004.03.15 upd ������u�����N�̏ꍇ������
						int finded = fmDao.findHatcCod(fmVOs[i]);
						switch(finded){
							case SubMateMasterDAO.NO_COD://������Ȃ�
								results[i] = new SubMateMasterResult(fmVOs[i],false,NG);
								error = true;
								break;
		
							case SubMateMasterDAO.OK://�����悠��
								results[i] = new SubMateMasterResult(fmVOs[i],true,"");
								break;					
						}
					}else{
						results[i] = new SubMateMasterResult(fmVOs[i],true,"");
					}
				}
			}
			
			if(error){
				fmDao.rollback();
				endTransaction = true;
				fmDao.close();
				return results;
			}

			//�X�V���s
			for(int i = 0;i < fmVOs.length;i++){
				if(fmVOs[i] == null)
					continue;
				fmDao.update(fmVOs[i],upddte,updjkk);
				fmDao.updateHin12(fmVOs[i],upddte,updjkk);
				results[i] = new SubMateMasterResult(fmVOs[i],true,"");
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


//-----  �폜����  --------------------------------------------------------------

	public SubMateMasterResult[] doDelete(SubMateMasterVO[] fmVO)throws SQLException{

		SubMateMasterDAO fmDao = null;
		SubMateMasterResult[] result = null;
		boolean endTransaction = false;

		try{
			fmDao = new SubMateMasterDAO();
			fmDao.setAutoCommit(false);//DB�̃g�����U�N�V�����J�n

			//�X�V����
			DateUtils dateUtils = new DateUtils();
			int upddte = dateUtils.getDate6Int();
			int updjkk = dateUtils.getTime6Int();

		//�܂�����
			result = doSelectForUpdate(fmDao,fmVO);
			for(int i = 0; i < result.length;i++){
				if(result[i] != null && !result[i].isSuccess()){
					fmDao.rollback();
					endTransaction = true;
					fmDao.close();
					return result;
				}
			}

		//�폜���s	
			for(int i = 0;i < fmVO.length;i++){
				if(fmVO[i] == null)
					continue;
				fmDao.delete(fmVO[i],upddte,updjkk);
				result[i] = new SubMateMasterResult(fmVO[i],true,"");
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

	private SubMateMasterResult[] doSelectForUpdate(SubMateMasterDAO fmDao,SubMateMasterVO[] fmVO)throws SQLException{

		SubMateMasterResult[] result = new SubMateMasterResult[fmVO.length];

		try{
			boolean error = false;
			for(int i = 0;i < fmVO.length;i++){
				if(fmVO[i] == null)
					continue;
			
				int returnCode = fmDao.selectForUpdate(fmVO[i]);

				switch(returnCode){
					case SubMateMasterDAO.NOT_MODIFIED:
						result[i] = new SubMateMasterResult(fmVO[i],true,"");
						break;					
					case SubMateMasterDAO.MODIFIED:
						result[i] = new SubMateMasterResult(fmVO[i],false,ANOTHER_USER_UPDATE);
						break;					
					case SubMateMasterDAO.NOT_EXIST:
						result[i] = new SubMateMasterResult(fmVO[i],false,NOT_EXIST);
						break;						
					case SubMateMasterDAO.LOGICAL_DELETE:
						result[i] = new SubMateMasterResult(fmVO[i],false,LOGICAL_DELETE);
						break;

				}


				int returnCode_ = fmDao.selectForInsert(fmVO[i]);
				switch(returnCode_){				
					case SubMateMasterDAO.NO_COD:
						result[i] = new SubMateMasterResult(fmVO[i],false,NG);
						break;
					case SubMateMasterDAO.OK:
						result[i] = new SubMateMasterResult(fmVO[i],true,"");
						break;						
				}


			}

		}catch(SQLException sqle){
			throw sqle;
		}
		return result;
	}
	

}

