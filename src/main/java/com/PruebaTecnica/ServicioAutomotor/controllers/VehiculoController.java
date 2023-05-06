package com.PruebaTecnica.ServicioAutomotor.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.PruebaTecnica.ServicioAutomotor.helpers.ViewRouterHelpers;
import com.PruebaTecnica.ServicioAutomotor.models.Cliente;
import com.PruebaTecnica.ServicioAutomotor.models.Marca;
import com.PruebaTecnica.ServicioAutomotor.models.Vehiculo;
import com.PruebaTecnica.ServicioAutomotor.serviceImp.ClienteService;
import com.PruebaTecnica.ServicioAutomotor.serviceImp.MarcaService;
import com.PruebaTecnica.ServicioAutomotor.serviceImp.VehiculoService;

@Controller
@RequestMapping("/car")
public class VehiculoController {

	@Autowired
	private VehiculoService vehiculoService;
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private MarcaService marcaService;
	
	
	@PostMapping("/new")
    public ResponseEntity<?> crearVehiculo(@RequestBody Vehiculo vehiculoNuevo) throws Exception{
        java.util.Optional<Vehiculo> v = vehiculoService.traerVehiculoByPatente(vehiculoNuevo.getPatente());
        if(!v.isPresent()) {
            return new ResponseEntity<Vehiculo>(vehiculoService.saveOrUpdate(vehiculoNuevo), HttpStatus.CREATED);
        }else {

            return new ResponseEntity<String>("El vehiculo a crear ya existe", HttpStatus.OK);
        }

    }
	
	@PutMapping("/{id}")
	public ResponseEntity<?> actualizarVehiculo(@RequestBody Vehiculo vehiculoModificado, @PathVariable(name = "id") int id){
		if(!vehiculoService.traerById(id).isPresent()) {
			return new ResponseEntity<String>("El vehiculo a modificar no existe", HttpStatus.BAD_REQUEST);
		}else {
			vehiculoModificado.setIdVehiculo(id);
			return new ResponseEntity<Vehiculo>(vehiculoService.saveOrUpdate(vehiculoModificado), HttpStatus.OK);
		}
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> eliminarVehiculo(@PathVariable("id")int id) throws Exception{
		Optional<Vehiculo> vehiculoBuscado = vehiculoService.traerById(id);
		if(!vehiculoBuscado.isPresent()) {
			return new ResponseEntity<String>("El vehiculo a eliminar no existe", HttpStatus.NOT_FOUND);
		}else {
			vehiculoService.delete(vehiculoBuscado.get().getIdVehiculo());
			return new ResponseEntity<String>("El vehiculo con el id="+ id+" fue eliminado exitosamente", HttpStatus.OK);
		}
	}
	
	
	@GetMapping("/listar")
	public ResponseEntity<?> listarVehiculos() {
		  return new ResponseEntity<List<Vehiculo>>(vehiculoService.listar(),  HttpStatus.OK);
	}
	

	@GetMapping("/{id}")
	public ResponseEntity<?> traerVehiculoById(@PathVariable(name="id") int id){		
		Optional<Vehiculo> vehiculoBuscado = vehiculoService.traerById(id);
		if(!vehiculoBuscado.isPresent()) {
			return new ResponseEntity<String>("El vehiculo buscado no existe", HttpStatus.NOT_FOUND);
		} else return new ResponseEntity<Vehiculo>(vehiculoBuscado.get(), HttpStatus.FOUND);
	}
	
	
	@GetMapping("/patente/{patente}")
	public ResponseEntity<?> traerVehiculoByPatente(@PathVariable(name="patente") String patente){		
		Optional<Vehiculo> vehiculoBuscado = vehiculoService.traerVehiculoByPatente(patente);
		if(!vehiculoBuscado.isPresent()) {
			return new ResponseEntity<String>("El vehiculo buscado no existe", HttpStatus.NOT_FOUND);
		} else return new ResponseEntity<Vehiculo>(vehiculoBuscado.get(), HttpStatus.FOUND);
	}
	
	@GetMapping("/client/{idCliente}")
	public ResponseEntity<?> traerVehiculoByCliente(@PathVariable(name="idCliente") int idCliente){		
		Optional<Cliente> cliente = clienteService.traerById(idCliente);
				
		if (!cliente.isPresent())  {
			return new ResponseEntity<String>("El cliente solicitado no existe", HttpStatus.NOT_FOUND);
		} 
		else return new ResponseEntity<List<Vehiculo>>( vehiculoService.traerVehiculosByCliente(cliente.get()), HttpStatus.FOUND);
		
	}
	
	
	@GetMapping("/marca/{idMarca}")
	public ResponseEntity<?> traerVehiculoByMarca(@PathVariable(name="idMarca") int idMarca){		
		boolean notExists = false;
		Optional<Marca> marca = marcaService.traerById(idMarca);
				
		if (!marca.isPresent())  {
			return new ResponseEntity<String>("La marca solicitada no existe", HttpStatus.NOT_FOUND);
		} 
		else return new ResponseEntity<List<Vehiculo>>( vehiculoService.traerVehiculosByMarca(marca.get()) , HttpStatus.FOUND);
		
	}
	
	
	/***************************************************MVC**************************************************/
	
	@GetMapping("/")
	public String home(Model model) {
		model.addAttribute("vehiculoList",  vehiculoService.listar());
		return ViewRouterHelpers.VEHICULO_VISTA;
	}
	
	
	@GetMapping("/create")
	public String createVehiculo(Model model) {
		model.addAttribute("vehiculo", new Vehiculo());
		model.addAttribute("marcaList", marcaService.listar());
		model.addAttribute("clienteList", clienteService.listar());
		
		return ViewRouterHelpers.VEHICULO_AGREGAR;
	}

	@PostMapping("/save")
	public String saveVehiculo(@Validated @ModelAttribute("vehiculo") Vehiculo vehiculoNuevo, Model model) {	
		vehiculoService.saveOrUpdate(vehiculoNuevo);
		return ViewRouterHelpers.INDEX_HOME_VEHICULO;
	}
	
	@GetMapping("/edit/{idVehiculo}")
	public String editVehiculo(@PathVariable int idVehiculo, Model model) {
		Optional<Vehiculo>vehiculoBuscado= vehiculoService.traerById(idVehiculo);
		model.addAttribute("vehiculo", vehiculoBuscado.get());
		model.addAttribute("marcaList", marcaService.listar());
		model.addAttribute("clienteList", clienteService.listar());
		model.addAttribute("editMode", true);
		return ViewRouterHelpers.VEHICULO_AGREGAR;
	}

	@GetMapping("/delete/{idVehiculo}")
	public String deleteVehiculo(Model model, @PathVariable int idVehiculo) {
		vehiculoService.delete(idVehiculo);
		return ViewRouterHelpers.INDEX_HOME_VEHICULO;
	}

	@GetMapping("/cancel")
	public String cancelAction() {
		return ViewRouterHelpers.INDEX_HOME_VEHICULO;
	}
	
	
	
}
