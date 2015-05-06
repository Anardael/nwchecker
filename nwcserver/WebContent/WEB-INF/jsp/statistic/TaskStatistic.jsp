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
<link rel="stylesheet" href="${resources}css/contests/contestPass.css" />
<jsp:include page="../fragments/staticFiles.jsp" />
<script type="text/javascript" src="${resources}js/TaskStatistic/SortingParams.js"></script>
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
					<jsp:useBean id="orderParams" class="com.nwchecker.server.utils.OrderParams" scope="session"/>
					<input type="hidden" name ="id" value="${taskId}">
					<p>
						Results per page: 
						<select class="selectpicker" name="pageSize">
							<option>10</option>
							<option>25</option>
							<option>50</option>
							<option>100</option>
						</select>
					</p>
					<p>Order by:					
					<ul>					
						<li><input id = "compiler" type="checkbox" name = "compiler"/>Compiler
						<select	class="selectpicker" name = "compilerType">
								<option value = "asc">Ascending</option>
								<option value = "desc">Descending</option>
						</select></li>
						<li><input id = "username" type="checkbox" name = "username"/>Username
						<select	class="selectpicker" name = "usernameType">
								<option value = "asc">Ascending</option>
								<option value = "desc">Descending</option>
						</select></li>
						<li><input id="execTime" type="checkbox" name="execTime"/>Execution time
						<select class="selectpicker" name="execTimeType">
								<option value = "asc">Ascending</option>
								<option value = "desc">Descending</option>
						</select></li>
						<li><input id = "memoryUsed" type="checkbox" name="memoryUsed"/>Memory Used
						<select class="selectpicker" name="memoryUsedType">
								<option value = "asc">Ascending</option>
								<option value = "desc">Descending</option>
						</select></li>
						<li><input id = "passed" type="checkbox" name="passed"/>Passed
						<select class="selectpicker" name="passedType">
								<option value = "asc">Ascending</option>
								<option value = "desc">Descending</option>
						</select></li>
					</ul>
					</p> 
					<input type="submit"/>
				</form>
				<table class="table">
					<thead>
						<tr>
							<th>#Id</th>
							<th>Username</th>
							<th>Compiler</th>
							<th>Execution time</th>
							<th>Memory Used</th>
							<th>Passed</th>
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
				<div class="text-center">
					<ul class="pagination">				
						<c:forEach begin="0" end="5" var="loop">
							<c:if test="${currentPage + loop - 3>0}">
								<c:if test="${currentPage + loop - 3<lastPage + 1}">
								<li>
									<a href="<c:url value="/TaskStatistic.do">
										<c:param name="id" value="${taskId}"/>
										<c:param name="page" value="${currentPage + loop - 3}"/>
										</c:url>">
									 	<c:out value="${currentPage + loop - 3}" />									 	
									 </a>
								</li>
								</c:if>
							</c:if>
						</c:forEach>
					</ul>
				</div>
			</div>
			</form>
			</section>
		</div>
	</div>
</body>
</html>
