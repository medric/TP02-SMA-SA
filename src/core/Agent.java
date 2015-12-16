package core;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

import core.Action;

/**
 * @author medric
 *
 */
public class Agent extends Observable implements Runnable {
	private String name;
	private Square currentSquare;
	private Square targetedSquare;
	private Inbox inbox;
	private Grid grid;
	private Color bg;
	private Random randomGenerator;
	
	public Agent(String name) {
		this.setName(name);
		
		randomGenerator = new Random();
	}
	
	public void run() {
		while(!grid.agentsSatisfied()) {
			CopyOnWriteArrayList<Message> messages = this.getInbox().getMessages(this);
			
			this.update();
			
			if(messages.size() == 0 || messages == null) {
				this.move();
			} else {
				try {
					for(Message message : messages) {
						switch(message.getAction()) {
							case REQUEST:
								
								ArrayList<Square> freeNeighbors = this.grid.getFreeNeighbors(this.getCurrentSquare());
								
								if(freeNeighbors.size() > 0 ) {
									Square neighbor = getRandomNeighbor(freeNeighbors);
									
									this.free();
									Message reply = new Message(this, message.getEmitter(), Action.MOVE, this.getCurrentSquare());
									this.getInbox().send(reply);
									this.setCurrentSquare(neighbor);
								}
								break;
	
							case MOVE:
								if(this.getGrid().isSquareFree(message.getTarget())) {
									this.free();
									this.setCurrentSquare(message.getTarget());
								}
								break;
						}
						
						messages.remove(message);
					}
					
				} catch(Exception e) {
					System.err.println(e);
				}
			}
		}
		
		this.update();
		System.out.println("Agent " + this.getName() + " satisfait");
	}
	
	/**
	 * Move an agent
	 */
	public void move() {	
		// Random
		ArrayList<Square> neighbors = this.getGrid().getNeighbors(this.getCurrentSquare());
		
		Square neighbor = getRandomNeighbor(neighbors);
		
		for(Square square : neighbors) {
			if(square.equals(this.targetedSquare) &&  this.getGrid().isSquareFree(square)) {
				neighbor = square;
			}
		}

		if(this.getGrid().isSquareFree(neighbor)) {
			this.free();
			this.setCurrentSquare(neighbor);
		} else {
			Message message = new Message(this, neighbor.getAgent(), Action.REQUEST, this.getCurrentSquare());
			this.getInbox().send(message);
		}

	}
	
	/**
	 * Get a random neighbor
	 * @param neighbors
	 * @return
	 */
	private Square getRandomNeighbor(ArrayList<Square> neighbors) {
		int index = randomGenerator.nextInt(neighbors.size());
		return neighbors.get(index);
	}
	
	/**
	 *  Free current square
	 */
	public void free() {
		this.getCurrentSquare().setAgent(null);
	}
	
	/*
	 * Check if target is reached
	 */
	public boolean targetReached() {
		return getCurrentSquare().equals(this.getTargetedSquare());
	}

	/**
	 * @return the currentPosition
	 */
	public Square getCurrentSquare() {
		return currentSquare;
	}

	/**
	 * @param currentPosition the currentPosition to set
	 */
	public void setCurrentSquare(Square currentSquare) {
		this.currentSquare = currentSquare;
		this.currentSquare.setAgent(this);
	}

	/**
	 * @return the targetPosition
	 */
	public Square getTargetedSquare() {
		return targetedSquare;
	}

	/**
	 * @param targetPosition the targetPosition to set
	 */
	public void setTargetedSquare(Square targetedSquare) {
		this.targetedSquare = targetedSquare;
	}

	/**
	 * @return the inbox
	 */
	public Inbox getInbox() {
		return inbox;
	}

	/**
	 * @param inbox the inbox to set
	 */
	public void setInbox(Inbox inbox) {
		this.inbox = inbox;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the grid
	 */
	public Grid getGrid() {
		return grid;
	}

	/**
	 * @param grid the grid to set
	 */
	public void setGrid(Grid grid) {
		this.grid = grid;
	}

	/**
	 * @return the bg
	 */
	public Color getBg() {
		return bg;
	}

	/**
	 * @param bg the bg to set
	 */
	public void setBg(Color bg) {
		this.bg = bg;
	}
	
	/**
	 * Notify view
	 */
	private void update() {
		// Notify update
		setChanged();
		this.getGrid().render();
		this.notifyObservers(this);
	}
}