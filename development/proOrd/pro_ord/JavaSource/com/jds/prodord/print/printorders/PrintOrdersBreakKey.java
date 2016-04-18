
package com.jds.prodord.print.printorders;


/**
 * PrintOrdersBreakKeyクラス.<BR>
 * <dt><b>作成日: </b></dt>
 * <dd>2005/06/28</dd>
 * <dt><B>作成者: </b></dt>
 * <dd>(NII)蛭田</dd>
 * <dt><B>処理概要: </b></dt>
 * <dd>処理概要を記述</dd>
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
public class PrintOrdersBreakKey {

	private String hacCod = "";
	private String sinKyuKbn = "";
	private int count = 0;
	private int pageCount = 0;
	
	public PrintOrdersBreakKey(){
	}
	
	public PrintOrdersBreakKey(String hacCod, int count){
		this.hacCod = hacCod;
		this.count = count;
	}
	

	/**
	 * 発注先コードの取得
	 * @return
	 */
	public String getHacCod() {
		return hacCod;
	}

	/**
	 * 発注先コードの設定
	 * @param string
	 */
	public void setHacCod(String string) {
		hacCod = string;
	}

	/**
	 * 新譜旧譜区分の取得
	 * @return
	 */
	public String getSinKyuKbn() {
		return sinKyuKbn;
	}

	/**
	 * 新譜旧譜区分の設定
	 * @param string
	 */
	public void setSinKyuKbn(String string) {
		sinKyuKbn = string;
	}

	/**
	 * カウントの取得
	 * @return
	 */
	public int getCount() {
		return count;
	}

	/**
	 * カウントの設定
	 * @param i
	 */
	public void setCount(int i) {
		count = i;
	}

	/**
	 * ページカウントの取得
	 * @return
	 */
	public int getPageCount() {
		return pageCount;
	}

	/**
	 * ページカウントの設定
	 * @param i
	 */
	public void setPageCount(int i) {
		pageCount = i;
	}

}
