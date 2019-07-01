package com.nelioalves.cursomc.repositores;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nelioalves.cursomc.domain.Pagamento;
@Repository
public interface RepositoryPagamento extends JpaRepository<Pagamento, Integer>{
	
}
