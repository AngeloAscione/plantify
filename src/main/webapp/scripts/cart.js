
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
                showNotification("Prodotto aggiunto al carrello", "success")
            }
        }
    };
    xhr.send();
}

export function removeFromCart(id) {
    console.log("Rimuovo dal carrello item con id = " + id);
    let xhr = new XMLHttpRequest();
    xhr.open("GET", "cart?type=removeFromCart&prodottoId=" + id, true);
    xhr.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
    xhr.onreadystatechange = function (){
        if (xhr.readyState === 4 && hxr.status === 200) {
            console.log("Elemento rimosso");
        }
    }
    xhr.send();
}