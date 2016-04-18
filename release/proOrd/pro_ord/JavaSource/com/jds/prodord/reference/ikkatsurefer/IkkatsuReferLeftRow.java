/**
* IkkatsuReferLeftRow  �ꊇ�Ɖ��ʃt�H�[���s�N���X
*
*	�쐬��    2003/03/31
*	�쐬��    �i�m�h�h�j���c �Ĕ�
* �����T�v    Http���N�G�X�g�ƃ��N�G�X�g�̌��ʂ̂����̌J��Ԃ����ځi���̃t���[���j���i�[����N���X�B
*
*	[�ύX����]
*        2003/05/15�i�m�h�h�j�g�c �h�q
*  			�E�����σt���O �ǉ��B
* 		 2004/04/21 (NII)�X
* 			�E�ō��艿�\���ɔ����C��
*/

package com.jds.prodord.reference.ikkatsurefer;



public class IkkatsuReferLeftRow implements java.io.Serializable{
	
    /**
     * ��\��Ў��ʃR�[�h
     */
    private String daiKaiSkbCod = "";
    
    /**
     * ��Ў��ʃR�[�h
     */
    private String kaiSkbCod = "";
    
    
//-------------------------------------------------------------    	
    /**
     * �`�ԃR�[�h
     */
    private String ketCod = "";
    
    /**
     * �`�Ԗ���
     */
    private String ketNm = "";
    
    /**
     * �L���ԍ�
     */
    private String kigBng = "";
    
	/**
     * �\���i��
     */
    private String hjiHnb = "";
    
    /**
     * �A�[�e�B�X�g(����)
     */
    private String artKj = "";
    

    /**
     * ������
     */
    private String hbiDte = "";
    

    /**
     * �^�C�g��(����)
     */
    private String titKj = "";
    

    /**
     * ���������N
     */
    private String tomRak = "";

	/**
	 * �ō��艿
	 */
	private String zikTik = "";
    

    /**
     * �����σT�C��
     */
    private String hacZmiSgn = "";

//------

    /**
     * �݌ɐ�
     */
    private String aziSuu = "";
    

    /**
     * �I��ϑ�
     */
    private String tnaSekZan = "";
    

    /**
     * ���c
     */
    private String jucZan = "";
    

    /**
     * ������
     */
    private String mhikSuu = "";
    

    /**
     * ���U�[�u
     */
    private String rsvSum = "";
    

    /**
     * �݌ɓ���
     */
    private String zaiNisuu = "";
    
//----------

    /**
     * ��Đ�
     */
    private String teianSuu = "";
    

    /**
     * �s���݌ɐ�
     */
    private String fskZaiSuu = "";
    

    /**
     * ���ϔ���
     */
    private String avgUri = "";

//--------------

    /**
     * �����ލ݌ɐ�
     */
    private String fukZaiSuu = "";


    /**
     * �����ޔ������P
     */
    private String fukHacSuu1 = "";


    /**
     * �����ޔ����݌v
     */
    private String fukHacRui = "";
    
    
    /**
     * �����ޓ��ɗ݌v
     */
    private String fukNyoKei = "";
    

	/**
     * �����σt���O
     */
    private boolean hacFlg = false;

//-----------------------------------------------------------------------------

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



    // ----------------------------------------------------------- Properties

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
     * �L���ԍ��̃Z�b�g
     * 
     * @param kigBng �L���ԍ�
     */
    public void setKigBng(String s) {

        this.kigBng = s;

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
     * �������̃Z�b�g
     * 
     * @param hbiDte ������
     */
    public void setHbiDte(String s) {

        this.hbiDte = s;

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
     * �^�C�g��(����)�̃Z�b�g
     * 
     * @param titKj �^�C�g��(����)
     */
    public void setTitKj(String s) {

        this.titKj = s;

    }


    /**
     * �^�C�g��(����)�̎擾
     * 
     * @return �^�C�g��(����)
     */
    public String getTitKj() {

        return this.titKj;

    }


    /**
     * ���������N�̃Z�b�g
     * 
     * @param tomRak ���������N
     */
    public void setTomRak(String s) {

        this.tomRak = s;

    }


    /**
     * ���������N�̎擾
     * 
     * @return ���������N
     */
    public String getTomRak() {

        return this.tomRak;

    }


    /**
     * �����σT�C���̃Z�b�g
     * 
     * @param hacZmiSgn �����σT�C��
     */
    public void setHacZmiSgn(String s) {

        this.hacZmiSgn = s;

    }


    /**
     * �����σT�C���̎擾
     * 
     * @return �����σT�C��
     */
    public String getHacZmiSgn() {

        return this.hacZmiSgn;

    }

	/**
	 * �ō��艿
	 * @return�@zikTik �ō��艿
	 */
	public String getZikTik() {
		return zikTik;
	}

	/**
	 * @param zikTik�@�ō��艿�@
	 */
	public void setZikTik(String zikTik) {
		this.zikTik = zikTik;
	}

//-----------

    /**
     * �݌ɐ��̃Z�b�g
     * 
     * @param aziSuu �݌ɐ�
     */
    public void setAziSuu(String aziSuu) {

        this.aziSuu = aziSuu;

    }


    /**
     * �݌ɐ��̎擾
     * 
     * @return �݌ɐ�
     */
    public String getAziSuu() {

        return this.aziSuu;

    }


    /**
     * �I��ϑ��̃Z�b�g
     * 
     * @param tnaSekZan �I��ϑ�
     */
    public void setTnaSekZan(String tnaSekZan) {

        this.tnaSekZan = tnaSekZan;

    }


    /**
     * �I��ϑ��̎擾
     * 
     * @return �I��ϑ�
     */
    public String getTnaSekZan() {

        return this.tnaSekZan;

    }


    /**
     * ���c�̃Z�b�g
     * 
     * @param jucZan ���c
     */
    public void setJucZan(String jucZan) {

        this.jucZan = jucZan;

    }


    /**
     * ���c�̎擾
     * 
     * @return ���c
     */
    public String getJucZan() {

        return this.jucZan;

    }


    /**
     * �������̃Z�b�g
     * 
     * @param mhikSuu ������
     */
    public void setMhikSuu(String mhikSuu) {

        this.mhikSuu = mhikSuu;

    }


    /**
     * �������̎擾
     * 
     * @return ������
     */
    public String getMhikSuu() {

        return this.mhikSuu;

    }


    /**
     * ���U�[�u�̃Z�b�g
     * 
     * @param rsvSum ���U�[�u
     */
    public void setRsvSum(String rsvSum) {

        this.rsvSum = rsvSum;

    }


    /**
     * ���U�[�u�̎擾
     * 
     * @return ���U�[�u
     */
    public String getRsvSum() {

        return this.rsvSum;

    }


    /**
     * �݌ɓ����̃Z�b�g
     * 
     * @param zaiNisuu �݌ɓ���
     */
    public void setZaiNisuu(String zaiNisuu) {

        this.zaiNisuu = zaiNisuu;

    }


    /**
     * �݌ɓ����̎擾
     * 
     * @return �݌ɓ���
     */
    public String getZaiNisuu() {

        return this.zaiNisuu;

    }

//---------------

    /**
     * ��Đ��̃Z�b�g
     * 
     * @param teianSuu ��Đ�
     */
    public void setTeianSuu(String teianSuu) {

        this.teianSuu = teianSuu;

    }


    /**
     * ��Đ��̎擾
     * 
     * @return ��Đ�
     */
    public String getTeianSuu() {

        return this.teianSuu;

    }


    /**
     * �s���݌ɐ��̃Z�b�g
     * 
     * @param fskZaiSuu �s���݌ɐ�
     */
    public void setFskZaiSuu(String fskZaiSuu) {

        this.fskZaiSuu = fskZaiSuu;

    }


    /**
     * �s���݌ɐ��̎擾
     * 
     * @return �s���݌ɐ�
     */
    public String getFskZaiSuu() {

        return this.fskZaiSuu;

    }


    /**
     * ���ϔ���̃Z�b�g
     * 
     * @param avgUri ���ϔ���
     */
    public void setAvgUri(String avgUri) {

        this.avgUri = avgUri;

    }


    /**
     * ���ϔ���̎擾
     * 
     * @return ���ϔ���
     */
    public String getAvgUri() {

        return this.avgUri;

    }
    
//---------------

    /**
     * �����ލ݌ɐ��̃Z�b�g
     * 
     * @param fukZaiSuu �����ލ݌ɐ�
     */
    public void setFukZaiSuu(String fukZaiSuu) {

        this.fukZaiSuu = fukZaiSuu;

    }


    /**
     * �����ލ݌ɐ��̎擾
     * 
     * @return �����ލ݌ɐ�
     */
    public String getFukZaiSuu() {

        return this.fukZaiSuu;

    }


    /**
     * �����ޔ������P�̃Z�b�g
     * 
     * @param fukHacSuu1 �����ޔ������P
     */
    public void setFukHacSuu1(String fukHacSuu1) {

        this.fukHacSuu1 = fukHacSuu1;

    }


    /**
     * �����ޔ������P�̎擾
     * 
     * @return �����ޔ������P
     */
    public String getFukHacSuu1() {

        return this.fukHacSuu1;

    }



    /**
     * �����ޔ����݌v�̃Z�b�g
     * 
     * @param fukHacRui �����ޔ����݌v
     */
    public void setFukHacRui(String fukHacRui) {

        this.fukHacRui = fukHacRui;

    }


    /**
     * �����ޔ����݌v�̎擾
     * 
     * @return �����ޔ����݌v
     */
    public String getFukHacRui() {

        return this.fukHacRui;

    }
    
    /**
	 * Gets the fukNyoKei
	 * @return Returns a String
	 */
	public String getFukNyoKei() {
		return fukNyoKei;
	}
	/**
	 * Sets the fukNyoKei
	 * @param fukNyoKei The fukNyoKei to set
	 */
	public void setFukNyoKei(String fukNyoKei) {
		this.fukNyoKei = fukNyoKei;
	}

	/**
     * �����σt���O�̃Z�b�g
     * 
     * @param hacFlg �����σt���O
     */
    public void setHacFlg(boolean hacFlg) {

        this.hacFlg = hacFlg;

    }


    /**
     * �����σt���O�̎擾
     * 
     * @return �����σt���O
     */
    public boolean getHacFlg() {

        return this.hacFlg;

    }

    // --------------------------------------------------------- Public Methods


	public void clear(){

        this.ketCod = "";
        this.kigBng = "";
        this.artKj = "";
        this.hbiDte = "";
        this.titKj = "";
        this.tomRak = "";
        this.hacZmiSgn = "";
        this.zikTik = "";
        
        this.aziSuu = "";
        this.tnaSekZan = "";
        this.jucZan = "";
        this.mhikSuu = "";
        this.rsvSum = "";
        this.zaiNisuu = "";
        this.teianSuu = "";
        this.fskZaiSuu = "";
        this.avgUri = "";
        this.fukZaiSuu = "";
        this.fukHacSuu1 = "";
        this.fukHacRui = "";
        this.fukNyoKei = "";
    
    }

		
}
