<!DOCTYPE HTML>

<%@ page contentType="text/html; charset=Shift_JIS" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld"  prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-form.tld" prefix="form" %>
<%@ taglib uri="/WEB-INF/struts-nested-ex.tld" prefix="nested-ex" %>

<html:html>

<head>
<TITLE><bean:message key="title.hachusyosyryk"/></TITLE>
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
	
	function movefocus(i, n) {
    	i.form.elements[n].focus();
	}
	function nextfocus(keta,mx,No){
		if(mx <= keta){
			document.all['date'+ (No + 1)].focus();
			document.all['date'+ (No + 1)].select();
		}
	}

	function openNewWindow(){
		var newWindow = document.indicatePrintForm.newWindow.value;
		if(newWindow == "1"){
			var forwardNm = "<html:rewrite forward="printOrders"/>";
			wd = window.open(forwardNm,"_blank","scrollbars=1,menubar=1,width=800,height=500,resizable=1");
		}
		document.indicatePrintForm.newWindow.value = "0";
	}

   		

//-->

</SCRIPT>
<LINK rel="stylesheet" href="theme/prodord1.css<nested-ex:nocache-param />" type="text/css">
<LINK href="theme/commonItemSize.css<nested-ex:nocache-param />" rel="stylesheet" type="text/css">

</head>


<BODY onLoad="openNewWindow()">

<div id="Layer1" style="position:absolute; left:800px; top:15px; width:130px; height:60px; z-index : 1;">
	 <table><tr>
		  <td align="left">
             <html:form action="/Gomain" target="_parent">
				<html:submit styleClass="button2" property="submit" style="width:115px">
				<bean:message key="button.mainmenu"/>
				</html:submit>
				<html:hidden property="pagingHelperType" value="com.jds.prodord.indicate.indicateprint.IndicatePrintPagingHelper"/>
				</html:form>
		  </td>
          <td>
             <html:form action="/Logoff" target="_parent">
				<html:submit property="submit" styleClass="button2" style="width:64px">
				<bean:message key="button.logoff"/>
				</html:submit>
				</html:form>
     </tr>
     </table>
</div>

<html:form action="/indicatePrint" target="_self" onsubmit="return acceptSubmit();">

<bean:define id="fmForm" name="indicatePrintForm" type="com.jds.prodord.indicate.indicateprint.IndicatePrintForm"/>
<html:hidden property="newWindow" name="newWindow" value="<%=fmForm.getNewWindow()%>"/>

<table><tr>
      <td align="left">
        <table border="0"  cellpadding="1" cellspacing="0"><tr>
        <td style="width:90px" class="title_dark"><bean:message key="label.daikai"/></td><td></td>
        <td style="width:70px" class="title_light"> 
          <table style="width:70px;height:25px;" cellspacing="0" cellpadding="2"><tr>
           <td class="details" align="center">
           	  <bean:write name="fmForm" property="queryDaiKaiSkbCod" ignore="true"/></td>
          </tr></table>
        </td>
        </tr></table>
        </td>
</tr></table>

<br>

<logic:present  name="SERVICE_MESSAGE">
	<DIV class="service_msg"><bean:write name="SERVICE_MESSAGE" /></DIV>
</logic:present>
<logic:present  name="INFOMSG_KEY">
		<DIV class="info_msg"><bean:write name="INFOMSG_KEY" /></DIV>
</logic:present>
<html:errors/>

<br><br><br><hr><br><br><br>

<CENTER>
<table border="0" cellspacing="10"><tr><td style="width:55px">

	<table border="0" style="width:740px" align="left" cellspacing="8">

		<tr>

			<td style="width:78px;height:32px;" class="title_neutral">
				<bean:message key="label.shiteiJoken"/>
			</td>

			<td style="width:118px" class="title_dark">
				<bean:message key="label.hacskicod"/>
			</td>
			<td style="width:22px">

				<html:radio property="rb_select" value="true" tabindex="1"/>

			</td>
			<td style="width:52px" class="title_light">

				<bean:message key="radio.all"/>

			</td>
            <td style="width:22px">

				<html:radio property="rb_select" value="false" onclick="movefocus(this,'hacCod1')" tabindex="2"/>

			</td>
			<td style="width:52px" class="title_light">

				<bean:message key="radio.choice"/>

			</td>
            <td style="width:218px" align="left">
            
				<html:text property="hacCod1" styleClass="s_10" maxlength="6" tabindex="3"/>

				<html:text property="hacCod2" styleClass="s_10" maxlength="6" tabindex="4"/>

				<html:text property="hacCod3" styleClass="s_10" maxlength="6" tabindex="5"/>
			<td style="width:20px"></td>
			<td>	
				<html:submit property="command" value="伝票発行" styleClass="button" onclick="return confirm('伝票発行します。よろしいですか？');" tabindex="6" />
			</td>

		</tr>
		<tr><td></td><td colspan="8" style="height:80px">
			<div style="border: 1px inset;border-color:lightGray;"></div>
		</td></tr>
   </table>

</td></tr></table>
</CENTER>

<CENTER>
<table style="width:700px" cellspacing="3"><tr align="center">
	<td class="title_light" style="width:257px;height:25px;"><span style="text-align:right;"><bean:message key="label.hacsyodte"/><bean:message key="label.fromto"/></SPAN></td>
	<td class="title_light" style="width:102px"><bean:message key="label.hacskicod"/></td>
	<td class="title_light" style="width:205px"><span style="text-align:right;"><bean:message key="label.hacsyobng"/><bean:message key="label.fromto"/></span></td>
	<td colspan="2"></td>	
  </tr>
  <tr align="center"><td>
	<table border="0" cellspacing="0"><tr><td bgcolor="lightGray">
       <table bgcolor="#FFFFFF" style="width:230px;height:1px;" cellspacing="0" cellpadding="0">
		<tr align="center">
	       <td style="width:230px;height:23px;" class="details">
		   <html:text property="hacSyoDte_Frm_Y" styleClass="s_1" maxlength="2" tabindex="7" styleId="date1" onkeypress="nextfocus(value.length,maxLength,1)"/>
	        /
	       <html:text property="hacSyoDte_Frm_M" styleClass="s_1" maxlength="2" tabindex="8" styleId="date2" onkeypress="nextfocus(value.length,maxLength,2)"/>
	        /
	       <html:text property="hacSyoDte_Frm_D" styleClass="s_1" maxlength="2" tabindex="9" styleId="date3" onkeypress="nextfocus(value.length,maxLength,3)"/>
	       　～　
	       <html:text property="hacSyoDte_to_Y" styleClass="s_1" maxlength="2" tabindex="10" styleId="date4" onkeypress="nextfocus(value.length,maxLength,4)"/>
	        /
	       <html:text property="hacSyoDte_to_M" styleClass="s_1" maxlength="2" tabindex="11" styleId="date5" onkeypress="nextfocus(value.length,maxLength,5)"/>
	        /
	       <html:text property="hacSyoDte_to_D" styleClass="s_1" maxlength="2" tabindex="12" styleId="date6"/>
	       </td>
        </tr></table>
      </tr></table>
	</td>

	<td>
	   <html:text property="hacCod" styleClass="s_10" maxlength="6" tabindex="13"/>
	</td>

	<td>
	<table border="0" cellspacing="0"><tr><td bgcolor="lightGray">
       <table bgcolor="#FFFFFF" style="width:190px;height:1px;" cellspacing="0" cellpadding="0">		
		<tr align="center">
			<td>
			<table cellspacing="0"><tr><td style="height:23px" class="details">
			   <html:text property="hacSyoBng_frm" styleClass="s_11" maxlength="8" tabindex="14"/>
			   　～　
			   <html:text property="hacSyoBng_to" styleClass="s_11" maxlength="8" tabindex="15"/>
			</td></tr></table>
			</td>
		</tr></table>
	</tr></table>
	</td>

	<td style="width:50px"></td>
	<td>
		<html:submit property="command" value="再出力" styleClass="button" onclick="return confirm('再出力します。よろしいですか？');" tabindex="16" />
	</td>
  </tr>
</table>
</CENTER>

<br><br><br><br><br><br><hr>
<div style="position:relative; text-align: right; margin-top:40px; margin-right:10px;">
	<html:submit property="command" value="クリア" styleClass="button" style="width:70px" tabindex="17"/>　
</div>
</html:form>

</BODY>



</html:html>

