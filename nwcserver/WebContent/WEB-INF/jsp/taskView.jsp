<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.nwchecker.server.dao.TaskDAO"%>
<%@page import="com.nwchecker.server.model.Task"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; UTF-8">

<title>Task view</title>

<!-- Bootstrap core CSS -->
<link href="<c:url value="/css/bootstrap.css" />" rel="stylesheet">
</head>
<body>
	<!-- TODO Add header -->
	
	<div id="taskDescription">
		<% 
			int taskID = Integer.parseInt(request.getParameter("TaskID"));
			Task task = TaskDAO.getTaskById(taskID);
			out.write(task.getDescription());
		%>
	</div>
	
	<!-- TODO Add footer -->
</body>
</html>