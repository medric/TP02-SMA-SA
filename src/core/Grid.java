package core;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Random;

import view.GridView;

/**
 * 
 */

/**
 * @author medric
 *
 */
public class Grid {
	private ArrayList<ArrayList<Square>> squares;
	private GridView gridView;
	private int size;
	
	public Grid(int gridSize) {
		this.squares = new ArrayList<ArrayList<Square>>();
	
		this.setSize(gridSize);
		this.initGrid(gridSize);
		this.setGridView(new GridView(this));	
	}
	
	/**
	 * 
	 * @param gridSize
	 */
	private void initGrid(int gridSize) {
		for(int row = 0; row < gridSize; row++) {
			ArrayList<Square> columns = new ArrayList<Square>(gridSize);
			for(int column = 0; column < gridSize; column++) {
				Square square = new Square(new Position(row, column));
				square.setAgent(null);
				columns.add(square);
			}
			this.squares.add(columns);
		}
	}
	
	/**
	 * 
	 * @param agents
	 */
	public void placeAgents(ArrayList<Agent> agents) {		
		/*Random random = new Random();
		
		for(Agent agent : agents) {
			int cursor = 0;
			Square randomSquare = this.getSquaresSet().get(random.nextInt(this.squares.size()));
			while(this.squares.get(randomSquare) != null && cursor < this.squares.size()) {
				randomSquare = this.getSquaresSet().get(random.nextInt(this.squares.size()));
				cursor++;
			}
			
			if(this.squares.get(randomSquare) == null) {
				this.squares.put(randomSquare, agent);
				agent.setCurrentSquare(randomSquare);
			}
		}*/
	}
	
	/**
	 * Get square neighbors
	 * @param square
	 * @return
	 */
	public ArrayList<Square> getNeighbors(Square square) {
		ArrayList<Square> neighbors = new ArrayList<Square>();
		
		int gridSize = this.getSquares().size();
		int gridWidth = (int)Math.sqrt(gridSize);
		
		// right neighbor
		if(square.getPosition().getX() < gridWidth - 1) {
			neighbors.add(this.getSquares().get(square.getPosition().getX() + 1).get(square.getPosition().getY()));
		} 
		
		// left neighbor
		if(square.getPosition().getX() > 0) {
			neighbors.add(this.getSquares().get(square.getPosition().getX() - 1).get(square.getPosition().getY()));
		}
		
		// bottom neighbor
		if(square.getPosition().getY() < gridWidth - 1) {	
			neighbors.add(this.getSquares().get(square.getPosition().getX()).get(square.getPosition().getY() + 1));
		} 
		
		// top neighbor
		if(square.getPosition().getY() > 0) {	
			neighbors.add(this.getSquares().get(square.getPosition().getX() + 1).get(square.getPosition().getY() - 1));
		} 
		
		return neighbors;
	}
	
	/**
	 * 
	 * @param position
	 * @return
	 */
	public Square getSquare(Position position) {
		return this.getSquares().get(position.getX()).get(position.getY());
	}
	
	/**
	 * 
	 * @param square
	 * @return
	 */
	public boolean isSquareFree(Square square) {
		return square.getAgent() != null ;
	}
	
	/**
	 * @return the cases
	 */
	public ArrayList<ArrayList<Square>> getSquares() {
		return squares;
	}

	/**
	 * @param cases the cases to set
	 */
	public void setSquares(ArrayList<ArrayList<Square>> squares) {
		this.squares = squares;
	}
	
	/**
	 * Render grid
	 */
	public void render() {
		for (ArrayList<Square> row : this.getSquares()) {
			for(Square square : row) { 
				String output = "(" + square.getPosition().getX() + "-";
				output += square.getPosition().getY() + ") :";
				if(isSquareFree(square)) {
					output += square.getAgent().getName();
				} else {
					output += "free";
				}
				System.out.println(output);
			}
		}
		
		System.out.println("________________");
	}
	
	/**
	 * 
	 * @return
	 */
	public ArrayList<Square> getShortestWay(Square startingSquare, Square targetedSquare) {
		ArrayList<Square> shortestWay = new ArrayList<Square>();
		
		// TODO
		
		return shortestWay;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	/**
	 * @return the gridView
	 */
	public GridView getGridView() {
		return gridView;
	}

	/**
	 * @param gridView the gridView to set
	 */
	public void setGridView(GridView gridView) {
		this.gridView = gridView;
	}
}
