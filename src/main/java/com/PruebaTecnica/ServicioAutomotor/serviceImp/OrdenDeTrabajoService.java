package com.PruebaTecnica.ServicioAutomotor.serviceImp;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PruebaTecnica.ServicioAutomotor.interfaceService.IOrdenDeTrabajoService;
import com.PruebaTecnica.ServicioAutomotor.models.Cliente;
import com.PruebaTecnica.ServicioAutomotor.models.OrdenDeTrabajo;
import com.PruebaTecnica.ServicioAutomotor.models.Servicio;
import com.PruebaTecnica.ServicioAutomotor.repositories.IClienteRepository;
import com.PruebaTecnica.ServicioAutomotor.repositories.IOrdenDeTrabajoRepository;
import com.PruebaTecnica.ServicioAutomotor.repositories.IServicioRepository;
import com.PruebaTecnica.ServicioAutomotor.repositories.IVehiculoRepository;

@Service
public class OrdenDeTrabajoService implements IOrdenDeTrabajoService {

	@Autowired
	private IOrdenDeTrabajoRepository ordenDeTrabajoRepository;
	
	@Autowired
	private IServicioRepository servicioRepository;

	@Autowired
	private IVehiculoRepository vehiculoRepository;
	
	@Autowired
	private IClienteRepository clienteRepository;
	
	
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
    		Cliente clienteDelVehiculo = vehiculoRepository.findById(ordenDeTrabajo.getVehiculo().getIdVehiculo()).get().getCliente();
    		clienteDelVehiculo.setCantServicios(clienteDelVehiculo.getCantServicios() + ordenDeTrabajo.getServicios().size());
			clienteRepository.save(clienteDelVehiculo);

        	ordenDeTrabajo.setServicios(getServicios(ordenDeTrabajo));
        	ordenDeTrabajo.setTotal(calcularTotal(ordenDeTrabajo));
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
	
	
	
	private List<Servicio> getServicios(OrdenDeTrabajo ordenDeTrabajo) {
        List<Servicio> listAux = new ArrayList<>();
		if(!ordenDeTrabajo.getServicios().isEmpty()) {
    		for (Servicio servicio : ordenDeTrabajo.getServicios()) {
    			listAux.add(servicioRepository.findById(servicio.getIdServicio()).get());
			}
    	}
		return listAux;
	}
	
	private void map(OrdenDeTrabajo modificado, OrdenDeTrabajo preModificado ){
		
        if( modificado.getFechaHora() != null) {
            preModificado.setFechaHora(modificado.getFechaHora());
        }
        
        if( modificado.getFechaHora() != null) {
            preModificado.setFechaHora(modificado.getFechaHora());
        }
        
        
        if( modificado.getVehiculo() != null) {
            preModificado.setVehiculo( modificado.getVehiculo());
        }
        
        if( modificado.getServicios() != null) {
            preModificado.setServicios( getServicios(modificado));
            preModificado.setTotal( calcularTotal(preModificado));
        }
	}
	
	private double calcularTotal (OrdenDeTrabajo ordenDeTrabajo) {
		double total = 0;
		for (Servicio servicio : ordenDeTrabajo.getServicios()) {
			total += servicio.getPrecio();
		}
		
		Cliente clienteDelVehiculo = vehiculoRepository.findById(ordenDeTrabajo.getVehiculo().getIdVehiculo()).get().getCliente();
				
		if(clienteDelVehiculo.isEsPremium()) {
			total -= total*0.15;
			clienteDelVehiculo.setCantServicios(0);
			clienteDelVehiculo.setEsPremium(false);
			clienteRepository.save(clienteDelVehiculo);
		}
		return total;
	}

}
