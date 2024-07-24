<%@ page import="it.unisa.model.prodotto.ProdottoBean" %>
<%@ page import="java.util.List" %>
<%@ page import="it.unisa.model.cartItem.CartItemBean" %>
<%@ page import="it.unisa.model.prodotto.ProdottoDAO" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Set" %>
<%@ page import="java.util.HashSet" %><%--
  Created by IntelliJ IDEA.
  User: fedeg
  Date: 22/07/2024
  Time: 13:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="navbar.jsp"%>

<html>
<head>
    <title>Carrello</title>
    <link rel="stylesheet" href="css/cart.css">
    <script type="module">
        import {removeFromCart} from "./scripts/cart.js";
        window.removeFromCart = removeFromCart;

        import {updateTotalPrice} from "./scripts/cart.js";
    </script>
</head>
<body>

    <div class="cart">
        <h2>Carrello</h2>
        <%
        Set<CartItemBean> carrello = (Set<CartItemBean>) session.getAttribute("cart");
        Double totale = 0.0;

        if (carrello == null){
            carrello = new HashSet<>();
            session.setAttribute("cart", carrello);
        }

        if (carrello.size() > 0) {
            for (CartItemBean item : carrello){
                ProdottoBean pb = new ProdottoDAO().doRetrieveByKey(item.getProdottoId());
                if (pb.getQta() <= 1)
                    item.setQuantita(pb.getQta());
            %>
            <div class="cart-item">
                <div class="item-info">
                    <a href="product?prodottoId=<%=pb.getProdottoId()%>">
                        <img src="<%=pb.getFoto()%>" alt="<%=pb.getNome()%>" class="item-image">
                    </a>
                    <div class="item-details">
                        <h3> <%=pb.getNome()%> </h3>
                        <% if (pb.getQta() == 1) { %>
                            <p style="color:#b12704!important">Disponibilità: solo 1</p>
                        <% } else if (pb.getQta() == 0) { %>
                            <p style="color:#b12704!important">Non più disponibile</p>
                        <% } %>
                        <p> <%=pb.getDescrizione()%> </p>
                    </div>
                </div>
                <div class="item-actions">
                    <span class="item-price">€ <%=pb.getPrezzo()%> </span>
                    <div class="item-quantity">
                        <label for="qta">Q.tà:</label>
                        <input type="number" id="qta" value="<%= item.getQuantita() %>" style="width: 50px" <% if (pb.getQta() <= 1){ %> readonly <% } %> >
                    </div>
                    <button onclick="removeFromCart(<%= pb.getProdottoId() %>)" class="remove-button">Rimuovi</button>
                </div>
            </div>
        <% } %>
        <div class="cart-summary">
            <p>Totale (<%= carrello.size() %> <% String articoli = carrello.size() == 1 ? "articolo" : "articoli"; %> <%= articoli %>): <span id="totale-prezzo"><script>updateTotalPrice()</script></span></p>
            <button class="checkout">Procedi all'ordine</button>
        </div>
        <% } else { %>
    <p>Nessun prodotto trovato nel carrello, <a href="products.jsp" style="text-decoration: none">sfoglia il catalogo!</a></p>
    <% } %>
    </div>

<%@ include file="footer.jsp" %>
</body>
</html>
