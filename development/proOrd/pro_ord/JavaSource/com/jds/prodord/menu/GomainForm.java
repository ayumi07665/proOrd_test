package com.jds.prodord.menu;

import org.apache.struts.action.ActionForm;

public class GomainForm extends ActionForm {

	private String pagingHelperType;

	public String getPagingHelperType() {
		return pagingHelperType;
	}
	public void setPagingHelperType(String pagingHelperType) {
		this.pagingHelperType = pagingHelperType;
	}

}
