package com.hygor.socialbooks.handler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.hygor.socialbooks.domain.DetalhesErro;
import com.hygor.socialbooks.services.exceptions.AutorExistenteExcpetion;
import com.hygor.socialbooks.services.exceptions.AutorNaoEcontradoException;
import com.hygor.socialbooks.services.exceptions.LivroNaoEncontradoExcpetion;

@ControllerAdvice
public class ResourceExceptionHandler {
	
	@ExceptionHandler(LivroNaoEncontradoExcpetion.class)
	public ResponseEntity<DetalhesErro> handleLivroNaoEncontradoException(LivroNaoEncontradoExcpetion e, HttpServletRequest request){
		
		DetalhesErro erro = new DetalhesErro();
		erro.setStatus(404l);
		erro.setTitulo("O livro não pode ser encontrado");
		erro.setMensagemDesenvolvedor("http://spring.io");
		erro.setTimestamp(System.currentTimeMillis());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
	}
	
	@ExceptionHandler(AutorExistenteExcpetion.class)
	public ResponseEntity<DetalhesErro> handleAutorExistenteExcpetion(AutorExistenteExcpetion e, HttpServletRequest request){
		
		DetalhesErro erro = new DetalhesErro();
		erro.setStatus(409l);
		erro.setTitulo("Autor ja existente");
		erro.setMensagemDesenvolvedor("http://spring.io");
		erro.setTimestamp(System.currentTimeMillis());
		
		return ResponseEntity.status(HttpStatus.CONFLICT).body(erro);
	}
	
	
	@ExceptionHandler(AutorNaoEcontradoException.class)
	public ResponseEntity<DetalhesErro> handleAutorNaoEcontradoException(AutorNaoEcontradoException e, HttpServletRequest request){
		
		DetalhesErro erro = new DetalhesErro();
		erro.setStatus(404l);
		erro.setTitulo("Autor não pode ser encontrado");
		erro.setMensagemDesenvolvedor("http://spring.io");
		erro.setTimestamp(System.currentTimeMillis());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
	}
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<DetalhesErro> handleDataIntegrityViolationException(DataIntegrityViolationException e, HttpServletRequest request){
		
		DetalhesErro erro = new DetalhesErro();
		erro.setStatus(400l);
		erro.setTitulo("Requisicao Invalida");
		erro.setMensagemDesenvolvedor("http://spring.io");
		erro.setTimestamp(System.currentTimeMillis());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
	}
}
