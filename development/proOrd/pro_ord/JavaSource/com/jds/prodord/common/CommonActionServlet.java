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
 * ActionServlet�g���N���X<BR>
 * <dt><b>�쐬��: </b></dt>
 * <dd>2006/9/19</dd>
 * <dt><B>�쐬��: </b></dt>
 * <dd>(NII)���c �Ĕ�</dd>
 * <dt><B>�����T�v: </b></dt>
 * <dd>�Z�b�V�����^�C���A�E�g�`�F�b�N��ǉ��B</dd>
 * <br>
 * <b>�ύX����</b><br>
 * <table border="1">
 *  <tr>
 *      <th width="80">�C�����t</th><th width="100">�C����</th><th width="300">�C�����e</th>
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
     * RequestProcessor��process���\�b�h�p���N���X�B<br>
     * �Z�b�V�����^�C���A�E�g�������Ŕ��肷��B
     * ����͈ꗗ�Ȃ�List����������ʂ�List��null��}������̂�h�����߁B
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @throws java.io.IOException ���o�͗�O
     * @throws javax.servlet.ServletException Servlet��O
     */
    public void process(
        HttpServletRequest request,
        HttpServletResponse response)
        throws IOException, ServletException {
        	
		try {
			String path = processPath(request);
			//�G���[�`�F�b�N
			if(path.startsWith("/commonError")){
				//�G���[��ʂւ̑J�ڂ��A�����Ă��Ȃ����`�F�b�N
				if(!chkError(request))
					//���ڃV�X�e���G���[��ʂɑJ��
					forwardDirectError(request, response);
			}
			String user = formatUsrId(getUserId(request));
			info("usrId=" + user + ": Processing a " + request.getMethod() + " for " + path);
//			checkMemory();
	
	        //Session-TimeOut����
	        if (chkLogin(request)) {
				//�ʏ폈��
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
	 * �Z�b�V��������LogonForm�����o���A���[�U�[ID���擾���܂��B
	 * @param request
	 * @return ���[�U�[ID LogonForm�����݂��Ȃ��Ƃ��̓u�����N
	 */
	private String getUserId(HttpServletRequest request) {
		LogonForm logonForm = getLogonForm(request);
		if (logonForm != null) {
			return logonForm.getUser();
		}
		return "";
	}
	
	/**
	 * ���[�U�[ID�̌����ɍ��킹�ĉE�X�y�[�X���߂��ĕԂ��܂��B
	 * @param usrId
	 * @return �t�H�[�}�b�g�ς�usrId
	 */
	private String formatUsrId(String usrId) {
		//���[�U�[ID
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
     * TimeOut��ʂɃt�H���[�h����
     * @param request�@HttpServletRequest
     * @param response�@HttpServletResponse
     * @throws java.io.IOException ���o�͗�O
     * @throws javax.servlet.ServletException Servlet��O
     */
    private void forwardTimeOut(
        HttpServletRequest request,
        HttpServletResponse response)
        throws ServletException, IOException {
          response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + TIMEOUTURL));
    }
    
	/**
	 * �G���[�J�ڗp��ʂɃt�H���[�h����
	 * @param request�@HttpServletRequest
	 * @param response�@HttpServletResponse
	 * @throws java.io.IOException ���o�͗�O
	 * @throws javax.servlet.ServletException Servlet��O
	 */
	private void forwardError(
		HttpServletRequest request,
		HttpServletResponse response)
		throws ServletException, IOException {
		  response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + ERRORURL));
	}
	
	/**
	 * ���ڃG���[��ʂɃt�H���[�h����
	 * @param request�@HttpServletRequest
	 * @param response�@HttpServletResponse
	 * @throws java.io.IOException ���o�͗�O
	 * @throws javax.servlet.ServletException Servlet��O
	 */
	private void forwardDirectError(
		HttpServletRequest request,
		HttpServletResponse response)
		throws ServletException, IOException {
		  response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + DIRECT_ERRORURL));
	}


    /**
     * ���O�C�����̃`�F�b�N�����B
     * ���[�U�[�����擾�ł������ۂ��ŁAsession-timeout�𔻒肷��B
     * @return Timeout���Ă���ꍇ false�A���Ă��Ȃ��ꍇ�� true ��Ԃ�
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
		//���[�U�[����Session����擾���邱�Ƃɂ��
		//Time Out���Ă��邩�𔻒肷��
		LogonForm logonForm = getLogonForm(req);
        return (logonForm != null);
    }

	/**
	 * session����LogonForm�����o���ĕԂ��܂��B
	 * @param req
	 * @return LogonForm ���݂��Ȃ��Ƃ���null
	 */
	private LogonForm getLogonForm(HttpServletRequest req) {
		return (LogonForm) req.getSession().getAttribute(CommonConst.user);
	}
		
	/**
	 * �G���[��ʂւ̑J�ڂ��A�����Ă��Ȃ����`�F�b�N���܂��B
	 * @param request
	 * @return �G���[��ʂւ̑J�ڂ��A�����Ă��Ȃ���� true�A���Ă���ꍇ�� false;
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
	 * �G���[���J�E���g�A�b�v���܂��B
	 * @param session
	 * @return �G���[�J�E���g
	 */
	private int countUpError(HttpSession session) {
		Integer errorCount = getErrorCount(session);
		errorCount = new Integer(errorCount.intValue() + 1);
		session.setAttribute(CommonConst.ERROR_COUNT, errorCount);
		return errorCount.intValue();
	}

	/**
	 * �G���[�J�E���g���擾���ĕԂ��܂��B
	 * @param session
	 * @return �G���[�J�E���g(1��̃G���[�����Ŕ��������G���[����)
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
