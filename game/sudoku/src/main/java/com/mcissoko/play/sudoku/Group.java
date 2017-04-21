package com.mcissoko.play.sudoku;

import java.util.HashMap;
import java.util.Map;

import com.mcissoko.play.sudoku.api.PlaySequence;
import com.mcissoko.play.sudoku.player.Grille;

public class Group {

	private GroupIndexEnum index;
	private Map<PositionIndexEnum, Case> cases;
	
	private GroupIndexEnum[] groupLine;
	private GroupIndexEnum[] groupColumn;
	
	private Grille grid;
		
	public Group(GroupIndexEnum groupIndex, Grille grid) {
		this.index = groupIndex;
		this.init();
		this.grid = grid;
		
	}

	private void init(){
		cases = new HashMap<>();
		for(PositionIndexEnum position: PositionIndexEnum.values()){
			cases.put(position, new Case(new Position(index, position), this));
		}		
		initGroupLine();
		initGroupColumn();
	}
	
	private void initGroupLine(){
		groupLine = new GroupIndexEnum[2];
		switch (this.index) {
		case INDEX_1:
			groupLine[0] = GroupIndexEnum.INDEX_2;
			groupLine[1] = GroupIndexEnum.INDEX_3;
			break;
		case INDEX_2:
			groupLine[0] = GroupIndexEnum.INDEX_1;
			groupLine[1] = GroupIndexEnum.INDEX_3;
			break;
			
		case INDEX_3:
			groupLine[0] = GroupIndexEnum.INDEX_1;
			groupLine[1] = GroupIndexEnum.INDEX_2;
			break;
		case INDEX_4:
			groupLine[0] = GroupIndexEnum.INDEX_5;
			groupLine[1] = GroupIndexEnum.INDEX_6;
			break;
		case INDEX_5:
			groupLine[0] = GroupIndexEnum.INDEX_4;
			groupLine[1] = GroupIndexEnum.INDEX_6;
			break;	
		case INDEX_6:
			groupLine[0] = GroupIndexEnum.INDEX_4;
			groupLine[1] = GroupIndexEnum.INDEX_5;
			break;
		case INDEX_7:
			groupLine[0] = GroupIndexEnum.INDEX_8;
			groupLine[1] = GroupIndexEnum.INDEX_9;
			break;	
		case INDEX_8:
			groupLine[0] = GroupIndexEnum.INDEX_7;
			groupLine[1] = GroupIndexEnum.INDEX_9;
			break;
		case INDEX_9:
			groupLine[0] = GroupIndexEnum.INDEX_7;
			groupLine[1] = GroupIndexEnum.INDEX_8;
			break;	
		default:
			break;
		}

		
	}
	
	private void initGroupColumn(){
		groupColumn = new GroupIndexEnum[2];
		switch (this.index) {
		case INDEX_1:
			groupColumn[0] = GroupIndexEnum.INDEX_4;
			groupColumn[1] = GroupIndexEnum.INDEX_7;
			break;
		case INDEX_2:
			groupColumn[0] = GroupIndexEnum.INDEX_5;
			groupColumn[1] = GroupIndexEnum.INDEX_8;
			break;
			
		case INDEX_3:
			groupColumn[0] = GroupIndexEnum.INDEX_6;
			groupColumn[1] = GroupIndexEnum.INDEX_9;
			break;
		case INDEX_4:
			groupColumn[0] = GroupIndexEnum.INDEX_1;
			groupColumn[1] = GroupIndexEnum.INDEX_7;
			break;
		case INDEX_5:
			groupColumn[0] = GroupIndexEnum.INDEX_2;
			groupColumn[1] = GroupIndexEnum.INDEX_8;
			break;	
		case INDEX_6:
			groupColumn[0] = GroupIndexEnum.INDEX_3;
			groupColumn[1] = GroupIndexEnum.INDEX_9;
			break;
		case INDEX_7:
			groupColumn[0] = GroupIndexEnum.INDEX_1;
			groupColumn[1] = GroupIndexEnum.INDEX_4;
			break;	
		case INDEX_8:
			groupColumn[0] = GroupIndexEnum.INDEX_2;
			groupColumn[1] = GroupIndexEnum.INDEX_5;
			break;
		case INDEX_9:
			groupColumn[0] = GroupIndexEnum.INDEX_3;
			groupColumn[1] = GroupIndexEnum.INDEX_6;
			break;	
		default:
			break;
		}
		
		
		
	}
	
	
	/**
	 * Vérifie si le numero n'est pas présente plus d'une fois dans le groupement
	 * @param content
	 * @return
	 */
	public boolean isContentUniqueInGroup(Case box) {
		int content = box.getContent();
		int occurrence = 0;
		for(Case caze : cases.values()){
			if (caze.sizeOfCandidate() == 1 && caze.getState() == StateCaseEnum.EMPTY){
				if(caze.getCandidates().get(0) == content){
					occurrence++;
				}
			}else{
				if (caze.getContent() == content) {
					occurrence++;
				}				
			}
		}
		
		return occurrence == 1;
	}
	
	public boolean isContentUnique(Case caze) {
		
		return isContentUniqueInGroup(caze) && this.grid.isNumberInGrilleLine(caze) && this.grid.isNumberInGrilleColumn(caze);
	}
	
	public boolean notExist(int content) {
		
		for(Case caze : cases.values()){
			if (caze.getContent() == content) {
				return false;
			}
		}
		
		return true;
	}


	public boolean isNumberInGroupLine(Case caze) {
		int valeur = caze.getContent();
		int ligne = caze.getPosition().getCoordinate().getLine();
		
		for(Case box : cases.values()){
		
			if(box.getPosition().getCoordinate().getLine() == ligne){
				if (box.sizeOfCandidate() == 1 && box.getState() == StateCaseEnum.EMPTY){
					if(box.getCandidates().get(0) == valeur){
						return true;
					}
				}else{
					if(box.getContent() ==  valeur){
						return true;					
					}
				}
			}
		}
		return false;
	}
	public boolean removeCandidateInGroupLine(Case filled, PlaySequence playSequence) {
		int line = filled.getPosition().getCoordinate().getLine();
		for(Case caze : cases.values()){
			if(caze.getPosition().getCoordinate().getLine() == line){
				if(caze.sizeOfCandidate() == 1 && filled.getContent().equals(caze.getCandidates().get(0))){
//					PlaySequence ps =  caze.fillContent();
//					removeCandidate(caze, ps);
//					grid.addPlaySequence(ps);
//					continue;
					System.out.println("Echec tentative: aucun candidat (2)");
					return false;
				}
				 caze.removeCandidate(filled.getContent(), playSequence);
				 //System.out.println(caze);
				 //System.out.println(caze.getCandidates());
			}
		}
		return true;
	}
	
	public boolean isNumberInGroupColumn(Case caze) {
		int valeur = caze.getContent();
		int colonne = caze.getPosition().getCoordinate().getColumn();
		
		for(Case box : cases.values()){
			
			if(box.getPosition().getCoordinate().getColumn() == colonne){
				if (box.sizeOfCandidate() == 1 && box.getState() == StateCaseEnum.EMPTY){
					if(box.getCandidates().get(0) == valeur){
						return true;
					}
				}else{ 
					if(box.getContent() ==  valeur){
						return true;					 
					}
				}
			}
		}
		return false;
	}
	
	public boolean removeCandidateInGroupColumn(Case filled, PlaySequence playSequence) {
		int column = filled.getPosition().getCoordinate().getColumn();
		for(Case caze : cases.values()){
			if(caze.getPosition().getCoordinate().getColumn() == column){
				if(caze.sizeOfCandidate() == 1 && filled.getContent().equals(caze.getCandidates().get(0))){
//					PlaySequence ps =  caze.fillContent();
//					removeCandidate(caze, ps);
//					grid.addPlaySequence(ps);
//					continue;
					System.out.println("Echec tentative: aucun candidat (3)");
					return false;
				}
				caze.removeCandidate(filled.getContent(), playSequence);
			}
		}
		
		return true;
	}
		
	/**
	 * Vérifie si le groupement est conforme au sudoku:
	 * chacune des 9 cases a un contenu unique
	 * et le contenu de chaque case est unique sur la ligne/colonne
	 * globale de la grille
	 * 
	 * 
	 * @param grille
	 * @return
	 */
	public boolean isSudoku(){
		
		for(Case caze : cases.values()){
			int content = caze.getContent();
			if (content > 0 && isContentUnique(caze)) {
				continue;
			}else{
				return false;
			}
		}
		
		return true;
	}
	
	
	
	public boolean removeCandidate(Case filled, PlaySequence playSequence){
		PositionIndexEnum index = filled.getPosition().getIndex();
		Integer candidate = filled.getContent();
		for(PositionIndexEnum positionIndexEnum: PositionIndexEnum.values()){
			Case caze = cases.get(positionIndexEnum);
			
			if (index == caze.getPosition().getIndex()) {
				continue;
			}
			if(caze.sizeOfCandidate() == 1 && candidate.equals(caze.getCandidates().get(0))){
//				PlaySequence ps =  caze.fillContent();
//				removeCandidate(caze, ps);
//				grid.addPlaySequence(ps);
//				continue;
				System.out.println("Echec tentative: aucun candidat ()");
				return false;
			}
			caze.removeCandidate(candidate, playSequence);
			
			//System.out.println(caze);
			//System.out.println(caze.getCandidates());
		}
		return 
		grid.getGroup(groupLine[0]).removeCandidateInGroupLine(filled, playSequence) &&
		grid.getGroup(groupLine[1]).removeCandidateInGroupLine(filled, playSequence) &&
		
		grid.getGroup(groupColumn[0]).removeCandidateInGroupColumn(filled, playSequence) &&
		grid.getGroup(groupColumn[1]).removeCandidateInGroupColumn(filled, playSequence);
		
		
	}
	
	
	public void resetContent(){
		for(Case caze : cases.values()){
			caze.resetContent();
		}
	}
	
	
	public Group fillCase(PositionIndexEnum positionIndex, int content){
		cases.get(positionIndex).setContent(content);
		return this;
	}
	
	public GroupIndexEnum[] getGroupLine() {
		return groupLine;
	}

	public GroupIndexEnum[] getGroupColumn() {
		return groupColumn;
	}	

	public GroupIndexEnum getIndex() {
		return index;
	}

	public Map<PositionIndexEnum, Case> getCases() {
		return cases;
	}
	public  Case getCases(PositionIndexEnum index) {
		return cases.get(index);
	}
	private boolean gridFilled;
	public Map<Integer, Object> checkCandidate(Map<Integer, Object> result){
		Case candidate = (Case) result.get(1);
		gridFilled = true;
		for(PositionIndexEnum positionIndexEnum: PositionIndexEnum.values()){
			Case caze = cases.get(positionIndexEnum);
			
			if(caze.getState() == StateCaseEnum.FILLED){
				continue;
			}
			if(candidate.getState() == StateCaseEnum.FILLED || caze.sizeOfCandidate() < candidate.sizeOfCandidate()){
				candidate = caze;
			}
			gridFilled = gridFilled & (caze.sizeOfCandidate() == 0);
		}
		result.put(0, gridFilled);
		result.put(1, candidate);
		return result;
	}
	

	public Grille getGrid() {
		return grid;
	}

	@Override
	public String toString() {
		
		StringBuilder sb = new StringBuilder();
		int startPosition = 1;
		int endPosition = 3;
		while (endPosition <= 9) {
			
			for (int i = startPosition; i <= endPosition; i++) {
				sb.append(cases.get(PositionIndexEnum.fromIndex(i)).toString());
			}
			sb.append("\n");
			startPosition = startPosition + 3;
			endPosition = endPosition + 3;
		}
	
		return sb.toString();
	}
	
	public void calculateOccurenceInGroupColumn(Case box, Map<Integer, Integer> occurrenceInColumn) {
		int column = box.getPosition().getCoordinate().getColumn();
		for(Case caze : cases.values()){
			if(caze.getPosition().getCoordinate().getColumn() == column){
				for(Integer candidate: caze.getCandidates()){
					if(occurrenceInColumn.containsKey(candidate)){
						occurrenceInColumn.put(candidate, (occurrenceInColumn.get(candidate) + 1));
					}
				}
				
			}
		}
	}
	
	public void calculateOccurenceInGroupLine(Case box, Map<Integer, Integer> occurrenceInLine) {
		int line = box.getPosition().getCoordinate().getLine();
		for(Case caze : cases.values()){
			if(caze.getPosition().getCoordinate().getLine() == line){
				for(Integer candidate: caze.getCandidates()){
					if(occurrenceInLine.containsKey(candidate)){
						occurrenceInLine.put(candidate, (occurrenceInLine.get(candidate) + 1));
					}
				}
				
			}
		}
	}
	

	public void calculateOccurence(Case box, Map<Integer, Integer> occurrenceInLine, Map<Integer, Integer> occurrenceInColumn) {
		
		calculateOccurenceInGroupLine(box, occurrenceInLine);
		calculateOccurenceInGroupColumn(box, occurrenceInColumn);
		
		grid.getGroup(groupLine[0]).calculateOccurenceInGroupLine(box, occurrenceInLine);
		grid.getGroup(groupLine[1]).calculateOccurenceInGroupLine(box, occurrenceInLine);
		
		grid.getGroup(groupColumn[0]).calculateOccurenceInGroupColumn(box, occurrenceInColumn);
		grid.getGroup(groupColumn[1]).calculateOccurenceInGroupColumn(box, occurrenceInColumn);
		
	}
}











