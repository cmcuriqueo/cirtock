function goBack() {
	window.history.go(-1);
}
function guardado(){
	alert("Guardado...");
}
function modificado() {
	alert("Modificado...");
}
function confirmar()
{
    if (confirm("Se perderan todos los nuevos datos\n¿desea continuar?")) {
        goBack();
    }
}
function eliminado(){
	alert("Eliminado...");
}

function is_cadena_vacia(cad){
	if(cad == ""){ alert("cadena vacia"); return true;}
		return false;
}
function eliminar(){
	if (confirm("Se eliminara el elemento")) {
		eliminado();
	}
}

function enviar_contrasenia(){
	alert("Contraseña enviada al email");
}