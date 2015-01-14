<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- set path to resources folder -->
<spring:url value="/resources/" var="resources"/>
<aside class="col-md-3">
	<ul class="list-group submenu">
    	<c:choose>
			<c:when test="${param.selectedOption == 'users'}">
				<li class="list-group-item active">
					<a href="admin.do?option=users">
						<spring:message code="adminPanel.menu.users" />
					</a>
				</li>
			</c:when>
			<c:otherwise>
				<li class="list-group-item">
					<a href="admin.do?option=users">
						<spring:message code="adminPanel.menu.users" />
					</a>
				</li>
			</c:otherwise>
		</c:choose>
		<c:choose>
			<c:when test="${param.selectedOption == 'user edit'}">
				<li class="list-group-item active">
					<a href="admin.do?option=userEdit">
						<spring:message code="adminPanel.menu.userEdit" />
					</a>
				</li>
			</c:when>
			<c:otherwise>
				<li class="list-group-item">
					<a href="admin.do?option=userEdit">
						<spring:message code="adminPanel.menu.userEdit" />
					</a>
				</li>
			</c:otherwise>
		</c:choose>
	</ul>
</aside>