package com.PruebaTecnica.ServicioAutomotor.interfaceService;

import java.util.List;
import java.util.Optional;

import com.PruebaTecnica.ServicioAutomotor.models.Cliente;

public interface IClienteService {
	
	public List<Cliente> listar();
	public Optional<Cliente>traerById(int id);
	public Optional<Cliente>traerByDni(long dni);
	public Cliente saveOrUpdate(Cliente cliente);
	public void delete(int idCliente);
	
}
