package com.PruebaTecnica.ServicioAutomotor.models;

import java.time.LocalDateTime;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.beans.factory.annotation.Value;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "cliente")
public class Cliente {	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idCliente;
	
	@Column(name="dni", nullable=false)
	private long dni;
	
	@Column(name="nombre", nullable=false, length=45)
	private String nombre;	

	@Column(name="apellido", nullable=false, length=45)
	private String apellido;

	@Column(name = "cantServicios", columnDefinition = "int default 0")
	private int cantServicios;

	@OneToMany(cascade = CascadeType.ALL,fetch=FetchType.EAGER, mappedBy="cliente")
	private Set<Vehiculo> vehiculos;
	
	
	@Column(name="createdat")
	@CreationTimestamp
	private LocalDateTime createdat;	
	
	@Column(name="updatedat")
	@UpdateTimestamp
	private LocalDateTime updatedat;

	
	public Cliente() {
		super();
	} //CONSTRUCTOR VACIO


	public Cliente(long dni, String nombre, String apellido, Set<Vehiculo> vehiculos) {
		super();
		this.dni = dni;
		this.nombre = nombre;
		this.apellido = apellido;
		this.vehiculos = vehiculos;
	}//CONSTRUCTOR


	public int getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}
	public long getDni() {
		return dni;
	}
	public void setDni(long dni) {
		this.dni = dni;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public int getCantServicios() {
		return cantServicios;
	}
	public void setCantServicios(int cantServicios) {
		this.cantServicios = cantServicios;
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

	public Set<Vehiculo> getVehiculos() {
		return vehiculos;
	}
	public void setVehiculos(Set<Vehiculo> vehiculos) {
		this.vehiculos = vehiculos;
	}


	@Override
	public String toString() {
		return "Cliente [idCliente=" + idCliente + ", dni=" + dni + ", nombre=" + nombre + ", apellido=" + apellido
				+ ", cntServicios=" + cantServicios + ", createdat=" + createdat + ", updatedat="
				+ updatedat + "]";
	}
	
	
	
}