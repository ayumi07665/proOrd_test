/**
* StockDaysPagingHelper  在庫日数マスターメンテナンス画面遷移補助クラス
*
*	作成日    2003/06/09
*	作成者    （ＮＩＩ）村上  博基
* 処理概要    メニューとの画面遷移時に前処理、後処理を行うクラス。
*
*	[変更履歴]
*             日付 名前
*  ・内容
*/

package com.jds.prodord.master.stockdays;

import com.jds.prodord.common.PagingHelper;
import com.jds.prodord.common.CommonConst;
import javax.servlet.http.*;
import org.apache.struts.action.*;

public class StockDaysPagingHelper extends PagingHelper {

	/**
	 * 前処理
	 * @return 前処理で発生したエラーのメッセージ。正常に遷移できるときはnull
	 */
	public ActionErrors pageIn(HttpServletRequest request){

    	HttpSession session = request.getSession();
   		com.jds.prodord.register.LogonForm logonForm
		  			= (com.jds.prodord.register.LogonForm)session.getAttribute(CommonConst.user);
		String daiKaiSkbCod = 	logonForm.getDaikaiskbcod();

		StockDaysForm form = new StockDaysForm();
		form.setQueryDaiKaiSkbCod(daiKaiSkbCod);
		
		return null;
	} 

	/**
	 * 後処理
	 * @return 後処理で発生したエラーのメッセージ。正常に遷移できるときはnull
	 */
	public ActionErrors pageOut(HttpServletRequest request){
    	HttpSession session = request.getSession(); 
		session.removeAttribute(CommonConst.FORMBEAN_NAME_STOCKDAYS);
		return null;
	} 
		
}

