<!DOCTYPE HTML>
<%@ page contentType="text/html; charset=Shift_JIS" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld"  prefix="bean" %>
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
	function formSubmit(x){
		parent.parent.rightF.midRF.setHacSuu();
		parent.parent.rightF.midRF.document.prsOrderForm.command.value = x;
		if(parent.parent.rightF.midRF.document.prsOrderForm.pre_page.value == "ManualOrder")
			parent.parent.parent.rightF.midRF.document.prsOrderForm.kbn.value = parent.parent.parent.rightF.btmRF.kbn.value;
//			parent.parent.parent.rightF.midRF.document.prsOrderForm.kbn.value = parent.parent.parent.rightF.btmRF.kbn.value; // 2004.01.14
		
		//納期一括変更対応 2005/09/22 add
		parent.parent.parent.rightF.midRF.document.prsOrderForm.nki_Y.value = parent.parent.parent.rightF.btmRF.date1.value;
		parent.parent.parent.rightF.midRF.document.prsOrderForm.nki_M.value = parent.parent.parent.rightF.btmRF.date2.value;
		parent.parent.parent.rightF.midRF.document.prsOrderForm.nki_D.value = parent.parent.parent.rightF.btmRF.date3.value;
		
		parent.parent.rightF.midRF.document.prsOrderForm.submit();
	}
	

//-->
</SCRIPT>
</head>
<body>
<bean:define id="fmForm" name="prsOrderForm" type="com.jds.prodord.order.prsorder.PrsOrderForm"/>
<div id="Layer2" style="position:absolute; left:10px; top:10px; width : 330px; height:47px; z-index : 1; visibility : visible;"> 
	<table border="0" cellspacing="0" cellpadding="0" style="width:330px">
		<tr>
			<td style="width:108px">
				<div class="index">
					<bean:write name="prsOrderForm" property="disp_currentPage" ignore="true" />
					/
					<bean:write name="prsOrderForm" property="pageCount" ignore="true" />
					<bean:message key="label.page"/>
				</div>
			</td>
			<td style="width:82px">
				<div class="index">
					<bean:message key="label.zen"/>
					<bean:write name="prsOrderForm" property="allRowCount" ignore="true" />
					<bean:message key="label.ken"/>
				</div>
			</td>
			<td>
				<html:submit property="command" onclick="formSubmit('PREV');">
					<bean:message key="button.prev100"/>
				</html:submit>
			</td><td>
				<html:submit property="command" onclick="formSubmit('NEXT');">
					<bean:message key="button.next100"/>
				</html:submit>
			</td>
		</tr>
	</table>
</div>
</body>
</html:html>