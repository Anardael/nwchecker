<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<c:set var="taskId" value="${currentTask.id}" />
<c:set var="baseURL" value="${pageContext.servletContext.contextPath}" />
<body>
	<script>
		$(document).ready(function() {
			$('#jTable').jtable({
				paging : true,
				pageSize : 10,
				sorting : true,
				actions : {
					listAction : '${baseURL}/TaskStatisticTable.do'
				},
				ajaxSettings : {
					type : 'GET',
					dataType : 'json'
				},
				fields : {
					taskPassId : {
						key : true,
						list : true,
						create : false,
						edit : false,
						width: '10%'
					},
					username : {
						title : 'Username'
					},
					compiler : {
						title : 'Compiler'
					},
					executionTime : {
						title : 'Execution Time'
					},
					memoryUsed : {
						title : 'Memory Used'
					},
					numberOfAttempts : {
						title : 'Number of Attemps',
						sorting : false
					},
					passed : {
						title : 'Passed'
					}
				}

			});
			$('#jTable').jtable('load', {
				taskId : '44'
			});
		})
	</script>
	<div id="jTable"></div>
	<div></div>
	<%-- 
	<form method="POST" action="TaskStatistic.do">

		<jsp:useBean id="orderParams"
			class="com.nwchecker.server.utils.OrderParams" scope="session" />
		<input type="hidden" name="id" value="${taskId}"> <input
			type="hidden" name="username_selectpicker" value="${taskId}">
		<input type="hidden" name="compiler_selectpicker" value="${taskId}">
		<input type="hidden" name="execTime_selectpicker" value="${taskId}">
		<input type="hidden" name="memUsed_selectpicker" value="${taskId}">
		<input type="hidden" name="passed_selectpicker" value="${taskId}">
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
					<th><select id="usernameSelect" class="selectpicker"
						name="username">
							<option value="">Username</option>
							<option value="asc" data-icon="glyphicon-chevron-up">Username</option>
							<option value="desc" data-icon="glyphicon-chevron-down">Username</option>
					</select></th>
					<th><select id="compilerSelect" class="selectpicker"
						name="compiler">
							<option value="">Compiler</option>
							<option value="asc" data-icon="glyphicon-chevron-up">Compiler</option>
							<option value="desc" data-icon="glyphicon-chevron-down">Compiler</option>
					</select></th>
					<th><select id="execTimeSelect" class="selectpicker"
						name="execTime">
							<option value="">Execution Time</option>
							<option value="asc" data-icon="glyphicon-chevron-up">Execution
								Time</option>
							<option value="desc" data-icon="glyphicon-chevron-down">Execution
								Time</option>
					</select></th>
					<th><select class="selectpicker" name="memoryUsed">
							<option value="">Memory used</option>
							<option value="asc" data-icon="glyphicon-chevron-up">Memory
								used</option>
							<option value="desc" data-icon="glyphicon-chevron-down">Memory
								used</option>
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
	</div> --%>
</body>
</html>