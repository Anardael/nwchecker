<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<spring:url value="" var="resources"/>

<header>
    <!-- service Logo -->
    <a href="/"><img src="/images/logo.png" alt="Whitesquare logo"></a>
    <!-- choose language -->
    <div class="languageChoose">
        <a href="/"><img src= "${resources}images/ukraineFlag.png" width="36" height="36"  alt="ua"></a>
        <a href="/"><img src="${resources}images/russiaFlag.png" width="36" height="36"  alt="ru"></a>
        <a href="/"><img src="${resources}images/ukFlag.png" width="36" height="36"  alt="en"></a>
    </div>
    <!-- navigating toolbar -->
    <nav class="navbar navbar-default">
        <ul class="nav navbar-nav">
            <!-- home -->
            <c:choose>
                <c:when test="${param.pageName=='home'}">
                    <li class="active"><a>Home</a></li>
                    </c:when>
                    <c:otherwise>
                    <li><a href="index">Home</a></li>
                    </c:otherwise>
                </c:choose>
            <!-- news -->
            <c:choose>
                <c:when test="${param.pageName=='news'}">
                    <li class="active"><a>News</a></li>
                    </c:when>
                    <c:otherwise>
                    <li><a href="/" >News</a></li>
                    </c:otherwise>
                </c:choose>
            <!-- tasks -->
            <c:choose>
                <c:when test="${param.pageName=='task'}">
                    <li class="active"><a href="/">Task</a></li>
                    </c:when>
                    <c:otherwise>
                    <li><a href="/" >Task</a></li>
                    </c:otherwise>
                </c:choose>
            <!-- rating -->
            <c:choose>
                <c:when test="${param.pageName=='rating'}">
                    <li class="active"><a href="/">Rating</a></li>
                    </c:when>
                    <c:otherwise>
                    <li><a href="/" >Rating</a></li>
                    </c:otherwise>
                </c:choose>
            <!-- olympiad -->
            <c:choose>
                <c:when test="${param.pageName=='olympiad'}">
                    <li class="active"><a href="/">Olympiad</a></li>
                    </c:when>
                    <c:otherwise>
                    <li><a href="/" >Olimpiad</a></li>
                    </c:otherwise>
                </c:choose>
            <!-- login -->
            <c:choose>
                <c:when test="${param.pageName=='login'}">
                    <li class="active"><a>Login</a></li>
                    </c:when>
                    <c:otherwise>
                    <li><a href="login" >Login</a></li>
                    </c:otherwise>
                </c:choose>
            <!-- registration -->
            <c:choose>
                <c:when test="${param.pageName=='registration'}">
                    <li class="active"><a>Registration</a></li>
                    </c:when>
                    <c:otherwise>
                    <li><a href="registration" >Registration</a></li>
                    </c:otherwise>
                </c:choose>			
        </ul>
    </nav>
    <div class="heading">
        <h1>${param.pageName}</h1>
    </div>
</header>