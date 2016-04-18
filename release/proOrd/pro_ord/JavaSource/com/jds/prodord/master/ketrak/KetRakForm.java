/**
* KetRakForm  �`�ԃ����N�ʏ����}�X�^�[�����e�i���X�t�H�[���N���X
*
*	�쐬��    2003/05/02
*	�쐬��    �i�m�h�h�j���� ���G
* �����T�v    Http���N�G�X�g�ƃ��N�G�X�g�̏������ʂ��i�[����N���X�B
*
*	[�ύX����]
*        2003/05/15�i�m�h�h�j�g�c �h�q
* 			�E��Ў��ʃR�[�h�A�`�ԃR�[�h �h���b�v�_�E���Ή��B
* 		 2003/06/02�i�m�h�h�j
* 			�E��N���A��{�^���ǉ��B��o�^���폜��̓��̓`�F�b�N�C���B
* 		 2003/08/01�i�m�h�h�j���c �Ĕ�
* 			�E�`�Ԗ��̃h���b�v�_�E���̃��x���ύX�i�R�[�h�̂� �� �R�[�h�Ɩ��̊����Q���L�Ɂj�B
*/

package com.jds.prodord.master.ketrak;

import org.apache.struts.action.*;

import javax.servlet.http.*;
import java.util.*;
import com.jds.prodord.common.*;

public class KetRakForm extends ActionForm {
	
	private String command = "";
	private ArrayList rows = new ArrayList();	
	public static final int DEFAULT_ROW_COUNT = 1;
	
	private String prcKbn = "";
	public static final String COMMAND_CLEAR = "�N���A";
	
	private String queryDaiKaiSkbCod = "";
	private String queryKaiSkbCod = "";
	private ArrayList queryKaiSkbCodList = null;
	private String hyoKbn    = "";

	private String[] kaiSkbCod = null;
	private String rank = "";
	private String[] ketCod = null;
	private String ikkatsu_ssnRedTim = "";
	private String ikkatsu_minZaiSuu = "";
	private String ikkatsu_minRotSuu = "";		
	private String ikkatsu_mrmSuu    = "";

	private ArrayList vl_kaiSkbCod = new ArrayList();
	private ArrayList vl_ketCod = new ArrayList();
	private ArrayList lb_ketCod = new ArrayList();
	
	public KetRakForm(){
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

	//2003/05/15
	public Collection getVl_ketCod(){
		return (Collection)vl_ketCod;
	}
	public Collection getLb_ketCod(){
		return (Collection)lb_ketCod;
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
	
	//2003/05/27
	public void setKetCodOptions(ArrayList ketCodList,ArrayList ketNm2List){
		vl_ketCod.clear();
		vl_ketCod.add("");
		lb_ketCod.clear();
		lb_ketCod.add("");
		
		for(int i = 0; i< ketCodList.size(); i++){
			vl_ketCod.add(ketCodList.get(i)+"");
			lb_ketCod.add(ketCodList.get(i)+"�@"+ketNm2List.get(i));
		}
	}
	
	//------------------------------------------------------------
	
	//�o�����[�I�u�W�F�N�g�̊i�[
	private ArrayList voList = new ArrayList();
	
    public void setKetRakVO(ArrayList arr){
    	
    	voList.clear();
		voList.addAll(arr);
		
	}
	public void addKetRakVO(KetRakVO fmVO){

		voList.add(fmVO);
		
	}
	public ArrayList getKetRakVO(){
		
		return voList;	
		
	}
	public void setKetRakVO(int i, KetRakVO fmVO){
    	
		voList.set(i, fmVO);
		
	}
    public KetRakVO getKetRakVO(int i){
		
		return (KetRakVO)voList.get(i);	
		
	}
	public void removeKetRakVO(){
    	
		voList.clear();
		
	}
	public void removeKetRakVO(int i){
    	voList.remove(i);
	}
	
	//�`�F�b�N�{�b�N�X�����Z�b�g���郁�\�b�h
	public void setCheck_false(){
		for(int i = 0; i < this.getSize();i++){
			this.getFormRow(i).setExecute(false);
		}
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
//    /**
//     * ���͉�Ў��ʃR�[�h�̃Z�b�g
//     * 
//     * @param kaiSkbCod ��Ў��ʃR�[�h
//     */
//    public void setKaiSkbCod(String s) {
//    	
//        this.kaiSkbCod = s;
//
//    }
//    /**
//     * ���͉�Ў��ʃR�[�h�̎擾
//     * 
//     * @return ��Ў��ʃR�[�h
//     */
//    public String getKaiSkbCod() {
//
//        return this.kaiSkbCod;
//
//    }
    /**
     * ���̓����N�̃Z�b�g
     * 
     * @param rank �����N
     */
    public void setRank(String s) {

        this.rank = s;

    }
    /**
     * ���̓����N�̎擾
     * 
     * @return �����N
     */
    public String getRank() {

        return this.rank;

    }
//    /**
//     * ���͌`�ԃR�[�h�̃Z�b�g
//     * 
//     * @param ketCod �`�ԃR�[�h
//     */
//    public void setKetCod(String s) {
//
//        this.ketCod = s;
//
//    }
//    /**
//     * ���͌`�ԃR�[�h�̎擾
//     * 
//     * @return �`�ԃR�[�h
//     */
//    public String getKetCod() {
//
//        return this.ketCod;
//
//    }

//3004/05/15
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
     * �`�ԃR�[�h�̃Z�b�g
     */
    public void setKetCod(String[] s) {
        this.ketCod = s;
    }
    /**
     * �`�ԃR�[�h�̎擾
     */
    public String[] getKetCod() {
        return this.ketCod;
    }
	/**
	 * Gets the ikkatsu_ssnRedTim
	 * @return Returns a String
	 */
	public String getIkkatsu_ssnRedTim() {
		return ikkatsu_ssnRedTim;
	}
	/**
	 * Sets the ikkatsu_ssnRedTim
	 * @param ikkatsu_ssnRedTim The ikkatsu_ssnRedTim to set
	 */
	public void setIkkatsu_ssnRedTim(String ikkatsu_ssnRedTim) {
		this.ikkatsu_ssnRedTim = ikkatsu_ssnRedTim;
	}


	/**
	 * Gets the ikkatsu_minZaiSuu
	 * @return Returns a String
	 */
	public String getIkkatsu_minZaiSuu() {
		return ikkatsu_minZaiSuu;
	}
	/**
	 * Sets the ikkatsu_minZaiSuu
	 * @param ikkatsu_minZaiSuu The ikkatsu_minZaiSuu to set
	 */
	public void setIkkatsu_minZaiSuu(String ikkatsu_minZaiSuu) {
		this.ikkatsu_minZaiSuu = ikkatsu_minZaiSuu;
	}


	/**
	 * Gets the ikkatsu_minRotSuu
	 * @return Returns a String
	 */
	public String getIkkatsu_minRotSuu() {
		return ikkatsu_minRotSuu;
	}
	/**
	 * Sets the ikkatsu_minRotSuu
	 * @param ikkatsu_minRotSuu The ikkatsu_minRotSuu to set
	 */
	public void setIkkatsu_minRotSuu(String ikkatsu_minRotSuu) {
		this.ikkatsu_minRotSuu = ikkatsu_minRotSuu;
	}


	/**
	 * Gets the ikkatsu_mrmSuu
	 * @return Returns a String
	 */
	public String getIkkatsu_mrmSuu() {
		return ikkatsu_mrmSuu;
	}
	/**
	 * Sets the ikkatsu_mrmSuu
	 * @param ikkatsu_mrmSuu The ikkatsu_mrmSuu to set
	 */
	public void setIkkatsu_mrmSuu(String ikkatsu_mrmSuu) {
		this.ikkatsu_mrmSuu = ikkatsu_mrmSuu;
	}

	//------------------------------------------------------------�\

     //�����Ώ�
    public boolean getExecute(int i){
    	return getFormRow(i).execute;
    }
    public void setExecute(int i,boolean b){
    	getFormRow(i).execute = b;
    }

     //��Ў��ʃR�[�h
    public String getOutKaiSkbCod(int i){
    	return getFormRow(i).outKaiSkbCod;
    }
    public void setOutKaiSkbCod(int i , String s){
    	getFormRow(i).outKaiSkbCod = s;
    }
     //�����N
    public String getOutRank(int i){
    	return getFormRow(i).outRank;
    }
    public void setOutRank(int i , String s){
    	getFormRow(i).outRank = s;
    }
     //�`�ԃR�[�h
    public String getOutKetCod(int i){
    	return getFormRow(i).outKetCod;
    }
    public void setOutKetCod(int i , String s){
    	getFormRow(i).outKetCod = s;
    }
     //���Y���[�h�^�C��
    public String getSsnRedTim(int i){
    	return getFormRow(i).ssnRedTim;
    }
    public void setSsnRedTim(int i , String s){
    	getFormRow(i).ssnRedTim = s;
    }
     //�ŏ��݌ɐ�
    public String getMinZaiSuu(int i){
    	return getFormRow(i).minZaiSuu;
    }
    public void setMinZaiSuu(int i , String s){
    	getFormRow(i).minZaiSuu = s;
    }
     //�ŏ����b�g��
    public String getMinRotSuu(int i){
    	return getFormRow(i).minRotSuu;
    }
    public void setMinRotSuu(int i , String s){
    	getFormRow(i).minRotSuu = s;
    }
     //�܂�ߐ���
    public String getMrmSuu(int i){
    	return getFormRow(i).mrmSuu;
    }
    public void setMrmSuu(int i , String s){
    	getFormRow(i).mrmSuu = s;
    }


	//---------------------------------------------------------------------------------
	public int getSize(){
		return rows.size();
	}
	
	public void setSize(int size){
		if(size > rows.size()){
			int j = size - this.getSize();
			rows.addAll(Collections.nCopies(j,null));
		}else if(size < rows.size()){
			for(int i = rows.size();i > size;i--){
				rows.remove((i-1));
			}
		}
	}
	

	public FormRow getFormRow(int i){
		FormRow fr = (FormRow)rows.get(i);
		if(fr == null){
			fr = new FormRow();
			rows.set(i,fr);
		}
		return fr;
	}

	public void clearTableField(){
		for(int i = 0;i < rows.size();i++){
			FormRow fr = (FormRow)rows.get(i);
			if(fr != null)
				fr.clear();
		}
		
	}
	public void clearTableField(int i){
		FormRow fr = (FormRow)rows.get(i);
		if(fr != null)
			fr.clear();
	}
	public void removeTableField(int i){
    	rows.remove(i);
	}

	//---------------------------------------------------------------------------------
	public void clearAll(){
		command = "";
		
		prcKbn = "";
        this.kaiSkbCod = null;
        this.rank = "";
        this.ketCod = null;
        this.ikkatsu_ssnRedTim = "";
		this.ikkatsu_minZaiSuu = "";
		this.ikkatsu_minRotSuu = "";
		this.ikkatsu_mrmSuu = "";
		setSize(DEFAULT_ROW_COUNT);
		clearTableField();
  	}
	
	
	public void removeAllRow(){
		rows.clear();
	}
	
	
	public List getFormRowList(){
		return rows;
	}

	//---------------------------------------------------------------------------------
	public static class FormRow {
		private boolean execute = false;
		
		private String outKaiSkbCod = "";
		private String outRank   = "";
		private String outKetCod = "";
		private String ssnRedTim = "";
		private String minZaiSuu = "";
		private String minRotSuu = "";		
		private String mrmSuu    = "";

	     //�����Ώ�
	    public boolean getExecute(){
	    	return execute;
	    }
	    public void setExecute(boolean b){
	    	execute = b;
	    }
	

	    
		public void clear(){
			execute = false;
			outKaiSkbCod = "";
			outRank = "";
			outKetCod = "";
			ssnRedTim = "";
			minZaiSuu = "";
			minRotSuu = "";
			mrmSuu = "";
			
		}
		
		public boolean isBlank(){
			if(!ssnRedTim.equals(""))
				return false;
			if(!minZaiSuu.equals(""))
				return false;
			if(!minRotSuu.equals(""))
				return false;
			if(!mrmSuu.equals(""))
				return false;
			return true;
		}
		
			/**
		 * Gets the outKaiSkbCod
		 * @return Returns a String
		 */
		public String getOutKaiSkbCod() {
			return outKaiSkbCod;
		}
		/**
		 * Sets the outKaiSkbCod
		 * @param outKaiSkbCod The outKaiSkbCod to set
		 */
		public void setOutKaiSkbCod(String outKaiSkbCod) {
			this.outKaiSkbCod = outKaiSkbCod;
		}

		/**
		 * Gets the outRank
		 * @return Returns a String
		 */
		public String getOutRank() {
			return outRank;
		}
		/**
		 * Sets the outRank
		 * @param outRank The outRank to set
		 */
		public void setOutRank(String outRank) {
			this.outRank = outRank;
		}

		/**
		 * Gets the outKetCod
		 * @return Returns a String
		 */
		public String getOutKetCod() {
			return outKetCod;
		}
		/**
		 * Sets the outKetCod
		 * @param outKetCod The outKetCod to set
		 */
		public void setOutKetCod(String outKetCod) {
			this.outKetCod = outKetCod;
		}

	
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

}

	//---------------------------------------------------------------------------------

	public ActionErrors validate(ActionMapping mapping,HttpServletRequest req){
		HttpSession session = req.getSession();
		//���b�Z�[�W���Z�b�V���������菜��
		session.removeAttribute(CommonConst.INFOMSG_KEY);
		
		
		if(command.equals("INIT"))
			return null;
		if(rank == null || ketCod == null)//2003/06/25 add okada
			return null;
		
		ActionErrors errors = new ActionErrors();

		//�����敪�F�P�i�Ɖ�j�C�Q�i�o�^�j�C�R�i�ύX�j�C�S�i�폜�j
		//    		�P�����Ў��ʁ{�����Nor�`��      �Q�E�S����t���L�[�w��      �R����������ʂɑ΂���

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

		//�����E�o�^�E�폜�̂Ƃ�
		if(!prcKbn.equals("3")){
			if(!ikkatsu_ssnRedTim.equals("") || !ikkatsu_minZaiSuu.equals("") || 
			   !ikkatsu_minRotSuu.equals("") || !ikkatsu_mrmSuu.equals("")){
			   	errors.add("",new ActionError("errors.input.prohibited","�ꊇ�ύX�p���̓G���A"));
				return errors;
			}
			//�o�^��
			if(prcKbn.equals("2")){
				for(int i = 0; i < rows.size();i++){
				    FormRow  row = getFormRow(i);
					if(!row.outKaiSkbCod.equals("")||!row.outRank.equals("")||!row.outKetCod.equals("")){
						errors.add("",new ActionError("errors.insert.exist_results"));
						return errors;
					}
					if(row.ssnRedTim.equals("")||row.minZaiSuu.equals("")||row.minRotSuu.equals("")||row.mrmSuu.equals("")){
						errors.add("",new ActionError("errors.input.requiredall"));
						return errors;
					}
				}
				//�����N
				if(rank.equals("")){
					errors.add("",new ActionError("errors.input.required","�����N"));		
				}
				//�`�ԃR�[�h
				if(ketCod[0].equals("")){
					errors.add("",new ActionError("errors.input.required","�`�ԃR�[�h"));	
				}
			//������
			}else if(prcKbn.equals("1")){
				if(rank.equals("")&&ketCod[0].equals("")){
					errors.add("",new ActionError("errors.input.required","�����N�A�܂��͌`�ԃR�[�h"));	
				}
			}	
		//�o�^�E�X�V�̂Ƃ�
		}if(prcKbn.equals("2") || prcKbn.equals("3")){
			
			boolean select = false;
			boolean ikkatsuHenko = false;
			//�X�V��
			if(prcKbn.equals("3")){
				//�ꊇ�ύX���ǂ���
				if(!ikkatsu_ssnRedTim.equals("") || !ikkatsu_minZaiSuu.equals("") || 
				   !ikkatsu_minRotSuu.equals("") || !ikkatsu_mrmSuu.equals("")){
				   	ikkatsuHenko = true;
				}
				if(ikkatsuHenko){
					if(!ikkatsu_ssnRedTim.equals("") && !StringUtils.isAsciiDigit(ikkatsu_ssnRedTim.trim()))
						errors.add("",new ActionError("errors.input.1","���Y���[�h�^�C���ꊇ�ύX","���p����"));
					if(!ikkatsu_minZaiSuu.equals("") && !StringUtils.isAsciiDigit(ikkatsu_minZaiSuu.trim()))
						errors.add("",new ActionError("errors.input.1","�ŏ��݌ɐ��ꊇ�ύX","���p����"));
					if(!ikkatsu_minRotSuu.equals("") && !StringUtils.isAsciiDigit(ikkatsu_minRotSuu.trim()))
						errors.add("",new ActionError("errors.input.1","�ŏ����b�g���ꊇ�ύX","���p����"));
					if(!ikkatsu_mrmSuu.equals("") && !StringUtils.isAsciiDigit(ikkatsu_mrmSuu.trim()))
						errors.add("",new ActionError("errors.input.1","�܂�ߐ��ʈꊇ�ύX","���p����"));
					if(!errors.empty())
						select = true;
				}
			}
			for(int i = 0; i < rows.size();i++){
				FormRow row = getFormRow(i);
				//�X�V��
				if(prcKbn.equals("3")){
					if(!ikkatsuHenko){//�ꊇ�ύX�łȂ���΁A�`�F�b�N�̂Ȃ��s�͓ǂݔ�΂�
						if(row == null || !row.execute)
							continue;
					}
					select = true;
						
					if(row.outKaiSkbCod.equals("")||row.outRank.equals("")||row.outKetCod.equals("")){
						errors.add("",new ActionError("errors.update.notselected","�X�V"));
						break;
					}
				}	
				if(!ikkatsu_ssnRedTim.equals("") && row.ssnRedTim.equals("") ){
					errors.add("",new ActionError("errors.input.required_row",String.valueOf(i+1),"���Y���[�h�^�C��"));		
				}else{
					if(!StringUtils.isAsciiDigit(row.ssnRedTim.trim())){
						errors.add("",new ActionError("errors.input.1_row",String.valueOf(i+1),"���Y���[�h�^�C��","���p����"));
					}
				}
				if(!ikkatsu_minZaiSuu.equals("") && row.minZaiSuu.equals("") ){
					errors.add("",new ActionError("errors.input.required_row",String.valueOf(i+1),"�ŏ��݌ɐ�"));		
				}else{
					if(!StringUtils.isAsciiDigit(row.minZaiSuu.trim())){
						errors.add("",new ActionError("errors.input.1_row",String.valueOf(i+1),"�ŏ��݌ɐ�","���p����"));
					}
				}
				if(!ikkatsu_minRotSuu.equals("") && row.minRotSuu.equals("") ){
					errors.add("",new ActionError("errors.input.required_row",String.valueOf(i+1),"�ŏ����b�g��"));		
				}else{
					if(!StringUtils.isAsciiDigit(row.minRotSuu.trim())){
						errors.add("",new ActionError("errors.input.1_row",String.valueOf(i+1),"�ŏ����b�g��","���p����"));
					}
				}
				if(!ikkatsu_mrmSuu.equals("") && row.mrmSuu.equals("") ){
					errors.add("",new ActionError("errors.input.required_row",String.valueOf(i+1),"�܂�ߐ���"));		
				}else{
					if(!StringUtils.isAsciiDigit(row.mrmSuu.trim())){
						errors.add("",new ActionError("errors.input.1_row",String.valueOf(i+1),"�܂�ߐ���","���p����"));
					}
				}
			}
			//�X�V��
			if(prcKbn.equals("3")){
				if(!select){
					errors.add("",new ActionError("errors.input.required","�����Ώ�"));
				}
			}
		}
		boolean select = false;
		//�폜�̎�
		if(prcKbn.equals("4")){
			for(int i = 0; i < rows.size();i++){
				FormRow row = getFormRow(i);
				if(row.outKaiSkbCod.equals("")||row.outRank.equals("")||row.outKetCod.equals("")){
					errors.add("",new ActionError("errors.update.notselected","�폜"));
				}
				if(row.execute){
					select = true;
					break;
				}else{
					select = false;
				}
			}
			if(!select)
				errors.add("",new ActionError("errors.input.required","�����Ώ�"));	
		}
		return errors.empty() ? null : errors;	
	}
	
	public void reset(ActionMapping maping,HttpServletRequest req){
		this.command = "INIT";
		setCheck_false();//2003/05/22 add okada
	}
	


}

