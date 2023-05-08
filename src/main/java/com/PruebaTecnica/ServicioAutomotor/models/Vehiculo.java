package com.PruebaTecnica.ServicioAutomotor.models;

import java.time.LocalDateTime;
import java.util.Objects;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "vehiculo")
public class Vehiculo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idVehiculo;
	
	@Column(name="patente", nullable=false, length=7) //FORMATO 'AAA111' 6 CARACTERES O FORMATO 'AA111AA' 7 CARACTERES
	private String patente;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_cliente",nullable=false)
	private Cliente cliente;
	
	@Column(name="modelo", nullable=false, length=45) 
	private String modelo;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="id_marca",nullable=false)
	private Marca marca;
	
	@Column(name="createdat", nullable = false)
	@CreationTimestamp
	private LocalDateTime createdat;	
	
	@Column(name="updatedat", nullable = false)
	@UpdateTimestamp
	private LocalDateTime updatedat;
	
	
	public Vehiculo() {
		super();
	} //CONSTRUCTOR VACIO

	public int getIdVehiculo() {
		return idVehiculo;
	}

	public void setIdVehiculo(int idVehiculo) {
		this.idVehiculo = idVehiculo;
	}

	public String getPatente() {
		return patente;
	}

	public void setPatente(String patente) {
		this.patente = patente;
	}
	
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Marca getMarca() {
		return marca;
	}


	public void setMarca(Marca marca) {
		this.marca = marca;
	}

	
	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
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
		return "Vehiculo [idVehiculo=" + idVehiculo + ", patente=" + patente + ", cliente=" + cliente + ", marca="
				+ marca + ", modelo=" + modelo +  ", createdat=" + createdat + ", updatedat=" + updatedat + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(cliente, idVehiculo, patente);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vehiculo other = (Vehiculo) obj;
		return Objects.equals(cliente, other.cliente) && idVehiculo == other.idVehiculo
				&& Objects.equals(patente, other.patente);
	}


	
	
	
	
		
}
