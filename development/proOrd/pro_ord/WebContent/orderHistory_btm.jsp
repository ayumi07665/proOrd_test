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

		if(x == "COMMAND_TEIDENHAKKOU"){
			fAnswer = confirm('訂正伝票発行します。よろしいですか？');
			if(!fAnswer)
				return;
		}
		if(x == "COMMAND_TEISOU"){
			fAnswer = confirm('訂正送信します。よろしいですか？');
			if(!fAnswer)
				return;
		}

		parent.midF.document.orderHistoryMidForm.command.value = x;
		parent.midF.document.orderHistoryMidForm.submit();		
		
        //明細非表示、処理中メッセージ表示・move
	    parent.midF.scrollMsg(true);
	}
	function setPage(){
		var disp_currentPage = parent.midF.document.orderHistoryMidForm.disp_currentPage.value;
		var pageCount = parent.midF.document.orderHistoryMidForm.pageCount.value;
		var allRowCount = parent.midF.document.orderHistoryMidForm.allRowCount.value;
		parent.btmF.disp_currentPage.innerText = disp_currentPage;
		parent.btmF.pageCount.innerText = pageCount;
		parent.btmF.allRowCount.innerText = allRowCount;
	}

//-->
</SCRIPT>
<LINK href="theme/prodord1.css<nested-ex:nocache-param />" rel="stylesheet" type="text/css">
<LINK href="theme/commonItemSize.css<nested-ex:nocache-param />" rel="stylesheet" type="text/css">
</head>
<bean:define id="fmTopForm" name="orderHistoryTopForm" type="com.jds.prodord.reference.orderhistory.OrderHistoryTopForm"/>
<BODY BGCOLOR="#FFF5E7" TEXT="#9B6E6E" LINK="#DC7373" VLINK="#FFB9AF" ALINK="#C8DFA3">
	
<table align="right">
  <tr> 
    <td>
      <logic:notEqual name="fmTopForm" property="hyoKbn" value="1">
	      <html:submit property="command"  styleClass="button" onclick="formSubmit('COMMAND_TEIDENHAKKOU');">
	      		<bean:message key="button.teidenhakkou"/>
	      </html:submit> 
	      <html:submit property="command"  styleClass="button" onclick="formSubmit('COMMAND_TEISOU');">
	      		<bean:message key="button.teisou"/>
	      </html:submit>
	  </logic:notEqual>
    </td>
  </tr>
</table>

<div id="Layer0" style="position:absolute; left:270px; top:10px; width : 150px; height : 24px; z-index : 1; visibility : visible;"> 
	<table border="0" cellspacing="0" cellpadding="0" style="width:150px">
		<tr>
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
<DIV id="disp_currentPage" class = "info_msg" align="right" style="position : absolute;left : 25px;top : 14px;width : 35px;height : 22px;visibility : visible;"></DIV>
<DIV id="Layer1" class = "info_msg" style="position : absolute;left : 60px;top : 14px;width : 10px;height : 22px;visibility : visible;">
	/
</DIV>
<DIV id="pageCount" class = "info_msg"  align="right"style="position : absolute;left : 70px;top : 14px;width : 35px;height : 22px;visibility : visible;"></DIV>
<DIV id="Layer2" class = "info_msg" style="position : absolute;left : 105px;top : 14px;width : 75px;height : 22px;visibility : visible;">
	<bean:message key="label.page"/>　　<bean:message key="label.zen"/>
</DIV>
<DIV id="allRowCount" class = "info_msg" align="right" style="position : absolute;left : 180px;top : 14px;width : 50px;height : 22px;visibility : visible;"></DIV>
<DIV id="Layer3" class = "info_msg" style="position : absolute;left : 230px;top : 14px;width : 20px;height : 22px;visibility : visible;">
	<bean:message key="label.ken"/>
</DIV>
</body>
</html:html>
