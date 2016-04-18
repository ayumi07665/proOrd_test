<!DOCTYPE HTML>
<%@ page contentType="text/html; charset=Shift_JIS" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld"  prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-form.tld" prefix="form" %>
<%@ taglib uri="/WEB-INF/struts-nested-ex.tld" prefix="nested-ex" %>

<html:html>
<HEAD>
<TITLE><bean:message key="title.fzkHinKsi"/></TITLE>
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
	
	function formSubmit(){
		return true;
	}

	function checkUpdatable(){
		var doc_input = document.getElementsByTagName("input")
		var kbn = document.fzkHinKsiForm.prcKbn.value;
		if(kbn == 4)
			for (var idx = 2; idx < (doc_input.length-7); idx++ ) {
				doc_input.item(idx).disabled = true;
			}
		else
			for (var idx = 1; idx < (doc_input.length-6); idx++ ) {
				doc_input.item(idx).disabled = false;
			}				
	}

	function setPrcKbn(i){
		if(i.form.prcKbn.value != "1")
			i.form.prcKbn.value = "1";
		else
			return;
	}
	
//-->

</SCRIPT>
<LINK href="theme/prodord1.css<nested-ex:nocache-param />" rel="stylesheet" type="text/css">
<LINK href="theme/commonItemSize.css<nested-ex:nocache-param />" rel="stylesheet" type="text/css">
</HEAD>
<!--<BODY onLoad="checkUpdatable()">-->
<BODY>
<html:form action = "/fzkHinKsi" target="_self" onsubmit="return acceptSubmit();">
<bean:define id="fmForm" name="fzkHinKsiForm" type="com.jds.prodord.master.fzkhinksi.FzkHinKsiForm"/>

<html:hidden property="updatable" name="updatable" value="<%=fmForm.getUpdatable()%>"/>

<H3 style="position:relative;top:-6px;margin-bottom:3px;"><bean:message key="title.fzkHinKsi2"/></H3>
	<table style="height:27px">
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
<hr>
<div style="padding-bottom:3px;">
<logic:present  name="INFOMSG_KEY">
	<DIV class="info_msg"><bean:write name="INFOMSG_KEY" /></DIV>
</logic:present>
<logic:present  name="SERVICE_MESSAGE">
	<DIV class="service_msg"><bean:write name="SERVICE_MESSAGE" /></DIV>
</logic:present>
<html:errors/>
</div>
	<CENTER>
	<table align="center" cellspacing="3" style="width:800px">
		<tr>
          <td  align="center" class="title_dark" style="width:70px;height:18px"><bean:message key="label.syorikbn"/></td>
          <td  align="center" class="title_dark" style="width:101px;height:18px"><bean:message key="label.kaiskb"/></td>
		  <td  align="center" class="title_dark" style="width:124px;height:18px"><bean:message key="label.hinban" /></td>
		  <td  align="center" class="title_dark" style="width:233px;height:18px"><bean:message key="label.title" /></td>
		  <td  align="center" class="title_dark" style="width:78px;height:18px"><bean:message key="label.hbidte" /></td>
          <td  align="center" class="title_dark" style="width:116px;height:18px"><bean:message key="label.ketnm"/></td>
          <td  align="center" class="title_dark" style="width:54px;height:18px"><bean:message key="label.setsuu"/></td>
        </tr>
		<tr><td align="center">
		    <html:select property="prcKbn" value="<%=fmForm.getPrcKbn()%>" onchange="checkUpdatable()">
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
            <td >
				<html:text property="hinban" style="text-align: left" styleClass="s_20" maxlength="13" onchange="setPrcKbn(this)"/>
            </td>
            <td style="height:25px" class="details border_light">
				<bean:write name="fmForm" property="title" ignore="true" />　
	        </td>
            <td style="height:25px" class="details border_light">
				<bean:write name="fmForm" property="hbidte" ignore="true" />　
	        </td>
            <td style="height:25px" class="details border_light">
				<bean:write name="fmForm" property="ketCod" ignore="true" />　<bean:write name="fmForm" property="ketNm" ignore="true" />　
	        </td>
            <td style="height:25px" class="details border_light" align="right">
				<bean:write name="fmForm" property="setsuu" ignore="true" />　
	        </td>
		</tr>
	</table>
<BR>	
<div class="center">
	<table cellspacing="0" cellpadding="0" align="center">
		<tr>
			<td>
			<div id="title" style="width:622px;overflow-y:scroll;">
				<table border="1" cellspacing="1" cellpadding="0" style="width:600px;border-bottom-style:none;margin-top:-2px;">
					<tr><td>
					<table border="0" bgcolor="#336666" cellspacing="1" cellpadding="1" style="width:594px;border-bottom-style:none;">
				     <tr class="title_light">
						<td class="title_light" style="width:81px;height:15px;" ><bean:message key="label.fukszicod"/></td>
						<td class="title_light" style="width:210px" ><bean:message key="label.fukszinm"/></td>
						<td class="title_light" style="width:99px" ><bean:message key="label.sirhaccod"/></td>
						<td class="title_light" style="width:163px" ><bean:message key="label.hacskinm"/></td>
						<td class="title_light" style="width:34px" ><bean:message key="label.kosei"/></td>
					</tr>
					</table>
					</td></tr>
				</table>
			</div>
			<div id="detail" style="width:622px;height:271px;overflow-y:scroll;">
				<table border="1" cellspacing="1" cellpadding="0" style="width:600px;margin-top:-3px;">
					<tr><td>
					<table border="0" bgcolor="#336666" cellspacing="1" cellpadding="1" style="width:594px;border-bottom-style:none;">
					<logic:iterate name="fzkHinKsiForm" id="formRow" indexId="index1"  property="formRowList"  type="com.jds.prodord.master.fzkhinksi.FzkHinKsiFormRow"> 
					<tr class="title_light">
				        <td class="details" style="width:80px;height:26px;">
       	                    <html:text name="formRow" property="fuksziCod" style="text-align: left" styleClass="s_6" maxlength="3" indexed="true"/>
				        </td>
			            <td class="details" style="width:210px;height:26px;text-align: left"  >
							<bean:write name="formRow" property="fuksziNm" ignore="true" />
				        </td>
			            <td class="details" style="width:98px;height:26px;">
							<html:text name="formRow" property="sirSk"  style="text-align: left" styleClass="s_8" maxlength="6" indexed="true"/>
				        </td>
				        <td class="details" style="width:163px;height:26px;text-align: left">
							<bean:write name="formRow" property="hacNm" ignore="true"/>
				        </td>
				        <td class="details" style="width:34px;height:26px;">
				            <html:checkbox name="formRow" property="check_bx" value="true" indexed="true" />
				        </td>
				    </tr>
					</logic:iterate>
					</table>
					</td></tr>
				</table>
			</div>
			</td>
		</tr>
	</table>
</div><BR>
	<table>
    <tr valign="bottom">
    	<td align="left" style="width:100px" class="index">
    		<bean:message key="label.zen"/>
			<bean:write name="fzkHinKsiForm" property="count" ignore="true" />
			<bean:message key="label.ken"/>
    	</td>
		<td align="center" valign="top">
		<html:submit property="command" value="実行" styleClass="button" style="width : 75px;height:25px;" onclick="return formSubmit()"/>
		</td>
		<td style="width:10px"></td>
		<td align="center" valign="top">
			<html:submit property="command" value="クリア" styleClass="button" style="width : 75px;height:25px;"/>
		</td>
		<td style="width:100px"></td> 
	</tr>
	</table>
</CENTER>
</html:form>
<div id="Layer2" style="position:absolute; left : 650px; top : 45px; width : 237px; height : 25px; z-index : 3;">
<table align="left">
	<tr valign="top">
		<td align="left">
			<html:form action="/GomasterMenu" target="_top">
				<html:submit property="submit" styleClass="button2" style="width:140px">
					<bean:message key="button.mastermenu" />
				</html:submit>
				<html:hidden property="pagingHelperType" value="com.jds.prodord.master.fzkhinksi.FzkHinKsiPagingHelper"/>
			</html:form>
		</td>
		<td align="left">

				<html:form action="/Gomain" target="_parent">

				<html:submit styleClass="button2" property="submit" style="width:115px">

				<bean:message key="button.mainmenu"/>

				</html:submit>

				<html:hidden property="pagingHelperType" value="com.jds.prodord.master.fzkhinksi.FzkHinKsiPagingHelper"/>

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
</BODY>
</html:html>
