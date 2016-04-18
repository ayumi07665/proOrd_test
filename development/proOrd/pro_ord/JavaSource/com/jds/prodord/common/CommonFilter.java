package com.jds.prodord.common;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * @version 	1.0
 * @author
 */
public class CommonFilter implements Filter {
	/**
	* @see javax.servlet.Filter#void ()
	*/
	public void destroy() {

	}

	/**
	* @see javax.servlet.Filter#void (javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
	*/
	public void doFilter(
		ServletRequest req,
		ServletResponse resp,
		FilterChain chain)
		throws ServletException, IOException {

		//Shift_JISにエンコード
		req.setCharacterEncoding("Shift_JIS");
		chain.doFilter(req, resp);

	}

	/**
	* Method init.
	* @param config
	* @throws javax.servlet.ServletException
	*/
	public void init(FilterConfig config) throws ServletException {

	}

}
