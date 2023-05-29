package uo.mp.minesweeper.game;

import uo.mp.minesweeper.exception.*;

public interface GameInteractor {
	
	GameMove askMove(int rows, int columns) throws UserInteractionException;
//	Solicita al usuario un comando (s, f, u) y unas coordenadas de fila y columna. 
//	Devuelve un objeto de tipo GameMove que contiene la información facilitada por el 
//	usuario. Los parámetros rows y columns marcan el tamaño máximo de fila y 
//	columna que el usuario puede introducir. 
//	El método ha de asegurarse de que tanto el carácter de comando como las 
//	coordenadas devueltas son valores válidos (dentro de los rangos permitidos).
	
	void showGame(long elapsedTime, Board board);
//	Muestra el estado del juego al usuario. Esto incluye estado del tablero, tiempo 
//	empleado y banderas que quedan por colocar. elapsedTime contiene el tiempo 
//	empleado, y board es una referencia al objeto tablero cuya información se va a 
//	mostrar.
	
	void showGameFinished();
	//Informa al usuario de que el juego ha terminado.

	void showCongratulations(long elpasedTime);
//	Informa al usuario de que ha ganado el juego. Le muestra el tiempo que ha 
//	empleado, que se recibe en el parámetro elapsedTime

	void showBooommm();
//	Informa al usuario de que ha pisado una casilla de mina.
	
	void showReadyToStart();
//	Informa al usuario de que el juego está listo para comenzar.

	void showErrorMessage(String message);


}
