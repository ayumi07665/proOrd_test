/**
* TeiansuuReferForm  ��Đ��Ɖ�w���t�H�[���N���X
*
*	�쐬��    2003/02/20
*	�쐬��    �i�m�h�h�j���c �Ĕ�
* �����T�v    Http���N�G�X�g�ƃ��N�G�X�g�̏������ʂ��i�[����N���X�B
*
*	[�ύX����]
*         2003/07/16 �i�m�h�h�j���c �Ĕ�
* 			�E�\�[�g�����Ƀ\�[�g�L�[�ǉ��B
* 		  2003/08/21 �i�m�h�h�j���c �Ĕ�
* 			�E�L���ԍ����͗����T���P�O��
* 		  2003/09/11 �i�m�h�h�j���c �Ĕ�
* 			�E��Đ��Ɖ�ʉ�ʂ���߂��Ă������A���������̓��͓��e��ێ����Ă����悤�ɏC��
* 		  2003/09/19�i�m�h�h�j���c �Ĕ�
* 			�E�Ώۃf�[�^�̂ݕ\���E��Ώۃf�[�^�\�� �I���`�F�b�N�{�b�N�X
* 		  2003/10/09�i�m�h�h�j���c �Ĕ�
* 			�E�Ώۃf�[�^�̂ݕ\���`�F�b�N�{�b�N�X���폜
* 			  ��Ώۃf�[�^�\���I�����ɂ́A�h���b�v�_�E���Ŕ͈͂�I������
* 		  2003/12/12  (NII)  �X
* 			�E�i�Ԏw�萔��10��15��
*		  2004/06/28�i�m�h�h�j�g�c
*			�E�R�s�[���y�[�X�g�@�\�ǉ�
*		  2006/05/10�i�m�h�h�j�c�[ �N��
*			�E�j�Ђ̃����N�����ύX�Ή�
*/

package com.jds.prodord.indicate.teiansuurefer;

import org.apache.struts.action.*;
import javax.servlet.http.*;
import java.util.*;
import com.jds.prodord.common.*;

public class TeiansuuReferForm extends ActionForm {

//	public TeiansuuReferForm() {
//		
//	}
	public static final String COMMAND_TEIANSYOKAI = "COMMAND_TEIANSYOKAI";
	public static final String COMMAND_JIDOUHACHU = "COMMAND_JIDOUHACHU";
	public static final String COMMAND_CLEAR = "COMMAND_CLEAR";
//	public static final String COMMAND_PASTE = "�\��t��";//2004/06/29 add
		
	public static final boolean RB_ALL = true;
	public static final boolean RB_SELECT = false;
	
	private ArrayList vl_kaiSkbCod = new ArrayList();
	private ArrayList vl_ketCod = new ArrayList();
	private ArrayList vl_rank = new ArrayList();
	private ArrayList vl_mkrBun = new ArrayList();
	private ArrayList vl_hyoKbn = new ArrayList();
	private ArrayList lb_hyoKbn = new ArrayList();
	private ArrayList vl_taisyoRange = new ArrayList();
	private ArrayList lb_taisyoRange = new ArrayList();
	
//	public static final String[] RANK = {"N1","N2","N3","S","A","B","C","D"};//"D"���ǉ� 2003/06/20
	public static final String[] VL_HYOKBN = {"H","Y"};
	public static final String[] LB_HYOKBN = {"�M�y","�m�y"};
	public static final String[] VL_TAISYORANGE = {"1","2","3","4","5"};
	public static final String[] LB_TAISYORANGE = {"0","1�@�`�@10","11�@�`�@30","31�@�`�@50","51�@�`�@100"};
	
	
	private String command = "";
	
	private boolean rb_kaiSkbCod = true;
	private boolean rb_rank = true;
	private boolean rb_ketCod = true;
	private boolean rb_mkrBun = true;
	private boolean rb_hyoKbn = true;
	private boolean rb_kigBng = true;
	
	private String queryDaiKaiSkbCod = "";
	private String queryKaiSkbCod = "";
	private ArrayList queryKaiSkbCodList = null;
	private String[] kaiSkbCod = null;
	private String[] rank = null;
	private String[] ketCod = null;
	private String[] mkrBun = null;
	private String[] hyoKbn = null;
	private String kigBng1 = "";
	private String kigBng2 = "";
	private String kigBng3 = "";
	private String kigBng4 = "";
	private String kigBng5 = "";
	private String kigBng6 = "";
	private String kigBng7 = "";
	private String kigBng8 = "";
	private String kigBng9 = "";
	private String kigBng10 = "";
	// �i�Ԏw�萔��10��15�� 2003/12/12 add�@st****
	private String kigBng11 = "";
	private String kigBng12 = "";
	private String kigBng13 = "";
	private String kigBng14 = "";
	private String kigBng15 = "";
	// �i�Ԏw�萔��10��15�� 2003/12/12 add�@****

	private boolean sort_rank = false;
	private boolean sort_ketCod = false;
	private boolean sort_sortKey = false;
	
	private boolean hitaisyo = false;
	private String taisyoRange = "";
	
	private String cpPaste = ""; //2004/06/28 add
	private String paste   = "";
	
	// ��Đ��Ɖ�ʉ�ʂ���߂��Ă������̂��߁A ************
	// �`�F�b�N�{�b�N�X�̒l��ێ����� 2003/09/11 add
	private boolean[] checkBoxValue = new boolean[4];
	
	public void setCheckBoxValueToArray(){
		checkBoxValue[0] = sort_rank;//�����N
		checkBoxValue[1] = sort_ketCod;//�`�ԃR�[�h
		checkBoxValue[2] = sort_sortKey;//�\�[�g�L�[
		checkBoxValue[3] = hitaisyo;//��Ώۃf�[�^���\��
	}
	public void setCheckBoxValue(){
		sort_rank = checkBoxValue[0];
		sort_ketCod = checkBoxValue[1];
		sort_sortKey = checkBoxValue[2];
		hitaisyo = checkBoxValue[3];
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
	
	public TeiansuuReferForm(){
		super();
		this.clearAll();
		this.setCommand("INIT");
		this.setOptions();//�I�����ڂ̃I�v�V�������Z�b�g
		
	}
	
	//-----------------------------------------------------------
	
	public Collection getVl_ketCod(){
		return (Collection)vl_ketCod;
	}
	public Collection getVl_kaiSkbCod(){
		return (Collection)vl_kaiSkbCod;
	}
	public Collection getVl_rank(){
		return (Collection)vl_rank;
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
	public Collection getVl_taisyoRange(){
		return (Collection)vl_taisyoRange;
	}
	public Collection getLb_taisyoRange(){
		return (Collection)lb_taisyoRange;
	}
	
	public void setRankOptions(String daiKaiSkbCod){
		vl_rank.clear();
	
		String[] RANK = makeRank(daiKaiSkbCod);
		for(int i = 0; i<RANK.length; i++){
			vl_rank.add(RANK[i]);
		}
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
		if(vl_taisyoRange.size() == 0){
			for(int i = 0; i<VL_TAISYORANGE.length; i++){
				vl_taisyoRange.add(VL_TAISYORANGE[i]);
			}
		}
		if(lb_taisyoRange.size() == 0){
			for(int i = 0; i<LB_TAISYORANGE.length; i++){
				lb_taisyoRange.add(LB_TAISYORANGE[i]);
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
     * �����N�̃Z�b�g
     * 
     * @param rank �����N
     */
    public void setRank(String[] s) {

        this.rank = s;

    }
    /**
     * �����N�̎擾
     * 
     * @return �����N
     */
    public String[] getRank() {

        return this.rank;

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
     * �L���ԍ��P�̃Z�b�g
     * 
     * @param kigBng1 �L���ԍ��P
     */
    public void setKigBng1(String s) {

        this.kigBng1 = s;

    }
    /**
     * �L���ԍ��P�̎擾
     * 
     * @return �L���ԍ��P
     */
    public String getKigBng1() {

        return this.kigBng1;

    }
    /**
     * �L���ԍ��Q�̃Z�b�g
     * 
     * @param kigBng2 �L���ԍ��Q
     */
    public void setKigBng2(String s) {

        this.kigBng2 = s;

    }
    /**
     * �L���ԍ��Q�̎擾
     * 
     * @return �L���ԍ��Q
     */
    public String getKigBng2() {

        return this.kigBng2;

    }
    /**
     * �L���ԍ��R�̃Z�b�g
     * 
     * @param kigBng3 �L���ԍ��R
     */
    public void setKigBng3(String s) {

        this.kigBng3 = s;

    }
    /**
     * �L���ԍ��R�̎擾
     * 
     * @return �L���ԍ��R
     */
    public String getKigBng3() {

        return this.kigBng3;

    }
    /**
     * �L���ԍ��S�̃Z�b�g
     * 
     * @param kigBng4 �L���ԍ��S
     */
    public void setKigBng4(String s) {

        this.kigBng4 = s;

    }
    /**
     * �L���ԍ��S�̎擾
     * 
     * @return �L���ԍ��S
     */
    public String getKigBng4() {

        return this.kigBng4;

    }
    /**
     * �L���ԍ��T�̃Z�b�g
     * 
     * @param kigBng5 �L���ԍ��T
     */
    public void setKigBng5(String s) {

        this.kigBng5 = s;

    }
    /**
     * �L���ԍ��T�̎擾
     * 
     * @return �L���ԍ��T
     */
    public String getKigBng5() {

        return this.kigBng5;

    }
    /**
     * �L���ԍ��̃Z�b�g�U
     * 
     * @param kigBng �L���ԍ��U
     */
    public void setKigBng6(String s) {

        this.kigBng6 = s;

    }
    /**
     * �L���ԍ��̎擾�U
     * 
     * @return �L���ԍ��U
     */
    public String getKigBng6() {

        return this.kigBng6;

    }
    /**
     * �L���ԍ��̃Z�b�g�V
     * 
     * @param kigBng �L���ԍ��V
     */
    public void setKigBng7(String s) {

        this.kigBng7 = s;

    }
    /**
     * �L���ԍ��̎擾�V
     * 
     * @return �L���ԍ��V
     */
    public String getKigBng7() {

        return this.kigBng7;

    }
    /**
     * �L���ԍ��̃Z�b�g�W
     * 
     * @param kigBng �L���ԍ��W
     */
    public void setKigBng8(String s) {

        this.kigBng8 = s;

    }
    /**
     * �L���ԍ��̎擾�W
     * 
     * @return �L���ԍ��W
     */
    public String getKigBng8() {

        return this.kigBng8;

    }
    /**
     * �L���ԍ��̃Z�b�g�X
     * 
     * @param kigBng �L���ԍ��X
     */
    public void setKigBng9(String s) {

        this.kigBng9 = s;

    }
    /**
     * �L���ԍ��̎擾�X
     * 
     * @return �L���ԍ��X
     */
    public String getKigBng9() {

        return this.kigBng9;

    }
    /**
     * �L���ԍ��̃Z�b�g�P�O
     * 
     * @param kigBng �L���ԍ��P�O
     */
    public void setKigBng10(String s) {

        this.kigBng10 = s;

    }
    /**
     * �L���ԍ��̎擾�P�O
     * 
     * @return �L���ԍ��P�O
     */
    public String getKigBng10() {

        return this.kigBng10;

    }

	// �i�Ԏw�萔��10��15�� 2003/12/12 add�@st****

	/**
     * �L���ԍ��̃Z�b�g�P�P
     * 
	 * @param kigBng �L���ԍ��P�P
	 */
	public void setKigBng11(String string) {

		this.kigBng11 = string;

	}

	/**
     * �L���ԍ��̎擾�P�P
     * 
	 * @return�@�L���ԍ��P�P
	 */
	public String getKigBng11() {

		return this.kigBng11;

	}

	/**
     * �L���ԍ��̃Z�b�g�P�Q
     * 
	 * @param kigBng �L���ԍ��P�Q
	 */
	public void setKigBng12(String string) {

		this.kigBng12 = string;

	}

	/**
     * �L���ԍ��̎擾�P�Q
     * 
	 * @return�@�L���ԍ��P�Q
	 */
	public String getKigBng12() {

		return this.kigBng12;

	}

	/**
     * �L���ԍ��̃Z�b�g�P�R
     * 
	 * @param kigBng �L���ԍ��P�R
	 */
	public void setKigBng13(String string) {

		this.kigBng13 = string;

	}

	/**
     * �L���ԍ��̎擾�P�R
     * 
	 * @return�@�L���ԍ��P�R
	 */
	public String getKigBng13() {

		return this.kigBng13;

	}

	/**
     * �L���ԍ��̃Z�b�g�P�S
     * 
	 * @param kigBng �L���ԍ��P�S
	 */
	public void setKigBng14(String string) {

		this.kigBng14 = string;

	}

	/**
     * �L���ԍ��̎擾�P�S
     * 
	 * @return�@�L���ԍ��P�S
	 */
	public String getKigBng14() {

		return this.kigBng14;

	}

	/**
     * �L���ԍ��̃Z�b�g�P�T
     * 
	 * @param kigBng �L���ԍ��P�T
	 */
	public void setKigBng15(String string) {

		this.kigBng15 = string;

	}

	/**
     * �L���ԍ��̎擾�P�T
     * 
	 * @return�@�L���ԍ��P�T
	 */
	public String getKigBng15() {
		
		return this.kigBng15;

	}

	// �i�Ԏw�萔��10��15�� 2003/12/12 add�@ed ****

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
     * ���W�I�{�^��-�����N�̃Z�b�g
     * 
     * @param rbrank ���W�I�{�^��-�����N
     */
    public void setRb_rank(boolean b) {

        this.rb_rank = b;

    }
    /**
     * ���W�I�{�^��-�����N�̎擾
     * 
     * @return ���W�I�{�^��-�����N
     */
    public boolean getRb_rank() {

        return this.rb_rank;

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
     * ���W�I�{�^��-�L���ԍ��̃Z�b�g
     * 
     * @param rb_kigBng ���W�I�{�^��-�L���ԍ�
     */
    public void setRb_kigBng(boolean b) {

        this.rb_kigBng = b;

    }
    /**
     * ���W�I�{�^��-�L���ԍ��̎擾
     * 
     * @return ���W�I�{�^��-�L���ԍ�
     */
    public boolean getRb_kigBng() {

        return this.rb_kigBng;

    }
    /**
     * �\�[�g����-�����N�̃Z�b�g
     * 
     * @param sort_rank �\�[�g����-�����N
     */
    public void setSort_rank(boolean b) {

        this.sort_rank = b;

    }
    /**
     * �\�[�g����-�����N�̎擾
     * 
     * @return �\�[�g����-�����N
     */
    public boolean getSort_rank() {

        return this.sort_rank;

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
	 * Gets the hitaisyo
	 * @return Returns a boolean
	 */
	public boolean getHitaisyo() {
		return hitaisyo;
	}
	/**
	 * Sets the hitaisyo
	 * @param hitaisyo The hitaisyo to set
	 */
	public void setHitaisyo(boolean hitaisyo) {
		this.hitaisyo = hitaisyo;
	}
	
	
	/**
	 * Gets the taisyoRange
	 * @return Returns a String
	 */
	public String getTaisyoRange() {
		return taisyoRange;
	}
	/**
	 * Sets the taisyoRange
	 * @param taisyoRange The taisyoRange to set
	 */
	public void setTaisyoRange(String taisyoRange) {
		this.taisyoRange = taisyoRange;
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
		
		this.kaiSkbCod = null;
        this.rank = null;
        this.ketCod = null;
        this.mkrBun = null;
        this.hyoKbn = null;
        this.kigBng1 = "";
	    this.kigBng2 = "";
	    this.kigBng3 = "";
	    this.kigBng4 = "";
	    this.kigBng5 = "";
	    this.kigBng6 = "";
	    this.kigBng7 = "";
	    this.kigBng8 = "";
	    this.kigBng9 = "";
	    this.kigBng10 = "";
		// �i�Ԏw�萔��10��15�� 2003/12/16 add�@st****
		this.kigBng11 = "";
		this.kigBng12 = "";
		this.kigBng13 = "";
		this.kigBng14 = "";
		this.kigBng15 = "";		
		// �i�Ԏw�萔��10��15�� 2003/12/16 add�@ed****	    
	    
	    this.taisyoRange = null;
        
        this.setRb_default();
        this.setCheckBoxValueToArray();
        
        this.cpPaste=""; //2004/06/29 add
        this.paste="";
       
  	}
	
	//���W�I�{�^���E�`�F�b�N�{�b�N�X���f�t�H���g�ɃZ�b�g���郁�\�b�h
	public void setRb_default(){
		
		setRb_kaiSkbCod(RB_SELECT);
		setRb_rank(RB_SELECT);
		setRb_ketCod(RB_SELECT);//��ЁE�����N�E�`�Ԃ̓f�t�H���g��"�I��"
		setRb_mkrBun(RB_ALL);
		setRb_hyoKbn(RB_ALL);
		setRb_kigBng(RB_ALL);
		setSort_rank(false);
        setSort_ketCod(false);
		setSort_sortKey(true);
		setHitaisyo(false);
	}
	
	//�`�F�b�N�{�b�N�X�����Z�b�g���郁�\�b�h
	public void setCheck_false(){
		
		setSort_rank(false);
		setSort_ketCod(false);
		setSort_sortKey(false);
		setHitaisyo(false);
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
		//������ďƉ� �܂��� ���������������̂Ƃ�
		if(command.equals(COMMAND_TEIANSYOKAI) || command.equals(COMMAND_JIDOUHACHU)){	

			//�i�Ԃ��`�k�k�w��̂Ƃ�
			if(rb_kigBng){ //true�Ȃ��ALL
					
				//��Ў��ʃR�[�h
				if(!rb_kaiSkbCod){ //false�Ȃ��SELECT
					if(kaiSkbCod == null){
						errors.add("",new ActionError("errors.input.required","��Ў��ʃR�[�h"));		
					}
				}
				//�����N
				if(!rb_rank){ //false�Ȃ��SELECT
					if(rank == null){
						errors.add("",new ActionError("errors.input.required","�����N"));		
					}else{
						if(rank.length == 1 && rank[0].equals("N1") && command.equals(COMMAND_JIDOUHACHU))
							errors.add("",new ActionError("errors.input.prohibited","�����������A�����NN1�̂�"));
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
			}
			//�i��
			if(!rb_kigBng){ //false�Ȃ��SELECT
				ArrayList kigBng = this.getKigBng_List();
				if(this.isBlank(kigBng)){
					errors.add("",new ActionError("errors.input.required","�i��"));		
				}else{
					//���p�p���̂�
					this.isAscii(kigBng,"�i��",errors);
				}	
			}
		
		}
		return errors.empty() ? null : errors;		
	}
	/** ���ׂău�����N���ǂ����`�F�b�N���郁�\�b�h */
	public boolean isBlank(ArrayList arr){		
		for(int i = 0; i<arr.size(); i++){
			String _kigBng = arr.get(i)+"";
			if(!_kigBng.trim().equals("")){
				return false;
			}
		}
		return true;
	}
	/** ���p�p�����ǂ����`�F�b�N���郁�\�b�h */
	public void isAscii(ArrayList arr, String param, ActionErrors errors){
		for(int i = 0; i<arr.size(); i++){
			String _kigBng = arr.get(i)+"";
			if(!_kigBng.trim().equals("") && !StringUtils.isAscii(_kigBng.trim())){
				String str = param + "(" + (i+1) + "�Ԗ�)";
				errors.add("",new ActionError("errors.input.1",str,"���p�p��"));
			}
		}
	}
	//�L���ԍ���ArrayList�ɃZ�b�g���ĕԂ�
	public ArrayList getKigBng_List(){
		ArrayList kigBng = new ArrayList();
		kigBng.add(kigBng1);kigBng.add(kigBng2);kigBng.add(kigBng3);
		kigBng.add(kigBng4);kigBng.add(kigBng5);
		kigBng.add(kigBng6);kigBng.add(kigBng7);kigBng.add(kigBng8);
		kigBng.add(kigBng9);kigBng.add(kigBng10);
	//�i�Ԏw�萔�@10��15�@add�@2003/12/12�@
		kigBng.add(kigBng11);kigBng.add(kigBng12);kigBng.add(kigBng13);
		kigBng.add(kigBng14);kigBng.add(kigBng15);
		return kigBng;
	}
	
	//--------------------------------------------------------------------------------------------
	
	public void reset(ActionMapping maping,HttpServletRequest req){
		this.command = "INIT";
		this.setCheck_false();//�`�F�b�N�{�b�N�X�����Z�b�g
	}
	
	static String[] makeRank(String daiKaiSkbCod){
		if(daiKaiSkbCod.equals(com.jds.prodord.common.CommonConst.KAICOD_K)){
			String[] RANK = {"N1","N2","N3","S","A","B","C"};
			return RANK;
		}else{
			String[] RANK = {"N1","N2","N3","S","A","B","C","D"};
			return RANK;
		}
	}


}

