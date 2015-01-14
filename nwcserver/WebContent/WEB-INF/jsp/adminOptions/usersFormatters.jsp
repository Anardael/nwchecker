<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<script type="text/javascript">
	function banTimeFormatter(value) {
		if (value > 0) {
    		return '<h3><label class="label label-danger"><spring:message code="adminPanel.users.tableHeader.isBanned.yes"/></label></h3>';
    	}
    	return '<h3><label class="label label-success"><spring:message code="adminPanel.users.tableHeader.isBanned.no"/></label></h3>';
	}
	function enabledFormatter(value) {
		if (value) {
    		return '<h3><label class="label label-success"><spring:message code="adminPanel.users.tableHeader.confirmed.yes"/></label></h3>';
    	}
    	return '<h3><label class="label label-danger"><spring:message code="adminPanel.users.tableHeader.confirmed.no"/></label></h3>';
	}
</script>