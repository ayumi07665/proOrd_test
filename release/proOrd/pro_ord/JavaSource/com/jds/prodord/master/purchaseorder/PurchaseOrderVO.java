/**
* PurchaseOrderVO  �d��������}�X�^�[�Ɖ�o�����[�I�u�W�F�N�g�N���X
*
*	�쐬��    2002/05/19
*	�쐬��    �i�m�h�h�j����  ���� 
* �����T�v    �d��������}�X�^�[�Ɖ��擾�����f�[�^���i�[����N���X�B
*
*/

package com.jds.prodord.master.purchaseorder;

import java.util.*;

public class PurchaseOrderVO  {

	private String DaiKaiSkbCod = "";
	private String queryDaiKaiSkbCod = "";

	private String hattyuCod = "";
	private String orderName1 = "";
	private String orderName2 = "";
	private String orderAdr1 = "";
	private String orderAdr2 = "";
	private String telNum = "";
	private String postNum = "";

	private ArrayList KaiSkbCodList = null;
	private ArrayList KaiSkbCod_arr = null;
	
	private boolean finded_flg = true;
	
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


     //***  ������R�[�h��get/set  ***
	public String getHattyuCod(){
		return hattyuCod;
	}
	public void setHattyuCod(String s){
		hattyuCod = s;
	}

     //***  �����於�̂P��get/set  ***
	public String getOrderName1(){
		return orderName1;
	}
	public void setOrderName1(String s){
		orderName1 = s;
	}

     //***  �����於�̂Q��get/set  ***
	public String getOrderName2(){
		return orderName2;
	}
	public void setOrderName2(String s){
		orderName2 = s;
	}

     //***  ������Z���P��get/set  ***
	public String getOrderAdr1(){
		return orderAdr1;
	}
	public void setOrderAdr1(String s){
		orderAdr1 = s;
	}

     //***  ������Z���Q��get/set  ***
	public String getOrderAdr2(){
		return orderAdr2;
	}
	public void setOrderAdr2(String s){
		orderAdr2 = s;
	}

     //***  �d�b�ԍ���get/set  ***
	public String getTelNum(){
		return telNum;
	}
	public void setTelNum(String s){
		telNum = s;
	}

     //***  �X�֔ԍ���get/set  ***
	public String getPostNum(){
		return postNum;
	}
	public void setPostNum(String s){
		postNum = s;
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


	//�������� ����or�Ȃ� �t���O	
	public boolean getFinded_flg(){
		return finded_flg;
	}	
	public void setFinded_flg(boolean b){
		finded_flg = b;
	}
}