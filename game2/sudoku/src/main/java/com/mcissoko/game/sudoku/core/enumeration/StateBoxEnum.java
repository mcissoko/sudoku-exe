package com.mcissoko.game.sudoku.core.enumeration;

public enum StateBoxEnum {

	EMPTY(0, "Free"), FILLED(1, "Filled"), FIXED(-1, "Resolved");
	
	private int id;
	private String label;
	
	private StateBoxEnum(int id, String label){
		this.id = id;
		this.label = label;
	}
	
	@Override
	public String toString() {
		
		return getLabel();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
	
	
}
