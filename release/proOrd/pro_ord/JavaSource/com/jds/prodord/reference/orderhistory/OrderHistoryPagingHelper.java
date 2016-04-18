/**
* OrderHistoryPagingHelper  発注履歴照会 画面遷移補助クラス
*
*	作成日    2003/04/18
*	作成者    （ＮＩＩ）蛭田 敬子
* 処理概要    メニューとの画面遷移時に前処理、後処理を行うクラス。
*
*	[変更履歴]
*             日付 名前
*  ・内容
*/

package com.jds.prodord.reference.orderhistory;

import com.jds.prodord.common.PagingHelper;
import com.jds.prodord.common.CommonConst;
import javax.servlet.http.*;
import org.apache.struts.action.*;

public class OrderHistoryPagingHelper extends PagingHelper {

	/**
	 * 前処理
	 * @return 前処理で発生したエラーのメッセージ。正常に遷移できるときはnull
	 */
	public ActionErrors pageIn(HttpServletRequest request){

    	HttpSession session = request.getSession(); 
   		com.jds.prodord.register.LogonForm logonForm 
		  			= (com.jds.prodord.register.LogonForm)session.getAttribute("user");
		String daiKaiSkbCod = 	logonForm.getDaikaiskbcod();

		OrderHistoryTopForm from = new OrderHistoryTopForm();
		from.setQueryDaiKaiSkbCod(daiKaiSkbCod);
		
		return null;
	} 

	/**
	 * 後処理
	 * @return 後処理で発生したエラーのメッセージ。正常に遷移できるときはnull
	 */
	public ActionErrors pageOut(HttpServletRequest request){
    	HttpSession session = request.getSession(); 
		session.removeAttribute(CommonConst.FORMBEAN_NAME_ORDERHIST_TOP);
		session.removeAttribute(CommonConst.FORMBEAN_NAME_ORDERHIST_MID);
		session.removeAttribute(CommonConst.ORDERHISTORY_VO);
		session.removeAttribute(CommonConst.INFOMSG_KEY);
		return null;
	} 


}

