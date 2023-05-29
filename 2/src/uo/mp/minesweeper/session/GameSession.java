
package uo.mp.minesweeper.session;


import uo.mp.lab.util.log.SimpleLogger;
import uo.mp.minesweeper.exception.GameException;
import uo.mp.minesweeper.exception.InvalidLineFormatException;
import uo.mp.minesweeper.exception.UserInteractionException;
import uo.mp.minesweeper.game.Board;
import uo.mp.minesweeper.game.Game;
import uo.mp.minesweeper.game.GameInteractor;
import uo.mp.minesweeper.ranking.GameRanking;
import uo.mp.minesweeper.ranking.GameRankingEntry;


public class GameSession {
	private SessionInteractor sessionInteractor;
	private GameInteractor gameInteractor;
	
	private SimpleLogger logger;
	private GameRanking ranking;
	private Game game;
	private String username;
	
	public void run() {
		
		callAskName();
		
		boolean flag = true;
		
    	while( flag ) {
    		
    		flag = menu(); 
    		
    	}
	        
	        
    
	}
	
	private void callAskName ( ) {
		//TODO da cambiare i catch 
		boolean flag = true;
		
		while( flag ) {
			try {
				
				this.username = sessionInteractor.askUserName();
				flag = false;
				
			}catch ( GameException ge ) {
				
	    		sessionInteractor.showErrorMessage(ge.getMessage());
	    		flag = true;
			}
		}
		
	}
	
	private boolean menu() {
		int choice;
		
		try {
    		choice =  sessionInteractor.askNextOption();
    		
    		
		}catch ( UserInteractionException ue ) {
			
			logger.log(ue);
			sessionInteractor.showErrorMessage(ue.getMessage());
			return true;
		
    	}
    	
    	
    	switch ( choice ) {
    		
	    	case 0: //end 
	    		return false;
	    			    	
	    	case 1: //play
	    		return play();
	    		
	    		
	    	case 2: //showMyResuslt
	    		
	    		sessionInteractor.showPersonalRanking(ranking.getEntriesForUserName(username));
	    		break;
	    		
	    	case 3: //showResuslt
	    		
	    		sessionInteractor.showRanking(ranking.getAllEntries());
	    		break;
	    		
	    	case 4://write on file
	    		writeFile();
	    		break;
	    	case 5:
	    		readFile();
    	}
    	
    	return true;
	    		    	

	    
	}
	

	        
    			
    private boolean writeFile() {
    	
    	try {
			ranking.saveResults( sessionInteractor.askFileName() );
			return true;
		} catch (UserInteractionException e) {
			logger.log(e);
			sessionInteractor.showErrorMessage(e.getMessage());
			return true;
		}
		
	}

	private boolean	readFile() {
		
		try{
			ranking.loadGameRankingEntry( sessionInteractor.askFileName() );
			return true;
		}catch(InvalidLineFormatException ie){
			
			//TODO cambiare tipo di eccezione
			logger.log(ie);
			sessionInteractor.showErrorMessage(ie.getMessage());
			ranking = new GameRanking();
			
			return true;
		}
		
		
	}

	private boolean play() {
    	
    	try {
    		boolean flag = true;
    		GameLevel difficulty = null;
    		
    		while ( flag ) {
    			
    			try {
    				difficulty = sessionInteractor.askGameLevel();
    				flag = !flag;
    				
    			}catch(UserInteractionException ue) {
    				
    				logger.log(ue);
    				sessionInteractor.showErrorMessage(ue.getMessage());
    			}
    			
    		}
	        

	        game = new Game(new Board(difficulty));
	        
	        game.setInteractor(gameInteractor);
	        game.play();

	        
	        
	        if ( sessionInteractor.doYouWantToRegisterYourScore() ) {
	            ranking.append(new GameRankingEntry(username, difficulty, game.getTiempo(), game.getWinner() ));
	        }
	        
	        return true;
	        
    	}catch ( UserInteractionException ue ) {
		
			logger.log(ue);
			sessionInteractor.showErrorMessage(ue.getMessage());
			return true;
		
    	}catch ( GameException ge ) {
		
			sessionInteractor.showErrorMessage(ge.getMessage());
			return true;
		
    	}catch ( Exception e ) {
			
			logger.log(e);
			sessionInteractor.showFatalErrorMessage(e.getMessage());
			return false;
    	}


		
	}
		

	
	public void setSessionInteractor(SessionInteractor interactor) {
	    this.sessionInteractor = interactor;
	}
	
	public void setGameInteractor(GameInteractor interactor) {
	    this.gameInteractor = interactor;
	}
	
	public void setLogger(SimpleLogger logger) {
	    this.logger = logger;
	}
	
	public void setGameRanking(GameRanking ranking) {
	    this.ranking = ranking;
	}

}
