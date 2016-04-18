/**
* PrsOrderForm  �v���X�E�����ޔ�����ʃt�H�[���N���X
*
*	�쐬��    2003/04/28
*	�쐬��    �i�m�h�h�j���c �Ĕ�
* �����T�v    Http���N�G�X�g�ƃ��N�G�X�g�̏������ʂ��i�[����N���X�B
*
*	[�ύX����]
* 		2003/05/01�i�m�h�h�j�g�c �h�q
* 			�E���̓`�F�b�N�ǉ�
* 		2003/01/13�i�m�h�h�j�X�@���₩
* 			�E�[����N���A�ǉ��ɔ����C��
* 		2004/03/08�@(�m�h�h) �X
* 			�E�V���敪"���̑�"�ǉ��ɔ����C��
*		2005/02/03�i�m�h�h�j�g�c
*			�E�ꏊ�R�[�h�̒ǉ�
*		2005/05/26�i�m�h�h�j�g�c
*			�E�������}�C�i�X���͉ɏC��
*		2005/09/07�i�m�h�h�j�g�c
*			�E�����ޔ�����ʁA�����ރR�[�h���v���_�E�����ڂɕύX�iVAP�БΉ��j
*		2005/09/21�i�m�h�h�j�g�c
*			�E�[���ꊇ�ύX�@�\�ǉ�
*/

package com.jds.prodord.order.prsorder;

import org.apache.struts.action.*;

import javax.servlet.http.*;
import java.util.*;
import com.jds.prodord.common.*;

public class PrsOrderForm extends ActionForm {

	public static final String COMMAND_HACHUSHIJI = "COMMAND_HACHUSHIJI";
	public static final String COMMAND_DENPYOHAKKOU = "COMMAND_DENPYOHAKKOU";
	public static final String COMMAND_PRSHACHU = "COMMAND_PRSHACHU";
	public static final String COMMAND_FKUSHIZAIHACHU = "COMMAND_FKUSHIZAIHACHU";
	public static final String COMMAND_KBNHENKO = "COMMAND_KBNHENKO";	
	public static final String COMMAND_NHNCLEAR = "COMMAND_NHNCLEAR";//2004.01.13 add
	public static final String COMMAND_NKICHANGE = "COMMAND_NKICHANGE";//2005.09.21 add
	
	public static final String ManualOrder = "ManualOrder";
	public static final String ManualOrderPaste = "ManualOrderPaste";
	public static final String IkkatsuRefer = "IkkatsuRefer";
	
	public static final String PRSHACHU = "0";
	public static final String FUKHACHU = "1";
	
	public static final String SUCCESS_HACHUSHIJI = "SUCCESS_HACHUSHIJI";
	public static final String SUCCESS_DENPYOHAKKOU = "SUCCESS_DENPYOHAKKOU";
	public static final String SUCCESS_KBNHENKO = "SUCCESS_KBNHENKO";
	public static final String SUCCESS_NHNCLEAR = "SUCCESS_NHNCLEAR";//2004.01.13 add
	public static final String SUCCESS_NKICHANGE = "SUCCESS_NKICHANGE";//2005.09.21 add
	
	public static final String COMMAND_CLEAR = "COMMAND_CLEAR";
	public static final String COMMAND_BACK = "COMMAND_BACK";
	
	public static final String NEXT = "NEXT";
	public static final String PREV = "PREV";
	public static final String SUCCESS_PREVNEXT = "SUCCESS_PREVNEXT";
	
//	public static final int MAX_ROW_COUNT = 5;
	public static final int MAX_ROW_COUNT = 100;
	
    private ArrayList details = new ArrayList();

	private String command = "";
    private String count = "";
    private String newWindow = "";//����p��ʂ��J�����ǂ����̃t���O
    private String pre_page = "";//�O�̃y�[�W�i�ǂ̃y�[�W����J�ڂ��Ă������j
    private String prs_FukSgn = "";//�v���X������ʂ������ޔ�����ʂ��̃T�C��
    
    private ArrayList check_prs1_index = new ArrayList();
    private ArrayList check_fuk1_index = new ArrayList();
    
    private int currentPage;//���݂̃y�[�W
    private int disp_currentPage;//���݂̃y�[�W�i�\���p�j
    private int pageCount;//�y�[�W��
    private int referredMaxPage;//�Q�Ƃ��ꂽ�ő�y�[�W
    private int allRowCount;//�S�y�[�W�̌���
    
	private String queryDaiKaiSkbCod = "";
	private String queryKaiSkbCod = "";
	private ArrayList queryKaiSkbCodList = null;
	private String bshCod = "";
	private String hyoKbn = "";
	
	private String kbn = "";
	
	public ArrayList fukSziList = new ArrayList(); //2005/09/07 add
	private String nki_Y ="";
	private String nki_M ="";
	private String nki_D ="";


	public PrsOrderForm(){
		super();
//System.out.println("�R���X�g���N�^ : "+command);
		this.removePrsOrderVO();
		this.setCommand("INIT");
	}
	
	

	//-----------------------------------------------------------
	//-----------------------------------------------------------
	//�o�����[�I�u�W�F�N�g�̊i�[
	private ArrayList voList = new ArrayList();
	
    public void setPrsOrderVO(ArrayList arr){
    	
    	voList.clear();
		voList.addAll(arr);
		
	}
	public void addPrsOrderVO(PrsOrderVO fmVO){

		voList.add(fmVO);
		
	}
	public ArrayList getPrsOrderVO(){
		ArrayList arr = new ArrayList();
		for(int i = 0; i < voList.size(); i++){
			arr.add(voList.get(i));
		}
		return arr;	
		
	}
    public PrsOrderVO getPrsOrderVO(int i){
		
		return (PrsOrderVO)voList.get(i);	
		
	}
	public void setPrsOrderVO(int i, PrsOrderVO fmVO){
    	
		voList.set(i, fmVO);
		
	}
	public void removePrsOrderVO(){
    	
		voList.clear();
		
	}	
	public void removePrsOrderVO(int i){
    	
		voList.remove(i);
		
	}
	

	//-----------------------------------------------------------�t�b�^�[
	 //�������{�^��
	public String getCommand(){
		return command;
	}
	public void setCommand(String s){
		command = s;
	}
	
	//�敪
	public String getKbn(){
		return kbn;
	}
	public void setKbn(String s){
		kbn = s;
	}


	//-----------------------------------------------------------���o��
	
     //��\��Ў��ʃR�[�h
    public String getQueryDaiKaiSkbCod(){
    	return queryDaiKaiSkbCod;
    }
    public void setQueryDaiKaiSkbCod(String s){
    	queryDaiKaiSkbCod = s;
    }
    //��Ў��ʃR�[�h
    public String getQueryKaiSkbCod(){
    	return queryKaiSkbCod;
    }
    public void setQueryKaiSkbCod(String s){
    	queryKaiSkbCod = s;
    }
    
    //��Ў��ʃR�[�h
    public ArrayList getQueryKaiSkbCodList(){
    	return queryKaiSkbCodList;
    }
    public void setQueryKaiSkbCodList(ArrayList s){
    	queryKaiSkbCodList = s;
    }
//--------------------------------------------------------------------

    /**
     * �����̃Z�b�g
     */
    public void setCount(int i) {
        this.count = i+"";
    }
    
    /**
     * �����̎擾
     */
    public String getCount() {
        return this.count;
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
	 * pre_page�̎擾
	 * 
	 * @return pre_page
	 */
	public String getPre_page() {
		
		return pre_page;
		
	}
	/**
	 * pre_page�̃Z�b�g
	 * 
	 * @param pre_page
	 */
	public void setPre_page(String pre_page) {
		
		this.pre_page = pre_page;
		
	}
	
	/**
	 * �v���X������ʂ������ޔ�����ʂ��̃T�C���̎擾
	 * 
	 * @param prs_FukSgn
	 */
	public String getPrs_FukSgn() {
		
		return prs_FukSgn;
		
	}
	/**
	 * �v���X������ʂ������ޔ�����ʂ��̃T�C���̃Z�b�g
	 * 
	 * @param prs_FukSgn
	 */
	public void setPrs_FukSgn(String prs_FukSgn) {
		
		this.prs_FukSgn = prs_FukSgn;
		
	}

	/**
	 * @return
	 */
	public String getNki_Y() {
		return nki_Y;
	}

	/**
	 * @param string
	 */
	public void setNki_Y(String string) {
		nki_Y = string;
	}

	/**
	 * @return
	 */
	public String getNki_M() {
		return nki_M;
	}

	/**
	 * @param string
	 */
	public void setNki_M(String string) {
		nki_M = string;
	}

	/**
	 * @return
	 */
	public String getNki_D() {
		return nki_D;
	}

	/**
	 * @param string
	 */
	public void setNki_D(String string) {
		nki_D = string;
	}


	//--------------------------------------------------------------------


    /**
     * ���݂̃y�[�W�̃Z�b�g
     * 
     * @param currentPage ���݂̃y�[�W
     */
    public void setCurrentPage(int currentPage) {


        this.currentPage = currentPage;


    }
    /**
     * ���݂̃y�[�W�̎擾
     * 
     * @return ���݂̃y�[�W
     */
    public int getCurrentPage() {


        return this.currentPage;


    }
    
    /**
     * ���݂̃y�[�W�̃Z�b�g�i�\���p�j
     * 
     * @param disp_currentPage ���݂̃y�[�W�i�\���p�j
     */
    public void setDisp_currentPage(int disp_currentPage) {


        this.disp_currentPage = disp_currentPage;


    }
    /**
     * ���݂̃y�[�W�̎擾�i�\���p�j
     * 
     * @return ���݂̃y�[�W�i�\���p�j
     */
    public int getDisp_currentPage() {
		int i = this.currentPage + 1;
        return i;


    }
    /**
     * �y�[�W���̃Z�b�g
     * 
     * @param pageCount �y�[�W��
     */
    public void setPageCount(int pageCount) {


        this.pageCount = pageCount;


    }
    /**
     * �y�[�W���̎擾
     * 
     * @return �y�[�W��
     */
    public int getPageCount() {


        return this.pageCount;


    }
    
    /**
     * �Q�Ƃ��ꂽ�ő�y�[�W�̃Z�b�g
     * 
     * @param referredMaxPage �Q�Ƃ��ꂽ�ő�y�[�W
     */
    public void setReferredMaxPage(int referredMaxPage) {
		
		if(this.referredMaxPage < referredMaxPage)
        	this.referredMaxPage = referredMaxPage;

    }
    public void clearReferredMaxPage() {

        	this.referredMaxPage = 0;

    }
    /**
     * �Q�Ƃ��ꂽ�ő�y�[�W�̎擾
     * 
     * @return �Q�Ƃ��ꂽ�ő�y�[�W
     */
    public int getReferredMaxPage() {


        return this.referredMaxPage;


    }
     /**
     * �S�y�[�W�̌����̃Z�b�g
     * 
     * @param allRowCount �S�y�[�W�̌���
     */
    public void setAllRowCount(int allRowCount) {


        this.allRowCount = allRowCount;


    }
    /**
     * �S�y�[�W�̌����̎擾
     * 
     * @return �S�y�[�W�̌���
     */
    public int getAllRowCount() {


        return this.allRowCount;


    }
    /**
     * VO�̃��X�g�i�y�[�W�P�ʁj�̎擾
     * 
     * @return VO�̃��X�g�i�y�[�W�P�ʁj
     */
    public PrsOrderVO[] getVosList(int i) {
    	ArrayList arr = null;
    	try{
//System.out.println("i * MAX_ROW_COUNT : (i + 1) * MAX_ROW_COUNT  "+i * MAX_ROW_COUNT+" : "+(i + 1) * MAX_ROW_COUNT);
//System.out.println("voList.size() : "+voList.size());
			int max = (voList.size() < ((i + 1) * MAX_ROW_COUNT))? voList.size() : (i + 1) * MAX_ROW_COUNT;

			arr = new ArrayList(this.voList.subList(i * MAX_ROW_COUNT, max));
//System.out.println("arr.size() : "+arr.size());
    	}catch(IllegalArgumentException e){
			return null;
		}
        return (PrsOrderVO[])arr.toArray(new PrsOrderVO[arr.size()]);
    }
    
	
	//---------------------------------------------------------------------------------
	public int getSize(){
		return details.size();
	}
	
	public void setSize(int size){
		if(size > details.size()){
			int j = size - this.getSize();			
		  for(int k=0; k<j; k++){
		    details.add(new PrsOrderFormRow());			  	
		  }						
		}else if(size < details.size()){
			for(int i = details.size();i > size;i--){
				details.remove((i-1));
			}
		}
	}

	
	public PrsOrderFormRow getFormRow_R(int i){
		PrsOrderFormRow fr = (PrsOrderFormRow)details.get(i);
		if(fr == null){
			fr = new PrsOrderFormRow();
			details.set(i,fr);
		}
		return fr;
	}
	

	public void clearTableField(){
		for(int i = 0;i < details.size();i++){
			PrsOrderFormRow fr = (PrsOrderFormRow)details.get(i);
			if(fr != null)
	            details.set(i,new PrsOrderFormRow());			
		}	
	}
	

	/**
	 * �ꏊ�R�[�h�̎擾
	 * @return
	 */
	public String getBshCod() {
		return bshCod;
	}

	/**
	 * �ꏊ�R�[�h�̃Z�b�g
	 * @param string
	 */
	public void setBshCod(String string) {
		bshCod = string;
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


	public void clearAll(){
		command = "";
		queryDaiKaiSkbCod = "";
		clearTableField();		
	}
		
	public ArrayList getFormRowList_R(){
		return details;
	}
	
	public void setFormRowList_R(ArrayList arr){
		details = arr;
	}
	
	public void removeAllRow(){
		details.clear();
	}
		
	//�`�F�b�N�{�b�N�X�����Z�b�g���郁�\�b�h
	public void setCheck_false(){
		for(int i = 0; i < this.getSize();i++){
			PrsOrderFormRow row  = this.getFormRow_R(i);
			row.setCheck_prs1(false);
			row.setCheck_fuk1(false);
		}
	}
	
	//�`�F�b�N����Ă���`�F�b�N�{�b�N�X�̃C���f�b�N�X
	
	public ArrayList getCheck_prs1_index(){
		return check_prs1_index;
	}
	public void setCheck_prs1_index(ArrayList arr){
		check_prs1_index = arr;
	}
	
	public ArrayList getCheck_fuk1_index(){
		return check_fuk1_index;
	}
	public void setCheck_fuk1_index(ArrayList arr){
		check_fuk1_index = arr;
	}
	
	public void setCheck_index(ArrayList arr1,ArrayList arr2){
		check_prs1_index = arr1;
		check_fuk1_index = arr2;
	}
	
	/**
	 * �����ރR�[�h�v���_�E�����ڎ擾
	 * @return
	 */
	public ArrayList getFukSziList() {
		return fukSziList;
	}

	/**
	 * �����ރR�[�h�v���_�E�����ڐݒ�
	 * @param list
	 */
	public void setFukSziList(ArrayList list) {
		fukSziList = list;
	}

	/**
	 * �n���ꂽ�����ޖ��̂̃��x�����擾���ĕԂ��܂�
	 */
	public String getFukSziNmOptionsLabel(String value){
		String ret = "";
		int i = 0;
		for (Iterator iter = getFukSziList().iterator(); iter.hasNext(); i++) {
			LabelValueBean lvb = (LabelValueBean) iter.next();
			if(lvb.getValue().equals(value)){
				ret = lvb.getLabel().substring(4);
				break;
			}
		}
		return ret;
	}
	//---------------------------------------------------------------------------------

	public ActionErrors validate(ActionMapping mapping,HttpServletRequest req){
//System.out.println("PrsOrderForm--> validate");
		HttpSession session = req.getSession();
		//���b�Z�[�W���Z�b�V���������菜��
		session.removeAttribute(CommonConst.INFOMSG_KEY);
		
		if(command.equals("INIT"))
			return null;
		if(command.equals(COMMAND_CLEAR))
			return null;
			
	
		ActionErrors errors = new ActionErrors();

		//�����w�� �܂��� �`�[���s �̂Ƃ�			
		if(command.equals(COMMAND_HACHUSHIJI)||command.equals(COMMAND_DENPYOHAKKOU)){
			//�X�V�����`�F�b�N
			boolean authorityChecked = false;
			authorityChecked = CheckCommon.checkHyoKbn(this.getQueryDaiKaiSkbCod(),this.getHyoKbn());
			if(!authorityChecked){
				errors.add("",new ActionError("errors.cannot","���̃A�J�E���g��","�o�^�^�X�V�^�폜��"));
				return errors;	
			}
			//�܂��Q�Ƃ���Ă��Ȃ��y�[�W����������
			if(this.referredMaxPage < this.voList.size() / MAX_ROW_COUNT){
				errors.add("",new ActionError("errors.page.notreferredto"));
				return errors;
			}
		}
		//�����w�� �܂��� �`�[���s �܂��� �O100����100�� �̂Ƃ�			
		if(command.equals(COMMAND_HACHUSHIJI)||command.equals(COMMAND_DENPYOHAKKOU)
		||command.equals(NEXT)||command.equals(PREV)){

			DateUtils dateUtils = new DateUtils();
			int today = dateUtils.getDate6Int();
			
			for(int i = 0; i < this.getSize();i++){
				PrsOrderFormRow row  = this.getFormRow_R(i);
				
				if(row == null)
					continue;
				
				if(row.getCheck_prs1()){
//System.out.println("���̓`�F�b�N");
					/** �����ރR�[�h **/	//2004.03.02 add
  					if(this.getPrs_FukSgn().equals(PrsOrderForm.FUKHACHU)){
						if(row.getFukSziCod().trim().equals("")){
							//�t���i�\���}�X�^�Ƀf�[�^������Ƃ��̂�
							errors.add("",new ActionError("errors.regist_row",String.valueOf(i+1),"�����ރR�[�h"));
							continue;
						}
					}

					/** ������ **/
					if(row.getPrsHacSuu().trim().equals("")){
						errors.add("",new ActionError("errors.input.required_row",String.valueOf(i+1),"������"));
					}else{			
//						//���p�����̂�
//						if(!StringUtils.isAsciiDigit(row.getPrsHacSuu().trim())){
//							errors.add("",new ActionError("errors.input.1_row",String.valueOf(i+1),"������","���p����"));
//						}
						//�������}�C�i�X���͉ɏC�� 2005/05/26 add
						try{
							if(!StringUtils.isAscii(row.getPrsHacSuu().trim()))
								throw new NumberFormatException();
							int hacSuu = Integer.parseInt(row.getPrsHacSuu().trim());
						}catch (NumberFormatException e) {
							errors.add("",new ActionError("errors.input.1_row",String.valueOf(i+1),"�v���X������","���p����"));
						}
					}					

					/** �[�� **/
					if(row.getPrsNkiYear().trim().equals("") || row.getPrsNkiMonth().trim().equals("") || row.getPrsNkiDay().trim().equals("")){
						errors.add("",new ActionError("errors.input.required_row",String.valueOf(i+1),"�[��"));
					}else{			
						//���t�̂�
						if(!CheckCommon.validateAsDate(row.getPrsNkiYear(),row.getPrsNkiMonth(),row.getPrsNkiDay())){
							errors.add("",new ActionError("errors.input.1_row",String.valueOf(i+1),"�[��","���t"));
						}
//						else{
//							int prsnki = Integer.parseInt(row.getPrsNkiYear().trim() + 
//									   	 StringUtils.lpad(row.getPrsNkiMonth().trim(),2,"0") + 
//										 StringUtils.lpad(row.getPrsNkiDay().trim(),2,"0"));
//							if(prsnki < today){
//								errors.add("",new ActionError("errors.input.1_row",String.valueOf(i+1),"�[��","�����ȍ~�̓��t"));
//							}
//						}2003/07/16 �`�F�b�N���O��
					}
					/** ������ **/
					if(row.getPrsMkrCod().trim().equals("")){
						errors.add("",new ActionError("errors.input.required_row",String.valueOf(i+1),"������"));
					}else{			
						//���p�p���̂�
						if(!StringUtils.isAscii(row.getPrsMkrCod().trim())){
							errors.add("",new ActionError("errors.input.1_row",String.valueOf(i+1),"������","���p�p��"));
						}
					}
					/** �[�i�� **/
					if(!row.getNhnMei().trim().equals("")){
						//�S�p�̂�
						if(StringUtils.containsAsciiOrHarfWidthKatakana(row.getNhnMei().trim())){
							errors.add("",new ActionError("errors.input.1_row",String.valueOf(i+1),"�[�i��","�S�p"));
						}
					}
					/** �R�����g **/
					if(!row.getComment().trim().equals("")){
						//�S�p�̂�
						if(StringUtils.containsAsciiOrHarfWidthKatakana(row.getComment().trim())){
							errors.add("",new ActionError("errors.input.1_row",String.valueOf(i+1),"�R�����g","�S�p"));
						}
					}
				}
						
			}
			
//			//�����Ώێw��Ȃ�
//			boolean executeChecked = false;
//			
//			if(command.equals(COMMAND_HACHUSHIJI) || command.equals(COMMAND_DENPYOHAKKOU)){
//					for(int i = 0; i < this.getSize();i++){
//						PrsOrderFormRow row  = this.getFormRow_R(i);
//						if(row != null && row.getCheck_prs1()){
//						executeChecked = true;
//							break;
//						}
//					}
//			}else if(command.equals(NEXT)||command.equals(PREV))//��100���O100��
//				executeChecked = true;
//				
//			if(!executeChecked){
//				errors.add("",new ActionError("errors.input.required","�����Ώ�"));
//			}
//			

		}
//		2004.03.08 add
		if(command.equals(COMMAND_KBNHENKO) && (this.getPrs_FukSgn().equals("0"))){
			if(this.getKbn().equals("5")){
				errors.add("",new ActionError("errors.input.prohibited","�v���X�����̏ꍇ�A�敪�̂��̑�"));
				return errors;
			}
		}		
		if(command.equals(COMMAND_PRSHACHU)){
			if(this.getKbn().equals("5")){
				errors.add("",new ActionError("errors.input.prohibited","�v���X�����̏ꍇ�A�敪�̂��̑�"));
				return errors;
			}
			PrsOrderLeftFrame leftFrame = (PrsOrderLeftFrame)session.getAttribute(CommonConst.BEAN_NAME_PRSORDER_LEFT);
			PrsOrderLeftRow formRow_L = leftFrame.getFormRow_L(0);
			if(formRow_L.getKbn().equals("���̑�")){
				errors.add("",new ActionError("errors.input.prohibited","�v���X�����̏ꍇ�A�敪�̂��̑�"));
				return errors;			
			}		
		}		
		
		return errors.empty() ? null : errors;
	}
	
	public void reset(ActionMapping maping,HttpServletRequest req){
//System.out.println("reset : "+command);			
		if(!this.command.equals(SUCCESS_DENPYOHAKKOU))
			this.setNewWindow("0");
		if(!this.command.equals(SUCCESS_HACHUSHIJI) 
		&& !this.command.equals(SUCCESS_DENPYOHAKKOU)
//		2004.01.13 add
//		&& !this.command.equals(this.SUCCESS_PREVNEXT) && !this.command.equals(this.SUCCESS_KBNHENKO))
		&& !this.command.equals(SUCCESS_PREVNEXT) 
		&& !this.command.equals(SUCCESS_KBNHENKO) 
		&& !this.command.equals(SUCCESS_NHNCLEAR)
		&& !this.command.equals(SUCCESS_NKICHANGE)) //2005/09/21 add
			this.command = "INIT";
		this.setCheck_false();//�`�F�b�N�{�b�N�X�����Z�b�g
	}
	


}

