/**
* FzkHinKsiFormRow  �t���i�\���}�X�^�[�����e�i���X�t�H�[���s�N���X
*
*	�쐬��    2004/02/12
*	�쐬��    �i�m�h�h�j�X
* �����T�v    Http���N�G�X�g�ƃ��N�G�X�g�̌��ʂ̂����̌J��Ԃ����ڂ��i�[����N���X�B
*
*	[�ύX����]
*        2004/06/23�i�m�h�h�j�g�c
* 			�E������於�̣�ǉ�
*/

package com.jds.prodord.master.fzkhinksi;

public class FzkHinKsiFormRow implements java.io.Serializable{
	
	private String fuksziCod = "";
	private String fuksziNm = "";
	private String sirSk = "";
	private String hacNm = ""; //2004/06/23 add
	
	private boolean check_bx = false;


	/**
	 * �����ރR�[�h�̎擾
	 * @return
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
	 * �����ޖ��̎擾
	 * @return
	 */
	public String getFuksziNm() {
		return fuksziNm;
	}

	/**
	 * �����ޖ��̃Z�b�g
	 * @param fuksziNm
	 */
	public void setFuksziNm(String fuksziNm) {
		this.fuksziNm = fuksziNm;
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




	public void clear(){
		check_bx = false;
		fuksziCod = "";
		fuksziNm = "";
		sirSk = "";
		hacNm = "";
		
	}
	


}
