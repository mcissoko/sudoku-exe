package com.mcissoko.play.sudoku;

import java.util.Collections;
import java.util.Date;
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
	
	public Sudoku(Grille grid) {
		super();
		this.grid = grid;
	}

	public int process(){
		try {
			//Date t1 = new Date();
			Map<Integer, Object> result = new HashMap<>();
			Case candidate = grid.random();
			System.out.println("Case depart: " + candidate);
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
				}
				if(gridFilled){
					if(grid.isSudoku()){
						//Date t2 = new Date();
						System.out.println(grid);
						//System.out.println(t2.getTime() - t1.getTime());
						System.out.println("resolu");
						return 0;
					}
					System.err.println(grid);
					System.out.println(grid.getPlaySequence().size());
					System.err.println("Echec");
					return 1;
				}else{
					if(candidate.getState() == StateCaseEnum.FILLED || candidate.getState() == StateCaseEnum.FIXED){
						seach = true;
						continue;
					}	
				}
				
				PlaySequence playSequence = candidate.fill();
				if(playSequence == null ){
					candidate.resetTried();
					
					//--System.out.println(candidate);
					
					System.err.println(grid);
					candidate = grid.restaure();
					if(candidate == null){
						System.err.println("KO");
						//--System.out.println(grid.getPlaySequence().size());
						return 2;
					}
					seach = false;
					gridFilled = false;
					continue;
				}
				
				candidate.getGroup().removeCandidate(candidate, playSequence);
				
				grid.addPlaySequence(playSequence); 
				
				seach = true;
				gridFilled = false;
				//System.out.println(grid);
			}
		} catch (IllegalStateException e) {
			e.printStackTrace();
//			System.out.println(grid);
			return -1;
		}
		return -2;
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
	
	}

	public Grille getGrid() {
		return grid;
	}

	public void setGrid(Grille grid) {
		this.grid = grid;
	}
	
	
}
