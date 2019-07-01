package com.nelioalves.cursomc.repositores;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.nelioalves.cursomc.domain.Cidade;

@Repository
public interface RepositoryCidade extends JpaRepository<Cidade, Integer>{
	
}
