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
	window.onscroll=function pageMove(){
		L = document.documentElement.scrollLeft || document.body.scrollLeft;
		if(parent.midLF.document.body != null)
			parent.midLF.document.body.scrollLeft = L;
		if(parent.midLF.document.documentElement != null)
			parent.midLF.document.documentElement.scrollLeft = L;
	}
	function formSubmit(x){

		parent.parent.rightF.midRF.document.prsOrderForm.command.value = x;
		parent.parent.rightF.midRF.document.prsOrderForm.submit();
	}
//-->

</SCRIPT>

<LINK href="theme/prodord1.css<nested-ex:nocache-param />" rel="stylesheet" type="text/css">
<LINK href="theme/commonItemSize.css<nested-ex:nocache-param />" rel="stylesheet" type="text/css">

<STYLE>

TABLE {

	table-layout: fixed;

}

</STYLE>

</head>

<BODY>

<bean:define id="fmForm" name="prsOrderForm" type="com.jds.prodord.order.prsorder.PrsOrderForm"/>

<div id="Layer2" style="position:absolute; left:10px; top:10px; width : 140px; height:150px; z-index : 1; visibility : visible;">
	<table style="width:130px;height:60px;">
		<tr><td colspan="2" style="height:25px" class="title_light">
			<span style="font-weight:bold;">
			<logic:equal name="fmForm" property="prs_FukSgn" value="<%=com.jds.prodord.order.prsorder.PrsOrderForm.PRSHACHU%>">
				-　<bean:message key="button.prshachu"/>　-
			</logic:equal>
			<logic:equal name="fmForm" property="prs_FukSgn" value="<%=com.jds.prodord.order.prsorder.PrsOrderForm.FUKHACHU%>">
				-　<bean:message key="button.fukuhachu"/>　-
			</logic:equal>
			</span>
		</td></tr>
		<tr>
			<td style="width:62px" class="title_dark">
				<bean:message key="label.daikai"/>
			</td><td style="width:62px" class="title_light">
				<table style="width:60px;height:25px;" cellspacing="2" cellpadding="2" class="details">
				<tr><td align="center">
					<bean:write name="fmForm" property="queryDaiKaiSkbCod" ignore="true"/>
				</td></tr></table>
            </td>
		</tr>
	</table>

<table border="0" style="width:410px;height:78px;">
		<tr>
			<td style="width:406px;height:23px;" class="title_dark">
				<bean:message key="table.hininfo"/>
			</td>
        </tr>
		<tr>
			<td height="3"></td>
        </tr>
		<tr>

    <td align="center"> 

      <table border="0" style="width:404px;height:40px;" cellspacing="1" cellpadding="0"><tr class="title_light">
      			<td style="width:28px" rowspan="2" class="title_light"></td>
				<td style="width:115px"><bean:message key="label.kigbng"/></td>
				<td style="width:135px"><bean:message key="label.artist"/></td>
				<td style="width:60px"><bean:message key="label.hbidte"/></td>
				<td style="width:60px"><bean:message key="label.kbn"/></td>
				</tr><tr class="title_light">
				<td><bean:message key="label.ketcod"/></td>
				<td><bean:message key="label.title"/></td>
				<td><bean:message key="label.rank"/></td>
				<td><bean:message key="label.tax_inc"/></td>
	  </tr></table>

	</td>
        </tr>

	</table>

</div>
<div id="Layer2" style="position:absolute; left : 146px; top : 10px; width : 200px; height : 110px; z-index : 2; visibility : visible;">
<table><tr>
            <td style="width:125px;height:26px;">
				<html:form action="/Gomain" target="_top">
				<html:submit property="submit" styleClass="button2" style="width:115px">
				<bean:message key="button.mainmenu"/></html:submit>
				<html:hidden property="pagingHelperType" value="com.jds.prodord.order.prsorder.PrsOrderPagingHelper"/>
				</html:form></td>
            <td style="width:65px;height:27px;">
				<html:form action="/Logoff" target="_top">
				<html:submit property="submit" styleClass="button2" style="width:64px">
				<bean:message key="button.logoff"/></html:submit></html:form></td>
        </tr>
		<tr>
            <td style="height:25px" colspan="2">
				<logic:equal name="fmForm" property="pre_page" value="<%=com.jds.prodord.order.prsorder.PrsOrderForm.ManualOrder%>">
					<html:form action="/manualOrder" target="_top">
					<html:submit property="submit" styleClass="button2" style="width:146px">
					<bean:message key="button.backtoindicate"/></html:submit>
<!--					<html:hidden property="pagingHelperType" value="com.jds.prodord.order.prsorder.PrsOrderPagingHelper"/>
-->
					</html:form>
				</logic:equal>
				<logic:equal name="fmForm" property="pre_page" value="<%=com.jds.prodord.order.prsorder.PrsOrderForm.ManualOrderPaste%>">
					<html:form action="/manualOrderPaste" target="_top">
					<html:submit property="submit" styleClass="button2">
					<bean:message key="button.backtoindicate"/></html:submit>
					</html:form>
				</logic:equal>
				<logic:equal name="fmForm" property="pre_page" value="<%=com.jds.prodord.order.prsorder.PrsOrderForm.IkkatsuRefer%>">
					<html:submit property="command" styleClass="button2" onclick="formSubmit('COMMAND_BACK');">
						<bean:message key="button.backtorefer"/>
					</html:submit>
				</logic:equal></td>
        </tr>
	</table>
</div>
</body>

</html:html>

