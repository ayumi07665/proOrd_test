/**
* FzkHinKsiPasteForm  �t���i�\���}�X�^�[�ꊇ�\��t���t�H�[���N���X
*
*	�쐬��    2007/09/30
*	�쐬��    �i�m�h�h�j�c��
* �����T�v    Http���N�G�X�g�ƃ��N�G�X�g�̏������ʂ��i�[����N���X�B
*
*	[�ύX����]
*
*/

package com.jds.prodord.master.fzkhinksipaste;

import org.apache.struts.action.*;

import javax.servlet.http.*;
import java.util.*;
import com.jds.prodord.common.*;

public class FzkHinKsiPasteForm extends ActionForm {
	
	public final int ROW_MAX = 200;
	
	public static final int NOT_HIN_EXIST = 0;//�i�ԂȂ�
	public static final int NOT_FKS_EXIST = 1;//�����ނȂ�
	public static final int NOT_SIR_EXIST = 2;//�d����Ȃ�
	public static final int MST08_EXIST   = 3;//�t���i�\���}�X�^�[�o�^�ς�
		
	private String command = "";
	LinkedList formRows = new LinkedList();
    
	private String queryDaiKaiSkbCod = "";
	private String queryKaiSkbCod = "";
	private String kaiSkbCod = "";
	private ArrayList queryKaiSkbCodList = null;
	
	private String cpPaste = ""; 
	private String cpPasteMode ="1";
	
	private String hyoKbn ="";
	//*****************************************************
	
	//���j���[���炫���Ƃ���true���Z�b�g
	private boolean fromMenuSgn = false;
	public boolean getFromMenuSgn(){
		return fromMenuSgn;
	}
	public void setFromMenuSgn(boolean fromMenuSgn){
			this.fromMenuSgn = fromMenuSgn;
	}
	
	public FzkHinKsiPasteForm(){
		super();
		this.clearAll();
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
    
	//��Ў��ʃR�[�h
	public String getKaiSkbCod(){
		return kaiSkbCod;
	}
	public void setKaiSkbCod(String s){
		kaiSkbCod = s;
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
    
	//---------------------------------------------------------------------------------

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

	public void clearAll(){
		command = "";
		
		setFormRows(new LinkedList());
		setCpPaste("");
		setCpPasteMode("1");

	}
	
	
	//---------------------------------------------------------------------------------

	public ActionErrors validate(ActionMapping mapping,HttpServletRequest req){
		HttpSession session = req.getSession();
		//���b�Z�[�W���Z�b�V���������菜��
		session.removeAttribute(CommonConst.INFOMSG_KEY);
		
		
		if(command.equals("INIT"))
			return null;
		if(command.equals(getMessage("button.clear")))
			return null;
		
		ActionErrors errors = new ActionErrors();
		
		//�\�t
		if(command.equals(getMessage("button.paste"))){
			//���͂Ȃ�
			if(StringUtils.isBlankOrNull(getCpPaste())){
				errors.add("",new ActionError("errors.input.required",getMessage("label.pastedeta")));
			}
			//�ő喾�א�����
			else if(DataFormatUtils.getDataRowCount(getCpPaste()) > ROW_MAX){
				errors.add("",new ActionError("errors.exceed",getMessage("label.pastedeta"),getMessage("label.maxpasterow")));
			}

		}
		//���s
		else if(command.equals(getMessage("button.execute"))){
			//�X�V�����`�F�b�N
			boolean authorityChecked = false;
			authorityChecked = CheckCommon.checkHyoKbn(this.getQueryDaiKaiSkbCod(),this.getHyoKbn());
			if(!authorityChecked){
				errors.add("",new ActionError("errors.cannot","���̃A�J�E���g��","�o�^�^�X�V�^�폜��"));
				return errors;	
			}

			//���ד��͗L���`�F�b�N
			boolean exist = false;
			
			int i = 0;
			ArrayList key = new ArrayList();

			for (Iterator iter = getFormRows().iterator(); iter.hasNext(); i++) {
				FzkHinKsiPasteFormRow row = (FzkHinKsiPasteFormRow) iter.next();
				// �i�Ԃ����͂���Ă��Ȃ�������A���̍s��
				if (StringUtils.isBlankOrNull(row.getKigBng())) {
					continue;
				}

				//���͂���				
				exist = true;
				// ���p�`�F�b�N
				if (!StringUtils.isAsciiOrHarfWidthKatakana(row.getKigBng())) {
					errors.add("ROW_"+i, new ActionError("errors.input.1",getMessage("label.kigbng"),"���p"));
				}

				// ����������
				if (StringUtils.isBlankOrNull(row.getFukSziCod())){
					errors.add("ROW_"+i, new ActionError("errors.input.required",getMessage("label.fukshizai")));
				}else
				if (!StringUtils.isAscii(row.getFukSziCod())) {
					errors.add("ROW_"+i, new ActionError("errors.ascii",getMessage("label.fukshizai")));
				}

				// �d��������
				if (StringUtils.isBlankOrNull(row.getSirSk())){
					errors.add("ROW_"+i, new ActionError("errors.input.required",getMessage("label.sirhaccod")));
				}else
				if (!StringUtils.isAscii(row.getSirSk())) {
					errors.add("ROW_"+i, new ActionError("errors.ascii",getMessage("label.sirhaccod")));
				}

				//�d���f�[�^�`�F�b�N

				if(key.indexOf(row.getKigBng().trim() + row.getFukSziCod().trim()) != -1){
					errors.add("ROW_"+i, new ActionError("errors.insert.duplicate"));
				}
				key.add(row.getKigBng().trim() + row.getFukSziCod().trim());

			}

			// ���ז����͂������ꍇ�A�G���[
			if (!exist)
				errors.add("", new ActionError("errors.input.requiredall"));
			
		}

		return errors.empty() ? null : errors;		
	}
	
	//---------------------------------------------------------------------------------

	public int getSize(){
		return formRows.size();
	}
	

	public void setSize(int size){
		if(size > formRows.size()){
			int j = size - this.getSize();
			for(int k=0; k<j; k++){
				formRows.add(new FzkHinKsiPasteFormRow());			  	
			}	
		}else if(size < formRows.size()){
			for(int i = formRows.size();i > size;i--){
				formRows.remove((i-1));
			}
		}
	}
	

	public FzkHinKsiPasteFormRow getFormRow(int i){
		FzkHinKsiPasteFormRow formRow = (FzkHinKsiPasteFormRow)formRows.get(i);
		if(formRow == null){
			formRow = new FzkHinKsiPasteFormRow();
			formRows.set(i,formRow);
		}
		return formRow;
	}


	public void clearTableField(){
		for(int i = 0;i < formRows.size();i++){
			FzkHinKsiPasteFormRow formRow = (FzkHinKsiPasteFormRow)formRows.get(i);
			if(formRow != null)
				formRow.clear();
		}
	}


	public void clearTableField(int i){
		FzkHinKsiPasteFormRow formRow = (FzkHinKsiPasteFormRow)formRows.get(i);
		if(formRow != null)
			formRow.clear();
	}


	public void removeTableField(int i){
		formRows.remove(i);
	}



//---------------------------------------------------------------------------------------------------
	
	public void reset(ActionMapping maping,HttpServletRequest req){
			this.command = "INIT";
	}
	


	//-------------------------------------------------------------���b�Z�[�W
	
	/**
	 * ���\�[�X�t�@�C������A�w�肳�ꂽ�L�[�ɑΉ����郁�b�Z�[�W���擾���܂��B
	 * @param key
	 * @return key�ɑΉ����郁�b�Z�[�W
	 * @see CommonMessages#getString(String)
	 */
	public String getMessage(String key){
		return ResourceMessages.getString(key);		
	}

	/**
	 * @return
	 */
	public LinkedList getFormRows() {
		return formRows;
	}

	/**
	 * @param list
	 */
	public void setFormRows(LinkedList list) {
		formRows = list;
	}

	/**
	 * @return
	 */
	public String getCpPasteMode() {
		return cpPasteMode;
	}

	/**
	 * @param string
	 */
	public void setCpPasteMode(String string) {
		cpPasteMode = string;
	}


}
