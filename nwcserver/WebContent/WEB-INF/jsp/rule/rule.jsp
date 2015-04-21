<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- set path to resources foled -->
<spring:url value="/resources/" var="resources" />

<html>
<head>
    <title></title>
</head>
<body>
<table cellspacing="0">
    <tr>
        <th>Rule</th><th>Content</th>
    </tr>
    <c:forEach items="${ruleList}" var="rule">
        <tr>
            <td>${rule.type}</td><td>${rule.content}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
