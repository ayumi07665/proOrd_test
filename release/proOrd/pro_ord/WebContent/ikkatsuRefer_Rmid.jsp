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
<script><!--
	
	window.onscroll = function() {
		pageMove();
		StartMove();
	}
	function pageMove(){
		var L = document.documentElement.scrollLeft || document.body.scrollLeft;
		var T = document.documentElement.scrollTop || document.body.scrollTop;

		if(parent.topRF.document.body != null)
			parent.topRF.document.body.scrollLeft = L;
		if(parent.topRF.document.documentElement != null)
			parent.topRF.document.documentElement.scrollLeft = L;
		if(parent.parent.leftF.midLF.document.body != null)
			parent.parent.leftF.midLF.document.body.scrollTop = T;		
		if(parent.parent.leftF.midLF.document.documentElement != null)
			parent.parent.leftF.midLF.document.documentElement.scrollTop = T;		
	}
	
	function StartMove(){
		var nYPos;
		var nXPos;
		nYPos = document.documentElement.scrollTop || document.body.scrollTop;
		nXPos = document.documentElement.scrollLeft || document.body.scrollLeft;
		moveLayer.style.left = (nXPos + 5) + "px";
		moveLayer.style.top = (nYPos + 15) + "px";
	}

	function moveDown(trglay){
		var mYPos;
		mYPos = document.documentElement.scrollTop || document.body.scrollTop;
		trglay.style.top = (mYPos + 70) + "px";
	}

	function setAlphaFilter(trglay, op){
		trglay.style.filter = 'alpha(opacity=' + op + ')';
		trglay.style.opacity = (op / 100);
	}
	function setShowHide(trglay, type){
		stopMsg();//｢処理中です｣メッセージを非表示
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

	function acceptSubmit(){
		for (var i = 0; i < document.all.length; i++) {
  		  document.all(i).style.visibility = "hidden";
		}
		return true;
	}
	function openNewWindow(){
		var newWindow = document.ikkatsuReferForm.newWindow.value;
		if(newWindow != "0"){
			var forwardNm = "<html:rewrite forward='printOrders'/>";
			wd = window.open(forwardNm,"_blank","scrollbars=1,menubar=1,width=800,height=500,resizable=1");
		}
	}
	function nextfocus(keta,mx,No){
		if(mx <= keta){
			document.all['date'+ (No + 1)].focus();
			document.all['date'+ (No + 1)].select();
		}
	}
	
// --></script>

<LINK rel="stylesheet" href="theme/prodord1.css<nested-ex:nocache-param />" type="text/css">
<LINK href="theme/commonItemSize.css<nested-ex:nocache-param />" rel="stylesheet" type="text/css">
</head>
<% String onLoadMethod = ""; %>
<logic:present  name="org.apache.struts.action.ERROR">
	<% onLoadMethod = "setShowHide(moveLayer,'visible'),dispMsg();"; %>
</logic:present>
<logic:notPresent  name="org.apache.struts.action.ERROR">
	<logic:present  name="INFOMSG_KEY">
		<% onLoadMethod = "setShowHide(moveLayer,'hidden'),dispMsg2(),openNewWindow();"; %>
	</logic:present>
	<logic:notPresent  name="INFOMSG_KEY">
		<% onLoadMethod = "setShowHide(moveLayer,'hidden'),openNewWindow();"; %>
	</logic:notPresent>
</logic:notPresent>
<BODY onLoad="<%= onLoadMethod %>" style="overflow:scroll;">

<html:form action="/ikkatsuRefer" target="_self" onsubmit="return acceptSubmit();">
<bean:define id="fmForm" name="ikkatsuReferForm" type="com.jds.prodord.reference.ikkatsurefer.IkkatsuReferForm"/>
<html:hidden property="command" name="command" value="java.lang.String"/>
<html:hidden property="count" name="count" value="<%=fmForm.getCount()%>"/>
<html:hidden property="newWindow" name="newWindow" value="<%=fmForm.getNewWindow()%>"/>

<logic:present  name="SERVICE_MESSAGE">
	<DIV class="service_msg"><bean:write name="SERVICE_MESSAGE" /></DIV>
</logic:present>
<DIV id="moveLayer" class = "moveLayer" align="left" onMouseover="setAlphaFilter(this, 100)" onMouseout="setAlphaFilter(this, 30)" onClick="moveDown(this)">
	<html:errors/>
</DIV>
<DIV id="infomsgLayer" class = "infomsgLayer" align="left">
	<logic:present  name="INFOMSG_KEY">
		<DIV class="info_msg"><bean:write name="INFOMSG_KEY" /></DIV>
	</logic:present>
</DIV>
<jsp:include page="processing.jsp" />

<table border="0" cellpadding="0" cellspacing="0">
<logic:iterate name="ikkatsuReferForm" id="formRow_R" indexId="index"  property="formRowList_R" type="com.jds.prodord.reference.ikkatsurefer.IkkatsuReferFormRow">
<tr>
			<td align="center" style="height:87px">
				<table style="width:368px" border="0" cellpadding="2" cellspacing="1" class="detailtable_back"><tr class="details">
				      <%String style = (formRow_R.getHacSuu_errSgn())? "background-color:#ff7171;" : "background-color:#ffffff;";%>
					  <td align="left" style="<%= style %>;width:82px;height:24px;">
					  	<div style="float:left;"><html:checkbox name="formRow_R" property="check_prsHacSuu" value="true" indexed="true"/></div>
					  	<div><html:text name="formRow_R" indexed="true" property="prsHacSuu" styleClass="s_8" maxlength="7" style="text-align: right" tabindex="1"/></div>
					  </td>
				      <td style="width:94px" align="left">
				      	<%String idName = "date" + (1 + 3 * index.intValue());%>
						<%String onKeyPress = "nextfocus(value.length,maxLength," + (1 + 3 * index.intValue()) +")";%>
					    <html:text name="formRow_R" indexed="true" property="prsNkiYear" styleClass="s_1" maxlength="2" tabindex="<%= String.valueOf(2 + 5 * index.intValue()) %>" styleId="<%= idName%>" onkeypress="<%= onKeyPress%>"/>
						/<%idName = "date" + (2 + 3 * index.intValue());%>
						<%onKeyPress = "nextfocus(value.length,maxLength," + (2 + 3 * index.intValue()) +")";%>
						<html:text name="formRow_R" indexed="true" property="prsNkiMonth" styleClass="s_1" maxlength="2" tabindex="<%= String.valueOf(3 + 5 * index.intValue()) %>" styleId="<%= idName%>" onkeypress="<%= onKeyPress%>"/>
						/<%idName = "date" + (3 + 3 * index.intValue());%>
						<html:text name="formRow_R" indexed="true" property="prsNkiDay" styleClass="s_1" maxlength="2" tabindex="<%= String.valueOf(4 + 5 * index.intValue()) %>" styleId="<%= idName%>"/>
                      </td>
				      <td style="width:59px" align="left">
				      	<html:text name="formRow_R" indexed="true" property="prsMkrCod" styleClass="s_7" maxlength="6" tabindex="<%= String.valueOf(5 + 5 * index.intValue()) %>"/>
                      </td>
				      <td style="width:60px" align="left">
				      	<bean:write name="formRow_R" property="choksoKbn" ignore="true" />
				      </td>
					  <td style="width:48px" align="center">
					  	<logic:notEqual name="formRow_R" property="prsMnyKei" value="0">
					  		<div style="font-size:10px;font-weight:bold;color:#ff7171;">
					  			<bean:message key="label.mnyk"/><br>
					  			<bean:message key="label.ari"/>
					  		</div>
					  	</logic:notEqual>
					  </td>
				</tr><tr class="details">
				      <td align="right" style="height:24px">
				      	<bean:write name="formRow_R" property="prsMnyKei" ignore="true" />
				      </td>
				      <td style="padding:0px;">
				      	<div style="width:30px;height:28px;text-align:center;float:left;border-right:1px solid #6b6e6c;">
				      		<div style="position:relative;top:7px;"><bean:write name="formRow_R" property="prsKbn1" ignore="true" /></div>
				      	</div>
				      	<div style="position:relative;width:97px;text-align:right;top:7px;">
				      		<bean:write name="formRow_R" property="prsHacSuu1" ignore="true" />
				      	</div>
				      </td>
				      <td align="right">
				      	<bean:write name="formRow_R" property="prsHacNyo1" ignore="true" />
				      </td>
				      <td align="left">
				      	<bean:write name="formRow_R" property="prsHacNki1" ignore="true" />
				      </td>
					  <td align="left" valign="middle" style="padding:0px 1px;">
					  	<div style="width:23px;vertical-align:middle;float:left;"><html:checkbox name="formRow_R" property="check_prs1" value="true" indexed="true"/></div>
					  	<div style="width:25px;font-size:10px;float:left;" class="index"><bean:message key="button.hacshiji"/></div>
					  </td>
				</tr><tr class="details">
				      <td align="right" style="height:24px">
				      	<bean:write name="formRow_R" property="prsNyoKei" ignore="true" />
				      </td>
				      <td style="padding:0px;">
				      	<div style="width:30px;height:28px;text-align:center;float:left;border-right:1px solid #6b6e6c;">
				      		<div style="position:relative;top:8px;"><bean:write name="formRow_R" property="prsKbn2" ignore="true" /></div>
				      	</div>
				      	<div style="position:relative;width:97px;text-align:right;top:7px;">
				      		<bean:write name="formRow_R" property="prsHacSuu2" ignore="true" />
				      	</div>
				      </td>
				      <td align="right">
				      	<bean:write name="formRow_R" property="prsHacNyo2" ignore="true" />
				      </td>
				      <td align="left">
				      	<bean:write name="formRow_R" property="prsHacNki2" ignore="true" />
				      </td>
					  <td align="left" valign="middle" style="padding:0px 1px;">
					  	<div style="width:23px;vertical-align:middle;float:left;"><html:checkbox name="formRow_R" property="check_prs2" value="true" indexed="true"/></div>
					  	<div style="width:25px;font-size:10px;float:left;" class="index"><bean:message key="button.denpyohakko"/></div>
					  </td>
				</tr></table>
			</td>
			<td style="padding:5px;"></td>
			
			<td align="center">
				<table style="width:365px" border="0" cellpadding="2" cellspacing="1" class="detailtable_back"><tr class="details">
				      <td style="width:60px;height:24px;" align="right">
				      	<bean:write name="formRow_R" property="todUriSuu" ignore="true" />
				      </td>
				      <td style="width:60px" align="right">
				      	<bean:write name="formRow_R" property="b3dUri" ignore="true" />
				      </td>
				      <td style="width:55px" align="right">
				      	<bean:write name="formRow_R" property="towUri" ignore="true" />
				      </td>
				      <td style="width:55px" align="right">
				      	<bean:write name="formRow_R" property="tomUri" ignore="true" />
				      </td>
				      <td style="width:55px" align="right">	
				      	<bean:write name="formRow_R" property="tomHpn" ignore="true" />
				      </td>
				      <td style="width:55px" align="right">
				      	<bean:write name="formRow_R" property="tomTna" ignore="true" />
				      </td>
				</tr><tr class="details">
				      <td align="right" style="height:24px">
				      	<bean:write name="formRow_R" property="b1dUri" ignore="true" />
				      </td>
				      <td align="right">
				      	<bean:write name="formRow_R" property="b4dUri" ignore="true" />
				      </td>
				      <td align="right">
				      	<bean:write name="formRow_R" property="b1wUri" ignore="true" />
				      </td>
				      <td align="right">
				      	<bean:write name="formRow_R" property="b1mUri" ignore="true" />
				      </td>
				      <td align="right">
				      	<bean:write name="formRow_R" property="b1mHpn" ignore="true" />
				      </td>
				      <td align="right">
				      	<bean:write name="formRow_R" property="b1mTna" ignore="true" />
				      </td>
				</tr><tr class="details">
				      <td align="right" style="height:24px">
				      	<bean:write name="formRow_R" property="b2dUri" ignore="true" />
				      </td>
				      <td align="right">
				      	<bean:write name="formRow_R" property="b5dUri" ignore="true" />
				      </td>
				      <td align="right">
				      	<bean:write name="formRow_R" property="b2wUri" ignore="true" />
				      </td>
				      <td align="right">
				      	<bean:write name="formRow_R" property="b2mUri" ignore="true" />
				      </td>
				      <td align="right">
				      	<bean:write name="formRow_R" property="b2mHpn" ignore="true" />
				      </td>
				      <td align="right">
				      	<bean:write name="formRow_R" property="b2mTna" ignore="true" />
				      </td>
				</tr></table>
			</td>
			<td style="padding:8px;"></td>
			
			<td align="center">
				<table style="width:278px" border="0" cellpadding="2" cellspacing="1" class="detailtable_back"><tr class="details">
				      <td style="width:65px;height:24px;" align="right" align="right">
					  	<bean:write name="formRow_R" ignore="true" property="fukHacSuu"/>
					  </td>
				      <td style="width:65px" align="left">
				      	<logic:notEqual name="formRow_R" property="fukNkiYear" value="">
				      		<bean:write name="formRow_R" ignore="true" property="fukNkiYear"/>/<bean:write name="formRow_R" ignore="true" property="fukNkiMonth"/>/<bean:write name="formRow_R" ignore="true" property="fukNkiDay"/>
                      	</logic:notEqual>
                      </td>
				      <td style="width:65px" align="left">
                        <bean:write name="formRow_R" ignore="true" property="fukMkrCod"/>
                      </td>
                      <td style="width:32px" align="left">
                      </td>
				<td style="width:32px" align="center">
					<div class="index"><bean:write name="formRow_R" ignore="true" property="check_sgn"/></div>
				</td>
				</tr><tr class="details">
				      <td align="right" style="height:22px">
						<bean:write name="formRow_R" property="fukMnyKei" ignore="true" />
					  </td>
				      <td align="right">
						<bean:write name="formRow_R" property="fukHacSuu1" ignore="true" />
					  </td>
				      <td align="left">
						<bean:write name="formRow_R" property="fukHacNki1" ignore="true" />
					  </td>
					  <td align="center">
                        <bean:write name="formRow_R" ignore="true" property="fukKbn1"/>
                      </td>
					  <td align="center">
					  	<%style = (formRow_R.getCheck_flg().equals("true"))? "background-color:#ff7171;" : "background-color:#ffffff;";%>
					  	<html:checkbox name="formRow_R" property="check_fuk1" value="true" indexed="true" style="<%= style %>"/>
					  </td>
				</tr><tr class="details">
				      <td align="right" style="height:22px">
				      	<bean:write name="formRow_R" property="fukNyoKei" ignore="true" />
				      </td>
				      <td align="right">
				      	<bean:write name="formRow_R" property="fukHacSuu2" ignore="true" />
				      </td>
				      <td align="left">
				      	<bean:write name="formRow_R" property="fukHacNki2" ignore="true" />
				      </td>
				      <td align="center">
                        <bean:write name="formRow_R" ignore="true" property="fukKbn2"/>
                      </td>
					  <td align="center">
					  </td>
				</tr></table>
			</td>
		</tr>
		<tr><td>　</td></tr>
</logic:iterate>
</table>
</html:form>
</body>

</html:html>
