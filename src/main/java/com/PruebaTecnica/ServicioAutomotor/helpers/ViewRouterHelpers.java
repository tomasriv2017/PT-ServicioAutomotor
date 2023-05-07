package com.PruebaTecnica.ServicioAutomotor.helpers;

public class ViewRouterHelpers {
	/**** Views ****/
	
	//ACCESO
	public final static String INDEX_VISTA =  "home/index.html";
	
	//CLIENTE
	public final static String CLIENTE_AGREGAR = "cliente/agregarCliente.html";
	
	//MARCA
	public final static String MARCA_VISTA =  "marca/marcaVista.html";
	public final static String MARCA_AGREGAR = "marca/agregarMarca.html";
	
	//VEHICULO
	public final static String VEHICULO_VISTA =  "vehiculo/vehiculoVista.html";
	public final static String VEHICULO_AGREGAR = "vehiculo/agregarVehiculo.html";

	//SERVICIO
	public final static String SERVICIO_VISTA = "servicio/servicioVista.html";

	
	//ORDEN DE TRABAJO
	public final static String ORDEN_VISTA = "orden/ordenesVista.html";
	public final static String ORDEN_AGREGAR = "orden/agregarOrden.html";

	
	/**** Redirects ****/
	public final static String INDEX_HOME_CLIENTE = "redirect:/";
	public final static String INDEX_HOME_VEHICULO = "redirect:/car/";
	public final static String INDEX_HOME_MARCA = "redirect:/marca/";
	public final static String INDEX_HOME_ORDEN = "redirect:/orden/";

}
