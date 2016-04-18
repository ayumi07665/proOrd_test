/**
* IndicatePrintForm  �������o�͎w���t�H�[���N���X
*
*	�쐬��    2003/04/23
*	�쐬��    �i�m�h�h�j���c �Ĕ�
* �����T�v    Http���N�G�X�g�ƃ��N�G�X�g�̏������ʂ��i�[����N���X�B
*
*	[�ύX����]
*       2003/06/05�i�m�h�h�j�g�c �h�q
* 			�E�u�ďo�́v�̏������ځi���������t�A������R�[�h�j�ǉ��B
*       2003/07/16�i�m�h�h�j���c �Ĕ�
* 			�E���������tFrom�ɓ������t���Z�b�g���郁�\�b�h �ǉ��B
* 		2005/05/23�i�m�h�h�j�g�c
* 			�E�������ꊇ�o�͑Ή��i���[�U�[�h�c�̒ǉ��j
* 
*/

package com.jds.prodord.indicate.indicateprint;

import org.apache.struts.action.*;

import javax.servlet.http.*;
import java.util.*;
import com.jds.prodord.common.*;

import java.text.*;

public class IndicatePrintForm extends ActionForm {

	public static final String COMMAND_CLEAR = "�N���A";
	public static final String COMMAND_DENPYOHAKKOU = "�`�[���s";
	public static final String COMMAND_SAISYRYK = "�ďo��";
	
	public static final String SUCCESS_DENPYOHAKKOU = "SUCCESS_DENPYOHAKKOU";
	public static final String SUCCESS_SAISYRYK = "SUCCESS_SAISYRYK";
	
	public static final String DENPYOHAKKOU = "1";
	public static final String SAISYRYK = "2";
	
	public static final boolean SELECT_ALL = true;
	public static final boolean SELECT_SEL = false;
	
	private String command = "";
	private String newWindow = "";//����p��ʂ��J�����ǂ����̃t���O
	private String syoriFlg = "";//�R�}���h�ăZ�b�g�̂��߂̃t���O
	
	private boolean rb_select = true;
	
	private String hacCod1 = "";
	private String hacCod2 = "";
	private String hacCod3 = "";
	
	private String hacSyoDte_Frm_Y = "";
    private String hacSyoDte_Frm_M = "";
    private String hacSyoDte_Frm_D = "";
    private String hacSyoDte_to_Y = "";
    private String hacSyoDte_to_M = "";
    private String hacSyoDte_to_D = "";
    private String hacCod = "";

	private String hacSyoBng_frm = "";
	private String hacSyoBng_to = "";
	
	private String queryDaiKaiSkbCod = "";
	private String queryKaiSkbCod = "";
	private ArrayList queryKaiSkbCodList = null;
	private String usrId = "";//2005/05/23 add
	private String hyoKbn = "";//2011/05/30 add
	
	public IndicatePrintForm(){
		super();
		this.clearAll();
		this.setRb_select(SELECT_ALL);
		this.setCommand("INIT");
	}
		

	//-----------------------------------------------------------
	 //�������{�^��
	public String getCommand(){
		return command;
	}
	public void setCommand(String s){
		command = s;
	}
	/**
     * ����p��ʂ��J�����ǂ����̃t���O
     */
    public void setNewWindow(String s) {
        this.newWindow = s;
    }
    public String getNewWindow() {
        return this.newWindow;
    }
    /**
     * �R�}���h�ăZ�b�g�̂��߂̃t���O
     */
    public void setSyoriFlg(String s) {
        this.syoriFlg = s;
    }
    public String getSyoriFlg() {
        return this.syoriFlg;
    }
	//-----------------------------------------------------------
     //��\��Ў��ʃR�[�h
    public String getQueryDaiKaiSkbCod(){
    	return queryDaiKaiSkbCod;
    }
    public void setQueryDaiKaiSkbCod(String s){
    	queryDaiKaiSkbCod = s;
    }
    //------------------------------------------------------------
    //��Ў��ʃR�[�h
    public String getQueryKaiSkbCod(){
    	return queryKaiSkbCod;
    }
    public void setQueryKaiSkbCod(String s){
    	queryKaiSkbCod = s;
    }
    
    //��Ў��ʃR�[�h���X�g
    public ArrayList getQueryKaiSkbCodList(){
    	return queryKaiSkbCodList;
    }
    public void setQueryKaiSkbCodList(ArrayList arr){
    	queryKaiSkbCodList = arr;
    }
	
	//---------------------------------------------------------------------------------

	//2003/06/04
    /**
     * ���������t(�N)From�̃Z�b�g
     * 
     * @param hacSyoDte_Frm_Y ���������t(�N)From
     */
    public void setHacSyoDte_Frm_Y(String hacSyoDte_Frm_Y) {
        this.hacSyoDte_Frm_Y = hacSyoDte_Frm_Y;
    }
    /**
     * ���������t(�N)From�̎擾
     * 
     * @return ������(�N)From
     */
    public String getHacSyoDte_Frm_Y() {
        return this.hacSyoDte_Frm_Y;
    }
    /**
     * ���������t(��)From�̃Z�b�g
     * 
     * @param hacSyoDte_Frm_M ���������t(��)From
     */
    public void setHacSyoDte_Frm_M(String hacSyoDte_Frm_M) {
        this.hacSyoDte_Frm_M = hacSyoDte_Frm_M;
    }
    /**
     * ���������t(��)From�̎擾
     * 
     * @return ���������t(��)From
     */
    public String getHacSyoDte_Frm_M() {
        return this.hacSyoDte_Frm_M;
    }
    /**
     * ���������t(��)From�̃Z�b�g
     * 
     * @param hacSyoDte_Frm_D ������(��)From
     */
    public void setHacSyoDte_Frm_D(String hacSyoDte_Frm_D) {
        this.hacSyoDte_Frm_D = hacSyoDte_Frm_D;
    }
    /**
     * ���������t(��)From�̎擾
     * 
     * @return ���������t(��)From
     */
    public String getHacSyoDte_Frm_D() {
        return this.hacSyoDte_Frm_D;
    }
    /**
     * ���������t(�N)To�̃Z�b�g
     * 
     * @param queryHacDteTo_Y ���������t(�N)To
     */
    public void setHacSyoDte_to_Y(String hacSyoDte_to_Y) {
        this.hacSyoDte_to_Y = hacSyoDte_to_Y;
    }
    /**
     * ���������t(�N)To�̎擾
     * 
     * @return ���������t(�N)To
     */
    public String getHacSyoDte_to_Y() {
        return this.hacSyoDte_to_Y;
    }
    /**
     * ���������t(��)To�̃Z�b�g
     * 
     * @param queryHacDteTo_M ���������t(��)To
     */
    public void setHacSyoDte_to_M(String hacSyoDte_to_M) {
        this.hacSyoDte_to_M = hacSyoDte_to_M;
    }
    /**
     * ���������t(��)To�̎擾
     * 
     * @return ���������t(��)To
     */
    public String getHacSyoDte_to_M() {
        return this.hacSyoDte_to_M;
    }
    /**
     * ���������t(��)To�̃Z�b�g
     * 
     * @param queryHacDteTo_D ���������t(��)To
     */
    public void setHacSyoDte_to_D(String hacSyoDte_to_D) {
        this.hacSyoDte_to_D = hacSyoDte_to_D;
    }
    /**
     * ���������t(��)To�̎擾
     * 
     * @return ���������t(��)To
     */
    public String getHacSyoDte_to_D() {
        return this.hacSyoDte_to_D;
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
     * ���͗��I�����W�I�{�^���̃Z�b�g
     * 
     * @param sort_ketCod ���͗��I�����W�I�{�^��
     */
    public void setRb_select(boolean b) {

        this.rb_select = b;

    }
    /**
     * ���͗��I�����W�I�{�^���̎擾
     * 
     * @return ���͗��I�����W�I�{�^��
     */
    public boolean getRb_select() {

        return this.rb_select;

    }
    
    /**
     * ������R�[�h�P�̃Z�b�g
     * 
     * @param hacCod1 ������R�[�h�P
     */
    public void setHacCod1(String hacCod1) {

        this.hacCod1 = hacCod1;

    }


    /**
     * ������R�[�h�P�̎擾
     * 
     * @return ������R�[�h�P
     */
    public String getHacCod1() {

        return this.hacCod1;

    }


    /**
     * ������R�[�h�Q�̃Z�b�g
     * 
     * @param hacCod2 ������R�[�h�Q
     */
    public void setHacCod2(String hacCod2) {

        this.hacCod2 = hacCod2;

    }


    /**
     * ������R�[�h�Q�̎擾
     * 
     * @return ������R�[�h�Q
     */
    public String getHacCod2() {

        return this.hacCod2;

    }


    /**
     * ������R�[�h�R�̃Z�b�g
     * 
     * @param hacCod3 ������R�[�h�R
     */
    public void setHacCod3(String hacCod3) {

        this.hacCod3 = hacCod3;

    }


    /**
     * ������R�[�h�R�̎擾
     * 
     * @return ������R�[�h�R
     */
    public String getHacCod3() {

        return this.hacCod3;

    }


    /**
     * �������ԍ�From�̃Z�b�g
     * 
     * @param hacSyoBng_frm �������ԍ�From
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
	 * �\���敪�̎擾
	 * @return
	 */
	public String getHyoKbn() {
		return hyoKbn;
	}

	/**
	 * �\���敪�̃Z�b�g
	 * @param string
	 */
	public void setHyoKbn(String string) {
		hyoKbn = string;
	}


    //---------------------------------------------------------------------------------

	public void clearAll(){
		command = "";
		this.setRb_select(SELECT_ALL);
		this.hacCod1 = "";
		this.hacCod2 = "";
		this.hacCod3 = "";
	
		this.hacSyoBng_frm = "";
		this.hacSyoBng_to = "";

		this.hacSyoDte_Frm_Y = "";
    	this.hacSyoDte_Frm_M = "";
    	this.hacSyoDte_Frm_D = "";
    	this.hacSyoDte_to_Y = "";
    	this.hacSyoDte_to_M = "";
    	this.hacSyoDte_to_D = "";
    	this.hacCod = "";
  	}
	
	//---------------------------------------------------------------------------------

	public ActionErrors validate(ActionMapping mapping,HttpServletRequest req){
		
		if(command.equals("INIT"))
			return null;
		if(command.equals(COMMAND_CLEAR))
			return null;
			
		//�R�}���h�ăZ�b�g	
		if(syoriFlg.equals(DENPYOHAKKOU) && !command.equals(SUCCESS_DENPYOHAKKOU))
			this.command = SUCCESS_DENPYOHAKKOU;
		if(syoriFlg.equals(SAISYRYK) && !command.equals(SUCCESS_SAISYRYK))
			this.command = SUCCESS_SAISYRYK;

		ActionErrors errors = new ActionErrors();
		
		//�`�[���s�E�ďo�͂̂Ƃ�
		if(command.equals(COMMAND_DENPYOHAKKOU) || command.equals(COMMAND_SAISYRYK)){
			//�X�V�����`�F�b�N
			boolean authorityChecked = false;
			
			authorityChecked = CheckCommon.checkHyoKbn(this.getQueryDaiKaiSkbCod(),this.getHyoKbn());
			if(!authorityChecked){
				errors.add("",new ActionError("errors.cannot","���̃A�J�E���g��","�o�^�^�X�V�^�폜��"));
				return errors;	
			}
		}
		
		//�`�[���s�̂Ƃ�
		if(command.equals(COMMAND_DENPYOHAKKOU)){
			
			if(rb_select == SELECT_ALL && !this.hacCod_IsBlank()){
				errors.add("",new ActionError("errors.input.prohibited","������R�[�h"));
				return errors;
			}
			if(rb_select == SELECT_SEL){
				if(this.hacCod_IsBlank()){
					errors.add("",new ActionError("errors.input.required","������R�[�h"));
				}else{
					if(!hacCod1.trim().equals("")){
						if(!StringUtils.isAscii(hacCod1.trim()))
							errors.add("",new ActionError("errors.input.1","������R�[�h(1�Ԗ�)","���p"));
					}
					if(!hacCod2.trim().equals("")){
						if(!StringUtils.isAscii(hacCod2.trim()))
							errors.add("",new ActionError("errors.input.1","������R�[�h(2�Ԗ�)","���p"));
					}
					if(!hacCod3.trim().equals("")){
						if(!StringUtils.isAscii(hacCod3.trim()))
							errors.add("",new ActionError("errors.input.1","������R�[�h(3�Ԗ�)","���p"));
					}
				}
			}
		}
		//�ďo�͂̂Ƃ�
		if(command.equals(COMMAND_SAISYRYK)){
			DateUtils dateUtils = new DateUtils();
			int today = dateUtils.getDate6Int();
			
			boolean isAsciiDigitErr = false;
			if(!hacSyoBng_frm.trim().equals("")){
				if(!StringUtils.isAsciiDigit(hacSyoBng_frm.trim())){
					errors.add("",new ActionError("errors.input.1","�������ԍ�(From)","���p����"));
					isAsciiDigitErr = true;
				}
			}else{
				if(!hacSyoBng_to.trim().equals("")){
					errors.add("",new ActionError("errors.input.required","�������ԍ�(From)"));
				}
			}
			if(!hacSyoBng_to.trim().equals("")){
				if(!StringUtils.isAsciiDigit(hacSyoBng_to.trim())){
					errors.add("",new ActionError("errors.input.1","�������ԍ�(To)","���p����"));
					isAsciiDigitErr = true;
				}else if(!hacSyoBng_frm.trim().equals("") && !isAsciiDigitErr){
					int frm = Integer.parseInt(hacSyoBng_frm.trim());
					int to = Integer.parseInt(hacSyoBng_to.trim());
					if(frm > to)
						errors.add("",new ActionError("errors.input.1","�������ԍ�(To)","�������ԍ�(From)�ȍ~�̐���"));
				}
			}
				

			boolean hacDte_required = false;
	    	//From���ǂꂩ�u�����N
	    	if(hacSyoDte_Frm_Y.trim().equals("") || hacSyoDte_Frm_M.trim().equals("") || hacSyoDte_Frm_D.trim().equals("")){
				hacDte_required = true;
	    	    errors.add("",new ActionError("errors.input.required","���������t(From)"));
	    	}else{
	    		//���p
         		if((!StringUtils.isAscii(hacSyoDte_Frm_Y.trim()) && !hacSyoDte_Frm_Y.trim().equals("")) || 
	    		   (!StringUtils.isAscii(hacSyoDte_Frm_M.trim()) && !hacSyoDte_Frm_M.trim().equals("")) || 
        	       (!StringUtils.isAscii(hacSyoDte_Frm_D.trim()) && !hacSyoDte_Frm_D.trim().equals("")) ){
        				errors.add("",new ActionError("errors.input.1","���������t","���p"));
  	      		}else{

	    		//From���t�^�`�F�b�N
	    	   		if(!CheckCommon.validateAsDate(hacSyoDte_Frm_Y,hacSyoDte_Frm_M,hacSyoDte_Frm_D)){
						errors.add("",new ActionError("errors.input.1","���������t(From)","���t"));		
	    	   		}else{
						int hacdte_from = Integer.parseInt(hacSyoDte_Frm_Y.trim() + 
					   	 	StringUtils.lpad(hacSyoDte_Frm_M.trim(),2,"0") + 
						 	StringUtils.lpad(hacSyoDte_Frm_D.trim(),2,"0"));
							//�����ȍ~�w
				    		if(hacdte_from > today)
				    			errors.add("",new ActionError("errors.input.1","���������t","�����ȑO�̓��t"));
			   		}
        	    }
	        }
	        if(!(hacSyoDte_to_Y.trim().equals("") && hacSyoDte_to_M.trim().equals("") && hacSyoDte_to_D.trim().equals(""))){
	    		if(hacSyoDte_Frm_Y.trim().equals("") || hacSyoDte_Frm_M.trim().equals("") || hacSyoDte_Frm_D.trim().equals("")){
	    	    	if(!hacDte_required)
	    	    		errors.add("",new ActionError("errors.input.required","���������t(From)"));
	    		}
	    		if(hacSyoDte_to_Y.trim().equals("") || hacSyoDte_to_M.trim().equals("") || hacSyoDte_to_D.trim().equals("")){
	    	    	errors.add("",new ActionError("errors.input.required","���������t(To)"));
	    		}else{ 
	    		//To���t�^�`�F�b�N
	    	   		if(!CheckCommon.validateAsDate(hacSyoDte_to_Y,hacSyoDte_to_M,hacSyoDte_to_D)){
						errors.add("",new ActionError("errors.input.1","���������t(To)","���t"));		
	    	   		}else{
						int hacdte_to = Integer.parseInt(hacSyoDte_to_Y.trim() + 
					 	  	 StringUtils.lpad(hacSyoDte_to_M.trim(),2,"0") + 
							 StringUtils.lpad(hacSyoDte_to_D.trim(),2,"0"));
						//�����ȍ~�w
				  		if(hacdte_to > today)
				    		errors.add("",new ActionError("errors.input.1","���������t","�����ȑO�̓��t"));

						int hacdte_from = Integer.parseInt(hacSyoDte_Frm_Y.trim() + 
					   	 	StringUtils.lpad(hacSyoDte_Frm_M.trim(),2,"0") + 
						 	StringUtils.lpad(hacSyoDte_Frm_D.trim(),2,"0"));
						if(!hacSyoDte_Frm_Y.trim().equals("") && !hacSyoDte_Frm_M.trim().equals("") && !hacSyoDte_Frm_D.trim().equals("")){
							if(hacdte_to < hacdte_from)
				    			errors.add("",new ActionError("errors.input.incorrect","���������t�͈͎̔w��"));
						}		 	
			 		}
	     	    }
	        }

			/** ������R�[�h **/
			if(hacCod.trim().equals("")){
				errors.add("",new ActionError("errors.input.required","������R�[�h"));
			}else{
    			//���p�̂�
				if(!StringUtils.isAscii(hacCod.trim())){
					errors.add("",new ActionError("errors.input.1","������R�[�h","���p"));
				}
			}
		}
		return errors.empty() ? null : errors;		
	}
	
//---------------------------------------------------------------------------------------------------
	
	public boolean hacCod_IsBlank(){
		if(hacCod1.trim().equals("") && hacCod2.trim().equals("") && hacCod3.trim().equals(""))
			return true;
		return false;
	}
	
	public void setHacSyoDte_Frm_today(){
		Date sysdate = new Date();
		
		hacSyoDte_Frm_Y = (new SimpleDateFormat("yy")).format(sysdate);
		hacSyoDte_Frm_M = (new SimpleDateFormat("MM")).format(sysdate);
		hacSyoDte_Frm_D = (new SimpleDateFormat("dd")).format(sysdate);
	}
	
//---------------------------------------------------------------------------------------------------
	
	public void reset(ActionMapping maping,HttpServletRequest req){
		if(!this.command.equals(SUCCESS_DENPYOHAKKOU) 
		&& !this.command.equals(SUCCESS_SAISYRYK)){
			this.setNewWindow("0");
			this.setSyoriFlg("");
			this.command = "INIT";
		}
	}
	


}



