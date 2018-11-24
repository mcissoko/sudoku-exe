package com.mcissoko.play.sudoku.test;

import java.util.concurrent.CompletableFuture;

import com.mcissoko.game.sudoku.client.Sudoku;
import com.mcissoko.game.sudoku.core.Grid;
import com.mcissoko.game.sudoku.core.Group;
import com.mcissoko.game.sudoku.core.IPromise;
import com.mcissoko.game.sudoku.core.PositionIndexEnum;
import com.mcissoko.game.sudoku.core.SudokuFactory;
import com.mcissoko.game.sudoku.core.enumeration.GroupIndexEnum;
import com.mcissoko.game.sudoku.core.enumeration.SudokuLevelEnum;

public class SudokuTest2 {

	public static void main(String[] args) {
		test6();
	}

	@SuppressWarnings("unused")
	private static void test8() {
		String name = "bbb";
//		CompletableFuture.supplyAsync(() -> {
//		      if (name == null) {
//		          throw new RuntimeException("Computation error!");
//		      }
//		      return "Hello, " + name;
//		  }).exceptionally((overcooked) -> {
//		       
//		  }).thenAccept(x -> System.out.println(x));
//		 
	}

	private static void test7() {

		CompletableFuture.supplyAsync(() -> 5)
		.thenApply(i -> i * 3)
		.thenAccept(j -> System.out.println("The result is " + j))
		.thenRun(() -> System.out.println("Finished."));
	}
	static Grid grid = null;

	private static void test6() {
		
		Sudoku sudoku = SudokuFactory.create();
		 IPromise promise = sudoku.newGrid(SudokuLevelEnum.EASY);
				promise.done(g -> { 
			grid = g;
			System.out.println("new grid \n"+g);
			
		});

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		promise = sudoku.resolve(grid, null);
		promise.done(done -> {
			System.out.println("solved :\n"+done);
		});
		

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	public static void test5(){
	//*
		Grid grille = null; //Sudoku.createNewEmptyGrid();
//		Group g1 = grille.getGroup(GroupIndexEnum.INDEX_1)
//		.fixBox(PositionIndexEnum.INDEX_1, 5)
//		.fixBox(PositionIndexEnum.INDEX_2, 3)
//		.fixBox(PositionIndexEnum.INDEX_3, 4)
//		
//		.fixBox(PositionIndexEnum.INDEX_4, 6)
//		.fixBox(PositionIndexEnum.INDEX_5, 7)
//		.fixBox(PositionIndexEnum.INDEX_6, 2)
//		
//		.fixBox(PositionIndexEnum.INDEX_7, 1)
//		.fixBox(PositionIndexEnum.INDEX_8, 9)
//		.fixBox(PositionIndexEnum.INDEX_9, 8) ;
		
		Group g2 = grille.getGroup(GroupIndexEnum.INDEX_2)
				.fixBox(PositionIndexEnum.INDEX_1, 6)
				.fixBox(PositionIndexEnum.INDEX_2, 7)
				.fixBox(PositionIndexEnum.INDEX_3, 8)
				
				.fixBox(PositionIndexEnum.INDEX_4, 1)
				.fixBox(PositionIndexEnum.INDEX_5, 9)
				.fixBox(PositionIndexEnum.INDEX_6, 5)
				
				.fixBox(PositionIndexEnum.INDEX_7, 3)
				.fixBox(PositionIndexEnum.INDEX_8, 4)
				.fixBox(PositionIndexEnum.INDEX_9, 2) ;
		
		Group g3 = grille.getGroup(GroupIndexEnum.INDEX_3)
				.fixBox(PositionIndexEnum.INDEX_1, 9)
				.fixBox(PositionIndexEnum.INDEX_2, 1)
				.fixBox(PositionIndexEnum.INDEX_3, 2)
				
				.fixBox(PositionIndexEnum.INDEX_4, 3)
				.fixBox(PositionIndexEnum.INDEX_5, 4)
				.fixBox(PositionIndexEnum.INDEX_6, 8)
				
				.fixBox(PositionIndexEnum.INDEX_7, 5)
				.fixBox(PositionIndexEnum.INDEX_8, 6)
				.fixBox(PositionIndexEnum.INDEX_9, 7) ;
		
//		Group g4 = grille.getGroup(GroupIndexEnum.INDEX_4)
//				.fixBox(PositionIndexEnum.INDEX_1, 8)
//				.fixBox(PositionIndexEnum.INDEX_2, 5)
//				.fixBox(PositionIndexEnum.INDEX_3, 9)
//				
//				.fixBox(PositionIndexEnum.INDEX_4, 4)
//				.fixBox(PositionIndexEnum.INDEX_5, 2)
//				.fixBox(PositionIndexEnum.INDEX_6, 6)
//				
//				.fixBox(PositionIndexEnum.INDEX_7, 7)
//				.fixBox(PositionIndexEnum.INDEX_8, 1)
//				.fixBox(PositionIndexEnum.INDEX_9, 3) ;
		
		Group g5 = grille.getGroup(GroupIndexEnum.INDEX_5)
				.fixBox(PositionIndexEnum.INDEX_1, 7)
				.fixBox(PositionIndexEnum.INDEX_2, 6)
				.fixBox(PositionIndexEnum.INDEX_3, 1)
				
				.fixBox(PositionIndexEnum.INDEX_4, 8)
				.fixBox(PositionIndexEnum.INDEX_5, 5)
				.fixBox(PositionIndexEnum.INDEX_6, 3)
				
				.fixBox(PositionIndexEnum.INDEX_7, 9)
				.fixBox(PositionIndexEnum.INDEX_8, 2)
				.fixBox(PositionIndexEnum.INDEX_9, 4) ;
		
		Group g6 = grille.getGroup(GroupIndexEnum.INDEX_6)
				.fixBox(PositionIndexEnum.INDEX_1, 4)
				.fixBox(PositionIndexEnum.INDEX_2, 2)
				.fixBox(PositionIndexEnum.INDEX_3, 3)
				
				.fixBox(PositionIndexEnum.INDEX_4, 7)
				.fixBox(PositionIndexEnum.INDEX_5, 9)
				.fixBox(PositionIndexEnum.INDEX_6, 1)
				
				.fixBox(PositionIndexEnum.INDEX_7, 8)
				.fixBox(PositionIndexEnum.INDEX_8, 5)
				.fixBox(PositionIndexEnum.INDEX_9, 6) ;		
		
//		Group g7 = grille.getGroup(GroupIndexEnum.INDEX_7)
//				.fixBox(PositionIndexEnum.INDEX_1, 9)
//				.fixBox(PositionIndexEnum.INDEX_2, 6)
//				.fixBox(PositionIndexEnum.INDEX_3, 1)
//				
//				.fixBox(PositionIndexEnum.INDEX_4, 2)
//				.fixBox(PositionIndexEnum.INDEX_5, 8)
//				.fixBox(PositionIndexEnum.INDEX_6, 7)
//				
//				.fixBox(PositionIndexEnum.INDEX_7, 3)
//				.fixBox(PositionIndexEnum.INDEX_8, 4)
//				.fixBox(PositionIndexEnum.INDEX_9, 5)
//				;
//		
		Group g8 = grille.getGroup(GroupIndexEnum.INDEX_8)
				.fixBox(PositionIndexEnum.INDEX_1, 5)
				.fixBox(PositionIndexEnum.INDEX_2, 3)
				.fixBox(PositionIndexEnum.INDEX_3, 7)
				
				.fixBox(PositionIndexEnum.INDEX_4, 4)
				.fixBox(PositionIndexEnum.INDEX_5, 1)
				.fixBox(PositionIndexEnum.INDEX_6, 9)
				
				.fixBox(PositionIndexEnum.INDEX_7, 2)
				.fixBox(PositionIndexEnum.INDEX_8, 8)
				.fixBox(PositionIndexEnum.INDEX_9, 6) ;
		
		Group g9 = grille.getGroup(GroupIndexEnum.INDEX_9)
				.fixBox(PositionIndexEnum.INDEX_1, 2)
				.fixBox(PositionIndexEnum.INDEX_2, 8)
				.fixBox(PositionIndexEnum.INDEX_3, 4)
				
				.fixBox(PositionIndexEnum.INDEX_4, 6)
				.fixBox(PositionIndexEnum.INDEX_5, 3)
				.fixBox(PositionIndexEnum.INDEX_6, 5)
				
				.fixBox(PositionIndexEnum.INDEX_7, 1)
				.fixBox(PositionIndexEnum.INDEX_8, 7)
				.fixBox(PositionIndexEnum.INDEX_9, 9) ;
		
		
		System.out.println(grille);
//		Sudoku sudoku = new Sudoku(grille);
//		int result = sudoku.solution(null);
//		System.out.println(result);
		/*
		boolean is = grille.isSudoku();
		System.out.println(is);
		grille.resetContent();
		System.out.println(grille);
		
		is = grille.isSudoku();
		
		System.out.println(is);
		
		int i = 0;
		//*/
		//*
		
		
		//*/
	}
	
}
