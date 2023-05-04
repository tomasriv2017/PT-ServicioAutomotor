package com.PruebaTecnica.ServicioAutomotor.models;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "vehiculo")
public class Vehiculo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idVehiculo;
	
	@Column(name="patente", nullable=false, length=7) //FORMATO 'AAA111' 6 CARACTERES O FORMATO 'AA111AA' 7 CARACTERES
	private String patente;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="idCliente",nullable=false)
	private Cliente cliente;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="idMarca",nullable=false)
	private Marca marca;
	
	@Column(name="createdat")
	@CreationTimestamp
	private LocalDateTime createdat;	
	
	@Column(name="updatedat")
	@UpdateTimestamp
	private LocalDateTime updatedat;
	
	
	public Vehiculo() {
		super();
	} //CONSTRUCTOR VACIO

	
	public Vehiculo(String patente, Cliente cliente, Marca marca) {
		super();
		this.patente = patente;
		this.cliente = cliente;
		this.marca = marca;
	}//CONSTRUCTOR

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
				+ marca + ", createdat=" + createdat + ", updatedat=" + updatedat + "]";
	}

		
}
