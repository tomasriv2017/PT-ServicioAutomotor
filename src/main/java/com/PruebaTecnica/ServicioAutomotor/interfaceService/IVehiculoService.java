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
	public List<Vehiculo>traerVehiculosByMarca(Marca marca);
	public List<Vehiculo>traerVehiculosByCliente(Cliente cliente);
	public Vehiculo saveOrUpdate(Vehiculo vehiculo);
	public void delete(int idVehiculo);
	
}
