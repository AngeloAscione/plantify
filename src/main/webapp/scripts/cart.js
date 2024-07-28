
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
                showNotification("Oggetto rimosso dal carrello", "info")
                document.getElementsByClassName("cart-item "+ id)[0].remove();
                const totale = document.querySelector(".cart-summary p");
                totale.innerText = "Totale (" + json_obj.dimCarrello + (parseInt(json_obj.dimCarrello) == 1 ? " articolo" : " articoli") +")";
                updateTotalPrice();
                if (parseInt(json_obj.dimCarrello) == 0){
                    const buttonOrder = document.querySelector(".add-to-cart-button-link");
                    buttonOrder.setAttribute("href", "#");
                    buttonOrder.setAttribute("alt", "Aggiungi almeno un articolo al carrello per procedere all'ordine");
                }
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
    })
})

function updateQta(numberInput, prodottoId, newQta) {
    console.log("Tento aggiornamento qta per prodotto con id = " + prodottoId);
    if (newQta == 0) {
        return removeFromCart(prodottoId)
    } else if (newQta > 0) {
        document.querySelector(".add-to-cart-button-link").setAttribute("href", "checkout.jsp");
    }

    let xhr = new XMLHttpRequest();
    xhr.open("GET", "cart?type=updateQta&newQta="+newQta+"&prodottoId=" + prodottoId, true);
    xhr.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
    xhr.onreadystatechange = function (){
        if (xhr.readyState === 4 && xhr.status === 200) {
            var json_obj = JSON.parse(xhr.responseText);
            if (json_obj.status == "maxOverflow"){
                numberInput.value = json_obj.maxNum;
                showNotification("Quantità limitata, massimo acquistabile " + json_obj.maxNum, "info");
            }
            const totale = document.querySelector(".cart-summary p");
            totale.innerText = "Totale (" + json_obj.dimCarrello + (parseInt(json_obj.dimCarrello) == 1 ? " articolo" : " articoli") +")";
            updateTotalPrice();
        }
    }
    xhr.send();
}

export function updateTotalPrice() {
    console.log("Chiedo prezzo totale");
    let xhr = new XMLHttpRequest();
    xhr.open("GET", "cart?type=totalPrice", true);
    xhr.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
    xhr.onreadystatechange = function (){
        if (xhr.readyState === 4 && xhr.status === 200) {
            var json_obj = JSON.parse(xhr.responseText);
            if (json_obj.status == "success"){
                const totale = document.querySelector(".cart-summary p");
                totale.innerText += ": "+ json_obj.totalPrice + " €";
            }
        }
    }
    xhr.send();
}