package com.nelioalves.cursomc.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nelioalves.cursomc.Services.exceptions.DataIntegrityException;
import com.nelioalves.cursomc.Services.exceptions.ObjectNotFundExcepion;
import com.nelioalves.cursomc.domain.Categoria;
import com.nelioalves.cursomc.domain.Cidade;
import com.nelioalves.cursomc.domain.Cliente;
import com.nelioalves.cursomc.domain.Endereco;
import com.nelioalves.cursomc.domain.enums.TipoCliente;
import com.nelioalves.cursomc.dto.ClienteDTO;
import com.nelioalves.cursomc.dto.ClienteNewDTO;
import com.nelioalves.cursomc.repositores.RepositoryCliente;
import com.nelioalves.cursomc.repositores.RepositoryEndereco;

@Service
public class ClienteService {

	@Autowired
	private RepositoryCliente repo;
	
	@Autowired
	private RepositoryEndereco enderecoRepository;

	public Cliente buscarCliente(Integer id) { // sempre que for pesquisar por id
		Optional<Cliente> cliente = repo.findById(id);
		return cliente.orElseThrow(() -> new ObjectNotFundExcepion(
				"Objeto não encontrado! Id: " + id + ". Tipo: " + Cliente.class.getName()));
	}

	public List<Cliente> buscarTodos() { // buscando todas as categorias.
		return repo.findAll();
	}
	
	@Transactional
	public Cliente inserir(Cliente obj) {
		obj.setId(null);
		obj = repo.save(obj);
		enderecoRepository.saveAll(obj.getEnderecos());
		return obj;
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
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir. Há entidades relacionadas.");
		}
	}

	public Page<Cliente> buscaPorPagina(Integer page, Integer linhasPorPaginas, String direction, String orderBy) {
		PageRequest pageRequest = PageRequest.of(page, linhasPorPaginas, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}

	public Cliente fromDTO(ClienteDTO objDto) { // Metodo auxiliar para converter cliente para ClienteDTO
		return new Cliente(objDto.getId(), objDto.getNome(), objDto.getEmail(), null, null);
	}
	
	public Cliente fromDTO(ClienteNewDTO objDto) { // Metodo auxiliar para converter cliente para ClienteNewDTO
		Cliente cli = new Cliente(null, objDto.getNome(), objDto.getEmail(), objDto.getCpf(), TipoCliente.toEnum(objDto.getTipo()));
		Cidade cid = new Cidade(objDto.getCidadeID(), null, null);
		Endereco end = new Endereco(null, objDto.getLogradouro(), objDto.getNumero(), objDto.getComplemento(), objDto.getBairro(), objDto.getCep(), cli, cid);
		cli.getEnderecos().add(end);
		return cli;
	}
}
