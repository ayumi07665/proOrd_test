package com.jds.prodord.common.taglib;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import com.jds.prodord.common.DateUtils;

import org.apache.struts.util.ResponseUtils;

/**
 * �L���b�V���h�~�pGET�p�����[�^�𐶐����邽�߂̃^�O�B<BR>
 * <dt><b>�쐬��: </b></dt>
 * <dd>2014/09/17</dd>
 * <dt><B>�쐬��: </b></dt>
 * <dd>(NII)�� �Ĕ�</dd>
 * <dt><B>�����T�v: </b></dt>
 * <dd>�L���b�V���h�~�pGET�p�����[�^�𐶐����܂��B</dd>
 * <dd>JavaScript�ACSS���ÓI�t�@�C���ւ̃����N�̍Ō�ɕt�����A�L���b�V����h�~���܂��B</dd>
 * <dd>�isrc="./Script.js?var=20140917"�j</dd>
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
public class NoCacheParamTag extends TagSupport {

	private static final long serialVersionUID = 1L;

	/* (�� Javadoc)
	 * @see javax.servlet.jsp.tagext.Tag#doStartTag()
	 */
	public int doStartTag() throws JspException {

		StringBuffer results = new StringBuffer();
		results.append("?var=");
		results.append(new DateUtils().getDate("yyyyMMdd")); //1�����ƂɕύX�����
		
		ResponseUtils.write(pageContext, results.toString());
				
		return (SKIP_BODY);

	}

	/* (�� Javadoc)
	 * @see javax.servlet.jsp.tagext.Tag#doEndTag()
	 */
	public int doEndTag() throws JspException {
		return (EVAL_PAGE);
	}

}
