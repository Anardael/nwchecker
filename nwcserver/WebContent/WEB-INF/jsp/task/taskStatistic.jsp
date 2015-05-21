<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<c:set var="taskId" value="${currentTask.id}" />
<c:set var="baseURL" value="${pageContext.servletContext.contextPath}" />
<body>
	<script>
		$(document).ready(function() {
			$('#jTable').jtable({
				jqueryuiTheme : true,
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
					Username : {
						title : '<spring:message code="task.statistic.usernameCaption"/>'
					},
					Compiler : {
						title : '<spring:message code="task.statistic.compilerCaption"/>'
					},
					ExecutionTime : {
						title : '<spring:message code="task.statistic.execTimeCaption"/>'
					},
					MemoryUsed : {
						title : '<spring:message code="task.statistic.memUsedCaption"/>'
					},
					Passed : {
						title : '<spring:message code="task.statistic.passedCaption"/>'
					}
				}

			});
			$('#jTable').jtable('load', {
				taskId : '${taskId}'
			});
			$('#filter').click(function(e) {
				e.preventDefault();
				$('#jTable').jtable('load', {
					jtFilter : $('#filterText').val(),
					taskId : '${taskId}'
				});
			})
		})
	</script>
	<form>
		<div class="navbar-form">
			<input type="text" id="filterText" class="form-control"
				placeholder="<spring:message code="pagination.search.field" />">
			<button type="submit" id="filter" class="btn btn-default"><spring:message code="pagination.search.button" /></button>
		</div>
	</form>
	<div id="jTable"></div>
</body>
</html>