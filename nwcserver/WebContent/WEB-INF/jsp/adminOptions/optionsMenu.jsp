<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- set path to resources folder -->
<spring:url value="/resources/" var="resources"/>
<div align="left">
	<aside class="col-md-3">
    	<ul class="list-group submenu">
        	<c:choose>
				<c:when test="${param.selectedOption == 'users'}">
					<li class="active"><a><spring:message code="adminPanel.menu.users" /></a></li>
				</c:when>
				<c:otherwise>
					<li><a href="admin.do?option=users"><spring:message code="adminPanel.menu.users" /></a></li>
				</c:otherwise>
			</c:choose>
			<c:choose>
				<c:when test="${param.selectedOption == 'default'}">
					<li class="active"><a>Some option</a></li>
				</c:when>
				<c:otherwise>
					<li><a href="admin.do?option=default">Some option</a></li>
				</c:otherwise>
			</c:choose>
        </ul>
    </aside>
</div>