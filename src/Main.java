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
		
		Agent circle = new Agent("circle");
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
		
		Inbox inbox = new Inbox();
		
		// Init agents
		for(Agent agent : agents) {
			inbox.addAgent(agent);
			agent.setInbox(inbox);
			agent.setGrid(grid);
		}
		
		//grid.placeAgents(agents);
		
		circle.setCurrentSquare(grid.getSquaresSet().get(2));
		star.setCurrentSquare(grid.getSquaresSet().get(4));
		triangle.setCurrentSquare(grid.getSquaresSet().get(3));
		square.setCurrentSquare(grid.getSquaresSet().get(8));
		diamond.setCurrentSquare(grid.getSquaresSet().get(7));
		
		// Set targets (squares)
		circle.setTargetedSquare(grid.getSquaresSet().get(0));
		star.setTargetedSquare(grid.getSquaresSet().get(1));
		triangle.setTargetedSquare(grid.getSquaresSet().get(2));
		square.setTargetedSquare(grid.getSquaresSet().get(3));
		diamond.setTargetedSquare(grid.getSquaresSet().get(4));
		
		// Run thread for each agent
		for(Agent agent : agents) {
			Thread t = new Thread(agent);
			t.start();
		}
	}
}
