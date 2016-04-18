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
	
	function movefocus(i, n) {
		checkMode();
		if(i.form.elements[n].disabled != true && i.checked == true)
    		i.form.elements[n].focus();
	}
	function checkMode(){
		var i;
		var flg = 0;
		with(document.teiansuuReferForm){
			if(rb_kigBng[0].checked)
				checkRbKigbng("true");
			for (i = 0; i < elements.length; i++ ){
//alert(elements[i].type);
		    	if(elements[i].type == "radio"){
//alert(elements[i].checked);
//alert(elements[i].name);
		    		flg = (elements[i].checked == true)? 1 : 0;
					i++;
		    	}if(elements[i].type == "checkbox" && elements[i].name == "hitaisyo")
					flg = (elements[i].checked == true)? 0 : 1;
				if(elements[i].type == "text" || elements[i].type == "select-one" || elements[i].type == "select-multiple"
				   || elements[i].type == "textarea" || elements[i].type == "submit"){


					if(elements[i].type == "submit"){
						if(flg == 1)
							elements[i].disabled = true;
						else
							elements[i].disabled = false;
					}else{
					
				        if(flg == 1){
							elements[i].disabled = true;
							elements[i].style.backgroundColor = '#efefef';
							
						}else{
							elements[i].disabled = false;
							elements[i].style.backgroundColor = '#ffffff';
							
						}
					}
					
					if(elements[i].type == "select-multiple" && elements[i].options.length == 1)
						elements[i].options[0].selected = true;
						
				}
		  	}
		  	if(!rb_kigBng[0].checked)
		  		checkRbKigbng("false");
	    }
	}
	
	function checkRbKigbng(b) {

		if(b == "true"){
			with(document.teiansuuReferForm){
				for (var idx = 0; idx < elements.length; idx++ ) {
					if(elements[idx].type == "checkbox" || elements[idx].type == "radio") {
						elements[idx].disabled = false;
					}
				}
			}
		}else{
			var flg = 0;
			with(document.teiansuuReferForm){
				for (var idx = 0; idx < elements.length; idx++ ) {
					if (elements[idx].type == "radio" && elements[idx].name != "rb_kigBng") {
						elements[idx].disabled = true;
					}
					if(elements[idx].type == "select-one" ||
					   elements[idx].type == "select-multiple" ||
					   (elements[idx].type == "checkbox" && elements[idx].name == "hitaisyo"))
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

<LINK rel="stylesheet" href="theme/prodord1.css<nested-ex:nocache-param />" type="text/css">
<LINK href="theme/commonItemSize.css<nested-ex:nocache-param />" rel="stylesheet" type="text/css">

</head>

<BODY onLoad="checkMode();">

<html:form action="/teiansuuRefer" target="_self" onsubmit="return acceptSubmit();">

<bean:define id="fmForm" name="teiansuuReferForm" type="com.jds.prodord.indicate.teiansuurefer.TeiansuuReferForm"/>

<html:hidden property="command" name="command" value="java.lang.String"/>
<br>


<logic:present  name="SERVICE_MESSAGE">
	<DIV class="service_msg"><bean:write name="SERVICE_MESSAGE" /></DIV>
</logic:present>
<html:errors/>

<hr>

<table border="0" cellspacing="10"><tr><td style="width:20px"></td><td>
	<table border="0" style="width:930px;height:150px;" align="left" cellspacing="8">
		<tr>
			<td style="width:32px" class="title_neutral">１</td>
			<td style="width:113px" class="title_dark">
				<bean:message key="label.kaiskb"/>
			</td>
			<td style="width:22px" align="center">
				<html:radio property="rb_kaiSkbCod" value="true" onclick="checkMode()"/>
			</td>
			<td style="width:57px" class="title_light">
				<bean:message key="radio.all"/>
			</td>
			<td style="width:22px" align="center">
				<html:radio property="rb_kaiSkbCod" value="false" onclick="movefocus(this,'kaiSkbCod')"/>
			</td>
			<td style="width:57px" class="title_light">
				<bean:message key="radio.choice"/>
			</td>
			<td style="width:89px" align="left">
				<html:select property="kaiSkbCod" size="3" multiple="true">
		   			<html:options property="vl_kaiSkbCod"/>
		   		</html:select>
			</td>
			<td style="width:32px" class="title_neutral">２</td>
			<td style="width:88px" class="title_dark">
				<bean:message key="label.rank"/>
			</td>
			<td style="width:22px" align="center">
				<html:radio property="rb_rank" value="true" onclick="checkMode()"/>
			</td>
			<td style="width:57px" class="title_light">
				<bean:message key="radio.all"/>
			</td>
			<td style="width:22px" align="center">
				<html:radio property="rb_rank" value="false" onclick="movefocus(this,'rank')"/>
			</td>
			<td style="width:57px" class="title_light">
				<bean:message key="radio.choice"/>
			</td>
			<td align="left">
				<html:select property="rank" size="3" multiple="true">
		   			<html:options property="vl_rank"/>
		   		</html:select>
			</td>
		</tr>
		<tr><td></td></tr>
		<tr>
			<td class="title_neutral">３</td>
			<td class="title_dark">
				<bean:message key="label.ketcod"/>
			</td>
			<td align="center">
				<html:radio property="rb_ketCod" value="true" onclick="checkMode()" />
			</td>
			<td class="title_light">
				<bean:message key="radio.all"/>
			</td>
			<td align="center">
				<html:radio property="rb_ketCod" value="false" onclick="movefocus(this,'ketCod')"/>
			</td>
			<td class="title_light">
				<bean:message key="radio.choice"/>
			</td>
			<td align="left">
				<html:select property="ketCod" size="5" multiple="true">
		   			<html:options property="vl_ketCod"/>
		   		</html:select>
			</td>
			<td class="title_neutral">４</td>
			<td class="title_dark">
				<bean:message key="label.mkrbun"/>
			</td>
			<td align="center">
				<html:radio property="rb_mkrBun" value="true" onclick="checkMode()" />
			</td>
			<td class="title_light">
				<bean:message key="radio.all"/>
			</td>
			<td align="center">
				<html:radio property="rb_mkrBun" value="false" onclick="movefocus(this,'mkrBun')"/>
			</td>
			<td class="title_light">
				<bean:message key="radio.choice"/>
			</td>
			<td align="left" style="min-width:150px;">
				<html:select property="mkrBun" size="4" multiple="true" >
		   			<html:options property="vl_mkrBun"/>
		   		</html:select>
			</td>
		</tr>
	</table>

</td></tr><tr><td></td><td>

	<table border="0" style="width:930px;height:120px;" align="left" cellspacing="8">
		<tr>
			<td style="width:35px" class="title_neutral">５</td>
			<td style="width:113px" class="title_dark">
				<bean:message key="label.houyoukbn"/>
			</td>
			<td style="width:22px" align="center">
				<html:radio property="rb_hyoKbn" value="true" onclick="checkMode()"/>
			</td>
			<td style="width:61px" class="title_light">
				<bean:message key="radio.all"/>
			</td>
			<td style="width:22px" align="center">
				<html:radio property="rb_hyoKbn" value="false" onclick="movefocus(this,'hyoKbn')"/>
			</td>
			<td style="width:61px" class="title_light">
				<bean:message key="radio.choice"/>
			</td>
			<td align="left" style="width:560px">
				<html:select property="hyoKbn" size="1" disabled="false">
		   			<html:options property="vl_hyoKbn" labelProperty="lb_hyoKbn"/>
		   		</html:select>
			</td>
		</tr>
		<tr><td></td></tr>
		<tr>
			<td class="title_neutral">６</td>
			<td class="title_dark">
				<bean:message key="label.hinban"/>
			</td>
			<td align="center">
				<html:radio property="rb_kigBng" value="true" onclick="checkMode()" styleId="rb_kigBng"/>
			</td>
			<td class="title_light">
				<bean:message key="radio.all"/>
			</td>
			<td align="center">
				<html:radio property="rb_kigBng" value="false" onclick="movefocus(this,'kigBng1')"/>
			</td>
			<td class="title_light">
				<bean:message key="radio.choice"/>
			</td>
			<td align="left">
				<html:text property="kigBng1" styleClass="s_18" maxlength="13" />
				<html:text property="kigBng2" styleClass="s_18" maxlength="13" />
				<html:text property="kigBng3" styleClass="s_18" maxlength="13" />
				<html:text property="kigBng4" styleClass="s_18" maxlength="13" />
				<html:text property="kigBng5" styleClass="s_18" maxlength="13" />
			</td>
		</tr>
		<tr>
			<td colspan="6" align="right" rowspan="2">
				<html:textarea property="cpPaste" cols="15" rows="2"/>
				<html:submit property="paste" styleClass="button" value="貼り付け" style="height:20px;"/>
			</td>
			<td align="left">
				<html:text property="kigBng6" styleClass="s_18" maxlength="13" />
				<html:text property="kigBng7" styleClass="s_18" maxlength="13" />
				<html:text property="kigBng8" styleClass="s_18" maxlength="13" />
				<html:text property="kigBng9" styleClass="s_18" maxlength="13" />
				<html:text property="kigBng10" styleClass="s_18" maxlength="13" />
			</td>
		</tr>
		<!-- 2003/12/12 add 品番指定数を10→15へ st  -->
		<tr><!--<td colspan="6"></td>-->
			<td align="left">
				<html:text property="kigBng11" styleClass="s_18" maxlength="13" />
				<html:text property="kigBng12" styleClass="s_18" maxlength="13" />
				<html:text property="kigBng13" styleClass="s_18" maxlength="13" />
				<html:text property="kigBng14" styleClass="s_18" maxlength="13" />
				<html:text property="kigBng15" styleClass="s_18" maxlength="13" />
			</td>
		</tr>
		<!-- 2003/12/12 add 品番指定数を10→15へ ed  -->		
	</table>

</td></tr><tr><td></td><td style="padding:5px;">
	<table cellpadding="2" style="width:480px;margin-bottom:5px;"><tr>
		<td style="width:90px" class="title_light">
			<bean:message key="label.orderby"/>
		</td><td style="width:5px"></td><td style="width:10px">
	 		<html:checkbox property="sort_rank" value="true"/>
		</td><td class="title_neutral">
			<bean:message key="label.rank"/>
		</td><td style="width:5px"></td><td style="width:10px">
			<html:checkbox property="sort_ketCod" value="true"/>
		</td><td class="title_neutral">
			<bean:message key="label.ketcod"/>
		</td><td style="width:5px"></td><td style="width:10px">
			<html:checkbox property="sort_sortKey" value="true"/>
		</td><td class="title_neutral">
			<bean:message key="label.sortkey"/>
		</td>
	</tr></table>
	
	<table style="width:450px" cellpadding="2"><tr>
		<td style="width:90px" class="title_light">
			<bean:message key="label.taisyojoken"/>
		</td><td style="width:5px"></td><td style="width:10px">
			<html:checkbox property="hitaisyo" value="true" onclick="movefocus(this,'taisyoRange');"/>
		</td><td class="title_neutral">
			<bean:message key="label.hitaisyo"/>
		</td><td style="width:70px" align="right" class="index">
			<bean:message key="label.zaisuu"/>&nbsp;&gt;&gt;
		</td><td>
			<html:select property="taisyoRange" size="1" disabled="false">
		   		<html:options property="vl_taisyoRange" labelProperty="lb_taisyoRange"/>
		   	</html:select>
		</td>
	</tr></table>
	
</td></tr>

</table>

<hr>

</html:form>

</BODY>

</html:html>

