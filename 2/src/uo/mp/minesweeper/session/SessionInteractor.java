package uo.mp.minesweeper.session;

import java.util.List;

import uo.mp.minesweeper.exception.*;
import uo.mp.minesweeper.ranking.GameRankingEntry;

public interface SessionInteractor {
	
	public GameLevel askGameLevel() throws UserInteractionException;
	
	public String askUserName() throws GameException;
	
	public int askNextOption() throws UserInteractionException;

	public boolean doYouWantToRegisterYourScore() throws UserInteractionException;
	
	public void showRanking(List<GameRankingEntry> ranking);
	
	public void showPersonalRanking(List<GameRankingEntry> ranking);
	
	public void showGoodBye();

	public void showErrorMessage(String message);
	
	public void showFatalErrorMessage(String message);

	String askFileName();
}
