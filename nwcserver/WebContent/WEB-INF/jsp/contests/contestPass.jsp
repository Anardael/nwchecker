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
        
        <style type="text/css">
        	.nav-pills > li.active > a, .nav-pills > li.active > a:hover {
    			background-color: #5BC0DE;
			}
			.title-container {
				background-color:#5BC0DE;
				color: #FFFFFF;
				margin-bottom: 10px;
				height: auto;
			}
			.contest-title {
				margin: 0px 3px 0px 3px;
			}
			.task-title {
				margin: 0px 3px 0px 3px;
			}
			.descr-container {
				margin-bottom: 30px;
			}
			.descr-not-yet {
				background-color: #F9F0C5;
			}
			.descr-success {
				background-color: #DEF0D8;
			}
			.descr-wrong {
				background-color: #F2DEDF;
			}
			.btn-file {
    			position: relative;
    			overflow: hidden;
    			background-color: #5BC0DE;
    			color: #FFFFFF;
			}
			.btn-file input[type=file] {
    			position: absolute;
    			top: 0;
    			right: 0;
    			min-width: 100%;
    			min-height: 100%;
    			font-size: 100px;
    			text-align: right;
    			filter: alpha(opacity=0);
    			opacity: 0;
    			outline: none;
    			cursor: inherit;
    			display: block;
			}
        </style>
    </head>
	<body>
		<div class="wrapper container">
			<!--including bodyHead -->
            <!-- send name of current page-->
            <jsp:include page="../fragments/bodyHeader.jsp">
                <jsp:param name="pageName" value="contest"/>
            </jsp:include>
            <div class="row">
            	<div class="col-xs-3 title-container">
            		<h1 class="contest-title">${contest.title}</h1>
            	</div>
            	<!-- TODO ect. -->
            </div>
            <div class="row">
            	<div class="col-xs-3">
            		<ul class="nav nav-pills nav-stacked" style="height:65%;overflow:auto;">  
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
            		<div class="col-sm-12 title-container">
            			<h3 class="task-title">${task.title}</h3>
            		</div>
            		<div class="col-sm-12 descr-container descr-not-yet">
            			<p>${task.description}</p>
            		</div>
            		<form:form class="form-horizontal" method="post" enctype="multipart/form-data"
            				   role="form">
            			<div class="form-group">
            				<div class="col-sm-4">
            					<h4 class="pull-right">
            						<spring:message code="contest.passing.selectCompiler.caption"/>
            					</h4>
            				</div>
            				<div class="col-sm-4">
            					<select class="selectpicker" data-style="btn-info">
            						<option>Compiler1</option>
            						<option>Compiler2</option>
            						<option>Compiler3</option>
            					</select>
            				</div>
            			</div>
            			<div class="form-group">
            				<div class="col-sm-offset-4 col-sm-4">
            					<span class="btn btn-default btn-block btn-file">
            						<spring:message code="contest.passing.uploadSourceFile.caption"/>
            						<input type="file">
            					</span>
            				</div>
            			</div>
            			<div class="form-group">
            				<div class="col-sm-offset-9 col-sm-3">
            					<button class="btn btn-info btn-block">
            						<spring:message code="contest.passing.sendCode.caption"/>
            					</button>
            				</div>
            			</div>
            		</form:form>
            	</div>
			</div>
		</div>
		<jsp:include page="../fragments/footer.jsp"/>
	</body>
</html>