const expresiones = {
	marca: /^[a-zA-Z\s]{1,40}$/, // Letras y espacios
};

const campos = {
	marca: false

};

const formulario = document.getElementById("form");
const inputs = document.querySelectorAll("#form input");

const validarFormulario = (evento)=>{

    switch(evento.target.name){
        case "marca":
            validarCampo(expresiones.marca , evento.target , "marca");
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
			case "marca":
            	validarCampo(expresiones.marca , inputs[i] , "marca");
            	break;   
		}
	}
}


inputs.forEach( (input) =>{
    input.addEventListener("keyup",validarFormulario);
    input.addEventListener("blur",validarFormulario);
});


if(inputs[2].value !== ""){
	document.addEventListener("DOMContentLoaded", validarCamposAgregados );	
}


formulario.addEventListener("submit", (e) =>{
 
    if(!campos.marca){
	
	    e.preventDefault();
        
        document.getElementById("form__msg-error").classList.add("form__msg-erro-activo");
        setTimeout( ()=>{
            document.getElementById("form__msg-error").classList.remove("form__msg-erro-activo");
        } , 5000); //serian 5000 milisegundos es decir 5 segundos para que desaparezca el msg de exito
    }
   
});
