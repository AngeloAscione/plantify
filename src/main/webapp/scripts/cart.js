function addToCart(id){
    let xhr = new XMLHttpRequest();
    xhr.open("POST", "cart", true);
    xhr.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
    xhr.onreadystatechange = function() {
        if (xhr.readyState === 4 && xhr.status === 200) {
            console.log(xhr.responseText);
        }
    };
    let data = JSON.stringify({
        "type": "addToCar",
        "prodottoId": id
    });
    xhr.send(data);
}