package com.PruebaTecnica.serviceImpTest;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.PruebaTecnica.ServicioAutomotor.models.Cliente;
import com.PruebaTecnica.ServicioAutomotor.models.Marca;
import com.PruebaTecnica.ServicioAutomotor.models.OrdenDeTrabajo;
import com.PruebaTecnica.ServicioAutomotor.models.Vehiculo;
import com.PruebaTecnica.ServicioAutomotor.repositories.IOrdenDeTrabajoRepository;
import com.PruebaTecnica.ServicioAutomotor.repositories.IVehiculoRepository;
import com.PruebaTecnica.ServicioAutomotor.serviceImp.VehiculoService;

public class VehiculoServiceTest {
	@Mock
	public IVehiculoRepository vehiculoRepository;
	
	@Mock
	private IOrdenDeTrabajoRepository ordenRepository;

	@InjectMocks
	public VehiculoService vehiculoService;
	
	
	@BeforeEach  //ABARCA PARA TODOS LOS TESTS (REPERCUTA EN TODOS LOS TESTS)
	public void setup() {
		MockitoAnnotations.openMocks(this);	
	}
	
	@Test
	public void listarTest() {
		List<Vehiculo> vehiculoListaEsperada = new ArrayList<>();
		vehiculoListaEsperada.add(hardcodearVehiculo().get());
		Mockito.when(vehiculoRepository.findAll()).thenReturn(vehiculoListaEsperada);
		
		List<Vehiculo> vehciuloListaActual = vehiculoService.listar();
		assertEquals(vehiculoListaEsperada, vehciuloListaActual);
	}
	
	@Test
	public void traerByIdTest(){
		Mockito.when(vehiculoRepository.findById(1)).thenReturn((hardcodearVehiculo()));
		Optional<Vehiculo> vehiculoActual = vehiculoService.traerById(1);
		assertEquals(hardcodearVehiculo(), vehiculoActual);
	}
	
	@Test
	public void traerVehiculoByPatenteTest(){
		Mockito.when(vehiculoRepository.findVehiculoByPatente("AAA111")).thenReturn((hardcodearVehiculo()));
		Optional<Vehiculo> vehiculoActual = vehiculoService.traerVehiculoByPatente("AAA111");
		assertEquals(hardcodearVehiculo(), vehiculoActual);
	}

	@Test
	public void traerVehiculosByMarcaTest(){
		List<Vehiculo> vehiculoListaEsperada = new ArrayList<>();
		vehiculoListaEsperada.add(hardcodearVehiculo().get());
		Mockito.when(vehiculoRepository.findAllByMarca(Mockito.any())).thenReturn((vehiculoListaEsperada));
		
		List<Vehiculo> vehciuloListaActual = vehiculoService.traerVehiculosByMarca(new Marca());
		assertEquals(vehiculoListaEsperada, vehciuloListaActual);
	}
	@Test
	public void traerVehiculosByClienteTest(){
		List<Vehiculo> vehiculoListaEsperada = new ArrayList<>();
		vehiculoListaEsperada.add(hardcodearVehiculo().get());
		Mockito.when(vehiculoRepository.findAllByCliente(Mockito.any())).thenReturn((vehiculoListaEsperada));
		
		List<Vehiculo> vehciuloListaActual = vehiculoService.traerVehiculosByCliente(new Cliente());
		assertEquals(vehiculoListaEsperada, vehciuloListaActual);
	}
	
	@Test
	public void saveTest(){
		Mockito.when(vehiculoRepository.save(Mockito.any())).thenReturn((hardcodearVehiculo().get()));
		
		Vehiculo vehiculoNuevo = vehiculoService.saveOrUpdate(new Vehiculo());
		assertEquals(hardcodearVehiculo().get(), vehiculoNuevo);
	}
	
	@Test
	public void updateTest(){
		Mockito.when(vehiculoRepository.findById(Mockito.any())).thenReturn((hardcodearVehiculo()));
		Mockito.when(vehiculoRepository.save(Mockito.any())).thenReturn((hardcodearVehiculo().get()));
		
		Vehiculo vehiculoNuevo = vehiculoService.saveOrUpdate(hardcodearVehiculo().get());
		assertEquals(hardcodearVehiculo().get(), vehiculoNuevo);
	}
	
	@Test
	public void deleteTest() {
		OrdenDeTrabajo odt = new OrdenDeTrabajo();
		odt.setIdOrdenDeTrabajo(1);
		odt.setVehiculo(hardcodearVehiculo().get());
		List<OrdenDeTrabajo> odtListaEsperada = new ArrayList<>();
		odtListaEsperada.add(odt);
		
		Mockito.when(ordenRepository.listOrdenDeTrabajoByVehiculo(Mockito.anyInt())).thenReturn(odtListaEsperada);
		Mockito.doNothing().when(ordenRepository).delete(Mockito.any());
		vehiculoService.delete(1);
		Mockito.doNothing().when(vehiculoRepository).deleteById(1);
		assertDoesNotThrow(() -> vehiculoService.delete(1));
	}
	
	
	
	private static Optional<Vehiculo> hardcodearVehiculo() {
		Marca marca = new Marca();
		marca.setIdMarca(1);
		marca.setNombreMarca("Ford");
		Cliente cliente = new Cliente();
		cliente.setIdCliente(1);
		cliente.setDni(11111111);
		
		Optional<Vehiculo> vehiculoPrueba = Optional.of(new Vehiculo());
		vehiculoPrueba.get().setIdVehiculo(1);
		vehiculoPrueba.get().setPatente("AAA111");
		vehiculoPrueba.get().setModelo("Ford Ka");
		vehiculoPrueba.get().setMarca(marca);
		vehiculoPrueba.get().setCliente(cliente);
		vehiculoPrueba.get().setCreatedat(LocalDateTime.now());
		vehiculoPrueba.get().setUpdatedat(LocalDateTime.now());
		
		return vehiculoPrueba;
	}
	
}
