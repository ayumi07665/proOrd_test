<!DOCTYPE HTML>

<%@ page contentType="text/html; charset=Shift_JIS" %>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-form.tld" prefix="form" %>
<%@ taglib uri="/WEB-INF/struts-nested-ex.tld" prefix="nested-ex" %>

<html:html>

<HEAD>

<META http-equiv ="X-UA-Compatible" content ="IE=edge"/>
<META http-equiv="Pragma" content="no-cache">
<META http-equiv="Cache-Control" content="no-cache">
<META http-equiv="Expires" content="Thu, 01 Dec 1994 16:00:00 GMT">
<META name="GENERATOR" content="IBM WebSphere Studio">
<LINK rel="stylesheet" href="theme/prodord1.css<nested-ex:nocache-param />" type="text/css">
<TITLE>error_system.jsp</TITLE>

</HEAD>

<BODY>

<hr>
<span style="color:red;font-weight:bold;">
	<bean:message key = "errors.exception"/>
</span>
<hr>
<table style="width:295px">
	<tr>
        <td valign="top">
			<html:form action="/Logoff" target="_top">
				<html:submit property="submit" styleClass="button2">
					<bean:message key="button.close"/>
				</html:submit>
			</html:form>
		</td>
    </tr>
 </table>

</BODY>

</html:html>

