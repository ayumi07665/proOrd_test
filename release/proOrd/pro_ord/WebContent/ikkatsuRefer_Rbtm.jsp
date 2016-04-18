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
		var fAnswer;
		if(x == "COMMAND_HACHUSHIJI"){
			fAnswer = confirm('発注指示します。よろしいですか？');
			if(!fAnswer)
				return;
		}
		if(x == "COMMAND_DENPYOHAKKOU"){
			fAnswer = confirm('伝票発行します。よろしいですか？');
			if(!fAnswer)
				return;
		}
		parent.parent.rightF.midRF.document.ikkatsuReferForm.command.value = x;
		parent.parent.rightF.midRF.document.ikkatsuReferForm.submit();
	}
	

//-->
</SCRIPT>
<LINK rel="stylesheet" href="theme/prodord1.css<nested-ex:nocache-param />" type="text/css">
<LINK href="theme/commonItemSize.css<nested-ex:nocache-param />" rel="stylesheet" type="text/css">
</head>

<BODY>
<div id="Layer2" style="position:absolute; left:10px; top:10px; width : 350px; height:47px; z-index : 1; visibility : visible;"> 
	<table border="0" cellspacing="0" cellpadding="0" style="width:350px">
		<tr>
			<td>
				<html:submit property="command" styleClass="button" onclick="formSubmit('COMMAND_PRSCOMMENT');">
					<bean:message key="button.prscomment"/>
				</html:submit>
			</td><td>
				<html:submit property="command" styleClass="button" onclick="formSubmit('COMMAND_HACHUSHIJI');">
					<bean:message key="button.hacshiji"/>
				</html:submit>
			</td><td>
				<html:submit property="command" styleClass="button" onclick="formSubmit('COMMAND_DENPYOHAKKOU');">
					<bean:message key="button.denpyohakko"/>
				</html:submit>
			</td>
			<td>
				<html:submit property="command" styleClass="button" onclick="formSubmit('COMMAND_FKUSHIZAIHACHU');">
					<bean:message key="button.fukuhachu"/>
				</html:submit>
			</td>
		</tr>
	</table>
</div>
</body>
</html:html>
