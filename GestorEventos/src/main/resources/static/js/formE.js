//Archivo de para el evento Form

//Variables
var ubicacionList = /*[[${listaUbicacionesDTO}]]*/[];
var cateringList = /*[[${listaCateringsDTO}]]*/[];
var decoradoList = /*[[${listaDecoradosDTO}]]*/[];
var ocioList = /*[[${listaOciosDTO}]]*/[];
var selectUbicacion = document.getElementById('selectUbicacion');
var selectCatering = document.getElementById('selectCatering');
var selectDecorado = document.getElementById('selectDecorado');
var selectOcio = document.getElementById('selectOcio');

window.addEventListener('load', function (e) {

    e.preventDefault();
    selectUbicacion.addEventListener('change', listados);


});

function listados() {
    //Variables
    var selectCatering = document.getElementById('selectCatering');
    var selectDecorado = document.getElementById('selectDecorado');
    var selectOcio = document.getElementById('selectOcio');
    var ubicacionList = /*[[${listaUbicacionesDTO}]]*/[];
    var cateringList = /*[[${listaCateringsDTO}]]*/[];
    var decoradoList = /*[[${listaDecoradosDTO}]]*/[];
    var ocioList = /*[[${listaOciosDTO}]]*/[];
    var ubi = null;
    var idUbi = selectUbicacion.value;
    console.log(ubicacionList.length);
    for (var i = 0; i < ubicacionList.length; i++) {
        if (ubicacionList[i].id == idUbi) {
            ubi = ubicacionList[i];
        }
    };

    console.log(ubi);
    console.log(ubi.direccionDTO.provincia);
    if (ubi.listaCateringsDTO.length == 0) {
        var filteredCaterings = [];

        for (var i = 0; i < cateringList.length; i++) {
            console.log(cateringList[i].empresaDTO.direccionDTO.provincia);
            if (cateringList[i].empresaDTO.direccionDTO.provincia == ubi.direccionDTO.provincia) {
                filteredCaterings.push(cateringList[i]);
            }
        };

        console.log(filteredCaterings.length);
        for (var i = 0; i < filteredCaterings.length; i++) {
            var option = document.createElement('option');
            option.value = filteredCaterings[i].id;
            option.text = filteredCaterings[i].menu;
            selectCatering.appendChild(option);
        }
    } else {

    }

    //Decorado Filtrado
    var filteredDecorados = [];
    console.log(ubi.direccionDTO.provincia);
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

    //Ocio Filtrado
    var filteredOcios = [];
    console.log(ubi.direccionDTO.provincia);
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
    }

}