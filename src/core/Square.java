package core;
/**
 * 
 */

/**
 * @author medric
 *
 */
public class Square {
	private Position position;
	private Agent agent;
		
	public Square(Position position) {
		this.setPosition(position);
	}

	/**
	 * @return the position
	 */
	public Position getPosition() {
		return position;
	}

	/**
	 * @param position the position to set
	 */
	public void setPosition(Position position) {
		this.position = position;
	}

	/**
	 * @return the agent
	 */
	public Agent getAgent() {
		return agent;
	}

	/**
	 * @param agent the agent to set
	 */
	public void setAgent(Agent agent) {
		this.agent = agent;
	}
}
