package uo.mp.minesweeper.ranking;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import uo.mp.minesweeper.session.GameLevel;

public class GameRankingEntry {

	private String userName;
	private GameLevel level;
	private long duration ;
	private boolean hasWon;
	private Date date;
	
	public GameRankingEntry ( String userName, GameLevel level, long duration , boolean hasWon) {
		
		this.userName = userName;
		this.level = level;
		this.duration = duration; 
		this.hasWon = hasWon;
        
		Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy HH:mm:ss");

        
		try {
			this.date = dateFormat.parse( dateFormat.format(date) );
		} catch (ParseException e) {
			this.date = new Date();
		}
		
		
	}
	
	public GameRankingEntry () { this.date = new Date(); }
	
	
	public String getUserName() {
		return userName;
	}

	public GameLevel getLevel() {
		return level;
	}

	public long getDuration() {
		return duration;
	}

	public boolean isHasWon() {
		return hasWon;
	}

	public Date getDate() {
		return date;
	}

	@Override
	public String toString() {
		return "GameRankingEntry [userName=" + userName + ", level=" + level + ", duration=" + duration + ", hasWon="
				+ hasWon + ", date=" + date + "]";
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setLevel(GameLevel level) {
		this.level = level;
	}

	public void setDuration(long duration) {
		this.duration = duration;
	}

	public void setHasWon(boolean hasWon) {
		this.hasWon = hasWon;
	}

	public void setDate() {
		this.date = new Date();
	}
	
	public void setDate(Date date) {
		this.date = date;
	}

	public String serialize() {

		return String.format("%s;%s;%s;%s;%s;%d",getUserName(),new SimpleDateFormat("yyyy/MM/dd").format( getDate() )
												, new SimpleDateFormat("HH:mm:ss").format( getDate() ),getLevel().name().toString()
												,isHasWon(),getDuration());
	}

	
	
	
	

	
}
