package core;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
	
}
