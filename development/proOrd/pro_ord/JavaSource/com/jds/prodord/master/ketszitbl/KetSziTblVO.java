/**
* KetSziTblVO  �`�ԕʍ\�����ރ}�X�^�[�����e�i���X�o�����[�I�u�W�F�N�g�N���X
*
*	�쐬��    2004/02/01
*	�쐬��    �i�m�h�h�j�X
* �����T�v    ��ʂ���擾�����f�[�^���i�[����N���X�B
*
*	[�ύX����]
*             ���t ���O
*  �E���e
*/

package com.jds.prodord.master.ketszitbl;


import java.util.*;
import com.jds.prodord.common.*;

public class KetSziTblVO  {
	
	private String daiKaiSkbCod = "";
	private String queryKaiSkbCod = "";
	private ArrayList queryKaiSkbCodList = null;
	
	private String kaiSkbCod = new String();
	private String ketCod = new String();
	private String ketCodList[] = null;
	private String ketNm = new String();
	private String ketNm2 = new String();

	private String fuksziCodString = "";
		
	private int updDte;
	private int updJkk;

    //���[�U�[�̑�\��Ў��ʃR�[�h
    public String getDaiKaiSkbCod() {
        return this.daiKaiSkbCod;
    }
    public void setDaiKaiSkbCod(String daiKaiSkbCod) {
        this.daiKaiSkbCod = daiKaiSkbCod;
    }


    //���[�U�[�̉�Ў��ʃR�[�h
	public String getQueryKaiSkbCod() {
		return queryKaiSkbCod;
	}
	public void setQueryKaiSkbCod(String queryKaiSkbCod) {
		this.queryKaiSkbCod = queryKaiSkbCod;
	}

    
    //��Ў��ʃR�[�h���X�g
    public ArrayList getQueryKaiSkbCodList(){
    	return queryKaiSkbCodList;
    }
    public void setQueryKaiSkbCodList(ArrayList arr){
    	queryKaiSkbCodList = arr;
    }

    
//-------------------------------------------------------------���̓G���A

    /**
     * ��Ў��ʃR�[�h�̃Z�b�g
     * 
     * @param kaiSkbCod ��Ў��ʃR�[�h
     */
    public String getKaiSkbCod() {

        return this.kaiSkbCod;

    }
   
    /**
     * ��Ў��ʃR�[�h�̎擾
     * 
     * @return ��Ў��ʃR�[�h
     */
    public void setKaiSkbCod(String kaiSkbCod) {

        this.kaiSkbCod = kaiSkbCod;

    }
 
	/**
	 * �`�ԃR�[�h���X�g�̃Z�b�g
	 * 
	 * @param ketCodlist[] �`�ԃR�[�h���X�g
	 */
	public String[] getKetCodList() {
		return ketCodList;
	}

	/**
	 * �`�ԃR�[�h���X�g�̎擾
	 * 
	 * @return �`�ԃR�[�h���X�g
	 */
	public void setKetCodList(String[] strings) {
		ketCodList = strings;
	}

    /**
     * �`�ԃR�[�h�̃Z�b�g
     * 
     * @param ketCod �`�ԃR�[�h
     */
	public void setKetCod(String ketCod) {
		this.ketCod = ketCod;
	}
    /**
     * �`�ԃR�[�h�̎擾
     * 
     * @return �`�ԃR�[�h
     */
	public String getKetCod() {
		return ketCod;
	}

   /**
  	* �`�Ԗ��̂̎擾
  	* 
  	* @return �`�Ԗ���
  	*/
	public String getKetNm() {
		return ketNm;
 	}
 	/**
  	* �`�Ԗ��̂̃Z�b�g
  	* 
  	* @param �`�Ԗ���
  	*/
 	public void setKetNm(String ketNm) {
	 	this.ketNm = ketNm;
 	}

 	/**
  	* �`�Ԗ���2�̎擾
  	* 
  	* @return �`�Ԗ���2
  	*/
 	public String getKetNm2() {
	 	return ketNm2;
 	}
 	/**
  	* �`�Ԗ��̂Q�̃Z�b�g
  	* 
  	* @param �`�Ԗ���2
  	*/
 	public void setKetNm2(String ketNm2) {
	 	this.ketNm2 = ketNm2;
 	}

	/**
	 * @return
	 */
	public String getFuksziCodString() {
		return fuksziCodString;
	}
	public void setFuksziCodString(String sziTbl) {
		fuksziCodString = sziTbl;
	}

	/**
	 * @param string
	 */
	public void setFuksziCodString(KetSziTblFormRow.FuksziCod[] fuksziCod) {
		StringBuffer sb = new StringBuffer();
		for(int i=0;i<fuksziCod.length;i++){
			sb.append(StringUtils.rpad(fuksziCod[i].getVal(),3," "));
		}
		fuksziCodString = sb.toString();
	}
	
	public void removeFukusziCodSpace(){
		StringBuffer sb = new StringBuffer();
		for(int i=0;i<fuksziCodString.length(); i+=3){
			sb.append(fuksziCodString.substring(i, i+3).trim());
		}
		fuksziCodString = sb.toString();		
	}

	public ArrayList getFuksziCodArr(){
		ArrayList fuksziCod = new ArrayList();
		for(int i = 0; i<fuksziCodString.length(); i+=3){
			try{
				fuksziCod.add(fuksziCodString.substring(i, i+3));
			}catch(IndexOutOfBoundsException e){
				break;
			}
		}
		return fuksziCod;
	}

	/**
	 * Gets the updDte
	 * @return Returns a int
	 */
	public int getUpdDte() {
		return updDte;
	}
	/**
	 * Sets the updDte
	 * @param updDte The updDte to set
	 */
	public void setUpdDte(int updDte) {
		this.updDte = updDte;
	}


	/**
	 * Gets the updJkk
	 * @return Returns a int
	 */
	public int getUpdJkk() {
		return updJkk;
	}
	/**
	 * Sets the updJkk
	 * @param updJkk The updJkk to set
	 */
	public void setUpdJkk(int updJkk) {
		this.updJkk = updJkk;
	}



}


