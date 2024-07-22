<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<nav class="navbar">
    <link rel="stylesheet" href="css/provastyle.css">
    <div class="logo">
        <img src="logo.png" alt="Plantify Logo">
    </div>
    <ul class="nav-links">
        <li><a href="#">Home</a></li>
        <li><a href="#">Products</a></li>
        <li><a href="#">Contacts</a></li>
        <li><a href="login.jsp">Sign in</a></li>
        <li><a href="register.jsp">Sign up</a></li>
    </ul>
    <div class="nav-icons">
        <a href="#"><img src="cart-icon.png" alt="Cart"></a>
        <a href="#"><img src="user-icon.png" alt="User"></a>
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
