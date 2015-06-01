<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<section class="col-md-15">
<div class="jumbotron">
	<blockquote>
		<p>
			"
			<spring:message code="index.quote" />
			"
		</p>
		<small><spring:message code="index.quoteAuthor" /></small>
	</blockquote>
</div>
<div class="info">
	<p>
		<b>NWCServer</b> -
		<spring:message code="index.content.header" />
	</p>
	<p>
		<b><spring:message code="index.content.goal" />:</b>
	</p>
	<p>
		<spring:message code="index.content.goalBody" />
	</p>
</div>
</section>
</html>