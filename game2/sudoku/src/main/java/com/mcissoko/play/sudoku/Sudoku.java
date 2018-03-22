package com.mcissoko.play.sudoku;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Sudoku {
	Logger log = LoggerFactory.getLogger(Sudoku.class);  

	private Grille grid;
	private LocalDateTime startDate;
	private LocalDateTime endDate;
	private Duration duration;
		
	public Sudoku() {
		grid = new Grille();
	}
	
	public Sudoku(Grille grid) {
		super();
		this.grid = grid;
	}
	
	public int solution(IMonitor monitor){
		int result;
		if((result = resolve(monitor)) >= 0) {
			this.duration = Duration.between(endDate, startDate);
			log.info("Start date: {}", startDate);
			log.info("End date: {}", endDate);
			log.info("Durée: {}", duration);
		}else {
			this.duration = null;			
		}
		return result;
	}

	public int resolve(IMonitor monitor){
		try {
			this.startDate = LocalDateTime.now();
			if(!this.grid.isValid()) {
				endDate = LocalDateTime.now();
				// pas de solution possible
				log.warn("Pas de solution possible pour cette grille\n{}", this.grid);
				return 3;
			}
			
			//Date t1 = new Date();
			Map<Integer, Object> result = new HashMap<>();
			Case candidate = grid.random();
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
					candidate = (Case) result.get(1);
				}
				
				if(gridFilled){
					this.endDate = LocalDateTime.now();
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
					if(candidate.getState() == StateCaseEnum.FILLED || candidate.getState() == StateCaseEnum.FIXED){
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
						this.endDate = LocalDateTime.now();
						log.info("Echec");
						log.info("Taille de la sequence", grid.getPlaySequence().size());
						return 2;
					}
					seach = false;
					gridFilled = false;
					if(monitor != null) {
						monitor.erase(candidate);						
					}
					continue;
				}
				
				candidate.getGroup().removeCandidate(candidate, playSequence);
				
				grid.addPlaySequence(playSequence); 
				
				if(monitor != null) {
					monitor.display(candidate);
				}
				
				seach = true;
				gridFilled = false;
			}
		} catch (IllegalStateException e) {
			log.error("Exception", e);
			return -1;
		}
		return -2;
	}
	
	public Grille getGrid() {
		return grid;
	}

	public void setGrid(Grille grid) {
		this.grid = grid;
	}
	
	public static Grille createNewEmptyGrid() {
		return new Grille();
	}

	public Duration getDuration() {
		return duration;
	}
	
}
