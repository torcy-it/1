package uo.mp.minesweeper.square.action;

import java.util.List;

import uo.mp.minesweeper.square.Square;

public class ClearAction implements Action{

	
	List<Square> neighbouringSquares;
	
	public ClearAction(List<Square> neighbouringSquares) {
		this.neighbouringSquares = neighbouringSquares;
	}
	
	
	@Override
	public void execute() {
		for( int i = 0 ; i < neighbouringSquares.size(); i++) 
				neighbouringSquares.get(i).stepOn();
			
	}
	



}
