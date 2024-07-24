function addToCart(id){
    console.log("Aggiungo al carrello item con id = " + id);
    let xhr = new XMLHttpRequest();
    xhr.open("GET", "cart?type=addToCart&prodottoId=" + id, true);
    xhr.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
    xhr.onreadystatechange = function() {
        if (xhr.readyState === 4 && xhr.status === 200) {
            console.log(xhr.responseText);
        }
    };
    xhr.send();
}