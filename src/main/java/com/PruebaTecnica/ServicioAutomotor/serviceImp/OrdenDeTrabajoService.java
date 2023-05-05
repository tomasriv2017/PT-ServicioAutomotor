package com.PruebaTecnica.ServicioAutomotor.serviceImp;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PruebaTecnica.ServicioAutomotor.interfaceService.IOrdenDeTrabajoService;
import com.PruebaTecnica.ServicioAutomotor.models.OrdenDeTrabajo;
import com.PruebaTecnica.ServicioAutomotor.models.Servicio;
import com.PruebaTecnica.ServicioAutomotor.repositories.IOrdenDeTrabajoRepository;
import com.PruebaTecnica.ServicioAutomotor.repositories.IServicioRepository;

@Service
public class OrdenDeTrabajoService implements IOrdenDeTrabajoService {

	@Autowired
	private IOrdenDeTrabajoRepository ordenDeTrabajoRepository;
	
	@Autowired
	private IServicioRepository servicioRepository;
	
	
	@Override
	public List<OrdenDeTrabajo> listar() {
		// TODO Auto-generated method stub
		return ordenDeTrabajoRepository.findAll();
	}

	@Override
	public List<OrdenDeTrabajo> listarByCliente(int idCliente) {
		// TODO Auto-generated method stub
		return ordenDeTrabajoRepository.listOrdenDeTrabajoByCliente(idCliente);
	}

	@Override
	public Optional<OrdenDeTrabajo> traerById(int idOrdenDeTrabajo) {
		// TODO Auto-generated method stub
		return ordenDeTrabajoRepository.findById(idOrdenDeTrabajo);
	}

	@Override
	public OrdenDeTrabajo saveOrUpdate(OrdenDeTrabajo ordenDeTrabajo) {
		// TODO Auto-generated method stub
        Optional<OrdenDeTrabajo> ordenDeTrabajodb = ordenDeTrabajoRepository.findById(ordenDeTrabajo.getIdOrdenDeTrabajo());
        
        if( !ordenDeTrabajodb.isPresent() ) { 
        	ordenDeTrabajo.setServicios(getServicios(ordenDeTrabajo));
        	ordenDeTrabajo.setTotal(calcularTotal(ordenDeTrabajo.getServicios()));
            return ordenDeTrabajoRepository.save(ordenDeTrabajo);
        }else {
            map(ordenDeTrabajo, ordenDeTrabajodb.get());
            return ordenDeTrabajoRepository.save(ordenDeTrabajodb.get());
       }
	}

	@Override
	public void delete(int idOrdenDeTrabajo) {
		// TODO Auto-generated method stub
		ordenDeTrabajoRepository.deleteById(idOrdenDeTrabajo);
	}
	
	
	
	private Set<Servicio> getServicios(OrdenDeTrabajo ordenDeTrabajo) {
        Set<Servicio> listAux = new HashSet<>();
		if(!ordenDeTrabajo.getServicios().isEmpty()) {
    		for (Servicio servicio : ordenDeTrabajo.getServicios()) {
    			listAux.add(servicioRepository.findById(servicio.getIdServicio()).get());
			}
    	}
		return listAux;
	}
	
	private void map(OrdenDeTrabajo modificado, OrdenDeTrabajo preModificado ){
		
        if( modificado.getFechaYHora() != null) {
            preModificado.setFechaYHora(modificado.getFechaYHora());
        }
        
        if( modificado.getVehiculo() != null) {
            preModificado.setVehiculo( modificado.getVehiculo());
        }
        
        if( modificado.getServicios() != null) {
            preModificado.setServicios( getServicios(modificado));
            preModificado.setTotal( calcularTotal( preModificado.getServicios()));
        }
	}
	
	private double calcularTotal (Set<Servicio> servicios) {
		double total = 0;
		
		for (Servicio servicio : servicios) {
			total += servicio.getPrecio();
		}
		return total;
	}

}
