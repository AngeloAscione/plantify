<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<nav class="navbar">
    <link rel="stylesheet" href="css/provastyle.css">
    <div class="logo">
        <img src="images/logo.jpg" alt="Plantify Logo">
    </div>

    <ul class="nav-links">
        <li><a href="homepage.jsp">Home</a></li>
        <li><a href="products.jsp">Products</a></li>
    </ul>
    <div class="nav-icons">
        <a href="cart.jsp"><img src="images/cart.jpg" alt="Cart"></a>
       <!-- gestione da accedi o registrati-->
        <div class="toggle-buttons">
            <button class="btn sign-up"><a class="btn-text-up" href="register.jsp">Sign Up</a></button>
            <button class="btn log-in"><a class="btn-text-in" href="login.jsp">Log in</a></button>
        </div>

        <span class="divider"></span>
        <!--mi piace di più la barra rispetto al menu -->
        <div class="search-container">
            <input type="text" placeholder="Cosa stai cercando?" class="search-input">
            <button class="search-button">
                <img src="search-icon.png" alt="Search">
            </button>
        </div>
    </div>
</nav>
