import java.awt.Color;
import java.util.ArrayList;

import core.Agent;
import core.Grid;
import core.Inbox;
import view.GridView;

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
			agent.addObserver(grid.getGridView());
		}
		
		//grid.placeAgents(agents);
		
		circle.setCurrentSquare(grid.getSquares().get(0).get(0));
		star.setCurrentSquare(grid.getSquares().get(2).get(1));
		triangle.setCurrentSquare(grid.getSquares().get(2).get(2));
		square.setCurrentSquare(grid.getSquares().get(4).get(1));
		diamond.setCurrentSquare(grid.getSquares().get(3).get(1));
		
		circle.setBg(Color.BLACK);
		star.setBg(Color.YELLOW);
		triangle.setBg(Color.CYAN);
		square.setBg(Color.GREEN);
		diamond.setBg(Color.BLUE);
		
		// Set targets (squares)
		circle.setTargetedSquare(grid.getSquares().get(0).get(4));
		star.setTargetedSquare(grid.getSquares().get(3).get(2));
		triangle.setTargetedSquare(grid.getSquares().get(0).get(0));
		square.setTargetedSquare(grid.getSquares().get(4).get(2));
		diamond.setTargetedSquare(grid.getSquares().get(4).get(4));
		
		// Run thread for each agent
		for(Agent agent : agents) {
			Thread t = new Thread(agent);
			t.start();
		}
	}
}
