/**
* OrderHistoryVO  ���������Ɖ��ʃo�����[�I�u�W�F�N�g�N���X
*
*	�쐬��    2003/04/18
*	�쐬��    �i�m�h�h�j�g�c �h�q
* �����T�v    �i�ԃ}�X�^�[(FTBHIN01)�A�݌Ƀe�[�u��(FTBZAI01)�A���������e�[�u��(FTBHAC02)�A�݌ɓ����}�X�^�[(FTBMST04)
* 			 �����ރ}�X�^�[(FTBFUK01)�A�`�ԃ����N�ʏ����}�X�^�[(FTBMST05)����擾�����f�[�^���i�[����N���X�B
*
*	[�ύX����]
*             ���t ���O
*  �E���e
* 		2004/01/22�@�i�m�h�h�j�X
* 			�E���ɐ�����\�ɏC��
* 		2004/03/29 (NII) �X
* 			�E�i�ԃ}�X�^�̃e�[�u�����ύX
* 		2004/04/02 (NII) �X
* 			�E�����ޔ����̏C��(���ރR�[�h�P�������݂ɑΉ�)
* 		2005/05/25�i�m�h�h�j�g�c
* 			�E�ꏊ�R�[�h�̒ǉ�
* 		2005/09/13�i�m�h�h�j�g�c
* 			�E�����X�V���E���z�E��������(�����ޯ��)�̒ǉ�
* 		2006/01/25�i�m�h�h�j�c�[
* 			�E�_�E�����[�h���ڂɒ��c���ǉ�
*       2007/03/01�i�m�h�h�j�c��
* 			�E�_�E�����[�h���ڂɕ����ރR�[�h�ǉ�
*       2007/12/05�i�m�h�h�j�c��
* 			�E�_�E�����[�h���ڂɃ^�C�g�������ǉ�
*		2007/12/25�i�m�h�h�j�c��
* 			�E�}�j���A�������w���ꊇ�\�t�@���l�Q�ǉ��Ή�
*		2008/03/07�i�m�h�h�j�c��
* 			�E�P���̒ǉ�
* 		2008/06/02�i�m�h�h�j�c��
* 			�E�u�`�o�Ё@�P���E���z�̏C��
*/

package com.jds.prodord.reference.orderhistory;

import java.util.*;
import com.jds.prodord.common.*;
public class OrderHistoryVO  {
	
    /**
     *	�R�}���h
     */
    private String command = "";    

	private String prcKbn = "";
     
    /**
     * ��\��Ў��ʃR�[�h
     */
    private String daiKaiSkbCod = "";
    
    /**
     * ��Ў��ʃR�[�h
     */
    private String kaiSkbCod = "";
    private ArrayList kaiSkbCodList = null;
    
    /**
     * �ꏊ�R�[�h
     */
	private String bshCod = "";
    
//--------------------------------------------------------

    /**
     * �������I��(�w�b�_)
     */
    private String syrZmiSgn_H = "";

    /**
     * ���ރR�[�h�I��(�w�b�_)
     */
    private String bunCod_H = "";
    
    /**
     * ���ɏ󋵑I��(�w�b�_)
     */
    private String nyuko_H = "";
    
    /**
     * ������R�[�h(�w�b�_)
     */
    private String hacCod_H = "";
    
    /**
     * �L���ԍ�(�w�b�_)
     */
    private String kigBng_H = "";
    
    /**
     * ������(�w�b�_)
     */
    private int hbiDte_H = 0;
    
    /**
     * �������ԍ�From&To(�w�b�_)
     */
    private String hacSyoBngFrm_H = "";
    private String hacSyoBngTo_H = "";

    /**
     * ������Frm&To(�w�b�_)
     */
    private int hacSyoDteFrm_H = 0;
    private int hacSyoDteTo_H = 0;

    /**
     * �[��Frm&To(�w�b�_)
     */
    private int nkiFrm_H = 0;
    private int nkiTo_H = 0;
   
	/**
	 * ���ɓ�Frm&To(�w�b�_)
	 */
	private int nyoDteFrm_H = 0;
	private int nyoDteTo_H = 0;

    /**
     * ������R�[�h
     */
    private String hacCod = "";
    
    /**
     * �敪
     */
    private String sinKyuKbn = "";
    
    /**
     * �L���ԍ�
     */
    private String kigBng = "";
    
    /**
     * ������
     */
    private String hbiDte = "";
    
    /**
     * check_Mid�`�F�b�N�{�b�N�X
     */
    private boolean check_Mid = false;    
    
    /**
     * �������ԍ�
     */
    private String hacSyoBng = "";

    /**
     * ������
     */
    private String hacSyoDte = "";
    
    /**
     * �o�͍�
     */
    private String syrZmiSgn = "";
    
    /**
     * �v���X�����ރR�[�h
     */
    private String prsFukSziCod = "";
    
    /**
     * �݌ɐ�
     */
    private String fukSziSuu = "";
    
    /**
     * ������
     */
    private String hacSuu = "";
    private String hacSuuOld = "";
    
    
    /**
     * ���ɐ�
     */
    private String nyoSuu = "";
    
    /**
     * �[��
     */
    private int nki = 0;
    
    /**
     * �����ԍ�
     */
    private String gyoBng = "";
    
    /**
     * �敪old
     */
    private String sinKyuKbnOld = "";
    
    /**
     * �[�i��
     */
    private String nhnMeiKj = "";

     /**
     * ���޺���
     */
    private String bunCod = "";
     
    /**
     * �\���i��
     */
    private String hjiHnb = "";
    
    /**
     * �R�����g
     */
    private String cmt = "";
    
    /**
     * ���ɓ�
     */
    private String nyoDte = "";

	/**
	 * ���c���@�@2006/1/25 add
	 */
	private String cyuzan = "";

	/**
	 * �����敪 
	 */
	private String cykKbn = ""; //2004.01.22 add
	 
	/** 
	 * �����ԍ�����t���O
	 */
	private boolean gyoBngflg = false;

	/** 
	 * ������
	 */
	private String syrSuu = "";
	
	/** 
	 * �r�d�p�m�n
	 */
	private String seqNo = "";
	
	/** 
	 * ���[�J�[����
	 */
	private String[] mkrBun = new String[0];
	
	/**
     * �敪
     */
	private String kbn_H = "";

	private String fukSziMkr = ""; 
	private String fukSziHacSaki = ""; 
	private String fukSziNm = ""; 
	private boolean exsitFuk01 = false; 

	private int updDte;
	private int updJkk;

	/** ���[�T�C��
	 */
	private String knuSgn = "";
	
	/**
	 * ���z
	 */
	private String kin = "";
	private String kinOld = "";
	/**
	 * �����X�V��
	 */
	private String rrkTbl = "";
	
	/**
	 * ���������i�����ޯ���j
	 */
	private boolean chkHacJun = false;
	
	/**
	 * �����ޖ���
	 */
	private String fukSziNmkj = "";
    
	/**
	 * �^�C�g������
	 */
	private String titKj = "";
    
	/**
	 * ���l�Q
	 */
	private String biKou2 = "";

	/**
	 * �P��
	 */
	private String tan2 = "";
	private String tan2Old = "";

//*************************************************************************************

    // ----------------------------------------------------------- Properties
    
    /**
     * �R�}���h�̃Z�b�g
     * 
     * @param command �R�}���h
     */
    public void setCommand(String command) {
        this.command = command;
    }

    /**
     * �R�}���h�̎擾
     * 
     * @return �R�}���h
     */
    public String getCommand() {
        return this.command;
    }

    /**
     * ��\��Ў��ʃR�[�h�̃Z�b�g
     * 
     * @param daiKaiSkbCod ��\��Ў��ʃR�[�h
     */
    public void setDaiKaiSkbCod(String daiKaiSkbCod) {
        this.daiKaiSkbCod = daiKaiSkbCod;
    }

    /**
     * ��\��Ў��ʃR�[�h�̎擾
     * 
     * @return ��\��Ў��ʃR�[�h
     */
    public String getDaiKaiSkbCod() {
        return this.daiKaiSkbCod;
    }
    
    /**
     * ��Ў��ʃR�[�h�̃Z�b�g
     * 
     * @param kaiSkbCod 
     */
    public void setKaiSkbCod(String kaiSkbCod) {
        this.kaiSkbCod = kaiSkbCod;
    }

    /**
     * ��Ў��ʃR�[�h�̎擾
     * 
     * @return ��Ў��ʃR�[�h
     */
    public String getKaiSkbCod() {
        return this.kaiSkbCod;
    }
    
    //��Ў��ʃR�[�h���X�g
    public ArrayList getKaiSkbCodList(){
    	return kaiSkbCodList;
    }
    public void setKaiSkbCodList(ArrayList arr){
    	kaiSkbCodList = arr;
    }            
//-----------------------------------------------------------------------------
//** �������� **//

    /**
     * �������I���̃Z�b�g
     * 
     * @param syrZmiSgn_H �������I��
     */
    public void setSyrZmiSgn_H(String syrZmiSgn_H) {
        this.syrZmiSgn_H = syrZmiSgn_H;
    }

    /**
     * �������I���̎擾
     * 
     * @return �������I��
     */
    public String getSyrZmiSgn_H() {
        return this.syrZmiSgn_H;
    }

    /**
     * ���ރR�[�h�̃Z�b�g
     * 
     * @param bunCod_H ���ރR�[�h
     */
    public void setBunCod_H(String bunCod_H) {
        this.bunCod_H = bunCod_H;
    }

    /**
     * ���ރR�[�h�̎擾
     * 
     * @return ���ރR�[�h
     */
    public String getBunCod_H() {
        return this.bunCod_H;
    }
    
    /**
     * ���ɏ󋵑I���̃Z�b�g
     * 
     * @param bunCod_H ���ɏ󋵑I��
     */
    public void setNyuko_H(String nyuko_H) {
        this.nyuko_H = nyuko_H;
    }

    /**
     * ���ɏ󋵑I���̎擾
     * 
     * @return ���ɏ󋵑I��
     */
    public String getNyuko_H() {
        return this.nyuko_H;
    }
    
    /**
     * ������R�[�h�̃Z�b�g
     * 
     * @param hacCod_H ������R�[�h
     */
    public void setHacCod_H(String hacCod_H) {
        this.hacCod_H = hacCod_H;
    }

    /**
     * ������R�[�h�̎擾
     * 
     * @return ������R�[�h
     */
    public String getHacCod_H() {
        return this.hacCod_H;
    }

    /**
     * �L���ԍ��̃Z�b�g
     * 
     * @param kigBng_H �L���ԍ�
     */
    public void setKigBng_H(String kigBng_H) {
        this.kigBng_H = kigBng_H;
    }

    /**
     * �L���ԍ��̎擾
     * 
     * @return �L���ԍ�
     */
    public String getKigBng_H() {
        return this.kigBng_H;
    }


    /**
     * ������
     */
	public int getHbiDte_H() {
		return hbiDte_H;
	}

	public void setHbiDte_H(int i) {
		hbiDte_H = i;
	}
	
	public void setHbiDte_H(String year,String month,String day){
		if(year.trim().equals("") || month.trim().equals("") || day.trim().equals(""))
			hbiDte_H = 0;
		else
			hbiDte_H = (Integer.parseInt(year,10)*10000) + (Integer.parseInt(month,10)*100) + Integer.parseInt(day,10);
	}



    /**
     * �������ԍ�From�̃Z�b�g
     * 
     * @param hacSyoBngFrm_H �������ԍ�From
     */
    public void setHacSyoBngFrm_H(String hacSyoBngFrm_H) {
        this.hacSyoBngFrm_H = hacSyoBngFrm_H;
    }

    /**
     * �������ԍ�From�̎擾
     * 
     * @return �������ԍ�From
     */
    public String getHacSyoBngFrm_H() {
        return this.hacSyoBngFrm_H;
    }

    /**
     * �������ԍ�To�̃Z�b�g
     * 
     * @param hacSyoBngTo_H �������ԍ�To
     */
    public void setHacSyoBngTo_H(String hacSyoBngTo_H) {
        this.hacSyoBngTo_H = hacSyoBngTo_H;
    }

    /**
     * �������ԍ�To�̎擾
     * 
     * @return �������ԍ�To
     */
    public String getHacSyoBngTo_H() {
        return this.hacSyoBngTo_H;
    }





    /**
     * ������From�̃Z�b�g
     * 
     * @param hacSyoDteFrm_H ������To
     */
    public void setHacSyoDteFrm_H(int hacSyoDteFrm_H) {
        this.hacSyoDteFrm_H = hacSyoDteFrm_H;
    }
    public void setHacSyoDteFrm_H(String year,String month,String day){
		if(year.trim().equals("") || month.trim().equals("") || day.trim().equals(""))
			hacSyoDteFrm_H = 0;
		else
			hacSyoDteFrm_H = (Integer.parseInt(year,10)*10000) + (Integer.parseInt(month,10)*100) + Integer.parseInt(day,10);
	}
    /**
     * ������Frm�̎擾
     * 
     * @return ������Frm
     */
    public String getHacSyoDteFrm_H() {
        return (this.hacSyoDteFrm_H == 0)? "" : this.hacSyoDteFrm_H+"";
    }

    /**
     * ������To�̃Z�b�g
     * 
     * @param hacSyoDteTo_H ������To
     */
    public void setHacSyoDteTo_H(int hacSyoDteTo_H) {
        this.hacSyoDteTo_H = hacSyoDteTo_H;
    }
	public void setHacSyoDteTo_H(String year,String month,String day){
		if(year.trim().equals("") || month.trim().equals("") || day.trim().equals(""))
			hacSyoDteTo_H = 0;
		else
			hacSyoDteTo_H = (Integer.parseInt(year,10)*10000) + (Integer.parseInt(month,10)*100) + Integer.parseInt(day,10);
	}
    /**
     * ������To�̎擾
     * 
     * @return ������To
     */
    public String getHacSyoDteTo_H() {
        return (this.hacSyoDteTo_H == 0)? "" : this.hacSyoDteTo_H+"";
    }






    /**
     * �[��Frm�̃Z�b�g
     * 
     * @param NkiFrm_H �[��Frm
     */
    public void setNkiFrm_H(int nkiFrm_H) {
        this.nkiFrm_H = nkiFrm_H;
    }
	public void setNkiFrm_H(String year,String month,String day){
		if(year.trim().equals("") || month.trim().equals("") || day.trim().equals(""))
			nkiFrm_H = 0;
		else
			nkiFrm_H = (Integer.parseInt(year,10)*10000) + (Integer.parseInt(month,10)*100) + Integer.parseInt(day,10);
	}
    /**
     * �[��Frm�̎擾
     * 
     * @return �[��Frm
     */
    public String getNkiFrm_H() {
        return (this.nkiFrm_H == 0)? "" : this.nkiFrm_H+"";
    }

    /**
     * �[��To�̃Z�b�g
     * 
     * @param NkiTo_H �[��To
     */
    public void setNkiTo_H(int nkiTo_H) {
        this.nkiTo_H = nkiTo_H;
    }
	public void setNkiTo_H(String year,String month,String day){
		if(year.trim().equals("") || month.trim().equals("") || day.trim().equals(""))
			nkiTo_H = 0;
		else
			nkiTo_H = (Integer.parseInt(year,10)*10000) + (Integer.parseInt(month,10)*100) + Integer.parseInt(day,10);
	}
    /**
     * �[��To�̎擾
     * 
     * @return �[��To
     */
    public String getNkiTo_H() {
        return (this.nkiTo_H == 0)? "" : this.nkiTo_H+"";
    }


	/**
	 * ���ɓ�From�̃Z�b�g
	 * 
	 * @param nyoDteFrm_H
	 */
	public void setNyoDteFrm_H(int nyoDteFrm_H) {
		this.nyoDteFrm_H = nyoDteFrm_H;
	}
	public void setNyoDteFrm_H(String year,String month,String day){
		if(year.trim().equals("") || month.trim().equals("") || day.trim().equals(""))
			nyoDteFrm_H = 0;
		else
			nyoDteFrm_H = (Integer.parseInt(year,10)*10000) + (Integer.parseInt(month,10)*100) + Integer.parseInt(day,10);
	}

	/**
	 * ���ɓ�from�̎擾
	 * 
	 * @return ���ɓ�from
	 */
	public String getNyoDteFrm_H() {
		return (this.nyoDteFrm_H == 0)? "" : this.nyoDteFrm_H+"";
	}

	/**
	 * ���ɓ�To�̃Z�b�g
	 * 
	 * @param i
	 */
	public void setNyoDteTo_H(int nyoDteTo_H) {
		this.nyoDteTo_H = nyoDteTo_H;
	}
	public void setNyoDteTo_H(String year,String month,String day){
		if(year.trim().equals("") || month.trim().equals("") || day.trim().equals(""))
			nyoDteTo_H = 0;
		else
			nyoDteTo_H = (Integer.parseInt(year,10)*10000) + (Integer.parseInt(month,10)*100) + Integer.parseInt(day,10);
	}

	/**
	 * @return
	 */
	public String getNyoDteTo_H() {
		return (this.nyoDteTo_H == 0)? "" : this.nyoDteTo_H+"";
	}


//	2004.01.22 add
	/**
	 * �����敪
	 * 
	 * @param nyoDte �����敪
	 */
	public void setCykKbn(String cykKbn) {
		this.cykKbn = cykKbn;
	}
	/**
	 * �����敪�̎擾
	 * 
	 * @return �����敪
	 */
	public String getCykKbn() {
		return this.cykKbn;
	}
    

//���ʉ��-------------------------------------------------------------------- 

    /**
     * ������R�[�h�̃Z�b�g
     * 
     * @param hacCod ������R�[�h
     */
    public void setHacCod(String hacCod) {

        this.hacCod = hacCod;

    }


    /**
     * ������R�[�h�̎擾
     * 
     * @return ������R�[�h
     */
    public String getHacCod() {

        return this.hacCod;

    }


    /**
     * �敪�̃Z�b�g
     * 
     * @param sinKyuKbn �敪
     */
    public void setSinKyuKbn(String sinKyuKbn) {

        this.sinKyuKbn = sinKyuKbn;

    }


    /**
     * �敪�̎擾
     * 
     * @return �敪
     */
    public String getSinKyuKbn() {

        return this.sinKyuKbn;

    }


    /**
     * �L���ԍ��̃Z�b�g
     * 
     * @param kigBng �L���ԍ�
     */
    public void setKigBng(String kigBng) {

        this.kigBng = kigBng;

    }


    /**
     * �L���ԍ��̎擾
     * 
     * @return �L���ԍ�
     */
    public String getKigBng() {

        return this.kigBng;

    }


    /**
     * �������̃Z�b�g
     * 
     * @param hbiDte ������
     */
    public void setHbiDte(String hbiDte) {

        this.hbiDte = hbiDte;

    }


    /**
     * �������̎擾
     * 
     * @return ������
     */
    public String getHbiDte() {

        return this.hbiDte;

    }

    /**
     * check_Mid�̃Z�b�g
     */
    public void setCheck_Mid(boolean check_Mid) {
        this.check_Mid = check_Mid;
    }

    /**
     * check_Mid�̎擾
     */
    public boolean getCheck_Mid() {
        return this.check_Mid;
    }


    /**
     * �������ԍ��̃Z�b�g
     * 
     * @param hacSyoBng �������ԍ�
     */
    public void setHacSyoBng(String hacSyoBng) {

        this.hacSyoBng = hacSyoBng;

    }


    /**
     * �������ԍ��̎擾
     * 
     * @return �������ԍ�
     */
    public String getHacSyoBng() {

        return this.hacSyoBng;

    }


    /**
     * �������̃Z�b�g
     * 
     * @param hacSyoDte ������
     */
    public void setHacSyoDte(String hacSyoDte) {

        this.hacSyoDte = hacSyoDte;

    }


    /**
     * �������̎擾
     * 
     * @return ������
     */
    public String getHacSyoDte() {

        return this.hacSyoDte;

    }


    /**
     * �o�͍ς̃Z�b�g
     * 
     * @param syrZmiSgn �o�͍�
     */
    public void setSyrZmiSgn(String syrZmiSgn) {

        this.syrZmiSgn = syrZmiSgn;

    }


    /**
     * �o�͍ς̎擾
     * 
     * @return �o�͍�
     */
    public String getSyrZmiSgn() {

        return this.syrZmiSgn;

    }


    /**
     * �v���X�����ރR�[�h�̃Z�b�g
     * 
     * @param prsFukSziCod �v���X�����ރR�[�h
     */
    public void setPrsFukSziCod(String prsFukSziCod) {

        this.prsFukSziCod = prsFukSziCod;

    }


    /**
     * �v���X�����ރR�[�h�̎擾
     * 
     * @return �v���X�����ރR�[�h
     */
    public String getPrsFukSziCod() {

        return this.prsFukSziCod;

    }


    /**
     * �݌ɐ��̃Z�b�g
     * 
     * @param fukSziSuu �݌ɐ�
     */
    public void setFukSziSuu(String fukSziSuu) {

        this.fukSziSuu = fukSziSuu;

    }


    /**
     * �݌ɐ��̎擾
     * 
     * @return �݌ɐ�
     */
    public String getFukSziSuu() {

        return this.fukSziSuu;

    }

    /**
     * �������̃Z�b�g
     * 
     * @param hacSuu ������
     */
    public void setHacSuu(String hacSuu) {

        this.hacSuu = hacSuu;

    }


    /**
     * �������̎擾
     * 
     * @return ������
     */
    public String getHacSuu() {

        return this.hacSuu;

    }


	/**
     * ������Old�̃Z�b�g
     * 
     * @param hacSuu ������Old
     */
    public void setHacSuuOld(String hacSuuOld) {

        this.hacSuuOld = hacSuuOld;

    }


    /**
     * ������Old�̎擾
     * 
     * @return ������Old
     */
    public String getHacSuuOld() {

        return this.hacSuuOld;

    }

    /**
     * ���ɐ��̃Z�b�g
     * 
     * @param nyoSuu ���ɐ�
     */
    public void setNyoSuu(String nyoSuu) {

        this.nyoSuu = nyoSuu;

    }


    /**
     * ���ɐ��̎擾
     * 
     * @return ���ɐ�
     */
    public String getNyoSuu() {

        return this.nyoSuu;

    }

   /**
    * �[��
    */
	public int getNki() {
		return nki;

	}
	public void setNki(int i) {
		nki = i;
	}
	
	public void setNki(String year,String month,String day){
		if(year.trim().equals("") || month.trim().equals("") || day.trim().equals(""))
			nki = 0;
		else
			nki = (Integer.parseInt(year,10)*10000) + (Integer.parseInt(month,10)*100) + Integer.parseInt(day,10);
	}
	public String getNkiYear(){
		if(nki <= 0)
			return "";
		String str = StringUtils.lpad(String.valueOf(nki),6,"0");
		return str.substring(0,2);
	}
	public String getNkiMonth(){
		if(nki <= 0)
			return "";
		String str = StringUtils.lpad(String.valueOf(nki),6,"0");
		return str.substring(2,4);
	}
	public String getNkiDay(){
		if(nki <= 0)
			return "";
		String str = StringUtils.lpad(String.valueOf(nki),6,"0");
		return str.substring(4,6);
	}

    /**
     * �����ԍ��̃Z�b�g
     * 
     * @param gyoBng �����ԍ�
     */
    public void setGyoBng(String gyoBng) {

        this.gyoBng = gyoBng;

    }


    /**
     * �����ԍ��̎擾
     *     * @return �����ԍ�
     */
    public String getGyoBng() {

        return this.gyoBng;

    }

    /**
     * �敪Old�̃Z�b�g
     * 
     * @param kbnOld �敪Old
     */
    public void setSinKyuKbnOld(String sinKyuKbnOld) {

        this.sinKyuKbnOld = sinKyuKbnOld;

    }


    /**
     * �敪Old�̎擾
     * 
     * @return �敪Old
     */
    public String getSinKyuKbnOld() {

        return this.sinKyuKbnOld;

    }
    /**
     * �[�i��̃Z�b�g
     * 
     * @param nhnMeiKj �[�i��
     */
    public void setNhnMeiKj(String nhnMeiKj) {

        this.nhnMeiKj = nhnMeiKj;

    }


    /**
     * �[�i��̎擾
     * 
     * @return �[�i��
     */
    public String getNhnMeiKj() {

        return this.nhnMeiKj;

    }
    

    /**
     * ���ރR�[�h�̃Z�b�g
     * 
     * @param bunCod ���ރR�[�h
     */
    public void setBunCod(String bunCod) {

        this.bunCod = bunCod;

    }


    /**
     * ���ރR�[�h�̎擾
     * 
     * @return ���ރR�[�h
     */
    public String getBunCod() {

        return this.bunCod;

    }
 
    /**
     * �\���i�Ԃ̃Z�b�g
     * 
     * @param hjihnb �\���i��
     */
    public void setHjiHnb(String hjiHnb) {

        this.hjiHnb = hjiHnb;

    }


    /**
     * �\���i�Ԃ̎擾
     * 
     * @return �\���i��
     */
    public String getHjiHnb() {

        return this.hjiHnb;

    }

    /**
     * �R�����g�̃Z�b�g
     * 
     * @param hjihnb �R�����g
     */
    public void setCmt(String cmt) {

        this.cmt = cmt;

    }


    /**
     * �R�����g�̎擾
     * 
     * @return �R�����g
     */
    public String getCmt() {

        return this.cmt;

    }
    
    /**
     * ���ɓ�
     * 
     * @param nyoDte ���ɓ�
     */
    public void setNyoDte(String nyoDte) {
        this.nyoDte = nyoDte;
    }
    /**
     * ���ɓ��̎擾
     * 
     * @return ���ɓ�
     */
    public String getNyoDte() {
        return this.nyoDte;
    }
    
	/**
	 * ���c���̎擾
	 * @return
	 */
	public String getCyuzan() {
		return cyuzan;
	}

	/**
	 * ���c���̐ݒ�
	 * @param string
	 */
	public void setCyuzan(String string) {
		cyuzan = string;
	}
  
	/**
	 * �����ޖ��̂̃Z�b�g
	 * 
	 * @param prsFukSziCod �����ޖ���
	 */
	public void setFukSziNmkj(String fukSziNmkj) {

		this.fukSziNmkj = fukSziNmkj;

	}

	/**
	 * �����ޖ��̂̎擾
	 * 
	 * @return �����ޖ���
	 */
	public String getFukSziNmkj() {

		return this.fukSziNmkj;

	}

	/**
	 * �^�C�g�������̃Z�b�g
	 * 
	 * @param titKj �^�C�g������
	 */
	public void setTitKj(String titKj) {

		this.titKj = titKj;

	}

	/**
	 * �^�C�g�������̎擾
	 * 
	 * @return �^�C�g������
	 */
	public String getTitKj() {

		return this.titKj;

	}

    // ----------------------------------------------------------- Properties Right
  
	/** �����ԍ�����t���O
	 */
	public boolean getGyoBngflg(){
		return gyoBngflg;
	}	
	public void setGyoBngflg(boolean s){
		gyoBngflg = s;
	}	

	
	/** ������
	 */
	public String getFukSziMkr(){
		return fukSziMkr;
	}	
	public void setFukSziMkr(String s){
		fukSziMkr = s;
	}	


	public String getFukSziHacSaki(){
		return fukSziHacSaki;
	}	
	public void setFukSziHacSaki(String s){
		fukSziHacSaki = s;
	}
	
	public String getFukSziNm(){
		return fukSziNm;
	}	
	public void setFukSziNm(String s){
		fukSziNm = s;
	}	
			

	public boolean getExsitFuk01(){
		return exsitFuk01;
	}	
	public void setExsitFuk01(boolean s){
		exsitFuk01 = s;
	}
	
	

	public int getUpdDte(){
		return updDte;
	}	
	public void setUpdDte(int s){
		updDte = s;
	}	
	public int getUpdJkk(){
		return updJkk;
	}	
	public void setUpdJkk(int s){
		updJkk = s;
	}	

	/**
     * ������
     * 
     * @param nyoDte ������
     */
    public void setSyrSuu(String syrSuu) {
        this.syrSuu = syrSuu;
    }
    /**
     * �����񐔂̎擾
     * 
     * @return ������
     */
    public String getSyrSuu() {
        return this.syrSuu;
    }
    
    /**
     * �r�d�p�m�n
     * 
     * @param nyoDte �r�d�p�m�n
     */
    public void setSeqNo(String seqNo) {
        this.seqNo = seqNo;
    }
    /**
     * �r�d�p�m�n�̎擾
     * 
     * @return �r�d�p�m�n
     */
    public String getSeqNo() {
        return this.seqNo;
    }
    /**
     * ���[�J�[���ނ̃Z�b�g
     * 
     * @param mkrBun ���[�J�[����
     */
    public void setMkrBun(String[] s) {

        this.mkrBun = s;

    }
    /**
     * ���[�J�[���ނ̎擾
     * 
     * @return ���[�J�[����
     */
    public String[] getMkrBun() {

        return this.mkrBun;

    }
    /**
     * �敪�̃Z�b�g
     * 
     * @param kbn �敪
     */
    public void setKbn_H(String s) {

        this.kbn_H = s;

    }
    /**
     * �敪�̎擾
     * 
     * @return �敪
     */
    public String getKbn_H() {

        return this.kbn_H;

    }
    
	/**
	 * ���[�T�C��
	 * @knuSgn
	 */
	public String getKnuSgn() {
		return knuSgn;
	}

	/**
	 * @param knuSgn
	 */
	public void setKnuSgn(String knuSgn) {
		this.knuSgn = knuSgn;
	}  
//--------------------------------------------------------------------------------------  
    
    /**
	 * Gets �T���v���敪
	 * @return Returns a String
	 */
	public String getSmpKbn(){
		String smpKbn = DataFormatUtils.getSmpKbn(sinKyuKbn, hbiDte, hacSyoDte);
		return smpKbn;
	}
	
	/**
	 * �V�����ǂ���(��������������)��Ԃ�
	 */
	public boolean isShinpu(){
		if(this.hbiDte.equals(""))
				return false;
			
		String _hbiDte = StringUtils.lpad(this.hbiDte,6,"0");
    	int  y   = Integer.parseInt(_hbiDte.substring(0,2));

    	if(y >= 80)
    		_hbiDte = "19" + _hbiDte;//1980�`1999
    	else
    		_hbiDte = "20" + _hbiDte;//2000�`

		int hbiDte = Integer.parseInt(_hbiDte);
		int hacSyoDte = Integer.parseInt("20" + StringUtils.lpad(this.hacSyoDte,6,"0"));
		
		if(hacSyoDte < hbiDte)
			return true;
			
		return false;
	}
	
	public static final int FROM_SMP_TO_OTHER = 1;
	public static final int FROM_OTHER_TO_SMP = 2;
	public static final int ELSE = 0;
	/**
	 * �敪���ǂ̂悤�ɕύX���ꂽ���A�^�C�v��Ԃ�
	 */
	public int getKbnHenkoType(){
		String sample = DataFormatUtils.getKbnCod(DataFormatUtils.SAMPLE);
		if(this.sinKyuKbnOld.equals(sample)){
			if(this.sinKyuKbn.equals(sample))
				return ELSE;
			else
				return FROM_SMP_TO_OTHER;
		}else{
			if(this.sinKyuKbn.equals(sample))
				return FROM_OTHER_TO_SMP;
		}
		return ELSE;		
	}
	
	
	/**
	 * @return
	 */
	public static int getELSE() {
		return ELSE;
	}

	/**
	 * @return
	 */
	public String getBshCod() {
		return bshCod;
	}

	/**
	 * @param string
	 */
	public void setBshCod(String string) {
		bshCod = string;
	}


	/**
	 * ���������i�����ޯ���j�̎擾
	 * @return
	 */
	public boolean isChkHacJun() {
		return chkHacJun;
	}

	/**
	 * ���������i�����ޯ���j�̐ݒ�
	 * @param b
	 */
	public void setChkHacJun(boolean b) {
		chkHacJun = b;
	}

	/**
	 * ���z�̎擾
	 * @return
	 */
	public String getKin() {
		return kin;
	}

	/**
	 * ���z�̐ݒ�
	 * @param string
	 */
	public void setKin(String string) {
		kin = string;
	}

	/**
	 * ���zOld�̎擾
	 * @return
	 */
	public String getKinOld() {
		return kinOld;
	}

	/**
	 * ���zOld�̐ݒ�
	 * @param string
	 */
	public void setKinOld(String string) {
		kinOld = string;
	}

	/**
	 * �P���̎擾
	 * @return
	 */
	public String getTan2() {
		return tan2;
	}

	/**
	 * �P���̐ݒ�
	 * @param string
	 */
	public void setTan2(String string) {
		tan2 = string;
	}


	/**
	 * �P��Old�̎擾
	 * @return
	 */
	public String getTan2Old() {

		return this.tan2Old;

	}

	/**
	 * �P��Old�̃Z�b�g
	 * @param string
	 */
	public void setTan2Old(String tan2Old) {

		this.tan2Old = tan2Old;

	}

	/**
	 * ���l�Q�̎擾
	 * @return
	 */
	public String getBiKou2() {
		return biKou2;
	}

	/**
	 * ���l�Q�̐ݒ�
	 * @param string
	 */
	public void setBiKou2(String string) {
		biKou2 = string;
	}

	/**
	 * �����X�V���̎擾
	 * @return
	 */
	public String getRrkTbl() {
		return rrkTbl;
	}

	/**
	 * �����X�V���̐ݒ�
	 * @param string
	 */
	public void setRrkTbl(String string) {
		rrkTbl = string;
	}

	/**
	 * �����X�V���L�[�̎擾
	 * @return
	 */
	public String getUpdateKey() {
		String updateKey = daiKaiSkbCod + "%" +
						   kaiSkbCod + "%" +
					       hacSyoDte + "%" +
					       hacSyoBng + "%" +
					       hacCod;
		return updateKey;
	}

	/**
	 * @return
	 */
	public String getPrcKbn() {
		return prcKbn;
	}

	/**
	 * @param string
	 */
	public void setPrcKbn(String string) {
		prcKbn = string;
	}


	/**
	 * �����L�[��SQL�̏�ԂŎ擾���܂��B
	 * @param map
	 * @return query
	 */
	public String getPartOfQuery(String key){

		String query = "";
		try {
			String[] keys = StringUtils.split(key, "%");
			
			query = " DAIKAISKBCOD = '" + keys[0] + "'"
				  + " AND KAISKBCOD = '" + keys[1] + "'"
				  + " AND HACSYODTE = " + keys[2]
				  + " AND HACSYOBNG = '" + DataFormatUtils.formatHacSyoBng(keys[3].trim()) + "'"
				  + " AND HACCOD = '" + keys[4] + "'";
			
		}catch (ArrayIndexOutOfBoundsException e) {
			SystemException se = new SystemException(e);
			se.printStackTrace();
			throw e;
		}

		return query;
	}


	/**
	 *  �f�o�b�O�p��System.out.println�ŃI�u�W�F�N�g�̓��e���o�͂��邽�߂̃��\�b�h
	 * @see java.lang.Object#toString()
	 */
	public String getVoData(){
		StringBuffer sb = new StringBuffer();
		String delim = ", ";

		sb.append(toString(daiKaiSkbCod, "daiKaiSkbCod") + delim);
		sb.append(toString(kaiSkbCod, "kaiSkbCod") + delim);
		sb.append(toString(hacSyoBng, "hacSyoBng") + delim);
		sb.append(toString(hacSyoDte, "hacSyoDte") + delim);
		sb.append(toString(syrSuu, "syrSuu") + delim);
		sb.append(toString(gyoBng, "gyoBng") + delim);
		sb.append(toString(seqNo, "seqNo") + delim);

		sb.append(toString(kigBng, "kigBng") + delim);
		sb.append(toString(bshCod, "bshCod") + delim);  
		sb.append(toString(hacCod, "hacCod") + delim);
		sb.append(toString(hacSuuOld, "hacSuuOld") + delim); 
		sb.append(toString(sinKyuKbn, "sinKyuKbn") + delim);
		sb.append(toString(sinKyuKbnOld, "sinKyuKbnOld") + delim);
		sb.append(toString(syrZmiSgn, "syrZmiSgn") + delim);
		sb.append(toString(prsFukSziCod, "prsFukSziCod") + delim);  
		sb.append(toString(fukSziSuu, "fukSziSuu") + delim);  
		sb.append(toString(hacSuu, "hacSuu") + delim);  
		sb.append(toString(bunCod, "bunCod") + delim);
		sb.append(toString(cykKbn, "cykKbn") + delim);
		sb.append(toString(String.valueOf(updDte), "updDte") + delim);
		sb.append(toString(String.valueOf(updJkk), "updJkk") + delim);
		sb.append(toString(rrkTbl, "rrkTbl") + delim);
		
		return sb.toString();
	}
	private String toString(Object o, String prmNm){
		return prmNm + "[" + o + "]";
	}
	
	/* (�� Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("command : " + command + ", ");
		sb.append("daiKaiSkbCod : " + daiKaiSkbCod + ", ");
		sb.append("kaiSkbCod : " + kaiSkbCod + ", ");
		sb.append("kaiSkbCodList : " + kaiSkbCodList + ", ");
		sb.append("bshCod : " + bshCod + ", ");
		sb.append("syrZmiSgn_H : " + syrZmiSgn_H + ", ");
		sb.append("bunCod_H : " + bunCod_H + ", ");
		sb.append("nyuko_H : " + nyuko_H + ", ");
		sb.append("hacCod_H : " + hacCod_H + ", ");
		sb.append("kigBng_H : " + kigBng_H + ", ");
		sb.append("hbiDte_H : " + hbiDte_H + ", ");
		sb.append("hacSyoBngFrm_H : " + hacSyoBngFrm_H + ", ");
		sb.append("hacSyoBngTo_H : " + hacSyoBngTo_H + ", ");
		sb.append("hacSyoDteFrm_H : " + hacSyoDteFrm_H + ", ");
		sb.append("hacSyoDteTo_H : " + hacSyoDteTo_H + ", ");
		sb.append("nkiFrm_H : " + nkiFrm_H + ", ");
		sb.append("nkiTo_H : " + nkiTo_H + ", ");
		sb.append("nyoDteFrm_H : " + nyoDteFrm_H + ", ");
		sb.append("nyoDteTo_H : " + nyoDteTo_H + ", ");
		sb.append("hacCod : " + hacCod + ", ");
		sb.append("sinKyuKbn : " + sinKyuKbn + ", ");
		sb.append("kigBng : " + kigBng + ", ");
		sb.append("hbiDte : " + hbiDte + ", ");
		sb.append("check_Mid : " + check_Mid + ", ");
		sb.append("hacSyoBng : " + hacSyoBng + ", ");
		sb.append("hacSyoDte : " + hacSyoDte + ", ");
		sb.append("syrZmiSgn : " + syrZmiSgn + ", ");
		sb.append("prsFukSziCod : " + prsFukSziCod + ", ");
		sb.append("fukSziSuu : " + fukSziSuu + ", ");
		sb.append("hacSuu : " + hacSuu + ", ");
		sb.append("hacSuuOld : " + hacSuuOld + ", ");
		sb.append("nyoSuu : " + nyoSuu + ", ");
		sb.append("nki : " + nki + ", ");
		sb.append("gyoBng : " + gyoBng + ", ");
		sb.append("sinKyuKbnOld : " + sinKyuKbnOld + ", ");
		sb.append("nhnMeiKj : " + nhnMeiKj + ", ");
		sb.append("bunCod : " + bunCod + ", ");
		sb.append("hjiHnb : " + hjiHnb + ", ");
		sb.append("cmt : " + cmt + ", ");
		sb.append("nyoDte : " + nyoDte + ", ");
		sb.append("cyuzan : " + cyuzan + ", ");
		sb.append("cykKbn : " + cykKbn + ", ");
		sb.append("gyoBngflg : " + gyoBngflg + ", ");
		sb.append("syrSuu : " + syrSuu + ", ");
		sb.append("seqNo : " + seqNo + ", ");
		sb.append("mkrBun : [");
		if(mkrBun != null){
			for (int i = 0; i < mkrBun.length; i++) {
				sb.append(mkrBun[i] + ", ");			
			}
		}
		sb.append("], ");
		sb.append("kbn_H : " + kbn_H + ", ");
		sb.append("fukSziMkr : " + fukSziMkr + ", ");
		sb.append("fukSziHacSaki : " + fukSziHacSaki + ", ");
		sb.append("fukSziNm : " + fukSziNm + ", ");
		sb.append("exsitFuk01 : " + exsitFuk01 + ", ");
		sb.append("updDte : " + updDte + ", ");
		sb.append("updJkk : " + updJkk + ", ");
		sb.append("knuSgn : " + knuSgn + ", ");
		sb.append("kin : " + kin + ", ");
		sb.append("rrkTbl : " + rrkTbl + ", ");
		sb.append("chkHacJun : " + chkHacJun + ", ");
		sb.append("bikou2 : " + biKou2 + ", ");
		sb.append("tan2 : " + tan2);
		sb.append("tan2Old : " + tan2Old);

		return sb.toString();
	}


}