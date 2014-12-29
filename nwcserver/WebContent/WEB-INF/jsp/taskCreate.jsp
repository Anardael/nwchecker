<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- set path to resources folder -->
<spring:url value="/resources/" var="resources"/>
<html>
    <!--including head -->
    <jsp:include page="fragments/staticFiles.jsp" />
    <body>
        <div class="wrapper container">
            <!--including bodyHead -->
            <!-- send name of current page-->
            <jsp:include page="fragments/bodyHeader.jsp">
                <jsp:param name="pageName" value="taskCreate"/>
            </jsp:include>
            <section>
                <form class="form-horizontal" action="addTask.do" method="post">
                    <div class="form-group">
                        <label for="inputTitle" class="col-sm-2 control-label">Title:</label>
                        <div class="col-sm-5">
                            <input class="form-control" id="inputTitle">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputMamoryLimit" class="col-sm-2 control-label">Memory limit:</label>
                        <div class="col-sm-2">
                            <input type="text" class="form-control" id="inputMamoryLimit">
                        </div>
                        <label for="inputMaxMark" class="col-sm-2 control-label">Rate:</label>
                        <div class="col-sm-2">
                            <input class="form-control" id="inputPassword3" >
                        </div>
                        <label for="inputFileName" class="col-sm-2 control-label">Input file name:</label>
                        <div class="col-sm-2">
                            <input class="form-control" id="inputFileName">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputTimeLimit" class="col-sm-2 control-label">Time limit:</label>
                        <div class="col-sm-2">
                            <input class="form-control" id="inputTimeLimit">
                        </div>
                        <label for="inputComplexity" class="col-sm-2 control-label">Complexity:</label>
                        <div class="col-sm-2">
                            <input class="form-control" id="inputComplexity">
                        </div>
                        <label for="outputFileName" class="col-sm-2 control-label">Output file name:</label>
                        <div class="col-sm-2">
                            <input class="form-control" id="outputFileName">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputDescription" class="col-sm-2 control-label">Description:</label>
                        <div class="col-sm-10">
                            <textarea class="form-control" rows="7"></textarea>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputFile" class="col-sm-2 control-label">Input data file:</label>
                        <div class="col-sm-2">
                            <input type="file" name="in">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="outputFile" class="col-sm-2 control-label">Output data file:</label>
                        <div class="col-sm-2">
                            <input type="file" name="out">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="forumLink" class="col-sm-2 control-label">Set forum link:</label>
                        <div class="col-sm-4">
                            <input class="form-control" id="outputFileName">
                        </div>
                    </div>
                    <div class="controls form-group">
                        <div class="entry input-group col-sm-5" style="padding-left: 180px">
                            <input class="form-control" name="fields[]" type="text" placeholder="Add theory link" />
                            <span class="input-group-btn">
                                <button class="btn btn-success btn-add" type="button">
                                    <span class="glyphicon glyphicon-plus"></span>
                                </button>
                            </span>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-2">
                            <div class="col-sm-2">
                                <button type="submit" class="btn btn-default" value="Submit">Submit</button>
                            </div>
                        </div>
                    </div>
                </form>
            </section>
        </div>
        <jsp:include page="fragments/footer.jsp"/>
    </body>
</html>