/**
* OrderHistoryDelegate  ���������Ɖ��� �������s�N���X
*
*	�쐬��    2003/04/18
*	�쐬��    �i�m�h�h�j�g�c �h�q
* �����T�v    ���������s����N���X�B
*
*	[�ύX����]
*        2003/06/26�i�m�h�h�j���c �Ĕ�
* 			�E�����`�[���s�@�\ �C���i�`�F�b�N���ꂽ�s�݂̂ł͂Ȃ��A����̔������ԍ������S�Ă̍s�ɂ��āA
* 			  �������f�[�^�����݂��邩�ǂ������`�F�b�N���A���݂��Ȃ��ꍇ��INSERT����j
* 		 2003/07/11�i�m�h�h�j���c �Ĕ�
* 			�E���ރR�[�h=������(1 or 2)�̂Ƃ��A�����敪��'0'�Œ�ǉ��B
* 		 2003/08/06�i�m�h�h�j���c �Ĕ� 
* 			�E�������M�A�����`�[���s���A�݌Ƀe�[�u���̍X�V�ǉ�
* 		 2003/09/29�i�m�h�h�j���c �Ĕ� 
* 			�EFTBHIN12.SMPKBN�ǉ��ɔ����āAHIN12��ZAI01�̍݌ɐ��������\�b�h�ǉ�
* 		 2003/10/22�i�m�h�h�j���c �Ĕ�
* 			�E�������M�͖��o�͂̂� �� �������M�͖��o�́E�o�͍ϗ�����ΏۂƂ���悤�ɏC��
*		 2004/01/16,20�i�m�h�h�j�X
*			�E�����`�[���s���ɔ��������ύX����Ă�����R�����g�̎����\�� 
*		 2004/01/22	�i�m�h�h�j�X
*			�E���ɐ�������ł���悤�ɏC��	
* 		 2004/02/27 �i�m�h�h�j�X
* 			�E���[�T�C�����X�V����悤�ɏC��		
* 		 2004/04/02 (NII) �X
* 			�E�����ޔ����̏C��(���ރR�[�h�P�������݂ɑΉ�)
*		 2004/08/02�i�m�h�h�j����
*			�E�i�ԃ}�X�^�[�Ń`�F�b�N���������ɁA���������e�[�u�����Q�Ƃ��邽�߁A
*			  �i�ԑ��݃`�F�b�N���R�����g�A�E�g
*			�E�[���ύX�ɔ������ɓ��X�V��ǉ�(�������M�E�����`�[���s)
* 		 2005/05/25�i�m�h�h�j�g�c
* 			�E�[�i�於��'�i�`�q�d�c'�̏ꍇ�A�����敪"0"�̏C��
* 		 2005/09/15�i�m�h�h�j�g�c
* 			�E�������M�̏ꍇ�A�����X�V���𔭒������e�[�u���E�������f�[�^�ɍX�V
* 		 2005/09/27�i�m�h�h�j�g�c
* 			�E������MAX�X��ɕύX
* 		 2006/01/25�i�m�h�h�j�c�[
* 			�E�_�E�����[�h���ڂɒ��c���ǉ�
* 		 2006/08/08 �i�m�h�h�j���c �Ĕ�
* 			�E���������f�[�^�擾���ɁA�e�[�u�����������Ĉ�x�Ŏ擾����悤�ɕύX
*		 200802/25�i�m�h�h�j�c��
*  			�E���[�ς݂�HAC01.�f�[�^����FLG��'1'���Z�b�g
*		 2008/06/02�i�m�h�h�j�c��
* 			�E�u�`�o�Ё@�P���E���z�̏C��
**/

package com.jds.prodord.reference.orderhistory;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import com.jds.prodord.common.CheckCommon;
import com.jds.prodord.common.DataFormatUtils;
import com.jds.prodord.common.DateUtils;
import com.jds.prodord.common.OrderUtils;
import com.jds.prodord.common.ProcessLogUtils;
import com.jds.prodord.common.StringUtils;
import com.jds.prodord.common.SystemException;

public class OrderHistoryDelegate {

	public static final String ANOTHER_USER_UPDATE = "ANOTHER_USER_UPDATE";
	public static final String EXIST = "EXIST";
	public static final String LOGICAL_DELETE = "LOGICAL_DELETE";
	public static final String HACSAKI_NOT_EXIST = "HACSAKI_NOT_EXIST";
	public static final String KIGBNG_NOT_EXIST = "KIGBNG_NOT_EXIST";
	public static final String HACBNG_EXIST = "HACBNG_EXIST";
	public static final String EXCEED_REINPUT = "EXCEED_REINPUT";

	/*************************************************************************************************
	 * 		����(�Ɖ�)
	 * 
	 ************************************************************************************************/
	public OrderHistoryResult doFindForRefer(OrderHistoryVO queryVO)throws SQLException{

ProcessLogUtils prcTime = new ProcessLogUtils("���������Ɖ�", 1);
prcTime.start();
		
		OrderHistoryDAO fmDao = null;
		OrderHistoryVO[] finded = new OrderHistoryVO[0];
		OrderHistoryResult result = null;
		
		try{
			fmDao = new OrderHistoryDAO();

ProcessLogUtils countTime = new ProcessLogUtils("�������������擾", 2);
countTime.start();
			int count = fmDao.getHac02Count(queryVO);
countTime.outTotal(count);
countTime.end();
			if(count > OrderHistoryMidForm.MAX_REFER_COUNT){
				result = new OrderHistoryResult(null,false,EXCEED_REINPUT);
				result.setCount(count);
			}else if(count == 0){
				result = new OrderHistoryResult(null,true,"");
				result.setResultList(finded);//����0�̔z���Result�ɋl�߂�
			}else{

ProcessLogUtils selectTime = new ProcessLogUtils("���������e�[�u������", 2);
selectTime.start();

				//���������e�[�u������
				finded = fmDao.findHac02Data(queryVO);
				result = new OrderHistoryResult(null,true,"");
				result.setResultList(finded);//���ʂ�Result�ɋl�߂�

selectTime.end();

			}

		}finally{
			if(fmDao != null){
				try{
					fmDao.close();
				}catch(SQLException sqle2){
					SystemException se = new SystemException(sqle2);
					se.printStackTrace();
				}
			}
		}
prcTime.outTotal(finded.length);
prcTime.end();
		return result;
	}
	
	/*************************************************************************************************
	 * 		����(�_�E�����[�h)
	 * 
	 ************************************************************************************************/
	public OrderHistoryResult doFindForDownload(OrderHistoryVO queryVO)throws SQLException{

ProcessLogUtils prcTime = new ProcessLogUtils("���������Ɖ�(�޳�۰��)", 1);
prcTime.start();
		
		OrderHistoryDAO fmDao = null;
		OrderHistoryVO[] finded = new OrderHistoryVO[0];
		OrderHistoryResult result = null;
		
		try{
			fmDao = new OrderHistoryDAO();

ProcessLogUtils selectTime = new ProcessLogUtils("���������e�[�u������", 2);
selectTime.start();

			//���������e�[�u������
			finded = fmDao.findHac02Data(queryVO);
			result = new OrderHistoryResult(null,true,"");
			result.setResultList(finded);//���ʂ�Result�ɋl�߂�

selectTime.end();


		}finally{
			if(fmDao != null){
				try{
					fmDao.close();
				}catch(SQLException sqle2){
					SystemException se = new SystemException(sqle2);
					se.printStackTrace();
				}
			}
		}
prcTime.outTotal(finded.length);
prcTime.end();
		return result;
	}

	/**********************************************************************************************
	 * 		���s
	 * 
	 *********************************************************************************************/
	public OrderHistoryResult doJikkou(OrderHistoryVO fmVO)throws SQLException{

		OrderHistoryDAO fmDao = null;
		OrderHistoryResult result = new OrderHistoryResult(fmVO,true,"");

		boolean endTransaction = false;
		try{
			fmDao = new OrderHistoryDAO();
			
			boolean error = false;

			//�����摶�݃`�F�b�N
			if(!fmVO.getHacCod_H().equals("")){
			    if(!CheckCommon.hasHacCod(fmVO.getDaiKaiSkbCod(),fmVO.getHacCod_H())){			    	
				    result = new OrderHistoryResult(fmVO,false,HACSAKI_NOT_EXIST);
				    error = true;
			    }
			}  
			//�G���[���Ȃ����
			if(!error){
				//����ɐ���
				result = new OrderHistoryResult(fmVO,true,"");
			}

		}finally{
			if(fmDao != null){
				try{
					fmDao.close();
				}catch(SQLException sqle2){
					SystemException se = new SystemException(sqle2);
					se.printStackTrace();
				}
			}
		}
		return result;
		
	}


	/********************************************************************************************
	 * 		�������M
	 * 
	 *******************************************************************************************/
	public OrderHistoryResult[] doTeiseiSousin(OrderHistoryVO[] fmVOs)throws SQLException{

ProcessLogUtils prcTime = new ProcessLogUtils("�������M", 1);
prcTime.start();

		OrderHistoryDAO fmDao = null;
		OrderHistoryResult[] results = null;
		
		boolean endTransaction = false;

		try{
			fmDao = new OrderHistoryDAO();
			fmDao.setAutoCommit(false); //DB�̃g�����U�N�V�����J�n
			//�X�V����
			DateUtils dateUtils = new DateUtils();
			int upddte = dateUtils.getDate6Int();
			int updjkk = dateUtils.getTime6Int();

			boolean error = false;

			//�܂�����
			results = this.doSelectForUpdate(fmDao,fmVOs);
			int count = 0;
			for(int i = 0; i < results.length;i++){
				if(results[i] == null)
					continue;
				count++;
				if(!results[i].isSuccess()){
					fmDao.rollback();
					endTransaction = true;
					fmDao.close();
					return results;
				}
			}

			//�G���[�̃��R�[�h���Ȃ����update���s
			if(!error){
				HashMap keyMap = new HashMap();
ProcessLogUtils updTime = new ProcessLogUtils("���������֘A�f�[�^�X�V", 2);
updTime.start();
updTime.outTotal(count);

				OrderUtils orderUtils = new OrderUtils(fmDao);
				for(int i = 0;i < fmVOs.length;i++){
					
					if(fmVOs[i] == null)
						continue;

					try{
						//HIN12��ZAI01�̍݌ɐ��̒��������� 2003/09/29 add
						adjustZaiSuu(fmVOs[i],fmDao,upddte,updjkk);
						//��������ύX�����ꍇ�ύX���e���R�����g�ց@2004/01/21 add 
						setComent(fmVOs[i]); 
						//bshCod����擾�ł���悤�ɏC�� 2005/05/25 add
						String cykKbn = getCykKbn(fmVOs[i].getNhnMeiKj(),fmVOs[i].getBunCod(),fmVOs[i].getBshCod());
						//���ɐ�������ł���悤�ɏC���@2004/01/22 add 
						setNyusu(fmVOs[i],cykKbn);
						//���ɓ��X�V��ǉ� 2004/08/02 add
						setNyukobi(fmVOs[i],cykKbn);

						/*-- �o�͍ς̏ꍇ�A�����X�V���̃Z�b�g --------------------------------------------*/
						if(fmVOs[i].getSyrZmiSgn().equals("1")){

							String rrkTbl = DataFormatUtils.makeRrkTblStr(fmVOs[i].getRrkTbl(), upddte);
	
							//VO�ɕύX���������X�V�����Z�b�g
							fmVOs[i].setRrkTbl(rrkTbl);
	
							//�������M�����X�V������𔭒����ԍ����X�V���邽�߂�Key			  	  
							if(!keyMap.containsKey(fmVOs[i].getUpdateKey()))
								keyMap.put(fmVOs[i].getUpdateKey(),fmVOs[i]);
						}
						/*-------------------------------------------------------------------------------*/
						
						//���������e�[�u���X�V
						fmDao.updateHac02(fmVOs[i],upddte,updjkk,fmVOs[i].getSyrZmiSgn(),cykKbn);
						
						if(fmVOs[i].getSyrZmiSgn().equals("1")){//�o�͍ς�������
							//�`�F�b�N���ꂽ�s�̔������f�[�^�����݂��Ă�����
							if(fmDao.hasHac01(fmVOs[i])){
								//bshCod����擾�ł���悤�ɏC�� 2005/05/25 add
								fmDao.updateHac01(fmVOs[i],upddte,updjkk,getCykKbn(fmVOs[i].getNhnMeiKj(),fmVOs[i].getBunCod(),fmVOs[i].getBshCod()));
							}//�������f�[�^�������Ă��܂��Ă�����
							else{
								
								//���[�ς݂͑��M���Ȃ��@2008/02/19
								int dtaExcFlg = 0;
								if(fmVOs[i].getKnuSgn().equals("1")){
									dtaExcFlg = 1;
								}else{
									dtaExcFlg = 0;
								}

								//�������f�[�^��������
								orderUtils.insertHac01(
									fmVOs[i].getDaiKaiSkbCod(),//��\���
									fmVOs[i].getKaiSkbCod(),//���
									fmVOs[i].getHacSyoDte(),//���������t
									fmVOs[i].getSyrSuu(),//������
									fmVOs[i].getSeqNo(),//SEQNO
									dtaExcFlg,//�f�[�^�����t���O
									upddte,
									updjkk);
							}
						}
						
					}catch(Exception e){
						SystemException se = new SystemException(e);
						se.printStackTrace();
						error = true;
						results[i] = new OrderHistoryResult(fmVOs[i],false,"");
						break;
					}
					//����ɐ���
					results[i] = new OrderHistoryResult(fmVOs[i],true,"");
					results[i].setKeyMap(keyMap);
				}

				//�G���[�Ȃ烍�[���o�b�N
				if(error){
					fmDao.rollback();
					endTransaction = true;
				//�G���[���Ȃ���΃R�~�b�g
				}else{
					fmDao.commit();
					endTransaction = true;
				
					for(int i = 0;i < fmVOs.length;i++){
						if(fmVOs[i] == null)
							continue;
						fmVOs[i].setUpdDte(upddte);
						fmVOs[i].setUpdJkk(updjkk);
						fmVOs[i].setHacSuuOld(fmVOs[i].getHacSuu());
						fmVOs[i].setSinKyuKbnOld(fmVOs[i].getSinKyuKbn());
						fmVOs[i].setCheck_Mid(false);  //2004.01.21 add
						fmVOs[i].setTan2Old(fmVOs[i].getTan2());
						fmVOs[i].setKinOld(fmVOs[i].getKin());

					}				
				}
updTime.end();

				//�G���[�Ȃ���keyMap����̏ꍇ
				if(!error && !keyMap.isEmpty()){
ProcessLogUtils rrkTblTime = new ProcessLogUtils("�����X�V���X�V", 2);
rrkTblTime.start();

					//�������ꂽ�s�̔������ԍ������ׂė����X�V�����X�V 2005/09/15 add
					try {
						Iterator keySet = keyMap.keySet().iterator();
						while (keySet.hasNext()) {
							String key = (String) keySet.next();
							OrderHistoryVO value = (OrderHistoryVO) keyMap.get(key);

							OrderHistoryVO queryVO = new OrderHistoryVO();
							//���������e�[�u���X�V
							fmDao.updateHac(queryVO.getPartOfQuery(key),value.getRrkTbl(),upddte,updjkk,OrderHistoryDAO.HAC02);
							//�������f�[�^�X�V
							fmDao.updateHac(queryVO.getPartOfQuery(key),value.getRrkTbl(),upddte,updjkk,OrderHistoryDAO.HAC01);
							//���������f�[�^�������Ă镪��IndicatePrint�̕��œo�^���Ă����
						
						}
					} catch (Exception e) {
						SystemException se = new SystemException(e);
						se.printStackTrace();
						error = true;
						results[0] = new OrderHistoryResult(fmVOs[0],false,"");
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
rrkTblTime.end();
				}
			}

		}finally{
			if(fmDao != null){
				try {
					if(!endTransaction)//exception ��commit or rollback �ł��Ȃ������Ƃ��� ������ rollback
						fmDao.rollback();
	
					fmDao.close();
		
				} catch (SQLException e) {
					SystemException se = new SystemException(e);
					se.printStackTrace();
				}
			}
		}

prcTime.end();
		return results;
		
	}


	 /************************************************************************************************
	 * 		selectForUpdate  �X�V���Ă�
	 * 
	 ************************************************************************************************/
	private OrderHistoryResult[] doSelectForUpdate(OrderHistoryDAO fmDao,OrderHistoryVO[] fmVOs)throws SQLException{

		OrderHistoryResult[] results = new OrderHistoryResult[fmVOs.length];

		try{
			boolean error = false;
			for(int i = 0;i < fmVOs.length;i++){
				if(fmVOs[i] == null){
					continue;
				}

				int returnCode = fmDao.selectForUpdate(fmVOs[i]);
				switch(returnCode){
					case OrderHistoryDAO.NOT_MODIFIED:
						results[i] = new OrderHistoryResult(fmVOs[i],true,"");
						break;					
					case OrderHistoryDAO.MODIFIED:
					case OrderHistoryDAO.NOT_EXIST:
					case OrderHistoryDAO.LOGICAL_DELETE:
						results[i] = new OrderHistoryResult(fmVOs[i],false,ANOTHER_USER_UPDATE);
						break;
				}
			}

		}catch(SQLException sqle){
			throw sqle;
		}
		return results;
	}
	

	/********************************************************************************************
	 * 		�����`�[���s
	 * 
	 *******************************************************************************************/
	public OrderHistoryResult[] doDnpHakkou(OrderHistoryVO[] fmVOs)throws SQLException{

ProcessLogUtils prcTime = new ProcessLogUtils("�����`�[���s", 1);
prcTime.start();

		OrderHistoryDAO fmDao = null;
	
		OrderHistoryResult[] results = new OrderHistoryResult[fmVOs.length];

		boolean endTransaction = false;
		try{
			fmDao = new OrderHistoryDAO();
			fmDao.setAutoCommit(false); //DB�̃g�����U�N�V�����J�n

			//�X�V����
			DateUtils dateUtils = new DateUtils();
			int upddte = dateUtils.getDate6Int();
			int updjkk = dateUtils.getTime6Int();

			boolean error = false;
			//�܂�����
			results = this.doSelectForUpdate(fmDao,fmVOs);
			int count = 0;
			for(int i = 0; i < results.length;i++){
				if(results[i] == null)
					continue;
				count++;
				if(!results[i].isSuccess()){
					fmDao.rollback();
					endTransaction = true;
					fmDao.close();
					return results;
				}
			}

			//�G���[�̃��R�[�h���Ȃ����update&insert���s
			if(!error){
ProcessLogUtils updTime = new ProcessLogUtils("���������֘A�f�[�^�X�V", 2);
updTime.start();
updTime.outTotal(count);

				HashMap keyMap = new HashMap();
				OrderUtils orderUtils = new OrderUtils(fmDao);
				for(int i = 0;i < fmVOs.length;i++){
					
					if(fmVOs[i] == null)
						continue;

					try{
						//HIN12��ZAI01�̍݌ɐ��̒��������� 2003/09/29 add
						adjustZaiSuu(fmVOs[i],fmDao,upddte,updjkk);
						//��������ύX�����ꍇ�ύX���e���R�����g�ց@2004/01/16 add 
						setComent(fmVOs[i]); 
						//bshCod����擾�ł���悤�ɏC�� 2005/05/25 add
						String cykKbn = getCykKbn(fmVOs[i].getNhnMeiKj(),fmVOs[i].getBunCod(),fmVOs[i].getBshCod());
						//���ɐ�������ł���悤�ɏC���@2004/01/22 add
						setNyusu(fmVOs[i],cykKbn); 
						//���ɓ��X�V��ǉ� 2004/08/03 add
						setNyukobi(fmVOs[i],cykKbn);
						
						/*-- �o�͍ς̏ꍇ�A�����X�V���̃Z�b�g --------------------------------------------*/
						if(fmVOs[i].getSyrZmiSgn().equals("1")){
							
							String rrkTbl = DataFormatUtils.makeRrkTblStr(fmVOs[i].getRrkTbl(), upddte);

							//VO�ɕύX���������X�V�����Z�b�g
							fmVOs[i].setRrkTbl(rrkTbl);
							
							//�������M�����X�V������𔭒����ԍ����X�V���邽�߂�Key			  	  
							if(!keyMap.containsKey(fmVOs[i].getUpdateKey()))
								keyMap.put(fmVOs[i].getUpdateKey(),fmVOs[i]);
						}
						/*-------------------------------------------------------------------------------*/

						//���������e�[�u���X�V
						fmDao.updateHac02(fmVOs[i],upddte,updjkk,fmVOs[i].getSyrZmiSgn(),cykKbn);
						
						//�������f�[�^�X�V
						//2003/06/26 �C�� okada
						OrderHistoryVO queryVO = new OrderHistoryVO();
						this.setVO_forCheck(queryVO,fmVOs[i]);//���������pVO�ɒl���Z�b�g
						OrderHistoryVO[] finded_forCheck = fmDao.findHac02(queryVO);//������������
						
						//�`�F�b�N���ꂽ�s�̔������f�[�^�����݂��Ă�����
						if(fmDao.hasHac01(fmVOs[i])){
							//bshCod����擾�ł���悤�ɏC�� 2005/05/25 add
							fmDao.updateHac01(fmVOs[i],upddte,updjkk,getCykKbn(fmVOs[i].getNhnMeiKj(),fmVOs[i].getBunCod(),fmVOs[i].getBshCod()));
						}
						//����̔������ԍ������S�Ă̍s�ɂ��āA�������f�[�^�̑��݂��`�F�b�N
						for(int j = 0; j < finded_forCheck.length; j++){
							//�������f�[�^�����݂��Ȃ�������
							if(!fmDao.hasHac01(finded_forCheck[j])){

								//���[�ς݂͑��M���Ȃ��@2008/02/19
								int dtaExcFlg = 0;
								if(finded_forCheck[j].getKnuSgn().equals("1")){
									dtaExcFlg = 1;
								}else{
									dtaExcFlg = 0;
								}

								//�������f�[�^��������
								orderUtils.insertHac01(
									finded_forCheck[j].getDaiKaiSkbCod(),//��\���
									finded_forCheck[j].getKaiSkbCod(),//���
									finded_forCheck[j].getHacSyoDte(),//���������t
									finded_forCheck[j].getSyrSuu(),//������
									finded_forCheck[j].getSeqNo(),//SEQNO
									dtaExcFlg,//�f�[�^�����t���O
									upddte,
									updjkk);
							}
						}

					}catch(Exception e){
						SystemException se = new SystemException(e);
						se.printStackTrace();
						error = true;
						results[i] = new OrderHistoryResult(fmVOs[i],false,"");
						break;
					}
					//����ɐ���
					results[i] = new OrderHistoryResult(fmVOs[i],true,"");
					results[i].setKeyMap(keyMap);

			   }

			   //�G���[�Ȃ烍�[���o�b�N
			   if(error){
				   fmDao.rollback();
				   endTransaction = true;
			   //�G���[���Ȃ���΃R�~�b�g
			   }else{
				   fmDao.commit();
				   endTransaction = true;
				   boolean flg = false;
				   for(int i = 0;i < fmVOs.length;i++){
					   if(fmVOs[i] == null)
						   continue;
					   fmVOs[i].setUpdDte(upddte);
					   fmVOs[i].setUpdJkk(updjkk);
					   fmVOs[i].setHacSuuOld(fmVOs[i].getHacSuu());
					   fmVOs[i].setSinKyuKbnOld(fmVOs[i].getSinKyuKbn());
					   fmVOs[i].setCheck_Mid(false);  //2004.01.20 add
				       fmVOs[i].setTan2Old(fmVOs[i].getTan2());
 	 				   fmVOs[i].setKinOld(fmVOs[i].getKin());

				   }
			   }

updTime.end();
			}

		}finally{
			if(fmDao != null){
				try {
					if(!endTransaction)//exception ��commit or rollback �ł��Ȃ������Ƃ��� ������ rollback
						fmDao.rollback();
	
					fmDao.close();
		
				} catch (SQLException e) {
					SystemException se = new SystemException(e);
					se.printStackTrace();
				}
			}
		}

prcTime.end();
		return results;
		
	}
		
	/** 
	 * HIN12��ZAI01�̍݌ɐ��𒲐����� 2003/09/29 add
	 */
	private void adjustZaiSuu(OrderHistoryVO vo,OrderHistoryDAO fmDao, int upddte,int updjkk)throws SQLException{
							
		int hacsuu_Old = 0;
		int hacsuu = 0;
		int sabun = 0;
		
		hacsuu_Old = Integer.parseInt(vo.getHacSuuOld());
		hacsuu	= Integer.parseInt(vo.getHacSuu());
		sabun = hacsuu_Old - hacsuu;
		
		OrderUtils orderUtils = new OrderUtils(fmDao);
	
		//�v���X�����̂Ƃ�
		if(vo.getBunCod().equals("0")){
			if(vo.isShinpu()){
				//�敪�ύX�^�C�v�ɉ����āAFTBHIN12�̑��݃`�F�b�N�����A�Ȃ����INSERT���s��
				checkAndInsertHin12(vo,fmDao,upddte,updjkk);
				//�������͕ύX����Ă��Ȃ��Ƃ�
				if(sabun == 0){
					switch(vo.getKbnHenkoType()){
						case OrderHistoryVO.FROM_SMP_TO_OTHER:
							//�����ލ݌Ƀe�[�u���X�V�@(����ً敪=�� ���� ����ً敪=S�֍݌ɂ��ڂ�)
							orderUtils.updateHin12(vo.getDaiKaiSkbCod(),vo.getKaiSkbCod(),vo.getKigBng(),"S",hacsuu,upddte,updjkk);
							orderUtils.updateHin12(vo.getDaiKaiSkbCod(),vo.getKaiSkbCod(),vo.getKigBng(),"",-hacsuu,upddte,updjkk);
							//�݌Ƀe�[�u���X�V
							orderUtils.updateZai01(vo.getDaiKaiSkbCod(),vo.getKigBng(),hacsuu,upddte,updjkk,OrderUtils.PRSHAC);
							break;
						case OrderHistoryVO.FROM_OTHER_TO_SMP:
							//�����ލ݌Ƀe�[�u���X�V�@(����ً敪=S ���� ����ً敪=���֍݌ɂ��ڂ�)
							orderUtils.updateHin12(vo.getDaiKaiSkbCod(),vo.getKaiSkbCod(),vo.getKigBng(),"",hacsuu,upddte,updjkk);
							orderUtils.updateHin12(vo.getDaiKaiSkbCod(),vo.getKaiSkbCod(),vo.getKigBng(),"S",-hacsuu,upddte,updjkk);
							//�݌Ƀe�[�u���X�V
							orderUtils.updateZai01(vo.getDaiKaiSkbCod(),vo.getKigBng(),-hacsuu,upddte,updjkk,OrderUtils.PRSHAC);
							break;
					}
				}//�������ɕύX���������Ƃ�
				else{
					switch(vo.getKbnHenkoType()){
						case OrderHistoryVO.FROM_SMP_TO_OTHER:
							//�����ލ݌Ƀe�[�u���X�V�@
							orderUtils.updateHin12(vo.getDaiKaiSkbCod(),vo.getKaiSkbCod(),vo.getKigBng(),"S",hacsuu_Old,upddte,updjkk);
							orderUtils.updateHin12(vo.getDaiKaiSkbCod(),vo.getKaiSkbCod(),vo.getKigBng(),"",-hacsuu,upddte,updjkk);
							//�݌Ƀe�[�u���X�V
							orderUtils.updateZai01(vo.getDaiKaiSkbCod(),vo.getKigBng(),hacsuu,upddte,updjkk,OrderUtils.PRSHAC);
							break;
						case OrderHistoryVO.FROM_OTHER_TO_SMP:
							//�����ލ݌Ƀe�[�u���X�V�@
							orderUtils.updateHin12(vo.getDaiKaiSkbCod(),vo.getKaiSkbCod(),vo.getKigBng(),"",hacsuu_Old,upddte,updjkk);
							orderUtils.updateHin12(vo.getDaiKaiSkbCod(),vo.getKaiSkbCod(),vo.getKigBng(),"S",-hacsuu,upddte,updjkk);
							//�݌Ƀe�[�u���X�V
							orderUtils.updateZai01(vo.getDaiKaiSkbCod(),vo.getKigBng(),-hacsuu,upddte,updjkk,OrderUtils.PRSHAC);
							break;
						case OrderHistoryVO.ELSE:
							//�����ލ݌Ƀe�[�u���X�V�@
							orderUtils.updateHin12(vo.getDaiKaiSkbCod(),vo.getKaiSkbCod(),vo.getKigBng(),vo.getSmpKbn(),sabun,upddte,updjkk);
							//�݌Ƀe�[�u���X�V
							if(vo.getSmpKbn().equals(""))
								orderUtils.updateZai01(vo.getDaiKaiSkbCod(),vo.getKigBng(),sabun,upddte,updjkk,OrderUtils.PRSHAC);
							break;
					}
				}
			
			}else{
				if(sabun != 0){
					//�����ލ݌Ƀe�[�u���X�V
					orderUtils.updateHin12(vo.getDaiKaiSkbCod(),vo.getKaiSkbCod(),vo.getKigBng(),vo.getSmpKbn(),sabun,upddte,updjkk);
				}
			}
		}
		//�����ޔ����̂Ƃ�
		else{
			//�������͕ύX����Ă��Ȃ��Ƃ�
			if(sabun == 0){
				//�܂��[�����}���Ă��Ȃ�
				if(upddte <= vo.getNki()){
					if(vo.isShinpu()){
						switch(vo.getKbnHenkoType()){
							case OrderHistoryVO.FROM_SMP_TO_OTHER:
								//�����ލ݌Ƀe�[�u���X�V�A(����ً敪=S ���� ����ً敪=���֍݌ɂ��ڂ�)
								orderUtils.updateHin12(vo.getDaiKaiSkbCod(),vo.getKaiSkbCod(),vo.getKigBng(),"S",vo.getPrsFukSziCod(),-hacsuu,upddte,updjkk);
								if(orderUtils.findHin12(vo.getDaiKaiSkbCod(),vo.getKaiSkbCod(),vo.getKigBng(),vo.getBunCod(),vo.getPrsFukSziCod(),"") == null)
									orderUtils.insertHin12(vo.getDaiKaiSkbCod(),vo.getKaiSkbCod(),vo.getKigBng(),vo.getPrsFukSziCod(),hacsuu,vo.getBunCod(),"",upddte,updjkk);
								else
									orderUtils.updateHin12(vo.getDaiKaiSkbCod(),vo.getKaiSkbCod(),vo.getKigBng(),"",vo.getPrsFukSziCod(),hacsuu,upddte,updjkk);
								//�݌Ƀe�[�u���X�V
								orderUtils.updateZai01(vo.getDaiKaiSkbCod(),vo.getKigBng(),hacsuu,upddte,updjkk,OrderUtils.FUKHAC);
								break;
							case OrderHistoryVO.FROM_OTHER_TO_SMP:
								//�����ލ݌Ƀe�[�u���X�V�A(����ً敪=�� ���� ����ً敪=S�֍݌ɂ��ڂ�)
								orderUtils.updateHin12(vo.getDaiKaiSkbCod(),vo.getKaiSkbCod(),vo.getKigBng(),"",vo.getPrsFukSziCod(),-hacsuu,upddte,updjkk);
								if(orderUtils.findHin12(vo.getDaiKaiSkbCod(),vo.getKaiSkbCod(),vo.getKigBng(),vo.getBunCod(),vo.getPrsFukSziCod(),"S") == null)
									orderUtils.insertHin12(vo.getDaiKaiSkbCod(),vo.getKaiSkbCod(),vo.getKigBng(),vo.getPrsFukSziCod(),hacsuu,vo.getBunCod(),"S",upddte,updjkk);
								else
									orderUtils.updateHin12(vo.getDaiKaiSkbCod(),vo.getKaiSkbCod(),vo.getKigBng(),"S",vo.getPrsFukSziCod(),hacsuu,upddte,updjkk);
								//�݌Ƀe�[�u���X�V
								orderUtils.updateZai01(vo.getDaiKaiSkbCod(),vo.getKigBng(),-hacsuu,upddte,updjkk,OrderUtils.FUKHAC);
								break;
						}
					}
				}
			}//�������ɕύX���������Ƃ� ����ԂɍX�V���s���邽�߁AHIN12�̍X�V�͂Ȃ�
			else{
				switch(vo.getKbnHenkoType()){
					case OrderHistoryVO.FROM_SMP_TO_OTHER:
						//�݌Ƀe�[�u���X�V(�����݌v�E�����Ɍv�ɔ������𑫂�����)
						orderUtils.updateZai01(vo.getDaiKaiSkbCod(),vo.getKigBng(),hacsuu,upddte,updjkk,OrderUtils.FUKHAC);
						break;
					case OrderHistoryVO.FROM_OTHER_TO_SMP:
						//�݌Ƀe�[�u���X�V(�����݌v�E�����Ɍv����O�񔭒���������)
						orderUtils.updateZai01(vo.getDaiKaiSkbCod(),vo.getKigBng(),-hacsuu_Old,upddte,updjkk,OrderUtils.FUKHAC);
						break;
					case OrderHistoryVO.ELSE:
						if(vo.getSmpKbn().equals(""))
							orderUtils.updateZai01(vo.getDaiKaiSkbCod(),vo.getKigBng(),-sabun,upddte,updjkk,OrderUtils.FUKHAC);
						break;
				}
			}
		}
		
		
	}
	
	/** 
	 * �敪�ύX�^�C�v�ɉ����āA
	 * FTBHIN12�̑��݃`�F�b�N�����A�Ȃ����INSERT���s��
	 */
	private void checkAndInsertHin12(OrderHistoryVO vo,OrderHistoryDAO fmDao,int upddte,int updjkk)throws SQLException{
		ArrayList arr = new ArrayList();
		String smpKbn = "";
		if(vo.getKbnHenkoType() == OrderHistoryVO.FROM_SMP_TO_OTHER)
			arr = fmDao.findHin12(vo,"S");
		else if(vo.getKbnHenkoType() == OrderHistoryVO.FROM_OTHER_TO_SMP){
			arr = fmDao.findHin12(vo,"");
			smpKbn = "S";
		}else if(vo.getKbnHenkoType() == OrderHistoryVO.ELSE)
			return;

		OrderUtils orderUtils = new OrderUtils(fmDao);
		for(int i = 0; i < arr.size(); i++){
			String[] str = (String[])arr.get(i);
			if(orderUtils.findHin12(vo.getDaiKaiSkbCod(),vo.getKaiSkbCod(),vo.getKigBng(),str[0],str[1],smpKbn) == null)
				orderUtils.insertHin12(vo.getDaiKaiSkbCod(),vo.getKaiSkbCod(),vo.getKigBng(),str[1],0,str[0],smpKbn,upddte,updjkk);
		}
		
	}
	
	/** 
	 * %�^�J�Z%�܂���%�i�`�q�d�c% �� �����敪 = "0" ����ȊO �� �����敪 = "1"
	 * �����ޔ����̂Ƃ� �� �����敪 = "0"
	 */
	private final String getCykKbn(String str,String bunCod,String bshCod){
		if(!bunCod.equals("0") && !bunCod.equals("")){//�����ޔ����̂Ƃ�
			return	"0";
		}
		return DataFormatUtils.getCykKbn(str, bshCod);
	}
	
	/** 
	 * �������������̂��߂�VO�ɒl���Z�b�g�i�������f�[�^���݃`�F�b�N�p�j
	 */
	private void setVO_forCheck(OrderHistoryVO queryVO, OrderHistoryVO vo){
		queryVO.setDaiKaiSkbCod(vo.getDaiKaiSkbCod());
		ArrayList arr = new ArrayList();
		arr.add(vo.getKaiSkbCod());
		queryVO.setKaiSkbCodList(arr);
		queryVO.setHacSyoDteFrm_H(Integer.parseInt(vo.getHacSyoDte()));
		queryVO.setHacSyoBngFrm_H(vo.getHacSyoBng());
	}

	/** 
	 * �������ύX���̃R�����g�����ݒ�@2004.01.16 add
	 */
	private void setComent(OrderHistoryVO queryVO){
		if(!queryVO.getCmt().equals("")){
			return ;
		}
		if(queryVO.getHacSuu().equals(queryVO.getHacSuuOld())){
			return;
		}else{
			queryVO.setCmt(
				"����������"
					+ StringUtils.toFullANS(queryVO.getHacSuuOld())
					+ "��"
					+ StringUtils.toFullANS(queryVO.getHacSuu()));
		}

	}

	/** 
	 * �������ύX���̓��ɐ��̐ݒ�@2004.02.27 upd
	 */
	private void setNyusu(OrderHistoryVO queryVO,String cykKbn){
		if(queryVO.getHacSuu().equals(queryVO.getHacSuuOld())){
			return ;
		}
		if(Integer.parseInt(queryVO.getHacSuu()) < Integer.parseInt(queryVO.getHacSuuOld())){
			if((Integer.parseInt(queryVO.getNyoSuu()) > 0 ) && 
				(Integer.parseInt(queryVO.getNyoSuu()) > Integer.parseInt(queryVO.getHacSuu()))){
				queryVO.setNyoSuu(queryVO.getHacSuu());
			}else{
				return;
			}
		}else{
			queryVO.setKnuSgn(" ");
		}
	}

	/**
	 * ���ɓ��̐ݒ�<br>
	 * �u�v���X ���� �����敪='1'�v�܂��́u�����ށv�̏ꍇ�A
	 * ���ɓ���0�ł͂Ȃ� ���� �[���������ȑO��������A
	 * ���ɓ��ɔ[�����Z�b�g
	 */
	private void setNyukobi(OrderHistoryVO vo, String cykKbn){

		//�u�v���X ���� �����敪='1'�v�܂��́u�����ށv�̏ꍇ
		if((vo.getBunCod().equals("0") && cykKbn.equals("1"))
			|| (vo.getBunCod().equals("1") || vo.getBunCod().equals("2"))){
				
			//���ɓ���0�ł͂Ȃ� ���� �[���������ȑO��������
			if(!vo.getNyoDte().equals("0")){
				if(vo.getNki() <= new DateUtils().getDate6Int()){
					//���ɓ��ɔ[�����Z�b�g
					vo.setNyoDte(String.valueOf(vo.getNki()));
				}
			}
		}
	}
}