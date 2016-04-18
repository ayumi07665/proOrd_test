package com.jds.prodord.master.fzkhinksipaste;

import java.util.*;
/**
* FzkHinKsiPasteVO  �t���i�\���}�X�^�[�ꊇ�\��t���o�����[�I�u�W�F�N�g�N���X
*
*	�쐬��    2007/09/30
*	�쐬��    �i�m�h�h�j�c��
* �����T�v    ��ʂ���擾�����f�[�^���i�[����N���X�B
*
*	[�ύX����]
*/

public class FzkHinKsiPasteVO {

	private String command = "";

	private String daiKaiSkbCod = "";
	private String queryKaiSkbCod = "";
	private ArrayList queryKaiSkbCodList = null;
	
	private String kaiskbcod = "";
	private String kigbng = "";
	private String fukszicod = "";
	private String kbn = "";
	private String prsFks = "";
	private String cpPaste =""; 
	private String bunCodString = new String();

	private LinkedList formRows = new LinkedList();  

	private String fuksziCod08String = "";
	private String sirSk08String = "";

	/**
	 * �i�ԃ}�X�^�[(FTBHIN01)���݃t���O
	 */
	private boolean exsitHin01 = false;
	
	/**
	 * �����ރ}�X�^�[(FTBFUK01)���݃t���O
	 */
	private boolean exsitFuk01 = false; 


	
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
    

//--------------------------------------------------------------------------

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
   * ��Ў��ʃR�[�h�̃Z�b�g
   * 
   * @param kaiskbcod ��Ў��ʃR�[�h
   */
  public void setKaiSkbCod(String kaiskbcod) {

	  this.kaiskbcod = kaiskbcod;

  }


  /**
   * ��Ў��ʃR�[�h�̎擾
   * 
   * @return ��Ў��ʃR�[�h
   */
  public String getKaiSkbCod() {

	  return this.kaiskbcod;

  }

  /**
   * �L���ԍ��̃Z�b�g
   * 
   * @param kigbng �L���ԍ�
   */
  public void setKigBng(String kigbng) {

	  this.kigbng = kigbng;

  }


  /**
   * �L���ԍ��̎擾
   * 
   * @return �L���ԍ�
   */
  public String getKigBng() {

	  return this.kigbng;

  }

  /**
   * �����ރR�[�h�̃Z�b�g
   * 
   * @param fukszicod �����ރR�[�h
   */
  public void setFukSziCod(String fukszicod) {

	  this.fukszicod = fukszicod;

  }


  /**
   * �����ރR�[�h�̎擾
   * 
   * @return �����ރR�[�h
   */
  public String getFukSziCod() {

	  return this.fukszicod;

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

	//--------------------------------------------------------------------------------------------
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

}

