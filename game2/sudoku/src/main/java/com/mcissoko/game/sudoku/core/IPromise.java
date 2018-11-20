package com.mcissoko.game.sudoku.core;

import java.util.function.Consumer;

public interface IPromise {
	
	void done(Consumer<Grid> action);

}
