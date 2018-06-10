package com.banca.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.banca.app.dao.IMovimientoDao;
import com.banca.app.entity.Movimiento;

@Service
public class MovimientoService implements IMovimientoService {

	@Autowired
	private IMovimientoDao movimientoDao;
	
	@Override
	@Transactional
	public void save(Movimiento movimiento) {
		// TODO Auto-generated method stub
		movimientoDao.save(movimiento);
		
	}

	@Override
	@Transactional(readOnly=true)
	public List<Movimiento> findAll() {
		// TODO Auto-generated method stub
		return movimientoDao.findAll();
	}

}
