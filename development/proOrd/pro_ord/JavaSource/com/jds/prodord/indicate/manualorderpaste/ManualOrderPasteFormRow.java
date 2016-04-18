package com.jds.prodord.indicate.manualorderpaste;

/**
 * ManualOrderPasteFormRow  マニュアル発注指示一括貼付フォーム行クラス。<BR>
 * <dt><b>作成日: </b></dt>
 * <dd>2007/09/10</dd>
 * <dt><B>作成者: </b></dt>
 * <dd>(NII)</dd>
 * <dt><B>処理概要: </b></dt>
 * <dd>Httpリクエストとリクエストの結果のうちの繰り返し項目を格納するクラス。</dd>
 * <br>
 * <b>変更履歴</b><br>
 * <table border="1">
 * 	<tr>
 * 		<th width="80">修正日付</th><th width="100">修正者</th><th width="300">修正内容</th>
 * 	</tr>
 * 	<tr>
 * 		<td>yyyy/MM/dd</td>
 * 		<td>(NII)</td>
 * 		<td>・</td>
 * 	</tr>
 * </table>
 */
public class ManualOrderPasteFormRow implements java.io.Serializable{
	
	private String kigBng = "";
	private String fukSziCod = "";
	private String suuRyo = "";
	private String biKou2= "";

	/**
	 * 記号番号の取得
	 * @return
	 */
	public String getKigBng() {
		return kigBng;
	}

	/**
	 * 記号番号のセット
	 * @param kigBng
	 */
	public void setKigBng(String kigBng) {
		this.kigBng = kigBng.toUpperCase();
	}
	/**
	 * 副資材コードの取得
	 * @return
	 */
	public String getFukSziCod() {
		return fukSziCod;
	}
	/**
	 * 副資材コードのセット
	 * @param fuksziCod
	 */
	public void setFukSziCod(String fukSziCod) {
		this.fukSziCod = fukSziCod.toUpperCase();
	}


	/**
	 * 数量の取得
	 * @return
	 */
	public String getSuuRyo() {
		return suuRyo;
	}
	/**
	 * 数量のセット
	 * @param string
	 */
	public void setSuuRyo(String suuRyo) {
		this.suuRyo = suuRyo;
	}

	/**
	 * 備考２の取得
	 * @return
	 */
	public String getBiKou2(){
		return biKou2;
	}

	/**
	 * 備考２のセット
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
