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
	/* 処理中メッセージのスクロール */
	var yokojiku = 3;
	var msgFlg = false;
	function scrollMsg(bgFlg){
		if(document.all("processingLayer").style.visibility != "visible"){
	    	document.all("moveLayer").style.visibility = "hidden";
	    	document.all("infomsgLayer").style.visibility = "hidden";
	    	if(bgFlg)//「処理中です」の後ろに白い背景を表示
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
		msgFlg = false;//｢処理中です｣メッセージを非表示に
	}
	function move(){
//		if(document.all) 
		obj = document.all("processingLayer").style;
		obj.left = yokojiku + "px";
	}
//-->
</script>
<!-- ｢処理中です｣メッセージ -->
<div id="processingLayer" class="info_msg infomsgLayer" style="z-index:3;top:12px;">
	<bean:message key="info.processing"/>
</div>
<div id="processingBg" style="background-color:#FFFFFF;z-index:2;top:9px;height:20px;width:300px;" class="infomsgLayer">　
</div>
<script>
<% if(type.equals("1")){ %>
	//画面描画中用｢処理中です｣メッセージ表示
	move();
	scrollMsg(true);
<% }%>
</script>
