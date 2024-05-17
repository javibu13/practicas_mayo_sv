<%--
  Created by IntelliJ IDEA.
  User: Portatil
  Date: 17/05/2024
  Time: 13:32
  To change this template use File | Settings | File Templates.
--%><script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript">
    $(document).ready(function() {
        $("form").on("submit", function(event) {
            event.preventDefault();
            const formData = $(this).serialize();
            $.ajax("login", {
                type: "POST",
                data: formData,
                success: function(response) {
                    if (response === "success") {

                        window.location.href = "/Videoclub/listMovies";
                    } else {

                        $("#result").html("<div class='alert alert-danger' role='alert'>Invalid email or password.</div>");
                    }
                },
                error: function(error) {

                    $("#result").html("<div class='alert alert-danger' role='alert'>Error during the login process.</div>");
                }
            });
        });
    });
</script>
<main class="form-signin w-100 m-auto">
    <form>
        <h1 class="h3 mb-3 fw-normal">Login</h1>

        <div class="form-floating">
            <input type="email" name="email" class="form-control" id="floatingEmail" placeholder="nombre@example.com">
            <label for="floatingEmail">Email</label>
        </div>
        <div class="form-floating">
            <input type="password" name="password" class="form-control" id="floatingPassword" placeholder="Contraseña">
            <label for="floatingPassword">Contraseña</label>
        </div>

        <button class="btn btn-lg btn-primary w-100" type="submit">Iniciar sesión</button>
    </form>
    <div class="mt-3">
        ¿No tienes una cuenta? <a href="register.jsp">Regístrate aquí</a>
    </div>
    <br/>
    <div id="result"></div>
</main>
