package com.PruebaTecnica.ServicioAutomotor.repositories;

import java.io.Serializable;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.PruebaTecnica.ServicioAutomotor.models.Cliente;

@Repository("userRepository")
public interface IClienteRepository  extends JpaRepository<Cliente, Serializable> {
	
	public Optional<Cliente> findClienteByDni(@Param("dni") long dni);
}
