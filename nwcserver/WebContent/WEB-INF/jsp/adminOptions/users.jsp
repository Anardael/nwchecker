<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<c:set var="baseURL" value="${pageContext.servletContext.contextPath}" />
<script type="text/javascript">
	ROLE_ADMIN = '<spring:message code="adminPanel.users.table.role.admin" />';
	ROLE_TEACHER = '<spring:message code="adminPanel.users.table.role.teacher" />';
	ROLE_USER = '<spring:message code="adminPanel.users.table.role.user" />';
	UNDEFINED = '<spring:message code="adminPanel.users.table.role.undefined" />';
</script>

<div class="row">
	<script>
		$(document)
				.ready(
						function() {
							$('#jTable')
									.jtable(
											{
												jqueryuiTheme : true,
												paging : true,
												pageSize : 10,
												sorting : true,
												actions : {
													listAction : '${baseURL}/getUsers.do'
												},
												ajaxSettings : {
													type : 'GET',
													dataType : 'json'
												},
												fields : {
													username : {
														title : '<spring:message code="adminPanel.users.tableHeader.username" />'
													},
													displayName : {
														title : '<spring:message code="adminPanel.users.tableHeader.displayName" />'
													},
													roles : {
														title : '<spring:message code="adminPanel.users.tableHeader.roles" />',
														display : function(data) {
															var response = '';
															var separator = ' ';
															for (index = 0; index < data.record.roles.length; index++) {
																if (data.record.roles[index].Role == 'ROLE_USER')
																	response = response
																			+ separator
																			+ ROLE_USER;
																else if (data.record.roles[index].Role == 'ROLE_TEACHER')
																	response = response
																			+ separator
																			+ ROLE_TEACHER;
																else if (data.record.roles[index].Role == 'ROLE_ADMIN')
																	response = response
																			+ separator
																			+ ROLE_ADMIN;
																else
																	response = response
																			+ separator
																			+ UNDEFINED;
																separator = ', ';
															}
															return response;
														}
													},
													email : {
														title : '<spring:message code="adminPanel.users.tableHeader.email" />'
													},
													department : {
														title : '<spring:message code="adminPanel.users.tableHeader.department" />'
													},
													info : {
														title : '<spring:message code="adminPanel.users.tableHeader.info" />'
													},
													edit : {
														width : '1%',
														sorting : false,
														list : true,
														display : function(data) {
															if (data.record) {
																var c = data.record.username;
																return '<form action="<c:url value="/userEdit.do"/>" method="GET"><input type="hidden" name="Username" value=' + c + ' /><button title="Edit" class="jtable-command-button jtable-edit-command-button"><span>Edit</span></button></form>';
															}
														}
													}
												}

											});
							$('#jTable').jtable('load');
							$('#filter').click(function(e) {
								e.preventDefault();
								$('#jTable').jtable('load', {
									jtFilter : $('#filterText').val()
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
</div>
</html>