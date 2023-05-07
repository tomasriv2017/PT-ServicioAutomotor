const expresiones = {
	patente: {
			opcion1: 		/^[a-zA-Z]{3}\d{3}$/ ,//Patente => 3 letras y 3 numeros .
			opcion2: 		 /^[a-zA-Z]{2}\d{3}[a-zA-Z]{2}$/,
	},
	modelo: /^[a-zA-Z\s]{1,20}$/ // Solor letras con un maximo de 20 caracteres
};

const campos = {
	patente: false,
    modelo: false
};

const formulario = document.getElementById("form");
const inputs = document.querySelectorAll("#form input");

const validarFormulario = (evento)=>{

    switch(evento.target.name){
        case "patente":
            validarCampo(expresiones.patente , evento.target , "patente");
            break;
        case "modelo":
            validarCampo(expresiones.modelo , evento.target , "modelo");
            break;   
    }
}

const validarCampo=(expresion, input, campo) =>{
	if(input.name === "patente"){
		if(expresion.opcion1.test(input.value) || expresion.opcion2.test(input.value)){
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
	else{
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
}
    



inputs.forEach( (input) =>{
    input.addEventListener("keyup",validarFormulario);
    input.addEventListener("blur",validarFormulario);
});



const validarCamposAgregados = () =>{
	for(let i=1; i<= (inputs.length -1); i++){
		switch(inputs[i].name){
        case "patente":
            validarCampo(expresiones.patente , inputs[i] , "patente");
            break;
        case "modelo":
            validarCampo(expresiones.modelo , inputs[i] , "modelo");
            break;     
		}
	}
}


if(inputs[2].value !== "" && inputs[3].value !== ""){
	document.addEventListener("DOMContentLoaded", validarCamposAgregados );	
}



formulario.addEventListener("submit", (e) =>{
 
    if(!campos.patente || !campos.modelo){
	
	    e.preventDefault();
        
        document.getElementById("form__msg-error").classList.add("form__msg-erro-activo");
        setTimeout( ()=>{
            document.getElementById("form__msg-error").classList.remove("form__msg-erro-activo");
        } , 5000); //serian 5000 milisegundos es decir 5 segundos para que desaparezca el msg de exito
    }
   
});