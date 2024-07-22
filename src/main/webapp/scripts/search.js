document.addEventListener("DOMContentLoaded", function () {
    const searchInput = document.getElementsByClassName("search-input")[0];
    const resultBox = document.getElementsByClassName("resultBox")[0];
    const searchForm = document.getElementsByClassName('search-div')[0];

    searchInput.addEventListener('keyup', function(e){
        const xhr = new XMLHttpRequest();
        xhr.open("POST", "search", true);
        xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

        let userData = e.target.value;
        let data = "q=" + encodeURIComponent(userData);

        if(userData){
            xhr.onreadystatechange = function(){
                if (xhr.readyState === 4){
                    if (xhr.status === 200){
                        let response = JSON.parse(xhr.responseText);
                        if (response["status"] === "success") {
                            showSuggestions(response["products"]);
                        }
                    }
                }
            };

            xhr.send(data);

            resultBox.hidden = false;
            searchInput.classList.add("active"); //show autocomplete box
        } else {
            searchInput.classList.remove("active"); //hide autocomplete box
            resultBox.hidden = true;
        }
    });

    // Nascondi o mostra in base a dove si ha cliccato
    document.addEventListener('click', (event) => {
        if (!searchForm.contains(event.target)) {
            resultBox.hidden = true;
        }
    });

    searchInput.addEventListener('focusin', function () {
        resultBox.hidden = false;
    });

    function showSuggestions(obj){
        let listData;
        if(!Object.keys(obj).length){
            listData = '<li></li>';
        }else{
            listData = Object.keys(obj).map(item => `<a href="product?id=${obj[item]}"><li>${item}</li></a>`)
            listData = listData.join('');
        }
        resultBox.innerHTML = listData;
    }
});
