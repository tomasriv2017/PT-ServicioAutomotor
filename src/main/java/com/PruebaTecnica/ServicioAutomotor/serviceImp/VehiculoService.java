package com.PruebaTecnica.ServicioAutomotor.serviceImp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PruebaTecnica.ServicioAutomotor.interfaceService.IVehiculoService;
import com.PruebaTecnica.ServicioAutomotor.models.Cliente;
import com.PruebaTecnica.ServicioAutomotor.models.Marca;
import com.PruebaTecnica.ServicioAutomotor.models.Vehiculo;
import com.PruebaTecnica.ServicioAutomotor.repositories.IVehiculoRepository;

@Service
public class VehiculoService implements IVehiculoService {

	@Autowired
	private IVehiculoRepository vehiculoRepository;
	
	@Override
	public List<Vehiculo> listar() {
		// TODO Auto-generated method stub
		return vehiculoRepository.findAll();
	}

	@Override
	public Optional<Vehiculo> traerById(int id) {
		// TODO Auto-generated method stub
		return vehiculoRepository.findById(id);
	}

	@Override
	public Optional<Vehiculo> traerVehiculoByPatente(String patente) {
		// TODO Auto-generated method stub
		return vehiculoRepository.findVehiculoByPatente(patente);
	}

	@Override
	public List<Vehiculo> traerVehiculosByMarca(Marca marca) {
		// TODO Auto-generated method stub
		return vehiculoRepository.findAllByMarca(marca);
	}

	@Override
	public List<Vehiculo> traerVehiculosByCliente(Cliente cliente) {
		// TODO Auto-generated method stub
		return vehiculoRepository.findAllByCliente(cliente);
	}

	@Override
	public Vehiculo saveOrUpdate(Vehiculo vehiculo) {
		// TODO Auto-generated method stub
        Optional<Vehiculo> vehiculodb = vehiculoRepository.findById(vehiculo.getIdVehiculo());
        if( !vehiculodb.isPresent() ) {
            return vehiculoRepository.save(vehiculo);
        }else {
            map(vehiculo, vehiculodb.get());
            return vehiculoRepository.save(vehiculodb.get());
       }
	}

	@Override
	public void delete(int idVehiculo) {
		// TODO Auto-generated method stub
		vehiculoRepository.deleteById(idVehiculo);
	}
	
	
	private void map(Vehiculo modificado, Vehiculo preModificado ){

        if(!modificado.getPatente().isEmpty()) {
            preModificado.setPatente(modificado.getPatente());
        }
        if (!modificado.getModelo().isEmpty()) {
            preModificado.setModelo(modificado.getModelo());
        }
        if ( modificado.getMarca() != null) {
        	preModificado.setMarca(modificado.getMarca());
        }
        if ( modificado.getCliente() != null) {
        	preModificado.setCliente(modificado.getCliente());
        }
	}
	
}
