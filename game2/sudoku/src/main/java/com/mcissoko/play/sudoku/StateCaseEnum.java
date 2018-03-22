package com.mcissoko.play.sudoku;

public enum StateCaseEnum {

	EMPTY(0, "Libre"), FILLED(1, "Ocuppée"), FIXED(-1, "Resolu");
	
	private int id;
	private String label;
	
	private StateCaseEnum(int id, String label){
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
