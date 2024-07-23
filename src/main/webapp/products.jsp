<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="navbar.jsp" %>
<html>
    <head>
        <title>Plantify - Prodotti</title>
        <link rel="stylesheet" href="css/products.css">

        <script>
            function sendSubmit(name){
                document.getElementById(name).submit()
            }
        </script>
    </head>
    <body>
    <!-- gestione prodotti-->
    <%@ include file="ideagallery.jsp"%>
    <%@ include file="footer.jsp" %>
    </body>
</html>
