/**
* KetSziTblDelegate  �`�ԕʍ\�����ރ}�X�^�[�����e�i���X���s�N���X
*
*	�쐬��    2004/02/03
*	�쐬��    �i�m�h�h�j�X
* �����T�v    �`�ԕʍ\�����ރ}�X�^�[�����e�i���X������N���X�B
*
*	[�ύX����]
* 
*/

package com.jds.prodord.master.ketszitbl;
import com.jds.prodord.common.*;

import java.sql.*;
import java.util.*;

public class KetSziTblDelegate {

	
	/**
	 * �`�Ԗ��̃}�X�^�[�e�[�u�������e�i���X
	 * 
	 */
	public static final String ANOTHER_USER_UPDATE = "ANOTHER_USER_UPDATE";
	public static final String EXIST = "EXIST";	
	public static final String NOT_EXIST = "NOT_EXIST";
	public static final String LOGICAL_DELETE = "LOGICAL_DELETE";
	
	public static final String FUKSZICOD_NOT_EXIST = "FUKSZICOD_NOT_EXIST";


	/**
	 * ����
	 * 
	 */
	public KetSziTblVO[] doFind(KetSziTblVO fmVO)throws SQLException{
		KetSziTblDAO fmDao = null;

		KetSziTblVO[] finded = null;

		try{
			fmDao = new KetSziTblDAO();

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

//	-----  �X�V����  --------------------------------------------------------------

	  public KetSziTblResult[] doUpdate(KetSziTblVO[] fmVO)throws SQLException{

		  KetSziTblDAO fmDao = null;
		  KetSziTblResult[] results = null;
		  KetSziTblVO fmVOs= new KetSziTblVO();
		
		  boolean endTransaction = false;
		  boolean error = false;

		  try{
			  fmDao = new KetSziTblDAO();
			  fmDao.setAutoCommit(false);  //DB�̃g�����U�N�V�����J�n
			
			  //�X�V����
			  DateUtils dateUtils = new DateUtils();
			  int upddte = dateUtils.getDate6Int();
			  int updjkk = dateUtils.getTime6Int();


			  //�܂�����
				  results = doSelectForUpdate(fmDao,fmVO);
				  for(int i = 0; i < results.length; i++){
					  if(results[i] != null && !results[i].isSuccess()){
						  error = true;
					  }
				  }

			  if(!error){
				  for(int i = 0; i < fmVO.length;i++){
					  if(fmVO[i] == null)
						  continue;
						ArrayList errors_fuk = new ArrayList();
					  //�����ރR�[�h���݃`�F�b�N
					  ArrayList fukSziCodArr = fmVO[i].getFuksziCodArr();
					  for(int j = 0; j < fukSziCodArr.size(); j++){
						  if(!fmDao.hasFukSziCod(fmVO[i],(fukSziCodArr.get(j)+"").trim())){//�����ރR�[�h�����݂��Ȃ�������
							errors_fuk.add(String.valueOf(j+1));
						  }
					  }
					if(errors_fuk.size() > 0){
						  results[i] = new KetSziTblResult(fmVO[i],false,FUKSZICOD_NOT_EXIST);
						  results[i].setMap(KetSziTblResult.KEY_FUKSZICOD,errors_fuk);

						  error = true;
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
			  for(int i = 0;i < fmVO.length;i++){
				  if(fmVO[i] == null)
					  continue;
				  //�����ރR�[�hString��space����菜��
				  fmVO[i].removeFukusziCodSpace();
				  fmDao.update(fmVO[i],upddte,updjkk);
				  results[i] = new KetSziTblResult(fmVO[i],true,"");
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
		  return results;

	  }
					  

	/**
	 * selectForUpdate  �X�V���Ă�
	 * 
	 */
	private KetSziTblResult[] doSelectForUpdate(KetSziTblDAO fmDao,KetSziTblVO[] fmVOs)throws SQLException{
		KetSziTblResult[] results = new KetSziTblResult[fmVOs.length];

		try{
			boolean error = false;
			for(int i = 0;i < fmVOs.length;i++){
				if(fmVOs[i] == null)
					continue;
			
				int returnCode = fmDao.selectForUpdate(fmVOs[i]);
					
				switch(returnCode){
					case KetSziTblDAO.NOT_MODIFIED:
						results[i] = new KetSziTblResult(fmVOs[i],true,"");
						break;					
					case KetSziTblDAO.MODIFIED:
						results[i] = new KetSziTblResult(fmVOs[i],false,ANOTHER_USER_UPDATE);
						break;					
					case KetSziTblDAO.NOT_EXIST:
						results[i] = new KetSziTblResult(fmVOs[i],false,NOT_EXIST);
						break;						
					case KetSziTblDAO.LOGICAL_DELETE:
						results[i] = new KetSziTblResult(fmVOs[i],false,LOGICAL_DELETE);
						break;
	
				}
			}

		}catch(SQLException sqle){
			throw sqle;
		}
		return results;
	}

}

