package uo.mp.minesweeper.ranking.parser;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

import uo.mp.lab.util.check.ArgumentChecks;
import uo.mp.lab.util.log.FileLogger;
import uo.mp.minesweeper.exception.*;
import uo.mp.minesweeper.ranking.GameRankingEntry;
import uo.mp.minesweeper.session.GameLevel;



public class GameRankingParser {
	private int lineNumber = 1;
	
	public List<GameRankingEntry> parse(List<String> lines) {
		
	
	ArgumentChecks.isTrue(lines !=null, "illegal null test");
  
  	List<GameRankingEntry> res = new LinkedList<GameRankingEntry>();
  		
  		for(String line: lines){
  			try{
  				checkIsBlank(line);
  				
  				parseLine(line);
  				
  				res.add( createGameEntry ( line ));
  				
  			//for(GameRankingEntry e : res)	System.out.println(e);
  				
  			}catch(InvalidLineFormatException e){
  				e.printStackTrace();
  				FileLogger fileLogger = new FileLogger();
  				fileLogger.log( e );
  				
  			} catch (ParseException e) {
  				e.printStackTrace();
  				FileLogger fileLogger = new FileLogger();
  				fileLogger.log( e );
			}
  			lineNumber++;
  		}
  		
  		return res;
  }


	private GameRankingEntry createGameEntry(String line) throws ParseException {
		
		String[] tokens = line.split(";");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		
		GameRankingEntry entry = new GameRankingEntry();
		
		entry.setUserName(tokens[0]);
		
		entry.setDate( dateFormat.parse(tokens[1] +" "+ tokens[2]));
		
		entry.setLevel( GameLevel.valueOf(tokens[3]));
	
		entry.setHasWon( tokens[4].contentEquals("lose") ? false : true );
		
		entry.setDuration(Integer.parseInt(tokens[5]));
	
		
		return entry;
	}


	private void checkIsBlank(String line) throws InvalidLineFormatException {
		if (line.isBlank()) {
			throw new InvalidLineFormatException(lineNumber, "Invalid line , is blank");
		}

	}
	
	//System.out.println("UserName\tDate\t\tHour\t\tLevel\tResult\tTime");
	private void parseLine(String line) throws InvalidLineFormatException {
		
		//System.out.println(line);
		
		String[] tokens = line.split(";");
		
		checkNumberOfToken(tokens);
		
		checkUsername ( tokens[0] ) ;
		
		checkDate ( tokens[1]);
		
		checkHour(tokens[2]);
		
		checkLevel ( tokens[3]);
		
		checkResult ( tokens[4]);
		
		checkTime ( tokens[5]);
		
	}

	
	private void checkNumberOfToken(String[] tokens) throws InvalidLineFormatException {

		if (tokens.length == 5)
			throw new InvalidLineFormatException(lineNumber, "Invalid line length");

	}

	private void checkTime(String string) throws InvalidLineFormatException{
		
		if( Integer.parseInt(string) < 0 ) 
			throw new InvalidLineFormatException(lineNumber, "Invalid time Format.\n");
	}

	private void checkResult(String string) throws InvalidLineFormatException{

		if( !string.equals("lose") && !string.equals("win") )
			throw new InvalidLineFormatException(lineNumber, "Invalid result Format.\n");
			
		
	}

	private void checkLevel(String string) throws InvalidLineFormatException{
		
		boolean flag = false ;
		
		switch ( string ) {
		
		case "EASY":
			flag = !flag;
			break;
		case "MEDIUM":
			flag = !flag;
			break;
		case "HARD":
			flag = !flag;
			break;
		}
		
		if( !flag )
			throw new InvalidLineFormatException(lineNumber, "Invalid Level Format.\n");
	}

	private void checkHour(String string) throws InvalidLineFormatException{
		String regex = "^[0-9]{2}:[0-9]{2}:[0-9]{2}$"; //formato hh:mm:ss
		
		if ( !string.matches(regex) ) 
			throw new InvalidLineFormatException(lineNumber, "Invalid Hour Format.\n");
	}

	private void checkDate(String string) throws InvalidLineFormatException{
		String regex = "^([0-9][0-9][0-9][0-9])/(0[0-9]||1[0-2])/([0-2][0-9]||3[0-1])$";  // formato dd/mm/yyyy
		
		if ( !string.matches(regex) ) 
			throw new InvalidLineFormatException(lineNumber, "Invalid Date Format.\n");

	}

	private void checkUsername(String username) throws InvalidLineFormatException{
		
        if (!username.matches("^[a-z]+$")) 
            throw new InvalidLineFormatException(lineNumber, "Invalid username format.\n");

	}
}
