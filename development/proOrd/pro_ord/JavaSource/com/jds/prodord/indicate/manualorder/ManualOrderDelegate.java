/**
* ManualOrderDelegate  �}�j���A�������w�����s�N���X
*
*	�쐬��    2003/04/25
*	�쐬��    �i�m�h�h�j���c �Ĕ�
* �����T�v    �}�j���A�������w�������s����N���X�B
*
*	[�ύX����]
*        2003/06/16�i�m�h�h�j�g�c �h�q
*  			�E�������ځi��ЁE���[�J�[���ށE�`�ԃR�[�h�E�M�m�敪�j�̒ǉ��B
* 			�E�[���̎����ݒ�B
* 		 2003/06/23
* 			�E������ޔ���� �ǉ��B
* 		 2003/06/30
* 			�E�����ޔ����̂Ƃ��A�[�i�於�擾 �ǉ��B
* 		 2003/07/11
* 			�E�i�Ԏw�莞�A�i�ԃ}�X�^�[�����݂��Ȃ������ꍇ�A
* 			  ��\��Х��Х���ރR�[�h="1'�ŕ����ރ}�X�^�[���Q�Ƃ��A�����ރR�[�h���擾 �ǉ��B
* 		 2003/07/17�i�m�h�h�j���c �Ĕ�
*  			�E�����ޔ������A�������Ƀf�t�H���g�ŕ����ޔ������P�i�ŐV�̔������j���Z�b�g�B
* 		 2003/12/19�i�m�h�h�j�X
* 			�E�S�Ă̕i�Ԃ𔭒�����O��Ń`�F�b�N��S�i�ԕt���ĕ\������
* 		 2004/03/04�i�m�h�h�j�X
* 			�EVAP�̂Ƃ��͕K�������ރR�[�h���\�������悤�Ƀ��O�C�����̉�Ў��ʃR�[�h��VO�ɃZ�b�g
* 		 2004/04/02 (NII) �X
* 			�E�����ޔ����̏C��(���ރR�[�h�P�������݂ɑΉ�)
*		 2005/02/03�i�m�h�h�j�g�c
*			�E�ꏊ�R�[�h�Ŕ[�i�於���擾����悤�ɏC��
*		 2005/05/25�i�m�h�h�j�g�c
*			�E�������ꊇ�o�͑Ή��i���[�U�[�h�c�̒ǉ��j
*			�E�j�Ђ̏ꍇ�A�[���i�������t�{�V���j�ɏC��
* 		 2006/05/10�i�m�h�h�j�c�[ �N��
* 			�E�L���O�Ђ̃����N���b�܂�
* 		 2006/12/19�i�m�h�h�j�c��
* 			�E�e�w�Ё@�[���f�t�H���g�l�ύX
*
*/

package com.jds.prodord.indicate.manualorder;

import com.jds.prodord.order.prsorder.*;
import java.sql.*;
import java.util.*;
import com.jds.prodord.common.*;


public class ManualOrderDelegate {


	/**
	 * �v���X����
	 * 
	 */
	public PrsOrderVO[] doPrsHachu(ManualOrderVO fmVO,ManualOrderForm fmForm)throws SQLException{
				 	
		ManualOrderDAO fmDao = null;
		PrsOrderVO[] finded = null;
				
		boolean endTransaction = false;
		try{
			fmDao = new ManualOrderDAO();
			fmDao.setAutoCommit(false); //DB�̃g�����U�N�V�����J�n
			//�������w��̂Ƃ�
			if(fmVO.getKigBng_arr().size() == 0){
				//�i�ԃ}�X�^�[����
				finded = fmDao.findHin01(fmVO);
				OrderUtils orderUtils = new OrderUtils(fmDao);
				for(int i = 0; i < finded.length; i++){
					finded[i].setQueryKaiSkbCod(fmVO.getQueryKaiSkbCod());
					finded[i].setUsrId(fmVO.getUsrId());//2005/05/23 add

					//�����ޔ�����R�[�h�E�����ޖ��̎擾
					orderUtils.findFukSziHacSaki(finded[i]);	
					//������}�X�^�[����
					fmDao.findHacNm(finded[i]);
					//�݌Ƀ}�X�^�[����
					fmDao.findZai01(finded[i]);
					//���������e�[�u�������i�v���X�j
					fmDao.findHac02Prs(finded[i]);
					finded[i].setBasedRowFlg("1");//��s�t���O
					//���������e�[�u�������i�����ށj
					fmDao.findHac02Fuk(finded[i]);
					finded[i].setKbn(fmVO.getKbn());//�敪
					finded[i].setPrsHacSuu("");//������
					finded[i].setChoksoKbn("0");//�����敪
					finded[i].setComment("");//�R�����g
					finded[i].setNhnMei(DataFormatUtils.getSkoNm(fmVO.getBshCod()));//�[�i�於�@2005/02/03 add
					// 2003.12.19 upd �S�i�ԂɃ`�F�b�N������@
					finded[i].setCheck_prs1(true);
					//2005/05/30 �[���iK�БΉ��j
					finded[i].setPrsNki(this.calculatePrsNki(finded[i].getHbiDte(),finded[i].getDaiKaiSkbCod()));//�[��
					if(i == 0){
						finded[i].setPre_page(PrsOrderForm.ManualOrder);//�J�ڌ��y�[�W
						finded[i].setPrs_FukSgn(PrsOrderForm.PRSHACHU);
					}
				}
			//�ǉ��i�Ԏw��̂Ƃ�
			}else{
				finded = new PrsOrderVO[fmVO.getKigBng_arr().size()];
				ArrayList kig_arr = fmVO.getKigBng_arr();

				OrderUtils orderUtils = new OrderUtils(fmDao);
				for(int i = 0; i < finded.length; i++){
					finded[i] = new PrsOrderVO();

					finded[i].setQueryKaiSkbCod(fmVO.getQueryKaiSkbCod());
					finded[i].setUsrId(fmVO.getUsrId());//2005/05/23 add

					//�i�ԃ}�X�^�[�����i�P������)
					if(!fmDao.findHin01_row(fmVO,kig_arr.get(i)+"",finded[i],ManualOrderDAO.HIN01))
						fmDao.findHin01_row(fmVO,kig_arr.get(i)+"",finded[i],ManualOrderDAO.HIN02);
					if(finded[i].getExsitHin01()){
						//������}�X�^�[����
						fmDao.findHacNm(finded[i]);
						//�݌Ƀ}�X�^�[����
						fmDao.findZai01(finded[i]);
						//�����ޔ�����R�[�h�E�����ޖ��̎擾
						orderUtils.findFukSziHacSaki(finded[i]);	
					}else{
						finded[i].setDaiKaiSkbCod(fmVO.getDaiKaiSkbCod());
						finded[i].setKaiSkbCod(fmVO.getQueryKaiSkbCod());//�i�Ԃ��}�X�^�[�ɑ��݂��Ȃ����̓��[�U�[�̉�Ђ��Z�b�g
						fmDao.findFuk01_NotExistHin(finded[i]);//2003/07/11 add
					}
					//���������e�[�u�������i�v���X�j
					fmDao.findHac02Prs(finded[i]);
					finded[i].setBasedRowFlg("1");//��s�t���O
					//���������e�[�u�������i�����ށj
					fmDao.findHac02Fuk(finded[i]);
					finded[i].setQueryKaiSkbCod(fmVO.getQueryKaiSkbCod());
					finded[i].setKbn(fmVO.getKbn());//�敪
					finded[i].setPrsHacSuu("");//������
					//2005/05/30 �[���iK�БΉ��j
					finded[i].setPrsNki(this.calculatePrsNki(finded[i].getHbiDte(),finded[i].getDaiKaiSkbCod()));//�[��
					finded[i].setChoksoKbn("0");//�����敪
					finded[i].setComment("");//�R�����g
					finded[i].setNhnMei(DataFormatUtils.getSkoNm(fmVO.getBshCod()));//�[�i�於�@2005/02/03 add
					// 2003.12.19 upd �S�i�ԂɃ`�F�b�N������@
					finded[i].setCheck_prs1(true);
					if(i == 0){
						finded[i].setPre_page(PrsOrderForm.ManualOrder);//�J�ڌ��y�[�W
						finded[i].setPrs_FukSgn(PrsOrderForm.PRSHACHU);
					}
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
		return finded;
	}
	
	/**
	 * �����ޔ���
	 * 
	 */
	public PrsOrderVO[] doFukHachu(ManualOrderVO fmVO,ManualOrderForm fmForm)throws SQLException{
				 	
		ManualOrderDAO fmDao = null;
		PrsOrderVO[] finded = null;
		ArrayList finded_Arr = new ArrayList();
		boolean hin_flg = false;
				
		boolean endTransaction = false;
		try{
			fmDao = new ManualOrderDAO();
			fmDao.setAutoCommit(false); //DB�̃g�����U�N�V�����J�n
			
			
			//�������w��̂Ƃ�
			if(fmVO.getKigBng_arr().size() == 0){
				
				//�i�ԃ}�X�^�[����
				finded = fmDao.findHin01(fmVO);
				OrderUtils orderUtils = new OrderUtils(fmDao);
				for(int i=0; i<finded.length; i++){
					finded[i].setQueryKaiSkbCod(fmVO.getQueryKaiSkbCod());  //2004.03.04
					finded[i].setUsrId(fmVO.getUsrId());//2005/05/25 add
					
					//�����ޔ�����R�[�h�E�����ޖ��̎擾
					orderUtils.findFukSziHacSaki(finded[i]);
					finded[i].setPrsMkrCod(finded[i].getFukSziHacSaki());//�����ޔ�����𔭒���R�[�h�ɃZ�b�g
				}

				for(int i = 0; i < finded.length; i++){
					//�����ރ}�X�^�[����
					finded_Arr.addAll(makeFukDataList(finded[i]));
				}

				finded = (PrsOrderVO[])finded_Arr.toArray(new PrsOrderVO[finded_Arr.size()]);//�z��ɖ߂�

				for(int i = 0; i < finded.length; i++){
				
					//������}�X�^�[����
//	2004.2.25 del	fmDao.findHacNm(finded[i]);
					//�݌Ƀ}�X�^�[����
					fmDao.findZai01(finded[i]);
					//���������e�[�u�������i�v���X�j
					fmDao.findHac02Prs(finded[i]);
					//���������e�[�u�������i�����ށj
					fmDao.findHac02Fuk(finded[i]);
					finded[i].setQueryKaiSkbCod(fmVO.getQueryKaiSkbCod());
					finded[i].setKbn(fmVO.getKbn());//�敪
					if(finded[i].getBasedRowFlg().equals("1"))
						finded[i].setPrsHacSuu(finded[i].getFukHacSuu1());//�������ɂ͕����ޔ�����1���Z�b�g
					finded[i].setPrsNki(this.calculateFukNki(finded[i].getHbiDte(),finded[i].getDaiKaiSkbCod()));//�[��
					finded[i].setChoksoKbn("0");//�����敪
					finded[i].setComment("");//�R�����g
					// 2003.12.19 upd �S�i�ԂɃ`�F�b�N������@
					finded[i].setCheck_prs1(true);

					fmDao.findMst03(finded[i],ManualOrderDAO.fukHacMei);//�����於�̎擾
					fmDao.findMst03(finded[i],ManualOrderDAO.nhnMei);//�[�i�於�̎擾

					if(i == 0){
						finded[i].setPre_page(PrsOrderForm.ManualOrder);//�J�ڌ��y�[�W
						finded[i].setPrs_FukSgn(PrsOrderForm.FUKHACHU);
					}
				}
			//�ǉ��i�Ԏw��̂Ƃ�
			}else{
				finded = new PrsOrderVO[fmVO.getKigBng_arr().size()];
				ArrayList kig_arr = fmVO.getKigBng_arr();
				OrderUtils orderUtils = new OrderUtils(fmDao);
				for(int i = 0; i < finded.length; i++){
					finded[i] = new PrsOrderVO();
					finded[i].setQueryKaiSkbCod(fmVO.getQueryKaiSkbCod());  //2004.03.04
					finded[i].setUsrId(fmVO.getUsrId());//2005/05/25 add

					//�i�ԃ}�X�^�[�����i�P������)
					if(!fmDao.findHin01_row(fmVO,kig_arr.get(i)+"",finded[i],ManualOrderDAO.HIN01))
						fmDao.findHin01_row(fmVO,kig_arr.get(i)+"",finded[i],ManualOrderDAO.HIN02);
					if(finded[i].getExsitHin01())
						orderUtils.findFukSziHacSaki(finded[i]);//2003/06/20
					finded[i].setPrsMkrCod(finded[i].getFukSziHacSaki());//�����ޔ�����𔭒���R�[�h�ɃZ�b�g  2003/07/17 add
				}

				for(int i = 0; i < finded.length; i++){
					//�����ރ}�X�^�[����
					finded_Arr.addAll(makeFukDataList(finded[i]));
				}

				finded = (PrsOrderVO[])finded_Arr.toArray(new PrsOrderVO[finded_Arr.size()]);//�z��ɖ߂�
				
				for(int i = 0; i < finded.length; i++){
					if(finded[i].getExsitHin01()){
						//������}�X�^�[����
//	2004.2.25 del		fmDao.findHacNm(finded[i]);
						
						//�݌Ƀ}�X�^�[����
						fmDao.findZai01(finded[i]);
						fmDao.findMst03(finded[i],ManualOrderDAO.fukHacMei);//�����於�̎擾
						fmDao.findMst03(finded[i],ManualOrderDAO.nhnMei);//�[�i�於�̎擾
					}else{
						finded[i].setDaiKaiSkbCod(fmVO.getDaiKaiSkbCod());
						finded[i].setKaiSkbCod(fmVO.getQueryKaiSkbCod());//�i�Ԃ��}�X�^�[�ɑ��݂��Ȃ����̓��[�U�[�̉�Ђ��Z�b�g
						//�����ރ}�X�^�[����
						fmDao.findFuk01_NotExistHin(finded[i]);//2003/07/11 add
					}
					//���������e�[�u�������i�v���X�j
					fmDao.findHac02Prs(finded[i]);
					//���������e�[�u�������i�����ށj
					fmDao.findHac02Fuk(finded[i]);
					finded[i].setQueryKaiSkbCod(fmVO.getQueryKaiSkbCod());
					finded[i].setKbn(fmVO.getKbn());//�敪
					if(finded[i].getBasedRowFlg().equals("1"))
						finded[i].setPrsHacSuu(finded[i].getFukHacSuu1());//�������ɂ͕����ޔ�����1���Z�b�g  2003/07/17 add
					finded[i].setPrsNki(this.calculateFukNki(finded[i].getHbiDte(),finded[i].getDaiKaiSkbCod()));//�[��
					finded[i].setChoksoKbn("0");//�����敪
					finded[i].setComment("");//�R�����g
					// 2003.12.19 upd �S�i�ԂɃ`�F�b�N������@
					finded[i].setCheck_prs1(true);

					if(i == 0){
						finded[i].setPre_page(PrsOrderForm.ManualOrder);//�J�ڌ��y�[�W
						finded[i].setPrs_FukSgn(PrsOrderForm.FUKHACHU);
					}

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
		return (PrsOrderVO[])finded_Arr.toArray(new PrsOrderVO[finded_Arr.size()]);//�z��ɖ߂�;
	}
	
	/**
	 * �擾���Ă��镛���ޏ������ɕ����ޏ�񃊃X�g�𐶐����܂��B
	 * @param fmVO
	 * @return �����ޏ�񃊃X�g
	 */
	private ArrayList makeFukDataList(PrsOrderVO fmVO) {	
	
		ArrayList finded_arr = new ArrayList();
		ArrayList fukSziCod_arr = fmVO.getFukSziCod_arr(); //�����ރR�[�harr
		ArrayList fukSziHacSaki_arr = fmVO.getFukSziHacSaki_arr();
		//�d����R�[�harr�@add 2004.02.25
		ArrayList bunCod_arr = fmVO.getBunCod_arr(); //���ރR�[�harr
		ArrayList fukSziNm_arr = fmVO.getFukSziNm_arr(); //�����ޖ���arr
	
		fmVO.setBasedRowFlg("1");
		finded_arr.add(fmVO); //�����ς݂̂���(��s�t���O=1�ɂ��������)��add
		
		if (!fmVO.getExsitHin01())
			return finded_arr;
	
		//���[�v��1����J�n
		for (int i = 1; i < fukSziCod_arr.size(); i++) {
			PrsOrderVO vo = new PrsOrderVO();
			vo.setDaiKaiSkbCod(fmVO.getDaiKaiSkbCod());
			vo.setKaiSkbCod(fmVO.getKaiSkbCod());
			vo.setUsrId(fmVO.getUsrId());
			vo.setKigBng(fmVO.getKigBng());
	
			vo.setFukSziHacSaki(String.valueOf(fukSziHacSaki_arr.get(i)));
			vo.setPrsMkrCod(String.valueOf(fukSziHacSaki_arr.get(i))); //�����ޔ�����
			vo.setFukSziNm(String.valueOf(fukSziNm_arr.get(i)));       //�����ޖ���
			vo.setBunCod(String.valueOf(bunCod_arr.get(i)));           //���ރR�[�h
			vo.setFukSziCod(String.valueOf(fukSziCod_arr.get(i)));     //�����ރR�[�h
			vo.setBasedRowFlg("2");                                    //��s�t���O
			//�i�ԏ��
			vo.setTitKj(fmVO.getTitKj());
			vo.setHjiHnb(fmVO.getHjiHnb());
			vo.setKetCod(fmVO.getKetCod());
			vo.setHbiDte(fmVO.getHbiDte());
			vo.setArtKj(fmVO.getArtKj());
			vo.setTomRak(fmVO.getTomRak());
			vo.setKetNm(fmVO.getKetNm());
			vo.setZikTik(fmVO.getZikTik()); //2004.04.21 add
			vo.setFukSziPtn(fmVO.getFukSziPtn());
			vo.setSetSuu(fmVO.getSetSuu());
			vo.setNhnCod(fmVO.getNhnCod());
			vo.setNhnMei(fmVO.getNhnMei());
			vo.setExsitHin01(fmVO.getExsitHin01());
	
			finded_arr.add(vo);
		}
		return finded_arr;
	}
	
	//--------------------------------------------------------------------
	
	/** �v���X�[���̎Z�o */
	public int calculatePrsNki(String hbiDte,String daiKaiSkbCod){
		if(hbiDte.equals("") || hbiDte.equals("0"))
			return 0;
		
		int prsnki = 0;
		//�����̓��t
		DateUtils duToday = new DateUtils();
		//2005/05/30 �j�Ђ̏ꍇ�A�[��(���� + �V��)
		if(daiKaiSkbCod.equals(CommonConst.KAICOD_K)||
				daiKaiSkbCod.equals(CommonConst.KAICOD_BAN)){
			duToday.addToDate(Calendar.DATE, 7);//�V����
			prsnki = duToday.getDate6Int();
		}else{

			//������
			DateUtils duHbiDte = new DateUtils(hbiDte, DateUtils.PTN_DATE_6);
			duHbiDte.addToDate(Calendar.DATE, -14);//�������|�P�S��

			//2006/12/19 �e�w�Ђ̏ꍇ
			if(daiKaiSkbCod.equals(CommonConst.KAICOD_FX)){
				//�������|�P�S�����������O�̓��t��������
				if(duToday.getDate8Int() > duHbiDte.getDate8Int()){
					int hbiDte8 = DateUtils.convertYYYYMMDD(Integer.parseInt(hbiDte));
					if(duToday.getDate8Int() > hbiDte8){
						duToday.addToDate(Calendar.DATE, 14);//�P�S����
						prsnki = duToday.getDate6Int();//�P�S����
					}else{
						prsnki = duToday.getDate6Int();//�������t
					}	
				}else{
					prsnki = duHbiDte.getDate6Int();//�������|�P�S��
				}
			}else{
				//�������|�P�S�����������O�̓��t��������
				if(duToday.getDate8Int() > duHbiDte.getDate8Int())
					prsnki = duToday.getDate6Int();//�������t
				else
					prsnki = duHbiDte.getDate6Int();//�������|�P�S��
			}
		}
		return prsnki;
	}

	
	/** �����ޔ[���̎Z�o */
	public int calculateFukNki(String hbiDte,String daiKaiSkbCod){
		if(hbiDte.equals("") || hbiDte.equals("0"))
			return 0;
			
		int fuknki = 0;
		//�����̓��t
		DateUtils duToday = new DateUtils();
		//������
		DateUtils duHbiDte = new DateUtils(hbiDte, DateUtils.PTN_DATE_6);

		//2006/12/19 �e�w�Ђ̏ꍇ
		if(daiKaiSkbCod.equals(CommonConst.KAICOD_FX)){
			duHbiDte.addToDate(Calendar.DATE, -42);//�������|�S�Q��
			//�������|�S�Q�����������O�̓��t��������
			if(duToday.getDate8Int() > duHbiDte.getDate8Int()){
				int hbiDte8 = DateUtils.convertYYYYMMDD(Integer.parseInt(hbiDte));
				if(duToday.getDate8Int() > hbiDte8){
					duToday.addToDate(Calendar.DATE, 7);//�V����
					fuknki = duToday.getDate6Int();//�V����
				}else{
					fuknki = duToday.getDate6Int();//�������t
				}	
			}else{
				fuknki = duHbiDte.getDate6Int();//�������|�S�Q��
			}
		//LAN�Ђ̏ꍇ	
		}else if(daiKaiSkbCod.equals(CommonConst.KAICOD_BAN)){
			duToday.addToDate(Calendar.DATE, 7);//�V����
			fuknki = duToday.getDate6Int();
		}else{
			duHbiDte.addToDate(Calendar.DATE, -21);//�������|�Q�P��
			//�������|�Q�P�����������O�̓��t��������
			if(duToday.getDate8Int() > duHbiDte.getDate8Int())
				fuknki = duToday.getDate6Int();//�������t
			else
				fuknki = duHbiDte.getDate6Int();//�������|�Q�P��
		}
		return fuknki;
	}

	
	/**
	 * �����N�`�F�b�N
	 * 
	 */
	public ManualOrderResult doRankCheck(ManualOrderVO fmVO)throws SQLException{
				 	
		ManualOrderDAO fmDao = null;
		ManualOrderResult result = new ManualOrderResult(fmVO,true,"");

		boolean error = false;
		
		ArrayList kig_arr = fmVO.getKigBng_arr();
		ArrayList errors_kig = new ArrayList();
		
		boolean endTransaction = false;
		try{
			fmDao = new ManualOrderDAO();
			fmDao.setAutoCommit(false); //DB�̃g�����U�N�V�����J�n

			if(kig_arr.size() > 0){
				for(int i = 0; i < kig_arr.size(); i++){
					int errtype_kig = fmDao.rankCheck(fmVO,kig_arr.get(i)+"");
					if(errtype_kig != ManualOrderDAO.EXIST){
						String[] err = {String.valueOf(i+1),errtype_kig+""};//[0]�F�G���[�̂���index,[1]�F�G���[�̎��
						errors_kig.add(err);
						error = true;
					}
				}
			}else{
				
			}
			//�G���[�������
			if(error){
				result = new ManualOrderResult(fmVO,false,"");
				if(errors_kig.size() > 0){
					result.setMap(ManualOrderResult.KEY_KIGBNG,errors_kig);
				}				
			}
			//�G���[���Ȃ����
			if(!error){
				//����ɐ���
				result = new ManualOrderResult(fmVO,true,"");
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
		return result;
	}
}

