package com.jds.prodord.indicate.manualorderpaste;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionErrors;

import com.jds.prodord.common.CommonConst;
import com.jds.prodord.common.PagingHelper;
/**
 * ManualOrderPastePagingHelper  マニュアル発注指示一括貼付画面遷移補助クラス。<BR>
 * <dt><b>作成日: </b></dt>
 * <dd>2007/09/10</dd>
 * <dt><B>作成者: </b></dt>
 * <dd>(NII)</dd>
 * <dt><B>処理概要: </b></dt>
 * <dd>メニューとの画面遷移時に前処理、後処理を行うクラス。</dd>
 * <br>
 * <b>変更履歴</b><br>
 * <table border="1">
 * 	<tr>
 * 		<th width="80">修正日付</th><th width="100">修正者</th><th width="300">修正内容</th>
 * 	</tr>
 * 	<tr>
 * 		<td>yyyy/MM/dd</td>
 * 		<td>(NII)</td>
 * 		<td>・</td>
 * 	</tr>
 * </table>
 */

public class ManualOrderPastePagingHelper extends PagingHelper {

	/**
	 * 前処理
	 * @return 前処理で発生したエラーのメッセージ。正常に遷移できるときはnull
	 */
	public ActionErrors pageIn(HttpServletRequest request){

//    	HttpSession session = request.getSession(); 
//   		com.jds.prodord.register.LogonForm logonForm 
//		  			= (com.jds.prodord.register.LogonForm)session.getAttribute(CommonConst.user);
//		String daiKaiSkbCod = 	logonForm.getDaikaiskbcod();
//
//		ManualOrderForm from = new ManualOrderForm();
//		from.setQueryDaiKaiSkbCod(daiKaiSkbCod);

		
		return null;
	} 

	/**
	 * 後処理
	 * @return 後処理で発生したエラーのメッセージ。正常に遷移できるときはnull
	 */
	public ActionErrors pageOut(HttpServletRequest request){
    	HttpSession session = request.getSession(); 
		clear(session);
		return null;
	}

	public void clear(HttpSession session) {
		session.removeAttribute(CommonConst.FORMBEAN_NAME_MANUALORDERPASTE);
	} 


}

