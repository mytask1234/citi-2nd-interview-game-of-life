package com.citi.game;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Game {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(Game.class);

	public Game() {

	}

	public void play(Set<Cell> liveCells) {

		Set<Cell> deadNeighbors = new HashSet<Cell>();
		
		while (liveCells.size() > 0) {
			
			LOGGER.debug("liveCells.size()={}, liveCells={}", liveCells.size(), liveCells);

			liveCells = nextGen(liveCells, deadNeighbors);
		}
		
		LOGGER.debug("liveCells.size()={}, liveCells={}", liveCells.size(), liveCells);
	}

	private Set<Cell> nextGen(Set<Cell> liveCells, Set<Cell> deadNeighbors) {

		Set<Cell> nextGen = new HashSet<Cell>();
		
		for (Cell live : liveCells) {
			
			deadNeighbors.clear();
			
			int liveNeighbors = getLiveNeighbors(live, liveCells, deadNeighbors);
			
			if (liveNeighbors == 2 || liveNeighbors == 3) {
				
				nextGen.add(live);
			}
			
			for (Cell deadNeighbor : deadNeighbors) {
				
				liveNeighbors = getLiveNeighbors(deadNeighbor, liveCells, null);
				
				if (liveNeighbors == 3) {
					nextGen.add(deadNeighbor);
				}
			}
		}
		
		return nextGen;
	}
	
	private int getLiveNeighbors(Cell cell, Set<Cell> liveCells, Set<Cell> deadNeighbors) {
		
		int liveNeighbors = 0;
		
		Set<Cell> neighbors = getNeighbors(cell);
		
		for (Cell neighbor : neighbors) {
			
			if (liveCells.contains(neighbor)) {
				
				++liveNeighbors;
				
			} else if (deadNeighbors != null) {
				
				deadNeighbors.add(neighbor);
			}
		}
		
		return liveNeighbors;
	}

	private Set<Cell> getNeighbors(Cell cell) {

		Set<Cell> neighbors = new HashSet<Cell>();

		int x = cell.getX();
		int y = cell.getY();

		neighbors.add(new Cell(x-1, y+1));
		neighbors.add(new Cell(x, y+1));
		neighbors.add(new Cell(x+1, y+1));

		neighbors.add(new Cell(x+1, y));

		neighbors.add(new Cell(x+1, y-1));
		neighbors.add(new Cell(x, y-1));
		neighbors.add(new Cell(x-1, y-1));

		neighbors.add(new Cell(x-1, y));

		return neighbors;
	}
}
