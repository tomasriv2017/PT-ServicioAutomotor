package com.PruebaTecnica.ServicioAutomotor.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.PruebaTecnica.ServicioAutomotor.models.Cliente;
import com.PruebaTecnica.ServicioAutomotor.serviceImp.ClienteService;

@Controller
@RequestMapping("/client")
public class ClienteController {
	
	@Autowired
	private ClienteService clienteService;
	
	@PostMapping("/new")
    public ResponseEntity<?> crearCliente(@RequestBody Cliente clienteNuevo) throws Exception{
        java.util.Optional<Cliente> c = clienteService.traerByDni(clienteNuevo.getDni());
        if(!c.isPresent()) {
            return new ResponseEntity<Cliente>(clienteService.saveOrUpdate(clienteNuevo), HttpStatus.CREATED);
        }else {

            return new ResponseEntity<String>("El cliente creado ya existe", HttpStatus.OK);
        }

    }
	
	@PutMapping("/{id}")
	public ResponseEntity<?> actualizarCliente(@RequestBody Cliente clienteModificado, @PathVariable(name = "id") int id){
		if(!clienteService.traerById(id).isPresent()) {
			return new ResponseEntity<String>("El cliente a modificar no existe", HttpStatus.BAD_REQUEST);
		}else {
			clienteModificado.setIdCliente(id);
			return new ResponseEntity<Cliente>(clienteService.saveOrUpdate(clienteModificado), HttpStatus.OK);
		}
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> eliminarUsuario(@PathVariable("id")int id) throws Exception{
		Optional<Cliente> clienteBuscado = clienteService.traerById(id);
		if(!clienteBuscado.isPresent()) {
			return new ResponseEntity<String>("El cliente a eliminar no existe", HttpStatus.NOT_FOUND);
		}else {
			clienteService.delete(clienteBuscado.get().getIdCliente());
			return new ResponseEntity<String>("El cliente con el id="+ id+" fue eliminado exitosamente", HttpStatus.OK);
		}
	}
	
	
	@GetMapping("/")
	public ResponseEntity<?> listarClientes() {
		  return new ResponseEntity<List<Cliente>>(clienteService.listar(),  HttpStatus.OK);
	}
	

	@GetMapping("/{id}")
	public ResponseEntity<?> traerClienteById(@PathVariable(name="id") int id){		
		Optional<Cliente> clienteBuscado = clienteService.traerById(id);
		if(!clienteBuscado.isPresent()) {
			return new ResponseEntity<String>("El cliente buscado no existe", HttpStatus.NOT_FOUND);
		} else return new ResponseEntity<Cliente>(clienteBuscado.get(), HttpStatus.FOUND);
	}
	
	
	@GetMapping("/dni/{dni}")
	public ResponseEntity<?> traerClienteByDni(@PathVariable(name="dni") long dni){		
		Optional<Cliente> clienteBuscado = clienteService.traerByDni(dni);
		if(!clienteBuscado.isPresent()) {
			return new ResponseEntity<String>("El cliente buscado no existe", HttpStatus.NOT_FOUND);
		} else return new ResponseEntity<Cliente>(clienteBuscado.get(), HttpStatus.FOUND);
	}
	

	
}
