
"use strict";


var paginaActual = 0;


$('#anterior').on('click', function () {
    paginaActual--;

    if( paginaActual < 1 ) {
        $('#anterior').prop('disabled', true);
    }else{
        $('#anterior').prop('disabled', false);
    }

    cargarArticuloPorPagina(paginaActual);
});

$('#reciente').on('click', function () {
    paginaActual--;
    cargarArticuloPorPagina(paginaActual);
});

function cargarArticuloPorPagina() {
//https://stackoverflow.com/questions/22037062/how-can-we-repeat-the-table-row-in-jquery-like-angularjs-does-with-ng-repeat/22039804

    if( paginaActual > 100 ) {
        $('#reciente').prop('disabled', true);
    }else{
        $('#reciente').prop('disabled', false);
    }

}


