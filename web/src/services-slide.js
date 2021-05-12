var service1 = {
    superheadline: "Obtenga un servicio completo",
    headline: "Outsourcing Contable",
    paragraph1: "Llevar la contabilidad completa de su empresa ya no será un desafío imposible. Conozca en detalle nuestro servicio asesor.",
    paragraph2: "Contamos con sistemas eficaces y el capital humano capacitado para ser tu partner en el manejo de la información contable mes a mes.",
    listitem1:"Estrategia de Negocios",
    listitem2:"Cálculo y Declaración de IVA",
    listitem3:"Registros de Compra y Venta",
    listitem4:"Conciliaciones y Consolidaciones",
    linked: "http://www.hsa-auditores.cl/outsourcing-contable/",

} 

var service2 = {
    superheadline: "Le ayudamos a cumplir con sus obligaciones tributarias",
    headline: "Asesoría Tributaria",
    paragraph1:"En HSA sabemos lo importante que es cumplir con las obligaciones tributarias que impone la normativa.",
    paragraph2:"Cumpla con sus obligaciones de manera segura y permanente con nuestro servicio de asesoría tributaria.",
    listitem1:"Cumplimiento del Régimen Tributario",
    listitem2:"Análisis y Normalización de la Situación Tributaria",
    listitem3:"Declaraciones Juradas de IVA Renta y Bienes Raíces",
    listitem4:"Cálculo y Declaración de Impuestos Mensuales y Adicionales",
    linked: "http://www.hsa-auditores.cl/asesoria-tributaria",
} 

var service3 = {
    superheadline: "Remuneraciones y Rol Privado",
    headline: "Cumplimiento Laboral",
    paragraph1: "Uno de los mayores incentivos en la actualidad, es que el capital humano de la compañía se sienta valorado y se le respeten sus derechos contractuales y previsionales.", 
    paragraph2: "Con nuestra asesoría, usted podrá cumplir de manera permanente con los documentos y planillas.",
    listitem1:"Administracion y Confección de Contratos y Anexos",
    listitem2:"Cálculo de Remuneraciones",
    listitem3:"Cálculo y Declaración Previsional",
    listitem4:"Gratificaciones, Vacaciones y Finiquito",
    linked: "http://www.hsa-auditores.cl/remuneraciones-y-rol-privado",
} 

var service4 = {
    superheadline: "Anticípese a los problemas de procesos internos",
    headline: "Auditoría",
    paragraph1: "Audite sus cuentas con profesionales expertos en la revisión y análisis de datos financieros, y reduzca el riesgo de caer en errores o fraudes.",
    paragraph2: "Contamos con profesionales preparados en la detección de errores y el análisis estandarizado de las normas contables.",
    listitem1:"Implementación de Normas Generales e Internacionales",
    listitem2:"Apego a Criterios de Realidad y Razonabilidad",
    listitem3:"Eficiencia y Calidad de los Procesos Internos",
    listitem4:"Revisión de Estados Financieros y Sistemas Anti-Fraude",
    linked: "http://www.hsa-auditores.cl/auditoria",
} 

var sourceHTML = $("#template-services").html();
var templateService = Handlebars.compile(sourceHTML);

$("#service1").click(function () {
    var services = templateService(service1);
    $("#content-service").html(services);
    $("#service1").addClass('bg-light');
    $("#service2").removeClass('bg-light');
    $("#service3").removeClass('bg-light');
    $("#service4").removeClass('bg-light');

});

$("#service2").click(function() {
    var services = templateService(service2);
    $("#content-service").html(services);
    $("#service2").addClass('bg-light');
    $("#service1").removeClass('bg-light');
    $("#service3").removeClass('bg-light');
    $("#service4").removeClass('bg-light');

});


$("#service3").click(function() {
    var services = templateService(service3);
    $("#content-service").html(services);
    $("#service3").addClass('bg-light');
    $("#service1").removeClass('bg-light');
    $("#service2").removeClass('bg-light');
    $("#service4").removeClass('bg-light');

});


$("#service4").click(function() {
    var services = templateService(service4);
    $("#content-service").html(services);
    $("#service4").addClass('bg-light');
    $("#service1").removeClass('bg-light');
    $("#service2").removeClass('bg-light');
    $("#service3").removeClass('bg-light');

});

var services = templateService(service1);
$("#content-service").html(services);