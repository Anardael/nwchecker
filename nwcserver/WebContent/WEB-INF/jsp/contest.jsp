<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
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
                <jsp:param name="pageName" value="contest"/>
            </jsp:include>
            <section>
                <div class="form-group col-sm-12" style="margin:auto">
                    <c:forEach items="${contests}" var="contest">
                        <div class="col-sm-6 col-sm-offset-3" >
                            <h4 style="text-align: center">${contest.title}</h4>
                            <span style="text-align: left"><spring:message code="contest.table.duration" />: ${contest.duration}</span>
                            <span style="float: right"><spring:message code="contest.table.starts" />: ${contest.starts.day}/${1+contest.starts.month}/${1900+contest.starts.year}
                                ${contest.starts.hours}:${contest.starts.minutes}</span>
                        </div>
                        <div class="col-sm-3" style="margin-top: 18px">
                            <form action="editContest.do" method="get" >
                                <input name="id" value="3" hidden>
                                <input type="submit" value="<spring:message code="contest.editButton.caption" />">
                            </form>
                        </div>
                    </c:forEach>
                    <div class="col-sm-6 col-sm-offset-3">
                        <form action="addContest.do" style="text-align: center; margin-top: 20px">
                            <input type="submit" value="<spring:message code="contest.createButton.caption" />">
                        </form>
                    </div>
                </div>
            </section>
        </div>
        <jsp:include page="fragments/footer.jsp"/>
    </body>
</html>