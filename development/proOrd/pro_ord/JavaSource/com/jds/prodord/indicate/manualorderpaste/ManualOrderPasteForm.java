package com.jds.prodord.indicate.manualorderpaste;

import org.apache.struts.action.*;
import javax.servlet.http.*;
import java.util.*;
import com.jds.prodord.common.*;
/**
 * ManualOrderPasteForm  �}�j���A�������w���ꊇ�\�t�t�H�[���N���X�B<BR>
 * <dt><b>�쐬��: </b></dt>
 * <dd>2007/09/10</dd>
 * <dt><B>�쐬��: </b></dt>
 * <dd>(NII)</dd>
 * <dt><B>�����T�v: </b></dt>
 * <dd>Http���N�G�X�g�ƃ��N�G�X�g�̏������ʂ��i�[����N���X</dd>
 * <br>
 * <b>�ύX����</b><br>
 * <table border="1">
 * 	<tr>
 * 		<th width="80">�C�����t</th><th width="100">�C����</th><th width="300">�C�����e</th>
 * 	</tr>
 * 	<tr>
 * 		<td>yyyy/MM/dd</td>
 * 		<td>(NII)</td>
 * 		<td>�E</td>
 * 	</tr>
 * </table>
 */
public class ManualOrderPasteForm extends ActionForm {
	
	public static final String PRS = "0";
	public static final String FKS = "1";

	public final int ROW_MAX = 200;
	
	public static final String NOT_HIN_EXIST = "label.kigbng";
	public static final String NOT_FKS_EXIST = "label.fukshizai";
		
	private String command = "";
	LinkedList formRows = new LinkedList();
    
	//�v���X�^������
	private String prsFks = "";
	//�敪
	private String kbn = "";
	
	private ArrayList prsFksList = new ArrayList();
	private ArrayList kbnList = new ArrayList();

	private String queryDaiKaiSkbCod = "";
	private String queryKaiSkbCod = "";
	private ArrayList queryKaiSkbCodList = null;
	private String bshCod = "";
	private String usrId = "";
	
	private String cpPaste = ""; 
	private String cpPasteMode ="1";
	
	private String prsFksChangeFlg = "0";
	
	//*****************************************************
	
	public ManualOrderPasteForm(){
		super();
		this.clearAll();
		this.setCommand("INIT");
	}
		

	//���j���[���炫���Ƃ���true���Z�b�g
	private boolean fromMenuSgn = false;
	public boolean getFromMenuSgn(){
		return fromMenuSgn;
	}
	public void setFromMenuSgn(boolean fromMenuSgn){
			this.fromMenuSgn = fromMenuSgn;
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
	
	//---------------------------------------------------------------------------------

	/**
	 * �v���X�^�����ނ̃Z�b�g
	 * @param string
	 */
	public void setPrsFks(String prsFks) {
		this.prsFks = prsFks;
	}
	/**
	 * �v���X�^�����ނ̎擾
	 * @return
	 */
	public String getPrsFks() {
		return this.prsFks;
	}

    /**
     * �敪�̃Z�b�g
     * 
     * @param kbn �敪
     */
    public void setKbn(String kbn) {
        this.kbn = kbn;
    }
    /**
     * �敪�̎擾
     * 
     * @return �敪
     */
    public String getKbn() {
        return this.kbn;
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

	public void clearAll(){
		command = "";
		setKbn(CommonConst.SINPU);
		setPrsFks(PRS);
		
		setFormRows(new LinkedList());
		setCpPaste("");
		setCpPasteMode("1");
		setPrsFksChangeFlg("0");

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
			//���ד��͗L���`�F�b�N
			boolean exist = false;
			
			int i = 0;
			for (Iterator iter = getFormRows().iterator(); iter.hasNext(); i++) {
				ManualOrderPasteFormRow row = (ManualOrderPasteFormRow) iter.next();
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
				if (getPrsFks().equals(FKS)) {
					if (StringUtils.isBlankOrNull(row.getFukSziCod())){
						errors.add("ROW_"+i, new ActionError("errors.input.required",getMessage("label.fukshizai")));
					}else 
					if (!StringUtils.isAscii(row.getFukSziCod())) {
						errors.add("ROW_"+i, new ActionError("errors.input.1",getMessage("label.fukshizai"),"���p�p��"));
					}
				}

				// ������������
				if (StringUtils.isBlankOrNull(row.getSuuRyo())){
					errors.add("ROW_"+i, new ActionError("errors.input.required",getMessage("label.hacsryo")));
				}else
				if (!StringUtils.isAsciiDigit(row.getSuuRyo())||Integer.parseInt(row.getSuuRyo())<=0){
					errors.add("ROW_"+i, new ActionError("errors.input.incorrect",getMessage("label.hacsryo")));
				}else 
				if (row.getSuuRyo().length()>7) {
					errors.add("ROW_"+i, new ActionError("errors.input.1",getMessage("label.hacsryo"),"7���ȓ�"));
				}

				// ���l�Q�`�F�b�N
				if(!row.getBiKou2().trim().equals("")){
					if (!StringUtils.isAsciiOrHarfWidthKatakana(row.getBiKou2())) 
						errors.add("ROW_"+i, new ActionError("errors.input.1",getMessage("label.bikou2"),"���p"));
				}
			}

			// ���ז����͂������ꍇ�A�G���[
			if (!exist)
				errors.add("", new ActionError("errors.input.requiredall"));
			
		}

		return errors.empty() ? null : errors;		
	}
	
	//---------------------------------------------------------------------------------

	public void initOptionLists() {
		ArrayList prsFksList = new ArrayList();
		prsFksList.add(new LabelValueBean(getMessage("label.prs"), PRS));
		prsFksList.add(new LabelValueBean(getMessage("label.fukshizai"), FKS));
		setPrsFksList(prsFksList);

		ArrayList kbnList = new ArrayList();
		kbnList.add(new LabelValueBean(getMessage("select.sinpu"), CommonConst.SINPU));
		kbnList.add(new LabelValueBean(getMessage("select.kyuhu"), CommonConst.KYUHU));
		kbnList.add(new LabelValueBean(getMessage("select.sample"), CommonConst.SAMPLE));
		kbnList.add(new LabelValueBean(getMessage("select.tokhan"), CommonConst.TOKHAN));
		kbnList.add(new LabelValueBean(getMessage("select.demoban"), CommonConst.DEMOBAN));
		kbnList.add(new LabelValueBean(getMessage("select.sonota"), CommonConst.OTHERS));
		setKbnList(kbnList);

	}

	//---------------------------------------------------------------------------------
	public int getSize(){
		return formRows.size();
	}
	

	public void setSize(int size){
		if(size > formRows.size()){
			int j = size - this.getSize();
			for(int k=0; k<j; k++){
				formRows.add(new ManualOrderPasteFormRow());			  	
			}	
		}else if(size < formRows.size()){
			for(int i = formRows.size();i > size;i--){
				formRows.remove((i-1));
			}
		}
	}
	

	public ManualOrderPasteFormRow getFormRow(int i){
		ManualOrderPasteFormRow formRow = (ManualOrderPasteFormRow)formRows.get(i);
		if(formRow == null){
			formRow = new ManualOrderPasteFormRow();
			formRows.set(i,formRow);
		}
		return formRow;
	}


	public void clearTableField(){
		for(int i = 0;i < formRows.size();i++){
			ManualOrderPasteFormRow formRow = (ManualOrderPasteFormRow)formRows.get(i);
			if(formRow != null)
				formRow.clear();
		}
	}


	public void clearTableField(int i){
		ManualOrderPasteFormRow formRow = (ManualOrderPasteFormRow)formRows.get(i);
		if(formRow != null)
			formRow.clear();
	}


	public void removeTableField(int i){
		formRows.remove(i);
	}



//---------------------------------------------------------------------------------------------------
	
	public void reset(ActionMapping maping,HttpServletRequest req){
//		if(!command.equals(COMMAND_PRSHACHU) && !command.equals(COMMAND_FUKUHACHU))
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
	 * @return
	 */
	public ArrayList getPrsFksList(){
		return prsFksList;
	}

	/**
	 * @param list
	 */
	public void setPrsFksList(ArrayList prsFksList) {
		this.prsFksList = prsFksList;
	}

	/**
	 * @return
	 */
	public ArrayList getKbnList() {
		return kbnList;
	}

	/**
	 * @param list
	 */
	public void setKbnList(ArrayList kbnList) {
		this.kbnList = kbnList;
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

	/**
	 * @return
	 */
	public String getPrsFksChangeFlg() {
		return prsFksChangeFlg;
	}

	/**
	 * @param string
	 */
	public void setPrsFksChangeFlg(String string) {
		prsFksChangeFlg = string;
	}

}



