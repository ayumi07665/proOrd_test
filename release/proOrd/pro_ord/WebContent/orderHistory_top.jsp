<!DOCTYPE HTML>
<%@ page contentType="text/html; charset=Shift_JIS" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld"  prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-form.tld" prefix="form" %>
<%@ taglib uri="/WEB-INF/struts-nested-ex.tld" prefix="nested-ex" %>
<%@ page import="com.jds.prodord.reference.orderhistory.OrderHistoryTopForm" %>

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
        for (var i = 0; i < document.all.length; i++) {
          document.all(i).style.visibility = "hidden";
        }
        if(document.orderHistoryTopForm.elements["prcKbn"].value == "1"){
        	//明細非表示、処理中メッセージ表示・move
	        parent.midF.Layer2.style.visibility = "hidden";
        	parent.midF.scrollMsg();
        }
        parent.midF.moveLayer.style.visibility = "hidden";
        return true;
    }
    
    function checkPrcKbn(){
        //処理区分チェック
		var fAnswer;
		if(document.orderHistoryTopForm.prcKbn.value == "2"){
			fAnswer = confirm('OKﾎﾞﾀﾝを押すとﾀﾞｳﾝﾛｰﾄﾞを開始します。\n\nﾀﾞｳﾝﾛｰﾄﾞﾀﾞｲｱﾛｸﾞが表示されるまでお待ちください...');
			if(!fAnswer)
				return false;
		}
		return true;
    }

	window.onscroll= function pageMove(){
		var L = document.documentElement.scrollLeft || document.body.scrollLeft;
		if(parent.midF.document.body != null)
			parent.midF.document.body.scrollLeft = L;
		if(parent.midF.document.documentElement != null)
			parent.midF.document.documentElement.scrollLeft = L;
	}
	
	function movefocus(i, n) {
		checkMode();
		if(i.form.elements[n].disabled != true)
    		i.form.elements[n].focus();
	}
	
	function checkMode(){
		var i;
		var flg = 0;
		with(document.orderHistoryTopForm){
			for (i = 0; i < elements.length; i++ ) {
//alert(elements[i].type);
		        if (elements[i].type == "checkbox" ) {
//alert(elements[i].checked);
//alert(elements[i].name);
		            flg = (elements[i].checked == true)? 0 : 1;
		        }
			    if(elements[i].type == "text" || elements[i].type == "select-one" || elements[i].type == "select-multiple"){
			        if(elements[i].name == "prcKbn")
			        	continue;
			        if(flg == 1){
						elements[i].disabled = true;
						elements[i].style.backgroundColor = '#efefef';
					}else{
						elements[i].disabled = false;
						elements[i].style.backgroundColor = '#ffffff';
					}
				}
		    }
	    }
	}

	function formSubmit(){
		if(parent.midF.document.body == null){
			setTimeout("formSubmit()",1000);
		}else{
			parent.midF.document.orderHistoryMidForm.command.value = "INIT";
			parent.midF.document.orderHistoryMidForm.submit();
		}
	}
	
	function nextfocus(keta,mx,No){
		if(mx <= keta){
			document.all['date'+ (No + 1)].focus();
			document.all['date'+ (No + 1)].select();
		}
	}
	
	function scrollForError(){
		<logic:present name="org.apache.struts.action.ERROR">
			document.body.scrollTop = 85;
		</logic:present>
	}
	
// -->

</script>
<LINK href="theme/prodord1.css<nested-ex:nocache-param />" rel="stylesheet" type="text/css">
<LINK href="theme/commonItemSize.css<nested-ex:nocache-param />" rel="stylesheet" type="text/css">
</head>
<BODY onLoad="formSubmit(),checkMode(),scrollForError();">
<html:form action="/orderHistoryTop" target="_self" onsubmit="return acceptSubmit();">
<bean:define id="fmTopForm" name="orderHistoryTopForm" type="com.jds.prodord.reference.orderhistory.OrderHistoryTopForm"/>

<div id="Layer2" style="position:absolute; left:10px; top:190px; width:1370px; height:1px; padding-bottom:10px; z-index : 2;">
	<html:errors/>
   	<logic:present  name="SERVICE_MESSAGE">
		<br><DIV class="service_msg"><bean:write name="SERVICE_MESSAGE" /></DIV>
   	</logic:present>
</div>
   	
<div id="Layer1" style="position:absolute; left:5px; top:5px; width:1490px; height:120px; z-index : 1;">
<table border="0" style="width:1490px;height:120px;" cellspacing="0">
	<tr>
		<td align="left">
        	<table border="0"  cellpadding="1" cellspacing="0">
        		<tr>
					<td style="width:90px" class="title_dark"><bean:message key="label.daikai"/></td>
					<td></td>
					<td style="width:70px" class="title_light"> 
						<table bgcolor="#FFFFFF" style="width:70px;height:25px;" cellspacing="2" cellpadding="2"><tr>
							<td class="details" align="center">
								<bean:write name="fmTopForm" property="queryDaiKaiSkbCod" ignore="true"/>
					        </td>
						</tr></table>
			        </td>
				</tr>
			</table>
		</td>
	</tr>
  <tr><td>
  	<table cellspacing="1"><tr valign="middle">
          <td style="width:65px" class="title_neutral"><bean:message key="label.shiteiJoken"/></td>
          <td style="width:1px"></td>
          <td style="width:80px"class="title_dark"><bean:message key="label.hacsyoselect"/></td>
          <td style="width:70px" class="title_light">
			<html:radio property="rb_HacSyo" value="1" style="margin-left:-5px"/><div style="float:right;padding-top:3px;padding-right:3px;"><bean:message key="label.misyryk"/></div></td>
          <td style="width:70px" class="title_light">
			<html:radio property="rb_HacSyo" value="2" style="margin-left:-5px"/><div style="float:right;padding-top:3px;padding-right:3px;"><bean:message key="label.syrzmi"/></div></td>
          <td style="width:70px" class="title_light">
			<html:radio property="rb_HacSyo" value="3" style="margin-left:-10px"/><div style="float:right;padding-top:3px;padding-right:10px;"><bean:message key="radio.all"/></div></td>
		  <td style="width:8px"></td>
          <td style="width:90px" class="title_dark"><bean:message key="label.buncodselect"/></td>
          <td style="width:70px" class="title_light">
           　<html:radio property="rb_BunCod" value="1" style="margin-left:-5px"/><div style="float:right;padding-top:3px;padding-right:5px;"><bean:message key="label.prs"/></div></td>
          <td style="width:70px" class="title_light">
		   　<html:radio property="rb_BunCod" value="2" style="margin-left:-5px"/><div style="float:right;padding-top:3px;padding-right:3px;"><bean:message key="label.fukshizai"/></div></td>
          <td style="width:70px" class="title_light"> 
		   　<html:radio property="rb_BunCod" value="3" style="margin-left:-10px"/><div style="float:right;padding-top:3px;padding-right:10px;"><bean:message key="radio.all"/></div></td>
				<td align="left" style="width:6px"></td>
				<td colspan="2" style="width:95px" >
					<table class="title_light" style="width:100px" >
						<tr> 
							<td><bean:message key="label.kbn"/></td>
						</tr>
					</table>
				</td>
        </tr>
      </table>
    </td></tr>
   <tr><td>
  	  <table cellspacing="1"><tr>
          <td style="width:80px"><div class="title_light" style="padding:3px;"><bean:message key="label.hacskicod"/></div></td>
          <td style="width:2px"></td>
          <td align="center"> 
    		<html:text property="queryHacCod" styleClass="s_10" maxlength="6"  onkeypress="pageMove()"/>
          </td>
          <td style="width:8px"></td>          
          <td style="width:70px"><div class="title_light" style="padding:3px;"><bean:message key="label.kigbng"/></div></td>
          <td style="width:2px"></td>
          <td align="center"> 
    		<html:text property="queryKigBng" styleClass="s_20" maxlength="13"  />
          </td>
          <td style="width:10px"></td>
          <td style="width:60px"><div class="title_light" style="padding:3px;"><bean:message key="label.hbidte"/></div></td>
          <td style="width:2px"></td>
          <td align="center">
            <table border="0" cellspacing="0"><tr><td bgcolor="lightGray">
              <table bgcolor="#FFFFFF" style="width:93px;height:5px;" cellspacing="0" cellpadding="0"><tr>
    				<td class="details">			 
				 	<html:text  property="queryHbiDte_Y" styleClass="s_1" maxlength="2" styleId="date1" onkeypress="nextfocus(value.length,maxLength,1)"/>
			 	/
			 	<html:text  property="queryHbiDte_M" styleClass="s_1" maxlength="2" styleId="date2" onkeypress="nextfocus(value.length,maxLength,2)"/>
			 	/
				 <html:text  property="queryHbiDte_D" styleClass="s_1" maxlength="2" styleId="date3"/>
					 </td></tr></table>
				</td></tr></table>
			 </td>
			 <td>
			 <table cellspacing="1"><tr><td style="width:9px"></td>
				 <td style="width:70px" class="title_light">
				　<html:radio property="rb_Nyuko" value="1" style="margin-left:-5px"/><div style="float:right;padding-top:3px;padding-right:3px;"><bean:message key="label.mnyk"/></div></td>
				 <td style="width:70px" class="title_light"> 
				　<html:radio property="rb_Nyuko" value="2" style="margin-left:-10px"/><div style="float:right;padding-top:3px;padding-right:10px;"><bean:message key="radio.all"/></div></td>
				 </tr></table>
			 </td>
				<td align="left"> 
				 	<div style="width:23px;float:left;"><html:checkbox property="checkKbn" value="true" onclick="movefocus(this,'kbn')"/></div>　
				 	<div style="float:left">
			 		<html:select property="kbn" value="<%=fmTopForm.getKbn()%>">
				 		<html:option value="1"><bean:message key="select.sinpu"/></html:option>
				 		<html:option value="0"><bean:message key="select.kyuhu"/></html:option>
						<html:option value="2"><bean:message key="select.sample"/></html:option>
						<html:option value="4"><bean:message key="select.tokhan"/></html:option>
						<%if(fmTopForm.getQueryDaiKaiSkbCod().equals("K")){%>
							<html:option value="6"><bean:message key="select.hicatalog"/></html:option>
						<%}%>
						<html:option value="3"><bean:message key="select.demoban"/></html:option>
						<html:option value="5"><bean:message key="select.sonota"/></html:option>
					</html:select>
					</div>
				</td>
		  </tr>
		</table>
  </td></tr>

	<tr><td>
		<table border="0"  align="left" cellspacing="0" style="height:55px">
			<tr>
				<td align="left" style="width:150px" colspan="2"> 
					<table  class="title_light" style="width:180px">
						<tr> 
							<td></td>
							<td> <bean:message key="label.hacsyobng"/> <bean:message key="label.fromto"/> </td>
						</tr>
					</table>
				</td>
				<td align="left" style="width:5px"></td>
				<td align="left" style="width:150px" colspan="2"> 
					<table  class="title_light" style="width:230px">
						<tr> 
							<td></td>
							<td><bean:message key="label.hacdte"/> <bean:message key="label.fromto"/> </td>
						</tr>
					</table>
				 </td>
				<td align="left" style="width:5px"></td>
				<td align="left" style="width:150px" colspan="2"> 
					<table class="title_light" style="width:230px">
						<tr> 
							<td></td>
							<td><bean:message key="label.nouki"/> <bean:message key="label.fromto"/> </td>
						</tr>
					</table>
				</td>
				<td align="left" style="width:5px"></td>
				<td align="left" style="width:150px" colspan="2"> 
					<table  class="title_light" style="width:230px">
						<tr> 
							<td></td>
							<td><bean:message key="label.nyodte"/> <bean:message key="label.fromto"/> </td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td align="left" style="width:6px">
					<html:checkbox property="checkHacBng" value="true" onclick="movefocus(this,'queryHacBngFrm')"/>
				</td>
				<td align="left">
					<table border="0" cellspacing="0"><tr><td bgcolor="lightGray">
						<table bgcolor="#FFFFFF" style="width:155px" height="1" cellspacing="0" cellpadding="0">
							<tr>
								<td class="details"> 
									<html:text property="queryHacBngFrm" styleClass="s_9" maxlength="8" />
									<font color="#000000">～</font>
									<html:text property="queryHacBngTo" styleClass="s_9" maxlength="8" />
								</td>
							</tr>
						</table>
					</td></tr></table>
				</td>
				<td >　</td>
				<td align="center" style="width:6px"> 
					<html:checkbox property="checkHacDte" value="true" onclick="movefocus(this,'queryHacDteFrm_Y')"/>
				</td>
				<td align="center">
					<table border="0" cellspacing="0"><tr><td bgcolor="lightGray">
						<table bgcolor="#FFFFFF" style="width:207px;height:1px;" cellspacing="0" cellpadding="0">
							<tr>
								<td class="details">
									<html:text property="queryHacDteFrm_Y" styleClass="s_1" maxlength="2" styleId="date4" onkeypress="nextfocus(value.length,maxLength,4)"/>
										 /
									<html:text property="queryHacDteFrm_M" styleClass="s_1" maxlength="2" styleId="date5" onkeypress="nextfocus(value.length,maxLength,5)"/>
										 /
									<html:text property="queryHacDteFrm_D" styleClass="s_1" maxlength="2" styleId="date6" onkeypress="nextfocus(value.length,maxLength,6)"/>
										 <font color="#000000">～</font>
									<html:text property="queryHacDteTo_Y" styleClass="s_1" maxlength="2" styleId="date7" onkeypress="nextfocus(value.length,maxLength,7)"/>
										 /
									<html:text property="queryHacDteTo_M" styleClass="s_1" maxlength="2" styleId="date8" onkeypress="nextfocus(value.length,maxLength,8)"/>
										 /
									<html:text property="queryHacDteTo_D" styleClass="s_1" maxlength="2" styleId="date9"/>
								</td>
							</tr>
						</table>
					</td></tr></table>
				</td>
				<td>　</td>
				<td align="left" style="width:6px"> 
					<html:checkbox property="checkNki" value="true" onclick="movefocus(this,'queryNkiFrm_Y')"/>
				</td>
				<td align="center">
					<table border="0" cellspacing="0"><tr><td bgcolor="lightGray">
						<table bgcolor="#FFFFFF" style="width:207px;height:1px;" cellspacing="0" cellpadding="0"><tr>
							<td class="details">  
								<html:text property="queryNkiFrm_Y" styleClass="s_1" maxlength="2" styleId="date10" onkeypress="nextfocus(value.length,maxLength,10)"/>
									/
								<html:text property="queryNkiFrm_M" styleClass="s_1" maxlength="2" styleId="date11" onkeypress="nextfocus(value.length,maxLength,11)"/>
									/
								<html:text property="queryNkiFrm_D" styleClass="s_1" maxlength="2" styleId="date12" onkeypress="nextfocus(value.length,maxLength,12)"/>
									 <font color="#000000">～</font>
								<html:text property="queryNkiTo_Y" styleClass="s_1" maxlength="2" styleId="date13" onkeypress="nextfocus(value.length,maxLength,13)"/>
									/
								<html:text property="queryNkiTo_M" styleClass="s_1" maxlength="2" styleId="date14" onkeypress="nextfocus(value.length,maxLength,14)"/>
									/
								<html:text property="queryNkiTo_D" styleClass="s_1" maxlength="2" styleId="date15"/>
							</td>
						</tr></table>
					</td></tr></table>
				</td>
				<td>　</td>
				<td align="left" style="width:6px"> 
					<html:checkbox property="checkNyoDte" value="true" onclick="movefocus(this,'queryNyoDteFrm_Y')"/>
				 </td>
				 <td align="center">
					<table border="0" cellspacing="0"><tr><td bgcolor="lightGray">
						<table bgcolor="#FFFFFF" style="width:207px;height:1px;" cellspacing="0" cellpadding="0"><tr>
							<td class="details">
								<html:text property="queryNyoDteFrm_Y" styleClass="s_1" maxlength="2" styleId="date16" onkeypress="nextfocus(value.length,maxLength,16)"/>
									/
								<html:text property="queryNyoDteFrm_M" styleClass="s_1" maxlength="2" styleId="date17" onkeypress="nextfocus(value.length,maxLength,17)"/>
									/
								<html:text property="queryNyoDteFrm_D" styleClass="s_1" maxlength="2" styleId="date18" onkeypress="nextfocus(value.length,maxLength,18)"/>
									 <font color="#000000">～</font>
								<html:text property="queryNyoDteTo_Y" styleClass="s_1" maxlength="2" styleId="date19" onkeypress="nextfocus(value.length,maxLength,19)"/>
									/
								<html:text property="queryNyoDteTo_M" styleClass="s_1" maxlength="2" styleId="date20" onkeypress="nextfocus(value.length,maxLength,20)"/>
									/
								<html:text property="queryNyoDteTo_D" styleClass="s_1" maxlength="2" styleId="date21"/>
							</td>
						</tr></table>
					</td></tr></table>
				</td>
			</tr>
			</table>
		</td>
	</tr>
  <tr><td style="height:3px"></td></tr>
  <tr><td>
  	  <table border="0" cellpadding="0" cellspacing="0" ><tr> 
			 <td style="width:300px">
				<table border="0" cellspacing="1" cellpadding="0" style="width:300px;height:30px;margin-left:1px;margin-right:1px;">
				<tr class="title_light"  align="center">
					 <td style="width:30px;height:30px;"></td>
					 <td style="width:50px"><bean:message key="label.hacski"/></td>
					 <td style="width:50px"><bean:message key="label.kbn"/></td>
					 <td style="width:100px"><bean:message key="label.kigbng"/></td>
					 <td style="width:60px"><bean:message key="label.hbidte"/></td>
				</tr>
				</table>
		     </td>
			 <td style="width:25px">
			 	<table style="width:25px"><tr><td></tr></table>
			 </td>
			 <td style="width:755px">
				<table border="0" cellspacing="1" cellpadding="0" style="width:755px;height:30px;margin-left:1px;margin-right:1px;">
				<tr class="title_light"  align="center">
					 <td style="width:70px;height:30px;"><bean:message key="label.hacsyobng"/></td>
					 <td style="width:70px"><bean:message key="label.gyobn"/></td>
					 <td style="width:60px"><bean:message key="label.hacdte"/></td>
					 <td style="width:50px"><bean:message key="label.syrzmi"/></td>
					 <td style="width:45px"><bean:message key="label.buncod"/></td>
					 <td style="width:100px"><bean:message key="label.tanka"/></td>
					 <td style="width:100px"><bean:message key="label.kin"/></td>
					 <td style="width:60px"><bean:message key="label.zaisuu"/></td>
					 <td style="width:65px"><bean:message key="label.hacsuu"/></td>
					 <td style="width:60px"><bean:message key="label.nyosuu"/></td>
					 <td style="width:60px"><bean:message key="label.nyodte"/></td>
				</tr></table>
			 </td>
			 <td style="width:7px" bgcolor="#FFF8E9">
			 	<table style="width:7px"><tr><td></tr></table>
			 </td>
			 <td style="width:790px">
			 	<table border="0" cellspacing="1" cellpadding="0" style="width:785px;height:30px;margin-left:1px;margin-right:1px;">
				<tr class="title_light"  align="center">
					 <td style="width:90px;height:30px;"><bean:message key="label.nouki"/></td>
					 <td style="width:230px"><bean:message key="label.nhnski"/></td>
					 <td style="width:230px"><bean:message key="label.comment"/></td>
					 <td style="width:230px"><bean:message key="label.bikou2"/></td>
				  </tr>
				</table>
			  </td>
		</table>
	 </td>
  </tr>
</table>
</DIV>
<div class="title_light" style="position:absolute;left:920px;top:90px;width:50px;">
 	<bean:message key="label.mkrbun"/>
</div>
<div style="position:absolute;left:920px;top:90px;width:120px;height:50px;z-index:3;">
	 <table  cellspacing="0" cellpadding="0"><tr>
		  <td style="width:53px">
		  	<div style="text-align:center;padding-top:26px;">
		  		<html:checkbox property="checkMkrBun" value="true" onclick="movefocus(this,'mkrBun')"/>
  			</div>
		  </td><td>
  			<html:select property="mkrBun" size="3" multiple="true">
					<html:options property="vl_mkrBun"/>
				</html:select>
		  </td></tr>
	  </table>
</div>
<%-- 発注書順 2005/09/16 add --%>
<div style="position:absolute; left : 825px; top : 45px; width : 70px; height : 50px; z-index : 3;">
	<table  cellspacing="0" cellpadding="0"><tr>
		<td class="title_light" style="width:60px;height:20px;">
		  	<bean:message key="label.hacsyojun"/>
		</td>
	</tr><tr>
		<td align="center" style="height:20px" valign="middle">
		  	<html:checkbox property="chkHacJun" value="true"/>
		</td>
	</tr></table>
</div>
<div style="position:absolute; left : 180px; top : 8px; width : 200px; height : 25px; z-index : 3;">
	<table  cellspacing="0" cellpadding="0"><tr>
		<td>
		   <table cellspacing="1"><tr>
	          <td style="width:100px"><div class="title_neutral" style="padding:3px;"><bean:message key="label.syorikbn"/></div></td>
	          <td style="width:2px"></td>
	          <td align="center"> 
	    		<html:select name="fmTopForm" property="prcKbn" styleId="prcKbn">
	    			<html:option value="<%= OrderHistoryTopForm.PRCKBN_SEARCH %>"><bean:message key="select.search"/></html:option>
	    			<html:option value="<%= OrderHistoryTopForm.PRCKBN_DOWNLOAD %>"><bean:message key="select.download"/></html:option>
	    		</html:select>
	          </td></tr>
	       </table>
		</td>
	</tr></table>
</div>
<div id="Layer2" style="position:absolute; left:880px; top : 50px; width : 120px; height : 30px; z-index : 3;">
	 <table  cellspacing="0"><tr>
			 <td style="width:10px"></td>
			 <td align="left" valign="top">
				<html:submit property="command" value="実行" styleClass="button" style="width : 50px" onclick="return checkPrcKbn()"/>	
			 </td>
			 <td style="width:5px"></td>
			 <td align="left" valign="top">
			<html:submit property="command" value="クリア" styleClass="button" style="width : 50px"/>
		  </td>
	  </tr>
	  </table>
</div>
</html:form>
<div id="Layer4" style="position:absolute; left:820px; top:10px; width : 161px; height : 20px; z-index : 2;">
	 <table><tr>
				<td align="left" style="height:20px">
				 <html:form action="/Gomain" target="_parent">
				<html:submit styleClass="button2" property="submit" style="width:115px">
				<bean:message key="button.mainmenu"/>
				</html:submit>
				<html:hidden property="pagingHelperType" value="com.jds.prodord.reference.orderhistory.OrderHistoryPagingHelper"/></html:form></td>
				<td style="height:20px" valign="bottom">
				 <html:form action="/Logoff" target="_parent">
				<html:submit property="submit" styleClass="button2" style="width:64px">
				<bean:message key="button.logoff"/></html:submit></html:form></td>
		  </tr>
	  </table>
</div>


</body>
</html:html>
