package com.PruebaTecnica.ServicioAutomotor.serviceImp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PruebaTecnica.ServicioAutomotor.interfaceService.IClienteService;
import com.PruebaTecnica.ServicioAutomotor.models.Cliente;
import com.PruebaTecnica.ServicioAutomotor.models.Vehiculo;
import com.PruebaTecnica.ServicioAutomotor.repositories.IClienteRepository;

@Service
public class ClienteService implements IClienteService {

	@Autowired
	private IClienteRepository clienteRepository;
	
	@Autowired
	private VehiculoService vehiculoService;
	
	@Override
	public List<Cliente> listar() {
		// TODO Auto-generated method stub
		return clienteRepository.findAll();
	}

	@Override
	public Optional<Cliente> traerById(int id) {
		// TODO Auto-generated method stub
		return clienteRepository.findById(id) ;
	}

	@Override
	public Optional<Cliente> traerByDni(long dni) {
		// TODO Auto-generated method stub
		return clienteRepository.findClienteByDni(dni);
	}

	@Override
	public Cliente saveOrUpdate(Cliente cliente) {
		// TODO Auto-generated method stub
	
	        Optional<Cliente> clientedb = clienteRepository.findById(cliente.getIdCliente());
	      
	        if( !clientedb.isPresent() ) {
	            return clienteRepository.save(cliente);
	        }else {
	            map(cliente, clientedb.get());
	            return clienteRepository.save(clientedb.get());
	       }
	  }
	

	@Override
	public void delete(int idCliente){
		// TODO Auto-generated method stub
		Cliente clienteBD = clienteRepository.findById(idCliente).get(); 
		List<Vehiculo> vehiculosBD = vehiculoService.traerVehiculosByCliente(clienteBD); //OBTENGO TODOS LOS VEHICULOS DEL USUARIO INDICADO
		if(vehiculosBD != null) { 
			for (Vehiculo vehiculo : vehiculosBD) {
				vehiculoService.delete(vehiculo.getIdVehiculo()); //ELIMINO CADA VEHICULO REGISTRADO AL CLIENTE
			}
		}	
		clienteRepository.deleteById(idCliente); //UNA VEZ ELIMINADA LAS ORDENES DEL MISMO Y SUS VEHICULOS, ELIMINO AL CLIENTE
	}
	
	
	private void map(Cliente modificado, Cliente preModificado ){

		if(modificado.getCantServicios() != 0) {
			preModificado.setCantServicios(modificado.getCantServicios());
		}
		
        if(modificado.getApellido()!=null) {
            preModificado.setApellido(modificado.getApellido());
        }
        if (modificado.getNombre()!=null) {
            preModificado.setNombre(modificado.getNombre());
        }
        if (modificado.getEmail() != null) {
        	preModificado.setEmail(modificado.getEmail());
        }
        if (modificado.getDni() != 0) {
            preModificado.setDni(modificado.getDni());
        }
	}

	
	
}
