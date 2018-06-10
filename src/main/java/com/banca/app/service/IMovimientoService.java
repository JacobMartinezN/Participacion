package com.banca.app.service;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.banca.app.entity.Movimiento;

public interface IMovimientoService {

	
	public void save(Movimiento movimiento);
	
	public List<Movimiento> findAll();
	
	public List<Movimiento> findMovimientosByCuenta(Long id);
}