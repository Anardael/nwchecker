<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<spring:url value="/resources/" var="resources" />

<html>
<form:form modelAttribute="ruleWrapper" action="editRules.do"
	method="post" id="rules-form">
	<table align="center">
		<c:forEach items="${ruleWrapper.ruleList}" var="rule" varStatus="vs">
			<tr>
				<td class="list-group-item">
					<p>${rule.typeContest.name}</p>
				</td>
				<td class="list-group-item">
                    <c:set var="flag" value="false"/>
                    <%--if teacher role--%>
                    <security:authorize access="hasRole('ROLE_TEACHER')">
                        <c:set var="flag" value="true"/>
                        <div class="rule-content">
                            <p data-toggle="modal" data-target="#myModal_${vs.index}">${rule.content}</p>
                            <%--modal windows--%>
                            <div class="modal" id="myModal_${vs.index}">
                                <div class="modal-dialog">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <button type="button" class="close" data-dismiss="modal"
                                                    aria-hidden="true">&times;</button>
                                            <h2 class="modal-title" align="center">${rule.typeContest.name}</h2>
                                        </div>
                                        <div class="modal-body">
                                            <form:hidden path="ruleList[${vs.index}].id"
                                                         class="list-group-area" />
                                            <form:hidden path="ruleList[${vs.index}].typeContest.id"
                                                         class="list-group-area" />
                                            <form:hidden path="ruleList[${vs.index}].language.id"
                                                         class="list-group-area" />
                                            <p>
                                                <form:textarea path="ruleList[${vs.index}].content"
                                                               class="list-group-area" />
                                            </p>
                                        </div>
                                        <div class="modal-footer">
                                            <button type="submit" class="btn btn-primary">
                                                <spring:message code="rules.editButton.caption" />
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <%--end modal windows--%>
                        </div>
                    </security:authorize>
                    <%-- if other roles or not authorized--%>
                    <c:if test="${flag == false}">
                        <div class="rule-content">
                            <p>${rule.content}</p>
                        </div>
                    </c:if>
                </td>
			</tr>
		</c:forEach>
	</table>
</form:form>
</body>
</html>
