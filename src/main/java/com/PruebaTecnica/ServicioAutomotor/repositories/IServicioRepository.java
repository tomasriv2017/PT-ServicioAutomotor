package com.PruebaTecnica.ServicioAutomotor.repositories;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.PruebaTecnica.ServicioAutomotor.models.Servicio;

@Repository("servicioRepository")
public interface IServicioRepository extends JpaRepository<Servicio, Serializable>  {
	
	
}
