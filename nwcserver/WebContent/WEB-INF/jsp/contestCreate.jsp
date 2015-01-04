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
                <jsp:param name="pageName" value="olympiad"/>
            </jsp:include>
            <section>
                <form:form modelattribute="contest" class="form-horizontal" action="addContest.do" method="post" enctype="multipart/form-data">
                    <fieldset>
                        <div class="form-group">
                            <label class="col-sm-2 control-label"><spring:message code="contestCreate.title" />: *</label>
                            <div class="col-sm-5">
                                <input class="form-control" name="title" >
                                <form:errors path="title" cssClass="error"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label"><spring:message code="contestCreate.description" />: *</label>
                            <div class="col-sm-10">
                                <textarea class="form-control" rows="7" name="description"></textarea>
                                <form:errors path="description" cssClass="error"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label"><spring:message code="contestCreate.starts" />:</label>
                            <div class="col-sm-2">
                                <input class="form-control" name="starts">
                            </div>
                            <label class="col-sm-2 control-label"><spring:message code="contestCreate.duration" />:</label>
                            <div class="col-sm-2">
                                <input class="form-control" name="duration">
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
                </form:form>
            </section>
        </div>
        <jsp:include page="fragments/footer.jsp"/>
    </body>
</html>