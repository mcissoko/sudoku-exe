package com.mcissoko.play.sudoku;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class Case implements Serializable{

	private static final long serialVersionUID = -3308228191662064132L;
	
	private Position position;
	private StateCaseEnum state;
	private List<Integer> candidates;
	private Integer content;
	private Group group;
	
	private List<Integer> triedCandidates;
	
	private final Random randomizer;
	protected Case(Position position, Group group) {
		super();
		this.position = position;
		this.state = StateCaseEnum.EMPTY;
		candidates = new ArrayList<>();
		triedCandidates = new ArrayList<>();
		
		IntStream.rangeClosed(1, 9).forEach(i -> candidates.add(i));
		
		this.content = 0;
		randomizer = new Random();
		this.group = group;
	}
	
	public int sizeOfCandidate(){
		return candidates.size();
	}
	
	protected void setCandidates(List<Integer> candidates) {
		if(this.state == StateCaseEnum.FIXED){
			return;
		}
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
	
	protected void resetTried(){
		this.triedCandidates.clear();
	}
	
	public void fix(Integer value){
		this.content = value;
		this.state = StateCaseEnum.FIXED;
		this.candidates.clear();
		this.group.removeCandidate(this);
		this.group.removeCandidateInGroupColumn(this);
		this.group.removeCandidateInGroupLine(this);
	}
	public PlaySequence fill(){
		Integer candidate = oneCandidate();
		if(candidate == 0){
			return null;
		}
		fillContent(candidate);
		if(!this.group.isContentUnique(this, null)){
			this.resetContent();
			return this.fill();
		}
		
		PlaySequence playSequence = new PlaySequence(getGroup().getIndex(), getPosition().getIndex(), getContent(), getCandidates());
		getCandidates().clear();	
		return playSequence;
	}

	protected void removeCandidate(Integer candidate, PlaySequence playSequence){
		candidates.remove(candidate);
		playSequence.getSequences().add(new Sequence(this.group.getIndex(), getPosition().getIndex()));
	}
	
	protected void removeCandidate(Integer candidate){
		candidates.remove(candidate);
	}
	
	protected List<Integer> getCandidates() {
		return candidates;
	}

	protected StateCaseEnum getEtat() {
		return state;
	}
	

	public Group getGroup() {
		return group;
	}
	
	protected List<Integer> getTriedCandidates() {
		return triedCandidates;
	}

	protected void setState(StateCaseEnum state) {
		this.state = state;
	}
	
	public StateCaseEnum getState() {
		return state;
	}

	public Integer getContent() {
		return content;
	}

	public void fillContent(Integer content) {
		this.content = content;
		this.state = StateCaseEnum.FILLED;
		
		if(!this.triedCandidates.contains(content)){
			this.triedCandidates.add(content);
		}
	}
	
	
	public void tryContent(Integer content) {
		this.content = content;
		this.state = StateCaseEnum.FILLED;
	}
	
	public void resetContent() {
		if(this.state == StateCaseEnum.FIXED){
			return;
		}
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
		if(this.state == StateCaseEnum.FIXED || candidates.contains(sellectedCandidate)){
			return;
		}
		candidates.add(sellectedCandidate);
	}

	public boolean hasCandidate(Integer content) {
		return this.candidates.contains(content);
	}

	
}
