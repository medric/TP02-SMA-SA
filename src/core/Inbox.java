/**
 * 
 */
package core;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author medric
 *
 */
public class Inbox {
	private HashMap<Agent, ArrayList<Message>> messages;

	/**
	 * @return the messages
	 */
	ArrayList<Message> getMessages(Agent agent) {
		return this.messages.get(agent);
	}

}
