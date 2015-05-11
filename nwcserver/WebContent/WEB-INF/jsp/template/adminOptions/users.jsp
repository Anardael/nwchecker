<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<script type="text/javascript">
	ROLE_ADMIN = '<spring:message code="adminPanel.users.table.role.admin" />';
	ROLE_TEACHER = '<spring:message code="adminPanel.users.table.role.teacher" />';
	ROLE_USER = '<spring:message code="adminPanel.users.table.role.user" />';
	UNDEFINED = '<spring:message code="adminPanel.users.table.role.undefined" />';
</script>
<div class="row">
	<!-- TABLE -->
	<c:url var="dataUrl" value="/getUsers.do" />
	<table id="usersData" data-toggle="table" data-striped="true"
		data-url="${dataUrl}" data-method="get" data-cache="false"
		data-sort-name="username" data-sort-order="asc" data-pagination="true"
		data-show-pagination-switch="true" data-search="true"
		data-clear-search="true" data-show-columns="true"
		data-minimum-count-columns="2">
		<thead>
			<tr>
				<th data-field="username" data-align="center"
					data-formatter="usernameFormatter" data-sortable="true"><spring:message
						code="adminPanel.users.tableHeader.username" /></th>
				<th data-field="displayName" data-align="center"
					data-sortable="true"><spring:message
						code="adminPanel.users.tableHeader.displayName" /></th>
				<th data-field="roles" data-align="center"
					data-formatter="rolesFormatter" data-sortable="true"
					data-sorter="usersSorter"><spring:message
						code="adminPanel.users.tableHeader.roles" /></th>
				<th data-field="email" data-align="center" data-sortable="true">
					<spring:message code="adminPanel.users.tableHeader.email" />
				</th>
				<th data-field="department" data-halign="center"
					data-formatter="infoFormatter" data-sortable="true"><spring:message
						code="adminPanel.users.tableHeader.department" /></th>
				<th data-field="info" data-halign="center"
					data-formatter="infoFormatter" data-sortable="true"><spring:message
						code="adminPanel.users.tableHeader.info" /></th>
			</tr>
		</thead>
	</table>
</div>
</html>