package core;
/**
 * 
 */

/**
 * @author medric
 *
 */
public class Case {
	private Position position;
	
	public Case(Position position) {
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
}
