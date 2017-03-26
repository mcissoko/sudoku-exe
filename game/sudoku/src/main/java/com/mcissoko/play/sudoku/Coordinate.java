package com.mcissoko.play.sudoku;

/**
 * @author Doudou
 *
 *	Premiere position = (1,1)
 */
public class Coordinate {

	/**
	 * Debut de ligne = 1 (non 0)
	 * Fin de ligne = 3
	 */
	private int line;
	/**
	 * Debut de colonne = 1 (non 0)
	 * Fin de colonne = 3
	 */
	private int column;
	
	public Coordinate(int line, int colunn) {
		super();
		this.line = line;
		this.column = colunn;
	}

	public int getLine() {
		return line;
	}

	public void setLine(int line) {
		this.line = line;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}
	
	@Override
	public String toString() {
		return line + "|" + column;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + column;
		result = prime * result + line;
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
		Coordinate other = (Coordinate) obj;
		if (column != other.column)
			return false;
		if (line != other.line)
			return false;
		return true;
	}
	
	
}
