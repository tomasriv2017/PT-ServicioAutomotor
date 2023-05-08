package com.PruebaTecnica.serviceImpTest;

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

import com.PruebaTecnica.ServicioAutomotor.models.Marca;
import com.PruebaTecnica.ServicioAutomotor.models.Vehiculo;
import com.PruebaTecnica.ServicioAutomotor.repositories.IMarcaRepository;
import com.PruebaTecnica.ServicioAutomotor.serviceImp.MarcaService;

public class MarcaServiceTest {
	
	@Mock
	public IMarcaRepository marcaRepository;

	@InjectMocks
	public MarcaService marcaService;
	
	
	@BeforeEach  //ABARCA PARA TODOS LOS TESTS (REPERCUTA EN TODOS LOS TESTS)
	public void setup() {
		MockitoAnnotations.openMocks(this);	
	}
	
	@Test
	public void listarTest() {
		List<Marca> marcaListEsperada = new ArrayList<>();
		marcaListEsperada.add(hardcodearMarca().get());
		Mockito.when(marcaRepository.findAll()).thenReturn(marcaListEsperada);
		
		List<Marca> marcaListActual = marcaService.listar();
		assertEquals(marcaListEsperada, marcaListActual);
	}
	
	@Test
	public void traerByIdTest(){
		Mockito.when(marcaRepository.findById(1)).thenReturn(hardcodearMarca());
		Optional<Marca> marcaActual = marcaService.traerById(1);
		assertEquals(hardcodearMarca(), marcaActual);
	}
	
	@Test
	public void traerByMarcaTest() {
		Mockito.when(marcaRepository.findMarcaByNombreMarca("Ford Test")).thenReturn(hardcodearMarca());
		Optional<Marca> marcaActual = marcaService.traerByMarca("Ford Test");
		assertEquals(hardcodearMarca(), marcaActual);
	}
	
	
	@Test
	public void saveTest() {
		Mockito.when(marcaRepository.save(Mockito.any())).thenReturn(hardcodearMarca().get());
		Marca marcaActual = marcaService.saveOrUpdate(new Marca());
		assertEquals(hardcodearMarca().get(), marcaActual);
	}

	@Test
	public void updateTest() {
		Mockito.when(marcaRepository.findById(Mockito.anyInt())).thenReturn(hardcodearMarca());
		Mockito.when(marcaRepository.save(Mockito.any())).thenReturn(hardcodearMarca().get());
		Marca marcaActual = marcaService.saveOrUpdate(hardcodearMarca().get());
		assertEquals(hardcodearMarca().get(), marcaActual);
	}
	
	
	@Test
	public void deleteTest() {
		Mockito.doNothing().when(marcaRepository).deleteById(1);
		marcaService.delete(1);
		Mockito.verify(marcaRepository).deleteById(1);	
		assertDoesNotThrow(() -> marcaService.delete(1));

	}
	
	
	private static Optional<Marca> hardcodearMarca() {
		Optional<Marca> marcaPrueba = Optional.of(new Marca());
		marcaPrueba.get().setIdMarca(1);
		marcaPrueba.get().setNombreMarca("Ford Test");
		marcaPrueba.get().setVehiculos(new HashSet<Vehiculo>());
		marcaPrueba.get().setCreatedat(LocalDateTime.now());
		marcaPrueba.get().setUpdatedat(LocalDateTime.now());
		
		return marcaPrueba;
	}
	
}
