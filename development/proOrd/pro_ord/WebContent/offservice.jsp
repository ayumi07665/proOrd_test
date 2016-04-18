<!DOCTYPE HTML>

<%@ page contentType="text/html; charset=Shift_JIS" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld"  prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-form.tld" prefix="form" %>
<%@ taglib uri="/WEB-INF/struts-nested-ex.tld" prefix="nested-ex" %>

<html:html>

<HEAD>

<META http-equiv ="X-UA-Compatible" content ="IE=edge"/>
<META http-equiv="Pragma" content="no-cache">
<META http-equiv="Cache-Control" content="no-cache">
<META http-equiv="Expires" content="Thu, 01 Dec 1994 16:00:00 GMT">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">
<META http-equiv="Content-Type" content="text/html; charset=Shift_JIS">

<TITLE></TITLE>
<link rel="stylesheet" href="theme/prodord2.css<nested-ex:nocache-param />" type="text/css">
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

<div style="position:absolute; left:377px; top:17px; z-index : 1;">
<img src="img/cut01.png">
</div>
<%--
<div style="position:absolute; left:865px; top:20px; z-index : 1;">
<img src="img/jds20th_lg.png">
</div>
--%>

<br>
<DIV style="font-size:20px; font-weight:bold; color:#3a4175;">
<bean:message key="errors.offservice"/>
</DIV>
<br><br>
<table style="width:372px">
		<tr>
            <td>
				<html:form action="/Logoff" target="_parent">
					<html:submit property="submit" styleClass="button2">
						<bean:message key="button.back"/>
					</html:submit>
				</html:form>
			</td>
        </tr>
     </table>
</BODY>

</html:html>

