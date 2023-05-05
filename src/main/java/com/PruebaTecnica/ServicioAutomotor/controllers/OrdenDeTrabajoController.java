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

import com.PruebaTecnica.ServicioAutomotor.models.OrdenDeTrabajo;
import com.PruebaTecnica.ServicioAutomotor.serviceImp.OrdenDeTrabajoService;

@Controller
@RequestMapping("/orden")
public class OrdenDeTrabajoController {
	
	@Autowired
	private OrdenDeTrabajoService ordenDeTrabajoService;
	
	@PostMapping("/new")
    public ResponseEntity<?> crearOrdenDeTrabajo(@RequestBody OrdenDeTrabajo ordenNueva) throws Exception{
        	System.out.println(ordenNueva.getServicios());
		  return new ResponseEntity<OrdenDeTrabajo>(ordenDeTrabajoService.saveOrUpdate(ordenNueva), HttpStatus.CREATED);
    
    }
		
	
	@PutMapping("/{id}")
	public ResponseEntity<?> actualizarOrden(@RequestBody OrdenDeTrabajo ordenModificado, @PathVariable(name = "id") int id){
		if(!ordenDeTrabajoService.traerById(id).isPresent()) {
			return new ResponseEntity<String>("La orden a modificar no existe", HttpStatus.BAD_REQUEST);
		}else {
			ordenModificado.setIdOrdenDeTrabajo(id);
			return new ResponseEntity<OrdenDeTrabajo>(ordenDeTrabajoService.saveOrUpdate(ordenModificado), HttpStatus.OK);
		}
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> eliminarOrden(@PathVariable("id")int id) throws Exception{
		Optional<OrdenDeTrabajo> ordenBuscada = ordenDeTrabajoService.traerById(id);
		if(!ordenBuscada.isPresent()) {
			return new ResponseEntity<String>("La orden a eliminar no existe", HttpStatus.NOT_FOUND);
		}else {
			ordenDeTrabajoService.delete(ordenBuscada.get().getIdOrdenDeTrabajo());
			return new ResponseEntity<String>("La orden con el id="+ id+" fue eliminada exitosamente", HttpStatus.OK);
		}
	}
	
	@GetMapping("/")
	public ResponseEntity<?> listarOrdenes() {
		  return new ResponseEntity<List<OrdenDeTrabajo>>(ordenDeTrabajoService.listar(),  HttpStatus.OK);
	}
	
	
	
	
	
//	
//	private void cast(OrdenDeTrabajo ordenDeTrabajo) {
//		Set<Servicio> listAux = new HashSet<>();
//		
//		for (Servicio servicio : ordenDeTrabajo.getServicios()) {
//			if(servicio instanceof Lavado) {
//				System.out.println("ACA ENTRO");
//				listAux.add( ((Lavado)servicio) );
//			}
////			if(servicio instanceof AceiteYFiltro) {
////				listAux.add( ((AceiteYFiltro)servicio) );
////			}
////			if( servicio instanceof AlineacionYBalanceo) {
////				listAux.add( ((AlineacionYBalanceo)servicio) );
////			}
//		}
//		
//		ordenDeTrabajo.setServicios(listAux);
//	}
	
}
