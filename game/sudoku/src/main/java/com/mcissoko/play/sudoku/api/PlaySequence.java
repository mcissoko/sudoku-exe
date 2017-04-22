package com.mcissoko.play.sudoku.api;

import java.util.ArrayList;
import java.util.List;

import com.mcissoko.play.sudoku.GroupIndexEnum;
import com.mcissoko.play.sudoku.PositionIndexEnum;

public class PlaySequence {

	private GroupIndexEnum groupIndex;
	private  PositionIndexEnum positionIndex;
	private Integer sellectedCandidate;
	private List<Integer> candidates;
	
	private List<Sequence> sequences;

	public PlaySequence(GroupIndexEnum groupIndex, PositionIndexEnum positionIndex, Integer sellectedCandidate, List<Integer> candidates) {
		super();
		this.groupIndex = groupIndex;
		this.positionIndex = positionIndex;
		this.sellectedCandidate = sellectedCandidate;
		
		this.candidates = new ArrayList<>(candidates);
		
		this.sequences = new ArrayList<>();
		
	}

	public PlaySequence(GroupIndexEnum groupIndex, PositionIndexEnum positionIndex) {
		super();
		this.groupIndex = groupIndex;
		this.positionIndex = positionIndex;
	}
	
	public List<Sequence> getSequences() {
		return sequences;
	}

	public void setSequences(List<Sequence> sequences) {
		this.sequences = sequences;
	}

	public GroupIndexEnum getGroupIndex() {
		return groupIndex;
	}

	public PositionIndexEnum getPositionIndex() {
		return positionIndex;
	}

	public Integer getSellectedCandidate() {
		return sellectedCandidate;
	}

	public List<Integer> getCandidates() {
		return candidates;
	}
	
	
}
