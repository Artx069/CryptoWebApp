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
function postJSON(url) {

    var xmlhttprequest = new XMLHttpRequest();
    xmlhttprequest.open('POST', url, true);
    xmlhttprequest.responseType = 'json';

    xmlhttprequest.send();
};


const runAsync = () => {
    setTimeout(() => {
        let v = runAsync()
        getJSON(url,  function(err, data) {

            if (err != null) {
                console.error(err);
            }else if(data != null){
                console.log("updated")
                for(let obj of data){
                    if(document.getElementById(obj["name"])){
                        let currentPriceActualInnerText = document.getElementById(obj["name"] +"_currentPrice");
                        let marketCapActualInnerText = document.getElementById(obj["name"] +"_marketCap");

                        let currentPriceAcutalObject = obj["current_price"].slice(-1)[0];
                        let marketCapAcutalObject = obj["market_cap"].slice(-1)[0];
                        Number(currentPriceActualInnerText.innerText) > Number(currentPriceAcutalObject) ? currentPriceActualInnerText.style.color = "red" : Number(currentPriceActualInnerText.innerText) == Number(currentPriceAcutalObject) ? currentPriceActualInnerText.style.color = "black" : currentPriceActualInnerText.style.color = "green";
                        currentPriceActualInnerText.innerText =  currentPriceAcutalObject;
                        marketCapActualInnerText.innerText = marketCapAcutalObject;

                    }else{
                        appendCoinData(obj);
                    }

                }
            }
        });
        v;
    }, 5000)
}

runAsync();




const btnTest = document.getElementById("btnTest");

btnTest.addEventListener("click", function(){
    const testInput = document.getElementById("testInput")["value"];
    let urlPost = "http://localhost:8080/addCoinId?coinId=" + testInput;
    let urlGet = "http://localhost:8080/getRequestedCoinData?coinId=" + testInput;
    postJSON(urlPost);
    getJSON(urlGet,  function(err, data) {

        if (err != null) {
            console.error(err);
        }else if(data != null){
            console.log("updated")
            console.log(data)
            for(let obj of data){
                appendCoinData(obj);
            }
        }
    });
})

function appendCoinData(obj){
    console.log("appending")
    if(document.getElementById(obj["name"]) == null){
        let table = document.getElementById("coinListTable");

        let tr = document.createElement("tr");
        let tdCoinName = document.createElement("td");
        let tdCoinSymbol = document.createElement("td");
        let tdCoinCurrentPrice = document.createElement("td");
        let tdCoinMarketCap = document.createElement("td");;

        tdCoinName.id = obj["name"];
        tdCoinName.innerText = obj["name"];
        tdCoinSymbol.innerText = obj["symbol"];
        tdCoinCurrentPrice.innerText = Number(((obj["current_price"]).toString())).toPrecision();
        tdCoinCurrentPrice.id = obj["name"] +"_currentPrice";
        tdCoinMarketCap.innerText = obj["market_cap"];
        tdCoinMarketCap.id = obj["name"] +"_marketCap";

        tr.append(tdCoinName, tdCoinSymbol,tdCoinCurrentPrice,tdCoinMarketCap);
        table.append(tr)
    }

}



