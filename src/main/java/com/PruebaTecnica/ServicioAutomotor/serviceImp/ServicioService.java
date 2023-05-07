package com.PruebaTecnica.ServicioAutomotor.serviceImp;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PruebaTecnica.ServicioAutomotor.interfaceService.IServicioService;
import com.PruebaTecnica.ServicioAutomotor.models.AceiteYFiltro;
import com.PruebaTecnica.ServicioAutomotor.models.AlineacionYBalanceo;
import com.PruebaTecnica.ServicioAutomotor.models.Lavado;
import com.PruebaTecnica.ServicioAutomotor.models.Servicio;
import com.PruebaTecnica.ServicioAutomotor.repositories.IServicioRepository;

@Service
public class ServicioService implements IServicioService{

	@Autowired
	private IServicioRepository serviceRepository;
	
	//Lista auxiliar donde se guardan los servicio  que se le agregaran a la orden, a la hora de crearla
	List<Servicio> serviciosAux = new ArrayList<>();
	
	
	@Override
	public Optional<Servicio> traerServicioByID(int idServicio) {
		// TODO Auto-generated method stub
		return serviceRepository.findById(idServicio);
	}

	@Override
	public List<Servicio> listarServicios() {
		// TODO Auto-generated method stub
		return serviceRepository.findAll();
	}	
	
	@Override
	public List<Lavado> listarLavados() {
		// TODO Auto-generated method stub
		List<Lavado> lavados = new ArrayList<>();
		for (Servicio servicio : serviceRepository.findAll()) {
			if(servicio instanceof Lavado) {
				lavados.add((Lavado)servicio);
			}
		}
		return lavados;
	}

	@Override
	public List<AceiteYFiltro> listarPorAceiteYFiltro() {
		// TODO Auto-generated method stub
		List<AceiteYFiltro> aceitesYFiltros = new ArrayList<>();
		for (Servicio servicio : serviceRepository.findAll() ) {
			if(servicio instanceof AceiteYFiltro) {
				aceitesYFiltros.add((AceiteYFiltro)servicio);
			}
		}
		return aceitesYFiltros;
	}

	@Override
	public List<AlineacionYBalanceo> listarPorAlineacionYBalanceo() {
		// TODO Auto-generated method stub
		List<AlineacionYBalanceo> alineacionYBalanceos = new ArrayList<>();
		
		for (Servicio servicio : serviceRepository.findAll() ) {
			if(servicio instanceof AlineacionYBalanceo) {
				alineacionYBalanceos.add((AlineacionYBalanceo)servicio);
			}
		}
		return alineacionYBalanceos;	
	}

	@Override
	public void guardarServicioEncontradoEnListAux(Servicio servicio) throws Exception {
		// TODO Auto-generated method stub
			boolean encontrado = false;
			Iterator<Servicio> iterator = serviciosAux.iterator();
			while(!encontrado && iterator.hasNext()) {
				Servicio s = iterator.next();
				if(s.getIdServicio() == servicio.getIdServicio()) {
					encontrado = true;
				}
			}
			
			if(encontrado) throw new Exception("El servicio ya se encuentra agregado al turno");
			serviciosAux.add(servicio);

	}

	@Override
	public Servicio buscarServicioPorDescripcion(String descripcion) throws Exception {
		// TODO Auto-generated method stub
		Optional<Servicio> servicioBuscado = serviceRepository.findServicioByDescripcion(descripcion);
		if(!servicioBuscado.isPresent()) throw new Exception("El servicio buscado no existe");
		return servicioBuscado.get();
	}

	@Override
	public List<Servicio> buscarTodosServicioDeListAux() {
		// TODO Auto-generated method stub
		return serviciosAux;
	}

	@Override
	public void borrarTodosServiciosDeListAux() {
		// TODO Auto-generated method stub
		serviciosAux.clear(); //Limpia la lista auxiliar una vez finalizada la operacion de agregar un turno
	}

	
}
