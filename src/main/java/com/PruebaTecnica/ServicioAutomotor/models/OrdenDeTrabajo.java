package com.PruebaTecnica.ServicioAutomotor.models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Entity
@Table(name = "orden_de_trabajo")
public class OrdenDeTrabajo {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idOrdenDeTrabajo;
	
	@Column(name="hora",  nullable = false)
	private String hora;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@Column(name="fecha",  nullable = false)
	private LocalDate fecha;
	
	@Column(name="total",  nullable = false)
	private double total;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="id_vehiculo",nullable=false)
	private Vehiculo vehiculo;
	
	@ManyToMany
	@JoinTable(name = "servicio_orden_trabajo",joinColumns = @JoinColumn(name="id_orden_de_trabajo"), inverseJoinColumns = @JoinColumn(name="id_servicio") )
	private List<Servicio> servicios;
	
	
	@Column(name="createdat",  nullable = false)
	@CreationTimestamp
	private LocalDateTime createdat;	
	
	@Column(name="updatedat",  nullable = false)
	@UpdateTimestamp
	private LocalDateTime updatedat;
	
	
	public OrdenDeTrabajo( String hora, LocalDate fecha, Vehiculo vehiculo, List<Servicio> servicios) {
		super();
		this.hora = hora;
		this.fecha = fecha;
		this.vehiculo = vehiculo;
		this.servicios = servicios;
	}//CONSTRUCTOR
	
	
	public OrdenDeTrabajo() {
		super();
	}//CONSTRUCTOR VACIO


	public int getIdOrdenDeTrabajo() {
		return idOrdenDeTrabajo;
	}

	public void setIdOrdenDeTrabajo(int idOrdenDeTrabajo) {
		this.idOrdenDeTrabajo = idOrdenDeTrabajo;
	}

	

	public String getHora() {
		return hora;
	}


	public void setHora(String hora) {
		this.hora = hora;
	}


	public LocalDate getFecha() {
		return fecha;
	}


	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}


	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public Vehiculo getVehiculo() {
		return vehiculo;
	}

	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}

	public LocalDateTime getCreatedat() {
		return createdat;
	}

	public void setCreatedat(LocalDateTime createdat) {
		this.createdat = createdat;
	}

	public LocalDateTime getUpdatedat() {
		return updatedat;
	}

	public void setUpdatedat(LocalDateTime updatedat) {
		this.updatedat = updatedat;
	}

	public List<Servicio> getServicios() {
		return servicios;
	}
	public void setServicios(List<Servicio> servicios) {
		this.servicios = servicios;
	}



//	public Date getFechaYHora() {
//		return fechaYHora;
//	}
//
//
//	public void setFechaYHora(Date fechaYHora) {
//		this.fechaYHora = fechaYHora;
//	}


	@Override
	public String toString() {
		return "OrdenDeTrabajo [idOrdenDeTrabajo=" + idOrdenDeTrabajo + ", hora=" + hora + ", fecha=" + fecha
				+ ", total=" + total + ", vehiculo=" + vehiculo + ", servicios=" + servicios + ", createdat="
				+ createdat + ", updatedat=" + updatedat + "]";
	}

	
}
