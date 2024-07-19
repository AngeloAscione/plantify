<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="navbar.jsp" %>
<html>
<head>
    <title>Sezione Piante</title>
    <link rel="stylesheet" href="css/homepage.css">
</head>
<body>
<header>
    <div class="hero">
        <div class="hero-content">
            <h1>Compra le tue piante preferite</h1>
            <div class="search-bar">
                <input type="text" placeholder="Cosa stai cercando?">
                <button type="button">🔍</button>
            </div>
        </div>
    </div>
</header>
<main>
    <section class="best-selling">
        <h2>Best Selling Plants</h2>
        <p>Easiest way to healthy life by buying your favorite plants</p>
        <button class="see-more">See more →</button>
        <div class="plant-grid">
            <div class="plant-item">
                <img src="natural-plant.jpg" alt="Natural Plant">
                <p>Natural Plants</p>
                <p>₱ 1,400.00</p>
            </div>
            <div class="plant-item">
                <img src="artificial-plant1.jpg" alt="Artificial Plant">
                <p>Artificial Plants</p>
                <p>₱ 900.00</p>
            </div>
            <div class="plant-item">
                <img src="artificial-plant2.jpg" alt="Artificial Plant">
                <p>Artificial Plants</p>
                <p>₱ 3,500.00</p>
            </div>
        </div>
    </section>
</main>

<%@ include file="footer.jsp" %>
</body>
</html>
