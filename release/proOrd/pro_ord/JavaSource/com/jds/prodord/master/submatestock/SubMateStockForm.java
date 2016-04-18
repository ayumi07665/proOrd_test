/**
* SubMateStockForm  �����ލ݌Ƀ����e�i���X �t�H�[���N���X
*
*	�쐬��    2003/08/18
*	�쐬��    �i�m�h�h�j����  ����
* �����T�v    Http���N�G�X�g�ƃ��N�G�X�g�̏������ʂ��i�[����N���X�B
*
*	[�ύX����]
*	�ύX��    2003/09/18
*	�ύX��    �i�m�h�h�j����  ���� 
*      �ύX���e  �T���v���敪��get/set�ǉ�
* 		 2004/06/29�i�m�h�h�j�g�c
* 			�E�R�s�[���y�[�X�g�@�\�ǉ�
*/
package com.jds.prodord.master.submatestock;
import org.apache.struts.action.*;

import javax.servlet.http.*;
import java.util.*;
import com.jds.prodord.common.*;

public class SubMateStockForm extends ActionForm {

	public static final String COMMAND_JIKKOU = "���s";
	public static final String COMMAND_CLEAR = "�N���A";
	public static final String COMMAND_PASTE = "�\��t��";//2004/06/29 add
		
	private String command = "";
	private ArrayList rows = new ArrayList();
	public static final int DEFAULT_ROW_COUNT = 1;

	private String queryDaiKaiSkbCod = "";
	private String queryKaiSkbCod = "";
	private ArrayList queryKaiSkbCodList = null;
	private String prcKbn = "";	
	private String smpKbnSel = "";	

	private String kigoBan0 = "";
	private String kigoBan1 = "";
	private String kigoBan2 = "";
	private String kigoBan3 = "";	
	private String kigoBan4 = "";	
	private String kigoBan5 = "";	
	private String kigoBan6 = "";	
	private String kigoBan7 = "";	
	private String kigoBan8 = "";	
	private String kigoBan9 = "";	
		
	private String atai = "1";
	private String cpPaste =""; //2004/06/29 add
	private String hyoKbn =""; 

	
	private ArrayList vl_kaiSkbCod = new ArrayList();
	private ArrayList kigbng_arr = new ArrayList();

 //-----  ���������ݒ�  ------------------------------------------------------------------------

	public SubMateStockForm(){
		super();
		this.clearAll();
		this.setSize(DEFAULT_ROW_COUNT);
		this.setCommand("INIT");
	}

//-----  get/set�ݒ�  -------------------------------------------------------------------------

	 //***  �������{�^����get/set  ***
	public String getCommand(){
		return command;
	}
	public void setCommand(String s){
		command = s;
	}

     //***  �N�G����\��Ў��ʃR�[�h  ***
    public String getQueryDaiKaiSkbCod(){
    	return queryDaiKaiSkbCod;
    }
    public void setQueryDaiKaiSkbCod(String s){
    	queryDaiKaiSkbCod = s;
    }

    //***  �N�G����Ў��ʃR�[�h  ***
    public String getQueryKaiSkbCod(){
    	return queryKaiSkbCod;
    }
    public void setQueryKaiSkbCod(String s){
    	queryKaiSkbCod = s;
    }

    //***  �N�G����Ў��ʃR�[�h���X�g��get/set  ***
    public ArrayList getQueryKaiSkbCodList(){
    	return queryKaiSkbCodList;
    }
    public void setQueryKaiSkbCodList(ArrayList arr){
    	queryKaiSkbCodList = arr;
    }

	//***  �����敪��get/set  ***
	public String getPrcKbn() {
		return prcKbn;
	}
	public void setPrcKbn(String prcKbn) {
		this.prcKbn = prcKbn;
	}

	//***  �T���v���敪��get/set  ***  //2003/09/18
	public String getSmpKbnSel() {
		return smpKbnSel;
	}
	public void setSmpKbnSel(String smpKbnSel) {
		this.smpKbnSel = smpKbnSel;
	}

	//*** �L���ԍ��O��get/set  ***
	public String getKigoBan0() {
		return kigoBan0;
	}
	public void setKigoBan0(String kigoBan0) {
		this.kigoBan0 = kigoBan0;
	}

	//*** �L���ԍ��P��get/set  ***
	public String getKigoBan1() {
		return kigoBan1;
	}
	public void setKigoBan1(String kigoBan1) {
		this.kigoBan1 = kigoBan1;
	}

	//*** �L���ԍ��Q��get/set  ***
	public String getKigoBan2() {
		return kigoBan2;
	}
	public void setKigoBan2(String kigoBan2) {
		this.kigoBan2 = kigoBan2;
	}

	//*** �L���ԍ��R��get/set  ***
	public String getKigoBan3() {
		return kigoBan3;
	}
	public void setKigoBan3(String kigoBan3) {
		this.kigoBan3 = kigoBan3;
	}

	//*** �L���ԍ��S��get/set  ***
	public String getKigoBan4() {
		return kigoBan4;
	}
	public void setKigoBan4(String kigoBan4) {
		this.kigoBan4 = kigoBan4;
	}

	//*** �L���ԍ��T��get/set  ***
	public String getKigoBan5() {
		return kigoBan5;
	}
	public void setKigoBan5(String kigoBan5) {
		this.kigoBan5 = kigoBan5;
	}

	//*** �L���ԍ��U��get/set  ***
	public String getKigoBan6() {
		return kigoBan6;
	}
	public void setKigoBan6(String kigoBan6) {
		this.kigoBan6 = kigoBan6;
	}

	//*** �L���ԍ��V��get/set  ***
	public String getKigoBan7() {
		return kigoBan7;
	}
	public void setKigoBan7(String kigoBan7) {
		this.kigoBan7 = kigoBan7;
	}

	//*** �L���ԍ��W��get/set  ***
	public String getKigoBan8() {
		return kigoBan8;
	}
	public void setKigoBan8(String kigoBan8) {
		this.kigoBan8 = kigoBan8;
	}

	//*** �L���ԍ��X��get/set  ***
	public String getKigoBan9() {
		return kigoBan9;
	}
	public void setKigoBan9(String kigoBan9) {
		this.kigoBan9 = kigoBan9;
	}
	
	//*** �L���ԍ�arr��get  ***
	public ArrayList getKigo_arr(){
		ArrayList kigo_arr = new ArrayList();
		kigo_arr.add(kigoBan0.trim());kigo_arr.add(kigoBan1.trim());kigo_arr.add(kigoBan2.trim());
		kigo_arr.add(kigoBan3.trim());kigo_arr.add(kigoBan4.trim());
		kigo_arr.add(kigoBan5.trim());kigo_arr.add(kigoBan6.trim());kigo_arr.add(kigoBan7.trim());
		kigo_arr.add(kigoBan8.trim());kigo_arr.add(kigoBan9.trim());
		return kigo_arr;
	}

	//***  �l�i�e�������q�����̒l������EJavaScript�Ŏg�p�j***
	public String  getAtai() {
		return atai;
	}
	public void setAtai(String atai) {
		this.atai = atai;
	}
	
	
	/**
	 * @return
	 */
	public String getCpPaste() {
		return cpPaste;
	}

	/**
	 * @param string
	 */
	public void setCpPaste(String string) {
		cpPaste = string;
	}

	//*** �\���敪  ***
	public String getHyoKbn() {
		return hyoKbn;
	}
	public void setHyoKbn(String s) {
		this.hyoKbn = s;
	}
	
	/**�����p�L���ԍ�**/
	public ArrayList getKigbng_arr() {
		return kigbng_arr;
	}

	public void setKigbng_arr(ArrayList kigbng_arr) {
		this.kigbng_arr = kigbng_arr;
	}



//------------------------------------------------------

	//	�o�����[�I�u�W�F�N�g�̊i�[
	private ArrayList voList = new ArrayList();

    public void setSubMateStockVO(ArrayList arr){
    	voList.clear();
		voList.addAll(arr);
	}

	public void addSubMateStockVO(SubMateStockVO fmVO){
		voList.add(fmVO);
	}
 	public ArrayList getSubMateStockVO(){
		return voList;	
	}

	public void setSubMateStockVO(int i, SubMateStockVO fmVO){
		voList.set(i, fmVO);
	}

	public SubMateStockVO getSubMateStockVO(int i){
		return (SubMateStockVO)voList.get(i);
	}

	public void removeSubMateStockVO(){
		voList.clear();
	}

	public void removeSubMateStockVO(int i){
    	voList.remove(i);
	}



//---------------------------------------------------------------

	public void clearAll(){
		command = "";
		prcKbn = "1";
		smpKbnSel = ""; //2003/09/18
		kigoBan0 = "";
		kigoBan1 = "";
		kigoBan2 = "";
		kigoBan3 = "";
		kigoBan4 = "";
		kigoBan5 = "";
		kigoBan6 = "";
		kigoBan7 = "";
		kigoBan8 = "";
		kigoBan9 = "";
		cpPaste = ""; //2004/06/29 add
		atai = Integer.toString(DEFAULT_ROW_COUNT);

		setSize(DEFAULT_ROW_COUNT);
		clearTableField();
  	}

	public void pointClear(){
		prcKbn = "1";
	}

	public void removeAllRow(){
		rows.clear();
	}
	
	
	public List getFormRowList(){
		return rows;
	}

//==================================================

	public static class FormRow {
		private boolean execute = false;
		private boolean hidFlag = false;
		private String hidDaiKaiSkbCod = "";
		private String kigoBan = "";
		private String huksizaiCod   = "";
		private String huksizaiMei   = "";
		private String huksizaiZaiko = "";
		private String smpKbn = ""; //2003/09/18
		private boolean sign = true;
		private String teiseiSuu = "";
	    private int hidUpdDte;
	    private int hidUpdJkk;
/*	    
	//***  gets the execute ***
		public boolean getExecute(){
			return execute;
		}

	//***  Gets the hidFlag  ***  
		public boolean getHidFlag() {
			return hidFlag;
		}
		public void setHidFlag(boolean hidFlag) {
			this.hidFlag = hidFlag;
		}

	//***  Gets the hidDaiKaiSkbCod  ***
		public String getHidDaiKaiSkbCod() {
			return hidDaiKaiSkbCod;
		}
		public void setHidDaiKaiSkbCod(String hidDaiKaiSkbCod) {
			this.hidDaiKaiSkbCod = hidDaiKaiSkbCod;
		}

	//***  Gets the KigBan  
		public String getKigBan() {
			return kigoBan;
		}
		public void setKigBan(String KigoBan) {
			this.kigoBan = kigoBan;
		}

	//***  Gets the HuksizaiCod  ***
		public String getHuksizaiCod() {
			return huksizaiCod;
		}
		public void setHuksizaiCod(String HuksizaiCod) {
			this.huksizaiCod = huksizaiCod;
		}

	//***  Gets the HuksizaiMei  ***
		public String getHuksizaiMei() {
			return huksizaiMei;
		}
		public void setHuksizaiMei(String HuksizaiMei) {
			this.huksizaiMei = huksizaiMei;
		}


	//***  Gets the HuksizaiZaiko  ***
		public String getHuksizaiZaiko() {
			return huksizaiZaiko;
		}
		public void setHuksizaiZiko(String HuksizaiZaiko) {
			this.huksizaiZaiko = huksizaiZaiko;
		}

	//***  Gets the smpKbn  *** //2003/09/18
		public String getSmpKbn() {
			return smpKbn;
		}
		public void setSmpKbn(String smpKbn) {
			this.smpKbn = smpKbn;
		}

	//***  Gets the sine  ***
		public boolean getSign() {
			return sign;
		}
		public void setSign(boolean Sign) {
			this.sign = sign;
		}

	//***  Gets the TeiseiSuu  ***
		public String getTeiseiSuu() {
			return teiseiSuu;
		}
		public void setTeiseiSuu(String TeiseiSuu) {
			this.teiseiSuu = teiseiSuu;
		}

	//***  Gets the updDte  ***
		public int getHidUpdDte() {
			return hidUpdDte;
		}
		public void setHidUpdDte(int hidUpdDte) {
			this.hidUpdDte = hidUpdDte;
		}
	//***  Gets the updJkk  ***
		public int getHidUpdJkku() {
			return hidUpdJkk;
		}
		public void setHidUpdJkk(int hidUpdJkk) {
			this.hidUpdJkk = hidUpdJkk;
		}
*/

		public void clear(){
			kigoBan = "";
			huksizaiCod = "";
			huksizaiMei = "";
			huksizaiZaiko = "";
			smpKbn = "";//2003/09/24 add
			sign = true;
			teiseiSuu = "";
		}
	
	}

//-------------------------------------------------

	//Execute
	public boolean getExecute(int i){
		return getFormRow(i).execute;
	}

    //(hidden)�t���O
    public boolean getHidFlag(int i){
    	return getFormRow(i).hidFlag;
    }
    public void setHidFlag(int i , boolean b){
    	getFormRow(i).hidFlag = b;
    }

    //(hidden)��\���
    public String getHidDaiKaiSkbCod(int i){
    	return getFormRow(i).hidDaiKaiSkbCod;
    }
    public void setHidDaiKaiSkbCod(int i , String s){
    	getFormRow(i).hidDaiKaiSkbCod = s;
    }

     //�T���v���敪 //2003/09/18
    public String getSmpKbn(int i){
    	return getFormRow(i).smpKbn;
    }
    public void setSmpKbn(int i , String s){
    	getFormRow(i).smpKbn = s;
    }

     //�L���ԍ�
    public String getKigoBan(int i){
    	return getFormRow(i).kigoBan;
    }
    public void setKigoBan(int i , String s){
    	getFormRow(i).kigoBan = s;
    }

     //�����ރR�[�h
    public String getHuksizaiCod(int i){
    	return getFormRow(i).huksizaiCod;
    }
    public void setHuksizaiCod(int i , String s){
    	getFormRow(i).huksizaiCod = s;
    }

     //�����ރR�[�h
    public String getHuksizaiMei(int i){
    	return getFormRow(i).huksizaiMei;
    }
    public void setHuksizaiMei(int i , String s){
    	getFormRow(i).huksizaiMei = s;
    }

     //�����ލ݌ɐ�
    public String getHuksizaiZaiko(int i){
    	return getFormRow(i).huksizaiZaiko;
    }
    public void setHuksizaiZaiko(int i , String s){
    	getFormRow(i).huksizaiZaiko = s;
    }

     //�T�C��
    public boolean getSign(int i){
    	return getFormRow(i).sign;
    }
    public void setSign(int i , boolean flag){
    	getFormRow(i).sign = flag;
    }

     //������
    public String getTeiseiSuu(int i){
    	return getFormRow(i).teiseiSuu;
    }
    public void setTeiseiSuu(int i , String s){
    	getFormRow(i).teiseiSuu = s;
    }

     //�X�V��
    public int getHidUpdDte(int i){
    	return getFormRow(i).hidUpdDte;
    }
    public void setHidUpdDte(int i , int s){
    	getFormRow(i).hidUpdDte = s;
    }

     //�X�V����
    public int getHidUpdJkk(int i){
    	return getFormRow(i).hidUpdJkk;
    }
    public void setHidUpdJkk(int i , int s){
    	getFormRow(i).hidUpdJkk = s;
    }




//-----------------------------------------------------

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

//=====  �Ɖ���͔���  ================================================================
 	public ActionErrors validate(ActionMapping mapping,HttpServletRequest req){
		HttpSession session = req.getSession();
		//���b�Z�[�W���Z�b�V���������菜��
		session.removeAttribute(CommonConst.INFOMSG_KEY);
		
		if(command.equals("INIT"))
			return null;

		ActionErrors errors = new ActionErrors();

		if(command.equals(COMMAND_JIKKOU)){
//			-----  ���͔���  ---------------------------------------------------------------
			//�X�V�����`�F�b�N
			if(!prcKbn.equals("1")){
				boolean authorityChecked = false;
				authorityChecked = CheckCommon.checkHyoKbn(this.getQueryDaiKaiSkbCod(),this.getHyoKbn());
				if(!authorityChecked){
					errors.add("",new ActionError("errors.cannot","���̃A�J�E���g��","�o�^�^�X�V�^�폜��"));
					return errors;	
				}
			}

			if(prcKbn.equals("1")){  //�Ɖ�
				ArrayList kigo_arr = getKigo_arr();
			
				boolean blank = true;
				for(int i = 0; i < kigo_arr.size(); i++){
					if(!kigo_arr.get(i).toString().equals("")){
						blank = false;
						break;
					}
				}
				if(blank){
					errors.add("",new ActionError("errors.input.required","�L���ԍ�"));
					return errors;
				}

				for(int i =0; i < kigo_arr.size(); i++){
					if(kigo_arr.get(i).toString().equals(""))
						continue;
					if(!StringUtils.isAscii((String)kigo_arr.get(i)))
						errors.add("",new ActionError("errors.input.1","�L���ԍ�("+ (i+1) +"�Ԗ�)","���p�p��"));
				}
			}else{  //�X�V

				boolean blank = true;
				for(int i = 0; i < rows.size();i++){
					FormRow row = getFormRow(i);
				
					if(row.kigoBan.equals("")){
						errors.add("",new ActionError("errors.update.notselected","�X�V"));    //�X�V�͌��������f�[�^�ɑ΂��čs���Ă�������
						return errors;
					}else if(!row.teiseiSuu.trim().equals("")){
						if(!StringUtils.isAsciiDigit(row.teiseiSuu.trim())){
							errors.add("",new ActionError("errors.input.1_row",String.valueOf(i+1),"������","���p����"));		
						}else{//2003/10/27 add
							if(!row.sign){
								if(Integer.parseInt(getSubMateStockVO(i).getHuksizaiZaiko()) + Integer.parseInt(row.teiseiSuu.trim()) > 999999)
									errors.add("",new ActionError("errors.input.incorrect","������"));
								}else{
									if(Integer.parseInt(getSubMateStockVO(i).getHuksizaiZaiko()) - Integer.parseInt(row.teiseiSuu.trim()) < (-999999))
										errors.add("",new ActionError("errors.input.incorrect","������"));
								}
						}
						if(!row.teiseiSuu.equals("0"))
							blank = false;
					}		
				}
				if(blank){
					errors.add("",new ActionError("errors.input.required","������"));
				}
//------------------------------------------------------------------------------------
			}  //  if(prcKbn().equals("1")){  �I��
		}  //if(command.equals(this.COMMAND_JIKKOU)){  �I��
		return errors.empty() ? null : errors;	  //���͔���I��
	
	}

//====================================================================================


	public void reset(ActionMapping maping,HttpServletRequest req){
		this.command = "INIT";
	}

//=====  �z��֋�̕�����̗v�f��ǉ����郁�\�b�h  ==========================================

	public ArrayList addBlankElement(ArrayList arr){

	for(int z = arr.size(); z<10; z++){
		arr.add("");
	}
		return arr;
	}



}
