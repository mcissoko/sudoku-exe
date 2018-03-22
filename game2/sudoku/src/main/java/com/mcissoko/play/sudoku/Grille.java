package com.mcissoko.play.sudoku;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Grille implements Serializable{

	Logger log = LoggerFactory.getLogger(Grille.class);  

	private static final long serialVersionUID = 4581943640090887910L;
	private Map<GroupIndexEnum, Group> groups;
	private Deque<PlaySequence> playSequence;
	
	Grille() {
		initGroups();
	}
	
	private void initGroups(){
		groups = new HashMap<>();
		playSequence = new ArrayDeque<>();
		for(GroupIndexEnum i: GroupIndexEnum.values()){
			groups.put(i, new Group(i, this));
		}
	}
	
	protected Case random(){
		
		Random randomizer = new Random();
		
		int i = randomizer.nextInt(groups.size());
		GroupIndexEnum index = GroupIndexEnum.fromIndex(i);
		Group group = groups.get(index);
		Case caze = group.random();
		return caze;
	}
	
	protected void addPlaySequence(PlaySequence playSequence){
		this.playSequence.addFirst(playSequence);
	}
	
	private PlaySequence getLastPlaySequence(){
		if(playSequence.isEmpty()){
			return null;
		}
		return this.playSequence.pop();
	}
	
	
	protected Deque<PlaySequence> getPlaySequence() {
		return playSequence;
	}

	protected boolean isNumberInGrilleLine(Case caze, List<Case> occurrences) {
		
		Group group = groups.get(caze.getPosition().getGroupIndex());
		
		GroupIndexEnum[] indexVoisinsLigne = group.getGroupLine();
		
		boolean resultatLigne2 = getGroup(indexVoisinsLigne[0]).isNumberInGroupLine(caze, occurrences);
		boolean resultatLigne3 = getGroup(indexVoisinsLigne[1]).isNumberInGroupLine(caze, occurrences);
		
		boolean ok = !resultatLigne2 && !resultatLigne3; 
		if(!ok){
			ok = false; 
		}
		return ok;
	}
	
	
	protected boolean isNumberInGrilleColumn(Case caze, List<Case> occurrences) {

		Group group = groups.get(caze.getPosition().getGroupIndex());

		GroupIndexEnum[] indexVoisinsColonne = group.getGroupColumn();

		boolean resultatColonne1 = getGroup(indexVoisinsColonne[0]).isNumberInGroupColumn(caze, occurrences);
		boolean resultatColonne2 = getGroup(indexVoisinsColonne[1]).isNumberInGroupColumn(caze, occurrences);

		boolean ok = !resultatColonne1 && !resultatColonne2;
		if (!ok) {
			ok = false;
		}
		return ok;
	}
	
	protected void resetContent(){
		for(Group group: groups.values()){
			group.resetContent();
		}
	}
	
	public boolean isValid(){
		for(Group group: groups.values()){
			if(group.isValid()){
				continue;
			}else{
				return false;
			}
		}
		return true;
	}
	
	public boolean isSudoku(){
		for(Group group: groups.values()){
			if(group.isSudoku()){
				continue;
			}else{
				return false;
			}
		}
		return true;
	}
	
	
	public Group getGroup(GroupIndexEnum groupIndexEnum) {
		return groups.get(groupIndexEnum);
	}
	
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("-----------------------------------------\n");
		
		int startPosition = 1;
		int endPosition = 3;
		
		int startGroup = 1;
		int endGroup = 3;
		
		while (endGroup <= 9) {
				while (endPosition <= 9) {
					for (int i = startGroup; i <= endGroup; i++) {
						Group g = this.getGroup(GroupIndexEnum.fromIndex(i));
						for (int k = startPosition; k <= endPosition; k++) {
							sb.append(g.getCases().get(PositionIndexEnum.fromIndex(k)).print());
						}
						sb.append("+ ");
					}
					sb.append("\n");
					startPosition = startPosition + 3;
					endPosition = endPosition + 3;
				}
				sb.append("-----------------------------------------\n");
				startGroup = startGroup + 3;
				endGroup = endGroup + 3;
				
				startPosition = 1;
				endPosition = 3;
		}
		
		return sb.toString();
	}

	protected Case restaure() {
		PlaySequence last = getLastPlaySequence();
		if(last == null){
			return null;
		}
		Group group = getGroup(last.getGroupIndex());
		Case caze = group.getCase(last.getPositionIndex());
		
		caze.resetContent();
		caze.setCandidates(last.getCandidates());
		for(Sequence seq: last.getSequences()){
			Group g = getGroup(seq.getGroupIndex());
			g.getCase(seq.getPositionIndex()).restaureCandidate(last.getSellectedCandidate());
		}
		
		if(caze.getCandidates().size() == 1){
			log.info("Un seul candidat disponible: {}, on remonte d'un cran", caze);

			caze.resetTried();
			return this.restaure();
		}
		
		return caze;
	}

	public List<Case> checkNumberIsResolved(Integer content) {
		List<Case> lst = new ArrayList<>();
		for(Group group: this.groups.values()){
			Case caze = group.isNumberExists(content);
			if(caze == null){
				return new ArrayList<>();
			}
			lst.add(caze);
		}
		
		return lst;
	}
	
	public Grille copy() {

		Grille copy = new Grille();
		for (GroupIndexEnum groupIndexEnum : GroupIndexEnum.values()) {
			Group group = copy.getGroup(groupIndexEnum);

			for (PositionIndexEnum positionIndexEnum : PositionIndexEnum.values()) {
				Case caze = this.getGroup(groupIndexEnum).getCase(positionIndexEnum);
				if (caze.getState() != StateCaseEnum.EMPTY) {
					group.fixCase(positionIndexEnum, caze.getContent());
				}

			}
		}
		return copy;

	}
	
	
}
