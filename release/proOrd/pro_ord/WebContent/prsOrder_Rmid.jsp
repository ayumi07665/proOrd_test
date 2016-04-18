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
		L = document.documentElement.scrollLeft || document.body.scrollLeft;
		T = document.documentElement.scrollTop || document.body.scrollTop;

		if(parent.topRF.document.body != null)
			parent.topRF.document.body.scrollLeft = L;
		if(parent.topRF.document.documentElement != null)
			parent.topRF.document.documentElement.scrollLeft = L;
		if(parent.parent.leftF.midLF.document.body != null)
			parent.parent.leftF.midLF.document.body.scrollTop = T;		
		if(parent.parent.leftF.midLF.document.documentElement != null)
			parent.parent.leftF.midLF.document.documentElement.scrollTop = T;		
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
		var newWindow = document.prsOrderForm.newWindow.value;

		if(newWindow == "1"){
			var forwardNm = "<html:rewrite forward="printOrders"/>";
			wd = window.open(forwardNm,"_blank","scrollbars=1,menubar=1,width=800,height=500,resizable=1");
		}
		document.prsOrderForm.newWindow.value = "0";
	}
	function nextfocus(keta,mx,No){
		if(mx <= keta){
			document.all['date'+ (No + 1)].focus();
			document.all['date'+ (No + 1)].select();
		}
	}
	
	var arrHacSuu = new Array();
	var arrKigBng = new Array();
	function setHacSuu(){
		if(document.prsOrderForm.prs_FukSgn.value == "0")
			return;
		var hacSuu = "";
		with(document.prsOrderForm){
			var nMax = count.value;
			var hacSuu;
			for (nIndex = 0; nIndex < nMax; nIndex++){
				
				arrHacSuu[nIndex] = elements["formRow_R["+ nIndex +"].prsHacSuu"].value;
				arrKigBng[nIndex] = parent.parent.leftF.midLF.document.prsOrderLeftForm.elements["formRow_L["+ nIndex +"].kigBng"].value;
				
				if(nIndex != 0 && (arrKigBng[nIndex] != arrKigBng[nIndex - 1])){
					hacSuu = "";
//alert(nIndex+1+". ブレイク");
				}
//alert(nIndex+1+". 1 : "+arrKigBng[nIndex]);
				if(arrHacSuu[nIndex]  != "" && elements["formRow_R["+ nIndex +"].check_prs1"].checked == true){
					hacSuu = arrHacSuu[nIndex];
//alert(nIndex+1+". 2 : "+hacSuu);
//				}else if((arrHacSuu[nIndex]  == "" || arrHacSuu[nIndex]  == hacSuu) && hacSuu != ""){
				}else if(arrHacSuu[nIndex]  == "" && hacSuu != ""){
//alert(nIndex+1+". 3 : "+hacSuu);
					elements["formRow_R["+ nIndex +"].prsHacSuu"].value = hacSuu;
					elements["formRow_R["+ nIndex +"].check_prs1"].checked = true;
				}
				
			}
		}
	}
	
// --></script>
<STYLE>
TABLE {
	table-layout: fixed;
}
</STYLE>
<LINK href="theme/prodord1.css<nested-ex:nocache-param />" rel="stylesheet" type="text/css">
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

<html:form action="/prsOrder" target="_self" onsubmit="return acceptSubmit();">
<bean:define id="fmForm" name="prsOrderForm" type="com.jds.prodord.order.prsorder.PrsOrderForm"/>
<html:hidden property="command" name="command" value="java.lang.String"/>
<html:hidden property="kbn" name="kbn" value="java.lang.String"/>
<html:hidden property="prs_FukSgn" name="prs_FukSgn" value="<%=fmForm.getPrs_FukSgn()%>"/>
<html:hidden property="count" name="count" value="<%=fmForm.getCount()%>"/>
<html:hidden property="newWindow" name="newWindow" value="<%=fmForm.getNewWindow()%>"/>
<html:hidden name="fmForm" property="pre_page" value="<%=fmForm.getPre_page()%>"/>
<html:hidden property="nki_Y" name="nki_Y" value="java.lang.String"/>
<html:hidden property="nki_M" name="nki_M" value="java.lang.String"/>
<html:hidden property="nki_D" name="nki_D" value="java.lang.String"/>

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

<table border="0" cellpadding="0" cellspacing="0" style="width:971px" id="detail">
<logic:iterate name="prsOrderForm" id="formRow_R" indexId="index"  property="formRowList_R" type="com.jds.prodord.order.prsorder.PrsOrderFormRow">
<tr>
			<td style="width:27px;height:70px;" align="center" valign="middle">
				<html:checkbox name="formRow_R" property="check_prs1" value="true" indexed="true"/>
			</td>
			<td style="width:326px" align="center">
				<table border="0" cellpadding="2" cellspacing="1" class="detailtable_back"><tr class="details">
				      <td style="width:60px;height:28px;" align="center">
					  	<html:text name="formRow_R" indexed="true" property="prsHacSuu" styleClass="s_7" maxlength="7" style="text-align: right" tabindex="<%= String.valueOf(1 + 7 * index.intValue()) %>"/>
					  </td>
				      <td style="width:98px" align="center">
				      	<%String idName = "date" + (1 + 3 * index.intValue());%>
						<%String onKeyPress = "nextfocus(value.length,maxLength," + (1 + 3 * index.intValue()) +")";%>
					    <html:text name="formRow_R" indexed="true" property="prsNkiYear" styleClass="s_1" maxlength="2" tabindex="<%= String.valueOf(2 + 7 * index.intValue()) %>" styleId="<%= idName%>" onkeypress="<%= onKeyPress%>"/>
						/<%idName = "date" + (2 + 3 * index.intValue());%>
						<%onKeyPress = "nextfocus(value.length,maxLength," + (2 + 3 * index.intValue()) +")";%>
						<html:text name="formRow_R" indexed="true" property="prsNkiMonth" styleClass="s_1" maxlength="2" tabindex="<%= String.valueOf(3 + 7 * index.intValue()) %>" styleId="<%= idName%>" onkeypress="<%= onKeyPress%>"/>
						/<%idName = "date" + (3 + 3 * index.intValue());%>
						<html:text name="formRow_R" indexed="true" property="prsNkiDay" styleClass="s_1" maxlength="2" tabindex="<%= String.valueOf(4 + 7 * index.intValue()) %>" styleId="<%= idName%>"/>
                      </td>
				      <td style="width:76px" align="center">
				      	<html:text name="formRow_R" indexed="true" property="prsMkrCod" styleClass="s_10" maxlength="6" tabindex="<%= String.valueOf(5 + 7 * index.intValue()) %>"/>
                      </td>
				      <td style="width:70px" align="left">
				      	<bean:write name="formRow_R" property="choksoKbn" ignore="true" />
				      </td>
				</tr><tr class="details">
				      <td style="height:28px" colspan="2" align="center">
				      	<html:text name="formRow_R" indexed="true" property="comment" styleClass="s_28" maxlength="20" style="text-align: left" tabindex="<%= String.valueOf(6 + 7 * index.intValue()) %>"/>
				      </td>
				      <td>
				      	<bean:write name="formRow_R" property="hacNm" ignore="true" />
				      </td>
				      <td align="center">
				      	<html:text name="formRow_R" indexed="true" property="nhnMei" styleClass="s_10" maxlength="20" style="text-align: left" tabindex="<%= String.valueOf(7 + 7 * index.intValue()) %>"/>
				      </td>
				</tr></table>
			</td>
			
			<td style="width:10px"></td>

			<logic:equal name="fmForm" property="prs_FukSgn" value="<%=com.jds.prodord.order.prsorder.PrsOrderForm.PRSHACHU%>">
			
			<td style="width:296px" align="center">
				<table border="0" cellpadding="2" cellspacing="1" class="detailtable_back"><tr class="details">
				      <td align="right" style="height:28px">
				      	<bean:write name="formRow_R" property="prsMnyKei" ignore="true" />
				      </td>
				      <td align="right">
				      	<bean:write name="formRow_R" property="prsHacSuu1" ignore="true" />
				      </td>
				      <td align="right">
				      	<bean:write name="formRow_R" property="prsHacNyo1" ignore="true" />
				      </td>
				      <td align="left">
				      	<bean:write name="formRow_R" property="prsHacNki1" ignore="true" />
				      </td>
				      <td align="center" style="width:28px">
				      	<bean:write name="formRow_R" property="prsKbn1" ignore="true" />
				      </td>
				</tr><tr class="details">
				      <td align="right" style="height:28px">
				      	<bean:write name="formRow_R" property="prsNyoKei" ignore="true" />
				      </td>
				      <td align="right">
				      	<bean:write name="formRow_R" property="prsHacSuu2" ignore="true" />
				      </td>
				      <td align="right">
				      	<bean:write name="formRow_R" property="prsHacNyo2" ignore="true" />
				      </td>
				      <td align="left">
				      	<bean:write name="formRow_R" property="prsHacNki2" ignore="true" />
				      </td>
				      <td align="center">
				      	<bean:write name="formRow_R" property="prsKbn2" ignore="true" />
				      </td>
				</tr></table>
			</td>
			<td style="width:10px"></td>
			</logic:equal>	<!-- logic:equal prs_FukSgn : fmForm.PRSHACHU -->
			
			<td style="width:296px" align="center">
				<table border="0" cellpadding="2" cellspacing="1" class="detailtable_back"><tr class="details">
				      <%-- 副資材コード 2005/09/07 修正--%>
				      <td style="height:28px" align="center">
				      	<%if(fmForm.getPrs_FukSgn().equals("1") && fmForm.getQueryDaiKaiSkbCod().equals("VAP")){%>
						   	<%-- 副資材発注画面でVAP社の場合ﾌﾟﾙﾀﾞｳﾝ項目 --%>
						   	<div style="width:55px; overflow:hidden;">
							   	<bean:define name="fmForm" property="fukSziList" id="fukSziList"/>
							   	<html:select name="formRow_R" property="fukSziCod" indexed="true">
							   		<html:options property="value" collection="fukSziList" labelProperty="label"/>
							   	</html:select>
						   	</div>
						<%}else{%>
                        	<div style="text-align: left;">
                        		<bean:write name="formRow_R" ignore="true" property="fukSziCod"/></div>
						<%}%>
                      </td>
				      <td align="right">
						<bean:write name="formRow_R" property="fukMnyKei" ignore="true" />
					  </td>
					  <td align="right">
						<bean:write name="formRow_R" property="fukHacSuu1" ignore="true" />
					  </td>
				      <td align="left">
						<bean:write name="formRow_R" property="fukHacNki1" ignore="true" />
					  </td>
				      <td align="center" style="width:28px">
				      	<bean:write name="formRow_R" property="fukKbn1" ignore="true" />
				      </td>
				</tr><tr class="details">
					  <td align="right" style="height:28px">
				      	<bean:write name="formRow_R" property="fukZaiSuu" ignore="true" />
				      </td>
				      <td align="right">
				      	<bean:write name="formRow_R" property="fukNyoKei" ignore="true" />
				      </td>
				      <td align="right">
				      	<bean:write name="formRow_R" property="fukHacSuu2" ignore="true" />
				      </td>
				      <td align="left">
				      	<bean:write name="formRow_R" property="fukHacNki2" ignore="true" />
				      </td>
				      <td align="center">
				      	<bean:write name="formRow_R" property="fukKbn2" ignore="true" />
				      </td>
				</tr></table>
			</td>
			
			
			<logic:equal name="fmForm" property="prs_FukSgn" value="<%=com.jds.prodord.order.prsorder.PrsOrderForm.FUKHACHU%>">
			<td style="width:10px"></td>
			<td style="width:296px" align="center">
				<table border="0" cellpadding="2" cellspacing="1" class="detailtable_back"><tr class="details">
				      <td align="right" style="height:28px">
				      	<bean:write name="formRow_R" property="prsMnyKei" ignore="true" />
				      </td>
				      <td align="right">
				      	<bean:write name="formRow_R" property="prsHacSuu1" ignore="true" />
				      </td>
				      <td align="right">
				      	<bean:write name="formRow_R" property="prsHacNyo1" ignore="true" />
				      </td>
				      <td align="left">
				      	<bean:write name="formRow_R" property="prsHacNki1" ignore="true" />
				      </td>
				      <td align="center" style="width:28px">
				      	<bean:write name="formRow_R" property="prsKbn1" ignore="true" />
				      </td>
				</tr><tr class="details">
				      <td align="right" style="height:28px">
				      	<bean:write name="formRow_R" property="prsNyoKei" ignore="true" />
				      </td>
				      <td align="right">
				      	<bean:write name="formRow_R" property="prsHacSuu2" ignore="true" />
				      </td>
				      <td align="right">
				      	<bean:write name="formRow_R" property="prsHacNyo2" ignore="true" />
				      </td>
				      <td align="left">
				      	<bean:write name="formRow_R" property="prsHacNki2" ignore="true" />
				      </td>
				      <td align="center">
				      	<bean:write name="formRow_R" property="prsKbn2" ignore="true" />
				      </td>
				</tr></table>
			</td>
			
			</logic:equal><!-- logic:equal prs_FukSgn : fmForm.FUKHACHU -->	
	
		</tr>
		<tr><td>　</td></tr>
</logic:iterate>
</table>
</html:form>
</body>

</html:html>
