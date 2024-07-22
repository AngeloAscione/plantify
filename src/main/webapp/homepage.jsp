<%@ page import="it.unisa.model.prodotto.ProdottoBean" %>
<%@ page import="java.util.List" %>
<%@ page import="it.unisa.model.prodotto.ProdottoDAO" %>
<%@ page import="java.util.Collection" %>
<%--<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>--%>
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
        <h2>Best Selling Plants</h2>
        <p>Easiest way to healthy life by buying your favorite plants</p>
        <button class="see-more">See more →</button>

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

<%--        <div class="plant-grid">--%>
<%--            <c:choose>--%>
<%--                <c:when test="${not empty products}">--%>
<%--                    <c:forEach var="product" items="${products}">--%>
<%--                        <div class="plant-item">--%>
<%--                            <img src="${product.foto}" alt="${product.nome}">--%>
<%--                            <p>${product.nome}</p>--%>
<%--                            <p>€ ${product.prezzo}</p>--%>
<%--                        </div>--%>
<%--                    </c:forEach>--%>
<%--                </c:when>--%>
<%--                <c:otherwise>--%>
<%--                    <p>Nessun prodotto trovato.</p>--%>
<%--                </c:otherwise>--%>
<%--            </c:choose>--%>
<%--        </div>--%>
<!--
        <div class="plant-grid">
            <div class="plant-item">
                <img src="images/natural-plant.jpg" alt="Natural Plant">
                <p>Natural Plants</p>
                <p>€ 1,400.00</p>
            </div>
            <div class="plant-item">
                <img src="images/artificial-plant1.jpg" alt="Artificial Plant">
                <p>Artificial Plants</p>
                <p>€ 900.00</p>
            </div>
        </div>-->
    </section>
</main>

<%@ include file="footer.jsp" %>
</body>
</html>
