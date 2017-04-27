package com.mcissoko.play.view;

import java.util.HashMap;
import java.util.Map;

import com.mcissoko.play.MainApp;
import com.mcissoko.play.data.Box;
import com.mcissoko.play.data.IndexBox;
import com.mcissoko.play.sudoku.Group;
import com.mcissoko.play.sudoku.GroupIndexEnum;
import com.mcissoko.play.sudoku.PositionIndexEnum;
import com.mcissoko.play.sudoku.Sudoku;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RootLayoutController {

	private MainApp mainApp;
	private Stage primaryStage;
	
	private Map<IndexBox, Box> grilleMap;
	
	public MainApp getMainApp() {
		return mainApp;
	}

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;		
	}

	
	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
		//ControllerManager.gestInstance().getRootLayoutController().getSignalController().setPrimaryStage(primaryStage);
	}
	
	@FXML
    private void handleExit() {
        System.exit(0);
    }
	
	public void initGrille(){
		
		this.grilleMap = new HashMap<>();
		
		grilleMap.put(new IndexBox(GroupIndexEnum.INDEX_1, PositionIndexEnum.INDEX_1), new Box(g1Case1, g1Candidates1Label));
		grilleMap.put(new IndexBox(GroupIndexEnum.INDEX_1, PositionIndexEnum.INDEX_2), new Box(g1Case2, g1Candidates2Label));
		grilleMap.put(new IndexBox(GroupIndexEnum.INDEX_1, PositionIndexEnum.INDEX_3), new Box(g1Case3, g1Candidates3Label));
		grilleMap.put(new IndexBox(GroupIndexEnum.INDEX_1, PositionIndexEnum.INDEX_4), new Box(g1Case4, g1Candidates4Label));
		grilleMap.put(new IndexBox(GroupIndexEnum.INDEX_1, PositionIndexEnum.INDEX_5), new Box(g1Case5, g1Candidates5Label));
		grilleMap.put(new IndexBox(GroupIndexEnum.INDEX_1, PositionIndexEnum.INDEX_6), new Box(g1Case6, g1Candidates6Label));
		grilleMap.put(new IndexBox(GroupIndexEnum.INDEX_1, PositionIndexEnum.INDEX_7), new Box(g1Case7, g1Candidates7Label));
		grilleMap.put(new IndexBox(GroupIndexEnum.INDEX_1, PositionIndexEnum.INDEX_8), new Box(g1Case8, g1Candidates8Label));
		grilleMap.put(new IndexBox(GroupIndexEnum.INDEX_1, PositionIndexEnum.INDEX_9), new Box(g1Case9, g1Candidates9Label));
		
		grilleMap.put(new IndexBox(GroupIndexEnum.INDEX_2, PositionIndexEnum.INDEX_1), new Box(g2Case1, g2Candidates1Label));
		grilleMap.put(new IndexBox(GroupIndexEnum.INDEX_2, PositionIndexEnum.INDEX_2), new Box(g2Case2, g2Candidates2Label));
		grilleMap.put(new IndexBox(GroupIndexEnum.INDEX_2, PositionIndexEnum.INDEX_3), new Box(g2Case3, g2Candidates3Label));
		grilleMap.put(new IndexBox(GroupIndexEnum.INDEX_2, PositionIndexEnum.INDEX_4), new Box(g2Case4, g2Candidates4Label));
		grilleMap.put(new IndexBox(GroupIndexEnum.INDEX_2, PositionIndexEnum.INDEX_5), new Box(g2Case5, g2Candidates5Label));
		grilleMap.put(new IndexBox(GroupIndexEnum.INDEX_2, PositionIndexEnum.INDEX_6), new Box(g2Case6, g2Candidates6Label));
		grilleMap.put(new IndexBox(GroupIndexEnum.INDEX_2, PositionIndexEnum.INDEX_7), new Box(g2Case7, g2Candidates7Label));
		grilleMap.put(new IndexBox(GroupIndexEnum.INDEX_2, PositionIndexEnum.INDEX_8), new Box(g2Case8, g2Candidates8Label));
		grilleMap.put(new IndexBox(GroupIndexEnum.INDEX_2, PositionIndexEnum.INDEX_9), new Box(g2Case9, g2Candidates9Label));
		
		grilleMap.put(new IndexBox(GroupIndexEnum.INDEX_3, PositionIndexEnum.INDEX_1), new Box(g3Case1, g3Candidates1Label));
		grilleMap.put(new IndexBox(GroupIndexEnum.INDEX_3, PositionIndexEnum.INDEX_2), new Box(g3Case2, g3Candidates2Label));
		grilleMap.put(new IndexBox(GroupIndexEnum.INDEX_3, PositionIndexEnum.INDEX_3), new Box(g3Case3, g3Candidates3Label));
		grilleMap.put(new IndexBox(GroupIndexEnum.INDEX_3, PositionIndexEnum.INDEX_4), new Box(g3Case4, g3Candidates4Label));
		grilleMap.put(new IndexBox(GroupIndexEnum.INDEX_3, PositionIndexEnum.INDEX_5), new Box(g3Case5, g3Candidates5Label));
		grilleMap.put(new IndexBox(GroupIndexEnum.INDEX_3, PositionIndexEnum.INDEX_6), new Box(g3Case6, g3Candidates6Label));
		grilleMap.put(new IndexBox(GroupIndexEnum.INDEX_3, PositionIndexEnum.INDEX_7), new Box(g3Case7, g3Candidates7Label));
		grilleMap.put(new IndexBox(GroupIndexEnum.INDEX_3, PositionIndexEnum.INDEX_8), new Box(g3Case8, g3Candidates8Label));
		grilleMap.put(new IndexBox(GroupIndexEnum.INDEX_3, PositionIndexEnum.INDEX_9), new Box(g3Case9, g3Candidates9Label));
		
		grilleMap.put(new IndexBox(GroupIndexEnum.INDEX_4, PositionIndexEnum.INDEX_1), new Box(g4Case1, g4Candidates1Label));
		grilleMap.put(new IndexBox(GroupIndexEnum.INDEX_4, PositionIndexEnum.INDEX_2), new Box(g4Case2, g4Candidates2Label));
		grilleMap.put(new IndexBox(GroupIndexEnum.INDEX_4, PositionIndexEnum.INDEX_3), new Box(g4Case3, g4Candidates3Label));
		grilleMap.put(new IndexBox(GroupIndexEnum.INDEX_4, PositionIndexEnum.INDEX_4), new Box(g4Case4, g4Candidates4Label));
		grilleMap.put(new IndexBox(GroupIndexEnum.INDEX_4, PositionIndexEnum.INDEX_5), new Box(g4Case5, g4Candidates5Label));
		grilleMap.put(new IndexBox(GroupIndexEnum.INDEX_4, PositionIndexEnum.INDEX_6), new Box(g4Case6, g4Candidates6Label));
		grilleMap.put(new IndexBox(GroupIndexEnum.INDEX_4, PositionIndexEnum.INDEX_7), new Box(g4Case7, g4Candidates7Label));
		grilleMap.put(new IndexBox(GroupIndexEnum.INDEX_4, PositionIndexEnum.INDEX_8), new Box(g4Case8, g4Candidates8Label));
		grilleMap.put(new IndexBox(GroupIndexEnum.INDEX_4, PositionIndexEnum.INDEX_9), new Box(g4Case9, g4Candidates9Label));
		
		grilleMap.put(new IndexBox(GroupIndexEnum.INDEX_5, PositionIndexEnum.INDEX_1), new Box(g5Case1, g5Candidates1Label));
		grilleMap.put(new IndexBox(GroupIndexEnum.INDEX_5, PositionIndexEnum.INDEX_2), new Box(g5Case2, g5Candidates2Label));
		grilleMap.put(new IndexBox(GroupIndexEnum.INDEX_5, PositionIndexEnum.INDEX_3), new Box(g5Case3, g5Candidates3Label));
		grilleMap.put(new IndexBox(GroupIndexEnum.INDEX_5, PositionIndexEnum.INDEX_4), new Box(g5Case4, g5Candidates4Label));
		grilleMap.put(new IndexBox(GroupIndexEnum.INDEX_5, PositionIndexEnum.INDEX_5), new Box(g5Case5, g5Candidates5Label));
		grilleMap.put(new IndexBox(GroupIndexEnum.INDEX_5, PositionIndexEnum.INDEX_6), new Box(g5Case6, g5Candidates6Label));
		grilleMap.put(new IndexBox(GroupIndexEnum.INDEX_5, PositionIndexEnum.INDEX_7), new Box(g5Case7, g5Candidates7Label));
		grilleMap.put(new IndexBox(GroupIndexEnum.INDEX_5, PositionIndexEnum.INDEX_8), new Box(g5Case8, g5Candidates8Label));
		grilleMap.put(new IndexBox(GroupIndexEnum.INDEX_5, PositionIndexEnum.INDEX_9), new Box(g5Case9, g5Candidates9Label));
		
		grilleMap.put(new IndexBox(GroupIndexEnum.INDEX_6, PositionIndexEnum.INDEX_1), new Box(g6Case1, g6Candidates1Label));
		grilleMap.put(new IndexBox(GroupIndexEnum.INDEX_6, PositionIndexEnum.INDEX_2), new Box(g6Case2, g6Candidates2Label));
		grilleMap.put(new IndexBox(GroupIndexEnum.INDEX_6, PositionIndexEnum.INDEX_3), new Box(g6Case3, g6Candidates3Label));
		grilleMap.put(new IndexBox(GroupIndexEnum.INDEX_6, PositionIndexEnum.INDEX_4), new Box(g6Case4, g6Candidates4Label));
		grilleMap.put(new IndexBox(GroupIndexEnum.INDEX_6, PositionIndexEnum.INDEX_5), new Box(g6Case5, g6Candidates5Label));
		grilleMap.put(new IndexBox(GroupIndexEnum.INDEX_6, PositionIndexEnum.INDEX_6), new Box(g6Case6, g6Candidates6Label));
		grilleMap.put(new IndexBox(GroupIndexEnum.INDEX_6, PositionIndexEnum.INDEX_7), new Box(g6Case7, g6Candidates7Label));
		grilleMap.put(new IndexBox(GroupIndexEnum.INDEX_6, PositionIndexEnum.INDEX_8), new Box(g6Case8, g6Candidates8Label));
		grilleMap.put(new IndexBox(GroupIndexEnum.INDEX_6, PositionIndexEnum.INDEX_9), new Box(g6Case9, g6Candidates9Label));
		
		grilleMap.put(new IndexBox(GroupIndexEnum.INDEX_7, PositionIndexEnum.INDEX_1), new Box(g7Case1, g7Candidates1Label));
		grilleMap.put(new IndexBox(GroupIndexEnum.INDEX_7, PositionIndexEnum.INDEX_2), new Box(g7Case2, g7Candidates2Label));
		grilleMap.put(new IndexBox(GroupIndexEnum.INDEX_7, PositionIndexEnum.INDEX_3), new Box(g7Case3, g7Candidates3Label));
		grilleMap.put(new IndexBox(GroupIndexEnum.INDEX_7, PositionIndexEnum.INDEX_4), new Box(g7Case4, g7Candidates4Label));
		grilleMap.put(new IndexBox(GroupIndexEnum.INDEX_7, PositionIndexEnum.INDEX_5), new Box(g7Case5, g7Candidates5Label));
		grilleMap.put(new IndexBox(GroupIndexEnum.INDEX_7, PositionIndexEnum.INDEX_6), new Box(g7Case6, g7Candidates6Label));
		grilleMap.put(new IndexBox(GroupIndexEnum.INDEX_7, PositionIndexEnum.INDEX_7), new Box(g7Case7, g7Candidates7Label));
		grilleMap.put(new IndexBox(GroupIndexEnum.INDEX_7, PositionIndexEnum.INDEX_8), new Box(g7Case8, g7Candidates8Label));
		grilleMap.put(new IndexBox(GroupIndexEnum.INDEX_7, PositionIndexEnum.INDEX_9), new Box(g7Case9, g7Candidates9Label));
		
		grilleMap.put(new IndexBox(GroupIndexEnum.INDEX_8, PositionIndexEnum.INDEX_1), new Box(g8Case1, g8Candidates1Label));
		grilleMap.put(new IndexBox(GroupIndexEnum.INDEX_8, PositionIndexEnum.INDEX_2), new Box(g8Case2, g8Candidates2Label));
		grilleMap.put(new IndexBox(GroupIndexEnum.INDEX_8, PositionIndexEnum.INDEX_3), new Box(g8Case3, g8Candidates3Label));
		grilleMap.put(new IndexBox(GroupIndexEnum.INDEX_8, PositionIndexEnum.INDEX_4), new Box(g8Case4, g8Candidates4Label));
		grilleMap.put(new IndexBox(GroupIndexEnum.INDEX_8, PositionIndexEnum.INDEX_5), new Box(g8Case5, g8Candidates5Label));
		grilleMap.put(new IndexBox(GroupIndexEnum.INDEX_8, PositionIndexEnum.INDEX_6), new Box(g8Case6, g8Candidates6Label));
		grilleMap.put(new IndexBox(GroupIndexEnum.INDEX_8, PositionIndexEnum.INDEX_7), new Box(g8Case7, g8Candidates7Label));
		grilleMap.put(new IndexBox(GroupIndexEnum.INDEX_8, PositionIndexEnum.INDEX_8), new Box(g8Case8, g8Candidates8Label));
		grilleMap.put(new IndexBox(GroupIndexEnum.INDEX_8, PositionIndexEnum.INDEX_9), new Box(g8Case9, g8Candidates9Label));
		
		grilleMap.put(new IndexBox(GroupIndexEnum.INDEX_9, PositionIndexEnum.INDEX_1), new Box(g9Case1, g9Candidates1Label));
		grilleMap.put(new IndexBox(GroupIndexEnum.INDEX_9, PositionIndexEnum.INDEX_2), new Box(g9Case2, g9Candidates2Label));
		grilleMap.put(new IndexBox(GroupIndexEnum.INDEX_9, PositionIndexEnum.INDEX_3), new Box(g9Case3, g9Candidates3Label));
		grilleMap.put(new IndexBox(GroupIndexEnum.INDEX_9, PositionIndexEnum.INDEX_4), new Box(g9Case4, g9Candidates4Label));
		grilleMap.put(new IndexBox(GroupIndexEnum.INDEX_9, PositionIndexEnum.INDEX_5), new Box(g9Case5, g9Candidates5Label));
		grilleMap.put(new IndexBox(GroupIndexEnum.INDEX_9, PositionIndexEnum.INDEX_6), new Box(g9Case6, g9Candidates6Label));
		grilleMap.put(new IndexBox(GroupIndexEnum.INDEX_9, PositionIndexEnum.INDEX_7), new Box(g9Case7, g9Candidates7Label));
		grilleMap.put(new IndexBox(GroupIndexEnum.INDEX_9, PositionIndexEnum.INDEX_8), new Box(g9Case8, g9Candidates8Label));
		grilleMap.put(new IndexBox(GroupIndexEnum.INDEX_9, PositionIndexEnum.INDEX_9), new Box(g9Case9, g9Candidates9Label));
		
		Sudoku sudoku = new Sudoku();
		sudoku.process();
		IndexBox index = new IndexBox();
		for(GroupIndexEnum groupIndexEnum: GroupIndexEnum.values()){
			Group group = sudoku.getGrid().getGroup(groupIndexEnum);
			index.setGroupIndexEnum(groupIndexEnum);
			for(PositionIndexEnum positionIndexEnum: PositionIndexEnum.values()){
				index.setPositionIndexEnum(positionIndexEnum);
				Box box = grilleMap.get(index);
				box.getGroupCase().setText(String.valueOf(group.getCase(positionIndexEnum).getContent()));
			}
		}
	}
	
	
   
	
	
	@FXML private TextField g1Case1;
	@FXML private Label g1Candidates1Label; 
	@FXML private TextField g1Case2;
	@FXML private Label g1Candidates2Label; 
	@FXML private TextField g1Case3;
	@FXML private Label g1Candidates3Label; 
	@FXML private TextField g1Case4;
	@FXML private Label g1Candidates4Label; 
	@FXML private TextField g1Case5;
	@FXML private Label g1Candidates5Label; 
	@FXML private TextField g1Case6;
	@FXML private Label g1Candidates6Label; 
	@FXML private TextField g1Case7;
	@FXML private Label g1Candidates7Label; 
	@FXML private TextField g1Case8;
	@FXML private Label g1Candidates8Label; 
	@FXML private TextField g1Case9;
	@FXML private Label g1Candidates9Label; 

	@FXML private TextField g2Case1;
	@FXML private Label g2Candidates1Label; 
	@FXML private TextField g2Case2;
	@FXML private Label g2Candidates2Label; 
	@FXML private TextField g2Case3;
	@FXML private Label g2Candidates3Label; 
	@FXML private TextField g2Case4;
	@FXML private Label g2Candidates4Label; 
	@FXML private TextField g2Case5;
	@FXML private Label g2Candidates5Label; 
	@FXML private TextField g2Case6;
	@FXML private Label g2Candidates6Label; 
	@FXML private TextField g2Case7;
	@FXML private Label g2Candidates7Label; 
	@FXML private TextField g2Case8;
	@FXML private Label g2Candidates8Label; 
	@FXML private TextField g2Case9;
	@FXML private Label g2Candidates9Label; 
	
	@FXML private TextField g3Case1;
	@FXML private Label g3Candidates1Label; 
	@FXML private TextField g3Case2;
	@FXML private Label g3Candidates2Label; 
	@FXML private TextField g3Case3;
	@FXML private Label g3Candidates3Label; 
	@FXML private TextField g3Case4;
	@FXML private Label g3Candidates4Label; 
	@FXML private TextField g3Case5;
	@FXML private Label g3Candidates5Label; 
	@FXML private TextField g3Case6;
	@FXML private Label g3Candidates6Label; 
	@FXML private TextField g3Case7;
	@FXML private Label g3Candidates7Label; 
	@FXML private TextField g3Case8;
	@FXML private Label g3Candidates8Label; 
	@FXML private TextField g3Case9;
	@FXML private Label g3Candidates9Label; 
	
	@FXML private TextField g4Case1;
	@FXML private Label g4Candidates1Label; 
	@FXML private TextField g4Case2;
	@FXML private Label g4Candidates2Label; 
	@FXML private TextField g4Case3;
	@FXML private Label g4Candidates3Label; 
	@FXML private TextField g4Case4;
	@FXML private Label g4Candidates4Label; 
	@FXML private TextField g4Case5;
	@FXML private Label g4Candidates5Label; 
	@FXML private TextField g4Case6;
	@FXML private Label g4Candidates6Label; 
	@FXML private TextField g4Case7;
	@FXML private Label g4Candidates7Label; 
	@FXML private TextField g4Case8;
	@FXML private Label g4Candidates8Label; 
	@FXML private TextField g4Case9;
	@FXML private Label g4Candidates9Label; 
	
	@FXML private TextField g5Case1;
	@FXML private Label g5Candidates1Label; 
	@FXML private TextField g5Case2;
	@FXML private Label g5Candidates2Label; 
	@FXML private TextField g5Case3;
	@FXML private Label g5Candidates3Label; 
	@FXML private TextField g5Case4;
	@FXML private Label g5Candidates4Label; 
	@FXML private TextField g5Case5;
	@FXML private Label g5Candidates5Label; 
	@FXML private TextField g5Case6;
	@FXML private Label g5Candidates6Label; 
	@FXML private TextField g5Case7;
	@FXML private Label g5Candidates7Label; 
	@FXML private TextField g5Case8;
	@FXML private Label g5Candidates8Label; 
	@FXML private TextField g5Case9;
	@FXML private Label g5Candidates9Label; 
	
	@FXML private TextField g6Case1;
	@FXML private Label g6Candidates1Label; 
	@FXML private TextField g6Case2;
	@FXML private Label g6Candidates2Label; 
	@FXML private TextField g6Case3;
	@FXML private Label g6Candidates3Label; 
	@FXML private TextField g6Case4;
	@FXML private Label g6Candidates4Label; 
	@FXML private TextField g6Case5;
	@FXML private Label g6Candidates5Label; 
	@FXML private TextField g6Case6;
	@FXML private Label g6Candidates6Label; 
	@FXML private TextField g6Case7;
	@FXML private Label g6Candidates7Label; 
	@FXML private TextField g6Case8;
	@FXML private Label g6Candidates8Label; 
	@FXML private TextField g6Case9;
	@FXML private Label g6Candidates9Label; 
	
	@FXML private TextField g7Case1;
	@FXML private Label g7Candidates1Label; 
	@FXML private TextField g7Case2;
	@FXML private Label g7Candidates2Label; 
	@FXML private TextField g7Case3;
	@FXML private Label g7Candidates3Label; 
	@FXML private TextField g7Case4;
	@FXML private Label g7Candidates4Label; 
	@FXML private TextField g7Case5;
	@FXML private Label g7Candidates5Label; 
	@FXML private TextField g7Case6;
	@FXML private Label g7Candidates6Label; 
	@FXML private TextField g7Case7;
	@FXML private Label g7Candidates7Label; 
	@FXML private TextField g7Case8;
	@FXML private Label g7Candidates8Label; 
	@FXML private TextField g7Case9;
	@FXML private Label g7Candidates9Label; 
	
	@FXML private TextField g8Case1;
	@FXML private Label g8Candidates1Label; 
	@FXML private TextField g8Case2;
	@FXML private Label g8Candidates2Label; 
	@FXML private TextField g8Case3;
	@FXML private Label g8Candidates3Label; 
	@FXML private TextField g8Case4;
	@FXML private Label g8Candidates4Label; 
	@FXML private TextField g8Case5;
	@FXML private Label g8Candidates5Label; 
	@FXML private TextField g8Case6;
	@FXML private Label g8Candidates6Label; 
	@FXML private TextField g8Case7;
	@FXML private Label g8Candidates7Label; 
	@FXML private TextField g8Case8;
	@FXML private Label g8Candidates8Label; 
	@FXML private TextField g8Case9;
	@FXML private Label g8Candidates9Label; 
	
	@FXML private TextField g9Case1;
	@FXML private Label g9Candidates1Label; 
	@FXML private TextField g9Case2;
	@FXML private Label g9Candidates2Label; 
	@FXML private TextField g9Case3;
	@FXML private Label g9Candidates3Label; 
	@FXML private TextField g9Case4;
	@FXML private Label g9Candidates4Label; 
	@FXML private TextField g9Case5;
	@FXML private Label g9Candidates5Label; 
	@FXML private TextField g9Case6;
	@FXML private Label g9Candidates6Label; 
	@FXML private TextField g9Case7;
	@FXML private Label g9Candidates7Label; 
	@FXML private TextField g9Case8;
	@FXML private Label g9Candidates8Label; 
	@FXML private TextField g9Case9;
	@FXML private Label g9Candidates9Label; 
	/*
	//*/
}