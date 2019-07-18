package com.nelioalves.cursomc.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nelioalves.cursomc.Services.exceptions.ObjectNotFundExcepion;
import com.nelioalves.cursomc.domain.Pedido;
import com.nelioalves.cursomc.repositores.RepositoryPedidos;

@Service
public class PedidoService {
	
	@Autowired
	private RepositoryPedidos repositoryPedido;
	
	public Pedido buscarPedido(Integer id) { // sempre que for pesquisar por id
		Optional<Pedido> cliente = repositoryPedido.findById(id);
		return cliente.orElseThrow(() -> new ObjectNotFundExcepion(
				"Objeto não encontrado! Id: " + id + ". Tipo: " + Pedido.class.getName()));
	}
}
