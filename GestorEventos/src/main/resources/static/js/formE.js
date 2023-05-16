//Función para listar por provincia y mas
function listados(ubicacionList, cateringList, decoradoList, ocioList) {
    //Variables
    var selectCatering = document.getElementById('selectCatering');
    var selectDecorado = document.getElementById('selectDecorado');
    var selectOcio = document.getElementById('selectOcio');
    var ubi = null;
    var idUbi = selectUbicacion.value;

    //Divs contenedores de los select
    var divCatering = document.getElementById('divCatering');
    var divDecorado = document.getElementById('divDecorado');
    var divOcio = document.getElementById('divOcio');
    //Divs de la imagen y el precio
    var divFPCatering = document.getElementById('fotoPrecioCatering');
    var divFPDecorado = document.getElementById('fotoPrecioDecorado');
    var divFPOcio = document.getElementById('fotoPrecioOcio');
    var divFPUbicacion = document.getElementById('fotoPrecioUbicacion');
    //Quitamos los precios y las imagenes porque se ha cambiado la ubicación
    divCatering.style.display = "none";
    divDecorado.style.display = "none";
    divOcio.style.display = "none";
    divFPCatering.style.display = "none";
    divFPDecorado.style.display = "none";
    divFPOcio.style.display = "none";
    divFPUbicacion.style.display = "none";
    //Buscamos la ubicacion
    for (var i = 0; i < ubicacionList.length; i++) {
        if (ubicacionList[i].id == idUbi) {
            ubi = ubicacionList[i];
        }
    };

    //Comprobar que el usuario a elegido tipo de Evento y horario

    if (ubi) {
        //Ponemos la imagen y el precio de la ubicacion
        var i = document.getElementById('fotoUbicacion');
        var p = document.getElementById('precioUbicacion');
        p.placeholder = ubi.precio_hora;
        i.src = ubi.foto;
        divFPUbicacion.style.display = "block";
        calcular_precio_hora("p_ubi", ubi.precio_hora);
        //Ponemos la provincia de la ubicacion
        poner_Provincia(ubi.direccionDTO.provincia);
        //Creamos la lista para los caterings filtrados y los añdimos y vaciamos
        var filteredCaterings = [];
        vaciarSelect(selectCatering);
        for (var i = 0; i < cateringList.length; i++) {
            if (cateringList[i].empresaDTO.direccionDTO.provincia == ubi.direccionDTO.provincia) {
                filteredCaterings.push(cateringList[i]);
            }
        };


        for (var i = 0; i < filteredCaterings.length; i++) {
            var option = document.createElement('option');
            option.value = filteredCaterings[i].id;
            option.text = filteredCaterings[i].menu;
            selectCatering.appendChild(option);
        }
        //Mostramos el div que contiene el select del Catering y le añadimos el eventListener al select
        divCatering.style.display = "block";
        selectCatering.addEventListener('change', function () {
            if (this.value !== "0") {
                //Recogemos el objeto Decorado

                var c = buscarObjetoPorId(this.value, cateringList);
                var i = document.getElementById('fotoCatering');
                var p = document.getElementById('precioCatering');
                if (c) {
                    //Recogemos el elemento con la imagen y el input 
                    p.placeholder = c.precio;
                    i.src = c.foto;
                    //Añadimos el precio al precio final
                    ponerDinero('pCatering', c.precio);
                } else {
                    p.placeholder = "Error";
                    i.alt = "Error";
                }
                divFPCatering.style.display = "block";
            } else {
                divFPCatering.style.display = "none";
            }
        });

        //Decorado Filtrado
        var filteredDecorados = [];
        vaciarSelect(selectDecorado);
        for (var i = 0; i < decoradoList.length; i++) {
            console.log(decoradoList[i].empresaDTO.direccionDTO.provincia);
            if (decoradoList[i].empresaDTO.direccionDTO.provincia == ubi.direccionDTO.provincia) {
                filteredDecorados.push(decoradoList[i]);
            }
        };

        for (var i = 0; i < filteredDecorados.length; i++) {
            var option = document.createElement('option');
            option.value = filteredDecorados[i].id;
            option.text = filteredDecorados[i].nombre;
            selectDecorado.appendChild(option);
        }
        //Mostramos el div que contiene el select del Decorado
        divDecorado.style.display = "block";
        selectDecorado.addEventListener('change', function () {
            if (this.value !== "0") {
                //Recogemos el objeto Decorado
                console.log(this.value);
                var d = buscarObjetoPorId(this.value, decoradoList);
                console.log(d);
                var i = document.getElementById('fotoDecorado');
                var p = document.getElementById('precioDecorado');
                if (d) {
                    //Recogemos el elemento con la imagen y el input 
                    p.placeholder = d.precio;
                    i.src = d.foto;
                    //Añadimos el precio al precio final
                    ponerDinero('pDecorado', d.precio);
                } else {
                    p.placeholder = "Error";
                    i.alt = "Error";
                }
                divFPDecorado.style.display = "block";
            } else {
                divFPDecorado.style.display = "none";
            }
        });

        //Ocio Filtrado
        var filteredOcios = [];
        vaciarSelect(selectOcio);
        for (var i = 0; i < ocioList.length; i++) {
            console.log(ocioList[i].empresaDTO.direccionDTO.provincia);
            if (ocioList[i].empresaDTO.direccionDTO.provincia == ubi.direccionDTO.provincia) {
                filteredOcios.push(ocioList[i]);
            }
        };

        for (var i = 0; i < filteredOcios.length; i++) {
            var option = document.createElement('option');
            option.value = filteredOcios[i].id;
            option.text = filteredOcios[i].nombre;
            selectOcio.appendChild(option);
        };
        //Mostramos el div que contiene el select del Ocio
        divOcio.style.display = "block";
        selectOcio.addEventListener('change', function () {
            if (this.value !== "0") {
                //Recogemos el objeto Decorado
                var o = buscarObjetoPorId(this.value, ocioList);
                var i = document.getElementById('fotoOcio');
                var p = document.getElementById('precioOcio');
                if (o) {
                    //Recogemos el elemento con la imagen y el input 
                    p.placeholder = o.precio_hora;
                    i.src = o.foto;

                    //Llamamos a la función para que calcule el precio
                    calcular_precio_hora("pOcio", o.precio_hora);


                } else {
                    p.placeholder = "Error";
                    i.alt = "Error";
                }
                divFPOcio.style.display = "block";
            } else {
                divFPOcio.style.display = "none";
            }
        });
    } else {
        var divProv = document.getElementById('provinciaShow');
        var divUbi = document.getElementById('ubicacionDiv');
        divUbi.classList.replace("col-8", "col");
        divProv.style.display = "none";
        //Quitamos que se muestre los de la ubicación
        divFPUbicacion.style.display = "none";
    }

}

function vaciarSelect(select) {
    var options = select.getElementsByTagName("option");

    for (let i = 0; i < options.length; i++) {
        if (options[i].value !== "0") {
            select.removeChild(options[i]);
        }
    }
}

function buscarObjetoPorId(id, lista) {
    const objetoEncontrado = lista.find(objeto => objeto.id == id);
    return objetoEncontrado ? objetoEncontrado : null;
}

function insertarUbicaciones(listaUbicaciones) {
    var selectUbicacion = document.getElementById('selectUbicacion');
    //Bucle para insertar las ubicaciones
    for (var i = 0; i < listaUbicaciones.length; i++) {
        var option = document.createElement('option');
        option.value = listaUbicaciones[i].id;
        option.text = listaUbicaciones[i].nombre;
        selectUbicacion.appendChild(option);
    }
}


//Función para que según el tipo de evento muestre o no algunos parámetros
function horario(tipo) {
    //Variables
    var divFechas = document.getElementById('divFechas');
    var divFF = document.getElementById('divFF');
    var divHI = document.getElementById('divHI');
    var divNH = document.getElementById('divNH');
    //Mostrar el div que contiene los parámetros y ocultar los otros por si se han cambiado los que hay que mostrar
    divFF.style.display = "none";
    divHI.style.display = "none";
    divNH.style.display = "none";
    divFechas.style.display = "block";
    //Según el tipo
    switch (tipo) {
        case 'Boda':
            //Fecha Inicio y Horas
            divHI.style.display = "block";
            divNH.style.display = "block";
            break;
        case 'Congreso':
            //Fecha Inicio y Fecha Fin
            divFF.style.display = "block";
            break;
        case 'Ponencia':
            //Fecha Inicio y Horas
            divHI.style.display = "block";
            divNH.style.display = "block";
            break;
        case 'Comunion':
            //Fecha Inicio y Horas
            divHI.style.display = "block";
            divNH.style.display = "block";
            break;
        case 'Otros':
            //Fecha Inicio y Fecha Fin
            divFF.style.display = "block";
            break;
    }
}

function quitarSession(nSession) {
    if (sessionStorage.getItem(nSession)) {
        sessionStorage.removeItem(nSession);
    }
}

//Función para calcular el precio, solo sumará el resultado
function calcular_precio_hora(s_object, precio_hora) {
    //Recogemos el precio y lo sumamos
    if (sessionStorage.getItem(s_object)) {
        //Aqui recogemos el antiguo precio y lo multiplicamos por las hora que hayan y se lo restamos al total antes de poner el nuevo
        var p_anterior = parseFloat(sessionStorage.getItem(s_object));

        //Miramos si que tipo de evento es para saber como calcular las horas
        var tipoEvento = document.getElementById('selectTipo').value;
        if (tipoEvento == "Congreso" || tipoEvento == "Otros") {
            var fechaInicio = new Date(document.getElementById("fechaInicio").value);
            var fechaFin = new Date(document.getElementById("fechaFin").value);
            if (fechaInicio != "" && fechaFin != "") {
                if (fechaFin > fechaInicio) {
                    //Calcula en milisegundos
                    var diferencia = fechaFin - fechaInicio;

                    // Calcular el número de horas redondeando hacia abajo
                    var horas = Math.floor(diferencia / (1000 * 60 * 60));
                    var pAnterior_total = p_anterior * horas;
                    var precioNuevo = precio_hora * horas;
                    //Recogemos el precio final le quitamos
                    var precio = document.getElementById('precioEvento');
                    var p1 = ((parseFloat(precio.value)) - pAnterior_total) + precioNuevo;
                    //Seteamos el precio actualizado
                    precio.value = p1;
                    //Seteamos el sesión storage
                    sessionStorage.setItem(s_object, precio_hora);
                } else {
                    alert('La fecha final del Evento tiene que ser superior que la de inicio');
                }
            } else {
                //
                alert('Tienes que elegir las fechas para el evento');
            }

        } else {
            var horas = document.getElementById('nHoras').value;
            if (horas != 0) {
                var pAnterior_total = p_anterior * horas;
                var precioNuevo = precio_hora * horas;
                var precio = document.getElementById('precioEvento');
                var p1 = ((parseFloat(precio.value)) - pAnterior_total) + precioNuevo;
                //Seteamos el precio actualizado
                console.log(precio.value);
                console.log(p1);
                precio.value = p1;
                //Seteamos el sesión storage
                sessionStorage.setItem(s_object, precio_hora);
            } else {
                //
                alert('Tienes que elegir el número de horas');
            }

        }

    } else {
        //Tan solo calculamos el precio sin tener en cuenta el precio anterior ya que es el primero
        var tipoEvento = document.getElementById('selectTipo').value;
        if (tipoEvento == "Congreso" || tipoEvento == "Otros") {
            var fechaInicio = new Date(document.getElementById("fechaInicio").value);
            var fechaFin = new Date(document.getElementById("fechaFin").value);
            if (fechaInicio != "" && fechaFin != "") {
                if(fechaFin > fechaInicio){
                     //Calcula en milisegundos
            var diferencia = fechaFin - fechaInicio;
            // Calcular el número de horas redondeando hacia abajo
            var horas = Math.floor(diferencia / (1000 * 60 * 60));
            var precioNuevo = precio_hora * horas;
            //Recogemos el precio final le quitamos
            var precio = document.getElementById('precioEvento');
            var p1 = (parseFloat(precio.value)) + precioNuevo;
            //Seteamos el precio actualizado
            console.log(precio.value);
            console.log(p1);
            precio.value = p1;
            //Seteamos el sesión storage
            sessionStorage.setItem(s_object, precio_hora);
                }else{
                    alert('La Fecha de Finalización del Evento tiene que ser superior a la de Inicio');
                }
            }else{
                alert('Tienes que elegir las fechas de tu evento');
            }
           
        } else {
            var horas = document.getElementById('nHoras').value;
            if (horas != 0) {
                var precioNuevo = precio_hora * horas;
                var precio = document.getElementById('precioEvento');
                var p1 = (parseFloat(precio.value)) + precioNuevo;
                //Seteamos el precio actualizado
                precio.value = p1;
                //Seteamos el sesión storage
                sessionStorage.setItem(s_object, precio_hora);
            } else {
                //
                alert('Tienes que elegir las horas');
            }


        }
    }
}

function ponerDinero(s_object, precioN) {
    //Recogemos el precio y se lo sumamos al precio final
    if (sessionStorage.getItem(s_object)) {
        //Quitamos el precio del anterior producto
        var p_anterior = sessionStorage.getItem(s_object);
        var precio = document.getElementById('precioEvento');
        var p1 = (parseFloat(precio.value) - p_anterior) + precioN;
        precio.value = p1;
        sessionStorage.setItem(s_object, precioN);
    } else {
        var precio = document.getElementById('precioEvento');
        var p1 = (parseFloat(precio.value)) + precioN;
        precio.value = p1;
        sessionStorage.setItem(s_object, precioN);
    }
}

function cambio_Tipo_Evento(tipoEvento) {
    //Quitamos todos los session porque se ha cambiado el tipo de evento
    quitarSession('pOcio');
    quitarSession('p_ubi');
    quitarSession('pDecorado');
    quitarSession('pCatering');
    //Dejamos todos los select a value 0 para que así el usuario los elija de nuevo.
    var selectUbi = document.getElementById('selectUbicacion');
    selectUbi.value = "0";
    var selectCatering = document.getElementById('selectCatering');
    selectCatering.value = "0";
    var selectOcio = document.getElementById('selectOcio');
    selectOcio.value = "0";
    var selectDecorado = document.getElementById('selectDecorado');
    selectDecorado.value = "0";
    //Ocultamos todos los div que no se tengan que ver
    var divUbi = document.getElementById('fotoPrecioUbicacion');
    divUbi.style.display = "none";
    var divCatering = document.getElementById('fotoPrecioCatering');
    divCatering.style.display = "none";
    var divOcio = document.getElementById('fotoPrecioOcio');
    divOcio.style.display = "none";
    var divDecorado = document.getElementById('fotoPrecioDecorado');
    divDecorado.style.display = "none";
    //Dejamos el precio del evento a 0
    var precio = document.getElementById('precioEvento');
    precio.value = 0;
    //Escondemos la provincia
    var divProv = document.getElementById('provinciaShow');
    var divUbi = document.getElementById('ubicacionDiv');
    divUbi.classList.replace("col-8", "col");
    divProv.style.display = "none";
    //Quitamos todos los valores de las fechas/ horas
    var fInicio = document.getElementById('fechaInicio');
    var fFinal = document.getElementById('fechaFinal');
    var hInicio = document.getElementById('horaInicio');
    var nHoras = document.getElementById('nHoras');
    
    if(tipoEvento == "Congreso" || tipoEvento == "Otros"){
        if(fInicio){
            fInicio.value = "";
        }
        
        if(fFinal){
            fFinal.value = "";
        }
        
    }else{
        if(fInicio){
            fInicio.value = "";
        }
        if(hInicio){
            hInicio.value = "";
        }
        
        if(nHoras){
           nHoras.selectedIndex = 0;
        }
        
    }
    
    

}

function poner_Provincia(provincia) {
    var provInput = document.getElementById('provincia');
    var divProv = document.getElementById('provinciaShow');
    var divUbi = document.getElementById('ubicacionDiv');
    provInput.value = provincia;
    divUbi.classList.replace("col", "col-8");
    divProv.style.display = "block";

}