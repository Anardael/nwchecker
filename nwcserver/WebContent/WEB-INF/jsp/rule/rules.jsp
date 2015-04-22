<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- set path to resources foled -->
<spring:url value="/resources/" var="resources" />

<html>
<head>
    <jsp:include page="../fragments/staticFiles.jsp" />
</head>
<body>
<div class="wrapper container">
    <!--including bodyHead -->
    <!-- send name of current page-->
    <jsp:include page="../fragments/bodyHeader.jsp">
        <jsp:param name="pageName" value="rules" />
    </jsp:include>

    <div class="rule-place" align="center">
            <table cellspacing="0">
                <tr>
                    <th>Rules</th><th>Content</th>
                </tr>
                <c:forEach items="${ruleList}" var="rule">
                    <tr>
                        <td>${rule.type}</td><td>${rule.content}</td>
                    </tr>
                </c:forEach>
            </table>
    </div>

</div>
<jsp:include page="../fragments/staticFiles.jsp"/>
</body>
</html>
