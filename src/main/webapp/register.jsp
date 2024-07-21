<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.sql.*" %>
<%@ page import="it.unisa.model.DBConnector" %>
<%@ page import="it.unisa.model.utente.UtenteBean" %>
<%@ page import="it.unisa.model.utente.UtenteDAO" %>
<%@ page import="it.unisa.utils.PasswordTool" %>
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
            <input type="password" id="password" name="password" required>
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
    </form>
    <p>Already have an account? <a href="login.jsp">Login here</a>.</p>
</div>
</body>
</html>
