package com.jds.prodord.common;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * リソース・バンドル・アクセサークラス(ApplicationResources_ja用).<BR>
 * <dt><b>作成日: </b></dt>
 * <dd>2007/09/10</dd>
 * <dt><B>作成者: </b></dt>
 * <dd>(NII)</dd>
 * <dt><B>処理概要: </b></dt>
 * <dd>ApplicationResources_jaのリソース・バンドルにアクセスするためのクラスです。</dd>
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
public class ResourceMessages {

	private static final String BUNDLE_NAME = "ApplicationResources_ja";

	private static final ResourceBundle RESOURCE_BUNDLE =
		ResourceBundle.getBundle(BUNDLE_NAME);
	
	/**
	 * リソースからキーに対応したメッセージを取得して返します。
	 * @param key
	 * @return リソースから取得した値
	 */
	public static String getString(String key) {
		try {
			return RESOURCE_BUNDLE.getString(key);
		} catch (MissingResourceException e) {
			return '!' + key + '!';
		}
	}
}
