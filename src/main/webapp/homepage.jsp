<%@ page import="it.unisa.model.prodotto.ProdottoBean" %>
<%@ page import="java.util.List" %>
<%@ page import="it.unisa.model.prodotto.ProdottoDAO" %>
<%@ page import="java.util.Collection" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="navbar.jsp" %>
<html>
<head>
    <title>Plantify - Homepage</title>
    <link rel="stylesheet" href="css/homepage.css">
</head>
<body>
<header class="header">
        <div class="conteiner-header">
            <h1 class="header-text-h1">Compra le tue piante preferite</h1>
            <%@ include file="search.jsp" %>
        </div>
</header>
<main>
    <section class="best-selling">
        <%@ include file="printProducts.jsp" %>
    </section>
</main>

<%@ include file="footer.jsp" %>
</body>
</html>
