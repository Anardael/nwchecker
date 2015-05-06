<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!-- set path to resources folder -->
<spring:url value="/resources/" var="resources" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%-- Importing head --%>
<head>
<jsp:include page="../fragments/staticFiles.jsp" />
<script type="text/javascript"
	src="${resources}/js/bootstrap/bootstrap-select.js"></script>
<script type="text/javascript"
	src="${resources}js/TaskStatistic/SortingParams.js"></script>

</head>
<body>
	<div class="wrapper container">
		<!--including bodyHead -->
		<!-- send name of current page-->
		<jsp:include page="../fragments/bodyHeader.jsp">
			<jsp:param name="pageName" value="contest" />
		</jsp:include>
		<div class="row">
			<%-- Side panel --%>
			<div id="tasks" class="col-md-3">
				<ul class="nav nav-pills nav-stacked">
					<c:url var="taskURL" value="/passTask.do?id=" scope="page" />
					<c:set var="count" value="0" scope="page" />

					<c:forEach var="taskInfo" items="${taskTitles}">
						<c:set var="count" value="${count + 1}" scope="page" />
						<c:set var="taskTitle" value="${taskInfo.value}" scope="page" />
						<c:choose>
							<c:when test="${taskId eq taskInfo.key}">
								<li class="active"><a href="${taskURL}${taskInfo.key}">
										<b>${count}. </b> <c:out value="${taskTitle}" />
								</a></li>
							</c:when>
							<c:otherwise>
								<li class="default"><a href="${taskURL}${taskInfo.key}">
										<b>${count}. </b> <c:out value="${taskTitle}" />
								</a></li>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</ul>
			</div>
			<section class="col-md-9"> <%-- Table of statistic --%>
			<div>
				<form method="POST" action="TaskStatistic.do">
					<jsp:useBean id="orderParams"
						class="com.nwchecker.server.utils.OrderParams" scope="session" />
					<input type="hidden" name="id" value="${taskId}">
					<p>
						Results per page: <select class="selectpicker" name="pageSize">
							<option>10</option>
							<option>25</option>
							<option>50</option>
							<option>100</option>
						</select>
					</p>
					<p>Order by:</p>
					<input type="submit" />

					<table class="table">
						<thead>
							<tr>
								<th>#Id</th>
								<th><select id="usernameSelect" class="selectpicker" name="username">
										<option value="">Username</option>
										<option value="asc" data-icon="glyphicon-chevron-up">Username</option>
										<option value="desc" data-icon="glyphicon-chevron-down">Username</option>
								</select></th>
								<th><select id="compilerSelect" class="selectpicker" name="compiler">
										<option value="">Compiler</option>
										<option value="asc" data-icon="glyphicon-chevron-up">Compiler</option>
										<option value="desc" data-icon="glyphicon-chevron-down">Compiler</option>
								</select></th>
								<th><select id="execTimeSelect" class="selectpicker" name="execTime">
										<option value="">Execution Time</option>
										<option value="asc" data-icon="glyphicon-chevron-up">Execution Time</option>
										<option value="desc" data-icon="glyphicon-chevron-down">Execution Time</option>
								</select></th>
								<th><select class="selectpicker" name="memoryUsed">
										<option value="">Memory used</option>
										<option value="asc" data-icon="glyphicon-chevron-up">Memory used</option>
										<option value="desc" data-icon="glyphicon-chevron-down">Memory used</option>
								</select></th>
								<th><select class="selectpicker" name="passed">
										<option value="">Passed</option>
										<option value="asc" data-icon="glyphicon-chevron-up">Passed</option>
										<option value="desc" data-icon="glyphicon-chevron-down">Passed</option>
								</select></th>
								<th>Attempts</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${taskPassList}" var="taskPass">
								<tr>
									<td>${taskPass.taskPassId}</td>
									<td>${taskPass.userName}</td>
									<td>${taskPass.compiler.name}</td>
									<td>${taskPass.executionTime}</td>
									<td>${taskPass.memoryUsed}</td>
									<td>${taskPass.passed}</td>
									<td>${taskPass.numberOfAttempts}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</form>
				<div class="text-center">
					<ul class="pagination">
						<c:forEach begin="0" end="5" var="loop">
							<c:if test="${currentPage + loop - 3>0}">
								<c:if test="${currentPage + loop - 3<lastPage + 1}">
									<li><a
										href="<c:url value="/TaskStatistic.do">
										<c:param name="id" value="${taskId}"/>
										<c:param name="page" value="${currentPage + loop - 3}"/>
										</c:url>">
											<c:out value="${currentPage + loop - 3}" />
									</a></li>
								</c:if>
							</c:if>
						</c:forEach>
					</ul>
				</div>
			</div>
			</section>
		</div>
	</div>
</body>
</html>
