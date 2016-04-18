package com.jds.prodord.common;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionServlet;

import com.jds.prodord.register.LogonForm;


/**
 * ActionServlet拡張クラス<BR>
 * <dt><b>作成日: </b></dt>
 * <dd>2006/9/19</dd>
 * <dt><B>作成者: </b></dt>
 * <dd>(NII)岡田 夏美</dd>
 * <dt><B>処理概要: </b></dt>
 * <dd>セッションタイムアウトチェックを追加。</dd>
 * <br>
 * <b>変更履歴</b><br>
 * <table border="1">
 *  <tr>
 *      <th width="80">修正日付</th><th width="100">修正者</th><th width="300">修正内容</th>
 *  </tr>
 *  <tr>
 *      <td>yyyy/mm/dd</td>
 *      <td>(NII)</td>
 *      <td></td>
 *  </tr>
 * </table>
 */
public class CommonActionServlet extends ActionServlet{
		
	/** Session-TimeOut URL */
    private static final String TIMEOUTURL = "/error.jsp?reason=session";
	/** Error URL */
	private static final String ERRORURL = "/error.jsp?reason=system";
	/** Error URL */
	private static final String DIRECT_ERRORURL = "/error_system.jsp";
	
	private Logger log = Logger.getLogger(this.getClass());

    /**
     * RequestProcessorのprocessメソッド継承クラス。<br>
     * セッションタイムアウトをここで判定する。
     * これは一覧などListを持った画面でListにnullを挿入するのを防ぐため。
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @throws java.io.IOException 入出力例外
     * @throws javax.servlet.ServletException Servlet例外
     */
    public void process(
        HttpServletRequest request,
        HttpServletResponse response)
        throws IOException, ServletException {
        	
		try {
			String path = processPath(request);
			//エラーチェック
			if(path.startsWith("/commonError")){
				//エラー画面への遷移が連続していないかチェック
				if(!chkError(request))
					//直接システムエラー画面に遷移
					forwardDirectError(request, response);
			}
			String user = formatUsrId(getUserId(request));
			info("usrId=" + user + ": Processing a " + request.getMethod() + " for " + path);
//			checkMemory();
	
	        //Session-TimeOut処理
	        if (chkLogin(request)) {
				//通常処理
				super.process(request, response);
	            return;
	        }

        	forwardTimeOut(request, response);
        	
		} catch (Exception e) {
			e.printStackTrace(System.out);
			forwardError(request, response);
		}
    }
    
	/**
	 * セッションからLogonFormを取り出し、ユーザーIDを取得します。
	 * @param request
	 * @return ユーザーID LogonFormが存在しないときはブランク
	 */
	private String getUserId(HttpServletRequest request) {
		LogonForm logonForm = getLogonForm(request);
		if (logonForm != null) {
			return logonForm.getUser();
		}
		return "";
	}
	
	/**
	 * ユーザーIDの桁数に合わせて右スペース埋めして返します。
	 * @param usrId
	 * @return フォーマット済みusrId
	 */
	private String formatUsrId(String usrId) {
		//ユーザーID
		usrId = (usrId == null)? String.valueOf(usrId) : usrId;
		if(usrId.length() <= 20)
			usrId = StringUtils.rpad(usrId, 20, " ");
		else if(usrId.length() <= 30)
			usrId = StringUtils.rpad(usrId, 30, " ");
		else if(usrId.length() <= 40)
			usrId = StringUtils.rpad(usrId, 40, " ");
		return usrId;
	}

	public static void checkMemory() {
		long total = Runtime.getRuntime().totalMemory();
		long free  = Runtime.getRuntime().freeMemory();
		System.out.println("TotalMemory: " + total +" bytes.");
		System.out.println("FreeMemory : " + free + " bytes.");
		long usedmemory = total - free;
		System.out.println("Used Memory: " + usedmemory + " bytes.");
	}


    /**
     * TimeOut画面にフォワードする
     * @param request　HttpServletRequest
     * @param response　HttpServletResponse
     * @throws java.io.IOException 入出力例外
     * @throws javax.servlet.ServletException Servlet例外
     */
    private void forwardTimeOut(
        HttpServletRequest request,
        HttpServletResponse response)
        throws ServletException, IOException {
          response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + TIMEOUTURL));
    }
    
	/**
	 * エラー遷移用画面にフォワードする
	 * @param request　HttpServletRequest
	 * @param response　HttpServletResponse
	 * @throws java.io.IOException 入出力例外
	 * @throws javax.servlet.ServletException Servlet例外
	 */
	private void forwardError(
		HttpServletRequest request,
		HttpServletResponse response)
		throws ServletException, IOException {
		  response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + ERRORURL));
	}
	
	/**
	 * 直接エラー画面にフォワードする
	 * @param request　HttpServletRequest
	 * @param response　HttpServletResponse
	 * @throws java.io.IOException 入出力例外
	 * @throws javax.servlet.ServletException Servlet例外
	 */
	private void forwardDirectError(
		HttpServletRequest request,
		HttpServletResponse response)
		throws ServletException, IOException {
		  response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + DIRECT_ERRORURL));
	}


    /**
     * ログイン時のチェック処理。
     * ユーザー情報を取得できたか否かで、session-timeoutを判定する。
     * @return Timeoutしている場合 false、していない場合は true を返す
     * @param req HttpServletRequest
     */
    private boolean chkLogin(HttpServletRequest req) {
		String reqURI = req.getRequestURI();
		
		boolean isLogon = reqURI.endsWith("/Logon.do");
		boolean isConnect = reqURI.endsWith("/proOrdConnect.do");
		boolean isLogoff = reqURI.endsWith("/Logoff.do");
		boolean isErrorRequested = reqURI.startsWith(req.getContextPath() + "/error");
		boolean isErrorRequested2 = reqURI.startsWith(req.getContextPath() + "/commonError");

		if (isLogon
			|| isConnect
			|| isLogoff
			|| isErrorRequested
			|| isErrorRequested2) {
			return true;
		}
		//ユーザー情報をSessionから取得することにより
		//Time Outしているかを判定する
		LogonForm logonForm = getLogonForm(req);
        return (logonForm != null);
    }

	/**
	 * sessionからLogonFormを取り出して返します。
	 * @param req
	 * @return LogonForm 存在しないときはnull
	 */
	private LogonForm getLogonForm(HttpServletRequest req) {
		return (LogonForm) req.getSession().getAttribute(CommonConst.user);
	}
		
	/**
	 * エラー画面への遷移が連続していないかチェックします。
	 * @param request
	 * @return エラー画面への遷移が連続していなければ true、している場合は false;
	 */
	private boolean chkError(HttpServletRequest request) {
		HttpSession session = request.getSession();
		int errorCount = countUpError(session);
		if(errorCount > 1){
			session.removeAttribute(CommonConst.ERROR_COUNT);
			return false;
		}
		return true;
	}
	
	/**
	 * エラーをカウントアップします。
	 * @param session
	 * @return エラーカウント
	 */
	private int countUpError(HttpSession session) {
		Integer errorCount = getErrorCount(session);
		errorCount = new Integer(errorCount.intValue() + 1);
		session.setAttribute(CommonConst.ERROR_COUNT, errorCount);
		return errorCount.intValue();
	}

	/**
	 * エラーカウントを取得して返します。
	 * @param session
	 * @return エラーカウント(1回のエラー処理で発生したエラー件数)
	 */
	private Integer getErrorCount(HttpSession session) {
		return (Integer)
			((session.getAttribute(CommonConst.ERROR_COUNT) == null)
				? new Integer(0)
				: session.getAttribute(CommonConst.ERROR_COUNT));
	}
	
	protected void debug(Object o){
		LogUtils.debug(log, o);
	}
	
	protected void info(Object o){
		LogUtils.info(log, o);
	}

}
