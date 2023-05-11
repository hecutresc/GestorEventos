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


    if (ubi) {
        //Ponemos la imagen y el precio de la ubicacion
        var i = document.getElementById('fotoUbicacion');
        var p = document.getElementById('precioUbicacion');
        p.placeholder = ubi.precio_hora;
        i.src = ubi.foto;
        divFPUbicacion.style.display = "block";

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