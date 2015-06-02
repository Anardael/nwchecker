<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>
<%@ taglib prefix="contest" uri="/tlds/ContestTags" %> 

<spring:url value="/resources/" var="resources"/>

<html>
    <h3 class="denied-msg">
        <spring:message code="contest.accessDeniedHeader"/>
    </h3>
    <h4 class="denied-msg">
        <spring:message code="contest.accessDenied" />.
    </h4>
</html>
