
window.addEventListener('load',function(){
    //Se recoge el boton de envio
    var btnEnvio = document.getElementById('btnEnviar');
    btnEnvio.addEventListener('click', function (e) {
      e.preventDefault();
      antesEnvio();
    });
})
//funcion 
function antesEnvio(){
    //Comprobamos que esten confirmados
    var email = document.getElementById('email');
    var email2 = document.getElementById('email2');
    var password = document.getElementById('password');
    var password2 = document.getElementById('password2');

    if (email.value == email2.value) {
      if (password.value == password2.value) {
        console.log('Hola ha entrado');
        document.querySelector("form").submit();
      } else {
        alert('¡Las contraseñas no coinciden!');
        password2.focus();
      }
    } else {
      alert('¡Los emails no coinciden!');
      email2.focus();
    }
}