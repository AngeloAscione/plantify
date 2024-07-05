<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <link rel="stylesheet" href="css/provastyle.css">
</head>
<body>
<div class="navbar">
    <ul>
        <li><img src="logo" alt="Plantify"></li>
        </li><a class="active" href="#home">Home</a></li>
        </li><a class="active" href="#Products">Products</a></li>
        </li><a class="active" href="#contact">Contact</a></li>
    </ul>
    <!-- search bar-->
    <div class="search-container">
        <form action="/action_page.php">
            <input type="text" placeholder="Search.." name="search">
            <button type="submit"><i class="fa fa-search"></i></button>
        </form>
    </div>
    <!--mettere il border al div-->
    <img src="carrello" alt="carrello">
    <img src="utente" alt="Plantify">
</div>

</body>
</html>
