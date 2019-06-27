package com.nelioalves.cursomc.repositores;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.nelioalves.cursomc.domain.Endereco;

@Repository
public interface RepositoryEndereco extends JpaRepository<Endereco, Integer>{
	
}
