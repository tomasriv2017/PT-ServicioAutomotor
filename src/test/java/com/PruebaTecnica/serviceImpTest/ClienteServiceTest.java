package com.PruebaTecnica.serviceImpTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;import java.io.CharConversionException;
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

import com.PruebaTecnica.ServicioAutomotor.models.Cliente;
import com.PruebaTecnica.ServicioAutomotor.models.Vehiculo;
import com.PruebaTecnica.ServicioAutomotor.repositories.IClienteRepository;
import com.PruebaTecnica.ServicioAutomotor.serviceImp.ClienteService;
import com.PruebaTecnica.ServicioAutomotor.serviceImp.VehiculoService;

public class ClienteServiceTest {
	@Mock
	public IClienteRepository clienteRepository;
	
	@Mock
	public VehiculoService vehiculoService;
	
	@InjectMocks
	public ClienteService clienteService;
	
	
	@BeforeEach  //ABARCA PARA TODOS LOS TESTS (REPERCUTA EN TODOS LOS TESTS)
	public void setup() {
		MockitoAnnotations.openMocks(this);	
	}
		
	@Test
	public void listartTest() {
		List<Cliente> listaClientes = new ArrayList<>();
		Cliente clientePrueba = hardcodearCliente().get();
		listaClientes.add(clientePrueba);
		
		Mockito.when(clienteRepository.findAll()).thenReturn(listaClientes); //SIMULA LA FUNCION DEL REPOSITORIO findAll
		List<Cliente> listaClienteEsperada = clienteService.listar();
		
		assertEquals(listaClienteEsperada, listaClientes);
	
	}
	
	
	@Test
	public void traerByIdTest() {	
		Mockito.when(clienteRepository.findById(Mockito.anyInt())).thenReturn(hardcodearCliente()); //SIMULA LA FUNCION DEL REPOSITORIO findById
		Optional<Cliente> clienteEsperadoPorId = clienteService.traerById(Mockito.anyInt());
		assertEquals(clienteEsperadoPorId, hardcodearCliente());
		
	}
	
	
	@Test
	public void traerByDniTest() {	
		Mockito.when(clienteRepository.findClienteByDni(Mockito.anyLong())).thenReturn(hardcodearCliente()); //SIMULA LA FUNCION DEL REPOSITORIO findById
		Optional<Cliente> clienteEsperado = clienteService.traerByDni(Mockito.anyLong());
		
		Optional<Cliente> clienteActual = Optional.of(new Cliente()) ;
		clienteActual.get().setIdCliente(2);
		clienteActual.get().setApellido("Perez");
		clienteActual.get().setNombre("Jorge");
		clienteActual.get().setDni(4444444);
		clienteActual.get().setCantServicios(4);
		clienteActual.get().setEsPremium(false);
		clienteActual.get().setVehiculos(new HashSet<Vehiculo>());
		clienteActual.get().setCreatedat(LocalDateTime.now());
		clienteActual.get().setUpdatedat(LocalDateTime.now());
		
		assertNotEquals(clienteEsperado, clienteActual);		
	}


	
	@Test
	public void validateDeleteTest() {
		Vehiculo vehiculo = new Vehiculo();
		vehiculo.setIdVehiculo(1);
		vehiculo.setCliente(hardcodearCliente().get());
		List<Vehiculo> vehiculosList = new ArrayList<>();
		vehiculosList.add(vehiculo);

		Mockito.when(clienteRepository.findById(1)).thenReturn(hardcodearCliente()); //SIMULA LA FUNCION DEL REPOSITORIO findById
		Mockito.when(vehiculoService.traerVehiculosByCliente(hardcodearCliente().get())).thenReturn(vehiculosList); //SIMULA LA FUNCION DEL REPOSITORIO findById
				
		Mockito.doNothing().when(vehiculoService).delete(1);

		clienteService.delete(1);
		Mockito.verify(clienteRepository).deleteById(1);
		
	}
	
	
//	@Test
//	public void validateSaveTest() {
//		Cliente clienteNuevo = new Cliente();
//		clienteNuevo.setIdCliente(2);
//		clienteNuevo.setApellido("Bondar");
//		clienteNuevo.setNombre("Mario");
//		clienteNuevo.setEmail("mario@mail.com");
//		clienteNuevo.setDni(55555555);
//		clienteNuevo.setCantServicios(5);
//		clienteNuevo.setEsPremium(false);
//		clienteNuevo.setVehiculos(new HashSet<Vehiculo>());
//		clienteNuevo.setCreatedat(LocalDateTime.now());
//		clienteNuevo.setUpdatedat(LocalDateTime.now());
//	
//		
//		clienteService.saveOrUpdate(clienteNuevo);
//		Mockito.verify(clienteRepository).save(clienteNuevo);
//	}
	
//	@Test
//	public void validateUpdateTest() {
//		Mockito.when(clienteRepository.findById(1)).thenReturn(hardcodearCliente()); //SIMULA LA FUNCION DEL REPOSITORIO findById
//		Cliente clienteNuevo = hardcodearCliente().get();
//		clienteService.saveOrUpdate(clienteNuevo);
//		Mockito.verify(clienteRepository).save(clienteNuevo);
//	}
	
	 @Test
	 public void saveTest() {
		Mockito.when(clienteRepository.save(Mockito.any())).thenReturn(hardcodearCliente().get());
		Cliente clienteActual = clienteService.saveOrUpdate(hardcodearCliente().get());

		assertEquals(hardcodearCliente().get(), clienteActual );
		
	 }
	
	 @Test
	 public void updateTest() {
		Mockito.when(clienteRepository.findById(1)).thenReturn(hardcodearCliente());
		Mockito.when(clienteRepository.save(Mockito.any())).thenReturn(hardcodearCliente().get());
		Cliente clienteActual = clienteService.saveOrUpdate(hardcodearCliente().get());
		
		assertEquals(hardcodearCliente().get(), clienteActual );
		
	 }

	
	
	
	
	private static Optional<Cliente> hardcodearCliente() {
		
		Optional<Cliente> clientePrueba = Optional.of(new Cliente()) ;
		clientePrueba.get().setIdCliente(1);
		clientePrueba.get().setApellido("Rivera");
		clientePrueba.get().setNombre("Tomas");
		clientePrueba.get().setEmail("tomasRiv@gmail.com");
		clientePrueba.get().setDni(11111111);
		clientePrueba.get().setCantServicios(1);
		clientePrueba.get().setEsPremium(false);
		clientePrueba.get().setVehiculos(new HashSet<Vehiculo>());
		clientePrueba.get().setCreatedat(LocalDateTime.now());
		clientePrueba.get().setUpdatedat(LocalDateTime.now());
		
		return clientePrueba;
	}
	
	


}
