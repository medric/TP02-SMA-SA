package core;
import java.util.ArrayList;
import java.util.Random;

import view.GridView;

/**
 * @author medric
 *
 */
public class Grid {
	private ArrayList<ArrayList<Square>> squares;
	private GridView gridView;
	private int size;
	private Random randomGenerator;
	
	public Grid(int gridSize) {
		this.squares = new ArrayList<ArrayList<Square>>();
	
		this.setSize(gridSize);
		this.initGrid(gridSize);
		this.setGridView(new GridView(this));	
		
		randomGenerator = new Random();
	}
	
	/**
	 * 
	 * @param gridSize
	 */
	private void initGrid(int gridSize) {
		for(int y = 0; y < gridSize; y++) {
			ArrayList<Square> columns = new ArrayList<Square>(gridSize);
			for(int x = 0; x < gridSize; x++) {
				Square square = new Square(new Position(x, y));
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
		int cursor = 0;
		for(Agent agent : agents) {
			Square randomSquare = this.squares.get(randomGenerator.nextInt(this.squares.size())).get(randomGenerator.nextInt(this.squares.size()));
			
			while(this.isSquareFree(randomSquare) && cursor < this.squares.size() * this.squares.size()) {
				randomSquare = this.squares.get(randomGenerator.nextInt(this.squares.size())).get(randomGenerator.nextInt(this.squares.size()));
				cursor++;
			}
			
			if(this.isSquareFree(randomSquare)) {
				randomSquare.setAgent(agent);
				agent.setCurrentSquare(randomSquare);
				
				Square target = this.squares.get(randomGenerator.nextInt(this.squares.size())).get(randomGenerator.nextInt(this.squares.size()));
				agent.setTargetedSquare(target);
			}
		}
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
			neighbors.add(this.getSquares().get(square.getPosition().getY()).get(square.getPosition().getX() + 1));
		} 
		
		// left neighbor
		if(square.getPosition().getX() > 0) {
			neighbors.add(this.getSquares().get(square.getPosition().getY()).get(square.getPosition().getX() - 1));
		}
		
		// bottom neighbor
		if(square.getPosition().getY() < gridWidth - 1) {	
			neighbors.add(this.getSquares().get(square.getPosition().getY() + 1).get(square.getPosition().getX()));
		} 
		
		// top neighbor
		if(square.getPosition().getY() > 0) {	
			neighbors.add(this.getSquares().get(square.getPosition().getY() - 1).get(square.getPosition().getX()));
		} 
		
		return neighbors;
	}
	
	/**
	 * Get neighbors that have no agent on
	 * @param square
	 * @return
	 */
	public ArrayList<Square> getFreeNeighbors(Square square) {
		ArrayList<Square> freeNeighbors = new ArrayList<Square>();
		
		for(Square neighbor : this.getNeighbors(square)) {
			if(this.isSquareFree(square)) {
				freeNeighbors.add(neighbor);
			}
		}
		
		return freeNeighbors;
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
		return square.getAgent() == null ;
	}
	
	/**
	 * @return the cases
	 */
	public ArrayList<ArrayList<Square>> getSquares() {
		return squares;
	}

	/**
	 * @param squares the squares to set
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
				if(!isSquareFree(square)) {
					output += square.getAgent().getName();
				} else {
					output += "free";
				}
				System.out.println(output);
			}
		}
		
		System.out.println("________________");
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
