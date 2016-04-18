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
	window.onscroll = function pageMove(){
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
<LINK rel="stylesheet" href="theme/prodord1.css<nested-ex:nocache-param />" type="text/css">
<LINK href="theme/commonItemSize.css<nested-ex:nocache-param />" rel="stylesheet" type="text/css">
</head>

<BODY style="overflow-y:scroll;">
<bean:define id="leftForm" name="ikkatsuReferLeftFrame" type="com.jds.prodord.reference.ikkatsurefer.IkkatsuReferLeftFrame" scope="session" toScope="session"/>
<html:hidden property="command" name="command" value="java.lang.String"/>

<table style="width:575px" border="0"  cellpadding="0" cellspacing="0">
<logic:iterate name="ikkatsuReferLeftFrame" id="formRow_L" indexId="index" property="formRowList_L" >
<tr>
			<td style="width:294px;height:90px;" align="center">
				<table border="0" cellpadding="2" cellspacing="1" class="detailtable_back"><tr class="details">
					  <td style="width:21px;height:24px;" align="center" valign="middle" rowspan="3" class="index">
				      	<%= (index.intValue() + 1) %>
				      </td>
				      <td style="width:132px" align="left">
				      	<bean:write name="formRow_L" property="hjiHnb" ignore="true" />
				      </td>
				      <td style="width:30px" align="left">
				      	<bean:write name="formRow_L" property="ketCod" ignore="true" />
					  </td>
					  <td style="width:61px" align="left">
				      	<bean:write name="formRow_L" property="ketNm" ignore="true" />
					  </td>
				      <td style="width:18px" align="center" valign="middle" rowspan="3">
				      	<%com.jds.prodord.reference.ikkatsurefer.IkkatsuReferLeftRow row = (com.jds.prodord.reference.ikkatsurefer.IkkatsuReferLeftRow)formRow_L;
					 	  String styleClass = "";
					 	  if(row.getHacZmiSgn().equals(com.jds.prodord.reference.ikkatsurefer.IkkatsuReferLeftFrame.HACZMI))
					 	  	styleClass = "haczmi";
					 	  else if(row.getHacZmiSgn().equals(com.jds.prodord.reference.ikkatsurefer.IkkatsuReferLeftFrame.SYRZMI))
					 	  	styleClass = "syrzmi";
					 	  else if(row.getHacZmiSgn().equals(com.jds.prodord.reference.ikkatsurefer.IkkatsuReferLeftFrame.MIHACHU))
					 	  	styleClass = "index";
					 	%>
					 	<span class="<%= styleClass %>">
					      <bean:write name="formRow_L" property="hacZmiSgn" ignore="true" />
					    </span>		    	
				      </td>
				</tr><tr class="details">
				      <td style="height:24px" align="left">
				      	<bean:write name="formRow_L" property="artKj" ignore="true" />
				      </td>
				      <td colspan="2" align="left">
				      	<bean:write name="formRow_L" property="hbiDte" ignore="true" />
				      </td>
				</tr><tr class="details">
				      <td style="height:24px" align="left">
				      	<bean:write name="formRow_L" property="titKj" ignore="true" />
				      </td>
				      <td align="left">
				      	<bean:write name="formRow_L" property="tomRak" ignore="true" />
				      </td>
				      <td align="right">
				      	<bean:write name="formRow_L" property="zikTik" ignore="true" />
				      </td>
				</tr></table>
			</td>
			<td style="width:6px"></td>
			<td style="width:127px">
				<table border="0" cellpadding="2" cellspacing="1" class="detailtable_back"><tr class="details">
				      <td style="width:56px;height:24px;" align="right">
						<bean:write name="formRow_L" property="aziSuu" ignore="true" />
				      </td>
				      <td style="width:56px" align="right">
				      	<bean:write name="formRow_L" property="jucZan" ignore="true" />
				      </td>
				</tr><tr class="details">
				      <td align="right" style="height:24px">
				      	<bean:write name="formRow_L" property="tnaSekZan" ignore="true" />
				      </td>
				      <td align="right">
				      	<bean:write name="formRow_L" property="mhikSuu" ignore="true" />
				      </td>
				</tr><tr class="details">
				      <td align="right" style="height:24px">
				      	<bean:write name="formRow_L" property="zaiNisuu" ignore="true" />
				      </td>
				      <td align="right">
				      	<bean:write name="formRow_L" property="rsvSum" ignore="true" />
				      </td>
				</tr></table>
			</td>
			<td style="width:6px"></td>
			<td style="width:73px">
				<table border="0" cellpadding="2" cellspacing="1" class="detailtable_back"><tr class="details">
				      <td align="right" style="height:24px">
				      	<bean:write name="formRow_L" property="teianSuu" ignore="true" />
				      </td>
				</tr><tr class="details">
				      <td align="right" style="height:24px">
				      	<bean:write name="formRow_L" property="fskZaiSuu" ignore="true" />
				      </td>
				</tr><tr class="details">
				      <td align="right" style="height:24px">
				      	<bean:write name="formRow_L" property="avgUri" ignore="true" />
				      </td>
				</tr></table>
			</td>
			<td style="width:6px"></td>
			<td style="width:69px">
				<table border="0" cellpadding="2" cellspacing="1" class="detailtable_back"><tr class="details">
				      <td align="right" style="height:24px">
				      	<bean:write name="formRow_L" property="fukZaiSuu" ignore="true" />
				      </td>
				</tr><tr class="details">
				      <td align="right" style="height:24px">
				      	<bean:write name="formRow_L" property="fukHacSuu1" ignore="true" />
				      </td>
				</tr><tr class="details">
				      <td align="right" style="height:24px">
				      	<bean:write name="formRow_L" property="fukNyoKei" ignore="true" />
				      </td>
				</tr></table>
			</td>
		</tr>
		<tr><td>　</td></tr>
</logic:iterate>
</table>


</body>
</html:html>
