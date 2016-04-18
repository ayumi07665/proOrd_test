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
<script>
<!--
	window.onscroll=function pageMove(){
		L = document.documentElement.scrollLeft || document.body.scrollLeft;
		T = document.documentElement.scrollTop || document.body.scrollTop;
		
		if(parent.topLF.document.body != null)
			parent.topLF.document.body.scrollLeft = L;
		if(parent.topLF.document.documentElement != null)
			parent.topLF.document.documentElement.scrollLeft = L;
		if(parent.parent.rightF.midRF.document.body != null)
			parent.parent.rightF.midRF.document.body.scrollTop = T;
		if(parent.parent.rightF.midRF.document.documentElement != null)
			parent.parent.rightF.midRF.document.documentElement.scrollTop = T;
	}
	
	function acceptSubmit(){
		for (var i = 0; i < document.all.length; i++) {
  		  document.all(i).style.visibility = "hidden";
		}
		return true;
	}
// --></script>
<STYLE>
TABLE {
	table-layout: fixed;
}
</STYLE>
<LINK href="theme/prodord1.css<nested-ex:nocache-param />" rel="stylesheet" type="text/css">
<LINK href="theme/commonItemSize.css<nested-ex:nocache-param />" rel="stylesheet" type="text/css">

</head>

<BODY style="overflow-y:scroll;">
<bean:define id="leftForm" name="prsOrderLeftFrame" type="com.jds.prodord.order.prsorder.PrsOrderLeftFrame" scope="session" toScope="session"/>
<html:hidden property="command" name="command" value="java.lang.String"/>

<form name="prsOrderLeftForm">

<table style="width:410px" border="0"  cellpadding="0" cellspacing="0">
<logic:iterate name="prsOrderLeftFrame" id="formRow_L" indexId="index" property="formRowList_L" >
<tr>
		<td style="width:410px;height:70px;" align="center">
				<table style="text-align:left;" border="0" cellpadding="2" cellspacing="1" class="detailtable_back">
					<%com.jds.prodord.order.prsorder.PrsOrderLeftRow row = (com.jds.prodord.order.prsorder.PrsOrderLeftRow)formRow_L;
					 String styleClass = (row.getBasedRowFlg().equals("1"))? "details" : "details2";%>
					<tr class="<%= styleClass %>">
					  <td style="width:24px" align="center" valign="middle" rowspan="3" class="index">
				      	<%= (index.intValue() + 1)%>
				      </td>
				      <td style="width:109px;height:29px;" colspan="2">
				      	 <bean:write name="formRow_L" property="hjiHnb" ignore="true" />
				      	 <html:hidden name="formRow_L" property="kigBng" value="<%= row.getKigBng() %>" indexed="true"/>
				      </td>
				      <td style="width:131px">
				      	<bean:write name="formRow_L" property="artKj" ignore="true" />
				      </td>
				      <td style="width:56px" align="center">
				      	<bean:write name="formRow_L" property="hbiDte" ignore="true" />
				      </td>
				      <td style="width:56px" align="center" valign="middle">
				      	<bean:write name="formRow_L" property="kbn" ignore="true" />					    	
				      </td>
				</tr><tr class="<%= styleClass %>">
				      <td style="height:28px">
				      	 <bean:write name="formRow_L" property="ketCod" ignore="true" />　
					  </td>
					  <td>
				      	<bean:write name="formRow_L" property="ketNm" ignore="true" />
					  </td>
				      <td>
				      	<bean:write name="formRow_L" property="titKj" ignore="true" />　
				      </td>
				      <td>
				      	 <bean:write name="formRow_L" property="tomRak" ignore="true" />　
				      </td>
				      <td  align="right">
				      	 <bean:write name="formRow_L" property="zikTik" ignore="true" />　
				      </td>
				</tr>
				</table>
		</td></tr>
		<tr><td>　</td></tr>
</logic:iterate>
</table>

</form>

</body>
</html:html>
