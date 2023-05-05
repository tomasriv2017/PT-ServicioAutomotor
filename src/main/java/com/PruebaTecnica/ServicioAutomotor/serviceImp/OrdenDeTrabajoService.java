package com.PruebaTecnica.ServicioAutomotor.serviceImp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PruebaTecnica.ServicioAutomotor.interfaceService.IOrdenDeTrabajoService;
import com.PruebaTecnica.ServicioAutomotor.models.OrdenDeTrabajo;
import com.PruebaTecnica.ServicioAutomotor.models.Servicio;
import com.PruebaTecnica.ServicioAutomotor.repositories.IOrdenDeTrabajoRepository;

@Service
public class OrdenDeTrabajoService implements IOrdenDeTrabajoService {

	@Autowired
	private IOrdenDeTrabajoRepository ordenDeTrabajoRepository;
	
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
        	ordenDeTrabajo.setTotal( calcularTotal(ordenDeTrabajo));
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
	
		
	private void map(OrdenDeTrabajo modificado, OrdenDeTrabajo preModificado ){

        if( modificado.getFechaYHora() != null) {
            preModificado.setFechaYHora(modificado.getFechaYHora());
        }
        
        if( modificado.getVehiculo() != null) {
            preModificado.setVehiculo( modificado.getVehiculo());
        }
        
        if( modificado.getServicios() != null) {
            preModificado.setServicios(modificado.getServicios());
            preModificado.setTotal( calcularTotal(modificado) );
        }
       

	}
	
	private double calcularTotal (OrdenDeTrabajo ordenDeTrabajo) {
		double total = 0;
		
		for (Servicio servicio : ordenDeTrabajo.getServicios()  ) {
			total += servicio.getPrecio();
		}
		
		return total;
	}

}
