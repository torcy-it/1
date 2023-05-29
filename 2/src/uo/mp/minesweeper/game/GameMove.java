package uo.mp.minesweeper.game;

public class GameMove {
	private char operation; 
	private int row;
	private int column ;
	
	public GameMove(char operation, int row, int column ) {
		this.operation = operation;
		this.row = row;
		this.column = column;
		
	}
	
	public char getOperation() {
		return operation;
	}
	
	public int getRow ( ) {
		return row;
	}
	
	
	public int getColumn ( ) {
		return column;
	}

	@Override
	public String toString() {
		return "GameMove [operation=" + operation + ", row=" + row + ", column=" + column + "]";
	}
}
