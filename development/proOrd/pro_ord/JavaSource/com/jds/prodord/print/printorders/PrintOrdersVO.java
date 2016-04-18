/**
* IkkatsuReferVO  ����������o�����[�I�u�W�F�N�g�N���X
*
*	�쐬��    2003/04/18
*	�쐬��    �i�m�h�h�j���c �Ĕ�
* �����T�v    �������f�[�^(FTBHAC01)�A��Ѓ}�X�^�[(FTBKAI01)����擾�����f�[�^���i�[����N���X�B
*
*	[�ύX����]
*             ���t ���O
*  �E���e
* 		 2004/04/21 (NII)�X
* 			�E�ō��艿�\���ɔ����C��
*		2005/05/19�iNII�j�g�c
* 			�E��Ж������Q(KAINMKJ2)�A�������̒ǉ�
* 		2005/08/29�iNII�j�g�c
* 			�E�X�V������A���z�̒ǉ�
*		2007/12/25�i�m�h�h�j�c��
* 			�E�}�j���A�������w���ꊇ�\�t�@���l�Q�ǉ��Ή�
*		2008/03/10�i�m�h�h�j�c��
* 			�E�P���ǉ��Ή�
*		2014/02/19�i�m�h�h�j�X
* 			�E�Ŕ��P���Ή�
*/

package com.jds.prodord.print.printorders;

import java.util.ArrayList;

public class PrintOrdersVO {


	/**
     * ��\��Ў��ʃR�[�h
     */
    private String daiKaiSkbCod;
	
	/**
     * ��Ў��ʃR�[�h
     */
    private String kaiSkbCod;
    
    /**
     * ��ЗX�֔ԍ�
     */
    private String kaiYbnNo;
    

    /**
     * ��ЏZ���P
     */
    private String kaiAdr1Kj;
    

    /**
     * ��ЏZ���Q
     */
    private String kaiAdr2Kj;
    

    /**
     * ��Гd�b�ԍ�
     */
    private String kaiTelNo;
    

    /**
     * ���FAX�ԍ�
     */
    private String kaiFaxBng;

    
    /**
     * ��Ѓ��Ssrc
     */
    private String kaiLogoSrc;
    
    /**
     * ��Ж�
     */
    private String kaiNmKj;

	/**
	 * ��Ж��Q
	 */
	private String kaiNmKj2;    

    /**
     * ���������t
     */
    private String hacSyoDte;
    

    /**
     * �������ԍ�
     */
    private String hacSyoBng;
    

    /**
     * ������R�[�h
     */
    private String hacCod;
    

    /**
     * �����於��
     */
    private String sirHacNm;
    
    
    /**
     * ������
     */
    private String syrSuu;
    
	/**
	 * �ō��艿
	 */
	private String zikTik;
	
	/**
	 * �艿�i�Ŕ��j
	 */
	private String tka;
	
	/**
	 * �X�V�����
	 */
	private String rrkDte;

//-----------------------------------------------------------------------------------
 
	//�y�[�W���C�A�E�g    
    private ArrayList pageLayout = null;
    
//-----------------------------------------------------------------------------------

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
     * �\���i��
     */
    private String hjiHnb = "";


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
    private String ketCod = "";

	/**
     * �`�Ԗ���
     */
    private String ketNm = "";
    
    /**
     * �v���X�����ރR�[�h
     */
    private String prsFukSziCod = "";
    

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
     * ���ރR�[�h
     */
    private String bunCod;
    
    /**
     * �V�[�P���XNo
     */
    private String seqNo;
    
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

//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~    

    /**
     * 
     * ��\��Ў��ʃR�[�h
     * 
     */
    public String getDaiKaiSkbCod(){
    	return daiKaiSkbCod;
    }
    public void setDaiKaiSkbCod(String s){
    	daiKaiSkbCod = s;
    }
    
    /**
     * 
     * ��Ў��ʃR�[�h�̃Z�b�g
     * 
     * @param kaiSkbCod ��Ў��ʃR�[�h
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
    
    /**
     * ��ЗX�֔ԍ��̃Z�b�g
     * 
     * @param kaiYbnNo ��ЗX�֔ԍ�
     */
    public void setKaiYbnNo(String kaiYbnNo) {

        this.kaiYbnNo = kaiYbnNo;

    }


    /**
     * ��ЗX�֔ԍ��̎擾
     * 
     * @return ��ЗX�֔ԍ�
     */
    public String getKaiYbnNo() {

        return this.kaiYbnNo;

    }


    /**
     * ��ЏZ���P�̃Z�b�g
     * 
     * @param kaiAdr1Kj ��ЏZ���P
     */
    public void setKaiAdr1Kj(String kaiAdr1Kj) {

        this.kaiAdr1Kj = kaiAdr1Kj;

    }


    /**
     * ��ЏZ���P�̎擾
     * 
     * @return ��ЏZ���P
     */
    public String getKaiAdr1Kj() {

        return this.kaiAdr1Kj;

    }


    /**
     * ��ЏZ���Q�̃Z�b�g
     * 
     * @param kaiAdr2Kj ��ЏZ���Q
     */
    public void setKaiAdr2Kj(String kaiAdr2Kj) {

        this.kaiAdr2Kj = kaiAdr2Kj;

    }


    /**
     * ��ЏZ���Q�̎擾
     * 
     * @return ��ЏZ���Q
     */
    public String getKaiAdr2Kj() {

        return this.kaiAdr2Kj;

    }


    /**
     * ��Гd�b�ԍ��̃Z�b�g
     * 
     * @param kaiTelNo ��Гd�b�ԍ�
     */
    public void setKaiTelNo(String kaiTelNo) {

        this.kaiTelNo = kaiTelNo;

    }


    /**
     * ��Гd�b�ԍ��̎擾
     * 
     * @return ��Гd�b�ԍ�
     */
    public String getKaiTelNo() {

        return this.kaiTelNo;

    }


    /**
     * ���FAX�ԍ��̃Z�b�g
     * 
     * @param kaiFaxBng ���FAX�ԍ�
     */
    public void setKaiFaxBng(String kaiFaxBng) {

        this.kaiFaxBng = kaiFaxBng;

    }


    /**
     * ���FAX�ԍ��̎擾
     * 
     * @return ���FAX�ԍ�
     */
    public String getKaiFaxBng() {

        return this.kaiFaxBng;

    }


	/**
     * 
     * ��Ѓ��Ssrc�̃Z�b�g
     * 
     * @param kaiLogoSrc ��Ѓ��Ssrc
     */
    public void setKaiLogoSrc(String kaiLogoSrc) {
    	
        this.kaiLogoSrc = kaiLogoSrc;
    }


    /**
     * ��Ѓ��Ssrc�̎擾
     * 
     * @return ��Ѓ��Ssrc
     */
    public String getKaiLogoSrc() {

        return "/img/" + this.kaiLogoSrc;

    }

    /**
     * ��Ж��̃Z�b�g
     * 
     * @param kaiNmKj ��Ж�
     */
    public void setKaiNmKj(String kaiNmKj) {

        this.kaiNmKj = kaiNmKj;

    }


    /**
     * ��Ж��̎擾
     * 
     * @return ��Ж�
     */
    public String getKaiNmKj() {

        return this.kaiNmKj;

    }


    /**
     * ���������t�̃Z�b�g
     * 
     * @param hacSyoDte ���������t
     */
    public void setHacSyoDte(String hacSyoDte) {

        this.hacSyoDte = hacSyoDte;

    }


    /**
     * ���������t�̎擾
     * 
     * @return ���������t
     */
    public String getHacSyoDte() {

        return this.hacSyoDte;

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
     * �����於�̂̃Z�b�g
     * 
     * @param sirHacNm �����於��
     */
    public void setSirHacNm(String sirHacNm) {

        this.sirHacNm = sirHacNm;

    }


    /**
     * �����於�̂̎擾
     * 
     * @return �����於��
     */
    public String getSirHacNm() {

        return this.sirHacNm;

    }
    
    
    /**
	 * �����񐔂̎擾
	 * @return Returns a String
	 */
	public String getSyrSuu() {
		return syrSuu;
	}
	/**
	 * �����񐔂̃Z�b�g
	 * @param syrSuu The syrSuu to set
	 */
	public void setSyrSuu(String syrSuu) {
		this.syrSuu = syrSuu;
	}


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
     * �\���i�Ԃ̃Z�b�g
     * 
     * @param hjiHnb �\���i��
     */
    public void setHjiHnb(String s) {

        this.hjiHnb = s;

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
	 * @param zikTik�@�ō��艿
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
	 * @param zikTik�@�艿(�Ŕ�)
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
	 * �V�[�P���XNo�̎擾
	 * @return Returns a String
	 */
	public String getSeqNo() {
		return seqNo;
	}
	/**
	 * �V�[�P���XNo�̃Z�b�g
	 * @param seqNo The seqNo to set
	 */
	public void setSeqNo(String seqNo) {
		this.seqNo = seqNo;
	}

	/**
	 * ��Ж������Q�̎擾
	 * @return
	 */
	public String getKaiNmKj2() {
		return kaiNmKj2;
	}

	/**
	 * ��Ж������Q�̃Z�b�g
	 * @param string
	 */
	public void setKaiNmKj2(String string) {
		kaiNmKj2 = string;
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
	 * �����X�V���̎擾
	 * @return
	 */
	public String getRrkDte() {
		return rrkDte;
	}

	/**
	 * �����X�V���̐ݒ�
	 * @param string
	 */
	public void setRrkDte(String string) {
		rrkDte = string;
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
	 * @param i
	 */
	public void setKin(String string) {
		kin = string;
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
	 * @param i
	 */
	public void setTan2(String string) {
		tan2 = string;
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
	 * @param i
	 */
	public void setBiKou2(String string) {
		biKou2 = string;
	}

//-----------------------------------------------------------------------------------
 
	//�y�[�W���C�A�E�g    
    public void setPageLayout(ArrayList arr){
    	this.pageLayout = arr;
    }
    public ArrayList getPageLayout(){
    	return this.pageLayout;
    }
    
	/* (�� Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("zikTik : " + zikTik + ", ");
		sb.append("tka : " + tka + ", ");
		sb.append("titKj : " + titKj + ", ");
		sb.append("syrSuu : " + syrSuu + ", ");
		sb.append("sirHacNm : " + sirHacNm + ", ");
		sb.append("sinKyuKbn : " + sinKyuKbn + ", ");
		sb.append("setSuu : " + setSuu + ", ");
		sb.append("seqNo : " + seqNo + ", ");
		sb.append("rrkDte : " + rrkDte + ", ");
		sb.append("prsFukSziCod : " + prsFukSziCod + ", ");
		sb.append("nki : " + nki + ", ");
		sb.append("nhnMeiKj : " + nhnMeiKj + ", ");
		sb.append("kigBng : " + kigBng + ", ");
		sb.append("ketNm : " + ketNm + ", ");
		sb.append("ketCod : " + ketCod + ", ");
		sb.append("kaiYbnNo : " + kaiYbnNo + ", ");
		sb.append("kaiTelNo : " + kaiTelNo + ", ");
		sb.append("kaiSkbCod : " + kaiSkbCod + ", ");
		sb.append("kaiNmKj2     : " + kaiNmKj2     + ", ");
		sb.append("kaiNmKj : " + kaiNmKj + ", ");
		sb.append("kaiLogoSrc : " + kaiLogoSrc + ", ");
		sb.append("kaiFaxBng : " + kaiFaxBng + ", ");
		sb.append("kaiAdr2Kj : " + kaiAdr2Kj + ", ");
		sb.append("kaiAdr1Kj : " + kaiAdr1Kj + ", ");
		sb.append("hjiHnb : " + hjiHnb + ", ");
		sb.append("hbiDte : " + hbiDte + ", ");
		sb.append("hacSyoDte : " + hacSyoDte + ", ");
		sb.append("hacSyoBng : " + hacSyoBng + ", ");
		sb.append("hacSuu : " + hacSuu + ", ");
		sb.append("hacCod : " + hacCod + ", ");
		sb.append("gyoBng : " + gyoBng + ", ");
		sb.append("fukSziSuu : " + fukSziSuu + ", ");
		sb.append("fukMeiKj : " + fukMeiKj + ", ");
		sb.append("daiKaiSkbCod : " + daiKaiSkbCod + ", ");
		sb.append("comment : " + comment + ", ");
		sb.append("bunCod : " + bunCod + ", ");
		sb.append("artKj : " + artKj + ", ");
		sb.append("kin : " + kin);
		sb.append("biKou2 : " + biKou2);
		sb.append("tan2 : " + tan2);

		return sb.toString();
	}
	
}

