package uo.mp.minesweeper.exception;

import uo.mp.lab.util.check.ArgumentChecks;

public class InvalidLineFormatException extends Exception {

	private static final long serialVersionUID = 1L;
	
	private int line;
	
	public InvalidLineFormatException(int line,String message) {
		super(message);
		ArgumentChecks.isTrue(line>=0);
		this.line=line;
	}

	public InvalidLineFormatException(String message) {
		super(message);
	}

}
