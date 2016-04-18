<!DOCTYPE HTML>

<%@ page contentType="text/html; charset=Shift_JIS" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld"  prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-form.tld" prefix="form" %>
<%@ taglib uri="/WEB-INF/struts-nested-ex.tld" prefix="nested-ex" %>

<html:html>

<head>
<TITLE><bean:message key="title.fzkhinksipaste"/></TITLE>
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

	var prsFksFirst = "";

	/*
	 ���M�@�\�t���v���_�E����onKeyDown�ŁA���M�������邩�ǂ����`�F�b�N����
	*/
	function checkOnKeyDown(firstVal,currentVal){
		//�l���ω����Ă��Ȃ����false
		if(firstVal == currentVal)
			return false;
		//Tab��Enter�������ꂽ�ꍇ
		if(event.keyCode == 9 || event.keyCode == 13){
			return true;
		}
		return false;
	}

	/*�u�v���X�^�����ށv�h���b�v�_�E����onClick��
	���M���Ă��悢�����肵��������form��submit����*/
	function changePrsFksOnClick(currentVal){	
		if(checkOnClick(prsFksFirst,currentVal))
			changePrsFks();
	}

	/*
	 ���M�@�\�t���v���_�E����onClick�ŁA���M���s�����ǂ����`�F�b�N����
	*/
	function checkOnClick(firstVal,currentVal){
		var ret;
		//�l���ω����Ă��Ȃ����false
		if(firstVal == currentVal){
			setAllowSubmitFlg(0);//���M�s��
			ret = false;
		//���M���t���O�������Ă�����true
		}else if(allowSubmitFlg == 1)				
			ret = true;
	
		/*���M���t���O���u�����N�̂Ƃ�
		 �i��x�ڂ�click���ꂽ�Ƃ��j�͑��M���t���O�𗧂Ă邾��*/
		if(allowSubmitFlg == 0){
			setAllowSubmitFlg(1);
			ret = false;
		}
		return ret;
	}

	/*
	 ���M���t���O(0�̎��͑��M�s��)
	 checkOnClick(firstVal,currentVal)�Ŏg�p
	*/
	var allowSubmitFlg = 0;
	function setAllowSubmitFlg(flg){
		allowSubmitFlg = flg;
	}

	/*
	 ���M�@�\�t���v���_�E����onBlur�ŁA
	 �t�H�[�J�X���O�����Ƃ������邩�ǂ����`�F�b�N����
	*/
	function checkOnBlur(obj,firstVal){
		//�l���ω����Ă��Ȃ���΃`�F�b�N���Ȃ�
		if(firstVal == obj.value)
			return;
	}
	
	//�u�v���X�^�����ށv�ύX��form��submit����
	function changePrsFks(){
		document.manualOrderPasteForm.prsFksChangeFlg.value ="1";
		document.manualOrderPasteForm.submit();
	}

//-->

</SCRIPT>
<LINK rel="stylesheet" href="theme/prodord1.css<nested-ex:nocache-param />" type="text/css">
<LINK href="theme/commonItemSize.css<nested-ex:nocache-param />" rel="stylesheet" type="text/css">

</head>


<BODY>

<div id="Layer1" style="position:absolute; left:800px; top:15px; width:130px; height:60px; z-index : 1;">
	<table><tr>
		  <td align="left">
             <html:form action="/Gomain" target="_parent">
				<html:submit styleClass="button2" property="submit" style="width:115px">
				<bean:message key="button.mainmenu"/>
				</html:submit>
				<html:hidden property="pagingHelperType" value="com.jds.prodord.master.fzkhinksipaste.FzkHinKsiPastePagingHelper"/>
				</html:form>
		  </td>
          <td>
             <html:form action="/Logoff" target="_parent">
				<html:submit property="submit" styleClass="button2" style="64px">
				<bean:message key="button.logoff"/>
				</html:submit>
				</html:form>
     </tr>
     </table>
</div>

<html:form action="/fzkHinKsiPaste" target="_self" onsubmit="return acceptSubmit();">
<bean:define id="fmForm" name="fzkHinKsiPasteForm" type="com.jds.prodord.master.fzkhinksipaste.FzkHinKsiPasteForm"/>
<%--
<html:hidden property="prsFksChangeFlg" />
--%>
<table><tr>
	<td align="left">
    <table style="border:0"  cellpadding="1" cellspacing="0"><tr>
        <td style="width:90px" class="title_dark"><bean:message key="label.daikai"/></td><td></td>
        <td style="width:70px" class="title_light"> 
          <table style="width:70px;height:25px;" cellspacing="0" cellpadding="2"><tr>
           <td class="details" align="center">
           	  <bean:write name="fmForm" property="queryDaiKaiSkbCod" ignore="true"/></td>
          </tr></table>
        </td>
        </tr></table>
        </td>
</tr></table>

<br><br>

<logic:present  name="SERVICE_MESSAGE">
	<DIV class="service_msg"><bean:write name="SERVICE_MESSAGE" /></DIV>
</logic:present>
<logic:present  name="INFOMSG_KEY">
		<DIV class="info_msg"><bean:write name="INFOMSG_KEY" /></DIV>
</logic:present>
<html:errors property=""/>

<hr><br>

<DIV class="center" style="width:900px;margin-bottom:8px;">

<%-- �\�t�� --%>
<logic:equal name="fmForm" property="cpPasteMode" value="1">
	<table border="0" style="width:900px;text-align:left;" cellspacing="8">
	<tr>
		<td style="width:87px;height:22px;" class="title_light"><bean:message key="label.pasteitem"/></td>
		<td style="width:347px">
			<table border="0" cellpadding="2" cellspacing="1" style="background-color : #6b6e6c;">
			<tr class="details" align="left">
			<td style="width:100px;height:22px;"><bean:message key="label.kigbng"/></td>
			<td style="width:100px"><bean:message key="label.fukshizai"/></td>
			<td style="width:100px"><bean:message key="label.sirhaccod"/></td>
			</tr>
			</table>
		</td>
		<td></td>
	</tr>
	<tr><%-- �f�[�^�y�[�X�g�G���A --%>
		<td colspan="3"><html:textarea property="cpPaste" cols="100" rows="15"/></td>
	</tr>
	<tr>
		<td colspan="3"><bean:message key="label.maxpasterow"/>�@<%=fmForm.ROW_MAX%><bean:message key="label.ken"/></td>
	</tr>
	</table>
</logic:equal>



<%-- �\�t�� --%>
<logic:notEqual name="fmForm" property="cpPasteMode" value="1">
<DIV style="width:940px; overflow:auto">

	<table border="0" style="width:920px" align="left" cellspacing="2">
		<tr >
		
<%for (int i = 0; i < 3; i++) {%>
			<td>
			<table border="0" cellpadding="2" cellspacing="1" >
				<tr>
				<td style="width:36px" class="title_light">NO</td>
			    <td style="width:142px" class="title_light"><bean:message key="label.kigbng"/></td>
			    <td style="width:47px" class="title_light"><bean:message key="label.fukshizai"/></td>
			    <td style="width:59px" class="title_light"><bean:message key="label.sirhaccod"/></td>
				<td style="width:12px" ></td>
			</tr>
			</table>
			</td>
<%}%>

		</tr>
</table>
</DIV>

<DIV style="width:940px;height:318px;overflow:auto">
	<table border="0" style="width:920px" align="left" cellspacing="2">

<%	String width_k ="129",width_h="56",colspan="3";
%>
<%	int adj = fmForm.getFormRows().size() % 3>0 ? 1:0;
	for (int i = 0; i < (fmForm.getFormRows().size() / 3) + adj; i++) {%>
		<tr valign="top">
		<logic:iterate name="fmForm" property="formRows" indexId="index" id="formRow" 
					type="com.jds.prodord.master.fzkhinksipaste.FzkHinKsiPasteFormRow"
	
					offset="<%=String.valueOf(i * 3)%>" length="3">  

			<td>
				<table border="0" cellpadding="2" cellspacing="1" class="detailtable_back">
				<tr class="details">
					<td style="width:31px;height:24px" class="index" align="center" rowspan="2"><%=index.intValue() + 1%></td>
				    <td style="width:<%=width_k%>px;text-align:left;">
						<html:text name="formRow" property="kigBng" style="text-align: left" styleClass="s_21" maxlength="13" indexed="true"/>
					</td>
				    <td style="width:44px;text-align:left;"><html:text name="formRow" property="fukSziCod" style="text-align: left" styleClass="s_5" maxlength="3" indexed="true"/>
					</td>
				    <td style="width:<%=width_h%>px;text-align:left;">
				    	<html:text name="formRow" property="sirSk" style="text-align: left" styleClass="s_7" maxlength="7" indexed="true"/>
				    </td>
				</tr>
				<tr class="details">
					<%String property = "ROW_"+index;%>
					<td colspan="<%=colspan%>" style="padding:1px;text-align:left;"><html:errors property="<%=property%>"/></td>
				</tr>
				</table>			
			</td>
			<td style="width:9px"></td>
		</logic:iterate>
		</tr>
	<%}%>

	</table>
</DIV>
</logic:notEqual>

</div>

<br><hr>
<div style="position:relative; text-align: right; margin-top:20px; margin-right:10px;">

	<logic:equal name="fmForm" property="cpPasteMode" value="1">
		<html:submit property="command" styleClass="button" style="width:70px" tabindex="17">
			<bean:message key="button.paste"/>
		</html:submit>�@
	</logic:equal>
	<logic:notEqual name="fmForm" property="cpPasteMode" value="1">
		<html:submit property="command" styleClass="button" style="width:70px" tabindex="17">
			<bean:message key="button.execute"/>
		</html:submit>�@
	</logic:notEqual>

	<html:submit property="command" styleClass="button" style="width:70px" tabindex="17">
		<bean:message key="button.clear"/>
	</html:submit>
</div>
</html:form>

</BODY>

</html:html>

