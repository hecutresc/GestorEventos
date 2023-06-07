let abrirMenu = document.querySelector("header > nav > div + div");
let cerrarMenu = document.querySelector("header > nav > div > a");

abrirMenu.addEventListener("click", openNav);
cerrarMenu.addEventListener("click", closeNav);


function openNav() {
    document.querySelector("header > nav > div").style.width = "250px";
    //document.querySelector("header > nav > div").style.zIndex = "1";
    if(document.getElementsByTagName("table").length > 0){
        document.getElementsByTagName("table")[0].style.zIndex = "-1"; // Agregado
    }
    
    document.getElementsByTagName("main")[0].style.zIndex = "-1"; // Cambiado
    document.body.style.backgroundColor = "rgba(0,0,0,0.4)";
}


function closeNav() {
    document.querySelector("header > nav > div").style.width = "0";

    setTimeout(function() {
        if(document.getElementsByTagName("table").length > 0){
            document.getElementsByTagName("table")[0].style.zIndex = "0";
        }
        
        document.getElementsByTagName("main")[0].style.zIndex = "0";
    }, 800);

    document.body.style.backgroundColor = "white";
}



