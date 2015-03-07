package com.nwchecker.server.filters;

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
import java.io.IOException;

/**
 * <h1>URL Filter</h1>
 * Implementation of class Servlet Filter.
 * Used to prevent bug in first visit of home page
 * and to handle absence of "UTF-8" encoding parameter
 * in some request headers.
 * <p>
 *
 * @author Serhii Dovhaniuk
 * @author Stanislav Krasovskyi
 * @version 1.0
 */
@WebFilter("/URLFilter")
public class URLFilter implements Filter {

	private static final String CONTENT_TYPE="application/x-www-form-urlencoded";
    private static final String ENCODING_DEFAULT = "UTF-8";
    private static final String ENCODING_INIT_PARAM_NAME = "encoding";
	
    private String encoding;
    
	/**
	 * Default constructor.
	 */
	public URLFilter() {
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		encoding = fConfig.getInitParameter(ENCODING_INIT_PARAM_NAME);
        if (encoding == null)
            encoding = ENCODING_DEFAULT;
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
		
		String contentType = request.getContentType();
        if (contentType != null && contentType.startsWith(CONTENT_TYPE))
            request.setCharacterEncoding(encoding);
        
		chain.doFilter(request, wrappedResponse);
	}
}
