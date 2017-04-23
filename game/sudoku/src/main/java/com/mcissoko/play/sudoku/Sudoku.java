package com.mcissoko.play.sudoku;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.mcissoko.play.sudoku.api.PlaySequence;
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
			boolean gridFilled;
			result.put(1, candidate );
			gridFilled = false;
			boolean seach = true;
			
			while(!gridFilled){
				
				result.put(0, gridFilled);
				if(seach){
					gridFilled = true;
					for(GroupIndexEnum groupIndexEnum: GroupIndexEnum.values()){
						Group group = grid.getGroup(groupIndexEnum);
						result = group.checkCandidate(result);
						gridFilled = gridFilled & (boolean) result.get(0);
					}	
					candidate = (Case) result.get(1);
//					if(candidate.getState() == StateCaseEnum.FILLED){
//						seach = true;
//						
//						//continue;
//					}		
				}
				if(gridFilled){
					if(grid.isSudoku()){
						System.out.println(grid);
						System.out.println("resolu");
						//System.out.println(grid.getPlaySequence().size());
						return;
					}else{
						System.out.println("Echec");
//						
						System.err.println(grid);
						System.out.println(grid.getPlaySequence().size());
						System.out.println(candidate);
						
						candidate = grid.restaure();
						if(candidate == null){
							System.err.println("KO 1");
							return;
						}
						seach = false;
						gridFilled = false;
						continue;
						
					}
					
				}else{
					if(candidate.getState() == StateCaseEnum.FILLED){
						seach = true;
						
						continue;
					}	
				}
				
				
				PlaySequence playSequence = candidate.fillContent();
				//boolean restaure = false;
				if(playSequence == null ){
					candidate.resetTried();
					
					System.out.println(candidate);
					System.err.println(grid);
					candidate = grid.restaure();
					if(candidate == null){
						System.err.println("KO");
						System.out.println(grid.getPlaySequence().size());
						return;
					}
					seach = false;
					gridFilled = false;
					continue;
					
				}
				
				candidate.getGroup().removeCandidate(candidate, playSequence);
				
				
				grid.addPlaySequence(playSequence); 
				
				seach = true;
				gridFilled = false;
			}
		} catch (IllegalStateException e) {
			e.printStackTrace();
//			System.out.println(grid);
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
