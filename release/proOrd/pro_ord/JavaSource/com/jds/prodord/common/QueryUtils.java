package com.jds.prodord.common;

import java.util.ArrayList;

/**
 * クエリーの編集を行うユーティリティクラス。<BR>
 * <dt><b>作成日: </b></dt>
 * <dd>2006/08/07</dd>
 * <dt><B>作成者: </b></dt>
 * <dd>(NII)岡田 夏美</dd>
 * <dt><B>処理概要: </b></dt>
 * <dd>クエリーの編集を行う</dd>
 * <dd>クエリー編集（配列）</dd>
 * <dd>クエリー編集（リスト）</dd>
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
public class QueryUtils {

	/** クエリー編集で生成する文字列がIN('A','B','C')の場合 */
	public static final String FLG_QUERY_IN = "0";

	/** クエリー編集で生成する文字列がNOT IN('A','B','C')の場合 */
	public static final String FLG_QUERY_NOT_IN = "1";
	
	/**
	 * クエリー編集（ArrayList・IN('A','B','C')）<br>
	 * 条件値リストよりSQL条件用に文字列を IN('A','B','C') にします。
	 * @param queryArray 条件値リスト
	 * @return editQueryArray 条件キー編集済み文字列
	 */
	public static String editArrayToQuery(ArrayList queryArray){
		return editArrayToQuery(
			(String[]) queryArray.toArray(new String[queryArray.size()]),
			FLG_QUERY_IN);
	}
	
	/**
	 * クエリー編集（String配列・IN('A','B','C')）<br>
	 * 条件値リストよりSQL条件用に文字列を IN('A','B','C') にします。
	 * @param queryArray 条件値配列
	 * @return editQueryArray 条件キー編集済み文字列
	 */
	public static String editArrayToQuery(String[] queryArray){
		return editArrayToQuery(queryArray, FLG_QUERY_IN);
	}

	/**
	 * クエリー編集（ArrayList）<br>
	 * 条件値リストよりSQL条件用に文字列を IN('A','B','C')
	 * または NOT IN('A','B','C') にします。
	 * @param queryArray 条件値リスト
	 * @param flg 生成する文字列がINかNOT INかを区別するフラグ
	 * @return editQueryArray 条件キー編集済み文字列
	 */
	public static String editArrayToQuery(ArrayList queryArray, String flg){
		return editArrayToQuery(
			(String[]) queryArray.toArray(new String[queryArray.size()]),
			flg);
	}

	/**
	 * クエリー編集（String配列）<br>
	 * 条件値リストよりSQL条件用に文字列を IN('A','B','C')
	 * または NOT IN('A','B','C') にします。
	 * @param queryArray 条件値配列
	 * @param flg 生成する文字列がINかNOT INかを区別するフラグ
	 * @return editQueryArray 条件キー編集済み文字列
	 */
	public static String editArrayToQuery(String[] queryArray, String flg){
		
		String str1 = "= '";
		String str2 = "IN ('"; 
		
		if(flg.equals(FLG_QUERY_NOT_IN)) {
			str1 = "<> '";
			str2 = "NOT IN ('"; 
		}
		
		String editQueryArray = null;
		int query_length = queryArray.length;
		if(queryArray == null || query_length == 0){
			editQueryArray = "= ''";
		}else if(query_length == 1){
			editQueryArray = str1 + queryArray[0] + "'";
		}else if(query_length > 1){
			StringBuffer queryBuffer = new StringBuffer();
			queryBuffer.append(str2);
			for(int i=0; i<query_length; i++){
				queryBuffer.append(queryArray[i]);
				if(i == query_length - 1){
				}else{
					queryBuffer.append("','");
				}
			}
			queryBuffer.append("')");
			editQueryArray = queryBuffer.toString();
		}
		return editQueryArray;	
	}
	
	/**
	 * ソート順調整のためのカラム(ORDER_COL)を
	 * SELECTするためのクエリー文字列を生成して返します。<br>
	 * 1桁目が数値の項目を、それ以外の項目より前 または 後に表示するように
	 * 制御したい場合に使用します。(mode=0…それ以外の項目より前、mode=1…後)
	 * @return
	 */
	public static String makeOrderColQuery(String colNm, String mode) {
		StringBuffer sb = new StringBuffer();
		sb.append("CASE ");
		for (int i = 0; i < 10; i++) {
			sb.append("WHEN SUBSTR(");
			sb.append(colNm);
			sb.append(",1,1) = '");
			sb.append(String.valueOf(i));
			sb.append("' THEN '");
			sb.append(mode);
			sb.append("' ");			
		}
		sb.append("ELSE '");
		sb.append((mode.equals("0")? "1" : "0"));
		sb.append("' END AS ORDER_COL ");
		
		return sb.toString();
	}
	
	/**
	 * 渡されたクエリー文字列の｢?｣をparam[]に指定された文字列で順に置き換えます。
	 * @param sql
	 * @param param
	 */
	public static String fillInQuestion(String sql, String[] param) {
		
		if(sql.indexOf("?") < 0)
			return sql;
			
		int count = 0;
		StringBuffer sb = new StringBuffer();
		for(int i = 0; i < sql.length(); i++){
			int index = sql.indexOf("?", i);
			if(index < 0){
				sb.append(sql.charAt(i));
			}else{
				if(i == index){
					sb.append("'" + param[count] + "'");
					count++;
				}else
					sb.append(sql.charAt(i));
			}
			
		}
		return sb.toString();
	}
	
}
