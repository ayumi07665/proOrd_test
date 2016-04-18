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
<TITLE><bean:message key="title.teiansyokaishiji"/></TITLE>
<LINK rel="stylesheet" href="theme/prodord1.css<nested-ex:nocache-param />" type="text/css">
</HEAD>
<BODY>
	
<hr>
<html:errors/>
<hr>
<table style="width:295px">
		<tr>
            <td>
				<html:form action="/teiansuuRefer" target="_top">
					<html:submit property="submit" styleClass="button2">
						<bean:message key="button.backtoindicate"/>
					</html:submit>
				<html:hidden property="command" value="INIT"/>
				</html:form>
			</td>
        </tr>
     </table>
</BODY>
</html:html>
	