package test.uo.mp.minesweeper.ranking.parser;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uo.mp.minesweeper.exception.GameException;
import uo.mp.minesweeper.ranking.parser.GameRankingParser;

/*
: lista vacía, lista con alguna línea 
en blanco, lista con alguna línea incorrecta (diferentes errores), lista con todas las líneas 
correctas (diferentes combinaciones de campos válidos), 

*/
class ParseTest {

	GameRankingParser gmr = new GameRankingParser();
	
	@BeforeEach
	void setUp() throws Exception {
	}

	
	/**
     * GIVEN una lista vacia
     * WHEN se llama el metodo parse 
     * THEN no se hace nada
	 * @throws GameException 
     */
	@Test
	void parseListaVacia(List<String> lines) {
		lines.clear();
		gmr.parse(lines);
		
	}

	/**
     * GIVEN una lista con lina blanca
     * WHEN se llama el metodo parse 
     * THEN se hace el parce solo de la linea correcta
	 * @throws GameException 
     */
	@Test
	void parseListaConAlgunaLineaBlanca(List<String> lines) {
		lines.add("ciao");
		lines.add("");
		lines.add("hola");
		gmr.parse(lines);
		
	}
	
	/**
     * GIVEN una lista incorrecta
     * WHEN se llama el metodo parse 
     * THEN  se hace el parce solo de la linea correcta
	 * @throws GameException 
     */
	@Test
	void parseListaConAlgunaLineaIncorrecta(List<String> lines) {
		lines.add("adolfo;11/05/2023;20:02:44;EASY;lose;12");
		lines.add("gianpietro;14/05/2023;20:02:44;EASY;lose;12");
		lines.add("");
		lines.add("gianpietro;14/05/20666;20:02:44;Med;lose;12");
		
		gmr.parse(lines);
		
	}
	
	/**
     * GIVEN una lista valida
     * WHEN se llama el metodo parse 
     * THEN  se hace el parce de todo
	 * @throws GameException 
     */
	@Test
	void parseListaValida(List<String> lines) {
		
		lines.add("adolfo;11/05/2023;20:02:44;EASY;lose;12");
		lines.add("gianpietro;14/05/2023;20:02:44;EASY;lose;12");
		lines.add("peppe;14/03/2023;20:02:44;EASY;lose;12");
		
		gmr.parse(lines);
		
	}
}
