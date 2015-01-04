<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; UTF-8">

<title>File Upload</title>

<!-- Bootstrap core CSS -->
<link href="<c:url value="/css/bootstrap.css" />" rel="stylesheet">
</head>
<body>
	<div align="justify">
		<c:if test="${!empty sessionScope.images}">
			<c:forEach var="i" begin="0" end="${sessionScope.imagesLength - 1}">
				<c:url var="url" value="/ImageBuffer?source=session&ImageID=${i}"/>
				<img src="${url}" width="24%" border="1"/>
			</c:forEach>
		</c:if>
	</div>
	<form action=<c:url value="/ImageUploader"/> method="post" enctype="multipart/form-data">
		<input type="file" id="file" name="file"/>
		<br/>
		<input type="submit" value="Upload File"/>
	</form>
</body>
</html>