<!-- 20世紀フォックス -->
<!DOCTYPE HTML>
<%@ page contentType="text/html; charset=Shift_JIS" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld"  prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-form.tld" prefix="form" %>
<%@ taglib uri="/WEB-INF/struts-nested-ex.tld" prefix="nested-ex" %>

<html:html>
<head>
<title><bean:message key="title.hachusyo"/></title>
<META http-equiv ="X-UA-Compatible" content ="IE=edge"/>
<META http-equiv="Pragma" content="no-cache">
<META http-equiv="Cache-Control" content="no-cache">
<META http-equiv="Expires" content="Thu, 01 Dec 1994 16:00:00 GMT">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">
<META http-equiv="Content-Type" content="text/html; charset=Shift_JIS">
<link href="theme/orders1.css<nested-ex:nocache-param />" rel="stylesheet" type="text/css">
<link href="theme/commonItemSize.css<nested-ex:nocache-param />" rel="stylesheet" type="text/css">

<script>
<!--
	function orders_print(){
		window.focus();
		window.print();
	}

// -->

</script>
<style>
/* 印刷時の余白幅指定 */
@page {
	margin: 20.0mm 15.0mm 10.0mm; /*上　左右　下*/
}
</style>
</head>

<body onLoad="orders_print();">

<html:form action="/printOrders" target="_self">
<logic:iterate name="printOrdersPages" id="printOrdersPage" property="ordersPageArr" type="com.jds.prodord.print.printorders.PrintOrdersPage"> 

<H2 style="margin:0px">
	<logic:equal name="printOrdersPage" property="fukSziFlg" value="false">
	  <bean:message key="label.prs"/>
	</logic:equal>
	<logic:equal name="printOrdersPage" property="fukSziFlg" value="true">
	  <bean:message key="label.fukshizai"/>
	</logic:equal>
	<bean:message key="label.hacsyo"/>
</H2>

<div style="text-align: right; margin-right: 25px; font-size : 13px; font-family: 'ＭＳ ゴシック',sans-serif;">
	<bean:write name="printOrdersPage" property="currentDate"/>
</div>

<table border="0" class="layout_table" style="width:680px;margin-left: 20px;"><tr><td valign="top" style="width:480px" class="layout_table"> 
  <table style="width:480px" border="1" cellspacing="0" cellpadding="2" class="details_table">
 	<tr>
 		<td class="header1" style="width:82px">
          <bean:message key="label.hacskicod"/>
	    </td>
        <td class="header1" style="width:347px;letter-spacing: 1.5em;border-right: 0px;">
          <bean:message key="label.hacskinm"/>
	    </td>
	    <td style="width:43px;border-left: 0px"></td>
        </tr>
    <tr>
      <td style="height:31px">
      	<div style="font-size:16px;font-weight:bold;vertical-align:middle;"><bean:write name="printOrdersPage" property="hacCod"/></div>
    </td>
      <td style="border-right: 0px">
      	<div style="font-size:16px;font-weight:bold;"><bean:write name="printOrdersPage" property="sirHacNm"/></div>
      </td>
      <td style="border-left: 0px;text-align:right;">
      	<div style="margin-right:3px;"><bean:message key="label.onchu"/></div>
      </td>   
        </tr>
  </table>
</td>
<td style="width:10px;border:none;"></td>
<td style="width:190px" class="layout_table" align="right" valign="top">
    <table style="width:184px" border="1" cellspacing="0" cellpadding="2" class="details_table">
      <tr> 
        <td class="header1">
	        <bean:message key="label.hacsyodte"/>
        </td>
	    <td class="header1">
        	<bean:message key="label.hacsyobng"/>
        </td>
      </tr>
      <tr> 
        <td style="height:31px">
        	<div style="font-size:16px;font-weight:bold;text-align:left;"><bean:write name="printOrdersPage" property="hacSyoDte"/></div>
        </td>
        <%-- 発注書番号・訂正回数 2006/04/28 add--%>
        <td style="padding: 0px;font-size:16px;font-weight:bold;">
        	<div style="width:63px;height:35px;text-align:center;float:left;border-right:1px solid;">
        		<div style="position:relative;top:9px;"><bean:write name="printOrdersPage" property="hacSyoBng"/></div>
        	</div>
        	<div style="position:relative;width:90px;text-align:center;top:9px;">
        		<bean:write name="printOrdersPage" property="teiNum"/>
        	</div>
        </td>
      </tr>
    </table>
</td></tr>

<tr><td colspan="3" class="layout_table" align="right">
   <table style="width:382px" cellspacing="0" cellpadding="2">
    <tr align="left">
      <td class="layout_table" valign="bottom">
        <div style="font-size : 15px; font-weight:bold; font-family : 'ＭＳ 明朝' , serif;padding-top: 3px;">
		    <bean:write name="printOrdersPage" property="kaiNmKj"/>
        </div>
      </td>
		<td colspan="2" rowspan="3" class="layout_table" valign="bottom">
    		<table style="width:132px"><tr>
		      <td class="header1 layout_table" valign="middle" style="width:128px;padding-top: 3px;">
		      	<html:img page="<%=printOrdersPage.getKaiLogoSrc()%>" style="width:114px;height:95px;" align="right" alt="" border="0"/>
		      </td>
    		</tr></table>
    	</td>
    </tr><tr align="left">
      <td class="layout_table" valign="bottom">
        <div style="font-size : 13px; font-family : 'ＭＳ 明朝' , serif;">
          <bean:message key="label.ybnmark"/><bean:write name="printOrdersPage" property="kaiYbnNo"/><br>
          <bean:write name="printOrdersPage" property="kaiAdr1Kj"/><br>
          <bean:write name="printOrdersPage" property="kaiAdr2Kj"/><br>
        </div>
      </td>
    </tr>
    <tr align="left">
      <td class="layout_table" valign="bottom">
        <div style="font-size : 13px; font-family : 'ＭＳ 明朝' , serif;">
          ＴＥＬ　<bean:write name="printOrdersPage" property="kaiTelNo"/><br>
          ＦＡＸ　<bean:write name="printOrdersPage" property="kaiFaxBng"/>
        </div>
      </td>
    </tr>
    
  </table>
            </td></tr>

<tr><td height="5" colspan="3" class="layout_table"></td></tr><tr><td colspan="3" class="layout_table">

  <table style="width:680px" border="1" cellspacing="0" cellpadding="2" class="details_table">
    <tr>
      <td class="header2" style="width:79px"><bean:message key="label.hacbng"/></td>
      <td class="header2" style="width:173px;letter-spacing: 1.5em;"><bean:message key="label.kigbng"/></td>
      <td style="width:89px" class="header2">
        <logic:equal name="printOrdersPage" property="fukSziFlg" value="false">
  		  	<bean:message key="label.setsuu"/>
		</logic:equal>
      	<logic:equal name="printOrdersPage" property="fukSziFlg" value="true">
  		  	<bean:message key="label.fukszinm"/>
		</logic:equal>
      </td>
      <td style="width:70px" class="header2"><bean:message key="label.hacsryo"/></td>
      <td class="header2" style="width:84px;letter-spacing: 1em;"><bean:message key="label.kin"/></td>
      <td class="header2" style="width:158px;letter-spacing: 1em;"><bean:message key="label.nouki"/></td>
    </tr>
    <tr> 
      <td class="header2" style="height:28px">
      	<bean:message key="label.kbn"/>
      </td>
      <td class="header2" style="letter-spacing: 1em;">
        <bean:message key="label.title"/><br>
        <bean:message key="label.hbidte"/>
      </td>
      <td colspan="2" class="header2" style="letter-spacing: 0.5em;"><bean:message key="label.nhnskinm"/></td>
      <td colspan="2" class="header2" style="padding:0px; letter-spacing: 0.5em;">
		<div style="width:254px;height:15px;text-align:center;border-bottom:1px solid #000000">
		   	<div style="position:relative;top:1px;"><bean:message key="label.bikou1"/></div>
		</div>
		<div style="position:relative;width:254px;text-align:center;top:1px;">
		   	<bean:message key="label.bikou2"/>
		</div>
      </td>
    </tr>
    
  <logic:iterate name="printOrdersPage" id="ordersRow" indexId="index"  property="ordersRowList" type="com.jds.prodord.print.printorders.PrintOrdersRow"> 
    <tr>
    </tr>
    <tr>
      <td align="center" style="height:18px">
      	<div style="font-size:16px; font-weight:bold; font-family: 'ＭＳ ゴシック',sans-serif;">
      		<bean:write name="ordersRow" property="gyoBng" ignore="true"/>
      	</div>
      </td>
      <td>
      	<div style="font-size:16px; font-weight:bold; font-family: 'ＭＳ ゴシック',sans-serif;">
      		<bean:write name="ordersRow" property="kigBng" ignore="true"/>
      	</div>
      </td>
      <td align="left">
      	<logic:equal name="printOrdersPage" property="fukSziFlg" value="false">
  		  <bean:write name="ordersRow" property="setSuu" ignore="true"/>
		</logic:equal>
      	<logic:equal name="printOrdersPage" property="fukSziFlg" value="true">
			<div style="font-size:11px;padding:0px;"><bean:write name="ordersRow" property="fukMeiKj" ignore="true"/></div>
		</logic:equal>
      </td>
      <td align="right">
      	<div style="font-size:15px;font-weight:bold; font-family: 'ＭＳ ゴシック',sans-serif;">
      		<bean:write name="ordersRow" property="hacSuu" ignore="true"/>
      	</div>
      </td>
      <%-- 金額 2006/04/28 add --%>
      <td align="right">
      	<%if(ordersRow.getKin()!=null && !ordersRow.getKin().toString().trim().equals("0") && !ordersRow.getKin().toString().trim().equals("0.00")){%>
      		<bean:write name="ordersRow" property="kin" ignore="true"/>
      	<%}%>
      </td>
      <td>
      	<div style="font-size:16px;font-weight:bold; font-family: 'ＭＳ ゴシック',sans-serif;">
      		<bean:write name="ordersRow" property="nki" ignore="true"/>
      	</div>
      </td>
    </tr>
    <tr> 
      <td align="center">
      	<div style="vertical-align:middle;font-size:15px; font-family: 'ＭＳ ゴシック',sans-serif;">
      		<bean:write name="ordersRow" property="sinKyuKbn" ignore="true"/>
      	</div>
      </td>
      <td style="height:31px" valign="top">
      	<div style="font-size:11px; padding-bottom: 6px;">
      		<bean:write name="ordersRow" property="titKj" ignore="true"/>
      	</div>
      	<bean:write name="ordersRow" property="hbiDte" ignore="true"/>
      </td>
      <td colspan="2" valign="top">
      	<div style="font-size:16px;font-weight:bold;">
      		<bean:write name="ordersRow" property="nhnMeiKj" ignore="true"/>
      	</div>
      </td>
      <td colspan="2" valign="top" style="padding:0px;">
		<div style="width:254px;height:18px;text-align:left;border-bottom:1px solid #000000">
	    	<div style="position:relative;top:3px;left:2px;font-size:12px;"><bean:write name="ordersRow" property="comment" ignore="true"/></div>
	    </div>
	    <div style="position:relative;width:254px;text-align:left;top:2px;left:2px;">
	      	<div style="font-size:15px;">
		    	<bean:write name="ordersRow" property="biKou2" ignore="true"/>
	      	</div>
	    </div>
      </td>
    </tr>
    
  </logic:iterate>
  
  </table>
</td></tr>
<tr><td colspan="3" class="layout_table">
 <br style="line-height: 50%;">
 <table style="width:690px" class="layout_table"><tr>
   <td style="width:36px" class="layout_table" valign="top">
     <div style="font-size : 10px; font-family : 'ＭＳ 明朝' , serif;">
       <bean:message key="label.chuu"/>
     </div>
   </td>
   <td style="width:345px" class="layout_table" valign="top">
     <div style="font-size : 10px; font-family : 'ＭＳ 明朝' , serif;">
       <bean:message key="label.kiteibun04"/>
     </div>
   </td>
   <%-- 更新履歴日 2006/04/28 add --%>
   <td style="width:300px" class="layout_table">
     <table cellspacing="0" style="width:300px;border: 0px none">
     	<tr valign="top" style="font-family : 'ＭＳ 明朝' , serif; ">
			<td style="width:59px;height:81px;border-right: 0px solid #000000; font-size : 10px;">
       			<bean:message key="label.no5"/><br></td>
			<td style="width:59px;border-right: 0px solid #000000;font-size : 10px;">
				<bean:message key="label.no4"/><br></td>
			<td style="width:59px;border-right: 0px solid #000000;font-size : 10px;">
				<bean:message key="label.no3"/><br></td>
			<td style="width:59px;border-right: 0px solid #000000;font-size : 10px;">
				<bean:message key="label.no2"/><br></td>
			<td style="width:59px;border-right: 1px solid #000000;font-size : 10px;">
				<bean:message key="label.no1"/><br></td>
		</tr>
	</table>
   </td>

</tr></table>
</td></tr>
</table>
<logic:equal name="printOrdersPage" property="pageBreak" value="true">
  <div class="page_break"></div>
</logic:equal>

</logic:iterate>
</html:form>
</body>
</html:html>
