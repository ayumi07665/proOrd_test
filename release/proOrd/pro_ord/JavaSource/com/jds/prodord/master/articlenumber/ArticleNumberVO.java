/**
* ArticleNumberVO  �i�ԃ}�X�^�[�����e�i���X�o�����[�I�u�W�F�N�g�N���X
*
*	�쐬��    2002/08/25
*	�쐬��    �i�m�h�h�j����  ���� 
* �����T�v    �{�ԃ}�X�^�[�Ɖ��擾�����f�[�^���i�[����N���X�B
*
*        2003/09/11�i�m�h�h�j����  ����
* 			�E�����敪�̃Q�b�^�[�E�Z�b�^�[��ǉ��B
*
*/

package com.jds.prodord.master.articlenumber;

import java.util.*;

public class ArticleNumberVO  {

	private String DaiKaiSkbCod = "";
	private String queryDaiKaiSkbCod = "";
	private String kaiSkbCod = "";
	private String queryKaiSkbCod = "";
	private ArrayList queryKaiSkbCodList = null;

	private String prcKbn = "";
	private String kigoBan = "";
	private String artist = "";
	private String title = "";
	private String ketCod = "";
	private ArrayList hbiDte_arr = new ArrayList();
	private String hbiDte = ""; 
	private String prsMkrCod = "";
	private String jytPrsMkr = "";
	private String hukSizCod = "";

	private ArrayList KaiSkbCodList = null;
	private ArrayList KaiSkbCod_arr = null;

	private int upddte;
	private int updjkk;	

	private boolean find_flag = false;
	private String dbName = "";

//	private String hatc_KaiSkbCod = "";
//	private String hatc_HatcCod = "";
	
//	private int hidUpdDte;
//	private int hidUpdJkk;

//=================================================================================


    //***  ��\��Ў��ʃR�[�h��set/get  ***
	public String getDaiKaiSkbCod(){
		return DaiKaiSkbCod;
	}	
	public void setDaiKaiSkbCod(String s){
		DaiKaiSkbCod = s;
	}	

    //***  �N�G����\��Ў��ʃR�[�h��get/set  ***
    public String getQueryDaiKaiSkbCod(){
    	return queryDaiKaiSkbCod;
    }
    public void setQueryDaiKaiSkbCod(String s){
    	queryDaiKaiSkbCod = s;
    }
        
    //***  �L���ԍ���get/set  ***
    public String getKigoBan(){
    	return kigoBan;
    }
    public void setKigoBan(String s){
    	kigoBan = s;
    }

    //***  �A�[�e�B�X�g��get/set  ***
    public String getArtist(){
    	return artist;
    }
    public void setArtist(String s){
    	artist = s;
    } 
    
    //***  �^�C�g����get/set  ***
    public String getTitle(){
    	return title;
    }
    public void setTitle(String s){
    	title = s;
    } 

    //***  �`�ԃR�[�h��get/set  ***
    public String getKetCod(){
    	return ketCod;
    }
    public void setKetCod(String s){
    	ketCod = s;
    } 


    //***  ��������get/set  ***
	public String getHbiDte() {
		return hbiDte;
	}
	public void setHbiDte(String s){
		hbiDte = s;
	}

    //***  �v���X���[�J�[�R�[�h��get/set  ***
    public String getPrsMkrCod(){
    	return prsMkrCod;
    }
    public void setPrsMkrCod(String s){
    	this.prsMkrCod = s;
    }
    //***  ����v���X���[�J�[�R�[�h��get/set  ***
    public String getJytPrsMkr(){
    	return jytPrsMkr;
    }
    public void setJytPrsMkr(String s){
    	this.jytPrsMkr = s;
    } 

  
    //***  �����ޔ�����R�[�h��get/set  ***
    public String getHukSizCod(){
    	return hukSizCod;
    }
    public void setHukSizCod(String s){
    	hukSizCod = s;
    }

    //***  ��Ў��ʃR�[�h���X�g��get/set  ***
    public ArrayList getKaiSkbCod_arr(){
    	return KaiSkbCod_arr;
    }
    public void setKaiSkbCod_arr(ArrayList arr){
    	KaiSkbCod_arr = arr;
    }

    //***  ��Ў��ʃR�[�h���X�g��get/set  ***
    public ArrayList getKaiSkbCodList(){
    	return KaiSkbCodList;
    }
    public void setKaiSkbCodList(ArrayList arr){
    	KaiSkbCodList = arr;
    }

	//�������ʔ���t���O false:���� true:�Ȃ�
	public boolean getFind_flag(){
		return find_flag;
	}	
	public void setFind_flag(boolean b){
		find_flag = b;
	}

	//DB�t���O false:FTBHIN01 true:FTBHIN02	
	public String getDbName(){
		return dbName;
	}	
	public void setDbName(String s){
			dbName = s;
	}
	//***  queryKaiSkbCod  ***
	public String getQueryKaiSkbCod() {
		return queryKaiSkbCod;
	}
	public void setQueryKaiSkbCod(String queryKaiSkbCod) {
		this.queryKaiSkbCod = queryKaiSkbCod;
	}

	//***  kaiSkbCod��get/set  ***
	public String getKaiSkbCod() {
		return kaiSkbCod;
	}
	public void setKaiSkbCod(String kaiSkbCod) {
		this.kaiSkbCod = kaiSkbCod;
	}


	//***  �N�G����Ў��ʃR�[�h���X�g��get/set  ***
	public ArrayList getQueryKaiSkbCodList() {
		return queryKaiSkbCodList;
	}
	public void setQueryKaiSkbCodList(ArrayList arr) {
		queryKaiSkbCodList = arr;
	}

	//***  upddte��get/set  ***
	public int getUpddte() {
		return upddte;
	}
	public void setUpddte(int upddte) {
		this.upddte = upddte;
	}

	//***  updjkk��get/set  ***
	public int getUpdjkk() {
		return updjkk;
	}
	public void setUpdjkk(int updjkk) {
		this.updjkk = updjkk;
	}

//
//	//***  Gets the hatc_KaiSkbCod  ***
//	public String getHatc_KaiSkbCod() {
//		return hatc_KaiSkbCod;
//	}
//	public void setHatc_KaiSkbCod(String hatc_KaiSkbCod) {
//		this.hatc_KaiSkbCod = hatc_KaiSkbCod;
//	}
//
//	//***  Gets the hatc_HatcCod	 ***
//	public String getHatc_HatcCod() {
//		return hatc_HatcCod;
//	}
//	public void setHatc_HatcCod(String hatc_HatcCod) {
//		this.hatc_HatcCod = hatc_HatcCod;
//	}
//
//	/**
//	 * Gets the hidUpdDte
//	 * @return Returns a int
//	 */
//	public int getHidUpdDte() {
//		return hidUpdDte;
//	}
//	/**
//	 * Sets the hidUpdDte
//	 * @param hidUpdDte The hidUpdDte to set
//	 */
//	public void setHidUpdDte(int hidUpdDte) {
//		this.hidUpdDte = hidUpdDte;
//	}
//
//
//	/**
//	 * Gets the hidUpdJkk
//	 * @return Returns a int
//	 */
//	public int getHidUpdJkk() {
//		return hidUpdJkk;
//	}
//	/**
//	 * Sets the hidUpdJkk
//	 * @param hidUpdJkk The hidUpdJkk to set
//	 */
//	public void setHidUpdJkk(int hidUpdJkk) {
//		this.hidUpdJkk = hidUpdJkk;
//	}
	//**  �����敪��get/set  ***
	public String getPrcKbn() {
		return prcKbn;
	}
	public void setPrcKbn(String prcKbn) {
		this.prcKbn = prcKbn;
	}

}