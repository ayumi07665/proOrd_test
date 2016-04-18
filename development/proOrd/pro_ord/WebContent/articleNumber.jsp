<!DOCTYPE HTML>

<%@ page contentType="text/html; charset=Shift_JIS" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld"  prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-form.tld" prefix="form" %>
<%@ taglib uri="/WEB-INF/struts-nested-ex.tld" prefix="nested-ex" %>

<html:html>
<head>
<title><bean:message key="title.articlenumber" /></title>
<META http-equiv ="X-UA-Compatible" content ="IE=edge"/>
<META http-equiv="Pragma" content="no-cache">
<META http-equiv="Cache-Control" content="no-cache">
<META http-equiv="Expires" content="Thu, 01 Dec 1994 16:00:00 GMT">
<META http-equiv="Content-Type" content="text/html; charset=Shift_JIS">
<META name="GENERATOR" content="IBM WebSphere Studio">
<LINK rel="stylesheet" href="theme/prodord1.css<nested-ex:nocache-param />" type="text/css">
<LINK href="theme/commonItemSize.css<nested-ex:nocache-param />" rel="stylesheet" type="text/css">


<SCRIPT language="JavaScript">
<!--
//	実行ボタンが押されたら画面を一瞬消す（二重投稿対策）。
	function acceptSubmit(){
		for (var i = 0; i < window.document.all.length; i++) {
  		  document.all(i).style.visibility = "hidden";
		}
		return true;
	}
	function nextfocus(keta,mx,No){
		if(mx <= keta){
			document.getElementById('date'+ (No + 1)).focus();
			document.getElementById('date'+ (No + 1)).select();
		}
	}
-->


</SCRIPT>

</head>


<body>
<H3><bean:message key="title.articlenumber2" /></H3>
 
<div class="button_right" align="right">
<table border="0" cellspacing="0" cellpadding="2">
	<tr valign="top">
	<td>
		<html:form action="/GomasterMenu" target="_top">
			<html:submit property="submit" styleClass="button2" style="width:140px">
				<bean:message key="button.mastermenu" />
			</html:submit>
			<html:hidden property="pagingHelperType" value="com.jds.prodord.master.articlenumber.ArticleNumberPagingHelper" />
		</html:form>
	</td>

	<td>
		<html:form action="/Gomain" target="_top">
			<html:submit property="submit" styleClass="button2" style="width:115px">
				<bean:message key="button.mainmenu" />
			</html:submit>
			<html:hidden property="pagingHelperType" value="com.jds.prodord.master.articlenumber.ArticleNumberPagingHelper" />
		</html:form>
	</td>

	<td>
		<html:form action="/Logoff" target="_parent">
			<html:submit property="submit" styleClass="button2" style="width:64px">
				<bean:message key="button.logoff" />
			</html:submit>
		</html:form>
	</td>
	</tr>
</table>
</div>

<html:form action="/ArticleNumber" onsubmit="return acceptSubmit();">
<bean:define id="fmForm" name="ArticleNumberForm" type="com.jds.prodord.master.articlenumber.ArticleNumberForm"/>

<div style="position:absolute; left:10px; top:60px; width:400px; height:35px; z-index : 1;">
 <table border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td align="left" style="width:160px">
	      <table style="height:30px">
			<tr>
				<td style="width:100px" class="title_dark">
					<bean:message key="label.daikai"/>
				</td>
				<td style="width:62px" class="title_light">
					<table style="width:62px;height:24px;" cellspacing="2" cellpadding="2" class="details">
						<tr><td align="center">
							<bean:write name="fmForm" property="queryDaiKaiSkbCod" ignore="true"/>
						</td></tr>
					</table>
				</td>
			</tr>
		  </table>
	  </td>
<td style="width:15px"></td>
      <td align="left" style="width:200px">
	      <table style="height:30px">
			<tr>
				<td style="width:100px" class="title_dark">
					<bean:message key="label.dbnm"/>
				</td>
				<td style="width:95px" class="title_light">
					<table style="width:95px;height:24px;" cellspacing="2" cellpadding="2" class="details">
						<tr><td align="center">
							<bean:write name="fmForm" property="dbName" />
						</td></tr>
					</table>
				</td>
			</tr>
		  </table>
	  </td>

	</tr>
  </table>
</div>

<html:errors/>
<logic:present  name="SERVICE_MESSAGE">
  <DIV class="service_msg"><bean:write name="SERVICE_MESSAGE" /></DIV>
</logic:present>

<logic:present  name="INFOMSG_KEY">
  <DIV class="info_msg"><bean:write name="INFOMSG_KEY" /></DIV>
</logic:present>



<center>
<table border="0" cellspacing="8" cellpadding="0">
	<tr>
		<td style="width:100px;height:22px;" class="title_dark">処理区分</td>
		<td style="width:100px;height:22px;" class="title_dark"><bean:message key="label.kaiskb" /></td>
		<td style="width:100px;height:22px;" class="title_dark"><bean:message key="label.kigbng" /></td>
	</tr>
    <tr>
      <td align="center">
		<html:select property="prcKbn" value="<%=fmForm.getPrcKbn()%>">
			<html:option value="1"><bean:message key="button.search" /></html:option>
			<html:option value="2"><bean:message key="button.insert" /></html:option>
			<html:option value="3"><bean:message key="button.update" /></html:option>
			<html:option value="4"><bean:message key="button.delete" /></html:option>
		</html:select>
		</td>
		<td align="center">
			<html:select property="kaiSkbCod" size="1">
				<html:options property="vl_kaiSkbCod"/>
			</html:select>
		</td>	
		<td align="center">
			<html:text property="kigoBan" size="20" maxlength="13" />	
		</td>
	</tr>
</table>	
	
<br><HR><br>

<table border="0" cellspacing="0" cellpadding="2">
  <tr>
    <td align="center" style="width:250px" class="title_light"><bean:message key="label.artist" /></td>
    <td style="width:20px"></td>
    <td align="center" style="width:250px" class="title_light"><bean:message key="label.title" /></td>
  </tr>
  <tr>
    <td align="center" style="width:250px" class="details border_dark"><div style="font-size:12pt;"><html:text property="artist" maxlength="20" styleClass="s_45" /></div></td>
    <td style="width:20px"></td>
    <td align="center" style="width:250px" class="details border_dark"><div style="font-size:12pt;"><html:text property="title" maxlength="20" styleClass="s_45" /></div></td>
  </tr>
</table>

<br><br>

<table border="0" cellspacing="0">
  <tr>
    <td align="center" style="width:120px" class="title_light border_dark"><bean:message key="label.ketcod" /></td>
    <td style="width:20px"></td>
    <td align="center" style="width:120px" class="title_light border_dark"><bean:message key="label.hbidte" /></td>
  </tr>

  <tr>
	<td align="center" style="width:120px" class="details border_dark">
    	<div style="font-size:12pt;">
			<html:select property="ketCod" size="1">
				<html:options property="vl_ketCod"  labelProperty="lb_ketCod" />
			</html:select>
 	   </div>
	</td>

    <td style="width:20px"></td>

    <td align="center" style="width:120px" class="details border_dark">
        	<table border="0" cellspacing="0"><tr><td>
				<table bgcolor="#FFFFFF" style="width:100px;height:1px;" cellspacing="0" cellpadding="2">
					<tr><td class="details">
						<html:text property="year" styleClass="s_1" maxlength="2" styleId="date1" onkeypress="nextfocus(value.length,maxLength,1)" />
		                   /
						<html:text property="month" styleClass="s_1" maxlength="2" styleId="date2" onkeypress="nextfocus(value.length,maxLength,2)" />
		                   /
						<html:text property="day" styleClass="s_1" maxlength="2" styleId="date3" />
					</td></tr>
				</table>
				</td></tr>
			</table>
	</td>

  </tr>
  
</table>


<br><br>

<table border="0" cellspacing="0" cellpadding="2" cellpadding="2">
  <tr>
    <td align="center" style="width:150px" class="title_light border_dark"><bean:message key="label.prsmkrcod" /></td>
    <td style="width:20px"></td>
    <td align="center" style="width:150px" class="title_light border_dark"><bean:message key="label.jytprsmkr" /></td>
    <td style="width:20px"></td>
    <td align="center" style="width:150px" class="title_light border_dark"><bean:message key="label.fukhaccod" /></td>
  </tr>
  <tr>
    <td align="center" style="width:150px" class="details border_dark"><div style="font-size:12pt;"><html:text property="prsMkrCod" maxlength="6"  style="text-align: left" /></div></td>
    <td style="width:20px"></td>
    <td align="center" style="width:150px" class="details border_dark"><div style="font-size:12pt;"><html:text property="jytPrsMkr" maxlength="6"  style="text-align: left" /></div></td>
    <td style="width:20px"></td>
    <td align="center" style="width:150px" class="details border_dark"><div style="font-size:12pt;"><html:text property="hukSizCod" maxlength="6"  style="text-align: left" /></div></td>
  </tr>
</table>

<br><br><br>

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