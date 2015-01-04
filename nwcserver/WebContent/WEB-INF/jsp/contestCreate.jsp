<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- set path to resources folder -->
<spring:url value="/resources/" var="resources"/>
<html>
    <style>
        /*make red style in error forms*/
        .error{
            color: #ff0000;
        }
    </style>
    <!--including head -->
    <jsp:include page="fragments/staticFiles.jsp" />
    <body>
        <div class="wrapper container">
            <!--including bodyHead -->
            <!-- send name of current page-->
            <jsp:include page="fragments/bodyHeader.jsp">
                <jsp:param name="pageName" value="contest"/>
            </jsp:include>
            <section>
                <form:form modelAttribute="contestAddForm" class="form-horizontal" 
                           action="addContest.do" method="post" role="form">
                    <div class="form-group">
                        <label class="col-sm-2 control-label"><spring:message code="contestCreate.title" />: *</label>
                        <div class="col-sm-6">
                            <form:input path="title" class="form-control"/>
                            <form:errors path="title" Class="error"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label"><spring:message code="contestCreate.description" />: *</label>
                        <div class="col-sm-10">
                            <form:textarea path="description" class="form-control" rows="7"></form:textarea>
                            <form:errors path="description" Class="error"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label"><spring:message code="contestCreate.starts" />:</label>
                        <div class="col-sm-2">
                            <form:input path="starts" class="form-control" name="starts"/>
                            <form:errors path="starts" Class="error"/>
                        </div>
                        <label class="col-sm-2 control-label"><spring:message code="contestCreate.duration" />:</label>
                        <div class="col-sm-2">
                            <form:input path="duration" class="form-control"/>
                            <form:errors path="duration" Class="error"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-2">
                            <div class="col-sm-2">
                                <button type="submit" class="btn btn-default" value="Submit">Submit</button>
                            </div>
                        </div>
                    </div>
                </form:form>
            </section>
        </div>
        <jsp:include page="fragments/footer.jsp"/>
    </body>
</html>