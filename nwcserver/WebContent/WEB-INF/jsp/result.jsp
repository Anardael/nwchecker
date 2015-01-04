<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                <jsp:param name="pageName" value="result"/>
            </jsp:include>
            <section>
                <c:set var="result" value="${result}"/>
                <c:if test="${not empty result}">
                    ${result}
                </c:if>
                <c:if test="${not empty error}">
                    <h4>Errors:</h4>
                    <c:forEach items="${errorq}" var="er">
                        <p>${er.message}</p>
                    </c:forEach>
                </c:if>
            </section>
        </div>
        <jsp:include page="fragments/footer.jsp"/>
    </body>
</html>