<%@ page import="it.unisa.model.prodotto.ProdottoBean" %>
<%@ page import="it.unisa.model.prodotto.ProdottoDAO" %>
<%@ page import="java.util.Collection" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="navbar.jsp" %>
<html>
    <head>
        <title>Plantify - Prodotti</title>
        <link rel="stylesheet" href="css/homepage.css">

        <script>
            function sendSubmit(name){
                document.getElementById(name).submit()
            }
        </script>

    </head>
    <body>

<!-- gestione prodotti-->
        <%@ include file="printProducts.jsp"%>
        <%@ include file="footer.jsp" %>
    </body>
</html>
