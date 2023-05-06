const expresiones = {
	dni: /^\d{8}$/, // 8 numeros para un DNI .
	fecha: /[0-9]{4}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])/, //yyyy-mm-dd
	cantDias: /^[1-9]/, // solo numeros
	dominio: /^[a-zA-Z]{3}\d{3}$/ ,//Patente => 3 letras y 3 numeros .
};

const campos = {
	dni:false,
	fecha: false,
	cantDias: false,
	dominio: false
};

const formulario = document.getElementById("form");
const inputs = document.querySelectorAll("#form input");

const validarFormulario = (evento)=>{

    switch(evento.target.name){
	    case "fecha":
		    validarCampo(expresiones.fecha , evento.target , "fecha");
		    break; 
        case "cantDias":
            validarCampo(expresiones.cantDias , evento.target , "cantDias");
            break;   
        case "dni":
            validarCampo(expresiones.dni , evento.target , "dni");
            break;   
       	case "dominio":
            validarCampo(expresiones.dominio , evento.target , "dominio");
            break;             
    }       
}

const validarCampo=(expresion, input, campo) =>{
    if(expresion.test(input.value) ) {
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
	    case "fecha":
		    validarCampo(expresiones.fecha , inputs[i] , "fecha");
		    break; 
        case "cantDias":
            validarCampo(expresiones.cantDias , inputs[i] , "cantDias");
            break;              
		}
	}
}

inputs.forEach( (input) =>{
    input.addEventListener("keyup",validarFormulario);
    input.addEventListener("blur",validarFormulario);
});


if(inputs[2].value !== "" && inputs[3].value !== ""){
	document.addEventListener("DOMContentLoaded", validarCamposAgregados );	
}


formulario.addEventListener("submit", (e) =>{
 
    if(!campos.cantDias || !campos.fecha || !campos.dni || !campos.dominio){
	
	    e.preventDefault();
        
        document.getElementById("form__msg-error").classList.add("form__msg-erro-activo");
        setTimeout( ()=>{
            document.getElementById("form__msg-error").classList.remove("form__msg-erro-activo");
        } , 5000); //serian 5000 milisegundos es decir 5 segundos para que desaparezca el msg de exito
    }
   
});