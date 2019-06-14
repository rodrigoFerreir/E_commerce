package com.nelioalves.cursomc.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nelioalves.cursomc.domain.Categoria;
import com.nelioalves.cursomc.repositores.RepositoryCategorias;

@Service
public class CategoriaService {
	
	@Autowired
	private RepositoryCategorias repo;
	
	public Categoria buscarCategoria(Integer id) { // sempre que for pesquisar por id
		Optional<Categoria> categ = repo.findById(id);
		return categ.orElse(null);
	}
}
