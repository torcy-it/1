package uo.mp.minesweeper.game;

import uo.mp.minesweeper.exception.GameException;
import uo.mp.minesweeper.exception.UserInteractionException;

public class Game {

	private Board board;
	private long t1;
	private long t2;
	private long t3 = 0; // inizializziamo direttamente la variabile
	private GameInteractor gameInteractor;
	
	
	private boolean winner;
	private long tiempo;
	private int startMines;

	public Game(Board board) {
		this.board = board;
		this.tiempo = 0 ;
		this.startMines = board.getNumberOfMinesLeft();
		this.winner = false;
	}
	
	public void play() throws GameException{
		this.t1 = System.currentTimeMillis(); // spostiamo l'inizializzazione qui
		
		if ( win(board.getState()) ) {
			gameInteractor.showCongratulations(0);
		}
		
		
		while (!board.hasExploded() && !winner ) {
			
			if (t3 != 0) {
				
				gameInteractor.showGame(t3, board);
			} else {
				
				gameInteractor.showGame(0, board);
			}
			
			try {
				getMovement( gameInteractor.askMove(board.getNumberOfRows(), board.getNumberOfColumns()));
				
			}catch ( UserInteractionException ue ) {
				
				gameInteractor.showErrorMessage(ue.getMessage());
				
			}
		
			
			
			
			this.t2 = System.currentTimeMillis();
			this.t3 = t2 - t1;
			
			winner = win(board.getState());
			//System.out.println(winner + "  " + board.hasExploded() );
		}
		
		gameInteractor.showGameFinished();
		
		if ( win(board.getState()) ) {
			gameInteractor.showCongratulations(t3);
		} else {
			gameInteractor.showBooommm();
		}
		
		// assegniamo il valore di t3 alla variabile tiempo
		this.tiempo = t3;
		
		gameInteractor.showGame(tiempo, board); 
	}
	

	
	// aggiungiamo un metodo per ottenere il tempo di gioco
	public long getTiempo() {
		return tiempo;
	}
	
	public boolean getWinner() {
		return winner;
	}
	
	private void getMovement( GameMove gameMove) throws GameException {
		
		if ( gameMove != null )
			if( gameMove.getOperation() == 'f') board.flag(gameMove.getRow(),gameMove.getColumn());
			else if ( gameMove.getOperation() == 's') board.stepOn(gameMove.getRow(),gameMove.getColumn());
			else if ( gameMove.getOperation() == 'u') board.unFlag(gameMove.getRow(),gameMove.getColumn());
			
	}


	private boolean win ( char [][] cBoard ) {
		int count = 0;
		
		for ( int i = 0 ; i < cBoard.length ; i++ ) {
			for ( int j = 0 ; j < cBoard.length ; j++ ) {
				if( cBoard[i][j] == '#' || cBoard[i][j] == 182 ) count++;
			}
		}
		
		//System.out.println(count + " "+ startMines + " " +board.getNumberOfMinesLeft() );
		if (count == startMines ) return true;
		else return false;
	}
//	Asigna a Game el objeto GameInteractor recibido como parámetro. Establece 
//	interactor como la interfaz que se utilizará para comunicarse con el jugador
	public void setInteractor ( GameInteractor interactor ) {
		this.gameInteractor = interactor;
	}


}
