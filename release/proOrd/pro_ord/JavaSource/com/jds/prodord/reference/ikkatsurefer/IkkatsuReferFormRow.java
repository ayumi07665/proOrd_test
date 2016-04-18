/**
* IkkatsuReferFormRow  �ꊇ�Ɖ��ʃt�H�[���s�N���X
*
*	�쐬��    2003/03/31
*	�쐬��    �i�m�h�h�j���c �Ĕ�
* �����T�v    Http���N�G�X�g�ƃ��N�G�X�g�̌��ʂ̂����̌J��Ԃ����ځi�E�̃t���[���j���i�[����N���X�B
*
*	[�ύX����]
*        2003/05/12�i�m�h�h�j�g�c �h�q
* 			�Eindex,�o�͍σT�C�� �ǉ��B
*/

package com.jds.prodord.reference.ikkatsurefer;



public class IkkatsuReferFormRow implements java.io.Serializable{
	
	
    /**
     * ��\��Ў��ʃR�[�h
     */
    private String daiKaiSkbCod = "";
    
    /**
     * ��Ў��ʃR�[�h
     */
    private String kaiSkbCod = "";
    	
    /**
     * �L���ԍ�
     */
    private String kigBng = "";
    
   	
    
    // --------------------------------------------------- Instance Variables    
    /**
     * ��������
     */
    private String todUriSuu = "";
    

    /**
     * �O������
     */
    private String b1dUri = "";
    

    /**
     * �Q���O����
     */
    private String b2dUri = "";
    

    /**
     * �R���O����
     */
    private String b3dUri = "";
    

    /**
     * �S���O����
     */
    private String b4dUri = "";
    

    /**
     * �T���O����
     */
    private String b5dUri = "";
    

    /**
     * ���T����
     */
    private String towUri = "";
    

    /**
     * �O�T����
     */
    private String b1wUri = "";
    

    /**
     * �O�X�T����
     */
    private String b2wUri = "";


    /**
     * ��������
     */
    private String tomUri = "";
    

    /**
     * �O������
     */
    private String b1mUri = "";
    

    /**
     * �O�X������
     */
    private String b2mUri = "";
    

    /**
     * �����ԕi
     */
    private String tomHpn = "";
    

    /**
     * �O���ԕi
     */
    private String b1mHpn = "";
    

    /**
     * �O�X���ԕi
     */
    private String b2mHpn = "";
    

    /**
     * �����I��
     */
    private String tomTna = "";
    

    /**
     * �O���I��
     */
    private String b1mTna = "";
    

    /**
     * �O�X���I��
     */
    private String b2mTna = "";
    

    /**
     * �v���X������
     */
    private String prsHacSuu = "";
    

    /**
     * �v���X�[��(�N)
     */
    private String prsNkiYear = "";
    

    /**
     * �v���X�[��(��)
     */
    private String prsNkiMonth = "";
    

    /**
     * �v���X�[��(��)
     */
    private String prsNkiDay = "";
    

    /**
     * �v���X������
     */
    private String prsMkrCod = "";


    /**
     * �����敪
     */
    private String choksoKbn = "";    


    /**
     * �v���X�����Ɍv
     */
    private String prsMnyKei = "";
    

    /**
     * �v���X�������P
     */
    private String prsHacSuu1 = "";
    

    /**
     * �v���X���ɗ��P
     */
    private String prsHacNyo1 = "";
    

    /**
     * �v���X�[���P
     */
    private String prsHacNki1 = "";
    

    /**
     * �v���X�����݌v
     */
    private String prsHacRui = "";
    

    /**
     * �v���X�������Q
     */
    private String prsHacSuu2 = "";
    

    /**
     * �v���X���ɗ��Q
     */
    private String prsHacNyo2 = "";
    

    /**
     * �v���X�[���Q
     */
    private String prsHacNki2 = "";
    

    /**
     * �v���X�`�F�b�N�{�b�N�X1
     */
    private boolean check_prs1 = false;
    

    /**
     * �v���X�`�F�b�N�{�b�N�X2
     */
    private boolean check_prs2 = false;

    /**
     * �����ޔ�����
     */
    private String fukHacSuu = "";
    

    /**
     * �����ޔ[��(�N)
     */
    private String fukNkiYear = "";
    

    /**
     * �����ޔ[��(��)
     */
    private String fukNkiMonth = "";
    

    /**
     * �����ޔ[��(��)
     */
    private String fukNkiDay = "";
    

    /**
     * �����ޔ�����
     */
    private String fukMkrCod = "";
    
    
    /**
     * �����ލ݌ɐ�
     */
    private String fukZaiSuu = "";


    /**
     * �����ޖ����Ɍv
     */
    private String fukMnyKei = "";
    

    /**
     * �����ޔ������P
     */
    private String fukHacSuu1 = "";
    

    /**
     * �����ޓ��ɗ��P
     */
    private String fukHacNyo1 = "";
    

    /**
     * �����ޔ[���P
     */
    private String fukHacNki1 = "";
    

    /**
     * �����ޔ����݌v
     */
    private String fukHacRui = "";
    

    /**
     * �����ޔ������Q
     */
    private String fukHacSuu2 = "";
    

    /**
     * �����ޓ��ɗ��Q
     */
    private String fukHacNyo2 = "";
    

    /**
     * �����ޔ[���Q
     */
    private String fukHacNki2 = "";
    

    /**
     * �����ރ`�F�b�N�{�b�N�X1
     */
    private boolean check_fuk1 = false;
    
    
    /**
     * �����ރ`�F�b�N�t���O
     */
    private String check_flg = "";
    
    
    /**
     * �����ރ`�F�b�N�T�C��
     */
    private String check_sgn = "";
    
    
    /**
     * �v���X���������p�`�F�b�N�{�b�N�X
     */
    private boolean check_prsHacSuu = false;
    
    /**
     * �v���X�敪�P
     */
    private String prsKbn1 = "";
    /**
     * �v���X�敪�Q
     */
    private String prsKbn2 = "";
    /**
     * �����ދ敪�P
     */
    private String fukKbn1 = "";
    /**
     * �����ދ敪�Q
     */
    private String fukKbn2 = "";
    /**
     * �v���X���ɗ݌v
     */
    private String prsNyoKei = "";
    /**
     * �����ޓ��ɗ݌v
     */
    private String fukNyoKei = "";
   
    
	//2003/05/15
    /**
     * index
     */
    private int index = 0;
        
    /**
     * �o�͍σT�C��
     */
    private String syrZmiSgn = "";
    
    /**
     * �������G���[�T�C��
     */
    private boolean hacSuu_errSgn = false;

	//---------------------------------------------------------------------------

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
    
    

    // ----------------------------------------------------------- Properties

    /**
     * ��������̃Z�b�g
     * 
     * @param todUriSuu ��������
     */
    public void setTodUriSuu(String todUriSuu) {

        this.todUriSuu = todUriSuu;

    }


    /**
     * ��������̎擾
     * 
     * @return ��������
     */
    public String getTodUriSuu() {

        return this.todUriSuu;

    }


    /**
     * �O������̃Z�b�g
     * 
     * @param b1dUri �O������
     */
    public void setB1dUri(String b1dUri) {

        this.b1dUri = b1dUri;

    }


    /**
     * �O������̎擾
     * 
     * @return �O������
     */
    public String getB1dUri() {

        return this.b1dUri;

    }


    /**
     * �Q���O����̃Z�b�g
     * 
     * @param b2dUri �Q���O����
     */
    public void setB2dUri(String b2dUri) {

        this.b2dUri = b2dUri;

    }


    /**
     * �Q���O����̎擾
     * 
     * @return �Q���O����
     */
    public String getB2dUri() {

        return this.b2dUri;

    }


    /**
     * �R���O����̃Z�b�g
     * 
     * @param b3dUri �R���O����
     */
    public void setB3dUri(String b3dUri) {

        this.b3dUri = b3dUri;

    }


    /**
     * �R���O����̎擾
     * 
     * @return �R���O����
     */
    public String getB3dUri() {

        return this.b3dUri;

    }


    /**
     * �S���O����̃Z�b�g
     * 
     * @param b4dUri �S���O����
     */
    public void setB4dUri(String b4dUri) {

        this.b4dUri = b4dUri;

    }


    /**
     * �S���O����̎擾
     * 
     * @return �S���O����
     */
    public String getB4dUri() {

        return this.b4dUri;

    }


    /**
     * �T���O����̃Z�b�g
     * 
     * @param b5dUri �T���O����
     */
    public void setB5dUri(String b5dUri) {

        this.b5dUri = b5dUri;

    }


    /**
     * �T���O����̎擾
     * 
     * @return �T���O����
     */
    public String getB5dUri() {

        return this.b5dUri;

    }


    /**
     * ���T����̃Z�b�g
     * 
     * @param towUri ���T����
     */
    public void setTowUri(String towUri) {

        this.towUri = towUri;

    }


    /**
     * ���T����̎擾
     * 
     * @return ���T����
     */
    public String getTowUri() {

        return this.towUri;

    }


    /**
     * �O�T����̃Z�b�g
     * 
     * @param b1wUri �O�T����
     */
    public void setB1wUri(String b1wUri) {

        this.b1wUri = b1wUri;

    }


    /**
     * �O�T����̎擾
     * 
     * @return �O�T����
     */
    public String getB1wUri() {

        return this.b1wUri;

    }


    /**
     * �O�X�T����̃Z�b�g
     * 
     * @param b2wUri �O�X�T����
     */
    public void setB2wUri(String b2wUri) {

        this.b2wUri = b2wUri;

    }


    /**
     * �O�X�T����̎擾
     * 
     * @return �O�X�T����
     */
    public String getB2wUri() {

        return this.b2wUri;

    }

    /**
     * ��������̃Z�b�g
     * 
     * @param tomUri ��������
     */
    public void setTomUri(String tomUri) {

        this.tomUri = tomUri;

    }


    /**
     * ��������̎擾
     * 
     * @return ��������
     */
    public String getTomUri() {

        return this.tomUri;

    }


    /**
     * �O������̃Z�b�g
     * 
     * @param b1mUri �O������
     */
    public void setB1mUri(String b1mUri) {

        this.b1mUri = b1mUri;

    }


    /**
     * �O������̎擾
     * 
     * @return �O������
     */
    public String getB1mUri() {

        return this.b1mUri;

    }


    /**
     * �O�X������̃Z�b�g
     * 
     * @param b2mUri �O�X������
     */
    public void setB2mUri(String b2mUri) {

        this.b2mUri = b2mUri;

    }


    /**
     * �O�X������̎擾
     * 
     * @return �O�X������
     */
    public String getB2mUri() {

        return this.b2mUri;

    }


    /**
     * �����ԕi�̃Z�b�g
     * 
     * @param tomHpn �����ԕi
     */
    public void setTomHpn(String tomHpn) {

        this.tomHpn = tomHpn;

    }


    /**
     * �����ԕi�̎擾
     * 
     * @return �����ԕi
     */
    public String getTomHpn() {

        return this.tomHpn;

    }


    /**
     * �O���ԕi�̃Z�b�g
     * 
     * @param b1mHpn �O���ԕi
     */
    public void setB1mHpn(String b1mHpn) {

        this.b1mHpn = b1mHpn;

    }


    /**
     * �O���ԕi�̎擾
     * 
     * @return �O���ԕi
     */
    public String getB1mHpn() {

        return this.b1mHpn;

    }


    /**
     * �O�X���ԕi�̃Z�b�g
     * 
     * @param b2mHpn �O�X���ԕi
     */
    public void setB2mHpn(String b2mHpn) {

        this.b2mHpn = b2mHpn;

    }


    /**
     * �O�X���ԕi�̎擾
     * 
     * @return �O�X���ԕi
     */
    public String getB2mHpn() {

        return this.b2mHpn;

    }


    /**
     * �����I��̃Z�b�g
     * 
     * @param tomTna �����I��
     */
    public void setTomTna(String tomTna) {

        this.tomTna = tomTna;

    }


    /**
     * �����I��̎擾
     * 
     * @return �����I��
     */
    public String getTomTna() {

        return this.tomTna;

    }


    /**
     * �O���I��̃Z�b�g
     * 
     * @param b1mTna �O���I��
     */
    public void setB1mTna(String b1mTna) {

        this.b1mTna = b1mTna;

    }


    /**
     * �O���I��̎擾
     * 
     * @return �O���I��
     */
    public String getB1mTna() {

        return this.b1mTna;

    }


    /**
     * �O�X���I��̃Z�b�g
     * 
     * @param b2mTna �O�X���I��
     */
    public void setB2mTna(String b2mTna) {

        this.b2mTna = b2mTna;

    }


    /**
     * �O�X���I��̎擾
     * 
     * @return �O�X���I��
     */
    public String getB2mTna() {

        return this.b2mTna;

    }


    /**
     * �v���X�������̃Z�b�g
     * 
     * @param prsHacSuu �v���X������
     */
    public void setPrsHacSuu(String prsHacSuu) {

        this.prsHacSuu = prsHacSuu;

    }


    /**
     * �v���X�������̎擾
     * 
     * @return �v���X������
     */
    public String getPrsHacSuu() {

        return this.prsHacSuu;

    }


    /**
     * �v���X�[��(�N)�̃Z�b�g
     * 
     * @param prsNkiYear �v���X�[��(�N)
     */
    public void setPrsNkiYear(String prsNkiYear) {

        this.prsNkiYear = prsNkiYear;

    }


    /**
     * �v���X�[��(�N)�̎擾
     * 
     * @return �v���X�[��(�N)
     */
    public String getPrsNkiYear() {

        return this.prsNkiYear;

    }


    /**
     * �v���X�[��(��)�̃Z�b�g
     * 
     * @param prsNkiMonth �v���X�[��(��)
     */
    public void setPrsNkiMonth(String prsNkiMonth) {

        this.prsNkiMonth = prsNkiMonth;

    }


    /**
     * �v���X�[��(��)�̎擾
     * 
     * @return �v���X�[��(��)
     */
    public String getPrsNkiMonth() {

        return this.prsNkiMonth;

    }


    /**
     * �v���X�[��(��)�̃Z�b�g
     * 
     * @param prsNkiDay �v���X�[��(��)
     */
    public void setPrsNkiDay(String prsNkiDay) {

        this.prsNkiDay = prsNkiDay;

    }


    /**
     * �v���X�[��(��)�̎擾
     * 
     * @return �v���X�[��(��)
     */
    public String getPrsNkiDay() {

        return this.prsNkiDay;

    }


    /**
     * �v���X������̃Z�b�g
     * 
     * @param prsMkrCod �v���X������
     */
    public void setPrsMkrCod(String prsMkrCod) {

        this.prsMkrCod = prsMkrCod;

    }


    /**
     * �v���X������̎擾
     * 
     * @return �v���X������
     */
    public String getPrsMkrCod() {

        return this.prsMkrCod;

    }

  
    /**
     * �����敪�̃Z�b�g
     * 
     * @param choksoKbn �����敪
     */
    public void setChoksoKbn(String choksoKbn) {

        this.choksoKbn = choksoKbn;

    }


    /**
     * �����敪�̎擾
     * 
     * @return �����敪
     */
    public String getChoksoKbn() {

        return this.choksoKbn;

    }

    /**
     * �v���X�����Ɍv�̃Z�b�g
     * 
     * @param prsMnyKei �v���X�����Ɍv
     */
    public void setPrsMnyKei(String prsMnyKei) {

        this.prsMnyKei = prsMnyKei;

    }


    /**
     * �v���X�����Ɍv�̎擾
     * 
     * @return �v���X�����Ɍv
     */
    public String getPrsMnyKei() {

        return this.prsMnyKei;

    }


    /**
     * �v���X�������P�̃Z�b�g
     * 
     * @param prsHacSuu1 �v���X�������P
     */
    public void setPrsHacSuu1(String prsHacSuu1) {

        this.prsHacSuu1 = prsHacSuu1;

    }


    /**
     * �v���X�������P�̎擾
     * 
     * @return �v���X�������P
     */
    public String getPrsHacSuu1() {

        return this.prsHacSuu1;

    }


    /**
     * �v���X���ɗ��P�̃Z�b�g
     * 
     * @param prsHacNyo1 �v���X���ɗ��P
     */
    public void setPrsHacNyo1(String prsHacNyo1) {

        this.prsHacNyo1 = prsHacNyo1;

    }


    /**
     * �v���X���ɗ��P�̎擾
     * 
     * @return �v���X���ɗ��P
     */
    public String getPrsHacNyo1() {

        return this.prsHacNyo1;

    }


    /**
     * �v���X�[���P�̃Z�b�g
     * 
     * @param prsHacNki1 �v���X�[���P
     */
    public void setPrsHacNki1(String prsHacNki1) {

        this.prsHacNki1 = prsHacNki1;

    }


    /**
     * �v���X�[���P�̎擾
     * 
     * @return �v���X�[���P
     */
    public String getPrsHacNki1() {

        return this.prsHacNki1;

    }


    /**
     * �v���X�����݌v�̃Z�b�g
     * 
     * @param prsHacRui �v���X�����݌v
     */
    public void setPrsHacRui(String prsHacRui) {

        this.prsHacRui = prsHacRui;

    }


    /**
     * �v���X�����݌v�̎擾
     * 
     * @return �v���X�����݌v
     */
    public String getPrsHacRui() {

        return this.prsHacRui;

    }


    /**
     * �v���X�������Q�̃Z�b�g
     * 
     * @param prsHacSuu2 �v���X�������Q
     */
    public void setPrsHacSuu2(String prsHacSuu2) {

        this.prsHacSuu2 = prsHacSuu2;

    }


    /**
     * �v���X�������Q�̎擾
     * 
     * @return �v���X�������Q
     */
    public String getPrsHacSuu2() {

        return this.prsHacSuu2;

    }


    /**
     * �v���X���ɗ��Q�̃Z�b�g
     * 
     * @param prsHacNyo2 �v���X���ɗ��Q
     */
    public void setPrsHacNyo2(String prsHacNyo2) {

        this.prsHacNyo2 = prsHacNyo2;

    }


    /**
     * �v���X���ɗ��Q�̎擾
     * 
     * @return �v���X���ɗ��Q
     */
    public String getPrsHacNyo2() {

        return this.prsHacNyo2;

    }


    /**
     * �v���X�[���Q�̃Z�b�g
     * 
     * @param prsHacNki2 �v���X�[���Q
     */
    public void setPrsHacNki2(String prsHacNki2) {

        this.prsHacNki2 = prsHacNki2;

    }


    /**
     * �v���X�[���Q�̎擾
     * 
     * @return �v���X�[���Q
     */
    public String getPrsHacNki2() {

        return this.prsHacNki2;

    }


    /**
     * �v���X�`�F�b�N�{�b�N�X1�̃Z�b�g
     * 
     * @param checkprs1 �v���X�`�F�b�N�{�b�N�X1
     */
    public void setCheck_prs1(boolean check_prs1) {

        this.check_prs1 = check_prs1;

    }


    /**
     * �v���X�`�F�b�N�{�b�N�X1�̎擾
     * 
     * @return �v���X�`�F�b�N�{�b�N�X1
     */
    public boolean getCheck_prs1() {

        return this.check_prs1;

    }


    /**
     * �v���X�`�F�b�N�{�b�N�X2�̃Z�b�g
     * 
     * @param checkprs2 �v���X�`�F�b�N�{�b�N�X2
     */
    public void setCheck_prs2(boolean check_prs2) {

        this.check_prs2 = check_prs2;

    }


    /**
     * �v���X�`�F�b�N�{�b�N�X2�̎擾
     * 
     * @return �v���X�`�F�b�N�{�b�N�X2
     */
    public boolean getCheck_prs2() {

        return this.check_prs2;

    }

    /**
     * �����ޔ������̃Z�b�g
     * 
     * @param fukHacSuu �����ޔ�����
     */
    public void setFukHacSuu(String fukHacSuu) {

        this.fukHacSuu = fukHacSuu;

    }


    /**
     * �����ޔ������̎擾
     * 
     * @return �����ޔ�����
     */
    public String getFukHacSuu() {

        return this.fukHacSuu;

    }


    /**
     * �����ޔ[��(�N)�̃Z�b�g
     * 
     * @param fukNkiYear �����ޔ[��(�N)
     */
    public void setFukNkiYear(String fukNkiYear) {

        this.fukNkiYear = fukNkiYear;

    }


    /**
     * �����ޔ[��(�N)�̎擾
     * 
     * @return �����ޔ[��(�N)
     */
    public String getFukNkiYear() {

        return this.fukNkiYear;

    }


    /**
     * �����ޔ[��(��)�̃Z�b�g
     * 
     * @param fukNkiMonth �����ޔ[��(��)
     */
    public void setFukNkiMonth(String fukNkiMonth) {

        this.fukNkiMonth = fukNkiMonth;

    }


    /**
     * �����ޔ[��(��)�̎擾
     * 
     * @return �����ޔ[��(��)
     */
    public String getFukNkiMonth() {

        return this.fukNkiMonth;

    }


    /**
     * �����ޔ[��(��)�̃Z�b�g
     * 
     * @param fukNkiDay �����ޔ[��(��)
     */
    public void setFukNkiDay(String fukNkiDay) {

        this.fukNkiDay = fukNkiDay;

    }


    /**
     * �����ޔ[��(��)�̎擾
     * 
     * @return �����ޔ[��(��)
     */
    public String getFukNkiDay() {

        return this.fukNkiDay;

    }


    /**
     * �����ޔ�����̃Z�b�g
     * 
     * @param fukMkrCod �����ޔ�����
     */
    public void setFukMkrCod(String fukMkrCod) {

        this.fukMkrCod = fukMkrCod;

    }


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
     * �����ޔ�����̎擾
     * 
     * @return �����ޔ�����
     */
    public String getFukMkrCod() {

        return this.fukMkrCod;

    }


    /**
     * �����ޖ����Ɍv�̃Z�b�g
     * 
     * @param fukMnyKei �����ޖ����Ɍv
     */
    public void setFukMnyKei(String fukMnyKei) {

        this.fukMnyKei = fukMnyKei;

    }


    /**
     * �����ޖ����Ɍv�̎擾
     * 
     * @return �����ޖ����Ɍv
     */
    public String getFukMnyKei() {

        return this.fukMnyKei;

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
     * �����ޓ��ɗ��P�̃Z�b�g
     * 
     * @param fukHacNyo1 �����ޓ��ɗ��P
     */
    public void setFukHacNyo1(String fukHacNyo1) {

        this.fukHacNyo1 = fukHacNyo1;

    }


    /**
     * �����ޓ��ɗ��P�̎擾
     * 
     * @return �����ޓ��ɗ��P
     */
    public String getFukHacNyo1() {

        return this.fukHacNyo1;

    }


    /**
     * �����ޔ[���P�̃Z�b�g
     * 
     * @param fukHacNki1 �����ޔ[���P
     */
    public void setFukHacNki1(String fukHacNki1) {

        this.fukHacNki1 = fukHacNki1;

    }


    /**
     * �����ޔ[���P�̎擾
     * 
     * @return �����ޔ[���P
     */
    public String getFukHacNki1() {

        return this.fukHacNki1;

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
     * �����ޔ������Q�̃Z�b�g
     * 
     * @param fukHacSuu2 �����ޔ������Q
     */
    public void setFukHacSuu2(String fukHacSuu2) {

        this.fukHacSuu2 = fukHacSuu2;

    }


    /**
     * �����ޔ������Q�̎擾
     * 
     * @return �����ޔ������Q
     */
    public String getFukHacSuu2() {

        return this.fukHacSuu2;

    }


    /**
     * �����ޓ��ɗ��Q�̃Z�b�g
     * 
     * @param fukHacNyo2 �����ޓ��ɗ��Q
     */
    public void setFukHacNyo2(String fukHacNyo2) {

        this.fukHacNyo2 = fukHacNyo2;

    }


    /**
     * �����ޓ��ɗ��Q�̎擾
     * 
     * @return �����ޓ��ɗ��Q
     */
    public String getFukHacNyo2() {

        return this.fukHacNyo2;

    }


    /**
     * �����ޔ[���Q�̃Z�b�g
     * 
     * @param fukHacNki2 �����ޔ[���Q
     */
    public void setFukHacNki2(String fukHacNki2) {

        this.fukHacNki2 = fukHacNki2;

    }


    /**
     * �����ޔ[���Q�̎擾
     * 
     * @return �����ޔ[���Q
     */
    public String getFukHacNki2() {

        return this.fukHacNki2;

    }


    /**
     * �����ރ`�F�b�N�{�b�N�X1�̃Z�b�g
     * 
     * @param checkfuk1 �����ރ`�F�b�N�{�b�N�X1
     */
    public void setCheck_fuk1(boolean check_fuk1) {

        this.check_fuk1 = check_fuk1;

    }


    /**
     * �����ރ`�F�b�N�{�b�N�X1�̎擾
     * 
     * @return �����ރ`�F�b�N�{�b�N�X1
     */
    public boolean getCheck_fuk1() {

        return this.check_fuk1;

    }

	/**
     * �����ރ`�F�b�N�t���O
     * 
     * @param check_flg �����ރ`�F�b�N�t���O
     */
    public void setCheck_flg(boolean check_flg) {
		
        this.check_flg = check_flg+"";

    }


    /**
     * �����ރ`�F�b�N�t���O
     * 
     * @return �����ރ`�F�b�N�t���O
     */
    public String getCheck_flg() {

        return this.check_flg;

    }
    
    /**
     * �����ރ`�F�b�N�T�C��
     * 
     * @param check_flg �����ރ`�F�b�N�T�C��
     */
    public void setCheck_sgn(String check_sgn) {
		
        this.check_sgn = check_sgn;

    }


    /**
     * �����ރ`�F�b�N�T�C��
     * 
     * @return �����ރ`�F�b�N�T�C��
     */
    public String getCheck_sgn() {

        return this.check_sgn;

    }

	/**
     * �v���X���������p�`�F�b�N�{�b�N�X
     * 
     * @param check_prsHacSuu �v���X���������p�`�F�b�N�{�b�N�X
     */
    public void setCheck_prsHacSuu(boolean check_prsHacSuu) {
		
        this.check_prsHacSuu = check_prsHacSuu;

    }


    /**
     * �v���X���������p�`�F�b�N�{�b�N�X
     * 
     * @return �v���X���������p�`�F�b�N�{�b�N�X
     */
    public boolean getCheck_prsHacSuu() {

        return this.check_prsHacSuu;

    }

	/**
     * index
     * 
     * @param index index
     */
    public void setIndex(int index) {
		
        this.index = index;

    }


    /**
     * index
     * 
     * @return index
     */
    public int getIndex() {

        return this.index;

    }

    /**
     * �o�͍σT�C��
     * 
     * @param syrZmiSgn �o�͍σT�C��
     */
    public void setSyrZmiSgn(String syrZmiSgn) {
		
        this.syrZmiSgn = syrZmiSgn;

    }


    /**
     * �o�͍σT�C��
     * 
     * @return �o�͍σT�C��
     */
    public String getSyrZmiSgn() {

        return this.syrZmiSgn;

    }
    
    /**
     * �������G���[�T�C��
     * 
     * @param hacSuu_errSgn �������G���[�T�C��
     */
    public void setHacSuu_errSgn(boolean hacSuu_errSgn) {
		
        this.hacSuu_errSgn = hacSuu_errSgn;

    }


    /**
     * �������G���[�T�C��
     * 
     * @return �������G���[�T�C��
     */
    public boolean getHacSuu_errSgn() {

        return this.hacSuu_errSgn;

    }
    
	
    /**
     * �v���X�敪�P�̃Z�b�g
     * 
     * @param prsKbn1 �v���X�敪�P
     */
    public void setPrsKbn1(String prsKbn1) {


        this.prsKbn1 = prsKbn1;


    }

    /**
     * �v���X�敪�P�̎擾
     * 
     * @return �v���X�敪�P
     */
    public String getPrsKbn1() {

		if(this.prsKbn1 == null || this.prsKbn1.equals(""))
			return this.prsKbn1;
        return this.prsKbn1.substring(0,1);


    }

	/**
     * �v���X�敪�Q�̃Z�b�g
     * 
     * @param prsKbn2 �v���X�敪�Q
     */
    public void setPrsKbn2(String prsKbn2) {


        this.prsKbn2 = prsKbn2;


    }

    /**
     * �v���X�敪�Q�̎擾
     * 
     * @return �v���X�敪�Q
     */
    public String getPrsKbn2() {

		if(this.prsKbn2 == null || this.prsKbn2.equals(""))
			return this.prsKbn2;
        return this.prsKbn2.substring(0,1);


    }

	/**
     * �����ދ敪�P�̃Z�b�g
     * 
     * @param prsKbn1 �����ދ敪�P
     */
    public void setFukKbn1(String fukKbn1) {


        this.fukKbn1 = fukKbn1;


    }

    /**
     * �����ދ敪�P�̎擾
     * 
     * @return �����ދ敪�P
     */
    public String getFukKbn1() {

		if(this.fukKbn1 == null || this.fukKbn1.equals(""))
			return this.fukKbn1;
        return this.fukKbn1.substring(0,1);

    }

	/**
     * �����ދ敪�Q�̃Z�b�g
     * 
     * @param prsKbn2 �����ދ敪�Q
     */
    public void setFukKbn2(String fukKbn2) {


        this.fukKbn2 = fukKbn2;


    }

    /**
     * �����ދ敪�Q�̎擾
     * 
     * @return �����ދ敪�Q
     */
    public String getFukKbn2() {

		if(this.fukKbn2 == null || this.fukKbn2.equals(""))
			return this.fukKbn2;
        return this.fukKbn2.substring(0,1);

    }
	
	/**
	 * Gets the prsNyoKei
	 * @return Returns a String
	 */
	public String getPrsNyoKei() {
		return prsNyoKei;
	}
	/**
	 * Sets the prsNyoKei
	 * @param prsNyoKei The prsNyoKei to set
	 */
	public void setPrsNyoKei(String prsNyoKei) {
		this.prsNyoKei = prsNyoKei;
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
	
     public void clear(){
    
        this.todUriSuu = "";
        this.b1dUri = "";
        this.b2dUri = "";
        this.b3dUri = "";
        this.b4dUri = "";
        this.b5dUri = "";
        this.towUri = "";
        this.b1wUri = "";
        this.b2wUri = "";
        this.tomUri = "";
        this.b1mUri = "";
        this.b2mUri = "";
        this.tomHpn = "";
        this.b1mHpn = "";
        this.b2mHpn = "";
        this.tomTna = "";
        this.b1mTna = "";
        this.b2mTna = "";
        this.prsHacSuu = "";
        this.prsNkiYear = "";
        this.prsNkiMonth = "";
        this.prsNkiDay = "";
        this.prsMkrCod = "";
    	this.choksoKbn = ""; 
        this.prsMnyKei = "";
        this.prsHacSuu1 = "";
        this.prsHacNyo1 = "";
        this.prsHacNki1 = "";
        this.prsHacRui = "";
        this.prsHacSuu2 = "";
        this.prsHacNyo2 = "";
        this.prsHacNki2 = "";
        this.check_prs1 = false;
        this.check_prs2 = false;
        this.fukHacSuu = "";
        this.fukNkiYear = "";
        this.fukNkiMonth = "";
        this.fukNkiDay = "";
        this.fukMkrCod = "";
        this.fukZaiSuu = "";
        this.fukMnyKei = "";
        this.fukHacSuu1 = "";
        this.fukHacNyo1 = "";
        this.fukHacNki1 = "";
        this.fukHacRui = "";
        this.fukHacSuu2 = "";
        this.fukHacNyo2 = "";
        this.fukHacNki2 = "";
        this.check_fuk1 = false;
        this.check_flg = "";
        this.check_sgn = "";
		this.check_prsHacSuu = false;
    	this.hacSuu_errSgn = false;
    	this.prsKbn1 = "";
        this.prsKbn2 = "";
        this.fukKbn1 = "";
        this.fukKbn2 = "";
        this.prsNyoKei = "";
        this.fukNyoKei = "";
    }

}
