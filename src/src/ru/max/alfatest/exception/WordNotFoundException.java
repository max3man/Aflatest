package ru.max.alfatest.exception;

public class WordNotFoundException extends Exception {

	private static final long serialVersionUID = 7100445629261247655L;

	public WordNotFoundException() {
		super();
	}	

	public WordNotFoundException(String msg, Throwable t) {
		super(msg, t);
	}

	public WordNotFoundException(String msg) {
		super(msg);
	}	
	
	public WordNotFoundException(Throwable msg) {
		super(msg);
	}

}
