package com.nwchecker.server.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorController {
	private static final Logger LOG = Logger.getLogger(ErrorController.class);
	@RequestMapping("/error")
	public String handleError(HttpServletRequest request){
		LOG.warn("Error at "+ request.getAttribute("javax.servlet.error.request_uri") + " exception caused: " + request.getAttribute("javax.servlet.error.status_code") + " error code: " + request.getAttribute("javax.servlet.error.exception"));
		return "nwcserver.error";
	}
}
