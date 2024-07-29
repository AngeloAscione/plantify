<%@ page import="it.unisa.model.prodotto.ProdottoBean" %>
<%@ page import="it.unisa.model.cartItem.CartItemBean" %>
<%@ page import="it.unisa.model.prodotto.ProdottoDAO" %>
<%@ page import="java.util.Set" %>
<%@ page import="java.util.HashSet" %>
<%@ page import="it.unisa.model.orderItem.OrderItemBean" %>
<%@ page import="it.unisa.model.ordine.OrdineBean" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="it.unisa.model.orderItem.OrderItemDAO" %><%--
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
    <title>Ordini</title>
    <link rel="stylesheet" href="css/orders.css">
    <link rel="stylesheet" href="css/products.css">
    <link rel="stylesheet" href="css/notification.css">
</head>
<body>
<div id="notification"></div>
    <div class="orderList">
        <h2>Ordini</h2>

        <%
            Set<OrdineBean> ordini = (Set<OrdineBean>) request.getAttribute("ordini");
            if (ordini != null && ordini.size() > 0){
                for (OrdineBean o : ordini){
        %>
            <div class="order-item">
                <div>
                    <h2>Ordine # <%= o.getOrdineId()%> </h2>
                    <p> <strong>Ordine effettuato il:</strong> <%= o.getDataOrdine().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))%> </p>
                    <p> <strong>Totale: </strong> <%= o.getTotale()%> </p>
                    <p> <strong>Stato: </strong> <%= o.getStatoOrdine().toString() %> </p>


                    <% Set<OrderItemBean> elementiOrdine = new OrderItemDAO().doRetrieveByOrderId(o.getOrdineId());
                        for (OrderItemBean item : elementiOrdine){
                            ProdottoBean prodotto = new ProdottoDAO().doRetrieveByKey(item.getProdottoId());
                    %>

                        <div>
                            <p><strong>Prodotto:</strong> <%= prodotto.getNome()%>, <strong>Prezzo: </strong> <%= item.getPrezzo()%> €, <strong> Quantità: </strong><%= item.getQuantita()%></p>
                        </div>


                    <% } %>

                </div>
            </div>
        <% }
        } else { %>
            <p>Nessun ordine trovato</p>
        <% }%>
    </div>
</body>
</html>
