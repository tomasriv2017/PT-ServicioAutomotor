package com.PruebaTecnica.ServicioAutomotor.interfaceService;

import java.util.List;
import java.util.Optional;

import com.PruebaTecnica.ServicioAutomotor.models.OrdenDeTrabajo;
import com.PruebaTecnica.ServicioAutomotor.models.Vehiculo;

public interface IOrdenDeTrabajoService {
	public List<OrdenDeTrabajo> listar();
	public List<OrdenDeTrabajo> listarByCliente(int idCliente);
	public Optional<OrdenDeTrabajo>traerById(int id);
	public OrdenDeTrabajo saveOrUpdate(OrdenDeTrabajo ordenDeTrabajo);
	public void delete(int idOrdenDeTrabajo);
}
