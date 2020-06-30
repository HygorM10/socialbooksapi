package com.hygor.socialbooks.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hygor.socialbooks.domain.Autor;
import com.hygor.socialbooks.repository.AutoresRepository;
import com.hygor.socialbooks.services.exceptions.AutorExistenteExcpetion;
import com.hygor.socialbooks.services.exceptions.AutorNaoEcontradoException;

@Service
public class AutoresService {

	@Autowired
	private AutoresRepository autoresRepository;
	
	public List<Autor> listar(){
		return autoresRepository.findAll();
	}
	
	public Autor salvar(Autor autor) {
		if(autor.getId() != null) {
			Autor a = autoresRepository.findById(autor.getId()).orElse(null);
			
			if(a != null)
				throw new AutorExistenteExcpetion("O Autor já existe!");
		}
		
		return autoresRepository.save(autor);
	}
	
	public Autor buscar(Long id) {
		Autor autor = autoresRepository.findById(id).orElse(null);
		
		if(autor == null) {
			throw new AutorNaoEcontradoException("Autor não foi encontrado!");
		}
		
		return autor;
	}
}
