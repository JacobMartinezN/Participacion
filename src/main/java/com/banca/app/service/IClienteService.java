package com.banca.app.service;

import java.util.List;

import com.banca.app.entity.Cliente;

public interface IClienteService {

	public void save(Cliente cliente);
	
	public List<Cliente> findAll();
	
	public Cliente findById(Long id);
}
