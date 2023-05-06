package com.PruebaTecnica.ServicioAutomotor.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.PruebaTecnica.ServicioAutomotor.helpers.ViewRouterHelpers;
import com.PruebaTecnica.ServicioAutomotor.serviceImp.ClienteService;

@Controller
@RequestMapping("/")
public class HomeController {
	
	@Autowired
	private ClienteService clienteService;
	
	
	/*****************************************MVC******************************************/	
	
	@GetMapping
	public String home(Model model) {
		model.addAttribute("clientesList",  clienteService.listar());
		return ViewRouterHelpers.INDEX_VISTA;
	}
	
	
	
	
	
	
}
