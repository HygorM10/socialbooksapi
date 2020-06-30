package com.hygor.socialbooks.services.exceptions;

public class AutorExistenteExcpetion extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4452870842478759143L;

	public AutorExistenteExcpetion(String mensagem) {
		super(mensagem);
	}
	
	public AutorExistenteExcpetion(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}
	
}
