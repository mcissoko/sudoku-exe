package com.mcissoko.play.sudoku.player;

import java.util.HashMap;
import java.util.Map;

import com.mcissoko.play.sudoku.Case;
import com.mcissoko.play.sudoku.GroupIndexEnum;
import com.mcissoko.play.sudoku.PositionIndexEnum;
import com.mcissoko.play.sudoku.Group;

public class Grille {

	private Map<GroupIndexEnum, Group> groups;

	
	public Grille() {
		initGroups();
	}
	
	private void initGroups(){
		groups = new HashMap<>();
		for(GroupIndexEnum i: GroupIndexEnum.values()){
			groups.put(i, new Group(i, this));
		}
	}
	
	public boolean isNumberInGrilleLine(Case caze) {
		
		Group group = groups.get(caze.getPosition().getGroupIndex());
		
		GroupIndexEnum[] indexVoisinsLigne = group.getGroupLine();
		
		boolean resultatLigne2 = getGroup(indexVoisinsLigne[0]).isNumberInGroupLine(caze);
		boolean resultatLigne3 = getGroup(indexVoisinsLigne[1]).isNumberInGroupLine(caze);
		
		boolean ok = !resultatLigne2 && !resultatLigne3; 
		if(!ok){
			ok = false; 
		}
		return ok;
	}
	
	
	public boolean isNumberInGrilleColumn(Case caze) {

		Group group = groups.get(caze.getPosition().getGroupIndex());

		GroupIndexEnum[] indexVoisinsColonne = group.getGroupColumn();

		boolean resultatColonne1 = getGroup(indexVoisinsColonne[0]).isNumberInGroupColumn(caze);
		boolean resultatColonne2 = getGroup(indexVoisinsColonne[1]).isNumberInGroupColumn(caze);

		boolean ok = !resultatColonne1 && !resultatColonne2;
		if (!ok) {
			ok = false;
		}
		return ok;
	}
	
	public void generate(){
		resetContent();
		while(this.isSudoku() == false){
			break;
		}
	}
	
	public void resetContent(){
		for(Group group: groups.values()){
			group.resetContent();
		}
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
	
	
}
