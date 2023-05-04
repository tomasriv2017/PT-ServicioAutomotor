package com.PruebaTecnica.ServicioAutomotor.models;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Table(name = "servicio")
@Inheritance( strategy = InheritanceType.JOINED)
public class Servicio {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected int idServicio;
	
	@Column(name="createdat",  nullable = false)
	@CreationTimestamp
	private LocalDateTime createdat;	
	
	@Column(name="updatedat",  nullable = false)
	@UpdateTimestamp
	private LocalDateTime updatedat;
	
	@Column(name= "precio", nullable = false)
	private double precio;

	public Servicio() {
		super();
	}//CONSTRUCTOR VACIO
	

	public Servicio(double precio) {
		super();
		this.precio = precio;
	}//CONSTRUCTOR


	public int getIdServicio() {
		return idServicio;
	}


	public void setIdServicio(int idServicio) {
		this.idServicio = idServicio;
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


	public double getPrecio() {
		return precio;
	}


	public void setPrecio(double precio) {
		this.precio = precio;
	}


	public Servicio(int idServicio, LocalDateTime createdat, LocalDateTime updatedat, double precio) {
		super();
		this.idServicio = idServicio;
		this.createdat = createdat;
		this.updatedat = updatedat;
		this.precio = precio;
	}
	
	
	
}
