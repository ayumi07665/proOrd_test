/**
* FzkHinKsiForm  �t���i�\���}�X�^�[�����e�i���X�t�H�[���N���X
*
*	�쐬��    2004/02/12
*	�쐬��    �i�m�h�h�j�X
* �����T�v    Http���N�G�X�g�ƃ��N�G�X�g�̏������ʂ��i�[����N���X�B
*
*	[�ύX����]
*
*/

package com.jds.prodord.master.fzkhinksi;

import org.apache.struts.action.*;

import javax.servlet.http.*;
import java.util.*;
import com.jds.prodord.common.*;

public class FzkHinKsiForm extends ActionForm {
	
	private String command = "";
	
	private ArrayList rows = new ArrayList();	

	public static final int DEFAULT_ROW_COUNT = 1;
	public static final int MIN_OF_BRANK_ROW_COUNT = 10;
	
	private String prcKbn = "";
	public static final String COMMAND_CLEAR = "�N���A";
	
	private String queryDaiKaiSkbCod = "";
	private String queryKaiSkbCod = "";
	private ArrayList queryKaiSkbCodList = null;

	private String[] kaiSkbCod = null;

	private ArrayList vl_kaiSkbCod = new ArrayList();

	private String hinban = "";
	private String title = "";	
	private String hbidte = "";
	private String ketNm = "";
	private String ketCod = "";
	private String setsuu = "";
	private String hyoKbn = "";
	
	int count = 0;
	boolean updatable = false;
		
	public FzkHinKsiForm(){
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
    
	public Collection getVl_kaiSkbCod(){
		return (Collection)vl_kaiSkbCod;
	}

	public void setKaiSkbCodOptions(ArrayList queryKaiSkbCodList){
		vl_kaiSkbCod.clear();
		for(int i = 0; i<queryKaiSkbCodList.size(); i++){
			vl_kaiSkbCod.add(queryKaiSkbCodList.get(i)+"");
		}
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
	private FzkHinKsiVO vo = new FzkHinKsiVO();
	
    public void setFzkHinKsiVO(FzkHinKsiVO vo){
		this.vo = vo;
		
	}
	public FzkHinKsiVO getFzkHinKsiVO(){
		
		return vo;	
		
	}	
	public void clearFzkHinKsiVO(){
    	
		this.vo = new FzkHinKsiVO();
		
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
	 * ��Ў��ʃR�[�h�̃Z�b�g
	 */
	public void setKaiSkbCod(String[] s) {
		this.kaiSkbCod = s;
	}
	/**
	 * ��Ў��ʃR�[�h�̎擾
	 */
	public String[] getKaiSkbCod() {
		return this.kaiSkbCod;
	}

	/**
	 *  �i�Ԃ̎擾
	 * @return
	 */
	public String getHinban() {
		return hinban;
	}
	/**
	 * �i�Ԃ̃Z�b�g
	 * @param string
	 */
	public void setHinban(String hinban) {
		this.hinban = hinban;
	}

	/**
	 * �^�C�g���̎擾
	 * @return
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * �^�C�g���̃Z�b�g
	 * @param string
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 *�@�������̎擾
	 * @return�@hbidte
	 */
	public String getHbidte() {
		return hbidte;
	}
	/**
	 * �������̃Z�b�g
	 * @param string
	 */
	public void setHbidte(String hbidte) {
		this.hbidte = hbidte;
	}

	/**
	 * �`�Ԗ��̂̎擾
	 * @return
	 */
	public String getKetNm() {
		return ketNm;
	}
	/**
	 * �`�Ԗ��̂̃Z�b�g
	 * @param string
	 */
	public void setKetNm(String ketNm) {
		this.ketNm = ketNm;
	}

	/**
	 * �`�ԃR�[�h�̎擾
	 * @return
	 */
	public String getKetCod() {
		return ketCod;
	}

	/**
	 * �`�ԃR�[�h�̃Z�b�g
	 * @param ketCd
	 */
	public void setKetCod(String ketCod) {
		this.ketCod = ketCod;
	}

	/**
	 * �Z�b�g���̎擾
	 * @return
	 */
	public String getSetsuu() {
		return setsuu;
	}
	/**
	 * �Z�b�g���̃Z�b�g
	 * @param string
	 */
	public void setSetsuu(String setsuu) {
		this.setsuu = setsuu;
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

	//---------------------------------------------------------------------------------
	public int getSize(){
		return rows.size();
	}
	
	public void setSize(int size){
		if(size > rows.size()){
			int j = size - this.getSize();
			for(int k=0; k<j; k++){
		    	rows.add(new FzkHinKsiFormRow());			  	
		  	}	
		}else if(size < rows.size()){
			for(int i = rows.size();i > size;i--){
				rows.remove((i-1));
			}
		}
	}
	
	public FzkHinKsiFormRow getFormRow(int i){
		FzkHinKsiFormRow fr = (FzkHinKsiFormRow)rows.get(i);
		if(fr == null){
			fr = new FzkHinKsiFormRow();
			rows.set(i,fr);
		}
		return fr;
	}

	public void clearTableField(){
		for(int i = 0;i < rows.size();i++){
			FzkHinKsiFormRow fr = (FzkHinKsiFormRow)rows.get(i);
			if(fr != null)
				fr.clear();
		}
	}

	public void clearTableField(int i){
		FzkHinKsiFormRow fr = (FzkHinKsiFormRow)rows.get(i);
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
		
		this.prcKbn = "";
		this.title = "";
		this.hbidte = "";
		this.setsuu = "";
		this.ketNm = "";
		this.ketCod = "";
		this.hinban = "";
		this.kaiSkbCod = null;
		setSize(DEFAULT_ROW_COUNT);
		clearTableField();
		this.clearFzkHinKsiVO();

  	}
	
	
	public void removeAllRow(){
		rows.clear();
	}
	
	public ArrayList getFormRowList(){
		return rows;
	}
//	�`�F�b�N�{�b�N�X�����Z�b�g���郁�\�b�h
	public void setCheck_false(){
		for(int i = 0; i < this.getSize();i++){
			this.getFormRow(i).setCheck_bx(false);
		}
	}

	//---------------------------------------------------------------------------------


	public ActionErrors validate(ActionMapping mapping,HttpServletRequest req){
		HttpSession session = req.getSession();
		//���b�Z�[�W���Z�b�V���������菜��
		session.removeAttribute(CommonConst.INFOMSG_KEY);
		
		if(command.equals("INIT"))
			return null;
		
		ActionErrors errors = new ActionErrors();

		if(command.equals(COMMAND_CLEAR))
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

		if(this.getHinban().equals(""))
			errors.add("",new ActionError("errors.input.required","�i��"));
		
		if((errors.empty()) && (prcKbn.equals("2") || prcKbn.equals("3") || prcKbn.equals("4"))){

			FzkHinKsiVO fmVO = this.getFzkHinKsiVO();
			if(this.getHinban().equals(fmVO.getHinban())){
			//2 or 3�F�o�^���͍X�V
				if(prcKbn.equals("2") || prcKbn.equals("3")){
			
					boolean select = false;
					ArrayList list = new ArrayList();
					int check_count = 0;
					for(int i = 0; i < rows.size();i++){
						FzkHinKsiFormRow row = this.getFormRow(i);

						if(row == null || !row.getCheck_bx())
							continue;
					
						//�����ރR�[�h�F�K�{
						if(row.getFuksziCod().trim().equals("")){
							errors.add("",new ActionError("errors.input.required_row",String.valueOf(i+1),"�����ރR�[�h"));
						}else {
						//�����ރR�[�h�F���p�����̂�
							if(!StringUtils.isAscii(row.getFuksziCod().trim()))
								errors.add("",new ActionError("errors.input.1_row",String.valueOf(i+1),"�����ރR�[�h","���p�p��"));
						}
						//�K�{
						if(row.getSirSk().trim().equals("")){
							errors.add("",new ActionError("errors.input.required_row",String.valueOf(i+1),"�d����R�[�h"));
						}else {
						//���p�����̂�
							if(!StringUtils.isAscii(row.getSirSk().trim()))
								errors.add("",new ActionError("errors.input.1_row",String.valueOf(i+1),"�d����R�[�h","���p�p��"));
						}

						//�����ރR�[�h�F�d��
						if(errors.empty()){
							if(list.indexOf(row.getFuksziCod().trim()) != -1)
								errors.add("",new ActionError("errors.insert.duplicate_no",String.valueOf(i+1),"�����ރR�[�h"));
						}
						list.add(row.getFuksziCod().trim());			
						select = true;
						check_count = check_count + 1; 
					}

					if(!select)
						errors.add("",new ActionError("errors.input.required","�\��"));
						
					if(check_count > 40)
						errors.add("",new ActionError("errors.cannot","�\��","41���ȏ�o�^"));
						
				}
			}else{
				errors.add("",new ActionError("errors.update.notselected","�o�^�E�X�V�E�폜"));
			}
		}
		return errors.empty() ? null : errors;	
	}
	
	public void reset(ActionMapping maping,HttpServletRequest req){
		this.command = "INIT";
		setCheck_false();
	}


}	