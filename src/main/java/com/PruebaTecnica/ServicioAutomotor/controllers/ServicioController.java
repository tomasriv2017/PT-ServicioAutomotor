package com.PruebaTecnica.ServicioAutomotor.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.PruebaTecnica.ServicioAutomotor.helpers.ViewRouterHelpers;
import com.PruebaTecnica.ServicioAutomotor.serviceImp.ServicioService;

@Controller
@RequestMapping("/service")
public class ServicioController {

	@Autowired
	private ServicioService servicioService;
	
	
	@GetMapping("/")
	public String listar(Model model) {
		model.addAttribute("lavadoList", servicioService.listarLavados());
		model.addAttribute("aceiteYFiltroList", servicioService.listarPorAceiteYFiltro());
		model.addAttribute("alineacionYBalanceoList", servicioService.listarPorAlineacionYBalanceo());		
		return ViewRouterHelpers.SERVICIO_VISTA;
	}
	
	
}
