/**
* SubMateMasterForm  �����ރ}�X�^�[�����e�i���X  �t�H�[���N���X
*
*	�쐬��    2003/0/16
*	�쐬��    �i�m�h�h�j����  ����
* �����T�v    Http���N�G�X�g�ƃ��N�G�X�g�̏������ʂ��i�[����N���X�B
*
*	[�ύX����]
*             ���t ���O
*  �E���e
*           2004/03/15�i�m�h�h�j�X
* 			�E������R�[�h�̓u�����N�o�^�ɕύX
* 
*/

package com.jds.prodord.master.submatemaster;

import org.apache.struts.action.*;

import javax.servlet.http.*;
import java.util.*;
import com.jds.prodord.common.*;

public class SubMateMasterForm extends ActionForm {


	public static final String COMMAND_JIKKOU = "���s";
	public static final String COMMAND_CLEAR = "�N���A";
		
	private String command = "";
	private ArrayList rows = new ArrayList();
	public static final int DEFAULT_ROW_COUNT = 1;

	private String queryDaiKaiSkbCod = "";
	private String queryKaiSkbCod = "";
	private ArrayList queryKaiSkbCodList = null;
	private String hyoKbn = ""; //add 2011/05/30
	
	private String prcKbn;
	private String[] kaiSkbCod = null;
	private String hukSziCod = "";

	private ArrayList vl_kaiSkbCod = new ArrayList();



//-----  ���������ݒ�  ------------------------------------------------------------------------

	public SubMateMasterForm(){
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

	//*** �����敪��get/set  ***
	public String getPrcKbn() {
		return prcKbn;
	}
	public void setPrcKbn(String prcKbn) {
		this.prcKbn = prcKbn;
	}

    //*** ���͉�Ў��ʃR�[�h��get/set  ***
    public void setKaiSkbCod(String[] s) {
        this.kaiSkbCod = s;
    }
     public String[] getKaiSkbCod() {
        return this.kaiSkbCod;
    }

   //*** �����޺��ނ�get/set  ***
   	public String getHukSziCod() {
		return hukSziCod;
	}
	public void setHukSziCod(String hukSziCod) {
		this.hukSziCod = hukSziCod;
	}

	//*** �\���敪��get/set  ***
	public String getHyoKbn() {
		return hyoKbn;
	}
	public void setHyoKbn(String s) {
		this.hyoKbn = s;
	}


//------------------------------------------------------

	//***  ��Ў��ʃR�[�h  ***
	public Collection getVl_kaiSkbCod(){
		return (Collection)vl_kaiSkbCod;
	}

	public void setKaiSkbCodOptions(ArrayList queryKaiSkbCodList){
		vl_kaiSkbCod.clear();
		for(int i = 0; i<queryKaiSkbCodList.size(); i++){
			vl_kaiSkbCod.add(queryKaiSkbCodList.get(i)+"");
		}
	}


	//-------------------------------------------------------------

	//	�o�����[�I�u�W�F�N�g�̊i�[
	private ArrayList voList = new ArrayList();

    public void setSubMateMasterVO(ArrayList arr){
    	voList.clear();
		voList.addAll(arr);
	}


	public void addSubMateMasterVO(SubMateMasterVO fmVO){
		voList.add(fmVO);
	}

	public ArrayList getSubMateMasterVO(){
		return voList;	
	}

	public void setSubMateMasterVO(int i, SubMateMasterVO fmVO){
		voList.set(i, fmVO);
	}

    public SubMateMasterVO getSubMateMasterVO(int i){
		return (SubMateMasterVO)voList.get(i);	
	}

	public void removeSubMateMasterVO(){
		voList.clear();
	}

	public void removeSubMateMasterVO(int i){
    	voList.remove(i);
	}


	//�`�F�b�N�{�b�N�X�����Z�b�g���郁�\�b�h
	public void setCheck_false(){
		for(int i = 0; i < this.getSize();i++){
			this.getFormRow(i).setExecute(false);
		}
	}

//---------------------------------------------------------------

     //�����Ώ�
    public boolean getExecute(int i){
    	return getFormRow(i).execute;
    }
    public void setExecute(int i,boolean b){
    	getFormRow(i).execute = b;
    }

     //��\��Ў��ʃR�[�h
    public String getHidDaiKaiSkbCod(int i){
    	return getFormRow(i).hidDaiKaiSkbCod;
    }
    public void setHidDaiKaiSkbCod(int i , String s){
    	getFormRow(i).hidDaiKaiSkbCod = s;
    }

     //��Ў��ʺ���
    public String getHidKaiSkbCod(int i){
    	return getFormRow(i).hidKaiSkbCod;
    }
    public void setHidKaiSkbCod(int i , String s){
    	getFormRow(i).hidKaiSkbCod = s;
    }


    //�����ރR�[�h
    public String getOutHukSziCod(int i){
    	return getFormRow(i).outHukSziCod;
    }
    public void setOutHukSziCod(int i , String s){
    	getFormRow(i).outHukSziCod = s;
    }

     //���ރR�[�h
    public String getOutBunruiCod(int i){
    	return getFormRow(i).outBunruiCod;
    }
    public void setOutBunruiCod(int i , String s){
    	getFormRow(i).outBunruiCod = s;
    }

      //�����溰��
    public String getOutHatcCod(int i){
    	return getFormRow(i).outHatcCod;
    }
    public void setOutHatcCod(int i , String s){
    	getFormRow(i).outHatcCod = s;
    }


     //�����ޖ�
    public String getOutHukSziMei(int i){
    	return getFormRow(i).outHukSziMei;
    }
    public void setOutHukSziMei(int i , String s){
    	getFormRow(i).outHukSziMei = s;
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

//======================================================

	public void clearAll(){
		command = "";
		
		prcKbn = "";
        this.kaiSkbCod = null;
		this.hukSziCod = "";
		setSize(DEFAULT_ROW_COUNT);
		clearTableField();
//	System.out.println("�t�B�[���h���̒l���N���A���܂����B");
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

		private String hidDaiKaiSkbCod = "";		
		private String hidKaiSkbCod = "";		
		private String outHukSziCod = "";
		private String outBunruiCod   = "";
		private String outHatcCod = "";
		private String outHukSziMei = "";
		private int hidUpdDte;
		private int hidUpdJkk;
			    
	//---------------------------------------------------
	     //�����Ώ�
	    public boolean getExecute(){
	    	return execute;
	    }
	    public void setExecute(boolean b){
	    	execute = b;
	    }

		//***  hid��\��Ў��ʺ���  ***
	    public String getHidDaiKaiSkbCod(){
	    	return hidDaiKaiSkbCod;
	    }
	    public void setHidDaiKaiSkbCod(String hidDaiKaiSkbCod){
	    	this.hidDaiKaiSkbCod = hidDaiKaiSkbCod;
	    }

		//***  hid��Ў��ʺ���  ***
	    public String getHidKaiSkbCod(){
	    	return hidKaiSkbCod;
	    }
	    public void setHidKaiSkbCod(String hidKaiSkbCod){
	    	this.hidKaiSkbCod = hidKaiSkbCod;
	    }

		//***  out�����ރR�[�h  ***
	    public String getOutHukSziCod(){
	    	return outHukSziCod;
	    }
	    public void setOutHukSziCod(String outHukSziCod){
	    	this.outHukSziCod = outHukSziCod;
	    }

		//***  out���ރR�[�h  ***
	    public String getOutBunruiCod(){
	    	return outBunruiCod;
	    }
	    public void setOutBunruiCod(String outBunruiCod){
	    	this.outBunruiCod = outBunruiCod;
	    }

		//***  out������R�[�h  ***
	    public String getOutHatcCod(){
	    	return outHatcCod;
	    }
	    public void setOutHatcCod(String outHatcCod){
	    	this.outHatcCod = outHatcCod;
	    }

		//***  out�����ޖ�  ***
	    public String getOutHukSziMei(){
	    	return outHukSziMei;
	    }
	    public void setOutHukSziMei(String outHukSziMei){
	    	this.outHukSziMei = outHukSziMei;
	    }
		//***  hid�X�V��  ***
	    public int getHidUpdDte(){
	    	return hidUpdDte;
	    }
	    public void setHidUpdDte(int hidUpdDte){
	    	this.hidUpdDte = hidUpdDte;
	    }
		//***  hid�X�V����  ***
	    public int getHidUpdJkk(){
	    	return hidUpdJkk;
	    }
	    public void setHidUpdJkk(int hidUpdJkk){
	    	this.hidUpdJkk = hidUpdJkk;
	    }




//--------------------------------------------
	
		public void clear(){
			execute = false;

			hidKaiSkbCod = "";		
			outHukSziCod = "";
			outBunruiCod = "";
			outHatcCod = "";
			outHukSziMei = "";
//			hidUpdDte = 0;
//			hidUpdJkk = 0;

		}
		
	}


//=====  �l���͔���  ================================================================

	public ActionErrors validate(ActionMapping mapping,HttpServletRequest req){
		HttpSession session = req.getSession();
		//���b�Z�[�W���Z�b�V���������菜��
		session.removeAttribute(CommonConst.INFOMSG_KEY);
		
		if(command.equals("INIT"))
			return null;

		ActionErrors errors = new ActionErrors();

		if(command.equals(COMMAND_JIKKOU)){

			//�X�V�����`�F�b�N
			if(!prcKbn.equals("1")){
				boolean authorityChecked = false;
				authorityChecked = CheckCommon.checkHyoKbn(this.getQueryDaiKaiSkbCod(),this.getHyoKbn());
				if(!authorityChecked){
					errors.add("",new ActionError("errors.cannot","���̃A�J�E���g��","�o�^�^�X�V�^�폜��"));
					return errors;	
				}
			}

			//String�^ prcKbn �� int�^ kubun�ɕϊ�
			final int kubun = Integer.parseInt(prcKbn);

			boolean select = false;  //�����Ώ۔���p�t���O

			switch(kubun){
			//-----  �i�Ɖ�j   --------------------------------------------
			case 1:
				//***  ��Ў��ʃR�[�h�̓��͔���  ***
				//���ɂȂ�

				//***  �����޺��ނ̓��͔���  ***
				if(!hukSziCod.equals("") && !StringUtils.isAscii(hukSziCod.trim()))
					errors.add("",new ActionError("errors.input.1","�����ރR�[�h","���p�p��"));

				break;
			//-----  (�o�^)  --------------------------------------------
			case 2:
				//***  �������ʃN���A����  ***
				for(int i = 0; i < rows.size();i++){
					FormRow row = getFormRow(i);
					if(!row.outHukSziCod.trim().equals("")){
						select = true;
						break;
					}
				}

				if(select){
					errors.add("",new ActionError("errors.insert.exist_results"));
					return errors;
				}
				//***  ��Ў��ʃR�[�h�̓��͔���  ***
				//���ɂȂ�

				//***  �����޺��ނ̓��͔���  ***
				if(hukSziCod.equals(""))
					errors.add("",new ActionError("errors.input.required","�����ރR�[�h"));

				if(!StringUtils.isAscii(hukSziCod.trim()))
					errors.add("",new ActionError("errors.input.1","�����ރR�[�h","���p�p��"));					
				else if(hukSziCod.trim().length() != 3)
					errors.add("",new ActionError("errors.input.1","�����ރR�[�h","�R��"));

				//***  ���ރR�[�h�̓��͔���  ***
				for(int i = 0; i < rows.size();i++){
					FormRow row = getFormRow(i);
					if(row.outBunruiCod.trim().equals(""))
						errors.add("",new ActionError("errors.input.required","���ރR�[�h"));
					else if(!StringUtils.isAsciiDigit(row.outBunruiCod.trim()))
						errors.add("",new ActionError("errors.input.1","���ރR�[�h","���p����"));
					else if(!row.outBunruiCod.trim().equals("1") &&!row.outBunruiCod.trim().equals("2"))
						errors.add("",new ActionError("errors.input.1","���ރR�[�h","1�܂���2"));

				}
								
				//***  �����溰�ނ̓��͔���  ***
				for(int i = 0; i < rows.size();i++){
					FormRow row = getFormRow(i);
//2004.03.15�@upd ������R�[�h�̓X�y�[�X�ł��ɕύX
//					if(row.outHatcCod.trim().equals(""))
//						errors.add("",new ActionError("errors.input.required","������R�[�h"));
//					else if(!StringUtils.isAsciiDigit(row.outHatcCod.trim()))
					if(!row.outHatcCod.trim().equals("")){
						if(!StringUtils.isAsciiDigit(row.outHatcCod.trim()))
							errors.add("",new ActionError("errors.input.1","������R�[�h","���p����"));
					}
				}

				//***  �����ޖ��̂̓��͔���  ***
				for(int i = 0; i < rows.size();i++){
					FormRow row = getFormRow(i);
					if(row.outHukSziMei.trim().equals(""))
						errors.add("",new ActionError("errors.input.required","�����ޖ�"));
					if(StringUtils.containsAsciiOrHarfWidthKatakana(row.outHukSziMei.trim()))
							errors.add("",new ActionError("errors.input.1","�����ޖ�","�S�p"));
					break;
				}

				break;

			//-----  �i�ύX�j  --------------------------------------------
			case 3:  
				for(int i = 0; i < rows.size();i++){
					FormRow row = getFormRow(i);
				
					if(row.outHukSziCod.trim().equals("")){
						errors.add("",new ActionError("errors.update.notselected","�X�V"));    //�X�V�͌��������f�[�^�ɑ΂��čs���Ă�������
						select = true;
						break;
					}

					if(row == null || !row.execute)
						continue;
					else
						select = true;

//		System.out.println("Form:select:" + select);

					boolean flag = false;

					//***  ���ރR�[�h�̓��͔���  ***
					if(row.outBunruiCod.trim().equals(""))
						errors.add("",new ActionError("errors.input.required","���ރR�[�h"));

					if(!StringUtils.isAsciiDigit(row.outBunruiCod.trim()))
						errors.add("",new ActionError("errors.input.1_row",String.valueOf(i+1),"���ރR�[�h","���p"));
					else if(!row.outBunruiCod.trim().equals("1") &&!row.outBunruiCod.trim().equals("2"))
						errors.add("",new ActionError("errors.input.1_row",String.valueOf(i+1),"���ރR�[�h","1�܂���2"));

					//***  �����溰�ނ̓��͔���  ***
					if(!row.outHatcCod.trim().equals("")){
						if(!StringUtils.isAsciiDigit(row.outHatcCod.trim()))
							errors.add("",new ActionError("errors.input.1_row",String.valueOf(i+1),"������R�[�h","���p"));
					}

					//***  �����ޖ��̓��͔���  ***
					if(row.outHukSziMei.trim().equals(""))
						errors.add("",new ActionError("errors.input.required","�����ޖ�"));
					if(StringUtils.containsAsciiOrHarfWidthKatakana(row.outHukSziMei.trim()))
						errors.add("",new ActionError("errors.input.1_row",String.valueOf(i+1),"�����ޖ�","�S�p"));
				}

				if(!select)
					errors.add("",new ActionError("errors.input.required","�����Ώ�"));  //�����Ώۂ��w�肵�Ă�������

				break;
			
			//-----  �i�폜�j  --------------------------------------------
			case 4:

				for(int i = 0; i < rows.size();i++){
					FormRow row = getFormRow(i);
					if(row.outBunruiCod.equals("")||row.outHukSziMei.equals("")){
						errors.add("",new ActionError("errors.update.notselected","�폜"));    //�폜�͌��������f�[�^�ɑ΂��čs���Ă�������
						select = true;
						break;
					}

					if(row == null || !row.execute)
						continue;
					else
						select = true;
				}

				if(!select)
					errors.add("",new ActionError("errors.input.required","�����Ώ�"));  //�����Ώۂ��w�肵�Ă�������

				break;

//--------------------------------------------------------------------------------------
			}  //switch�I��
		}  //if(command.equals(this.COMMAND_JIKKOU)){  �I��

		//���͔���I��
		return errors.empty() ? null : errors;	
	}


//====================================================================================

	public void reset(ActionMapping maping,HttpServletRequest req){
		this.command = "INIT";
		setCheck_false();
	}


}

