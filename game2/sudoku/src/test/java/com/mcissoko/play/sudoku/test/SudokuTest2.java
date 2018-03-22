package com.mcissoko.play.sudoku.test;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Random;

import com.mcissoko.play.sudoku.Grille;
import com.mcissoko.play.sudoku.Group;
import com.mcissoko.play.sudoku.GroupIndexEnum;
import com.mcissoko.play.sudoku.PositionIndexEnum;
import com.mcissoko.play.sudoku.Sudoku;

public class SudokuTest2 {

	public static void main(String[] args) {
		test5();
	}

	public static void test5(){
	//*
		Grille grille = Sudoku.createNewEmptyGrid();
//		Group g1 = grille.getGroup(GroupIndexEnum.INDEX_1)
//		.fixCase(PositionIndexEnum.INDEX_1, 5)
//		.fixCase(PositionIndexEnum.INDEX_2, 3)
//		.fixCase(PositionIndexEnum.INDEX_3, 4)
//		
//		.fixCase(PositionIndexEnum.INDEX_4, 6)
//		.fixCase(PositionIndexEnum.INDEX_5, 7)
//		.fixCase(PositionIndexEnum.INDEX_6, 2)
//		
//		.fixCase(PositionIndexEnum.INDEX_7, 1)
//		.fixCase(PositionIndexEnum.INDEX_8, 9)
//		.fixCase(PositionIndexEnum.INDEX_9, 8) ;
		
		Group g2 = grille.getGroup(GroupIndexEnum.INDEX_2)
				.fixCase(PositionIndexEnum.INDEX_1, 6)
				.fixCase(PositionIndexEnum.INDEX_2, 7)
				.fixCase(PositionIndexEnum.INDEX_3, 8)
				
				.fixCase(PositionIndexEnum.INDEX_4, 1)
				.fixCase(PositionIndexEnum.INDEX_5, 9)
				.fixCase(PositionIndexEnum.INDEX_6, 5)
				
				.fixCase(PositionIndexEnum.INDEX_7, 3)
				.fixCase(PositionIndexEnum.INDEX_8, 4)
				.fixCase(PositionIndexEnum.INDEX_9, 2) ;
		
		Group g3 = grille.getGroup(GroupIndexEnum.INDEX_3)
				.fixCase(PositionIndexEnum.INDEX_1, 9)
				.fixCase(PositionIndexEnum.INDEX_2, 1)
				.fixCase(PositionIndexEnum.INDEX_3, 2)
				
				.fixCase(PositionIndexEnum.INDEX_4, 3)
				.fixCase(PositionIndexEnum.INDEX_5, 4)
				.fixCase(PositionIndexEnum.INDEX_6, 8)
				
				.fixCase(PositionIndexEnum.INDEX_7, 5)
				.fixCase(PositionIndexEnum.INDEX_8, 6)
				.fixCase(PositionIndexEnum.INDEX_9, 7) ;
		
//		Group g4 = grille.getGroup(GroupIndexEnum.INDEX_4)
//				.fixCase(PositionIndexEnum.INDEX_1, 8)
//				.fixCase(PositionIndexEnum.INDEX_2, 5)
//				.fixCase(PositionIndexEnum.INDEX_3, 9)
//				
//				.fixCase(PositionIndexEnum.INDEX_4, 4)
//				.fixCase(PositionIndexEnum.INDEX_5, 2)
//				.fixCase(PositionIndexEnum.INDEX_6, 6)
//				
//				.fixCase(PositionIndexEnum.INDEX_7, 7)
//				.fixCase(PositionIndexEnum.INDEX_8, 1)
//				.fixCase(PositionIndexEnum.INDEX_9, 3) ;
		
		Group g5 = grille.getGroup(GroupIndexEnum.INDEX_5)
				.fixCase(PositionIndexEnum.INDEX_1, 7)
				.fixCase(PositionIndexEnum.INDEX_2, 6)
				.fixCase(PositionIndexEnum.INDEX_3, 1)
				
				.fixCase(PositionIndexEnum.INDEX_4, 8)
				.fixCase(PositionIndexEnum.INDEX_5, 5)
				.fixCase(PositionIndexEnum.INDEX_6, 3)
				
				.fixCase(PositionIndexEnum.INDEX_7, 9)
				.fixCase(PositionIndexEnum.INDEX_8, 2)
				.fixCase(PositionIndexEnum.INDEX_9, 4) ;
		
		Group g6 = grille.getGroup(GroupIndexEnum.INDEX_6)
				.fixCase(PositionIndexEnum.INDEX_1, 4)
				.fixCase(PositionIndexEnum.INDEX_2, 2)
				.fixCase(PositionIndexEnum.INDEX_3, 3)
				
				.fixCase(PositionIndexEnum.INDEX_4, 7)
				.fixCase(PositionIndexEnum.INDEX_5, 9)
				.fixCase(PositionIndexEnum.INDEX_6, 1)
				
				.fixCase(PositionIndexEnum.INDEX_7, 8)
				.fixCase(PositionIndexEnum.INDEX_8, 5)
				.fixCase(PositionIndexEnum.INDEX_9, 6) ;		
		
//		Group g7 = grille.getGroup(GroupIndexEnum.INDEX_7)
//				.fixCase(PositionIndexEnum.INDEX_1, 9)
//				.fixCase(PositionIndexEnum.INDEX_2, 6)
//				.fixCase(PositionIndexEnum.INDEX_3, 1)
//				
//				.fixCase(PositionIndexEnum.INDEX_4, 2)
//				.fixCase(PositionIndexEnum.INDEX_5, 8)
//				.fixCase(PositionIndexEnum.INDEX_6, 7)
//				
//				.fixCase(PositionIndexEnum.INDEX_7, 3)
//				.fixCase(PositionIndexEnum.INDEX_8, 4)
//				.fixCase(PositionIndexEnum.INDEX_9, 5)
//				;
//		
		Group g8 = grille.getGroup(GroupIndexEnum.INDEX_8)
				.fixCase(PositionIndexEnum.INDEX_1, 5)
				.fixCase(PositionIndexEnum.INDEX_2, 3)
				.fixCase(PositionIndexEnum.INDEX_3, 7)
				
				.fixCase(PositionIndexEnum.INDEX_4, 4)
				.fixCase(PositionIndexEnum.INDEX_5, 1)
				.fixCase(PositionIndexEnum.INDEX_6, 9)
				
				.fixCase(PositionIndexEnum.INDEX_7, 2)
				.fixCase(PositionIndexEnum.INDEX_8, 8)
				.fixCase(PositionIndexEnum.INDEX_9, 6) ;
		
		Group g9 = grille.getGroup(GroupIndexEnum.INDEX_9)
				.fixCase(PositionIndexEnum.INDEX_1, 2)
				.fixCase(PositionIndexEnum.INDEX_2, 8)
				.fixCase(PositionIndexEnum.INDEX_3, 4)
				
				.fixCase(PositionIndexEnum.INDEX_4, 6)
				.fixCase(PositionIndexEnum.INDEX_5, 3)
				.fixCase(PositionIndexEnum.INDEX_6, 5)
				
				.fixCase(PositionIndexEnum.INDEX_7, 1)
				.fixCase(PositionIndexEnum.INDEX_8, 7)
				.fixCase(PositionIndexEnum.INDEX_9, 9) ;
		
		
		System.out.println(grille);
		Sudoku sudoku = new Sudoku(grille);
		int result = sudoku.solution(null);
		System.out.println(result);
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
