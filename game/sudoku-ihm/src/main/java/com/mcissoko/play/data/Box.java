package com.mcissoko.play.data;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class Box {

	
	private TextField groupCase;
	private Label groupCandidatesLabel;
	public Box(TextField groupCase, Label groupCandidatesLabel) {
		super();
		this.groupCase = groupCase;
		this.groupCandidatesLabel = groupCandidatesLabel;
	}
	public TextField getGroupCase() {
		return groupCase;
	}
	public void setGroupCase(TextField groupCase) {
		this.groupCase = groupCase;
	}
	public Label getGroupCandidatesLabel() {
		return groupCandidatesLabel;
	}
	public void setGroupCandidatesLabel(Label groupCandidatesLabel) {
		this.groupCandidatesLabel = groupCandidatesLabel;
	}
	
	
	
	
	
}
