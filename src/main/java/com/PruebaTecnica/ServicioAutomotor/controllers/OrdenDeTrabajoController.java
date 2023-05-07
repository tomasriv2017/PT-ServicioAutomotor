package com.PruebaTecnica.ServicioAutomotor.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.PruebaTecnica.ServicioAutomotor.helpers.ViewRouterHelpers;
import com.PruebaTecnica.ServicioAutomotor.models.AceiteYFiltro;
import com.PruebaTecnica.ServicioAutomotor.models.AlineacionYBalanceo;
import com.PruebaTecnica.ServicioAutomotor.models.Lavado;
import com.PruebaTecnica.ServicioAutomotor.models.OrdenDeTrabajo;
import com.PruebaTecnica.ServicioAutomotor.models.Servicio;
import com.PruebaTecnica.ServicioAutomotor.models.Vehiculo;
import com.PruebaTecnica.ServicioAutomotor.serviceImp.ClienteService;
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
	
	@Autowired
	private ClienteService clienteService;
	
	
//	@PostMapping("/new")
//    public ResponseEntity<?> crearOrdenDeTrabajo(@RequestBody OrdenDeTrabajo ordenNueva) throws Exception{
//        	System.out.println(ordenNueva.getServicios());
//		  return new ResponseEntity<OrdenDeTrabajo>(ordenDeTrabajoService.saveOrUpdate(ordenNueva), HttpStatus.CREATED);
//    
//    }
//		
//	@PutMapping("/{id}")
//	public ResponseEntity<?> actualizarOrdenDeTrabajo(@RequestBody OrdenDeTrabajo ordenModificado, @PathVariable(name = "id") int id){
//		if(!ordenDeTrabajoService.traerById(id).isPresent()) {
//			return new ResponseEntity<String>("La orden a modificar no existe", HttpStatus.BAD_REQUEST);
//		}else {
//			ordenModificado.setIdOrdenDeTrabajo(id);
//			return new ResponseEntity<OrdenDeTrabajo>(ordenDeTrabajoService.saveOrUpdate(ordenModificado), HttpStatus.OK);
//		}
//	}
//	
//	
//	@DeleteMapping("/{id}")
//	public ResponseEntity<?> eliminarOrdenDeTrabajo(@PathVariable("id")int id) throws Exception{
//		Optional<OrdenDeTrabajo> ordenBuscada = ordenDeTrabajoService.traerById(id);
//		if(!ordenBuscada.isPresent()) {
//			return new ResponseEntity<String>("La orden a eliminar no existe", HttpStatus.NOT_FOUND);
//		}else {
//			ordenDeTrabajoService.delete(ordenBuscada.get().getIdOrdenDeTrabajo());
//			return new ResponseEntity<String>("La orden con el id="+ id+" fue eliminada exitosamente", HttpStatus.OK);
//		}
//	}
//	
//	@GetMapping("/listar")
//	public ResponseEntity<?> listar() {
//		  return new ResponseEntity<List<OrdenDeTrabajo>>(ordenDeTrabajoService.listar(),  HttpStatus.OK);
//	}
//	
//	@GetMapping("/{id}")
//	public ResponseEntity<?> traerOrdenDeTrabajoById(@PathVariable(name="id") int id){		
//		Optional<OrdenDeTrabajo> ordenDeTrabajoBuscada = ordenDeTrabajoService.traerById(id);
//		if(!ordenDeTrabajoBuscada.isPresent()) {
//			return new ResponseEntity<String>("La orden de trabajo buscada no existe", HttpStatus.NOT_FOUND);
//		} else return new ResponseEntity<OrdenDeTrabajo>(ordenDeTrabajoBuscada.get(), HttpStatus.FOUND);
//	}
//	
//	
//	@GetMapping("/client/{idCliente}")
//	public ResponseEntity<?> listarByIdCliente( @PathVariable("idCliente") int idCliente){
//		return new ResponseEntity<List<OrdenDeTrabajo>>(ordenDeTrabajoService.listarByCliente(idCliente), HttpStatus.FOUND);
//	}
//	
	
	/********************************************MVC***************************************************/
	@GetMapping("/")
	public String listarOrdenDeTrabajo(Model model) {
		model.addAttribute("ordenesList", ordenDeTrabajoService.listar());
		model.addAttribute("clientesList", clienteService.listar());
		model.addAttribute("vehiculoList", vehiculoService.listar());
		return ViewRouterHelpers.ORDEN_VISTA;
	}
	
	@GetMapping("/create")
	public String createOrdenDeTrabajo(Model model) {	
		List<Vehiculo> listaVehiculos = vehiculoService.listar();
		setEstadoALosClientes(listaVehiculos);
		
		model.addAttribute("orden", new OrdenDeTrabajo());
		model.addAttribute("vehiculoList", listaVehiculos);
		model.addAttribute("servicioList", servicioService.listarServicios());
		model.addAttribute("nuevoServicio", new Servicio());
		return ViewRouterHelpers.ORDEN_AGREGAR;
	}
	
	@PostMapping("/save")
	public String saveOrdenDeTrabajo(@Validated @ModelAttribute("orden") OrdenDeTrabajo ordenNueva, Model model ) {
			ordenNueva.setServicios(servicioService.buscarTodosServicioDeListAux());
			try {
				ordenDeTrabajoService.saveOrUpdate(ordenNueva);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				model.addAttribute("errorMsg", e.getMessage());
				model.addAttribute("orden", new OrdenDeTrabajo());
				model.addAttribute("vehiculoList", vehiculoService.listar());
				model.addAttribute("servicioList", servicioService.listarServicios());
				model.addAttribute("nuevoServicio", new Servicio());
				return ViewRouterHelpers.ORDEN_AGREGAR;
			}
			servicioService.borrarTodosServiciosDeListAux(); // reinicio el listado limpiandolo
		return ViewRouterHelpers.INDEX_HOME_ORDEN;
	}
	
	
	@PostMapping("/findService")
	public String buscarLugar(@Validated @ModelAttribute("servicioBuscado") Servicio servicio, 
			@Validated @ModelAttribute("orden") OrdenDeTrabajo orden,
			Model model) {
		
		try {			
			Servicio servicioBuscado = servicioService.buscarServicioPorDescripcion(servicio.getDescripcion());
			servicioService.guardarServicioEncontradoEnListAux(servicioBuscado);
		} catch (Exception e) {
			model.addAttribute("errorMsg", e.getMessage());
		}
					
		List<Servicio> serviciosAgregados = (List<Servicio>)servicioService.buscarTodosServicioDeListAux();
		List<Lavado> lavadoLista = new ArrayList<>();
		List<AceiteYFiltro> ayfLista = new ArrayList<>();
		List<AlineacionYBalanceo> aybLista = new ArrayList<>();
		getServiciosByTipo(serviciosAgregados,lavadoLista, ayfLista, aybLista); 
	
		Optional<OrdenDeTrabajo> ordenEnBD = ordenDeTrabajoService.traerById(orden.getIdOrdenDeTrabajo());
		if(ordenEnBD.isPresent()) { //DETERMINO SI LA ORDEN YA ESTA CREADA, EN EL CASO DE YA EXISTIR ES UNA MODIFICACION
			model.addAttribute("orden", ordenEnBD.get()); 
			model.addAttribute("editMode", true);
		}
		else  model.addAttribute("orden", orden);
		
		model.addAttribute("servicioList", getServiciosNoAgregados(serviciosAgregados));
		model.addAttribute("vehiculoList", vehiculoService.listar());
		model.addAttribute("lavadoList", lavadoLista);
		model.addAttribute("aceiteYFiltroList", ayfLista);
		model.addAttribute("alineacionYBalanceoList", aybLista);
		model.addAttribute("nuevoServicio", new Servicio());		
		return ViewRouterHelpers.ORDEN_AGREGAR;
	}
	
	
	@GetMapping("/edit/{idOrdenDeTrabajo}")
	public String editOrdenDeTrabajo(@PathVariable int idOrdenDeTrabajo, Model model) throws Exception {
		Optional<OrdenDeTrabajo>ordenDeTrabajo= ordenDeTrabajoService.traerById(idOrdenDeTrabajo);
		
		List<Servicio> serviciosAgregados = ordenDeTrabajo.get().getServicios() ; //OBTENGO TODOS LOS SERVICIOS YA AGREGADOS A LA ORDEN
		List<Lavado> lavadoLista = new ArrayList<>();
		List<AceiteYFiltro> ayfLista = new ArrayList<>();
		List<AlineacionYBalanceo> aybLista = new ArrayList<>();
		getServiciosByTipo(serviciosAgregados,lavadoLista, ayfLista, aybLista); 
		
		for (Servicio servicioGuardado  : serviciosAgregados) { //GUARDO TODOS LOS SERVICIOS QUE YA TENIA LA ORDEN CREADA EN LA LISTA AUXILIAR
			servicioService.guardarServicioEncontradoEnListAux(servicioGuardado);
		}
		
		model.addAttribute("orden", ordenDeTrabajo.get());
		model.addAttribute("servicioList", getServiciosNoAgregados(serviciosAgregados));
		model.addAttribute("vehiculoList", vehiculoService.listar());
		model.addAttribute("lavadoList", lavadoLista);
		model.addAttribute("aceiteYFiltroList", ayfLista);
		model.addAttribute("alineacionYBalanceoList", aybLista);
		model.addAttribute("nuevoServicio", new Servicio());
		model.addAttribute("editMode", true);
		return ViewRouterHelpers.ORDEN_AGREGAR;
	}
	
	@GetMapping("/delete/{idOrdenDeTrabajo}")
	public String deleteOrdenDeTrabajo(Model model, @PathVariable int idOrdenDeTrabajo) {
		ordenDeTrabajoService.delete(idOrdenDeTrabajo);		 
		return ViewRouterHelpers.INDEX_HOME_ORDEN;
	}

	@GetMapping("/cancel")
	public String cancelarAccion() {
		servicioService.borrarTodosServiciosDeListAux(); // RENINICIO EL LISTADO LIMPIANDOLO PARA EL PROXIMO TURNO A CREAR
		return ViewRouterHelpers.INDEX_HOME_ORDEN;
	}

	//FILTRAR ORDENES POR CLIENTE
	@GetMapping("/listFiltradoPorCliente")
	public String filterOrdenByCliente(Model model, 
			@RequestParam(value = "cliente") int idCliente) {
		model.addAttribute("ordenesList", ordenDeTrabajoService.listarByCliente(idCliente));
		model.addAttribute("clientesList", clienteService.listar());
		model.addAttribute("vehiculoList", vehiculoService.listar());
		return ViewRouterHelpers.ORDEN_VISTA;
	}
		
	//FILTRAR ORDENES POR VEHICULO
	@GetMapping("/listFiltradoPorVehiculo")
	public String filterOrdenByVehiculo(Model model, 
			@RequestParam(value = "vehiculo") int idVehiculo) {		
		model.addAttribute("ordenesList", ordenDeTrabajoService.listarByVehiculo(idVehiculo));
		model.addAttribute("clientesList", clienteService.listar());
		model.addAttribute("vehiculoList", vehiculoService.listar());

		return ViewRouterHelpers.ORDEN_VISTA;
	}
		
	//LIMPIAR FILTROS
	@GetMapping("/limpiarFiltros")
	public String cleanFiltros() {
		return ViewRouterHelpers.INDEX_HOME_ORDEN;
	}
	
	
	/***************************************FUNCIONES********************************************/
	private List<Servicio> getServiciosNoAgregados (List<Servicio> serviciosAgregados) {
        List<Servicio> servicios = servicioService.listarServicios(); //OBTENGO TODOS LOS SERVICIOS
        List<Servicio> serviciosRestantes = new ArrayList<>();
        List<Servicio> listServiciosAQuitar = new ArrayList<>();
        Map<String, Servicio> mapServiciosAgregados = new HashMap<>(); //CREO UN MAPA QUE VALIDA MEDIANTE LA DESCRIPCION(KEY) A LOS SERVICIOS

        for (Servicio servicio : serviciosAgregados) { //CARGO TODOS LOS SERVICIOS EN UN MAPA
            mapServiciosAgregados.put(servicio.getDescripcion(), servicio);
        }
        for (int i = 0; i < servicios.size(); i++) { //VERIFICO LOS SERVICIOS QUE NO FUERON AGREGADOS A LA ORDEN Y LOS AGREGO EN UNA LISTA AUXILIAR
            if(mapServiciosAgregados.get(servicios.get(i).getDescripcion()) == null) {
            	serviciosRestantes.add(servicios.get(i));
            }
        }
        for (int i = 0; i < serviciosRestantes.size(); i++) {
            for (Servicio servicio : serviciosAgregados) {
                if (serviciosRestantes.get(i).getDescripcion().substring(0, 5).equalsIgnoreCase(servicio.getDescripcion().substring(0, 5))) {
                	listServiciosAQuitar.add(serviciosRestantes.get(i));
                }
            }
        }
        serviciosRestantes.removeAll(listServiciosAQuitar);
        return serviciosRestantes;
    }
	
	
	
	
	private void getServiciosByTipo (List<Servicio> serviciosAgregados, List<Lavado> lavadoLista, List<AceiteYFiltro> ayfLista, List<AlineacionYBalanceo> aybLista) {
		for (Servicio servicioAux : serviciosAgregados) { //CASTEO LOS SERVICIOS A SU TIPO A TRAVES DE SU HERENCIA Y LOS GUARDO EN SU CORRESPONDIENTE LISTA
			if(servicioAux instanceof Lavado) {lavadoLista.add((Lavado)servicioAux);}
			if(servicioAux instanceof AceiteYFiltro) {ayfLista.add((AceiteYFiltro)servicioAux);}
			if(servicioAux instanceof AlineacionYBalanceo) {aybLista.add((AlineacionYBalanceo)servicioAux);}
		}
	}
	
	private void setEstadoALosClientes (List<Vehiculo> listaVehiculos){ //LE SETEO LOS ESTADOS 'PREMIUM' o 'NOPREMIUM' A TODOS LOS CLIENTES EN BASE  A SI SU CANT. DE SERVICIOS ES MAYOR O IGUAL A 5
		for (Vehiculo vehiculos : listaVehiculos) {   
			if(vehiculos.getCliente().getCantServicios() >= 5) {
				vehiculos.getCliente().setEsPremium(true);
				clienteService.saveOrUpdate(vehiculos.getCliente()); 
			}
		}
	}
	
	
}
