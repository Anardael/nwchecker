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
    <link href="${resources}css/rules/rules-style.css" rel="stylesheet"/>
</head>
<body>
<div class="wrapper container">
    <jsp:include page="../fragments/bodyHeader.jsp">
        <jsp:param name="pageName" value="rules" />
    </jsp:include>
    <div class="col-sm-offset-2 col-sm-8 ">
        <form:form modelAttribute="ruleWrapper" action="editRules.do" method="post" id="rules-form">
            <table align="center">
                <tr>
                    <td class="list-group-item list-group-item-heading list-group-item-info" colspan="2" align="center">
                        <b><spring:message code="rules.contests.tableHeader.title"/></b>
                    </td>
                </tr>
                <c:forEach items="${ruleWrapper.ruleList}" var="rule" varStatus="vs">
                    <tr>
                        <td class="list-group-item">
                            ${rule.type}
                        </td>
                        <td class="list-group-item">
                            <c:forEach var="item" items="${userData.roles}">
                                <c:choose>
                                    <c:when test="${item.role eq 'ROLE_TEACHER'}">
                                        <div class="rule-content">
                                            <form:hidden path="ruleList[${vs.index}].id" class="list-group-area"/>
                                            <form:hidden path="ruleList[${vs.index}].type" class="list-group-area"/>
                                            <form:textarea path="ruleList[${vs.index}].content" class="list-group-area"/>
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
                </c:forEach>
            </table>
            <security:authorize access="hasRole('ROLE_TEACHER')">
                <div class="rule-submit-btn" align="center" hidden="true">
                    <button class="btn btn-rule" type="submit" >
                        <spring:message code="rules.editButton.caption"/>
                    </button>
                </div>
            </security:authorize>
        </form:form>
    </div>
</div>
<jsp:include page="../fragments/footer.jsp"/>

<script type="text/javascript" src="${resources}js/rules/rulesEdit.js"></script>
</body>
</html>
