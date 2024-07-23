<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="it.unisa.model.prodotto.ProdottoBean" %>
<%@ page import="it.unisa.model.prodotto.ProdottoDAO" %>
<%@ page import="java.util.Collection" %>
<main>
    <link rel="stylesheet" href="css/products.css">
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