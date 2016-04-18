package com.jds.prodord.common;

import org.apache.log4j.Logger;

/**
 * �J�X�^����O�ł��B<BR>
 * <dt><b>�쐬��: </b></dt>
 * <dd>2005/10/07</dd>
 * <dt><B>�쐬��: </b></dt>
 * <dd>(NII)�g�c</dd>
 * <dt><B>�����T�v: </b></dt>
 * <dd>�J�X�^����O</dd>
 * <b>�ύX����</b><br>
 * <table border="1">
 * 	<tr>
 * 		<th width="80">�C�����t</th><th width="100">�C����</th><th width="300">�C�����e</th>
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
			//SystemException ��2�d�i�[��h������
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
