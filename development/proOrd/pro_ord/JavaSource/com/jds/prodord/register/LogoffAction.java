package com.jds.prodord.register;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class LogoffAction extends Action{
    public ActionForward perform(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest req,
                                 HttpServletResponse res
    ) throws java.io.IOException, javax.servlet.ServletException {
    	HttpSession session = req.getSession();
    	
    	session.invalidate();
    	
		return (mapping.findForward("logoff"));    		
    	
    }
}


