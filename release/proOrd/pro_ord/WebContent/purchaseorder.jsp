<!DOCTYPE HTML>

<%@ page contentType="text/html; charset=Shift_JIS" %>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld"  prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-form.tld" prefix="form" %>
<%@ taglib uri="/WEB-INF/struts-nested-ex.tld" prefix="nested-ex" %>

<html:html>
<head>

<title><bean:message key="title.perchaseorder" /></title>
<meta http-equiv="Content-Type" content="text/html; charset=Shift_JIS">
<META http-equiv ="X-UA-Compatible" content ="IE=edge"/>
<META http-equiv="Pragma" content="no-cache">
<META http-equiv="Cache-Control" content="no-cache">
<META http-equiv="Expires" content="Thu, 01 Dec 1994 16:00:00 GMT">
<META name="GENERATOR" content="IBM WebSphere Studio">
<LINK rel="stylesheet" href="theme/prodord1.css<nested-ex:nocache-param />" type="text/css">
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
		document.PurchaseOrderForm.hattyuCod.focus();
	}

-->

</SCRIPT>

</head>

<body onLoad="firstfocus()">


<H3><bean:message key="title.perchaseorder2" /></H3>

<div class="button_right" align="right">
<table border="0" cellspacing="0" cellpadding="2">
	<tr valign="top">
	<td>
		<html:form action="/GomasterMenu" target="_top">
			<html:submit property="submit" styleClass="button2" style="width:140px">
				<bean:message key="button.mastermenu" />
			</html:submit>
			<html:hidden property="pagingHelperType" value="com.jds.prodord.master.purchaseorder.PurchaseOrderPagingHelper"/>
		</html:form>
	</td>

	<td>
		<html:form action="/Gomain" target="_top">
			<html:submit property="submit" styleClass="button2" style="width:115px">
				<bean:message key="button.mainmenu" />
			</html:submit>
		<html:hidden property="pagingHelperType" value="com.jds.prodord.master.purchaseorder.PurchaseOrderPagingHelper" />
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


	<html:errors/>
	<logic:present  name="SERVICE_MESSAGE">
	  <DIV class="service_msg"><bean:write name="SERVICE_MESSAGE"/></DIV>
	</logic:present>

	<logic:present  name="INFOMSG_KEY">
	  <DIV class="info_msg"><bean:write name="INFOMSG_KEY" /></DIV>
	</logic:present>



<html:form action="/PurchaseOrder" onsubmit="return acceptSubmit();">
<bean:define id="fmForm" name="PurchaseOrderForm" type="com.jds.prodord.master.purchaseorder.PurchaseOrderForm"/>


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
						<bean:write name="fmForm" property="daiKaiSkbCod" ignore="true"/>
					</td></tr></table>
				</td>
			</tr>
		  </table>
	  </td>
	</tr>
  </table>
</div>

  <table border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td>
      	<table style="height:30px">
			<tr>
			<td style="width:110px" class="title_dark">
			   <bean:message key="label.hacskicod"/>
			</td>
			<td class="title_light">

				<table style="width:100px" border="0" cellspacing="0" cellpadding="2">
		            <tr bgcolor="#FFFFFF">
						<td valign="middle" align="center"></td>
						<td style="width:100px" align="left"><html:text property="hattyuCod" styleClass="s_14" maxlength="6"/></td>                
		          	</tr>
	            </table>
        	</td>
			</tr>
		</table>
      </td>
    </tr>
  </table>


<br><HR>
<br><br>

<center>

<table border="0" cellspacing="0">
  <tr>
    <td align="center" style="width:280px" class="title_light border_dark"><bean:message key="label.hacskinm"/></td>
    <td style="width:20px"></td>
    <td align="center" style="width:280px" class="title_light border_dark">-</td>
  </tr>
  <tr>
    <td align="center" style="width:280px;height:18px;" class="details border_dark"><div style="font-size:12pt;"><bean:write property="orderName1" name="fmForm"/></div></td>
    <td style="width:20px"></td>
    <td style="width:280px;height:18px;" class="details border_dark"><div style="font-size:12pt;"><bean:write property="orderName2" name="fmForm"/></div></td>
  </tr>
</table>

<br><br><br>

<table border="0" cellspacing="0">
  <tr>
    <td align="center" style="width:280px" class="title_light border_dark"><bean:message key="label.hacski"/><bean:message key="label.adr"/></td>
    <td style="width:20px"></td>
    <td align="center" style="width:280px" class="title_light border_dark">-</td>
  </tr>
  <tr>
    <td align="center" style="width:280px;height:18px;" class="details border_dark"><div style="font-size:12pt;"><bean:write property="orderAdr1" name="fmForm"/></div></td>
    <td style="width:20px"></td>
    <td style="width:280px;height:18px;" class="details border_dark"><div style="font-size:12pt;"><bean:write property="orderAdr2" name="fmForm"/>　</div></td>
  </tr>
</table>

<br><br><br>


<table border="0" cellspacing="0">
  <tr>
    <td align="center" style="width:140px" class="title_light border_dark"><bean:message key="label.telno"/></td>
    <td style="width:20px"></td>
    <td align="center" style="width:140px" class="title_light border_dark"><bean:message key="label.ybnno"/></td>
  </tr>
  <tr>
    <td align="center" style="width:140px;height:18px;" class="details border_dark"><div style="font-size:12pt;"><bean:write property="telNum" name="fmForm"/></div></td>
    <td style="width:20px"></td>
    <td align="center" style="width:140px;height:18px;" class="details border_dark"><div style="font-size:12pt;"><bean:write property="postNum" name="fmForm"/></div></td>
  </tr>
</table>
  
<br><br>
<hr><br>

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

</body>
</html:html>