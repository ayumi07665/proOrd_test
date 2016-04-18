package com.jds.prodord.indicate.manualorderpaste;

import java.util.LinkedList;

/**
 * ManualOrderPasteResult  �}�j���A�������w���ꊇ�\�t�������ʃN���X�B<BR>
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
public class ManualOrderPasteResult {

	private ManualOrderPasteVO fmVO;
	private boolean success;
	private String desc;
	
	/**
	 * �R���X�g���N�^
	 * @param fmVO �}�j���A�������w���ꊇ�\�t�o�����[�I�u�W�F�N�g�N���X
	 * @param success
	 * @param desc
	 */
	public ManualOrderPasteResult(ManualOrderPasteVO fmVO,boolean success,String desc){
		this.fmVO = fmVO;
		this.success = success;
		this.desc = desc;
	}
	
	public LinkedList errList = null;

	public ManualOrderPasteVO getVO(){
		return fmVO;
	}
	public boolean isSuccess(){
		return success;
	}
	public String getDescription(){
		return desc;
	}

	/**
	 * �װؽĂ��擾����B
	 * @return
	 */
	public LinkedList getErrList() {
		return errList;
	}

	/**
	 * �װؽĂ�ݒ肷��B
	 * @param list
	 */
	public void setErrList(LinkedList list) {
		errList = list;
	}

}

