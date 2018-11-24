package com.mcissoko.game.sudoku.client;

import com.mcissoko.game.sudoku.core.Grid;
import com.mcissoko.game.sudoku.core.IMonitor;
import com.mcissoko.game.sudoku.core.IPromise;
import com.mcissoko.game.sudoku.core.enumeration.SudokuLevelEnum;

public interface Sudoku {

	IPromise resolve (Grid grid, IMonitor mon);
	IPromise newGrid(SudokuLevelEnum level);
	
	Grid getGrid();

}
