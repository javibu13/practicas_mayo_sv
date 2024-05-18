<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ include file="/includes/commonStartDoc.jsp" %>

<script type="text/javascript">
    $(document).ready(function() {
        $("form").on("submit", async function(event) {
            event.preventDefault();
            const passwordFieldCurrent = document.getElementById('currentPassword');
            const passwordFieldNew = document.getElementById('newPassword');
            const passwordCurrent = passwordFieldCurrent.value;
            const passwordNew = passwordFieldNew.value;

            // Convierte la contraseña a un ArrayBuffer
            const encoder1 = new TextEncoder();
            const encoder2 = new TextEncoder();
            const data1 = encoder1.encode(passwordCurrent);
            const data2 = encoder2.encode(passwordNew);

            // Calcula el hash SHA-1
            const hashBuffer1 = await crypto.subtle.digest('SHA-1', data1);
            const hashBuffer2 = await crypto.subtle.digest('SHA-1', data2);

            // Convierte el ArrayBuffer a una cadena hexadecimal
            const hashArray1 = Array.from(new Uint8Array(hashBuffer1));
            const hashArray2 = Array.from(new Uint8Array(hashBuffer2));
            const hashHex1 = hashArray1.map(b => b.toString(16).padStart(2, '0')).join('');
            const hashHex2 = hashArray2.map(b => b.toString(16).padStart(2, '0')).join('');

            const formData = "&currentPassword=" + hashHex1 + "&newPassword=" + hashHex2;
            $.ajax("changePassword", {
                type: "POST",
                data: formData,
                success: function(response) {
                        $("#result").html("<div class='alert alert-success' role='alert'>" + "Password changed." + "</div>");
                    
                },
                error: function(xhr) {
                    $("#result").html("<div class='alert alert-danger' role='alert'>" + "Something went wrong" + "</div>");
                }
            });
        });
    });
</script>

<body>
<%@ include file="/includes/headerBar.jsp" %>
<main class="container my-4">
    <h1 class="text-center mb-4">Your Profile</h1>
    
    <div id="result">

    </div>

    <div class="form-group">
        <label for="firstName">First Name:</label>
        <input type="text" id="firstName" name="firstName" class="form-control" value="${user.firstName}" readonly disabled>
    </div>
    <div class="form-group">
        <label for="lastName">Last Name:</label>
        <input type="text" id="lastName" name="lastName" class="form-control" value="${user.lastName}" readonly disabled>
    </div>
    <div class="form-group">
        <label for="email">Email:</label>
        <input type="email" id="email" name="email" class="form-control" value="${user.email}" readonly disabled>
    </div>
    <div class="form-group">
        <label for="phoneNumber">Phone Number:</label>
        <input type="text" id="phoneNumber" name="phoneNumber" class="form-control" value="${user.phoneNumber}" readonly disabled>
    </div>
    <br>
    <br>
    <br>
    <form id="changePass" action="changePassword" method="POST" class="mb-3">
        <div class="form-group">
            <label for="currentPassword">Current Password:</label>
            <input type="password" class="form-control" id="currentPassword" name="currentPassword" required>
        </div>
        <div class="form-group">
            <label for="newPassword">New Password:</label>
            <input type="password" class="form-control" id="newPassword" name="newPassword" required>
        </div>
        <button type="submit" class="btn btn-success">Change Password</button>
    </form>
</main>
