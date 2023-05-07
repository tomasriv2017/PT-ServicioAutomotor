package com.PruebaTecnica.ServicioAutomotor.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;


@Entity
@Table(name="aceite_y_filtro")
@PrimaryKeyJoinColumn(name = "id_aceite_y_filtro")
public class AceiteYFiltro extends Servicio {
	
	
    @Column(name="tipo_servicio", nullable = false )
    @Enumerated(value = EnumType.ORDINAL)
	private  TipoServicio tipoServicio;

	public AceiteYFiltro() {
		super();
	} //CONSTRUCTOR VACIO


	 public AceiteYFiltro(String descripcion, double precio, TipoServicio tipoServicio) {
		super(descripcion, precio);
		this.tipoServicio = tipoServicio;
	}//CONSTRUCTOR
	 

	public enum TipoServicio {
	        BASICO,
	        ALTO_RENDIMIENTO
	 }//PERMITE ESCALABILIDAD
	
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



    
    
    
}