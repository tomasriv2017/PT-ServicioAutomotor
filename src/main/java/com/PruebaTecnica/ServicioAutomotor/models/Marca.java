package com.PruebaTecnica.ServicioAutomotor.models;

import java.time.LocalDateTime;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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
	
	@Column(name="marca", nullable=false, length=45)
	private String marca;
	
	@OneToMany(cascade = CascadeType.ALL,fetch=FetchType.EAGER, mappedBy="marca")
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

	
	public Marca(String marca, Set<Vehiculo> vehiculos) {
		super();
		this.marca = marca;
		this.vehiculos = vehiculos;
	}//CONSTRUCTOR


	public int getIdMarca() {
		return idMarca;
	}

	public void setIdMarca(int idMarca) {
		this.idMarca = idMarca;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
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
		return "Marca [idMarca=" + idMarca + ", marca=" + marca + ", createdat=" + createdat + ", updatedat="
				+ updatedat + "]";
	}
	
}
