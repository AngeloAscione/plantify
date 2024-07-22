export function showNotification(message, type) {
    var notification = document.getElementById("notification");
    if (notification != null){
        // Imposta il contenuto della notifica con il messaggio passato come parametro.
        notification.innerHTML = message;
        // Imposta la classe dell'elemento per includere il tipo di notifica e la classe "show" per renderlo visibile.
        notification.className = type + " show";

        // Imposta un timeout per rimuovere la classe "show"
        setTimeout(function(){
            notification.className = notification.className.replace("show", "");
        }, 3000);
    }
}
