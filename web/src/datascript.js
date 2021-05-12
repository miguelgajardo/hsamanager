//DATA
var data = {
    superheadline: "Ley 20.780, 20.899 y 21.210", 
    headline: "Expertos en la Reforma Tributaria.",
    paragraph: "Mantenemos a nuestros asesores preparados y actualizados con los cambios y requerimientos que la Ley exije.",
    listitem1: "Regímenes ProPyme y General",
    listitem2: "Impuestos a la Renta",
    listitem3: "Beneficios y Otras Disposiciones"
};

var data1 = {
    superheadline: "Experiencia en diversos rubros", 
    headline: "Asesoría para la industria, el comercio y los servicios.",
    paragraph: "Conocemos las características y exigencias de negocios de distinto rubro y tamaño.",
}

var data3 = {
    superheadline: "PyME", 
    headline: "Servicios focalizados en la PyME.",
    paragraph: "Enfocamos nuestro servicio en Pequeños y Medianos emprendimientos.",
    listitem1: "Contabilidad Completa y Registros Electrónicos",
    listitem2: "Cálculo y Declaración de Remuneraciones y Previsión",
    listitem3: "IVA, honorarios e impuestos adicionales"
}
//RECOGER EL HTML DEL ELEMENTO QUE SE INDICA #
var source = $("#carouseltemplate").html();
var source1 = $("#carouseltemplate1").html();
//LUEGO, COMPILO LA VARIABLE ANTERIOR (SOURCE) EN CADA TEMPLATE
var template = Handlebars.compile(source);
var template1 = Handlebars.compile(source1);

//REGISTRO DE LOS DATOS


$('#boton1').click(function () {
        var structure = template(data);
        $('#content').html(structure);
        $('.main').css({'background-image':'url(img/expertos_reforma.png'});
        $('#boton1').removeClass('btn btn-light');
        $('#boton1').addClass('btn btn-danger');

} ); 

$('#boton2').click(function () {
    var structure = template1(data1);
    $('#content').html(structure);
    $('.main').css({'background-image':'url(img/experiencia_rubros.png)'});
    $('#boton1').removeClass('btn btn-danger');
    $('#boton1').addClass('btn btn-light');

} ); 

$('#boton3').click(function () {
    var structure = template(data3);
    $('#content').html(structure);
    $('.main').css({'background-image':'url(img/enfocados_pyme.png)'});
    $('#boton1').removeClass('btn btn-danger');
    $('#boton1').addClass('btn btn-light');
} ); 


//INSERTAR LOS DATOS EN EL TEMPLATE Y PASARLOS A LA VARIABLE structure
var structure = template(data);

//REGISTRAR LOS DATOS DEL TEMPLATE AL HTML CON EL #CONTENT EN EL INDEX esta es la info por defecto, active en el html

$('#content').html(structure);