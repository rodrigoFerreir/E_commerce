package com.nelioalves.cursomc.repositores;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.nelioalves.cursomc.domain.Cliente;

@Repository
public interface RepositoryCliente extends JpaRepository<Cliente, Integer>{
	
}
