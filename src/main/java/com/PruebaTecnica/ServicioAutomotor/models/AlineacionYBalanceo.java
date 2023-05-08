
package com.PruebaTecnica.ServicioAutomotor.models;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;


@Entity
@Table(name="alineacion_y_balanceo")
@PrimaryKeyJoinColumn(name = "id_alineacion_y_balanceo")
public class AlineacionYBalanceo extends Servicio {
	
    @Column(name="tiene_cambio_cubiertas", nullable = false )
	private boolean tieneCambioCubiertas;

    
	public boolean isTieneCambioCubiertas() {
		return tieneCambioCubiertas;
	}

	public void setTieneCambioCubiertas(boolean tieneCambioCubiertas) {
		this.tieneCambioCubiertas = tieneCambioCubiertas;
	}

		
	public AlineacionYBalanceo() {
		super();
	}//CONSTRUCTOR VACIO

	@Override
	public String toString() {
		return  descripcion;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(tieneCambioCubiertas);
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
		AlineacionYBalanceo other = (AlineacionYBalanceo) obj;
		return tieneCambioCubiertas == other.tieneCambioCubiertas;
	}
  
	
	
}	

