/**
* IkkatsuReferForm  �ꊇ�Ɖ��ʃt�H�[���N���X
*
*	�쐬��    2003/03/31
*	�쐬��    �i�m�h�h�j���c �Ĕ�
* �����T�v    Http���N�G�X�g�ƃ��N�G�X�g�̏������ʂ��i�[����N���X�B
*
*	[�ύX����]
* 		2003/09/10�i�m�h�h�j���c �Ĕ�
* 			�E�v���X�������������ލ݌Ɂ{�����ޖ����Ɍv���傫�������ꍇ�̃G���[�̕\�����@��ύX
* 			�i�s���ƂłȂ��A�S�̂ň�̃��b�Z�[�W�B�G���[�̂������s�̃Z����Ԃ�����j
* 		2005/05/23�i�m�h�h�j�g�c
* 			�E�������ꊇ�o�͑Ή��i���[�U�[�h�c�̒ǉ��j
* 			�E�j�Ђ̏ꍇ�A�����ލ݌ɐ��̃`�F�b�N���O���悤�ɕύX
* 			�E�������}�C�i�X���͉ɏC��
*/

package com.jds.prodord.reference.ikkatsurefer;

import org.apache.struts.action.*;

import javax.servlet.http.*;
import java.util.*;
import com.jds.prodord.common.*;
import com.jds.prodord.indicate.teiansuurefer.*;
public class IkkatsuReferForm extends ActionForm {

	public static final String COMMAND_PRSCOMMENT = "COMMAND_PRSCOMMENT";
	public static final String COMMAND_HACHUSHIJI = "COMMAND_HACHUSHIJI";
	public static final String COMMAND_DENPYOHAKKOU = "COMMAND_DENPYOHAKKOU";
	public static final String COMMAND_FKUSHIZAIHACHU = "COMMAND_FKUSHIZAIHACHU";
	
	public static final String COMMAND_TEIANSYOKAI = TeiansuuReferForm.COMMAND_TEIANSYOKAI;
	public static final String COMMAND_JIDOUHACHU = TeiansuuReferForm.COMMAND_JIDOUHACHU;
	
	public static final String SUCCESS_HACHUSHIJI = "SUCCESS_HACHUSHIJI";
	public static final String SUCCESS_DENPYOHAKKOU = "SUCCESS_DENPYOHAKKOU";
	public static final String COMMAND_CLEAR = "COMMAND_CLEAR";
	
	public static final String NEXT = "NEXT";
	public static final String PREV = "PREV";
	public static final String SUCCESS_PREVNEXT = "SUCCESS_PREVNEXT";
	
	public static final String ZAIKO_JUBUN = "�݌ɏ\��";
	public static final String JIKAI_HACHU = "���񔭒�";
	public static final String HACHU_HITYO = "�����K�v";
	
//	public static final int MAX_ROW_COUNT = 10;
	public static final int MAX_ROW_COUNT = 100;

    private ArrayList details = new ArrayList();

	private String command = "";
    private String count = "";
    private String newWindow = "";//����p��ʂ��J�����ǂ����̃t���O
    private ArrayList check_prs1_index = new ArrayList();
    private ArrayList check_prs2_index = new ArrayList();
    private ArrayList check_fuk1_index = new ArrayList();
    private ArrayList check_prsHacSuu_index = new ArrayList();
    
    private int currentPage;//���݂̃y�[�W
    private int disp_currentPage;//���݂̃y�[�W�i�\���p�j
    
    private int pageCount;//�y�[�W��
    private int referredMaxPage;//�Q�Ƃ��ꂽ�ő�y�[�W
    private int allRowCount;//�S�y�[�W�̌���
    private ArrayList vosList = new ArrayList();//VO�̃��X�g�i�y�[�W���Ɓj
    
    private HashMap ketCod_map = new HashMap();//�`�ԃR�[�h�ƌ`�Ԗ��̂��i�[
    
	private String queryDaiKaiSkbCod = "";
	private String queryKaiSkbCod = "";
		
	private String hyoKbn = "";//2011/05/25 add

	
	public IkkatsuReferForm(){
		super();
//System.out.println("�R���X�g���N�^ : "+command);
		this.removeIkkatsuReferVO();
		this.setCommand("INIT");
	}
	
	
	//-----------------------------------------------------------
	//-----------------------------------------------------------
	//�o�����[�I�u�W�F�N�g�̊i�[
	private ArrayList voList = new ArrayList();
	
    public void setIkkatsuReferVO(ArrayList arr){
    	
    	voList.clear();
		voList.addAll(arr);
		
	}
	public void addIkkatsuReferVO(IkkatsuReferVO fmVO){

		voList.add(fmVO);
		
	}
	public void setIkkatsuReferVO(int i, IkkatsuReferVO fmVO){
    	
		voList.set(i, fmVO);
		
	}
	public void setIkkatsuReferVO_all(int i, IkkatsuReferVO fmVO){
    	
		int pageIdx = i / MAX_ROW_COUNT;
		int rowIdx = i % MAX_ROW_COUNT;
//System.out.println(pageIdx+" : "+rowIdx);
		ArrayList pageVOs = (ArrayList)vosList.get(pageIdx);
		pageVOs.set(rowIdx,fmVO);

	}
	public void removeIkkatsuReferVO(){
    	
		voList.clear();
		
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
    //��Ў��ʃR�[�h
    public String getQueryKaiSkbCod(){
    	return queryKaiSkbCod;
    }
    public void setQueryKaiSkbCod(String s){
    	queryKaiSkbCod = s;
    }
    //�\���敪
    public String getHyoKbn(){
    	return hyoKbn;
    }
    public void setHyoKbn(String s){
    	hyoKbn = s;
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
    public static int getCurrentPage(int i) {


        return  i / MAX_ROW_COUNT;


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
     * VO�̃��X�g�i���ʑS�́j�̃Z�b�g
     * 
     * @param vosList VO�̃��X�g�i���ʑS�́j
     */
    public void setVosList(ArrayList vosList) {

        this.vosList = vosList;

    }
    /**
     * VO�̃��X�g�i���ʑS�́j�̎擾
     * 
     * @return VO�̃��X�g�i���ʑS�́j
     */
    public ArrayList getVosList() {

        return this.vosList;

    
    }
    /**
     * VO�̃��X�g�i�y�[�W�P�ʁj�̎擾
     * 
     * @return VO�̃��X�g�i�y�[�W�P�ʁj
     */
    public IkkatsuReferVO[] getVosList(int i) {
		ArrayList arr = (ArrayList)this.vosList.get(i);
        return (IkkatsuReferVO[])arr.toArray(new IkkatsuReferVO[arr.size()]);
    }
    /**
     * VO�̃��X�g�i�y�[�W�P�ʁj�̎擾
     * 
     * @return VO�̃��X�g�i�y�[�W�P�ʁj
     */
    public void setVosList(int i, ArrayList vos) {
		vosList.set(i, vos);
    }
    /**
     * �S�y�[�W��VO�̃��X�g�i���ʑS�́j�̎擾
     * 
     * @return �S�y�[�W��VO�̃��X�g�i���ʑS�́j
     */
    public ArrayList getAllVos() {
		ArrayList returnArr = new ArrayList();
		for(int i = 0; i < this.vosList.size(); i++){
			returnArr.addAll((Collection)vosList.get(i));
		}
        return returnArr;
    }
	/**
     * VO�̃��X�g�i���ʑS�́j�̍폜
     * 
     * 
     */
    public void removeVosList() {

        this.vosList.clear();

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

	//---------------------------------------------------------------------------------
	public int getSize(){
		return details.size();
	}
	
	public void setSize(int size){
		if(size > details.size()){
			int j = size - this.getSize();			
		  for(int k=0; k<j; k++){
		    details.add(new IkkatsuReferFormRow());			  	
		  }						
		}else if(size < details.size()){
			for(int i = details.size();i > size;i--){
				details.remove((i-1));
			}
		}
	}

	
	public IkkatsuReferFormRow getFormRow_R(int i){
		IkkatsuReferFormRow fr = (IkkatsuReferFormRow)details.get(i);
		if(fr == null){
			fr = new IkkatsuReferFormRow();
			details.set(i,fr);
		}
		return fr;
	}
	

	public void clearTableField(){
		for(int i = 0;i < details.size();i++){
			IkkatsuReferFormRow fr = (IkkatsuReferFormRow)details.get(i);
			if(fr != null)
	            details.set(i,new IkkatsuReferFormRow());			
		}	
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
			IkkatsuReferFormRow row  = this.getFormRow_R(i);
			row.setCheck_prs1(false);
			row.setCheck_prs2(false);
			row.setCheck_fuk1(false);
			row.setCheck_prsHacSuu(false);
		}
	}
	
	//�`�F�b�N����Ă���`�F�b�N�{�b�N�X�̃C���f�b�N�X
	
	public ArrayList getCheck_prs1_index(){
		return check_prs1_index;
	}
	public void setCheck_prs1_index(ArrayList arr){
		check_prs1_index = arr;
	}
	
	public ArrayList getCheck_prs2_index(){
		return check_prs2_index;
	}
	public void setCheck_prs2_index(ArrayList arr){
		check_prs2_index = arr;
	}
	
	public ArrayList getCheck_fuk1_index(){
		return check_fuk1_index;
	}
	public void setCheck_fuk1_index(ArrayList arr){
		check_fuk1_index = arr;
	}
	
	public ArrayList getCheck_prsHacSuu_index(){
		return check_prsHacSuu_index;
	}
	public void setCheck_prsHacSuu_index(ArrayList arr){
		check_prsHacSuu_index = arr;
	}
	
	public void setCheck_index(ArrayList arr1,ArrayList arr2,ArrayList arr3,ArrayList arr4){
		check_prs1_index = arr1;
		check_prs2_index = arr2;
		check_fuk1_index = arr3;
		check_prsHacSuu_index = arr4;
	}
	
	//---------------------------------------------------------------------------------

	public ActionErrors validate(ActionMapping mapping,HttpServletRequest req){
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
		}

		//�����w�� �܂��� �`�[���s �܂��� �v���X�R�����g���� �܂��� �����ޔ��� �̂Ƃ�	
		if(command.equals(COMMAND_HACHUSHIJI)||command.equals(COMMAND_DENPYOHAKKOU)||command.equals(COMMAND_PRSCOMMENT)||command.equals(COMMAND_FKUSHIZAIHACHU)){
			if(this.referredMaxPage < this.vosList.size()-1){//�܂��Q�Ƃ���Ă��Ȃ��y�[�W����������
				errors.add("",new ActionError("errors.page.notreferredto"));
				return errors;
			}
		}

		//�����w�� �܂��� �`�[���s �܂��� �v���X�R�����g���� �܂��� ��100���O100�� �̂Ƃ�				
		if(command.equals(COMMAND_HACHUSHIJI)||command.equals(COMMAND_DENPYOHAKKOU)||command.equals(COMMAND_PRSCOMMENT)
		 ||command.equals(NEXT)||command.equals(PREV)){
		 	

			DateUtils dateUtils = new DateUtils();
			int today = dateUtils.getDate6Int();
			
			boolean check_prs;
			boolean check_fuk;
			
			boolean prsHacSuu_err;
			boolean prsHacSuu_err2 = false;
			
			for(int i = 0; i < this.getSize();i++){
				IkkatsuReferFormRow row  = this.getFormRow_R(i);
				check_prs = false;
				check_fuk = false;
				prsHacSuu_err = false;
				
				if(row == null)
					continue;
				if(command.equals(COMMAND_HACHUSHIJI)){
					if(row.getCheck_prs1())
						check_prs = true;
				}else if(command.equals(COMMAND_DENPYOHAKKOU)){
					if(row.getCheck_prs2())
						check_prs = true;
				 //2003/05/08 ��v���X�R�����g���ͣ�̂Ƃ�
				}else if(command.equals(COMMAND_PRSCOMMENT)||command.equals(NEXT)||command.equals(PREV)){
					if(row.getCheck_prs1()||row.getCheck_prs2())
						check_prs = true;
				}

				if(check_prs){
					//�v���X������
					if(row.getPrsHacSuu().trim().equals("")){
						errors.add("",new ActionError("errors.input.required_row",String.valueOf(i+1),"�v���X������"));
						prsHacSuu_err = true;
					}else{
//						//���p�����̂�
//						if(!StringUtils.isAsciiDigit(row.getPrsHacSuu().trim())){
						//�������}�C�i�X���͉ɏC�� 2005/05/24 add
						try{
							if(!StringUtils.isAscii(row.getPrsHacSuu().trim()))
								throw new NumberFormatException();
							int hacSuu = Integer.parseInt(row.getPrsHacSuu().trim());
						}catch (NumberFormatException e) {
							errors.add("",new ActionError("errors.input.1_row",String.valueOf(i+1),"�v���X������","���p����"));
							prsHacSuu_err = true;
						}
					}
					//�v���X�[��
					if(row.getPrsNkiYear().trim().equals("") || row.getPrsNkiMonth().trim().equals("") || row.getPrsNkiDay().trim().equals("")){
						errors.add("",new ActionError("errors.input.required_row",String.valueOf(i+1),"�v���X�[��"));
					}else{			
						//���t�̂�
						if(!CheckCommon.validateAsDate(row.getPrsNkiYear(),row.getPrsNkiMonth(),row.getPrsNkiDay())){
							errors.add("",new ActionError("errors.input.1_row",String.valueOf(i+1),"�v���X�[��","���t"));
						}
//						else{
//							int prsnki = Integer.parseInt(row.getPrsNkiYear().trim() + 
//									   	 StringUtils.lpad(row.getPrsNkiMonth().trim(),2,"0") + 
//										 StringUtils.lpad(row.getPrsNkiDay().trim(),2,"0"));
//							if(prsnki < today){
//								errors.add("",new ActionError("errors.input.1_row",String.valueOf(i+1),"�v���X�[��","�����ȍ~�̓��t"));
//							}
//						}2003/07/16 �`�F�b�N���O��
					}
					//�v���X������
					if(row.getPrsMkrCod().trim().equals("")){
						errors.add("",new ActionError("errors.input.required_row",String.valueOf(i+1),"�v���X������"));
					}else{			
						//���p�p���̂�
						if(!StringUtils.isAscii(row.getPrsMkrCod().trim())){
							errors.add("",new ActionError("errors.input.1_row",String.valueOf(i+1),"�v���X������","���p�p��"));
						}
					}
					row.setHacSuu_errSgn(false);
					if(!queryDaiKaiSkbCod.equals(CommonConst.KAICOD_K)){ //�j�Ђ̏ꍇ�A�����ލ݌ɐ��̃`�F�b�N�͂��Ȃ� 2005/05/24 add					
						//���������p�`�F�b�N�{�b�N�X���`�F�b�N����Ă��Ȃ�������
						if(!row.getCheck_prsHacSuu()){
							if(!prsHacSuu_err){
								int prsHacSuu = (!row.getPrsHacSuu().trim().equals(""))?Integer.parseInt(row.getPrsHacSuu().trim()):0;
								int fukZaiSuu = (!row.getFukZaiSuu().equals(""))? Integer.parseInt(row.getFukZaiSuu()) : 0;
								int fukMnyKei = (!row.getFukMnyKei().equals(""))? Integer.parseInt(row.getFukMnyKei()) : 0;
								//�v���X�������������ލ݌Ɂ{�����ޖ����Ɍv���傫��������
								if(prsHacSuu > (fukZaiSuu + fukMnyKei)){
		//								errors.add("",new ActionError("errors.input.1_row",String.valueOf(i+1),"�v���X������","�����ލ݌ɐ��ȉ��̐���"));
									row.setHacSuu_errSgn(true);
									prsHacSuu_err2 = true;
								}
							}
						}
					}
				}		
			}
			if(prsHacSuu_err2)
				errors.add("",new ActionError("errors.input.1","�v���X������","�����ލ݌ɐ��ȉ�"));
		}
		
		
		return errors.empty() ? null : errors;
	}
	
	public void reset(ActionMapping maping,HttpServletRequest req){		
		if(!this.command.equals(SUCCESS_DENPYOHAKKOU))
			this.setNewWindow("0");
		if(!this.command.equals(SUCCESS_HACHUSHIJI) 
		&& !this.command.equals(SUCCESS_DENPYOHAKKOU)
		&& !this.command.equals(SUCCESS_PREVNEXT))
			this.command = "INIT";
		this.setCheck_false();//�`�F�b�N�{�b�N�X�����Z�b�g
	}

}