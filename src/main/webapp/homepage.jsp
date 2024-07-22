<%@ page import="it.unisa.model.prodotto.ProdottoBean" %>
<%@ page import="java.util.List" %>
<%@ page import="it.unisa.model.prodotto.ProdottoDAO" %>
<%@ page import="java.util.Collection" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="navbar.jsp" %>
<html>
<head>
    <title>Sezione Piante</title>
    <link rel="stylesheet" href="css/homepage.css">
</head>
<body>
<header class="header">
        <div class="header-text">
            <h1>Compra le tue piante preferite</h1>
        </div>
</header>
<main>
    <section class="best-selling">
        <div class="plant-grid">
            <%
            Collection<ProdottoBean> prodotti = new ProdottoDAO().doRetrieveAll();
            if (prodotti != null && prodotti.size() > 0){
                for(ProdottoBean pb : prodotti){ %>

                <div class="plant-item">
                    <img src="<%=pb.getFoto()%>" alt="<%=pb.getNome()%>">
                    <p> <%=pb.getNome()%> </p>
                    <p> € <%=pb.getPrezzo()%> </p>
                </div>
            <% }
            } else { %>
                <p>Nessun prodotto trovato</p>
            <% } %>

    </section>
</main>

<%@ include file="footer.jsp" %>
</body>
</html>
