package com.mcissoko.play.sudoku;

public class Numero extends Thread {

	private int valeur;
	private Coordinate position;
	
	public Numero(int valeur) {
		super();
		this.valeur = valeur;
		this.position = null; 
	}


	@Override
	public void run() {
		
	}


	public int getValeur() {
		return valeur;
	}


	public void setValeur(int valeur) {
		this.valeur = valeur;
	}


	public Coordinate getPosition() {
		return position;
	}


	public void setPosition(Coordinate position) {
		this.position = position;
	}
	
	
}
