package com.hygor.socialbooks.resources;

import java.net.URI;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.hygor.socialbooks.domain.Comentarios;
import com.hygor.socialbooks.domain.Livro;
import com.hygor.socialbooks.services.LivrosServices;

@RestController
@RequestMapping("/livros")
public class LivrosResources {
	
	@Autowired
	private LivrosServices livrosServices;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Livro>> listar() {
		return ResponseEntity.status(HttpStatus.OK).body(livrosServices.listar());
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> salvar(@Valid @RequestBody Livro livro) {
		livro = livrosServices.salvar(livro);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(livro.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> buscar(@PathVariable("id") Long id) {
		
		Livro livro = livrosServices.buscar(id);
		
		CacheControl cacheControl = CacheControl.maxAge(20, TimeUnit.SECONDS);
		
		return ResponseEntity.status(HttpStatus.OK).cacheControl(cacheControl).body(livro);
		
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deletar(@PathVariable("id") Long id) {
		livrosServices.deletar(id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> atualizar(@RequestBody Livro livro,
			@PathVariable("id") Long id) {
		livro.setId(id);
		livrosServices.atualizar(livro);
		
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value = "{id}/comentarios", method = RequestMethod.POST)
	public ResponseEntity<Void> adicionarComentario(@PathVariable("id") Long livroId, 
			@RequestBody Comentarios comentario) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		comentario.setUsuario(auth.getName());
		
		livrosServices.salvarComentario(livroId, comentario);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value = "{id}/comentarios", method = RequestMethod.GET)
	public ResponseEntity<List<Comentarios>> listarComentarios(@PathVariable("id") Long livroId){
		List<Comentarios> comentarios = livrosServices.listarComentarios(livroId);
		
		return ResponseEntity.status(HttpStatus.OK).body(comentarios);
	}
}
