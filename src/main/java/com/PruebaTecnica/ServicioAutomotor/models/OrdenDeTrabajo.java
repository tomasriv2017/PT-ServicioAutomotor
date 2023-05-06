package com.PruebaTecnica.ServicioAutomotor.models;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

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

@Entity
@Table(name = "orden_de_trabajo")
public class OrdenDeTrabajo {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idOrdenDeTrabajo;

	@DateTimeFormat(pattern="yyyy-MM-dd'T'HH:mm")
    @Column(name="fecha_Hora",  nullable = false)
    private Date fechaHora;
	
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
	
	
	public OrdenDeTrabajo( Date fechaHora, Vehiculo vehiculo, List<Servicio> servicios) {
		super();
		this.fechaHora = fechaHora;
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

	
	
	public Date getFechaHora() {
		return fechaHora;
	}


	public void setFechaHora(Date fechaHora) {
		this.fechaHora = fechaHora;
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


	@Override
	public String toString() {
		return "OrdenDeTrabajo [idOrdenDeTrabajo=" + idOrdenDeTrabajo + ", fechaHora=" + fechaHora + ", total=" + total
				+ ", vehiculo=" + vehiculo + ", servicios=" + servicios + ", createdat=" + createdat + ", updatedat="
				+ updatedat + "]";
	}
	
	
}
