const expresiones = {
	nombreYApellido: /^[a-zA-ZÀ-ÿ\s]{1,40}$/, // Letras y espacios, pueden llevar acentos.
	correo: /^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$/,
	dni: /^\d{8}$/ // 8 numeros.
};

const campos = {
	nombre: false,
    apellido: false,
	email:false,
	dni: false
};

const formulario = document.getElementById("form");
const inputs = document.querySelectorAll("#form input");

const validarFormulario = (evento)=>{

    switch(evento.target.name){
        case "apellido":
            validarCampo(expresiones.nombreYApellido , evento.target , "apellido");
            break;
        case "nombre":
            validarCampo(expresiones.nombreYApellido , evento.target , "nombre");
            break;
        case "dni":
            validarCampo(expresiones.dni , evento.target , "dni");
            break;  
        case "email":
            validarCampo(expresiones.correo , evento.target , "email");
            break;                 
    }
}

const validarCampo=(expresion, input, campo) =>{
    if(expresion.test(input.value)){
        document.getElementById(`grupo__${campo}`).classList.remove("form__grupo-incorrecto");
        document.getElementById(`grupo__${campo}`).classList.add("form__grupo-correcto");
        document.querySelector(`#grupo__${campo} i`).classList.remove("fa-times-circle");
        document.querySelector(`#grupo__${campo} i`).classList.add("fa-check-circle");

        document.querySelector(`#grupo__${campo} .form__error-input-msg`).classList.remove("form__error-input-msg-activo");
        campos[campo] = true;
    }
    else{
        document.getElementById(`grupo__${campo}`).classList.remove("form__grupo-correcto");
        document.getElementById(`grupo__${campo}`).classList.add("form__grupo-incorrecto");
        document.querySelector(`#grupo__${campo} i`).classList.remove("fa-check-circle");
        document.querySelector(`#grupo__${campo} i`).classList.add("fa-times-circle");

        document.querySelector(`#grupo__${campo} .form__error-input-msg`).classList.add("form__error-input-msg-activo");
        campos[campo] = false;
    }
}


const validarCamposAgregados = () =>{
	for(let i=1; i<= (inputs.length -1); i++){
		switch(inputs[i].name){
			case "apellido":
            	validarCampo(expresiones.nombreYApellido , inputs[i] , "apellido");
            	break;
        	case "nombre":
            	validarCampo(expresiones.nombreYApellido , inputs[i] , "nombre");
            	break;
	        case "dni":
	            validarCampo(expresiones.dni , inputs[i] , "dni");
	            break; 
	        case "email":
	            validarCampo(expresiones.correo , inputs[i] , "email");
	            break;    
		}
	}
}


inputs.forEach( (input) =>{
    input.addEventListener("keyup",validarFormulario);
    input.addEventListener("blur",validarFormulario);
});


if(inputs[2].value !== "" &&  inputs[3].value !== "" && inputs[4].value !== "" && inputs[5].value !== "" ){
	document.addEventListener("DOMContentLoaded", validarCamposAgregados );	
}


formulario.addEventListener("submit", (e) =>{
 
    if(!campos.apellido || !campos.nombre || !campos.dni  || !campos.email){
	
	    e.preventDefault();
        
        document.getElementById("form__msg-error").classList.add("form__msg-erro-activo");
        setTimeout( ()=>{
            document.getElementById("form__msg-error").classList.remove("form__msg-erro-activo");
        } , 5000); //serian 5000 milisegundos es decir 5 segundos para que desaparezca el msg de exito
    }
   
});
