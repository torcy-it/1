package uo.mp.minesweeper.ranking.serialize;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import uo.mp.lab.util.check.ArgumentChecks;

import uo.mp.minesweeper.ranking.GameRankingEntry;

public class GameRankingSerialize {
	
	public List<String> serialize(List<GameRankingEntry> gameRankings) {
//		throws UserInteractionException
//		if( gameRankings == null ) throw new UserInteractionException("Can't load list because is Empty");
		
		ArgumentChecks.isTrue(gameRankings!=null);
		
		List<String> lines = new ArrayList<String>();
		for(GameRankingEntry gameRanking : gameRankings){
			String[] tokens = gameRanking.serialize().split(";");
		
			
			
			tokens[4] = tokens[4].contentEquals("false") ? "lose" : "won";
			
			lines.add(  String.join(";", tokens) );
		}
		
		return lines;
	}
	
}
