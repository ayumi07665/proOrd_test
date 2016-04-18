/**
* SubMateStockDelegate  �����ލ݌Ƀ����e�i���X ���s�N���X
*
*	�쐬��    2003/08/18
*	�쐬��    �i�m�h�h�j����  ����
* �����T�v    �݌ɓ��������e�i���X�����s����N���X�B
*
*	[�ύX����]
*             ���t ���O
*  �E���e
*/

package com.jds.prodord.master.submatestock;

import java.sql.*;
import java.util.*;
import com.jds.prodord.common.*;
public class SubMateStockDelegate {

	
//=====  �����ލ݌Ƀe�[�u���Ɖ�  =====================================================

	public static final String ANOTHER_USER_UPDATE = "ANOTHER_USER_UPDATE";
	public static final String EXIST = "EXIST";	
	public static final String NOT_EXIST = "NOT_EXIST";
	public static final String LOGICAL_DELETE = "LOGICAL_DELETE";



//-----  ��������  --------------------------------------------------------------

	public SubMateStockVO[] doFind(SubMateStockVO fmVO)throws SQLException{

		SubMateStockDAO fmDao = null;
		SubMateStockVO[] finded = null;

		try{
			fmDao = new SubMateStockDAO();

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

//-----  �X�V����  --------------------------------------------------------------

	public SubMateStockResult[] doUpdate(SubMateStockVO[] fmVO)throws SQLException{


		SubMateStockDAO fmDao = null;
		SubMateStockResult[] results = null;
		boolean endTransaction = false;

		try{
			fmDao = new SubMateStockDAO();
			fmDao.setAutoCommit(false);//DB�̃g�����U�N�V�����J�n

			//�X�V����
			DateUtils dateUtils = new DateUtils();
			int upddte = dateUtils.getDate6Int();
			int updjkk = dateUtils.getTime6Int();
			
			boolean error = false; 
			
			//�܂�����
			results = doSelectForUpdate(fmDao,fmVO);
			for(int i = 0; i < results.length;i++){
				if(results[i] != null && !results[i].isSuccess()){
					error = true;
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
				fmDao.update(fmVO[i],upddte,updjkk);
				results[i] = new SubMateStockResult(fmVO[i],true,"");
			} 

			fmDao.commit();
			endTransaction = true;
			for(int i = 0;i < fmVO.length;i++){
				if(fmVO[i] == null)
					continue;
				fmVO[i].setHidUpdDte(upddte);
				fmVO[i].setHidUpdJkk(updjkk);
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


//-----  selectForUpdate  �o�^�E�X�V�E�폜���Ă�  ---------

	private SubMateStockResult[] doSelectForUpdate(SubMateStockDAO fmDao,SubMateStockVO[] fmVO)throws SQLException{

		SubMateStockResult[] result = new SubMateStockResult[fmVO.length];

		try{
			boolean error = false;
			for(int i = 0;i < fmVO.length;i++){
				if(fmVO[i] == null)
					continue;
			
				int returnCode = fmDao.selectForUpdate(fmVO[i]);

				switch(returnCode){
					case SubMateStockDAO.NOT_MODIFIED:
						result[i] = new SubMateStockResult(fmVO[i],true,"");
						break;					
					case SubMateStockDAO.MODIFIED:
						result[i] = new SubMateStockResult(fmVO[i],false,ANOTHER_USER_UPDATE);
						break;					
					case SubMateStockDAO.NOT_EXIST:
						result[i] = new SubMateStockResult(fmVO[i],false,NOT_EXIST);
						break;						
					case SubMateStockDAO.LOGICAL_DELETE:
						result[i] = new SubMateStockResult(fmVO[i],false,LOGICAL_DELETE);
						break;

				}

			}

		}catch(SQLException sqle){
			throw sqle;
		}
		return result;
	}

//-----  �L���ԍ����ݔ��菈��  --------------------------------------------------------------

	public ArrayList kigoban_search(SubMateStockVO fmVO)throws SQLException{

		ArrayList kigo_ArrErr = new ArrayList();
		ArrayList kigo_ArrOk = new ArrayList();
		ArrayList retrun_Arr = new ArrayList();
		String kigbng = "";

		for(int i=0 ; i<fmVO.getKigoBan_arr().size() ; i++){
			//FTBHIN01�̔���
			kigbng = CheckCommon.getKigBng(fmVO.getDaiKaiSkbCod(),fmVO.getQueryKaiSkbCodList(),(String)fmVO.getKigoBan_arr().get(i));
			if( kigbng == null){
				//FTBHIN02�̔���
				kigbng = CheckCommon.getKigBng2(fmVO.getDaiKaiSkbCod(),fmVO.getQueryKaiSkbCodList(),(String)fmVO.getKigoBan_arr().get(i));
				if( kigbng == null){
					kigo_ArrErr.add(Integer.toString(i+1));
				}else{
					kigo_ArrOk.add(kigbng);
				}
			}else{
				kigo_ArrOk.add(kigbng);
			}
		}

		retrun_Arr.add(kigo_ArrErr);//�G���[�i�Ԃ̓��͔ԍ�
		retrun_Arr.add(kigo_ArrOk); //OK�i�Ԃ̋L���ԍ�
		
		return retrun_Arr;
	}


}