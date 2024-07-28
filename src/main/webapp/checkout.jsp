<%@ page import="it.unisa.model.utente.UtenteBean" %><%--
  Created by IntelliJ IDEA.
  User: Angelo
  Date: 28/07/2024
  Time: 17:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="navbar.jsp" %>

<html>
<head>
    <title>Chckout - Plantify</title>
    <link rel="stylesheet" href="css/checkout.css">
    <script src="scripts/checkout.js"></script>
</head>
<body>
<% UtenteBean ub = (UtenteBean) request.getSession().getAttribute("userInfo"); %>
<div class="container">
    <h1>Checkout</h1>

    <div class="section">
        <h2>In consegna a ${userInfo.nome} ${userInfo.cognome} </h2>
        <p>${userInfo.via} ${userInfo.civico}, ${userInfo.cap}</p>
        <a href="#" onclick="document.getElementById('editForm').style.display='block'">Modifica</a>
    </div>

    <!-- Form per la modifica dei dati di consegna -->
    <div class="section" id="editForm" style="display: none;">
        <h2>Modifica indirizzo di consegna</h2>
        <form action="user" method="post">
            <input type="text" id="type" name="type" value="updateUserInfo" hidden>
            <div class="form-group">
                <label for="nome">Nome</label>
                <input type="text" id="nome" name="nome" value="<%= ub.getNome() %>" required>
            </div>
            <div class="form-group">
                <label for="cognome">Cognome</label>
                <input type="text" id="cognome" name="cognome" value="<%= ub.getCognome() %>" required>
            </div>
            <div class="form-group">
                <label for="via">Via</label>
                <input type="text" id="via" name="via" value="<%= ub.getVia() %>" required>
            </div>
            <div class="form-group">
                <label for="civico">Civico</label>
                <input type="text" id="civico" name="civico" value="<%= ub.getCivico() %>" required>
            </div>
            <div class="form-group">
                <label for="cap">CAP</label>
                <input type="text" id="cap" name="cap" value="<%= ub.getCap() %>" required>
            </div>
            <button type="submit">Salva</button>
            <button type="button" onclick="document.getElementById('editForm').style.display='none'">Annulla</button>
        </form>
    </div>

    <div class="section">
        <h2>Pagamento con</h2>
        <p>Mastercard **** 1234</p>
    </div>

    <div class="section">
        <h2>Totale ordine</h2>
        <p>Articoli: ${totalPrice}</p>
        <a href="checkout" style="text-decoration: none; color: white"><button>Acquista ora</button></a>
    </div>
</div>


<%@ include file="footer.jsp" %>
</body>
</html>
