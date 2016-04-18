package com.jds.prodord.common;

import org.apache.log4j.Logger;

/**
 * カスタム例外です。<BR>
 * <dt><b>作成日: </b></dt>
 * <dd>2005/10/07</dd>
 * <dt><B>作成者: </b></dt>
 * <dd>(NII)蛭田</dd>
 * <dt><B>処理概要: </b></dt>
 * <dd>カスタム例外</dd>
 * <b>変更履歴</b><br>
 * <table border="1">
 * 	<tr>
 * 		<th width="80">修正日付</th><th width="100">修正者</th><th width="300">修正内容</th>
 * 	</tr>
 *	<tr>
 * 		<td></td>
 * 		<td></td>
 * 		<td></td>
 * 	</tr>
 * </table>
 */
public class SystemException extends Exception {

	private Throwable cause;
	private Logger log = Logger.getLogger(this.getClass());
		
	public SystemException(){
	}
	
	public SystemException(Exception ex){
		if(ex instanceof SystemException) {
			//SystemException の2重格納を防ぐため
			SystemException syse = (SystemException)ex;
			this.cause = syse.getCause();
		} else {
			this.cause = (Throwable)ex;
		}
	}
	
	public void printStackTrace(){
		LogUtils.info(log, "### " + cause.toString() + " ###");
		cause.printStackTrace();
	}
	
	/**
	 * @return
	 */
	public Exception getCause() {
		return (Exception)cause;
	}
	
	/**
	 * @param throwable
	 */
	public void setCause(Throwable throwable) {
		cause = throwable;
	}

}
