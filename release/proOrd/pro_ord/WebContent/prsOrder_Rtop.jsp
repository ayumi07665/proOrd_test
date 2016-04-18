<!DOCTYPE HTML>
<%@ page contentType="text/html; charset=Shift_JIS" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld"  prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-nested-ex.tld" prefix="nested-ex" %>
<html>

<head>

<LINK href="theme/prodord1.css<nested-ex:nocache-param />" rel="stylesheet" type="text/css">
<LINK href="theme/commonItemSize.css<nested-ex:nocache-param />" rel="stylesheet" type="text/css">

<script><!--

	window.onscroll=function pageMove(){
		L = document.documentElement.scrollLeft || document.body.scrollLeft;
		if(parent.midRF.document.body != null)
			parent.midRF.document.body.scrollLeft = L;
			document.body.scrollTop = 0;
		if(parent.midRF.document.documentElement != null)
			parent.midRF.document.documentElement.scrollLeft = L;
			document.documentElement.scrollTop = 0;
	}

// --></script>

<STYLE>

TABLE {

	table-layout: fixed;

}

</STYLE>

<META http-equiv ="X-UA-Compatible" content ="IE=edge"/>
<META http-equiv="Pragma" content="no-cache">
<META http-equiv="Cache-Control" content="no-cache">
<META http-equiv="Expires" content="Thu, 01 Dec 1994 16:00:00 GMT">
<META http-equiv="Content-Style-Type" content="text/css">
<META http-equiv="Content-Type" content="text/html; charset=Shift_JIS">
<META name="GENERATOR" content="IBM WebSphere Studio">

</head>

<BODY>
<bean:define id="fmForm" name="prsOrderForm" type="com.jds.prodord.order.prsorder.PrsOrderForm"/>

<table border="0"  cellpadding="0" cellspacing="0" style="width:960px;height:63px;">
  <tr> 
    <td> </td>
  </tr>
</table>
<table border="0"  cellpadding="1" cellspacing="0" style="width:960px;height:74px;">

		<tr>
            <td style="width:27px;height:12px;" valign="middle"></td>
            <td style="width:324px" class="title_dark">

			</td>
            <td style="width:8px"></td>
            
            <logic:equal name="fmForm" property="prs_FukSgn" value="<%=com.jds.prodord.order.prsorder.PrsOrderForm.PRSHACHU%>">
            
            <td style="width:296px" class="title_dark" valign="middle">
            	<table cellpadding="4" class="title_dark" align="center">
            	<tr><td>
            	<bean:message key="table.prshacinfo"/>
            	</td></tr>
            	</table>
			</td>
			<td style="width:8px"></td>
			
			</logic:equal><!-- logic:equal prs_FukSgn : fmForm.PRSHACHU -->
            
            <td style="width:296px" class="title_dark" valign="middle">
				<table cellpadding="4" class="title_dark" align="center">
            	<tr><td>
				<bean:message key="table.fukszihacinfo"/>
				</td></tr>
            	</table>
			</td>
			
			<logic:equal name="fmForm" property="prs_FukSgn" value="<%=com.jds.prodord.order.prsorder.PrsOrderForm.FUKHACHU%>">
			
            <td style="width:8px"></td>
            <td style="width:296px" class="title_dark" valign="middle">
            	<table cellpadding="4" class="title_dark" align="center">
            	<tr><td>
            	<bean:message key="table.prshacinfo"/>
            	</td></tr>
            	</table>
			</td>
			
			</logic:equal><!-- logic:equal prs_FukSgn : fmForm.FUKHACHU -->
			
        </tr>

		<tr>
            <td style="height:3px">
			</td>
        </tr>

		

	<tr>
            <td style="height:21px"></td>
            <td align="center">
            <table border="0" style="width:324px;height:40px;" cellspacing="1" cellpadding="0"><tr class="title_light">

				<td style="width:63px"><bean:message key="label.hacsuu"/></td>
				<td style="width:101px"><bean:message key="label.nouki"/></td>
				<td style="width:79px"><bean:message key="label.hacski"/></td>
				<td style="width:73px"><bean:message key="label.chksokbn"/></td>

				</tr><tr class="title_light">

				<td colspan="2"><bean:message key="label.comment"/></td>
				<td><bean:message key="label.hacskinm"/></td>
				<td><bean:message key="label.nhnski"/></td>

		</tr></table>
            </td>
            <td style="width:8px"></td>
            
            <logic:equal name="fmForm" property="prs_FukSgn" value="<%=com.jds.prodord.order.prsorder.PrsOrderForm.PRSHACHU%>">
            
            <td align="center"> 

      <table border="0" style="width:296px;height:40px;" cellspacing="1" cellpadding="0"><tr class="title_light">
				
				<td><bean:message key="label.mnykttl"/></td>
				<td><bean:message key="label.hacrki1"/></td>
				<td><bean:message key="label.nykrki1"/></td>
				<td><bean:message key="label.nouki1"/></td>
				<td style="width:30px"></td>

				</tr><tr class="title_light">
				
				<td><bean:message key="label.nyksum"/></td>
				<td><bean:message key="label.hacrki2"/></td>
				<td><bean:message key="label.nykrki2"/></td>
				<td><bean:message key="label.nouki2"/></td>
				<td></td>

	 </tr></table>

			</td>
			<td style="width:8px"></td>
			
			</logic:equal><!-- logic:equal prs_FukSgn : fmForm.PRSHACHU -->
            
            <td align="center"> 

      <table border="0" style="width:296px;height:40px;" cellspacing="1" cellpadding="0"><tr class="title_light">
				
				<td><bean:message key="label.fukszicod"/></td>
				<td><bean:message key="label.mnykttl"/></td>
				<td><bean:message key="label.hacrki1"/></td>
				<td><bean:message key="label.nouki1"/></td>
				<td style="width:30px"></td>

				</tr><tr class="title_light">
				
				<td><bean:message key="label.zaisuu"/></td>
				<td><bean:message key="label.nyksum"/></td>
				<td><bean:message key="label.hacrki2"/></td>
				<td><bean:message key="label.nouki2"/></td>
				<td></td>

	  </tr></table>

    	</td>
    	
    	<logic:equal name="fmForm" property="prs_FukSgn" value="<%=com.jds.prodord.order.prsorder.PrsOrderForm.FUKHACHU%>">
			<td style="width:8px"></td>
            <td align="center"> 

      <table border="0" style="width:296px;height:40px;" cellspacing="1" cellpadding="0"><tr class="title_light">
				
				<td><bean:message key="label.mnykttl"/></td>
				<td><bean:message key="label.hacrki1"/></td>
				<td><bean:message key="label.nykrki1"/></td>
				<td><bean:message key="label.nouki1"/></td>
				<td style="width:30px"></td>

				</tr><tr class="title_light">
				
				<td><bean:message key="label.nyksum"/></td>
				<td><bean:message key="label.hacrki2"/></td>
				<td><bean:message key="label.nykrki2"/></td>
				<td><bean:message key="label.nouki2"/></td>
				<td></td>

	 </tr></table>

			</td>
			
    	</logic:equal><!-- logic:equal prs_FukSgn : fmForm.FUKHACHU -->
    
        </tr></table>
</body>

</html>

