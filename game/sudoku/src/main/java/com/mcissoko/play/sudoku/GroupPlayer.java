package com.mcissoko.play.sudoku;

import com.mcissoko.play.sudoku.player.Grille;

public class GroupPlayer extends Thread{

	private static final Object lock = new Object();
	
	public static int number;
	
	private Grille grille;
	private Group grouping;
	
	
	
	
	public GroupPlayer(Grille grille, GroupIndexEnum groupIndexEnum) {
		this.grille = grille;
		this.grouping = grille.getGroup(groupIndexEnum);
		
	}
	
	

//	public void run() {
//		synchronized (lock) {
//			while (!grouping.isSudoku(grille)) {
//				System.out.println(this.grouping.getIndex() + " -->" + this.getId());
//				//do process
//				
//				if(number == 0){
//					System.out.println("Terminate " + " ==>" + this.grouping.getIndex() + " -->" + this.getId());
//					System.out.println(grille);
//					return;
//				}
//				boolean placed = grouping.numberPlacement(number, grille);
//				if(placed){
//					System.out.println("Placed " + number + " ==>" + this.grouping.getIndex() + " -->" + this.getId());
//					System.out.println(grille);
//				}
//				lock.notifyAll();
//				try {
//					lock.wait();
//				} catch (InterruptedException e) {
//				}
//			}
//		}
//	}
	
	

	
	
	

}
