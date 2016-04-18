<!DOCTYPE HTML>
<%@ page contentType="text/html; charset=Shift_JIS" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld"  prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-form.tld" prefix="form" %>
<%@ taglib uri="/WEB-INF/struts-nested-ex.tld" prefix="nested-ex" %>

<html:html>
<head>
<META http-equiv ="X-UA-Compatible" content ="IE=edge"/>
<META http-equiv="Pragma" content="no-cache">
<META http-equiv="Cache-Control" content="no-cache">
<META http-equiv="Expires" content="Thu, 01 Dec 1994 16:00:00 GMT">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">
<META http-equiv="Content-Type" content="text/html; charset=Shift_JIS">

<SCRIPT>
<!--
	function acceptSubmit(){
		for (var i = 0; i < document.all.length; i++) {
  		  document.all(i).style.visibility = "hidden";
		}
		return true;
	}
	function formSubmit(x){
		parent.teiansuuRefer_mid.document.teiansuuReferForm.command.value = x;
//		parent._submit();
		parent.teiansuuRefer_mid.document.teiansuuReferForm.submit();
	}
	

//-->
</SCRIPT>
<LINK rel="stylesheet" href="theme/prodord1.css<nested-ex:nocache-param />" type="text/css">
<LINK href="theme/commonItemSize.css<nested-ex:nocache-param />" rel="stylesheet" type="text/css">
</head>

<BODY>

	<table align="right">
		<tr>
			<td>
				<html:submit property="command" styleClass="button" onclick="formSubmit('COMMAND_TEIANSYOKAI');">
					<bean:message key="button.teiansyokai"/>
				</html:submit>
				<html:submit property="command" styleClass="button" onclick="formSubmit('COMMAND_JIDOUHACHU');">
					<bean:message key="button.jidohachu"/>
				</html:submit>
			</td><td style="width:50px">
				<html:submit property="command" styleClass="button" onclick="formSubmit('COMMAND_CLEAR');" style="width:70px">
					<bean:message key="button.clear"/>
				</html:submit>
			</td>

		</tr>
	</table>

</BODY>
</html:html>
