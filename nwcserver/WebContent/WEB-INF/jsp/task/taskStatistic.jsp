<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<c:set var="taskId" value="${currentTask.id}" />
<c:set var="baseURL" value="${pageContext.servletContext.contextPath}" />
<body>
	<%-- <script>
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
					user : {
						title   : '<spring:message code="task.statistic.usernameCaption"/>',
						display : function(data){
							return data.record.user.displayName;
						}
					},
					compiler : {
						title : '<spring:message code="task.statistic.compilerCaption"/>',
						display:function(data){
							return data.record.compiler.compiler;
						}
					},
					executionTime : {
						title : '<spring:message code="task.statistic.execTimeCaption"/>'
					},
					memoryUsed : {
						title : '<spring:message code="task.statistic.memUsedCaption"/>'
					},
					passed : {
						title : '<spring:message code="task.statistic.passedCaption"/>',
						display:function(data){
							if (data.record.passed){
								return 'Passed'
							}
							else return 'Failed'
						}
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
	<div id="jTable"></div> --%>
	
	
</body>
</html>