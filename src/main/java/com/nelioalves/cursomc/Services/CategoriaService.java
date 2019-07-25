package com.nelioalves.cursomc.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.nelioalves.cursomc.Services.exceptions.DataIntegrityException;
import com.nelioalves.cursomc.Services.exceptions.ObjectNotFundExcepion;
import com.nelioalves.cursomc.domain.Categoria;
import com.nelioalves.cursomc.repositores.RepositoryCategorias;

@Service
public class CategoriaService {
	
	@Autowired
	private RepositoryCategorias repo;
	
	public Categoria buscarCategoria(Integer id) { // sempre que for pesquisar por id
		Optional<Categoria> categ = repo.findById(id);
		return categ.orElseThrow(() -> new ObjectNotFundExcepion(
				"Objeto não encontrado! Id: " + id + ". Tipo: " + Categoria.class.getName()));
	}
	
	public Categoria inserir(Categoria obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	
	public Categoria atualizar(Categoria obj) {
		buscarCategoria(obj.getId());
		return repo.save(obj);
		
	}
	
	public void deletar(Integer id) {
		buscarCategoria(id);
		try{
		repo.deleteById(id);
		}
		catch(DataIntegrityViolationException e){
			throw new DataIntegrityException("Não é possível excluir uma categoria com produtos.");
		}
	}
}
