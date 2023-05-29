package uo.mp.minesweeper.ranking;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import uo.mp.lab.util.check.ArgumentChecks;
import uo.mp.lab.util.file.*;

import uo.mp.minesweeper.exception.InvalidLineFormatException;
import uo.mp.minesweeper.exception.UserInteractionException;
import uo.mp.minesweeper.ranking.parser.GameRankingParser;
import uo.mp.minesweeper.ranking.serialize.GameRankingSerialize;

public class GameRanking {
	
	private static final int MIN_FILENAME_LENGHT = 5;
	
    private List<GameRankingEntry> gameRankingEntries;

    public GameRanking() {
        gameRankingEntries = new ArrayList<>();
    }

    public GameRanking(String rankingFile) {
    	
		try{
			
			loadGameRankingEntry( rankingFile );
			
		}catch(InvalidLineFormatException ie){
			
			ie.printStackTrace();
			gameRankingEntries = new ArrayList<>();
		}
	}
    

	public void append(GameRankingEntry gameRankingEntry) {
        gameRankingEntries.add(gameRankingEntry);
    }

    public List<GameRankingEntry> getAllEntries() {
        return new ArrayList<>(gameRankingEntries);
    }
	
	public List<GameRankingEntry> getEntriesForUserName ( String userName){
	    List<GameRankingEntry> filteredEntries = new ArrayList<>();
	    
	    for (GameRankingEntry entry : gameRankingEntries) {
	        if (entry.getUserName().equals(userName)) {
	            filteredEntries.add(entry);
	        }
	    }
	    return filteredEntries;
	}
	
	public boolean isEmpty ( ) {
		
		return gameRankingEntries.isEmpty();
	}


	public void loadGameRankingEntry(String filename) throws InvalidLineFormatException {

		ArgumentChecks.isTrue(filename !=null );
		ArgumentChecks.isTrue(!filename.isBlank() );
		
		checkFileNname(filename);
		List<String> lines = readGameRankingEntry(filename);
		
		List<GameRankingEntry> entry = new GameRankingParser().parse( lines );
		
		updateGameRankingEntry( entry );
	}
	
	
	
	
	private void checkFileNname(String filename) throws InvalidLineFormatException{
		if(filename.length() < MIN_FILENAME_LENGHT) {
			throw new InvalidLineFormatException("Error reading File");
		}
	}
	
	private void updateGameRankingEntry(List<GameRankingEntry> entry) {
		this.gameRankingEntries = entry;
		
	}
	
	
	private List<String> readGameRankingEntry(String filename) throws InvalidLineFormatException {
			
		try {
				List<String> res = new FileUtil().readLines(filename);
				
				return res;
			}catch(IOException e) {
				e.printStackTrace();
				throw new InvalidLineFormatException("Error reading File");
			}
			
		
	}
	
	
	public void saveResults(String resultsFilename) throws UserInteractionException {
		
			GameRankingSerialize gameRankingSerialize = new GameRankingSerialize();
			
			List<String> lines = gameRankingSerialize.serialize(gameRankingEntries); 
			
		
			new FileUtil().writeLines(resultsFilename, lines);
				

	}




}
