<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<spring:url value="/resources/" var="resources" />

<html>
    <div class="text-center" style="margin-top: 10%">
        <h1>
            <spring:message code="pageNotFound.page.caption" />
        </h1>
        <button class="btn btn-primary back-btn" onclick="goBack()">
            <spring:message code="pageNotFound.backButton.caption" />
        </button>
    </div>
</html>