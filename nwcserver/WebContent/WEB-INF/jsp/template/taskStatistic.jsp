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
				jqueryuiTheme : true,
				paging : true,
				pageSize : 5,
				sorting : true,
				actions : {
					listAction : '${baseURL}/TaskStatisticTable.do'
				},
				ajaxSettings : {
					type : 'GET',
					dataType : 'json'
				},
				fields : {
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
</body>
</html>