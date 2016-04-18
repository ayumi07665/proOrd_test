/**
* PrintOrdersRow  ����������s�N���X
*
*	�쐬��    2003/04/18
*	�쐬��    �i�m�h�h�j���c �Ĕ�
* �����T�v    Http���N�G�X�g�ƃ��N�G�X�g�̌��ʂ̂����̌J��Ԃ����ڂ��i�[����N���X�B
*
*	[�ύX����]
*             ���t ���O
*  �E���e
* 		 2004/04/21 (NII)�X
* 			�E�ō��艿�\���ɔ����C��
* 		 2005/06/14 (NII)�g�c
* 			�E�������̒ǉ��i�j�БΉ��j
* 		 2005/09/13�iNII�j�g�c
* 			�E���z�̒ǉ��iVAP�БΉ��j
* 		 2007/12/25�i�m�h�h�j�c��
* 			�E�}�j���A�������w���ꊇ�\�t�@���l�Q�ǉ��Ή�
* 		 2008/03/10�i�m�h�h�j�c��
* 			�E�P���ǉ��Ή�
*/

package com.jds.prodord.print.printorders;

public class PrintOrdersRow {


    /**
     * �V�������敪
     */
    private String sinKyuKbn;
    

    /**
     * ���׍s�ԍ�
     */
    private String gyoBng;
    

    /**
     * �L���ԍ�
     */
    private String kigBng;
    

    /**
     * �^�C�g������
     */
    private String titKj;
    
    /**
     * �A�[�e�B�X�g(����)
     */
    private String artKj = "";
    
    /**
     * �`�ԃR�[�h
     */
    private String ketCod;
    
    /**
     * �`�Ԗ���
     */
    private String ketNm;

    /**
     * �v���X�����ރR�[�h
     */
    private String prsFukSziCod;
    

    /**
     * �����ޖ���
     */
    private String fukMeiKj;
    
    
	/**
     * �����ލ݌ɐ�
     */
	private String fukSziSuu;
	
	
    /**
     * �Z�b�g��
     */
    private String setSuu;
    

    /**
     * ��������
     */
    private String hacSuu;
    

    /**
     * �[��
     */
    private String nki;
    

    /**
     * �[�i�於��
     */
    private String nhnMeiKj;
    
    
    /**
     * �R�����g
     */
    private String comment;

	/**
	 * �ō��艿
	 */
	private String zikTik;
	/**
	 * �艿(�Ŕ�)
	 */
	private String tka;
	

	/**
	 * ������
	 */
	private String hbiDte;
	
	/**
	 * ���z
	 */
	private String kin;

	/**
	 * ���l�Q
	 */
	private String biKou2;

	/**
	 * �P��
	 */
	private String tan2;

//--------------------------------------------------------------------------


    /**
     * �V�������敪�̃Z�b�g
     * 
     * @param sinKyuKbn �V�������敪
     */
    public void setSinKyuKbn(String sinKyuKbn) {

        this.sinKyuKbn = sinKyuKbn;

    }


    /**
     * �V�������敪�̎擾
     * 
     * @return �V�������敪
     */
    public String getSinKyuKbn() {

        return this.sinKyuKbn;

    }


    /**
     * ���׍s�ԍ��̃Z�b�g
     * 
     * @param gyoBng ���׍s�ԍ�
     */
    public void setGyoBng(String gyoBng) {

        this.gyoBng = gyoBng;

    }


    /**
     * ���׍s�ԍ��̎擾
     * 
     * @return ���׍s�ԍ�
     */
    public String getGyoBng() {

        return this.gyoBng;

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
    
    
    /**
     * �A�[�e�B�X�g(����)�̃Z�b�g
     * 
     * @param artKj �A�[�e�B�X�g(����)
     */
    public void setArtKj(String s) {

        this.artKj = s;

    }


    /**
     * �A�[�e�B�X�g(����)�̎擾
     * 
     * @return �A�[�e�B�X�g(����)
     */
    public String getArtKj() {

        return this.artKj;

    }
    

	/**
     * �`�ԃR�[�h�̃Z�b�g
     * 
     * @param ketCod �`�ԃR�[�h
     */
    public void setKetCod(String s) {

        this.ketCod = s;

    }


    /**
     * �`�ԃR�[�h�̎擾
     * 
     * @return �`�ԃR�[�h
     */
    public String getKetCod() {

        return this.ketCod;

    }
    
    /**
     * �`�Ԗ��̂̃Z�b�g
     * 
     * @param ketNm �`�Ԗ���
     */
    public void setKetNm(String s) {

        this.ketNm = s;

    }


    /**
     * �`�Ԗ��̂̎擾
     * 
     * @return �`�Ԗ���
     */
    public String getKetNm() {

        return this.ketNm;

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
     * �����ޖ��̂̃Z�b�g
     * 
     * @param fukMeiKj �����ޖ���
     */
    public void setFukMeiKj(String fukMeiKj) {

        this.fukMeiKj = fukMeiKj;

    }


    /**
     * �����ޖ��̂̎擾
     * 
     * @return �����ޖ���
     */
    public String getFukMeiKj() {

        return this.fukMeiKj;

    }
    
    
    /**
     * �����ލ݌ɐ��̃Z�b�g
     * 
     * @param prsFukSziCod �����ލ݌ɐ�
     */
    public void setFukSziSuu(String fukSziSuu) {

        this.fukSziSuu = fukSziSuu;

    }


    /**
     * �����ލ݌ɐ��̎擾
     * 
     * @return �����ލ݌ɐ�
     */
    public String getFukSziSuu() {

        return this.fukSziSuu;

    }


    /**
     * �Z�b�g���̃Z�b�g
     * 
     * @param setSuu �Z�b�g��
     */
    public void setSetSuu(String setSuu) {

        this.setSuu = setSuu;

    }


    /**
     * �Z�b�g���̎擾
     * 
     * @return �Z�b�g��
     */
    public String getSetSuu() {

        return this.setSuu;

    }


    /**
     * �������ʂ̃Z�b�g
     * 
     * @param hacSuu ��������
     */
    public void setHacSuu(String hacSuu) {

        this.hacSuu = hacSuu;

    }


    /**
     * �������ʂ̎擾
     * 
     * @return ��������
     */
    public String getHacSuu() {

        return this.hacSuu;

    }


    /**
     * �[���̃Z�b�g
     * 
     * @param nki �[��
     */
    public void setNki(String nki) {

        this.nki = nki;

    }


    /**
     * �[���̎擾
     * 
     * @return �[��
     */
    public String getNki() {

        return this.nki;

    }


    /**
     * �[�i�於�̂̃Z�b�g
     * 
     * @param nhnMeiKj �[�i�於��
     */
    public void setNhnMeiKj(String nhnMeiKj) {

        this.nhnMeiKj = nhnMeiKj;

    }


    /**
     * �[�i�於�̂̎擾
     * 
     * @return �[�i�於��
     */
    public String getNhnMeiKj() {

        return this.nhnMeiKj;

    }

	/**
	 * �ō��艿
	 * @return�@�ō��艿
	 */
	public String getZikTik() {
		return zikTik;
	}

	/**
	 * �ō��艿
	 * @param zikTik
	 */
	public void setZikTik(String zikTik) {
		this.zikTik = zikTik;
	}

	/**
	 * �艿(�Ŕ�)
	 * @return�@�艿(�Ŕ�)
	 */
	public String getTka() {
		return tka;
	}

	/**
	 * �艿(�Ŕ�)
	 * @param tka
	 */
	public void setTka(String tka) {
		this.tka = tka;
	}
	
	/**
     * �R�����g�̃Z�b�g
     * 
     * @param comment �R�����g
     */
    public void setComment(String comment) {

        this.comment = comment;

    }


    /**
     * �R�����g�̎擾
     * 
     * @return �R�����g
     */
    public String getComment() {

        return this.comment;

    }

	/**
	 * �������̎擾
	 * @return
	 */
	public String getHbiDte() {
		return hbiDte;
	}

	/**
	 * �������̃Z�b�g
	 * @param string
	 */
	public void setHbiDte(String string) {
		hbiDte = string;
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

//---------------------------------------------------------------------------------------


	public void clear(){
		
        this.sinKyuKbn = "";
        this.gyoBng = "";
        this.kigBng = "";
        this.titKj = "";
        this.prsFukSziCod = "";
        this.fukMeiKj = "";
        this.setSuu = "";
        this.hacSuu = "";
        this.nki = "";
        this.nhnMeiKj = "";	
		this.comment = "";	
		this.zikTik = ""; //2004.04.21 add	
		this.tka = ""; //2014.02.19 add	
		this.hbiDte = ""; //2005.06.14 add
		this.kin = "";    //2005.09.13 add
		this.biKou2 = "";
		this.tan2 = "";
	}


}

