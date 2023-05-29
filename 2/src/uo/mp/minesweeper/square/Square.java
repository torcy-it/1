package uo.mp.minesweeper.square;

import uo.mp.minesweeper.square.action.Action;

public class Square {
	
	private State state;
	private int value;
	private Action action;
	
	/* 
	 * CLOSED (no descubierto), OPENED (descubierto, se ve lo que contiene la casilla) o 
	 * FLAGGED (marcado). Además, tiene un valor numérico asociado (lo que contiene la casilla), con
	 * 3 posibles casos.
	 * • 0  Casilla vacía.
	 * • >=1 y <=8  Casilla de pista numérica.
	 * • -1  Casilla con mina.
	 */
	
	public Square ( ) {
		
		this.state = State.CLOSED;
		this.value = 0;
	}
	
	public Square ( int value ) {
		
		this.state = State.CLOSED;
		this.value = value;
	}
	
	public void stepOn ( ) {
		
		if( state.equals(State.CLOSED) ) {
			this.state = State.OPENED;
			action.execute();
		}
	}
	
	public void flag() {
		if(state.equals(State.CLOSED)) {
			this.state = State.FLAGGED;
		}
	}
	
	public void unFlag ( ) {
		
		if( state.equals(State.FLAGGED) ) {
			this.state = State.CLOSED;
		}
	}
	
	public void open ( ) {
		this.state = State.OPENED;
	}


	public int getValue() {
		
		return value;
	}


	public void setValue(int value) {
		this.value = value;
	}


	@Override
	public String toString() {
		
		char cValue = ' '; 
		
		if( value == 0 ) cValue = ' ';
		else if ( value == -1) cValue = '@';
		else cValue = (char) value;
		
		return "Square [state=" + state + ", value=" + cValue + "]";
	}
	
	public boolean hasMine() {
		return value == -1;
	}
	
	public void putMine ( ) {
		setValue ( -1 );
	}
	
	public boolean isFlagged() {
		return state.equals(State.FLAGGED);
	}
	

	public boolean isOpened() {
		return state.equals(State.OPENED);
	}

	public void setAction( Action action) {
		this.action = action;
	}
	
	
}
