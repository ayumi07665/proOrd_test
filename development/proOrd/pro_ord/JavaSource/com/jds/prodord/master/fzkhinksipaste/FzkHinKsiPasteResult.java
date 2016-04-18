package com.jds.prodord.master.fzkhinksipaste;

import java.util.LinkedList;
/**
* FzkHinKsiPasteResult  �t���i�\���}�X�^�[�ꊇ�\��t�����ʃN���X
*
*	�쐬��    2007/09/30
*	�쐬��    �i�m�h�h�j�c��
* �����T�v    �������ʂ��i�[����N���X�B
*
*	[�ύX����]
*/

public class FzkHinKsiPasteResult {

	private FzkHinKsiPasteVO fmVO;
	private boolean success;
	private String desc;
	
	/**
	 * �R���X�g���N�^
	 * @param fmVO �}�j���A�������w���ꊇ�\�t�o�����[�I�u�W�F�N�g�N���X
	 * @param success
	 * @param desc
	 */
	public FzkHinKsiPasteResult(FzkHinKsiPasteVO fmVO,boolean success,String desc){
		this.fmVO = fmVO;
		this.success = success;
		this.desc = desc;
	}
	
	public LinkedList errList = null;

	public FzkHinKsiPasteVO getVO(){
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

