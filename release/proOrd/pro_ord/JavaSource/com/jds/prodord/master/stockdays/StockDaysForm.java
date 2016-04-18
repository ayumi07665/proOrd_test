/**
* StockDaysForm  �݌ɓ����}�X�^�[�����e�i���X�t�H�[���N���X
*
*	�쐬��    2003/06/09
*	�쐬��    �i�m�h�h�j����  ����
* �����T�v    Http���N�G�X�g�ƃ��N�G�X�g�̏������ʂ��i�[����N���X�B
*
*	[�ύX����]
*            2003/08/01�i�m�h�h�j���c �Ĕ�
* 		�E�`�Ԗ��̃h���b�v�_�E���̃��x���ύX�i�R�[�h�̂� �� �R�[�h�Ɩ��̊����Q���L�Ɂj�B
* 
*/

package com.jds.prodord.master.stockdays;

import org.apache.struts.action.*;

import javax.servlet.http.*;
import java.util.*;
import com.jds.prodord.common.*;

public class StockDaysForm extends ActionForm {


	public static final String COMMAND_JIKKOU = "���s";
	public static final String COMMAND_CLEAR = "�N���A";
		
	private String command = "";
	private ArrayList rows = new ArrayList();
	public static final int DEFAULT_ROW_COUNT = 1;

	private String queryDaiKaiSkbCod = "";
	private String queryKaiSkbCod = "";
	private ArrayList queryKaiSkbCodList = null;
	
	private String prcKbn;
	private String[] kaiSkbCod = null;
	private String rank = "";
	private String[] ketCod = null;
	private String stockDays = "";
	private String hyoKbn = ""; //add 2011/05/30

	
	private ArrayList vl_kaiSkbCod = new ArrayList();
	private ArrayList vl_ketCod = new ArrayList();
	private ArrayList lb_ketCod = new ArrayList();


//-----  ���������ݒ�  ------------------------------------------------------------------------

	public StockDaysForm(){
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

    //*** ���̓����N�̃Z�b�g     ***
    public void setRank(String s) {
        this.rank = s;
    }
    public String getRank() {
        return this.rank;
    }

   //*** ���͌`�ԃR�[�h��get/set  ***
    public void setKetCod(String[] s) {
        this.ketCod = s;
    }
    public String[] getKetCod() {
        return this.ketCod;
    }



   //*** �݌ɓ����ꊇ������get/set  ***
    public void setStockDays(String s) {
        this.stockDays = s;
    }
    public String getStockDays() {
        return this.stockDays;
    }

    //*** �\���敪��get/set  ***
    public void setHyoKbn(String s) {
        this.hyoKbn = s;
    }
    public String getHyoKbn() {
        return this.hyoKbn;
    }


//------------------------------------------------------

	//***  �`�ԃR�[�h�E��Ў��ʃR�[�h  ***
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


	//-------------------------------------------------------------

	//	�o�����[�I�u�W�F�N�g�̊i�[
	private ArrayList voList = new ArrayList();

    public void setStockDaysVO(ArrayList arr){
    	
    	voList.clear();
		voList.addAll(arr);
		
	}


	public void addStockDaysVO(StockDaysVO fmVO){

		voList.add(fmVO);
		
	}

	public ArrayList getStockDaysVO(){
		
		return voList;	
		
	}

	public void setStockDaysVO(int i, StockDaysVO fmVO){

		voList.set(i, fmVO);
		
	}
    public StockDaysVO getStockDaysVO(int i){
		
		return (StockDaysVO)voList.get(i);	
		
	}

	public void removeStockDaysVO(){
    	
		voList.clear();
		
	}
	public void removeStockDaysVO(int i){
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


     //�݌ɓ���
    public String getOutStockDays(int i){
    	return getFormRow(i).outStockDays;
    }
    public void setOutStockDays(int i , String s){
    	getFormRow(i).outStockDays = s;
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
        this.rank = "";
        this.ketCod = null;
		this.stockDays = "";
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
		
		private String outKaiSkbCod = "";
		private String outRank   = "";
		private String outKetCod = "";
		private String outStockDays = "";
	    
	//---------------------------------------------------
	     //�����Ώ�
	    public boolean getExecute(){
	    	return execute;
	    }
	    public void setExecute(boolean b){
	    	execute = b;
	    }

		
	//***  Gets the outKaiSkbCod  
		public String getOutKaiSkbCod() {
			return outKaiSkbCod;
		}
		public void setOutKaiSkbCod(String outKaiSkbCod) {
			this.outKaiSkbCod = outKaiSkbCod;
		}

	//***  Gets the outRank  ***
		public String getOutRank() {
			return outRank;
		}
		public void setOutRank(String outRank) {
			this.outRank = outRank;
		}


	//***  Gets the outKetCod  ***
		public String getOutKetCod() {
			return outKetCod;
		}
		public void setOutKetCod(String outKetCod) {
			this.outKetCod = outKetCod;
		}

	
	//***  Gets the outStockDays  ***
		public String getOutStockDays() {
			return outStockDays;
		}
		public void setOutStockDays(String outStockDays) {
			this.outStockDays = outStockDays;
		}


	//-------------------------------------------------
	
		public void clear(){
			execute = false;
			outKaiSkbCod = "";
			outRank = "";
			outKetCod = "";
			outStockDays = "";
			
		}
		
		public boolean isBlank(){
			if(!outStockDays.equals(""))
				return false;

			return true;
		}


	}


//=====  �l���͔���  ================================================================

	public ActionErrors validate(ActionMapping mapping,HttpServletRequest req){
		HttpSession session = req.getSession();
		//���b�Z�[�W���Z�b�V���������菜��
		session.removeAttribute(CommonConst.INFOMSG_KEY);
		
		if(command.equals("INIT"))
			return null;
		if(rank == null || ketCod == null)//2003/06/25 add okada
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

			//-----  �i�Ɖ�j  ��Ў��ʃR�[�h�{(�����N or �`�ԃR�[�h)  --------------------------------------------
			case 1:
				//***  �݌ɓ����ꊇ�ύX�̓��͔���  ***
				if(!stockDays.trim().equals(""))
					errors.add("",new ActionError("errors.input.prohibited","�݌ɓ����ꊇ�ύX"));
				//***  �����Nor�`�ԃR�[�h���͑��ݔ���  ***
				if(rank.equals("")&&ketCod[0].equals(""))
					errors.add("",new ActionError("errors.input.required","�����N�A�܂��͌`�ԃR�[�h"));
				break;

			//-----  (�o�^)  ��Ў��ʃR�[�h�{�����N+�`�ԃR�[�h + �݌ɓ���  --------------------------------------------
			case 2:

				//***  �����N�̓��͔���  ***
				if(rank.equals(""))
					errors.add("",new ActionError("errors.input.required","�����N"));
				//***  �`�ԃR�[�h�̓��͔���  ***
				if(ketCod[0].equals(""))
					errors.add("",new ActionError("errors.input.required","�`�ԃR�[�h"));
				//***  �݌ɓ����ꊇ�ύX�̓��͔���  ***
				if(!stockDays.trim().equals(""))
					errors.add("",new ActionError("errors.input.prohibited","�݌ɓ����ꊇ�ύX"));
				//***  �݌ɓ����̓��͔���  ***
				for(int i = 0; i < rows.size();i++){
					FormRow row = getFormRow(i);
					if(row.outStockDays.trim().equals(""))
						errors.add("",new ActionError("errors.input.required","�݌ɓ���"));
					else if(!StringUtils.isAsciiDigit(row.outStockDays.trim()))
						errors.add("",new ActionError("errors.input.1","�݌ɓ���","���p����"));
					break;
				}
				//***  �������ʃN���A����  ***
				for(int i = 0; i < rows.size();i++){
					FormRow row = getFormRow(i);
						if(!row.outKaiSkbCod.trim().equals("")||!row.outRank.trim().equals("")||!row.outKetCod.trim().equals("")){
							select = true;
							break;
						}
				}	
				if(select)
					errors.add("",new ActionError("errors.insert.exist_results"));
				break;

			//-----  �i�ύX�j  �������ʂɑ΂��� + �`�F�b�N�{�b�N�X + �i�݌ɓ��� or �݌ɓ����ꊇ�ύX�j   --------------------------------------------
			case 3:  

				for(int i = 0; i < rows.size();i++){
					FormRow row = getFormRow(i);
				
					if(row.outKaiSkbCod.equals("")||row.outRank.equals("")||row.outKetCod.equals("")){
						errors.add("",new ActionError("errors.update.notselected","�X�V"));    //�X�V�͌��������f�[�^�ɑ΂��čs���Ă�������
						select = true;
						break;
					}

					if(row == null || !row.execute)
						continue;
					else
						select = true;

					boolean flag = false;

					//***  �݌ɓ����̓��͔���  ***
					if(row.outStockDays.equals("")) 
						errors.add("",new ActionError("errors.input.required_row",String.valueOf(i+1),"�݌ɓ���"));
					else if(!StringUtils.isAsciiDigit(row.outStockDays.trim()))
						errors.add("",new ActionError("errors.input.1_row",String.valueOf(i+1),"�݌ɓ���","���p����"));
				}

				//***  �݌ɓ����ꊇ�ύX�̓��͔���  ***				
				if(!stockDays.equals("") && !StringUtils.isAsciiDigit(stockDays.trim()))
					errors.add("",new ActionError("errors.input.1","�݌ɓ����ꊇ�ύX�̒l","���p����"));
			
				if(!select && stockDays.equals(""))
					errors.add("",new ActionError("errors.input.required","�����Ώۂ܂��͍݌ɓ����ꊇ�ύX"));  //�����Ώۂ��w�肵�Ă�������

				break;
			
			//-----  �i�폜�j  �������ʂɑ΂��� + �`�F�b�N�{�b�N�X  --------------------------------------------
			case 4:

				//***  �݌ɓ����ꊇ�ύX�̓��͔���  ***
				if(!stockDays.trim().equals(""))
					errors.add("",new ActionError("errors.input.prohibited","�݌ɓ����ꊇ�ύX"));

				for(int i = 0; i < rows.size();i++){
					FormRow row = getFormRow(i);
					if(row.outKaiSkbCod.equals("") || row.outRank.equals("") || row.outKetCod.equals("")){
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

