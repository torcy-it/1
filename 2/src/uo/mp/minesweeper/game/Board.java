package uo.mp.minesweeper.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import uo.mp.lab.util.check.ArgumentChecks;
import uo.mp.minesweeper.exception.GameException;
import uo.mp.minesweeper.session.GameLevel;
import uo.mp.minesweeper.square.Square;
import uo.mp.minesweeper.square.action.*;

public class Board {
	
	private int width; 
	private int height;
	private int percentage;
	private Square [][] squares;
	private int numFlag;
	private int numMines;
	private boolean exploded;
	/*
	public Board (int width, int height, int percentage ) {
		

		
		this.numMines = (  (height * width) * percentage )  / 100 ;
		Random rand = new Random();
		
		int totalMine = numMines;
		this.percentage = percentage;
		this.width = width;
		this.height = height;
		this.numFlag = numMines;
		
		this.exploded = false;
		
		if( width == 9 ) this.numFlag = 10;
		else if (width == 30 ) this.numFlag = 40;
		else this.numFlag = 99;
		
		this.squares = new Square[height][width];
		
		for( int i = 0 ; i < width; i++ ) {
			for ( int j = 0; j < height; j++ ) {
				squares[i][j] = new Square();
				squares[i][j].setValue(0);
			}
		}
		
		int count = 2;
		
		for( int i = 0 ; i < width; i++ ) {
			for ( int j = 0; j < height; j++ ) {
				
				if( rand.nextInt(7) == 2 && totalMine > 0 && count > 0) {
					count--;
					setValueSquare( i , j ); 
					totalMine--;

				}

			}
			count = 2;
		}
		
		setAction();
		
		uncoverWelcomeArea();
	}*/
	
	public Board (GameLevel gameLevel ) {
		
		dimensionBoard ( gameLevel );
		
		int totalMine = (  (height * width) * percentage )  / 100 ;
		Random rand = new Random();
		
		
		this.numFlag = totalMine;
		this.exploded = false;
		this.numMines = totalMine;
		
		this.squares = new Square[height][width];
		
		for( int i = 0 ; i < height; i++ ) {
			for ( int j = 0; j < width; j++ ) {
				//System.out.print(j+" ");
				squares[i][j] = new Square();
				squares[i][j].setValue(0);
			}
			//System.out.println(" --> "+i);
		}
		
		
		int count = 2;
	
		for( int i = 0 ; i < height; i++ ) {
			for ( int j = 0; j < width; j++ ) {
				
				if( rand.nextInt(7) == 2 && totalMine > 0 && count >0) {
					count--;
					setValueSquare( i , j ); 
					totalMine--;

				}

			}
			count = 2;
		}
		
		
		
		setAction();
		
		uncoverWelcomeArea();
	}

	private void dimensionBoard(GameLevel gameLevel) {
		//principiante 9x9 - 12
		//medium 16x16 - 15 
		//hard 30x16 -20 
		int p, w, h;
		if(gameLevel.equals(GameLevel.EASY)) {
			p = 12;
			w = 9;
			h = 9;
		}
		else if (gameLevel.equals(GameLevel.MEDIUM)) {
			p = 15;
			w = 16;
			h = 16;
		}
		else {
			p = 20;
			w = 30;
			h = 16;
		}
		
		this.percentage = p;
		this.width = w;
		this.height = h;
		
		
	}

	private void setValueSquare (int i , int j ) {
		
		
		squares[i][j].putMine();
		squares[i][j].setAction(new BlowUpAction(this));
		
		if(j-1 >= 0  && i-1 >= 0 && !squares[i-1][j-1].hasMine() )
			squares[i-1][j-1].setValue( squares[i-1][j-1].getValue() +1 );
		
		if ( i-1 >= 0 && !squares[i-1][j].hasMine() )
			squares[i-1][j].setValue( squares[i-1][j].getValue() +1 );
		
		if(j+1 < width  && i-1 >= 0 && !squares[i-1][j+1].hasMine() )
			squares[i-1][j+1].setValue( squares[i-1][j+1].getValue() +1 );
		
		if ( j+1 < width && !squares[i][j+1].hasMine() )
			squares[i][j+1].setValue( squares[i][j+1].getValue() +1 );
		
		if(j+1 < width  && i+1 < height  && !squares[i+1][j+1].hasMine() )
			squares[i+1][j+1].setValue( squares[i+1][j+1].getValue() +1 );
		
		if ( i+1 < height && !squares[i+1][j].hasMine() )
			squares[i+1][j].setValue( squares[i+1][j].getValue() +1 );
		
		if(j-1 >= 0  && i+1 < height && !squares[i+1][j-1].hasMine() )
			squares[i+1][j-1].setValue( squares[i+1][j-1].getValue() +1 );
		
		if(j-1 >= 0 && !squares[i][j-1].hasMine() )
			squares[i][j-1].setValue( squares[i][j-1].getValue() +1 );
		
	}
	
	private void setAction ( ) {
		
		for( int i = 0 ; i < height; i++ ) {
			for ( int j = 0; j < width; j++ ) {
				if( squares[i][j].getValue() == 0 )  squares[i][j].setAction( new ClearAction( getNeighbouringSquares ( i, j  )));
				else if( squares[i][j].getValue() == -1 ) squares[i][j].setAction( new BlowUpAction( this));
				else squares[i][j].setAction(new NullAction());
				
			}
		}
		
	}
	
	private List<Square> getNeighbouringSquares ( int i , int j  ){
		
		List<Square> neighbouringSquares = new ArrayList<Square>();
		if( j-1 >= 0  && i-1 >= 0 )
			neighbouringSquares.add( squares[i-1][j-1] );
		if( i-1 >= 0)
			neighbouringSquares.add( squares[i-1][j] );
		if(j+1 < width  && i-1 >= 0 )
			neighbouringSquares.add( squares[i-1][j+1] );
		if (j+1 < width)
			neighbouringSquares.add( squares[i][j+1] );
		if(j+1 < width  && i+1 < height)
			neighbouringSquares.add( squares[i+1][j+1] );
		if(i+1 < height)
			neighbouringSquares.add( squares[i+1][j] );
		if(j-1 >= 0  && i+1 < height)
			neighbouringSquares.add( squares[i+1][j-1] );
		if(j-1 >= 0)
			neighbouringSquares.add( squares[i][j-1] );
		
		return neighbouringSquares;
		
	}
		
	public Board(int mines, Square[][] squares) {

		ArgumentChecks.isTrue(mines == 10, "mines must be 10");
		this.squares = squares;
	}
	
	
	public boolean hasExploded() {
		return exploded;
		
	}
	
	public void markAsExploded() {
		this.exploded = true;
	}
	
	public void print() {
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) 
				System.out.print(squares[i][j].getValue()+"       ");
			
			System.out.println();
			
		}
	}
	
	public void stepOn(int x, int y) {
		
		if(!squares[x][y].isFlagged() && !squares[x][y].isOpened()) {
			squares[x][y].stepOn();
		}
		
	}
	
	public void unVeil() {
		
		for ( int i = 0 ; i < height; i++ ) {
			for( int j = 0 ; j < width; j++ )
				squares[i][j].open();
		}
		
		
	}
	
	public int getNumberOfFlagsLeft() {
		return numFlag;
	}

	public int getNumberOfMinesLeft(){
		return numMines;
	}
	
	
	public void flag(int x, int y) throws GameException{	
		
		if( squares[x][y].isFlagged() ) throw new GameException("The Square is already Flagged. Please try again.\n");
		else if ( squares[x][y].isOpened() ) throw new GameException("You can't flag a opened square. Please try again.\n");
		else{
			squares[x][y].flag();
			numFlag--;
		}
			
		
		
	}
	
	public void unFlag(int x, int y) {
		// se c'è la bandiera levala
		if(squares[x][y].isFlagged()) {
			squares[x][y].unFlag();
			numFlag++;
		}
	}
		
	/*
	 * 	Devuelve un array de caracteres que representa el estado del tablero de juego. 
		Cada posición del array que devuelve, contendrá el carácter que representa la 
		casilla gráficamente de acuerdo a su valor y estado actual.
	 */
	public char[][] getState(){
		
		char[][] board = new char[height][width];
		
		for (int i = 0 ; i < height ; i++ ) {
			for ( int j = 0 ; j < width ; j++ ) {
				
				if ( squares[i][j].isFlagged() ) board[i][j] = 182;
				else if (squares[i][j].isOpened() && squares[i][j].getValue() == 0  ) board[i][j] = ' ';
				else if (squares[i][j].isOpened() && squares[i][j].getValue() != 0) board[i][j] = (char) (squares[i][j].getValue()+ '0');
				else if( squares[i][j].isOpened() && squares[i][j].getValue() == -1 ) board[i][j] = 40;
				else board[i][j] = '#';
				
			}
			
		}
		
		return board;
	}
	
	
	
	public Square[][] getSquares() {
		
			Square[][] copy = new Square[height][width];
			for (int i = 0; i <height; i++) {
	            copy[i] = squares[i].clone();
	        } 
	        return copy;
	}
	
	/*
	 * 	Busca una casilla vacía al azar y ejecuta stepOn sobre ella para descubrir una isla. 
		La casilla escogida al azar no podrá ser una esquina del tablero. Asegúrate de que 
		este método se llama una vez antes de mostrar el tablero y solicitar la primera 
		operación o comando al usuario. 
	 */
	public void uncoverWelcomeArea() {
		
		boolean flag = true;
		
		while( flag ) {
			Random rand = new Random();
			
			int i = rand.nextInt(height);
			int j = rand.nextInt(width);
			
			if( squares[i][j].getValue() == 0) {
				squares[i][j].stepOn();
				flag = !flag;
			}
			
		}	
		
	}

	public int getNumberOfRows() {
		return height;
	}
	
	public int getNumberOfColumns() {
		return width;
	}

	
	
	
	
		

	

}
