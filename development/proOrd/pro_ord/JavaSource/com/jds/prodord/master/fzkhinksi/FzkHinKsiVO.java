/**
* FzkHinKsiVO  �t���i�\���}�X�^�[�����e�i���X�o�����[�I�u�W�F�N�g�N���X
*
*	�쐬��    2004/02/13
*	�쐬��    �i�m�h�h�j�X
* �����T�v    ��ʂ���擾�����f�[�^���i�[����N���X�B
*
*	[�ύX����]
*             ���t ���O
* 		 2004/03/29 (NII) �X
* 			�E�i�ԃ}�X�^�̃e�[�u�����ύX
* 		 2004/04/02 (NII) �X
* 			�E�����ޔ����̏C��(���ރR�[�h�P�������݂ɑΉ�) 
* 		 2004/06/23�i�m�h�h�j�g�c
*			�E������於�̣�ǉ� 
*/

package com.jds.prodord.master.fzkhinksi;

import java.util.*;
public class FzkHinKsiVO  {
	
	private String daiKaiSkbCod = "";
	private String queryKaiSkbCod = "";
	private ArrayList queryKaiSkbCodList = null;
	
	private String kaiSkbCod = new String();
	private String hinban = new String();
	private String title = new String();
	private String hbidte = new String();
	private String ketNm = new String();
	private String ketCod = new String();
	private String setsuu = new String();
	private String fuksziCod = new String();
	private String fuksziNm = new String();
	private String sirSk = new String();
	private String hacNm = new String(); //2004/06/23 add

	private String bunCodString = new String();
	private String fuksziCod06String = "";
	private String fuksziCod08String = "";
	private String sirSk08String = "";

	private ArrayList linenoArr = null;
		
	private int updDte;
	private int updJkk;
	
	private boolean check_bx = false;
	private boolean exsitHin01 = false;
	private boolean exsitHin02 = false;
	private boolean exsitMst08 = false;

	private boolean flag = false;

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
	 * �i�Ԃ̎擾
	 * @return �i��
	 */
	public String getHinban() {
		return hinban;
	}
	/**
	 * �i�Ԃ̃Z�b�g
	 * @param hinban
	 */
	public void setHinban(String hinban) {
		this.hinban = hinban;
	}


	/**
	 * �^�C�g���̎擾
	 * @return
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * �^�C�g���̃Z�b�g
	 * @param title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * �������̎擾
	 * @return hbidte
	 */
	public String getHbidte() {
		return hbidte;
	}
	/**
	 * �������̃Z�b�g
	 * @param hbidte
	 */
	public void setHbidte(String hbidte) {
		this.hbidte = hbidte;
	}

   /**
  	* �`�Ԗ��̂̎擾
  	* 
  	* @return ketNm
  	*/
	public String getKetNm() {
		return ketNm;
 	}
 	/**
  	* �`�Ԗ��̂̃Z�b�g
  	* 
  	* @param ketNm
  	*/
 	public void setKetNm(String ketNm) {
	 	this.ketNm = ketNm;
 	}

	/**
	 * �`�ԃR�[�h�̎擾
	 * 
	 * @return ketNm
	 */
	 public String getKetCod() {
		 return ketCod;
	 }
	 /**
	 * �`�ԃR�[�h�̃Z�b�g
	 * 
	 * @param ketNm
	 */
	 public void setKetCod(String ketCod) {
		 this.ketCod = ketCod;
	 }

	/**
	 * �Z�b�g���̎擾
	 * @return setsuu
	 */
	public String getSetsuu() {
		return setsuu;
	}
	/**
	 * �Z�b�g���̃Z�b�g
	 * @param setsuu
	 */
	public void setSetsuu(String setsuu) {
		this.setsuu = setsuu;
	}

	/**
	 * �����ރR�[�h�̎擾
	 * @return fuksziCod
	 */
	public String getFuksziCod() {
		return fuksziCod;
	}
	/**
	 * �����ރR�[�h�̃Z�b�g
	 * @param fuksziCod
	 */
	public void setFuksziCod(String fuksziCod) {
		this.fuksziCod = fuksziCod;
	}

	/**
	 * �����ޖ��̂̎擾
	 * @return fuksziNm
	 */
	public String getFuksziNm() {
		return fuksziNm;
	}
	/**
	 * �����ޖ��̂̃Z�b�g
	 * @param string
	 */
	public void setFuksziNm(String fuksziNm) {
		this.fuksziNm = fuksziNm;
	}


	/**
	 * �d����̎擾
	 * @return sirSk
	 */
	public String getSirSk() {
		return sirSk;
	}
	/**
	 * �d����̃Z�b�g
	 * @param sirSk
	 */
	public void setSirSk(String sirSk) {
		this.sirSk = sirSk;
	}

	/**
	 * �`�F�b�N�{�b�N�X�̃Z�b�g
	 * @param check_bx �`�F�b�N�{�b�N�X
	 */
	 public void setCheck_bx(boolean check_bx) {
		 this.check_bx = check_bx;
	 }

	/**
	* �`�F�b�N�{�b�N�X�̎擾
	* 
	* @return �`�F�b�N�{�b�N�X
	*/
	 public boolean getCheck_bx() {
		 return this.check_bx;
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

	/**
	 * �i�ԃ}�X�^�[(FTBHIN01)���݃t���O�̃Z�b�g
	 * 
	 * @param exsitZai01 �i�ԃ}�X�^�[(FTBHIN01)���݃t���O
	 */
	public void setExsitHin01(boolean exsitHin01) {
		this.exsitHin01 = exsitHin01;
	}
	/**
	 * �i�ԃ}�X�^�[(FTBHIN01)���݃t���O�̎擾
	 * 
	 * @return �i�ԃ}�X�^�[(FTBHIN01)���݃t���O
	 */
	public boolean getExsitHin01() {
		return this.exsitHin01;
	}

	/**
	 * �t���i�}�X�^�[(FTBMST08)���݃t���O�̃Z�b�g
	 * @param exsitMst08 �t���i�}�X�^�[(FTBMST08)���݃t���O
	 */
	public void setExsitMst08(boolean exsitMst08) {
		this.exsitMst08 = exsitMst08;
	}
	/**
	 * �t���i�}�X�^�[(FTBMST08)���݃t���O�̎擾
	 * @return exsitMst08 �t���i�}�X�^�[(FTBMST08)���݃t���O
	 */
	public boolean getExsitMst08() {
		return this.exsitMst08;
	}


	/**
	 * @return
	 */
	public String getFuksziCod06String() {
		return fuksziCod06String;
	}
	public void setFuksziCod06String(String sziTbl) {
		fuksziCod06String = sziTbl;
	}

	/**
	 * @param string
	 */
	
	public void removeFukusziCod06Space(){
		StringBuffer sb = new StringBuffer();
		for(int i=0;i<fuksziCod06String.length(); i+=3){
			sb.append(fuksziCod06String.substring(i, i+3).trim());
		}
		fuksziCod06String = sb.toString();		
	}

	public ArrayList get06FuksziCodArr(){
		ArrayList fuksziCod = new ArrayList();
		for(int i = 0; i<fuksziCod06String.length(); i+=3){
			try{
				fuksziCod.add(fuksziCod06String.substring(i, i+3));
			}catch(IndexOutOfBoundsException e){
				break;
			}
		}
		return fuksziCod;
	}

	/**
	 * @return
	 */
	public String getFuksziCod08String() {
		return fuksziCod08String;
	}
	public void setFuksziCod08String(String sziTbl) {
		fuksziCod08String = sziTbl;
	}

	/**
	 * @param string
	 */
	
	public void removeFukusziCod08Space(){
		StringBuffer sb = new StringBuffer();
		for(int i=0;i<fuksziCod08String.length(); i+=3){
			sb.append(fuksziCod08String.substring(i, i+3).trim());
		}
		fuksziCod08String = sb.toString();		
	}
	public ArrayList get08FuksziCodArr(){
		ArrayList fuksziCod = new ArrayList();
		for(int i = 0; i<fuksziCod08String.trim().length(); i+=3){
			try{
				fuksziCod.add(fuksziCod08String.substring(i, i+3));
			}catch(IndexOutOfBoundsException e){
				break;
			}
		}
		return fuksziCod;
	}

	/**
	 * @return
	 */
	public String getSirSk08String() {
		return sirSk08String;
	}
	public void setSirSk08String(String sirTbl) {
		sirSk08String = sirTbl;
	}

	/**
	 * @param string
	 */
	
	public void removeSirSk08Space(){
		StringBuffer sb = new StringBuffer();
		for(int i=0;i<sirSk08String.length(); i+=6){
			sb.append(sirSk08String.substring(i, i+6));
		}
		fuksziCod08String = sb.toString();		
	}
	public ArrayList get08SirSkCodArr(){
		ArrayList sirSk = new ArrayList();
		for(int i = 0; i<sirSk08String.length(); i+=6){
			try{
				sirSk.add(sirSk08String.substring(i, i+6));
			}catch(IndexOutOfBoundsException e){
				break;
			}
		}
		return sirSk;
	}
	
	public ArrayList getBunCodArr(){
		ArrayList bunCod = new ArrayList();
		for(int i = 0; i<bunCodString.length(); i+=1){
			try{
				bunCod.add(bunCodString.substring(i, i+1));
			}catch(IndexOutOfBoundsException e){
				break;
			}
		}
		return bunCod;
	}
	public void addBunCod(String bunCod){
		bunCodString += bunCod;
	}
	public void clearBunCod(){
		bunCodString = "";
	}

	/**
	 * �s�ԍ��̎擾
	 * @return lineArr
	 */
	public ArrayList getLineArr() {
		return linenoArr;
	}

	/**
	 * �s�ԍ��̃Z�b�g
	 * @param list
	 */
	public void setLineArr(ArrayList linenoArr) {
		this.linenoArr = linenoArr;
	}

	/**
	 * @return
	 */
	public boolean getFlag() {
		return flag;
	}

	/**
	 * @param b
	 */
	public void setFlag(boolean flag) {
		this.flag = flag;
	}


	/**
	 * �����於�̂̎擾
	 * @return
	 */
	public String getHacNm() {
		return hacNm;
	}

	/**
	 * �����於�̂̃Z�b�g
	 * @param string
	 */
	public void setHacNm(String string) {
		hacNm = string;
	}

}

