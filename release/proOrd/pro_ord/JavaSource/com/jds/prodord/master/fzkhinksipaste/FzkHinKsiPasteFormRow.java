package com.jds.prodord.master.fzkhinksipaste;

/**
* FzkHinKsiPasteFormRow  �t���i�\���}�X�^�[�ꊇ�\��t���t�H�[���s�N���X
*
*	�쐬��    2007/09/30
*	�쐬��    �i�m�h�h�j�c��
* �����T�v    Http���N�G�X�g�ƃ��N�G�X�g�̌��ʂ̂����̌J��Ԃ����ڂ��i�[����N���X�B
*
*	[�ύX����]
*/

public class FzkHinKsiPasteFormRow implements java.io.Serializable{
	
	private String kigBng = "";
	private String fukSziCod = "";
	private String sirSk = "";

	/**
	 * �L���ԍ��̎擾
	 * @return
	 */
	public String getKigBng() {
		return kigBng;
	}
	/**
	 * �L���ԍ��̃Z�b�g
	 * @param kigBng
	 */
	public void setKigBng(String kigBng) {
		this.kigBng = kigBng.toUpperCase();
	}

	/**
	 * �����ރR�[�h�̎擾
	 * @return
	 */
	public String getFukSziCod() {
		return fukSziCod;
	}
	/**
	 * �����ރR�[�h�̃Z�b�g
	 * @param fuksziCod
	 */
	public void setFukSziCod(String fukSziCod) {
		this.fukSziCod = fukSziCod.toUpperCase();
	}

	/**
	 * �d����̎擾
	 * @return
	 */
	public String getSirSk() {
		return sirSk;
	}
	/**
	 * �d����̃Z�b�g
	 * @param string
	 */
	public void setSirSk(String sirSk) {
		this.sirSk = sirSk.toUpperCase();
	}


	public void clear(){
		kigBng = "";
		fukSziCod = "";
		sirSk = "";
	}
}
