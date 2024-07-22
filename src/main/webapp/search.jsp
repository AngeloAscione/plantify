
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="search-container">
    <link rel="stylesheet" href="css/searchbar.css">
    <script src="${pageContext.request.contextPath}/scripts/search.js"></script>
    <form  class="search-form" onsubmit="return false;">
        <input class="search-input" type="search" placeholder="Cerca..." aria-label="Search">
        <button class="search-button">
            <i class="fas fa-search"></i>
        </button>
        <div class="resultBox">
        </div>
    </form>
</div>