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
<%@ include file="printProducts.jsp"%>
<%@ include file="footer.jsp" %>
</body>
</html>
