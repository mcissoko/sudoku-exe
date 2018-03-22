package com.mcissoko.play.sudoku;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Group implements Serializable{

	private static final long serialVersionUID = 3330283792812169026L;
	private final GroupIndexEnum index;
	private Map<PositionIndexEnum, Case> cases;
	
	private GroupIndexEnum[] groupLine;
	private GroupIndexEnum[] groupColumn;
	
	private Grille grid;
		
	protected Group(GroupIndexEnum groupIndex, Grille grid) {
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
	protected boolean isContentUniqueInGroup(Case box, List<Case> occurrences) {
		int content = box.getContent();
		int occurrenceCount = 0;
		for(Case caze : cases.values()){
			if (caze.sizeOfCandidate() == 1 && caze.getState() == StateCaseEnum.EMPTY){
				if(caze.getCandidates().get(0).equals(content)){
					occurrenceCount++;
				}
			}else{
				if (caze.getContent().equals(content)) {
					if(occurrences != null){
						occurrences.add(caze);
					}
					occurrenceCount++;
				}				
			}
		}
		
		return occurrenceCount == 1;
	}
	
	public boolean isContentUnique(Case caze, List<Case> occurrences) {
		
		return isContentUniqueInGroup(caze, occurrences) && this.grid.isNumberInGrilleLine(caze, occurrences) && this.grid.isNumberInGrilleColumn(caze, occurrences);
	}
	
	protected boolean notExist(int content) {
		
		for(Case caze : cases.values()){
			if (caze.getContent() == content) {
				return false;
			}
		}
		
		return true;
	}


	protected boolean isNumberInGroupLine(Case caze, List<Case> occurrences) {
		int valeur = caze.getContent();
		int ligne = caze.getPosition().getCoordinate().getLine();
		
		for(Case box : cases.values()){
		
			if(box.getPosition().getCoordinate().getLine() == ligne){
				if (box.sizeOfCandidate() == 1 && box.getState() == StateCaseEnum.EMPTY){
					if(box.getCandidates().get(0).equals(valeur)){
						return true;
					}
				}else{
					if(box.getContent().equals(valeur)){
						if(occurrences != null){
							occurrences.add(box);
						}
						return true;					
					}
				}
			}
		}
		return false;
	}
	protected void removeCandidateInGroupLine(Case filled, PlaySequence playSequence) {
		int line = filled.getPosition().getCoordinate().getLine();
		for(Case caze : cases.values()){
			if(caze.getPosition().getCoordinate().getLine() == line){
				if(caze.hasCandidate(filled.getContent())){
					caze.removeCandidate(filled.getContent(), playSequence);
				}
			}
		}
	}
	
	protected void removeCandidateInGroupLine(Case filled) {
		int line = filled.getPosition().getCoordinate().getLine();
		for (GroupIndexEnum gi : this.groupLine) {
			Group group = this.grid.getGroup(gi);
			for (Case caze : group.getCases().values()) {
				if (caze.getPosition().getCoordinate().getLine() == line) {
					if (caze.hasCandidate(filled.getContent())) {
						caze.removeCandidate(filled.getContent());
					}
				}
			}
		}
	}
	
	protected boolean isNumberInGroupColumn(Case caze, List<Case> occurrences) {
		int valeur = caze.getContent();
		int colonne = caze.getPosition().getCoordinate().getColumn();
		
		for(Case box : cases.values()){
			
			if(box.getPosition().getCoordinate().getColumn() == colonne){
				if (box.sizeOfCandidate() == 1 && box.getState() == StateCaseEnum.EMPTY){
					if(box.getCandidates().get(0).equals(valeur)){
						return true;
					}
				}else{ 
					if(box.getContent().equals(valeur)){
						if(occurrences != null){
							occurrences.add(box);
						}
						return true;					 
					}
				}
			}
		}
		return false;
	}
	
	protected void removeCandidate(Case filled) {
		cases.values().forEach(c -> c.getCandidates().remove(filled.getContent()));
	}
	
	protected void removeCandidateInGroupColumn(Case filled, PlaySequence playSequence) {
		int column = filled.getPosition().getCoordinate().getColumn();
		for(Case caze : cases.values()){
			if(caze.getPosition().getCoordinate().getColumn() == column){

				if(caze.hasCandidate(filled.getContent())){
					caze.removeCandidate(filled.getContent(), playSequence);
				}
			}
		}
		
	}
	
	protected void removeCandidateInGroupColumn(Case filled) {
		int column = filled.getPosition().getCoordinate().getColumn();
		for(GroupIndexEnum gi: this.groupColumn) {
			Group group = this.grid.getGroup(gi);
			for(Case caze : group.getCases().values()){
				if(caze.getPosition().getCoordinate().getColumn() == column){

					if(caze.hasCandidate(filled.getContent())){
						caze.removeCandidate(filled.getContent());
					}
				}
			}
		}
		
	}
	
	public boolean isValid() {
		
		List<Case> empty = cases.values().parallelStream().filter(c -> c.getState() == StateCaseEnum.EMPTY).collect(Collectors.toList());
		if(!empty.isEmpty()) {			
			List<Integer> filledList = cases.values().parallelStream().filter(c -> c.getState() != StateCaseEnum.EMPTY).map(Case::getContent).collect(Collectors.toList()); 
			List<Integer> toFill = IntStream.rangeClosed(1, 9).boxed().filter(i -> !filledList.contains(i)).collect(Collectors.toList()); 
			for(Integer i: toFill) {
				boolean found = false;
				for(Case caze: empty) {
					if(caze.getCandidates().contains(i)) {
						found = true;
					}
				}
				if(!found) {
					return false;
				}
			}
		}
		
		return true;
		
		/*
		//List<Case> empty = cases.values().parallelStream().filter(c -> c.getState() == StateCaseEnum.EMPTY).collect(Collectors.toList());
		List<Integer> filledList = cases.values().parallelStream()
				.filter(c -> c.getState() != StateCaseEnum.EMPTY)
				.map(Case::getContent).collect(Collectors.toList()); 
		
		Set<Integer> candidates = cases.values().parallelStream()
				.filter(c -> c.getState() == StateCaseEnum.EMPTY)
				.map(Case::getCandidates).collect(HashSet::new, Set::addAll, Set::addAll);
		boolean possible = IntStream.rangeClosed(1, 9).boxed().filter(i -> !filledList.contains(i)).filter(j -> !candidates.contains(j)).count() <= 0;
		if(!possible) {
			return false;
		}
		//*/
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
			if (content > 0 && isContentUnique(caze, null)) {
				continue;
			}else{
				return false;
			}
		}
		
		return true;
	}
	
	protected void removeCandidate(Case filled, PlaySequence playSequence){
		PositionIndexEnum index = filled.getPosition().getIndex();
		for(PositionIndexEnum positionIndexEnum: PositionIndexEnum.values()){
			Case caze = cases.get(positionIndexEnum);
			
			if (index == caze.getPosition().getIndex()) {
				continue;
			}
			
			if(caze.hasCandidate(filled.getContent())){
				caze.removeCandidate(filled.getContent(), playSequence);
			}
		}
		Stream.of(groupLine).forEach(line -> grid.getGroup(line).removeCandidateInGroupLine(filled, playSequence));
		Stream.of(groupColumn).forEach(column -> grid.getGroup(column).removeCandidateInGroupColumn(filled, playSequence));
	}
	
	protected void resetContent(){
		for(Case caze : cases.values()){
			caze.resetContent();
		}
	}
	
	
	public Group fillCase(PositionIndexEnum positionIndex, int content){
		cases.get(positionIndex).fillContent(content);
		return this;
	}
	
	public Group fixCase(PositionIndexEnum positionIndex, int content){
		cases.get(positionIndex).fix(content);
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

	protected Map<PositionIndexEnum, Case> getCases() {
		return cases;
	}
	public  Case getCase(PositionIndexEnum index) {
		return cases.get(index);
	}
	protected Map<Integer, Object> checkCandidate(Map<Integer, Object> result){
		Case candidate = (Case) result.get(1);
		boolean gridFilled = true;
		for(PositionIndexEnum positionIndexEnum: PositionIndexEnum.values()){
			Case caze = cases.get(positionIndexEnum);
			
			if(caze.getState() == StateCaseEnum.FILLED || caze.getState() == StateCaseEnum.FIXED){
				gridFilled = gridFilled & true;
				continue;
			}
			if((candidate.getState() == StateCaseEnum.FILLED || candidate.getState() == StateCaseEnum.FIXED) || (caze.sizeOfCandidate() < candidate.sizeOfCandidate())){
				candidate = caze;
			}
			gridFilled = gridFilled & (caze.sizeOfCandidate() == 0);
		}
		result.put(0, gridFilled);
		result.put(1, candidate);
		return result;
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
	
	protected Case random() {
		Random randomizer = new Random();		
		
		int i = randomizer.nextInt(cases.size());
		PositionIndexEnum index = PositionIndexEnum.fromIndex(i);
		Case caze = cases.get(index);
		
		return caze;
	}

	protected Case isNumberExists(Integer content) {
		if(content == null){
			return null;
		}
		for(Case caze : cases.values()){
			if(content.equals(caze.getContent())){
				return caze;
			}
		}
		return null;
	}
}

