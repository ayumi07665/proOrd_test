<!DOCTYPE HTML>
<%@ page contentType="text/html; charset=Shift_JIS" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld"  prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-form.tld" prefix="form" %>
<%@ taglib uri="/WEB-INF/struts-nested-ex.tld" prefix="nested-ex" %>

<html:html>
<HEAD>
<TITLE><bean:message key="title.ketszitbl"/></TITLE>
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
	function nextfocus(keta,mx,No){
		if(mx <= keta){
			document.all['date'+ (No + 1)].focus();
			document.all['date'+ (No + 1)].select();
		}
	}

	function formSubmit(){
		return true;
	}

	function setPrcKbn(i){
		if(i.form.prcKbn.value == "3")
			return;
		else
			i.form.prcKbn.value = "3";
	}
	
	function pageMove(i){
		if(i.id == "detail_L"){
			detail_R.scrollTop = detail_L.scrollTop;
		}
		else if(i.id == "detail_R"){
			detail_L.scrollTop = detail_R.scrollTop;
		}
	}

		
//-->

</SCRIPT>
<LINK href="theme/prodord1.css<nested-ex:nocache-param />" rel="stylesheet" type="text/css">
<LINK href="theme/commonItemSize.css<nested-ex:nocache-param />" rel="stylesheet" type="text/css">
</HEAD>
<!--<BODY onLoad="checkUpdatable()">-->
<BODY>
<html:form action = "/ketSziTbl" target="_self" onsubmit="return acceptSubmit();">
<bean:define id="fmForm" name="ketSziTblForm" type="com.jds.prodord.master.ketszitbl.KetSziTblForm"/>

<html:hidden property="updatable" name="updatable" value="<%=fmForm.getUpdatable()%>"/>

<H3 style="position:relative;top:-6px;margin-bottom:3px;"><bean:message key="title.ketszitbl2"/></H3>
	<table style="height:27px">
		<tr>
			<td style="width:100px" class="title_dark">
			<bean:message key="label.daikai"/>
			</td><td style="width:62px" class="title_light">
				<table style="width:62px;height:24px;" cellspacing="2" cellpadding="2" class="details">
				<tr><td align="center">
					<bean:write name="fmForm" property="queryDaiKaiSkbCod" ignore="true"/>
				</td></tr></table>
			</td>
		</tr>
	</table>
<hr>
<div style="padding-bottom:3px;">
<logic:present  name="INFOMSG_KEY">
	<DIV class="info_msg"><bean:write name="INFOMSG_KEY" /></DIV>
</logic:present>
<logic:present  name="SERVICE_MESSAGE">
	<DIV class="service_msg"><bean:write name="SERVICE_MESSAGE" /></DIV>
</logic:present>
<html:errors/>
</div>
	<CENTER>
	<table align="center" cellspacing="3" style="width:250px">
		<tr>
          <td  align="center" class="title_dark" style="height:18px"><bean:message key="label.syorikbn"/></td>
          <td  align="center" class="title_dark" style="height:18px"><bean:message key="label.ketcod"/></td>
          <td></td>
        </tr>
		<tr><td align="center">
		    <html:select property="prcKbn" value="<%=fmForm.getPrcKbn()%>">
		   			<html:option value="1"><bean:message key="button.search"/></html:option>
					<html:option value="3"><bean:message key="button.update"/></html:option>
			</html:select>

		</td>
		<td align="center">
		   	<html:select property="ketCod" styleClass="s_4" multiple="true" style="width:108px;height:56px">
		   		<html:options property="vl_ketCod" labelProperty="lb_ketCod"/>
		   	</html:select>
		</td>
		</tr>
	</table>
<BR>	
<div>
	<table cellspacing="0" cellpadding="0" align="center">
		<tr>
			<td>
			<div id="header_L" style="width:365px;overflow:scroll;">
				<table border="1" cellspacing="1" cellpadding="0" style="width:340px;border-bottom-style:none;margin-top:-3px;">
					<tr><td><table border="0" bgcolor="#336666"  cellspacing="1" cellpadding="1" style="width:334px;border-bottom-style:none;"><tr class="title_light">
				     <tr class="title_light">
				     	<td style="width:23px;height:16px;"> </td>
						<td class="title_light" style="width:62px"><bean:message key="label.ketcod"/></td>
						<td class="title_light" style="width:121px"><bean:message key="label.ketnm"/></td>
						<td class="title_light" style="width:121px"><bean:message key="label.ketnm2"/></td>
				     </tr>
					</table></td></tr>
				</table>
			</div>
			</td>
			<td>
			<div id="header_R" style="width:482px;overflow:scroll;">
				<table border="1"  cellspacing="1" cellpadding="0" style="width:460px;border-bottom-style:none;margin-top:-3px;">
					<tr><td><table border="0" bgcolor="#336666" cellspacing="1" cellpadding="1" style="width:454px;border-bottom-style:none;"><tr class="title_light">
				    <tr class="title_light">
						<td class="title_light" style="height:16px"><bean:message key="label.koseiszi"/></td>
					</tr>
					</table></td></tr>
				</table>
			</div>
			</td>
		</tr>
		<tr>
			<td>
			<div id="detail_L" style="width:365px;height:287px;overflow:scroll;" onScroll="pageMove(this)">
				<table border="1"  cellspacing="1" cellpadding="0" style="width:340px;margin-top:-4px;">
					<tr><td><table border="0" bgcolor="#336666" cellspacing="1" cellpadding="1" style="width:334px;border-bottom-style:none;"><tr class="title_light">
					<logic:iterate name="ketSziTblForm" id="formRow" indexId="index"  property="formRowList" type="com.jds.prodord.master.ketszitbl.KetSziTblFormRow"> 
				    <tr>
				        <td bgcolor="#FFFFFF" style="width:22px;height:22px;">
				            <html:checkbox name="formRow" property="check_bx" value="true" indexed="true" onclick="setPrcKbn(this)"/>
				        </td>
				        <td bgcolor="#FFFFFF" style="width:60px;height:22px;">
       	                    <bean:write name="formRow" property="ketCod" ignore="true" />
				        </td>
			            <td bgcolor="#FFFFFF" style="width:117px;height:22px;">
							<bean:write name="formRow" property="ketNm" ignore="true" />
				        </td>
			            <td bgcolor="#FFFFFF" style="width:117px;height:22px;">
							<bean:write name="formRow" property="ketNm2" ignore="true" />
				        </td>
					</tr>
					</logic:iterate> 
					</table></td></tr>
				</table>
			</div>
			</td>
			<td>
			<div id="detail_R" style="width:482px;height:287px;overflow:scroll;" onScroll="pageMove(this)">
				<table border="1" cellspacing="1" cellpadding="0" style="margin-top:-4px;">
					<tr><td><table border="0" bgcolor="#336666" cellspacing="1" cellpadding="1" style="border-bottom-style:none;"><tr class="title_light">
					<logic:iterate name="ketSziTblForm" id="formRow" indexId="index1"  property="formRowList"  type="com.jds.prodord.master.ketszitbl.KetSziTblFormRow">
					    <tr>
					    <bean:define name="formRow" id="fuksziCod" property="fuksziCod" type="com.jds.prodord.master.ketszitbl.KetSziTblFormRow.FuksziCod[]"/>
					    <%for(int i = 0; i < fuksziCod.length; i++){
					    	String propertyName = "fuksziCod["+ i +"].val";%>
							<td bgcolor="#FFFFFF" style="height:22px">
								<html:text name="formRow" property="<%= propertyName %>" styleClass="s_5" maxlength="3" indexed="true"  style="text-align: left"/>
						    </td>
						<% } %>
					    </tr>
					</logic:iterate>
					</table></td></tr>
				</table>
			</div>
			</td>
		</tr>
	</table>
</div><BR>
	<table>
    <tr valign="bottom">
    	<td align="left" style="width:100px" class="index">
    		<bean:message key="label.zen"/>
			<bean:write name="ketSziTblForm" property="count" ignore="true" />
			<bean:message key="label.ken"/>
    	</td>
		<td align="center" valign="top">
		<html:submit property="command" value="実行" styleClass="button" style="width : 75px;height:25px;" onclick="return formSubmit()"/>
		</td>
		<td style="width:10px"></td>
		<td align="center" valign="top">
			<html:submit property="command" value="クリア" styleClass="button" style="width : 75px;height:25px;"/>
		</td>
		<td style="width:100px"></td> 
	</tr>
	</table>
</CENTER>
</html:form>
<div id="Layer2" style="position:absolute; left : 650px; top : 45px; width : 237px; height : 25px; z-index : 3;">
<table align="left">
	<tr valign="top">
		<td align="left">
			<html:form action="/GomasterMenu" target="_top">
				<html:submit property="submit" styleClass="button2" style="width:140px">
					<bean:message key="button.mastermenu" />
				</html:submit>
				<html:hidden property="pagingHelperType" value="com.jds.prodord.master.ketszitbl.KetSziTblPagingHelper"/>
			</html:form>
		</td>
		<td align="left">

				<html:form action="/Gomain" target="_parent">

				<html:submit styleClass="button2" property="submit" style="width:115px">

				<bean:message key="button.mainmenu"/>

				</html:submit>

				<html:hidden property="pagingHelperType" value="com.jds.prodord.master.ketszitbl.KetSziTblPagingHelper"/>

				</html:form>

			</td><td>

				<html:form action="/Logoff" target="_parent">

				<html:submit property="submit" styleClass="button2" style="width:64px">

				<bean:message key="button.logoff"/>

				</html:submit>

				</html:form>

			</td>
			
		</tr>
</table>
</div>
</BODY>
</html:html>
