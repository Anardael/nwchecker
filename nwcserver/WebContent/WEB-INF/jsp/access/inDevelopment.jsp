<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<spring:url value="/resources/" var="resources" />

<html>
    <div class="text-center">
        <h1>
            <spring:message code="inDevelopment.page.caption" />
        </h1>
        <button class="btn btn-primary back-btn" onclick="goBack()">
            <spring:message code="inDevelopment.buttonBack.caption" />
        </button>
    </div>
</html>