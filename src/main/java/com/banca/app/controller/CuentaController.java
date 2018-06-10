package com.banca.app.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.banca.app.entity.Cliente;
import com.banca.app.entity.Cuenta;
import com.banca.app.service.IClienteService;
import com.banca.app.service.ICuentaService;

@Controller
@SessionAttributes("cuenta")
public class CuentaController {
	
	@Autowired
	private ICuentaService cuentaService;
	
	@Autowired
	private IClienteService clienteService;
	
	
	@GetMapping(value="agregarCuenta/{clienteId}")
	public String agregar(@PathVariable(value = "clienteId") Long clienteId, Model model) {
		
		Cliente cliente = clienteService.findById(clienteId);
		Cuenta cuenta=new Cuenta();
		cuenta.setCliente(cliente);
		
		model.addAttribute("titulo", "Agregar Cuenta");
		model.addAttribute("cuenta", cuenta);
		
		return "agregarCuenta";
	}
	
	@PostMapping(value="agregarCuenta")
	public String guardar(Model model, @Valid Cuenta cuenta) {
		
		cuentaService.save(cuenta);
		
		
		return "redirect:/listarCuenta/" + cuenta.getCliente().getId();
		
	}
	
	@GetMapping(value="listarCuenta/{clienteId}")
	public String listar(Model model,@PathVariable(value = "clienteId") Long clienteId) {
		
		
		model.addAttribute("titulo", "Listar Cuentas");
		model.addAttribute("cuentas", cuentaService.findCuentasByCliente(clienteId));
		
		return "listarCuenta" ;
		
	}

}
