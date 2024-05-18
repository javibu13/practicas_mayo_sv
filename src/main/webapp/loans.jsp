<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Active Loans</title>
</head>
<body>
<main class="container mt-5">
    <h1 class="text-center mb-3">Active Loans</h1>
    <table class="table table-hover table-striped">
        <thead class="table-dark">
        <tr>
            <th>Movie Title</th>
            <th>Loan Start Date</th>
            <th>Expected Return Date</th>
            <th>Returned Date</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${loans}" var="loan">
            <tr>
                <td>${loan.movieTitle}</td>
                <td><fmt:formatDate value="${loan.startDate}" pattern="dd/MM/yyyy"/></td>
                <td><fmt:formatDate value="${loan.expectedDate}" pattern="dd/MM/yyyy"/></td>

                <c:if test="${loan.returnDate == null}">
                <td>
                    —
                </td>
                <td>
                    <form method="POST" action="loanMovie">
                        <input type="hidden" name="idLoan" value="${loan.idLoan}"/>
                        <input type="hidden" name="action" value="return">
                        <button type="submit" class="btn btn-primary btn-sm" onclick="return confirm('Are you sure you want to return this movie?');">Return</button>
                    </form>
                </td>
                    </c:if>
                <c:if test="${loan.returnDate != null}">
                    <td>
                            ${loan.returnDate}
                    </td>
                    <td>
                        <span class="badge bg-success">Returned</span>
                    </td>
                </c:if>

            </tr>
        </c:forEach>
        </tbody>
    </table>
</main>
</body>
</html>
