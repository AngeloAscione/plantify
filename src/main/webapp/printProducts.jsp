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
<script src="scripts/cart.js"></script>


<main>
    <section class="best-selling">
        <div class="plant-grid"><%
            Collection<ProdottoBean> prodotti = new ProdottoDAO().doRetrieveAll();
            if (prodotti != null && prodotti.size() > 0){
                for(ProdottoBean pb : prodotti){ %>
                        <div class="plant-item">
                            <a href="product?prodottoId=<%=pb.getProdottoId()%>">
                                <img src="<%=pb.getFoto()%>" alt="<%=pb.getNome()%>">
                            </a>
                            <div class="info-container">
                                <div class="info">
                                    <p> <%=pb.getNome()%> </p>
                                    <p> € <%=pb.getPrezzo()%> </p>
                                </div>
                                <div class="add-to-cart">
                                    <button onclick="addToCart(<%= pb.getProdottoId() %>)">Aggiungi al carrello</button>
                                </div>
                            </div>
                        </div>
                <% }
            } else { %>
                <p>Nessun prodotto trovato</p>
            <% } %>
        </div>
    </section>
</main>
