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

//-->
</SCRIPT>

<LINK href="theme/prodord1.css<nested-ex:nocache-param />" rel="stylesheet" type="text/css">
<LINK href="theme/commonItemSize.css<nested-ex:nocache-param />" rel="stylesheet" type="text/css">

</head>

<BODY>

<bean:define id="fmForm" name="manualOrderForm" type="com.jds.prodord.indicate.manualorder.ManualOrderForm"/>

	<div class="button_right">
	<table align="right">
		<tr valign="bottom">
			<td align="right">
				<html:form action="/Gomain" target="_parent">
				<html:submit styleClass="button2" property="submit" style="width:115px">
				<bean:message key="button.mainmenu"/>
				</html:submit>
				<html:hidden property="pagingHelperType" value="com.jds.prodord.indicate.manualorder.ManualOrderPagingHelper"/>
				</html:form>
			</td><td>
				<html:form action="/Logoff" target="_parent">
				<html:submit property="submit" styleClass="button2" style="width:64px">
				<bean:message key="button.logoff"/>
				</html:submit>
				</html:form>
			</td>
		</tr>
	</table>
	</div>

	<div style="position:absolute; left:10px; top:25px; width:200px; height:35px; z-index : 1;">
	<table style="height:30px;">
		<tr>
			<td style="width:100px" class="title_dark">
			<bean:message key="label.daikai"/>
			</td><td style="width:70px" class="title_light">
				<table style="width:70px;height:24px;" cellspacing="2" cellpadding="2" class="details">
				<tr><td align="center">
					<bean:write name="fmForm" property="queryDaiKaiSkbCod" ignore="true"/>
				</td></tr></table>
			</td>
		</tr>
	</table>
	</div>
	
</BODY>
</html:html>
