package com.PruebaTecnica.ServicioAutomotor.interfaceService;

import java.util.List;
import java.util.Optional;

import com.PruebaTecnica.ServicioAutomotor.models.Marca;

public interface IMarcaService {
	
	public List<Marca> listar();
	public Optional<Marca>traerById(int id);
	public Optional<Marca>traerByMarca(String marca);
	public Marca saveOrUpdate(Marca marca);
	public void delete(int idMarca);
}
