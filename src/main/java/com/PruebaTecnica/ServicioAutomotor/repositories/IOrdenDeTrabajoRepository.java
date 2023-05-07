package com.PruebaTecnica.ServicioAutomotor.repositories;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.PruebaTecnica.ServicioAutomotor.models.OrdenDeTrabajo;

@Repository("ordenDeTrabajoRepository")
public interface IOrdenDeTrabajoRepository extends JpaRepository<OrdenDeTrabajo, Serializable>{
	
	@Query("SELECT odt FROM OrdenDeTrabajo odt JOIN FETCH odt.vehiculo v WHERE v.cliente.idCliente = (:idCliente)")
	public List<OrdenDeTrabajo> listOrdenDeTrabajoByCliente(@Param("idCliente") int idCliente);	
	
	@Query("SELECT odt FROM OrdenDeTrabajo odt  JOIN FETCH odt.vehiculo v WHERE v.idVehiculo = (:idVehiculo)")
	public List<OrdenDeTrabajo> listOrdenDeTrabajoByVehiculo(@Param("idVehiculo") int idVehiculo);

}
