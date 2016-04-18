/**
* TeiansuuReferVO  ��Đ��Ɖ�w���o�����[�I�u�W�F�N�g�N���X
*
*	�쐬��    2003/02/20
*	�쐬��    �i�m�h�h�j���c �Ĕ�
* �����T�v    ��ʂ���擾�����f�[�^���i�[����N���X�B
*
*	[�ύX����]
*		2005/02/03�i�m�h�h�j�g�c
*			�E�ꏊ�R�[�h�̒ǉ�
*		2005/05/23�i�m�h�h�j�g�c
*			�E���[�U�[�h�c�̒ǉ�
*/

package com.jds.prodord.indicate.teiansuurefer;

import java.util.*;
public class TeiansuuReferVO {

	private String command = "";

	private String daiKaiSkbCod = "";
	private String kaiSkbCod = "";
	private ArrayList queryKaiSkbCodList = null;
	
	private ArrayList kaiSkbCod_arr = new ArrayList();
	private ArrayList rank_arr = new ArrayList();
	private ArrayList ketCod_arr = new ArrayList();
	private ArrayList mkrBun_arr = new ArrayList();
	private ArrayList hyoKbn_arr = new ArrayList();
	private ArrayList kigBng_arr = new ArrayList();
	
	private boolean sort_rank = false;
	private boolean sort_ketCod = false;
	private boolean sort_sortKey = false;
	
	private boolean hitaisyo = false;
	private String taisyoRange = "";
	
	private String bshCod = "";
	private String usrId = "";

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
    public void setKaiSkbCod(String kaiSkbCod) {

        this.kaiSkbCod = kaiSkbCod;

    }


    /**
     * ���[�U�[�̉�Ў��ʃR�[�h�̎擾
     * 
     * @return ���[�U�[�̉�Ў��ʃR�[�h
     */
    public String getKaiSkbCod() {

        return this.kaiSkbCod;

    }
    
    //��Ў��ʃR�[�h���X�g
    public ArrayList getQueryKaiSkbCodList(){
    	return queryKaiSkbCodList;
    }
    public void setQueryKaiSkbCodList(ArrayList arr){
    	queryKaiSkbCodList = arr;
    }
    
//-------------------------------------------------------------���̓G���A�̔z��

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
     * �����N�̃Z�b�g
     * 
     * @param rank_arr �����N
     */
    public void setRank_arr(ArrayList rank_arr) {

        this.rank_arr = rank_arr;

    }


    /**
     * �����N�̎擾
     * 
     * @return �����N
     */
    public ArrayList getRank_arr() {

        return this.rank_arr;

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
//-------------------------------------------------------------�\�[�g��

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
	
	
	public final static int TAISYO_ONLY = 0;
	public final static int HITAISYO_ONLY = 1;
	/**
	 * Gets the taisyoDataKbn
	 * @return Returns a int
	 */
	public int getTaisyoDataKbn(){
		if(!this.hitaisyo)
			return TAISYO_ONLY;
		else{
			return HITAISYO_ONLY;
		}
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

