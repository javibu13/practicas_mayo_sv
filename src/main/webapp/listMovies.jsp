<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <title>List of Movies</title>
</head>
<body>
<div class="container">
    <h1 class="mt-5">List of Movies</h1>
    <table class="table table-striped mt-3">
        <thead>
        <tr>
            <th>Title</th>
            <th>Director</th>
            <th>Synopsis</th>
            <th>Trailer</th>
            <th>Image</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="movie" items="${movies}">
            <tr>
                <td>${movie.title}</td>
                <td>${movie.director}</td>
                <td>${movie.synopsis}</td>
                <td>${movie.trailer}</td>
                <td>${movie.path}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
