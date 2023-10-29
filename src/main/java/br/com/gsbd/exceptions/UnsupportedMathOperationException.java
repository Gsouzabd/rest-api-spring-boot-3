package br.com.gsbd.exceptions;

public class UnsupportedMathOperationException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UnsupportedMathOperationException(String ex) {
		super(ex);
	}
}
