<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.sql.*" %>
<%@ page import="it.unisa.model.DBConnector" %>
<%@ page import="it.unisa.model.utente.UtenteBean" %>
<%@ page import="it.unisa.model.utente.UtenteDAO" %>
<%
    String email = request.getParameter("email");
    String password = request.getParameter("password");
    String errorMessage = null;

    if (email != null && password != null) {
        try {
            UtenteDAO utenteDAO = new UtenteDAO();
            UtenteBean utente = utenteDAO.doRetrieveByEmailAndPassword(email, password);

            if (utente != null) {
                session.setAttribute("utente", utente);
                response.sendRedirect("homepage.jsp");
                return;
            } else {
                errorMessage = "Invalid email or password. Please try again.";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            errorMessage = "An error occurred. Please try again.";
        }
    }
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
<div class="login-container">
    <h1>Login</h1>
    <% if (errorMessage != null) { %>
    <div class="error-message"><%= errorMessage %></div>
    <% } %>
    <form action="login.jsp" method="post">
        <div class="form-group">
            <label for="email">Email:</label>
            <input type="email" id="email" name="email" required>
        </div>
        <div class="form-group">
            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required>
        </div>
        <button type="submit">Login</button>
    </form>
    <p>Don't have an account? <a href="register.jsp">Register here</a>.</p>
</div>
</body>
</html>