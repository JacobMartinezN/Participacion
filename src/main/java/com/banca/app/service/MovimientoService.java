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
		this.actualizarSaldo(movimiento);
		movimientoDao.save(movimiento);
		
	}

	@Override
	@Transactional(readOnly=true)
	public List<Movimiento> findAll() {
		// TODO Auto-generated method stub
		return movimientoDao.findAll();
	}

	@Override
	public List<Movimiento> findMovimientosByCuenta(Long id) {
		// TODO Auto-generated method stub
		return movimientoDao.findMovimientosByCuenta(id);
	}
	
	@Override
	public void actualizarSaldo(Movimiento movimiento) {
		if(movimiento.getTipo().equalsIgnoreCase("Retiro")) {
			double monto = movimiento.getMonto();
			double saldoAnterior = movimiento.getCuenta().getSaldo();
			movimiento.getCuenta().setSaldo(saldoAnterior - monto);
		}else {
			if(movimiento.getTipo().equalsIgnoreCase("Deposito")) {
				double monto = movimiento.getMonto();
				double saldoAnterior = movimiento.getCuenta().getSaldo();
				movimiento.getCuenta().setSaldo(saldoAnterior + monto);
			}
		}
	}

}
