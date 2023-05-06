package com.PruebaTecnica.ServicioAutomotor.helpers;

public class ViewRouterHelpers {
	/**** Views ****/
	
	//ACCESO
	public final static String INDEX_VISTA =  "home/index";
	
	//CLIENTE
	public final static String CLIENTE_AGREGAR = "cliente/agregarCliente";
	
	//VEHICULO
	public final static String VEHICULO_VISTA =  "vehiculo/vehiculoVista";
	public final static String VEHICULO_AGREGAR = "vehiculo/agregarVehiculo";
	
	//SERVICIO
	public final static String SERVICIO_VISTA = "servicio/servicioVista";
	
	//ORDEN DE TRABAJO
	public final static String ORDEN_VISTA = "orden/ordenesVista";
	public final static String ORDEN_AGREGAR = "orden/agregarOrden";

	
	/**** Redirects ****/
	public final static String INDEX_HOME_CLIENTE = "redirect:/";
	public final static String INDEX_HOME_VEHICULO = "redirect:/car/";
	public final static String INDEX_HOME_ORDEN = "redirect:/orden/";

}
