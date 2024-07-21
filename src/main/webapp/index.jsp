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

        <!--TODO rendere sensata questa parte di codice;
        <div class="plant-grid">
        <c:forEach var="plant" items="${plants}">
            <div class="plant-item">
                <img src="${plant.imageUrl}" alt="${plant.name}">
                <p>${plant.name}</p>
                <p>₱ ${plant.price}</p>
            </div>
        </c:forEach>
    </div>-->

        <div class="plant-grid">
            <div class="plant-item">
                <img src="images/natural-plant.jpg" alt="Natural Plant">
                <p>Natural Plants</p>
                <p>₱ 1,400.00</p>
            </div>
            <div class="plant-item">
                <img src="images/artificial-plant1.jpg" alt="Artificial Plant">
                <p>Artificial Plants</p>
                <p>₱ 900.00</p>
            </div>
            <div class="plant-item">
                <img src="images/artificial-plant2.jpg" alt="Artificial Plant">
                <p>Artificial Plants</p>
                <p>₱ 3,500.00</p>
            </div>
        </div>
    </section>
</main>

<%@ include file="footer.jsp" %>
</body>
</html>
