/**
* PurchaseOrderForm  �d��������}�X�^�[�Ɖ�t�H�[���N���X
*
*	�쐬��    2003/05/19
*	�쐬��    �i�m�h�h�j���� ����
* �����T�v    Http���N�G�X�g�ƃ��N�G�X�g�̏������ʂ��i�[����N���X�B
*/

package com.jds.prodord.master.purchaseorder;

import org.apache.struts.action.*;
import javax.servlet.http.*;
import java.util.*;
import com.jds.prodord.common.*;

public class PurchaseOrderForm extends ActionForm {

	public static final String COMMAND_JITTKOU = "���s";
	public static final String COMMAND_CLEAR = "�N���A";
	
	private String command = "";
	private String daiKaiSkbCod = "";
	private String QueryDaiKaiSkbCod = "";

	private String hattyuCod = "";
	private String orderName1 = "";
	private String orderName2 = "";
	private String orderAdr1 = "";
	private String orderAdr2 = "";
	private String telNum = "";
	private String postNum = "";

	private ArrayList KaiSkbCodList = null;

//=============================================================================

	public PurchaseOrderForm(){
		super();
		this.setCommand("INIT");

	}

     //***  �{�^����get/set  ***
	public String getCommand(){
		return command;
	}
	public void setCommand(String s){
		command = s;
	}

     //***  ��\��Ў��ʃR�[�h��get/set  ***
    public String getDaiKaiSkbCod(){
    	return daiKaiSkbCod;
    }
    public void setDaiKaiSkbCod(String s){
    	daiKaiSkbCod = s;
    }
        
	//*** �N�G����\��Ў��ʃR�[�h��get/set  ***
	public String getQueryDaiKaiSkbCod() {
		return QueryDaiKaiSkbCod;
	}
	public void setQueryDaiKaiSkbCod(String queryDaiKaiSkbCod) {
		QueryDaiKaiSkbCod = queryDaiKaiSkbCod;
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
    public ArrayList getKaiSkbCodList(){
    	return KaiSkbCodList;
    }
    public void setKaiSkbCodList(ArrayList arr){
    	KaiSkbCodList = arr;
    }

//---------------------------------------------------------------------------------

		//���̓`�F�b�N

	public ActionErrors validate(ActionMapping mapping,HttpServletRequest req){
			HttpSession session = req.getSession();
			//���b�Z�[�W���Z�b�V���������菜��
			session.removeAttribute(CommonConst.INFOMSG_KEY);
		
			if(command.equals("INIT")){
				return null;
			}
				ActionErrors errors = new ActionErrors();
			if(command.equals(COMMAND_JITTKOU)){
				//�K�{
					if(hattyuCod.trim().equals("")){
							errors.add("",new ActionError("errors.input.required","������R�[�h"));
				}else {

					//���p�����̂�
					if(!StringUtils.isAscii(hattyuCod.trim()))
							errors.add("",new ActionError("errors.input.1","������R�[�h","���p�p��"));
			
				}
			}

		return errors.empty() ? null : errors;

	}

	public void reset(ActionMapping maping,HttpServletRequest req){
			this.command = "INIT";
	}


	//****  �l���N���A  ****
	public void clearAll(){
		command = "";

        this.hattyuCod = "";
        this.orderName1 = "";
        this.orderName2 = "";
        this.orderAdr1 = "";
        this.orderAdr2 = "";
	    this.telNum = "";
	    this.postNum = "";

  	}

	public void clearTableField1(){
		command = "";
	    hattyuCod = "";
	    orderName1 = "";
	    orderName2 = "";
	    orderAdr1 = "";
	    orderAdr2 = "";
	    telNum = "";
	    postNum = "";
	}


}