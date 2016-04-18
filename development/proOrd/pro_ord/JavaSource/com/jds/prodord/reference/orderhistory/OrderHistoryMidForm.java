/**
* OrderHistoryMidForm  ���������Ɖ��ʃt�H�[���N���X
*
*	�쐬��    2003/04/18
*	�쐬��    �i�m�h�h�j�g�c �h�q
* �����T�v    Http���N�G�X�g�ƃ��N�G�X�g�̏������ʂ��i�[����N���X�B
*
*	[�ύX����]
* 		2003/10/22�i�m�h�h�j���c �Ĕ�
* 			�E�������M�͖��o�͂̂� �� �������M�͖��o�́E�o�͍ϗ�����ΏۂƂ���悤�ɏC��
* 		2004/01/20�i�m�h�h�j�X
* 			�E�����`�[���s�{�^���������A��������ύX������R�����g�������\��
* 		2004/01/22�i�m�h�h�j�X
* 			�E���Ɏ�����ł���悤�ɕύX
* 		2004/03/08 (�m�h�h) �X
* 			�E�V���敪"���̑�"�ǉ��ɔ����C��
* 		2005/05/25�i�m�h�h�j�g�c
* 			�E�[�i�於��'�i�`�q�d�c'�̏ꍇ�A�����敪"0"�̏C��
* 			�E�������̓��̓`�F�b�N�C��
*		2005/09/01�i�m�h�h�j�g�c
*			�E���ׂɒP���̍��ڒǉ�
*		2007/12/25�i�m�h�h�j�c��
* 			�E�}�j���A�������w���ꊇ�\�t�@���l�Q�ǉ��Ή�
* 		2008/02/25�i�m�h�h�j�c��
* 			�E�������ɒ����Ȃ���Δ����������ɐ��ł��G���[�Ƃ��Ȃ�
* 		2008/06/02�i�m�h�h�j�c��
* 			�E�u�`�o�Ё@�P���E���z�̏C��
*/

package com.jds.prodord.reference.orderhistory;

import org.apache.struts.action.*;

import javax.servlet.http.*;
import java.util.*;
import com.jds.prodord.common.*;

public class OrderHistoryMidForm extends ActionForm {

	public static final String COMMAND_DOWNLOAD =  "COMMAND_DOWNLOAD";
	public static final String COMMAND_TEIDENHAKKOU = "COMMAND_TEIDENHAKKOU";
	public static final String COMMAND_TEISOU = "COMMAND_TEISOU";
	public static final String SUCCESS_TEIDENHAKKOU = "SUCCESS_TEIDENHAKKOU";
	
	public static final String SYRZMI = "�o�͍�";
	public static final String MISYRYK = "���o��";
	
	public static final String PRS = "�v���X";
	public static final String SZI = "������";
	
	public static final String NEXT = "NEXT";
	public static final String PREV = "PREV";
	
	public static final int MAX_ROW_COUNT = 100;//1�y�[�W�ɕ\�����錏��
	public static final int MAX_REFER_COUNT = 1000;//�ő匟������

    private ArrayList details = new ArrayList();

	private String command = "";
    private String count = "";
    private String newWindow = "";//����p��ʂ��J�����ǂ����̃t���O
    
	private String queryDaiKaiSkbCod = "";
	private ArrayList queryKaiSkbCodList = null;
	private String hyoKbn = ""; //add 2011/05/30
	
	private String teidenflg = "";
	
    private int currentPage;//���݂̃y�[�W
    private int disp_currentPage;//���݂̃y�[�W�i�\���p�j
    private int pageCount;//�y�[�W��
    private int allRowCount;//�S�y�[�W�̌���
    private ArrayList vosList = new ArrayList();//VO�̃��X�g�i�y�[�W���Ɓj
    private ArrayList check_mid_index = new ArrayList();

	public OrderHistoryMidForm(){
		super();
		this.removeOrderHistoryVO();
		this.removeAllRow();
		this.setCommand("INIT");
	}
	
    //-----------------------------------------------------------
	//-----------------------------------------------------------
	//�o�����[�I�u�W�F�N�g�̊i�[
	private ArrayList voList = new ArrayList();
	
    public void setOrderHistoryVO(ArrayList arr){
    	
    	voList.clear();
		voList.addAll(arr);
		
	}
	public void addOrderHistoryVO(OrderHistoryVO fmVO){

		voList.add(fmVO);
		
	}
	public ArrayList getOrderHistoryVO(){
		
		return voList;	
		
	}
	public void setOrderHistoryVO(int i, OrderHistoryVO fmVO){
    	
		voList.set(i, fmVO);
		
	}
    public OrderHistoryVO getOrderHistoryVO(int i){
		
		return (OrderHistoryVO)voList.get(i);	
		
	}
	public void removeOrderHistoryVO(){
    	
		voList.clear();
		
	}	
	public void removeOrderHistoryVO(int i){
    	
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

	//-----------------------------------------------------------���o��
	
     //��\��Ў��ʃR�[�h
    public String getQueryDaiKaiSkbCod(){
    	return queryDaiKaiSkbCod;
    }
    public void setQueryDaiKaiSkbCod(String s){
    	queryDaiKaiSkbCod = s;
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
     * �����`�[���sset�t���O
	 *   �P��> SUCCESS_TEIDENHAKKOU ���Z�b�g
     */
    public void setTeiDenflg(String s) {
        this.teidenflg = s;
    }
    public String getTeiDenflg() {
        return this.teidenflg;
    }
    
	//---------------------------------------------------------------------------------
	
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
    public String getDisp_currentPage() {
		int i = this.currentPage + 1;
        return i+"";

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
    public String getPageCount() {

        return this.pageCount+"";

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
    public String getAllRowCount() {

        return this.allRowCount+"";

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
	 * VO�̃��X�g�i���ʑS�́j�̃N���A
	 */
	public void removeVosList() {

		this.vosList.clear();
    
	}
    
    /**
     * VO�̃��X�g�i�y�[�W�P�ʁj�̎擾
     * 
     * @return VO�̃��X�g�i�y�[�W�P�ʁj
     */
    public OrderHistoryVO[] getVosList(int i) {
		ArrayList arr = (ArrayList)this.vosList.get(i);
        return (OrderHistoryVO[])arr.toArray(new OrderHistoryVO[arr.size()]);

    
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

	//---------------------------------------------------------------------------------
	public int getSize(){
		return details.size();
	}
	
	public void setSize(int size){
		if(size > details.size()){
			int j = size - this.getSize();			
		  for(int k=0; k<j; k++){
		    details.add(new OrderHistoryFormRow());			  	
		  }						
		}else if(size < details.size()){
			for(int i = details.size();i > size;i--){
				details.remove((i-1));
			}
		}
	}

	
	public OrderHistoryFormRow getFormRow(int i){
		OrderHistoryFormRow fr = (OrderHistoryFormRow)details.get(i);
		if(fr == null){
			fr = new OrderHistoryFormRow();
			details.set(i,fr);
		}
		return fr;
	}
	

	public void clearTableField(){
		for(int i = 0;i < details.size();i++){
			OrderHistoryFormRow fr = (OrderHistoryFormRow)details.get(i);
			if(fr != null)
	            details.set(i,new OrderHistoryFormRow());			
		}	
	}
	


	public void clearAll(){
		command = "";
		queryDaiKaiSkbCod = "";
		clearTableField();		
	}
		
	public ArrayList getFormRowList(){
		return details;
	}
	
// 2004.01.19 add �R�����g�����\���̂���
	public ArrayList getFormRowCloneList(){

		ArrayList arr = new ArrayList();		

		for(int i = 0; i < details.size();i++){
			arr.add(((OrderHistoryFormRow)details.get(i)).clone());
		}
		return arr;
	}
	
	public void setFormRowList(ArrayList arr){
		details = arr;
	}
	
	public void removeAllRow(){
		details.clear();
	}
		
	//�`�F�b�N�{�b�N�X�����Z�b�g���郁�\�b�h
	public void setCheck_false(){
		for(int i = 0; i < this.getSize();i++){
			OrderHistoryFormRow row  = this.getFormRow(i);
			row.setCheck_Mid(false);
		}
	}
	
	//�`�F�b�N����Ă���`�F�b�N�{�b�N�X�̃C���f�b�N�X
	
	public ArrayList getCheck_Mid_index(){
		return check_mid_index;
	}
	public void setCheck_Mid_index(ArrayList arr){
		check_mid_index = arr;
	}
	
	
	public void setCheck_index(ArrayList arr1){
		check_mid_index = arr1;
	}
	
	public void setOrderHistoryVO_all(int i, OrderHistoryVO fmVO){
    	
		int pageIdx = i / MAX_ROW_COUNT;
		int rowIdx = i % MAX_ROW_COUNT;
//System.out.println(pageIdx+" : "+rowIdx);
		ArrayList pageVOs = (ArrayList)vosList.get(pageIdx);
		pageVOs.set(rowIdx,fmVO);

	}
	
	//---------------------------------------------------------------------------------

	public ActionErrors validate(ActionMapping mapping,HttpServletRequest req){
		HttpSession session = req.getSession();
		//���b�Z�[�W���Z�b�V���������菜��
		session.removeAttribute(CommonConst.INFOMSG_KEY);
		
		if(command.equals("INIT"))
			return null;
			
		//�����`�[���s�ɂ���B
		if(this.teidenflg.equals("1")){
			this.command = SUCCESS_TEIDENHAKKOU;			
		}
		
		ActionErrors errors = new ActionErrors();
		//�u�������M�v��������`�[���s�  ������			
		if(command.equals(COMMAND_TEISOU) || command.equals(COMMAND_TEIDENHAKKOU)){

			//�X�V�����`�F�b�N
			boolean authorityChecked = false;
			authorityChecked = CheckCommon.checkHyoKbn(this.getQueryDaiKaiSkbCod(),this.getHyoKbn());
			if(!authorityChecked){
				errors.add("",new ActionError("errors.cannot","���̃A�J�E���g��","�o�^�^�X�V�^�폜��"));
				return errors;	
			}

			DateUtils dateUtils = new DateUtils();
			int today = dateUtils.getDate6Int();
			
			for(int i = 0; i < this.getSize();i++){
				OrderHistoryFormRow row  = this.getFormRow(i);
				if(row == null)
					continue;

				if(row.getCheck_Mid()){

					/** ���l�Q 2007/12/21 add **/
					if(!row.getBiKou2().trim().equals("")){
						if (!StringUtils.isAsciiOrHarfWidthKatakana(row.getBiKou2())) 
							errors.add(""+i, new ActionError("errors.input.1_row",String.valueOf(i+1),"���l�Q","���p"));
					}

					/** ������ **/
					if(row.getHacSuu().trim().equals("")){
						errors.add("",new ActionError("errors.input.required_row",String.valueOf(i+1),"������"));
					}else{
						//2005/05/30 �������}�C�i�X���͉ɏC��
						try{
							if(!StringUtils.isAscii(row.getHacSuu().trim()))
								throw new NumberFormatException();
							int hacSuu = Integer.parseInt(row.getHacSuu().trim());
							
							//2004.01.22 add ���Ɏ�����ł���悤�ɕύX
							String bunCod = (row.getBunCod().equals("�v���X"))? "0" : "";
							//bshCod����擾�ł���悤�ɏC�� 2005/05/25 add
							row.setCykkbn(this.getCykKbn(row.getNhnMeiKj(),bunCod,row.getBshCod()));
							
							int hacSuuOld = Integer.parseInt(row.getHacSuuOld().trim());
							int nyoSuu = Integer.parseInt(StringUtils.replace(row.getNyoSuu().trim(),",",""));

							//���ɐ�=0�Ŕ��������v���X����}�C�i�X�ɕς�����ꍇ 2005/06/03 add
							if(nyoSuu == 0 && hacSuuOld > 0 && hacSuu < 0){
								errors.add("",new ActionError("errors.input.1_row",String.valueOf(i+1),"������","���ɐ����傫����"));
							}
							//���ɐ�<>0�̏ꍇ
							if(nyoSuu != 0 && bunCod.equals("0") && row.getCykkbn().equals("0")){
//								if(hacSuu < nyoSuu)
								if(hacSuuOld != hacSuu && hacSuu < nyoSuu) //�������̒������Ȃ���΃G���[�ɂ��Ȃ��悤�ύX 2008/02/13
									errors.add("",new ActionError("errors.input.1_row",String.valueOf(i+1),"������","���ɐ����傫����"));
							}

						}catch (NumberFormatException e) {
							errors.add("",new ActionError("errors.input.1_row",String.valueOf(i+1),"������","���p����"));
						}
					}
					
					/** ���z 2005/09/13 add **/
//					if(!row.getKin().trim().equals("")){
//						if(!StringUtils.isAsciiDigit(String.valueOf(row.getKin().trim())))
//							errors.add("",new ActionError("errors.input.1_row",String.valueOf(i+1),"���z","���p����"));
//					}

					/** ���z 2007/12/25 �����_�ȉ����͉\�֕ύX **/
					if(!row.getKin().trim().equals("")){
						if(!StringUtils.isFloat(String.valueOf(row.getKin().trim()),2))
							errors.add("",new ActionError("errors.input.1_row",String.valueOf(i+1),"���z","������9���E������2��"));
						if(!(Double.parseDouble(row.getKin().trim()) < 1000000000))
							errors.add("",new ActionError("errors.input.1_row",String.valueOf(i+1),"���z","������9���E������2��"));

						//�u�`�o�ВP���Z�o�Ή��@���z�^��������7���𒴂����ꍇ
						if(row.getDaiKaiSkbCod().equals(CommonConst.KAICOD_VAP)){
							double kin = Double.parseDouble(row.getKin().trim());
							int hacSuu = Integer.parseInt(row.getHacSuu().trim());
	
							if((kin / hacSuu) >= 10000000){
								errors.add("",new ActionError("errors.input.1_row",String.valueOf(i+1),"���z�^������","7���ȉ�"));
							}
						}
					}
					/** �P�� 2008/03/07 add **/
					if(!row.getTan2().trim().equals("")){
						if(!StringUtils.isFloat(String.valueOf(row.getTan2().trim()),2))
							errors.add("",new ActionError("errors.input.1_row",String.valueOf(i+1),"�P��","������7���E������2��"));
						if(!(Double.parseDouble(row.getTan2().trim()) < 10000000))
							errors.add("",new ActionError("errors.input.1_row",String.valueOf(i+1),"�P��","������7���E������2��"));

						//�u�`�o�Ћ��z�Z�o�Ή��@�P������������9���𒴂����ꍇ
						if(row.getDaiKaiSkbCod().equals(CommonConst.KAICOD_VAP)){
							double tan2 = Double.parseDouble(row.getTan2().trim());
							int hacSuu = Integer.parseInt(row.getHacSuu().trim());
	
							if((tan2 * hacSuu) >= 1000000000){
								errors.add("",new ActionError("errors.input.1_row",String.valueOf(i+1),"�P����������","9���ȉ�"));
							}
						}
					}
					
					/** �[�� **/
					if(!row.getNkiYear().equals("")||!row.getNkiMonth().equals("")||!row.getNkiDay().equals("")){
						//���t�^
						if(!CheckCommon.validateAsDate(row.getNkiYear(),row.getNkiMonth(),row.getNkiDay())){
							errors.add("",new ActionError("errors.input.1_row",String.valueOf(i+1),"�[��","���t"));
						}
					}

					/** �[�i�� **/
					if(!row.getNhnMeiKj().equals("")){
						//�S�p�̂�
						if(StringUtils.containsAsciiOrHarfWidthKatakana(row.getNhnMeiKj().trim())){
							errors.add("",new ActionError("errors.input.1_row",String.valueOf(i+1),"�[�i��","�S�p"));
						}
					}
					/** �R�����g **/
					if(!row.getCmt().equals("")){
						//�S�p�̂�
						if(StringUtils.containsAsciiOrHarfWidthKatakana(row.getCmt().trim())){
							errors.add("",new ActionError("errors.input.1_row",String.valueOf(i+1),"�R�����g","�S�p"));
						}
					}
					/** �V���敪 **/	//2004.03.08 add
					if((row.getBunCod().equals("�v���X"))&&(row.getSinKyuKbn().equals("���̑�"))){
						errors.add("",new ActionError("errors.input.prohibited_row",String.valueOf(i+1),"�v���X�̏ꍇ�A�敪�̂��̑�"));
					}

					// ������`�[���s��������A���o�͎͂w��s��
					if(command.equals(COMMAND_TEIDENHAKKOU)){
						if(row.getSyrZmiSgn().equals(MISYRYK))
							errors.add("",new ActionError("errors.input.prohibited_row",String.valueOf(i+1),"�����`�[���s���A���o�͂̍s"));
					}
				}
			}
					
			//�����Ώێw��Ȃ�
			boolean executeChecked = false;
			if(command.equals(COMMAND_TEIDENHAKKOU)){
				for(int i = 0; i < this.getSize();i++){
					OrderHistoryFormRow row  = this.getFormRow(i);
						executeChecked = true;
				}
			}else if(command.equals(COMMAND_TEISOU)){
				for(int i = 0; i < this.getSize();i++){
					OrderHistoryFormRow row  = this.getFormRow(i);
						executeChecked = true;
				}
			}
			if(!executeChecked){
				errors.add("",new ActionError("errors.input.required","�����Ώ�"));
			}
			
		}
		
		return errors.empty() ? null : errors;

	}
	
	
	
	public void reset(ActionMapping maping,HttpServletRequest req){
		for(int i=0; i<this.getSize(); i++){
			getFormRow(i).setCheck_Mid(false);
		}
		if(!this.command.equals(SUCCESS_TEIDENHAKKOU)){
			this.setNewWindow("0");
			this.command = "INIT";
		}
	}
	
	//page�̏�����
	public void clear_page(){
		this.setCurrentPage(-1);
		this.setPageCount(0);
		this.setAllRowCount(0);
	}
	
	/** 
	 * %�^�J�Z%�܂���%�i�`�q�d�c% �� �����敪 = "0" ����ȊO �� �����敪 = "1"
	 * �����ޔ����̂Ƃ� �� �����敪 = "0"
	 */
	private final String getCykKbn(String str,String bunCod,String bshCod){
		if(!bunCod.equals("0") && !bunCod.equals("")){//�����ޔ����̂Ƃ�
			return	"0";
		}
		return DataFormatUtils.getCykKbn(str, bshCod);
	}


}

