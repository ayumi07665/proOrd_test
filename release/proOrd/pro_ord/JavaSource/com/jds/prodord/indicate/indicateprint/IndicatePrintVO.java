/**
* IndicatePrintVO  �������o�͎w���o�����[�I�u�W�F�N�g�N���X
*
*	�쐬��    2003/04/23
*	�쐬��    �i�m�h�h�j���c �Ĕ�
* �����T�v    ��ʂ���擾�����f�[�^���i�[����N���X�B
*
*	[�ύX����]
*       2003/06/05�i�m�h�h�j�g�c �h�q
* 			�E�u�ďo�́v�̏������ځi���������t�A������R�[�h�j�ǉ��B
* 		2005/05/25�i�m�h�h�j�g�c 
* 			�E�������ꊇ�o�͑Ή��i���[�U�[�h�c�̒ǉ��j
* 		2005/09/09�i�m�h�h�j�g�c
* 			�EVAP�Е����޺��ޕ����Ή��i�����ރR�[�h�̒ǉ��j
*/

package com.jds.prodord.indicate.indicateprint;

import java.util.*;
public class IndicatePrintVO {

	private String command = "";

	private String daiKaiSkbCod = "";
	private String kaiSkbCod = "";
	private ArrayList queryKaiSkbCodList = null;
		
	private String hacSyoBng_frm = "";
	private String hacSyoBng_to = "";
	private int hacSyoDte_frm = 0;
	private int hacSyoDte_to = 0;
	private String hacCod_K = "";

	private String syrSuu = "";
	private String seqNo = "";
	
	private ArrayList hacCod_arr = new ArrayList();

    private String hacSyoDte = "";
    private String hacSyoBng = "";
    private String gyoBng = "";
    private String hacCod = "";

    private String fukSziNm = "";//�����ޖ���
    
    private String usrId = "";
	private String fukSziCod = "";
    
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
     * @param daiKaiSkbCod �\��Ў��ʃR�[�h
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
    
    //��Ў��ʃR�[�h���X�g
    public ArrayList getQueryKaiSkbCodList(){
    	return queryKaiSkbCodList;
    }
    public void setQueryKaiSkbCodList(ArrayList arr){
    	queryKaiSkbCodList = arr;
    }
    

//--------------------------------------------------------------------------

    /**
     * �������ԍ�From�̃Z�b�g
     * 
     * @param hacSyoBngfrm �������ԍ�From
     */
    public void setHacSyoBng_frm(String hacSyoBng_frm) {

        this.hacSyoBng_frm = hacSyoBng_frm;

    }


    /**
     * �������ԍ�From�̎擾
     * 
     * @return �������ԍ�From
     */
    public String getHacSyoBng_frm() {

        return this.hacSyoBng_frm;

    }


    /**
     * �������ԍ�To�̃Z�b�g
     * 
     * @param hacSyoBng_to �������ԍ�To
     */
    public void setHacSyoBng_to(String hacSyoBng_to) {

        this.hacSyoBng_to = hacSyoBng_to;

    }


    /**
     * �������ԍ�To�̎擾
     * 
     * @return �������ԍ�To
     */
    public String getHacSyoBng_to() {

        return this.hacSyoBng_to;

    }


    /**
     * ������R�[�harr�̃Z�b�g
     * 
     * @param hacCod_arr ������R�[�harr
     */
    public void setHacCod_arr(ArrayList hacCod_arr) {

        this.hacCod_arr = hacCod_arr;

    }


    /**
     * ������R�[�harr�̎擾
     * 
     * @return ������R�[�harr
     */
    public ArrayList getHacCod_arr() {

        return this.hacCod_arr;

    }
    
        /**
     * ������R�[�h�̃Z�b�g
     * 
     * @param hacCod_K ������R�[�h
     */
    public void setHacCod_K(String hacCod_K) {

        this.hacCod_K = hacCod_K;

    }


    /**
     * ������R�[�h�̎擾
     * 
     * @return ������R�[�h
     */
    public String getHacCod_K() {

        return this.hacCod_K;

    }
    
    
    /**
     * ������From�̃Z�b�g
     * 
     * @param hacSyoDte_frm ������To
     */
    public void setHacSyoDte_frm(int hacSyoDte_frm) {
        this.hacSyoDte_frm = hacSyoDte_frm;
    }
    public void setHacSyoDte_frm(String year,String month,String day){
		if(year.trim().equals("") || month.trim().equals("") || day.trim().equals(""))
			hacSyoDte_frm = 0;
		else
			hacSyoDte_frm = (Integer.parseInt(year,10)*10000) + (Integer.parseInt(month,10)*100) + Integer.parseInt(day,10);
	}
    /**
     * ������Frm�̎擾
     * 
     * @return ������Frm
     */
    public String getHacSyoDte_frm() {
        return (this.hacSyoDte_frm == 0)? "" : this.hacSyoDte_frm+"";
    }

    /**
     * ������To�̃Z�b�g
     * 
     * @param hacSyoDteTo_H ������To
     */
    public void setHacSyoDte_to(int hacSyoDte_to) {
        this.hacSyoDte_to = hacSyoDte_to;
    }
	public void setHacSyoDte_to(String year,String month,String day){
		if(year.trim().equals("") || month.trim().equals("") || day.trim().equals(""))
			hacSyoDte_to = 0;
		else
			hacSyoDte_to = (Integer.parseInt(year,10)*10000) + (Integer.parseInt(month,10)*100) + Integer.parseInt(day,10);
	}
    /**
     * ������To�̎擾
     * 
     * @return ������To
     */
    public String getHacSyoDte_to() {
        return (this.hacSyoDte_to == 0)? "" : this.hacSyoDte_to+"";
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

//---------------------------------------------------------------------------------------

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

	/**
	 * �����ރR�[�h�̎擾
	 * @return
	 */
	public String getFukSziCod() {
		return fukSziCod;
	}

	/**
	 * �����ރR�[�h�̐ݒ�
	 * @param string
	 */
	public void setFukSziCod(String string) {
		fukSziCod = string;
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
		sb.append(toString(hacSyoBng_frm, "hacSyoBng_frm") + delim);
		sb.append(toString(hacSyoBng_to, "hacSyoBng_to") + delim);
		sb.append(toString(String.valueOf(hacSyoDte_frm), "hacSyoDte_frm") + delim);
		sb.append(toString(String.valueOf(hacSyoDte_to), "hacSyoDte_to") + delim);
		sb.append(toString(hacCod_K, "hacCod_K") + delim);
		sb.append(toString(syrSuu, "syrSuu") + delim);  
		sb.append(toString(seqNo, "seqNo") + delim);

		sb.append(toString(hacSyoDte, "hacSyoDte") + delim);
		sb.append(toString(hacSyoBng, "hacSyoBng") + delim);
		sb.append(toString(gyoBng, "gyoBng") + delim);
		sb.append(toString(hacCod, "hacCod") + delim);
		sb.append(toString(fukSziNm, "fukSziNm") + delim); 
		sb.append(toString(usrId, "usrId") + delim);
		sb.append(toString(fukSziCod, "fukSziCod") + delim);  
		
		return sb.toString();
	}
	private String toString(Object o, String prmNm){
		return prmNm + "[" + o + "]";
	}


}

