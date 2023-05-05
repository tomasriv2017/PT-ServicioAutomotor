package com.PruebaTecnica.ServicioAutomotor.models;

import java.time.LocalDateTime;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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


	@ManyToMany
	@JoinTable(name = "servicio_orden_trabajo",joinColumns = @JoinColumn(name="id_servicio"), inverseJoinColumns = @JoinColumn(name="id_orden_de_trabajo") )
	@JsonIgnore
	private Set<OrdenDeTrabajo> ordenes;
	
	public Servicio() {
		super();
	}//CONSTRUCTOR VACIO
	

	public Servicio(double precio) {
		super();
		this.precio = precio;
	}
	//CONSTRUCTOR


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

	public Set<OrdenDeTrabajo> getOrdenes() {
		return ordenes;
	}


	public void setOrdenes(Set<OrdenDeTrabajo> ordenes) {
		this.ordenes = ordenes;
	}


	@Override
	public String toString() {
		return "Servicio [idServicio=" + idServicio + ", createdat=" + createdat + ", updatedat=" + updatedat
				+ ", precio=" + precio + "]";
	}
	
	
	
	
	
}
