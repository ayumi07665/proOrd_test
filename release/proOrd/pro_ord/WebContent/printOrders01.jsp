<!-- バップ -->
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page contentType="text/html; charset=Shift_JIS" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld"  prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-form.tld" prefix="form" %>
<%@ taglib uri="/WEB-INF/struts-nested-ex.tld" prefix="nested-ex" %>

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
<link rel="stylesheet" href="theme/old_orders1.css<nested-ex:nocache-param />" type="text/css">

<script language="JavaScript">
<!--
	function orders_print(){
		window.focus();
		window.print();
	}

// -->

</script>
</head>

<body onLoad="orders_print();">

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

<div id="myClock" style="margin-left: 625px; margin-top:2px; font-size : 13px; font-size : 13px; font-family: 'ＭＳ ゴシック',sans-serif;">
<%--	<bean:write name="printOrdersPage" property="currentDate"/>--%><br>
</div>

<table border="0" width="700" class="layout_table" style="margin-left: 20px;"><tr><td valign="top" width="430" class="layout_table"> 
  <table width="100%" border="1" cellspacing="0" cellpadding="2" class="details_table">
 	<tr>
 		<td class="header1" width="22%">
          <bean:message key="label.hacskicod"/>
	    </td>
        <td class="header1" width="78%" style="letter-spacing: 1.5em;">
          <bean:message key="label.hacskinm"/>
	    </td>
    </tr>
    <tr>
      <td height="37">
      	<span style="font-size:16px;font-weight:bold;"><bean:write name="printOrdersPage" property="hacCod"/></span>
    　</td>
      <td>
      	<span style="font-size:16px;font-weight:bold;"><bean:write name="printOrdersPage" property="sirHacNm"/>　</span>
      </td>
        </tr>
  </table>
</td>
<td width="10"></td>
<td class="layout_table" width="260" align="right" valign="top">
    <table width="80%" border="1" cellspacing="0" cellpadding="2" class="details_table"><tr>
    	<td class="header1">
        	<bean:message key="label.hacsyodte"/></td>
		<td class="header1">
    		<bean:message key="label.hacsyobng"/></td>
	</tr><tr> 
    	<td height="37">
    		<span style="font-size:16px;font-weight:bold;"><bean:write name="printOrdersPage" property="hacSyoDte"/></span></td>
        <%-- 発注書番号・訂正回数 2005/09/07 add--%>
        <td style="padding: 0px;font-size:16px;font-weight:bold;">
        	<div style="width:70%;height:100%;text-align:center;float:left;border-right:1px solid;">
        		<div style="position:relative;top:9px;"><bean:write name="printOrdersPage" property="hacSyoBng"/></div>
        	</div>
        	<div style="position:relative;width:100%;text-align:center;top:9px;">
        		<bean:write name="printOrdersPage" property="teiNum"/>
        	</div>
        </td>
	</tr></table>
</td></tr>

<tr><td colspan="3" class="layout_table" align="right">
	<br style="line-height: 50%;">
   <table width="40%" cellspacing="0" cellpadding="2">
     <tr>
     	<td colspan="3" class="layout_table">
    		<table width="100%"><tr>
		      <td class="header1 layout_table" valign="middle" width="60%">
		      	<div style="font-size : 20px; font-family: 'ＭＳ ゴシック',sans-serif; font-weight:bold;">
		      	  <bean:write name="printOrdersPage" property="kaiNmKj"/>
		      	</div>
		      </td>
		      <td class="layout_table">
		        <html:img page="<%=printOrdersPage.getKaiLogoSrc()%>" width="56" height="38" align="right" alt="" border="0"/>
		      </td>
    		</tr></table>
    	</td>
    </tr>
    <tr>
      <td class="layout_table" valign="top">
        <div style="font-size : 13px; font-family : 'ＭＳ 明朝' , serif;">
          <bean:message key="label.ybnmark"/><bean:write name="printOrdersPage" property="kaiYbnNo"/>
        </div>
      </td>
      <td class="layout_table" colspan="2" width="70%">
        <div style="font-size : 13px; font-family : 'ＭＳ 明朝' , serif;">
          <bean:write name="printOrdersPage" property="kaiAdr1Kj"/>
          <bean:write name="printOrdersPage" property="kaiAdr2Kj"/>
        </div>
      </td>
    </tr>
    <tr>
      <td class="layout_table" colspan="3">
        <div style="font-size : 13px; font-family : 'ＭＳ 明朝' , serif;">
		ＴＥＬ:<bean:write name="printOrdersPage" property="kaiTelNo"/>
		 ＦＡＸ:<bean:write name="printOrdersPage" property="kaiFaxBng"/>
        </div>
      </td>
    </tr>
    
  </table>
            </td></tr>

<tr><td height="10" colspan="3" class="layout_table"></td></tr><tr><td colspan="3" class="layout_table">

  <table width="100%" border="1" cellspacing="0" cellpadding="2" class="details_table">
    <tr>
      <td class="header2" width="11%"><bean:message key="label.hacbng"/></td>
      <td width="16%" class="header2" style="letter-spacing: 1em;"><bean:message key="label.kigbng"/></td>
      <td width="7%" class="header2"><bean:message key="label.hbidte"/></td>
      <logic:equal name="printOrdersPage" property="fukSziFlg" value="false">
        <td width="5%" class="header2">
  		  	<bean:message key="label.ket"/>
		</td>
		<td width="5%" class="header2">
  		  	<bean:message key="label.setsuu2"/>
  		</td>  	
	  </logic:equal>
      <logic:equal name="printOrdersPage" property="fukSziFlg" value="true">
		<td colspan="2" width="10%" class="header2">
  		  	<bean:message key="label.fukszicod"/>
  		</td>  	
	  </logic:equal>
      <td width="10%" class="header2"><bean:message key="label.hacsryo"/></td>
      <td width="10%" class="header2" style="letter-spacing: 1.0em;"><bean:message key="label.nouki"/></td>
      <td width="17%" class="header2" style="letter-spacing: 1.0em;"><bean:message key="label.nhnskinm"/></td>
    </tr>
    <tr> 
      <td class="header2" height="35">
      	<bean:message key="label.kbn"/>
      </td>
      <td colspan="2" class="header2" style="letter-spacing: 1em;">
        <bean:message key="label.title"/><br>
        <bean:message key="label.artist"/>
      </td>
      <td colspan="2" class="header2" style="padding:0px;">
        <logic:equal name="printOrdersPage" property="fukSziFlg" value="false">
			<div style="width:100%;height:50%;text-align:center;border-bottom:1px solid #000000">
		    	<div style="position:relative;top:1px;font-size:9pt"><bean:message key="label.shizai"/><bean:message key="label.zaisuu"/></div>
		    </div>
		    <div style="position:relative;width:100%;text-align:center;top:1px;">
		    	<bean:message key="label.tax_exc"/>
		    </div>
		</logic:equal>
      	<logic:equal name="printOrdersPage" property="fukSziFlg" value="true">
      	  <span>
  		  <bean:message key="label.fukszinm"/>
  		  </span>
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
      <td align="center" height="23">
      	<span style="font-size:16px; font-weight:bold; font-family: 'ＭＳ 明朝',sans-serif;">
      	<%com.jds.prodord.print.printorders.PrintOrdersRow row = (com.jds.prodord.print.printorders.PrintOrdersRow)ordersRow;
      	  if(row.getGyoBng() != null)
      	  	row.setGyoBng(printOrdersPage.getHacSyoBng()+row.getGyoBng());
      	%>
      		<bean:write name="ordersRow" property="gyoBng" ignore="true"/>　
      	</span>
      </td>
      <td>
      	<span style="font-size:16px; font-weight:bold; font-family: 'ＭＳ ゴシック',sans-serif;">
      		<bean:write name="ordersRow" property="kigBng" ignore="true"/>　
      	</span>
      </td>
      <td align="right">
		<bean:write name="ordersRow" property="hbiDte" ignore="true"/>
      </td>
	  <logic:equal name="printOrdersPage" property="fukSziFlg" value="false">
        <td align="left">
      	  <span style="font-size:9px; font-family: 'ＭＳ ゴシック',sans-serif;">
			<bean:write name="ordersRow" property="ketNm" ignore="true"/>　
		  </span>
        </td>
        <td align="right">
  		  <bean:write name="ordersRow" property="setSuu" ignore="true"/>　
        </td>
	  </logic:equal>
      <logic:equal name="printOrdersPage" property="fukSziFlg" value="true">
        <td colspan="2" align="right">
  		  <bean:write name="ordersRow" property="prsFukSziCod" ignore="true"/>　
        </td>
	  </logic:equal>
      <td align="right">
      	<span style="font-size:14px;font-weight:bold; font-family: 'ＭＳ ゴシック',sans-serif;">
      		<bean:write name="ordersRow" property="hacSuu" ignore="true"/>
      	</span>
      </td>

      <td align="right">
      	<span style="font-size:16px;font-weight:bold; font-family: 'ＭＳ ゴシック',sans-serif;">
      		<bean:write name="ordersRow" property="nki" ignore="true"/>　
      	</span>
      </td>
      <td>
      	<span style="font-size:14px;font-weight:bold;">
      		<bean:write name="ordersRow" property="nhnMeiKj" ignore="true"/>　
      	</span>
      </td>

    </tr>
     <tr> 
      <td align="center">
      	<span style="font-size:15px; font-family: 'ＭＳ ゴシック',sans-serif;">
      		<bean:write name="ordersRow" property="sinKyuKbn" ignore="true"/>
      	</span>　
      </td>
      <td colspan="2" height="37" valign="top">
      	<div style="padding-bottom: 4px;">
      		<bean:write name="ordersRow" property="titKj" ignore="true"/>
      	</div>
      	<bean:write name="ordersRow" property="artKj" ignore="true"/>　
      </td>
      <td colspan="2" valign="top" style="padding:0px;">
      	<logic:equal name="printOrdersPage" property="fukSziFlg" value="false">
			<div style="width:100%;height:50%;text-align:right;border-bottom:1px solid #000000">
		    	<div style="position:relative;top:1px;right:3px;">
		    		<bean:write name="ordersRow" property="fukSziSuu" ignore="true"/>
				</div>
		    </div>
		    <div style="position:relative;width:100%;text-align:right;top:2px;right:2px;">
					<!-- 税込定価修正 04/08/17 ⇒ 税抜価格に修正 14/02/19-->
					<%if(ordersRow.getTka()!=null && !ordersRow.getTka().equals("null")
					 && !ordersRow.getTka().equals("")){%>
			    		<bean:write name="ordersRow" property="tka" ignore="true"/>
			    		<bean:message key="label.en"/>
					<%}%>
		    </div>
		</logic:equal>
      	<logic:equal name="printOrdersPage" property="fukSziFlg" value="true">
  		  	<bean:write name="ordersRow" property="fukMeiKj" ignore="true"/>　
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
 <br style="line-height: 50%;">
 <table width="100%" class="layout_table">
 <tr>
   <td width="5%" class="layout_table" valign="top">
     <div style="font-size : 10px; font-family : 'ＭＳ 明朝' , serif;">
       <bean:message key="label.chuu"/>　
     </div>
   </td>
   <td width="50%" class="layout_table" valign="top">
     <div style="font-size : 10px; font-family : 'ＭＳ 明朝' , serif;">
       <bean:message key="label.kiteibun01"/>　
     </div>
   </td>
   <%-- 更新履歴日 2005/09/06 add --%>
   <td width="45%" class="layout_table">
     <table width="100%" cellspacing="0" style="border: 0px none">
     	<tr valign="top" style="font-family : 'ＭＳ 明朝' , serif; ">
			<td width="20%" height="70" style="border-right: 0px solid #000000; font-size : 10px;">
       			<bean:message key="label.no5"/></td>
			<td width="20%" style="border-right: 0px solid #000000;font-size : 10px;">
				<bean:message key="label.no4"/></td>
			<td width="20%" style="border-right: 0px solid #000000;font-size : 10px;">
				<bean:message key="label.no3"/></td>
			<td width="20%" style="border-right: 0px solid #000000;font-size : 10px;">
				<bean:message key="label.no2"/></td>
			<td width="20%" style="border-right: 1px solid #000000;font-size : 10px;">
				<bean:message key="label.no1"/></td>
		</tr>
     	<tr valign="top" style="font-family : 'ＭＳ 明朝' , serif; ">
			<td width="20%" height="70" style="border-right: 0px solid #000000; font-size : 10px;">
       			<br></td>
			<td width="20%" style="border-right: 0px solid #000000;font-size : 10px;">
				<br></td>
			<td width="20%" style="border-right: 0px solid #000000;font-size : 10px;">
				<br></td>
			<td width="20%" style="border-right: 0px solid #000000;font-size : 10px;">
				<br></td>
			<td width="20%" style="border-right: 1px solid #000000;font-size : 10px;">
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
