<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: Portatil
  Date: 18/05/2024
  Time: 10:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<main class="container mt-5">
    <h1 class="text-center mb-3">Préstamos Activos</h1>
    <table class="table table-hover table-striped">
        <thead class="table-dark">
        <tr>
            <th>Movie Tittle</th>
            <th>Loan start date</th>
            <th>Expected Return date</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${loans}" var="loan">
            <tr>
                <td>${loan.bookTitle}</td>
                <td><fmt:formatDate value="${loan.loanDate}" pattern="dd/MM/yyyy"/></td>
                <td><fmt:formatDate value="${loan.expectedReturnDate}" pattern="dd/MM/yyyy"/></td>
                <td>
                    <c:if test="${loan.actualReturnDate == null}">
                        <form method="POST" action="returnBook">
                            <input type="hidden" name="loanId" value="${loan.loanId}"/>
                            <button type="submit" class="btn btn-primary btn-sm" onclick="return confirm('¿Are you sure to return this movie?');">Return</button>
                        </form>
                    </c:if>
                    <c:if test="${loan.actualReturnDate != null}">
                        <span class="badge bg-success">Returned</span>
                    </c:if>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</main>

</body>
</html>
