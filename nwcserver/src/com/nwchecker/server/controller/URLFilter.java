package com.nwchecker.server.controller;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

/**
 * Servlet Filter implementation class URLFilter
 */
@WebFilter("/URLFilter")
public class URLFilter implements Filter {

	/**
	 * Default constructor.
	 */
	public URLFilter() {
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
			ServletException {
		if (!(request instanceof HttpServletRequest)) {
			chain.doFilter(request, response);
			return;
		}

		HttpServletResponse httpResponse = (HttpServletResponse) response;

		HttpServletResponseWrapper wrappedResponse = new HttpServletResponseWrapper(httpResponse) {
			public String encodeRedirectUrl(String url) {
				return url;
			}

			public String encodeRedirectURL(String url) {
				return url;
			}

			public String encodeUrl(String url) {
				return url;
			}

			public String encodeURL(String url) {
				return url;
			}
		};
		chain.doFilter(request, wrappedResponse);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
	}
}
