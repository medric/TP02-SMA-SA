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
}
