/**
* ArticleNumberForm  �i�ԃ}�X�^�[�����e�i���X�t�H�[���N���X
*
*	�쐬��    2003/08/25
*	�쐬��    �i�m�h�h�j���� ����
* �����T�v    Http���N�G�X�g�ƃ��N�G�X�g�̏������ʂ��i�[����N���X�B
* 
* 	[�ύX����]
* 		 2003/10/07�i�m�h�h�j���c  �Ĕ�
* 			�E�����E�o�^����Form��VO��ۑ��A�X�V�E�폜���͂���VO�𗘗p����
* 			  �������s�Ȃ��悤�ɕύX
*/

package com.jds.prodord.master.articlenumber;

import org.apache.struts.action.*;

import javax.servlet.http.*;
import java.util.*;
import com.jds.prodord.common.*;

public class ArticleNumberForm extends ActionForm {

	public static final String COMMAND_JITTKOU = "���s";
	public static final String COMMAND_CLEAR = "�N���A";

	private String command = "";
	private String daiKaiSkbCod = "";
	private String queryDaiKaiSkbCod = "";
	private String[] kaiSkbCod = null;
	private ArrayList KaiSkbCodList = null;
	private String queryKaiSkbCod = "";
	private ArrayList queryKaiSkbCodList = null;

	private ArrayList vl_kaiSkbCod = new ArrayList();
	private ArrayList vl_ketCod = new ArrayList();
	private ArrayList lb_ketCod = new ArrayList();

	private String prcKbn = "";
	private String kigoBan = "";
	private String artist = "";
	private String title = "";
	private String[] ketCod = null;

	private String year = "";
	private String month = "";
	private String day = "";	
	private String prsMkrCod = "";
	private String jytPrsMkr = "";
	private String hukSizCod = "";
	private String dbName = "";
	private boolean findedFlag = false;
	private String hyoKbn = "";

//=============================================================================

	public ArticleNumberForm(){
		super();
		this.setCommand("INIT");
	}
	
//----------------------------------------- 2003/10/07 add okada ---

	private ArticleNumberVO vo = new ArticleNumberVO();
	
	public ArticleNumberVO getArticleNumberVO(){
		return vo;
	}
	public void setArticleNumberVO(ArticleNumberVO vo){
		this.vo = vo;
	}
	public void removeArticleNumberVO(){
		vo = new ArticleNumberVO();
	}
	
//-------------------------------------------------------------------	
	


     //***  �{�^����get/set  ***
	public String getCommand(){
		return command;
	}
	public void setCommand(String s){
		this.command = s;
	}


     //***  ��\��Ў��ʃR�[�h��get/set  ***
    public String getDaiKaiSkbCod(){
    	return daiKaiSkbCod;
    }
    public void setDaiKaiSkbCod(String s){
    	this.daiKaiSkbCod = s;
    }

     //***  ��Ў��ʃR�[�h��get/set  ***
    public String[] getKaiSkbCod(){
    	return kaiSkbCod;
    }
    public void setKaiSkbCod(String[] s){
    	this.kaiSkbCod = s;
    }

	//***  �N�G����Ў��ʃR�[�h��get/set  ***
	public String getQueryKaiSkbCod() {
		return queryKaiSkbCod;
	}
	public void setQueryKaiSkbCod(String queryKaiSkbCod) {
		this.queryKaiSkbCod = queryKaiSkbCod;
	}

     //***  ��Ў��ʃR�[�h���X�g��get/set  ***
    public ArrayList getKaiSkbCodList(){
    	return KaiSkbCodList;
    }
    public void setKaiSkbCodList(ArrayList arr){
    	this.KaiSkbCodList = arr;
    }

	//***  �N�G����Ў��ʃR�[�h���X�g��get/set  ***
	public ArrayList getQueryKaiSkbCodList() {
		return queryKaiSkbCodList;
	}
	public void setQueryKaiSkbCodList(ArrayList arr) {
		this.queryKaiSkbCodList = arr;
	}

	//***  Gets the vl_kaiSkbCod  ***
	public Collection getVl_kaiSkbCod() {
		return (Collection)vl_kaiSkbCod;
	}

	public void setKaiSkbCodOptions(ArrayList queryKaiSkbCodList){
		vl_kaiSkbCod.clear();
		for(int i=0; i<queryKaiSkbCodList.size();i++){
			vl_kaiSkbCod.add(queryKaiSkbCodList.get(i)+"");
		}
	}

	//***  Gets the vl_ketCod�Elb_ketCod  ***
	public Collection getVl_ketCod() {
		return (Collection)vl_ketCod;
	}
	public Collection getLb_ketCod(){
		return (Collection)lb_ketCod;
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

	//*** �����敪  ��get/set  ***
	public String getPrcKbn() {
		return prcKbn;
	}
	public void setPrcKbn(String prcKbn) {
		this.prcKbn = prcKbn;
	}

     //***  �L���ԍ���get/set  ***
    public String getKigoBan(){
    	return kigoBan;
    }
    public void setKigoBan(String s){
    	this.kigoBan = s;
    }

     //***  �A�[�e�B�X�g��get/set  ***
    public String getArtist(){
    	return artist;
    }
    public void setArtist(String s){
    	this.artist = s;
    } 
    
     //***  �^�C�g����get/set  ***
    public String getTitle(){
    	return title;
    }
    public void setTitle(String s){
    	this.title = s;
    }


     //***  �`�ԃR�[�h��get/set  ***
    public String[] getKetCod(){
    	return ketCod;
    }
    public void setKetCod(String[] str){
    	this.ketCod = str;
    }


     //***  �N��get/set  ***
    public String getYear(){
    	return year;
    }
    public void setYear(String s){
    	this.year = s;
    }

     //***  ����get/set  ***
    public String getMonth(){
    	return month;
    }
    public void setMonth(String s){
    	this.month = s;
    }

     //***  ����get/set  ***
    public String getDay(){
    	return day;
    }
    public void setDay(String s){
    	this.day = s;
    }

    //***  �v���X���[�J�[�R�[�h��get/set  ***
    public String getPrsMkrCod(){
    	return prsMkrCod;
    }
    public void setPrsMkrCod(String s){
    	this.prsMkrCod = s;
    }
    //***  ����v���X���[�J�[�R�[�h��get/set  ***
    public String getJytPrsMkr(){
    	return jytPrsMkr;
    }
    public void setJytPrsMkr(String s){
    	this.jytPrsMkr = s;
    } 
    
    //***  �����ޔ�����R�[�h��get/set  ***
    public String getHukSizCod(){
    	return hukSizCod;
    }
    public void setHukSizCod(String s){
    	this.hukSizCod = s;
    }

    //***  �f�[�^�x�[�X����get/set  ***
    public String getDbName(){
    	return dbName;
    }
    public void setDbName(String s){
		if(s.equals("HIN01")){
			this.dbName = "�i�ԃ}�X�^�[";
		}else if(s.equals("HIN02")){
			this.dbName = "�J�^���O�O�i��";
		}else{
			this.dbName = "";			
		}
    }

	/**
	 * Gets the hidUpdDte
	 * @return Returns a int
	 */
/*	public int getHidUpdDte() {
		return hidUpdDte;
	}
	public void setHidUpdDte(int hidUpdDte) {
		this.hidUpdDte = hidUpdDte;
	}
*/

	/**
	 * Gets the hidUpdJkk
	 * @return Returns a int
	 */
/*	public int getHidUpdJkk() {
		return hidUpdJkk;
	}
	public void setHidUpdJkk(int hidUpdJkk) {
		this.hidUpdJkk = hidUpdJkk;
	}
*/

	//*** Gets the queryDaiKaiSkbCod  ***
	public String getQueryDaiKaiSkbCod() {
		return queryDaiKaiSkbCod;
	}
	public void setQueryDaiKaiSkbCod(String queryDaiKaiSkbCod) {
		this.queryDaiKaiSkbCod = queryDaiKaiSkbCod;
	}

	/**
	 * Gets the findedFlag
	 * @return Returns a boolean
	 */
	public boolean getFindedFlag() {
		return findedFlag;
	}
	public void setFindedFlag(boolean findedFlag) {
		this.findedFlag = findedFlag;
	}

	//*** �\���敪  ��get/set  ***
	public String getHyoKbn() {
		return hyoKbn;
	}
	public void setHyoKbn(String s) {
		this.hyoKbn = s;
	}

//=====  �l���͔���  ================================================================

	public ActionErrors validate(ActionMapping mapping,HttpServletRequest req){

	HttpSession session = req.getSession();
	//���b�Z�[�W���Z�b�V���������菜��
	session.removeAttribute(CommonConst.INFOMSG_KEY);
	
	if(command.equals("INIT"))
		return null;
	
	ActionErrors errors = new ActionErrors();
		
	if(command.equals(COMMAND_JITTKOU)){
		//�X�V�����`�F�b�N
		if(!prcKbn.equals("1")){
			boolean authorityChecked = false;
			authorityChecked = CheckCommon.checkHyoKbn(this.getQueryDaiKaiSkbCod(),this.getHyoKbn());
			if(!authorityChecked){
				errors.add("",new ActionError("errors.cannot","���̃A�J�E���g��","�o�^�^�X�V�^�폜��"));
				return errors;	
			}
		}
		
		final int kubun = Integer.parseInt(prcKbn);

		switch(kubun){
//-----  (�Ɖ�)  --------------------------------------------------------
			case 1:
				//�L���ԍ�
				if(kigoBan.trim().equals("")){
					errors.add("",new ActionError("errors.input.required","�L���ԍ�"));
				}else{
				//���p�����̂�
				if(!StringUtils.isAscii(kigoBan.trim()))
					errors.add("",new ActionError("errors.input.1","�L���ԍ�","���p�p��"));
				}

				break;
//-----  (�o�^)  --------------------------------------------------------
			case 2:
				//�L���ԍ�
				if(kigoBan.trim().equals("")){
					errors.add("",new ActionError("errors.input.required","�L���ԍ�"));
				}else{
				//���p�����̂�
				if(!StringUtils.isAscii(kigoBan.trim()))
					errors.add("",new ActionError("errors.input.1","�L���ԍ�","���p�p��"));
				}
				//�A�[�e�B�X�g
				if(!artist.trim().equals("")){
					//�S�p�̂�
					if(StringUtils.containsAsciiOrHarfWidthKatakana(artist.trim()))
						errors.add("",new ActionError("errors.input.1","�A�[�e�B�X�g","�S�p"));
				}
				//�^�C�g��
				if(!title.trim().equals("")){
					if(StringUtils.containsAsciiOrHarfWidthKatakana(title.trim()))
						errors.add("",new ActionError("errors.input.1","�^�C�g��","�S�p"));
				}
				//������
				if(year.trim().equals("") || month.trim().equals("") || day.trim().equals("")){
					errors.add("",new ActionError("errors.input.required","������"));
				}else{
					if(!year.trim().equals("") && !month.trim().equals("") && !day.trim().equals("")){
						if(!CheckCommon.validateAsDate(year,month,day))
							errors.add("",new ActionError("errors.input.1","������","���t"));
					}		
				}
				//�v���X���[�J�[�R�[�h
				if(!prsMkrCod.trim().equals("")){
					//���p�����̂�
					if(!StringUtils.isAscii(prsMkrCod.trim()))
						errors.add("",new ActionError("errors.input.1","�v���X���[�J�[�R�[�h","���p�p��"));
				}
				//����v���X���[�J�[�R�[�h
				if(!jytPrsMkr.trim().equals("")){
					//���p�����̂�
					if(!StringUtils.isAscii(jytPrsMkr.trim()))
						errors.add("",new ActionError("errors.input.1","����v���X���[�J�[�R�[�h","���p�p��"));
				}
				//�����ޔ�����
				if(!hukSizCod.trim().equals("")){
					//���p�����̂�
					if(!StringUtils.isAscii(hukSizCod.trim()))
						errors.add("",new ActionError("errors.input.1","�����ޔ�����R�[�h","���p�p��"));
				}

				break;
//-----  (�ύX)  --------------------------------------------------------
			case 3:
				if(!this.getArticleNumberVO().getKaiSkbCod().equals(kaiSkbCod[0]))
					errors.add("",new ActionError("errors.update.notselected","�X�V"));
				else if(!this.getArticleNumberVO().getKigoBan().equals(DataFormatUtils.mk_srykig(kigoBan.trim())))
					errors.add("",new ActionError("errors.update.notselected","�X�V"));
				else{
					//�L���ԍ�
					if(kigoBan.trim().equals("")){
						errors.add("",new ActionError("errors.input.required","�L���ԍ�"));
					}else{
						//���p�����̂�
						if(!StringUtils.isAscii(kigoBan.trim()))
							errors.add("",new ActionError("errors.input.1","�L���ԍ�","���p�p��"));
					}
					//�A�[�e�B�X�g
					if(!artist.trim().equals("")){
						//�S�p�̂�
						if(StringUtils.containsAsciiOrHarfWidthKatakana(artist.trim()))
							errors.add("",new ActionError("errors.input.1","�A�[�e�B�X�g","�S�p"));
					}
					//�^�C�g��
					if(!title.trim().equals("")){
						if(StringUtils.containsAsciiOrHarfWidthKatakana(title.trim()))
							errors.add("",new ActionError("errors.input.1","�^�C�g��","�S�p"));
					}
					//������
					if(year.trim().equals("") || month.trim().equals("") || day.trim().equals("")){
						errors.add("",new ActionError("errors.input.required","������"));
					}else{
						if(!year.trim().equals("") && !month.trim().equals("") && !day.trim().equals("")){
							if(!CheckCommon.validateAsDate(year,month,day))
								errors.add("",new ActionError("errors.input.1","������","���t"));
						}
					}
					//�v���X���[�J�[�R�[�h
					if(!prsMkrCod.trim().equals("")){
						//���p�����̂�
						if(!StringUtils.isAscii(prsMkrCod.trim()))
							errors.add("",new ActionError("errors.input.1","�v���X���[�J�[�R�[�h","���p�p��"));
					}
					//����v���X���[�J�[�R�[�h
					if(!jytPrsMkr.trim().equals("")){
						//���p�����̂�
						if(!StringUtils.isAscii(jytPrsMkr.trim()))
							errors.add("",new ActionError("errors.input.1","����v���X���[�J�[�R�[�h","���p�p��"));
					}
					//�����ޔ�����
					if(!hukSizCod.trim().equals("")){
						//���p�����̂�
						if(!StringUtils.isAscii(hukSizCod.trim()))
							errors.add("",new ActionError("errors.input.1","�����ޔ�����R�[�h","���p�p��"));
					}
				}

				break;

//-----  (�폜)  --------------------------------------------------------
			case 4:
				if(!this.getArticleNumberVO().getKaiSkbCod().equals(kaiSkbCod[0]))
					errors.add("",new ActionError("errors.update.notselected","�X�V"));
				else if(!this.getArticleNumberVO().getKigoBan().equals(DataFormatUtils.mk_srykig(kigoBan.trim())))
					errors.add("",new ActionError("errors.update.notselected","�X�V"));

				break;
//--------------------------------------------------------------------
			}  //switch�I��
		}  //if(command.equals(this.COMMAND_JIKKOU)){�I��
		return errors.empty() ? null : errors;
	}



	public void reset(ActionMapping maping,HttpServletRequest req){
			this.command = "INIT";
	}

//----------------------------------------------------------------------------------

	public void clearAll(){
		command = "";
		this.kaiSkbCod = null;
	    this.prcKbn = "";
        this.kigoBan = "";
        this.artist = "";
        this.title = "";
        this.ketCod = null;
		this.year = "";
		this.month = "";
		this.day = "";
	    this.prsMkrCod = "";
	    this.jytPrsMkr = "";
	    this.hukSizCod = "";
		this.dbName = "";
		this.findedFlag = false;
  	}

	public void clearTableField1(){
		command = "";
	    artist = "";
	    title = "";
	    ketCod = null;
		year = "";
		month = "";
		day = "";
	    prsMkrCod = "";
	    jytPrsMkr = "";
	    hukSizCod = "";
		dbName = "";
		findedFlag = false;
	}


}