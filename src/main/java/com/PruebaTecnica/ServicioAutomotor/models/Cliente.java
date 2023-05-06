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
@Table(name = "cliente")
public class Cliente {	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idCliente;
	
	@Column(name="email", nullable=false)
	private String email;
	
	@Column(name="dni", nullable=false)
	private long dni;
	
	@Column(name="nombre", nullable=false, length=45)
	private String nombre;	

	@Column(name="apellido", nullable=false, length=45)
	private String apellido;

	@Column(name = "cant_servicios", columnDefinition = "int default 0")
	private int cantServicios;

	@OneToMany(cascade = CascadeType.ALL,fetch=FetchType.LAZY, mappedBy="cliente")
	@JsonIgnore
	private Set<Vehiculo> vehiculos;
	
	
	@Column(name="createdat",  nullable = false)
	@CreationTimestamp
	private LocalDateTime createdat;	
	
	@Column(name="updatedat",  nullable = false)
	@UpdateTimestamp
	private LocalDateTime updatedat;

	
	public Cliente() {
		super();
	} //CONSTRUCTOR VACIO


	public Cliente(long dni, String nombre, String apellido, String email) {
		super();
		this.dni = dni;
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
//		this.vehiculos = vehiculos;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	@Override
	public String toString() {
		return "Cliente [idCliente=" + idCliente + ", dni=" + dni + ", nombre=" + nombre + ", apellido=" + apellido
				+ ", email=" + email + ", cntServicios=" + cantServicios + ", createdat=" + createdat + ", updatedat="
				+ updatedat + "]";
	}
	
	
	
}
