package com.PruebaTecnica.ServicioAutomotor.controllers;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import org.springframework.web.bind.annotation.RequestParam;

import com.PruebaTecnica.ServicioAutomotor.helpers.ViewRouterHelpers;
import com.PruebaTecnica.ServicioAutomotor.models.AceiteYFiltro;
import com.PruebaTecnica.ServicioAutomotor.models.AlineacionYBalanceo;
import com.PruebaTecnica.ServicioAutomotor.models.Lavado;
import com.PruebaTecnica.ServicioAutomotor.models.OrdenDeTrabajo;
import com.PruebaTecnica.ServicioAutomotor.models.Servicio;
import com.PruebaTecnica.ServicioAutomotor.serviceImp.OrdenDeTrabajoService;
import com.PruebaTecnica.ServicioAutomotor.serviceImp.ServicioService;
import com.PruebaTecnica.ServicioAutomotor.serviceImp.VehiculoService;

@Controller
@RequestMapping("/orden")
public class OrdenDeTrabajoController {
	
	@Autowired
	private OrdenDeTrabajoService ordenDeTrabajoService;
	
	@Autowired
	private VehiculoService vehiculoService;
	
	@Autowired
	private ServicioService servicioService;
	
	
	@PostMapping("/new")
    public ResponseEntity<?> crearOrdenDeTrabajo(@RequestBody OrdenDeTrabajo ordenNueva) throws Exception{
        	System.out.println(ordenNueva.getServicios());
		  return new ResponseEntity<OrdenDeTrabajo>(ordenDeTrabajoService.saveOrUpdate(ordenNueva), HttpStatus.CREATED);
    
    }
		
	@PutMapping("/{id}")
	public ResponseEntity<?> actualizarOrdenDeTrabajo(@RequestBody OrdenDeTrabajo ordenModificado, @PathVariable(name = "id") int id){
		if(!ordenDeTrabajoService.traerById(id).isPresent()) {
			return new ResponseEntity<String>("La orden a modificar no existe", HttpStatus.BAD_REQUEST);
		}else {
			ordenModificado.setIdOrdenDeTrabajo(id);
			return new ResponseEntity<OrdenDeTrabajo>(ordenDeTrabajoService.saveOrUpdate(ordenModificado), HttpStatus.OK);
		}
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> eliminarOrdenDeTrabajo(@PathVariable("id")int id) throws Exception{
		Optional<OrdenDeTrabajo> ordenBuscada = ordenDeTrabajoService.traerById(id);
		if(!ordenBuscada.isPresent()) {
			return new ResponseEntity<String>("La orden a eliminar no existe", HttpStatus.NOT_FOUND);
		}else {
			ordenDeTrabajoService.delete(ordenBuscada.get().getIdOrdenDeTrabajo());
			return new ResponseEntity<String>("La orden con el id="+ id+" fue eliminada exitosamente", HttpStatus.OK);
		}
	}
	
	@GetMapping("/listar")
	public ResponseEntity<?> listar() {
		  return new ResponseEntity<List<OrdenDeTrabajo>>(ordenDeTrabajoService.listar(),  HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> traerOrdenDeTrabajoById(@PathVariable(name="id") int id){		
		Optional<OrdenDeTrabajo> ordenDeTrabajoBuscada = ordenDeTrabajoService.traerById(id);
		if(!ordenDeTrabajoBuscada.isPresent()) {
			return new ResponseEntity<String>("La orden de trabajo buscada no existe", HttpStatus.NOT_FOUND);
		} else return new ResponseEntity<OrdenDeTrabajo>(ordenDeTrabajoBuscada.get(), HttpStatus.FOUND);
	}
	
	
	@GetMapping("/client/{idCliente}")
	public ResponseEntity<?> listarByIdCliente( @PathVariable("idCliente") int idCliente){
		return new ResponseEntity<List<OrdenDeTrabajo>>(ordenDeTrabajoService.listarByCliente(idCliente), HttpStatus.FOUND);
	}
	
	
	/********************************************MVC***************************************************/
	@GetMapping("/")
	public String listarOrdenDeTrabajo(Model model) {

		model.addAttribute("ordenesList", ordenDeTrabajoService.listar());
		return ViewRouterHelpers.ORDEN_VISTA;
	}
	
	@GetMapping("/create")
	public String createOrdenDeTrabajo(Model model) {		
		model.addAttribute("orden", new OrdenDeTrabajo());
		model.addAttribute("vehiculoList", vehiculoService.listar());
		model.addAttribute("servicioList", servicioService.listarServicios());
		model.addAttribute("nuevoServicio", new Servicio());
		
		return ViewRouterHelpers.ORDEN_AGREGAR;
	}
	
	@PostMapping("/save")
	public String saveOrdenDeTrabajo(@Validated @ModelAttribute("orden") OrdenDeTrabajo ordenNueva) {
		
		ordenNueva.setServicios(servicioService.buscarTodosServicioDeListAux());
		ordenDeTrabajoService.saveOrUpdate(ordenNueva);
		servicioService.borrarTodosServiciosDeListAux(); // reinicio el listado limpiandolo
		return ViewRouterHelpers.INDEX_HOME_ORDEN;
	}
	
	
	@PostMapping("/findService")
	public String buscarLugar(@Validated @ModelAttribute("servicioBuscado") Servicio servicio, 
			@Validated @ModelAttribute("orden") OrdenDeTrabajo orden , Model model) {

		try {
			Servicio servicioBuscado = servicioService.buscarServicioPorDescripcion(servicio.getDescripcion());
			servicioService.guardarServicioEncontradoEnListAux(servicioBuscado);
		} catch (Exception e) {
			model.addAttribute("errorMsg", e.getMessage());
		}
			
		List<Servicio> serviciosAgregados = (List<Servicio>)servicioService.buscarTodosServicioDeListAux();
		List<Lavado> lavadoList = new ArrayList<>();
		List<AceiteYFiltro> ayfList = new ArrayList<>();
		List<AlineacionYBalanceo> aybList = new ArrayList<>();
				
		for (Servicio servicioAux : serviciosAgregados) { //OBTENGO TODOS LOS SERVICOS QUE YA SE ENCUENTRAN SOLICITADOS EN LA ORDEN Y LOS CASTEO A SU TIPO
			
			if(servicioAux instanceof Lavado) {lavadoList.add((Lavado)servicioAux);}
			if(servicioAux instanceof AceiteYFiltro) {ayfList.add((AceiteYFiltro)servicioAux);}
			if(servicioAux instanceof AlineacionYBalanceo) {aybList.add((AlineacionYBalanceo)servicioAux);}
		}
		model.addAttribute("orden", orden);
		model.addAttribute("servicioList", getServiciosNoAgregados(serviciosAgregados));
		model.addAttribute("vehiculoList", vehiculoService.listar());
		model.addAttribute("lavadoList", lavadoList);
		model.addAttribute("aceiteYFiltroList", ayfList);
		model.addAttribute("alineacionYBalanceoList", aybList);
		model.addAttribute("nuevoServicio", new Servicio());
		return ViewRouterHelpers.ORDEN_AGREGAR;
	}
	
	
	@GetMapping("/cancel")
	public String cancelarAccion() {
		servicioService.borrarTodosServiciosDeListAux(); // reinicio el listado limpiandolo pora el proximo turno a crear
		return ViewRouterHelpers.INDEX_HOME_ORDEN;
	}

	private List<Servicio> getServiciosNoAgregados (List<Servicio> serviciosAgregados) {
		List<Servicio> servicios = servicioService.listarServicios(); //OBTENGO TODOS LOS SERVICIOS
		List<Servicio> serviciosNoAgregados = new ArrayList<>();
		Map<String, Servicio> mapServiciosAgregados = new HashMap<>(); //CREO UN MAPA QUE VALIDA MEDIANTE LA DESCRIPCION(KEY) A LOS SERVICIOS
		
		for (Servicio servicio : serviciosAgregados) { //CARGO TODOS LOS SERVICIOS EN UN MAPA
			mapServiciosAgregados.put(servicio.getDescripcion(), servicio);
		}
		for (int i = 0; i < servicios.size(); i++) { //VERIFICO LOS SERVICIOS QUE NO FUERON AGREGADOS A LA ORDEN Y LOS AGREGO EN UNA LISTA AUXILIAR
			if(mapServiciosAgregados.get(servicios.get(i).getDescripcion()) == null) {
				serviciosNoAgregados.add(servicios.get(i));
			}
			
		}
		return serviciosNoAgregados;
	}
	
	
	
}
