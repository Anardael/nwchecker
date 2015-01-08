<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- set path to resources folder -->
<spring:url value="/resources/" var="resources"/>
<html>
	<!--including head -->
    <jsp:include page="../fragments/staticFiles.jsp" />
	<body>
		<div class="wrapper container">
			<!--Including bodyHead -->
			<!-- Send name of current page-->
            <jsp:include page="../fragments/bodyHeader.jsp">
                <jsp:param name="pageName" value="home"/>
            </jsp:include>
            <!--Including admin optionsMenu -->
            <!-- Send name of selected option-->
            <jsp:include page="optionsMenu.jsp">
            	<jsp:param name="selectedOption" value="none"/>
            </jsp:include>
            <div align="left">
            	<table>
            		<tr>
            			<th><spring:message code="adminPanel.users.tableHeader.username"/></th>
            			<th><spring:message code="adminPanel.users.tableHeader.accessLevel"/></th>
            			<th><spring:message code="adminPanel.users.tableHeader.displayName"/></th>
            			<th><spring:message code="adminPanel.users.tableHeader.email"/></th>
            			<th><spring:message code="adminPanel.users.tableHeader.department"/></th>
            			<th><spring:message code="adminPanel.users.tableHeader.info"/></th>
            			<th><spring:message code="adminPanel.users.tableHeader.isBanned"/></th>
            			<th><spring:message code="adminPanel.users.tableHeader.confirmed"/></th>
            		</tr>
            		<c:forEach var="user" items="${users}">
            			<tr>
            				<td><c:out value="${user.username}"></c:out></td>
            				<td><c:out value="${user.accessLevel}"></c:out></td>
            				<td><c:out value="${user.displayName}"></c:out></td>
            				<td><c:out value="${user.email}"></c:out></td>
            				<td><c:out value="${user.department}"></c:out></td>
            				<td><c:out value="${user.info}"></c:out></td>
            				<c:choose>
            					<c:when test="${user.banTime > 0}">
            						<td class="warning">
            							<spring:message code="adminPanel.users.tableHeader.isBanned.yes"/>
            							<c:out value=" (${user.banTime})"></c:out>
            						</td>
            					</c:when>
            					<c:otherwise>
            						<td class="success">
            							<spring:message code="adminPanel.users.tableHeader.isBanned.no"/>
            						</td>
            					</c:otherwise>
            				</c:choose>
            				<c:choose>
            					<c:when test="${user.enabled}">
            						<td class="success">
            							<spring:message code="adminPanel.users.tableHeader.confirmed.yes"/>
            						</td>
            					</c:when>
            					<c:otherwise>
            						<td class="warning">
            							<spring:message code="adminPanel.users.tableHeader.confirmed.no"/>
            						</td>
            					</c:otherwise>
            				</c:choose>
            			</tr>
            		</c:forEach>
            	</table>
            </div>
		</div>
	</body>
</html>