<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
                <form class="form-horizontal" action="addTask.do" method="post" enctype="multipart/form-data">
                    <fieldset>
                        <div class="form-group">
                            <label class="col-sm-2 control-label"><spring:message code="taskCreate.title" />: *</label>
                            <div class="col-sm-7">
                                <input class="form-control" name="title">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label"><spring:message code="taskCreate.memoryLimit" />:</label>
                            <div class="col-sm-2">
                                <input type="text" class="form-control" name="memoryLimit">
                            </div>
                            <label class="col-sm-2 control-label"><spring:message code="taskCreate.rate" />: *</label>
                            <div class="col-sm-2">
                                <input class="form-control" name="rate"/>
                            </div>
                            <label class="col-sm-2 control-label"><spring:message code="taskCreate.inputFileName" />: *</label>
                            <div class="col-sm-2">
                                <input class="form-control" name="inputFileName"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label"><spring:message code="taskCreate.timeLimit" />:</label>
                            <div class="col-sm-2">
                                <input class="form-control" name="timeLimit">
                            </div>
                            <label class="col-sm-2 control-label"><spring:message code="taskCreate.complexity" />:</label>
                            <div class="col-sm-2">
                                <input class="form-control" name="complexity">
                            </div>
                            <label class="col-sm-2 control-label"><spring:message code="taskCreate.outputFileName" />: *</label>
                            <div class="col-sm-2">
                                <input class="form-control" name="outputFileName"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label"><spring:message code="taskCreate.description" />: *</label>
                            <div class="col-sm-10">
                                <textarea class="form-control" rows="7" name="description"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label"><spring:message code="taskCreate.verificationScript" />:</label>
                            <div class="col-sm-10">
                                <textarea class="form-control" rows="7" name="verificationScript"></textarea>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label"><spring:message code="taskCreate.inputData" />:</label>
                            <div class="col-sm-2">
                                <input type="file" name="inputFile">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label"><spring:message code="taskCreate.outputData" />:</label>
                            <div class="col-sm-2">
                                <input type="file" name="outputFile">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label"><spring:message code="taskCreate.forumLink" />:</label>
                            <div class="col-sm-4">
                                <input class="form-control" name="forumLink">
                            </div>
                        </div>
                        <div class="controls form-group">
                            <div class="entry input-group col-sm-5" style="padding-left: 180px">
                                <input class="form-control" name="theoryLinks[]" autocomplete="off" type="text" placeholder="<spring:message code="taskCreate.addTheoryLink" />" />
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
                    </fieldset>
                </form>
            </section>
        </div>
        <jsp:include page="fragments/footer.jsp"/>
    </body>
</html>