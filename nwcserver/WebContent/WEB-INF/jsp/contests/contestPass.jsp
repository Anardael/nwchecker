<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- set path to resources folder -->
<spring:url value="/resources/" var="resources"/>
<html>
	<!--including head -->
    <head>
        <jsp:include page="../fragments/staticFiles.jsp" />  
        <link rel="stylesheet" href="${resources}css/bootstrap-select.min.css"/>
        <script type="text/javascript" src="${resources}js/bootstrap/bootstrap-select.js"></script>
          
    </head>
	<body>
		<div class="wrapper container">
			<!--including bodyHead -->
            <!-- send name of current page-->
            <jsp:include page="../fragments/bodyHeader.jsp">
                <jsp:param name="pageName" value="contest"/>
            </jsp:include>
            <div class="col-xs-12">
            	<h1>${contest.title}</h1>
            </div>
            <div class="row">
            	<div class="col-xs-3">
            		<ul class="nav nav-pills nav-stacked">  
            			<c:url var="linkURL" value="/passContest.do?contestId=${contest.id}&selectedTask="/>
            			<c:set var="count" value="0" scope="page"/>
            			<c:forEach var="task" items="${contest.tasks}">
            				<c:set var="count" value="${count + 1}" scope="page"/>
            				<c:choose>
            					<c:when test="${selectedTask == count}">
            						<li class="active">
            							<a href="${linkURL}${count}">
            								${count}. ${task.title}
            							</a>
            						</li>
            					</c:when>
            					<c:otherwise>
            						<li>
            							<a href="${linkURL}${count}">
            								${count}. ${task.title}
            							</a>
            						</li>
            					</c:otherwise>
            				</c:choose>
            			</c:forEach>
            		</ul>
            	</div>
            	<div class="col-xs-9">
            		<c:set var="task" value="${contest.tasks[selectedTask-1]}"/>
            		<div class="col-sm-12" style="background-color:#29C5E6;color:#FFFFFF;margin-bottom:10px;">
            			<h3 style="margin:3px 0px 3px 0px;">${task.title}</h3>
            		</div>
            		<div class="col-sm-12" style="background-color:#F9F0C5;margin-bottom:10px;">
            			<p>${task.description}</p>
            		</div>
            		<form:form modelAttribute="" method="post" enctype="multipart/form-data" 
            				   role="form" class="form-horizontal">
            			<div class="row">
            				<div class="col-sm-offset-4 col-sm-4">
            					<select class="selectpicker" data-style="btn-primary">
            						<option>Compiler1</option>
            						<option>Compiler2</option>
            						<option>Compiler3</option>
            					</select>
            				</div>
            			</div>
            			<div class="row">
            				<div class="col-sm-offset-4 col-sm-4">
            					<input type="file" id="sourceFile" name="sourceFile" 
            						   data-input="false" data-icon="false"
            						   data-buttonname="btn-primary"
            						   data-buttontext="Upload code">
            				</div>
            			</div>
            			<div class="row">
            				<div class="col-sm-offset-5 col-sm-2">
            					<button class="btn btn-primary btn-block">
            						Send code
            					</button>
            				</div>
            			</div>
            		</form:form>
            		
            		<!-- TODO appload, ect. -->
            	</div>
			</div>
		</div>
		<jsp:include page="../fragments/footer.jsp"/>
	</body>
</html>