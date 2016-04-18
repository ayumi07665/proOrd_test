<!DOCTYPE HTML>
<%@ page contentType="text/html; charset=Shift_JIS" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld"  prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-form.tld" prefix="form" %>
<%@ taglib uri="/WEB-INF/struts-nested-ex.tld" prefix="nested-ex" %>

<html:html>
<HEAD>
<TITLE><bean:message key="title.nyukosuuadjust"/></TITLE>
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
	function nextfocus(keta,mx,No){
		if(mx <= keta){
			document.all['date'+ (No + 1)].focus();
			document.all['date'+ (No + 1)].select();
		}
	}

	function formSubmit(){
/*		var kbn = document.nyukoSuuAdjustForm.prcKbn.value;
		
		if(kbn == "2"){
			return confirm('完納サインを立てます。よろしいですか？');
		}
*/		return true;
	}
	function checkUpdatable(){
		var doc_select = document.getElementsByTagName("select")
		var kbn = document.nyukoSuuAdjustForm.updatable.value;
		var b = (kbn == "true")? false : true;
		for (var idx = 1; idx < doc_select.length; idx++ ) {
			doc_select.item(idx).disabled = b;
		}
		if(b == true)
			document.nyukoSuuAdjustForm.prcKbn.value = 1;	
	}
	function setPrcKbn(i){
		if(i.form.prcKbn.value == "2")
			return;
		if(i.value == "1" || i.value == "2")
			i.form.prcKbn.value = "2";
	}
	
//-->

</SCRIPT>
<LINK href="theme/prodord1.css<nested-ex:nocache-param />" rel="stylesheet" type="text/css">
<LINK href="theme/commonItemSize.css<nested-ex:nocache-param />" rel="stylesheet" type="text/css">
</HEAD>
<BODY onLoad="checkUpdatable()">
<html:form action = "/nyukoSuuAdjust" target="_self" onsubmit="return acceptSubmit();">
<bean:define id="fmForm" name="nyukoSuuAdjustForm" type="com.jds.prodord.master.nyukosuuadjust.NyukoSuuAdjustForm"/>

<html:hidden property="updatable" name="updatable" value="<%=fmForm.getUpdatable()%>"/>

<H3 style="position:relative;top:-6px;margin-bottom:3px;"><bean:message key="title.nyukosuuadjust2"/></H3>
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
	<table align="center" cellspacing="3" style="width:710px">
		<tr>
          <td class="title_dark" style="width:70px;height:22px;"><bean:message key="label.syorikbn"/></td>
          <td class="title_dark" style="width:100px"><bean:message key="label.hacskicod"/></td>
          <td class="title_dark"><bean:message key="label.hacdte"/>　<bean:message key="label.fromto"/></td>
          <td class="title_dark"><bean:message key="label.nouki"/>　<bean:message key="label.fromto"/></td>
          <td></td>
        </tr>
		<tr><td align="center">
		    <html:select property="prcKbn" value="<%=fmForm.getPrcKbn()%>">
		   			<html:option value="1"><bean:message key="button.search"/></html:option>
					<html:option value="2"><bean:message key="button.update"/></html:option>
			</html:select>

		</td>
		<td align="center">
			<html:text property="hacCod_H" styleClass="s_10" maxlength="6"  />
		</td>

		<td align="center">
		    <html:text  property="hacDteFrm_H.year" styleClass="s_1" maxlength="2" styleId="date1" onkeypress="nextfocus(value.length,maxLength,1)"/>
		    /
		    <html:text  property="hacDteFrm_H.month" styleClass="s_1" maxlength="2" styleId="date2" onkeypress="nextfocus(value.length,maxLength,2)"/>
		    /
			<html:text  property="hacDteFrm_H.day" styleClass="s_1" maxlength="2" styleId="date3" onkeypress="nextfocus(value.length,maxLength,3)"/>
			　～　
			<html:text  property="hacDteTo_H.year" styleClass="s_1" maxlength="2" styleId="date4" onkeypress="nextfocus(value.length,maxLength,4)"/>
		    /
		    <html:text  property="hacDteTo_H.month" styleClass="s_1" maxlength="2" styleId="date5" onkeypress="nextfocus(value.length,maxLength,5)"/>
		    /
			<html:text  property="hacDteTo_H.day" styleClass="s_1" maxlength="2" styleId="date6" onkeypress="nextfocus(value.length,maxLength,6)"/>
		</td>
		<td align="center">
		    <html:text  property="nkiFrm_H.year" styleClass="s_1" maxlength="2" styleId="date7" onkeypress="nextfocus(value.length,maxLength,7)"/>
		    /
		    <html:text  property="nkiFrm_H.month" styleClass="s_1" maxlength="2" styleId="date8" onkeypress="nextfocus(value.length,maxLength,8)"/>
		    /
			<html:text  property="nkiFrm_H.day" styleClass="s_1" maxlength="2" styleId="date9" onkeypress="nextfocus(value.length,maxLength,9)"/>
			　～　
			<html:text  property="nkiTo_H.year" styleClass="s_1" maxlength="2" styleId="date10" onkeypress="nextfocus(value.length,maxLength,10)"/>
		    /
		    <html:text  property="nkiTo_H.month" styleClass="s_1" maxlength="2" styleId="date11" onkeypress="nextfocus(value.length,maxLength,11)"/>
		    /
			<html:text  property="nkiTo_H.day" styleClass="s_1" maxlength="2" styleId="date12"/>
		</td>
		<td>
		   	
		</td>
		</tr>
		<tr>
	</table>
	<table style="width:890px;position:relative;top:-7px;"><tr>
			<td style="width:35px">　
			</td>
			<td class="title_dark" style="width:80px">
				<bean:message key="label.kigbng"/>
			</td><td>
				<html:text property="kigBng1_H" styleClass="s_18" maxlength="13"/>
			</td><td>
				<html:text property="kigBng2_H" styleClass="s_18" maxlength="13"/>
			</td><td>
				<html:text property="kigBng3_H" styleClass="s_18" maxlength="13"/>
			</td><td>
				<html:text property="kigBng4_H" styleClass="s_18" maxlength="13"/>
			</td><td>
				<html:text property="kigBng5_H" styleClass="s_18" maxlength="13"/>
			</td>
			<td rowspan="2">
				<html:textarea property="cpPaste" cols="15" rows="2"/>
			</td>
			<td rowspan="2">
				<html:submit property="command" value="貼り付け" styleClass="button" style="height:20px;"/>
			</td>
		</tr><tr>
			<td colspan="2"></td><td>
				<html:text property="kigBng6_H" styleClass="s_18" maxlength="13"/>				
			</td><td>
				<html:text property="kigBng7_H" styleClass="s_18" maxlength="13"/>
			</td><td>
				<html:text property="kigBng8_H" styleClass="s_18" maxlength="13"/>
			</td><td>
				<html:text property="kigBng9_H" styleClass="s_18" maxlength="13"/>
			</td><td>
				<html:text property="kigBng10_H" styleClass="s_18" maxlength="13"/>
			</td>
		</tr>
	</table>

<div id="header" style="height:26px;width:930px;overflow-y:scroll;border-style:none;padding:0px;margin-top:0px;">
  <table border="1"  cellspacing="1" cellpadding="0">
    <tr><td> 

	<table border="0" cellspacing="1" cellpadding="1" style="width:905px;text-align:center;background-color:#336666;">
		<colgroup>
			<col style="width:25px;"></col>
            <col style="width:80px"></col>
            <col style="width:116px"></col>
            <col style="width:80px"></col>
            <col style="width:80px"></col>
            <col style="width:71px"></col>
            <col style="width:71px"></col>
            <col style="width:80px"></col> 
            <col style="width:62px"></col> 
        	<col style="width:62px"></col>
        	<col style="width:162px"></col>
		</colgroup>
        <tr class="title_light">
        	<td style="height:18px;"></td>
            <td><bean:message key="label.hacskicod"/></td>
            <td><bean:message key="label.kigbng"/></td>
            <td><bean:message key="label.hacdte"/></td>
            <td><bean:message key="label.hacsyobng"/></td>
            <td><bean:message key="label.gyobn"/></td>
            <td><bean:message key="label.kbn"/></td>
            <td><bean:message key="label.nouki"/></td> 
            <td><bean:message key="label.hacsuu"/></td> 
        	<td><bean:message key="label.nyosuu"/></td>
        	<td><bean:message key="label.knusgn"/></td>
        </tr>
    </table>
    
    </td></tr></table>
</div>

<div id="detail" style="width:930px;height:330px;overflow-y:scroll;border-style:none;padding:0px;">

  <table border="1" cellspacing="1" cellpadding="0" style="margin-top:-4px;">
    <tr><td>
    
	<table border="0" cellspacing="1" cellpadding="1" style="width:905px;background-color:#336666;text-align:center">
		<colgroup>
			<col style="width:25px;"></col>
            <col style="width:80px"></col>
            <col style="width:116px"></col>
            <col style="width:80px"></col>
            <col style="width:80px"></col>
            <col style="width:71px"></col>
            <col style="width:71px"></col>
            <col style="width:80px"></col> 
            <col style="width:62px"></col> 
        	<col style="width:62px"></col>
        	<col style="width:162px"></col>
		</colgroup>
        <logic:iterate name="nyukoSuuAdjustForm" id="formRow" indexId="index"  property="formRowList" type="com.jds.prodord.master.nyukosuuadjust.NyukoSuuAdjustFormRow">
        <tr class="details">
        	<td style="height:22px" align="center" valign="middle" class="index">
				<%= (index.intValue() + 1) %>
			</td>
            <td>
				<bean:write name="formRow" property="hacCod" ignore="true" />
            </td>
            <td>
				<bean:write name="formRow" property="kigBng" ignore="true" />
            </td>
            <td>
				<bean:write name="formRow" property="hacSyoDte" ignore="true" />
            </td>
            <td>
				<bean:write name="formRow" property="hacSyoBng" ignore="true" />
            </td>
            <td>
				<bean:write name="formRow" property="gyoBng" ignore="true" />
            </td>
            <td>
				<bean:write name="formRow" property="kbn" ignore="true" />
            </td>
            <td>
				<bean:write name="formRow" property="nki" ignore="true" />
            </td>
            <td align="right">
				<bean:write name="formRow" property="hacSuu" ignore="true" />
            </td>
            <td align="right">
				<bean:write name="formRow" property="nyoSuu" ignore="true" />
			</td>
            <td align="center">
				<html:select name="formRow" property="knuSgn" indexed="true" onchange="setPrcKbn(this)">
					<html:option value=""></html:option>
					<html:option value="1"><bean:message key="label.knu"/></html:option>
					<html:option value="2"><bean:message key="label.nyosuuknu"/></html:option>
				</html:select>
            </td>
        </tr>
		</logic:iterate>
    </table>
    </td></tr>
  </table>
  
</div><br>
	<table>
    <tr valign="bottom">
    	<td align="left" style="width:100px" class="index">
    		<bean:message key="label.zen"/>
			<bean:write name="nyukoSuuAdjustForm" property="count" ignore="true" />
			<bean:message key="label.ken"/>
    	</td>
		<td align="center" valign="top">
		<html:submit property="command" value="実行" styleClass="button" style="width : 75px" onclick="return formSubmit()"/>
		</td>
		<td style="width:10px"></td>
		<td align="center" valign="top">
			<html:submit property="command" value="クリア" styleClass="button" style="width : 75px"/>
		</td>
		<td style="width:100px"></td>
	</tr>
	</table>
</CENTER>
		 
</html:form>
<div id="Layer2" style="position:absolute; left : 650px; top : 45px; width : 237px; height : 30px; z-index : 3;">
<table align="left">
	<tr valign="top">
		<td align="left">
			<html:form action="/GomasterMenu" target="_top">
				<html:submit property="submit" styleClass="button2" style="width:140px">
					<bean:message key="button.mastermenu" />
				</html:submit>
				<html:hidden property="pagingHelperType" value="com.jds.prodord.master.nyukosuuadjust.NyukoSuuAdjustPagingHelper"/>
			</html:form>
		</td>
		<td align="left">

				<html:form action="/Gomain" target="_parent">

				<html:submit styleClass="button2" property="submit" style="width:115px">

				<bean:message key="button.mainmenu"/>

				</html:submit>

				<html:hidden property="pagingHelperType" value="com.jds.prodord.master.nyukosuuadjust.NyukoSuuAdjustPagingHelper"/>

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
