<!DOCTYPE HTML>
<%@ page contentType="text/html; charset=Shift_JIS" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld"  prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-form.tld" prefix="form" %>
<%@ taglib uri="/WEB-INF/struts-nested-ex.tld" prefix="nested-ex" %>

<html:html>
<HEAD>

<title><bean:message key="title.submatemaster" /></title>
<META http-equiv ="X-UA-Compatible" content ="IE=edge"/>
<META http-equiv="Pragma" content="no-cache">
<META http-equiv="Cache-Control" content="no-cache">
<META http-equiv="Expires" content="Thu, 01 Dec 1994 16:00:00 GMT">
<META http-equiv="Content-Style-Type" content="text/css">
<META http-equiv="Content-Type" content="text/html; charset=Shift_JIS">
<META name="GENERATOR" content="IBM WebSphere Studio">
<LINK href="theme/prodord1.css<nested-ex:nocache-param />" rel="stylesheet" type="text/css">
<LINK href="theme/commonItemSize.css<nested-ex:nocache-param />" rel="stylesheet" type="text/css">

<SCRIPT>

<!--
	
	function acceptSubmit(){
		for (var i = 0; i < document.all.length; i++) {
  		  document.all(i).style.visibility = "hidden";
		}
		return true;
	}
	function firstfocus() {
		document.SubMateMasterForm.prcKbn.focus();
	}
-->

</SCRIPT>
</HEAD>

<body onLoad="firstfocus()">

<H3><bean:message key="title.submatemaster2" /></H3>

<div class="button_right" align="right">
<table style="border:0" cellspacing="0" cellpadding="2">
	<tr>
	<td valign="top">
		<html:form action="/GomasterMenu" target="_top">
			<html:submit property="submit" styleClass="button2" style="width:140px">
				<bean:message key="button.mastermenu" />
			</html:submit>
			<html:hidden property="pagingHelperType" value="com.jds.prodord.master.submatemaster.SubMateMasterPagingHelper"/>
		</html:form>
	</td>
	<td>
		<html:form action="/Gomain" target="_top">
			<html:submit property="submit" styleClass="button2" style="width:115px">
				<bean:message key="button.mainmenu" />
			</html:submit>
			<html:hidden property="pagingHelperType" value="com.jds.prodord.master.submatemaster.SubMateMasterPagingHelper" />
		</html:form>
	</td>
	<td>
		<html:form action="/Logoff" target="_parent">
			<html:submit property="submit" styleClass="button2" style="width:64px">
				<bean:message key="button.logoff"/>
			</html:submit>
		</html:form>
	</td>
	</tr>
</table>
</div>


<html:form action = "/SubMateMaster" target="_self" onsubmit="return acceptSubmit();">
<bean:define id="fmForm" name="SubMateMasterForm" type="com.jds.prodord.master.submatemaster.SubMateMasterForm"/>


<div style="position:absolute; left:10px; top:60px; width:200px; height:35px; z-index : 1;">
 <table border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td align="left" style="width:160px">
	      <table style="height:30px">
			<tr>
				<td style="width:100px" class="title_dark">
				<bean:message key="label.daikai"/>
				</td><td style="width:62px" class="title_light">
					<table style="width:62px;height:24px;" cellspacing="2" cellpadding="2" class="details">
					<tr><td align="center">
					<bean:write name="fmForm" property="queryDaiKaiSkbCod" ignore="true"/>
					</td></tr></table>
				</td>
			</tr>
		  </table>
	  </td>
	</tr>
  </table>
</div>

<hr>
<br>

	<html:errors/>
	<logic:present  name="SERVICE_MESSAGE">
	  <DIV class="service_msg"><bean:write name="SERVICE_MESSAGE"/></DIV>
	</logic:present>

	<logic:present  name="INFOMSG_KEY">
	  <DIV class="info_msg"><bean:write name="INFOMSG_KEY" /></DIV>
	</logic:present>

<center>




<table ><tr><td>

	<table align="left" cellspacing="8">
		<tr >	
			<td style="width:100px;height:22px;" class="title_dark">処理区分</td>
			<td style="width:100px;height:22px;" class="title_dark"><bean:message key="label.kaiskb"/></td>
			<td style="width:100px;height:22px;" class="title_dark"><bean:message key="label.fukszicod"/></td>
		</tr>
		<tr>
			<td  align="center">
				<html:select property="prcKbn" value="<%=fmForm.getPrcKbn()%>">
		   			<html:option value="1"><bean:message key="button.search"/></html:option>
					<html:option value="2"><bean:message key="button.insert"/></html:option>
					<html:option value="3"><bean:message key="button.update"/></html:option>
					<html:option value="4"><bean:message key="button.delete"/></html:option>
				</html:select>
			</td>
			<td align="center">
				<html:select property="kaiSkbCod" size="1">
			   		<html:options property="vl_kaiSkbCod"/>
			   	</html:select>
			</td>
			<td align="center">
				<html:text property="hukSziCod" style="text-align: left" styleClass="s_10" maxlength="3"  />
			</td>
		</tr>
	</table>

</td></tr></table>
	
<br><br>



<table border="1"  cellspacing="1" cellpadding="0">
    <tr><td>

	    <table border="0" bgcolor="#336666" cellspacing="1" cellpadding="1">
	        <tr>
	            <td class="title_light" style="width:30px"></td>
	            <td class="title_light" style="width:100px"><bean:message key="label.fukszicod"/></td>
	            <td class="title_light" style="width:100px"><bean:message key="label.buncod1"/></td>
	            <td class="title_light" style="width:100px"><bean:message key="label.hacskicod"/></td>
	            <td class="title_light" style="width:220px"><bean:message key="label.fukszinm"/></td>
	        </tr>

		<logic:iterate name="SubMateMasterForm" id="row" indexId="index"  property="formRowList" >
	        <tr>
	            <td align="center" bgcolor="#FFFFFF">
	            	<% String propertyName = "execute[" + index + "]";%>
					<html:checkbox property="<%= propertyName%>"  />
	            </td>
	            <td align="left" bgcolor="#FFFFFF">
	            	<% propertyName = "outHukSziCod[" + index + "]";%>
					<bean:write name ="SubMateMasterForm" property="<%= propertyName%>" />
	            </td>
	            <td align="center" bgcolor="#FFFFFF">
	            	<% propertyName = "outBunruiCod[" + index + "]";%>
					<html:text property="<%= propertyName%>" style="text-align: left" styleClass="s_10" maxlength="1"  />
	            </td>
	            <td align="center" bgcolor="#FFFFFF">
	            	<% propertyName = "outHatcCod[" + index + "]";%>
					<html:text property="<%= propertyName%>" style="text-align: left" styleClass="s_10" maxlength="6"  />
	            </td>
	            <td align="center" bgcolor="#FFFFFF">
	            	<% propertyName = "outHukSziMei[" + index + "]";%>
					<html:text property="<%= propertyName%>" style="text-align: left" styleClass="s_40" maxlength="20"  />
	            </td>
	        </tr>
		</logic:iterate>

    </table>

   </td></tr>
</table>

<br><br>


	<table>
		<tr>
			<td>
				<html:submit property="command" value="実行" styleClass="button" style="width : 75px">
				</html:submit>
			</td>
			<td>
				<html:submit property="command" value="クリア" styleClass="button" style="width : 75px">
				</html:submit>
			</td>

		</tr>
	</table>
</center>
</html:form>

</BODY>
</html:html>