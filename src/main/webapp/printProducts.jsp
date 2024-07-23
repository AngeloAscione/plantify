<%@ page import="it.unisa.model.prodotto.ProdottoBean" %>
<%@ page import="it.unisa.model.prodotto.ProdottoDAO" %>
<%@ page import="java.util.Collection" %><%--
  Created by IntelliJ IDEA.
  User: goldr
  Date: 22/07/2024
  Time: 19:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" href="css/products.css">

<main>
    <section class="best-selling">
        <div class="plant-grid"><%
            Collection<ProdottoBean> prodotti = new ProdottoDAO().doRetrieveAll();
            if (prodotti != null && prodotti.size() > 0){
                for(ProdottoBean pb : prodotti){ %>
                    <a href="product?prodottoId=<%=pb.getProdottoId()%>">
                        <div class="plant-item">
                            <img src="<%=pb.getFoto()%>" alt="<%=pb.getNome()%>">
                            <p> <%=pb.getNome()%> </p>
                            <p> € <%=pb.getPrezzo()%> </p>
                        </div>
                    </a>
                <% }
            } else { %>
                <p>Nessun prodotto trovato</p>
            <% } %>
        </div>
    </section>
</main>
