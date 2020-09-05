package com.citi.game;

import java.util.HashSet;
import java.util.Set;

public class Main {

	public static void main(String[] args) {

		Set<Cell> liveCells = init();
		
		Game game = new Game();
		
		game.play(liveCells);
	}
	
	private static Set<Cell> init() {
		
		Set<Cell> liveCells = new HashSet<>();
		
		liveCells.add(new Cell(1, 3));
		liveCells.add(new Cell(1, 2));
		
		liveCells.add(new Cell(3, 3));
		liveCells.add(new Cell(3, 2));
		
		liveCells.add(new Cell(2, 1));
		
		return liveCells;
	}
}
