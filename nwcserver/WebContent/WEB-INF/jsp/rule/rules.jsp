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
        <form:form modelAttribute="updateContent" action="donec/edit.do" method="post" role="form" class="rules-form">
            <table>
                <tr>
                    <td class="list-group-item list-group-item-heading list-group-item-info" colspan="2" align="center">
                        <b><spring:message code="rules.contests.tableHeader.title"/></b>
                    </td>
                </tr>
                <c:forEach items="${ruleList}" var="rule">
                    <tr>
                        <td class="list-group-item">
                            ${rule.type}
                        </td>
                        <td class="list-group-item">
                            <div class="rule-content-area" hidden="true"><form:textarea path="content"/>${rule.content}</div>
                            <%--<div class="rule-content-area" hidden="true"><textarea>${rule.content}</textarea></div>--%>
                            <div class="rule-content">${rule.content}</div>
                        </td>
                    </tr>
                </c:forEach>
            </table>
            <security:authorize access="hasRole('ROLE_TEACHER')">
                <div class="rule-submit-btn">
                    <button class="btn btn-rule" type="submit" >
                        <spring:message code="rules.submitButton.caption"/>
                    </button>
                </div>
            </security:authorize>
        </form:form>
    </div>
</div>
<jsp:include page="../fragments/footer.jsp"/>
</body>
</html>
