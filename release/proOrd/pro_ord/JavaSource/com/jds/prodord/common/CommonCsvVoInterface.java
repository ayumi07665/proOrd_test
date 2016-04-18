package com.jds.prodord.common;

/**
* CSVデータ格納クラスの必須メソッド
*
*
*
*/
public interface CommonCsvVoInterface{

	public final static int TYPE_STRING = 0;
	public final static int TYPE_NUMBER = 1;

	/**
	*
	*@return カラム数
	*/
	public int getColSize();
	
	/**
	*
	*@return カラム（項目）
	*/
	public String getColumn(int i);

	/**
	*
	*@return カラムのタイプ
	*/
	public int getColumnType(int i);

}



