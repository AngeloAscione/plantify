<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="navbar.jsp" %>
<html>
<head>
    <title>Plantify - Homepage</title>
    <link rel="stylesheet" href="css/checkout.css">
    <link rel="stylesheet" href="css/provastyle.css">

</head>
<body>

<%
    String message = "";
    String goBackLink = "";

    if (request.getAttribute("orderError") != null) {
        message = "L'ordine non è andato a buon fine, torna al carrello cliccando ";
        goBackLink = "cart?type=getCart";
    } else {
        message = "Ordine completato, torna allo shopping cliccando ";
        goBackLink = "index.html";
    }
%>

<div class="container">
    <div class="section">
        <h2><%= message %><a href="<%= goBackLink %>">qui</a></h2>
    </div>
</div>

</body>
</html>
