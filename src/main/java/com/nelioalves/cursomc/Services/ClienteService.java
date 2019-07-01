package com.nelioalves.cursomc.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nelioalves.cursomc.Services.exceptions.ObjectNotFundExcepion;
import com.nelioalves.cursomc.domain.Cliente;
import com.nelioalves.cursomc.repositores.RepositoryCliente;

@Service
public class ClienteService {
	
	@Autowired
	private RepositoryCliente repositoryCliente;
	
	public Cliente buscarCliente(Integer id) { // sempre que for pesquisar por id
		Optional<Cliente> cliente = repositoryCliente.findById(id);
		return cliente.orElseThrow(() -> new ObjectNotFundExcepion(
				"Objeto não encontrado! Id: " + id + ". Tipo: " + Cliente.class.getName()));
	}
}
