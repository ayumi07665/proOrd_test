/**
* IkkatsuReferDelegate  �ꊇ�Ɖ��� �������s�N���X
*
*	�쐬��    2003/03/31
*	�쐬��    �i�m�h�h�j���c �Ĕ�
* �����T�v    ���������s����N���X�B
*
*	[�ύX����]
*       2003/05/06�i�m�h�h�j�g�c �h�q
* 			�E��v���X�R�����g���ͣ �@�\�̒ǉ��B(�G���[�`�F�b�N�ǉ�)
* 		2003/07/01
* 			�E�����ޔ����̂Ƃ��A�[�i�於�̎擾 �ǉ��B
* 		2003/07/17�i�m�h�h�j���c �Ĕ�
* 			�E�\���̌v�Z���烊�U�[�u���폜�B
* 			�E�v���X�[���̐ݒ���@�ύX�B
* 			�E�i�Ԏw�莞�A�����ɂ�钊�o���s��Ȃ��B
* 		2003/07/24�i�m�h�h�j���c �Ĕ�
* 			�E���������������ݎ��A�v���X�������}�C�i�X����O�̕����ލ݌ɐ����擾
* 			  ���������E�������f�[�^�̕����ލ݌ɐ��ɃZ�b�g
* 		2003/07/31�i�m�h�h�j���c �Ĕ�
* 			�E��Đ��̌v�Z���@�ύX (�����_�ȉ��؂�グ(+0.99) �� �l�̌ܓ�(+0.5))
* 			�E�\���̌v�Z���@�ύX ((�݌ɐ� + �I��ϑ��c + �v���X�����Ɍv) -�i���c + ������) �c + �v���X�����Ɍv�ǉ�)
* 		2003/08/06�i�m�h�h�j���c �Ĕ� 
* 			�E�����w���A�`�[���s���A�݌Ƀe�[�u���̍X�V�ǉ�
* 		2003/08/19�i�m�h�h�j���c �Ĕ� 
* 			�E���o�����F(���Y���[�h�^�C�� * ���ϔ���)��(�\�� + �v���X�����Ɍv)�ȉ���������\�����Ȃ�
*			  ����A+ �v���X�����Ɍv ���폜   �c7/31�̏C���ŁA�\���̌v�Z�Ƀv���X�����Ɍv��������ꂽ����
* 		2003/08/19�i�m�h�h�j���c �Ĕ� 
* 			�E���o�����F�w����ʂŁu��Ώۃf�[�^�\���v���I�����ꂽ�ꍇ�A
* 			  �w�肳�ꂽ�͈͂ɉ����āA�f�[�^�𒊏o����@�\��ǉ�
* 		2003/12/16�i�m�h�h�j�X 
* 			�E�����ޔ����Ɋւ�����̕ύX
* 		2005/05/24�i�m�h�h�j�g�c
* 			�E�j�Ђ̏ꍇ�A�[���i�������t�{�V���j�ɏC��
* 		2006/08/08 �i�m�h�h�j���c �Ĕ�
* 			�E��Đ��Z�o�p�f�[�^�擾���ɁA�e�[�u�����������Ĉ�x�Ŏ擾����悤�ɕύX
* 		 2006/12/19�i�m�h�h�j�c��
* 			�E�e�w�Ё@�[���f�t�H���g�l�ύX
*/

package com.jds.prodord.reference.ikkatsurefer;
import java.sql.*;
import java.util.*;
import java.math.BigDecimal;

import com.jds.prodord.common.*;
import com.jds.prodord.indicate.teiansuurefer.*;

public class IkkatsuReferDelegate {

	public static final String ANOTHER_USER_UPDATE = "ANOTHER_USER_UPDATE";
	public static final String EXIST = "EXIST";
	public static final String LOGICAL_DELETE = "LOGICAL_DELETE";
	public static final String HACSAKI_NOT_EXIST = "HACSAKI_NOT_EXIST";


	/**
	 * ����
	 * 
	 */
	public IkkatsuReferVO[] doFind(TeiansuuReferVO queryVO)throws SQLException{

ProcessLogUtils prcTime = new ProcessLogUtils("��Đ��Ɖ�", 1);
prcTime.start();
int total = 0;

		IkkatsuReferDAO fmDao = null;
		ArrayList finded_Arr = new ArrayList();
		
		IkkatsuReferVO[] finded = null;
		
		//�v���X�[���E�����ޔ[���̎Z�o(�S�s����)
		int prsNki = this.calculatePrsNki(queryVO.getDaiKaiSkbCod());
		int fukNki = this.calculateFukNki(queryVO.getDaiKaiSkbCod());
		
		try{

			fmDao = new IkkatsuReferDAO();

ProcessLogUtils selectTime = new ProcessLogUtils("��Đ��Z�o�p�f�[�^����", 2);
selectTime.start();

			//��Đ��f�[�^����
			finded = fmDao.findTeiansuuData(queryVO);
			
total = finded.length;			
selectTime.outTotal(total);
selectTime.end();

ProcessLogUtils computeTime = new ProcessLogUtils("���o���ڂ̃Z�b�g�E���o", 2);
computeTime.start();

			for(int i = 0; i<finded.length; i++){
				/*------------���o���ڂ̃Z�b�g------------*/
				//�\��
				this.computeKanouSuu(finded[i]);
				//�s���݌ɐ�
				this.computeFskZaiSuu(finded[i]);
				//��Đ�
				this.computeTeianSuu(finded[i]);

				finded_Arr.add(finded[i]);//ArrayList�Ɋi�[
			}

			//�i�ԑI���Ȃ��������� 2003/07/17 add
			if(queryVO.getKigBng_arr().size() == 0){

				//�����ɍ����f�[�^�𒊏o  2003/09/19 �Ώۃf�[�^�敪�ǉ�
				this.extractData(finded_Arr,queryVO.getTaisyoDataKbn(),queryVO.getTaisyoRange());

			}
			finded = (IkkatsuReferVO[])finded_Arr.toArray(new IkkatsuReferVO[finded_Arr.size()]);//�z��ɖ߂�

computeTime.end();

ProcessLogUtils otherTime = new ProcessLogUtils("���̑��f�[�^�Z�b�g", 2);
otherTime.start();

			OrderUtils orderUtils = new OrderUtils(fmDao);
			for(int i = 0; i<finded.length; i++){
				//��s�t���O ����ʐ���̂��߂̃t���O�Ƃ��Ďg�p����
				finded[i].setBasedRowFlg("1");
				//�����ޔ�����E���́A���ރR�[�h�擾
				orderUtils.findFukSziHacSaki(finded[i]);					
				//���������e�[�u�������i�v���X�j
				fmDao.findHac02Prs(finded[i]);
				//���������e�[�u�������i�����ށj
				fmDao.findHac02Fuk(finded[i]);
				
				if(finded[i].getExsitZai01() && finded[i].getExsitMst05() && finded[i].getExsitMst04()){
					//�v���X������
					finded[i].setPrsHacSuu(finded[i].getTeianSuu());
					
					//�����ޔ�����
				// 2003.12.16�@upd�@�����ޔ����Ɋւ�����̕ύX�@st ****
					if(!finded[i].getPrsHacSuu().equals("") && !finded[i].getFukZaiSuu().equals("") && !finded[i].getFukMnyKei().equals("")){
						//�v���X��Đ� >= �����ލ݌ɐ�
						//�v���X������ >= �����ލ݌ɐ��@+�@�����ޖ����Ɍv (2003.12.16 upd)
						if(Integer.parseInt(finded[i].getPrsHacSuu()) >= Integer.parseInt(finded[i].getFukZaiSuu()) + Integer.parseInt(finded[i].getFukMnyKei())){
							finded[i].setFukHacSuu(finded[i].getFukHacSuu1());//�O�񕛎��ޔ��������Z�b�g
							finded[i].setCheck_fuk1(true);
						}else{
							finded[i].setFukHacSuu("0");	
						}
						//�v���X��Đ� * 2 >= �����ލ݌ɐ� 
						//�v���X������ * 2 >= �����ލ݌ɐ� +�@�����ޖ����Ɍv(2003.12.16 upd)
						int prsHacSuu_twice = Integer.parseInt(finded[i].getPrsHacSuu()) * 2;
						if(prsHacSuu_twice >= Integer.parseInt(finded[i].getFukZaiSuu()) + Integer.parseInt(finded[i].getFukMnyKei())){
							finded[i].setCheck_flg(true);
						}
				// 2003/12/16�@upd�@�����ޔ����Ɋւ�����̕ύX�@ed ****
					}
					
				}
				//�����ނ̃`�F�b�N�T�C��
				if(!finded[i].getTeianSuu().equals(""))
					finded[i].setCheck_sgn(this.getFukSziSgnString(finded[i].getCheck_fuk1(),finded[i].getCheck_flg()));
				
				//�v���X�[���E�����ޔ[��
				finded[i].setPrsNki(prsNki);
				finded[i].setFukNki(fukNki);
				
				//�݌ɓ���
				if(finded[i].getExsitZai01()){
					this.computeZaiNiSuu(finded[i]);
				}
				//�����敪
				finded[i].setChoksoKbn("");
				if(queryVO.getCommand().equals(IkkatsuReferForm.COMMAND_JIDOUHACHU)){
					finded[i].setCheck_prs1(true);
					finded[i].setCheck_prs2(true);
					finded[i].setCheck_prsHacSuu(true);
				}
			}
			
otherTime.end();

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

prcTime.log("TOTAL�F" + finded.length + "/" + total + "��");
prcTime.end();
		return finded;
	}
	

	/**
	 * �����w��
	 * 
	 */
	public IkkatsuReferResult[] doHacShiji(IkkatsuReferVO[] fmVOs)throws SQLException{
		IkkatsuReferDAO fmDao = null;
	
		IkkatsuReferResult[] results = new IkkatsuReferResult[fmVOs.length];

		boolean endTransaction = false;
		try{
			fmDao = new IkkatsuReferDAO();
			fmDao.setAutoCommit(false); //DB�̃g�����U�N�V�����J�n


			//�X�V����
			DateUtils dateUtils = new DateUtils();
			int upddte = dateUtils.getDate6Int();
			int updjkk = dateUtils.getTime6Int();

			boolean error = false;
			for(int i = 0;i < fmVOs.length;i++){
				if(fmVOs[i] == null)
					continue;			
				//�����摶�݃`�F�b�N
			    if(!CheckCommon.hasHacCod(fmVOs[i].getDaiKaiSkbCod(),fmVOs[i].getPrsMkrCod())){			    	
				    results[i] = new IkkatsuReferResult(fmVOs[i],false,HACSAKI_NOT_EXIST);
				    error = true;
					continue;
			    }				
			}
		 

			//�G���[�̃��R�[�h���Ȃ����insert���s
			if(!error){
				//�����񐔂̍̔�
				int[] number = null;
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
						//�v���X�������}�C�i�X����O�̕����ލ݌ɐ����擾 2003/07/24 add
						String fukSziSuu =
							orderUtils.findHin12(
								fmVOs[i].getDaiKaiSkbCod(),
								fmVOs[i].getKaiSkbCod(),
								fmVOs[i].getKigBng(),
								"1",
								fmVOs[i].getFukSziCod(),
								fmVOs[i].getSmpKbn());
						fmVOs[i].setFukSziSuu((fukSziSuu == null)? "0" : fukSziSuu);

						String syrzmisgn = "0";
						//���������e�[�u���ɏ�������
						fmDao.insertHac02(fmVOs[i],upddte,updjkk,syrsuu,StringUtils.lpad(seqno+"",3,"0"),syrzmisgn);

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
						orderUtils.updateZai01(fmVOs[i].getDaiKaiSkbCod(),fmVOs[i].getKigBng(),
											   Integer.parseInt(fmVOs[i].getPrsHacSuu()),upddte,updjkk,OrderUtils.PRSHAC);
					
					}catch(SQLException sqle2){
						SystemException se = new SystemException(sqle2);
						se.printStackTrace();
						error = true;
						results[i] = new IkkatsuReferResult(fmVOs[i],false,"");
						continue;
					}
					//����ɐ���
					results[i] = new IkkatsuReferResult(fmVOs[i],true,"");
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
				
				for(int i = 0;i < fmVOs.length;i++){
					if(fmVOs[i] == null)
						continue;
					//�����σT�C����"1"�i�����ρj���Z�b�g
					fmVOs[i].setHacZmiSgn(IkkatsuReferDAO.HACZMI);
					//�`�F�b�N�{�b�N�X���N���A
					fmVOs[i].setCheck_prs1(false);
					fmVOs[i].setCheck_prs2(false);
					fmVOs[i].setCheck_prsHacSuu(false);
					//�v���X���������Z�b�g���Ȃ���
					this.setPrsHacJoho(fmVOs[i]);

					fmVOs[i].setUpdDte(upddte);
					fmVOs[i].setUpdJkk(updjkk);
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
	 * �`�[���s
	 * 
	 */
	public IkkatsuReferResult[] doDnpHakkou(IkkatsuReferVO[] fmVOs)throws SQLException{
		IkkatsuReferDAO fmDao = null;
	
		IkkatsuReferResult[] results = new IkkatsuReferResult[fmVOs.length];

		boolean endTransaction = false;
		try{
			fmDao = new IkkatsuReferDAO();
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
			    if(!CheckCommon.hasHacCod(fmVOs[i].getDaiKaiSkbCod(),fmVOs[i].getPrsMkrCod())){			    	
				    results[i] = new IkkatsuReferResult(fmVOs[i],false,HACSAKI_NOT_EXIST);
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
						
					seqno++;//fmVOs[i] == null�ł͂Ȃ�������SEQNO++
					try{
						//�v���X�������}�C�i�X����O�̕����ލ݌ɐ����擾 2003/07/24 add
						String fukSziSuu =
							orderUtils.findHin12(
								fmVOs[i].getDaiKaiSkbCod(),
								fmVOs[i].getKaiSkbCod(),
								fmVOs[i].getKigBng(),
								"1",
								fmVOs[i].getFukSziCod(),
								fmVOs[i].getSmpKbn());
						fmVOs[i].setFukSziSuu((fukSziSuu == null)? "0" : fukSziSuu);

						String syrzmisgn = "1";
						String _seqno = StringUtils.lpad(seqno+"",3,"0");
						//���������e�[�u���ɏ�������
						fmDao.insertHac02(fmVOs[i],upddte,updjkk,syrsuu,_seqno,syrzmisgn);
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
						orderUtils.updateZai01(fmVOs[i].getDaiKaiSkbCod(),fmVOs[i].getKigBng(),
											   Integer.parseInt(fmVOs[i].getPrsHacSuu()),upddte,updjkk,OrderUtils.PRSHAC);
											   
					}catch(SQLException sqle2){
						SystemException se = new SystemException(sqle2);
						se.printStackTrace();
						error = true;
						results[i] = new IkkatsuReferResult(fmVOs[i],false,"");
						continue;
					}
					//����ɐ���
					results[i] = new IkkatsuReferResult(fmVOs[i],true,"");
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
					fmVOs[i].setHacZmiSgn(IkkatsuReferDAO.SYRZMI);//�����σT�C����"2"�i�o�͍ρj���Z�b�g
					//�v���X���������Z�b�g���Ȃ���
					this.setPrsHacJoho(fmVOs[i]);
					//�`�F�b�N�{�b�N�X���N���A
					fmVOs[i].setCheck_prs1(false);
					fmVOs[i].setCheck_prs2(false);
					fmVOs[i].setCheck_prsHacSuu(false);
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
	 * �����ޔ���
	 * 
	 */
	public IkkatsuReferVO[] doFukHachu(IkkatsuReferVO[] fmVOs)throws SQLException{
						
		IkkatsuReferDAO fmDao = null;
		ArrayList finded_Arr = new ArrayList();
		IkkatsuReferVO[] finded = null;
		try{
			fmDao = new IkkatsuReferDAO();
			for(int i = 0; i < fmVOs.length; i++){
				if(fmVOs[i] == null)
						continue;
				//�����ޏ��擾
				finded_Arr.addAll(makeFukDataList(fmVOs[i]));
			}
			finded = (IkkatsuReferVO[])finded_Arr.toArray(new IkkatsuReferVO[finded_Arr.size()]);//�z��ɖ߂�
			for(int i = 0; i < finded.length; i++){
				//���������e�[�u�������i�����ށj
				fmDao.findHac02Fuk(finded[i]);

				//2003/07/01 
				fmDao.findMst03(finded[i],IkkatsuReferDAO.fukHacMei);//�����於�̎擾
				fmDao.findMst03(finded[i],IkkatsuReferDAO.nhnMei);//�[�i�於�̎擾
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
		return (IkkatsuReferVO[])finded_Arr.toArray(new IkkatsuReferVO[finded_Arr.size()]);//�z��ɖ߂�
	}


	/**
	 * �擾���Ă��镛���ޏ������ɕ����ޏ�񃊃X�g�𐶐����܂��B
	 * @param fmVO
	 * @return �����ޏ�񃊃X�g
	 */
	private ArrayList makeFukDataList(IkkatsuReferVO fmVO) {
	
		ArrayList finded_arr = new ArrayList();
		ArrayList fukSziCod_arr = fmVO.getFukSziCod_arr(); //�����ރR�[�harr
		ArrayList fukSziHacSaki_arr = fmVO.getFukSziHacSaki_arr(); //�d����R�[�harr
		ArrayList bunCod_arr = fmVO.getBunCod_arr(); //���ރR�[�harr
		ArrayList fukSziNm_arr = fmVO.getFukSziNm_arr(); //�����ޖ���arr
	
		finded_arr.add(fmVO); //�����ς݂̂���(��s�t���O=1�ɂ��������)��add
	
		//���[�v��1����J�n
		for (int i = 1; i < fukSziCod_arr.size(); i++) {
			IkkatsuReferVO vo = new IkkatsuReferVO();
			vo.setDaiKaiSkbCod(fmVO.getDaiKaiSkbCod());
			vo.setKaiSkbCod(fmVO.getKaiSkbCod());
			vo.setUsrId(fmVO.getUsrId());
			vo.setKigBng(fmVO.getKigBng());
			vo.setFukSziHacSaki(String.valueOf(fukSziHacSaki_arr.get(i))); //�����ޔ�����
			vo.setFukSziNm(String.valueOf(fukSziNm_arr.get(i)));           //�����ޖ���
			vo.setBunCod(String.valueOf(bunCod_arr.get(i)));               //���ރR�[�h
			vo.setFukSziCod(String.valueOf(fukSziCod_arr.get(i)));         //�����ރR�[�h
			vo.setBasedRowFlg("2");                                        //��s�t���O
	
			finded_arr.add(vo);
		}
	
		return finded_arr;
	}
	
	
//---------------------------------------------------------------------------------------------
	
	/** �\���̌v�Z */
	public void computeKanouSuu(IkkatsuReferVO fmVO){
		if(!fmVO.getExsitZai01())
			return;

		int azisuu    = Integer.parseInt(fmVO.getAziSuu());
		int tnasekzan = Integer.parseInt(fmVO.getTnaSekZan());
//		int rsvsum    = Integer.parseInt(fmVO.getRsvSum());//2003/07/16 �폜
		int juczan    = Integer.parseInt(fmVO.getJucZan());
		int mhiksuu   = Integer.parseInt(fmVO.getMhikSuu());
		int prsmnykei = Integer.parseInt(fmVO.getPrsMnyKei());//2003/07/31 add
		
		//�i(�݌ɐ� + �I��ϑ��c + �v���X�����Ɍv) -�i���c + ������)�j ��VO�ɃZ�b�g
		int kanousuu = (azisuu + tnasekzan + prsmnykei) - (juczan + mhiksuu);
		fmVO.setKanouSuu(kanousuu+"");
		
	}
	/** �s���݌ɐ��̌v�Z */
	public void computeFskZaiSuu(IkkatsuReferVO fmVO){
		if(!fmVO.getExsitZai01() || !fmVO.getExsitMst04())
			return;
			
		int hackankaku = fmVO.getKsnNisuu();
		int avguri     = Integer.parseInt(fmVO.getAvgUri());
		int kanousuu   = Integer.parseInt(fmVO.getKanouSuu());

		//�i�i���ϔ��� * �����Ԋu(�݌ɓ���)�j - �i�\���j��VO�ɃZ�b�g
		int fskzaisuu = (avguri * hackankaku) - (kanousuu);
		fmVO.setFskZaiSuu(fskzaisuu+"");
		
	}

	/** ��Đ��̌v�Z */
	public void computeTeianSuu(IkkatsuReferVO fmVO){
		if(!fmVO.getExsitZai01() || !fmVO.getExsitMst05() || !fmVO.getExsitMst04())
			return;

		double fskzaisuu = Double.parseDouble(fmVO.getFskZaiSuu());
		double mrmsuu    = (double)fmVO.getMrmSuu();
		double d         = 0.5;
		
		int i = 0;
		
		/*�s���݌Ɂ^�܂�ߐ��� + 0.5 �� �����_�ȉ��؂�̂Ă������ʁA
		  �O�ɂȂ��Ă��܂����ꍇ�͂P�Ƃ��� 2003/08/05 add  */
		if(fskzaisuu > 0){
			if((int)(fskzaisuu / mrmsuu + d) == 0)
				i = 1;
			else
				i = (int)(fskzaisuu / mrmsuu + d);
		}
		
		//�i�s���݌Ɂ^�܂�ߐ��� + 0.5 �� �����_�ȉ��؂�̂āj�~ �܂�ߐ��� ��VO�ɃZ�b�g
		int teiansuu = (int)(i * mrmsuu);
		fmVO.setTeianSuu(teiansuu+"");
		
	}
	
	/** �݌ɓ����̌v�Z */
	public void computeZaiNiSuu(IkkatsuReferVO fmVO){
		if(!fmVO.getExsitZai01())
			return;

		int kanousuu  = (fmVO.getKanouSuu().equals(""))? 0 : Integer.parseInt(fmVO.getKanouSuu());
		int avguri    = (fmVO.getAvgUri().equals(""))? 0 : Integer.parseInt(fmVO.getAvgUri());
		
		if(avguri == 0){
			fmVO.setZaiNisuu("0");
			return;	
		}else if(kanousuu < 0){
			fmVO.setZaiNisuu("0");
			return;
		}
		
		//�i�\�� �^ ���ϔ���j������Q�ʂ��l�̌ܓ� ��VO�ɃZ�b�g
		BigDecimal kanousuu_b = new BigDecimal(kanousuu);
		BigDecimal avguri_b = new BigDecimal(avguri);
		BigDecimal zainisuu = kanousuu_b.divide(avguri_b, 1, BigDecimal.ROUND_HALF_UP);//������Q�ʂ��l�̌ܓ�

		fmVO.setZaiNisuu(zainisuu+"");		
	}

	/** �f�[�^�̒��o *///2003/09/19 �Ώۃf�[�^�敪�ǉ� 2003/10/09 ��ΏۑI�����̑Ώ۔͈͑I���@�\�ǉ�
	public void extractData(ArrayList arr, int taisyoDataKbn, String taisyoRange){
//System.out.println("----------------------------------------");
			
		for(int i = 0; i<arr.size(); i++){
			IkkatsuReferVO fmVO = (IkkatsuReferVO)arr.get(i);
			int ssnredtim = fmVO.getSsnRedTim();
			int avguri    = (fmVO.getAvgUri().equals(""))? 0 : Integer.parseInt(fmVO.getAvgUri());
			int kanousuu  = (fmVO.getKanouSuu().equals(""))? 0 : Integer.parseInt(fmVO.getKanouSuu());
//			int prsmnykei = (fmVO.getPrsMnyKei().equals(""))? 0 : Integer.parseInt(fmVO.getPrsMnyKei()); 2003/08/19 �폜

			boolean remove_flg = false;	
			//�i���Y���[�h�^�C�� * ���ϔ��� <= �\���j �̂��̂���菜��
			if((ssnredtim * avguri) <= kanousuu){
				remove_flg = true;
//System.out.println("���Y���[�h�^�C�� * ���ϔ��� <= �\�� : " + fmVO.getKigBng());
			//��Đ� = 0 �̂��̂���菜��
			}else if(fmVO.getTeianSuu().equals("0")){
//System.out.println("��Đ� = 0 : " + fmVO.getKigBng());
				remove_flg = true;
			//�����σT�C�� <> ���������̂���菜��
			}else if(fmVO.getHacZmiSgn() != IkkatsuReferDAO.MIHACHU){
//System.out.println("�����σT�C�� != ������ : " + fmVO.getKigBng());
				remove_flg = true;
			}
			if(taisyoDataKbn == TeiansuuReferVO.TAISYO_ONLY){
				if(remove_flg){//��Ώۃf�[�^�͍폜
					arr.remove(i);
					i--;
				}
			}else if(taisyoDataKbn == TeiansuuReferVO.HITAISYO_ONLY){//2003/10/09 �C��
				if(!remove_flg){//�Ώۃf�[�^���폜�A��Ώۃf�[�^���c��
					arr.remove(i);
					i--;
					continue;
				}
				int azisuu    = Integer.parseInt((fmVO.getAziSuu().equals(""))? "0" : fmVO.getAziSuu());
				switch(Integer.parseInt(taisyoRange)){
					case 1:
						remove_flg = (azisuu == 0);//�͈͓��Ȃ�� true : �͈͊O�Ȃ�� false
						break;
					case 2:
						remove_flg = (0 < azisuu && azisuu <= 10);
						break;
					case 3:
						remove_flg = (10 < azisuu && azisuu <= 30);
						break;
					case 4:
						remove_flg = (30 < azisuu && azisuu <= 50);
						break;
					case 5:
						remove_flg = (50 < azisuu && azisuu <= 100);
						break;			
				}
				if(!remove_flg){//��Ώۂ̒��ł��͈͊O�̂��̂��폜
					arr.remove(i);
					i--;
				}	
			}
		}
//System.out.println("----------------------------------------");
	}
	
	/** �v���X�[���̎Z�o */
	public int calculatePrsNki(String daiKaiSkbCod){
		int prsnki = 0;
		DateUtils dateUtils = new DateUtils();
		int youbi = dateUtils.getDayOfWeek();

		//�j�Ђ̏ꍇ 2005/05/24 add  LAN�iBAN)�Ђ̏ꍇ2013/06/03 add
		if(daiKaiSkbCod.equals(CommonConst.KAICOD_K) ||
				daiKaiSkbCod.equals(CommonConst.KAICOD_BAN)){
			dateUtils.addToDate(Calendar.DATE, 7);//�V����
		//FX�Ђ̏ꍇ 2006/12/19
		}else if(daiKaiSkbCod.equals(CommonConst.KAICOD_FX)){
			dateUtils.addToDate(Calendar.DATE, 14);//�P�S����
		}else{
			//���j����������
			if(youbi == Calendar.MONDAY){
				dateUtils.addToDate(Calendar.DATE, 4);//�S����
			//�΁`���j����������
			}else if(youbi != Calendar.SUNDAY && youbi != Calendar.SATURDAY){
				dateUtils.addToDate(Calendar.DATE, 6);//�U����
			}
		}
 		prsnki = dateUtils.getDate6Int();
		return prsnki;
	}
	
	/** �����ޔ[���̎Z�o */
	public int calculateFukNki(String daiKaiSkbCod){
		int fuknki = 0;
		DateUtils dateUtils = new DateUtils();
		int youbi = dateUtils.getDayOfWeek();

		//FX�Ђ̏ꍇ 2006/12/19�@LAN�iBAN)�Ђ̏ꍇ2013/06/03 add
		if(daiKaiSkbCod.equals(CommonConst.KAICOD_FX)||
				daiKaiSkbCod.equals(CommonConst.KAICOD_BAN)){
			dateUtils.addToDate(Calendar.DATE, 7);//�V����
		}else{
			//���j����������
			if(youbi == Calendar.MONDAY){
				dateUtils.addToDate(Calendar.DATE, 7);//�V����
			//�΁`���j����������
			}else if(youbi != Calendar.SUNDAY && youbi != Calendar.SATURDAY){
				dateUtils.addToDate(Calendar.DATE, 6);//�U����
			}
		}
		fuknki = dateUtils.getDate6Int();
		return fuknki;
	}
	
	
	/** �v���X���������Z�b�g���Ȃ��� */
	public void setPrsHacJoho(IkkatsuReferVO fmVO){
		fmVO.setPrsHacSuu2(new String(fmVO.getPrsHacSuu1()));
		fmVO.setPrsHacNyo2(new String(fmVO.getPrsHacNyo1()));
		if(!fmVO.getPrsHacNki1().equals(""))
			fmVO.setPrsHacNki2(new String(fmVO.getPrsHacNki1()));
		else
			fmVO.setPrsHacNki2("");
		fmVO.setPrsKbn2(new String(fmVO.getPrsKbn1()));
		
		fmVO.setPrsHacSuu1(fmVO.getPrsHacSuu());
		fmVO.setPrsHacNyo1("0");
		fmVO.setPrsHacNki1(fmVO.getPrsNki()+"");
		fmVO.setPrsKbn1(DataFormatUtils.getKbnCod(DataFormatUtils.KYUHU));
		
	}
	
	/** �����ރ`�F�b�N�T�C���̕\���������Ԃ� **/
	private String getFukSziSgnString(boolean check, boolean flg){
		String fukszisgn = "";
		if(flg){
			if(check)
				fukszisgn = IkkatsuReferForm.HACHU_HITYO;
			else
				fukszisgn = IkkatsuReferForm.JIKAI_HACHU;	
		}else{
			fukszisgn = IkkatsuReferForm.ZAIKO_JUBUN;			
		}
		return fukszisgn;
	}
	
	/** 2003/05/06 ****************************************************************
	 * 		�G���[�`�F�b�N														  *
	 ******************************************************************************/
	public IkkatsuReferResult[] doCheck(IkkatsuReferVO[] fmVOs)throws SQLException{
		IkkatsuReferDAO fmDao = null;
		IkkatsuReferResult[] results = new IkkatsuReferResult[fmVOs.length];

		boolean endTransaction = false;
		try{
			fmDao = new IkkatsuReferDAO();
			fmDao.setAutoCommit(false); //DB�̃g�����U�N�V�����J�n
			
			boolean error = false;
					
			for(int i = 0;i < fmVOs.length;i++){
				if(fmVOs[i] == null)
					continue;
					
				//�����摶�݃`�F�b�N
			    if(!fmDao.hasHacSakiMei(fmVOs[i])){
				    results[i] = new IkkatsuReferResult(fmVOs[i],false,HACSAKI_NOT_EXIST);
				    error = true;
					continue;
			    }else{//�G���[���Ȃ����
			    	//����ɐ���
					results[i] = new IkkatsuReferResult(fmVOs[i],true,"");
			    }
			}
			
		}finally{
			if(fmDao != null){
				try{
					fmDao.commit();
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

