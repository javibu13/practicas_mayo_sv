<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ include file="/includes/commonStartDoc.jsp" %>

<script type="text/javascript">
    $(document).ready(function() {
        $("form").on("submit", async event => {
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

            try {
                const response = await fetch('signup', {
                method: 'POST',
                body: formData
                });

                if (response.ok) {
                    const text = await response.text();
                    if (text === "success") {
                        window.location.href = "/Videoclub/login";
                    } else {
                        document.getElementById('result').innerHTML = `<div class='alert alert-danger' role='alert'>${text}</div>`;
                    }
                } else {
                    if (response.status === 409) {
                        document.getElementById('result').innerHTML = "<div class='alert alert-danger' role='alert'>Email already in use.</div>";
                    } else {
                        document.getElementById('result').innerHTML = "<div class='alert alert-danger' role='alert'>Error during registration process.</div>";
                    }
                }
            } catch (error) {
                document.getElementById('result').innerHTML = "<div class='alert alert-danger' role='alert'>Error during registration process.</div>";
            }
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
                <form action="register" method="post">
                    <div class="form-group mb-3">
                        <label for="firstName">First Name</label>
                        <input type="text" class="form-control" id="firstName" name="firstName" required>
                    </div>
                    <div class="form-group mb-3">
                        <label for="lastName">Last Name</label>
                        <input type="text" class="form-control" id="lastName" name="lastName" required>
                    </div>
                    <div class="form-group mb-3">
                        <label for="email">Email</label>
                        <input type="email" class="form-control" id="email" name="email" required>
                    </div>
                    <div class="form-group mb-3">
                        <label for="phoneNumber">Phone Number</label>
                        <input type="text" class="form-control" id="phoneNumber" name="phoneNumber">
                    </div>
                    <div class="form-group mb-3">
                        <label for="password">Password</label>
                        <input type="password" class="form-control" id="password" name="password" required>
                    </div>
                    <button id="signupButton" class="w-100 btn btn-lg btn-primary mb-3" type="submit">Sign Up</button>
                    
                    <div id="result"></div>
                </form>
            </div>
        </div>
    </main>
</body>
