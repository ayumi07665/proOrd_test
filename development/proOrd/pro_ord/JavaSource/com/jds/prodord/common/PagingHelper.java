package com.jds.prodord.common;

import org.apache.struts.action.*;
import javax.servlet.http.*;

/**
 * ページ遷移時の前処理、後処理を行うヘルパークラス
 * 
 * 
 * 
 */
public class PagingHelper {
	/**
	 * 前処理がある場合オーバーライドしてください
	 * @return 前処理で発生したエラーのメッセージ。正常に遷移できるときはnull
	 */
	public ActionErrors pageIn(HttpServletRequest request){
		return null;
	} 
	/**
	 * 後処理がある場合オーバーライドしてください
	 * @return 後処理で発生したエラーのメッセージ。正常に遷移できるときはnull
	 */
	public ActionErrors pageOut(HttpServletRequest request){
		return null;
	} 

}

