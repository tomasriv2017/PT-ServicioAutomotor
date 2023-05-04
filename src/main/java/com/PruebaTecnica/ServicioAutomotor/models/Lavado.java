package com.PruebaTecnica.ServicioAutomotor.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;



@Entity
@Table(name="lavado")
@PrimaryKeyJoinColumn(name = "id_lavado")
public class Lavado  extends Servicio{
	
    public enum TipoServicio {
        BASICO,
        COMPLETO,
        PREMIUM
    }//PERMITE ESCALABILIDAD
	
    @Column(name="tipo_servicio", nullable = false )
    @Enumerated(value = EnumType.ORDINAL)
	private  TipoServicio tipoServicio;
    
	public Lavado() {
		super();
	}//CONSTRUCTOR VACIO

	
	public Lavado(TipoServicio tipoServicio, double precio) {
		super(precio);
		this.tipoServicio = tipoServicio;
	}


	public TipoServicio getTipoServicio() {
		return tipoServicio;
	}

	public void setTipoServicio(TipoServicio tipoServicio) {
		this.tipoServicio = tipoServicio;
	}


	@Override
	public String toString() {
		return "Lavado [tipoServicio=" + tipoServicio + "]";
	}
	
}
