<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<spring:url value="/resources/" var="resources" />

<html>
<div class="text-center">
    <h1>
        <spring:message	code="contest.access.denied.message" />
    </h1>
    <h3>
        <spring:message	code="contest.access.denied.page" />
    </h3>
    <button class="btn btn-primary back-btn" onclick="goToContests()">
        <spring:message	code="contest.access.denied.backPage" />
    </button>
</div>
</html>
