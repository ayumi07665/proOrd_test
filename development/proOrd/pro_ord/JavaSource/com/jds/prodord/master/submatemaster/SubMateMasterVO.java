/**
* SubMateMasterVO  �����ރ}�X�^�[�����e�i���X  �o�����[�I�u�W�F�N�g�N���X
*
*	�쐬��    2003/06/24
*	�쐬��    �i�m�h�h�j����  ����
* �����T�v    ��ʂ���擾�����f�[�^���i�[����N���X�B
*
*	[�ύX����]
*             ���t ���O
*  �E���e
*/

package com.jds.prodord.master.submatemaster;

import java.util.*;
public class SubMateMasterVO {

	private String daiKaiSkbCod = "";
	private String queryKaiSkbCod = "";
	private ArrayList queryKaiSkbCodList = null;
	
	private String kaiSkbCod = new String();
	private String hukSziCod = new String();

	private String hidDaiKaiSkbCod = new String();
	private String hidKaiSkbCod = new String();
	private String outHukSziCod = new String();
	private String outBunruiCod = new String();
	private String outHatcCod = new String();
	private String outHukSziMei = new String();
	
	private int hidUpdDte;
	private int hidUpdJkk;

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

    
//-----  ���̓G���A  -----------------------------------------

	//***  ��Ў��ʃR�[�h��get/set  ***
    public String getKaiSkbCod() {
        return this.kaiSkbCod;
    }
    public void setKaiSkbCod(String kaiSkbCod) {
        this.kaiSkbCod = kaiSkbCod;
    }

	//***  �����޺��ނ�get/set  ***
	public String getHukSziCod() {
		return hukSziCod;
	}
	public void setHukSziCod(String hukSziCod) {
		this.hukSziCod = hukSziCod;
	}

	//***  Gets the hidDaiKaiSkbCod  ***
	public String getHidDaiKaiSkbCod() {
		return hidDaiKaiSkbCod;
	}
	public void setHidDaiKaiSkbCod(String hidDaiKaiSkbCod) {
		this.hidDaiKaiSkbCod = hidDaiKaiSkbCod;
	}

	//***  Gets the hidKaiSkbCod  ***
	public String getHidKaiSkbCod() {
		return hidKaiSkbCod;
	}
	public void setHidKaiSkbCod(String hidKaiSkbCod) {
		this.hidKaiSkbCod = hidKaiSkbCod;
	}

	//*** Gets the out�����޺���  ***
	public String getOutHukSziCod() {
		return outHukSziCod;
	}
	public void setOutHukSziCod(String outHukSziCod) {
		this.outHukSziCod = outHukSziCod;
	}

	//***  Gets the out���ރR�[�h  ***
	public String getOutBunruiCod() {
		return outBunruiCod;
	}
	public void setOutBunruiCod(String outBunruiCod) {
		this.outBunruiCod = outBunruiCod;
	}

	//***  Gets the out��������  ***
	public String getOutHatcCod() {
		return outHatcCod;
	}
	public void setOutHatcCod(String outHatcCod) {
		this.outHatcCod = outHatcCod;
	}

	//***  Gets the out�����ޖ�  ***
	public String getOutHukSziMei() {
		return outHukSziMei;
	}
	public void setOutHukSziMei(String outHukSziMei) {
		this.outHukSziMei = outHukSziMei;
	}

	//***  Gets the hidUpdDte  ***
	public int getHidUpdDte() {
		return hidUpdDte;
	}
	public void setHidUpdDte(int hidUpdDte) {
		this.hidUpdDte = hidUpdDte;
	}

	//***  Gets the hidUpdJkk  ***
	public int getHidUpdJkk() {
		return hidUpdJkk;
	}
	public void setHidUpdJkk(int hidUpdJkk) {
		this.hidUpdJkk = hidUpdJkk;
	}

	//***  �X�V���t��get/set  ***
	public int getUpdDte() {
		return updDte;
	}
	public void setUpdDte(int updDte) {
		this.updDte = updDte;
	}

	//***  �X�V���Ԃ�get/set  ***
	public int getUpdJkk() {
		return updJkk;
	}
	public void setUpdJkk(int updJkk) {
		this.updJkk = updJkk;
	}


}
