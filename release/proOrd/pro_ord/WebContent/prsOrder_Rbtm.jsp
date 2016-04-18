<!DOCTYPE HTML>
<%@ page contentType="text/html; charset=Shift_JIS" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld"  prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-form.tld" prefix="form" %>
<%@ taglib uri="/WEB-INF/struts-nested-ex.tld" prefix="nested-ex" %>

<html:html>
<head>
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
	function formSubmit(x){
		var fAnswer;
		if(x == "COMMAND_HACHUSHIJI"){
			parent.parent.rightF.midRF.setHacSuu();
			fAnswer = confirm('発注指示します。よろしいですか？');
			if(!fAnswer)
				return;
		}
		if(x == "COMMAND_DENPYOHAKKOU"){
			parent.parent.rightF.midRF.setHacSuu();
			fAnswer = confirm('伝票発行します。よろしいですか？');
			if(!fAnswer)
				return;
		}
//		if(x == "COMMAND_KBNHENKO" || x == "COMMAND_NHNCLEAR"){
		if(parent.parent.rightF.midRF.document.prsOrderForm.pre_page.value == "ManualOrder"
			||parent.parent.rightF.midRF.document.prsOrderForm.pre_page.value == "ManualOrderPaste")
			parent.parent.rightF.midRF.document.prsOrderForm.kbn.value = document.getElementById("kbn").value;

//		}

		//納期一括変更対応 2005/09/22 add
		parent.parent.rightF.midRF.document.prsOrderForm.nki_Y.value = document.getElementById("date1").value;
		parent.parent.rightF.midRF.document.prsOrderForm.nki_M.value = document.getElementById("date2").value;
		parent.parent.rightF.midRF.document.prsOrderForm.nki_D.value = document.getElementById("date3").value;

		parent.parent.rightF.midRF.document.prsOrderForm.command.value = x;
		parent.parent.rightF.midRF.document.prsOrderForm.submit();
	}
// 2004.01.09 add 
	function NhnMei_Clear(){
alert(nynClear);
		if(nynClear != undefined){		
			if(kbn.value > 1 ){
				nynClear.disabled = false;
			}else{
				nynClear.disabled = true;
			}
		}
	}

	function nextfocus(keta,mx,No){
		if(mx <= keta){
			document.all['date'+ (No + 1)].focus();
			document.all['date'+ (No + 1)].select();
		}
	}
	
//-->
</SCRIPT>
<LINK href="theme/prodord1.css<nested-ex:nocache-param />" rel="stylesheet" type="text/css">
<LINK href="theme/commonItemSize.css<nested-ex:nocache-param />" rel="stylesheet" type="text/css">
</head>
<BODY>

<bean:define id="fmForm" name="prsOrderForm" type="com.jds.prodord.order.prsorder.PrsOrderForm"/>

<%-- 納期一括変更機能 2005/09/21 add--%>
<div style="position:absolute; left:30px; top:0px; width : 300px; height:24px; z-index : 1;visibility : visible;">
 	<table><tr><td>
	 	<html:text  property="nki_Y" value="<%=fmForm.getNki_Y()%>" styleClass="s_1" maxlength="2" name="fmForm" styleId="date1" onkeypress="nextfocus(value.length,maxLength,1)"/>
	 	/</td><td>
	 	<html:text  property="nki_M" value="<%=fmForm.getNki_M()%>" styleClass="s_1" maxlength="2" name="fmForm" styleId="date2" onkeypress="nextfocus(value.length,maxLength,2)"/>
	 	/</td><td>
		<html:text  property="nki_D" value="<%=fmForm.getNki_D()%>" styleClass="s_1" maxlength="2" name="fmForm" styleId="date3" onkeypress="nextfocus(value.length,maxLength,3)"/>
		　</td><td><div style="margin-left: 10px">
		<html:submit property="command" value="納期一括変更" style="width:80px;height:18px;border-color:#cccccc;border-width:3px;background-color:#efefef;font-size:8pt" onclick="formSubmit('COMMAND_NKICHANGE');"/>
	</div></td></tr></table>
</div>

<%-- 区分変更機能（マニュアル発注･一括貼付から遷移してきた時のみ） --%>
<%if(fmForm.getPre_page().equals(com.jds.prodord.order.prsorder.PrsOrderForm.ManualOrder)
	||fmForm.getPre_page().equals(com.jds.prodord.order.prsorder.PrsOrderForm.ManualOrderPaste)){%>
<!--<div style="margin-top:5px;margin-left:30px; width : 150px;height : 25px;">-->
<div style="position:absolute; left:30px; top:27px; width : 150px; height:25px; z-index : 1;visibility : visible;">
  
<html:select name="fmForm" property="kbn" value="<%=fmForm.getKbn()%>" style="vertical-align:bottom;" styleId="kbn">
	<html:option value="1"><bean:message key="select.sinpu"/></html:option>
	<html:option value="0"><bean:message key="select.kyuhu"/></html:option>
	<html:option value="2"><bean:message key="select.sample"/></html:option>
	<html:option value="4"><bean:message key="select.tokhan"/></html:option>
	<%if(fmForm.getQueryDaiKaiSkbCod().equals("K")){%>
		<html:option value="6"><bean:message key="select.hicatalog"/></html:option>
	<%}%>
	<html:option value="3"><bean:message key="select.demoban"/></html:option>
	<html:option value="5"><bean:message key="select.sonota"/></html:option>
</html:select>
<html:submit property="command" value="　" style="width:22px;height:18px;border-color:#cccccc;border-width:3px;background-color:#efefef;" onclick="formSubmit('COMMAND_KBNHENKO');"/>

</div>
<%}%>

<%-- 2004.1.9 add 納入先クリア（マニュアル発注･一括貼付から遷移してきた時のみ） --%>
<%if(fmForm.getPre_page().equals(com.jds.prodord.order.prsorder.PrsOrderForm.ManualOrder)
	||fmForm.getPre_page().equals(com.jds.prodord.order.prsorder.PrsOrderForm.ManualOrderPaste)){
%>
	<logic:equal name="fmForm" property="prs_FukSgn" value="<%=com.jds.prodord.order.prsorder.PrsOrderForm.PRSHACHU%>">

<%	boolean flg = true;
	if((!fmForm.getKbn().equals("")) && (fmForm.getKbn().length() == 1)){
		if(Integer.parseInt(fmForm.getKbn()) > 1 )
			flg = false;
	}
%>
	<div style="position:absolute; left:140px; top:27px; width : 60px; height:25px; z-index : 1;visibility : visible;">
		<html:submit property="command" value="納品先クリア" 
			styleId="nynClear" disabled="<%= flg %>"
			style="width:80px;height:18px;border-color:#cccccc;border-width:3px;background-color:#efefef;font-size:8pt" 
			onclick="formSubmit('COMMAND_NHNCLEAR');" />
	</div>
	</logic:equal>
<%}%>
<!--2004.1.9 add 納入先クリア 追加によるテーブルサイズ変更
<div id="Layer2" style="position:absolute; right:5px; top:10px; width : 350px; height:47px; z-index : 1;
  visibility : visible;
"> -->


<%-- ボタン --%>
<div id="Layer2" style="position:absolute; right:5px; top:10px; width : 310px; height:47px; z-index : 1; visibility : visible;">
 
	<table border="0" cellspacing="3" cellpadding="0" align="right">
		<tr>
			<td>
				<html:submit property="command" styleClass="button" onclick="formSubmit('COMMAND_HACHUSHIJI');">
					<bean:message key="button.hacshiji"/>
				</html:submit>
			</td><td>
				<html:submit property="command" styleClass="button" onclick="formSubmit('COMMAND_DENPYOHAKKOU');">
					<bean:message key="button.denpyohakko"/>
				</html:submit>
			</td>
			<td>　　
				<logic:equal name="fmForm" property="pre_page" value="<%=com.jds.prodord.order.prsorder.PrsOrderForm.ManualOrder%>">
					<logic:equal name="fmForm" property="prs_FukSgn" value="<%=com.jds.prodord.order.prsorder.PrsOrderForm.PRSHACHU%>">
						<html:submit property="command" styleClass="button" onclick="formSubmit('COMMAND_FKUSHIZAIHACHU');">
							<bean:message key="button.fukuhachu"/>
						</html:submit>
					</logic:equal>
					<logic:equal name="fmForm" property="prs_FukSgn" value="<%=com.jds.prodord.order.prsorder.PrsOrderForm.FUKHACHU%>">
						<html:submit property="command" styleClass="button" onclick="formSubmit('COMMAND_PRSHACHU');">
							<bean:message key="button.prshachu"/>
						</html:submit>
					</logic:equal>
				</logic:equal>
<!--			</td><td width="50"></td> -->
			</td><td style="width:10px"></td> 
		</tr>
	</table>
</div>

</body>
</html:html>
