package com.jds.prodord.common;

/**
* CSV�f�[�^�i�[�N���X�̕K�{���\�b�h
*
*
*
*/
public interface CommonCsvVoInterface{

	public final static int TYPE_STRING = 0;
	public final static int TYPE_NUMBER = 1;

	/**
	*
	*@return �J������
	*/
	public int getColSize();
	
	/**
	*
	*@return �J�����i���ځj
	*/
	public String getColumn(int i);

	/**
	*
	*@return �J�����̃^�C�v
	*/
	public int getColumnType(int i);

}



