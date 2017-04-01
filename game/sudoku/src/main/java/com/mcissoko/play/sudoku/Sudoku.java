package com.mcissoko.play.sudoku;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.mcissoko.play.sudoku.player.Grille;

public class Sudoku {

	private Grille grid;
		
	public Sudoku() {
		grid = new Grille();
		 
	}
	
	public void process(){
		try {
			Map<Integer, Object> result = new HashMap<>();
			Case candidate = grid.getGroup(GroupIndexEnum.INDEX_1).getCases().get(PositionIndexEnum.INDEX_1);
			boolean fixed = true;
			result.put(0, fixed);
			result.put(1, candidate );
			while(true){
				fixed = true;
				result.put(0, fixed);
				
				for(GroupIndexEnum groupIndexEnum: GroupIndexEnum.values()){
					Group group = grid.getGroup(groupIndexEnum);
					result = group.checkCandidate(result);
					fixed = fixed & (boolean) result.get(0);
				}
				if(fixed){
					if( grid.isSudoku()){
						System.out.println(grid);
						System.out.println("resolu");
						return;
					}else{
						System.out.println("Echec");
						grid = new Grille();
						process();
					}
					
				}
				candidate = (Case) result.get(1);
				if(candidate.getState() == StateCaseEnum.FILLED){
					continue;
				}
				/*
				Map<Integer, Integer> occurrenceInLine = new HashMap<>();
				Map<Integer, Integer> occurrenceInColumn = new HashMap<>();
				for(Integer i: candidate.getCandidates()){
					occurrenceInLine.put(i, 0);
					occurrenceInColumn.put(i, 0);
				}
				candidate.getGroup().calculateOccurence(candidate, occurrenceInLine, occurrenceInColumn);
				
				Entry<Integer, Integer> candidateLine = min(occurrenceInLine);
				Entry<Integer, Integer> candidateColumn = min(occurrenceInColumn);
				Integer value;
				if(candidateLine.getValue() <= candidateColumn.getValue()){
					value = candidateLine.getValue();
				}else{
					value = candidateColumn.getValue();
				}
				candidate.fillContent(value);
				
				//*/
				candidate.fillContent();
				//System.out.println(grille);
				
				candidate.getGroup().removeCandidate(candidate);
			}
		} catch (IllegalStateException e) {
			e.printStackTrace();
			System.out.println(grid);
			grid = new Grille();
			process();
		}
		
	}
	
	public Entry<Integer, Integer> max(Map<Integer, Integer> occurrence){
		int maxOccur = (Collections.max(occurrence.values())); 
		for (Entry<Integer, Integer> entry : occurrence.entrySet()) {  
            if (entry.getValue() == maxOccur) {
                return entry;
            }
        }
		return null;
	}
	
	public Entry<Integer, Integer> min(Map<Integer, Integer> occurrence){
		int minOccur = (Collections.min(occurrence.values())); 
		for (Entry<Integer, Integer> entry : occurrence.entrySet()) {  
            if (entry.getValue() == minOccur) {
                return entry;
            }
        }
		return null;
	}
	
	
	public static void main(String[] args) {
		
		Sudoku player = new Sudoku();
		player.process();
		/*
		Grille grille = new Grille();
		
		GroupPlayer[] gPlager = new GroupPlayer[9];
		
		for(GroupIndexEnum groupIndexEnum: GroupIndexEnum.values()){
			gPlager[groupIndexEnum.getIndex() - 1] = new GroupPlayer(grille, groupIndexEnum);
		}
		gPlager[0].start();
		for(int i = 1; i < gPlager.length; i++){
			
			try {
				gPlager[i].start();
				gPlager[i].wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		//*/
		
	
	}
	
}
