/**
* ManualOrderForm  �}�j���A�������w���t�H�[���N���X
*
*	�쐬��    2003/04/25
*	�쐬��    �i�m�h�h�j���c �Ĕ�
* �����T�v    Http���N�G�X�g�ƃ��N�G�X�g�̏������ʂ��i�[����N���X�B
*
*	[�ύX����]
*        2003/06/16�i�m�h�h�j�g�c �h�q
*  			�E�������ځi��ЁE���[�J�[���ށE�`�ԃR�[�h�E�M�m�敪�j�̒ǉ��B
* 		 2003/07/23 �i�m�h�h�j���c �Ĕ�
* 			�E�\�[�g�����ǉ��i�`�ԃR�[�h�A�\�[�g�L�[�j�B
* 		 2003/09/11 �i�m�h�h�j���c �Ĕ�
* 			�E�v���X�E�����ޔ�����ʂ���߂��Ă������A���������̓��͓��e��ێ����Ă����悤�ɏC��
* 		 2004/03/08�@(�m�h�h) �X
* 			�E�V���敪"���̑�"�ǉ��ɔ����C��
*		 2004/07/02�i�m�h�h�j�g�c
*			�E�R�s�[���y�[�X�g�@�\�ǉ�
*		 2005/02/03�i�m�h�h�j�g�c
*			�E�ꏊ�R�[�h�̒ǉ�
*		 2005/05/25�i�m�h�h�j�g�c
*			�E���[�U�[�h�c�̒ǉ�
* 		 2006/05/10�i�m�h�h�j�c�[ �N��
* 			�E�L���O�Ђ̃����N���b�܂�
* 
*/

package com.jds.prodord.indicate.manualorder;

import org.apache.struts.action.*;
import javax.servlet.http.*;
import java.util.*;
import com.jds.prodord.common.*;

public class ManualOrderForm extends ActionForm {

	public static final String COMMAND_CLEAR = "COMMAND_CLEAR";
	public static final String COMMAND_PRSHACHU = "COMMAND_PRSHACHU";
	public static final String COMMAND_FUKUHACHU = "COMMAND_FUKUHACHU";
//	public static final String COMMAND_SONOTAHACHU = "COMMAND_SONOTAHACHU";
	public static final String COMMAND_PASTE = "�\��t��";//2004/06/29 add
	
	public static final boolean SELECT_HBI = true;
	public static final boolean SELECT_HIN = false;
	
	public static final String KYUHU = "0";
	public static final String SINPU = "1";
	public static final String SAMPLE = "2";
	public static final String DEMOBAN = "3";
	
//	public static final String[] RANK = {"N0","N1","N2","N3","S","A","B","C","D"};
	public static final String[] VL_HYOKBN = {"H","Y"};
	public static final String[] LB_HYOKBN = {"�M�y","�m�y"};
		
	private String command = "";
	
	private ArrayList vl_kaiSkbCod = new ArrayList();
	private ArrayList vl_ketCod = new ArrayList();
	private ArrayList vl_mkrBun = new ArrayList();
	private ArrayList vl_hyoKbn = new ArrayList();
	private ArrayList lb_hyoKbn = new ArrayList();
	public static final boolean RB_ALL = true;
	public static final boolean RB_SELECT = false;
	
	private boolean rb_select = true;
	private boolean rb_kaiSkbCod = true;
	private boolean rb_ketCod = true;
	private boolean rb_mkrBun = true;
	private boolean rb_hyoKbn = true;
	private boolean rb_kigBng = true;
	
	//�������P
    private FormDate hbiDte1 = new FormDate();
    //�������Q
    private FormDate hbiDte2 = new FormDate();
    //�������R
    private FormDate hbiDte3 = new FormDate();
    //�������S
    private FormDate hbiDte4 = new FormDate();
    //�������T
    private FormDate hbiDte5 = new FormDate();
    
    
	//�敪
	private String kbn = "";
	
	//�L���ԍ��P�`�P�T
	private KigBng kigBng1 = new KigBng();
	private KigBng kigBng2 = new KigBng();
	private KigBng kigBng3 = new KigBng();
	private KigBng kigBng4 = new KigBng();
	private KigBng kigBng5 = new KigBng();
	private KigBng kigBng6 = new KigBng();
	private KigBng kigBng7 = new KigBng();
	private KigBng kigBng8 = new KigBng();
	private KigBng kigBng9 = new KigBng();
	private KigBng kigBng10 = new KigBng();
	private KigBng kigBng11 = new KigBng();
	private KigBng kigBng12 = new KigBng();
	private KigBng kigBng13 = new KigBng();
	private KigBng kigBng14 = new KigBng();
	private KigBng kigBng15 = new KigBng();
	
	private String queryDaiKaiSkbCod = "";
	private String queryKaiSkbCod = "";
	private ArrayList queryKaiSkbCodList = null;
	private String bshCod = "";//2005/02/03 add
	private String usrId = "";//2005/05/23 add
	
	private String[] kaiSkbCod = null;
	private String[] ketCod = null;
	private String[] mkrBun = null;
	private String[] hyoKbn = null;
	
	private boolean sort_ketCod = false;
	private boolean sort_sortKey = false;
	
	private String cpPaste =""; //2004/06/29 add
	private String paste ="";
	
	// ��Đ��Ɖ�ʉ�ʂ���߂��Ă������̂��߁A ************
	// �`�F�b�N�{�b�N�X�̒l��ێ����� 2003/09/11 add
	private boolean[] checkBoxValue = new boolean[2];
	
	public void setCheckBoxValueToArray(){
		checkBoxValue[0] = sort_ketCod;//�`�ԃR�[�h
		checkBoxValue[1] = sort_sortKey;//�\�[�g�L�[
	}
	public void setCheckBoxValue(){
		sort_ketCod = checkBoxValue[0];
		sort_sortKey = checkBoxValue[1];
	}
	public void clearCheckBoxValue(){
		for(int i = 0; i < checkBoxValue.length; i++){
			checkBoxValue[i] = false;
		}
	}
	//*****************************************************
	
	//���j���[���炫���Ƃ���true���Z�b�g
	private boolean fromMenuSgn = false;
	public boolean getFromMenuSgn(){
		return fromMenuSgn;
	}
	public void setFromMenuSgn(boolean fromMenuSgn){
			this.fromMenuSgn = fromMenuSgn;
	}
	
	public ManualOrderForm(){
		super();
		this.clearAll();
		this.setRb_select(SELECT_HBI);
		this.setCommand("INIT");
		this.setOptions();//�I�����ڂ̃I�v�V�������Z�b�g
		this.setRb_default();
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

	public Collection getVl_ketCod(){
		return (Collection)vl_ketCod;
	}
	public Collection getVl_kaiSkbCod(){
		return (Collection)vl_kaiSkbCod;
	}
	public Collection getVl_mkrBun(){
		return (Collection)vl_mkrBun;
	}
	public Collection getVl_hyoKbn(){
		return (Collection)vl_hyoKbn;
	}
	public Collection getLb_hyoKbn(){
		return (Collection)lb_hyoKbn;
	}


	//�I�����ڂ̃I�v�V�������Z�b�g���郁�\�b�h
	public void setOptions(){
		if(vl_hyoKbn.size() == 0){
			for(int i = 0; i<VL_HYOKBN.length; i++){
				vl_hyoKbn.add(VL_HYOKBN[i]);
			}
		}
		if(lb_hyoKbn.size() == 0){
			for(int i = 0; i<LB_HYOKBN.length; i++){
				lb_hyoKbn.add(LB_HYOKBN[i]);
			}
		}
	}
	public void setKaiSkbCodOptions(ArrayList queryKaiSkbCodList){
		vl_kaiSkbCod.clear();
		for(int i = 0; i<queryKaiSkbCodList.size(); i++){
			vl_kaiSkbCod.add(queryKaiSkbCodList.get(i)+"");
		}
	}
	public void setKetCodOptions(ArrayList ketNmList){
		vl_ketCod.clear();
		for(int i = 0; i<ketNmList.size(); i++){
			vl_ketCod.add(ketNmList.get(i)+"");
		}
	}
	public void setMkrBunOptions(ArrayList mkrBunList){
		vl_mkrBun.clear();
		for(int i = 0; i<mkrBunList.size(); i++){
			vl_mkrBun.add(mkrBunList.get(i)+"");
		}
	}
	
    /**
     * ���͗��I�����W�I�{�^���̃Z�b�g
     * 
     * @param sort_ketCod ���͗��I�����W�I�{�^��
     */
    public void setRb_select(boolean b) {

        this.rb_select = b;

    }
    /**
     * ���͗��I�����W�I�{�^���̎擾
     * 
     * @return ���͗��I�����W�I�{�^��
     */
    public boolean getRb_select() {

        return this.rb_select;

    }
    
    /**
     * �������P�̃Z�b�g
     * 
     * @param hbiDte1 �������P
     */
    public void setHbiDte1(FormDate hbiDte1) {

        this.hbiDte1 = hbiDte1;

    }


    /**
     * �������P�̎擾
     * 
     * @return �������P
     */
    public FormDate getHbiDte1() {

        return this.hbiDte1;

    }


    /**
     * �������Q�̃Z�b�g
     * 
     * @param hbiDte2 �������Q
     */
    public void setHbiDte2(FormDate hbiDte2) {

        this.hbiDte2 = hbiDte2;

    }


    /**
     * �������Q�̎擾
     * 
     * @return �������Q
     */
    public FormDate getHbiDte2() {

        return this.hbiDte2;

    }


    /**
     * �������R�̃Z�b�g
     * 
     * @param hbiDte3 �������R
     */
    public void setHbiDte3(FormDate hbiDte3) {

        this.hbiDte3 = hbiDte3;

    }


    /**
     * �������R�̎擾
     * 
     * @return �������R
     */
    public FormDate getHbiDte3() {

        return this.hbiDte3;

    }


    /**
     * �������S�̃Z�b�g
     * 
     * @param hbiDte4 �������S
     */
    public void setHbiDte4(FormDate hbiDte4) {

        this.hbiDte4 = hbiDte4;

    }


    /**
     * �������S�̎擾
     * 
     * @return �������S
     */
    public FormDate getHbiDte4() {

        return this.hbiDte4;

    }


    /**
     * �������T�̃Z�b�g
     * 
     * @param hbiDte5 �������T
     */
    public void setHbiDte5(FormDate hbiDte5) {

        this.hbiDte5 = hbiDte5;

    }


    /**
     * �������T�̎擾
     * 
     * @return �������T
     */
    public FormDate getHbiDte5() {

        return this.hbiDte5;

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
     * �L���ԍ��P�̃Z�b�g
     * 
     * @param kigBng1 �L���ԍ��P
     */
    public void setKigBng1(KigBng kigBng1) {

        this.kigBng1 = kigBng1;

    }


    /**
     * �L���ԍ��P�̎擾
     * 
     * @return �L���ԍ��P
     */
    public KigBng getKigBng1() {

        return this.kigBng1;

    }


    /**
     * �L���ԍ��Q�̃Z�b�g
     * 
     * @param kigBng2 �L���ԍ��Q
     */
    public void setKigBng2(KigBng kigBng2) {

        this.kigBng2 = kigBng2;

    }


    /**
     * �L���ԍ��Q�̎擾
     * 
     * @return �L���ԍ��Q
     */
    public KigBng getKigBng2() {

        return this.kigBng2;

    }


    /**
     * �L���ԍ��R�̃Z�b�g
     * 
     * @param kigBng3 �L���ԍ��R
     */
    public void setKigBng3(KigBng kigBng3) {

        this.kigBng3 = kigBng3;

    }


    /**
     * �L���ԍ��R�̎擾
     * 
     * @return �L���ԍ��R
     */
    public KigBng getKigBng3() {

        return this.kigBng3;

    }


    /**
     * �L���ԍ��S�̃Z�b�g
     * 
     * @param kigBng4 �L���ԍ��S
     */
    public void setKigBng4(KigBng kigBng4) {

        this.kigBng4 = kigBng4;

    }


    /**
     * �L���ԍ��S�̎擾
     * 
     * @return �L���ԍ��S
     */
    public KigBng getKigBng4() {

        return this.kigBng4;

    }


    /**
     * �L���ԍ��T�̃Z�b�g
     * 
     * @param kigBng5 �L���ԍ��T
     */
    public void setKigBng5(KigBng kigBng5) {

        this.kigBng5 = kigBng5;

    }


    /**
     * �L���ԍ��T�̎擾
     * 
     * @return �L���ԍ��T
     */
    public KigBng getKigBng5() {

        return this.kigBng5;

    }


    /**
     * �L���ԍ��U�̃Z�b�g
     * 
     * @param kigBng6 �L���ԍ��U
     */
    public void setKigBng6(KigBng kigBng6) {

        this.kigBng6 = kigBng6;

    }


    /**
     * �L���ԍ��U�̎擾
     * 
     * @return �L���ԍ��U
     */
    public KigBng getKigBng6() {

        return this.kigBng6;

    }


    /**
     * �L���ԍ��V�̃Z�b�g
     * 
     * @param kigBng7 �L���ԍ��V
     */
    public void setKigBng7(KigBng kigBng7) {

        this.kigBng7 = kigBng7;

    }


    /**
     * �L���ԍ��V�̎擾
     * 
     * @return �L���ԍ��V
     */
    public KigBng getKigBng7() {

        return this.kigBng7;

    }


    /**
     * �L���ԍ��W�̃Z�b�g
     * 
     * @param kigBng8 �L���ԍ��W
     */
    public void setKigBng8(KigBng kigBng8) {

        this.kigBng8 = kigBng8;

    }


    /**
     * �L���ԍ��W�̎擾
     * 
     * @return �L���ԍ��W
     */
    public KigBng getKigBng8() {

        return this.kigBng8;

    }


    /**
     * �L���ԍ��X�̃Z�b�g
     * 
     * @param kigBng9 �L���ԍ��X
     */
    public void setKigBng9(KigBng kigBng9) {

        this.kigBng9 = kigBng9;

    }


    /**
     * �L���ԍ��X�̎擾
     * 
     * @return �L���ԍ��X
     */
    public KigBng getKigBng9() {

        return this.kigBng9;

    }


    /**
     * �L���ԍ��P�O�̃Z�b�g
     * 
     * @param kigBng10 �L���ԍ��P�O
     */
    public void setKigBng10(KigBng kigBng10) {

        this.kigBng10 = kigBng10;

    }


    /**
     * �L���ԍ��P�O�̎擾
     * 
     * @return �L���ԍ��P�O
     */
    public KigBng getKigBng10() {

        return this.kigBng10;

    }


    /**
     * �L���ԍ��P�P�̃Z�b�g
     * 
     * @param kigBng11 �L���ԍ��P�P
     */
    public void setKigBng11(KigBng kigBng11) {

        this.kigBng11 = kigBng11;

    }


    /**
     * �L���ԍ��P�P�̎擾
     * 
     * @return �L���ԍ��P�P
     */
    public KigBng getKigBng11() {

        return this.kigBng11;

    }


    /**
     * �L���ԍ��P�Q�̃Z�b�g
     * 
     * @param kigBng12 �L���ԍ��P�Q
     */
    public void setKigBng12(KigBng kigBng12) {

        this.kigBng12 = kigBng12;

    }


    /**
     * �L���ԍ��P�Q�̎擾
     * 
     * @return �L���ԍ��P�Q
     */
    public KigBng getKigBng12() {

        return this.kigBng12;

    }


    /**
     * �L���ԍ��P�R�̃Z�b�g
     * 
     * @param kigBng13 �L���ԍ��P�R
     */
    public void setKigBng13(KigBng kigBng13) {

        this.kigBng13 = kigBng13;

    }


    /**
     * �L���ԍ��P�R�̎擾
     * 
     * @return �L���ԍ��P�R
     */
    public KigBng getKigBng13() {

        return this.kigBng13;

    }


    /**
     * �L���ԍ��P�S�̃Z�b�g
     * 
     * @param kigBng14 �L���ԍ��P�S
     */
    public void setKigBng14(KigBng kigBng14) {

        this.kigBng14 = kigBng14;

    }


    /**
     * �L���ԍ��P�S�̎擾
     * 
     * @return �L���ԍ��P�S
     */
    public KigBng getKigBng14() {

        return this.kigBng14;

    }


    /**
     * �L���ԍ��P�T�̃Z�b�g
     * 
     * @param kigBng15 �L���ԍ��P�T
     */
    public void setKigBng15(KigBng kigBng15) {

        this.kigBng15 = kigBng15;

    }


    /**
     * �L���ԍ��P�T�̎擾
     * 
     * @return �L���ԍ��P�T
     */
    public KigBng getKigBng15() {

        return this.kigBng15;

    }
    
    /**
     * ��Ў��ʃR�[�h�̃Z�b�g
     * 
     * @param kaiSkbCod ��Ў��ʃR�[�h
     */
    public void setKaiSkbCod(String[] s) {
    	
        this.kaiSkbCod = s;

    }
    /**
     * ��Ў��ʃR�[�h�̎擾
     * 
     * @return ��Ў��ʃR�[�h
     */
    public String[] getKaiSkbCod() {

        return this.kaiSkbCod;

    }
    
    /**
     * �`�ԃR�[�h�̃Z�b�g
     * 
     * @param ketCod �`�ԃR�[�h
     */
    public void setKetCod(String[] s) {

        this.ketCod = s;

    }
    /**
     * �`�ԃR�[�h�̎擾
     * 
     * @return �`�ԃR�[�h
     */
    public String[] getKetCod() {

        return this.ketCod;

    }
    /**
     * ���[�J�[���ނ̃Z�b�g
     * 
     * @param mkrBun ���[�J�[����
     */
    public void setMkrBun(String[] s) {

        this.mkrBun = s;

    }
    /**
     * ���[�J�[���ނ̎擾
     * 
     * @return ���[�J�[����
     */
    public String[] getMkrBun() {

        return this.mkrBun;

    }
    /**
     * �M�m�敪�̃Z�b�g
     * 
     * @param hyoKbn �M�m�敪
     */
    public void setHyoKbn(String[] s) {

        this.hyoKbn = s;

    }
    /**
     * �M�m�敪�̎擾
     * 
     * @return �M�m�敪
     */
    public String[] getHyoKbn() {

        return this.hyoKbn;

    }
    
    /**
     * ���W�I�{�^��-��Ђ̃Z�b�g
     * 
     * @param rb_KaiSkbCod ���W�I�{�^��-���
     */
    public void setRb_kaiSkbCod(boolean b) {

        this.rb_kaiSkbCod = b;

    }
    /**
     * ���W�I�{�^��-��Ђ̎擾
     * 
     * @return ���W�I�{�^��-���
     */
    public boolean getRb_kaiSkbCod() {

        return this.rb_kaiSkbCod;

    }
    
    /**
     * ���W�I�{�^��-�`�ԃR�[�h�̃Z�b�g
     * 
     * @param rbketCod ���W�I�{�^��-�`�ԃR�[�h
     */
    public void setRb_ketCod(boolean b) {

        this.rb_ketCod = b;

    }
    /**
     * ���W�I�{�^��-�`�ԃR�[�h�̎擾
     * 
     * @return ���W�I�{�^��-�`�ԃR�[�h
     */
    public boolean getRb_ketCod() {

        return this.rb_ketCod;

    }
    /**
     * ���W�I�{�^��-���[�J�[���ނ̃Z�b�g
     * 
     * @param rb_mkrBun ���W�I�{�^��-���[�J�[����
     */
    public void setRb_mkrBun(boolean b) {

        this.rb_mkrBun = b;

    }
    /**
     * ���W�I�{�^��-���[�J�[���ނ̎擾
     * 
     * @return ���W�I�{�^��-���[�J�[����
     */
    public boolean getRb_mkrBun() {

        return this.rb_mkrBun;

    }
    /**
     * ���W�I�{�^��-�M�m�敪�̃Z�b�g
     * 
     * @param rb_hyoKbn ���W�I�{�^��-�M�m�敪
     */
    public void setRb_hyoKbn(boolean b) {

        this.rb_hyoKbn = b;

    }
    /**
     * ���W�I�{�^��-�M�m�敪�̎擾
     * 
     * @return ���W�I�{�^��-�M�m�敪
     */
    public boolean getRb_hyoKbn() {

        return this.rb_hyoKbn;

    }
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
	
	/**
	 * ��\��t����{�^��
	 * @return
	 */
	public String getPaste() {
		return paste;
	}

	/**
	 * ��\��t����{�^��
	 * @param string
	 */
	public void setPaste(String string) {
		paste = string;
	}

    //---------------------------------------------------------------------------------

	public void clearAll(){
		command = "";
		this.setRb_select(SELECT_HBI);
		this.setKbn(SINPU);
		
		ArrayList hbiDte = this.getHbiDte_List();
		ArrayList kigBng = this.getKigBng_List();
		
		for(int i = 0; i<hbiDte.size(); i++){
			((FormDate)hbiDte.get(i)).clear();
		}
		for(int i = 0; i<kigBng.size(); i++){
			((KigBng)kigBng.get(i)).clear();
		}
		kaiSkbCod = null;
		ketCod = null;
		mkrBun = null;
		hyoKbn = null;
		this.setRb_default();
		this.setCheckBoxValueToArray();
		
		this.cpPaste="";//2004/06/29 add
  	}
	
	
	//���W�I�{�^�����f�t�H���g�ɃZ�b�g���郁�\�b�h
	public void setRb_default(){
		
		setRb_kaiSkbCod(RB_SELECT);
		setRb_ketCod(RB_SELECT);
		setRb_mkrBun(RB_ALL);
		setRb_hyoKbn(RB_ALL);
		
		setSort_ketCod(false);
		setSort_sortKey(true);
		
	}
	//�`�F�b�N�{�b�N�X�����Z�b�g���郁�\�b�h
	public void setCheck_false(){
		
		setSort_ketCod(false);
		setSort_sortKey(false);
		
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
		
		ArrayList hbiDte = this.getHbiDte_List();
		ArrayList kigBng = this.getKigBng_List();
		
		//�v���X���� �܂��� �����ޔ����̂Ƃ�
		if(command.equals(COMMAND_PRSHACHU) || command.equals(COMMAND_FUKUHACHU)){
			

			if(rb_select == SELECT_HBI){
				//��Ў��ʃR�[�h
				if(!rb_kaiSkbCod){ //false�Ȃ��SELECT
					if(kaiSkbCod == null){
						errors.add("",new ActionError("errors.input.required","��Ў��ʃR�[�h"));		
					}
				}
				//�`�ԃR�[�h
				if(!rb_ketCod){ //false�Ȃ��SELECT
					if(ketCod == null){
						errors.add("",new ActionError("errors.input.required","�`�ԃR�[�h"));		
					}
				}
				//���[�J�[����
				if(!rb_mkrBun){ //false�Ȃ��SELECT
					if(mkrBun == null){
						errors.add("",new ActionError("errors.input.required","���[�J�[����"));		
					}
				}
				if(this.hbiDte_IsBlank(hbiDte)){
					errors.add("",new ActionError("errors.input.required","������"));
				}else{
					//�Z�b�g�œ���
					this.hbiDte_IsSet(hbiDte,"������",errors);
					//���t�^�œ���
					this.hbiDte_IsDate(hbiDte,"������",errors);
				}
			}
			if(rb_select == SELECT_HIN){
				if(this.kigBng_IsBlank(kigBng)){
					errors.add("",new ActionError("errors.input.required","�ǉ��i��"));
					return errors;
				}else{
					//���p�p���̂�
					this.kigBng_IsAscii(kigBng,"�i��",errors);
				}
			}
		}

		if(command.equals(COMMAND_PRSHACHU)){	//2004.03.08 add
			if(this.getKbn().equals("5")){
				errors.add("",new ActionError("errors.input.prohibited","�v���X�����̏ꍇ�A�敪�̂��̑�"));
				return errors;
			}
		}

		return errors.empty() ? null : errors;		
	}
	
//--���̓`�F�b�N���\�b�h------------------------------------------------------------------------------
	
	/** �����������ׂău�����N���ǂ����`�F�b�N���郁�\�b�h */
	public boolean hbiDte_IsBlank(ArrayList hbiDte){
		for(int i = 0; i<hbiDte.size(); i++){
			FormDate _hbiDte = (FormDate)hbiDte.get(i);
			if(!_hbiDte.isBlank())
				return false;
		}
		return true;
	}
	/** ���������Z�b�g�œ��͂���Ă邩�ǂ����`�F�b�N���郁�\�b�h */
	public void hbiDte_IsSet(ArrayList hbiDte, String param, ActionErrors errors){
		for(int i = 0; i<hbiDte.size(); i++){
			FormDate _hbiDte = (FormDate)hbiDte.get(i);
			if(!(_hbiDte.isSet() || _hbiDte.isBlank())){
				String str = param + "(" + (i+1) + "�Ԗ�)";
				errors.add("",new ActionError("errors.input.1",str,"�Z�b�g"));
			}
		}
	}
	/** �����������t�œ��͂���Ă邩�ǂ����`�F�b�N���郁�\�b�h */
	public void hbiDte_IsDate(ArrayList hbiDte, String param, ActionErrors errors){
		for(int i = 0; i<hbiDte.size(); i++){
			FormDate _hbiDte = (FormDate)hbiDte.get(i);
			if(_hbiDte.isSet() && !_hbiDte.isDate()){
				String str = param + "(" + (i+1) + "�Ԗ�)";
				errors.add("",new ActionError("errors.input.1",str,"���t"));
			}
		}
	}
	
	
	/** �ǉ��i�ԁi�L���ԍ��j�����ׂău�����N���ǂ����`�F�b�N���郁�\�b�h */
	public boolean kigBng_IsBlank(ArrayList kigBng){		
		for(int i = 0; i<kigBng.size(); i++){
			KigBng _kigBng = (KigBng)kigBng.get(i);
			if(!_kigBng.isBlank()){
				return false;
			}
		}
		return true;
	}
	/** �ǉ��i�ԁi�L���ԍ��j���p�p�����ǂ����`�F�b�N���郁�\�b�h */
	public void kigBng_IsAscii(ArrayList kigBng, String param, ActionErrors errors){
		for(int i = 0; i<kigBng.size(); i++){
			KigBng _kigBng = (KigBng)kigBng.get(i);	
			if(!_kigBng.isBlank() && !_kigBng.isAscii()){
				String str = param + "(" + (i+1) + "�Ԗ�)";
				errors.add("",new ActionError("errors.input.1",str,"���p�p��"));
			}
		}
	}
	
	
	
	//��������ArrayList�ɃZ�b�g���ĕԂ�
	public ArrayList getHbiDte_List(){
		ArrayList hbiDte = new ArrayList();
		hbiDte.add(getHbiDte1());hbiDte.add(getHbiDte2());hbiDte.add(getHbiDte3());
		hbiDte.add(getHbiDte4());hbiDte.add(getHbiDte5());
		return hbiDte;
	}
	//�L���ԍ���ArrayList�ɃZ�b�g���ĕԂ�
	public ArrayList getKigBng_List(){
		ArrayList kigBng = new ArrayList();
		kigBng.add(getKigBng1());kigBng.add(getKigBng2());
		kigBng.add(getKigBng3());kigBng.add(getKigBng4());
		kigBng.add(getKigBng5());kigBng.add(getKigBng6());
		kigBng.add(getKigBng7());kigBng.add(getKigBng8());
		kigBng.add(getKigBng9());kigBng.add(getKigBng10());
		kigBng.add(getKigBng11());kigBng.add(getKigBng12());
		kigBng.add(getKigBng13());kigBng.add(getKigBng14());
		kigBng.add(getKigBng15());
		return kigBng;
	}

	//2004/07/02 add
	public void setKigBng_List(ArrayList list){

		for(int i=0;i<list.size();i++){

			KigBng kigbng = new KigBng((String)list.get(i));

			if(getKigBng1().isBlank()){
				setKigBng1(kigbng);
				continue;
			}
			if(getKigBng2().isBlank()){
				setKigBng2(kigbng);
				continue;
			}
			if(getKigBng3().isBlank()){
				setKigBng3(kigbng);
				continue;
			}
			if(getKigBng4().isBlank()){
				setKigBng4(kigbng);
				continue;
			}
			if(getKigBng5().isBlank()){
				setKigBng5(kigbng);
				continue;
			}
			if(getKigBng6().isBlank()){
				setKigBng6(kigbng);
				continue;
			}
			if(getKigBng7().isBlank()){
				setKigBng7(kigbng);
				continue;
			}
			if(getKigBng8().isBlank()){
				setKigBng8(kigbng);
				continue;
			}
			if(getKigBng9().isBlank()){
				setKigBng9(kigbng);
				continue;
			}
			if(getKigBng10().isBlank()){
				setKigBng10(kigbng);
				continue;
			}
			if(getKigBng11().isBlank()){
				setKigBng11(kigbng);
				continue;
			}
			if(getKigBng12().isBlank()){
				setKigBng12(kigbng);
				continue;
			}
			if(getKigBng13().isBlank()){
				setKigBng13(kigbng);
				continue;
			}
			if(getKigBng14().isBlank()){
				setKigBng14(kigbng);
				continue;
			}
			if(getKigBng15().isBlank()){
				setKigBng15(kigbng);
				continue;
			}

		}

	}

	//�L���ԍ���ArrayList�ɃZ�b�g���ĕԂ�
	public ArrayList getKigBngStr_List(){
		ArrayList kigBng = new ArrayList();
		kigBng.add(getKigBng1().getKigBng());kigBng.add(getKigBng2().getKigBng());
		kigBng.add(getKigBng3().getKigBng());kigBng.add(getKigBng4().getKigBng());
		kigBng.add(getKigBng5().getKigBng());kigBng.add(getKigBng6().getKigBng());
		kigBng.add(getKigBng7().getKigBng());kigBng.add(getKigBng8().getKigBng());
		kigBng.add(getKigBng9().getKigBng());kigBng.add(getKigBng10().getKigBng());
		kigBng.add(getKigBng11().getKigBng());kigBng.add(getKigBng12().getKigBng());
		kigBng.add(getKigBng13().getKigBng());kigBng.add(getKigBng14().getKigBng());
		kigBng.add(getKigBng15().getKigBng());
		return kigBng;
	}
	

//---------------------------------------------------------------------------------------------------
	
	public void reset(ActionMapping maping,HttpServletRequest req){
//System.out.println("reset : "+command);
		this.setCheck_false();//�`�F�b�N�{�b�N�X�����Z�b�g
		if(!command.equals(COMMAND_PRSHACHU) && !command.equals(COMMAND_FUKUHACHU))
			this.command = "INIT";
	}
	
//---------------------------------------------------------------------------------------------------

  //�����N���X�g�쐬
  static String[] makeRank(String daiKaiSkbCod){
	  if(daiKaiSkbCod.equals(com.jds.prodord.common.CommonConst.KAICOD_K)){
		  String[] RANK = {"N0","N1","N2","N3","S","A","B","C"};
		  return RANK;
	  }else{
		  String[] RANK = {"N0","N1","N2","N3","S","A","B","C","D"};
		  return RANK;
	  }
  }

	
	/**���t�N���X*/
	public class FormDate {
		private String year = "";	
		private String month = "";	
		private String day = "";	
		
		/**
	     * �N�̃Z�b�g
	     * 
	     * @param year �N
	     */
	    public void setYear(String year) {
	        this.year = year;
	    }
	    /**
	     * �N�̎擾
	     * 
	     * @return �N
	     */
	    public String getYear() {
	        return this.year;
	    }
	    /**
	     * ���̃Z�b�g
	     * 
	     * @param month ��
	     */
	    public void setMonth(String month) {
	        this.month = month;
	    }
	    /**
	     * ���̎擾
	     * 
	     * @return ��
	     */
	    public String getMonth() {
	        return this.month;
	    }
	    /**
	     * ���̃Z�b�g
	     * 
	     * @param day ��
	     */
	    public void setDay(String day) {
	        this.day = day;
	    }
	    /**
	     * ���̎擾
	     * 
	     * @return ��
	     */
	    public String getDay() {
	        return this.day;
	    }
	    
	    public boolean isBlank(){
	    	
			if(!this.year.trim().equals("")){
				return false;
			}
			if(!this.month.trim().equals("")){
				return false;
			}
			if(!this.day.trim().equals("")){
				return false;
			}
			return true;
	    }
		
		public boolean isSet(){
	    	return (!this.year.trim().equals("") && !this.month.trim().equals("") && !this.day.trim().equals(""));
	    }
	    
	    public boolean isDate(){
	    	return CheckCommon.validateAsDate(this.year.trim(),this.month.trim(),this.day.trim());
	    }
	    
	    public void clear(){
	    	year = "";	
			month = "";	
			day = "";	    	
	    }
	    
	}
	/**�L���ԍ��N���X*/
	public class KigBng {
		private String kigBng = "";
		
		public KigBng(){
		}
		
		public KigBng(String kigBng){
			this.kigBng = kigBng;
		}
		
		/**
	     * �L���ԍ��̃Z�b�g
	     * 
	     * @param kigBng �L���ԍ�
	     */
	    public void setKigBng(String kigBng) {
	
	        this.kigBng = kigBng;
	
	    }
	    /**
	     * �L���ԍ��̎擾
	     * 
	     * @return �L���ԍ�
	     */
	    public String getKigBng() {
	
	        return this.kigBng.trim();
	
	    }
	    public boolean isBlank(){
	    	return this.kigBng.trim().equals("");
	    }
	    public boolean isAscii(){
	    	return StringUtils.isAscii(this.kigBng.trim());
	    }
	    public void clear(){
	    	kigBng = "";   	
	    }
	}

	//2005/02/03
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



