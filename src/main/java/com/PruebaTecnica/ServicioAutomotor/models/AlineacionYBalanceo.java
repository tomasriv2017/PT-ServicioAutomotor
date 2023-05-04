
package com.PruebaTecnica.ServicioAutomotor.models;

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

	
	public AlineacionYBalanceo(boolean tieneCambioCubiertas, double precio) {
		super(precio);
		this.tieneCambioCubiertas = tieneCambioCubiertas;
	}//CONSTRUCTOR

	@Override
	public String toString() {
		return "AlineacionYBalanceo [tieneCambioCubiertas=" + tieneCambioCubiertas + "]";
	}
	
    
}	

