package com.jds.prodord.indicate.manualorderpaste;

/**
 * ManualOrderPasteFormRow  �}�j���A�������w���ꊇ�\�t�t�H�[���s�N���X�B<BR>
 * <dt><b>�쐬��: </b></dt>
 * <dd>2007/09/10</dd>
 * <dt><B>�쐬��: </b></dt>
 * <dd>(NII)</dd>
 * <dt><B>�����T�v: </b></dt>
 * <dd>Http���N�G�X�g�ƃ��N�G�X�g�̌��ʂ̂����̌J��Ԃ����ڂ��i�[����N���X�B</dd>
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
public class ManualOrderPasteFormRow implements java.io.Serializable{
	
	private String kigBng = "";
	private String fukSziCod = "";
	private String suuRyo = "";
	private String biKou2= "";

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
	 * ���ʂ̎擾
	 * @return
	 */
	public String getSuuRyo() {
		return suuRyo;
	}
	/**
	 * ���ʂ̃Z�b�g
	 * @param string
	 */
	public void setSuuRyo(String suuRyo) {
		this.suuRyo = suuRyo;
	}

	/**
	 * ���l�Q�̎擾
	 * @return
	 */
	public String getBiKou2(){
		return biKou2;
	}

	/**
	 * ���l�Q�̃Z�b�g
	 * @param string
	 */
	public void setBiKou2(String biKou2){
		this.biKou2= biKou2;
	}

	public void clear(){
		kigBng = "";
		fukSziCod = "";
		suuRyo = "";
		biKou2= "";
	}
	


}
