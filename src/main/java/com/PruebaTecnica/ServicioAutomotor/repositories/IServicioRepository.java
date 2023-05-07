package com.PruebaTecnica.ServicioAutomotor.repositories;

import java.io.Serializable;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.PruebaTecnica.ServicioAutomotor.models.Servicio;

@Repository("servicioRepository")
public interface IServicioRepository extends JpaRepository<Servicio, Serializable>  {
	
	public Optional<Servicio> findServicioByDescripcion(@Param("descripcion") String descripcion);
	
}
