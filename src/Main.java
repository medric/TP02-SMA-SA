import java.util.ArrayList;

import core.Agent;
import core.Grid;
import core.Inbox;

/**
 * 
 */

/**
 * @author medric
 *
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// Init grid
		Grid grid = new Grid(5);
		
		Agent circle = new Agent("cirle");
		Agent star = new Agent("star");
		Agent triangle = new Agent("triangle");
		Agent square = new Agent("square");
		Agent diamond = new Agent("diamond");
		
		// Set agents
		ArrayList<Agent> agents = new ArrayList<Agent>();
		
		agents.add(circle);
		agents.add(star);
		agents.add(triangle);
		agents.add(square);
		agents.add(diamond);
		
		grid.placeAgents(agents);
		
		// Set targets (squares)
		circle.setTargetedSquare(grid.getSquaresSet().get(0));
		star.setTargetedSquare(grid.getSquaresSet().get(1));
		triangle.setTargetedSquare(grid.getSquaresSet().get(2));
		square.setTargetedSquare(grid.getSquaresSet().get(3));
		diamond.setTargetedSquare(grid.getSquaresSet().get(4));
		
		Inbox inbox = new Inbox();
		
	}

}
