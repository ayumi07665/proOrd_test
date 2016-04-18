/**
* OrderHistoryTopForm  ���������Ɖ��ʌ��������t�H�[���N���X
*
*	�쐬��    2003/04/18
*	�쐬��    �i�m�h�h�j�g�c �h�q
* �����T�v    Http���N�G�X�g�ƃ��N�G�X�g�̏������ʂ��i�[����N���X�B
*
*	[�ύX����]
* 		2004/03/31	(NII)�X
* 			�E�w������ɓ��ɓ���ǉ�
* 		2005/05/25�i�m�h�h�j�g�c
* 			�E�ꏊ�R�[�h�̒ǉ�
* 		2005/09/16�i�m�h�h�j�g�c
* 			�E�������ڂɔ���������ǉ�
*/

package com.jds.prodord.reference.orderhistory;

import org.apache.struts.action.*;
import javax.servlet.http.*;
import java.util.*;
import com.jds.prodord.common.*;

public class OrderHistoryTopForm extends ActionForm {

	public static final String COMMAND_JIKKOU = "���s";
	public static final String COMMAND_CLEAR = "�N���A";
	
	public static final String PRCKBN_SEARCH = "1";
	public static final String PRCKBN_DOWNLOAD = "2";

    private ArrayList details = new ArrayList();

	private String command = "";
    
	private String queryDaiKaiSkbCod = "";
	private String queryKaiSkbCod  = "";
	private ArrayList queryKaiSkbCodList = null;
	private String hyoKbn = "";
	private String bshCod = "";
	private String prcKbn = "";
	
	/**
     * �������I�����W�I�{�^��
     *   �����o�́E�o�͍ρE�`�k�k��
     */
    private String rb_HacSyo = "";
    

    /**
     * ���ރR�[�h�I�����W�I�{�^��
     *   ���v���X�E���ށE�`�k�k��
     */
    private String rb_BunCod = "";

	/**
     * ���ɏ󋵑I�����W�I�{�^��
     *   �������ɁE�`�k�k��
     */
    private String rb_Nyuko = "";
	

    /**
     * ������R�[�h
     */
    private String queryHacCod = "";
    
    /**
     * �L���ԍ�
     */
    private String queryKigBng = "";
    
    /**
     * ������
     */
    private String queryHbiDte_Y = "";
    private String queryHbiDte_M = "";
    private String queryHbiDte_D = "";
    
    /**
     * �������ԍ�From&To
     */
    private boolean checkHacBng = true;
    private String queryHacBngFrm = "";
    private String queryHacBngTo = "";
    
    /**
     * ������From&To
     */
    private boolean checkHacDte = true;
    private String queryHacDteFrm_Y = "";
    private String queryHacDteFrm_M = "";
    private String queryHacDteFrm_D = "";
    private String queryHacDteTo_Y = "";
    private String queryHacDteTo_M = "";
    private String queryHacDteTo_D = "";
    
    /**
     * �[��From&To
     */
    private boolean checkNki = true;
    private String queryNkiFrm_Y = "";
    private String queryNkiFrm_M = "";
    private String queryNkiFrm_D = "";
    private String queryNkiTo_Y = "";
    private String queryNkiTo_M = "";
    private String queryNkiTo_D = "";
    
    /**
     * ���[�J�[����
     */
    private boolean checkMkrBun = true;
    private String[] mkrBun = null;
	private ArrayList vl_mkrBun = new ArrayList();
	
	public Collection getVl_mkrBun(){
		return (Collection)vl_mkrBun;
	}
	public void setMkrBunOptions(ArrayList mkrBunList){
		vl_mkrBun.clear();
		for(int i = 0; i<mkrBunList.size(); i++){
			vl_mkrBun.add(mkrBunList.get(i)+"");
		}
	}
	
	/**
     * �敪
     */
    private boolean checkKbn = true;
	private String kbn = "";
	
 	public OrderHistoryTopForm(){
		super();
//System.out.println("Top�R���X�g���N�^ : "+command);
		this.removeOrderHistoryVO();
		this.setCommand("INIT");
		clear_text();
 	}	

	/**
	 * ���ɓ�From&To
	 */
	private boolean checkNyoDte = true;
	private String queryNyoDteFrm_Y = "";
	private String queryNyoDteFrm_M = "";
	private String queryNyoDteFrm_D = "";
	private String queryNyoDteTo_Y = "";
	private String queryNyoDteTo_M = "";
	private String queryNyoDteTo_D = "";
 	
	/**
	 * ��������
	 */
	private boolean chkHacJun = true;

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

//--------------------------------------------------------------------
    /**
     * �������I�����W�I�{�^���̃Z�b�g
     * 
     * @param choiceHacSyo �������I�����W�I�{�^��
     */
    public void setRb_HacSyo(String rb_HacSyo) {

        this.rb_HacSyo = rb_HacSyo;
    }


    /**
     * �������I�����W�I�{�^���̎擾
     * 
     * @return �������I�����W�I�{�^��
     */
    public String getRb_HacSyo() {

        return this.rb_HacSyo;

    }


    /**
     * ���ރR�[�h�I�����W�I�{�^���̃Z�b�g
     * 
     * @param choiceBunCod ���ރR�[�h�I�����W�I�{�^��
     */
    public void setRb_BunCod(String rb_BunCod) {

        this.rb_BunCod = rb_BunCod;

    }


    /**
     * ���ރR�[�h�I�����W�I�{�^���̎擾
     * 
     * @return ���ރR�[�h�I�����W�I�{�^��
     */
    public String getRb_BunCod() {

        return this.rb_BunCod;

    }

	/**
     * ���ɏ󋵑I�����W�I�{�^���̃Z�b�g
     * 
     * @param rb_Nyuko ���ɏ󋵑I�����W�I�{�^��
     */
    public void setRb_Nyuko(String rb_Nyuko) {

        this.rb_Nyuko = rb_Nyuko;

    }


    /**
     * ���ɏ󋵑I�����W�I�{�^���̎擾
     * 
     * @return ���ɏ󋵑I�����W�I�{�^��
     */
    public String getRb_Nyuko() {

        return this.rb_Nyuko;

    }


    /**
     * ������R�[�h�̃Z�b�g
     * 
     * @param queryHacCod ������R�[�h
     */
    public void setQueryHacCod(String queryHacCod) {

        this.queryHacCod = queryHacCod;

    }


    /**
     * ������R�[�h�̎擾
     * 
     * @return ������R�[�h
     */
    public String getQueryHacCod() {

        return this.queryHacCod;

    }


    /**
     * �L���ԍ��̃Z�b�g
     * 
     * @param queryKigBng �L���ԍ�
     */
    public void setQueryKigBng(String queryKigBng) {

        this.queryKigBng = queryKigBng;

    }


    /**
     * �L���ԍ��̎擾
     * 
     * @return �L���ԍ�
     */
    public String getQueryKigBng() {

        return this.queryKigBng;

    }

//** ������ **//
    /**
     * ������(�N)�̃Z�b�g
     * 
     * @param queryHbiDte_Y ������(�N)
     */
    public void setQueryHbiDte_Y(String queryHbiDte_Y) {
        this.queryHbiDte_Y = queryHbiDte_Y;
    }
    /**
     * ������(�N)�̎擾
     * 
     * @return ������(�N)
     */
    public String getQueryHbiDte_Y() {
        return this.queryHbiDte_Y;
    }
    /**
     * ������(��)�̃Z�b�g
     * 
     * @param queryHbiDte_M ������(��)
     */
    public void setQueryHbiDte_M(String queryHbiDte_M) {
        this.queryHbiDte_M = queryHbiDte_M;
    }
    /**
     * ������(��)�̎擾
     * 
     * @return ������(��)
     */
    public String getQueryHbiDte_M() {
        return this.queryHbiDte_M;
    }
    /**
     * ������(��)�̃Z�b�g
     * 
     * @param queryHbiDte_D ������(��)
     */
    public void setQueryHbiDte_D(String queryHbiDte_D) {
        this.queryHbiDte_D = queryHbiDte_D;
    }
    /**
     * ������(��)�̎擾
     * 
     * @return ������(��)
     */
    public String getQueryHbiDte_D() {
        return this.queryHbiDte_D;
    }


//** �������ԍ� **//
    /**
     * �������ԍ��`�F�b�N�̃Z�b�g
     * 
     * @param checkHacBng �������ԍ��`�F�b�N
     */
    public void setCheckHacBng(boolean checkHacBng) {
        this.checkHacBng = checkHacBng;
    }
    /**
     * �������ԍ��`�F�b�N�̎擾
     * 
     * @return �������ԍ��`�F�b�N
     */
    public boolean getCheckHacBng() {
        return this.checkHacBng;
    }
    /**
     * �������ԍ�From�̃Z�b�g
     * 
     * @param queryHacBngFrm �������ԍ�From
     */
    public void setQueryHacBngFrm(String queryHacBngFrm) {
        this.queryHacBngFrm = queryHacBngFrm;
    }
    /**
     * �������ԍ�From�̎擾
     * 
     * @return �������ԍ�From
     */
    public String getQueryHacBngFrm() {
        return this.queryHacBngFrm;
    }
    /**
     * �������ԍ�To�̃Z�b�g
     * 
     * @param queryHacBngTo �������ԍ�To
     */
    public void setQueryHacBngTo(String queryHacBngTo) {
        this.queryHacBngTo = queryHacBngTo;
    }
    /**
     * �������ԍ�To�̎擾
     * 
     * @return �������ԍ�To
     */
    public String getQueryHacBngTo() {
        return this.queryHacBngTo;
    }

//** ������ **//
    /**
     * �������`�F�b�N�̃Z�b�g
     * 
     * @param checkHacDte �������`�F�b�N
     */
    public void setCheckHacDte(boolean checkHacDte) {
        this.checkHacDte = checkHacDte;
    }
    /**
     * �������`�F�b�N�̎擾
     * 
     * @return �������`�F�b�N
     */
    public boolean getCheckHacDte() {
        return this.checkHacDte;
    }
    /**
     * ������(�N)From�̃Z�b�g
     * 
     * @param queryHacDteFrm_Y ������(�N)From
     */
    public void setQueryHacDteFrm_Y(String queryHacDteFrm_Y) {
        this.queryHacDteFrm_Y = queryHacDteFrm_Y;
    }
    /**
     * ������(�N)From�̎擾
     * 
     * @return ������(�N)From
     */
    public String getQueryHacDteFrm_Y() {
        return this.queryHacDteFrm_Y;
    }
    /**
     * ������(��)From�̃Z�b�g
     * 
     * @param queryHacDteFrm_M ������(��)From
     */
    public void setQueryHacDteFrm_M(String queryHacDteFrm_M) {
        this.queryHacDteFrm_M = queryHacDteFrm_M;
    }
    /**
     * ������(��)From�̎擾
     * 
     * @return ������(��)From
     */
    public String getQueryHacDteFrm_M() {
        return this.queryHacDteFrm_M;
    }
    /**
     * ������(��)From�̃Z�b�g
     * 
     * @param queryHacDteFrm_D ������(��)From
     */
    public void setQueryHacDteFrm_D(String queryHacDteFrm_D) {
        this.queryHacDteFrm_D = queryHacDteFrm_D;
    }
    /**
     * ������(��)From�̎擾
     * 
     * @return ������(��)From
     */
    public String getQueryHacDteFrm_D() {
        return this.queryHacDteFrm_D;
    }
    /**
     * ������(�N)To�̃Z�b�g
     * 
     * @param queryHacDteTo_Y ������(�N)To
     */
    public void setQueryHacDteTo_Y(String queryHacDteTo_Y) {
        this.queryHacDteTo_Y = queryHacDteTo_Y;
    }
    /**
     * ������(�N)To�̎擾
     * 
     * @return ������(�N)To
     */
    public String getQueryHacDteTo_Y() {
        return this.queryHacDteTo_Y;
    }
    /**
     * ������(��)To�̃Z�b�g
     * 
     * @param queryHacDteTo_M ������(��)To
     */
    public void setQueryHacDteTo_M(String queryHacDteTo_M) {
        this.queryHacDteTo_M = queryHacDteTo_M;
    }
    /**
     * ������(��)To�̎擾
     * 
     * @return ������(��)To
     */
    public String getQueryHacDteTo_M() {
        return this.queryHacDteTo_M;
    }
    /**
     * ������(��)To�̃Z�b�g
     * 
     * @param queryHacDteTo_D ������(��)To
     */
    public void setQueryHacDteTo_D(String queryHacDteTo_D) {
        this.queryHacDteTo_D = queryHacDteTo_D;
    }
    /**
     * ������(��)To�̎擾
     * 
     * @return ������(��)To
     */
    public String getQueryHacDteTo_D() {
        return this.queryHacDteTo_D;
    }


//** �[�� **//
    /**
     * �[���`�F�b�N�̃Z�b�g
     * 
     * @param checkNki �[���`�F�b�N
     */
    public void setCheckNki(boolean checkNki) {
        this.checkNki = checkNki;
    }
    /**
     * �[���`�F�b�N�̎擾
     * 
     * @return �[���`�F�b�N
     */
    public boolean getCheckNki() {
        return this.checkNki;
    }    
    /**
     * �[��(�N)From�̃Z�b�g
     * 
     * @param queryNkiFrm_Y �[��(�N)From
     */
    public void setQueryNkiFrm_Y(String queryNkiFrm_Y) {
        this.queryNkiFrm_Y = queryNkiFrm_Y;
    }
    /**
     * �[��(�N)From�̎擾
     * 
     * @return �[��(�N)From
     */
    public String getQueryNkiFrm_Y() {
        return this.queryNkiFrm_Y;
    }
    /**
     * �[��(��)From�̃Z�b�g
     * 
     * @param queryNkiFrm_M �[��(��)From
     */
    public void setQueryNkiFrm_M(String queryNkiFrm_M) {
        this.queryNkiFrm_M = queryNkiFrm_M;
    }
    /**
     * �[��(��)From�̎擾
     * 
     * @return �[��(��)From
     */
    public String getQueryNkiFrm_M() {
        return this.queryNkiFrm_M;        
    }
    /**
     * �[��(��)From�̃Z�b�g
     * 
     * @param queryNkiFrm_D �[��(��)From
     */
    public void setQueryNkiFrm_D(String queryNkiFrm_D) {
        this.queryNkiFrm_D = queryNkiFrm_D;
    }
    /**
     * �[��(��)From�̎擾
     * 
     * @return �[��(��)From
     */
    public String getQueryNkiFrm_D() {
        return this.queryNkiFrm_D;
    }
    /**
     * �[��(�N)To�̃Z�b�g
     * 
     * @param queryNkiTo_Y �[��(�N)To
     */
    public void setQueryNkiTo_Y(String queryNkiTo_Y) {
        this.queryNkiTo_Y = queryNkiTo_Y;
    }
    /**
     * �[��(�N)To�̎擾
     * 
     * @return �[��(�N)To
     */
    public String getQueryNkiTo_Y() {
        return this.queryNkiTo_Y;
    }
    /**
     * �[��(��)To�̃Z�b�g
     * 
     * @param queryNkiTo_M �[��(��)To
     */
    public void setQueryNkiTo_M(String queryNkiTo_M) {
        this.queryNkiTo_M = queryNkiTo_M;
    }
    /**
     * �[��(��)To�̎擾
     * 
     * @return �[��(��)To
     */
    public String getQueryNkiTo_M() {
        return this.queryNkiTo_M;
    }
    /**
     * �[��(��)To�̃Z�b�g
     * 
     * @param queryNkiTo_D �[��(��)To
     */
    public void setQueryNkiTo_D(String queryNkiTo_D) {
        this.queryNkiTo_D = queryNkiTo_D;
    }
    /**
     * �[��(��)To�̎擾
     * 
     * @return �[��(��)To
     */
     public String getQueryNkiTo_D() {
        return this.queryNkiTo_D;
    }
    
	/**
     * ���[�J�[���ރ`�F�b�N�̃Z�b�g
     * 
     * @param checkNki ���[�J�[���ރ`�F�b�N
     */
    public void setCheckMkrBun(boolean checkMkrBun) {
        this.checkMkrBun = checkMkrBun;
    }
    /**
     * ���[�J�[���ރ`�F�b�N�̎擾
     * 
     * @return ���[�J�[���ރ`�F�b�N
     */
    public boolean getCheckMkrBun() {
        return this.checkMkrBun;
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
     * �敪�`�F�b�N�̃Z�b�g
     * 
     * @param kbn �敪�`�F�b�N
     */
    public void setCheckKbn(boolean checkKbn) {
        this.checkKbn = checkKbn;
    }
    /**
     * �敪�`�F�b�N�̎擾
     * 
     * @return �敪�`�F�b�N
     */
    public boolean getCheckKbn() {
        return this.checkKbn;
    } 
	/**
     * �敪�̃Z�b�g
     * 
     * @param kbn �敪
     */
    public void setKbn(String s) {

        this.kbn = s;

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
	 * ���ɓ��`�F�b�N�̃Z�b�g
	 * 
	 * @param checkNyoDte�@���ɓ��`�F�b�N
	 */
	public void setCheckNyoDte(boolean checkNyoDte) {
		this.checkNyoDte = checkNyoDte;
	}

	/**
	 * ���ɓ��`�F�b�N�̎擾
	 * 
	 * @return checkNyoDte ���ɓ��`�F�b�N
	 */
	public boolean getCheckNyoDte() {
		return checkNyoDte;
	}

	/**
	 *���ɓ�(�N)From�̃Z�b�g
	 *
	 *  @param queryNyoDteFrm_Y�@���ɓ�(�N)From
	 */
	public void setQueryNyoDteFrm_Y(String queryNyoDteFrm_Y) {
		this.queryNyoDteFrm_Y = queryNyoDteFrm_Y;
	}

	/**
	 * ���ɓ�(�N)From�̎擾
	 * 
	 * @return�@���ɓ�(�N)From
	 */
	public String getQueryNyoDteFrm_Y() {
		return queryNyoDteFrm_Y;
	}

	/**
	 * ���ɓ�(��)From�̃Z�b�g
	 * 
	 * @param queryNyoDteFrm_M�@���ɓ�(��)From
	 */
	public void setQueryNyoDteFrm_M(String queryNyoDteFrm_M) {
		this.queryNyoDteFrm_M = queryNyoDteFrm_M;
	}


	/**
	 * ���ɓ�(��)From�̎擾
	 * 
	 * @return�@���ɓ�(��)From
	 **/
	public String getQueryNyoDteFrm_M() {
		return queryNyoDteFrm_M;
	}

	/**
	 * ���ɓ�(��)From�̃Z�b�g
	 * 
	 * @param queryNyoDteFrm_D�@���ɓ�(��)From
	 */
	public void setQueryNyoDteFrm_D(String queryNyoDteFrm_D) {
		this.queryNyoDteFrm_D = queryNyoDteFrm_D;
	}

	/**
	 * ���ɓ�(��)From�̎擾
	 * 
	 * @return�@���ɓ�(��)From
	 */
	public String getQueryNyoDteFrm_D() {
		return queryNyoDteFrm_D;
	}

	/**
	 * ���ɓ�(�N)to�̃Z�b�g
	 * 
	 * @param queryNyoDteTo_Y ���ɓ�(�N)to
	 */
	public void setQueryNyoDteTo_Y(String queryNyoDteTo_Y) {
		this.queryNyoDteTo_Y = queryNyoDteTo_Y;
	}

	/**
	 * ���ɓ�(�N)to�̎擾
	 * 
	 * @return�@ ���ɓ�(�N)to
	 */
	public String getQueryNyoDteTo_Y() {
		return queryNyoDteTo_Y;
	}

	/**
	 * ���ɓ�(��)to�̃Z�b�g
	 * 
	 * @param queryNyoDteTo_M ���ɓ�(��)to
	 */
	public void setQueryNyoDteTo_M(String queryNyoDteTo_M) {
		this.queryNyoDteTo_M = queryNyoDteTo_M;
	}

	/**
	 * ���ɓ�(��)to�̎擾
	 * 
	 * @return ���ɓ�(��)to
	 */
	public String getQueryNyoDteTo_M() {
		return queryNyoDteTo_M;
	}


	/**
	 * ���ɓ�(��)to�̎擾
	 * 
	 * @return ���ɓ�(��)to
	 */
	public String getQueryNyoDteTo_D() {
		return queryNyoDteTo_D;
	}

	/**
	 * ���ɓ�(��)to�̎擾
	 * 
	 * @param queryNyoDteTo_D ���ɓ�(��)to
	 */
	public void setQueryNyoDteTo_D(String queryNyoDteTo_D) {
		this.queryNyoDteTo_D = queryNyoDteTo_D;
	}


	/**
	 * �`�F�b�N�{�b�N�X���������̎擾
	 * @return
	 */
	public boolean isChkHacJun() {
		return chkHacJun;
	}

	/**
	 * �`�F�b�N�{�b�N�X���������̐ݒ�
	 * @param b
	 */
	public void setChkHacJun(boolean b) {
		chkHacJun = b;
	}

	/**
	 * @return
	 */
	public String getPrcKbn() {
		return prcKbn;
	}

	/**
	 * @param string
	 */
	public void setPrcKbn(String string) {
		prcKbn = string;
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

	
	public OrderHistoryFormRow getFormRow_R(int i){
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
	
	
	//�\���敪
	public void setHyoKbn(String hyoKbn){
		this.hyoKbn = hyoKbn;
	}
	
	public String getHyoKbn(){
		return  this.hyoKbn;
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
	public void reset_checkbox(){
		this.setCheckHacBng(false);
		this.setCheckHacDte(false);
		this.setCheckNki(false);		
		this.setCheckKbn(false);
		this.setCheckNyoDte(false);
		this.setChkHacJun(false);
	}
	//���������\�b�h(text)
	public void clear_text(){
		this.setPrcKbn(PRCKBN_SEARCH);
		this.setRb_HacSyo("2");
		this.setRb_BunCod("1");
		this.setRb_Nyuko("2");
		this.setCheckHacBng(false);
		this.setCheckHacDte(false);
		this.setCheckNki(false);
		this.setCheckMkrBun(false);
		this.setCheckKbn(false);	
		this.setCheckNyoDte(false);	
		this.setQueryHacCod("");
		this.setQueryKigBng("");
		this.setQueryHbiDte_Y("");
		this.setQueryHbiDte_M("");
		this.setQueryHbiDte_D("");
		this.setQueryHacBngFrm("");
		this.setQueryHacBngTo("");
		this.setQueryHacDteFrm_Y("");
		this.setQueryHacDteFrm_M("");
		this.setQueryHacDteFrm_D("");
		this.setQueryHacDteTo_Y("");
		this.setQueryHacDteTo_M("");
		this.setQueryHacDteTo_D("");
		this.setQueryNkiFrm_Y("");
		this.setQueryNkiFrm_M("");
		this.setQueryNkiFrm_D("");
		this.setQueryNkiTo_Y("");
		this.setQueryNkiTo_M("");
		this.setQueryNkiTo_D("");
		this.setMkrBun(null);
		this.setKbn("");
		this.setQueryNyoDteFrm_Y("");
		this.setQueryNyoDteFrm_M("");
		this.setQueryNyoDteFrm_D("");
		this.setQueryNyoDteTo_Y("");
		this.setQueryNyoDteTo_M("");
		this.setQueryNyoDteTo_D("");
		this.setChkHacJun(false);
	}
	

	//---------------------------------------------------------------------------------
	public ActionErrors validate(ActionMapping mapping,HttpServletRequest req){
		if(command.equals("INIT"))
			return null;
		if(command.equals(COMMAND_CLEAR))
			return null;
		
		ActionErrors errors = new ActionErrors();

		//���s�̂Ƃ�				
		if(command.equals(COMMAND_JIKKOU)){
			DateUtils dateUtils = new DateUtils();
			int today = dateUtils.getDate6Int();
        
	/** ���̓`�F�b�N **/
 	    	/** ������R�[�h **/
			if(!queryHacCod.trim().equals("")){ //

    			//���p�̂�
				if(!StringUtils.isAscii(queryHacCod.trim())){
					errors.add("",new ActionError("errors.input.1","������R�[�h","���p"));
				}
			}
             /** �L���ԍ� **/
            if(!queryKigBng.trim().equals("")){
            	
            	//���p
            	if(!StringUtils.isAscii(queryKigBng.trim())){
            		errors.add("",new ActionError("errors.input.1","�L���ԍ�","���p"));
            	}
            }
            
 			/** ������ **/
    		if(!queryHbiDte_Y.trim().equals("") || !queryHbiDte_M.trim().equals("") || !queryHbiDte_D.trim().equals("")){
            	//���p
            	if( (!StringUtils.isAscii(queryHbiDte_Y.trim()) && !queryHbiDte_Y.trim().equals("")) ||
            	    (!StringUtils.isAscii(queryHbiDte_M.trim()) && !queryHbiDte_M.trim().equals("")) || 
            	    (!StringUtils.isAscii(queryHbiDte_D.trim()) && !queryHbiDte_D.trim().equals("")) ){
            		errors.add("",new ActionError("errors.input.1","������","���p"));
            	}else{
				//���t�^
					if(!CheckCommon.validateAsDate(queryHbiDte_Y,queryHbiDte_M,queryHbiDte_D)){
							errors.add("",new ActionError("errors.input.1","������","���t"));
					}
				}
			}
			
			/** �������ԍ�From&To **/
			if(rb_HacSyo.equals("1")){
				if(checkHacBng || !queryHacBngFrm.trim().equals("") || !queryHacBngTo.trim().equals("")){
					errors.add("",new ActionError("errors.input.prohibited","���o�͑I�����A�������ԍ�"));
				}
			}else{		
				if(checkHacBng){
					boolean isAsciiDigitErr = false;
					if(queryHacBngFrm.trim().equals("")){
						errors.add("",new ActionError("errors.input.required","�������ԍ�(From)"));
					}else if(!StringUtils.isAsciiDigit(queryHacBngFrm.trim())){
						errors.add("",new ActionError("errors.input.1","�������ԍ�(From)","���p����"));
						isAsciiDigitErr = true;
					}
					if(!queryHacBngTo.trim().equals("")){
						if(!StringUtils.isAsciiDigit(queryHacBngTo.trim())){
							errors.add("",new ActionError("errors.input.1","�������ԍ�(To)","���p����"));
							isAsciiDigitErr = true;
						}
						if(!queryHacBngFrm.trim().equals("") && !isAsciiDigitErr){
							int hacBngFrm = Integer.parseInt(queryHacBngFrm);
							int hacBngTo = Integer.parseInt(queryHacBngTo);
							if(hacBngFrm > hacBngTo){
								errors.add("",new ActionError("errors.input.incorrect","�������ԍ��͈͎̔w��"));
							}
						}
					}
				}else{
					if(!queryHacBngFrm.trim().equals("") || !queryHacBngTo.trim().equals("")){
						errors.add("",new ActionError("errors.input.prohibited","�������ԍ�"));
					}
				}
			}		

			/** ������From&To **/
		    if(checkHacDte){//���������͉̂Ƃ�
		    	boolean hacDte_required = false;
		    	//From���ǂꂩ�u�����N
		    	if(queryHacDteFrm_Y.trim().equals("") || queryHacDteFrm_M.trim().equals("") || queryHacDteFrm_D.trim().equals("")){
					hacDte_required = true;
		    	    errors.add("",new ActionError("errors.input.required","������(From)"));
		    	}else{
		    		//���p
             		if((!StringUtils.isAscii(queryHacDteFrm_Y.trim()) && !queryHacDteFrm_Y.trim().equals("")) || 
		    		   (!StringUtils.isAscii(queryHacDteFrm_M.trim()) && !queryHacDteFrm_M.trim().equals("")) || 
            	       (!StringUtils.isAscii(queryHacDteFrm_D.trim()) && !queryHacDteFrm_D.trim().equals("")) ){
            				errors.add("",new ActionError("errors.input.1","������","���p"));
      	      		}else{

		    		//From���t�^�`�F�b�N
		    	   		if(!CheckCommon.validateAsDate(queryHacDteFrm_Y,queryHacDteFrm_M,queryHacDteFrm_D)){
							errors.add("",new ActionError("errors.input.1","������(From)","���t"));		
		    	   		}else{
							int hacdte_from = Integer.parseInt(queryHacDteFrm_Y.trim() + 
						   	 	StringUtils.lpad(queryHacDteFrm_M.trim(),2,"0") + 
							 	StringUtils.lpad(queryHacDteFrm_D.trim(),2,"0"));
								//�����ȍ~�w
					    		if(hacdte_from > today)
					    			errors.add("",new ActionError("errors.input.1","������(From)","�����ȑO�̓��t"));
				   		}
            	    }
		        }
		        if(!(queryHacDteTo_Y.trim().equals("") && queryHacDteTo_M.trim().equals("") && queryHacDteTo_D.trim().equals(""))){
		    		if(queryHacDteFrm_Y.trim().equals("") || queryHacDteFrm_M.trim().equals("") || queryHacDteFrm_D.trim().equals("")){
		    	    	if(!hacDte_required)
		    	    		errors.add("",new ActionError("errors.input.required","������(From)"));
		    		}
		    		if(queryHacDteTo_Y.trim().equals("") || queryHacDteTo_M.trim().equals("") || queryHacDteTo_D.trim().equals("")){
		    	    	errors.add("",new ActionError("errors.input.required","������(To)"));
		    		}else{ 
		    		//To���t�^�`�F�b�N
		    	   		if(!CheckCommon.validateAsDate(queryHacDteTo_Y,queryHacDteTo_M,queryHacDteTo_D)){
							errors.add("",new ActionError("errors.input.1","������(To)","���t"));		
		    	   		}else{
							int hacdte_to = Integer.parseInt(queryHacDteTo_Y.trim() + 
						 	  	 StringUtils.lpad(queryHacDteTo_M.trim(),2,"0") + 
								 StringUtils.lpad(queryHacDteTo_D.trim(),2,"0"));
							//�����ȍ~�w
					  		if(hacdte_to > today)
					    		errors.add("",new ActionError("errors.input.1","������","�����ȑO�̓��t"));

							int hacdte_from = Integer.parseInt(queryHacDteFrm_Y.trim() + 
						   	 	StringUtils.lpad(queryHacDteFrm_M.trim(),2,"0") + 
							 	StringUtils.lpad(queryHacDteFrm_D.trim(),2,"0"));
							if(!queryHacDteFrm_Y.trim().equals("") && !queryHacDteFrm_M.trim().equals("") && !queryHacDteFrm_D.trim().equals("")){
								if(hacdte_to < hacdte_from)
					    			errors.add("",new ActionError("errors.input.incorrect","�������͈͎̔w��"));
							}		 	
				 		}
		     	    }
		        }		
		    }else{
		    	if(!queryHacDteFrm_Y.trim().equals("") || !queryHacDteFrm_M.trim().equals("")|| 
		    	   !queryHacDteFrm_D.trim().equals("") || !queryHacDteTo_Y.trim().equals("") || 
		    	   !queryHacDteTo_M.trim().equals("")  || !queryHacDteTo_D.trim().equals("")){
		    		errors.add("",new ActionError("errors.input.prohibited","������"));
		    	}
  		    }
		    
		    /** �[��From&To **/
		    if(checkNki){//�[�����͉̂Ƃ�
		    	boolean nki_required = false;
		    	//From���ǂꂩ�u�����N
		    	if(queryNkiFrm_Y.trim().equals("") || queryNkiFrm_M.trim().equals("") || queryNkiFrm_D.trim().equals("")){
		    	    nki_required = true;
		    	    errors.add("",new ActionError("errors.input.required","�[��(From)"));
		    	}else{
		    		//���p
             		if((!StringUtils.isAscii(queryNkiFrm_Y.trim()) && !queryNkiFrm_Y.trim().equals("")) || 
		    		   (!StringUtils.isAscii(queryNkiFrm_M.trim()) && !queryNkiFrm_M.trim().equals("")) || 
            	       (!StringUtils.isAscii(queryNkiFrm_D.trim()) && !queryNkiFrm_D.trim().equals("")) ){
            				errors.add("",new ActionError("errors.input.1","�[��","���p"));
      	      		}else{
		    			//From���t�^�`�F�b�N
		    	   		if(!CheckCommon.validateAsDate(queryNkiFrm_Y,queryNkiFrm_M,queryNkiFrm_D)){
							errors.add("",new ActionError("errors.input.1","�[��(From)","���t"));		
				   		}
		        	}
		    	}
		        if(!(queryNkiTo_Y.trim().equals("") && queryNkiTo_M.trim().equals("") && queryNkiTo_D.trim().equals(""))){
		    		if(queryNkiFrm_Y.trim().equals("") || queryNkiFrm_M.trim().equals("") || queryNkiFrm_D.trim().equals("")){
		    	    	if(!nki_required)
		    	    		errors.add("",new ActionError("errors.input.required","�[��(From)"));
		    		}
		    		if(queryNkiTo_Y.trim().equals("") || queryNkiTo_M.trim().equals("") || queryNkiTo_D.trim().equals("")){
		    	    	errors.add("",new ActionError("errors.input.required","�[��(To)"));
		    		}else{ 
		    		//To���t�^�`�F�b�N
		    	   		if(!CheckCommon.validateAsDate(queryNkiTo_Y,queryNkiTo_M,queryNkiTo_D)){
							errors.add("",new ActionError("errors.input.1","�[��(To)","���t"));		
		    	   		}else{
							int nki_to = Integer.parseInt(queryNkiTo_Y.trim() + 
						 	  	 StringUtils.lpad(queryNkiTo_M.trim(),2,"0") + 
								 StringUtils.lpad(queryNkiTo_D.trim(),2,"0"));

							int nki_from = Integer.parseInt(queryNkiFrm_Y.trim() + 
						   	 	StringUtils.lpad(queryNkiFrm_M.trim(),2,"0") + 
							 	StringUtils.lpad(queryNkiFrm_D.trim(),2,"0"));

							if(!queryNkiFrm_Y.trim().equals("") && !queryNkiFrm_M.trim().equals("") && !queryNkiFrm_D.trim().equals("")){
								if(nki_to < nki_from){
					    			errors.add("",new ActionError("errors.input.incorrect","�[���͈͎̔w��"));
								}	
							} 	
				 		}
		     	    }
		        }		
		    }else{
		    	if(!queryNkiFrm_Y.trim().equals("") || !queryNkiFrm_M.trim().equals("")|| 
		    	   !queryNkiFrm_D.trim().equals("") || !queryNkiTo_Y.trim().equals("") || 
		    	   !queryNkiTo_M.trim().equals("")  || !queryNkiTo_D.trim().equals("")){
		    		errors.add("",new ActionError("errors.input.prohibited","�[��"));
		    	}
  		    }
  		    //���[�J�[����
			if(checkMkrBun){
				if(mkrBun == null){
					errors.add("",new ActionError("errors.input.required","���[�J�[����"));		
				}
			}
			/** ���ɓ�From&To **/  //2004/04/01 add
			if(checkNyoDte){//���ɓ����͉̂Ƃ�
				boolean nyodte_required = false;
				//From���ǂꂩ�u�����N
				if(queryNyoDteFrm_Y.trim().equals("") || queryNyoDteFrm_M.trim().equals("") || queryNyoDteFrm_D.trim().equals("")){
					nyodte_required = true;
					errors.add("",new ActionError("errors.input.required","���ɓ�(From)"));
				}else{
					//���p
					if((!StringUtils.isAscii(queryNyoDteFrm_Y.trim()) && !queryNyoDteFrm_Y.trim().equals("")) || 
					   (!StringUtils.isAscii(queryNyoDteFrm_M.trim()) && !queryNyoDteFrm_M.trim().equals("")) || 
					   (!StringUtils.isAscii(queryNyoDteFrm_D.trim()) && !queryNyoDteFrm_D.trim().equals("")) ){
							errors.add("",new ActionError("errors.input.1","���ɓ�","���p"));
					}else{
						//From���t�^�`�F�b�N
						if(!CheckCommon.validateAsDate(queryNyoDteFrm_Y,queryNyoDteFrm_M,queryNyoDteFrm_D)){
							errors.add("",new ActionError("errors.input.1","���ɓ�(From)","���t"));		
						}
					}
				}
				if(!(queryNyoDteTo_Y.trim().equals("") && queryNyoDteTo_M.trim().equals("") && queryNyoDteTo_D.trim().equals(""))){
					if(queryNyoDteTo_Y.trim().equals("") || queryNyoDteTo_M.trim().equals("") || queryNyoDteTo_D.trim().equals("")){
						errors.add("",new ActionError("errors.input.required","���ɓ�(To)"));
					}else{ 
					//To���t�^�`�F�b�N
						if(!CheckCommon.validateAsDate(queryNyoDteTo_Y,queryNyoDteTo_M,queryNyoDteTo_D)){
							errors.add("",new ActionError("errors.input.1","���ɓ�(To)","���t"));		
						}else{
							int nyodte_to = Integer.parseInt(queryNyoDteTo_Y.trim() + 
								 StringUtils.lpad(queryNyoDteTo_M.trim(),2,"0") + 
								 StringUtils.lpad(queryNyoDteTo_D.trim(),2,"0"));

							int nyodte_from = Integer.parseInt(queryNyoDteFrm_Y.trim() + 
								StringUtils.lpad(queryNyoDteFrm_M.trim(),2,"0") + 
								StringUtils.lpad(queryNyoDteFrm_D.trim(),2,"0"));

							if(!queryNyoDteFrm_Y.trim().equals("") && !queryNyoDteFrm_M.trim().equals("") && !queryNyoDteFrm_D.trim().equals("")){
								if(nyodte_to < nyodte_from){
									errors.add("",new ActionError("errors.input.incorrect","���ɓ��͈͎̔w��"));
								}	
							} 	
						}
					}
				}		
			}else{
				if(!queryNyoDteFrm_Y.trim().equals("") || !queryNyoDteFrm_M.trim().equals("")|| 
				   !queryNyoDteFrm_D.trim().equals("") || !queryNyoDteTo_Y.trim().equals("") || 
				   !queryNyoDteTo_M.trim().equals("")  || !queryNyoDteTo_D.trim().equals("")){
					errors.add("",new ActionError("errors.input.prohibited","���ɓ�"));
				}
			}

		}
		
		
		return errors.empty() ? null : errors;
	}
	
	public void reset(ActionMapping maping,HttpServletRequest req){
		this.command = "INIT";
		reset_checkbox();
		clear_text();
	}

}

