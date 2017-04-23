package com.mcissoko.play.sudoku;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.mcissoko.play.sudoku.api.PlaySequence;
import com.mcissoko.play.sudoku.api.Sequence;

public class Case {

	private Position position;
	private StateCaseEnum state;
	private List<Integer> candidates;
	private Integer content;
	private Group group;
	
	private List<Integer> triedCandidates;
	
	private final Random randomizer;
	public Case(Position position, Group group) {
		super();
		this.position = position;
		this.state = StateCaseEnum.EMPTY;
		candidates = new ArrayList<>();
		triedCandidates = new ArrayList<>();
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
	
	public void setCandidates(List<Integer> candidates) {
		this.candidates = new ArrayList<>(candidates);
	}

	private Integer oneCandidate(){
		List<Integer> list = new ArrayList<>(candidates);
		list.removeAll(triedCandidates);
		if(list.isEmpty()){
			return 0;
		}
		Integer candidate = list.get(randomizer.nextInt(list.size()));
		
		return candidate;
	}
	
	public void resetTried(){
		this.triedCandidates.clear();
	}
	
	public PlaySequence fillContent(){
		Integer candidate = oneCandidate();
		if(candidate == 0){
			//System.out.println(this+ "; essais: " + this.triedCandidates);
			//System.out.println("Echec tentative");
			return null;
		}
		setContent(candidate);
		
		
//		if(!this.group.isContentUnique(this)){
//			//removeCandidate(candidate);
//			this.resetContent();
//			fillContent();
//			
//			return;
//		}else{
//			this.setContent(candidate);
//			pop(candidate);
//		}
		PlaySequence playSequence = new PlaySequence(getGroup().getIndex(), getPosition().getIndex(), getContent(), getCandidates());
		getCandidates().clear();	
		return playSequence;
	}
//	public void fillContent(Integer candidate){
//		candidates.remove(candidate);
//		setContent(candidate);
//		if(!this.group.isContentUnique(this)){
//			removeCandidate(candidate);
//			this.resetContent();
//			fillContent();
//			
//			return;
//		}else{
//			this.setContent(candidate);
//			pop(candidate);
//			candidates.clear();			
//		}
//		
//	}
	
	public void removeCandidate(Integer candidate, PlaySequence playSequence){
		candidates.remove(candidate);
		playSequence.getSequences().add(new Sequence(this.group.getIndex(), getPosition().getIndex()));
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
	
	public List<Integer> getTriedCandidates() {
		return triedCandidates;
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
		
		if(!this.triedCandidates.contains(content)){
			this.triedCandidates.add(content);
		}
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

	public void restaureCandidate(Integer sellectedCandidate) {
		if(candidates.contains(sellectedCandidate)){
			return;
		}
		candidates.add(sellectedCandidate);
	}

	public boolean hasCandidate(Integer content) {
		return this.candidates.contains(content);
	}

	
}
