<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0"> 
    <title><spring:message code="application.title" /></title>

    <spring:url value="/resources/" var="resources"/>
    <link href="${resources}css/reg.css" rel="stylesheet"/>
    <link href="${resources}css/bootstrap.css" rel="stylesheet"/>
    <link href="${resources}css/styles.css" rel="stylesheet"/>
    <link href="${resources}css/task.css" rel="stylesheet"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <script type="text/javascript" src="${resources}js/bootstrap.min.js"></script>
    <script type="text/javascript" src="${resources}js/taskCreateAddLink.js"></script>
    <script type="text/javascript" src="${resources}js/bootstrap.file-input.js"></script>
</head>
