<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-nested-ex.tld" prefix="nested-ex" %>

<%@ page 
language="java"

contentType="text/html; charset=SHIFT_JIS"

pageEncoding="SHIFT_JIS"

%>
<%
	String type = (request.getParameter("type") == null)? "1" : request.getParameter("type");
%>
<script>
<!--//
	/* ���������b�Z�[�W�̃X�N���[�� */
	var yokojiku = 3;
	var msgFlg = false;
	function scrollMsg(bgFlg){
		if(document.all("processingLayer").style.visibility != "visible"){
	    	document.all("moveLayer").style.visibility = "hidden";
	    	document.all("infomsgLayer").style.visibility = "hidden";
	    	if(bgFlg)//�u�������ł��v�̌��ɔ����w�i��\��
				document.all("processingBg").style.visibility = "visible";
	    	document.all("processingLayer").style.visibility = "visible";
	    	msgFlg = true;
	    }
	    if(!msgFlg){
			document.all("processingBg").style.visibility = "hidden";
	    	document.all("processingLayer").style.visibility = "hidden";
	    	return;
	    }
		move();
		++yokojiku;
		if(yokojiku > 200)
			yokojiku = 0; 
		setTimeout("scrollMsg()",20);
	}
	function stopMsg(){	
		msgFlg = false;//��������ł�����b�Z�[�W���\����
	}
	function move(){
//		if(document.all) 
		obj = document.all("processingLayer").style;
		obj.left = yokojiku + "px";
	}
//-->
</script>
<!-- ��������ł�����b�Z�[�W -->
<div id="processingLayer" class="info_msg infomsgLayer" style="z-index:3;top:12px;">
	<bean:message key="info.processing"/>
</div>
<div id="processingBg" style="background-color:#FFFFFF;z-index:2;top:9px;height:20px;width:300px;" class="infomsgLayer">�@
</div>
<script>
<% if(type.equals("1")){ %>
	//��ʕ`�撆�p��������ł�����b�Z�[�W�\��
	move();
	scrollMsg(true);
<% }%>
</script>
