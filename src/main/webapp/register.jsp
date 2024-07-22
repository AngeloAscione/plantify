<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="navbar.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Register</title>
    <link rel="stylesheet" href="css/form.css">
</head>
<body>
<div class="register-container">
    <h1>Register</h1>
    <form action="register" method="post">
        <div class="form-group">
            <label for="nome">Nome:</label>
            <input type="text" id="nome" name="nome" required>
        </div>
        <div class="form-group">
            <label for="cognome">Cognome:</label>
            <input type="text" id="cognome" name="cognome" required>
        </div>
        <div class="form-group">
            <label for="email">Email:</label>
            <input type="email" id="email" name="email" required>
        </div>
        <div class="form-group">
            <label for="password">Password:</label>
            <input type="password" id="password" name="password"
                   pattern="^(?=.*[A-Z])(?=.*\d)(?=.*[!@#$%^&*(),.?&quot;:{}|<>])[A-Za-z\d!@#$%^&*(),.?&quot;:{}|&lt;&gt;]{8,16}$"
                   title="La password deve essere tra 8 e 16 caratteri e contenere almeno una lettera maiuscola, un numero e un carattere speciale." required>
        </div>
        <div class="form-group">
            <label for="via">Via:</label>
            <input type="text" id="via" name="via" required>
        </div>
        <div class="form-group">
            <label for="civico">Civico:</label>
            <input type="number" id="civico" name="civico" required>
        </div>
        <div class="form-group">
            <label for="cap">CAP:</label>
            <input type="number" id="cap" name="cap" required>
        </div>
        <div class="form-group">
            <label for="telefono">Telefono:</label>
            <input type="text" id="telefono" name="telefono" required>
        </div>
        <button type="submit">Register</button>
        <%
        Integer passNotValid = (Integer) request.getAttribute("passwordNotValid");
        Integer emailTaken = (Integer) request.getAttribute("emailTaken");
        if (passNotValid != null && passNotValid == 1){ %>
            <div>
                <p style="color: red; font-size: 24px"> Password non valida </p>
            </div>
        <% } else if (emailTaken != null && emailTaken == 1){ %>
            <div>
                <p style="color: red; font-size: 24px"> Email già registrata, prova ad effettuare il login </p>
            </div>
        <% } %>
    </form>
    <p>Already have an account? <a href="login.jsp">Login here</a>.</p>
</div>
</body>
</html>
