/**
* PrsOrderVO  �v���X�E�����ޔ�����ʃo�����[�I�u�W�F�N�g�N���X
*
*	�쐬��    2003/04/28
*	�쐬��    �i�m�h�h�j���c �Ĕ�
* �����T�v    �i�ԃ}�X�^�[(FTBHIN01)�A�݌Ƀe�[�u��(FTBZAI01)�A���������e�[�u��(FTBHAC02)�A
* 			 ������}�X�^�[(FTBHAC03)�����ރ}�X�^�[(FTBFUK01)����擾�����f�[�^���i�[����N���X�B
*
*	[�ύX����]
*        2003/05/12 �i�m�h�h�j�g�c �h�q
* 			�Eindex,�o�͍σT�C��,�����σt���O �̒ǉ��B
* 		 2003/06/30
* 			�E�[�i��R�[�h �ǉ��B
* 		 2004/02/25�@(�m�h�h�j�X
* 			�E�����ރR�[�h�E�d����̓W�J���p�^�[���}�X�^����t���\���}�X�^�ɕύX
* 		 2004/03/29 (NII) �X
* 			�E�i�ԃ}�X�^�̃e�[�u�����ύX
* 		 2004/04/02 (NII) �X
* 			�E�����ޔ����̏C��(���ރR�[�h�P�������݂ɑΉ�)
* 		 2004/04/02 (NII) �X
* 			�E�����ޔ����̏C��(���ރR�[�h�P�������݂ɑΉ�)
* 		 2004/04/21 (NII)�X
* 			�E�ō��艿�\���ɔ����C��
*		 2005/02/03�i�m�h�h�j�g�c
*			�E�ꏊ�R�[�h�̒ǉ�
*		 2005/05/25�i�m�h�h�j�g�c
*			�E���[�U�[�h�c�̒ǉ�
*		 2007/12/25�i�m�h�h�j�c��
*			�E�}�j���A�������w���ꊇ�\�t�@���l�Q�ǉ��Ή�
* 
*/
package com.jds.prodord.order.prsorder;

import java.util.*;
import com.jds.prodord.common.*;

public class PrsOrderVO implements CommonOrderVO{
	
	/**
     * �O�̃y�[�W�i�ǂ̃y�[�W����J�ڂ��Ă������j
     */
    private String pre_page = "";
	
	/**
     * ����
     */
    private int count = 0;
        
    /**
     * ��\��Ў��ʃR�[�h
     */
    private String daiKaiSkbCod = "";
    
    /**
     * ��Ў��ʃR�[�h
     */
    private String kaiSkbCod = "";
    
    /**
     * ��Ў��ʃR�[�h�i���[�U�[�j
     */
    private String queryKaiSkbCod = "";
    
    /**
     * �ꏊ�R�[�h
     */
    private String bshCod = "";
//--------------------------------------------------------

	
	/**
     * �i�ԃ}�X�^�[(FTBHIN01)���݃t���O
     */
    private boolean exsitHin01 = false;
	
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

    
//-------------------------------------------------------------------------------------

	/**
     * �敪
     */
    private String kbn = "";   

//--������}�X�^�[(FTBMST03)------------------------------------------------------------

    /**
     * �����於��
     */
    private String hacNm = "";  
      
       
//--�݌Ƀe�[�u��(FTBZAI01)--------------------------------------------------------------

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
     * �v���X������
     */
    private String prsHacSuu = "";
    /**
     * �v���X�[��
     */
    private int prsNki = 0;
    /**
     * �����敪
     */
    private String choksoKbn = "";  
    /**
     * �R�����g
     */
    private String comment;  
    /**
     * �[�i�於
     */
    private String nhnMei;  
     
//--���������e�[�u��(FTBHAC02)--------------------------------------------------------------

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
     * �����ޔ[���Q
     */
    private String fukHacNki2 = "";
    /**
     * �����ދ敪�Q
     */
    private String fukKbn2 = "";
//    /**
//     * �o�͍σT�C��
//     */
//    private String syrZmiSgn = "";
     /**
     * �o�͍σT�C��
     */
    private int syrZmiSgn_prs = 0;
     /**
     * �o�͍σT�C��
     */
    private int syrZmiSgn_fuk = 0;
    /**
     * ���[�U�[�h�c
     */
    private String usrId = "";
    
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
     * �����ރp�^�[��
     */
    private String fukSziPtn = "";
    /**
	 * �����ރR�[�harr
	 */
	private ArrayList fukSziCod_arr = new ArrayList();
    /**
     * �����ރR�[�h
     */
    private String fukSziCod = "";
    /**
     * ��s�t���O
     */
    private String basedRowFlg = "";
	/**
	 * ���ރR�[�h
	 */
	private String bunCod = "";
	/**
	 * �����ޔ�����arr
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
     
//--�����ލ݌Ƀ}�X�^�[(FTBHIN12)--------------------------------------------------   
    
    /**
     * �����ސ�(HIN12)
     */
    private String fukSziSuu = "";
    
//-------------------------------------------------------------------------------------
    
    /**
     * �v���X�`�F�b�N�{�b�N�X1
     */
    private boolean check_prs1 = false;

    /**
     * �����ރ`�F�b�N�{�b�N�X1
     */
    private boolean check_fuk1 = false;
	

	private int updDte;
	private int updJkk;
	

	/**
	 * ��Đ��Ɖ��ʂł�index
	 */
	private int index = 0;
	
	/**
	 * �����σt���O
	 */
	private boolean hacFlg = false;
	
	/**
	 * �v���X������ʂ������ޔ�����ʂ��̃T�C��
	 */
	private String prs_FukSgn = "";
	
	//�[�i��R�[�h
	private String nhnCod ="";

	/**
	 * ���l�Q
	 */
	private String biKou2 = "";
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
//-----------------------------------------------------------------------------

	/**
     * �i�ԃ}�X�^�[(FTBHIN01)���݃t���O�̃Z�b�g
     * 
     * @param exsitZai01 �i�ԃ}�X�^�[(FTBHIN01)���݃t���O
     */
    public void setExsitHin01(boolean exsitHin01) {

        this.exsitHin01 = exsitHin01;

    }

    /**
     * �i�ԃ}�X�^�[(FTBHIN01)���݃t���O�̎擾
     * 
     * @return �i�ԃ}�X�^�[(FTBHIN01)���݃t���O
     */
    public boolean getExsitHin01() {

        return this.exsitHin01;

    }
    
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
     * @param hjiHnb �\���i��
     */
    public void setHjiHnb(String s) {

        this.hjiHnb = s;

    }

    /**
     * �\���i�Ԃ̎擾
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
	 * �ō����i
	 * @return�@zikTik�@�ō����i
	 */
	public String getZikTik() {
		return zikTik;
	}

	/**
	 * �ō����i
	 * @param zikTik �ō����i
	 */
	public void setZikTik(String zikTik) {
		this.zikTik = zikTik;
	}

    /**
     * �敪�̃Z�b�g
     * 
     * @param kbn �敪
     */
    public void setKbn(String kbn) {

        this.kbn = kbn;

    }

    /**
     * �敪�̎擾
     * 
     * @return �敪
     */
    public String getKbn() {

        return this.kbn;

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
    
    // ----------------------------------------------------------- Properties Right
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
	 * �[�i�於�̎擾
	 * 
	 * @return nhnMei
	 */
	public String getNhnMei() {
		
		return nhnMei;
		
	}
	/**
	 * �[�i�於�̃Z�b�g
	 * 
	 * @param nhnMei �[�i�於
	 */
	public void setNhnMei(String nhnMei) {
		
		this.nhnMei = nhnMei;
		
	}
	
//--------------------------------------------------------------------------
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
//------------------------------------------------------------------------------------------------------

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
	 * Gets the �����ރp�^�[��
	 * @return Returns a String
	 */
	public String getFukSziPtn() {
		return fukSziPtn;
	}
	/**
	 * Sets the �����ރp�^�[��
	 * @param fukSziPtn The fukSziPtn to set
	 */
	public void setFukSziPtn(String fukSziPtn) {
		this.fukSziPtn = fukSziPtn;
	}
	//2003/06/20	
	/**
     * �����ރR�[�harr�̃Z�b�g
     * 
     * @param fukSziCod_arr �����ރR�[�harr
     */
    public void setFukSziCod_arr(ArrayList fukSziCod_arr) {

        this.fukSziCod_arr = fukSziCod_arr;

    }
    /**
     * �����ރR�[�harr�̎擾
     * 
     * @return �����ރR�[�harr
     */
    public ArrayList getFukSziCod_arr() {

        return this.fukSziCod_arr;

    }
//2004.02.25 add 
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
     * �����ރR�[�h�̃Z�b�g
     * 
     * @param fukSziCod �����ރR�[�h
     */
    public void setFukSziCod(String fukSziCod) {

        this.fukSziCod = fukSziCod;

    }
	
	/**
     * �����ރR�[�h�̎擾
     * 
     * @return �����ރR�[�h
     */
    public String getFukSziCod() {

        return this.fukSziCod;

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
	 * ���ރR�[�h�̎擾
	 * @return bunCod
	 */
	public String getBunCod() {
		return bunCod;
	}

	/**
	 * ���ރR�[�h�̃Z�b�g
	 * @param bunCod
	 */
	public void setBunCod(String bunCod) {
		this.bunCod = bunCod;
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
	 * pre_page�̎擾
	 * 
	 * @return pre_page
	 */
	public String getPre_page() {
		
		return pre_page;
		
	}
	/**
	 * pre_page�̃Z�b�g
	 * 
	 * @param pre_page
	 */
	public void setPre_page(String pre_page) {
		
		this.pre_page = pre_page;
		
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
     * index�̃Z�b�g
     */
    public void setIndex(int i) {
        this.index = i;
    }
    /**
     * index�̎擾
     */
    public int getIndex() {
        return this.index;
    }
    
//    /**
//     * �o�͍σT�C���̃Z�b�g
//     */
//    public void setSyrZmiSgn(String syrZmiSgn) {
//        this.syrZmiSgn = syrZmiSgn;
//    }
//    /**
//     * �o�͍σT�C���̎擾
//     */
//    public String getSyrZmiSgn() {
//        return this.syrZmiSgn;
//    }
    
    /**
     * �o�͍σT�C���̃Z�b�g
     */
    public void setSyrZmiSgn_prs(int syrZmiSgn_prs) {
        this.syrZmiSgn_prs = syrZmiSgn_prs;
    }
    /**
     * �o�͍σT�C���̎擾
     */
    public int getSyrZmiSgn_prs() {
        return this.syrZmiSgn_prs;
    }
    
    /**
     * �o�͍σT�C���̃Z�b�g
     */
    public void setSyrZmiSgn_fuk(int syrZmiSgn_fuk) {
        this.syrZmiSgn_fuk = syrZmiSgn_fuk;
    }
    /**
     * �o�͍σT�C���̎擾
     */
    public int getSyrZmiSgn_fuk() {
        return this.syrZmiSgn_fuk;
    }
    /**
     * �����σt���O�̃Z�b�g
     */
    public void setHacFlg(boolean hacFlg) {
        this.hacFlg = hacFlg;
    }
    /**
     * �����σt���O�̎擾
     */
    public boolean getHacFlg() {
        return this.hacFlg;
    }
	
	/**
	 * �v���X������ʂ������ޔ�����ʂ��̃T�C���̎擾
	 * 
	 * @param prs_FukSgn
	 */
	public String getPrs_FukSgn() {
		
		return prs_FukSgn;
		
	}
	/**
	 * �v���X������ʂ������ޔ�����ʂ��̃T�C���̃Z�b�g
	 * 
	 * @param prs_FukSgn
	 */
	public void setPrs_FukSgn(String prs_FukSgn) {
		
		this.prs_FukSgn = prs_FukSgn;
		
	}

//2003/06/30
    /**
     * �[�i��R�[�h�̃Z�b�g
     * 
     * @param nhnCod �[�i��R�[�h
     */
    public void setNhnCod(String nhnCod) {

        this.nhnCod = nhnCod;

    }

    /**
     * �[�i��R�[�h�̎擾
     * 
     * @return �[�i��R�[�h
     */
    public String getNhnCod() {

        return this.nhnCod;

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
		String today = new DateUtils().getDate6();
		String smpKbn = DataFormatUtils.getSmpKbn(kbn, hbiDte, today);
		return smpKbn;
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
	 * ���[�U�[�h�c�擾
	 * @return
	 */
	public String getUsrId() {
		return usrId;
	}

	/**
	 * ���[�U�[�h�c�Z�b�g
	 * @param string
	 */
	public void setUsrId(String string) {
		usrId = string;
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

	
	/* (�� Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("pre_page : " + pre_page + ", ");
		sb.append("count : " + count + ", ");
		sb.append("daiKaiSkbCod : " + daiKaiSkbCod + ", ");
		sb.append("kaiSkbCod : " + kaiSkbCod + ", ");
		sb.append("queryKaiSkbCod : " + queryKaiSkbCod + ", ");
		sb.append("bshCod : " + bshCod + ", ");
		sb.append("exsitHin01 : " + exsitHin01 + ", ");
		sb.append("exsitZai01 : " + exsitZai01 + ", ");
		sb.append("exsitHac02Prs : " + exsitHac02Prs + ", ");
		sb.append("exsitHac02Fuk : " + exsitHac02Fuk + ", ");
		sb.append("exsitFuk01 : " + exsitFuk01 + ", ");
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
		sb.append("kbn : " + kbn + ", ");
		sb.append("hacNm : " + hacNm + ", ");
		sb.append("prsMnyKei : " + prsMnyKei + ", ");
		sb.append("prsHacRui : " + prsHacRui + ", ");
		sb.append("fukZaiSuu : " + fukZaiSuu + ", ");
		sb.append("fukMnyKei : " + fukMnyKei + ", ");
		sb.append("fukHacRui : " + fukHacRui + ", ");
		sb.append("prsNyoKei : " + prsNyoKei + ", ");
		sb.append("fukNyoKei : " + fukNyoKei + ", ");
		sb.append("prsHacSuu : " + prsHacSuu + ", ");
		sb.append("prsNki : " + prsNki + ", ");
		sb.append("choksoKbn : " + choksoKbn + ", ");
		sb.append("comment : " + comment + ", ");
		sb.append("nhnMei : " + nhnMei + ", ");
		sb.append("prsHacSuu1 : " + prsHacSuu1 + ", ");
		sb.append("prsHacNyo1 : " + prsHacNyo1 + ", ");
		sb.append("prsHacNki1 : " + prsHacNki1 + ", ");
		sb.append("prsKbn1 : " + prsKbn1 + ", ");
		sb.append("prsHacSuu2 : " + prsHacSuu2 + ", ");
		sb.append("prsHacNyo2 : " + prsHacNyo2 + ", ");
		sb.append("prsKbn2 : " + prsKbn2 + ", ");
		sb.append("fukHacSuu1 : " + fukHacSuu1 + ", ");
		sb.append("fukHacNki1 : " + fukHacNki1 + ", ");
		sb.append("fukKbn1 : " + fukKbn1 + ", ");
		sb.append("fukHacSuu2 : " + fukHacSuu2 + ", ");
		sb.append("fukHacNki2 : " + fukHacNki2 + ", ");
		sb.append("fukKbn2 : " + fukKbn2 + ", ");
		sb.append("syrZmiSgn_prs : " + syrZmiSgn_prs + ", ");
		sb.append("syrZmiSgn_fuk : " + syrZmiSgn_fuk + ", ");
		sb.append("usrId : " + usrId + ", ");
		sb.append("fukSziHacSaki : " + fukSziHacSaki + ", ");
		sb.append("fukSziNm : " + fukSziNm + ", ");
		sb.append("fukSziPtn : " + fukSziPtn + ", ");
		sb.append("fukSziCod_arr : " + fukSziCod_arr + ", ");
		sb.append("fukSziCod : " + fukSziCod + ", ");
		sb.append("basedRowFlg : " + basedRowFlg + ", ");
		sb.append("bunCod : " + bunCod + ", ");
		sb.append("fukSziHacSaki_arr : " + fukSziHacSaki_arr + ", ");
		sb.append("bunCod_arr : " + bunCod_arr + ", ");
		sb.append("fukSziNm_arr : " + fukSziNm_arr + ", ");
		sb.append("fukSziSuu : " + fukSziSuu + ", ");
		sb.append("check_prs1 : " + check_prs1 + ", ");
		sb.append("check_fuk1 : " + check_fuk1 + ", ");
		sb.append("updDte : " + updDte + ", ");
		sb.append("updJkk : " + updJkk + ", ");
		sb.append("index : " + index + ", ");
		sb.append("hacFlg : " + hacFlg + ", ");
		sb.append("prs_FukSgn : " + prs_FukSgn + ", ");
		sb.append("nhnCod : " + nhnCod + ", ");
		sb.append("hacFlg : " + hacFlg + ", ");
		sb.append("prs_FukSgn : " + prs_FukSgn + ", ");
		sb.append("nhnCod : " + nhnCod);

		return sb.toString();
	}
}

