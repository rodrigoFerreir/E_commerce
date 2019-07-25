package com.nelioalves.cursomc.resources;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.nelioalves.cursomc.Services.CategoriaService;
import com.nelioalves.cursomc.domain.Categoria;

@RestController
@RequestMapping(value="/categorias")
public class CategoriaResource {
	
	@Autowired
	private CategoriaService service; 
	
	@RequestMapping(value = "/{id}", method=RequestMethod.GET)
	public ResponseEntity<Categoria> find(@PathVariable Integer id) {
		Categoria objBusca = service.buscarCategoria(id);
		return ResponseEntity.ok().body(objBusca);
		
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> inserir(@RequestBody Categoria obj){
		obj = service.inserir(obj);
		URI uri = ServletUriComponentsBuilder
											.fromCurrentRequest()
											.path("/{id}")	       //metodo para recuperar uri e concatenar com uri inicial
											.buildAndExpand(obj.getId())
											.toUri(); 
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value = "/{id}" ,method = RequestMethod.PUT)
	public ResponseEntity<Void> atualizar(@RequestBody Categoria obj, @PathVariable Integer id){
		obj.setId(id);
		obj = service.atualizar(obj);
		
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/{id}" ,method = RequestMethod.DELETE)
	public ResponseEntity<Void> deletar(@RequestBody Categoria obj, @PathVariable Integer id){
		service.deletar(id);
		return ResponseEntity.noContent().build();
	}

}
  