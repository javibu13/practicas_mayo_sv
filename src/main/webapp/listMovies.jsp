<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/includes/commonStartDoc.jsp" %>

<body>
<%@ include file="/includes/headerBar.jsp" %>

<div class="container">
    <h1 class="mt-5">List of Movies</h1>
    <table class="" data-toggle="table" data-pagination="true" data-search="true">
        <thead class="table-dark">
        <tr>
            <th data-align="center" data-halign="center">Title</th>
            <th data-align="center" data-halign="center">Director</th>
            <th data-align="center" data-halign="center">Synopsis</th>
            <th data-align="center" data-halign="center">Trailer</th>
            <th data-align="center" data-halign="center">Image</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="movie" items="${movies}">
            <tr>
                <td data-field="title" data-sortable="true"><a href="${pageContext.request.contextPath}/movieDetails?id=${movie.idMovie}">${movie.title}</a></td>
                <td data-field="director" data-sortable="true">${movie.director}</td>
                <td data-field="synopsis">${movie.synopsis}</td>
                <td data-field="trailer" ><a class="no-link" href="${movie.trailer}"><i class="bi bi-camera-reels"></i></a></td>
                <td data-field="image" ><img class="fixed-table-image-size" src="${staticPath}${movie.path}"></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
