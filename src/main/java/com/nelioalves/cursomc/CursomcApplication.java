package com.nelioalves.cursomc;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.nelioalves.cursomc.domain.Categoria;
import com.nelioalves.cursomc.domain.Cidade;
import com.nelioalves.cursomc.domain.Cliente;
import com.nelioalves.cursomc.domain.Endereco;
import com.nelioalves.cursomc.domain.Estado;
import com.nelioalves.cursomc.domain.Pagamento;
import com.nelioalves.cursomc.domain.PagamentoComBoleto;
import com.nelioalves.cursomc.domain.PagamentoComCartao;
import com.nelioalves.cursomc.domain.Pedido;
import com.nelioalves.cursomc.domain.Produto;
import com.nelioalves.cursomc.domain.enums.EstadoPagamento;
import com.nelioalves.cursomc.domain.enums.TipoCliente;
import com.nelioalves.cursomc.repositores.RepositoryCategorias;
import com.nelioalves.cursomc.repositores.RepositoryCidade;
import com.nelioalves.cursomc.repositores.RepositoryCliente;
import com.nelioalves.cursomc.repositores.RepositoryEndereco;
import com.nelioalves.cursomc.repositores.RepositoryEstado;
import com.nelioalves.cursomc.repositores.RepositoryPagamento;
import com.nelioalves.cursomc.repositores.RepositoryPedidos;
import com.nelioalves.cursomc.repositores.RepositoryProdutos;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {
	
	@Autowired
	private RepositoryCategorias categoriaRepository;
	@Autowired
	private RepositoryProdutos produtoRepository;
	@Autowired
	private RepositoryCidade cidadeRepository;
	@Autowired
	private RepositoryEstado estadoRepository;
	@Autowired
	private RepositoryCliente clienteRepository;
	@Autowired
	private RepositoryEndereco enderecoRepository;
	@Autowired
	private RepositoryPedidos pedidosRepository;
	@Autowired
	private RepositoryPagamento pagamentoRepository;

	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria(null, "Informatica");
		Categoria cat2 = new Categoria(null, "Escritorio");
		Categoria cat3 = new Categoria(null, "Manutencao");
		Categoria cat4 = new Categoria(null, "Sistemas");
		
		Produto p1 = new Produto(null, "computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 200.00);
		Produto p3 = new Produto(null, "conjunto de chaves", 20.00);
		Produto p4 = new Produto(null, "servidor", 22000.00);
		
		// associando listas de produtos com as categorias
		
		cat1.getProdutos().addAll(Arrays.asList(p1, p2));
		cat2.getProdutos().addAll(Arrays.asList(p1, p2));
		cat3.getProdutos().addAll(Arrays.asList(p3));
		cat4.getProdutos().addAll(Arrays.asList(p4));
		
		
		p1.getCategoria().addAll(Arrays.asList(cat1, cat2));
		p2.getCategoria().addAll(Arrays.asList(cat1, cat2));
		p3.getCategoria().addAll(Arrays.asList(cat3));
		p4.getCategoria().addAll(Arrays.asList(cat4));
				
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3, p4));
		////////	
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		Estado est3 = new Estado(null, "Pernambuco");
		
		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Recife", est3);
		Cidade c4 = new Cidade(null, "Bom Jardim", est3);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2));
		est3.getCidades().addAll(Arrays.asList(c3, c4));
		
		estadoRepository.saveAll(Arrays.asList(est1, est2, est3));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3, c4));
	    ////////
		
		Cliente clie1 = new Cliente(null, "Maria Silva", "36378912377", "maria@gmail.com", TipoCliente.PESSOAFISICA);
		Cliente clie2 = new Cliente(null, "Rodrigo Ferreira", "3365998767", "rodrigo@hotmail.com", TipoCliente.PESSOAJURIDICA);
		
		clie1.getTelefones().addAll(Arrays.asList("98656475", "32546874"));
		clie2.getTelefones().addAll(Arrays.asList("87542596", "88789696"));
		
		Endereco end1 = new Endereco(null, "Rua Flores", "300", "Jardim", "Centro", "56670000", clie1, c1);
		Endereco end2 = new Endereco(null, "Altos", "001", "Apt 101", "Zona Sul", "33170000", clie2, c3);
		Endereco end3 = new Endereco(null, "Avenida Matos", "404", "Not Found", "Zona Leste", "56670017", clie1, c2);
		
		clie1.getEnderecos().addAll(Arrays.asList(end1, end3));
		clie2.getEnderecos().addAll(Arrays.asList(end2));
		
		clienteRepository.saveAll(Arrays.asList(clie1, clie2));
		enderecoRepository.saveAll(Arrays.asList(end1, end2, end3));
		////////30/06/2019
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy HH:mm");
		
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), clie1, end1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), clie1, end2);
		
		Pagamento pagt1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagt1);
		
		Pagamento pagt2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"), null);
		ped2.setPagamento(pagt2);
		
		clie1.getPedidos().addAll(Arrays.asList(ped1, ped2));
		
		pedidosRepository.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepository.saveAll(Arrays.asList(pagt2));
	}
	

}














