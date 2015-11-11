package core;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * 
 */

/**
 * @author medric
 *
 */
public class Grid {
	private HashMap<Square, Agent> squares;
	
	public Grid(int gridSize) {
		this.squares = new HashMap<Square, Agent>();
		
		this.initGrid(gridSize);
	}
	
	/**
	 * 
	 * @param gridSize
	 */
	private void initGrid(int gridSize) {
		for(int row = 0; row < gridSize; row++) {
			for(int column = 0; column < gridSize; column++) {
				Square square = new Square(new Position(row, column));
				
				this.squares.put(square, null);
			}
		}
	}
	
	/**
	 * 
	 * @param agents
	 */
	public void placeAgents(ArrayList<Agent> agents) {		
		Random random = new Random();
		
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
		}
	}
	
	/**
	 * Get square neighbors
	 * @param square
	 * @return
	 */
	public ArrayList<Square> getNeighbors(Square square) {
		ArrayList<Square> neighbors = new ArrayList<Square>();
		int index = this.getSquaresSet().indexOf(square) - 1;
		int gridSize = this.getSquares().size();
		int gridWidth = (int)Math.sqrt(gridSize);
		
		// right neighbor
		if(square.getPosition().getX() < gridWidth - 1) {
			neighbors.add(this.getSquaresSet().get(index + 1));
		} 
		
		// left neighbor
		if(square.getPosition().getX() > 0) {
			neighbors.add(this.getSquaresSet().get(index - 1));
		}
		
		// bottom neighbor
		if(/*square.getPosition().getX() == 0 &&*/ square.getPosition().getY() < gridWidth - 1) {	
			int target = index + gridWidth;
			neighbors.add(this.getSquaresSet().get(target));
		} 
		
		// top neighbor
		if(square.getPosition().getY() > 0) {	
			int target = index - gridWidth;
			neighbors.add(this.getSquaresSet().get(target));
		} 
		
		return neighbors;
	}
	
	/**
	 * 
	 * @param square
	 * @return
	 */
	public boolean isSquareFree(Square square) {
		return this.getSquares().get(square) == null;
	}
	
	/**
	 * 
	 * @return
	 */
	public ArrayList<Square> getSquaresSet() {
		return new ArrayList<Square>(this.squares.keySet());
	}

	/**
	 * @return the cases
	 */
	public HashMap<Square, Agent> getSquares() {
		return squares;
	}

	/**
	 * @param cases the cases to set
	 */
	public void setSquares(HashMap<Square, Agent> squares) {
		this.squares = squares;
	}
	
	/**
	 * Render grid
	 */
	public void render() {
		for (Map.Entry<Square, Agent> entry : this.getSquares().entrySet()) {
			String output = "(" + entry.getKey().getPosition().getX() + "-";
			output += entry.getKey().getPosition().getY() + ") :";
			if(entry.getValue() != null) {
				output += entry.getValue().getName();
			} else {
				output += "free";
			}
			System.out.println(output);
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
}
