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
import com.PruebaTecnica.ServicioAutomotor.models.Marca;
import com.PruebaTecnica.ServicioAutomotor.serviceImp.MarcaService;

@Controller
@RequestMapping("/marca")
public class MarcaController {
	
	@Autowired
	private MarcaService marcaService;
	
	@PostMapping("/new")
    public ResponseEntity<?> crearMarca(@RequestBody Marca marcaNuevo) throws Exception{
        java.util.Optional<Marca> m = marcaService.traerByMarca(marcaNuevo.getNombreMarca());
        if(!m.isPresent()) {
            return new ResponseEntity<Marca>(marcaService.saveOrUpdate(marcaNuevo), HttpStatus.CREATED);
        }else {

            return new ResponseEntity<String>("La marca a crear ya existe", HttpStatus.OK);
        }

    }
	
	@PutMapping("/{id}")
	public ResponseEntity<?> actualizarMarca(@RequestBody Marca marcaModificada, @PathVariable(name = "id") int id){
		if(!marcaService.traerById(id).isPresent()) {
			return new ResponseEntity<String>("La marca a modificar no existe", HttpStatus.BAD_REQUEST);
		}else {
			marcaModificada.setIdMarca(id);
			return new ResponseEntity<Marca>(marcaService.saveOrUpdate(marcaModificada), HttpStatus.OK);
		}
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> eliminarMarca(@PathVariable("id")int id) throws Exception{
		Optional<Marca> marcaBuscada = marcaService.traerById(id);
		if(!marcaBuscada.isPresent()) {
			return new ResponseEntity<String>("La marca a eliminar no existe", HttpStatus.NOT_FOUND);
		}else {
			marcaService.delete(marcaBuscada.get().getIdMarca());
			return new ResponseEntity<String>("La marca con el id="+ id+" fue eliminada exitosamente", HttpStatus.OK);
		}
	}
	
	
	@GetMapping("/listar")
	public ResponseEntity<?> listarMarcas() {
		  return new ResponseEntity<List<Marca>>(marcaService.listar(),  HttpStatus.OK);
	}
	

	@GetMapping("/{id}")
	public ResponseEntity<?> traerMarcaById(@PathVariable(name="id") int id){		
		Optional<Marca> marcaBuscada = marcaService.traerById(id);
		if(!marcaBuscada.isPresent()) {
			return new ResponseEntity<String>("La marca buscada no existe", HttpStatus.NOT_FOUND);
		} else return new ResponseEntity<Marca>(marcaBuscada.get(), HttpStatus.FOUND);
	}
	
	
	@GetMapping("/marca/{marca}")
	public ResponseEntity<?> traerMarcaByMarca(@PathVariable(name="marca") String marca){		
		Optional<Marca> marcaBuscada = marcaService.traerByMarca(marca);
		if(!marcaBuscada.isPresent()) {
			return new ResponseEntity<String>("La marca buscada no existe", HttpStatus.NOT_FOUND);
		} else return new ResponseEntity<Marca>(marcaBuscada.get(), HttpStatus.FOUND);
	}
	
	
	/****************************************************MVC**************************************************************/
	@GetMapping("/")
	public String listMarca(Model model) {
		model.addAttribute("marcaList",  marcaService.listar());
		return ViewRouterHelpers.MARCA_VISTA;
	}
	
	
	@GetMapping("/create")
	public String createMarca(Model model) {
		model.addAttribute("marca", new Marca());
		return ViewRouterHelpers.MARCA_AGREGAR;
	}

	@PostMapping("/save")
	public String saveMarca(@Validated @ModelAttribute("marca") Marca marca, Model model) {			
		marcaService.saveOrUpdate(marca);
		return ViewRouterHelpers.INDEX_HOME_MARCA;
	}
	
	@GetMapping("/edit/{idMarca}")
	public String editMarca(@PathVariable int idMarca, Model model) {
		Optional<Marca> marcaBuscada= marcaService.traerById(idMarca);
		model.addAttribute("marca", marcaBuscada.get());
		model.addAttribute("editMode", true);
		return ViewRouterHelpers.MARCA_AGREGAR;
	}

	@GetMapping("/cancel")
	public String cancelAction() {
		return ViewRouterHelpers.INDEX_HOME_MARCA;
	}
	
}
