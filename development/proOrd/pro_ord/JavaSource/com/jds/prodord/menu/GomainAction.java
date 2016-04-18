package com.jds.prodord.menu;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.jds.prodord.common.PagingHelper;

public class GomainAction extends Action{
	
	public ActionForward perform(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest req,
		HttpServletResponse res)
		throws java.io.IOException, javax.servlet.ServletException {
   		
   		GomainForm gomainForm = (GomainForm)form;
   		
   		HttpSession session = req.getSession();
   		
		String pagingHelperType = gomainForm.getPagingHelperType();
		pageOut(req, pagingHelperType);
		
		return (mapping.findForward("main"));    		
    	
    }
    
	protected void pageOut(HttpServletRequest req, String pagingHelperType) {
		if(pagingHelperType != null && !pagingHelperType.trim().equals("")){
			try{
				PagingHelper pagingHelper =
					(PagingHelper) Class.forName(pagingHelperType).newInstance();
				pagingHelper.pageOut(req);	
			}catch(ClassNotFoundException cnfe){//クラスが見つからない
				cnfe.printStackTrace();
			}catch(IllegalAccessException iae){//引数なしコンストラクタのアクセス制限に引っかかった
				iae.printStackTrace();
			}catch(InstantiationException ie){//クラスがインターフェイスか抽象クラスでインスタンス生成が不可
				ie.printStackTrace();
			}
		}
	}

}

