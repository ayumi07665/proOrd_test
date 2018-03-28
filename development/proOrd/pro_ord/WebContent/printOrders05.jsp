<!-- バンダイナムコアーツ -->
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

<div id="myClock" style="margin-left: 625px; font-size : 13px; font-family: 'ＭＳ ゴシック',sans-serif;">
<%--	<bean:write name="printOrdersPage" property="currentDate"/>--%><br>
</div>

<table border="0" class="layout_table" style="width:680px;margin-left: 20px;"><tr><td valign="top" style="width:400px" class="layout_table"> 
  <table style="width:400px" border="1" cellspacing="0" cellpadding="2" class="details_table">
 	<tr>
 		<td class="header1" style="width:85px">
          <bean:message key="label.hacskicod"/>
	    </td>
        <td class="header1" style="width:305px;letter-spacing: 1.5em;">
          <bean:message key="label.hacskinm"/>
	    </td>
    </tr>
    <tr>
      <td style="height:33px">
      	<div style="font-size:15px;font-weight:bold;vertical-align:middle;"><bean:write name="printOrdersPage" property="hacCod"/></div>
    </td>
      <td>
      	<div style="font-size:15px;font-weight:bold;"><bean:write name="printOrdersPage" property="sirHacNm"/></div>
      </td>
        </tr>
  </table>
</td>
<td style="width:10px;border:none;"></td>
<td class="layout_table" style="width:240px" align="right" valign="top">
    <table style="width:195px" border="1" cellspacing="0" cellpadding="2" class="details_table"><tr>
    	<td class="header1">
        	<bean:message key="label.hacsyodte"/></td>
		<td class="header1">
    		<bean:message key="label.hacsyobng"/></td>
	</tr><tr> 
    	<td style="height:33px" align="left">
    		<div style="font-size:16px;font-weight:bold;"><bean:write name="printOrdersPage" property="hacSyoDte"/></div></td>
        <%-- 発注書番号・訂正回数 2005/09/07 add--%>
        <td style="padding: 0px;font-size:16px;font-weight:bold;">
        	<div style="width:69px;height:37px;text-align:center;float:left;border-right:1px solid;">
        		<div style="position:relative;top:10px;"><bean:write name="printOrdersPage" property="hacSyoBng"/></div>
        	</div>
        	<div style="position:relative;width:92px;text-align:center;top:10px;">
        		<bean:write name="printOrdersPage" property="teiNum"/>
        	</div>
        </td>
	</tr></table>
</td></tr>

<tr><td colspan="3" class="layout_table" align="right">
   <table style="width:278px;margin-top:2px;" cellspacing="0" cellpadding="2">
     <tr>
     	<td colspan="3" class="layout_table">
    		<table style="width:278px;height:32px"><tr>
		      <td class="header1 layout_table" valign="middle" style="width:278px">
		      	<div style="font-size : 19px; font-family: 'ＭＳ ゴシック',sans-serif; font-weight:bold;">
		      	  <bean:write name="printOrdersPage" property="kaiNmKj"/>
		      	</div>
		      </td>
    		</tr></table>
    	</td>
    </tr>
    <tr>
      <td class="layout_table" valign="top" style="width:80px;text-align:left;">
        <div style="font-size : 12px; font-family : 'ＭＳ 明朝' , serif;">
          <bean:message key="label.ybnmark"/><bean:write name="printOrdersPage" property="kaiYbnNo"/>
        </div>
      </td>
      <td class="layout_table" colspan="2" style="width:200px;text-align:left;">
        <div style="font-size : 12px; font-family : 'ＭＳ 明朝' , serif;">
          <bean:write name="printOrdersPage" property="kaiAdr1Kj"/><br>
          <bean:write name="printOrdersPage" property="kaiAdr2Kj"/>
        </div>
      </td>
    </tr>
    <tr>
      <td class="layout_table" colspan="3" align="left">
        <div style="font-size : 12px; font-family : 'ＭＳ 明朝' , serif; height : 12px">
        </div>
      </td>
    </tr>
    
  </table>
            </td></tr>

<tr><td style="height:3px" colspan="3" class="layout_table"></td></tr><tr><td colspan="3" class="layout_table">

  <table style="width:680px" border="1" cellspacing="0" cellpadding="2" class="details_table">
    <tr>
      <td class="header2" style="width:80px"><bean:message key="label.hacbng"/></td>
      <td class="header2" style="width:140px;letter-spacing: 1em;"><bean:message key="label.kigbng"/></td>
      <td style="width:53px" class="header2"><bean:message key="label.hbidte"/></td>
      <logic:equal name="printOrdersPage" property="fukSziFlg" value="false">
        <td style="width:38px" class="header2">
  		  	<bean:message key="label.ket"/>
		</td>
		<td style="width:35px" class="header2">
  		  	<bean:message key="label.setsuu2"/>
  		</td>  	
	  </logic:equal>
      <logic:equal name="printOrdersPage" property="fukSziFlg" value="true">
		<td colspan="2" style="width:75px" class="header2">
  		  	<bean:message key="label.fukszicod"/>
  		</td>  	
	  </logic:equal>
      <td style="width:74px" class="header2"><bean:message key="label.hacsryo"/></td>
      <td class="header2" style="width:74px;letter-spacing: 1.0em;"><bean:message key="label.nouki"/></td>
      <td class="header2" style="width:140px;letter-spacing: 1.0em;"><bean:message key="label.nhnskinm"/></td>
    </tr>
    <tr> 
      <td class="header2" style="height:27px">
      	<bean:message key="label.kbn"/>
      </td>
      <td colspan="2" class="header2" style="letter-spacing: 1em;">
        <bean:message key="label.title"/><br>
        <bean:message key="label.artist"/>
      </td>
      <td colspan="2" class="header2" style="padding:0px;">
        <logic:equal name="printOrdersPage" property="fukSziFlg" value="false">
			<div style="width:80px;height:15px;text-align:center;border-bottom:1px solid #000000">
		    	<div style="position:relative;top:1px;font-size:9pt"><bean:message key="label.shizai"/><bean:message key="label.zaisuu"/></div>
		    </div>
		    <div style="position:relative;width:80px;text-align:center;top:1px;">
		    	<bean:message key="label.tax_inc2"/>
		    </div>
		</logic:equal>
      	<logic:equal name="printOrdersPage" property="fukSziFlg" value="true">
      	  <div><bean:message key="label.fukszinm"/></div>
		</logic:equal>
      
      </td>
      <td class="header2" style="letter-spacing: 1.0em;"><bean:message key="label.tanka"/></td>
      <td class="header2" style="letter-spacing: 1.0em;"><bean:message key="label.kin"/></td>
      <td class="header2" style="letter-spacing: 1.0em;"><bean:message key="label.bikou"/></td>
    </tr>
    
  <logic:iterate name="printOrdersPage" id="ordersRow" indexId="index"  property="ordersRowList" type="com.jds.prodord.print.printorders.PrintOrdersRow"> 
	<tr>
	</tr>
    <tr>
      <td align="center" style="height:18px">
      	<div style="font-size:15px; font-weight:bold; font-family: 'ＭＳ 明朝',sans-serif;">
      		<bean:write name="ordersRow" property="gyoBng" ignore="true"/>
      	</div>
      </td>
      <td>
      	<div style="font-size:15px; font-weight:bold; font-family: 'ＭＳ ゴシック',sans-serif;">
      		<bean:write name="ordersRow" property="kigBng" ignore="true"/>
      	</div>
      </td>
      <td align="right">
		<bean:write name="ordersRow" property="hbiDte" ignore="true"/>
      </td>
	  <logic:equal name="printOrdersPage" property="fukSziFlg" value="false">
        <td align="left">
      	  <div style="white-space:nowrap; font-size:9px; font-family: 'ＭＳ ゴシック',sans-serif;">
			<bean:write name="ordersRow" property="ketNm" ignore="true"/>
		  </div>
        </td>
        <td align="right"><bean:write name="ordersRow" property="setSuu" ignore="true"/></td>
	  </logic:equal>
      <logic:equal name="printOrdersPage" property="fukSziFlg" value="true">
        <td colspan="2" align="right"><bean:write name="ordersRow" property="prsFukSziCod" ignore="true"/></td>
	  </logic:equal>
      <td align="right">
      	<div style="font-size:13px;font-weight:bold; font-family: 'ＭＳ ゴシック',sans-serif;">
      		<bean:write name="ordersRow" property="hacSuu" ignore="true"/>
      	</div>
      </td>

      <td align="right">
      	<div style="font-size:15px;font-weight:bold; font-family: 'ＭＳ ゴシック',sans-serif;"><bean:write name="ordersRow" property="nki" ignore="true"/></div>
      </td>
      <td>
      	<div style="white-space:nowrap;font-size:13px;font-weight:bold;">
      		<bean:write name="ordersRow" property="nhnMeiKj" ignore="true"/>
      	</div>
      </td>

    </tr>
     <tr> 
      <td align="center">
      	<div style="vertical-align:middle;font-size:15px; font-family: 'ＭＳ ゴシック',sans-serif;">
      		<bean:write name="ordersRow" property="sinKyuKbn" ignore="true"/>
      	</div>
      </td>
      <td colspan="2" style="height:29px" valign="top">
      	<div style="padding-bottom: 4px;">
      		<bean:write name="ordersRow" property="titKj" ignore="true"/>
      	</div>
      	<div style="white-space:nowrap;margin-bottom:-12px;"> 
      	<bean:write name="ordersRow" property="artKj" ignore="true"/>
      	</div>
      </td>
      <td colspan="2" valign="top" style="padding:0px;">
      	<logic:equal name="printOrdersPage" property="fukSziFlg" value="false">
			<div style="width:80px;height:17px;text-align:right;border-bottom:1px solid #000000">
		    	<div style="position:relative;top:2px;right:3px;">
		    		<bean:write name="ordersRow" property="fukSziSuu" ignore="true"/>
				</div>
		    </div>
		    <div style="position:relative;width:80px;text-align:right;top:2px;right:2px;">
					<!-- 税込定価修正 04/08/17-->
					<%if(ordersRow.getZikTik()!=null && !ordersRow.getZikTik().equals("null")
					 && !ordersRow.getZikTik().equals("")){%>
			    		<bean:write name="ordersRow" property="zikTik" ignore="true"/>
			    		<bean:message key="label.en"/>
					<%}%>
		    </div>
		</logic:equal>
      	<logic:equal name="printOrdersPage" property="fukSziFlg" value="true">
      		<div style="word-break:break-all"><bean:write name="ordersRow" property="fukMeiKj" ignore="true"/></div>
		</logic:equal>
      </td>
      <%-- 単価 2008/03/10 add --%>
      <td align="right" valign="bottom">
      	<%if(ordersRow.getTan2()!=null && !ordersRow.getTan2().toString().trim().equals("0.00")){%>
      		<bean:write name="ordersRow" property="tan2" ignore="true"/>
      	<%}%>
      </td>
      <%-- 金額 2005/09/13 add --%>
      <td align="right" valign="bottom">
      	<%if(ordersRow.getKin()!=null && !ordersRow.getKin().toString().trim().equals("0")){%>
      		<bean:write name="ordersRow" property="kin" ignore="true"/>
      	<%}%>
      </td>

      <td valign="top">
      	<bean:write name="ordersRow" property="comment" ignore="true"/>
      </td>
    </tr>
  </logic:iterate>
  
  </table>
</td></tr>
<tr><td colspan="3" class="layout_table">
 <table style="width:680px;margin-top:5px;" class="layout_table">
 <tr>
   <td style="width:30px" class="layout_table" valign="top">
     <div style="font-size : 10px; font-family : 'ＭＳ 明朝' , serif;">
       <bean:message key="label.chuu"/>
     </div>
   </td>
   <td style="width:344px" class="layout_table" valign="top">
     <div style="font-size : 10px; font-family : 'ＭＳ 明朝' , serif;">
       <bean:message key="label.kiteibun05"/>
     </div>
   </td>
   <%-- 更新履歴日 2005/09/06 add --%>
   <td style="width:300px" class="layout_table">
     <table cellspacing="0" style="width:300px;border: 0px none">
     	<tr valign="top" style="font-family : 'ＭＳ 明朝' , serif; ">
			<td style="width:60px;height:63px;border-right: 0px solid #000000; font-size : 10px;">
       			<bean:message key="label.no5"/></td>
			<td style="width:60px;border-right: 0px solid #000000;font-size : 10px;">
				<bean:message key="label.no4"/></td>
			<td style="width:60px;border-right: 0px solid #000000;font-size : 10px;">
				<bean:message key="label.no3"/></td>
			<td style="width:60px;border-right: 0px solid #000000;font-size : 10px;">
				<bean:message key="label.no2"/></td>
			<td style="width:60px;border-right: 1px solid #000000;font-size : 10px;">
				<bean:message key="label.no1"/></td>
		</tr>
     	<tr valign="top" style="font-family : 'ＭＳ 明朝' , serif; ">
			<td style="width:60px;height:65px;border-right: 0px solid #000000; font-size : 10px;">
       			<br></td>
			<td style="width:60px;border-right: 0px solid #000000;font-size : 10px;">
				<br></td>
			<td style="width:60px;border-right: 0px solid #000000;font-size : 10px;">
				<br></td>
			<td style="width:60px;border-right: 0px solid #000000;font-size : 10px;">
				<br></td>
			<td style="width:60px;border-right: 1px solid #000000;font-size : 10px;">
				<br></td>
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
