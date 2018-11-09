package com.mcissoko.game.sudoku;

class Sequence {
	private GroupIndexEnum groupIndex;
	private  PositionIndexEnum positionIndex;
	protected Sequence(GroupIndexEnum groupIndex, PositionIndexEnum positionIndex) {
		super();
		this.groupIndex = groupIndex;
		this.positionIndex = positionIndex;
	}
	protected GroupIndexEnum getGroupIndex() {
		return groupIndex;
	}
	protected PositionIndexEnum getPositionIndex() {
		return positionIndex;
	}
	
	
}
