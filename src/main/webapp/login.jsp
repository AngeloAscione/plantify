<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.sql.*" %>
<%@ page import="it.unisa.model.utente.UtenteBean" %>
<%@ page import="it.unisa.model.utente.UtenteDAO" %>
<%@ include file="navbar.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <link rel="stylesheet" href="css/form.css">
    <link rel="stylesheet" href="css/products.css">
    <link rel="stylesheet" href="css/notification.css">
</head>
<body>
<div id="notification"></div>
<script type="module" src="scripts/notification.js"></script>

<div class="container-form">
    <h1>Login</h1>
    <form action="login" method="post">
        <div class="form-group">
            <label for="email">Email:</label>
            <input type="email" id="email" name="email" required>
        </div>
        <div class="form-group">
            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required>
        </div>
        <button type="submit">Login</button>
        <%
            Integer passwordNotValid = (Integer) request.getAttribute("passwordNotValid");
            Integer emailNotValid = (Integer) request.getAttribute("emailNotValid");
            String message = null;
            if ((passwordNotValid != null && passwordNotValid == 1) || (emailNotValid != null && emailNotValid == 1)) {
                //message = "Credenziali errate";
                %>
                <script type="module">
                    import {showNotification} from "./scripts/notification.js";
                    showNotification("Credenziali errate","error");
                </script>
            <%}
           // if (message != null){ %>
        <!--<div>
            <p style="color: red; font-size: 24px"> <%=message%> </p>
        </div>-->
        <% /*}*/ %>
    </form>
    <p>Don't have an account? <a href="register.jsp">Register here</a>.</p>
</div>
</body>
</html>
