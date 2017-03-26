package com.mcissoko.play.sudoku;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Case {

	private Position position;
	private StateCaseEnum state;
	private List<Integer> candidates;
	private Integer content;
	private Group group;
	
	private final Random randomizer;
	public Case(Position position, Group group) {
		super();
		this.position = position;
		this.state = StateCaseEnum.EMPTY;
		candidates = new ArrayList<>();
		for(int i = 0; i < 9; i++){
			candidates.add(i + 1);
		}
		this.content = 0;
		randomizer = new Random();
		this.group = group;
	}
	
	public int sizeOfCandidate(){
		return candidates.size();
	}
	
	private int pop(Integer candidate){
		//Integer candidate = oneCandidate();
		candidates.remove(candidate);
		
		return candidate.intValue();
	}
	
	private Integer oneCandidate(){
		if(this.candidates.isEmpty()){
			return 0;
		}
		Integer candidate = candidates.get(randomizer.nextInt(candidates.size()));
		
		return candidate;
	}
	
	public void fillContent(){
		Integer candidate = oneCandidate();
		if(candidate == 0){
			throw new IllegalStateException("Echec tentative");
			
		}
		setContent(candidate);
		if(!this.group.isContentUnique(this)){
			removeCandidate(candidate);
			this.resetContent();
			fillContent();
			
			return;
		}else{
			this.setContent(candidate);
			pop(candidate);
			candidates.clear();			
		}
		
	}
	
	public void removeCandidate(Integer candidate){
		candidates.remove(candidate);
	}
	

	public List<Integer> getCandidates() {
		return candidates;
	}

	public StateCaseEnum getEtat() {
		return state;
	}
	

	public Group getGroup() {
		return group;
	}

	public void setState(StateCaseEnum state) {
		this.state = state;
	}
	
	public StateCaseEnum getState() {
		return state;
	}

	public Integer getContent() {
		return content;
	}

	public void setContent(Integer content) {
		this.content = content;
		this.state = StateCaseEnum.FILLED;
	}
	public void resetContent() {
		this.content =  0;
		this.state = StateCaseEnum.EMPTY;
	}

	public Position getPosition() {
		return position;
	}
	
	public String print(){
		if(this.state == StateCaseEnum.EMPTY){
			return "( ) ";
		}
		return "(" +this.content+ ") ";
	}
	
	@Override
	public String toString() {
		return getPosition().getCoordinate().toString() + print() + "  ";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((position == null) ? 0 : position.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Case other = (Case) obj;
		if (position == null) {
			if (other.position != null)
				return false;
		} else if (!position.equals(other.position))
			return false;
		return true;
	}

	
}
