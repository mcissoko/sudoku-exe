package com.mcissoko.play.data;

import com.mcissoko.play.sudoku.GroupIndexEnum;
import com.mcissoko.play.sudoku.PositionIndexEnum;

public class IndexBox {

	private GroupIndexEnum groupIndexEnum;
	private PositionIndexEnum positionIndexEnum;
	public IndexBox(GroupIndexEnum groupIndexEnum, PositionIndexEnum positionIndexEnum) {
		super();
		this.groupIndexEnum = groupIndexEnum;
		this.positionIndexEnum = positionIndexEnum;
	}
	public IndexBox() {
		// TODO Auto-generated constructor stub
	}
	public GroupIndexEnum getGroupIndexEnum() {
		return groupIndexEnum;
	}
	public void setGroupIndexEnum(GroupIndexEnum groupIndexEnum) {
		this.groupIndexEnum = groupIndexEnum;
	}
	public PositionIndexEnum getPositionIndexEnum() {
		return positionIndexEnum;
	}
	public void setPositionIndexEnum(PositionIndexEnum positionIndexEnum) {
		this.positionIndexEnum = positionIndexEnum;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((groupIndexEnum == null) ? 0 : groupIndexEnum.hashCode());
		result = prime * result + ((positionIndexEnum == null) ? 0 : positionIndexEnum.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		IndexBox other = (IndexBox) obj;
		if (groupIndexEnum != other.groupIndexEnum)
			return false;
		if (positionIndexEnum != other.positionIndexEnum)
			return false;
		return true;
	};
	
	
}
