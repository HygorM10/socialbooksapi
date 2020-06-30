package com.hygor.socialbooks.services.exceptions;

public class AutorNaoEcontradoException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4452870842478759143L;

	public AutorNaoEcontradoException(String mensagem) {
		super(mensagem);
	}
	
	public AutorNaoEcontradoException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}
	
}
