package com.nelioalves.cursomc.repositores;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.nelioalves.cursomc.domain.ItemPedido;

@Repository
public interface RepositoryItensPedidos extends JpaRepository<ItemPedido, Integer>{
	
}
