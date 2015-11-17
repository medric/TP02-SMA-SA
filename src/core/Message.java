package core;
/**
 * 
 */

/**
 * @author medric
 *
 */
public class Message {
	private Agent emitter;
	private Agent recipient;
	private Action action;
	private Square target;
	
	public Message(Agent emitter, Agent recipient, Action action, Square target) {
		this.setAction(action);
		this.setEmitter(emitter);
		this.setRecipient(recipient);
		this.setTarget(target);
	}

	/**
	 * @return the emitter
	 */
	public Agent getEmitter() {
		return emitter;
	}

	/**
	 * @param emitter the emitter to set
	 */
	public void setEmitter(Agent emitter) {
		this.emitter = emitter;
	}

	/**
	 * @return the recipient
	 */
	public Agent getRecipient() {
		return recipient;
	}

	/**
	 * @param recipient the recipient to set
	 */
	public void setRecipient(Agent recipient) {
		this.recipient = recipient;
	}

	/**
	 * @return the action
	 */
	public Action getAction() {
		return action;
	}

	/**
	 * @param action the action to set
	 */
	public void setAction(Action action) {
		this.action = action;
	}

	public Square getTarget() {
		return target;
	}

	public void setTarget(Square target) {
		this.target = target;
	}
}
