/**
* ManualOrderVO  �}�j���A�������w���o�����[�I�u�W�F�N�g�N���X
*
*	�쐬��    2003/04/25
*	�쐬��    �i�m�h�h�j���c �Ĕ�
* �����T�v    �i�ԃ}�X�^�[(FTBHIN01)�A�݌Ƀe�[�u��(FTBZAI01)�A���������e�[�u��(FTBHAC02)�A
* 			 �����ރ}�X�^�[(FTBFUK01)�A�����ރp�^�[���}�X�^�[(FTBFUK02)����擾�����f�[�^���i�[����N���X�B
*
*	[�ύX����]
*       2003/06/16�i�m�h�h�j�g�c �h�q
*  			�E�������ځi��ЁE���[�J�[���ށE�`�ԃR�[�h�E�M�m�敪�j�̒ǉ��B
* 		2004/03/29 (NII) �X
* 			�E�i�ԃ}�X�^�̃e�[�u�����ύX
*		2005/02/03�i�m�h�h�j�g�c
*			�E�ꏊ�R�[�h�̒ǉ�
*		2005/05/25�i�m�h�h�j�g�c
*			�E���[�U�[�h�c�̒ǉ�
*/

package com.jds.prodord.indicate.manualorder;

import java.util.*;
import com.jds.prodord.order.prsorder.*;
public class ManualOrderVO {

	private String command = "";

	private String daiKaiSkbCod = "";
	private String queryKaiSkbCod = "";
	private ArrayList queryKaiSkbCodList = null;
	private String bshCod = "";
	private String usrId = "";
	
	private ArrayList hbiDte_arr = new ArrayList();
	private int hbiDte1 = 0; 
	private int hbiDte2 = 0; 
	private int hbiDte3 = 0; 
	private int hbiDte4 = 0; 
	private int hbiDte5 = 0; 
	
	private String kbn = "";
	
	private ArrayList kigBng_arr = new ArrayList();

	private ArrayList ketCod_arr = new ArrayList();
	private ArrayList mkrBun_arr = new ArrayList();
	private ArrayList hyoKbn_arr = new ArrayList();
	private ArrayList kaiSkbCod_arr = new ArrayList();

	private String fukSziMkr ="";
	private String fukSziHacSaki ="";
	private String fukSziNm ="";
	private String fukSziCod ="";
	private String fukSziPtnCod ="";

	
	private boolean exsitFuk01 = false;
	private ArrayList fukSziCod_arr = new ArrayList();


	//�v���X������ʂɓn��VO
	private	PrsOrderVO[] prsVOs = null;
	
	private boolean sort_ketCod = false;
	private boolean sort_sortKey = false;

	
    /**
     * �R�}���h�̃Z�b�g
     * 
     * @param command �R�}���h
     */
    public void setCommand(String command) {

        this.command = command;

    }


    /**
     * �R�}���h�̎擾
     * 
     * @return �R�}���h
     */
    public String getCommand() {

        return this.command;

    }

    /**
     * ���[�U�[�̑�\��Ў��ʃR�[�h�̃Z�b�g
     * 
     * @param daiKaiSkbCod ���[�U�[�̕\��Ў��ʃR�[�h
     */
    public void setDaiKaiSkbCod(String daiKaiSkbCod) {

        this.daiKaiSkbCod = daiKaiSkbCod;

    }


    /**
     * ���[�U�[�̑�\��Ў��ʃR�[�h�̎擾
     * 
     * @return ���[�U�[�̑�\��Ў��ʃR�[�h
     */
    public String getDaiKaiSkbCod() {

        return this.daiKaiSkbCod;

    }
    
    /**
     * ���[�U�[�̉�Ў��ʃR�[�h�̃Z�b�g
     * 
     * @param kaiSkbCod ���[�U�[�̉�Ў��ʃR�[�h
     */
    public void setQueryKaiSkbCod(String queryKaiSkbCod) {

        this.queryKaiSkbCod = queryKaiSkbCod;

    }


    /**
     * ���[�U�[�̉�Ў��ʃR�[�h�̎擾
     * 
     * @return ���[�U�[�̉�Ў��ʃR�[�h
     */
    public String getQueryKaiSkbCod() {

        return this.queryKaiSkbCod;

    }
    
    //��Ў��ʃR�[�h���X�g
    public ArrayList getQueryKaiSkbCodList(){
    	return queryKaiSkbCodList;
    }
    public void setQueryKaiSkbCodList(ArrayList arr){
    	queryKaiSkbCodList = arr;
    }
    

//--------------------------------------------------------------------------

    
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
     * ������1
     * 
     */
	public int getHbiDte1() {
		return hbiDte1;
	}
	public void setHbiDte1(ManualOrderForm.FormDate date){
		String year = date.getYear().trim();
		String month = date.getMonth().trim();
		String day = date.getDay().trim();
		if(year.equals("") || month.equals("") || day.equals(""))
			hbiDte1 = 0;
		else
			hbiDte1 = (Integer.parseInt(year,10)*10000) + (Integer.parseInt(month,10)*100) + Integer.parseInt(day,10);
	}
    /** 
     * ������2
     * 
     */
	public int getHbiDte2() {
		return hbiDte2;
	}
	public void setHbiDte2(ManualOrderForm.FormDate date){
		String year = date.getYear().trim();
		String month = date.getMonth().trim();
		String day = date.getDay().trim();
		if(year.equals("") || month.equals("") || day.equals(""))
			hbiDte2 = 0;
		else
			hbiDte2 = (Integer.parseInt(year,10)*10000) + (Integer.parseInt(month,10)*100) + Integer.parseInt(day,10);
	}
    /** 
     * ������3
     * 
     */
	public int getHbiDte3() {
		return hbiDte3;
	}
	public void setHbiDte3(ManualOrderForm.FormDate date){
		String year = date.getYear().trim();
		String month = date.getMonth().trim();
		String day = date.getDay().trim();
		if(year.equals("") || month.equals("") || day.equals(""))
			hbiDte3 = 0;
		else
			hbiDte3 = (Integer.parseInt(year,10)*10000) + (Integer.parseInt(month,10)*100) + Integer.parseInt(day,10);
	}
    /** 
     * ������4
     * 
     */
	public int getHbiDte4() {
		return hbiDte4;
	}
	public void setHbiDte4(ManualOrderForm.FormDate date){
		String year = date.getYear().trim();
		String month = date.getMonth().trim();
		String day = date.getDay().trim();
		if(year.equals("") || month.equals("") || day.equals(""))
			hbiDte4 = 0;
		else
			hbiDte4 = (Integer.parseInt(year,10)*10000) + (Integer.parseInt(month,10)*100) + Integer.parseInt(day,10);
	}
    /** 
     * ������5
     * 
     */
	public int getHbiDte5() {
		return hbiDte5;
	}
	public void setHbiDte5(ManualOrderForm.FormDate date){
		String year = date.getYear().trim();
		String month = date.getMonth().trim();
		String day = date.getDay().trim();
		if(year.equals("") || month.equals("") || day.equals(""))
			hbiDte5 = 0;
		else
			hbiDte5 = (Integer.parseInt(year,10)*10000) + (Integer.parseInt(month,10)*100) + Integer.parseInt(day,10);
	}
    
//-------------------------------------------------------------���̓G���A�̔z��


    /**
     * �������̃Z�b�g
     * 
     * @param hbiDte_arr ������
     */
    public void setHbiDte_arr(ArrayList hbiDte_arr) {

        this.hbiDte_arr = hbiDte_arr;
   
    }


    /**
     * �������̎擾
     * 
     * @return ������
     */
    public ArrayList getHbiDte_arr() {
    	hbiDte_arr.clear();
    	if(hbiDte1 != 0)
	    	hbiDte_arr.add(hbiDte1+"");
	    if(hbiDte2 != 0)
	    	hbiDte_arr.add(hbiDte2+"");
	    if(hbiDte3 != 0)
	    	hbiDte_arr.add(hbiDte3+"");
	    if(hbiDte4 != 0)
	    	hbiDte_arr.add(hbiDte4+"");
	    if(hbiDte5 != 0)
	    	hbiDte_arr.add(hbiDte5+"");

        return this.hbiDte_arr;

    }



    /**
     * �L���ԍ��̃Z�b�g
     * 
     * @param kigBng_arr �L���ԍ�
     */
    public void setKigBng_arr(ArrayList kigBng_arr) {

        this.kigBng_arr = kigBng_arr;
   
    }


    /**
     * �L���ԍ��̎擾
     * 
     * @return �L���ԍ�
     */
    public ArrayList getKigBng_arr() {

        return this.kigBng_arr;

    }
    
//2003/06/16
    /**
     * ��Ў��ʃR�[�h�̃Z�b�g
     * 
     * @param kaiSkbCod_arr ��Ў��ʃR�[�h
     */
    public void setKaiSkbCod_arr(ArrayList kaiSkbCod_arr) {

        this.kaiSkbCod_arr = kaiSkbCod_arr;

    }


    /**
     * ��Ў��ʃR�[�h�̎擾
     * 
     * @return ��Ў��ʃR�[�h
     */
    public ArrayList getKaiSkbCod_arr() {

        return this.kaiSkbCod_arr;

    }
    
	/**
     * �`�ԃR�[�h�̃Z�b�g
     * 
     * @param ketCod_arr �`�ԃR�[�h
     */
    public void setKetCod_arr(ArrayList ketCod_arr) {

        this.ketCod_arr = ketCod_arr;

    }


    /**
     * �`�ԃR�[�h�̎擾
     * 
     * @return �`�ԃR�[�h
     */
    public ArrayList getKetCod_arr() {

        return this.ketCod_arr;

    }


    /**
     * ���[�J�[���ނ̃Z�b�g
     * 
     * @param mkrBun_arr ���[�J�[����
     */
    public void setMkrBun_arr(ArrayList mkrBun_arr) {

        this.mkrBun_arr = mkrBun_arr;

    }


    /**
     * ���[�J�[���ނ̎擾
     * 
     * @return ���[�J�[����
     */
    public ArrayList getMkrBun_arr() {

        return this.mkrBun_arr;

    }


    /**
     * �M�m�敪�̃Z�b�g
     * 
     * @param hyoKbn_arr �M�m�敪
     */
    public void setHyoKbn_arr(ArrayList hyoKbn_arr) {

        this.hyoKbn_arr = hyoKbn_arr;

    }


    /**
     * �M�m�敪�̎擾
     * 
     * @return �M�m�敪
     */
    public ArrayList getHyoKbn_arr() {

        return this.hyoKbn_arr;

    }    



    //-----------------------------------------------------------------------------------
    

	/**
     * PrsOrderVO[]�̃Z�b�g
     * 
     * @param prsVOs PrsOrderVO[]
     */
    public void setPrsVOs(PrsOrderVO[] prsVOs) {

        this.prsVOs = prsVOs;
   
    }


    /**
     * PrsOrderVO[]�̎擾
     * 
     * @return PrsOrderVO[]
     */
    public PrsOrderVO[] getPrsVOs() {

        return this.prsVOs;

    }
    //--------------------------------------------------------------------------------------------
    
	/**
     * �\�[�g����-�`�ԃR�[�h�̃Z�b�g
     * 
     * @param sort_ketCod �\�[�g����-�`�ԃR�[�h
     */
    public void setSort_ketCod(boolean b) {

        this.sort_ketCod = b;

    }
    /**
     * �\�[�g����-�`�ԃR�[�h�̎擾
     * 
     * @return �\�[�g����-�`�ԃR�[�h
     */
    public boolean getSort_ketCod() {

        return this.sort_ketCod;

    }
	/**
     * �\�[�g����-�\�[�g�L�[�̃Z�b�g
     * 
     * @param sort_sortKey �\�[�g����-�\�[�g�L�[
     */
    public void setSort_sortKey(boolean b) {

        this.sort_sortKey = b;

    }
    /**
     * �\�[�g����-�\�[�g�L�[�̎擾
     * 
     * @return �\�[�g����-�\�[�g�L�[
     */
    public boolean getSort_sortKey() {

        return this.sort_sortKey;

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

}

