package core;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * 
 */

/**
 * @author medric
 *
 */
public class Agent implements Runnable {
	private String name;
	private Position currentPosition;
	private Position targetPosition;
	private Inbox inbox;
	
	public Agent(String name) {
		this.setName(name);
	}
	
	public void run() {
		boolean puzzleDone = false;
		while(!puzzleDone) {
			
		}
	}
	
	public void move() {
	}
	
	public void free() {
		
	}

	/**
	 * @return the currentPosition
	 */
	Position getCurrentPosition() {
		return currentPosition;
	}

	/**
	 * @param currentPosition the currentPosition to set
	 */
	void setCurrentPosition(Position currentPosition) {
		this.currentPosition = currentPosition;
	}

	/**
	 * @return the targetPosition
	 */
	Position getTargetPosition() {
		return targetPosition;
	}

	/**
	 * @param targetPosition the targetPosition to set
	 */
	void setTargetPosition(Position targetPosition) {
		this.targetPosition = targetPosition;
	}

	/**
	 * @return the inbox
	 */
	Inbox getInbox() {
		return inbox;
	}

	/**
	 * @param inbox the inbox to set
	 */
	void setInbox(Inbox inbox) {
		this.inbox = inbox;
	}

	/**
	 * @return the name
	 */
	private String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	private void setName(String name) {
		this.name = name;
	}
}
