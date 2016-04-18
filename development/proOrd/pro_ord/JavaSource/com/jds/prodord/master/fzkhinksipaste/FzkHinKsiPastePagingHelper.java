/**
* FzkHinKsiPastePagingHelper  付属品構成マスター一括貼り付け画面遷移補助クラス
*
*	作成日    2007/09/30
*	作成者    （ＮＩＩ）田中
* 処理概要    メニューとの画面遷移時に前処理、後処理を行うクラス。
*
*	[変更履歴]
*/

package com.jds.prodord.master.fzkhinksipaste;

import com.jds.prodord.common.PagingHelper;
import com.jds.prodord.common.CommonConst;
import javax.servlet.http.*;
import org.apache.struts.action.*;

public class FzkHinKsiPastePagingHelper extends PagingHelper {

	/**
	 * 前処理
	 * @return 前処理で発生したエラーのメッセージ。正常に遷移できるときはnull
	 */
	public ActionErrors pageIn(HttpServletRequest request){

//   	HttpSession session = request.getSession(); 
//   		com.jds.prodord.register.LogonForm logonForm 
//		  			= (com.jds.prodord.register.LogonForm)session.getAttribute(CommonConst.user);
//		String daiKaiSkbCod = 	logonForm.getDaikaiskbcod();
//
//		FzkHinKsiPasteForm form = new FzkHinKsiPasteForm();
//		form.setQueryDaiKaiSkbCod(daiKaiSkbCod);
		
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
		session.removeAttribute(CommonConst.FORMBEAN_NAME_MANUALORDER);
	} 


}

