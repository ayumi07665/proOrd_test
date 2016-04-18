/**
* PrsOrderDelegate  �v���X�E�����ޔ������ �������s�N���X
*
*	�쐬��    2003/04/28
*	�쐬��    �i�m�h�h�j���c �Ĕ�
* �����T�v    ���������s����N���X�B
*
*	[�ύX����]
*       2003/04/28 �i�m�h�h�j�g�c �h�q
* 			�E�u�����w���v�u�`�[���s�v�̒ǉ��B
*		2003/05/16 �i�m�h�h�j���c �Ĕ�
* 			�E�����摶�݃`�F�b�N�ɔ����於�̂̎擾�ǉ��B
* 		2003/06/18 �i�m�h�h�j���c �Ĕ�
* 			�E�O100����100���Ή��ǉ��B
*			�E�����ޔ����@�\�ǉ��B
*		2003/07/02 �i�m�h�h�j�g�c �h�q
* 			�E�����ޔ����̂Ƃ��A�����敪��'0'�Œ�ǉ��B
* 		2003/07/28�i�m�h�h�j���c �Ĕ�
* 			�E�����w���E�`�[���s���A�v���X�������}�C�i�X����O�̕����ލ݌ɐ����擾
* 			  ���������E�������f�[�^�̕����ލ݌ɐ��ɃZ�b�g
* 		2003/08/06�i�m�h�h�j���c �Ĕ� 
* 			�E�����w���A�`�[���s���A�݌Ƀe�[�u���̍X�V�ǉ�
* 		2004/04/02 (NII) �X
* 			�E�����ޔ����̏C��(���ރR�[�h�P�������݂ɑΉ�)
* 		2005/05/26�i�m�h�h�j�g�c
* 			�E�[�i�於��'�i�`�q�d�c'�̏ꍇ�A�����敪"0"�̏C��
* 		2005/09/14�i�m�h�h�j�g�c
* 			�E�����ޔ�����ʁA�����ރR�[�h���v���_�E�����ڂɕύX�iVAP�БΉ��j
* 
*/

package com.jds.prodord.order.prsorder;
import java.sql.*;
import java.util.*;

import com.jds.prodord.common.*;
import com.jds.prodord.reference.ikkatsurefer.*;
public class PrsOrderDelegate {

	public static final String ANOTHER_USER_UPDATE = "ANOTHER_USER_UPDATE";
	public static final String EXIST = "EXIST";
	public static final String LOGICAL_DELETE = "LOGICAL_DELETE";
	public static final String HACSAKI_NOT_EXIST = "HACSAKI_NOT_EXIST";


	

	/**********************************************************************************************
	 * 		�����w��
	 * 
	 *********************************************************************************************/
	public PrsOrderResult[] doHacShiji(PrsOrderVO[] fmVOs, String syrkbn,String bshCod)throws SQLException{
		PrsOrderDAO fmDao = null;
	
		PrsOrderResult[] results = new PrsOrderResult[fmVOs.length];

		boolean endTransaction = false;
		try{
			fmDao = new PrsOrderDAO();
			fmDao.setAutoCommit(false); //DB�̃g�����U�N�V�����J�n


			//�X�V����
			DateUtils dateUtils = new DateUtils();
			int upddte = dateUtils.getDate6Int();
			int updjkk = dateUtils.getTime6Int();

			boolean error = false;
			int[] number = null;
			
			for(int i = 0;i < fmVOs.length;i++){
				if(fmVOs[i] == null)
					continue;
					
				//�����摶�݃`�F�b�N
				fmDao.hasHacSaki(fmVOs[i]);
			    if(fmVOs[i].getHacNm().equals(PrsOrderDAO.NOT_EXIST)){			    	
				    results[i] = new PrsOrderResult(fmVOs[i],false,HACSAKI_NOT_EXIST);
				    fmVOs[i].setHacNm("");
				    error = true;
					continue;
			    }
			}
		 

			//�G���[�̃��R�[�h���Ȃ����insert���s
			if(!error){
				//�����񐔂̍̔�
				for(int i = 0;i < fmVOs.length;i++){
					if(fmVOs[i] != null){
						number = NumberingUtils.createNumbers(fmVOs[i].getQueryKaiSkbCod(),"",1,"");
						break;
					}
				}
				OrderUtils orderUtils = new OrderUtils(fmDao);
				java.text.DecimalFormat df = new java.text.DecimalFormat("000000");
				String syrsuu = df.format(number[0]);
				int seqno = 0;
				for(int i = 0;i < fmVOs.length;i++){
					if(fmVOs[i] == null)
						continue;

					seqno++;//fmVOs[i] == null�ł͂Ȃ�������
					try{
						//�v���X�������}�C�i�X����O�̕����ލ݌ɐ����擾 2003/07/28 add
//						String fukSziSuu = OrderUtils.findHin12(fmVOs[i].getDaiKaiSkbCod(),fmVOs[i].getKaiSkbCod(),fmVOs[i].getKigBng(),"1",fmVOs[i].getFukSziCod(),fmVOs[i].getSmpKbn());
//						fmVOs[i].setFukSziSuu((fukSziSuu == null)? "0" : fukSziSuu);
						if(fmVOs[i].getBasedRowFlg().equals("1")){
							String fukSziSuu = orderUtils.findHin12(fmVOs[i].getDaiKaiSkbCod(),fmVOs[i].getKaiSkbCod(),fmVOs[i].getKigBng(),fmVOs[i].getBunCod(),fmVOs[i].getFukSziCod(),fmVOs[i].getSmpKbn());
							fmVOs[i].setFukSziSuu((fukSziSuu == null)? "0" : fukSziSuu);
						}else{
							fmVOs[i].setFukSziSuu("0");
						}

						
						String syrzmisgn = "0";
						String cykKbn = getCykKbn(fmVOs[i].getNhnMei(),syrkbn,bshCod);
						//���������̓o�^
						fmDao.insertHac02(fmVOs[i],upddte,updjkk,syrsuu,StringUtils.lpad(seqno+"",3,"0"),syrzmisgn,cykKbn,syrkbn);
						fmVOs[i].setChoksoKbn(cykKbn);//�����敪���Z�b�g
						
						if(syrkbn.equals(PrsOrderForm.PRSHACHU)){
							//�����ލ݌ɂ̑��݃`�F�b�N�E�o�^(����)
							orderUtils.insertHin12List(
								fmVOs[i].getDaiKaiSkbCod(),
								fmVOs[i].getKaiSkbCod(),
								fmVOs[i].getKigBng(),
								fmVOs[i].getSmpKbn(),
								fmVOs[i].getFukSziCod_arr(),
								fmVOs[i].getBunCod_arr(),
								upddte,
								updjkk);
							//�����ލ݌ɂ��v���X�������}�C�i�X����
							orderUtils.updateHin12(fmVOs[i].getDaiKaiSkbCod(),fmVOs[i].getKaiSkbCod(),
												   fmVOs[i].getKigBng(),fmVOs[i].getSmpKbn(),(Integer.parseInt(fmVOs[i].getPrsHacSuu()) * -1),upddte,updjkk);
							//�݌Ƀe�[�u���X�V 2003/08/06 add
							if(fmVOs[i].getSmpKbn().equals(""))
								orderUtils.updateZai01(fmVOs[i].getDaiKaiSkbCod(),fmVOs[i].getKigBng(),
											   	   Integer.parseInt(fmVOs[i].getPrsHacSuu()),upddte,updjkk,OrderUtils.PRSHAC);
											   	   
							//IkkatsuRefer�֓n�� ������ϣ �T�C��
							fmVOs[i].setSyrZmiSgn_prs(IkkatsuReferDAO.HACZMI);

						}else if(syrkbn.equals(PrsOrderForm.FUKHACHU)){
							//HIN12�����݂��Ȃ������� 2003/09/22 add
							if(orderUtils.findHin12(fmVOs[i].getDaiKaiSkbCod(),fmVOs[i].getKaiSkbCod(),fmVOs[i].getKigBng(),fmVOs[i].getBunCod(),fmVOs[i].getFukSziCod(),fmVOs[i].getSmpKbn()) == null)
								orderUtils.insertHin12(fmVOs[i].getDaiKaiSkbCod(),fmVOs[i].getKaiSkbCod(),fmVOs[i].getKigBng(),fmVOs[i].getFukSziCod(),0,fmVOs[i].getBunCod(),fmVOs[i].getSmpKbn(),upddte,updjkk);
							//�݌Ƀe�[�u���X�V 2003/08/06 add
							if(fmVOs[i].getSmpKbn().equals(""))
								orderUtils.updateZai01(fmVOs[i].getDaiKaiSkbCod(),fmVOs[i].getKigBng(),
											   	   Integer.parseInt(fmVOs[i].getPrsHacSuu()),upddte,updjkk,OrderUtils.FUKHAC);
							
							fmVOs[i].setSyrZmiSgn_fuk(IkkatsuReferDAO.HACZMI);
						}
				
					}catch(SQLException sqle2){
						SystemException se = new SystemException(sqle2);
						se.printStackTrace();
						error = true;
						results[i] = new PrsOrderResult(fmVOs[i],false,"");
						continue;
					}
					//����ɐ���
					results[i] = new PrsOrderResult(fmVOs[i],true,"");
				} 
			}
			//�G���[�Ȃ烍�[���o�b�N
			if(error){
//System.out.println("PrsOrderDelegate --> rollback");
				fmDao.rollback();
				endTransaction = true;
			//�G���[���Ȃ���΃R�~�b�g
			}else{
//System.out.println("PrsOrderDelegate --> commit");
				fmDao.commit();
				endTransaction = true;
				
				for(int i = 0;i < fmVOs.length;i++){
					if(fmVOs[i] == null)
						continue;
					if(syrkbn.equals(PrsOrderForm.PRSHACHU)){
						//�v���X���������Z�b�g���Ȃ���
						this.setPrsHacJoho(fmVOs[i]);
					}else if(syrkbn.equals(PrsOrderForm.FUKHACHU)){
						//�����ޔ��������Z�b�g���Ȃ���
						this.setFukHacJoho(fmVOs[i]);
					}
					//�`�F�b�N�{�b�N�X���N���A
					fmVOs[i].setCheck_prs1(false);
				}
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

	
	/*************************************************************************************************
	 * 		�`�[���s
	 * 
	 ************************************************************************************************/
	public PrsOrderResult[] doDnpHakkou(PrsOrderVO[] fmVOs, String syrkbn,String bshCod)throws SQLException{
		PrsOrderDAO fmDao = null;
	
		PrsOrderResult[] results = new PrsOrderResult[fmVOs.length];

		boolean endTransaction = false;
		try{
			fmDao = new PrsOrderDAO();
			fmDao.setAutoCommit(false); //DB�̃g�����U�N�V�����J�n


			//�X�V����
			DateUtils dateUtils = new DateUtils();
			int upddte = dateUtils.getDate6Int();
			int updjkk = dateUtils.getTime6Int();

			boolean error = false;
			int[] number = null;

			for(int i = 0;i < fmVOs.length;i++){
				if(fmVOs[i] == null)
					continue;
					
				//�����摶�݃`�F�b�N
				fmDao.hasHacSaki(fmVOs[i]);
			    if(fmVOs[i].getHacNm().equals(PrsOrderDAO.NOT_EXIST)){			    	
				    results[i] = new PrsOrderResult(fmVOs[i],false,HACSAKI_NOT_EXIST);
				    fmVOs[i].setHacNm("");
				    error = true;
					continue;
			    }
			}

			//�G���[�̃��R�[�h���Ȃ����insert���s
			if(!error){
				//�����񐔂̍̔�
				for(int i = 0;i < fmVOs.length;i++){
					if(fmVOs[i] != null){
						number = NumberingUtils.createNumbers(fmVOs[i].getQueryKaiSkbCod(),"",1,"");
						break;
					}
				}
				OrderUtils orderUtils = new OrderUtils(fmDao);
				java.text.DecimalFormat df = new java.text.DecimalFormat("000000");
				String syrsuu = df.format(number[0]);
				int seqno = 0;
				for(int i = 0;i < fmVOs.length;i++){
					if(fmVOs[i] == null)
						continue;
						
					seqno++;//fmVOs[i] == null�ł͂Ȃ�������s�ԍ�++
					try{
						//�v���X�������}�C�i�X����O�̕����ލ݌ɐ����擾 2003/07/28 add
//						String fukSziSuu = OrderUtils.findHin12(fmVOs[i].getDaiKaiSkbCod(),fmVOs[i].getKaiSkbCod(),fmVOs[i].getKigBng(),"1",fmVOs[i].getFukSziCod(),fmVOs[i].getSmpKbn());
						if(fmVOs[i].getBasedRowFlg().equals("1")){
							String fukSziSuu = orderUtils.findHin12(fmVOs[i].getDaiKaiSkbCod(),fmVOs[i].getKaiSkbCod(),fmVOs[i].getKigBng(),fmVOs[i].getBunCod(),fmVOs[i].getFukSziCod(),fmVOs[i].getSmpKbn());
							fmVOs[i].setFukSziSuu((fukSziSuu == null)? "0" : fukSziSuu);
						}else
							fmVOs[i].setFukSziSuu("0");

						String syrzmisgn = "1";
						String cykKbn = getCykKbn(fmVOs[i].getNhnMei(),syrkbn,bshCod);
						String _seqno = StringUtils.lpad(seqno+"",3,"0");
						//���������e�[�u���ɏ�������
						fmDao.insertHac02(fmVOs[i],upddte,updjkk,syrsuu,_seqno,syrzmisgn,cykKbn,syrkbn);
						fmVOs[i].setChoksoKbn(cykKbn);//�����敪���Z�b�g
						//�������f�[�^��������
						orderUtils.insertHac01(
							fmVOs[i].getDaiKaiSkbCod(),//��\���
							fmVOs[i].getKaiSkbCod(),//���
							String.valueOf(upddte),//���������t
							syrsuu,//������
							_seqno,//SEQNO
							0,//�f�[�^�����t���O
							upddte,
							updjkk);
						
						if(syrkbn.equals(PrsOrderForm.PRSHACHU)){
							//�����ލ݌ɂ̑��݃`�F�b�N�E�o�^(����)
							orderUtils.insertHin12List(
								fmVOs[i].getDaiKaiSkbCod(),
								fmVOs[i].getKaiSkbCod(),
								fmVOs[i].getKigBng(),
								fmVOs[i].getSmpKbn(),
								fmVOs[i].getFukSziCod_arr(),
								fmVOs[i].getBunCod_arr(),
								upddte,
								updjkk);
							//�����ލ݌ɂ��v���X�������}�C�i�X����
							orderUtils.updateHin12(fmVOs[i].getDaiKaiSkbCod(),fmVOs[i].getKaiSkbCod(),
												   fmVOs[i].getKigBng(),fmVOs[i].getSmpKbn(),(Integer.parseInt(fmVOs[i].getPrsHacSuu()) * -1),upddte,updjkk);
							//�݌Ƀe�[�u���X�V 2003/08/06 add
							if(fmVOs[i].getSmpKbn().equals(""))
								orderUtils.updateZai01(fmVOs[i].getDaiKaiSkbCod(),fmVOs[i].getKigBng(),
											   	   Integer.parseInt(fmVOs[i].getPrsHacSuu()),upddte,updjkk,OrderUtils.PRSHAC);
							
							//IkkatsuRefer�֓n�� ��o�͍ϣ �T�C��
							fmVOs[i].setSyrZmiSgn_prs(IkkatsuReferDAO.SYRZMI);
							
						}else if(syrkbn.equals(PrsOrderForm.FUKHACHU)){
							//HIN12�����݂��Ȃ������� 2003/09/22 add
							if(orderUtils.findHin12(fmVOs[i].getDaiKaiSkbCod(),fmVOs[i].getKaiSkbCod(),fmVOs[i].getKigBng(),fmVOs[i].getBunCod(),fmVOs[i].getFukSziCod(),fmVOs[i].getSmpKbn()) == null)
								orderUtils.insertHin12(fmVOs[i].getDaiKaiSkbCod(),fmVOs[i].getKaiSkbCod(),fmVOs[i].getKigBng(),fmVOs[i].getFukSziCod(),0,fmVOs[i].getBunCod(),fmVOs[i].getSmpKbn(),upddte,updjkk);
							//�݌Ƀe�[�u���X�V 2003/08/06 add
							if(fmVOs[i].getSmpKbn().equals(""))
								orderUtils.updateZai01(fmVOs[i].getDaiKaiSkbCod(),fmVOs[i].getKigBng(),
											   	   Integer.parseInt(fmVOs[i].getPrsHacSuu()),upddte,updjkk,OrderUtils.FUKHAC);
							
							fmVOs[i].setSyrZmiSgn_fuk(IkkatsuReferDAO.SYRZMI);
						}
						
					}catch(SQLException sqle2){
						SystemException se = new SystemException(sqle2);
						se.printStackTrace();
						error = true;
						results[i] = new PrsOrderResult(fmVOs[i],false,"");
						continue;
					}
					//����ɐ���
					results[i] = new PrsOrderResult(fmVOs[i],true,"");
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
				boolean flg = false;
				for(int i = 0;i < fmVOs.length;i++){
					if(fmVOs[i] == null)
						continue;
					if(fmVOs[i] != null && !flg){//results�ɏ����񐔂��Z�b�g
						results[i].setSyrSuu(number[0]+"");
						flg = true;
					}
					if(syrkbn.equals(PrsOrderForm.PRSHACHU)){
						//�v���X���������Z�b�g���Ȃ���
						this.setPrsHacJoho(fmVOs[i]);
					}else if(syrkbn.equals(PrsOrderForm.FUKHACHU)){
						//�����ޔ��������Z�b�g���Ȃ���
						this.setFukHacJoho(fmVOs[i]);
					}
					//�`�F�b�N�{�b�N�X���N���A
					fmVOs[i].setCheck_prs1(false);
				}
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
	 * �����ރ}�X�^�[�����i�����ރR�[�h�v���_�E�����X�g�̎擾�j
	 * @param daiKaiSkbCod
	 * @param kaiSkbCod
	 * @return fukSziList
	 * @throws SQLException
	 */
	public ArrayList doFukSziList(String daiKaiSkbCod, String kaiSkbCod)
		throws SQLException{
		
		PrsOrderDAO fmDao = null;
		ArrayList fukSziList = null;

		try{
			fmDao = new PrsOrderDAO();

			//�����ރ}�X�^�[����
			fukSziList = fmDao.getFukSziList(daiKaiSkbCod,kaiSkbCod);

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
		return fukSziList;
		
	}
	
//------------------------------------------------------------------------------------
	
	/** �v���X���������Z�b�g���Ȃ��� */
	public void setPrsHacJoho(PrsOrderVO fmVO){
		fmVO.setPrsHacSuu2(new String(fmVO.getPrsHacSuu1()));
		fmVO.setPrsHacNyo2(new String(fmVO.getPrsHacNyo1()));
		fmVO.setPrsHacNki2(new String(fmVO.getPrsHacNki1()));
		fmVO.setPrsKbn2(new String(fmVO.getPrsKbn1()));
		fmVO.setPrsHacSuu1(fmVO.getPrsHacSuu());
		fmVO.setPrsHacNyo1("0");
		fmVO.setPrsHacNki1(fmVO.getPrsNki()+"");
		fmVO.setPrsKbn1(fmVO.getKbn());
		
	}
	/** �����ޔ��������Z�b�g���Ȃ��� */
	public void setFukHacJoho(PrsOrderVO fmVO){
		fmVO.setFukHacSuu2(new String(fmVO.getFukHacSuu1()));
		fmVO.setFukHacNki2(new String(fmVO.getFukHacNki1()));
		fmVO.setFukKbn2(new String(fmVO.getFukKbn1()));
		fmVO.setFukHacSuu1(fmVO.getPrsHacSuu());
		fmVO.setFukHacNki1(fmVO.getPrsNki()+"");
		fmVO.setFukKbn1(fmVO.getKbn());
	}
	
	/** 
	 * %�^�J�Z%�܂���%�i�`�q�d�c% �� �����敪 = "0" ����ȊO �� �����敪 = "1"
	 * �����ޔ����̂Ƃ� �� �����敪 = "0"
	 */
	private final String getCykKbn(String str,String syrkbn,String bshCod){
		if(syrkbn.equals(PrsOrderForm.FUKHACHU)){
			return	"0";
		}
		return DataFormatUtils.getCykKbn(str, bshCod);
	}


}

