package com.nelioalves.cursomc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.nelioalves.cursomc.domain.Categoria;
import com.nelioalves.cursomc.repositores.RepositoryCategorias;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {
	
	@Autowired
	private RepositoryCategorias categoriaRepository;

	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria(null, "Informatica");
		Categoria cat2 = new Categoria(null, "Escritorio");
		Categoria cat3 = new Categoria(null, "Manutencao");
		Categoria cat4 = new Categoria(null, "Sistemas");
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4));
		
	}
	

}
