package com.jds.prodord.common.errors;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.jds.prodord.common.CommonConst;

/**
 * @version 	1.0
 * @author
 */
public class CommonErrorAction extends Action {

	public ActionForward perform(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws IOException, ServletException {

		ActionForward forward = new ActionForward();
		// return value
		String reason = request.getParameter("reason");
				
		forward = mapping.findForward(reason);

		//正常にエラー画面へ遷移できるはずなのでエラーカウントをクリア
		request.getSession().removeAttribute(CommonConst.ERROR_COUNT);
		// Finish with
		return (forward);

	}
}
