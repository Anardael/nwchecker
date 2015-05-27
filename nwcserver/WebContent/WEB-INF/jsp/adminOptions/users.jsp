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

	function operateFormatter(value, row, index) {
		return [
				'<a class="edit ml10" href="javascript:void(0)" title="Edit">',
				'<i class="glyphicon glyphicon-edit"></i>', '</a>' ].join('');
	}
	window.operateEvents = {
	        'click .edit': function (e, value, row, index) {
	        	location.href = 'userEdit.do?username=' + row['username'];
	        }
	    };
</script>

<div class="row">
	<!-- TABLE -->
	<c:url var="dataURL" value="/getUsers.do" />
	<table id="usersData" class="table" data-toggle="table"
		data-striped="true" data-url="${dataURL}"
		data-side-pagination="server" data-pagination="true"
		data-page-list="[5, 10, 20, 50, 100, 200]" data-search="true"
		data-clear-search="true" data-sort-name="username"
		data-sort-order="asc">
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
				<th data-field="operate" data-formatter="operateFormatter"
					data-events="operateEvents"></th>
			</tr>
		</thead>
	</table>
</div>
</html>