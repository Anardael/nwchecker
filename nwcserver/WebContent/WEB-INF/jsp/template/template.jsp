<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<jsp:include page="../fragments/staticFiles.jsp" />
	<link rel="stylesheet" href="${resources}css/bootstrap-dialog.css"/>
    <link rel="stylesheet" href="${resources}css/contests/contestPass.css"/>
    <link rel="stylesheet" href="${resources}js/laddaBtnLoad/ladda-themeless.min.css"/>

    <script type="text/javascript" src="${resources}js/bootstrap/bootstrap-select.js"></script>
    <script type="text/javascript" src="${resources}js/bootstrap/bootstrap-dialog.js"></script>
    <script type="text/javascript" src="${resources}js/contests/contestPass.js"></script>
    <script type="text/javascript" src="${resources}js/contests/contestPassTimer.js"></script>
    <script type="text/javascript" src="${resources}js/laddaBtnLoad/spin.min.js"></script>
    <script type="text/javascript" src="${resources}js/laddaBtnLoad/ladda.min.js"></script>
    <script type="text/javascript" src="${resources}js/contests/tasks/taskSubmit.js"></script>
</head>
<body>
	<div class="wrapper-container">
		<tiles:insertAttribute value="header" />
		<div id="tasks" class="col-md-3">
			<tiles:insertAttribute value="menu" />
		</div>
		<div class="col-md-9"> 
			<tiles:insertAttribute value="body"/>
		</div>		
	</div>
</body>
</html>