package com.nelioalves.cursomc.repositores;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nelioalves.cursomc.domain.Pedido;

@Repository
public interface RepositoryPedidos extends JpaRepository<Pedido, Integer>{
	
}
