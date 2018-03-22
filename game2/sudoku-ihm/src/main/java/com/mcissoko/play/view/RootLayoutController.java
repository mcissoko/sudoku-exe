package com.mcissoko.play.view;

import java.time.Duration;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.UnaryOperator;

import org.apache.commons.lang.StringUtils;

import com.mcissoko.play.MainApp;
import com.mcissoko.play.data.Box;
import com.mcissoko.play.data.IndexBox;
import com.mcissoko.play.listener.FocusEventListener;
import com.mcissoko.play.listener.InputChangeListener;
import com.mcissoko.play.print.MyPrinter;
import com.mcissoko.play.sudoku.Case;
import com.mcissoko.play.sudoku.Grille;
import com.mcissoko.play.sudoku.Group;
import com.mcissoko.play.sudoku.GroupIndexEnum;
import com.mcissoko.play.sudoku.IMonitor;
import com.mcissoko.play.sudoku.PositionIndexEnum;
import com.mcissoko.play.sudoku.StateCaseEnum;
import com.mcissoko.play.sudoku.Sudoku;
import com.mcissoko.play.util.Melodie;
import com.mcissoko.play.util.Sequence;

import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class RootLayoutController implements IMonitor {

	private MainApp mainApp;
	private Stage primaryStage;
	
	private Sudoku sudoku;
	private Grille solution;
	private Map<String, Box> grilleMap;
	
	private final String focusInOthersStyle  = "-fx-background-color: #a9a6d8;";
	private final String focusOutStyle = "-fx-background-color: white;";
	private final String fixedStyle = "-fx-font: bold 26px Arial;";
	private boolean initiating; 
	private final String errorStyle = "-fx-background-color: red;";
	private final String focusInStyle =
	    "-fx-border-color: blue ;"
	    + "-fx-border-width: 4 ; "
	    + "-fx-font: bold 20px Arial;"
	   + "-fx-border-style: segments(5, 5, 5, 5)  line-cap round ;"
	    ;
	
	
	private List<IndexBox> occurIndexList;
	private Deque<Sequence> undoSequences;
	private Deque<Sequence> redoSequences;
	
	private boolean writingSolution;
	
	private List<Box> boxes;
	private Thread thread;	

	private Grille grille;
	private MyPrinter myPrinter;
	
	protected IMonitor monitor;
	@FXML
    private void handleExit() {
		
        System.exit(0);
    }
	
	private String indexBox(GroupIndexEnum groupIndexEnum, PositionIndexEnum positionIndexEnum) {
		return "g"+ groupIndexEnum.getIndex() + "Case" + positionIndexEnum.getIndex();
	}	

	@FXML private void cancelResolve() {
		thread.stop();
		//service.cancel();
		this.sudoku.setGrid(grille);
		cancelPane.setVisible(false);
		cancelPane.toBack();
		menuPane.setDisable(false);
		unrePane.setDisable(false);
	}
	
	
	private void analyseSolution(int status) {
		
		
		if(status != 0) {
			this.sudoku.setGrid(grille);	
			showMessage(AlertType.ERROR, "Statut de la resolution",  status + ": Pas de solution");
			
		}else {
			
			this.writingSolution = true;
			for(GroupIndexEnum groupIndexEnum: GroupIndexEnum.values()){
				Group group = this.sudoku.getGrid().getGroup(groupIndexEnum);
				
				for(PositionIndexEnum positionIndexEnum: PositionIndexEnum.values()){
					Case caze = group.getCase(positionIndexEnum);
					if(caze.getState() == StateCaseEnum.FIXED){
						continue;
					}
					Box box = grilleMap.get(indexBox(groupIndexEnum, positionIndexEnum));
					box.getGroupCase().setText(String.valueOf(group.getCase(positionIndexEnum).getContent()));
				}
			}
			markAsResolved();			
		}
		this.writingSolution = false;
	}
	
	@FXML private void resolveCurrentPasAPas() {
		resolve(this.monitor);
	}
	
	private void resolve(IMonitor mon) {
		
		grille = this.sudoku.getGrid();
		System.out.println(grille);
		Grille grilleCopy;
		
		grilleCopy = grille.copy();
		 
		
		this.sudoku.setGrid(grilleCopy);
		//TODO start process
		menuPane.setDisable(true);
		unrePane.setDisable(true);
		
		cancelPane.toFront();
		cancelPane.setVisible(true);
		
//		service = new Service<Void>() {
//	        @Override
//	        protected Task<Void> createTask() {
//	            return new Task<Void>() {           
//	                @Override
//	                protected Void call() throws Exception {
//	                    //Background work                       
//	                    final CountDownLatch latch = new CountDownLatch(1);
//	                    Platform.runLater(new Runnable() {                          
//	                        @Override
//	                        public void run() {
//	                            try{
//	                            	int status = sudoku.process();
//	            					
//	            					cancelPane.toBack();
//	            					cancelPane.setVisible(false);
//	            					menuPane.setDisable(false);
//	            					unrePane.setDisable(false);
//	            					
//	            					analyseSolution(status, grille);
//	                            }finally{
//	                                latch.countDown();
//	                            }
//	                        }
//	                    });
//	                    latch.await();                      
//	                    //Keep with the background work
//	                    return null;
//	                }
//	            };
//	        }
//	    };
	    //service.start();
//		Platform.runLater(new Runnable() {
//			   @Override
//			   public void run() {
//					int status = sudoku.process();
//					
//					
//					cancelPane.toBack();
//					cancelPane.setVisible(false);
//					menuPane.setDisable(false);
//					unrePane.setDisable(false);
//					
//					analyseSolution(status, grille);
//					
//				}
//			});
		this.thread = new Thread(new Runnable() {
			

			@Override
			public void run() {
				final int status = sudoku.solution(mon);
				Platform.runLater(new Runnable() {
					   @Override
					   public void run() {
						   cancelPane.toBack();
							cancelPane.setVisible(false);
							menuPane.setDisable(false);
							unrePane.setDisable(false);
							
							analyseSolution(status);
							
						}
					});
				
				
			}
		});
		thread.setDaemon(true);

		thread.start();
		
	}

	@FXML private void resolveCurrent() {
		resolve(null);
	}
	
	
	@SuppressWarnings("unused")
	private void showMessage (AlertType type, String message) {
		this.showMessage(type, StringUtils.EMPTY, StringUtils.EMPTY, message);
		
	}
	private void showMessage (AlertType type, String title, String message) {
		this.showMessage(type, title, StringUtils.EMPTY, message);
		
	}
	private void showMessage (AlertType type, String title, String header, String message) {
		Alert alert = new Alert(type);
		alert.setTitle(title);
		alert.setHeaderText(header);
		alert.setContentText(message);
		alert.showAndWait().ifPresent(rs -> {
		    if (rs == ButtonType.OK) {
		        System.out.println("Pressed OK.");
		    }
		});
	}
	
	private static final int MAX_SEQUENCE = 100;
	private void registerChange(GroupIndexEnum gIngex, PositionIndexEnum pIndex, Integer newContent, Integer oldContent) {
		if(undoSequences.size() >= MAX_SEQUENCE) {
			do {
				undoSequences.pollLast();				
			}while(undoSequences.size() > MAX_SEQUENCE); 
		}
		undoSequences.addFirst(new Sequence(gIngex, pIndex, newContent, oldContent));
		if(undoBtn.isDisable()) {
			undoBtn.setDisable(false);
		}
	}
	
	@FXML private void undo() {
		if(undoSequences.isEmpty()) {
			undoBtn.setDisable(true);	
			return;
		}
		Sequence seq = undoSequences.pollFirst();
		Box b = grilleMap.get(indexBox(seq.getGroupIndex(), seq.getPositionIndex()));
		b.setChangeFromUndo(true);
		Integer content = seq.getOldContent();
		if(content > 0) {
			b.getGroupCase().setText(String.valueOf(content));			
		} else {
			b.getGroupCase().setText(StringUtils.EMPTY);
		}
		undoBtn.setDisable(undoSequences.isEmpty());
		
		// Activate redo
		if(redoBtn.isDisabled()) {
			redoBtn.setDisable(false);	
		}
		redoSequences.addFirst(seq);
		
		b.setChangeFromUndo(false);
		
		b.getGroupCase().setStyle(focusInStyle);
		
		b.getGroupCase().requestFocus();			
		
	}
	
	@FXML private void redo() {
		if(redoSequences.isEmpty()) {
			redoBtn.setDisable(true);	
			return;
		}
		Sequence seq = redoSequences.pollFirst();
		Box b = grilleMap.get(indexBox(seq.getGroupIndex(), seq.getPositionIndex()));
		b.setChangeFromRedo(true);
		Integer content = seq.getNewContent();
		if(content > 0) {
			b.getGroupCase().setText(String.valueOf(content));			
		} else {
			b.getGroupCase().setText(StringUtils.EMPTY);
		}
		redoBtn.setDisable(redoSequences.isEmpty());
		b.setChangeFromRedo(false);

		b.getGroupCase().setStyle(focusInStyle);
		
		b.getGroupCase().requestFocus();			
	}
	
	public void excuteValueChanged(String id, InputActionEnum actionEnum, String value, String old) {
		if(this.writingSolution) {
			return;
		}
		Box box = grilleMap.get(id);
		IndexBox index = box.getIndexBox();
		Group group = this.sudoku.getGrid().getGroup(box.getIndexBox().getGroupIndexEnum());
		Case caze = group.getCase(box.getIndexBox().getPositionIndexEnum());
				
		if(!box.isChangeFromUndo() && !box.isChangeFromRedo() && redoBtn.isDisabled() == false) {
			redoBtn.setDisable(true);
			redoSequences.clear();
		}
		
		if(!box.isChangeFromUndo()) {
			registerChange(caze.getGroup().getIndex(), caze.getPosition().getIndex(), StringUtils.isBlank(value) ? 0 : Integer.parseInt(value), StringUtils.isBlank(old) ? 0 : Integer.parseInt(old));				
		}

		switch (actionEnum) {
		case NEW:
			
			caze.tryContent(Integer.parseInt(value));	
			break;
		case CHANGE:
			
			caze.tryContent(Integer.parseInt(value));
			this.activateOthers();
			break;
			
		default:
			// REMOVE
			caze.resetContent();
			this.activateOthers();
			occurIndexList.clear();
			resolveMenuItem.setDisable(false);
			resolveMenuItem2.setDisable(false);
			
			if(undoBtn.isDisable() && !undoSequences.isEmpty()) {
				undoBtn.setDisable(false);
			}
			return;
		}
		
		List<Case> occur = new ArrayList<>();
		if(!caze.getGroup().isContentUnique(caze, occur)){
			
			for(Case c: occur){
				box = grilleMap.get(indexBox(c.getGroup().getIndex(), c.getPosition().getIndex()));
				occurIndexList.add(new IndexBox(c.getGroup().getIndex(), c.getPosition().getIndex()));
				if (box.getIndexBox().equals(index)) {
					box.getGroupCase().setStyle(errorStyle + focusInStyle);
				}else {
					box.getGroupCase().setStyle(errorStyle);					
				}
					
				box.setInError(true);
				
			}
			resolveMenuItem.setDisable(true);
			resolveMenuItem2.setDisable(true);
			
			
			this.deactivateOthers();
			
		}else {
			occurIndexList.clear();
			resolveMenuItem.setDisable(false);
			resolveMenuItem2.setDisable(false);
			
			if(undoBtn.isDisabled()) {
				undoBtn.setDisable(false);
			}
			
			if(this.sudoku.getGrid().isSudoku()){
				markAsResolved();
				
			}else{
				// vérifier si le nombre est resolu
				/*
				List<Case> resolved = this.sudoku.getGrid().checkNumberIsResolved(caze.getContent());
				if(!resolved.isEmpty()){
					this.markAsResolved(resolved);
				}
				//*/
			}
		}
		
	}
	
	private void markAsResolved() {
		this.gridSudoku.setDisable(true);
		this.durationLabel.setVisible(true);
		resolveMenuItem.setDisable(true);
		resolveMenuItem2.setDisable(true);
		eraseMenuItem.setDisable(true);
		redoBtn.setDisable(true); redoBtn.setVisible(false); redoSequences.clear();
		undoBtn.setDisable(true); undoBtn.setVisible(false); undoSequences.clear();
		String str = "Résolu en " + convertDuration(this.sudoku.getDuration());
		Platform.runLater(
				  () -> {
					  this.durationLabel.setText(str);
				    
				  }
				);
		animateBravo();
	}
	
	private String convertDuration(Duration duration) {
		int nano = duration.getNano();
		
		if(nano < 1000000) {
			return nano + " ns";
		}
		
		int time = nano / 1000000;// ms
		int remain = nano % 1000000;
		if(time < 1000) {
			return time + " ms " + (remain > 0 ? remain + " ns" : "");
		}
		time = time / 1000;
		remain = time % 1000;
		if(time < 60) {
			return time + " s " + (remain > 0 ? remain + " ms" : "");
		}
		
		return time + " s";
	}

	private void animateBravo() {
		bravoLabel.setVisible(true);
		durationLabel.setVisible(true);
        fadeTransition.play();
	}
	
	private void stopBravo() {
		bravoLabel.setVisible(false);
		durationLabel.setVisible(false);
        fadeTransition.stop();
	}

//	private void markAsResolved(List<Case> resolved){
//		for(Case c: resolved){
//			Box box = grilleMap.get(indexBox(c.getGroup().getIndex(), c.getPosition().getIndex()));
//			box.getGroupCase().setStyle(resovedStyle);
//		}
//	}
	
	private void deactivateOthers(){
		if (occurIndexList.isEmpty()){
			return;
		}
		for(Box b: this.grilleMap.values()){
			Case c = this.sudoku.getGrid().getGroup(b.getIndexBox().getGroupIndexEnum()).getCase(b.getIndexBox().getPositionIndexEnum());
			if(c.getState() != StateCaseEnum.FIXED && !occurIndexList.contains(b.getIndexBox())){
				b.getGroupCase().setDisable(true);
			}
			
		}
	}
	
	private void activateOthers(){
		if (occurIndexList.isEmpty()){
			return;
		}
		for(Box b: this.grilleMap.values()){
			Case c = this.sudoku.getGrid().getGroup(b.getIndexBox().getGroupIndexEnum()).getCase(b.getIndexBox().getPositionIndexEnum());
			if(c.getState() != StateCaseEnum.FIXED && !occurIndexList.contains(b.getIndexBox())){
				b.getGroupCase().setDisable(false);
			}
			
		}
		
		for(IndexBox ib: this.occurIndexList){
			Box box = grilleMap.get(this.indexBox(ib.getGroupIndexEnum(), ib.getPositionIndexEnum()));
			box.setInError(false);
			if(box.getGroupCase().isFocused()) {
				box.getGroupCase().setStyle(focusInStyle);				
			}else {
				box.getGroupCase().setStyle(focusInOthersStyle);
			}
		}
		
	}
	//private Case cazePrev;
	
	public void executeEventEntered(String id){
		this.setInitiating(false);

		Box box = grilleMap.get(id);
		
		
		//box.getGroupCase().selectRange(0, 1);
		Group group = sudoku.getGrid().getGroup(box.getIndexBox().getGroupIndexEnum());
		Case caze = group.getCase(box.getIndexBox().getPositionIndexEnum()); 
		
//		boxes.stream().filter(b -> !b.isInError()).forEach(b -> b.getGroupCase().setStyle(focusOutStyle));
		for(Box b: boxes){
			if(! b.isInError()){								
				b.getGroupCase().setStyle(focusOutStyle);
			}			
			
		}
			
		boxes.clear();
		/////////////////////////////////////////
		
		for(PositionIndexEnum index: PositionIndexEnum.values()){
			Box b = grilleMap.get(indexBox(group.getIndex(), index));
			if(! b.isInError()){
				b.getGroupCase().setStyle(focusInOthersStyle);								
			}
			
			boxes.add(b);
		}
		
		
		//--------------------------------------------------------- line
		for(GroupIndexEnum gie: group.getGroupLine()){
			Group g = sudoku.getGrid().getGroup(gie);
			for(PositionIndexEnum index: PositionIndexEnum.values()){
				Case c = g.getCase(index);
				if(caze.getPosition().getCoordinate().getLine() == c.getPosition().getCoordinate().getLine()){
					Box b = grilleMap.get(indexBox(gie, index));
					if(! b.isInError()){								
						b.getGroupCase().setStyle(focusInOthersStyle);	
					}
					
					boxes.add(b);
				}
			}
		}
		//--------------------------------------------------------- column
		for(GroupIndexEnum gie: group.getGroupColumn()){
			Group g = sudoku.getGrid().getGroup(gie);
			for(PositionIndexEnum index: PositionIndexEnum.values()){
				Case c = g.getCase(index);
				if(caze.getPosition().getCoordinate().getColumn() == c.getPosition().getCoordinate().getColumn()){
					Box b = grilleMap.get(indexBox(gie, index));
					if(! b.isInError()){									
						b.getGroupCase().setStyle(focusInOthersStyle);
					}				
					
					boxes.add(b);
				}
			}
		}
		if(box.isInError()) {
			box.getGroupCase().setStyle(focusInStyle + errorStyle);
		}else {
			box.getGroupCase().setStyle(focusInStyle);			
		}
	}
	
	public boolean isInitiating() {
		return initiating;
	}
	public void setInitiating(boolean initiating) {
		this.initiating = initiating;
	}
	
	

	private void initGrille(SudokuLevelEnum level){
		sudoku = new Sudoku();
		redoBtn.setDisable(true); redoBtn.setVisible(true); redoSequences.clear();
		undoBtn.setDisable(true); undoBtn.setVisible(true); undoSequences.clear();
		
		printMenuItem.setDisable(false);
		//printSolutionMenuItem.setDisable(false);
		
		if(SudokuLevelEnum.DEFAULT == level) {
			
			System.out.println(level);
		} else {

			this.setInitiating(true);
			//resolveMenuItem.setDisable(true);

			sudoku.solution(null);
			solution = sudoku.getGrid();
			// sudoku.setGrid(null);
			Grille grille = Sudoku.createNewEmptyGrid();

			Map<Integer, Integer> mapper = (new Melodie(level)).getMapper();
			// IndexBox index = indexBox();
			List<GroupIndexEnum> groupIndexList = Arrays.asList(GroupIndexEnum.values());
			Collections.shuffle(groupIndexList);
			int i = 1;
			for (GroupIndexEnum groupIndexEnum : groupIndexList) {
				Group group = solution.getGroup(groupIndexEnum);
				// index.setGroupIndexEnum(groupIndexEnum);
				List<PositionIndexEnum> posIndexList = Arrays.asList(PositionIndexEnum.values());
				Collections.shuffle(posIndexList);
				int limit = mapper.get(i);

				for (PositionIndexEnum positionIndexEnum : posIndexList) {

					Box box = grilleMap.get(indexBox(groupIndexEnum, positionIndexEnum));
					if (limit > 0) {
						box.getGroupCase().setText(String.valueOf(group.getCase(positionIndexEnum).getContent()));
						box.getGroupCase().setDisable(true);

						grille.getGroup(groupIndexEnum).fixCase(positionIndexEnum, group.getCase(positionIndexEnum).getContent());

						box.getGroupCase().setStyle(fixedStyle);
						box.setFixed(true);
					}

					limit--;
				}
				i++;
			}

			sudoku.setGrid(grille);

			this.setInitiating(false);
			resolveMenuItem.setDisable(false);
			resolveMenuItem2.setDisable(false);
		}
	}
	
	@FXML private void printGrid(){
		printJobStatusLabel.setVisible(true);
		
		//gridSudoku.setStyle("-fx-background-color: white;");
		//boxes.forEach(b -> b.getGroupCase().setStyle("-fx-background-color: white;-fx-text-inner-color: red;"));
			
		myPrinter.print(gridSudoku);
		
		Timeline timeline = new Timeline();
		timeline.getKeyFrames().add(new KeyFrame(javafx.util.Duration.seconds(10), new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				printJobStatusLabel.setVisible(false);
			}
		}));
		timeline.play();
	}
	
	@FXML private void printSolution(){
		
	}
	
	@FXML private void erase(){
		for(Box b: grilleMap.values()) {
			if(b.isFixed() == false && StringUtils.isNotBlank(b.getGroupCase().getText())) {
				b.getGroupCase().setText(StringUtils.EMPTY);
			}
		}
		undoSequences.clear();
		undoBtn.setDisable(true);
		
		redoSequences.clear();
		redoBtn.setDisable(true);
		
		activateOthers();
	}
		
	@FXML private void choiceEasy(){
		choice(SudokuLevelEnum.EASY);
	}
	@FXML private void choiceMedium(){
		choice(SudokuLevelEnum.MEDIUM);
	}
	@FXML private void choiceHard(){
		choice(SudokuLevelEnum.HARD);
	}
	@FXML private void choiceExpert(){
		choice(SudokuLevelEnum.EXPERT);
	}
	@FXML private void choiceDefault(){
		choice(SudokuLevelEnum.DEFAULT);
	}
	private void choice(SudokuLevelEnum level) {
		resolveMenuItem.setDisable(false);
		resolveMenuItem2.setDisable(false);
		eraseMenuItem.setDisable(false);
		initGrille(level);
		
		choicePane.setVisible(false);
		gridSudoku.setFocusTraversable(false);
		gridSudoku.setVisible(true);
		
	}
	
	@FXML private void newGame(){
		
		stopBravo();
		choicePane.setVisible(true);
		gridSudoku.setVisible(false);
		printJobStatusLabel.setVisible(false);
		
		this.initiating = true;
		for(GroupIndexEnum groupIndexEnum: GroupIndexEnum.values()){
			for(PositionIndexEnum positionIndexEnum: PositionIndexEnum.values()){
				Box box = grilleMap.get(indexBox(groupIndexEnum, positionIndexEnum));
				box.getGroupCase().setText(StringUtils.EMPTY);
				box.getGroupCase().setDisable(false);
				box.getGroupCase().setStyle(focusOutStyle);
				box.setFixed(false);
			}
		}
		
		this.gridSudoku.setDisable(false);
		// TODO 
		//this.gridSudoku.setStyle("-fx-background-color: white");
		resolveMenuItem.setDisable(true);
		resolveMenuItem2.setDisable(true);
		this.initiating = false;
	}
	
	
	@SuppressWarnings("unchecked")
	public void init(){
		this.monitor = this;
		this.grilleMap = new HashMap<>();
		boxes = new ArrayList<>();
		occurIndexList = new ArrayList<>();
		undoSequences = new ArrayDeque<>();
		redoSequences = new ArrayDeque<>();
		
		myPrinter = new MyPrinter();
		myPrinter.setPrintJobStatusLabel(printJobStatusLabel);
		
		grilleMap.put(indexBox(GroupIndexEnum.INDEX_1, PositionIndexEnum.INDEX_1), new Box(g1Case1, g1Candidates1Label, new IndexBox(GroupIndexEnum.INDEX_1, PositionIndexEnum.INDEX_1)));
		grilleMap.put(indexBox(GroupIndexEnum.INDEX_1, PositionIndexEnum.INDEX_2), new Box(g1Case2, g1Candidates2Label, new IndexBox(GroupIndexEnum.INDEX_1, PositionIndexEnum.INDEX_2)));
		grilleMap.put(indexBox(GroupIndexEnum.INDEX_1, PositionIndexEnum.INDEX_3), new Box(g1Case3, g1Candidates3Label, new IndexBox(GroupIndexEnum.INDEX_1, PositionIndexEnum.INDEX_3)));
		grilleMap.put(indexBox(GroupIndexEnum.INDEX_1, PositionIndexEnum.INDEX_4), new Box(g1Case4, g1Candidates4Label, new IndexBox(GroupIndexEnum.INDEX_1, PositionIndexEnum.INDEX_4)));
		grilleMap.put(indexBox(GroupIndexEnum.INDEX_1, PositionIndexEnum.INDEX_5), new Box(g1Case5, g1Candidates5Label, new IndexBox(GroupIndexEnum.INDEX_1, PositionIndexEnum.INDEX_5)));
		grilleMap.put(indexBox(GroupIndexEnum.INDEX_1, PositionIndexEnum.INDEX_6), new Box(g1Case6, g1Candidates6Label, new IndexBox(GroupIndexEnum.INDEX_1, PositionIndexEnum.INDEX_6)));
		grilleMap.put(indexBox(GroupIndexEnum.INDEX_1, PositionIndexEnum.INDEX_7), new Box(g1Case7, g1Candidates7Label, new IndexBox(GroupIndexEnum.INDEX_1, PositionIndexEnum.INDEX_7)));
		grilleMap.put(indexBox(GroupIndexEnum.INDEX_1, PositionIndexEnum.INDEX_8), new Box(g1Case8, g1Candidates8Label, new IndexBox(GroupIndexEnum.INDEX_1, PositionIndexEnum.INDEX_8)));
		grilleMap.put(indexBox(GroupIndexEnum.INDEX_1, PositionIndexEnum.INDEX_9), new Box(g1Case9, g1Candidates9Label, new IndexBox(GroupIndexEnum.INDEX_1, PositionIndexEnum.INDEX_9)));
		
		grilleMap.put(indexBox(GroupIndexEnum.INDEX_2, PositionIndexEnum.INDEX_1), new Box(g2Case1, g2Candidates1Label, new IndexBox(GroupIndexEnum.INDEX_2, PositionIndexEnum.INDEX_1)));
		grilleMap.put(indexBox(GroupIndexEnum.INDEX_2, PositionIndexEnum.INDEX_2), new Box(g2Case2, g2Candidates2Label, new IndexBox(GroupIndexEnum.INDEX_2, PositionIndexEnum.INDEX_2)));
		grilleMap.put(indexBox(GroupIndexEnum.INDEX_2, PositionIndexEnum.INDEX_3), new Box(g2Case3, g2Candidates3Label, new IndexBox(GroupIndexEnum.INDEX_2, PositionIndexEnum.INDEX_3)));
		grilleMap.put(indexBox(GroupIndexEnum.INDEX_2, PositionIndexEnum.INDEX_4), new Box(g2Case4, g2Candidates4Label, new IndexBox(GroupIndexEnum.INDEX_2, PositionIndexEnum.INDEX_4)));
		grilleMap.put(indexBox(GroupIndexEnum.INDEX_2, PositionIndexEnum.INDEX_5), new Box(g2Case5, g2Candidates5Label, new IndexBox(GroupIndexEnum.INDEX_2, PositionIndexEnum.INDEX_5)));
		grilleMap.put(indexBox(GroupIndexEnum.INDEX_2, PositionIndexEnum.INDEX_6), new Box(g2Case6, g2Candidates6Label, new IndexBox(GroupIndexEnum.INDEX_2, PositionIndexEnum.INDEX_6)));
		grilleMap.put(indexBox(GroupIndexEnum.INDEX_2, PositionIndexEnum.INDEX_7), new Box(g2Case7, g2Candidates7Label, new IndexBox(GroupIndexEnum.INDEX_2, PositionIndexEnum.INDEX_7)));
		grilleMap.put(indexBox(GroupIndexEnum.INDEX_2, PositionIndexEnum.INDEX_8), new Box(g2Case8, g2Candidates8Label, new IndexBox(GroupIndexEnum.INDEX_2, PositionIndexEnum.INDEX_8)));
		grilleMap.put(indexBox(GroupIndexEnum.INDEX_2, PositionIndexEnum.INDEX_9), new Box(g2Case9, g2Candidates9Label, new IndexBox(GroupIndexEnum.INDEX_2, PositionIndexEnum.INDEX_9)));
		
		grilleMap.put(indexBox(GroupIndexEnum.INDEX_3, PositionIndexEnum.INDEX_1), new Box(g3Case1, g3Candidates1Label, new IndexBox(GroupIndexEnum.INDEX_3, PositionIndexEnum.INDEX_1)));
		grilleMap.put(indexBox(GroupIndexEnum.INDEX_3, PositionIndexEnum.INDEX_2), new Box(g3Case2, g3Candidates2Label, new IndexBox(GroupIndexEnum.INDEX_3, PositionIndexEnum.INDEX_2)));
		grilleMap.put(indexBox(GroupIndexEnum.INDEX_3, PositionIndexEnum.INDEX_3), new Box(g3Case3, g3Candidates3Label, new IndexBox(GroupIndexEnum.INDEX_3, PositionIndexEnum.INDEX_3)));
		grilleMap.put(indexBox(GroupIndexEnum.INDEX_3, PositionIndexEnum.INDEX_4), new Box(g3Case4, g3Candidates4Label, new IndexBox(GroupIndexEnum.INDEX_3, PositionIndexEnum.INDEX_4)));
		grilleMap.put(indexBox(GroupIndexEnum.INDEX_3, PositionIndexEnum.INDEX_5), new Box(g3Case5, g3Candidates5Label, new IndexBox(GroupIndexEnum.INDEX_3, PositionIndexEnum.INDEX_5)));
		grilleMap.put(indexBox(GroupIndexEnum.INDEX_3, PositionIndexEnum.INDEX_6), new Box(g3Case6, g3Candidates6Label, new IndexBox(GroupIndexEnum.INDEX_3, PositionIndexEnum.INDEX_6)));
		grilleMap.put(indexBox(GroupIndexEnum.INDEX_3, PositionIndexEnum.INDEX_7), new Box(g3Case7, g3Candidates7Label, new IndexBox(GroupIndexEnum.INDEX_3, PositionIndexEnum.INDEX_7)));
		grilleMap.put(indexBox(GroupIndexEnum.INDEX_3, PositionIndexEnum.INDEX_8), new Box(g3Case8, g3Candidates8Label, new IndexBox(GroupIndexEnum.INDEX_3, PositionIndexEnum.INDEX_8)));
		grilleMap.put(indexBox(GroupIndexEnum.INDEX_3, PositionIndexEnum.INDEX_9), new Box(g3Case9, g3Candidates9Label, new IndexBox(GroupIndexEnum.INDEX_3, PositionIndexEnum.INDEX_9)));
		
		grilleMap.put(indexBox(GroupIndexEnum.INDEX_4, PositionIndexEnum.INDEX_1), new Box(g4Case1, g4Candidates1Label, new IndexBox(GroupIndexEnum.INDEX_4, PositionIndexEnum.INDEX_1)));
		grilleMap.put(indexBox(GroupIndexEnum.INDEX_4, PositionIndexEnum.INDEX_2), new Box(g4Case2, g4Candidates2Label, new IndexBox(GroupIndexEnum.INDEX_4, PositionIndexEnum.INDEX_2)));
		grilleMap.put(indexBox(GroupIndexEnum.INDEX_4, PositionIndexEnum.INDEX_3), new Box(g4Case3, g4Candidates3Label, new IndexBox(GroupIndexEnum.INDEX_4, PositionIndexEnum.INDEX_3)));
		grilleMap.put(indexBox(GroupIndexEnum.INDEX_4, PositionIndexEnum.INDEX_4), new Box(g4Case4, g4Candidates4Label, new IndexBox(GroupIndexEnum.INDEX_4, PositionIndexEnum.INDEX_4)));
		grilleMap.put(indexBox(GroupIndexEnum.INDEX_4, PositionIndexEnum.INDEX_5), new Box(g4Case5, g4Candidates5Label, new IndexBox(GroupIndexEnum.INDEX_4, PositionIndexEnum.INDEX_5)));
		grilleMap.put(indexBox(GroupIndexEnum.INDEX_4, PositionIndexEnum.INDEX_6), new Box(g4Case6, g4Candidates6Label, new IndexBox(GroupIndexEnum.INDEX_4, PositionIndexEnum.INDEX_6)));
		grilleMap.put(indexBox(GroupIndexEnum.INDEX_4, PositionIndexEnum.INDEX_7), new Box(g4Case7, g4Candidates7Label, new IndexBox(GroupIndexEnum.INDEX_4, PositionIndexEnum.INDEX_7)));
		grilleMap.put(indexBox(GroupIndexEnum.INDEX_4, PositionIndexEnum.INDEX_8), new Box(g4Case8, g4Candidates8Label, new IndexBox(GroupIndexEnum.INDEX_4, PositionIndexEnum.INDEX_8)));
		grilleMap.put(indexBox(GroupIndexEnum.INDEX_4, PositionIndexEnum.INDEX_9), new Box(g4Case9, g4Candidates9Label, new IndexBox(GroupIndexEnum.INDEX_4, PositionIndexEnum.INDEX_9)));
		
		grilleMap.put(indexBox(GroupIndexEnum.INDEX_5, PositionIndexEnum.INDEX_1), new Box(g5Case1, g5Candidates1Label, new IndexBox(GroupIndexEnum.INDEX_5, PositionIndexEnum.INDEX_1)));
		grilleMap.put(indexBox(GroupIndexEnum.INDEX_5, PositionIndexEnum.INDEX_2), new Box(g5Case2, g5Candidates2Label, new IndexBox(GroupIndexEnum.INDEX_5, PositionIndexEnum.INDEX_2)));
		grilleMap.put(indexBox(GroupIndexEnum.INDEX_5, PositionIndexEnum.INDEX_3), new Box(g5Case3, g5Candidates3Label, new IndexBox(GroupIndexEnum.INDEX_5, PositionIndexEnum.INDEX_3)));
		grilleMap.put(indexBox(GroupIndexEnum.INDEX_5, PositionIndexEnum.INDEX_4), new Box(g5Case4, g5Candidates4Label, new IndexBox(GroupIndexEnum.INDEX_5, PositionIndexEnum.INDEX_4)));
		grilleMap.put(indexBox(GroupIndexEnum.INDEX_5, PositionIndexEnum.INDEX_5), new Box(g5Case5, g5Candidates5Label, new IndexBox(GroupIndexEnum.INDEX_5, PositionIndexEnum.INDEX_5)));
		grilleMap.put(indexBox(GroupIndexEnum.INDEX_5, PositionIndexEnum.INDEX_6), new Box(g5Case6, g5Candidates6Label, new IndexBox(GroupIndexEnum.INDEX_5, PositionIndexEnum.INDEX_6)));
		grilleMap.put(indexBox(GroupIndexEnum.INDEX_5, PositionIndexEnum.INDEX_7), new Box(g5Case7, g5Candidates7Label, new IndexBox(GroupIndexEnum.INDEX_5, PositionIndexEnum.INDEX_7)));
		grilleMap.put(indexBox(GroupIndexEnum.INDEX_5, PositionIndexEnum.INDEX_8), new Box(g5Case8, g5Candidates8Label, new IndexBox(GroupIndexEnum.INDEX_5, PositionIndexEnum.INDEX_8)));
		grilleMap.put(indexBox(GroupIndexEnum.INDEX_5, PositionIndexEnum.INDEX_9), new Box(g5Case9, g5Candidates9Label, new IndexBox(GroupIndexEnum.INDEX_5, PositionIndexEnum.INDEX_9)));
		
		grilleMap.put(indexBox(GroupIndexEnum.INDEX_6, PositionIndexEnum.INDEX_1), new Box(g6Case1, g6Candidates1Label, new IndexBox(GroupIndexEnum.INDEX_6, PositionIndexEnum.INDEX_1)));
		grilleMap.put(indexBox(GroupIndexEnum.INDEX_6, PositionIndexEnum.INDEX_2), new Box(g6Case2, g6Candidates2Label, new IndexBox(GroupIndexEnum.INDEX_6, PositionIndexEnum.INDEX_2)));
		grilleMap.put(indexBox(GroupIndexEnum.INDEX_6, PositionIndexEnum.INDEX_3), new Box(g6Case3, g6Candidates3Label, new IndexBox(GroupIndexEnum.INDEX_6, PositionIndexEnum.INDEX_3)));
		grilleMap.put(indexBox(GroupIndexEnum.INDEX_6, PositionIndexEnum.INDEX_4), new Box(g6Case4, g6Candidates4Label, new IndexBox(GroupIndexEnum.INDEX_6, PositionIndexEnum.INDEX_4)));
		grilleMap.put(indexBox(GroupIndexEnum.INDEX_6, PositionIndexEnum.INDEX_5), new Box(g6Case5, g6Candidates5Label, new IndexBox(GroupIndexEnum.INDEX_6, PositionIndexEnum.INDEX_5)));
		grilleMap.put(indexBox(GroupIndexEnum.INDEX_6, PositionIndexEnum.INDEX_6), new Box(g6Case6, g6Candidates6Label, new IndexBox(GroupIndexEnum.INDEX_6, PositionIndexEnum.INDEX_6)));
		grilleMap.put(indexBox(GroupIndexEnum.INDEX_6, PositionIndexEnum.INDEX_7), new Box(g6Case7, g6Candidates7Label, new IndexBox(GroupIndexEnum.INDEX_6, PositionIndexEnum.INDEX_7)));
		grilleMap.put(indexBox(GroupIndexEnum.INDEX_6, PositionIndexEnum.INDEX_8), new Box(g6Case8, g6Candidates8Label, new IndexBox(GroupIndexEnum.INDEX_6, PositionIndexEnum.INDEX_8)));
		grilleMap.put(indexBox(GroupIndexEnum.INDEX_6, PositionIndexEnum.INDEX_9), new Box(g6Case9, g6Candidates9Label, new IndexBox(GroupIndexEnum.INDEX_6, PositionIndexEnum.INDEX_9)));
		
		grilleMap.put(indexBox(GroupIndexEnum.INDEX_7, PositionIndexEnum.INDEX_1), new Box(g7Case1, g7Candidates1Label, new IndexBox(GroupIndexEnum.INDEX_7, PositionIndexEnum.INDEX_1)));
		grilleMap.put(indexBox(GroupIndexEnum.INDEX_7, PositionIndexEnum.INDEX_2), new Box(g7Case2, g7Candidates2Label, new IndexBox(GroupIndexEnum.INDEX_7, PositionIndexEnum.INDEX_2)));
		grilleMap.put(indexBox(GroupIndexEnum.INDEX_7, PositionIndexEnum.INDEX_3), new Box(g7Case3, g7Candidates3Label, new IndexBox(GroupIndexEnum.INDEX_7, PositionIndexEnum.INDEX_3)));
		grilleMap.put(indexBox(GroupIndexEnum.INDEX_7, PositionIndexEnum.INDEX_4), new Box(g7Case4, g7Candidates4Label, new IndexBox(GroupIndexEnum.INDEX_7, PositionIndexEnum.INDEX_4)));
		grilleMap.put(indexBox(GroupIndexEnum.INDEX_7, PositionIndexEnum.INDEX_5), new Box(g7Case5, g7Candidates5Label, new IndexBox(GroupIndexEnum.INDEX_7, PositionIndexEnum.INDEX_5)));
		grilleMap.put(indexBox(GroupIndexEnum.INDEX_7, PositionIndexEnum.INDEX_6), new Box(g7Case6, g7Candidates6Label, new IndexBox(GroupIndexEnum.INDEX_7, PositionIndexEnum.INDEX_6)));
		grilleMap.put(indexBox(GroupIndexEnum.INDEX_7, PositionIndexEnum.INDEX_7), new Box(g7Case7, g7Candidates7Label, new IndexBox(GroupIndexEnum.INDEX_7, PositionIndexEnum.INDEX_7)));
		grilleMap.put(indexBox(GroupIndexEnum.INDEX_7, PositionIndexEnum.INDEX_8), new Box(g7Case8, g7Candidates8Label, new IndexBox(GroupIndexEnum.INDEX_7, PositionIndexEnum.INDEX_8)));
		grilleMap.put(indexBox(GroupIndexEnum.INDEX_7, PositionIndexEnum.INDEX_9), new Box(g7Case9, g7Candidates9Label, new IndexBox(GroupIndexEnum.INDEX_7, PositionIndexEnum.INDEX_9)));
		
		grilleMap.put(indexBox(GroupIndexEnum.INDEX_8, PositionIndexEnum.INDEX_1), new Box(g8Case1, g8Candidates1Label, new IndexBox(GroupIndexEnum.INDEX_8, PositionIndexEnum.INDEX_1)));
		grilleMap.put(indexBox(GroupIndexEnum.INDEX_8, PositionIndexEnum.INDEX_2), new Box(g8Case2, g8Candidates2Label, new IndexBox(GroupIndexEnum.INDEX_8, PositionIndexEnum.INDEX_2)));
		grilleMap.put(indexBox(GroupIndexEnum.INDEX_8, PositionIndexEnum.INDEX_3), new Box(g8Case3, g8Candidates3Label, new IndexBox(GroupIndexEnum.INDEX_8, PositionIndexEnum.INDEX_3)));
		grilleMap.put(indexBox(GroupIndexEnum.INDEX_8, PositionIndexEnum.INDEX_4), new Box(g8Case4, g8Candidates4Label, new IndexBox(GroupIndexEnum.INDEX_8, PositionIndexEnum.INDEX_4)));
		grilleMap.put(indexBox(GroupIndexEnum.INDEX_8, PositionIndexEnum.INDEX_5), new Box(g8Case5, g8Candidates5Label, new IndexBox(GroupIndexEnum.INDEX_8, PositionIndexEnum.INDEX_5)));
		grilleMap.put(indexBox(GroupIndexEnum.INDEX_8, PositionIndexEnum.INDEX_6), new Box(g8Case6, g8Candidates6Label, new IndexBox(GroupIndexEnum.INDEX_8, PositionIndexEnum.INDEX_6)));
		grilleMap.put(indexBox(GroupIndexEnum.INDEX_8, PositionIndexEnum.INDEX_7), new Box(g8Case7, g8Candidates7Label, new IndexBox(GroupIndexEnum.INDEX_8, PositionIndexEnum.INDEX_7)));
		grilleMap.put(indexBox(GroupIndexEnum.INDEX_8, PositionIndexEnum.INDEX_8), new Box(g8Case8, g8Candidates8Label, new IndexBox(GroupIndexEnum.INDEX_8, PositionIndexEnum.INDEX_8)));
		grilleMap.put(indexBox(GroupIndexEnum.INDEX_8, PositionIndexEnum.INDEX_9), new Box(g8Case9, g8Candidates9Label, new IndexBox(GroupIndexEnum.INDEX_8, PositionIndexEnum.INDEX_9)));
		
		grilleMap.put(indexBox(GroupIndexEnum.INDEX_9, PositionIndexEnum.INDEX_1), new Box(g9Case1, g9Candidates1Label, new IndexBox(GroupIndexEnum.INDEX_9, PositionIndexEnum.INDEX_1)));
		grilleMap.put(indexBox(GroupIndexEnum.INDEX_9, PositionIndexEnum.INDEX_2), new Box(g9Case2, g9Candidates2Label, new IndexBox(GroupIndexEnum.INDEX_9, PositionIndexEnum.INDEX_2)));
		grilleMap.put(indexBox(GroupIndexEnum.INDEX_9, PositionIndexEnum.INDEX_3), new Box(g9Case3, g9Candidates3Label, new IndexBox(GroupIndexEnum.INDEX_9, PositionIndexEnum.INDEX_3)));
		grilleMap.put(indexBox(GroupIndexEnum.INDEX_9, PositionIndexEnum.INDEX_4), new Box(g9Case4, g9Candidates4Label, new IndexBox(GroupIndexEnum.INDEX_9, PositionIndexEnum.INDEX_4)));
		grilleMap.put(indexBox(GroupIndexEnum.INDEX_9, PositionIndexEnum.INDEX_5), new Box(g9Case5, g9Candidates5Label, new IndexBox(GroupIndexEnum.INDEX_9, PositionIndexEnum.INDEX_5)));
		grilleMap.put(indexBox(GroupIndexEnum.INDEX_9, PositionIndexEnum.INDEX_6), new Box(g9Case6, g9Candidates6Label, new IndexBox(GroupIndexEnum.INDEX_9, PositionIndexEnum.INDEX_6)));
		grilleMap.put(indexBox(GroupIndexEnum.INDEX_9, PositionIndexEnum.INDEX_7), new Box(g9Case7, g9Candidates7Label, new IndexBox(GroupIndexEnum.INDEX_9, PositionIndexEnum.INDEX_7)));
		grilleMap.put(indexBox(GroupIndexEnum.INDEX_9, PositionIndexEnum.INDEX_8), new Box(g9Case8, g9Candidates8Label, new IndexBox(GroupIndexEnum.INDEX_9, PositionIndexEnum.INDEX_8)));
		grilleMap.put(indexBox(GroupIndexEnum.INDEX_9, PositionIndexEnum.INDEX_9), new Box(g9Case9, g9Candidates9Label, new IndexBox(GroupIndexEnum.INDEX_9, PositionIndexEnum.INDEX_9)));
		
		FocusEventListener focusListen = new FocusEventListener(this);
		InputChangeListener inputListen = new InputChangeListener(this);
		for(GroupIndexEnum groupIndexEnum: GroupIndexEnum.values()){
			for(PositionIndexEnum positionIndexEnum: PositionIndexEnum.values()){
				Box box = grilleMap.get(indexBox(groupIndexEnum, positionIndexEnum));
				
				box.getGroupCase().focusedProperty().addListener(focusListen);
				box.getGroupCase().setTextFormatter(new TextFormatter<String>(filter));
				
				box.getGroupCase().textProperty().addListener(inputListen);
				box.getGroupCase().addEventFilter(KeyEvent.KEY_PRESSED,
		                event -> System.out.println("Pressed: " + event.getText()));
			}
		}
		
		// Init "Bravo !" text animation ==================================================================
		this.fadeTransition = new FadeTransition(javafx.util.Duration.seconds(2), bravoLabel);
        fadeTransition.setFromValue(8.0);
        fadeTransition.setToValue(0.0);
        fadeTransition.setCycleCount(Animation.INDEFINITE);
	}
   
	UnaryOperator<TextFormatter.Change> filter = c -> {
	    String proposedText = c.getControlNewText();
	    if (proposedText.matches("[1-9]{0,1}")) {
	        return c ;
	    } else {
	        return null ;
	    }
	};
	
	@FXML private MenuItem printMenuItem;
	@FXML private MenuItem printSolutionMenuItem;
	@FXML private Label printJobStatusLabel;
	
	
	@FXML private Label durationLabel; 
	@FXML private Pane menuPane;
	@FXML private Pane unrePane;
	@FXML private Pane cancelPane;
	
	@FXML private Button undoBtn;
	@FXML private Button redoBtn;
	
	private FadeTransition fadeTransition;
	
	@FXML private Label bravoLabel; 
	
	@FXML private MenuItem resolveMenuItem;
	@FXML private MenuItem resolveMenuItem2;
	@FXML private MenuItem eraseMenuItem;
	@FXML private GridPane gridSudoku;
	@FXML private Pane choicePane;
	
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
		
	}

	@Override
	public void display(Case caze) {
		
		try {
			Box box = grilleMap.get(indexBox(caze.getGroup().getIndex(), caze.getPosition().getIndex()));
			if(box != null) {
				box.getGroupCase().setText(caze.getContent() + "");
			}
			Thread.sleep(1000);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void erase(Case caze) {
		try {
			System.out.println(caze);
			Box box = grilleMap.get(indexBox(caze.getGroup().getIndex(), caze.getPosition().getIndex()));
			if(box != null) {
				box.getGroupCase().setText("");
			}
			Thread.sleep(1000);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}