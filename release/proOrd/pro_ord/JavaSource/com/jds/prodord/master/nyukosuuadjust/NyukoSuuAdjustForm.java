/**
* NyukoSuuAdjustForm  ���ɐ������t�H�[���N���X
*
*	�쐬��    2003/09/30
*	�쐬��    �i�m�h�h�j���c �Ĕ�
* �����T�v    Http���N�G�X�g�ƃ��N�G�X�g�̏������ʂ��i�[����N���X�B
*
*	[�ύX����]
*        2003/10/24�i�m�h�h�j���c �Ĕ�
* 			�E���������ɔ��������tFrm-To�A�[��Frm-To�A�L���ԍ��~10��ǉ�
* 		 2004/06/25�i�m�h�h�j�g�c
* 			�E�R�s�[���y�[�X�g�@�\�ǉ�
*/

package com.jds.prodord.master.nyukosuuadjust;

import org.apache.struts.action.*;

import javax.servlet.http.*;
import java.util.*;
import com.jds.prodord.common.*;

public class NyukoSuuAdjustForm extends ActionForm {
	
	private String command = "";
	private ArrayList rows = new ArrayList();	
	public static final int DEFAULT_ROW_COUNT = 1;
	
	private String prcKbn = "";
	public static final String COMMAND_CLEAR = "�N���A";
	public static final String COMMAND_PASTE = "�\��t��";//2004/06/25 add
	
	private String queryDaiKaiSkbCod = "";
	private String queryKaiSkbCod = "";
	private ArrayList queryKaiSkbCodList = null;

	private String hacCod_H = "";
	private FormDate hacDteFrm_H = new FormDate();
	private FormDate hacDteTo_H = new FormDate();
	private FormDate nkiFrm_H = new FormDate();
	private FormDate nkiTo_H = new FormDate();
	
	private String kigBng1_H = "";
	private String kigBng2_H = "";
	private String kigBng3_H = "";
	private String kigBng4_H = "";
	private String kigBng5_H = "";
	private String kigBng6_H = "";
	private String kigBng7_H = "";
	private String kigBng8_H = "";
	private String kigBng9_H = "";
	private String kigBng10_H = "";
	
	private String cpPaste = "";//2004/06/25 add
	private String hyoKbn = "";
	
	int count = 0;
	boolean updatable = false;
		
	public NyukoSuuAdjustForm(){
		super();
		this.clearAll();
		this.setSize(DEFAULT_ROW_COUNT);
		this.setCommand("INIT");
	}
		

	//-----------------------------------------------------------�t�b�^�[
	 //�������{�^��
	public String getCommand(){
		return command;
	}
	public void setCommand(String s){
		command = s;
	}

	//-----------------------------------------------------------���o��
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
    
    //�\���敪
    public String getHyoKbn(){
    	return hyoKbn;
    }
    public void setHyoKbn(String s){
    	hyoKbn = s;
    }
    
	
	//------------------------------------------------------------
	
	//�o�����[�I�u�W�F�N�g�̊i�[
	private ArrayList voList = new ArrayList();
	
    public void setNyukoSuuAdjustVO(ArrayList arr){
    	voList.clear();
		voList.addAll(arr);
		
	}
	public void addNyukoSuuAdjustVO(NyukoSuuAdjustVO fmVO){
		voList.add(fmVO);
		
	}
	public ArrayList getNyukoSuuAdjustVO(){
		
		return voList;	
		
	}
	public void setNyukoSuuAdjustVO(int i, NyukoSuuAdjustVO fmVO){
    	voList.set(i, fmVO);
		
	}
    public NyukoSuuAdjustVO getNyukoSuuAdjustVO(int i){
		
		return (NyukoSuuAdjustVO)voList.get(i);
	}
	
	public void removeNyukoSuuAdjustVO(){
    	
		voList.clear();
		
	}
	public void removeNyukoSuuAdjustVO(int i){
    	voList.remove(i);
	}

	
	//-------------------------------------------------------------------

	/**
	 * Gets the prcKbn
	 * @return Returns a String
	 */
	public String getPrcKbn() {
		return prcKbn;
	}
	/**
	 * Sets the prcKbn
	 * @param prcKbn The prcKbn to set
	 */
	public void setPrcKbn(String prcKbn) {
		this.prcKbn = prcKbn;
	}

	//---------------------------------------------------------------------------------
	
	
	/**
	 * Gets the hacCod_H
	 * @return Returns a String
	 */
	public String getHacCod_H() {
		return hacCod_H;
	}
	/**
	 * Sets the hacCod_H
	 * @param hacCod_H The hacCod_H to set
	 */
	public void setHacCod_H(String hacCod_H) {
		this.hacCod_H = hacCod_H;
	}

	/**
	 * Gets the hacDteFrm_H
	 * @return Returns a FormDate
	 */
	public FormDate getHacDteFrm_H() {
		return hacDteFrm_H;
	}
	/**
	 * Sets the hacDteFrm_H
	 * @param hacDteFrm_H The hacDteFrm_H to set
	 */
	public void setHacDteFrm_H(FormDate hacDteFrm_H) {
		this.hacDteFrm_H = hacDteFrm_H;
	}


	/**
	 * Gets the hacDteTo_H
	 * @return Returns a FormDate
	 */
	public FormDate getHacDteTo_H() {
		return hacDteTo_H;
	}
	/**
	 * Sets the hacDteTo_H
	 * @param hacDteTo_H The hacDteTo_H to set
	 */
	public void setHacDteTo_H(FormDate hacDteTo_H) {
		this.hacDteTo_H = hacDteTo_H;
	}


	/**
	 * Gets the nkiFrm_H
	 * @return Returns a FormDate
	 */
	public FormDate getNkiFrm_H() {
		return nkiFrm_H;
	}
	/**
	 * Sets the nkiFrm_H
	 * @param nkiFrm_H The nkiFrm_H to set
	 */
	public void setNkiFrm_H(FormDate nkiFrm_H) {
		this.nkiFrm_H = nkiFrm_H;
	}


	/**
	 * Gets the nkiTo_H
	 * @return Returns a FormDate
	 */
	public FormDate getNkiTo_H() {
		return nkiTo_H;
	}
	/**
	 * Sets the nkiTo_H
	 * @param nkiTo_H The nkiTo_H to set
	 */
	public void setNkiTo_H(FormDate nkiTo_H) {
		this.nkiTo_H = nkiTo_H;
	}


	/**
	 * Gets the kigBng1_H
	 * @return Returns a String
	 */
	public String getKigBng1_H() {
		return kigBng1_H;
	}
	/**
	 * Sets the kigBng1_H
	 * @param kigBng1_H The kigBng1_H to set
	 */
	public void setKigBng1_H(String kigBng1_H) {
		this.kigBng1_H = kigBng1_H;
	}


	/**
	 * Gets the kigBng2_H
	 * @return Returns a String
	 */
	public String getKigBng2_H() {
		return kigBng2_H;
	}
	/**
	 * Sets the kigBng2_H
	 * @param kigBng2_H The kigBng2_H to set
	 */
	public void setKigBng2_H(String kigBng2_H) {
		this.kigBng2_H = kigBng2_H;
	}


	/**
	 * Gets the kigBng3_H
	 * @return Returns a String
	 */
	public String getKigBng3_H() {
		return kigBng3_H;
	}
	/**
	 * Sets the kigBng3_H
	 * @param kigBng3_H The kigBng3_H to set
	 */
	public void setKigBng3_H(String kigBng3_H) {
		this.kigBng3_H = kigBng3_H;
	}


	/**
	 * Gets the kigBng4_H
	 * @return Returns a String
	 */
	public String getKigBng4_H() {
		return kigBng4_H;
	}
	/**
	 * Sets the kigBng4_H
	 * @param kigBng4_H The kigBng4_H to set
	 */
	public void setKigBng4_H(String kigBng4_H) {
		this.kigBng4_H = kigBng4_H;
	}


	/**
	 * Gets the kigBng5_H
	 * @return Returns a String
	 */
	public String getKigBng5_H() {
		return kigBng5_H;
	}
	/**
	 * Sets the kigBng5_H
	 * @param kigBng5_H The kigBng5_H to set
	 */
	public void setKigBng5_H(String kigBng5_H) {
		this.kigBng5_H = kigBng5_H;
	}


	/**
	 * Gets the kigBng6_H
	 * @return Returns a String
	 */
	public String getKigBng6_H() {
		return kigBng6_H;
	}
	/**
	 * Sets the kigBng6_H
	 * @param kigBng6_H The kigBng6_H to set
	 */
	public void setKigBng6_H(String kigBng6_H) {
		this.kigBng6_H = kigBng6_H;
	}


	/**
	 * Gets the kigBng7_H
	 * @return Returns a String
	 */
	public String getKigBng7_H() {
		return kigBng7_H;
	}
	/**
	 * Sets the kigBng7_H
	 * @param kigBng7_H The kigBng7_H to set
	 */
	public void setKigBng7_H(String kigBng7_H) {
		this.kigBng7_H = kigBng7_H;
	}


	/**
	 * Gets the kigBng8_H
	 * @return Returns a String
	 */
	public String getKigBng8_H() {
		return kigBng8_H;
	}
	/**
	 * Sets the kigBng8_H
	 * @param kigBng8_H The kigBng8_H to set
	 */
	public void setKigBng8_H(String kigBng8_H) {
		this.kigBng8_H = kigBng8_H;
	}


	/**
	 * Gets the kigBng9_H
	 * @return Returns a String
	 */
	public String getKigBng9_H() {
		return kigBng9_H;
	}
	/**
	 * Sets the kigBng9_H
	 * @param kigBng9_H The kigBng9_H to set
	 */
	public void setKigBng9_H(String kigBng9_H) {
		this.kigBng9_H = kigBng9_H;
	}


	/**
	 * Gets the kigBng10_H
	 * @return Returns a String
	 */
	public String getKigBng10_H() {
		return kigBng10_H;
	}
	/**
	 * Sets the kigBng10_H
	 * @param kigBng10_H The kigBng10_H to set
	 */
	public void setKigBng10_H(String kigBng10_H) {
		this.kigBng10_H = kigBng10_H;
	}
	
	
	/**
	 * Gets the count
	 * @return Returns a int
	 */
	public int getCount() {
		return count;
	}
	/**
	 * Sets the count
	 * @param count The count to set
	 */
	public void setCount(int count) {
		this.count = count;
	}
	
	/**
	 * Gets the updatable
	 * @return Returns a boolean
	 */
	public boolean isUpdatable() {
		return updatable;
	}
	public String getUpdatable() {
		if(updatable)
			return "true";
		else
			return "false";
	}
	/**
	 * Sets the updatable
	 * @param updatable The updatable to set
	 */
	public void setUpdatable(boolean updatable) {
		this.updatable = updatable;
	}

	/**
	 * �R�s�[���y�[�X�g
	 * @return
	 */
	public String getCpPaste() {
		return cpPaste;
	}

	/**
	 * �R�s�[���y�[�X�g
	 * @param string
	 */
	public void setCpPaste(String string) {
		cpPaste = string;
	}

	//---------------------------------------------------------------------------------
	public int getSize(){
		return rows.size();
	}
	
	public void setSize(int size){
		if(size > rows.size()){
			int j = size - this.getSize();
			for(int k=0; k<j; k++){
		    	rows.add(new NyukoSuuAdjustFormRow());			  	
		  	}	
		}else if(size < rows.size()){
			for(int i = rows.size();i > size;i--){
				rows.remove((i-1));
			}
		}
	}
	

	public NyukoSuuAdjustFormRow getFormRow(int i){
		NyukoSuuAdjustFormRow fr = (NyukoSuuAdjustFormRow)rows.get(i);
		if(fr == null){
			fr = new NyukoSuuAdjustFormRow();
			rows.set(i,fr);
		}
		return fr;
	}

	public void clearTableField(){
		for(int i = 0;i < rows.size();i++){
			NyukoSuuAdjustFormRow fr = (NyukoSuuAdjustFormRow)rows.get(i);
			if(fr != null)
				fr.clear();
		}
		
	}
	public void clearTableField(int i){
		NyukoSuuAdjustFormRow fr = (NyukoSuuAdjustFormRow)rows.get(i);
		if(fr != null)
			fr.clear();
	}
	public void removeTableField(int i){
    	rows.remove(i);
	}

	//---------------------------------------------------------------------------------
	public void clearAll(){
		command = "";
		count = 0;
		
		prcKbn = "";
		hacCod_H = "";
		hacDteFrm_H.clear();
		hacDteTo_H.clear();
		nkiFrm_H.clear();
		nkiTo_H.clear();	
		kigBng1_H = "";
		kigBng2_H = "";
		kigBng3_H = "";
		kigBng4_H = "";
		kigBng5_H = "";
		kigBng6_H = "";
		kigBng7_H = "";
		kigBng8_H = "";
		kigBng9_H = "";
		kigBng10_H = "";
		setSize(DEFAULT_ROW_COUNT);
		clearTableField();
		cpPaste = "";
  	}
	
	
	public void removeAllRow(){
		rows.clear();
	}
	
	
	public List getFormRowList(){
		return rows;
	}

	//---------------------------------------------------------------------------------

	public ActionErrors validate(ActionMapping mapping,HttpServletRequest req){
		HttpSession session = req.getSession();
		//���b�Z�[�W���Z�b�V���������菜��
		session.removeAttribute(CommonConst.INFOMSG_KEY);
		
		
		if(command.equals("INIT"))
			return null;
		
		
		ActionErrors errors = new ActionErrors();

		//�����敪�F�P�i�����j�C�Q�i���ɐ������j

		if(command.equals(COMMAND_CLEAR))
			return null;
			
		if(command.equals(COMMAND_PASTE)) //2004/06/25 add
			return null;

		//�X�V�����`�F�b�N
		if(!prcKbn.equals("1")){
			boolean authorityChecked = false;
			authorityChecked = CheckCommon.checkHyoKbn(this.getQueryDaiKaiSkbCod(),this.getHyoKbn());
			if(!authorityChecked){
				errors.add("",new ActionError("errors.cannot","���̃A�J�E���g��","�o�^�^�X�V�^�폜��"));
				return errors;	
			}
		}

		//�����̂Ƃ�
		if(prcKbn.equals("1")){
			boolean blank = true;
			
			//������
			if(!hacCod_H.trim().equals("")){
				//���p�p���̂�
				if(!StringUtils.isAscii(hacCod_H.trim())){
					errors.add("",new ActionError("errors.input.1","������R�[�h","���p�p��"));
				}
				blank = false;
			}
			//������
			if(!hacDteFrm_H.isBlank()){
				if(!hacDteFrm_H.isSet())
					errors.add("",new ActionError("errors.input.1","������(From)","�Z�b�g"));
				else if(!hacDteFrm_H.isDate())
					errors.add("",new ActionError("errors.input.1","������(From)","���t"));
				if(!hacDteTo_H.isBlank()){
					if(!hacDteTo_H.isSet())
						errors.add("",new ActionError("errors.input.1","������(To)","�Z�b�g"));
					else if(!hacDteTo_H.isDate())
						errors.add("",new ActionError("errors.input.1","������(To)","���t"));
					else if(hacDteFrm_H.getDate() > hacDteTo_H.getDate())
						errors.add("",new ActionError("errors.input.incorrect","�������͈͎̔w��"));
				}
				blank = false;
			}else if(!hacDteTo_H.isBlank()){
				errors.add("",new ActionError("errors.input.required","������(From)"));
				blank = false;
			}
			
			//�[��
			if(!nkiFrm_H.isBlank()){
				if(!nkiFrm_H.isSet())
					errors.add("",new ActionError("errors.input.1","�[��(From)","�Z�b�g"));
				else if(!nkiFrm_H.isDate())
					errors.add("",new ActionError("errors.input.1","�[��(From)","���t"));
				if(!nkiTo_H.isBlank()){
					if(!nkiTo_H.isSet())
						errors.add("",new ActionError("errors.input.1","�[��(To)","�Z�b�g"));
					else if(!nkiTo_H.isDate())
						errors.add("",new ActionError("errors.input.1","�[��(To)","���t"));
					else if(nkiFrm_H.getDate() > nkiTo_H.getDate())
						errors.add("",new ActionError("errors.input.incorrect","�[���͈͎̔w��"));
				}
				blank = false;
			}else if(!nkiTo_H.isBlank()){
				errors.add("",new ActionError("errors.input.required","�[��(From)"));
				blank = false;
			}
			
			//�L���ԍ�
			ArrayList kigBngList = this.getKigBng_List();
			for(int i = 0; i < kigBngList.size(); i++){
				String kig = kigBngList.get(i)+"";
				if(!kig.equals("")){
					//���p�p���̂�
					if(!StringUtils.isAscii(kig)){
						errors.add("",new ActionError("errors.input.1","�L���ԍ�("+ (i+1) + "�Ԗ�)","���p�p��"));
					}
					blank = false;
				}
			}
			if(blank)
				errors.add("",new ActionError("errors.input.required","��������"));

		}

		boolean select = false;
		//���ɐ������̎�
		if(prcKbn.equals("2")){

			if(!isUpdatable()){
				errors.add("",new ActionError("errors.update.notselected","�X�V"));
				return errors;
			}
			for(int i = 0; i < getSize();i++){
				NyukoSuuAdjustFormRow row  = this.getFormRow(i);
				if(row.getExecute()){
					select = true;
					break;
				}
			}
			if(!select)
				errors.add("",new ActionError("errors.input.required","�����Ώ�"));
		}
		return errors.empty() ? null : errors;	
	}
	
	/** �L���ԍ���ArrayList�ɃZ�b�g���ĕԂ� */
	public ArrayList getKigBng_List(){
		ArrayList arr = new ArrayList();
		arr.add(kigBng1_H.trim());arr.add(kigBng2_H.trim());
		arr.add(kigBng3_H.trim());arr.add(kigBng4_H.trim());
		arr.add(kigBng5_H.trim());arr.add(kigBng6_H.trim());
		arr.add(kigBng7_H.trim());arr.add(kigBng8_H.trim());
		arr.add(kigBng9_H.trim());arr.add(kigBng10_H.trim());
		return arr;
	}
	
	/**���t�N���X*/
	public class FormDate {
		private String year = "";	
		private String month = "";	
		private String day = "";	
		
		/**
	     * �N�̃Z�b�g
	     * 
	     * @param year �N
	     */
	    public void setYear(String year) {
	        this.year = year;
	    }
	    /**
	     * �N�̎擾
	     * 
	     * @return �N
	     */
	    public String getYear() {
	        return this.year;
	    }
	    /**
	     * ���̃Z�b�g
	     * 
	     * @param month ��
	     */
	    public void setMonth(String month) {
	        this.month = month;
	    }
	    /**
	     * ���̎擾
	     * 
	     * @return ��
	     */
	    public String getMonth() {
	        return this.month;
	    }
	    /**
	     * ���̃Z�b�g
	     * 
	     * @param day ��
	     */
	    public void setDay(String day) {
	        this.day = day;
	    }
	    /**
	     * ���̎擾
	     * 
	     * @return ��
	     */
	    public String getDay() {
	        return this.day;
	    }
	    

		
	    
	    public boolean isBlank(){
	    	
			if(!this.year.trim().equals("")){
				return false;
			}
			if(!this.month.trim().equals("")){
				return false;
			}
			if(!this.day.trim().equals("")){
				return false;
			}
			return true;
	    }
		
		public boolean isSet(){
	    	return (!this.year.trim().equals("") && !this.month.trim().equals("") && !this.day.trim().equals(""));
	    }
	    
	    public boolean isDate(){
	    	return CheckCommon.validateAsDate(this.year.trim(),this.month.trim(),this.day.trim());
	    }
	    
	    public int getDate(){
	    	if(!isDate())
	    		return 0;
	    	else
	    		return (Integer.parseInt(year.trim(),10)*10000) + (Integer.parseInt(month.trim(),10)*100) + Integer.parseInt(day.trim(),10);
	    }
	    
	    public void clear(){
	    	year = "";	
			month = "";	
			day = "";	    	
	    }
	    
	}
	
	public void reset(ActionMapping maping,HttpServletRequest req){
		this.command = "INIT";
	}
	

}

