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
	window.onscroll = function pageMove(){
		L = document.documentElement.scrollLeft || document.body.scrollLeft;
		if(parent.midLF.document.body != null)
			parent.midLF.document.body.scrollLeft = L;
		if(parent.midLF.document.documentElement != null)
			parent.midLF.document.documentElement.scrollLeft = L;
	}

//-->

</SCRIPT>

<LINK rel="stylesheet" href="theme/prodord1.css<nested-ex:nocache-param />" type="text/css">
<LINK href="theme/commonItemSize.css<nested-ex:nocache-param />" rel="stylesheet" type="text/css">

<STYLE>

TABLE {
	table-layout: fixed;
}

</STYLE>

</head>

<BODY>

<bean:define id="fmForm" name="ikkatsuReferForm" type="com.jds.prodord.reference.ikkatsurefer.IkkatsuReferForm"/>

<div id="Layer2" style="position:absolute; left:10px; top:10px; width : 178px; height:47px; z-index : 1; visibility : visible;">
	<table style="width:150px;height:25px;">
		<tr>
			<td style="width:70px" class="title_dark">
				<bean:message key="label.daikai"/>
			</td><td class="title_light">
				<table style="width:70px;height:22px" cellspacing="2" cellpadding="2" class="details">
				<tr><td align="center">
					<bean:write name="fmForm" property="queryDaiKaiSkbCod" ignore="true"/>
				</td></tr></table>
			</td>
		</tr>
	</table>
</div>

<div id="Layer2" style="position:absolute; left : 198px; top:10px; width : 390px; height : 44px; z-index : 2; visibility : visible;">
	<table style="width:390px">
		<tr valign="bottom">
            <td align="right" style="width:160px">
				<html:form action="/teiansuuRefer" target="_top">
				<html:submit property="submit" styleClass="button2" style="width:140px">
				<bean:message key="button.backtoindicate"/></html:submit>
				</html:form>
			</td>
            <td style="width:15px"></td>
            <td style="width:120px">
				<html:form action="/Gomain" target="_top">
				<html:submit property="submit" styleClass="button2" style="width:115px">
				<bean:message key="button.mainmenu"/></html:submit>
				<html:hidden property="pagingHelperType" value="com.jds.prodord.reference.ikkatsurefer.IkkatsuReferPagingHelper"/>
				</html:form>
			</td>
            <td>
				<html:form action="/Logoff" target="_top">
				<html:submit property="submit" styleClass="button2" style="width:64px">
				<bean:message key="button.logoff"/></html:submit>
				</html:form>
			</td>
        </tr>
	</table>
</div>

<table border="0"  cellpadding="0" cellspacing="0" style="width:580px;height:38px;">
  <tr> 
    <td> </td>
  </tr>
</table>

	<table border="0" style="width:580px;height:105px;">
		<tr>
			<td style="width:293px;height:24px;" class="title_dark">
				<bean:message key="table.hininfo"/>
			</td>
			<td></td>
			<td style="width:124px" class="title_dark">
				<bean:message key="table.zaiinfo"/>
			</td>
			<td></td>
			<td style="width:73px" class="title_dark">
				<bean:message key="table.teianninfo"/>
			</td>
			<td></td>
			<td style="width:73px" class="title_dark">
				<bean:message key="table.fuksziinfo"/>
			</td>
		</tr>
		<tr>
			<td style="height:12px"></td><td></td><td></td><td></td><td></td><td></td><td></td>
		</tr>
		<tr>

    <td align="center"> 
      <table border="0" style="width:293px;height:56px;" cellspacing="1" cellpadding="0"><tr class="title_light">
      			<td style="width:26px" rowspan="3" class="title_light"></td>
				<td style="width:137px;height:16px;"><bean:message key="label.kigbng"/></td>
				<td style="width:103px"><bean:message key="label.ketcod"/></td>
				<td style="width:20px" rowspan="3" class="title_light"></td>
				</tr><tr class="title_light">
				<td style="height:16px;"><bean:message key="label.artist"/></td>
				<td><bean:message key="label.hbidte"/></td>
				</tr><tr class="title_light">
				<td style="height:16px;"><bean:message key="label.title"/></td>
				<td style="padding:0px;">
					<div style="width:35px;height:17px;text-align:center;float:left;border-right:1px solid #FFF8E9">
				    	<div style="position:relative;"><bean:message key="label.rank"/></div>
				    </div>
				    <div style="position:relative;width:65px;text-align:center;float:left;">
				    	<bean:message key="label.tax_inc"/>
				    </div>
				</td>
	  </tr></table>
	</td>
	<td></td>
	<td>
		<table border="0" style="width:124px;height:56px;" cellspacing="1" cellpadding="0"><tr class="title_light">
				<td style="width:62px;height:16px;"><bean:message key="label.zaisuu"/></td>
				<td style="width:62px"><bean:message key="label.chz"/></td>
				</tr><tr class="title_light">
				<td style="height:16px;"><bean:message key="label.tnageskso"/></td>
				<td><bean:message key="label.mha"/></td>
				</tr><tr class="title_light">
				<td style="height:16px;"><bean:message key="label.zainssu"/></td>
				<td><bean:message key="label.rsv"/></td>
		</tr></table>
	</td>
	<td></td>
	<td>
		<table border="0" style="width:73px;height:56px;" cellspacing="1" cellpadding="0"><tr class="title_light">
				<td style="height:16px;"><bean:message key="label.teiannsuu"/></td>
				</tr><tr class="title_light">
				<td style="height:16px;"><bean:message key="label.hskzaisuu"/></td>
				</tr><tr class="title_light">
				<td style="height:16px;"><bean:message key="label.avruri"/></td>
		</tr></table>
	</td>
	<td></td>
	<td>
		<table border="0" style="width:73px;height:56px;" cellspacing="1" cellpadding="0"><tr class="title_light">
				<td style="height:16px;"><bean:message key="label.zaisuu"/></td>
				</tr><tr class="title_light">
				<td style="height:16px;"><bean:message key="label.hacrkizen"/></td>
				</tr><tr class="title_light">
				<td style="height:16px;"><bean:message key="label.nyksum"/></td>
		</tr></table>
	</td>

		</tr>
	</table>

</body>

</html:html>

