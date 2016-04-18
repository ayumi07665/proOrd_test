package com.jds.prodord.common;

import java.util.ArrayList;

/**
 * �N�G���[�̕ҏW���s�����[�e�B���e�B�N���X�B<BR>
 * <dt><b>�쐬��: </b></dt>
 * <dd>2006/08/07</dd>
 * <dt><B>�쐬��: </b></dt>
 * <dd>(NII)���c �Ĕ�</dd>
 * <dt><B>�����T�v: </b></dt>
 * <dd>�N�G���[�̕ҏW���s��</dd>
 * <dd>�N�G���[�ҏW�i�z��j</dd>
 * <dd>�N�G���[�ҏW�i���X�g�j</dd>
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
public class QueryUtils {

	/** �N�G���[�ҏW�Ő������镶����IN('A','B','C')�̏ꍇ */
	public static final String FLG_QUERY_IN = "0";

	/** �N�G���[�ҏW�Ő������镶����NOT IN('A','B','C')�̏ꍇ */
	public static final String FLG_QUERY_NOT_IN = "1";
	
	/**
	 * �N�G���[�ҏW�iArrayList�EIN('A','B','C')�j<br>
	 * �����l���X�g���SQL�����p�ɕ������ IN('A','B','C') �ɂ��܂��B
	 * @param queryArray �����l���X�g
	 * @return editQueryArray �����L�[�ҏW�ςݕ�����
	 */
	public static String editArrayToQuery(ArrayList queryArray){
		return editArrayToQuery(
			(String[]) queryArray.toArray(new String[queryArray.size()]),
			FLG_QUERY_IN);
	}
	
	/**
	 * �N�G���[�ҏW�iString�z��EIN('A','B','C')�j<br>
	 * �����l���X�g���SQL�����p�ɕ������ IN('A','B','C') �ɂ��܂��B
	 * @param queryArray �����l�z��
	 * @return editQueryArray �����L�[�ҏW�ςݕ�����
	 */
	public static String editArrayToQuery(String[] queryArray){
		return editArrayToQuery(queryArray, FLG_QUERY_IN);
	}

	/**
	 * �N�G���[�ҏW�iArrayList�j<br>
	 * �����l���X�g���SQL�����p�ɕ������ IN('A','B','C')
	 * �܂��� NOT IN('A','B','C') �ɂ��܂��B
	 * @param queryArray �����l���X�g
	 * @param flg �������镶����IN��NOT IN������ʂ���t���O
	 * @return editQueryArray �����L�[�ҏW�ςݕ�����
	 */
	public static String editArrayToQuery(ArrayList queryArray, String flg){
		return editArrayToQuery(
			(String[]) queryArray.toArray(new String[queryArray.size()]),
			flg);
	}

	/**
	 * �N�G���[�ҏW�iString�z��j<br>
	 * �����l���X�g���SQL�����p�ɕ������ IN('A','B','C')
	 * �܂��� NOT IN('A','B','C') �ɂ��܂��B
	 * @param queryArray �����l�z��
	 * @param flg �������镶����IN��NOT IN������ʂ���t���O
	 * @return editQueryArray �����L�[�ҏW�ςݕ�����
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
	 * �\�[�g�������̂��߂̃J����(ORDER_COL)��
	 * SELECT���邽�߂̃N�G���[������𐶐����ĕԂ��܂��B<br>
	 * 1���ڂ����l�̍��ڂ��A����ȊO�̍��ڂ��O �܂��� ��ɕ\������悤��
	 * ���䂵�����ꍇ�Ɏg�p���܂��B(mode=0�c����ȊO�̍��ڂ��O�Amode=1�c��)
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
	 * �n���ꂽ�N�G���[������̢?���param[]�Ɏw�肳�ꂽ������ŏ��ɒu�������܂��B
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
