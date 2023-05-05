package com.PruebaTecnica.ServicioAutomotor.repositories;

import java.io.Serializable;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.PruebaTecnica.ServicioAutomotor.models.Marca;

@Repository("marcaRepository")
public interface IMarcaRepository  extends JpaRepository<Marca, Serializable> {
	
	public Optional<Marca> findMarcaByMarca(@Param("marca") String marca); //Marca seria el nombre de la misma

}
