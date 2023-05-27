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
        var res = calcular_precio_hora("p_ubi", ubi.precio_hora);
        console.log(res);
        if (res) {
            //Ponemos la imagen y el precio de la ubicacion
            var i = document.getElementById('fotoUbicacion');
            var p = document.getElementById('precioUbicacion');
            p.placeholder = ubi.precio_hora;
            i.src = ubi.foto;
            divFPUbicacion.style.display = "block";
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
                    //Quitamos el dinero
                    quitarDinero('pCatering');
                    //Quitamos el div
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
                    var d = buscarObjetoPorId(this.value, decoradoList);
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
                    //Quitamos el dinero
                    quitarDinero('pDecorado');
                    //Quitamos la vista
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
                        var res2 = calcular_precio_hora("pOcio", o.precio_hora);

                        if (res2) {
                            divFPOcio.style.display = "block";

                        } else {
                            selectOcio.value = 0;
                        }

                    } else {
                        p.placeholder = "Error";
                        i.alt = "Error";
                        divFPOcio.style.display = "block";
                    }

                } else {
                    //Quitamos el precio del Ocibicaco
                    quitar_precio_hora('pOcio');
                    //No se enseña el div
                    divFPOcio.style.display = "none";
                }
            });
        } else {
            selectUbicacion.value = 0;
        }
    } else {
        var divProv = document.getElementById('provinciaShow');
        var divUbi = document.getElementById('ubicacionDiv');
        divUbi.classList.replace("col-8", "col");
        divProv.style.display = "none";
        //Quitamos que se muestre los datos de la ubicación
        divFPUbicacion.style.display = "none";
        //Quitamos el dinero y lo ponemos a 0.
        quitarSession('pOcio');
        quitarSession('p_ubi');
        quitarSession('pDecorado');
        quitarSession('pCatering');
        quitarSession('horas');
        var precio = document.getElementById('precioEvento');
        precio.value = 0;
        this.value = "";
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
function horario(tipo, ubicacionList, ocioList) {
    //Variables
    var divFechas = document.getElementById('divFechas');
    var divFF = document.getElementById('divFF');
    var divHI = document.getElementById('divHI');
    var divNH = document.getElementById('divNH');
    //Divs contenedores de los select
    var divCatering = document.getElementById('divCatering');
    var divDecorado = document.getElementById('divDecorado');
    var divOcio = document.getElementById('divOcio');
    //Divs de la imagen y el precio
    var divFPCatering = document.getElementById('fotoPrecioCatering');
    var divFPDecorado = document.getElementById('fotoPrecioDecorado');
    var divFPOcio = document.getElementById('fotoPrecioOcio');
    var divFPUbicacion = document.getElementById('fotoPrecioUbicacion');
    //
    var selectUbi = document.getElementById('selectUbicacion');
    selectUbi.value = "";
    //Quitamos los precios y las imagenes porque se ha cambiado la ubicación
    divCatering.style.display = "none";
    divDecorado.style.display = "none";
    divOcio.style.display = "none";
    divFPCatering.style.display = "none";
    divFPDecorado.style.display = "none";
    divFPOcio.style.display = "none";
    divFPUbicacion.style.display = "none";
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
            var h = document.getElementById('nHoras');
            h.addEventListener('change', function () {
                fHoras(h, ubicacionList, ocioList);
            });
            break;
        case 'Congreso':
            //Fecha Inicio y Fecha Fin
            divFF.style.display = "block";
            var ff = document.getElementById('fechaFin');
            ff.addEventListener('change', function () {
                ffinal(ff, ubicacionList, ocioList);
            });
            break;
        case 'Ponencia':
            //Fecha Inicio y Horas
            divHI.style.display = "block";
            divNH.style.display = "block";
            var h = document.getElementById('nHoras');
            h.addEventListener('change', function () {
                fHoras(h, ubicacionList, ocioList);
            });
            break;
        case 'Comunion':
            //Fecha Inicio y Horas
            divHI.style.display = "block";
            divNH.style.display = "block";
            var h = document.getElementById('nHoras');
            h.addEventListener('change', function () {
                fHoras(h, ubicacionList, ocioList);
            });
            break;
        case 'Otros':
            //Fecha Inicio y Fecha Fin
            divFF.style.display = "block";
            var ff = document.getElementById('fechaFin');
            ff.addEventListener('change', function () {
                ffinal(ff, ubicacionList, ocioList);
            });
            break;
    }
}

function ffinal(element, ubicacionList, ocioList) {
    var fi = document.getElementById('fechaInicio');
    if (fi.value != "") {
        if (new Date(element.value) > new Date() && new Date(element.value) > new Date(fi.value)) {
            //Calcula en milisegundos
            var diferencia = new Date(element.value) - new Date(fi.value);
            // Calcular el número de horas redondeando hacia abajo
            var horasNuevas = Math.floor(diferencia / (1000 * 60 * 60));
            console.log(horasNuevas);
            var horasAntiguas = sessionStorage.getItem('horas');
            sessionStorage.setItem('horas', horasNuevas);
            //Recogemos el precio total antes de ser actualizado
            var precio = document.getElementById('precioEvento');
            //Comprobamos que almenos esta escogida la ubicación
            var ubi = document.getElementById('selectUbicacion');
            if (ubi.value != 0) {
                //Cambiamos el precio de la ubicación
                var ubicacion = buscarObjetoPorId(ubi.value, ubicacionList);
                var precioHorasAnterior = horasAntiguas * (ubicacion.precio_hora);
                var p_a = parseFloat(precio.value);
                var precioQ = p_a - precioHorasAnterior;
                var nPrecio = horasNuevas * ubicacion.precio_hora;
                var pTotal = precioQ + nPrecio;
                precio.value = Number(pTotal.toFixed(2));
                //Miramos si ha contratado también ocio
                var oci = document.getElementById('selectOcio');
                if (oci.value != 0) {
                    var ocio = buscarObjetoPorId(oci.value, ocioList);
                    var precioHorasAnterior = horasAntiguas * (ocio.precio_hora);
                    var p_a = parseFloat(precio.value);
                    var precioQ = p_a - precioHorasAnterior;
                    var nPrecio = horasNuevas * ocio.precio_hora;
                    var pTotal = precioQ + nPrecio;
                    precio.value = Number(pTotal.toFixed(2));
                }

            }


        } else {

            element.value = "";
            alert('La fecha final no puede ser menor que la fecha actual o menor que la fecha de Inicio');
        }
    } else {

        element.value = "";
        alert('Elige primero la fecha de Inicio');
    }
};

function quitarSession(nSession) {
    if (sessionStorage.getItem(nSession)) {
        sessionStorage.removeItem(nSession);
    }
}

//Función para quitar el precio x hora de los caterings y las ubicaciones
function quitar_precio_hora(s_object) {
    if (sessionStorage.getItem(s_object)) {
        var p_anterior = sessionStorage.getItem(s_object);
        var precio = document.getElementById('precioEvento');
        //Según el tipo de evento calculamos como sacar las horas
        var tipoEvento = document.getElementById('selectTipo');
        if (tipoEvento.value == "Congreso" || tipoEvento.value == "Otros") {
            var fechaInicio = new Date(document.getElementById("fechaInicio").value);
            var fechaFin = new Date(document.getElementById("fechaFin").value);
            var diferencia = fechaFin - fechaInicio;
            // Calcular el número de horas redondeando hacia abajo
            var horas = Math.floor(diferencia / (1000 * 60 * 60));
            var pAnterior_total = p_anterior * horas;
            var p1 = ((parseFloat(precio.value)) - pAnterior_total);
            precio.value = Number(p1.toFixed(2));
            //Seteamos el sesión storage
            sessionStorage.removeItem(s_object);
        } else {
            var horas = document.getElementById('nHoras').value;
            var pAnterior_total = p_anterior * horas;
            var precio = document.getElementById('precioEvento');
            var p1 = ((parseFloat(precio.value)) - pAnterior_total);
            precio.value = Number(p1.toFixed(2));
            //Seteamos el sesión storage
            sessionStorage.removeItem(s_object);
        }
    }
}

//Función para calcular el precio, solo sumará el resultado
function calcular_precio_hora(s_object, precio_hora) {
    var tipoEvento = document.getElementById('selectTipo');
    if (tipoEvento != 0) {
        //Recogemos el precio y lo sumamos
        if (sessionStorage.getItem(s_object)) {
            //Aqui recogemos el antiguo precio y lo multiplicamos por las hora que hayan y se lo restamos al total antes de poner el nuevo
            var p_anterior = parseFloat(sessionStorage.getItem(s_object));

            //Miramos si que tipo de evento es para saber como calcular las horas
            var tipoEvento = document.getElementById('selectTipo').value;
            if (tipoEvento == "Congreso" || tipoEvento == "Otros") {
                var fechaInicio = new Date(document.getElementById("fechaInicio").value);
                var fechaFin = new Date(document.getElementById("fechaFin").value);
                if (fechaInicio && fechaFin) {
                    if (fechaFin > fechaInicio) {
                        //Calcula en milisegundos
                        var diferencia = fechaFin - fechaInicio;

                        // Calcular el número de horas redondeando hacia abajo
                        var horas = Math.floor(diferencia / (1000 * 60 * 60));
                        sessionStorage.setItem('horas', horas);
                        var pAnterior_total = p_anterior * horas;
                        var precioNuevo = precio_hora * horas;
                        //Recogemos el precio final le quitamos
                        var precio = document.getElementById('precioEvento');
                        var p1 = ((parseFloat(precio.value)) - pAnterior_total) + precioNuevo;
                        //Seteamos el precio actualizado
                        precio.value = Number(p1.toFixed(2));
                        //Seteamos el sesión storage
                        sessionStorage.setItem(s_object, precio_hora);
                        return true;
                    } else {
                        alert('La fecha final del Evento tiene que ser superior que la de inicio');
                        return false;
                    }
                } else {
                    //
                    alert('Tienes que elegir las fechas para el evento');
                    return false;
                }

            } else {
                var horas = document.getElementById('nHoras').value;
                if (horas != 0) {
                    sessionStorage.setItem('horas', horas);
                    var pAnterior_total = p_anterior * horas;
                    var precioNuevo = precio_hora * horas;
                    var precio = document.getElementById('precioEvento');
                    var p1 = ((parseFloat(precio.value)) - pAnterior_total) + precioNuevo;
                    //Seteamos el precio actualizado
                    precio.value = Number(p1.toFixed(2));
                    //Seteamos el sesión storage
                    sessionStorage.setItem(s_object, precio_hora);
                    return true;
                } else {
                    //
                    alert('Tienes que elegir el número de horas');
                    return false;
                }

            }

        } else {
            //Tan solo calculamos el precio sin tener en cuenta el precio anterior ya que es el primero
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
                        sessionStorage.setItem('horas', horas);
                        var precioNuevo = precio_hora * horas;
                        //Recogemos el precio final le quitamos
                        var precio = document.getElementById('precioEvento');
                        var p1 = (parseFloat(precio.value)) + precioNuevo;
                        //Seteamos el precio actualizado
                        console.log(precio.value);
                        console.log(p1);
                        precio.value = Number(p1.toFixed(2));
                        //Seteamos el sesión storage
                        sessionStorage.setItem(s_object, precio_hora);
                        return true;
                    } else {
                        alert('La Fecha de Finalización del Evento tiene que ser superior a la de Inicio');
                        return false;
                    }
                } else {
                    alert('Tienes que elegir las fechas de tu evento');
                    return false;
                }

            } else {
                var horas = document.getElementById('nHoras').value;
                if (horas != 0) {
                    var precioNuevo = precio_hora * horas;
                    sessionStorage.setItem('horas', horas);
                    var precio = document.getElementById('precioEvento');
                    var p1 = (parseFloat(precio.value)) + precioNuevo;
                    //Seteamos el precio actualizado
                    precio.value = Number(p1.toFixed(2));
                    //Seteamos el sesión storage
                    sessionStorage.setItem(s_object, precio_hora);
                    return true;
                } else {
                    //
                    alert('Tienes que elegir las horas');
                    return false;
                }


            }
        }
    } else {
        alert('Elige el tipo de evento que quieres hacer');
        return false;
    }
}

function ponerDinero(s_object, precioN) {
    //Recogemos el precio y se lo sumamos al precio final
    if (sessionStorage.getItem(s_object)) {
        //Quitamos el precio del anterior producto
        var p_anterior = sessionStorage.getItem(s_object);
        var precio = document.getElementById('precioEvento');
        var p1 = (parseFloat(precio.value) - p_anterior) + precioN;
        precio.value = Number(p1.toFixed(2));
        sessionStorage.setItem(s_object, precioN);
    } else {
        var precio = document.getElementById('precioEvento');
        var p1 = (parseFloat(precio.value)) + precioN;
        precio.value = Number(p1.toFixed(2));
        sessionStorage.setItem(s_object, precioN);
    }
}

function quitarDinero(s_object) {
    if (sessionStorage.getItem(s_object)) {
        //Quitamos el precio del anterior producto
        var p_anterior = sessionStorage.getItem(s_object);
        var precio = document.getElementById('precioEvento');
        var p1 = (parseFloat(precio.value) - p_anterior);
        precio.value = Number(p1.toFixed(2));
        sessionStorage.removeItem(s_object);
    }
}

function cambio_Tipo_Evento(tipoEvento, ubicacionList, ocioList) {
    //Quitamos todos los session porque se ha cambiado el tipo de evento
    quitarSession('pOcio');
    quitarSession('p_ubi');
    quitarSession('pDecorado');
    quitarSession('pCatering');
    quitarSession('horas');
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

    if (tipoEvento == "Congreso" || tipoEvento == "Otros") {

        if (fInicio) {
            fInicio.value = "";
            fInicio.addEventListener('change', function () {
                if (new Date(this.value) > new Date()) {
                    //Comprobamos que la fecha final esta escogida
                    var ff = document.getElementById('fechaFinal');
                    if (ff) {
                        //Calcula en milisegundos
                        var diferencia = new Date(element.value) - new Date(fi.value);
                        // Calcular el número de horas redondeando hacia abajo
                        var horasNuevas = Math.floor(diferencia / (1000 * 60 * 60));
                        var horasAntiguas = sessionStorage.getItem('horas');
                        sessionStorage.setItem('horas', horasNuevas);
                        //Recogemos el precio total antes de ser actualizado
                        var precio = document.getElementById('precioEvento');
                        //Comprobamos que almenos esta escogida la ubicación
                        var ubi = document.getElementById('selectUbicacion');
                        if (ubi.value != 0) {
                            //Cambiamos el precio de la ubicación
                            var ubicacion = buscarObjetoPorId(ubi.value, ubicacionList);
                            var precioHorasAnterior = horasAntiguas * (ubicacion.precio_hora);
                            var p_a = parseFloat(precio.value);
                            var precioQ = p_a - precioHorasAnterior;
                            var nPrecio = horasNuevas * ubicacion.precio_hora;
                            var pTotal = precioQ + nPrecio;
                            precio.value = Number(pTotal.toFixed(2));
                            //Miramos si ha contratado también ocio
                            var oci = document.getElementById('selectOcio');
                            if (oci.value != 0) {
                                var ocio = buscarObjetoPorId(oci.value, ocioList);
                                var precioHorasAnterior = horasAntiguas * (ocio.precio_hora);
                                var p_a = parseFloat(precio.value);
                                var precioQ = p_a - precioHorasAnterior;
                                var nPrecio = horasNuevas * ocio.precio_hora;
                                var pTotal = precioQ + nPrecio;
                                precio.value = Number(pTotal.toFixed(2));
                            }

                        }

                    }
                } else {
                    fInicio.value = "";
                    alert('Tienes que introducir una fecha válida(Superior a la actual)');
                }
            });
        }



        if (fFinal) {
            fFinal.value = "";
        }

    } else {
        if (fInicio) {
            fInicio.value = "";
            fInicio.addEventListener('change', function () {
                if (new Date(this.value) < new Date()) {
                    fInicio.value = "";
                    alert('Tienes que introducir una fecha válida(Superior a la actual)');
                }
            });
        }
        if (hInicio) {
            hInicio.value = "";
        }

        if (nHoras) {
            nHoras.selectedIndex = 0;
        }

    }



}

function fHoras(element, ubicacionList, ocioList) {
    if (sessionStorage.getItem('horas')) {
        var horasAntiguas = sessionStorage.getItem('horas');
        var horasNuevas = element.value;
        sessionStorage.setItem('horas', horasNuevas);
        //Recogemos el precio total antes de ser actualizado
        var precio = document.getElementById('precioEvento');
        //Comprobamos que almenos esta escogida la ubicación
        var ubi = document.getElementById('selectUbicacion');
        if (ubi.value != 0) {
            //Cambiamos el precio de la ubicación
            var ubicacion = buscarObjetoPorId(ubi.value, ubicacionList);
            var precioHorasAnterior = horasAntiguas * (ubicacion.precio_hora);
            var p_a = parseFloat(precio.value);
            var precioQ = p_a - precioHorasAnterior;
            var nPrecio = horasNuevas * ubicacion.precio_hora;
            var pTotal = precioQ + nPrecio;
            precio.value = Number(pTotal.toFixed(2));
            //Miramos si ha contratado también ocio
            var oci = document.getElementById('selectOcio');
            if (oci.value != 0) {
                var ocio = buscarObjetoPorId(oci.value, ocioList);
                var precioHorasAnterior = horasAntiguas * (ocio.precio_hora);
                var p_a = parseFloat(precio.value);
                var precioQ = p_a - precioHorasAnterior;
                var nPrecio = horasNuevas * ocio.precio_hora;
                var pTotal = precioQ + nPrecio;
                precio.value = Number(pTotal.toFixed(2));
            }

        }
    } else {
        sessionStorage.setItem('horas', element.value);
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

function edicion(eventoDTO, ubicacionList, cateringList, decoradoList, ocioList) {
    //Se nos pasan objetos y según el tipo de evento que sea tendremos que mostrar o no divs
    var divFechas = document.getElementById('divFechas');
    divFechas.style.display = "block";
    var fI = document.getElementById('fechaInicio');
    fI.addEventListener('change', function(){
        var fF = document.getElementById('fechaFin');
        if(new Date(this.value) > new Date()){
            
            if(eventoDTO.tipo == "Congreso" || eventoDTO.tipo == "Otros"){
                fF.value = "";
            }
        }else{
            alert('La fecha de Inicio tiene que ser superior a la actual');
            this.value = "";
            if(eventoDTO.tipo == "Congreso" || eventoDTO.tipo == "Otros"){
                fF.value = "";
            }
            
            
        }

    });
    if (eventoDTO.tipo == "Congreso" || eventoDTO.tipo == "Otros") {
        //Ponemos los valores de las horas a null
        var fechaInicio = new Date(document.getElementById("fechaInicio").value);
        var fechaFin = new Date(document.getElementById("fechaFin").value);
        //Calcula en milisegundos
        var diferencia = fechaFin - fechaInicio;
        // Calcular el número de horas redondeando hacia abajo
        var horas = Math.floor(diferencia / (1000 * 60 * 60));
        sessionStorage.setItem('horas', horas);
    } else {
        var horas = document.getElementById('nHoras').value;
        sessionStorage.setItem('horas', horas);
    }
    horario(eventoDTO.tipo, ubicacionList, ocioList);

    //Poner las listas de los añadidos según la provincia
    var ubicacionDTO = buscarObjetoPorId(eventoDTO.ubicacionDTO.id, ubicacionList);
    var selectUbicacion = document.getElementById("selectUbicacion");
    var divFPUbicacion = document.getElementById('fotoPrecioUbicacion');
    selectUbicacion.value = ubicacionDTO.id;

    //Mostrar los divs
    var iU = document.getElementById('fotoUbicacion');
    var pU = document.getElementById('precioUbicacion');
    pU.placeholder = ubicacionDTO.precio_hora;
    iU.src = ubicacionDTO.foto;
    divFPUbicacion.style.display = "block";
    //Poner listas de los objetos
    //Ponemos la provincia de la ubicacion
    poner_Provincia(ubicacionDTO.direccionDTO.provincia);

    //Creamos la lista para los caterings filtrados y los añdimos y vaciamos
    //Variables necesarias
    var divCatering = document.getElementById('divCatering');
    var divDecorado = document.getElementById('divDecorado');
    var divOcio = document.getElementById('divOcio');
    var divFPCatering = document.getElementById('fotoPrecioCatering');
    var divFPDecorado = document.getElementById('fotoPrecioDecorado');
    var divFPOcio = document.getElementById('fotoPrecioOcio');
    //Catering List
    var selectCatering = document.getElementById('selectCatering');

    var filteredCaterings = [];
    vaciarSelect(selectCatering);
    for (var i = 0; i < cateringList.length; i++) {
        if (cateringList[i].empresaDTO.direccionDTO.provincia == ubicacionDTO.direccionDTO.provincia) {
            filteredCaterings.push(cateringList[i]);
        }
    };
    for (var i = 0; i < filteredCaterings.length; i++) {
        var option = document.createElement('option');
        option.value = filteredCaterings[i].id;
        option.text = filteredCaterings[i].menu;
        selectCatering.appendChild(option);
    }
    selectCatering.addEventListener('change', function () {
        edicionCatering(this.value, cateringList, divFPCatering);
    });
    divCatering.style.display = "block";
    //DecoradoList
    var selectDecorado = document.getElementById('selectDecorado');

    var filteredDecorados = [];
    vaciarSelect(selectDecorado);
    for (var i = 0; i < decoradoList.length; i++) {
        console.log(decoradoList[i].empresaDTO.direccionDTO.provincia);
        if (decoradoList[i].empresaDTO.direccionDTO.provincia == ubicacionDTO.direccionDTO.provincia) {
            filteredDecorados.push(decoradoList[i]);
        }
    };

    for (var i = 0; i < filteredDecorados.length; i++) {
        var option = document.createElement('option');
        option.value = filteredDecorados[i].id;
        option.text = filteredDecorados[i].nombre;
        selectDecorado.appendChild(option);
    }
    selectDecorado.addEventListener('change', function () {
        edicionDecorado(this.value, decoradoList, divFPDecorado);
    });
    divDecorado.style.display = "block";
    //Ocio List
    //Ocio Filtrado
    var selectOcio = document.getElementById('selectOcio');

    var filteredOcios = [];
    vaciarSelect(selectOcio);
    for (var i = 0; i < ocioList.length; i++) {
        console.log(ocioList[i].empresaDTO.direccionDTO.provincia);
        if (ocioList[i].empresaDTO.direccionDTO.provincia == ubicacionDTO.direccionDTO.provincia) {
            filteredOcios.push(ocioList[i]);
        }
    };

    for (var i = 0; i < filteredOcios.length; i++) {
        var option = document.createElement('option');
        option.value = filteredOcios[i].id;
        option.text = filteredOcios[i].nombre;
        selectOcio.appendChild(option);
    };
    selectOcio.addEventListener('change', function () {
        edicionOcio(this.value, ocioList, divFPOcio, this);
    });
    divOcio.style.display = "block";
    //Poner los valores de cada atributo
    if (eventoDTO.cateringDTO.id != null) {
        //Buscar el objeto en las listas y poner todo
        var cateringDTO = buscarObjetoPorId(eventoDTO.cateringDTO.id, cateringList);
        selectCatering.value = cateringDTO.id;
        var iC = document.getElementById('fotoCatering');
        var pC = document.getElementById('precioCatering');
        //Poner los divs visibles 
        if (cateringDTO) {
            //Recogemos el elemento con la imagen y el input 
            pC.placeholder = cateringDTO.precio;
            iC.src = cateringDTO.foto;
            sessionStorage.setItem('pCatering', cateringDTO.precio);
        } else {
            pC.placeholder = "Error";
            iC.alt = "Error";
        }
        divFPCatering.style.display = "block";

    }

    if (eventoDTO.decoradoDTO.id != null) {
        //Buscar el objeto en las listas y poner todo
        var decoradoDTO = buscarObjetoPorId(eventoDTO.decoradoDTO.id, decoradoList);
        selectDecorado.value = decoradoDTO.id;
        var iD = document.getElementById('fotoDecorado');
        var pD = document.getElementById('precioDecorado');
        //Poner los divs visibles 
        if (decoradoDTO) {
            //Recogemos el elemento con la imagen y el input 
            pD.placeholder = decoradoDTO.precio;
            iD.src = decoradoDTO.foto;
            sessionStorage.setItem('pDecorado', decoradoDTO.precio);
        } else {
            pD.placeholder = "Error";
            iD.alt = "Error";
        }
        divFPDecorado.style.display = "block";
    }

    if (eventoDTO.ocioDTO.id != null) {
        var ocioDTO = buscarObjetoPorId(eventoDTO.ocioDTO.id, ocioList);
        selectOcio.value = ocioDTO.id;
        var iO = document.getElementById('fotoOcio');
        var pO = document.getElementById('precioOcio');
        if (ocioDTO) {
            //Recogemos el elemento con la imagen y el input 
            pO.placeholder = ocioDTO.precio_hora;
            iO.src = ocioDTO.foto;
            sessionStorage.setItem('pOcio', ocioDTO.precio_hora);
        } else {
            pO.placeholder = "Error";
            iO.alt = "Error";
        }
        divFPOcio.style.display = "block";
    }


}

function edicionCatering(id, cateringList, divFPCatering) {
    if (id !== "0") {
        //Recogemos el objeto Decorado

        var c = buscarObjetoPorId(id, cateringList);
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
        //Quitamos el dinero
        quitarDinero('pCatering');
        //Quitamos el div
        divFPCatering.style.display = "none";
    }
}

function edicionDecorado(id, decoradoList, divFPDecorado) {
    if (id !== "0") {
        //Recogemos el objeto Decorado
        var d = buscarObjetoPorId(id, decoradoList);
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
        //Quitamos el dinero
        quitarDinero('pDecorado');
        //Quitamos la vista
        divFPDecorado.style.display = "none";
    }
}

function edicionOcio(id, ocioList, divFPOcio, selectOcio) {
    if (id !== "0") {
        //Recogemos el objeto Decorado
        var o = buscarObjetoPorId(id, ocioList);
        var i = document.getElementById('fotoOcio');
        var p = document.getElementById('precioOcio');
        if (o) {
            //Recogemos el elemento con la imagen y el input 
            p.placeholder = o.precio_hora;
            i.src = o.foto;

            //Llamamos a la función para que calcule el precio
            var res2 = calcular_precio_hora("pOcio", o.precio_hora);

            if (res2) {
                divFPOcio.style.display = "block";

            } else {
                selectOcio.value = 0;
            }

        } else {
            p.placeholder = "Error";
            i.alt = "Error";
            divFPOcio.style.display = "block";
        }

    } else {
        //Quitamos el precio del Ocibicaco
        quitar_precio_hora('pOcio');
        //No se enseña el div
        divFPOcio.style.display = "none";
    }
}