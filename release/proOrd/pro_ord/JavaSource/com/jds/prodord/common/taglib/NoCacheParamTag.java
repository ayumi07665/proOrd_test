package com.jds.prodord.common.taglib;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import com.jds.prodord.common.DateUtils;

import org.apache.struts.util.ResponseUtils;

/**
 * キャッシュ防止用GETパラメータを生成するためのタグ。<BR>
 * <dt><b>作成日: </b></dt>
 * <dd>2014/09/17</dd>
 * <dt><B>作成者: </b></dt>
 * <dd>(NII)星 夏美</dd>
 * <dt><B>処理概要: </b></dt>
 * <dd>キャッシュ防止用GETパラメータを生成します。</dd>
 * <dd>JavaScript、CSS等静的ファイルへのリンクの最後に付加し、キャッシュを防止します。</dd>
 * <dd>（src="./Script.js?var=20140917"）</dd>
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
public class NoCacheParamTag extends TagSupport {

	private static final long serialVersionUID = 1L;

	/* (非 Javadoc)
	 * @see javax.servlet.jsp.tagext.Tag#doStartTag()
	 */
	public int doStartTag() throws JspException {

		StringBuffer results = new StringBuffer();
		results.append("?var=");
		results.append(new DateUtils().getDate("yyyyMMdd")); //1日ごとに変更される
		
		ResponseUtils.write(pageContext, results.toString());
				
		return (SKIP_BODY);

	}

	/* (非 Javadoc)
	 * @see javax.servlet.jsp.tagext.Tag#doEndTag()
	 */
	public int doEndTag() throws JspException {
		return (EVAL_PAGE);
	}

}
