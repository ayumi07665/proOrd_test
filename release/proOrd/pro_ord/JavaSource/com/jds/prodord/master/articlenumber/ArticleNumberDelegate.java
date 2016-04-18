/**
*ArtcleNumberDelegate �i�ԃ}�X�^�[�����e�i���X�������s�N���X
*
*	�쐬��    2003/08/25
*	�쐬��    �i�m�h�h�j����  ����
* �����T�v    �i�ԃ}�X�^�[�Ɖ�������s����N���X�B
*
*         2003/09/11�i�m�h�h�j����  ����
* 			�E�i�ԃ}�X�^�[�ł��X�V�ł���悤�ɏ����ύX�B
*/

package com.jds.prodord.master.articlenumber;


import com.jds.prodord.common.*;

import java.sql.*;
import java.util.*;


public class ArticleNumberDelegate {

	public static final String ANOTHER_USER_UPDATE = "ANOTHER_USER_UPDATE";
	public static final String EXIST = "EXIST";	
	public static final String NOT_EXIST = "NOT_EXIST";
	public static final String LOGICAL_DELETE = "LOGICAL_DELETE";
	public static final String CANNOT_DELETE = "CANNOT_DELETE";
	public static final String NOTEXIST_HACCOD = "NOTEXIST_HACCOD";
	public static final String EXIST_HAC02 = "EXIST_HAC02";



//*****  ����   ****************************************************

	public ArticleNumberVO doFind(ArticleNumberVO fmVO)throws SQLException{

//		System.out.println("Delegate�ɂĐݒ���s���܂�");

		ArticleNumberDAO fmDao = null;
		ArticleNumberVO finded = null;

		try{

			fmDao = new ArticleNumberDAO();
			finded = fmDao.find(fmVO);									

		}finally{
			try{
				fmDao.close();
			}catch(SQLException sqle2){
				sqle2.printStackTrace();
			}
		}
		return finded;
	}


//*****  �o�^   ****************************************************

	public ArticleNumberResult doInsert(ArticleNumberVO fmVO)throws SQLException{


//		System.out.println("Delegate�ɂĐݒ���s���܂�");

		ArticleNumberDAO fmDao = null;
		ArticleNumberVO finded = null;
		ArticleNumberResult result = null;
		boolean endTransaction = false;

		try{
			fmDao = new ArticleNumberDAO();
			fmDao.setAutoCommit(false); //DB�̃g�����U�N�V�����J�n

			//�X�V����
			DateUtils dateUtils = new DateUtils();
			int upddte = dateUtils.getDate6Int();
			int updjkk = dateUtils.getTime6Int();
					
			//�܂�����
			boolean error = false;
				int returnCode = fmDao.selectForUpdate(fmVO);
				switch(returnCode){
					case ArticleNumberDAO.NOT_EXIST://�܂��Ȃ�
						result = new ArticleNumberResult(fmVO,true,"");
						break;					
					case ArticleNumberDAO.NOT_MODIFIED://���łɂ���
					case ArticleNumberDAO.MODIFIED:
						result = new ArticleNumberResult(fmVO,false,EXIST);
						error = true;
						break;					
					case ArticleNumberDAO.LOGICAL_DELETE://�_���폜���R�[�h���c���Ă�
						result = new ArticleNumberResult(fmVO,false,LOGICAL_DELETE);
						error = true;
						break;					
				}


			if(!error){ //selectForUpdate������

				//������R�[�h���݃`�F�b�N
				result = this.existHacCod(fmVO);
				
				//�G���[����������rollback���ďI��
				if((result != null && !result.isSuccess())){
					fmDao.rollback();
					endTransaction = true;
					fmDao.close();
					return result;
				}
				
				//�G���[�̃��R�[�h���Ȃ����insert���s
				try{
					fmDao.insert(fmVO,upddte,updjkk);
				}catch(SQLException sqle2){
					error = true;
					result = new ArticleNumberResult(fmVO,false,"");
				}
				
				//�G���[�Ȃ烍�[���o�b�N
				if(error){
					fmDao.rollback();
					endTransaction = true;
				//�G���[���Ȃ���΃R�~�b�g
				}else{
					//����ɐ���
					result = new ArticleNumberResult(fmVO,true,"");
					fmDao.commit();
					endTransaction = true;
		  			 //�o�����[�I�u�W�F�N�g�ɍX�V�������Z�b�g
					fmVO.setUpddte(upddte);
					fmVO.setUpdjkk(updjkk);
					fmVO.setDbName("HIN02");//2003/10/07 add okada
				}


			} //if(!error){(�O)�I��

				}catch(SQLException sqle2){//��Ӑ���ŃG���[�ɂȂ���
					result = new ArticleNumberResult(fmVO,false,"");

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
				} //try�I��

		return result;
		
	}

//*****  �X�V   ****************************************************

	public ArticleNumberResult doUpdate(ArticleNumberVO fmVO)throws SQLException{

		ArticleNumberDAO fmDao = null;
		ArticleNumberResult result = null;
		boolean endTransaction = false;

		try{
			fmDao = new ArticleNumberDAO();
			fmDao.setAutoCommit(false);//DB�̃g�����U�N�V�����J�n

			//�X�V����
			DateUtils dateUtils = new DateUtils();
			int upddte = dateUtils.getDate6Int();
			int updjkk = dateUtils.getTime6Int();

			//�܂�����
			result = this.doSelectForUpdate(fmDao,fmVO);
				//�G���[����������rollback���ďI��
				if((result != null && !result.isSuccess())){
					fmDao.rollback();
					endTransaction = true;
					fmDao.close();
					return result;
				}

			//�����R�[�h����//
			result = this.existHacCod(fmVO);
				//�G���[����������rollback���ďI��
				if((result != null && !result.isSuccess())){
					fmDao.rollback();
					endTransaction = true;
					fmDao.close();
					return result;
				}

			//�X�V���s
				boolean error = false;
				try{
					fmDao.update(fmVO,upddte,updjkk);
				}catch(SQLException sqle2){
					error = true;
					result = new ArticleNumberResult(fmVO,false,"");
				}

				//�G���[�Ȃ烍�[���o�b�N
				if(error){
					fmDao.rollback();
					endTransaction = true;
				//�G���[���Ȃ���΃R�~�b�g
				}else{
					result = new ArticleNumberResult(fmVO,true,"");
					fmDao.commit();
					endTransaction = true;
					fmVO.setUpddte(upddte);
					fmVO.setUpdjkk(updjkk);
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

//*****  �폜   ****************************************************

	public ArticleNumberResult doDelete(ArticleNumberVO fmVO)throws SQLException{

		ArticleNumberDAO fmDao = null;
		ArticleNumberResult result = null;
		boolean endTransaction = false;

		try{
			fmDao = new ArticleNumberDAO();
			fmDao.setAutoCommit(false);//DB�̃g�����U�N�V�����J�n

			DateUtils dateUtils = new DateUtils();
			int upddte = dateUtils.getDate6Int();
			int updjkk = dateUtils.getTime6Int();

			result = doSelectForUpdate(fmDao,fmVO);
				if(result != null && !result.isSuccess()){
					fmDao.rollback();
					endTransaction = true;
					fmDao.close();
					return result;
				}

			//�폜���s
				boolean error = false;
				try{
					fmDao.delete(fmVO,upddte,updjkk);				
				}catch(SQLException sqle2){
					error = true;
					result = new ArticleNumberResult(fmVO,false,"");
				}

				//�G���[�Ȃ烍�[���o�b�N
				if(error){
					fmDao.rollback();
					endTransaction = true;
				//�G���[���Ȃ���΃R�~�b�g
				}else{
					result = new ArticleNumberResult(fmVO,true,"");
					fmDao.commit();
					endTransaction = true;
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

//-----  selectForUpdate  �X�V�E�폜���Ă�  ---------

	private ArticleNumberResult doSelectForUpdate(ArticleNumberDAO fmDao,ArticleNumberVO fmVO)throws SQLException{

		ArticleNumberResult result = new ArticleNumberResult(fmVO,false,"");

		try{
			int returnCode;

			if(fmVO.getPrcKbn().equals("3")){  //03/09/11 �ǉ�
				returnCode = fmDao.UselectForUpdate(fmVO); //Update�p
			}else{
				returnCode = fmDao.DselectForUpdate(fmVO); //Delete�p
			}

				switch(returnCode){
					case ArticleNumberDAO.NOT_MODIFIED:
						result = new ArticleNumberResult(fmVO,true,"");
						break;					
					case ArticleNumberDAO.MODIFIED:
						result = new ArticleNumberResult(fmVO,false,ANOTHER_USER_UPDATE);
						break;					
					case ArticleNumberDAO.NOT_EXIST:
						result = new ArticleNumberResult(fmVO,false,NOT_EXIST);
						break;						
					case ArticleNumberDAO.LOGICAL_DELETE:
						result = new ArticleNumberResult(fmVO,false,LOGICAL_DELETE);
						break;
					case ArticleNumberDAO.CANNOT_DELETE:
						result = new ArticleNumberResult(fmVO,false,CANNOT_DELETE);
						break;
					case ArticleNumberDAO.EXIST_HAC02:
						result = new ArticleNumberResult(fmVO,false,EXIST_HAC02);
						break;
				}


		}catch(SQLException sqle){
			throw sqle;
		}
		return result;
	}
	
	//������R�[�h���݃`�F�b�N
	private ArticleNumberResult existHacCod(ArticleNumberVO fmVO)throws SQLException{

		ArticleNumberResult result = new ArticleNumberResult(fmVO,false,"");
		try{
				ArrayList arr = new ArrayList();
				if(!fmVO.getPrsMkrCod().equals("") && !CheckCommon.hasHacCod(fmVO.getDaiKaiSkbCod(),fmVO.getPrsMkrCod()))
					arr.add(ArticleNumberResult.NOTEXIST_PRS);
				if(!fmVO.getJytPrsMkr().equals("") && !CheckCommon.hasHacCod(fmVO.getDaiKaiSkbCod(),fmVO.getJytPrsMkr()))
					arr.add(ArticleNumberResult.NOTEXIST_JYT);
				if(!fmVO.getHukSizCod().equals("") && !CheckCommon.hasHacCod(fmVO.getDaiKaiSkbCod(),fmVO.getHukSizCod()))
					arr.add(ArticleNumberResult.NOTEXIST_FUK);
				
				if(arr.size() != 0){
					result = new ArticleNumberResult(fmVO,false,NOTEXIST_HACCOD);
					result.setError(arr);
				}else
					result = new ArticleNumberResult(fmVO,true,"");
				
		}catch(SQLException sqle){
			throw sqle;
		}
		return result;
	}

}  //�N���X�I��
