<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<nav class="navbar">
    <link rel="stylesheet" href="css/provastyle.css">
    <script src="scripts/navbar.js"></script>

    <div class="logo">
        <a href="index.html"><img src="images/logo.jpg" alt="Plantify Logo"></a>
    </div>

    <ul class="nav-links">
        <li><a href="homepage.jsp">Home</a></li>
        <li><a href="products.jsp">Products</a></li>
    </ul>
    <div class="nav-icons">
        <a href="cart?type=getCart"><img src="images/cart.jpg" alt="Cart"></a>
       <!-- gestione da accedi o registrati-->
        <%
            Boolean logged = (Boolean) request.getSession().getAttribute("logged");
            if (logged == null)
                logged = false;
        %>

        <% if (!logged){ %>
            <div class="toggle-buttons">
                <a class="btn-text-up" href="register.jsp"><button class="btn sign-up">Sign Up</button></a>
                <a class="btn-text-in" href="login.jsp" style="color: black !important;"><button class="btn log-in">Log in</button></a>
            </div>
        <% } else { %>
            <div class="menu-container">
                <button class="menu-button">
                    <img src="images/user.jpg" alt="Cart" class="menu-icon">
                </button>
                <div class="dropdown-menu">
                    <a href="orders">Ordini</a>
                    <a href="logout">Logout</a>
                </div>
            </div>
        <% } %>
    </div>
</nav>
