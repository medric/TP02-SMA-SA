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
	
	/**
	 * 
	 * @param agent
	 */
	public void addAgent(Agent agent) {
		this.messages.put(agent, new ArrayList<Message>());
	}
	
	/**
	 * 
	 * @param message
	 */
	public void send(Message message) {
		ArrayList<Message> recipientMessages = this.messages.get(message.getRecipient());
		
		if(recipientMessages != null) {
			recipientMessages.add(message);
			this.messages.put(message.getRecipient(), recipientMessages);
		}
	}
}
