/**
* SubMateStockVO  �����ލ݌Ƀ����e�i���X �o�����[�I�u�W�F�N�g�N���X
*
*	�쐬��    2003/08/06
*	�쐬��    �i�m�h�h�j����  ����
* �����T�v    ��ʂ���擾�����f�[�^���i�[����N���X�B
*
*	[�ύX����]
*	�ύX��    2003/09/18
*	�ύX��    �i�m�h�h�j����  ���� 
*      �ύX���e  �T���v���敪��get/set�ǉ�
*/

package com.jds.prodord.master.submatestock;
import java.util.*;

public class SubMateStockVO {
 	private String daiKaiSkbCod = "";
	private String queryKaiSkbCod = "";
	private ArrayList queryKaiSkbCodList = null;
	private String kaiSkbCod = new String();
	private String smpKbn = "";	 //2003/09/18

	private String hidDaiKaiSkbCod = new String();
	private ArrayList kigoBan_arr = null;
	private String kigoBan = new String();
	private String hjiHnb = new String();
	private String huksizaiCod = new String();
	private String huksizaiMei = new String();
	private String huksizaiZaiko = new String();
	private String teiseiSuu = new String();

	private int hidUpdDte;
	private int hidUpdJkk;

	private boolean flag = false;

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

    

    
//-----  ���̓G���A  -----------------------------------------

	//***  ��Ў��ʃR�[�h��get/set  ***
    public String getKaiSkbCod() {
        return this.kaiSkbCod;
    }
    public void setKaiSkbCod(String kaiSkbCod) {
        this.kaiSkbCod = kaiSkbCod;
    }

	//*** Gets the hidDaiKaiSkbCod  ***
	public String getHidDaiKaiSkbCod() {
		return hidDaiKaiSkbCod;
	}
	public void setHidDaiKaiSkbCod(String hidDaiKaiSkbCod) {
		this.hidDaiKaiSkbCod = hidDaiKaiSkbCod;
	}

	//***  �T���v���敪��get/set  *** //2003/09/18
	public String getSmpKbn() {
		return smpKbn;
	}
	public void setSmpKbn(String smpKbn) {
		this.smpKbn = smpKbn;
	}

	//***  �L���ԍ�_arr��get/set  ***
    public ArrayList getKigoBan_arr() {
        return this.kigoBan_arr;
    }
    public void setKigoBan_arr(ArrayList kigoBan_arr) {
        this.kigoBan_arr = kigoBan_arr;
    }

	//***  �L���ԍ���get/set  ***
    public String getKigoBan() {
        return this.kigoBan;
    }
    public void setKigoBan(String kigoBan) {
        this.kigoBan = kigoBan;
    }

	//***  �\���i�Ԃ�get/set  ***
    public String getHjiHnb() {
        return this.hjiHnb;
    }
    public void setHjiHnb(String hjiHnb) {
        this.hjiHnb = hjiHnb;
    }
	//***  �����ރR�[�h��get/set  ***
	public String getHuksizaiCod() {
		return huksizaiCod;
	}
	public void setHuksizaiCod(String huksizaiCod) {
		this.huksizaiCod = huksizaiCod;
	}

	//***  �����ޖ��̂�get/set  ***
	public String getHuksizaiMei() {
		return huksizaiMei;
	}
	public void setHuksizaiMei(String huksizaiMei) {
		this.huksizaiMei = huksizaiMei;
	}


	//***  �����ލ݌ɐ���get/set  ***
	public String getHuksizaiZaiko() {
		return huksizaiZaiko;
	}
	public void setHuksizaiZaiko(String huksizaiZaiko) {
		this.huksizaiZaiko = huksizaiZaiko;
	}

	//***  ��������get/set  ***
	public String getTeiseiSuu() {
		return teiseiSuu;
	}
	public void setTeiseiSuu(String teiseiSuu) {
		this.teiseiSuu = teiseiSuu;
	} 	//***  Gets the updDte  ***
	public int getHidUpdDte() {
		return hidUpdDte;
	}
	public void setHidUpdDte(int hidUpdDte) {
		this.hidUpdDte = hidUpdDte;
	}

	//***  Gets the updJkk  ***
	public int getHidUpdJkk() {
		return hidUpdJkk;
	}
	public void setHidUpdJkk(int hidUpdJkk) {
		this.hidUpdJkk = hidUpdJkk;
	}

	/**
	 * Gets the flag
	 * @return Returns a boolean
	 */
	public boolean getFlag() {
		return flag;
	}
	/**
	 * Sets the flag
	 * @param flag The flag to set
	 */
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
 }
