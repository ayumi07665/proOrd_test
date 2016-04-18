<!DOCTYPE HTML>
<%@ page contentType="text/html; charset=Shift_JIS" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld"  prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-form.tld" prefix="form" %>
<%@ taglib uri="/WEB-INF/struts-nested-ex.tld" prefix="nested-ex" %>

<HTML>
<HEAD>
<script>
<!--
	function acceptSubmit(){
		for (var i = 0; i < document.all.length; i++) {
  		  document.all(i).style.visibility = "hidden";
		}
		return true;
	}
	function formSubmit(){
		document.prsOrderForm.submit();
	}
	var yokojiku = -20;
	function chkwindowsize(){
		if (document.all){
			x = window.innerWidth;
			y = window.innerHeight;
		} else {
			x = document.body.clientWidth;
			y = document.body.clientHeight;
		}
	}
	function xzahyou(){
		move();
		++yokojiku;
		if(yokojiku > y)
			yokojiku = -20; 
		timerID = setTimeout("xzahyou()",20);
	}
	function move(){
		OBJ = document.getElementById("obj1").style;
		OBJ.left = yokojiku + "px";
	}
// --></script>
<META http-equiv ="X-UA-Compatible" content ="IE=edge"/>
<META http-equiv="Pragma" content="no-cache">
<META http-equiv="Cache-Control" content="no-cache">
<META http-equiv="Expires" content="Thu, 01 Dec 1994 16:00:00 GMT">
<META name="GENERATOR" content="IBM WebSphere Studio">
<LINK rel="stylesheet" href="theme/prodord1.css<nested-ex:nocache-param />" type="text/css">
<LINK href="theme/commonItemSize.css<nested-ex:nocache-param />" rel="stylesheet" type="text/css">
</HEAD>
<BODY onLoad="chkwindowsize();xzahyou();formSubmit();">
<div id="obj1" style="position:absolute;" class="info_msg"><bean:message key="info.processing"/></div>
<html:form action="/prsOrder" onsubmit="return acceptSubmit();" target="_top">
</html:form>
</BODY>
</HTML>
