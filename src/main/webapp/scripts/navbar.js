document.addEventListener('click', function(event) {
    var isClickInside = document.querySelector('.menu-container').contains(event.target);
    if (!isClickInside) {
        document.querySelector('.dropdown-menu').style.display = 'none';
    }
});

document.querySelector('.menu-container').addEventListener('mouseover', function() {
    document.querySelector('.dropdown-menu').style.display = 'block';
});

document.querySelector('.menu-container').addEventListener('mouseleave', function() {
    document.querySelector('.dropdown-menu').style.display = 'none';
});
