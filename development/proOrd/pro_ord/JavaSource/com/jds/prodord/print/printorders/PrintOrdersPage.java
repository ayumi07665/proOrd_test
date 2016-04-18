/**
* PrintOrdersPage  ����������t�H�[���N���X
*
*	�쐬��    2003/04/18
*	�쐬��    �i�m�h�h�j���c �Ĕ�
* �����T�v    Http���N�G�X�g�ƃ��N�G�X�g�̏������ʂ��i�[����N���X�B
*
*	[�ύX����]
*		2005/05/19�i�m�h�h�j�g�c
* 			�E��Ж������Q(KAINMKJ2)�A���[�U�[�h�c�̒ǉ�
* 		2005/08/29�i�m�h�h�j�g�c
* 			�E�X�V������E�����񐔂̒ǉ�
*/

package com.jds.prodord.print.printorders;

import java.util.ArrayList;

import com.jds.prodord.common.DataFormatUtils;

public class PrintOrdersPage {

 	
	public PrintOrdersPage(){

	}
	
	public static final int DEFAULT_ROW_COUNT = 10;
	
	/**
     * ��\��Ў��ʃR�[�h
     */
    private String queryDaiKaiSkbCod;
	
	/**
     * ��Ў��ʃR�[�h
     */
    private String kaiSkbCod;
    
    
    /**
     * ��ЗX�֔ԍ�
     */
    private String kaiYbnNo;
    

    /**
     * ��ЏZ���P
     */
    private String kaiAdr1Kj;
    

    /**
     * ��ЏZ���Q
     */
    private String kaiAdr2Kj;
    

    /**
     * ��Гd�b�ԍ�
     */
    private String kaiTelNo;
    

    /**
     * ���FAX�ԍ�
     */
    private String kaiFaxBng;
    
    /**
     * ��Ѓ��Ssrc
     */
    private String kaiLogoSrc;
    
    /**
     * ���׍s
     */
    private ArrayList details = new ArrayList();
    
    /**
     * ���y�[�W�t���O
     */
    private boolean pageBreak = true;//�����l�Ftrue
    
    /**
     * �����ރt���O
     */
    private boolean fukSziFlg = false;
    
    /**
     * ���ݎ���
     */
    private String currentDate;

	/**
	 * ���[�U�[�h�c
	 */
	private String usrId;    
    
	//-----------------------------------------------------------���o��
	

    /**
     * ��Ж�
     */
    private String kaiNmKj;

    /**
     * ��Ж��Q
     */
	private String kaiNmKj2;

    /**
     * ���������t
     */
    private String hacSyoDte;
    

    /**
     * �������ԍ�
     */
    private String hacSyoBng;
    

    /**
     * ������R�[�h
     */
    private String hacCod;
    

    /**
     * �����於��
     */
    private String sirHacNm;
    
	/**
	 * �X�V�����1
	 */
	private String rrkTbl_1;
	/**
	 * �X�V�����2
	 */
	private String rrkTbl_2;
	/**
	 * �X�V�����3
	 */
	private String rrkTbl_3;
	/**
	 * �X�V�����4
	 */
	private String rrkTbl_4;
	/**
	 * �X�V�����5
	 */
	private String rrkTbl_5;
	
	/**
	 * �����X�V���t
	 */
	private String rrkUpDte;
	/**
	 * ������
	 */
	private int teiNum;

    // ----------------------------------------------------------- Properties
    
    
    /**
     * 
     * ��\��Ў��ʃR�[�h
     * 
     */
    public String getQueryDaiKaiSkbCod(){
    	return queryDaiKaiSkbCod;
    }
    public void setQueryDaiKaiSkbCod(String s){
    	queryDaiKaiSkbCod = s;
    }
    
    /**
     * 
     * ��Ў��ʃR�[�h�̃Z�b�g
     * 
     * @param kaiSkbCod ��Ў��ʃR�[�h
     */
    public void setKaiSkbCod(String kaiSkbCod) {

        this.kaiSkbCod = kaiSkbCod;

    }


    /**
     * ��Ў��ʃR�[�h�̎擾
     * 
     * @return ��Ў��ʃR�[�h
     */
    public String getKaiSkbCod() {

        return this.kaiSkbCod;

    }
    
    /**
     * ��ЗX�֔ԍ��̃Z�b�g
     * 
     * @param kaiYbnNo ��ЗX�֔ԍ�
     */
    public void setKaiYbnNo(String kaiYbnNo) {

        this.kaiYbnNo = kaiYbnNo;

    }


    /**
     * ��ЗX�֔ԍ��̎擾
     * 
     * @return ��ЗX�֔ԍ�
     */
    public String getKaiYbnNo() {

        return this.kaiYbnNo;

    }


    /**
     * ��ЏZ���P�̃Z�b�g
     * 
     * @param kaiAdr1Kj ��ЏZ���P
     */
    public void setKaiAdr1Kj(String kaiAdr1Kj) {

        this.kaiAdr1Kj = kaiAdr1Kj;

    }


    /**
     * ��ЏZ���P�̎擾
     * 
     * @return ��ЏZ���P
     */
    public String getKaiAdr1Kj() {

        return this.kaiAdr1Kj;

    }


    /**
     * ��ЏZ���Q�̃Z�b�g
     * 
     * @param kaiAdr2Kj ��ЏZ���Q
     */
    public void setKaiAdr2Kj(String kaiAdr2Kj) {

        this.kaiAdr2Kj = kaiAdr2Kj;

    }


    /**
     * ��ЏZ���Q�̎擾
     * 
     * @return ��ЏZ���Q
     */
    public String getKaiAdr2Kj() {

        return this.kaiAdr2Kj;

    }


    /**
     * ��Гd�b�ԍ��̃Z�b�g
     * 
     * @param kaiTelNo ��Гd�b�ԍ�
     */
    public void setKaiTelNo(String kaiTelNo) {

        this.kaiTelNo = kaiTelNo;

    }


    /**
     * ��Гd�b�ԍ��̎擾
     * 
     * @return ��Гd�b�ԍ�
     */
    public String getKaiTelNo() {

        return this.kaiTelNo;

    }


    /**
     * ���FAX�ԍ��̃Z�b�g
     * 
     * @param kaiFaxBng ���FAX�ԍ�
     */
    public void setKaiFaxBng(String kaiFaxBng) {

        this.kaiFaxBng = kaiFaxBng;

    }


    /**
     * ���FAX�ԍ��̎擾
     * 
     * @return ���FAX�ԍ�
     */
    public String getKaiFaxBng() {

        return this.kaiFaxBng;

    }

	/**
     * 
     * ��Ѓ��Ssrc�̃Z�b�g
     * 
     * @param kaiLogoSrc ��Ѓ��Ssrc
     */
    public void setKaiLogoSrc(String kaiLogoSrc) {
    	
        this.kaiLogoSrc = kaiLogoSrc;
    }


    /**
     * ��Ѓ��Ssrc�̎擾
     * 
     * @return ��Ѓ��Ssrc
     */
    public String getKaiLogoSrc() {
		String returnStr = this.kaiLogoSrc;
        return returnStr;

    }
	
	/**
     * ���y�[�W�t���O�̃Z�b�g
     * 
     * @param sort_ketCod ���y�[�W�t���O
     */
    public void setPageBreak(boolean b) {

        this.pageBreak = b;

    }
    /**
     * ���y�[�W�t���O�̎擾
     * 
     * @return ���y�[�W�t���O
     */
    public boolean getPageBreak() {

        return this.pageBreak;

    }
    
    /**
     * �����ރt���O�̃Z�b�g
     * 
     * @param fukSziFlg �����ރt���O
     */
    public void setFukSziFlg(boolean b) {

        this.fukSziFlg = b;

    }
    /**
     * �����ރt���O�̎擾
     * 
     * @return �����ރt���O
     */
    public boolean getFukSziFlg() {

        return this.fukSziFlg;

    }

    /**
     * ��Ж��̃Z�b�g
     * 
     * @param kaiNmKj ��Ж�
     */
    public void setKaiNmKj(String kaiNmKj) {

        this.kaiNmKj = kaiNmKj;

    }


    /**
     * ��Ж��̎擾
     * 
     * @return ��Ж�
     */
    public String getKaiNmKj() {

        return this.kaiNmKj;

    }


    /**
     * ���������t�̃Z�b�g
     * 
     * @param hacSyoDte ���������t
     */
    public void setHacSyoDte(String hacSyoDte) {

        this.hacSyoDte = hacSyoDte;

    }


    /**
     * ���������t�̎擾
     * 
     * @return ���������t
     */
    public String getHacSyoDte() {

        return this.hacSyoDte;

    }


    /**
     * �������ԍ��̃Z�b�g
     * 
     * @param hacSyoBng �������ԍ�
     */
    public void setHacSyoBng(String hacSyoBng) {

        this.hacSyoBng = hacSyoBng;

    }


    /**
     * �������ԍ��̎擾
     * 
     * @return �������ԍ�
     */
    public String getHacSyoBng() {

        return this.hacSyoBng;

    }


    /**
     * ������R�[�h�̃Z�b�g
     * 
     * @param hacCod ������R�[�h
     */
    public void setHacCod(String hacCod) {

        this.hacCod = hacCod;

    }


    /**
     * ������R�[�h�̎擾
     * 
     * @return ������R�[�h
     */
    public String getHacCod() {

        return this.hacCod;

    }


    /**
     * �����於�̂̃Z�b�g
     * 
     * @param sirHacNm �����於��
     */
    public void setSirHacNm(String sirHacNm) {

        this.sirHacNm = sirHacNm;

    }


    /**
     * �����於�̂̎擾
     * 
     * @return �����於��
     */
    public String getSirHacNm() {

        return this.sirHacNm;

    }

	/**
     * ���ݓ����̃Z�b�g
     * 
     * @param sirHacNm ���ݓ���
     */
    public void setCurrentDate(String currentDate) {

        this.currentDate = currentDate;

    }


    /**
     * ���ݓ����̎擾
     * 
     * @return ���ݓ���
     */
    public String getCurrentDate() {

        return this.currentDate;

    }

//2005/05/19 add
	/**
	 * ��Ж������Q�̎擾
	 * @return
	 */
	public String getKaiNmKj2() {
		return kaiNmKj2;
	}

	/**
	 * ��Ж������Q�̃Z�b�g
	 * @param string
	 */
	public void setKaiNmKj2(String string) {
		kaiNmKj2 = string;
	}

//2005/06/14 add
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


//�X�V�����,������ 2005/08/29 add

	/**
	 * 16�i���̓��t��10�i��YY/MM/DD�ŕԂ��܂��B
	 */
	private String format_16(String str) {
		if (str == null || str.trim().equals(""))
			return "";
		int date16Int = Integer.parseInt(str, 16);
		str = DataFormatUtils.setFormatYYMMDD(date16Int + "");
		return str;
	}

	/**
	 * 16�i���̓��t��10�i��YY/MM/DD�ŃZ�b�g���܂��B
	 * @param string
	 */
	public void setRrkTbl(
		String string1,
		String string2,
		String string3,
		String string4,
		String string5) {
			
		rrkTbl_1 = format_16(string1);
		rrkTbl_2 = format_16(string2);
		rrkTbl_3 = format_16(string3);
		rrkTbl_4 = format_16(string4);
		rrkTbl_5 = format_16(string5);
	}
	
	/**
	 * @return
	 */
	public String getRrkTbl_1() {
		return rrkTbl_1;
	}
	/**
	 * @param string
	 */
	public void setRrkTbl_1(String string) {
		rrkTbl_1 = string;
	}
	/**
	 * 
	 * @return
	 */
	public String getRrkTbl_2() {
	  return rrkTbl_2;
	}
	/**
	 * @param string
	 */
	public void setRrkTbl_2(String string) {
	  rrkTbl_2 = string;
	}
	/**
	 * @return
	 */
	public String getRrkTbl_3() {
	  return rrkTbl_3;
	}
	/**
	 * @param string
	 */
	public void setRrkTbl_3(String string) {
	  rrkTbl_3 = string;
	}
	/**
	 * @return
	 */
	public String getRrkTbl_4() {
	  return rrkTbl_4;
	}
	/**
	 * @param string
	 */
	public void setRrkTbl_4(String string) {
	  rrkTbl_4 = string;
	}
	/**
	 * @return
	 */
	public String getRrkTbl_5() {
		return rrkTbl_5;
	}
	/**
	 * @param string
	 */
	public void setRrkTbl_5(String string) {
		rrkTbl_5 = string;
	}

	/**
	 * @return
	 */
	public String getRrkUpDte() {
		return rrkUpDte;
	}

	/**
	 * @param string
	 */
	public void setRrkUpDte(String string) {
		rrkUpDte = string;
	}

	/**
	 * �����񐔂̎擾
	 * @return
	 */
	public int getTeiNum() {
		return teiNum;
	}

	/**
	 * �����񐔂̐ݒ�
	 * @param i
	 */
	public void setTeiNum(int i) {
		teiNum = i;
	}


	//---------------------------------------------------------------------------------
	public int getSize(){
		return details.size();
	}
	
	public void setSize(int size){
		if(size > details.size()){
			int j = size - this.getSize();			
		  for(int k=0; k<j; k++){
		    details.add(new PrintOrdersRow());			  	
		  }						
		}else if(size < details.size()){
			for(int i = details.size();i > size;i--){
				details.remove((i-1));
			}
		}
	}
	public PrintOrdersRow getOrdersRow(int i){
		PrintOrdersRow or = (PrintOrdersRow)details.get(i);
		if(or == null){
			or = new PrintOrdersRow();
			details.set(i,or);
		}
		return or;
	}

	public void clearAll(){
		this.queryDaiKaiSkbCod = "";
		this.kaiSkbCod = "";
        this.kaiNmKj = "";
        this.hacSyoDte = "";
        this.hacSyoBng = "";
        this.hacCod = "";
        this.sirHacNm = "";
		this.kaiNmKj2=""; 	//2005/05/19 add
        this.usrId = ""; 	//2005/06/14 add
		this.rrkTbl_1 = ""; //2005/09/06 add
		this.rrkTbl_2 = "";
		this.rrkTbl_3 = "";
		this.rrkTbl_4 = "";
		this.rrkTbl_5 = "";
		this.rrkUpDte = "";
		this.teiNum = 0;

		removeAllRow();		
	}
	
	
	//�s	
	public ArrayList getOrdersRowList(){
		return details;
	}
	
	public void setOrdersRowList(ArrayList arr){
		details = arr;
	}
	
	public void addOrdersRowList(PrintOrdersRow row){
		details.add(row);
	}
	
	public void removeAllRow(){
		details.clear();
	}
	

	//---------------------------------------------------------------------------------

	/**
	 * ��Џ����Z�b�g���܂��B
	 * @param vo
	 */
	public void setKaiInfo(PrintOrdersVO vo) {

		setKaiNmKj(vo.getKaiNmKj());
		setKaiYbnNo(vo.getKaiYbnNo());
		setKaiAdr1Kj(vo.getKaiAdr1Kj());	
		setKaiAdr2Kj(vo.getKaiAdr2Kj());
		setKaiTelNo(vo.getKaiTelNo());	
		setKaiFaxBng(vo.getKaiFaxBng());
		setKaiLogoSrc(vo.getKaiLogoSrc());
		//2005/05/19 kaiNmKj2,queryDaiKaiSkbCod add
		setKaiNmKj2(vo.getKaiNmKj2());
		setQueryDaiKaiSkbCod(vo.getDaiKaiSkbCod());
		
	}	


}

