package com.mcissoko.play.sudoku.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.mcissoko.play.sudoku.Group;
import com.mcissoko.play.sudoku.GroupIndexEnum;
import com.mcissoko.play.sudoku.PositionIndexEnum;
import com.mcissoko.play.sudoku.player.Grille;

public class SudokuTest1 {

	public static void main(String[] args) {
		test1();
	}
	
	public static void test2(){
		List<Integer> numbers = new ArrayList<>();
		for(int i = 1; i <= 9; i++){
			numbers.add(i);
		}
		Random randomizer = new Random();
		while(true){
			
			if(numbers.isEmpty()){
				return;
			}
			Integer random = numbers.get(randomizer.nextInt(numbers.size()));
			numbers.remove(random);
			System.out.println(random);
		}
		
	}
	
	
	public static void test1(){
	//*
		Grille grille = new Grille();
		Group g1 = grille.getGroup(GroupIndexEnum.INDEX_1)
		.fillCase(PositionIndexEnum.INDEX_1, 5)
		.fillCase(PositionIndexEnum.INDEX_2, 3)
		.fillCase(PositionIndexEnum.INDEX_3, 4)
		
		.fillCase(PositionIndexEnum.INDEX_4, 6)
		.fillCase(PositionIndexEnum.INDEX_5, 7)
		.fillCase(PositionIndexEnum.INDEX_6, 2)
		
		.fillCase(PositionIndexEnum.INDEX_7, 1)
		.fillCase(PositionIndexEnum.INDEX_8, 9)
		.fillCase(PositionIndexEnum.INDEX_9, 8) ;
		
		Group g2 = grille.getGroup(GroupIndexEnum.INDEX_2)
				.fillCase(PositionIndexEnum.INDEX_1, 6)
				.fillCase(PositionIndexEnum.INDEX_2, 7)
				.fillCase(PositionIndexEnum.INDEX_3, 8)
				
				.fillCase(PositionIndexEnum.INDEX_4, 1)
				.fillCase(PositionIndexEnum.INDEX_5, 9)
				.fillCase(PositionIndexEnum.INDEX_6, 5)
				
				.fillCase(PositionIndexEnum.INDEX_7, 3)
				.fillCase(PositionIndexEnum.INDEX_8, 4)
				.fillCase(PositionIndexEnum.INDEX_9, 2) ;
		
		Group g3 = grille.getGroup(GroupIndexEnum.INDEX_3)
				.fillCase(PositionIndexEnum.INDEX_1, 9)
				.fillCase(PositionIndexEnum.INDEX_2, 1)
				.fillCase(PositionIndexEnum.INDEX_3, 2)
				
				.fillCase(PositionIndexEnum.INDEX_4, 3)
				.fillCase(PositionIndexEnum.INDEX_5, 4)
				.fillCase(PositionIndexEnum.INDEX_6, 8)
				
				.fillCase(PositionIndexEnum.INDEX_7, 5)
				.fillCase(PositionIndexEnum.INDEX_8, 6)
				.fillCase(PositionIndexEnum.INDEX_9, 7) ;
		
		Group g4 = grille.getGroup(GroupIndexEnum.INDEX_4)
				.fillCase(PositionIndexEnum.INDEX_1, 8)
				.fillCase(PositionIndexEnum.INDEX_2, 5)
				.fillCase(PositionIndexEnum.INDEX_3, 9)
				
				.fillCase(PositionIndexEnum.INDEX_4, 4)
				.fillCase(PositionIndexEnum.INDEX_5, 2)
				.fillCase(PositionIndexEnum.INDEX_6, 6)
				
				.fillCase(PositionIndexEnum.INDEX_7, 7)
				.fillCase(PositionIndexEnum.INDEX_8, 1)
				.fillCase(PositionIndexEnum.INDEX_9, 3) ;
		
		Group g5 = grille.getGroup(GroupIndexEnum.INDEX_5)
				.fillCase(PositionIndexEnum.INDEX_1, 7)
				.fillCase(PositionIndexEnum.INDEX_2, 6)
				.fillCase(PositionIndexEnum.INDEX_3, 1)
				
				.fillCase(PositionIndexEnum.INDEX_4, 8)
				.fillCase(PositionIndexEnum.INDEX_5, 5)
				.fillCase(PositionIndexEnum.INDEX_6, 3)
				
				.fillCase(PositionIndexEnum.INDEX_7, 9)
				.fillCase(PositionIndexEnum.INDEX_8, 2)
				.fillCase(PositionIndexEnum.INDEX_9, 4) ;
		
		Group g6 = grille.getGroup(GroupIndexEnum.INDEX_6)
				.fillCase(PositionIndexEnum.INDEX_1, 4)
				.fillCase(PositionIndexEnum.INDEX_2, 2)
				.fillCase(PositionIndexEnum.INDEX_3, 3)
				
				.fillCase(PositionIndexEnum.INDEX_4, 7)
				.fillCase(PositionIndexEnum.INDEX_5, 9)
				.fillCase(PositionIndexEnum.INDEX_6, 1)
				
				.fillCase(PositionIndexEnum.INDEX_7, 8)
				.fillCase(PositionIndexEnum.INDEX_8, 5)
				.fillCase(PositionIndexEnum.INDEX_9, 6) ;		
		
		Group g7 = grille.getGroup(GroupIndexEnum.INDEX_7)
				.fillCase(PositionIndexEnum.INDEX_1, 9)
				.fillCase(PositionIndexEnum.INDEX_2, 6)
				.fillCase(PositionIndexEnum.INDEX_3, 1)
				
				.fillCase(PositionIndexEnum.INDEX_4, 2)
				.fillCase(PositionIndexEnum.INDEX_5, 8)
				.fillCase(PositionIndexEnum.INDEX_6, 7)
				
				.fillCase(PositionIndexEnum.INDEX_7, 3)
				.fillCase(PositionIndexEnum.INDEX_8, 4)
				.fillCase(PositionIndexEnum.INDEX_9, 5)
				;
		
		Group g8 = grille.getGroup(GroupIndexEnum.INDEX_8)
				.fillCase(PositionIndexEnum.INDEX_1, 5)
				.fillCase(PositionIndexEnum.INDEX_2, 3)
				.fillCase(PositionIndexEnum.INDEX_3, 7)
				
				.fillCase(PositionIndexEnum.INDEX_4, 4)
				.fillCase(PositionIndexEnum.INDEX_5, 1)
				.fillCase(PositionIndexEnum.INDEX_6, 9)
				
				.fillCase(PositionIndexEnum.INDEX_7, 2)
				.fillCase(PositionIndexEnum.INDEX_8, 8)
				.fillCase(PositionIndexEnum.INDEX_9, 6) ;
		
		Group g9 = grille.getGroup(GroupIndexEnum.INDEX_9)
				.fillCase(PositionIndexEnum.INDEX_1, 2)
				.fillCase(PositionIndexEnum.INDEX_2, 8)
				.fillCase(PositionIndexEnum.INDEX_3, 4)
				
				.fillCase(PositionIndexEnum.INDEX_4, 6)
				.fillCase(PositionIndexEnum.INDEX_5, 3)
				.fillCase(PositionIndexEnum.INDEX_6, 5)
				
				.fillCase(PositionIndexEnum.INDEX_7, 1)
				.fillCase(PositionIndexEnum.INDEX_8, 7)
				.fillCase(PositionIndexEnum.INDEX_9, 9) ;
		
		/*
		for(int i = 1 ; i <= 9 ; i++){
			System.out.println(grille.getGroupement(GroupIndexEnum.INDEX_i).toString() + "\n");			
		}
		//*/
		//boolean is = g3.isSudoku(grille);
		System.out.println(grille);
		boolean is = grille.isSudoku();
		System.out.println(is);
		grille.resetContent();
		System.out.println(grille);
		
		is = grille.isSudoku();
		
		System.out.println(is);
		
		int i = 0;
		//*
		
		
		//*/
	}

}
