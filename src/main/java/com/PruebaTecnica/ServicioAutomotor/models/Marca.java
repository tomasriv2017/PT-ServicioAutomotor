package com.PruebaTecnica.ServicioAutomotor.models;

import java.time.LocalDateTime;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "marca")
public class Marca {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idMarca;
	
	@Column(name="nombre_marca", nullable=false, length=45)
	private String nombreMarca;
	
	@OneToMany(cascade = CascadeType.ALL,fetch=FetchType.LAZY, mappedBy="marca")
	@JsonIgnore
	private Set<Vehiculo> vehiculos;
	
	@Column(name="createdat", nullable= false)
	@CreationTimestamp
	private LocalDateTime createdat;	
	
	@Column(name="updatedat" ,nullable= false)
	@UpdateTimestamp
	private LocalDateTime updatedat;
	
	public Marca() {
		super();
	}//CONSTRUCTOR VACIO

	
	public Marca(String nombreMarca) {
		super();
		this.nombreMarca = nombreMarca;
	}//CONSTRUCTOR


	public int getIdMarca() {
		return idMarca;
	}

	public void setIdMarca(int idMarca) {
		this.idMarca = idMarca;
	}

	

	public String getNombreMarca() {
		return nombreMarca;
	}


	public void setNombreMarca(String nombreMarca) {
		this.nombreMarca = nombreMarca;
	}


	public Set<Vehiculo> getVehiculos() {
		return vehiculos;
	}

	public void setVehiculos(Set<Vehiculo> vehiculos) {
		this.vehiculos = vehiculos;
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

	@Override
	public String toString() {
		return "Marca [idMarca=" + idMarca + ", nombreMarca=" + nombreMarca + ", createdat=" + createdat + ", updatedat="
				+ updatedat + "]";
	}
	
}
