package com.nelioalves.cursomc.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.nelioalves.cursomc.Services.exceptions.DataIntegrityException;
import com.nelioalves.cursomc.Services.exceptions.ObjectNotFundExcepion;
import com.nelioalves.cursomc.domain.Cliente;
import com.nelioalves.cursomc.dto.ClienteDTO;
import com.nelioalves.cursomc.repositores.RepositoryCliente;

@Service
public class ClienteService {
	
	@Autowired
	private RepositoryCliente repo;
	
	public Cliente buscarCliente(Integer id) { // sempre que for pesquisar por id
		Optional<Cliente> cliente = repo.findById(id);
		return cliente.orElseThrow(() -> new ObjectNotFundExcepion(
				"Objeto não encontrado! Id: " + id + ". Tipo: " + Cliente.class.getName()));
	}
	
	public List<Cliente> buscarTodos() { // buscando todas as categorias.
		return repo.findAll();
	}
	
	public Cliente inserir(Cliente obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	
	public Cliente atualizar(Cliente obj) {
		Cliente newObj = buscarCliente(obj.getId());
		atualizarDado(newObj, obj);
		return repo.save(newObj); 		
	}
	
	private void atualizarDado(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}
	
	public void deletar(Integer id) {
		buscarCliente(id);
		try{
		repo.deleteById(id);
		}
		catch(DataIntegrityViolationException e){
			throw new DataIntegrityException("Não é possível excluir. Há entidades relacionadas.");
		}
	}

	public Page<Cliente> buscaPorPagina(Integer page, Integer linhasPorPaginas, String direction, String orderBy) {
		PageRequest pageRequest = PageRequest.of(page, linhasPorPaginas, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}

	public Cliente fromDTO(ClienteDTO objDto){ // Metodo auxiliar para converter categoria para categoriaDTO
		return new Cliente(objDto.getId(), objDto.getNome(), objDto.getEmail(), null, null);
	}
}
