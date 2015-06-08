<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<spring:url value="/resources/" var="resources" />

<html>
<div class="text-center">
    <h1>
        Access denied
    </h1>
    <h3>
        Contest is Edited
    </h3>
    <button class="btn btn-primary back-btn" onclick="goToContests()">
        Back
    </button>
</div>
</html>
