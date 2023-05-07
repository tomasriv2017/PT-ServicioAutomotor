package com.PruebaTecnica.ServicioAutomotor.interfaceService;

import java.util.List;
import java.util.Optional;

import com.PruebaTecnica.ServicioAutomotor.models.AceiteYFiltro;
import com.PruebaTecnica.ServicioAutomotor.models.AlineacionYBalanceo;
import com.PruebaTecnica.ServicioAutomotor.models.Lavado;
import com.PruebaTecnica.ServicioAutomotor.models.Servicio;

public interface IServicioService {
	
	public Optional<Servicio> traerServicioByID(int idServicio);
	public List<Servicio> listarServicios();
	public List<Lavado> listarLavados();
	public List<AceiteYFiltro> listarPorAceiteYFiltro();
	public List<AlineacionYBalanceo> listarPorAlineacionYBalanceo();
	
	public void guardarServicioEncontradoEnListAux(Servicio servicio) throws Exception;
	public Servicio buscarServicioPorDescripcion(String descripcion) throws Exception;
	public List<Servicio> buscarTodosServicioDeListAux();
	public void borrarTodosServiciosDeListAux();
	
	
	
}
