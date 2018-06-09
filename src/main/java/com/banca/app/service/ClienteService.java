package com.banca.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.banca.app.dao.IClienteDao;
import com.banca.app.entity.Cliente;

@Service
public class ClienteService implements IClienteService {

	@Autowired
	private IClienteDao daoCliente;
	
	@Override
	@Transactional
	public void save(Cliente cliente) {
		// TODO Auto-generated method stub
		daoCliente.save(cliente);
		
	}

	@Override
	@Transactional(readOnly=true)
	public List<Cliente> findAll() {
		// TODO Auto-generated method stub
		return daoCliente.findAll();
	}

	@Override
	@Transactional(readOnly=true)
	public Cliente findById(Long id) {
		// TODO Auto-generated method stub
		return daoCliente.findById(id).orElse(null);
	}

}
