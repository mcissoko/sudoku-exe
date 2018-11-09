package com.mcissoko.game.sudoku;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Sudoku {
	Logger log = LoggerFactory.getLogger(Sudoku.class);  

	private Grid grid;
	private Date startDate;
	private Date endDate;
	private long duration;
		
	public Sudoku() {
		grid = new Grid();
	}
	
	public Sudoku(Grid grid) {
		super();
		this.grid = grid;
	}
	
	public int solution(IMonitor monitor){
		int result;
		this.startDate = new Date();
		if((result = resolve(monitor)) >= 0) {
			this.duration = endDate.getTime() - startDate.getTime();
			log.info("Start date: {}", startDate);
			log.info("End date:   {}", endDate);
			log.info("Durée: {}", duration);
		}else {
			this.duration = 0;			
		}
		return result;
	}

	public int resolve(IMonitor monitor){
		try {
			if(!this.grid.isValid()) {
				endDate = new Date();
				// pas de solution possible
				log.warn("Pas de solution possible pour cette grille\n{}", this.grid);
				return 3;
			}
			
			//Date t1 = new Date();
			Map<Integer, Object> result = new HashMap<>();
			Box candidate = grid.random();
			log.info("Case depart: " + candidate);
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
					candidate = (Box) result.get(1);
				}
				
				if(gridFilled){
					this.endDate = new Date();
					if(grid.isSudoku()){
						log.info("\n {}", grid);
						log.info("resolu");
						return 0;
					}
					log.info("\n {}", grid);
					log.info("Taille de la sequence", grid.getPlaySequence().size());
					log.info("Echec");
					return 1;
				}else{
					if(candidate.getState() == StateBoxEnum.FILLED || candidate.getState() == StateBoxEnum.FIXED){
						seach = true;
						continue;
					}	
				}
				
				PlaySequence playSequence = candidate.fill();
				if(playSequence == null ){
					candidate.resetTried();
										
					log.info("\n {}", grid);
					candidate = grid.restaure();
					if(candidate == null){
						this.endDate = new Date();
						log.info("Echec");
						log.info("Taille de la sequence", grid.getPlaySequence().size());
						return 2;
					}
					seach = false;
					gridFilled = false;
					
					monitor.erase(candidate);						
					
					continue;
				}
				
				candidate.getGroup().removeCandidate(candidate, playSequence);
				
				grid.addPlaySequence(playSequence); 
				
				monitor.display(candidate);
				
				
				seach = true;
				gridFilled = false;
			}
		} catch (IllegalStateException e) {
			log.error("Exception", e);
			return -1;
		}
		return -2;
	}
	
	public Grid getGrid() {
		return grid;
	}

	public void setGrid(Grid grid) {
		this.grid = grid;
	}
	
	public static Grid createNewEmptyGrid() {
		return new Grid();
	}

	public long getDuration() {
		return duration;
	}
	
}
