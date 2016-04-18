package com.jds.prodord.common;

/**
* 更新履歴書き出しデータ格納クラスの必須メソッド
*
*
*
*/
public interface CommonLoggerInterface{

	/**
	*
	*@return キーフィールド
	*/
	public String getKeyfld();
	
	/**
	*
	*@return マスターＩＤ
	*/
	public String getMstid();

	/**
	*
	*@return ＫＥＹ
	*/
	public String getMstkey();

	/**
	*
	*@return エントリー日付
	*/
	public int getEnydte();

	/**
	*
	*@return エントリー時刻
	*/
	public int getEnyjkk();
	
	/**
	*
	*@return ターミナル№
	*/
	public String getTrmno();
	
	/**
	*
	*@return セグメント№
	*/
	public String getSegno();
	
	/**
	*
	*@return 更新前マスター更新日付
	*/
	public int getUpzmstupddte();

	/**
	*
	*@return 更新前マスター更新時刻
	*/
	public int getUpzmstupdjkk();
	
	/**
	*
	*@return 更新区分
	*/
	public String getKbn();
	
	/**
	*
	*@return フラグ
	*/
	public String getFlg();

	/**
	*
	*@return エラーコード
	*/
	public String getErrcod();
	
	/**
	*
	*@return 更新データＴＥＸＴ
	*/
	public String getUpdtxt();
	
	/**
	*
	*@return 更新前データＴＥＸＴ
	*/
	public String getUpdzentxt();
	
	/**
	*
	*@return 最新更新区分
	*/
	public String getUpdkbn();
	
	/**
	*
	*@return 最新更新日付
	*/
	public int getUpddte();
	
	/**
	*
	*@return 最新更新時刻
	*/
	public int getUpdjkk();






	/**
	 * 
	 * @return ユーザー情報
	 */
//	public String getUserInfo(); 
	/**
	 * 
	 * @return 処理内容
	 */
//	public String getCommand(); 

	/**
	 * 
	 * @return 更新日付
	 */
//	public int getUpddte(); 

	/**
	 * 
	 * @return 更新時刻
	 */
//	public int getUpdjkk(); 

	/**
	 * 
	 * @return 更新前データ内容
	 */
//	public String getOldData(); 
	/**
	 * 
	 * @return 更新後データ内容
	 */
//	public String getNewData(); 





}



