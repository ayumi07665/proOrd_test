<!DOCTYPE HTML>

<%@ page contentType="text/html; charset=Shift_JIS" %>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
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
<TITLE>error.jsp</TITLE>
<script>
<!--
	function formSubmit(){
		document.forms[0].submit();
	}
// --></script>
</HEAD>

<BODY onload="formSubmit();">

<% String reason = request.getParameter("reason"); %>
<% String action = "/commonError.do?reason=" + ((reason == null)? "system" : reason); %>

<html:form action="<%= action %>" target="_top">
</html:form>

</BODY>

</html:html>

