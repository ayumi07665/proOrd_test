<!DOCTYPE HTML>

<%@ page contentType="text/html; charset=Shift_JIS" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld"  prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-form.tld" prefix="form" %>
<%@ taglib uri="/WEB-INF/struts-nested-ex.tld" prefix="nested-ex" %>

<html:html>

<HEAD>

<TITLE><bean:message key="title.logon"/></TITLE>

<META http-equiv ="X-UA-Compatible" content ="IE=edge"/>
<META http-equiv="Pragma" content="no-cache">
<META http-equiv="Cache-Control" content="no-cache">
<META http-equiv="Expires" content="Thu, 01 Dec 1994 16:00:00 GMT">
<META http-equiv="Content-Type" content="text/html; charset=SHIFT_JIS">
<META name="GENERATOR" content="IBM WebSphere Studio">
<SCRIPT><!--

	function nextfocus(i, n, m) {
  		if (i.value.length >= m) {
    		i.form.elements[n].focus();
  		}
	}
	
//-->
</SCRIPT>

<LINK rel="stylesheet" href="theme/prodord2.css<nested-ex:nocache-param />" type="text/css">
</HEAD>

<BODY>
<hr>
<table border="0" cellspacing="0" cellpadding="0"><tr>
 <td style="width:170px">
   <div style="position:relative;">
     <img src="img/jds_lg.png">
   </div>
 </td>
 <td>
   <div style="position:absolute; left:190px; top:25px;z-index : 2;">
	 <img src="img/title_lg.png">
   </div>
 </td>
</tr></table>

<hr>
<div class="header1">
<bean:message key="header.logon"/>
</div>

<div style="position:absolute; left:377px; top:17px; z-index : 1;">
<img src="img/cut01.png">
</div>
<%--
<div style="position:absolute; left:865px; top:20px; z-index : 1;">
<img src="img/jds20th_lg.png">
</div>
--%>
 <p>
    <html:errors/><br>
	<logic:present  name="SERVICE_MESSAGE">
		<br><font color="red"><bean:write name="SERVICE_MESSAGE" /></font>
   	</logic:present>
 </p>

 

<br><br><br>

<html:form action="/Logon" name="LogonForm" type="com.jds.prodord.register.LogonForm" focus="user"><CENTER>


<table class="frameTable_back">
 <tr><td>	

		<table class="frameTable_front">
			<tr><td style="height:30px"></td></tr>
			<tr>
				<td style="width:110px;color:#8F7DB9" align="right">
				  <bean:message key="label.usrid"/></td>
				<td style="width:20px;color:#8F7DB9" align="center">：</td>
				<td align="left"><html:text property="user" maxlength="8" onkeyup="nextfocus(this,'pwd',maxLength)" style="width:110px;height:16px;"/></td>
			</tr>
			<tr>
				<td style="color:#8F7DB9" align="right">
				  <bean:message key="label.password"/></td>
				<td style="color:#8F7DB9" align="center">：</td>
				<td align="left"><html:password property="pwd" maxlength="8" onkeyup="nextfocus(this,'submit',maxLength)" style="width:110px;height:16px;"/></td>
			</tr>
			<tr><td style="height:30px"></td></tr>
		</table>


 </td></tr>
</table>


<br><br>



<html:submit property="submit" styleClass="button2">

<bean:message key="button.logon"/>

</html:submit>

</CENTER></html:form>

</BODY>

</html:html>

