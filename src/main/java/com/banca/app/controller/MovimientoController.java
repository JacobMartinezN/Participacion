package com.banca.app.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.banca.app.entity.Cuenta;
import com.banca.app.entity.Movimiento;
import com.banca.app.service.ICuentaService;
import com.banca.app.service.IMovimientoService;

@Controller
@SessionAttributes("movimiento")
public class MovimientoController {
	
	@Autowired
	private IMovimientoService movimientoService;
	
	@Autowired
	private ICuentaService cuentaService;
	
	@GetMapping(value="/listarMovimiento/{cuentaId}")
	public String listar(Model model,@PathVariable(value = "cuentaId") Long cuentaId) {
		
		
		boolean advertencia = false;
			
			if(model.asMap().containsKey("warning")) {
				String mensaje = model.asMap().get("warning").toString();
				advertencia = true;
				model.addAttribute("mensaje",mensaje);
			}
			
			model.addAttribute("advertencia",advertencia);
			
			model.addAttribute("titulo", "Listar Movimiento");
			model.addAttribute("movimientos", movimientoService.findMovimientosByCuenta(cuentaId));
			model.addAttribute("idcuenta", cuentaId);
			Cuenta cuenta = cuentaService.findById(cuentaId);
			model.addAttribute("idcliente", cuenta.getCliente().getId());
			model.addAttribute("cliente", cuenta.getCliente().getNombres() + ' ' + cuenta.getCliente().getApellidos());
			model.addAttribute("ncuenta", cuenta.getNcuenta());
			model.addAttribute("banco", cuenta.getBanco());
			model.addAttribute("monto",  cuenta.getSaldo());
			
			return "listarmovimiento" ;
		
	}


	@GetMapping(value="/agregarMovimiento/{cuentaId}")
	public String agregar(@PathVariable(value = "cuentaId") Long cuentaId, Model model) {
		
		Movimiento movimiento = new Movimiento();
		movimiento.setCuenta(cuentaService.findById(cuentaId));
		model.addAttribute("titulo", "Agregar Movimiento");
		model.addAttribute("movimiento", movimiento);
		model.addAttribute("idcuenta", cuentaId);
		model.addAttribute("ncuenta", movimiento.getCuenta().getNcuenta());
		model.addAttribute("banco", movimiento.getCuenta().getBanco());
		model.addAttribute("monto",  movimiento.getCuenta().getSaldo());
		return "agregarMovimiento";
	}
	
	@PostMapping(value="/agregarMovimiento/{cuentaId}")
	public String guardar(Model model, @Valid Movimiento movimiento,
			@PathVariable(value = "cuentaId") Long cuentaId, RedirectAttributes redirectAttr) {
		
		Cuenta cuenta = cuentaService.findById(cuentaId);
		movimiento.setCuenta(cuenta);
		
		if(movimiento.getTipo().equalsIgnoreCase("Retiro") && movimiento.getMonto() > cuenta.getSaldo()) {
			redirectAttr.addFlashAttribute("warning","No hay saldo suficiente en la cuenta.");
			return "redirect:/listarMovimiento/" + cuentaId;
		}
		
		movimientoService.save(movimiento);
		return "redirect:/listarMovimiento/" + cuentaId;
	}
}
