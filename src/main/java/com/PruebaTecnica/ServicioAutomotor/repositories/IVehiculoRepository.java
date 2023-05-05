package com.PruebaTecnica.ServicioAutomotor.repositories;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.PruebaTecnica.ServicioAutomotor.models.Cliente;
import com.PruebaTecnica.ServicioAutomotor.models.Marca;
import com.PruebaTecnica.ServicioAutomotor.models.Vehiculo;

@Repository("vehiculoRepository")
public interface IVehiculoRepository extends JpaRepository<Vehiculo, Serializable> {
	
	public Optional<Vehiculo> findVehiculoByPatente(@Param("patente") String patente);

	public List<Vehiculo> findAllByMarca(@Param("marca") Marca marca);

	public List<Vehiculo> findAllByCliente(@Param("cliente") Cliente cliente);
	

}
