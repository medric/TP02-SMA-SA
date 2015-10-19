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
	private HashMap<Case, Agent> cases;
	
	public Grid(int it) {
		this.cases = new HashMap<Case, Agent>();

		for(int i = 0; i < it; i++) {
			for(int j = 0; j < it; j++) {
				Case c = new Case(new Position(i, j));
				
				this.cases.put(c, null);
			}
		}
	}
		
	public void setAgent(Agent agent) {		
		Random random = new Random();
		ArrayList<Case> cases = new ArrayList<Case>(this.cases.keySet());
		Case randomCase = cases.get(random.nextInt(cases.size()));
		
		if(this.cases.get(randomCase) == null) {
			this.cases.put(randomCase, agent);
			agent.setCurrentPosition(randomCase.getPosition());
		}
	}

	/**
	 * @return the cases
	 */
	public HashMap<Case, Agent> getCases() {
		return cases;
	}

	/**
	 * @param cases the cases to set
	 */
	public void setCases(HashMap<Case, Agent> cases) {
		this.cases = cases;
	}
	
}
