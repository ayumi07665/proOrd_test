package com.jds.prodord.menu.mastermenu;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.jds.prodord.common.CommonConst;
import com.jds.prodord.menu.GomainAction;
import com.jds.prodord.register.LogonForm;

public class GomasterMenuAction extends GomainAction {
	
	public ActionForward perform(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest req,
		HttpServletResponse res)
		throws java.io.IOException, javax.servlet.ServletException {
		
		GomasterMenuForm gomasterForm = (GomasterMenuForm)form;
		
		HttpSession session = req.getSession();
		LogonForm logonForm =
			(LogonForm) req.getSession().getAttribute(CommonConst.user);
		String forwardName = "success";
		
		//表示区分：'1'の時、マスターメンテメニューは表示不可
		if (logonForm.getHyoKbn().equals("1"))
			forwardName = "failure";
			
		String pagingHelperType = gomasterForm.getPagingHelperType();
		pageOut(req, pagingHelperType);

		return (mapping.findForward(forwardName));

	}

}
