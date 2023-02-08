let abrirMenu = document.querySelector("header > nav > div + div");
let cerrarMenu = document.querySelector("header > nav > div > a");

abrirMenu.addEventListener("click", openNav);
cerrarMenu.addEventListener("click", closeNav);


    /* Set the width of the side navigation to 250px and the left margin of the page content to 250px and add a black background color to body */
function openNav() {
    document.querySelector("header > nav > div").style.width = "250px";
    document.getElementsByTagName("main").style.marginLeft = "250px";
    document.body.style.backgroundColor = "rgba(0,0,0,0.4)";
  }
  
  /* Set the width of the side navigation to 0 and the left margin of the page content to 0, and the background color of body to white */
  function closeNav() {
    document.querySelector("header > nav > div").style.width = "0";
    document.getElementsByTagName("main").style.marginLeft = "0";
    document.body.style.backgroundColor = "white";
  } 


