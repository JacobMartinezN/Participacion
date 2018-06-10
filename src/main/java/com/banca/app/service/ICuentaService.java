package com.banca.app.service;

import java.util.List;

import com.banca.app.entity.Cuenta;



public interface ICuentaService {

	public void save(Cuenta cuenta);
	
	public List<Cuenta> findAll();
	
	public Cuenta findById(Long id);
	
	public List<Cuenta> findCuentasByCliente(Long id);
}
