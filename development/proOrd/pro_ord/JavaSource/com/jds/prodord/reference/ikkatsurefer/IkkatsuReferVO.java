/**
* IkkatsuReferVO  �ꊇ�Ɖ��ʃo�����[�I�u�W�F�N�g�N���X
*
*	�쐬��    2003/03/31
*	�쐬��    �i�m�h�h�j���c �Ĕ�
* �����T�v    �i�ԃ}�X�^�[(FTBHIN01)�A�݌Ƀe�[�u��(FTBZAI01)�A���������e�[�u��(FTBHAC02)�A�݌ɓ����}�X�^�[(FTBMST04)
* 			 �����ރ}�X�^�[(FTBFUK01)�A�`�ԃ����N�ʏ����}�X�^�[(FTBMST05)����擾�����f�[�^���i�[����N���X�B
*
*	[�ύX����]
*       2003/05/06�i�m�h�h�j�g�c �h�q
* 			�E�v���X������ʂɓn���f�[�^�A�����於�̂̒ǉ��B
* 		2003/07/01
* 			�E�[�i��R�[�h�A�[�i�於�̒ǉ��B
* 		2004/02/25�@(�m�h�h�j�X
* 			�E�����ރR�[�h�E�d����̓W�J���p�^�[���}�X�^����t���\���}�X�^�ɕύX
* 		2004/03/29 (NII) �X
* 			�E�i�ԃ}�X�^�̃e�[�u�����ύX
* 		2004/04/02 (NII) �X
* 			�E�����ޔ����̏C��(���ރR�[�h�P�������݂ɑΉ�)
* 		2004/04/21 (NII)�X
* 			�E�ō��艿�\���ɔ����C��
*		2005/02/03�i�m�h�h�j�g�c
*			�E�ꏊ�R�[�h�̒ǉ�
*		2005/05/19�i�m�h�h�j�g�c
*			�E���[�U�[�h�c�̒ǉ�
**/

package com.jds.prodord.reference.ikkatsurefer;

import java.util.*;
import com.jds.prodord.common.*;
import com.jds.prodord.order.prsorder.*;

public class IkkatsuReferVO implements CommonOrderVO{
	
	/**
     * ����
     */
    private int count = 0;
        
    /**
     * ��\��Ў��ʃR�[�h
     */
    private String daiKaiSkbCod = "";
    /**
     * ��Ў��ʃR�[�h(���[�U�[)
     */
    private String queryKaiSkbCod = "";
    
    /**
     * ��Ў��ʃR�[�h
     */
    private String kaiSkbCod = "";
    /**
     * �ꏊ�R�[�h
     */
    private String bshCod = "";
	/**
	 * ���[�U�[�h�c
	 */
	private String usrId = "";
    
//--------------------------------------------------------

    /**
     * �݌Ƀe�[�u��(FTBZAI01)���݃t���O
     */
    private boolean exsitZai01 = false;
    
    /**
     * ���������e�[�u��(FTBHAC02)���݃t���O�i�v���X�j
     */
    private boolean exsitHac02Prs = false;
    
    /**
     * ���������e�[�u��(FTBHAC02)���݃t���O�i�����ށj
     */
    private boolean exsitHac02Fuk = false;
    
    /**
     * �݌ɓ����}�X�^�[(FTBMST04)���݃t���O
     */
    private boolean exsitMst04 = false;
    
    /**
     * �`�ԃ����N�ʏ����}�X�^�[(FTBMST05)���݃t���O
     */
    private boolean exsitMst05 = false; 
    
    /**
     * �����ރ}�X�^�[(FTBFUK01)���݃t���O
     */
    private boolean exsitFuk01 = false; 

    

//--�i�ԃ}�X�^�[(FTBHIN01)--------------------------------------------------------------

    /**
     * �`�ԃR�[�h
     */
    private String ketCod = "";
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
     * �v���X������
     */
    private String prsMkrCod = "";
    /**
     * �����ރ��[�J�[�R�[�h(�����ރp�^�[���R�[�h)
     */
    private String fukSziMkr = "";
    /**
     * �Z�b�g��
     */
    private String setSuu = "";
	/**
	 * �ō��艿
	 */
	private String zikTik = "";
    

//--�`�Ԗ��̃}�X�^�[(FTBZMST06)---------------------------------------------------------
    
    /**
     * �`�Ԗ���
     */
    private String ketNm = "";
    
       
//--�݌Ƀe�[�u��(FTBZAI01)--------------------------------------------------------------

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
     * ���ϔ���
     */
    private String avgUri = "";
    /**
     * �O�X���I��
     */
    private String b2mTna = "";
    /**
     * �v���X�����Ɍv
     */
    private String prsMnyKei = "";
    /**
     * �v���X�����݌v
     */
    private String prsHacRui = "";
    /**
     * �����ލ݌ɐ�(ZAI01)
     */
    private String fukZaiSuu = "";
    /**
     * �����ސ�(HIN12)
     */
    private String fukSziSuu = "";
    /**
     * �����ޖ����Ɍv
     */
    private String fukMnyKei = "";
    /**
     * �����ޔ����݌v
     */
    private String fukHacRui = "";
    /**
     * �v���X���ɗ݌v
     */
    private String prsNyoKei = "";
    /**
     * �����ޓ��ɗ݌v
     */
    private String fukNyoKei = "";
     
//---------------------------------------------------------------------------------------------

    /**
     * ��Đ�
     */
    private String teianSuu = "";
    /**
     * �s���݌ɐ�
     */
    private String fskZaiSuu = "";
    /**
     * �\��
     */
    private String kanouSuu = "";
    /**
     * �݌ɓ���
     */
    private String zaiNisuu = "";
    
//---------------------------------------------------------------------------------------------
    /**
     * �v���X������
     */
    private String prsHacSuu = "";
    /**
     * �v���X�[��
     */
    private int prsNki = 0;
    /**
     * �����ޔ�����
     */
    private String fukHacSuu = "";
    /**
     * �����ޔ[��
     */
    private int fukNki = 0;
    /**
     * �����敪
     */
    private String choksoKbn = "";    
   
     
//--���������e�[�u��(FTBHAC02)--------------------------------------------------------------

    /**
     * �����σT�C��
     */
    private int hacZmiSgn = 0;
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
     * �v���X�敪�P
     */
    private String prsKbn1 = "";
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
     * �v���X�敪�Q
     */
    private String prsKbn2 = "";
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
     * �����ދ敪�P
     */
    private String fukKbn1 = "";
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
     * �����ދ敪�Q
     */
    private String fukKbn2 = "";   
    
//--�݌ɓ����}�X�^�[(FTBMST04)---------------------------------------------------------

    /**
     * �݌ɓ����E�������[�h�^�C���i���Z�����j
     */
    private int ksnNisuu = 0;

     
//--�`�ԃ����N�ʏ����}�X�^�[(FTBMST05)--------------------------------------------------    
    
    /**
     * ���Y���[�h�^�C��
     */
    private int ssnRedTim = 0;
    
    /**
     * �܂�ߐ�
     */
    private int mrmSuu = 0;
    
    
//--�����ރ}�X�^�[(FTBMST01)--------------------------------------------------    
    
    /**
     * �����ޔ�����
     */
    private String fukSziHacSaki = "";
    
    /**
     * �����ޖ���
     */
    private String fukSziNm = "";
    
    /**
     * �����ރR�[�h
     */
    private String fukSziCod = "";
    
    /**
     * �����ރR�[�harr
     */
    private ArrayList fukSziCod_arr = new ArrayList();
    
	/**
	 * �����ޔ�����R�[�harr 
	 */
	private ArrayList fukSziHacSaki_arr = new ArrayList(); //	2004.02.25 add
	/**
	 * ���ރR�[�harr
	 */
	private ArrayList bunCod_arr = new ArrayList();
	/**
	 * �����ޖ���arr
	 */
	private ArrayList fukSziNm_arr = new ArrayList();

    /**
     * �����ރp�^�[���R�[�h
     */
    private String fukSziPtn = "";
    
//-------------------------------------------------------------------------------------
    
    /**
     * �v���X�`�F�b�N�{�b�N�X1
     */
    private boolean check_prs1 = false;
    /**
     * �v���X�`�F�b�N�{�b�N�X2
     */
    private boolean check_prs2 = false;
    /**
     * �����ރ`�F�b�N�{�b�N�X1
     */
    private boolean check_fuk1 = false;
	/**
     * �����ރ`�F�b�N�t���O
     */
    private boolean check_flg = false;
    /**
     * �����ރ`�F�b�N�T�C��
     */
    private String check_sgn = "";
    /**
     * �v���X���������p�`�F�b�N�{�b�N�X
     */
    private boolean check_prsHacSuu = false;

	private int updDte;
	private int updJkk;

	
	/**
     * �����於��
     */
    private String hacNm = "";
    /**
     * ��s�t���O
     */
    private String basedRowFlg = "";
	/**
	 * ���ރR�[�h
	 */
	private String bunCod = "";
   
    //�v���X������ʂɓn��VO
	private	PrsOrderVO[] prsVOs = null;
	
	//�[�i��R�[�h
	private String nhnCod = "";
	
	//�[�i�於
	private String nhnMei = "";
//*************************************************************************************

    // ----------------------------------------------------------- Properties
    
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
     * ��Ў��ʃR�[�h(���[�U�[)�̃Z�b�g
     * 
     * @param queryKaiSkbCod 
     */
    public void setQueryKaiSkbCod(String queryKaiSkbCod) {

        this.queryKaiSkbCod = queryKaiSkbCod;

    }


    /**
     * ��Ў��ʃR�[�h(���[�U�[)�̎擾
     * 
     * @return ��Ў��ʃR�[�h(���[�U�[)
     */
    public String getQueryKaiSkbCod() {

        return this.queryKaiSkbCod;

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
//-----------------------------------------------------------------------------

    /**
     * �݌Ƀe�[�u��(FTBZAI01)���݃t���O�̃Z�b�g
     * 
     * @param exsitZai01 �݌Ƀe�[�u��(FTBZAI01)���݃t���O
     */
    public void setExsitZai01(boolean exsitZai01) {

        this.exsitZai01 = exsitZai01;

    }


    /**
     * �݌Ƀe�[�u��(FTBZAI01)���݃t���O�̎擾
     * 
     * @return �݌Ƀe�[�u��(FTBZAI01)���݃t���O
     */
    public boolean getExsitZai01() {

        return this.exsitZai01;

    }


    /**
     * ���������e�[�u��Prs(FTBHAC02)���݃t���O�̃Z�b�g
     * 
     * @param exsitHac02 ���������e�[�u��Prs(FTBHAC02)���݃t���O
     */
    public void setExsitHac02Prs(boolean exsitHac02Prs) {

        this.exsitHac02Prs = exsitHac02Prs;

    }


    /**
     * ���������e�[�u��Prs(FTBHAC02)���݃t���O�̎擾
     * 
     * @return ���������e�[�u��Prs(FTBHAC02)���݃t���O
     */
    public boolean getExsitHac02Prs() {

        return this.exsitHac02Prs;

    }

    /**
     * ���������e�[�u��Fuk(FTBHAC02)���݃t���O�̃Z�b�g
     * 
     * @param exsitHac02 ���������e�[�u��Fuk(FTBHAC02)���݃t���O
     */
    public void setExsitHac02Fuk(boolean exsitHac02Fuk) {

        this.exsitHac02Fuk = exsitHac02Fuk;

    }


    /**
     * ���������e�[�u��Fuk(FTBHAC02)���݃t���O�̎擾
     * 
     * @return ���������e�[�u��Fuk(FTBHAC02)���݃t���O
     */
    public boolean getExsitHac02Fuk() {

        return this.exsitHac02Fuk;

    }
    /**
     * �݌ɓ����}�X�^�[(FTBMST04)���݃t���O�̃Z�b�g
     * 
     * @param exsitMst04 �݌ɓ����}�X�^�[(FTBMST04)���݃t���O
     */
    public void setExsitMst04(boolean exsitMst04) {

        this.exsitMst04 = exsitMst04;

    }


    /**
     * �݌ɓ����}�X�^�[(FTBMST04)���݃t���O�̎擾
     * 
     * @return �݌ɓ����}�X�^�[(FTBMST04)���݃t���O
     */
    public boolean getExsitMst04() {

        return this.exsitMst04;

    }


    /**
     * �`�ԃ����N�ʏ����}�X�^�[(FTBMST05)���݃t���O�̃Z�b�g
     * 
     * @param exsitMst05 �`�ԃ����N�ʏ����}�X�^�[(FTBMST05)���݃t���O
     */
    public void setExsitMst05(boolean exsitMst05) {

        this.exsitMst05 = exsitMst05;

    }


    /**
     * �`�ԃ����N�ʏ����}�X�^�[(FTBMST05)���݃t���O�̎擾
     * 
     * @return �`�ԃ����N�ʏ����}�X�^�[(FTBMST05)���݃t���O
     */
    public boolean getExsitMst05() {

        return this.exsitMst05;

    }
    
    /**
     * �����ރ}�X�^�[(FTBFUK01)���݃t���O�̃Z�b�g
     * 
     * @param exsitMst05 �����ރ}�X�^�[(FTBFUK01)���݃t���O
     */
    public void setExsitFuk01(boolean exsitFuk01) {

        this.exsitFuk01 = exsitFuk01;

    }


    /**
     * �����ރ}�X�^�[(FTBFUK01)���݃t���O�̎擾
     * 
     * @return �����ރ}�X�^�[(FTBFUK01)���݃t���O
     */
    public boolean getExsitFuk01() {

        return this.exsitFuk01;

    }
    
    public void setExisted(){
		
		this.exsitZai01 = true;
	    this.exsitHac02Prs = true;
	    this.exsitHac02Fuk = true;
	    this.exsitMst04 = true;
	    this.exsitMst05 = true; 
	    this.exsitFuk01 = true; 

	}
 
    // ----------------------------------------------------------- Properties Left

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
    public void setHacZmiSgn(int i) {

        this.hacZmiSgn = i;

    }


    /**
     * �����σT�C���̎擾
     * 
     * @return �����σT�C��
     */
    public int getHacZmiSgn() {

        return this.hacZmiSgn;

    }

    // ----------------------------------------------------------- Properties Right

    /**
     * �݌ɐ��̃Z�b�g
     * 
     * @param aZiSuu �݌ɐ�
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
     * �\���̃Z�b�g
     * 
     * @param kanouSuu �\��
     */
    public void setKanouSuu(String kanouSuu) {

        this.kanouSuu = kanouSuu;

    }


    /**
     * �\���̎擾
     * 
     * @return �\��
     */
    public String getKanouSuu() {

        return this.kanouSuu;

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
     * �݌ɓ����E�������[�h�^�C���̃Z�b�g
     * 
     * @param ksnNisuu �݌ɓ����E�������[�h�^�C��
     */
    public void setKsnNisuu(int ksnNisuu) {

        this.ksnNisuu = ksnNisuu;

    }


    /**
     * �݌ɓ����E�������[�h�^�C���̎擾
     * 
     * @return �݌ɓ����E�������[�h�^�C��
     */
    public int getKsnNisuu() {

        return this.ksnNisuu;

    }


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
     * @param hskZaiSuu �s���݌ɐ�
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

     //�v���X�[��
	public int getPrsNki() {
		return prsNki;
	}
	public void setPrsNki(int i) {
		prsNki = i;
	}
	
	public void setPrsNki(String year,String month,String day){
		if(year.trim().equals("") || month.trim().equals("") || day.trim().equals(""))
			prsNki = 0;
		else
			prsNki = (Integer.parseInt(year,10)*10000) + (Integer.parseInt(month,10)*100) + Integer.parseInt(day,10);
	}
	public String getPrsNkiYear(){
		if(prsNki <= 0)
			return "";
		String str = StringUtils.lpad(String.valueOf(prsNki),6,"0");
		return str.substring(0,2);
	}
	public String getPrsNkiMonth(){
		if(prsNki <= 0)
			return "";
		String str = StringUtils.lpad(String.valueOf(prsNki),6,"0");
		return str.substring(2,4);
	}
	public String getPrsNkiDay(){
		if(prsNki <= 0)
			return "";
		String str = StringUtils.lpad(String.valueOf(prsNki),6,"0");
		return str.substring(4,6);
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


        return this.prsKbn1;


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


        return this.prsKbn2;


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


     //�����ޔ[��
	public int getFukNki() {
		return fukNki;
	}
	public void setFukNki(int i) {
		fukNki = i;
	}
	
	public void setFukNki(String year,String month,String day){
		if(year.trim().equals("") || month.trim().equals("") || day.trim().equals(""))
			fukNki = 0;
		else
			fukNki = (Integer.parseInt(year,10)*10000) + (Integer.parseInt(month,10)*100) + Integer.parseInt(day,10);
	}
	public String getFukNkiYear(){
		if(fukNki <= 0)
			return "";
		String str = StringUtils.lpad(String.valueOf(fukNki),6,"0");
		return str.substring(0,2);
	}
	public String getFukNkiMonth(){
		if(fukNki <= 0)
			return "";
		String str = StringUtils.lpad(String.valueOf(fukNki),6,"0");
		return str.substring(2,4);
	}
	public String getFukNkiDay(){
		if(fukNki <= 0)
			return "";
		String str = StringUtils.lpad(String.valueOf(fukNki),6,"0");
		return str.substring(4,6);
	}


    /**
     * �����ރ��[�J�[�R�[�h(�����ރp�^�[���R�[�h)�̃Z�b�g
     * 
     * @param fukSziMkr �����ރ��[�J�[�R�[�h
     */
    public void setFukSziMkr(String fukSziMkr) {

        this.fukSziMkr = fukSziMkr;

    }

    /**
     * �����ރ��[�J�[�R�[�h(�����ރp�^�[���R�[�h)�̎擾
     * 
     * @return �����ރ��[�J�[�R�[�h
     */
    public String getFukSziMkr() {

        return fukSziMkr;

    }
    
    /**
     * �����ޔ�����̃Z�b�g
     * 
     * @param fukSziMkr �����ޔ�����
     */
    public void setFukSziHacSaki(String fukSziHacSaki) {

        this.fukSziHacSaki = fukSziHacSaki;

    }

    /**
     * �����ޔ�����̎擾
     * 
     * @return �����ޔ�����
     */
    public String getFukSziHacSaki() {

        return this.fukSziHacSaki;

    }
    
    /**
     * �����ޖ��̂̃Z�b�g
     * 
     * @param fukSziMkr �����ޖ���
     */
    public void setFukSziNm(String fukSziNm) {

        this.fukSziNm = fukSziNm;

    }

    /**
     * �����ޖ��̂̎擾
     * 
     * @return �����ޖ���
     */
    public String getFukSziNm() {

        return this.fukSziNm;

    }
    
    /**
     * �����ލ݌ɐ�(ZAI01)�̃Z�b�g
     * 
     * @param fukZaiSuu �����ލ݌ɐ�(ZAI01)
     */
    public void setFukZaiSuu(String fukZaiSuu) {

        this.fukZaiSuu = fukZaiSuu;

    }


    /**
     * �����ލ݌ɐ�(ZAI01)�̎擾
     * 
     * @return �����ލ݌ɐ�(ZAI01)
     */
    public String getFukZaiSuu() {

        return this.fukZaiSuu;

    }
    
    /**
     * �����ސ�(HIN12)�̃Z�b�g 
     * 
     * @param fukSziSuu �����ސ�(HIN12)
     */
    public void setFukSziSuu(String fukSziSuu) {

        this.fukSziSuu = fukSziSuu;

    }


    /**
     * �����ސ�(HIN12)�̎擾
     * 
     * @return �����ސ�(HIN12)
     */
    public String getFukSziSuu() {

        return this.fukSziSuu;

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


        return this.fukKbn1;


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


        return this.fukKbn2;


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

        this.check_flg = check_flg;

    }


    /**
     * �����ރ`�F�b�N�t���O
     * 
     * @return �����ރ`�F�b�N�t���O
     */
    public boolean getCheck_flg() {

        return this.check_flg;

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
//------------------------------------------------------------------------------------------------------
    /**
     * ���Y���[�h�^�C���̃Z�b�g
     * 
     * @param ssnRedTim ���Y���[�h�^�C��
     */
    public void setSsnRedTim(int ssnRedTim) {

        this.ssnRedTim = ssnRedTim;

    }


    /**
     * ���Y���[�h�^�C���̎擾
     * 
     * @return ���Y���[�h�^�C��
     */
    public int getSsnRedTim() {

        return this.ssnRedTim;

    }
    
    
    /**
     * �܂�ߐ��̃Z�b�g
     * 
     * @param mrmSuu �܂�ߐ�
     */
    public void setMrmSuu(int mrmSuu) {

        this.mrmSuu = mrmSuu;

    }


    /**
     * �܂�ߐ��̎擾
     * 
     * @return �܂�ߐ�
     */
    public int getMrmSuu() {

        return this.mrmSuu;

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
    
    /**
     * �Z�b�g���̃Z�b�g
     * 
     * @param setSuu 
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
     * �����̃Z�b�g
     */
    public void setCount(int i) {
        this.count = i;
    }
    /**
     * �����̎擾
     */
    public int getCount() {
        return this.count;
    }
    
    /**
	 * �����ރR�[�harr�̎擾
	 * @return Returns a ArrayList
	 */
	public ArrayList getFukSziCod_arr() {
		return fukSziCod_arr;
	}
	/**
	 * �����ރR�[�harr�̃Z�b�g
	 * @param fukSziCod_arr The fukSziCod_arr to set
	 */
	public void setFukSziCod_arr(ArrayList fukSziCod_arr) {
		this.fukSziCod_arr = fukSziCod_arr;
	}
	/**
	 * ���ރR�[�h
	 * @return
	 */
	public ArrayList getBunCod_arr() {
		return bunCod_arr;
	}

	/**
	 * ���ރR�[�h
	 * @param list
	 */
	public void setBunCod_arr(ArrayList list) {
		bunCod_arr = list;
	}
	/**
	 * �����ޖ���
	 * @return
	 */
	public ArrayList getFukSziNm_arr() {
		return fukSziNm_arr;
	}

	/**
	 * �����ޖ���
	 * @param list
	 */
	public void setFukSziNm_arr(ArrayList list) {
		fukSziNm_arr = list;
	}
    
    
	/**
	 * �����ރR�[�h�̎擾
	 * @return Returns a String
	 */
	public String getFukSziCod() {
		return fukSziCod;
	}
	/**
	 * �����ރR�[�h�̃Z�b�g
	 * @param fukSziCod The fukSziCod to set
	 */
	public void setFukSziCod(String fukSziCod) {
		this.fukSziCod = fukSziCod;
	}
    
	/**
	 * ��s�t���O�̎擾
	 * @return Returns a String
	 */
	public String getBasedRowFlg() {
		return basedRowFlg;
	}
	/**
	 * ��s�t���O�̃Z�b�g
	 * @param basedRowFlg The basedRowFlg to set
	 */
	public void setBasedRowFlg(String basedRowFlg) {
		this.basedRowFlg = basedRowFlg;
	}
    
    /**
	 * �����ރp�^�[���R�[�h�̎擾
	 * @return Returns a String
	 */
	public String getFukSziPtn() {
		return fukSziPtn;
	}
	/**
	 * �����ރp�^�[���R�[�h�̃Z�b�g
	 * @param fukSziPtnCod The fukSziPtnCod to set
	 */
	public void setFukSziPtn(String fukSziPtnCod) {
		this.fukSziPtn = fukSziPtnCod;
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
	 * �����於�̎擾
	 * 
	 * @return �����於
	 */
	public String getHacNm() {
		
		return hacNm;
		
	}
	
	/**
	 * �����於�̃Z�b�g
	 * 
	 * @param hacNm �����於
	 */
	public void setHacNm(String hacNm) {
		
		this.hacNm = hacNm;
		
	}

	/**
     * PrsOrderVO[]�̃Z�b�g
     * 
     * @param prsVOs PrsOrderVO[]
     */
    public void setPrsVOs(PrsOrderVO[] prsVOs) {

        this.prsVOs = prsVOs;
   
    }


    /**
     * PrsOrderVO[]�̎擾
     * 
     * @return PrsOrderVO[]
     */
    public PrsOrderVO[] getPrsVOs() {

        return this.prsVOs;

    }
	
//2003/07/01
	/**
	 * �[�i��R�[�h�̎擾
	 * 
	 * @return �[�i��R�[�h
	 */
	public String getNhnCod() {
		
		return nhnCod;
		
	}
	
	/**
	 * �[�i��R�[�h�̃Z�b�g
	 * 
	 * @param nhnCod �[�i��R�[�h
	 */
	public void setNhnCod(String nhnCod) {
		
		this.nhnCod = nhnCod;
		
	}
	
	/**
	 * �[�i�於�̎擾
	 * 
	 * @return �[�i�於
	 */
	public String getNhnMei() {
		
		return nhnMei;
		
	}
	
	/**
	 * �[�i�於�̃Z�b�g
	 * 
	 * @param nhnCod �[�i�於
	 */
	public void setNhnMei(String nhnMei) {
		
		this.nhnMei = nhnMei;
		
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
	
	/**
	 * Gets �T���v���敪
	 * @return Returns a String
	 */
	public String getSmpKbn(){	
		return "";//�v���X�����݂̂Ȃ̂Ńu�����N
	}
//	2004.02.25 add 
	/**
	 * �����ޔ�����
	 * @return
	 */
	public ArrayList getFukSziHacSaki_arr() {
		return fukSziHacSaki_arr;
	}

	/**
	 * �����ޔ�����
	 * @param list
	 */
	public void setFukSziHacSaki_arr(ArrayList list) {
		fukSziHacSaki_arr = list;
	}

// 2004.04.02 add
	/**
	 *  ���ރR�[�h�̎擾
	 * @return
	 */
	public String getBunCod() {
		return bunCod;
	}

	/**
	 *  ���ރR�[�h�̃Z�b�g
	 * @param bunCod
	 */
	public void setBunCod(String bunCod) {
		this.bunCod = bunCod;
	}

// 2004.04.21 add
	/**
	 * �ō��艿
	 * @return
	 */
	public String getZikTik() {
		return zikTik;
	}

	/**
	 * @param string
	 */
	public void setZikTik(String string) {
		zikTik = string;
	}

//2005/02/03 add
	/**
	 * �ꏊ�R�[�h�̎擾
	 * @return
	 */
	public String getBshCod() {
		return bshCod;
	}

	/**
	 * �ꏊ�R�[�h�̃Z�b�g
	 * @param string
	 */
	public void setBshCod(String string) {
		bshCod = string;
	}

//2005/05/19 add
	/**
	 * ���[�U�[�h�c�̎擾
	 * @return
	 */
	public String getUsrId() {
		return usrId;
	}

	/**
	 * ���[�U�[�h�c�̃Z�b�g
	 * @param string
	 */
	public void setUsrId(String string) {
		usrId = string;
	}
	
	/* (�� Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("count : " + count + ", ");
		sb.append("daiKaiSkbCod : " + daiKaiSkbCod + ", ");
		sb.append("queryKaiSkbCod : " + queryKaiSkbCod + ", ");
		sb.append("kaiSkbCod : " + kaiSkbCod + ", ");
		sb.append("bshCod : " + bshCod + ", ");
		sb.append("usrId : " + usrId + ", ");
		sb.append("exsitZai01 : " + exsitZai01 + ", ");
		sb.append("exsitHac02Prs : " + exsitHac02Prs + ", ");
		sb.append("exsitHac02Fuk : " + exsitHac02Fuk + ", ");
		sb.append("exsitMst04 : " + exsitMst04 + ", ");
		sb.append("exsitMst05  : " + exsitMst05 + ", ");
		sb.append("exsitFuk01  : " + exsitFuk01 + ", ");
		sb.append("ketCod : " + ketCod + ", ");
		sb.append("kigBng : " + kigBng + ", ");
		sb.append("hjiHnb : " + hjiHnb + ", ");
		sb.append("artKj : " + artKj + ", ");
		sb.append("hbiDte : " + hbiDte + ", ");
		sb.append("titKj : " + titKj + ", ");
		sb.append("tomRak : " + tomRak + ", ");
		sb.append("prsMkrCod : " + prsMkrCod + ", ");
		sb.append("fukSziMkr : " + fukSziMkr + ", ");
		sb.append("setSuu : " + setSuu + ", ");
		sb.append("zikTik : " + zikTik + ", ");
		sb.append("ketNm : " + ketNm + ", ");
		sb.append("aziSuu : " + aziSuu + ", ");
		sb.append("tnaSekZan : " + tnaSekZan + ", ");
		sb.append("jucZan : " + jucZan + ", ");
		sb.append("mhikSuu : " + mhikSuu + ", ");
		sb.append("rsvSum : " + rsvSum + ", ");
		sb.append("todUriSuu : " + todUriSuu + ", ");
		sb.append("b1dUri : " + b1dUri + ", ");
		sb.append("b2dUri : " + b2dUri + ", ");
		sb.append("b3dUri : " + b3dUri + ", ");
		sb.append("b4dUri : " + b4dUri + ", ");
		sb.append("b5dUri : " + b5dUri + ", ");
		sb.append("towUri : " + towUri + ", ");
		sb.append("b1wUri : " + b1wUri + ", ");
		sb.append("b2wUri : " + b2wUri + ", ");
		sb.append("tomUri : " + tomUri + ", ");
		sb.append("b1mUri : " + b1mUri + ", ");
		sb.append("b2mUri : " + b2mUri + ", ");
		sb.append("tomHpn : " + tomHpn + ", ");
		sb.append("b1mHpn : " + b1mHpn + ", ");
		sb.append("b2mHpn : " + b2mHpn + ", ");
		sb.append("tomTna : " + tomTna + ", ");
		sb.append("b1mTna : " + b1mTna + ", ");
		sb.append("avgUri : " + avgUri + ", ");
		sb.append("b2mTna : " + b2mTna + ", ");
		sb.append("prsMnyKei : " + prsMnyKei + ", ");
		sb.append("prsHacRui : " + prsHacRui + ", ");
		sb.append("fukZaiSuu : " + fukZaiSuu + ", ");
		sb.append("fukSziSuu : " + fukSziSuu + ", ");
		sb.append("fukMnyKei : " + fukMnyKei + ", ");
		sb.append("fukHacRui : " + fukHacRui + ", ");
		sb.append("prsNyoKei : " + prsNyoKei + ", ");
		sb.append("fukNyoKei : " + fukNyoKei + ", ");
		sb.append("teianSuu : " + teianSuu + ", ");
		sb.append("fskZaiSuu : " + fskZaiSuu + ", ");
		sb.append("kanouSuu : " + kanouSuu + ", ");
		sb.append("zaiNisuu : " + zaiNisuu + ", ");
		sb.append("prsHacSuu : " + prsHacSuu + ", ");
		sb.append("prsNki : " + prsNki + ", ");
		sb.append("fukHacSuu : " + fukHacSuu + ", ");
		sb.append("fukNki : " + fukNki + ", ");
		sb.append("choksoKbn     : " + choksoKbn + ", ");
		sb.append("hacZmiSgn : " + hacZmiSgn + ", ");
		sb.append("prsHacSuu1 : " + prsHacSuu1 + ", ");
		sb.append("prsHacNyo1 : " + prsHacNyo1 + ", ");
		sb.append("prsHacNki1 : " + prsHacNki1 + ", ");
		sb.append("prsKbn1 : " + prsKbn1 + ", ");
		sb.append("prsHacSuu2 : " + prsHacSuu2 + ", ");
		sb.append("prsHacNyo2 : " + prsHacNyo2 + ", ");
		sb.append("prsHacNki2 : " + prsHacNki2 + ", ");
		sb.append("prsKbn2 : " + prsKbn2 + ", ");
		sb.append("fukHacSuu1 : " + fukHacSuu1 + ", ");
		sb.append("fukHacNyo1 : " + fukHacNyo1 + ", ");
		sb.append("fukHacNki1 : " + fukHacNki1 + ", ");
		sb.append("fukKbn1 : " + fukKbn1 + ", ");
		sb.append("fukHacSuu2 : " + fukHacSuu2 + ", ");
		sb.append("fukHacNyo2 : " + fukHacNyo2 + ", ");
		sb.append("fukHacNki2 : " + fukHacNki2 + ", ");
		sb.append("fukKbn2    : " + fukKbn2    + ", ");
		sb.append("ksnNisuu : " + ksnNisuu + ", ");
		sb.append("ssnRedTim : " + ssnRedTim + ", ");
		sb.append("mrmSuu : " + mrmSuu + ", ");
		sb.append("fukSziHacSaki : " + fukSziHacSaki + ", ");
		sb.append("fukSziNm : " + fukSziNm + ", ");
		sb.append("fukSziCod : " + fukSziCod + ", ");
		sb.append("fukSziCod_arr : " + fukSziCod_arr + ", ");
		sb.append("fukSziHacSaki_arr : " + fukSziHacSaki_arr + ", ");
		sb.append("bunCod_arr : " + bunCod_arr + ", ");
		sb.append("fukSziNm_arr : " + fukSziNm_arr + ", ");
		sb.append("fukSziPtn : " + fukSziPtn + ", ");
		sb.append("check_prs1 : " + check_prs1 + ", ");
		sb.append("check_prs2 : " + check_prs2 + ", ");
		sb.append("check_fuk1 : " + check_fuk1 + ", ");
		sb.append("check_flg : " + check_flg + ", ");
		sb.append("check_sgn : " + check_sgn + ", ");
		sb.append("check_prsHacSuu : " + check_prsHacSuu + ", ");
		sb.append("updDte : " + updDte + ", ");
		sb.append("updJkk : " + updJkk + ", ");
		sb.append("hacNm : " + hacNm + ", ");
		sb.append("basedRowFlg : " + basedRowFlg + ", ");
		sb.append("bunCod : " + bunCod + ", ");
		sb.append("prsVOs : " + prsVOs + ", ");
		sb.append("nhnCod : " + nhnCod + ", ");
		sb.append("nhnMei : " + nhnMei);

		return sb.toString();
	}

}

