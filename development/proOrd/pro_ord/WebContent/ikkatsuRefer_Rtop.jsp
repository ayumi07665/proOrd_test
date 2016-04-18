<!DOCTYPE HTML>
<%@ page contentType="text/html; charset=Shift_JIS" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld"  prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-nested-ex.tld" prefix="nested-ex" %>

<html>

<head>

<LINK rel="stylesheet" href="theme/prodord1.css<nested-ex:nocache-param />" type="text/css">
<LINK href="theme/commonItemSize.css<nested-ex:nocache-param />" rel="stylesheet" type="text/css">

<script><!--

	window.onscroll = function pageMove(){
		L = document.documentElement.scrollLeft || document.body.scrollLeft;
		if(parent.midRF.document.body != null)
			parent.midRF.document.body.scrollLeft = L;
		if(parent.midRF.document.documentElement != null)
			parent.midRF.document.documentElement.scrollLeft = L;
	}

// --></script>

<META http-equiv ="X-UA-Compatible" content ="IE=edge"/>
<META http-equiv="Pragma" content="no-cache">
<META http-equiv="Cache-Control" content="no-cache">
<META http-equiv="Expires" content="Thu, 01 Dec 1994 16:00:00 GMT">
<META http-equiv="Content-Style-Type" content="text/css">
<META http-equiv="Content-Type" content="text/html; charset=Shift_JIS">
<META name="GENERATOR" content="IBM WebSphere Studio">

</head>



<BODY>

<table border="0"  cellpadding="0" cellspacing="0" style="width:1000px;height:40px;">
  <tr> 
    <td> </td>
  </tr>
</table>
<table border="0" cellpadding="0" cellspacing="0">
		<tr>
            <td class="title_dark" style="width:370px;height:25px;">
				<bean:message key="table.prshacinfo"/>
			</td>
            <td style="padding:3px;"></td>
			<td class="title_dark" style="width:368px">
				<bean:message key="table.uriinfo"/>
			</td>
			<td style="padding:3px;"></td>
			<td class="title_dark" style="width:285px;padding-right:5px;">
				<bean:message key="table.fukszihacinfo"/>
			</td>
		</tr>
		<tr>
            <td></td>
            <td></td>
			<td style="padding:0px 1px;">
				<table style="width:372px;height:18px;" border="0" cellspacing="1" cellpadding="0"><tr class="title_neutral">
					<td style="width:129px">
					<bean:message key="label.dailyuri"/>
					</td>
					<td style="width:59px">
					<bean:message key="label.weeklyuri"/>
					</td>
					<td style="width:59px">
					<bean:message key="label.monthlyuri"/>
					</td>
					<td style="width:59px">
					<bean:message key="label.henpin"/>
					</td>
					<td style="width:59px">
					<bean:message key="label.tanaage"/>
					</td>
				</tr></table>
			</td>
			<td></td><td></td>
		</tr>
		
	<tr>
	<td align="center" style="padding:0px 1px;"> 
      <table style="width:370px;height:56px;" border="0" cellspacing="1" cellpadding="0"><tr class="title_light">
				<td style="width:85px"><bean:message key="label.hacsuu"/></td>
				<td style="width:98px"><bean:message key="label.nouki"/></td>
				<td style="width:63px"><bean:message key="label.hacski"/></td>
				<td style="width:63px"></td>
				<td style="width:53px"></td>
				</tr><tr class="title_light">
				<td><bean:message key="label.mnykttl"/></td>
			    <td><bean:message key="label.hacrki1"/></td>
				<td><bean:message key="label.nykrki1"/></td>
				<td><bean:message key="label.nouki1"/></td>
				<td></td>
				</tr><tr class="title_light">
				<td><bean:message key="label.nyksum"/></td>
				<td><bean:message key="label.hacrki2"/></td>
				<td><bean:message key="label.nykrki2"/></td>
				<td><bean:message key="label.nouki2"/></td>
				<td></td>
		</tr></table>
	</td>
	<td></td>
			
    <td align="center" style="padding:0px 1px;"> 
      <table border="0" cellspacing="1" style="width:372px;height:56px;padding:0px;"><tr class="title_light">
				<td style="width:65px"><bean:message key="label.day"/></td>
				<td style="width:64px"><bean:message key="label.3daybefore"/></td>
				<td style="width:58px"><bean:message key="label.week"/></td>
				<td style="width:58px"><bean:message key="label.month"/></td>
				<td style="width:58px"><bean:message key="label.month"/></td>
				<td style="width:58px"><bean:message key="label.month"/></td>
				</tr><tr class="title_light">
				<td><bean:message key="label.preday"/></td>
				<td><bean:message key="label.4daybefore"/></td>
				<td><bean:message key="label.preweek"/></td>
				<td><bean:message key="label.premonth"/></td>
				<td><bean:message key="label.premonth"/></td>
				<td><bean:message key="label.premonth"/></td>
				</tr><tr class="title_light">
				<td><bean:message key="label.2daybefore"/></td>
				<td><bean:message key="label.5daybefore"/></td>
				<td><bean:message key="label.2weekbefore"/></td>
				<td><bean:message key="label.2monthbefore"/></td>
				<td><bean:message key="label.2monthbefore"/></td>
				<td><bean:message key="label.2monthbefore"/></td>
	 </tr></table>
	</td>
	<td></td>
			
    <td align="center" style="padding:0px 1px;"> 
      <table border="0" cellspacing="1" style="width:285px;height:56px;padding:0px;"><tr class="title_light">
				<td style="width:70px"><bean:message key="label.teiannsuu"/></td>
				<td style="width:70px"><bean:message key="label.nouki"/></td>
				<td style="width:70px"><bean:message key="label.hacski"/></td>
				<td style="width:35px"></td>
				<td style="width:35px"></td>
				</tr><tr class="title_light">
				<td><bean:message key="label.mnykttl"/></td>
				<td><bean:message key="label.hacrki1"/></td>
				<td><bean:message key="label.nouki1"/></td>
				<td></td>
				<td></td>
				</tr><tr class="title_light">
				<td><bean:message key="label.nyksum"/></td>
				<td><bean:message key="label.hacrki2"/></td>
				<td><bean:message key="label.nouki2"/></td>
				<td></td>
				<td></td>
	  </tr></table>
    </td>
</tr></table>
</body>

</html>

