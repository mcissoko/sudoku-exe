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
	
	private List<PlaySequence> sellectedBoxes;

	public PlaySequence(GroupIndexEnum groupIndex, PositionIndexEnum positionIndex, Integer sellectedCandidate, List<Integer> candidates) {
		super();
		this.groupIndex = groupIndex;
		this.positionIndex = positionIndex;
		this.sellectedCandidate = sellectedCandidate;
		
		this.candidates = new ArrayList<>(candidates);
		
		this.sellectedBoxes = new ArrayList<>();
		
	}

	public PlaySequence(GroupIndexEnum groupIndex, PositionIndexEnum positionIndex) {
		super();
		this.groupIndex = groupIndex;
		this.positionIndex = positionIndex;
	}
	

	
	public List<PlaySequence> getSellectedBoxes() {
		return sellectedBoxes;
	}

	public void setSellectedBoxes(List<PlaySequence> sellectedBoxes) {
		this.sellectedBoxes = sellectedBoxes;
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
