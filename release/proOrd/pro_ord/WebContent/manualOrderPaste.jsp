<!DOCTYPE HTML>

<%@ page contentType="text/html; charset=Shift_JIS" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld"  prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-form.tld" prefix="form" %>
<%@ taglib uri="/WEB-INF/struts-nested-ex.tld" prefix="nested-ex" %>

<html:html>

<head>
<TITLE><bean:message key="title.manualorderpaste"/></TITLE>
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
	/*�y�[�W�ǂݍ��ݎ��̒l���擾����*/
	function setFirstValue(){
		prsFksFirst = document.manualOrderPasteForm.prsFks.value;
	}

	/*�u�v���X�^�����ށv�h���b�v�_�E����onKeyDown��
	  ���M���Ă��悢�����肵��������form��submit����*/
	function changePrsFksOnKeyDown(currentVal){
		if(checkOnKeyDown(prsFksFirst,currentVal))
			changePrsFks();
	}

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


<BODY onLoad="setFirstValue()">

<div id="Layer1" style="position:absolute; left:800px; top:15px; width:130px; height:60px; z-index : 1;">
	<table><tr>
		  <td align="left">
             <html:form action="/Gomain" target="_parent">
				<html:submit styleClass="button2" property="submit" style="width:115px">
				<bean:message key="button.mainmenu"/>
				</html:submit>
				<html:hidden property="pagingHelperType" value="com.jds.prodord.indicate.manualorderpaste.ManualOrderPastePagingHelper"/>
				</html:form>
		  </td>
          <td>
             <html:form action="/Logoff" target="_parent">
				<html:submit property="submit" styleClass="button2" style="width:64px">
				<bean:message key="button.logoff"/>
				</html:submit>
				</html:form>
     </tr>
     </table>
</div>

<html:form action="/manualOrderPaste" target="_self" onsubmit="return acceptSubmit();">
<bean:define id="fmForm" name="manualOrderPasteForm" type="com.jds.prodord.indicate.manualorderpaste.ManualOrderPasteForm"/>
<html:hidden property="prsFksChangeFlg" />
<table><tr>
	<td align="left">
    <table border="0"  cellpadding="1" cellspacing="0"><tr>
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


<DIV class="center" style="width:800px;margin-bottom:8px;">
	<table border="0" style="width:800px" cellspacing="8">
		<tr><%--�@�v���X�^�����ށ@--%>
			<td class="title_neutral" style="width:73px;height:24px;">�P</td>
			<td class="title_dark" style="width:146px"><bean:message key="label.prsfks"/></td>
			<td align="left" style="width:134px">
				<html:select property="prsFks" 
					onkeydown="changePrsFksOnKeyDown(this.value)" 
					onclick="changePrsFksOnClick(this.value)" 
					onblur="checkOnBlur(this,prsFksFirst)">
				<bean:define name="fmForm" property="prsFksList" id="prsFksList"/>
					<html:options  collection="prsFksList" property="value" labelProperty="label"/>
				</html:select></td>

			<td style="width:15px"></td>
			<%--�@�敪�@--%>
			<td class="title_neutral" style="width:73px">�Q</td>
			<td class="title_dark" style="width:146px"><bean:message key="label.kbn"/></td>
            <td align="left" style="width:139px">
				<html:select property="kbn">
				<bean:define name="fmForm" property="kbnList" id="kbnList"/>
					<html:options  collection="kbnList" property="value" labelProperty="label"/>
				</html:select></td>
			<td ></td>
		</tr>   	
	</table>
</DIV>


<DIV class="center" style="width:900px;margin-bottom:8px;text-align:left;">

<%-- �\�t�� --%>
<logic:equal name="fmForm" property="cpPasteMode" value="1">
	<table border="0" style="width:900px" cellspacing="8">
	<tr>
		<td style="width:87px;height:22px;" class="title_light"><bean:message key="label.pasteitem"/></td>
		<td style="width:521px">
			<table border="0" cellpadding="2" cellspacing="1" style="background-color : #6b6e6c;">
			<tr class="details">
			<td style="width:100px;height:22px;"><bean:message key="label.kigbng"/></td>
		<%if(fmForm.getPrsFks().equals("1")){%>
			<td style="width:100px"><bean:message key="label.fukshizai"/></td>
		<%}%>
			<td style="width:100px"><bean:message key="label.hacsryo"/></td>
			<td style="width:100px"><bean:message key="label.bikou2"/></td>
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
<DIV class="center" style="width:800px; overflow:auto">

	<table border="0" style="width:780px" align="left" cellspacing="2">
		<tr >

			<td>
			<table border="0" cellpadding="2" cellspacing="1">
				<tr>
				<td style="width:59px" class="title_light">NO</td>
		<%if(fmForm.getPrsFks().equals("0")){%>
			    <td style="width:233px" class="title_light"><bean:message key="label.kigbng"/></td>
			    <td style="width:133px" class="title_light"><bean:message key="label.hacsryo"/></td>
			    <td style="width:333px" class="title_light"><bean:message key="label.bikou2"/></td>
		<%}else {%>
			    <td style="width:190px" class="title_light"><bean:message key="label.kigbng"/></td>
			    <td style="width:98px" class="title_light"><bean:message key="label.fukshizai"/></td>
			    <td style="width:129px" class="title_light"><bean:message key="label.hacsryo"/></td>
			    <td style="width:290px" class="title_light"><bean:message key="label.bikou2"/></td>
		<%}%>
			</tr>
			</table>
			</td>
		</tr>
</table>
</DIV>

<DIV class="center" style="width:800px; height:320px; overflow:auto">
	<table border="0" style="width:780px" align="left" cellspacing="2">

<%	String width_k ="231",width_h="131",width_c="331",colspan="3";
	if(fmForm.getPrsFks().equals("1")){
		width_k ="186";width_h="126";width_c="282";colspan="4";
	}
%>

<%	for (int i = 0; i < (fmForm.getFormRows().size()); i++) {%>
		<tr>
		<logic:iterate name="fmForm" property="formRows" indexId="index" id="formRow" 
					type="com.jds.prodord.indicate.manualorderpaste.ManualOrderPasteFormRow"
					offset="<%=String.valueOf(i)%>" length="1">  
			<td>
				<table border="0" cellpadding="2" cellspacing="1" class="detailtable_back">
				<tr class="details">
					<td style="width:57px;height:24px;" class="index" align="center" rowspan="2"><%=index.intValue() + 1%></td>
				    <td style="width:<%=width_k%>px" align="left" >
						<html:text name="formRow" property="kigBng" style="text-align: left" styleClass="s_25" maxlength="13" indexed="true"/>
					</td>
		<%if(fmForm.getPrsFks().equals("1")){%>
				    <td style="width:95px"><html:text name="formRow" property="fukSziCod" style="text-align: left" styleClass="s_5" maxlength="3" indexed="true"/>
					</td>
		<%}%>
				    <td style="width:<%=width_h%>px" align="right">
				    	<html:text name="formRow" property="suuRyo" style="text-align: right" styleClass="s_12" maxlength="7" indexed="true"/>
				    </td>
				    <td style="width:<%=width_c%>px" align="left">
				    	<html:text name="formRow" property="biKou2" style="text-align: left" styleClass="s_50" maxlength="25" indexed="true"/>
				    </td>
				</tr>
				<tr class="details">
					<%String property = "ROW_"+index;%>
					<td colspan="<%=colspan%>" style="padding:1px;text-align:left;"><html:errors property="<%=property%>"/></td>
				</tr>
				</table>			
			</td>
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

