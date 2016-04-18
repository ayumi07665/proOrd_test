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
<script>
<!--

	function acceptSubmit(){
		for(var i = 0; i < document.all.length; i++) {
  		  document.all(i).style.visibility = "hidden";
		}
		return true;
	}
	function setPage(){
		if(parent.btmF.document.body == null)
			setTimeout("setPage()",1000);
		else
			parent.btmF.setPage();
	}
	
	window.onscroll = function() {
		pageMove();
		StartMove();
	}
	function pageMove(){
		var L = document.documentElement.scrollLeft || document.body.scrollLeft;
		if(parent.topF.document.body != null)
			parent.topF.document.body.scrollLeft = L;
		if(parent.topF.document.documentElement != null)
			parent.topF.document.documentElement.scrollLeft = L;
	}

	var nYPos;
	var nXPos;
	function StartMove(){
		nYPos = document.body.scrollTop;
		nXPos = document.body.scrollLeft;
		moveLayer.style.left = (nXPos + 5) + "px";
		moveLayer.style.top = (nYPos + 15) + "px";
	}

	var mYPos;
	function moveDown(trglay){
		mYPos = document.body.scrollTop;
		trglay.style.top = (mYPos + 70) + "px";
	}

	function setAlphaFilter(trglay, op){
		trglay.style.filter = 'alpha(opacity=' + op + ')';
		trglay.style.opacity = (op / 100);
	}
	function setShowHide(trglay, type){
		trglay.style.visibility = type;
	}

	function dispMsg(){
		setAlphaFilter(moveLayer, 100);
		setTimeout('setAlphaFilter(moveLayer, 30)',4000);
	}
	function dispMsg2(){
		setShowHide(infomsgLayer, "visible");
		setTimeout("setShowHide(infomsgLayer, 'hidden')",4000);
	}	

	function openNewWindow(){
		var newWindow = document.orderHistoryMidForm.newWindow.value;

		if(newWindow == "1"){
			var forwardNm = "<html:rewrite forward="printOrders"/>";
			wd = window.open(forwardNm,"_blank","scrollbars=1,menubar=1,width=800,height=500,resizable=1");
		}
		document.orderHistoryMidForm.newWindow.value = "0";
	}
	function changeValue(n){
		var kbn = 0;
		//代表会社＝'K'の場合("新譜","旧譜","サンプル","特販","非ｶﾀﾛｸﾞ","デモ盤","その他")
		if(document.forms[0].queryDaiKaiSkbCod.value == "K"){
			kbnString = new Array("<bean:message key="select.sinpu"/>",
								　"<bean:message key="select.kyuhu"/>",
								  "<bean:message key="select.sample"/>",
								  "<bean:message key="select.tokhan"/>",
								  "<bean:message key="select.hicatalog"/>",
								  "<bean:message key="select.demoban"/>",
								  "<bean:message key="select.sonota"/>");

			if(n.value == kbnString[0])
				kbn = 1;
			else if(n.value == kbnString[1])
				kbn = 2;
			else if(n.value == kbnString[2])
				kbn = 3;
			else if(n.value == kbnString[3])
				kbn = 4;
			else if(n.value == kbnString[4]) //2004.03.08 add
				kbn = 5;
			else if(n.value == kbnString[5]) //2005.06.27 add
				kbn = 6;
			else if(n.value == kbnString[6])
				kbn = 0;

		//("新譜","旧譜","サンプル","特販","デモ盤","その他")
		}else{
			kbnString = new Array("<bean:message key="select.sinpu"/>",
								　"<bean:message key="select.kyuhu"/>",
								  "<bean:message key="select.sample"/>",
								  "<bean:message key="select.tokhan"/>",
								  "<bean:message key="select.demoban"/>",
								  "<bean:message key="select.sonota"/>");

			if(n.value == kbnString[0])
				kbn = 1;
			else if(n.value == kbnString[1])
				kbn = 2;
			else if(n.value == kbnString[2])
				kbn = 3;
			else if(n.value == kbnString[3])
				kbn = 4;
			else if(n.value == kbnString[4]) //2004.03.08 add
				kbn = 5;
			else if(n.value == kbnString[5])
				kbn = 0;
		}

		n.value = kbnString[kbn];
	}
	function nextfocus(keta,mx,No){
		if(mx <= keta){
			document.all['date'+ (No + 1)].focus();
			document.all['date'+ (No + 1)].select();
		}
	}
	
// -->
</script>
<LINK href="theme/prodord1.css<nested-ex:nocache-param />" rel="stylesheet" type="text/css">
<LINK href="theme/commonItemSize.css<nested-ex:nocache-param />" rel="stylesheet" type="text/css">
</head>

<% String onLoadMethod = ""; %>
<logic:present  name="org.apache.struts.action.ERROR">
	<% onLoadMethod = "setShowHide(moveLayer,'visible'),dispMsg(),setPage(),stopMsg();"; %>
</logic:present>
<logic:notPresent  name="org.apache.struts.action.ERROR">
	<logic:present  name="INFOMSG_KEY">
		<% onLoadMethod = "setShowHide(moveLayer,'hidden'),dispMsg2(),openNewWindow(),setPage(),stopMsg();"; %>
	</logic:present>
	<logic:notPresent  name="INFOMSG_KEY">
		<% onLoadMethod = "setShowHide(moveLayer,'hidden'),openNewWindow(),setPage(),stopMsg();"; %>
	</logic:notPresent>
</logic:notPresent>
<BODY onLoad="<%= onLoadMethod %>">

<html:form action="/orderHistoryMid" target="_self" onsubmit="return acceptSubmit();">
<bean:define id="fmMidForm" name="orderHistoryMidForm" type="com.jds.prodord.reference.orderhistory.OrderHistoryMidForm"/>
<html:hidden property="command" name="command" value="java.lang.String"/>
<html:hidden property="newWindow" name="newWindow" value="<%=fmMidForm.getNewWindow()%>"/>
<html:hidden property="disp_currentPage" name="disp_currentPage" value="<%=fmMidForm.getDisp_currentPage()%>"/>
<html:hidden property="pageCount" name="pageCount" value="<%=fmMidForm.getPageCount()%>"/>
<html:hidden property="allRowCount" name="allRowCount" value="<%=fmMidForm.getAllRowCount()%>"/>
<html:hidden property="queryDaiKaiSkbCod" name="daiKaiSkbCod" value="<%=fmMidForm.getQueryDaiKaiSkbCod()%>"/>

<logic:present  name="SERVICE_MESSAGE">
   <div id="Layer1" style="position:absolute; left:10px; top:5px; z-index : 2;background-color:#FFFFFF">
	<DIV class="service_msg"><bean:write name="SERVICE_MESSAGE" /></DIV>
   </div>
</logic:present>
<DIV id="moveLayer" class = "moveLayer" align="left" style="z-index : 2;width:400px;" onMouseover="setAlphaFilter(this, 100)" onMouseout="setAlphaFilter(this, 30)" onClick="moveDown(this)">
	<html:errors/>
</DIV>
<DIV id="infomsgLayer" class = "infomsgLayer" align="left" style="z-index : 2;">
	<logic:present  name="INFOMSG_KEY">
		<DIV class="info_msg"><bean:write name="INFOMSG_KEY" /></DIV>
	</logic:present>
</DIV>
<jsp:include page="processing.jsp">
	<jsp:param name="type" value="2"/>
</jsp:include>

<div id="Layer2" style="position:absolute; left:5px; top:5px; z-index : 1;">
<table border="0" cellpadding="0" cellspacing="0" class="fixed_table">

<logic:iterate name="orderHistoryMidForm" id="formRow" indexId="index"  property="formRowList" type="com.jds.prodord.reference.orderhistory.OrderHistoryFormRow"> 
	    <tr> 
          <td style="width:300px">
              <table border="0" cellpadding="2" cellspacing="1" class="detailtable_back" style="width:300px;margin-bottom:2px">
              	<% String styleClass = (formRow.getBreakflg() == true)? "details" : "details2";%>
                <tr class="<%= styleClass %>"> 
                  <td style="width:27px;height:27px;" align="center" class="index">
                    <%= (index.intValue() + 1) %>
                  </td>
                  <td style="width:47px;height:27px;" align="center">
                    <bean:write name="formRow" property="hacCod" ignore="true" />
                  </td>
                  <td style="width:47px" align="center">
                  	<html:text name="formRow" property="sinKyuKbn" styleClass="s_6" maxlength="4" indexed="true" readonly="true" onclick="changeValue(this)"/>
                  </td>
                  <td style="width:97px" align="left">
                    <bean:write name="formRow" property="hjiHnb" ignore="true" />
                  </td>
                  <td style="width:57px" align="center">
                    <bean:write name="formRow" property="hbiDte" ignore="true" />
                  </td>
                </tr>          
              </table>
          </td>
          <td> 
            <html:checkbox name="formRow" property="check_Mid" value="true" indexed="true" style="width:25px;padding-right:0px;" />
          </td>
          <td style="width:755px" valign="top"> 
              <table border="0" cellpadding="2" cellspacing="1" class="detailtable_back" style="width:755px">
                <tr class="details"> 
                  <td style="width:65px;height:27px;" align="center">
                    <bean:write name="formRow" property="hacSyoBng" ignore="true" />
                  </td>
                  <td style="width:66px;height:27px;" align="center">
                    <bean:write name="formRow" property="gyoBng" ignore="true" />
                  </td>
                  <td style="width:56px" align="center">
                    <bean:write name="formRow" property="hacSyoDte" ignore="true" />
                  </td>
                  <td style="width:47px" align="center">
                  	<%if(formRow.getSyrZmiSgn().equals(com.jds.prodord.reference.orderhistory.OrderHistoryMidForm.MISYRYK))
				 	  	styleClass = "haczmi";
				 	  else if(formRow.getSyrZmiSgn().equals(com.jds.prodord.reference.orderhistory.OrderHistoryMidForm.SYRZMI))
				 	  	styleClass = "syrzmi";
				 	%>
				 	<span class="<%= styleClass %>">
				      <bean:write name="formRow" property="syrZmiSgn" ignore="true" />
				    </span>		    	
                  </td>
                  <td style="width:42px" align="center">
                      <bean:write name="formRow" property="bunCod" ignore="true" />
                  </td>
                  <td style="width:96px" align="center">
                      <html:text name="formRow" property="tan2" styleClass="s_14" maxlength="10" indexed="true" style="text-align: right"/>
                  </td>
                  <td style="width:96px" align="center">
                      <html:text name="formRow" property="kin" styleClass="s_14" maxlength="12" indexed="true" style="text-align: right"/>
                  </td>
                  <td style="width:57px" align="right">
                    <bean:write name="formRow" property="fukSzisuu" ignore="true" />
                  </td>
                  <td style="width:62px;height:27px;" align="center">
                   <html:text name="formRow" property="hacSuu" styleClass="s_8" maxlength="7" indexed="true" style="text-align: right"/>
                  </td>
                  <td style="width:56px;height:27px;" align="right">
                   <bean:write name="formRow" property="nyoSuu" ignore="true" />
                  </td>
                  <td style="width:57px" align="center">
                    <bean:write name="formRow" property="nyoDte" ignore="true" />
                  </td>                      
                </tr>
              </table>
          </td>
          <td style="width:10px">
	          <table style="width:10px"><tr><td></tr></table>
	      </td>
          <td style="width:790px" valign="top">  
              <table border="0" cellpadding="2" cellspacing="1" class="detailtable_back" style="width:785px">
                <tr class="details">
                  <td style="width:85px;height:27px;" align="center">
					<%String idName = "date" + (1 + 3 * index.intValue());%>
					<%String onKeyPress = "nextfocus(value.length,maxLength," + (1 + 3 * index.intValue()) +")";%>
                    <html:text name="formRow" indexed="true" property="nkiYear" styleClass="s_1" maxlength="2" styleId="<%= idName%>" onkeypress="<%= onKeyPress%>"/>/
					<%idName = "date" + (2 + 3 * index.intValue());%>
					<%onKeyPress = "nextfocus(value.length,maxLength," + (2 + 3 * index.intValue()) +")";%>
					<html:text name="formRow" indexed="true" property="nkiMonth" styleClass="s_1" maxlength="2" styleId="<%= idName%>" onkeypress="<%= onKeyPress%>"/>/
					<%idName = "date" + (3 + 3 * index.intValue());%>
					<html:text name="formRow" indexed="true" property="nkiDay" styleClass="s_1" maxlength="2" styleId="<%= idName%>"/>
                  </td>                                           
                  <td style="width:225px" align="center">
                    <html:text name="formRow" property="nhnMeiKj" styleClass="s_41" maxlength="20" indexed="true"  />
                  </td>
                  <td style="width:225px" align="center">
                    <html:text name="formRow" property="cmt" styleClass="s_41" maxlength="20" indexed="true"  />
                  </td>
                  <td style="width:225px" align="center">
                    <html:text name="formRow" property="biKou2" styleClass="s_41" maxlength="20" indexed="true"  />
                  </td>
                </tr>
              </table>
          </td>
      <td>
      </tr>
</logic:iterate>
</table>

</div>
</html:form>
</body>
</html:html>
