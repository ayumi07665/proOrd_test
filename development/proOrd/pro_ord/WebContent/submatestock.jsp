<!DOCTYPE HTML>
<%@ page contentType="text/html; charset=Shift_JIS" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld"  prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-form.tld" prefix="form" %>
<%@ taglib uri="/WEB-INF/struts-nested-ex.tld" prefix="nested-ex" %>

<html:html>
<HEAD>

<title><bean:message key="title.submatestock" /></title>
<META http-equiv ="X-UA-Compatible" content ="IE=edge"/>
<META http-equiv="Pragma" content="no-cache">
<META http-equiv="Cache-Control" content="no-cache">
<META http-equiv="Expires" content="Thu, 01 Dec 1994 16:00:00 GMT">
<META http-equiv="Content-Style-Type" content="text/css">
<META http-equiv="Content-Type" content="text/html; charset=Shift_JIS">
<META name="GENERATOR" content="IBM WebSphere Studio">
<LINK href="theme/prodord1.css<nested-ex:nocache-param />" rel="stylesheet" type="text/css">
<LINK href="theme/commonItemSize.css<nested-ex:nocache-param />" rel="stylesheet" type="text/css">

<SCRIPT>
<!--
	
	function acceptSubmit(){
		for (var i = 0; i < document.all.length; i++) {
  		  document.all(i).style.visibility = "hidden";
		}
		return true;
	}

	function changeMode(){
		n = document.SubMateStockForm.prcKbn.selectedIndex;
		flag = eval(document.SubMateStockForm.prcKbn.options[n].value);
		atai = eval(document.SubMateStockForm.atai.value);

		for(var i=0; i<atai; i++){
			if(flag == 1){
				document.SubMateStockForm["smpKbnSel"].disabled = false;
				document.SubMateStockForm["smpKbnSel"].style.backgroundColor = '#ffffff';
				document.SubMateStockForm["kigoBan0"].disabled = false;
				document.SubMateStockForm["kigoBan0"].style.backgroundColor = '#ffffff';
				document.SubMateStockForm["kigoBan1"].disabled = false;
				document.SubMateStockForm["kigoBan1"].style.backgroundColor = '#ffffff';
				document.SubMateStockForm["kigoBan2"].disabled = false;
				document.SubMateStockForm["kigoBan2"].style.backgroundColor = '#ffffff';
				document.SubMateStockForm["kigoBan3"].disabled = false;
				document.SubMateStockForm["kigoBan3"].style.backgroundColor = '#ffffff';
				document.SubMateStockForm["kigoBan4"].disabled = false;
				document.SubMateStockForm["kigoBan4"].style.backgroundColor = '#ffffff';
				document.SubMateStockForm["kigoBan5"].disabled = false;
				document.SubMateStockForm["kigoBan5"].style.backgroundColor = '#ffffff';
				document.SubMateStockForm["kigoBan6"].disabled = false;
				document.SubMateStockForm["kigoBan6"].style.backgroundColor = '#ffffff';
				document.SubMateStockForm["kigoBan7"].disabled = false;
				document.SubMateStockForm["kigoBan7"].style.backgroundColor = '#ffffff';
				document.SubMateStockForm["kigoBan8"].disabled = false;
				document.SubMateStockForm["kigoBan8"].style.backgroundColor = '#ffffff';
				document.SubMateStockForm["kigoBan9"].disabled = false;
				document.SubMateStockForm["kigoBan9"].style.backgroundColor = '#ffffff';
				document.SubMateStockForm["sign[" + i +"]"].disabled = true;
				document.SubMateStockForm["sign[" + i +"]"].style.backgroundColor = '#efefef';
				document.SubMateStockForm["teiseiSuu[" + i +"]"].disabled = true;
				document.SubMateStockForm["teiseiSuu[" + i +"]"].style.backgroundColor = '#efefef';
				document.SubMateStockForm["cpPaste"].style.backgroundColor = '#ffffff';	
				document.SubMateStockForm["cpPaste"].disabled = false;
				document.all.paste.disabled = false;

			}else{
				document.SubMateStockForm["smpKbnSel"].disabled = true;
				document.SubMateStockForm["smpKbnSel"].style.backgroundColor = '#efefef';
				document.SubMateStockForm["kigoBan0"].disabled = true;
				document.SubMateStockForm["kigoBan0"].style.backgroundColor = '#efefef';
				document.SubMateStockForm["kigoBan1"].disabled = true;
				document.SubMateStockForm["kigoBan1"].style.backgroundColor = '#efefef';
				document.SubMateStockForm["kigoBan2"].disabled = true;
				document.SubMateStockForm["kigoBan2"].style.backgroundColor = '#efefef';
				document.SubMateStockForm["kigoBan3"].disabled = true;
				document.SubMateStockForm["kigoBan3"].style.backgroundColor = '#efefef';
				document.SubMateStockForm["kigoBan4"].disabled = true;
				document.SubMateStockForm["kigoBan4"].style.backgroundColor = '#efefef';
				document.SubMateStockForm["kigoBan5"].disabled = true;
				document.SubMateStockForm["kigoBan5"].style.backgroundColor = '#efefef';
				document.SubMateStockForm["kigoBan6"].disabled = true;
				document.SubMateStockForm["kigoBan6"].style.backgroundColor = '#efefef';
				document.SubMateStockForm["kigoBan7"].disabled = true;
				document.SubMateStockForm["kigoBan7"].style.backgroundColor = '#efefef';
				document.SubMateStockForm["kigoBan8"].disabled = true;
				document.SubMateStockForm["kigoBan8"].style.backgroundColor = '#efefef';
				document.SubMateStockForm["kigoBan9"].disabled = true;
				document.SubMateStockForm["kigoBan9"].style.backgroundColor = '#efefef';
				document.SubMateStockForm["sign[" + i +"]"].disabled = false;
				document.SubMateStockForm["sign[" + i +"]"].style.backgroundColor = '#ffffff';
				document.SubMateStockForm["teiseiSuu[" + i +"]"].disabled = false; 
				document.SubMateStockForm["teiseiSuu[" + i +"]"].style.backgroundColor = '#ffffff';	
				document.SubMateStockForm["cpPaste"].style.backgroundColor = '#efefef';
				document.SubMateStockForm["cpPaste"].disabled = true;
				document.all.paste.disabled = true;

			}		
		}		

	}

-->

</SCRIPT>

</HEAD>

<body onLoad="changeMode()">


<H3><bean:message key="title.submatestock2" /></H3>

<div class="button_right" align="right">
<table border="0" cellspacing="0" cellpadding="2">
	<tr>
	<td valign="top">
		<html:form action="/GomasterMenu" target="_top">
			<html:submit property="submit" styleClass="button2" style="width:140px">
				<bean:message key="button.mastermenu" />
			</html:submit>
			<html:hidden property="pagingHelperType" value="com.jds.prodord.master.submatestock.SubMateStockPagingHelper"/>
		</html:form>
	</td>
	<td>
		<html:form action="/Gomain" target="_top">
			<html:submit property="submit" styleClass="button2" style="width:115px">
				<bean:message key="button.mainmenu" />
			</html:submit>
			<html:hidden property="pagingHelperType" value="com.jds.prodord.master.submatestock.SubMateStockPagingHelper" />
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


<html:form action = "/SubMateStock" target="_self" onsubmit="return acceptSubmit();">
<bean:define id="fmForm" name="SubMateStockForm" type="com.jds.prodord.master.submatestock.SubMateStockForm"/>

<div style="position:absolute; left:10px; top:60px; width:200px; height:35px; z-index : 1;">
 <table border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td align="left" style="width:160px">
	      <table style="height:30px">
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
	  </td>
	</tr>
  </table>
</div>

<hr>
	<html:errors/>
	<logic:present  name="SERVICE_MESSAGE">
	  <DIV class="service_msg"><bean:write name="SERVICE_MESSAGE"/></DIV>
	</logic:present>

	<logic:present  name="INFOMSG_KEY">
	  <DIV class="info_msg"><bean:write name="INFOMSG_KEY" /></DIV>
	</logic:present>
<br>
<center>

  <table border="0" cellspacing="0" cellpadding="0">
    <tr>
		<td>
			<table style="height:30px">
				<tr>
					<td style="width:70px;height:22px;" class="title_dark" align="center"><bean:message key="label.syorikbn"/></td>
					<td style="width:100px;height:22px;" class="title_dark" align="center"><bean:message key="label.smpkbn"/></td>
					<td style="width:650px" class="title_dark" align="center" colspan="2"><bean:message key="label.kigbng" /></td>                
				</tr>
				<tr>
					<td>
			            <table style="width:70px;height:52px;" border="0" cellspacing="0" cellpadding="2">
				           	<tr>
								<td align="center">
									<html:select property = "prcKbn" value="<%=fmForm.getPrcKbn()%>" onchange="changeMode()">
										<html:option value = "1"><bean:message key="button.search" /></html:option>
										<html:option value = "2"><bean:message key="button.update" /></html:option>
									</html:select>
								</td>
							</tr>
						</table>
					</td>
					<td>
			            <table style="width:100px;height:52px;" border="0" cellspacing="0" cellpadding="2">
				           	<tr>
								<td align="center">
									<html:select property = "smpKbnSel" value="<%=fmForm.getSmpKbnSel()%>" >
										<html:option value = " ">　</html:option>
										<html:option value = "S">Ｓ</html:option>
									</html:select>
								</td>
							</tr>
						</table>
					</td>
				
					<td>
			            <table style="width:570px;height:52px;" border="0" cellspacing="0" cellpadding="2">
							<tr>
								<td>
									<html:text property="kigoBan0" maxlength="13" styleClass="s_18"/>
									<html:text property="kigoBan1" maxlength="13" styleClass="s_18"/>
									<html:text property="kigoBan2" maxlength="13" styleClass="s_18"/>
									<html:text property="kigoBan3" maxlength="13" styleClass="s_18"/>
									<html:text property="kigoBan4" maxlength="13" styleClass="s_18"/>
								</td>
							</tr>
							<tr>
								<td>
									<html:text property="kigoBan5" maxlength="13" styleClass="s_18"/>
									<html:text property="kigoBan6" maxlength="13" styleClass="s_18"/>
									<html:text property="kigoBan7" maxlength="13" styleClass="s_18"/>
									<html:text property="kigoBan8" maxlength="13" styleClass="s_18"/>
									<html:text property="kigoBan9" maxlength="13" styleClass="s_18"/>
								</td>
							</tr>
						</table>
					</td>
					
					<td align="center"rowspan="2"><html:textarea property="cpPaste" cols="15" rows="2"/>
					<html:submit property="command" value="貼り付け" styleClass="button" style="height:20px;" styleId="paste"/></td>
			    </tr>
	 		 </table>
		</td>
    </tr>
  </table>

<br><br>

<table border="1"  cellspacing="1" cellpadding="0" style="width:700px">
    <tr><td>

	<table border="0" bgcolor="#336666" cellspacing="1" cellpadding="2" align="center">
	        <tr>
	            <td style="width:130px;height:20px;" class="title_light" align="center"><bean:message key="label.kigbng"/></td>
	            <td style="width:90px;height:20px;" class="title_light" align="center"><bean:message key="label.fukszicod1"/></td>
	            <td style="width:170px;height:20px;" class="title_light" align="center"><bean:message key="label.fukszinm"/></td>
	            <td style="width:100px;height:20px;" class="title_light" align="center"><bean:message key="label.smpkbn"/></td>
	            <td style="width:100px;height:20px;" class="title_light" align="center"><bean:message key="label.fukshizai"/><bean:message key="label.zaisuu"/></td>
	            <td style="width:50px;height:20px;" class="title_light" align="center"><bean:message key="label.sign"/></td>
	            <td style="width:60px;height:20px;" class="title_light" align="center"><bean:message key="label.teiseisuu"/></td>
	        </tr>
				

		<% String atai = (String)fmForm.getAtai();%>
		<html:hidden property="atai" name="atai" value="<%= atai %>"/>


		<logic:iterate name="SubMateStockForm" id="row" indexId="index"  property="formRowList" >
		<% String propertyName = "execute[" + index + "]";%>
	        <tr>
				<% String styleClass = (fmForm.getHidFlag(index.intValue()) == true)? "details2" : "details";%>
				<% propertyName= "hidFlag[" + index +"]"; %>
	            <td align="left" class="<%= styleClass %>">
					<% propertyName= "kigoBan[" + index +"]"; %>
					　<bean:write name="SubMateStockForm"property="<%= propertyName %>" />
	            </td>
			
	            <td align="left" bgcolor="#FFFFFF">
					<% propertyName= "huksizaiCod[" + index +"]"; %>
					　<bean:write name= "SubMateStockForm" property="<%= propertyName %>" />
	            </td>

	            <td align="left" bgcolor="#FFFFFF">
					<% propertyName= "huksizaiMei[" + index +"]"; %>
					<bean:write name= "SubMateStockForm" property="<%= propertyName %>" />
	            </td>
				<td align="center" bgcolor="#FFFFFF">
					<% propertyName= "smpKbn[" + index +"]"; %>
					<bean:write name="SubMateStockForm" property="<%= propertyName %>" />
				</td>	            
				<td align="right" bgcolor="#FFFFFF">
					<% propertyName= "huksizaiZaiko[" + index +"]"; %>
					<bean:write name="SubMateStockForm" property="<%= propertyName %>" />
				</td>
				<td align="center" bgcolor="#FFFFFF">
					<% propertyName= "sign[" + index +"]"; %>
					<html:select name="SubMateStockForm" property="<%= propertyName %>">
						<html:option value="true">－</html:option>
						<html:option value="false">＋</html:option>
					</html:select>
				</td>
				<td bgcolor="#FFFFFF">
					<% propertyName= "teiseiSuu[" + index +"]"; %>
					<html:text property="<%= propertyName %>" maxlength="6" styleClass="s_8" style="text-align: right" />
				</td>

	        </tr>

		</logic:iterate>

    </table>



   </td></tr>
</table>

<br><br>

	<table>
		<tr>
			<td>
				<html:submit property="command" value="実行" styleClass="button" style="width : 75px">
				</html:submit>
			</td>
			<td>
				<html:submit property="command" value="クリア" styleClass="button" style="width : 75px">
				</html:submit>
			</td>

		</tr>
	</table>
	
</center>
</html:form>

</BODY>
</html:html>