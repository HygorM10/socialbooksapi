package com.hygor.socialbooks.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.hygor.socialbooks.domain.Comentarios;
import com.hygor.socialbooks.domain.Livro;
import com.hygor.socialbooks.repository.ComentariosRepository;
import com.hygor.socialbooks.repository.LivrosRepository;
import com.hygor.socialbooks.services.exceptions.LivroNaoEncontradoExcpetion;

@Service
public class LivrosServices {

	@Autowired
	private LivrosRepository livrosRepository;
	
	@Autowired
	private ComentariosRepository comentariosRepository;
	
	public List<Livro> listar(){
		return livrosRepository.findAll();
	}
	
	public Livro buscar(Long id) {
		Livro livro = livrosRepository.findById(id).orElse(null);
		
		if(livro == null) {
			throw new LivroNaoEncontradoExcpetion("O Livro não pôde ser encontrado.");
		}
		
		return livro;
	}
	
	public Livro salvar(Livro livro) {
		livro.setId(null);
		return livrosRepository.save(livro);
	}
	
	public void deletar(Long id) {
		try {
			livrosRepository.deleteById(id);
		}catch (EmptyResultDataAccessException e) {
			throw new LivroNaoEncontradoExcpetion("Livro não pode ser encontrado!");
		}
	}
	
	public void atualizar(Livro livro) {
		verificarExistencia(livro);
		livrosRepository.save(livro);
	}
	
	private void verificarExistencia(Livro livro) {
		buscar(livro.getId());
	}
	
	public Comentarios salvarComentario(Long livroId, Comentarios comentario) {
		Livro livro = buscar(livroId);
		
		comentario.setLivro(livro);
		comentario.setData(new Date());
		
		return comentariosRepository.save(comentario);
	}
	
	public List<Comentarios> listarComentarios(Long livroId){
		Livro livro = buscar(livroId);
		
		return livro.getComentarios();
	}
}
