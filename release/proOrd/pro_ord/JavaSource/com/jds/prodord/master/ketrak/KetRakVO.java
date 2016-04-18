/**
* KetRakVO  �`�ԃ����N�ʏ����}�X�^�[�����e�i���X�o�����[�I�u�W�F�N�g�N���X
*
*	�쐬��    2003/05/02
*	�쐬��    �i�m�h�h�j���� ���G
* �����T�v    ��ʂ���擾�����f�[�^���i�[����N���X�B
*
*	[�ύX����]
*             ���t ���O
*  �E���e
*/

package com.jds.prodord.master.ketrak;

import java.util.*;
public class KetRakVO  {

	private String daiKaiSkbCod = "";
	private String queryKaiSkbCod = "";
	private ArrayList queryKaiSkbCodList = null;
	
	private String kaiSkbCod = new String();
	private String rank = new String();
	private String ketCod = new String();
	private String ssnRedTim = new String();
	private String minZaiSuu = new String();
	private String minRotSuu = new String();
	private String mrmSuu = new String();

	private int updDte;
	private int updJkk;
	

    //���[�U�[�̑�\��Ў��ʃR�[�h
    public String getDaiKaiSkbCod() {
        return this.daiKaiSkbCod;
    }
    public void setDaiKaiSkbCod(String daiKaiSkbCod) {
        this.daiKaiSkbCod = daiKaiSkbCod;
    }


    //���[�U�[�̉�Ў��ʃR�[�h
	public String getQueryKaiSkbCod() {
		return queryKaiSkbCod;
	}
	public void setQueryKaiSkbCod(String queryKaiSkbCod) {
		this.queryKaiSkbCod = queryKaiSkbCod;
	}

    
    //��Ў��ʃR�[�h���X�g
    public ArrayList getQueryKaiSkbCodList(){
    	return queryKaiSkbCodList;
    }
    public void setQueryKaiSkbCodList(ArrayList arr){
    	queryKaiSkbCodList = arr;
    }

    
//-------------------------------------------------------------���̓G���A

    /**
     * ��Ў��ʃR�[�h�̃Z�b�g
     * 
     * @param kaiSkbCod ��Ў��ʃR�[�h
     */
    public String getKaiSkbCod() {

        return this.kaiSkbCod;

    }
   
    /**
     * ��Ў��ʃR�[�h�̎擾
     * 
     * @return ��Ў��ʃR�[�h
     */
    public void setKaiSkbCod(String kaiSkbCod) {

        this.kaiSkbCod = kaiSkbCod;

    }

    /**
     * �����N�̃Z�b�g
     * 
     * @param �����N
     */
	public void setRank(String rank) {
		this.rank = rank;
	}
    /**
     * �����N�̎擾
     * 
     * @return �����N
     */
	public String getRank() {
		return rank;
	}

    /**
     * �`�ԃR�[�h�̃Z�b�g
     * 
     * @param ketCod �`�ԃR�[�h
     */
	public void setKetCod(String ketCod) {
		this.ketCod = ketCod;
	}
    /**
     * �`�ԃR�[�h�̎擾
     * 
     * @return �`�ԃR�[�h
     */
	public String getKetCod() {
		return ketCod;
	}


//-------------------------------------------------------------------------

	/**
	 * Gets the ssnRedTim
	 * @return Returns a String
	 */
	public String getSsnRedTim() {
		return ssnRedTim;
	}
	/**
	 * Sets the ssnRedTim
	 * @param ssnRedTim The ssnRedTim to set
	 */
	public void setSsnRedTim(String ssnRedTim) {
		this.ssnRedTim = ssnRedTim;
	}


	/**
	 * Gets the minZaiSuu
	 * @return Returns a String
	 */
	public String getMinZaiSuu() {
		return minZaiSuu;
	}
	/**
	 * Sets the minZaiSuu
	 * @param minZaiSuu The minZaiSuu to set
	 */
	public void setMinZaiSuu(String minZaiSuu) {
		this.minZaiSuu = minZaiSuu;
	}


	/**
	 * Gets the minRotSuu
	 * @return Returns a String
	 */
	public String getMinRotSuu() {
		return minRotSuu;
	}
	/**
	 * Sets the minRotSuu
	 * @param minRotSuu The minRotSuu to set
	 */
	public void setMinRotSuu(String minRotSuu) {
		this.minRotSuu = minRotSuu;
	}


	/**
	 * Gets the mrmSuu
	 * @return Returns a String
	 */
	public String getMrmSuu() {
		return mrmSuu;
	}
	/**
	 * Sets the mrmSuu
	 * @param mrmSuu The mrmSuu to set
	 */
	public void setMrmSuu(String mrmSuu) {
		this.mrmSuu = mrmSuu;
	}



//-----------------------------------------------------------------------
	/**
	 * Gets the updDte
	 * @return Returns a int
	 */
	public int getUpdDte() {
		return updDte;
	}
	/**
	 * Sets the updDte
	 * @param updDte The updDte to set
	 */
	public void setUpdDte(int updDte) {
		this.updDte = updDte;
	}

	/**
	 * Gets the updJkk
	 * @return Returns a int
	 */
	public int getUpdJkk() {
		return updJkk;
	}
	/**
	 * Sets the updJkk
	 * @param updJkk The updJkk to set
	 */
	public void setUpdJkk(int updJkk) {
		this.updJkk = updJkk;
	}

}

