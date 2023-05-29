package uo.mp.minesweeper.ranking.comparator;

import java.util.Comparator;

import uo.mp.minesweeper.ranking.GameRankingEntry;

public class GameRankingComparator implements Comparator<GameRankingEntry> {

	@Override
	public int compare(GameRankingEntry entry1, GameRankingEntry entry2) {
       
        if (entry1.getLevel() != entry2.getLevel()) {
            return entry2.getLevel().compareTo(entry1.getLevel());
        }
        
        
        if (entry1.getDuration() != entry2.getDuration()) {
            return  (int) (entry1.getDuration() - entry2.getDuration());
        }
        
    
        return entry1.getDate().compareTo(entry2.getDate());
        
    }

}
