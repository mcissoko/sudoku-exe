package com.mcissoko.play.sudoku;

import java.util.ArrayList;
import java.util.List;

class PlaySequence {

	private GroupIndexEnum groupIndex;
	private  PositionIndexEnum positionIndex;
	private Integer sellectedCandidate;
	private List<Integer> candidates;
	
	private List<Sequence> sequences;

	PlaySequence(GroupIndexEnum groupIndex, PositionIndexEnum positionIndex, Integer sellectedCandidate, List<Integer> candidates) {
		super();
		this.groupIndex = groupIndex;
		this.positionIndex = positionIndex;
		this.sellectedCandidate = sellectedCandidate;
		
		this.candidates = new ArrayList<>(candidates);
		
		this.sequences = new ArrayList<>();
		
	}

	PlaySequence(GroupIndexEnum groupIndex, PositionIndexEnum positionIndex) {
		super();
		this.groupIndex = groupIndex;
		this.positionIndex = positionIndex;
	}
	
	protected List<Sequence> getSequences() {
		return sequences;
	}

	protected void setSequences(List<Sequence> sequences) {
		this.sequences = sequences;
	}

	protected GroupIndexEnum getGroupIndex() {
		return groupIndex;
	}

	protected PositionIndexEnum getPositionIndex() {
		return positionIndex;
	}

	protected Integer getSellectedCandidate() {
		return sellectedCandidate;
	}

	protected List<Integer> getCandidates() {
		return candidates;
	}
	
	
}
