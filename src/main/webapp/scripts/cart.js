
import {showNotification} from "./notification.js";

export function addToCart(id){
    console.log("Aggiungo al carrello item con id = " + id);
    let xhr = new XMLHttpRequest();
    xhr.open("GET", "cart?type=addToCart&prodottoId=" + id, true);
    xhr.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
    xhr.onreadystatechange = function() {
        if (xhr.readyState === 4 && xhr.status === 200) {
            var json_obj = JSON.parse(xhr.responseText);
            if (json_obj.status == "success"){
                showNotification("Prodotto aggiunto al carrello", "success");
            }
        }
    }
    xhr.send();
}

export function removeFromCart(id) {
    console.log("Rimuovo dal carrello item con id = " + id);
    let xhr = new XMLHttpRequest();
    xhr.open("GET", "cart?type=removeFromCart&prodottoId=" + id, true);
    xhr.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
    xhr.onreadystatechange = function (){
        if (xhr.readyState === 4 && xhr.status === 200) {
            var json_obj = JSON.parse(xhr.responseText);
            if (json_obj.status == "success"){
                document.getElementsByClassName("cart-item "+ id)[0].remove()
            }
        }
    }
    xhr.send();
}

const qtaInputs = document.querySelectorAll(".item-quantity input");
qtaInputs.forEach(input => {
    console.log("Creo listner");
    input.addEventListener('change', function (e){
        const prodottoId = e.target.dataset.productId;
        const newQta = e.target.value;
        console.log(prodottoId);
        console.log(newQta);
        updateQta(e.target, prodottoId, newQta)
        updateTotalPrice();
    })
})

function updateQta(numberInput, prodottoId, newQta) {
    console.log("Tento aggiornamento qta per prodotto con id = " + prodottoId);
    if (newQta == 0) return removeFromCart(prodottoId)

    let xhr = new XMLHttpRequest();
    xhr.open("GET", "cart?type=updateQta&newQta="+newQta+"&prodottoId=" + prodottoId, true);
    xhr.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
    xhr.onreadystatechange = function (){
        if (xhr.readyState === 4 && xhr.status === 200) {
            var json_obj = JSON.parse(xhr.responseText);
            if (json_obj.status == "maxOverflow"){
                numberInput.value = json_obj.maxNum;
                showNotification("Quantità limitata, massimo acquistabile = " + json_obj.maxNum, "info");
            }
        }
    }
    xhr.send();
}

export function updateTotalPrice() {

}