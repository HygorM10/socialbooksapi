package com.hygor.socialbooks.services.exceptions;

public class LivroNaoEncontradoExcpetion extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4452870842478759143L;

	public LivroNaoEncontradoExcpetion(String mensagem) {
		super(mensagem);
	}
	
	public LivroNaoEncontradoExcpetion(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}
	
}
