<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Film Details</title>

<jsp:include page="../bootstrapHead.jsp" />

</head>
<body>

<jsp:include page="../navbar.jsp"/>

<div class="container-fluid">
<!-- TODO more film details -->
	<h1>Film Details</h1>
	<div>
		<h5>${film.title} (${film.releaseYear})</h5>
		<p>${film.length } minutes</p>
		<blockquote>${film.description}</blockquote>
	</div>
</div>

<jsp:include page="../bootstrapFoot.jsp"/>

</body>
</html>