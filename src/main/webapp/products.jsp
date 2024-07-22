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
        <%
        Collection<ProdottoBean> prodotti = new ProdottoDAO().doRetrieveAll();
        if (prodotti != null && prodotti.size() > 0){
            for(ProdottoBean pb : prodotti){ %>

            <form id="<%=pb.getNome()%>" method="post" action="product">
                <input type="number" hidden value="<%=pb.getProdottoId()%>" name="prodottoId">
                <input type="text" hidden value="getProductDetails" name="type">
                <a href="#" onclick="sendSubmit('<%=pb.getNome()%>')" style="text-decoration: none">
                    <div class="plant-item">
                        <img src="<%=pb.getFoto()%>" alt="<%=pb.getNome()%>">
                        <p> <%=pb.getNome()%> </p>
                        <p> € <%=pb.getPrezzo()%> </p>
                    </div>
                </a>
            </form>
        <% }
        } else { %>
            <p>Nessun prodotto trovato</p>
        <% } %>
        <%@ include file="footer.jsp" %>
    </body>
</html>
