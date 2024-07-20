<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.sql.*" %>
<%@ page import="it.unisa.model.DBConnector" %>
<%@ page import="it.unisa.model.utente.UtenteBean" %>
<%@ page import="it.unisa.model.utente.UtenteDAO" %>
<%@ page import="it.unisa.utils.PasswordTool" %>
<%
    String nome = request.getParameter("nome");
    String cognome = request.getParameter("cognome");
    String email = request.getParameter("email");
    String password = request.getParameter("password");
    String via = request.getParameter("via");
    String civicoStr = request.getParameter("civico");
    String capStr = request.getParameter("cap");
    String telefono = request.getParameter("telefono");
    String errorMessage = null;

    if (nome != null && cognome != null && email != null && password != null && via != null && civicoStr != null && capStr != null && telefono != null) {
        if (!PasswordTool.isValidPassword(password)) {
            errorMessage = "Password must be 8-16 characters long, include at least one number, one uppercase letter, and one special character.";
        } else {
            try {
                int civico = Integer.parseInt(civicoStr);
                int cap = Integer.parseInt(capStr);

                UtenteBean utente = new UtenteBean();
                utente.setNome(nome);
                utente.setCognome(cognome);
                utente.setEmail(email);
                utente.setPassword(password);
                utente.setVia(via);
                utente.setCivico(civico);
                utente.setCap(cap);
                utente.setTelefono(telefono);
                utente.setAdmin(false);
                utente.setLogged(false);

                UtenteDAO utenteDAO = new UtenteDAO();
                boolean success = utenteDAO.doSave(utente);

                if (success) {
                    session.setAttribute("utente", utente);
                    response.sendRedirect("index.jsp");
                    return;
                } else {
                    errorMessage = "Registration failed. Please try again.";
                }
            } catch (SQLException e) {
                e.printStackTrace();
                errorMessage = "An error occurred. Please try again.";
            } catch (NumberFormatException e) {
                errorMessage = "Invalid input. Please check your data.";
            }
        }
    }
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Register</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
<div class="register-container">
    <h1>Register</h1>
    <% if (errorMessage != null) { %>
    <div class="error-message"><%= errorMessage %></div>
    <% } %>
    <form action="register.jsp" method="post" onsubmit="return true">
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
