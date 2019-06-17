package com.nelioalves.cursomc.repositores;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.nelioalves.cursomc.domain.Produto;

@Repository
public interface RepositoryProdutos extends JpaRepository<Produto, Integer>{
	
}
