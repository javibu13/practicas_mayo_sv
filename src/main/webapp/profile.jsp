<%--
  Created by IntelliJ IDEA.
  User: Portatil
  Date: 17/05/2024
  Time: 13:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<main class="container my-4">
    <h1 class="text-center mb-4">Tu Perfil</h1>
    <form action="ShowProfileServlet" method="POST" class="mb-3">
        <div class="form-group">
            <label for="firstName">Nombre:</label>
            <input type="text" id="firstName" name="firstName" class="form-control" value="${user.firstName}" required>
        </div>
        <div class="form-group">
            <label for="lastName">Apellido:</label>
            <input type="text" id="lastName" name="lastName" class="form-control" value="${user.lastName}" required>
        </div>
        <div class="form-group">
            <label for="email">Correo Electrónico:</label>
            <input type="email" id="email" name="email" class="form-control" value="${user.email}" required>
        </div>
        <div class="form-group">
            <label for="phoneNumber">Número de Teléfono:</label>
            <input type="text" id="phoneNumber" name="phoneNumber" class="form-control" value="${user.phoneNumber}">
        </div>
        <div class="form-group">
            <label for="address">Dirección:</label>
            <input type="text" id="address" name="address" class="form-control" value="${user.address}">
        </div>
        <button type="submit" class="btn btn-primary">Actualizar Información</button>
    </form>
    <form action="ChangePasswordServlet" method="POST" class="mb-3">
        <div class="form-group">
            <label for="currentPassword">Contraseña Actual:</label>
            <input type="password" class="form-control" id="currentPassword" name="currentPassword" required>
        </div>
        <div class="form-group">
            <label for="newPassword">Nueva Contraseña:</label>
            <input type="password" class="form-control" id="newPassword" name="newPassword" required>
        </div>
        <div class="form-group">
            <label for="confirmPassword">Confirmar Nueva Contraseña:</label>
            <input type="password" class="form-control" id="confirmPassword" name="confirmPassword" required>
        </div>
        <button type="submit" class="btn btn-success">Cambiar Contraseña</button>
    </form>
</main>