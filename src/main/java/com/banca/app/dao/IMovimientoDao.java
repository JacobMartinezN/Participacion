package com.banca.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.banca.app.entity.Movimiento;

@Repository
public interface IMovimientoDao extends JpaRepository<Movimiento, Long>{

	
}
