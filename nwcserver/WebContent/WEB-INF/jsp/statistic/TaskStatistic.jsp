<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- set path to resources folder -->
<spring:url value="/resources/" var="resources" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%-- Importing head --%>
<head>
<jsp:include page="../fragments/staticFiles.jsp" />
</head>
<body>
	<div class="wrapper container">
		<!--including bodyHead -->
		<!-- send name of current page-->
		<jsp:include page="../fragments/bodyHeader.jsp">
			<jsp:param name="pageName" value="home" />
		</jsp:include>
		<%-- Side panel --%>
		<div class="row">
			<aside class="col-md-3">
			<ul class="list-group submenu">
				<li class="list-group-item active"><spring:message code="home.info.caption" /></li>
				<li class="list-group-item"><a href="donec.do"><spring:message code="home.rules.caption" /></a></li>
				<li class="list-group-item"><a href="vestibulum.do"><spring:message code="home.contacts.caption" /></a></li>
				<li class="list-group-item"><a href="etiam.do"><spring:message code="home.archive.caption" /></a></li>
				<li class="list-group-item"><a href="phasellus.do"><spring:message code="home.forum.caption" /></a></li>
			</ul>
			</aside>
			<section class="col-md-9"> <%-- Table of statistic --%>
			<div>
				<table class="table">
					<thead>
						<tr>
							<th>Execution time</th>
							<th>Username</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${taskPassList}" var="taskPass">
							<tr>
								<td>${taskPass.executionTime}</td>
								<td>${taskPass.user.displayName}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			</section>
		</div>
	</div>
</body>
</html>