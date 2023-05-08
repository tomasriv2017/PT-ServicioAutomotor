package com.PruebaTecnica.serviceImpTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.PruebaTecnica.ServicioAutomotor.models.Cliente;
import com.PruebaTecnica.ServicioAutomotor.models.OrdenDeTrabajo;
import com.PruebaTecnica.ServicioAutomotor.models.Servicio;
import com.PruebaTecnica.ServicioAutomotor.models.Vehiculo;
import com.PruebaTecnica.ServicioAutomotor.repositories.IClienteRepository;
import com.PruebaTecnica.ServicioAutomotor.repositories.IOrdenDeTrabajoRepository;
import com.PruebaTecnica.ServicioAutomotor.repositories.IServicioRepository;
import com.PruebaTecnica.ServicioAutomotor.repositories.IVehiculoRepository;
import com.PruebaTecnica.ServicioAutomotor.serviceImp.OrdenDeTrabajoService;

public class OrdenDeTrabajoServiceTest {
	
	@Mock
	private IOrdenDeTrabajoRepository ordenDeTrabajoRepository;
	
	@Mock
	private IServicioRepository servicioRepository;

	@Mock
	private IVehiculoRepository vehiculoRepository;
	
	@Mock
	private IClienteRepository clienteRepository;
	
	@InjectMocks
	private OrdenDeTrabajoService ordenDeTrabajoService;
	
	@BeforeEach  //ABARCA PARA TODOS LOS TESTS (REPERCUTA EN TODOS LOS TESTS)
	public void setup() {
		MockitoAnnotations.openMocks(this);	
	}
	
	
	@Test
	public void listarTest() {
		List<OrdenDeTrabajo> odtLista = new ArrayList<>();
		odtLista.add(hardcodearOrdenDeTabajo().get());
		Mockito.when(ordenDeTrabajoRepository.findAll()).thenReturn(odtLista); //SIMULA LA FUNCION DEL REPOSITORIO findById
		List<OrdenDeTrabajo> odtListActual = ordenDeTrabajoService.listar();
		assertEquals(odtLista, odtListActual);
	}
	
	@Test
	public void listarByClienteTest() {
		List<OrdenDeTrabajo> odtLista = new ArrayList<>();
		odtLista.add(hardcodearOrdenDeTabajo().get());
		Mockito.when(ordenDeTrabajoRepository.listOrdenDeTrabajoByCliente(1)).thenReturn(odtLista); //SIMULA LA FUNCION DEL REPOSITORIO findById
		List<OrdenDeTrabajo> odtListActual = ordenDeTrabajoService.listarByCliente(1);
		assertEquals(odtLista, odtListActual);
	}
	
	
	@Test
	public void listarByVehiculoTest() {
		List<OrdenDeTrabajo> odtLista = new ArrayList<>();
		odtLista.add(hardcodearOrdenDeTabajo().get());
		Mockito.when(ordenDeTrabajoRepository.listOrdenDeTrabajoByVehiculo(1)).thenReturn(odtLista); //SIMULA LA FUNCION DEL REPOSITORIO findById
		List<OrdenDeTrabajo> odtListActual = ordenDeTrabajoService.listarByVehiculo(1);
		assertEquals(odtLista, odtListActual);
	}
	
	
	
	@Test
	public void traerByIdTest() {
		Optional<OrdenDeTrabajo> odt = hardcodearOrdenDeTabajo();
		Mockito.when(ordenDeTrabajoRepository.findById(1)).thenReturn(odt); //SIMULA LA FUNCION DEL REPOSITORIO findById
		Optional<OrdenDeTrabajo> odtActual = ordenDeTrabajoService.traerById(1);
		
		assertEquals(odtActual, odt);
	}
	
	@Test
	public void saveTest() throws Exception {	
		Mockito.when(servicioRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(hardcodearOrdenDeTabajo().get().getServicios().get(0)));
		Mockito.when(vehiculoRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(hardcodearOrdenDeTabajo().get().getVehiculo()));
		Mockito.when(vehiculoRepository.save(Mockito.any())).thenReturn(Optional.of(hardcodearOrdenDeTabajo().get().getVehiculo().getCliente()));
		Mockito.when(ordenDeTrabajoRepository.save(Mockito.any())).thenReturn(hardcodearOrdenDeTabajo().get());
		
		OrdenDeTrabajo odtActual = ordenDeTrabajoService.saveOrUpdate(hardcodearOrdenDeTabajo().get());
		assertEquals(hardcodearOrdenDeTabajo().get(), odtActual);
	}

	@Test
	public void updateTest() throws Exception {	
	Mockito.when(ordenDeTrabajoRepository.findById(Mockito.anyInt())).thenReturn(hardcodearOrdenDeTabajo()); //SIMULA LA FUNCION DEL REPOSITORIO findById
	Mockito.when(servicioRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(hardcodearOrdenDeTabajo().get().getServicios().get(0))); //SIMULA LA FUNCION DEL REPOSITORIO findById
	Mockito.when(vehiculoRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(hardcodearOrdenDeTabajo().get().getVehiculo()));
	Mockito.when(vehiculoRepository.save(Mockito.any())).thenReturn(Optional.of(hardcodearOrdenDeTabajo().get().getVehiculo().getCliente()));
	Mockito.when(ordenDeTrabajoRepository.save(Mockito.any())).thenReturn(hardcodearOrdenDeTabajo().get()); //SIMULA LA FUNCION DEL REPOSITORIO findById
	
	OrdenDeTrabajo odtActual = ordenDeTrabajoService.saveOrUpdate(hardcodearOrdenDeTabajo().get());
	assertEquals(hardcodearOrdenDeTabajo().get(), odtActual);
	}
	
	
	@Test
	public void deleteTest() {
		Mockito.doNothing().when(ordenDeTrabajoRepository).deleteById(1);
		ordenDeTrabajoService.delete(1);
		Mockito.verify(ordenDeTrabajoRepository).deleteById(1);	
	}
	
	
	
	
	
	
	public Optional<OrdenDeTrabajo> hardcodearOrdenDeTabajo() {
		Servicio servicio = new Servicio();
		servicio.setIdServicio(1);
		List<Servicio> serviciosList = new ArrayList<>();
		serviciosList.add(servicio);
		
		Cliente cliente = new Cliente();
		cliente.setIdCliente(1);
		cliente.setDni(41767737);
		cliente.setEsPremium(true);
		
		Vehiculo vehciulo = new Vehiculo();
		vehciulo.setIdVehiculo(1);
		vehciulo.setPatente("AA111AA");
		vehciulo.setCliente(cliente);
		
		Optional<OrdenDeTrabajo> odt = Optional.of(new OrdenDeTrabajo());
		odt.get().setIdOrdenDeTrabajo(1);
		odt.get().setFechaHora(new Date());
		odt.get().setServicios(serviciosList);
		odt.get().setVehiculo(vehciulo);
		odt.get().setTotal(1000);
		odt.get().setCreatedat(LocalDateTime.now());
		odt.get().setCreatedat(LocalDateTime.now());
		return odt;
	}

}
