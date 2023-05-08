package com.PruebaTecnica.serviceImpTest;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.PruebaTecnica.ServicioAutomotor.models.AceiteYFiltro;
import com.PruebaTecnica.ServicioAutomotor.models.AlineacionYBalanceo;
import com.PruebaTecnica.ServicioAutomotor.models.Lavado;
import com.PruebaTecnica.ServicioAutomotor.models.OrdenDeTrabajo;
import com.PruebaTecnica.ServicioAutomotor.models.Servicio;
import com.PruebaTecnica.ServicioAutomotor.models.Lavado.TipoServicio;
import com.PruebaTecnica.ServicioAutomotor.repositories.IServicioRepository;
import com.PruebaTecnica.ServicioAutomotor.serviceImp.ServicioService;

public class ServicioServiceTest {
	@Mock
	public IServicioRepository serviceRepository;

	@InjectMocks
	public ServicioService servicioService;
	
	
	@BeforeEach  //ABARCA PARA TODOS LOS TESTS (REPERCUTA EN TODOS LOS TESTS)
	public void setup() {
		MockitoAnnotations.openMocks(this);	
	}
	
	@Test
	public void traerServicioByIDTest() {
		Mockito.when(serviceRepository.findById(1)).thenReturn(hardcodearServicio()); //SIMULA LA FUNCION DEL REPOSITORIO findAll
		Optional<Servicio> servicioActual = servicioService.traerServicioByID(1);
		assertEquals(hardcodearServicio(), servicioActual);
	
	}
	
	@Test
	public void listarServiciosTest() {
		List<Servicio> serviciosListEsperada = new ArrayList<>();
		serviciosListEsperada.add(hardcodearServicio().get());

		Mockito.when(serviceRepository.findAll()).thenReturn(serviciosListEsperada); //SIMULA LA FUNCION DEL REPOSITORIO findAll
		List<Servicio> servicioListaActual = servicioService.listarServicios();
		assertEquals(serviciosListEsperada, servicioListaActual);
	}
	
	@Test
	public void listarLavadosTest() {
		List<Servicio> lavadosListEsperada = new ArrayList<>();
		lavadosListEsperada.add(hardcodearLavado().get());

		Mockito.when(serviceRepository.findAll()).thenReturn(lavadosListEsperada); //SIMULA LA FUNCION DEL REPOSITORIO findAll
		List<Lavado> lavadoListaActual = servicioService.listarLavados();
		assertEquals(lavadosListEsperada, lavadoListaActual);
	}
	
	@Test
	public void listarPorAceiteYFiltroTest() {
		List<Servicio> aceiteYFiltroListEsperada = new ArrayList<>();
		aceiteYFiltroListEsperada.add(hardcodearAceiteYFiltro().get());

		Mockito.when(serviceRepository.findAll()).thenReturn(aceiteYFiltroListEsperada); //SIMULA LA FUNCION DEL REPOSITORIO findAll
		List<AceiteYFiltro> aceiteYFiltroListaActual = servicioService.listarPorAceiteYFiltro();
		assertEquals(aceiteYFiltroListEsperada, aceiteYFiltroListaActual);
	}
	
	@Test
	public void listarPorAlineacionYBalanceoTest() {
		List<Servicio> alineacionYBalanceoListEsperada = new ArrayList<>();
		alineacionYBalanceoListEsperada.add(hardcodearAlienacionYBalanceo().get());

		Mockito.when(serviceRepository.findAll()).thenReturn(alineacionYBalanceoListEsperada); //SIMULA LA FUNCION DEL REPOSITORIO findAll
		List<AlineacionYBalanceo> alineacionYBalanceoListaActual = servicioService.listarPorAlineacionYBalanceo();
		assertEquals(alineacionYBalanceoListEsperada, alineacionYBalanceoListaActual);
	}
	
	@Test
	public void buscarServicioPorDescripcionTest() throws Exception {
		Mockito.when(serviceRepository.findServicioByDescripcion("LAVADO - BASICO")).thenReturn(hardcodearServicio()); //SIMULA LA FUNCION DEL REPOSITORIO findAll
		Servicio ervicioActual = servicioService.buscarServicioPorDescripcion("LAVADO - BASICO");
		assertEquals(hardcodearServicio().get(), ervicioActual);
	}
	
	@Test
	public void guardarServiciosEncontradoEnListAuxTest() throws Exception {
		assertDoesNotThrow(() -> servicioService.guardarServicioEncontradoEnListAux(hardcodearLavado().get()));
	}
	
	@Test
	public void guardarServiciosEncontradoEnListAuxExecpttionTest() {
		String expectedMessage = "El servicio ya se encuentra agregado al turno";
		try {
			servicioService.guardarServicioEncontradoEnListAux(hardcodearServicio().get());
			servicioService.guardarServicioEncontradoEnListAux(hardcodearServicio().get());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			String actualMessage = e.getMessage();
			assertTrue(actualMessage.contains(expectedMessage));
		}		
	}
	@Test
	public void buscarTodosServicioDeListAuxTest() throws Exception {
		assertDoesNotThrow(() -> servicioService.buscarTodosServicioDeListAux());

	}
	
	@Test
	public void borrarTodosServiciosDeListAux() throws Exception {
		assertDoesNotThrow(() -> servicioService.borrarTodosServiciosDeListAux());

	}
	
	
	
	private static Optional<Servicio> hardcodearServicio() {
		Optional<Servicio> servicio = Optional.of(new Lavado()) ;
		servicio.get().setIdServicio(1);
		servicio.get().setDescripcion("LAVADO - BASICO");
		servicio.get().setPrecio(1000);
		servicio.get().setOrdenes(new HashSet<OrdenDeTrabajo>());
		servicio.get().setCreatedat(LocalDateTime.now());
		servicio.get().setUpdatedat(LocalDateTime.now());
		return servicio;
	}
	
	
	private static Optional<Lavado> hardcodearLavado() {
		Optional<Lavado> lavado = Optional.of(new Lavado()) ;
		lavado.get().setIdServicio(1);
		lavado.get().setDescripcion("LAVADO - BASICO");
		lavado.get().setPrecio(1000);
		lavado.get().setTipoServicio(TipoServicio.BASICO);
		lavado.get().setOrdenes(new HashSet<OrdenDeTrabajo>());
		lavado.get().setCreatedat(LocalDateTime.now());
		lavado.get().setUpdatedat(LocalDateTime.now());
		return lavado;
	}
	
	private static Optional<AceiteYFiltro> hardcodearAceiteYFiltro() {
		Optional<AceiteYFiltro> aceiteYFiltro = Optional.of(new AceiteYFiltro()) ;
		aceiteYFiltro.get().setIdServicio(2);
		aceiteYFiltro.get().setDescripcion("ACEITE Y FILTRO - BASICO");
		aceiteYFiltro.get().setPrecio(1500);
		aceiteYFiltro.get().setTipoServicio(com.PruebaTecnica.ServicioAutomotor.models.AceiteYFiltro.TipoServicio.BASICO);
		aceiteYFiltro.get().setOrdenes(new HashSet<OrdenDeTrabajo>());
		aceiteYFiltro.get().setCreatedat(LocalDateTime.now());
		aceiteYFiltro.get().setUpdatedat(LocalDateTime.now());
		return aceiteYFiltro;
	}
	
	private static Optional<AlineacionYBalanceo> hardcodearAlienacionYBalanceo() {
		Optional<AlineacionYBalanceo> alineacionYBalanceo = Optional.of(new AlineacionYBalanceo());
		alineacionYBalanceo.get().setIdServicio(3);
		alineacionYBalanceo.get().setDescripcion("ALINEACION Y BALANCEO - SIN CAMBIOS");
		alineacionYBalanceo.get().setPrecio(500);
		alineacionYBalanceo.get().setTieneCambioCubiertas(false);
		alineacionYBalanceo.get().setOrdenes(new HashSet<OrdenDeTrabajo>());
		alineacionYBalanceo.get().setCreatedat(LocalDateTime.now());
		alineacionYBalanceo.get().setUpdatedat(LocalDateTime.now());
		return alineacionYBalanceo;
	}
	
	
}
