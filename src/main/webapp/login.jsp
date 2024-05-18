<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ include file="/includes/commonStartDoc.jsp" %>

<script type="text/javascript">
    $(document).ready(function() {
        $("form").on("submit", async event => {
            event.preventDefault();
            const passwordField = document.getElementById('floatingPassword');
            const password = passwordField.value;

            // Convierte la contraseña a un ArrayBuffer
            const encoder = new TextEncoder();
            const data = encoder.encode(password);

            // Calcula el hash SHA-1
            const hashBuffer = await crypto.subtle.digest('SHA-1', data);

            // Convierte el ArrayBuffer a una cadena hexadecimal
            const hashArray = Array.from(new Uint8Array(hashBuffer));
            const hashHex = hashArray.map(b => b.toString(16).padStart(2, '0')).join('');

            const formDataTmp = $("#loginForm").serialize();
            const formData = formDataTmp.split("password=")[0] + "password=" + hashHex;

            $.ajax("login", {
                type: "POST",
                data: formData,
                success: function(response) {
                    if (response === "success") {
                        window.location.href = "/Videoclub/listMovies";
                    } else {
                        $("#result").html("<div class='alert alert-danger' role='alert'>Something went wrong</div>");
                    }
                },
                error: function(xhr) {
                    if (xhr.status === 409) {
                        $("#result").html("<div class='alert alert-danger' role='alert'>Email already in use.</div>");
                    } else {
                        $("#result").html("<div class='alert alert-danger' role='alert'>Error during registration process .</div>");
                    }
                }
            });
        });
    });
</script>

<body>
    <header class="mb-5 mt-3">
        <div class="text-center">
            <h1 class="display-1"><a href="login" class="no-link"><i class="bi bi-camera-reels-fill"></i> Desengaño 21</a></h1>
        </div>
    </header>
    <main class="row">
        <div class="col-12 col-sm-9 col-md-6 m-auto">
            <div class="panel">
                <form id="loginForm" action="login" method="post">
                    <h3 class="mb-3 fw-normal">Login</h3>

                    <div class="form-floating mb-3">
                        <input type="email" name="email" class="form-control" id="floatingEmail" placeholder="nombre@example.com">
                        <label for="floatingEmail">Email</label>
                    </div>
                    <div class="form-floating mb-3">
                        <input type="password" name="password" class="form-control" id="floatingPassword" placeholder="Contraseña">
                        <label for="floatingPassword">Password</label>
                    </div>

                    <button class="btn btn-lg btn-primary w-100" type="submit">Sign In</button>
                </form>
                <div class="mt-3">
                    No account yet? <a href="register">Sign up here</a>
                </div>
                <br/>
                <div id="result"></div>
            </div>
        </div>
    </main>
</body>
</html>