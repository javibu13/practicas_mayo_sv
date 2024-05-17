<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript">
    $(document).ready(function() {
        $("form").on("submit", async function(event) {
            event.preventDefault();
            const passwordField = document.getElementById('password');
            const password = passwordField.value;

            // Convierte la contraseña a un ArrayBuffer
            const encoder = new TextEncoder();
            const data = encoder.encode(password);

            // Calcula el hash SHA-1
            const hashBuffer = await crypto.subtle.digest('SHA-1', data);

            // Convierte el ArrayBuffer a una cadena hexadecimal
            const hashArray = Array.from(new Uint8Array(hashBuffer));
            const hashHex = hashArray.map(b => b.toString(16).padStart(2, '0')).join('');

            const formDataTmp = $(this).serialize();
            const formData = formDataTmp.split("password=")[0] + "password=" + hashHex;

            $.ajax("register", {
                type: "POST",
                data: formData,
                success: function(response) {
                    if (response === "success") {
                        window.location.href = "/Videoclub/listMovies";
                    } else {
                        $("#result").html("<div class='alert alert-danger' role='alert'>" + response + "</div>");
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
<main class="form-signin w-100 m-auto">
    <form action="register" method="post">
        <div class="form-group">
            <label for="firstName">First Name</label>
            <input type="text" class="form-control" id="firstName" name="firstName" required>
        </div>
        <div class="form-group">
            <label for="lastName">Last Name</label>
            <input type="text" class="form-control" id="lastName" name="lastName" required>
        </div>
        <div class="form-group">
            <label for="email">Email</label>
            <input type="email" class="form-control" id="email" name="email" required>
        </div>
        <div class="form-group">
            <label for="phoneNumber">Phone Number</label>
            <input type="text" class="form-control" id="phoneNumber" name="phoneNumber">
        </div>
        <div class="form-group">
            <label for="password">Password</label>
            <input type="password" class="form-control" id="password" name="password" required>
        </div>
        <button class="w-100 btn btn-lg btn-primary" type="submit">Register</button>
    </form>
    <div id="result"></div>
</main>
