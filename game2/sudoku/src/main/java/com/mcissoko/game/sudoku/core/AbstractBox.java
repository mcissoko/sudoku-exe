package com.mcissoko.game.sudoku.core;

import java.util.Set;

public abstract class AbstractBox {
	protected Set<Integer> candidates;
	protected StateBoxEnum state;

	protected Set<Integer> triedCandidates;
}
