package com.nelioalves.cursomc.repositores;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.nelioalves.cursomc.domain.Estado;

@Repository
public interface RepositoryEstado extends JpaRepository<Estado, Integer>{
	
}
