<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; UTF-8">

<title>Task creation</title>

<!-- Bootstrap core CSS -->
<link href="<c:url value="/css/bootstrap.css" />" rel="stylesheet">
<!-- WYSIWYG import -->
<script type="text/javascript" src=<c:url value="/js/tinymce/tinymce.min.js" />></script>
<!-- WYSIWYG initialization -->
<script type="text/javascript" src=<c:url value="/js/tinymce.initialize.js" />></script>
</head>
<body>
	<!-- TODO Add header -->
	
	<form action=<c:url value="/CreateTask" /> method="post">
		<!-- TODO Main task settings -->
		<textarea id="taskDescription" name="taskDescription">Type your code here...</textarea>
		<!-- TODO Another task settings -->
		<button type="submit">Create task</button>
	</form>
	
	<!-- TODO Add footer -->
</body>
</html>