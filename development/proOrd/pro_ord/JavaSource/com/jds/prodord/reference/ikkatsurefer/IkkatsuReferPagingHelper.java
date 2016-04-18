/**
* IkkatsuReferPagingHelper  一括照会 画面遷移補助クラス
*
*	作成日    2003/03/31
*	作成者    （ＮＩＩ）岡田 夏美
* 処理概要    メニューとの画面遷移時に前処理、後処理を行うクラス。
*
*	[変更履歴]
*             日付 名前
*  ・内容
*/

package com.jds.prodord.reference.ikkatsurefer;

import com.jds.prodord.common.PagingHelper;
import com.jds.prodord.common.CommonConst;
import com.jds.prodord.indicate.teiansuurefer.TeiansuuReferPagingHelper;
import com.jds.prodord.order.prsorder.PrsOrderPagingHelper;

import javax.servlet.http.*;
import org.apache.struts.action.*;

public class IkkatsuReferPagingHelper extends PagingHelper {

	/**
	 * 前処理
	 * @return 前処理で発生したエラーのメッセージ。正常に遷移できるときはnull
	 */
	public ActionErrors pageIn(HttpServletRequest request){

    	HttpSession session = request.getSession(); 
   		com.jds.prodord.register.LogonForm logonForm 
		  			= (com.jds.prodord.register.LogonForm)session.getAttribute(CommonConst.user);
		String daiKaiSkbCod = 	logonForm.getDaikaiskbcod();

		IkkatsuReferForm from = new IkkatsuReferForm();
		from.setQueryDaiKaiSkbCod(daiKaiSkbCod);
//		session.setAttribute(CommonConst.FORMBEAN_NAME_IKKATSU,from);
		
		return null;
	} 

	/**
	 * 後処理
	 * @return 後処理で発生したエラーのメッセージ。正常に遷移できるときはnull
	 */
	public ActionErrors pageOut(HttpServletRequest request){
    	HttpSession session = request.getSession();
		clear(session);
		PrsOrderPagingHelper poHelper = new PrsOrderPagingHelper();
		poHelper.clear(session);
		TeiansuuReferPagingHelper trHelper = new TeiansuuReferPagingHelper();
		trHelper.clear(session);
		return null;
	}

	public void clear(HttpSession session) {
		session.removeAttribute(CommonConst.FORMBEAN_NAME_IKKATSU);
		session.removeAttribute(CommonConst.BEAN_NAME_IKKATSU_LEFT);
		session.removeAttribute(CommonConst.INFOMSG_KEY);
		session.removeAttribute(CommonConst.BEAN_NAME_LEFTFORM);
	} 


}

