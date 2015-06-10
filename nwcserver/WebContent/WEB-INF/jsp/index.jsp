<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <div class="jumbotron">
        <blockquote>
            <p>
                "
                <spring:message code="index.quote" />
                "
            </p>
            <spring:message code="index.quoteAuthor" />
        </blockquote>
    </div>
    <div id="info">
        <b>NWCServer</b>
        <p>
            <spring:message code="index.content.header" />
        </p>
        <b><spring:message code="index.content.goal" /></b>
        <p>
            <spring:message code="index.content.goalBody" />
        </p>
    </div>
</html>