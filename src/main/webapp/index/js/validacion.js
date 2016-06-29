
function comprobar() { 
         var nombre = document.login.nombre;  //al variable se asigna el campo nombre 
         var password = document.login.password; //al variable se asigna el campo password 
         var aviso = document.getElementById("aviso"); //se asigna atributo id = aviso del documento
         aviso.innerHTML = "" //inicializamos vacio el atributo de mensaje que se mostrara en la pantalla  
         var enviar = "si" 	//inicializamos el envio  confirmando con si
	
         if (nombre.value == "" || nombre.value.indexOf(" ") == 0) { //si esta vacio el nombre? entonces
            var texto = "Es obligatorio introducir el nombre.<br/>"  //variable con el mensaje
            aviso.innerHTML += texto //ahora el mensaje lo guardamos en el aviso donde se mostrara en la pantalla
            enviar = "no"			//enviar cambiamos con no
         } else if (password.value == "" || password.value.indexOf(" ") == 0 ) {
            var texto = "Es obligatorio introducir el password.<br/>"  
            aviso.innerHTML += texto
            enviar = "no"
         } else if (nombre.value == "admin" && password.value=="123") {  //si el nombre y password es correcto
			   alert("Bienvenido al sistema");								//mensaje de bienvenida
			}else{														//caso contrario
			   var texto = "usuario o contraseña incorrecto <br/>"			//error de usuario y el password
			   aviso.innerHTML += texto
				enviar="no"
			}
         if (enviar == "no") {
            return false
         }//si la variable enviar contiene no retorna faso
}



function restaurar() {											
         var aviso = document.getElementById("aviso");
         aviso.innerHTML = ""									//aca inicializamos en vacio
}


//funcion para validar los comentarios del catalogo

function validaComentario(){
         var texto = document.getElementById("comentario").value;
         
       if (texto==null || texto=="" || texto=="Escriba acá su comentario") {
          
           confirm("No ingresó ningún texto. Desea continuar?");

         }else{
            alert("Usted escribio " + texto);
         }

}

