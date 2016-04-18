package com.jds.prodord.indicate.manualorderpaste;

import java.util.*;
import com.jds.prodord.order.prsorder.*;
/**
 * ManualOrderPasteVO  �}�j���A�������w���ꊇ�\�t�o�����[�I�u�W�F�N�g�N���X�B<BR>
 * <dt><b>�쐬��: </b></dt>
 * <dd>2007/09/10</dd>
 * <dt><B>�쐬��: </b></dt>
 * <dd>(NII)</dd>
 * <dt><B>�����T�v: </b></dt>
 * <dd>�������ʂ��i�[����N���X�B</dd>
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

public class ManualOrderPasteVO {

	private String command = "";

	private String daiKaiSkbCod = "";
	private String queryKaiSkbCod = "";
	private ArrayList queryKaiSkbCodList = null;
	private String bshCod = "";
	private String usrId = "";
	
	private String kbn = "";
	private String prsFks = "";
	private String cpPaste =""; 

	private LinkedList formRows = new LinkedList();  


	//�v���X������ʂɓn��VO
	private	PrsOrderVO[] prsVOs = null;
	

	
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
//    
//    //��Ў��ʃR�[�h���X�g
//    public ArrayList getQueryKaiSkbCodList(){
//    	return queryKaiSkbCodList;
//    }
//    public void setQueryKaiSkbCodList(ArrayList arr){
//    	queryKaiSkbCodList = arr;
//    }
    

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
     * �v���X�^�����ނ̃Z�b�g
	 * @return
	 */
	public String getPrsFks() {
		return prsFks;
	}

	/**
     * �v���X�^�����ނ̎擾
	 * @param string
	 */
	public void setPrsFks(String string) {
		prsFks = string;
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
	public String getCpPaste() {
		return cpPaste;
	}

	/**
	 * @param string
	 */
	public void setCpPaste(String string) {
		cpPaste = string;
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

}

