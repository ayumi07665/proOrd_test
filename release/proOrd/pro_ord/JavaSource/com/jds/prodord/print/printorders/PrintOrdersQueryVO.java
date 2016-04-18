/**
* PrintOrdersQueryVO  ��������� ���������p�o�����[�I�u�W�F�N�g�N���X
*
*	�쐬��    2003/04/18
*	�쐬��    �i�m�h�h�j���c �Ĕ�
* �����T�v    �������f�[�^���������p�̃f�[�^���i�[����N���X�B
*
*	[�ύX����]
* 		2005/09/14�i�m�h�h�j�g�c
* 			�E�����`�[���s�t���O�̒ǉ�
*/

package com.jds.prodord.print.printorders;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.jds.prodord.common.DataFormatUtils;
import com.jds.prodord.common.StringUtils;
import com.jds.prodord.common.SystemException;

public class PrintOrdersQueryVO  {

	private String daiKaiSkbCod = "";
	private String kaiSkbCod = "";
	private ArrayList kaiSkbCod_arr = new ArrayList();
	private String syrSuu = "";
	
	private ArrayList partOfQuery_arr = new ArrayList();
	private boolean hasHacSyoBng = false;
	private boolean teiDnpHakkou = false;
	private int updDte;
	private int updJkk;
	

    /**
     * ��\��Ў��ʃR�[�h�̃Z�b�g
     * 
     * @param daiKaiSkbCod ��\��Ў��ʃR�[�h
     */
    public void setDaiKaiSkbCod(String daiKaiSkbCod) {

        this.daiKaiSkbCod = daiKaiSkbCod;

    }


    /**
     * ��\��Ў��ʃR�[�h�̎擾
     * 
     * @return ��\��Ў��ʃR�[�h
     */
    public String getDaiKaiSkbCod() {

        return this.daiKaiSkbCod;

    }
 

	/**
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
     * ��Ў��ʃR�[�harr�̃Z�b�g
     * 
     * @param kaiSkbCod_arr ��Ў��ʃR�[�harr
     */
    public void setKaiSkbCod_arr(ArrayList kaiSkbCod_arr) {

        this.kaiSkbCod_arr = kaiSkbCod_arr;

    }


    /**
     * ��Ў��ʃR�[�harr�̎擾
     * 
     * @return ��Ў��ʃR�[�harr
     */
    public ArrayList getKaiSkbCod_arr() {

        return this.kaiSkbCod_arr;

    }


	/**
     * �����񐔂̃Z�b�g
     * 
     * @param syrSuu ������
     */
	public void setSyrSuu(String s){
		
		this.syrSuu = StringUtils.lpad(s,6,"0");
		
	}
	/**
     * �����񐔂̎擾
     * 
     * @return ������
     */
	public String getSyrSuu(){
		
		return this.syrSuu;
		
	}
	
    
    /**
	 * Gets the hasHacSyoBng
	 * @return Returns a boolean
	 */
	public boolean getHasHacSyoBng() {
		return hasHacSyoBng;
	}
	/**
	 * Sets the hasHacSyoBng
	 * @param hasHacSyoBng The hasHacSyoBng to set
	 */
	public void setHasHacSyoBng(boolean hasHacSyoBng) {
		this.hasHacSyoBng = hasHacSyoBng;
	}


	/**
	 * �����`�[���s�t���O�̎擾
	 * @return
	 */
	public boolean isTeiDnpHakkou() {
		return teiDnpHakkou;
	}

	/**
	 * �����`�[���s�t���O�̐ݒ�
	 * @param b
	 */
	public void setTeiDnpHakkou(boolean b) {
		teiDnpHakkou = b;
	}


	/**
	 * �X�V���t��Ԃ��܂��B
	 * ���������Ɖ�F�����`�[���s���痈���Ƃ��́A���������Ɖ��ʂ���n���ꂽ���t��Ԃ�
	 * @return
	 */
	public int getUpdDte() {
		if(updDte == 0){//�X�V���t���Z�b�g����Ă��Ȃ������炱���Ŏ擾
			Date sysdate = new Date(System.currentTimeMillis());
			updDte = Integer.parseInt((new SimpleDateFormat("yyMMdd")).format(sysdate),10);
		}
		return updDte;
	}

	/**
	 * �X�V������Ԃ��܂��B
	 * ���������Ɖ�F�����`�[���s���痈���Ƃ��́A���������Ɖ��ʂ���n���ꂽ������Ԃ�
	 * @return
	 */
	public int getUpdJkk() {
		if(updJkk == 0){//�X�V�������Z�b�g����Ă��Ȃ������炱���Ŏ擾
			Date sysdate = new Date(System.currentTimeMillis());
			updJkk = Integer.parseInt((new SimpleDateFormat("HHmmss")).format(sysdate),10);
		}
		return updJkk;
	}

	/**
	 * @param i
	 */
	public void setUpdDte(int i) {
		updDte = i;
	}

	/**
	 * @param i
	 */
	public void setUpdJkk(int i) {
		updJkk = i;
	}

//--------------------------------------------------------------------------------   

	/**
	 * Sets the partOfQuery_arr
	 * @param partOfQuery_arr The partOfQuery_arr to set
	 * (KAISKBCOD%HACSYODTE%HACSYOBNG%HACCOD)
	 */
	public void setPartOfQuery_arr(ArrayList partOfQuery_arr) {
		this.partOfQuery_arr = partOfQuery_arr;
	}
	public ArrayList getPartOfQuery_arr() {
		return partOfQuery_arr;
	}

	public String getPartOfQuery(String tblNm){
				
		String query = "";
		try {
			for(int i = 0; i < partOfQuery_arr.size(); i++){
				String[] query_arr = StringUtils.split(partOfQuery_arr.get(i).toString(), "%");
				if(i == 0)
					query +=  " AND ((";
				query += tblNm + ".KAISKBCOD = '" + query_arr[0]
					+ "' AND " + tblNm + ".HACSYODTE = " + query_arr[1]
				    +  " AND " + tblNm + ".HACSYOBNG = '" + DataFormatUtils.formatHacSyoBng(query_arr[2].trim())
					+ "' AND " + tblNm + ".HACCOD = '" + query_arr[3] + "'";
				if(i == partOfQuery_arr.size() - 1)
					query += "))";
				else
					query += ") OR (";
					
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			SystemException se = new SystemException(e);
			se.printStackTrace();
			throw e;
		}
		return query;
	}

}

