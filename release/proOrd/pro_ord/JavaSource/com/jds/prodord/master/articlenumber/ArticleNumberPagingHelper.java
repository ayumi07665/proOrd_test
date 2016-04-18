/**
* ArticleNumberPagingHelper  品番マスターメンテナンス画面遷移補助クラス
*
*	作成日    2003/08/25
*	作成者    （ＮＩＩ） 
* 処理概要    メニューとの画面遷移時に前処理、後処理を行うクラス。
*
*/

package com.jds.prodord.master.articlenumber;

import com.jds.prodord.common.PagingHelper;
import com.jds.prodord.common.CommonConst;
import javax.servlet.http.*;
import org.apache.struts.action.*;

public class ArticleNumberPagingHelper extends PagingHelper {


	/**
	 * 前処理
	 * @return 前処理で発生したエラーのメッセージ。正常に遷移できるときはnull
	 */
	public ActionErrors pageIn(HttpServletRequest request){

		return null;
	} 

	/**
	 * 後処理
	 * @return 後処理で発生したエラーのメッセージ。正常に遷移できるときはnull
	 */
	public ActionErrors pageOut(HttpServletRequest request){
    	HttpSession session = request.getSession(); 
		session.removeAttribute(CommonConst.FORMBEAN_NAME_ARTICLENUMBER);
		return null;
	} 


}

