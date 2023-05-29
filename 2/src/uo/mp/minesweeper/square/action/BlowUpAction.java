package uo.mp.minesweeper.square.action;

import uo.mp.minesweeper.game.Board;

public class BlowUpAction implements Action{
	
	private Board board ; 
	
	public BlowUpAction ( Board board ) {
		this.board = board;
	}
	
	
	@Override
	public void execute() {
		board.markAsExploded();
	}

}
