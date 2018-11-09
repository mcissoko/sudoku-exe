package com.mcissoko.play.sudoku.test;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Random;

import com.mcissoko.game.sudoku.Grid;
import com.mcissoko.game.sudoku.Group;
import com.mcissoko.game.sudoku.GroupIndexEnum;
import com.mcissoko.game.sudoku.PositionIndexEnum;
import com.mcissoko.game.sudoku.Sudoku;

public class SudokuTest1 {

	public static void main(String[] args) {
		test4();
	}
	
	public static void test4(){
		int result = 0;
		while(result == 0){
			System.out.println("============== start ===============");
			Sudoku sudoku = new Sudoku();
			result = sudoku.solution(null);
			try {
				Thread.sleep(000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("============== end ===============");
		}
		System.err.println(result);
	}
	public static void test3(){
		Deque<String> queue = new ArrayDeque<>();
		queue.addFirst("Un");
		queue.addFirst("deux");
		queue.addFirst("trois");
		queue.addFirst("quatre");
		
		System.out.println(queue.pop());
		System.out.println(queue);
		
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
		Grid grille = Sudoku.createNewEmptyGrid();
		Group g1 = grille.getGroup(GroupIndexEnum.INDEX_1)
		.fillBox(PositionIndexEnum.INDEX_1, 5)
		.fillBox(PositionIndexEnum.INDEX_2, 3)
		.fillBox(PositionIndexEnum.INDEX_3, 4)
		
		.fillBox(PositionIndexEnum.INDEX_4, 6)
		.fillBox(PositionIndexEnum.INDEX_5, 7)
		.fillBox(PositionIndexEnum.INDEX_6, 2)
		
		.fillBox(PositionIndexEnum.INDEX_7, 1)
		.fillBox(PositionIndexEnum.INDEX_8, 9)
		.fillBox(PositionIndexEnum.INDEX_9, 8) ;
		
		Group g2 = grille.getGroup(GroupIndexEnum.INDEX_2)
				.fillBox(PositionIndexEnum.INDEX_1, 6)
				.fillBox(PositionIndexEnum.INDEX_2, 7)
				.fillBox(PositionIndexEnum.INDEX_3, 8)
				
				.fillBox(PositionIndexEnum.INDEX_4, 1)
				.fillBox(PositionIndexEnum.INDEX_5, 9)
				.fillBox(PositionIndexEnum.INDEX_6, 5)
				
				.fillBox(PositionIndexEnum.INDEX_7, 3)
				.fillBox(PositionIndexEnum.INDEX_8, 4)
				.fillBox(PositionIndexEnum.INDEX_9, 2) ;
		
		Group g3 = grille.getGroup(GroupIndexEnum.INDEX_3)
				.fillBox(PositionIndexEnum.INDEX_1, 9)
				.fillBox(PositionIndexEnum.INDEX_2, 1)
				.fillBox(PositionIndexEnum.INDEX_3, 2)
				
				.fillBox(PositionIndexEnum.INDEX_4, 3)
				.fillBox(PositionIndexEnum.INDEX_5, 4)
				.fillBox(PositionIndexEnum.INDEX_6, 8)
				
				.fillBox(PositionIndexEnum.INDEX_7, 5)
				.fillBox(PositionIndexEnum.INDEX_8, 6)
				.fillBox(PositionIndexEnum.INDEX_9, 7) ;
		
		Group g4 = grille.getGroup(GroupIndexEnum.INDEX_4)
				.fillBox(PositionIndexEnum.INDEX_1, 8)
				.fillBox(PositionIndexEnum.INDEX_2, 5)
				.fillBox(PositionIndexEnum.INDEX_3, 9)
				
				.fillBox(PositionIndexEnum.INDEX_4, 4)
				.fillBox(PositionIndexEnum.INDEX_5, 2)
				.fillBox(PositionIndexEnum.INDEX_6, 6)
				
				.fillBox(PositionIndexEnum.INDEX_7, 7)
				.fillBox(PositionIndexEnum.INDEX_8, 1)
				.fillBox(PositionIndexEnum.INDEX_9, 3) ;
		
		Group g5 = grille.getGroup(GroupIndexEnum.INDEX_5)
				.fillBox(PositionIndexEnum.INDEX_1, 7)
				.fillBox(PositionIndexEnum.INDEX_2, 6)
				.fillBox(PositionIndexEnum.INDEX_3, 1)
				
				.fillBox(PositionIndexEnum.INDEX_4, 8)
				.fillBox(PositionIndexEnum.INDEX_5, 5)
				.fillBox(PositionIndexEnum.INDEX_6, 3)
				
				.fillBox(PositionIndexEnum.INDEX_7, 9)
				.fillBox(PositionIndexEnum.INDEX_8, 2)
				.fillBox(PositionIndexEnum.INDEX_9, 4) ;
		
		Group g6 = grille.getGroup(GroupIndexEnum.INDEX_6)
				.fillBox(PositionIndexEnum.INDEX_1, 4)
				.fillBox(PositionIndexEnum.INDEX_2, 2)
				.fillBox(PositionIndexEnum.INDEX_3, 3)
				
				.fillBox(PositionIndexEnum.INDEX_4, 7)
				.fillBox(PositionIndexEnum.INDEX_5, 9)
				.fillBox(PositionIndexEnum.INDEX_6, 1)
				
				.fillBox(PositionIndexEnum.INDEX_7, 8)
				.fillBox(PositionIndexEnum.INDEX_8, 5)
				.fillBox(PositionIndexEnum.INDEX_9, 6) ;		
		
		Group g7 = grille.getGroup(GroupIndexEnum.INDEX_7)
				.fillBox(PositionIndexEnum.INDEX_1, 9)
				.fillBox(PositionIndexEnum.INDEX_2, 6)
				.fillBox(PositionIndexEnum.INDEX_3, 1)
				
				.fillBox(PositionIndexEnum.INDEX_4, 2)
				.fillBox(PositionIndexEnum.INDEX_5, 8)
				.fillBox(PositionIndexEnum.INDEX_6, 7)
				
				.fillBox(PositionIndexEnum.INDEX_7, 3)
				.fillBox(PositionIndexEnum.INDEX_8, 4)
				.fillBox(PositionIndexEnum.INDEX_9, 5)
				;
		
		Group g8 = grille.getGroup(GroupIndexEnum.INDEX_8)
				.fillBox(PositionIndexEnum.INDEX_1, 5)
				.fillBox(PositionIndexEnum.INDEX_2, 3)
				.fillBox(PositionIndexEnum.INDEX_3, 7)
				
				.fillBox(PositionIndexEnum.INDEX_4, 4)
				.fillBox(PositionIndexEnum.INDEX_5, 1)
				.fillBox(PositionIndexEnum.INDEX_6, 9)
				
				.fillBox(PositionIndexEnum.INDEX_7, 2)
				.fillBox(PositionIndexEnum.INDEX_8, 8)
				.fillBox(PositionIndexEnum.INDEX_9, 6) ;
		
		Group g9 = grille.getGroup(GroupIndexEnum.INDEX_9)
				.fillBox(PositionIndexEnum.INDEX_1, 2)
				.fillBox(PositionIndexEnum.INDEX_2, 8)
				.fillBox(PositionIndexEnum.INDEX_3, 4)
				
				.fillBox(PositionIndexEnum.INDEX_4, 6)
				.fillBox(PositionIndexEnum.INDEX_5, 3)
				.fillBox(PositionIndexEnum.INDEX_6, 5)
				
				.fillBox(PositionIndexEnum.INDEX_7, 1)
				.fillBox(PositionIndexEnum.INDEX_8, 7)
				.fillBox(PositionIndexEnum.INDEX_9, 9) ;
		
		/*
		for(int i = 1 ; i <= 9 ; i++){
			System.out.println(grille.getGroupement(GroupIndexEnum.INDEX_i).toString() + "\n");			
		}
		//*/
		//boolean is = g3.isSudoku(grille);
		System.out.println(grille);
		boolean is = grille.isSudoku();
		System.out.println(is);
		//grille.resetContent();
		System.out.println(grille);
		
		is = grille.isSudoku();
		
		System.out.println(is);
		
		int i = 0;
		//*
		
		
		//*/
	}

}
