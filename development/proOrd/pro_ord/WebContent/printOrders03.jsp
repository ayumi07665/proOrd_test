<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page contentType="text/html; charset=Shift_JIS" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld"  prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-form.tld" prefix="form" %>

<html:html>
<head>
<title><bean:message key="title.hachusyo"/></title>
<META http-equiv ="X-UA-Compatible" content ="IE=EmulateIE8"/>
<META http-equiv="Pragma" content="no-cache">
<META http-equiv="Cache-Control" content="no-cache">
<META http-equiv="Expires" content="Thu, 01 Dec 1994 16:00:00 GMT">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">
<META http-equiv="Content-Type" content="text/html; charset=Shift_JIS">
<link rel="stylesheet" href="theme/old_orders1.css" type="text/css">

<script language="JavaScript">
<!--
	function orders_print(){
		window.focus();
		window.print();
	}

// -->

</script>
<STYLE>
.font1{
	font-family: 'ＭＳ ゴシック',sans-serif;
}
.font2{
	font-family: 'ＭＳ 明朝' , serif;
}
.lineheight1_2{
	line-height: 1.1;
}
</STYLE>
</head>

<body onLoad="orders_print();" style="text-align:center">

<html:form action="/printOrders" target="_self">
<logic:iterate name="printOrdersPages" id="printOrdersPage" property="ordersPageArr" type="com.jds.prodord.print.printorders.PrintOrdersPage"> 

<H2 style="margin-top:5px; margin-bottom:0px">
	<logic:equal name="printOrdersPage" property="fukSziFlg" value="false">
	  <bean:message key="label.prs"/>
	</logic:equal>
	<logic:equal name="printOrdersPage" property="fukSziFlg" value="true">
	  <bean:message key="label.fukshizai"/>
	</logic:equal>
	<bean:message key="label.hacsyo"/>
</H2>

<div id="myClock" style="margin-left: 620px; margin-top:2px; font-size : 13px;" class="font1">
	<bean:write name="printOrdersPage" property="currentDate"/>
</div>

<table border="0" width="700" class="layout_table" style="margin-left: 20px;">
<tr><td valign="top" width="430" class="layout_table"> 
	<table width="100%" border="1" cellspacing="0" cellpadding="2" class="details_table">
		<tr>
			<td class="header1" width="22%">
				<bean:message key="label.hacskicod"/>
			</td>
			<td class="header1" width="70%" style="letter-spacing: 1.5em;border-right: 0px;">
				<bean:message key="label.hacskinm"/>
			</td>
			<td width="8%" style="border-left: 0px"></td>
		</tr>
		<tr>
			<%-- 発注先コード --%>
			<td height="37" align="center">
				<span style="font-size:16px;font-weight:bold;"><bean:write name="printOrdersPage" property="hacCod"/></span>　</td>
			<%-- 発注先名称 --%>
			<td style="border-right: 0px">
				<span style="font-size:16px;font-weight:bold;"><bean:write name="printOrdersPage" property="sirHacNm"/>　</span></td>
			<%-- 様 --%>
			<td style="border-left: 0px;text-align:right;">
				<span style="margin-right:3px;font-size:16px;font-weight:bold;"><bean:message key="label.sama"/></span></td>
		</tr>
	</table>
	</td>
	<td width="10"></td>
	<td class="layout_table" align="right" valign="top">
	<table width="80%" border="1" cellspacing="0" cellpadding="2" class="details_table">
		<tr>
			<td class="header1"><bean:message key="label.hacsyodte"/></td>
			<td class="header1"><bean:message key="label.hacsyobng"/></td>
		</tr>
		<tr align="center">
			<%-- 発注書日付 --%>
			<td height="37">
				<span style="font-size:16px;font-weight:bold;"><bean:write name="printOrdersPage" property="hacSyoDte"/>　</span></td>
			<%-- 発注書番号 --%>
			<td>
				<span style="font-size:16px;font-weight:bold;"><bean:write name="printOrdersPage" property="hacSyoBng"/>　</span></td>
		</tr>
	</table>
	</td>
</tr>
<tr><td colspan="3" class="layout_table" align="right">
	<br style="line-height: 50%;">
	<table width="28%" cellspacing="0" cellpadding="2" class="layout_table">
	<%-- 会社ロゴ --%>
	<tr><td class="layout_table">
			<div style="margin-left: 5px">
			<html:img page="<%=printOrdersPage.getKaiLogoSrc()%>" width="132" height="20" alt=""/></div>
		</td></tr>
	<tr><td height="3"></td></tr>
	<%-- 会社名 --%>
	<tr><td class="layout_table font1">
		<div style="font-size : 15px; font-weight:bold; margin-left: 10px;">
			<bean:write name="printOrdersPage" property="kaiNmKj"/></div></td></tr>
	 <tr><td height="3"></td></tr>
	<%-- 会社名２ --%>
	<tr><td class="layout_table font1">
		<bean:write name="printOrdersPage" property="kaiNmKj2"/></td></tr>
	<tr><td height="5"></td></tr>
	<%-- 郵便番号 --%>
	<tr><td class="layout_table font2 lineheight1_2">
	<bean:message key="label.ybnmark"/><bean:write name="printOrdersPage" property="kaiYbnNo"/><br>
	<%-- 住所 --%>
		<bean:write name="printOrdersPage" property="kaiAdr1Kj"/><br>
		<bean:write name="printOrdersPage" property="kaiAdr2Kj"/>
		<br style="line-height: 30%;">
		</td></tr>
	<%-- TEL・FAX --%>
	<tr><td class="layout_table font2 lineheight1_2">
		ＴＥＬ　<bean:write name="printOrdersPage" property="kaiTelNo"/><br>
		ＦＡＸ　<bean:write name="printOrdersPage" property="kaiFaxBng"/>
	</td></tr>
	</table>
</td></tr>
<tr>
	<td height="3" colspan="3" class="layout_table"></td>
</tr>
<tr><td colspan="3" class="layout_table">
	<table width="100%" border="1" cellspacing="0" cellpadding="2" class="details_table">
	<tr>
		<td class="header2" width="7%"><bean:message key="label.hacbng"/></td>
		<td width="21%" class="header2" style="letter-spacing: 1.5em;"><bean:message key="label.kigbng"/></td>
		<td width="10%" class="header2">
		<logic:equal name="printOrdersPage" property="fukSziFlg" value="false">
			<bean:message key="label.ketnm"/>
		</logic:equal>
		<logic:equal name="printOrdersPage" property="fukSziFlg" value="true">
			<div style="letter-spacing: 1.0em;">
				<bean:message key="label.cod"/>
			</div>
		</logic:equal>
		</td>
		<td width="7%" class="header2"><bean:message key="label.setsuu"/></td>
		<td width="9%" class="header2"><bean:message key="label.hacsryo"/></td>
		<td width="8%" class="header2" style="letter-spacing: 1em;"><bean:message key="label.tanka"/></td>
		<td width="8%" class="header2" style="letter-spacing: 1em;"><bean:message key="label.nouki"/></td>
	</tr>
	<tr> 
		<td class="header2" height="35">
			<bean:message key="label.kbn"/>
		</td>
		<td class="header2" style="letter-spacing: 1em;">
			<bean:message key="label.title"/><br>
			<bean:message key="label.artist"/>
		</td>
		<td class="header2" style="padding:0px;">
		<logic:equal name="printOrdersPage" property="fukSziFlg" value="false">
			<%-- 発売日 --%>
			<div style="width:100%;height:50%;text-align:center;border-bottom:1px solid #000000">
				<div style="position:relative;top:1px;"><bean:message key="label.hbidte2"/></div>
			</div>
			<%-- 仕様 --%>
			<div style="position:relative;width:100%;text-align:center;top:1px;">
				<bean:message key="label.spec"/>
			</div>
		</logic:equal>
		<logic:equal name="printOrdersPage" property="fukSziFlg" value="true">
			<span style="letter-spacing: 0.5em;">
				<bean:message key="label.fukszinm"/>
			</span>
		</logic:equal>
		</td>
		<td colspan="2" class="header2" style="letter-spacing: 0.5em;"><bean:message key="label.nhnskinm"/></td>
		<td colspan="2" class="header2" style="letter-spacing: 1.5em;"><bean:message key="label.bikou"/></td>
	</tr>
    
	<logic:iterate name="printOrdersPage" id="ordersRow" indexId="index"  property="ordersRowList" type="com.jds.prodord.print.printorders.PrintOrdersRow"> 
	<tr>
	</tr>
	<tr>
		<td align="center" height="23">
			<span style="font-size:16px; font-weight:bold;" class="font1">
			<%com.jds.prodord.print.printorders.PrintOrdersRow row = (com.jds.prodord.print.printorders.PrintOrdersRow)ordersRow;
				if(row.getGyoBng() != null)
					row.setGyoBng(Integer.parseInt(row.getGyoBng())+"");
			%>
			<bean:write name="ordersRow" property="gyoBng" ignore="true"/>　
			</span>
		</td>
		<td>
			<span style="font-size:16px; font-weight:bold;" class="font1">
				<bean:write name="ordersRow" property="kigBng" ignore="true"/>　
			</span>
		</td>
		<td>
			<logic:equal name="printOrdersPage" property="fukSziFlg" value="false">
				<bean:write name="ordersRow" property="ketNm" ignore="true"/>　
			</logic:equal>
			<logic:equal name="printOrdersPage" property="fukSziFlg" value="true">
				<bean:write name="ordersRow" property="prsFukSziCod" ignore="true"/>　
			</logic:equal>
		</td>
		<td align="right">
			　<bean:write name="ordersRow" property="setSuu" ignore="true"/>
		</td>
		<td align="right">
			<span style="font-size:16px;font-weight:bold;" class="font1">
				<bean:write name="ordersRow" property="hacSuu" ignore="true"/>
			</span>
		</td>
		<td>　</td>
		<td>
			<span style="font-size:16px;font-weight:bold;" class="font1">
				<bean:write name="ordersRow" property="nki" ignore="true"/>　
			</span>
		</td>
	</tr>
    <tr>
		<%-- 区分 --%>
		<td align="center">
			<span style="font-size:15px;" class="font1">
				<bean:write name="ordersRow" property="sinKyuKbn" ignore="true"/>
			</span>　
		</td>
		<%-- タイトル・アーティスト --%>
		<td height="37" valign="top">
			<div style="padding-bottom: 4px;">
				<bean:write name="ordersRow" property="titKj" ignore="true"/>
			</div>
			<bean:write name="ordersRow" property="artKj" ignore="true"/>　
		</td>
		<td valign="top" style="padding:0px;">
			<logic:equal name="printOrdersPage" property="fukSziFlg" value="false">
			<%-- 発売日 --%>
			<div style="width:100%;height:50%;text-align:center;border-bottom:1px solid #000000;margin-top: 2px">
				<div style="position:relative;top:1px;left:3px;">
					<bean:write name="ordersRow" property="hbiDte" ignore="true"/>
				</div>
			</div>
			<%-- 仕様 --%>
			<div style="position:relative;width:100%;"></div>
			</logic:equal>
			<logic:equal name="printOrdersPage" property="fukSziFlg" value="true">
				<bean:write name="ordersRow" property="fukMeiKj" ignore="true"/>　
			</logic:equal>
		 </td>
		<%-- 納品先名称 --%>
		<td colspan="2" valign="top">
			<span style="font-size:16px;font-weight:bold;" class="font1">
				<bean:write name="ordersRow" property="nhnMeiKj" ignore="true"/>　
			</span>
		</td>
		<%-- 備考 --%>
		<td colspan="2" valign="top">
			<bean:write name="ordersRow" property="comment" ignore="true"/>　
		</td>
	</tr>
	</logic:iterate>
	</table>
</td></tr>
<tr><td colspan="3" class="layout_table">
	<br style="line-height: 50%;">
	<table width="100%" class="layout_table">
		<tr>
			<td width="5%" class="layout_table lineheight1_2" valign="top">
				<div style="font-size : 10px;" class="font2">
					<bean:message key="label.chuu"/>　</div>
			</td>
			<td width="60%" class="layout_table lineheight1_2" valign="top">
				<div style="font-size : 10px;" class="font2">
					<bean:message key="label.kiteibun03"/>　</div>
			</td>
			<td width="35%" class="layout_table">
				<table width="100%" cellspacing="0" style="border: 0px none"><tr>
					<td width="33%" style="border-right: 0px" height="90">　</td>
					<td width="33%" style="border-right: 0px">　</td>
					<td width="33%" style="border-right: 1px solid #000000; letter-spacing: 0.5em;" align="center" valign="top" class="font2">
						<bean:message key="label.tanto"/>　</td>
				</tr></table>
			</td>
		</tr>
	</table>
	<%-- ユーザーＩＤ --%>
	<div style="width:100%; text-align:right;padding-top:2px;">
		<bean:write name="printOrdersPage" property="usrId"/>
	</div>
</td></tr>
</table>

<logic:equal name="printOrdersPage" property="pageBreak" value="true">
  <div class="page_break"></div>
</logic:equal>

</logic:iterate>
</html:form>
</body>
</html:html>
