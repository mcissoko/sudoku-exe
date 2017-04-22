package com.mcissoko.play.sudoku.api;

import com.mcissoko.play.sudoku.GroupIndexEnum;
import com.mcissoko.play.sudoku.PositionIndexEnum;

public class Sequence {
	private GroupIndexEnum groupIndex;
	private  PositionIndexEnum positionIndex;
	public Sequence(GroupIndexEnum groupIndex, PositionIndexEnum positionIndex) {
		super();
		this.groupIndex = groupIndex;
		this.positionIndex = positionIndex;
	}
	public GroupIndexEnum getGroupIndex() {
		return groupIndex;
	}
	public PositionIndexEnum getPositionIndex() {
		return positionIndex;
	}
	
	
}
