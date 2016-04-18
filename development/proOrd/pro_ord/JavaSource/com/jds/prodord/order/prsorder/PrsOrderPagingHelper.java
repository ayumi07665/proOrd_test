/**
* PrsOrderPagingHelper  プレス発注・副資材画面遷移補助クラス
*
*	作成日    2003/04/28
*	作成者    （ＮＩＩ）岡田 夏美
* 処理概要    メニューとの画面遷移時に前処理、後処理を行うクラス。
*
*	[変更履歴]
*             日付 名前
*  ・内容
*/

package com.jds.prodord.order.prsorder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionErrors;

import com.jds.prodord.common.CommonConst;
import com.jds.prodord.common.PagingHelper;
import com.jds.prodord.indicate.manualorder.ManualOrderPagingHelper;
import com.jds.prodord.indicate.manualorderpaste.ManualOrderPastePagingHelper;
import com.jds.prodord.indicate.teiansuurefer.TeiansuuReferPagingHelper;
import com.jds.prodord.reference.ikkatsurefer.IkkatsuReferPagingHelper;

public class PrsOrderPagingHelper extends PagingHelper {

	/**
	 * 前処理
	 * @return 前処理で発生したエラーのメッセージ。正常に遷移できるときはnull
	 */
	public ActionErrors pageIn(HttpServletRequest request){

    	HttpSession session = request.getSession(); 
   		com.jds.prodord.register.LogonForm logonForm 
		  			= (com.jds.prodord.register.LogonForm)session.getAttribute(CommonConst.user);
		String daiKaiSkbCod = 	logonForm.getDaikaiskbcod();

		PrsOrderForm from = new PrsOrderForm();
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
		IkkatsuReferPagingHelper iktHelper = new IkkatsuReferPagingHelper();
		iktHelper.clear(session);
		ManualOrderPagingHelper moHelper = new ManualOrderPagingHelper();
		moHelper.clear(session);
		ManualOrderPastePagingHelper mopHelper = new ManualOrderPastePagingHelper();
		mopHelper.clear(session);
		TeiansuuReferPagingHelper trHelper = new TeiansuuReferPagingHelper();
		trHelper.clear(session);
		return null;
	}

	public void clear(HttpSession session) {
		session.removeAttribute(CommonConst.INFOMSG_KEY);
		session.removeAttribute(CommonConst.FORMBEAN_NAME_PRSORDER);
		session.removeAttribute(CommonConst.BEAN_NAME_PRSORDER_LEFT);
		session.removeAttribute(CommonConst.PRSORDER_VOS);
		session.removeAttribute(CommonConst.BEAN_NAME_LEFTFORM);
	} 


}

