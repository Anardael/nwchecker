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
                <div id="taskCreate">
                </div>
            </section>
        </div>
        <jsp:include page="fragments/footer.jsp"/>
    </body>
</html>