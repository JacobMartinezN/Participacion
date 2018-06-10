package com.banca.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.banca.app.entity.Movimiento;

@Repository
public interface IMovimientoDao extends JpaRepository<Movimiento, Long>{

	@Query("select m from Movimiento m join fetch m.cuenta c where c.id=?1")
	public List<Movimiento> findMovimientosByCuenta(Long id);
	
}
