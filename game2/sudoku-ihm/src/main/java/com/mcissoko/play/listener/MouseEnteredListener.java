package com.mcissoko.play.listener;

import com.mcissoko.play.view.RootLayoutController;

import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

@Deprecated
public class MouseEnteredListener implements EventHandler<MouseEvent> {

	private RootLayoutController controller;
	private String current;
	
	
	public MouseEnteredListener(RootLayoutController controller) {
		super();
		this.controller = controller;
	}


	@Override
	public void handle(MouseEvent event) {
		if(current != null && current.equals(event.getSource())){
			return;
		}
		TextField field = (TextField) event.getSource();
		current = field.getId();
	    controller.executeEventEntered(current);
	}

}
