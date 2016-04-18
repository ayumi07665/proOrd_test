package com.jds.prodord.common;

import java.util.ArrayList;

/**
 * 発注処理共通VOインターフェース。<BR>
 * <dt><b>作成日: </b></dt>
 * <dd>2006/11/20</dd>
 * <dt><B>作成者: </b></dt>
 * <dd>(NII)</dd>
 * <dt><B>処理概要: </b></dt>
 * <dd></dd>
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
public interface CommonOrderVO {

	/**
	 * @return
	 */
	public String getDaiKaiSkbCod();
	/**
	 * @return
	 */
	public String getFukSziMkr();
	/**
	 * @return
	 */
	public String getKaiSkbCod();
	/**
	 * @return
	 */
	public String getKigBng();
	/**
	 * @return
	 */
	public String getQueryKaiSkbCod();
	/**
	 * @return
	 */
	public boolean getExsitFuk01();
	/**
	 * @return
	 */
	public String getFukSziCod();
	/**
	 * @return
	 */
	public ArrayList getFukSziCod_arr();
	/**
	 * @return
	 */
	public String getFukSziHacSaki();
	/**
	 * @return
	 */
	public ArrayList getFukSziHacSaki_arr();
	/**
	 * @return
	 */
	public ArrayList getFukSziNm_arr();
	/**
	 * @return
	 */
	public ArrayList getBunCod_arr();
	/**
	 * @return
	 */
	public String getFukSziNm();
	/**
	 * @return
	 */
	public String getFukSziPtn();
	/**
	 * @return
	 */
	public String getBunCod();

	/**
	 * @param string
	 */
	public void setDaiKaiSkbCod(String string);
	/**
	 * @param string
	 */
	public void setFukSziMkr(String string);
	/**
	 * @param string
	 */
	public void setKaiSkbCod(String string);
	/**
	 * @param string
	 */
	public void setKigBng(String string);
	/**
	 * @param string
	 */
	public void setQueryKaiSkbCod(String string);
	/**
	 * @param b
	 */
	public void setExsitFuk01(boolean b);
	/**
	 * @param string
	 */
	public void setFukSziCod(String string);
	/**
	 * @param fukSziCod_arr
	 */
	public void setFukSziCod_arr(ArrayList fukSziCod_arr);
	/**
	 * @param string
	 */
	public void setFukSziHacSaki(String string);
	/**
	 * @param fukSziHacSaki_arr
	 */
	public void setFukSziHacSaki_arr(ArrayList fukSziHacSaki_arr);
	/**
	 * @param fukSziNm_arr
	 */
	public void setFukSziNm_arr(ArrayList fukSziNm_arr);
	/**
	 * @param bunCod_arr
	 */
	public void setBunCod_arr(ArrayList bunCod_arr);
	/**
	 * @param string
	 */
	public void setFukSziNm(String string);
	/**
	 * @param string
	 */
	public void setFukSziPtn(String string);
	/**
	 * @param string
	 */
	public void setBunCod(String string);

}
