<%@ page import="it.unisa.model.prodotto.ProdottoBean" %>
<%@ page import="it.unisa.model.cartItem.CartItemBean" %>
<%@ page import="it.unisa.model.prodotto.ProdottoDAO" %>
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
    <link rel="stylesheet" href="css/products.css">
    <link rel="stylesheet" href="css/notification.css">
    <script type="module">
        import {removeFromCart} from "./scripts/cart.js";
        window.removeFromCart = removeFromCart;
    </script>
</head>
<body>
<div id="notification"></div>
    <div class="cart">
        <h2>Carrello</h2>
        <%
        Set<CartItemBean> carrello = (Set<CartItemBean>) session.getAttribute("cart");
        int dimCarrello = 0;
        Double prezzo = 0.0;
        if (carrello == null){
            carrello = new HashSet<>();
            session.setAttribute("cart", carrello);
        }

        if (carrello.size() > 0) {
            for (CartItemBean item : carrello){
                ProdottoBean pb = new ProdottoDAO().doRetrieveByKey(item.getProdottoId());
                if (pb.getQta() <= 1)
                    item.setQuantita(pb.getQta());
                dimCarrello += item.getQuantita();
                prezzo += item.getQuantita() * pb.getPrezzo();
            %>
            <div class="cart-item <%=pb.getProdottoId()%>">
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
                        <input type="number" id="qta" data-product-id="<%= pb.getProdottoId() %>"value="<%= item.getQuantita() %>" style="width: 50px" <% if (pb.getQta() <= 1){ %> readonly <% } %> >
                    </div>
                    <button class="add-to-cart" onclick="removeFromCart(<%= pb.getProdottoId() %>)" class="remove-button">Rimuovi</button>
                </div>
            </div>
        <% } %>
        <div class="cart-summary">
            <% if (request.getSession().getAttribute("logged") != null && (Boolean) request.getSession().getAttribute("logged")){ %>
            <p>Totale (<%= dimCarrello %> <%= carrello.size() == 1 ? "articolo" : "articoli" %>): <%=String.format("%.2f", prezzo)%> €</p>
            <% String link;
                if (prezzo > 0.0){
                    link = "checkout.jsp";
            } else {
                    link = "#";
            } %>
            <a class="add-to-cart-button-link" href="<%= link %>" style="text-decoration: none; color: white"><button class="add-to-cart">Procedi all'ordine</button></a>
            <%
            request.getSession().setAttribute("totalPrice", String.format("%.2f", prezzo));
            } else { %>
                <span> Effettua il <a href="login.jsp">login</a> per completare l'ordine </span>
            <% } %>
        </div>
        <% } else { %>
    <p>Nessun prodotto trovato nel carrello, <a href="products.jsp" style="text-decoration: none">sfoglia il catalogo!</a></p>
    <% } %>
    </div>

</body>
</html>
