/**
* TeiansuuReferPagingHelper  提案数照会指示画面遷移補助クラス
*
*	作成日    2003/02/20
*	作成者    （ＮＩＩ）岡田 夏美
* 処理概要    メニューとの画面遷移時に前処理、後処理を行うクラス。
*
*	[変更履歴]
*             日付 名前
*  ・内容
*/

package com.jds.prodord.indicate.teiansuurefer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionErrors;

import com.jds.prodord.common.CommonConst;
import com.jds.prodord.common.PagingHelper;
import com.jds.prodord.order.prsorder.PrsOrderPagingHelper;
import com.jds.prodord.reference.ikkatsurefer.IkkatsuReferPagingHelper;

public class TeiansuuReferPagingHelper extends PagingHelper {

	/**
	 * 前処理
	 * @return 前処理で発生したエラーのメッセージ。正常に遷移できるときはnull
	 */
	public ActionErrors pageIn(HttpServletRequest request){

    	HttpSession session = request.getSession(); 
   		com.jds.prodord.register.LogonForm logonForm 
		  			= (com.jds.prodord.register.LogonForm)session.getAttribute(CommonConst.user);
		String daiKaiSkbCod = 	logonForm.getDaikaiskbcod();

		TeiansuuReferForm from = new TeiansuuReferForm();
		from.setQueryDaiKaiSkbCod(daiKaiSkbCod);
//		session.setAttribute(CommonConst.FORMBEAN_NAME_TEIANSUU,from);
		
		return null;
	} 

	/**
	 * 後処理
	 * @return 後処理で発生したエラーのメッセージ。正常に遷移できるときはnull
	 */
	public ActionErrors pageOut(HttpServletRequest request){
    	HttpSession session = request.getSession(); 
		clear(session);
		IkkatsuReferPagingHelper iktHelper = new IkkatsuReferPagingHelper();
		iktHelper.clear(session);
		PrsOrderPagingHelper poHelper = new PrsOrderPagingHelper();
		poHelper.clear(session);
		return null;
	}

	public void clear(HttpSession session) {
		session.removeAttribute(CommonConst.FORMBEAN_NAME_TEIANSUU);
		session.removeAttribute(CommonConst.TEIANSUUREFER_VO);
	} 


}

