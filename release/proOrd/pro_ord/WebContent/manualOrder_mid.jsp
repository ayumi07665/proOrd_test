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
	function nextfocus(keta,mx,No){
		if(mx <= keta){
			document.all['date'+ (No + 1)].focus();
			document.all['date'+ (No + 1)].select();
		}
	}
	
	function movefocus(i, n) {
		checkMode();
		if(i.form.elements[n].disabled != true && i.checked == true)
    		i.form.elements[n].focus();
	}
	function checkMode(){
		var flg = 0;
		with(document.manualOrderForm){
			if(rb_select[0].checked)
				checkRbSelect("hbidte");
			for (var i = 0; i < elements.length; i++ ) {
//alert(elements[i].type);
		    	if (elements[i].type == "radio" ) {
//alert(elements[i].checked);
//alert(elements[i].name);
					flg = (elements[i].checked == true)? 1 : 0;
					i++;
				}
				if(elements[i].type == "select-one" || elements[i].type == "select-multiple"){
					if(flg == 1){
						elements[i].disabled = true;
						elements[i].style.backgroundColor = '#efefef';
					}else{
						elements[i].disabled = false;
						elements[i].style.backgroundColor = '#ffffff';
					}
					if(elements[i].type == "select-multiple" && elements[i].options.length == 1)
						elements[i].options[0].selected = true;
					flg = 0;
				}
				if(elements[i].type == "text"){
					if(flg == 0){
						elements[i].disabled = true;
						elements[i].style.backgroundColor = '#efefef';
					}else{
						elements[i].disabled = false;
						elements[i].style.backgroundColor = '#ffffff';
					}
				}
				
				//2004/06/30 add  ------------------------------st
				if(elements[i].type == "textarea"){
					if(flg == 0){
						elements[i].disabled = true;
						elements[i].style.backgroundColor = '#efefef';
					}else{
						elements[i].disabled = false;
						elements[i].style.backgroundColor = '#ffffff';
					}
				}
				if(elements[i].type == "submit"){
					if(flg == 0)
						elements[i].disabled = true;
					else
						elements[i].disabled = false;

				}

				//----------------------------------------------ed

			}
			if(!rb_select[0].checked)
				checkRbSelect("kigbng");
	    }
	}
	
	function checkRbSelect(b) {
		with(document.manualOrderForm){
			if(b == "hbidte"){
				for (var idx = 0; idx < elements.length; idx++ ) {
					if(elements[idx].type == "checkbox" || elements[idx].type == "radio") {
						elements[idx].disabled = false;
					}
				}
			}else if(b == "kigbng"){
				var flg = 0;
				for (var idx = 0; idx < elements.length; idx++ ) {
					if (elements[idx].type == "radio" && elements[idx].name != "rb_select") {
						elements[idx].disabled = true;
					}
					if(elements[idx].type == "checkbox" ||
					   elements[idx].type == "select-multiple" ||
					   (elements[idx].type == "select-one" && elements[idx].name != "kbn"))
						flg = 1;
					if(flg == 1){
						elements[idx].disabled = true;
						if(elements[idx].type != "checkbox")
							elements[idx].style.backgroundColor = '#efefef';
					}
					flg = 0;
				}
			}
		}
	}

//-->

</SCRIPT>

<LINK href="theme/prodord1.css<nested-ex:nocache-param />" rel="stylesheet" type="text/css">
<LINK href="theme/commonItemSize.css<nested-ex:nocache-param />" rel="stylesheet" type="text/css">

</head>

<BODY onLoad="checkMode();">

<html:form action="/manualOrder" target="_self" onsubmit="return acceptSubmit();">

<bean:define id="fmForm" name="manualOrderForm" type="com.jds.prodord.indicate.manualorder.ManualOrderForm"/>

<html:hidden property="command" name="command" value="java.lang.String"/>

<logic:present  name="SERVICE_MESSAGE">
	<DIV class="service_msg"><bean:write name="SERVICE_MESSAGE" /></DIV>
</logic:present>
<html:errors/>

<hr><br>
<CENTER>
<table border="0" cellspacing="10"><tr><td style="width:25px"></td><td>

	<table border="0" style="width:950px;height:280px;" align="left" cellspacing="7">

		<tr><td style="width:38px;height:64px;" class="title_neutral">１</td>
			<td style="width:160px" class="title_dark"><bean:message key="label.kaiskb"/></td>
			<td style="width:22px" align="center">
				<html:radio property="rb_kaiSkbCod" value="true" onclick="checkMode()"/></td>
			<td style="width:59px" class="title_light"><bean:message key="radio.all"/></td>
			<td style="width:22px" align="center">
				<html:radio property="rb_kaiSkbCod" value="false" onclick="movefocus(this,'kaiSkbCod')"/></td>
			<td style="width:59px" class="title_light"><bean:message key="radio.choice"/></td>
			<td style="width:87px" align="left">
				<html:select property="kaiSkbCod" size="3" multiple="true">
		   			<html:options property="vl_kaiSkbCod"/>
		   		</html:select></td>
		   	<td style="width:16px"></td>
		   	
            <td class="title_neutral" style="width:30px">２</td>
            <td class="title_dark" style="width:126px"><bean:message key="label.mkrbun"/></td>
            <td style="width:22px" align="center"><html:radio property="rb_mkrBun" value="true" onclick="checkMode()" /></td>
			<td style="width:56px" class="title_light"><bean:message key="radio.all"/></td>
			<td style="width:22px" align="center"><html:radio property="rb_mkrBun" value="false" onclick="movefocus(this,'mkrBun')"/></td>
			<td style="width:56px" class="title_light"><bean:message key="radio.choice"/></td>
			<td style="min-width:150px" align="left">
				<html:select property="mkrBun" size="4" multiple="true">
		   			<html:options property="vl_mkrBun"/>
		   		</html:select>
		   	<td ></td>
		</tr>
		
		<tr>
			<td class="title_neutral" rowspan="2">３</td>
			<td class="title_dark" rowspan="2"><bean:message key="label.ketcod"/></td>
			<td align="center" rowspan="2">
				<html:radio property="rb_ketCod" value="true" onclick="checkMode()" /></td>
			<td class="title_light" rowspan="2"><bean:message key="radio.all"/></td>
			<td align="center" rowspan="2">
				<html:radio property="rb_ketCod" value="false" onclick="movefocus(this,'ketCod')"/></td>
			<td class="title_light" rowspan="2"><bean:message key="radio.choice"/></td>
			<td align="left" rowspan="2">
				<html:select property="ketCod" size="5" multiple="true">
		   			<html:options property="vl_ketCod"/>
		   		</html:select></td>
		   	<td rowspan="2"></td>
			<td  class="title_neutral">４</td>
			<td  class="title_dark"><bean:message key="label.houyoukbn"/></td>
			<td  align="center">
				<html:radio property="rb_hyoKbn" value="true" onclick="checkMode()"/></td>
			<td  class="title_light">
				<bean:message key="radio.all"/></td>
			<td  align="center">
				<html:radio property="rb_hyoKbn" value="false" onclick="movefocus(this,'hyoKbn')"/></td>
			<td  class="title_light">
				<bean:message key="radio.choice"/></td>
			<td align="left">
				<html:select property="hyoKbn" size="1" disabled="false">
		   			<html:options property="vl_hyoKbn" labelProperty="lb_hyoKbn"/>
		   		</html:select></td>
		   	<td></td>
		</tr>

		<tr>
			<td class="title_neutral">５</td>
			<td class="title_dark"><bean:message key="label.kbn"/></td>
            <td></td>
            <td align="left" colspan="2">
				<html:select property="kbn" value="<%=fmForm.getKbn()%>">
					<html:option value="1"><bean:message key="select.sinpu"/></html:option>
		   			<html:option value="0"><bean:message key="select.kyuhu"/></html:option>
					<html:option value="2"><bean:message key="select.sample"/></html:option>
					<html:option value="4"><bean:message key="select.tokhan"/></html:option>
					
					<%if(fmForm.getQueryDaiKaiSkbCod().equals("K")){%>
						<html:option value="6"><bean:message key="select.hicatalog"/></html:option>
					<%}%>
					<html:option value="3"><bean:message key="select.demoban"/></html:option>
					<html:option value="5"><bean:message key="select.sonota"/></html:option>
				</html:select></td>
		</tr>   	
        
        <tr>
			<td  class="title_neutral" style="height:35px">６</td>
			<td  class="title_dark">
				<bean:message key="label.hbidte"/></td>
            <td>
            	<html:radio property="rb_select" value="true" onclick="movefocus(this,'hbiDte1.year');"/>
            </td>
            <td align="left" colspan="11">
       			<table border="0" cellspacing="2" cellpadding="0"><tr><td>
				<table border="0" cellspacing="0"><tr><td class="title_dark">
	              <table bgcolor="#FFFFFF" style="width:100px;height:1px;" cellspacing="0" cellpadding="2">
	              <tr>
	                <td class="details">
	                  <html:text property="hbiDte1.year" styleClass="s_1" maxlength="2" styleId="date1" onkeypress="nextfocus(value.length,maxLength,1)" />
	                   /
	                  <html:text property="hbiDte1.month" styleClass="s_1" maxlength="2" styleId="date2" onkeypress="nextfocus(value.length,maxLength,2)" />
	                   /
	                  <html:text property="hbiDte1.day" styleClass="s_1" maxlength="2" styleId="date3" onkeypress="nextfocus(value.length,maxLength,3)" />
	             </td></tr></table>
	           </td></tr></table>
	        </td><td width="3"></td><td>
	           <table border="0" cellspacing="0"><tr><td class="title_dark">
	              <table bgcolor="#FFFFFF" style="width:100px;height:1px;" cellspacing="0" cellpadding="2">
	              <tr>
	                <td class="details">
	                  <html:text property="hbiDte2.year" styleClass="s_1" maxlength="2" styleId="date4" onkeypress="nextfocus(value.length,maxLength,4)" />
	                   /
	                  <html:text property="hbiDte2.month" styleClass="s_1" maxlength="2" styleId="date5" onkeypress="nextfocus(value.length,maxLength,5)" />
	                   /
	                  <html:text property="hbiDte2.day" styleClass="s_1" maxlength="2" styleId="date6" onkeypress="nextfocus(value.length,maxLength,6)" />
	             </td></tr></table>
	           </td></tr></table>
	         </td><td width="3"></td><td>
	           <table border="0" cellspacing="0"><tr><td class="title_dark">
	              <table bgcolor="#FFFFFF" style="width:100px;height:1px;" cellspacing="0" cellpadding="2">
	              <tr>
	                <td class="details">
	                  <html:text property="hbiDte3.year" styleClass="s_1" maxlength="2" styleId="date7" onkeypress="nextfocus(value.length,maxLength,7)" />
	                   /
	                  <html:text property="hbiDte3.month" styleClass="s_1" maxlength="2" styleId="date8" onkeypress="nextfocus(value.length,maxLength,8)" />
	                   /
	                  <html:text property="hbiDte3.day" styleClass="s_1" maxlength="2" styleId="date9" onkeypress="nextfocus(value.length,maxLength,9)" />
	             </td></tr></table>
	           </td></tr></table>
	         </td><td width="3"></td><td>
	           <table border="0" cellspacing="0"><tr><td class="title_dark">
	              <table bgcolor="#FFFFFF" style="width:100px;height:1px;" cellspacing="0" cellpadding="2">
	              <tr>
	                <td class="details">
	                  <html:text property="hbiDte4.year" styleClass="s_1" maxlength="2" styleId="date10" onkeypress="nextfocus(value.length,maxLength,10)" />
	                   /
	                  <html:text property="hbiDte4.month" styleClass="s_1" maxlength="2" styleId="date11" onkeypress="nextfocus(value.length,maxLength,11)" />
	                   /
	                  <html:text property="hbiDte4.day" styleClass="s_1" maxlength="2" styleId="date12" onkeypress="nextfocus(value.length,maxLength,12)" />
	             </td></tr></table>
	           </td></tr></table>
	        </td><td width="3"></td><td>
	           <table border="0" cellspacing="0"><tr><td class="title_dark">
	              <table bgcolor="#FFFFFF" style="width:100px;height:1px;" cellspacing="0" cellpadding="2">
	              <tr>
	                <td class="details">
	                  <html:text property="hbiDte5.year" styleClass="s_1" maxlength="2" styleId="date13" onkeypress="nextfocus(value.length,maxLength,13)" />
	                   /
	                  <html:text property="hbiDte5.month" styleClass="s_1" maxlength="2" styleId="date14" onkeypress="nextfocus(value.length,maxLength,14)" />
	                   /
	                  <html:text property="hbiDte5.day" styleClass="s_1" maxlength="2" styleId="date15"/>
	             </td></tr></table>
	           </td></tr></table>

			</td></tr></table>
			
			</td>

		</tr>
		<tr><td colspan="14" style="height:10px"></td></tr>
		<tr>
			<td class="title_neutral">７</td>
			<td class="title_dark">
				<bean:message key="label.hinshitei"/>
			</td>
            <td>
            	<html:radio property="rb_select" value="false" onclick="movefocus(this,'kigBng1.kigBng');"/>
            </td>
            <td align="left" colspan="11">
				<html:text property="kigBng1.kigBng" styleClass="s_18" maxlength="13" />
				<html:text property="kigBng2.kigBng" styleClass="s_18" maxlength="13" />
				<html:text property="kigBng3.kigBng" styleClass="s_18" maxlength="13" />
				<html:text property="kigBng4.kigBng" styleClass="s_18" maxlength="13" />
				<html:text property="kigBng5.kigBng" styleClass="s_18" maxlength="13" />
			</td>
			<td rowspan="2" align="left" valign="bottom">
				<html:textarea property="cpPaste" cols="15" rows="2"/>
			</td>
		</tr>
		<tr>
			<td></td>
			<td>　
			</td>
            <td>
			</td>
            <td align="left" colspan="11">
				<html:text property="kigBng6.kigBng" styleClass="s_18" maxlength="13" />
				<html:text property="kigBng7.kigBng" styleClass="s_18" maxlength="13" />
				<html:text property="kigBng8.kigBng" styleClass="s_18" maxlength="13" />
				<html:text property="kigBng9.kigBng" styleClass="s_18" maxlength="13" />
				<html:text property="kigBng10.kigBng" styleClass="s_18" maxlength="13" />
			</td>
		</tr>
		<tr>
			<td></td>
			<td>　
			</td>
            <td>
			</td>
            <td align="left" colspan="11">
				<html:text property="kigBng11.kigBng" styleClass="s_18" maxlength="13" />
				<html:text property="kigBng12.kigBng" styleClass="s_18" maxlength="13" />
				<html:text property="kigBng13.kigBng" styleClass="s_18" maxlength="13" />
				<html:text property="kigBng14.kigBng" styleClass="s_18" maxlength="13" />
				<html:text property="kigBng15.kigBng" styleClass="s_18" maxlength="13" />
			</td>
			<td align="left"><html:submit property="paste" value="貼り付け" styleClass="button" style="height:20px;"/>
			</td>
		</tr>
        <TR>
            <TD colspan="16">
            
    <table border="0" style="width:400px" align="left" cellspacing="10">
		<tr>
			<td style="width:110px" class="title_light">
				<bean:message key="label.orderby"/>
			</td><td style="width:10px">
				<html:checkbox property="sort_ketCod" value="true"/>
			</td><td class="title_neutral">
				<bean:message key="label.ketcod"/>
			</td><td style="width:10px">
				<html:checkbox property="sort_sortKey" value="true"/>
			</td><td class="title_neutral">
				<bean:message key="label.sortkey"/>
			</td>
		</tr>
	</table>
            
            </TD>
        </TR>
   </table>

</td></tr></table>
</CENTER>
<BR>
<hr>
<br>

</html:form>

</BODY>

</html:html>

