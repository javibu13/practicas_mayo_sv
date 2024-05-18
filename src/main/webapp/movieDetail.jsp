<%@ page import="org.example.domain.Movie" %>
<%@ page import="org.example.dao.Database" %>
<%@ page import="org.example.dao.MoviesDao" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/includes/commonStartDoc.jsp" %>

<body>
<%@ include file="/includes/headerBar.jsp" %>
<main class="row">
    <div class="col-12 col-sm-9 col-md-6 m-auto">
        <div class="panel">
            <div class="row">
                <div class="col-12 col-md-4">
                    <img src="${staticPath}${movie.path}" alt="${movie.title}" class="fixed-details-image-size">
                </div>
                <div class="col-12 col-md-8">
                    <h2>${movie.title}</h2>
                    <p><strong>Director:</strong> ${movie.director}</p>
                    <p><strong>Synopsis:</strong> ${movie.synopsis}</p>
                    <p><strong>Trailer:</strong> <a href="${movie.trailer}" target="_blank">Watch Trailer</a></p>
                    <p><strong>Available:</strong> ${availableStock}/${movie.quantity}</p>

                    <!-- Display Rent or Return Button -->
                    <c:choose>
                        <c:when test="${movie.rentedByUser}">
                            <form action="${pageContext.request.contextPath}/loanMovie" method="post">
                                <input type="hidden" name="idMovie" value="${movie.idMovie}">
                                <input type="hidden" name="action" value="return">
                                <button type="submit" class="btn btn-primary">Return Movie</button>
                            </form>
                        </c:when>
                        <c:otherwise>
                            <form action="${pageContext.request.contextPath}/loanMovie" method="post">
                                <input type="hidden" name="idMovie" value="${movie.idMovie}">
                                <input type="hidden" name="action" value="rent">
                                <button type="submit" class="btn btn-primary">Loan Movie</button>
                            </form>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </div>
    </div>
</main>
</body>
