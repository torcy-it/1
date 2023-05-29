package test.uo.mp.minesweeper.ranking.serializer;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uo.mp.minesweeper.exception.GameException;
import uo.mp.minesweeper.ranking.GameRankingEntry;
import uo.mp.minesweeper.ranking.serialize.GameRankingSerialize;

/*
 * . Casos 
principales: lista vacía, lista con objetos con diferentes campos (victoria/derrota, nivel de 
dificultad…),
 * 
 */
class SerializerTest {
	
	GameRankingSerialize gs = new GameRankingSerialize();
	
	@BeforeEach
	void setUp() throws Exception {
	}

	/**
     * GIVEN una lista vacia
     * WHEN se llama el metodo serialize 
     * THEN no se hace nada
	 * @throws GameException 
     */
	@Test
	void serializeListaVacia(List< GameRankingEntry > gameRankingEntries) {
		gameRankingEntries.clear();
		gs.serialize(gameRankingEntries);
		
	}


}
