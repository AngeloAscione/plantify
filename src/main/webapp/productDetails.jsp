<%@ page import="it.unisa.model.utente.UtenteBean" %><%--
  Created by IntelliJ IDEA.
  User: goldr
  Date: 22/07/2024
  Time: 18:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="navbar.jsp" %>

<html>
<head>
    <title>${productDetails.nome}</title>
    <link rel="stylesheet" href="css/productDetail.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
</head>
<body>


<div class="card-prodotto">
  <div class="image-prodotto">
    <img src="${productDetails.foto}" >
  </div>
  <div class="info-prodotto">
    <h1>${productDetails.nome}</h1>
    <p>${productDetails.descrizione}</p>
    <p>${productDetails.prezzo}</p>
  </div>
    <!--wishList-->
    <form action="${pageContext.request.contextPath}/AddWishList" method="post">
        <input type="hidden" name="prodottoId" value="${productDetails.prodottoId}">
        <button class="addToWishlistButton"><i class="far fa-heart"></i></button>
    </form>
    <script>
        document.querySelector('.addToWishlistButton').addEventListener('click', function() {
        this.classList.toggle('active');});
    </script>


    <%
        UtenteBean utenteBean;
        utenteBean = (UtenteBean) session.getAttribute("userInfo");
        if (utenteBean == null){
            utenteBean = new UtenteBean();
            utenteBean.setAdmin(false);
            utenteBean.setUtenteId(-1);
        }
    %>

    <% if (utenteBean.isAdmin()) { %>
    <form action="product" method="post">
        <input name="type" value="modifyProduct" hidden>
        <input name="prodottoId" value="${productDetails.prodottoId}" hidden>
        <input type="submit" value="Modifica prodotto">
    </form>
    <form action="product" method="post">
        <input name="type" value="deleteProduct" hidden>
        <input name="prodottoId" value="${productDetails.prodottoId}" hidden>
        <input name="utenteId" value="<% utenteBean.getUtenteId(); %>" hidden>
        <input type="submit" value="Elimina prodotto">
    </form>

    <% } %>
</div>
<br><br><br>

<%@ include file="footer.jsp" %>
</body>
</html>
