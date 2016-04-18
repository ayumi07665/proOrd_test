/**
* OrderHistoryCsvVO  ���������Ɖ���CSV�o�����[�I�u�W�F�N�g�N���X
*
*	�쐬��    2003/04/18
*	�쐬��    �i�m�h�h�j�g�c �h�q
* �����T�v    VO�Ɋi�[����Ă���f�[�^��CSV�p�Ɋi�[�������N���X�B
*
*	[�ύX����]
* 		2005/09/15�i�m�h�h�j�g�c
* 			�E���׍��ڂɋ��z�̒ǉ�
* 		2006/01/25�i�m�h�h�j�c�[
* 			�E�_�E�����[�h���ڂɒ��c���ǉ�
*       2007/03/06�i�m�h�h�j�c��
* 			�E�_�E�����[�h���ڂɕ����ޖ��̒ǉ�
*       2007/12/05�i�m�h�h�j�c��
* 			�E�_�E�����[�h���ڂɃ^�C�g�������ǉ�
*		2007/12/25�i�m�h�h�j�c��
* 			�E�}�j���A�������w���ꊇ�\�t�@���l�Q�ǉ��Ή�
*		2008/03/07�i�m�h�h�j�c��
* 			�E�P���̒ǉ�
*/

package com.jds.prodord.reference.orderhistory;

import com.jds.prodord.common.*;
public class OrderHistoryCsvVO implements CommonCsvVoInterface{
	
 	
	private final int[] typeBox = {0,0,0,0,0,1,0,0,1,0,0,1,1,1,1,1,1,1,0,0,1,0,0}; //CommonCsvVoInterface�̃^�C�v�ɂȂ炤
	
	public int getColSize(){
		return 23;
	} 	
	

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
     * �\���i��
     */
    private String hjiHnb = "";
    
	/**
	 * �^�C�g������
	 */
	private String titKj = "";
    
    /**
     * ������
     */
    private String hbiDte = "";
    
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
     * ���ރR�[�h
     */
    private String bunCod = "";
    
    /**
     * ���z  2008/02/07 add
     */
    private String tan2 = "";
    
	/**
	 * ���z  2005/09/15 add
	 */
	private String kin = "";
    
    /**
     * �v���X�����ގc
     */
    private String fukSziSuu = "";
    
    /**
     * ������
     */
    private String hacSuu = "";
    
    /**
     * ���ɐ�
     */
    private String nyoSuu = "";
    
    /**
     * �[��
     */
    private String nki = "";
    
    /**
     * �����ԍ�
     */
    private String gyoBng = "";
    
    /**
     * �[�i��
     */
    private String nhnMeiKj = "";
	
	/**
     * �R�����g
     */
    private String cmt = "";
    
    /**
     * ���ɓ�
     */
    private String nyodte = "";
    
    /**
     * ���c��
     */
    private String cyuzan = "";

	/**
	 * �v���X�����ރR�[�h
	 */
	private String prsFukSziCod = "";

	/**
	 * �����ޖ���
	 */
	private String fukSziNmkj = "";

	/**
	 * ���l�Q
	 */
	private String biKou2 = "";

//*************************************************************************************

	public int getColumnType(int i){
		return typeBox[i];
	}

	public String getColumn(int i){
		String returnStr = "";
		switch(i){
			case 0: 
				returnStr = this.getHacCod();break;
			case 1: 
				returnStr = this.getSinKyuKbn();break;
			case 2: 
				returnStr = this.getKigBng();break;
			case 3: 
				returnStr = this.getHjiHnb();break;
			case 4: 
				returnStr = this.getTitkj();break;
			case 5: 
				returnStr = this.getHbiDte();break;
			case 6: 
				returnStr = this.getHacSyoBng();break;
			case 7: 
				returnStr = this.getGyoBng();break;
			case 8: 
				returnStr = this.getHacSyoDte();break;
			case 9: 
				returnStr = this.getSyrZmiSgn();break;
			case 10: 
				returnStr = this.getBunCod();break;
			case 11: 
				returnStr = this.getTan2();break; //2008/02/07 add
			case 12: 
				returnStr = this.getKin();break; //2005/09/15 add
			case 13: 
				returnStr = this.getFukSziSuu();break;
			case 14: 
				returnStr = this.getHacSuu();break;
			case 15: 
				returnStr = this.getNyoSuu();break;
			case 16: 
				returnStr = this.getNyoDte();break;
			case 17: 
				returnStr = this.getNki();break;
			case 18: 
				returnStr = this.getNhnMeiKj();break;
			case 19: 
				returnStr = this.getCmt();break;
			case 20:
				returnStr = this.getCyuzan();break;
			case 21:
				returnStr = this.getFukSziNmkj();break;
			case 22:
				returnStr = this.getBiKou2();break;
		}
		return returnStr;
	}    

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
     * �\���i��
     * 
     * @param hjiHnb �\���i��
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
	 * �^�C�g������
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
	public String getTitkj() {
		return this.titKj;
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
     * �v���X�����ގc�̃Z�b�g
     * 
     * @param fukSziSuu �v���X�����ގc
     */
    public void setFukSziSuu(String fukSziSuu) {

        this.fukSziSuu = fukSziSuu;

    }


    /**
     * �v���X�����ގc�̎擾
     * 
     * @return �v���X�����ގc
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
	public String getNki() {
		return this.nki;

	}
	public void setNki(String nki) {
		this.nki = nki;
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
     * �R�����g�̃Z�b�g
     * 
     * @param cmt �R�����g
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
     * ���ɓ��̃Z�b�g
     * 
     * @param nyodte ���ɓ�
     */
    public void setNyoDte(String nyodte) {

        this.nyodte = nyodte;

    }


    /**
     * ���ɓ��̎擾
     * 
     * @return ���ɓ�
     */
    public String getNyoDte() {

        return this.nyodte;

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
	 * �v���X�����ރR�[�h�̃Z�b�g
	 * 
	 * @param fukSziSuu �v���X�����ރR�[�h
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
	 * �����ޖ��̂̃Z�b�g
	 * 
	 * @param fukSziNmkj �����ޖ���
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
	 * ���l�Q�̃Z�b�g
	 * 
	 * @param biKou2 ���l�Q
	 */
	public void setBiKou2(String biKou2) {

		this.biKou2 = biKou2;

	}


	/**
	 * ���l�Q�̎擾
	 * 
	 * @return ���l�Q
	 */
	public String getBiKou2() {

		return this.biKou2;

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

}



