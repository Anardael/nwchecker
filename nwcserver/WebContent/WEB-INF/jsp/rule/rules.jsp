<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!-- set path to resources foled -->
<spring:url value="/resources/" var="resources" />

<html>
<head>
    <jsp:include page="../fragments/staticFiles.jsp" />
    <script type="text/javascript" src="${resources}js/rules/rulesEditBtn.js"></script>
</head>
<body>
<div class="wrapper container">
    <!--including bodyHead -->
    <!-- send name of current page-->
    <jsp:include page="../fragments/bodyHeader.jsp">
        <jsp:param name="pageName" value="rules" />
    </jsp:include>
    <div class="rule-place" align="center">
        <table>
            <tr>
                <td class="list-group-item list-group-item-heading list-group-item-info" colspan="2" align="center">
                    <b><spring:message code="rules.contests.tableHeader.title"/></b>
                </td>
            </tr>
            <c:forEach items="${ruleList}" var="rule" varStatus="vs">
                <form:form modelAttribute="ruleList" action="donec/edit.do" method="post" class="rules-form">
                    <tr>
                        <td class="list-group-item">
                            ${rule.type}
                        </td>
                        <td class="list-group-item">
                            <c:forEach var="item" items="${userData.roles}">
                                <c:choose>
                                    <c:when test="${item.role eq 'ROLE_TEACHER'}">
                                        <div class="rule-content-area">
                                            <form:textarea path="ruleList[${vs.index}].content"/>
                                        </div>
                                    </c:when>
                                    <c:otherwise>
                                        <div class="rule-content">${rule.content}</div>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                            <c:if test="${userData eq null}">
                                <div class="rule-content">${rule.content}</div>
                            </c:if>
                        </td>
                    </tr>
                </form:form>
            </c:forEach>
        </table>
        <security:authorize access="hasRole('ROLE_TEACHER')">
            <div class="rule-submit-btn">
                <button class="btn btn-rule" type="submit" >
                    <spring:message code="rules.submitButton.caption"/>
                </button>
            </div>
        </security:authorize>

        <%--<form:form modelAttribute="testRule" action="donec/edit.do" method="post">
            <c:forEach var="item" items="${userData.roles}">
                <c:if test="${item.role eq 'ROLE_TEACHER'}">
                    <div class="rule-content-area">
                            <form:textarea path="content"/>
                    </div>
                </c:if>
            </c:forEach>
            <div class="rule-submit-btn">
                <button class="btn btn-rule" type="submit" >
                    <spring:message code="rules.submitButton.caption"/>
                </button>
            </div>
        </form:form>--%>

    </div>
</div>
<jsp:include page="../fragments/footer.jsp"/>
</body>
</html>
