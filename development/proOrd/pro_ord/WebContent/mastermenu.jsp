<!DOCTYPE HTML>
<%@ page contentType="text/html; charset=Shift_JIS" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld"  prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-form.tld" prefix="form" %>
<%@ taglib uri="/WEB-INF/struts-nested-ex.tld" prefix="nested-ex" %>
<html:html>
<HEAD>
<TITLE><bean:message key="title.mastermenu"/></TITLE>
<META http-equiv ="X-UA-Compatible" content ="IE=edge"/>
<META http-equiv="Pragma" content="no-cache">
<META http-equiv="Cache-Control" content="no-cache">
<META http-equiv="Expires" content="Thu, 01 Dec 1994 16:00:00 GMT">
<META name="GENERATOR" content="IBM WebSphere Studio">
<LINK rel="stylesheet" href="theme/prodord2.css<nested-ex:nocache-param />" type="text/css">
<LINK href="theme/commonItemSize.css<nested-ex:nocache-param />" rel="stylesheet" type="text/css">
</HEAD>
<BODY>
<hr>
<table border="0" cellspacing="0" cellpadding="0"><tr>
 <td style="width:170px">
   <div style="position:relative;">
     <img src="img/jds_lg.png">
   </div>
 </td>
 <td>
   <div style="position:absolute; left:190px; top:25px;z-index : 2;">
	 <img src="img/title_lg.png">
   </div>
 </td>
</tr></table>

<hr>
<div class="header1">
<bean:message key="header.mastermenu"/>
</div>

<div style="position:absolute; left:377px; top:17px; z-index : 1;">
<img src="img/cut01.png">
</div>
<%--
<div style="position:absolute; left:865px; top:20px; z-index : 1;">
<img src="img/jds20th_lg.png">
</div>
--%>
    <html:errors/>
    <logic:present  name="SERVICE_MESSAGE">
		<br><font color="red"><bean:write name="SERVICE_MESSAGE" /></font>
   	</logic:present>


<div align="right" style="padding-top:10px;padding-bottom:15px;">
<table style="border:0;" cellspacing="0" cellpadding="2">
	<tr>

	<td>
		<html:form action="/Gomain" target="_top">
			<html:submit property="submit" styleClass="button2" style="width:115px">
				<bean:message key="button.mainmenu" />
			</html:submit>
		</html:form>
	</td>

	<td>
	<html:form action="/Logoff" target="_parent">
		<html:submit property="submit" styleClass="button2" style="width:64px">
			<bean:message key="button.logoff"/>
		</html:submit>
	</html:form>
	</td>
	</tr>
</table>
</div>


<CENTER>

<table cellspacing="0">
 <tr><td style="width:10px;height:10px" class="borderR borderBtm">　</td>
  <td class="borderBtm">　</td>
  <td style="width:10px" class="borderL borderBtm">　</td><tr>
 <tr><td class="borderR">　</td><td style="padding:10px;">

<table>
  <tr>
  <td valign="top">
    <table border="0" style="width:400px" cellspacing="3">

      <logic:iterate id="masterMenu1" name="masterMenuControl" property="menu1">
    	<bean:define id="eachMenu1" name="masterMenu1" type="com.jds.prodord.menu.Menu"/>
            <tr>
            	<td align="right" style="height:40px">
	    			<html:link forward="<%=eachMenu1.getForwardPath()%>">
            			<img class="signal2">
            			　　<bean:write name="eachMenu1" property="menuName"/>　　
            			<img class="signal2">
           			</html:link>
	    		</td>
	    	</tr> 
      </logic:iterate>

    </table>
  </td>
  <td style="width:2px">
  </td>
  <td valign="top">
    <table border="0" style="width:400px" cellspacing="3">

      <logic:iterate id="masterMenu2" name="masterMenuControl" property="menu2">
	    <bean:define id="eachMenu2" name="masterMenu2" type="com.jds.prodord.menu.Menu"/>
            <tr>
            	<td align="left" style="height:40px">
	    			<html:link forward="<%=eachMenu2.getForwardPath()%>">
            			<img class="signal2">
            			　　<bean:write name="eachMenu2" property="menuName"/>　　
            			<img class="signal2">
           			</html:link>
	    		</td>
            </tr>
      </logic:iterate>

    </table>
  </td>
  </tr>
</table>


 </td><td class="borderL">　</td></tr>
 <tr><td style="height:10px" class="borderR borderTop"></td>
  <td class="borderTop">　</td>
  <td class="borderL borderTop">　</td><tr>
</table>

</CENTER>
<br><br><br>

</BODY>
</html:html>
