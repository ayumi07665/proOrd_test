<!DOCTYPE HTML>
<%@ page contentType="text/html; charset=Shift_JIS" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld"  prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-form.tld" prefix="form" %>
<%@ taglib uri="/WEB-INF/struts-nested-ex.tld" prefix="nested-ex" %>

<html:html>
<HEAD>
<TITLE><bean:message key="title.ketrak"/></TITLE>
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

	function formSubmit(){
/*
		var kbn = document.ketRakForm.prcKbn.value;
		if(kbn == "2"){
			return confirm('登録します。よろしいですか？');
		}
		if(kbn == "3"){
			return confirm('更新します。よろしいですか？');
		}
		if(kbn == "4"){
			return confirm('削除します。よろしいですか？');
		}
*/
	}
//-->

</SCRIPT>
<LINK href="theme/prodord1.css<nested-ex:nocache-param />" rel="stylesheet" type="text/css">
<LINK href="theme/commonItemSize.css<nested-ex:nocache-param />" rel="stylesheet" type="text/css">
</HEAD>
<BODY>
<html:form action = "/ketRak" target="_self" onsubmit="return acceptSubmit();">
<bean:define id="fmForm" name="ketRakForm" type="com.jds.prodord.master.ketrak.KetRakForm"/>
<H3><bean:message key="title.ketrak2"/></H3>
<table>
	<tr>
	<td style="width:160px">
	<table style="height:30px">
		<tr>
			<td style="width:100px" class="title_dark">
			<bean:message key="label.daikai"/>
			</td>
			<td style="width:62px" class="title_light">
				<table style="width:62px;height:24px;" cellspacing="2" cellpadding="2" class="details">
				<tr><td align="center">
					<bean:write name="fmForm" property="queryDaiKaiSkbCod" ignore="true"/>
				</td></tr>
				</table>
			</td>
		</tr>
	</table>
	</td>
	<td></td>
	</tr>
</table>
<br>

<hr>
<br>
<logic:present  name="INFOMSG_KEY">
	<DIV class="info_msg"><bean:write name="INFOMSG_KEY" /></DIV>
</logic:present>
<logic:present  name="SERVICE_MESSAGE">
	<DIV class="service_msg"><bean:write name="SERVICE_MESSAGE" /></DIV>
	
</logic:present>
<html:errors/>
<br>

	<CENTER>
	<table align="center" cellspacing="3" style="width:625px">
		<tr>
          <td  align="center" class="title_dark" style="height:22px"><bean:message key="label.syorikbn"/></td>
          <td  align="center" class="title_dark" style="height:22px"><bean:message key="label.kaiskb"/></td>
          <td  align="center" class="title_dark" style="height:22px"><bean:message key="label.rank"/></td>
          <td  align="center" class="title_dark" style="height:22px"><bean:message key="label.ketcod"/></td>
        </tr>
		<tr><td align="center">
		    <html:select property="prcKbn" value="<%=fmForm.getPrcKbn()%>">
		   			<html:option value="1"><bean:message key="button.search"/></html:option>
					<html:option value="2"><bean:message key="button.insert"/></html:option>
					<html:option value="3"><bean:message key="button.update"/></html:option>
					<html:option value="4"><bean:message key="button.delete"/></html:option>
			</html:select>

		</td>
		<td align="center">
			<html:select property="kaiSkbCod" size="1">
		   		<html:options property="vl_kaiSkbCod"/>
		   	</html:select>
		</td>

		<td align="center">
		    <html:select property="rank" value="<%=fmForm.getRank()%>">
		   			<html:option value="" >　</html:option>
					<html:option value="N1">N1　</html:option>
					<html:option value="N2">N2　</html:option>
					<html:option value="N3">N3　</html:option>
					<html:option value="S">S　</html:option>
					<html:option value="A">A　</html:option>
					<html:option value="B">B　</html:option>
					<html:option value="C">C　</html:option>
					<html:option value="D">D　</html:option>			
			</html:select>
		</td>
		<td align="center">
		   	<html:select property="ketCod" size="1">
		   		<html:options property="vl_ketCod" labelProperty="lb_ketCod"/>
		   	</html:select>
		</td>
		</tr>
	</table>
	
	<table align="center" cellspacing="3" style="width:625px">
		<tr><td style="width:50px;height:22px;"></td>
		  <td  align="center" style="width:85px" class="index"><bean:message key="label.ikkatuhenko"/>&nbsp;&gt;&gt;</td>
          <td  align="center" class="title_dark"><bean:message key="label.ssnredtim"/></td>
          <td  align="center" class="title_dark"><bean:message key="label.minzaisuu"/></td>
          <td  align="center" class="title_dark"><bean:message key="label.minrotsuu"/></td>
          <td  align="center" class="title_dark"><bean:message key="label.mrmsuu"/></td>
        </tr>
		<tr><td colspan="2"></td>
		<td align="center">
			<html:text property="ikkatsu_ssnRedTim" style="text-align: right" styleClass="s_10" maxlength="2"  />
		</td>
		<td align="center">
			<html:text property="ikkatsu_minZaiSuu" style="text-align: right" styleClass="s_10" maxlength="7"  />
		</td>
		<td align="center">
		    <html:text property="ikkatsu_minRotSuu" style="text-align: right" styleClass="s_10" maxlength="7"  />
		</td>
		<td align="center">
		   	<html:text property="ikkatsu_mrmSuu" style="text-align: right" styleClass="s_10" maxlength="7"  />
		</td>
		</tr>
	</table>
	
	</CENTER>

<br>

  <CENTER>
  <table border="1" cellspacing="1" cellpadding="0" style="width:780px">
    <tr><td> 

	<table border="0" bgcolor="#336666" cellspacing="1" cellpadding="1" style="width:780px" align="center">
        <tr>
            <td class="title_light" style="height:20px"></td>
            <td class="title_light" style="height:20px"><bean:message key="label.kaiskb_"/></td>
            <td class="title_light" style="height:20px"><bean:message key="label.rank"/></td>
            <td class="title_light" style="height:20px"><bean:message key="label.ketcod"/></td>
            <td class="title_light" style="height:20px"><bean:message key="label.ssnredtim"/></td>
            <td class="title_light" style="height:20px"><bean:message key="label.minzaisuu"/></td>
            <td class="title_light" style="height:20px"><bean:message key="label.minrotsuu"/></td>
            <td class="title_light" style="height:20px"><bean:message key="label.mrmsuu"/></td>
        </tr>


		<logic:iterate name="ketRakForm" id="row" indexId="index"  property="formRowList" >

        <tr>
            <td bgcolor="#FFFFFF">
            	<% String propertyName = "execute[" + index + "]";%>
				<html:checkbox property="<%= propertyName%>"  />
            </td>
            <td bgcolor="#FFFFFF">
            	<% propertyName = "outKaiSkbCod[" + index + "]";%>
				<bean:write name ="ketRakForm" property="<%= propertyName%>" />
            </td>
            <td bgcolor="#FFFFFF">
            	<% propertyName = "outRank[" + index + "]";%>
				<bean:write name ="ketRakForm" property="<%= propertyName%>" />
            </td>

            <td bgcolor="#FFFFFF">
            	<% propertyName = "outKetCod[" + index + "]";%>
				<bean:write name ="ketRakForm" property="<%= propertyName%>" />
            </td>
            <td bgcolor="#FFFFFF">
            	<% propertyName = "ssnRedTim[" + index + "]";%>
				<html:text property="<%= propertyName%>" style="text-align: right" styleClass="s_10" maxlength="2"  />
            </td>
            <td bgcolor="#FFFFFF">
            	<% propertyName = "minZaiSuu[" + index + "]";%>
				<html:text property="<%= propertyName%>" style="text-align: right" styleClass="s_10" maxlength="7"  />
            </td>
            <td bgcolor="#FFFFFF">
            	<% propertyName = "minRotSuu[" + index + "]";%>
				<html:text property="<%= propertyName%>" style="text-align: right" styleClass="s_10" maxlength="7"  />
            </td>
            <td bgcolor="#FFFFFF">
            	<% propertyName = "mrmSuu[" + index + "]";%>
				<html:text property="<%= propertyName%>" style="text-align: right" styleClass="s_10" maxlength="7"  />
            </td>
            
        </tr>
		</logic:iterate>
    </table>
    </td></tr>
  </table>
  <br>

	<table>
    <tr valign="bottom">
		<td align="center"  valign="top">
		<html:submit property="command" value="実行" styleClass="button" style="width : 75px" onclick="formSubmit()"/>
		</td>
		<td width="10"></td>
		<td align="center" valign="top">
			<html:submit property="command" value="クリア" styleClass="button" style="width : 75px"/>
		</td>
	</tr>
	</table>
  </CENTER>
</html:form>
<div id="Layer2" style="position:absolute; left : 650px; top : 60px; width : 237px; height : 30px; z-index : 3;">
<table align="left">
	<tr valign="top">
		<td align="left">
			<html:form action="/GomasterMenu" target="_top">
				<html:submit property="submit" styleClass="button2" style="width:140px">
					<bean:message key="button.mastermenu" />
				</html:submit>
				<html:hidden property="pagingHelperType" value="com.jds.prodord.master.ketrak.KetRakPagingHelper"/>
			</html:form>
		</td>
		<td align="left">

				<html:form action="/Gomain" target="_parent">

				<html:submit styleClass="button2" property="submit" style="width:115px">

				<bean:message key="button.mainmenu"/>

				</html:submit>

				<html:hidden property="pagingHelperType" value="com.jds.prodord.master.ketrak.KetRakPagingHelper"/>

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
