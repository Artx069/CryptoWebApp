let url = "http://localhost:8080/refreshedstats"
var getJSON = function(url, callback) {

    var xmlhttprequest = new XMLHttpRequest();
    xmlhttprequest.open('GET', url, true);
    xmlhttprequest.responseType = 'json';

    xmlhttprequest.onload = function() {

        var status = xmlhttprequest.status;

        if (status == 200) {
            callback(null, xmlhttprequest.response);
        } else {
            callback(status, xmlhttprequest.response);
        }
    };

    xmlhttprequest.send();
};


const runAsync = () => {
    setTimeout(() => {
        let v = runAsync()
        getJSON(url,  function(err, data) {

            if (err != null) {
                console.error(err);
            } else {
                for(let obj of data){
                    let currentPrice = document.getElementById(obj["name"] +"_currentPrice");
                    let marketCap = document.getElementById(obj["name"] +"_marketCap");

                    currentPrice.innerText =  obj["current_price"];
                    marketCap.innerText = obj["market_cap"];
                }
            }
        });
        v;
    }, 5000)
}

runAsync();




