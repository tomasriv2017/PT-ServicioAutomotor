package com.PruebaTecnica.ServicioAutomotor.interfaceService;

import java.util.List;
import java.util.Optional;

import com.PruebaTecnica.ServicioAutomotor.models.Cliente;
import com.PruebaTecnica.ServicioAutomotor.models.Marca;
import com.PruebaTecnica.ServicioAutomotor.models.Vehiculo;

public interface IVehiculoService {
	
	public List<Vehiculo> listar();
	public Optional<Vehiculo>traerById(int id);
	public Optional<Vehiculo>traerVehiculoByPatente(String patente);
	public Optional<Vehiculo>traerVheiculoByMarca(Marca marca);
	public Optional<Vehiculo>traerVheiculoByCliente(Cliente cliente);
	public Vehiculo saveOrUpdate(Vehiculo vehiculo);
	public void delete(int idVehiculo);
	
}
