<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>MVC Video Store</title>
	
	<jsp:include page="bootstrapHead.jsp" />
	
</head>
<body>

<jsp:include page="navbar.jsp"/>

	<div class="container-fluid">
		<h1>MVC/JPA Video Store</h1>
		<form action="getFilm.do" method="GET">
		<div class="row">
			Film ID: <input class="col form-control" type="number" name="fid" /> 
			<input class="col btn btn-success" type="submit"
				value="Show Film" />
		</div>
		</form>

		<table class="table table-striped table-hover">
			<thead class="table-dark">
				<tr>
					<th>Year</th>
					<th>Title</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="film" items="${filmList}">
					<tr>
						<td>${film.releaseYear}</td>
						<td>
							<a href="getFilm.do?fid=${film.id}"> ${film.title}</a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

	</div>


<jsp:include page="bootstrapFoot.jsp"/>
</body>
</html>