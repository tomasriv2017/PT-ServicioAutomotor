package com.PruebaTecnica.ServicioAutomotor.models;

import java.util.Objects;

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

	public TipoServicio getTipoServicio() {
		return tipoServicio;
	}

	public void setTipoServicio(TipoServicio tipoServicio) {
		this.tipoServicio = tipoServicio;
	}


	@Override
	public String toString() {
		 return descripcion;

	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(tipoServicio);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Lavado other = (Lavado) obj;
		return tipoServicio == other.tipoServicio;
	}



	
}
