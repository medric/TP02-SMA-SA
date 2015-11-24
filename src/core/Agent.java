package core;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;
import java.util.concurrent.Semaphore;

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
	private static Semaphore mutex;
	
	public Agent(String name, Semaphore _mutex) {
		this.setName(name);
		
		randomGenerator = new Random();
		mutex = _mutex;
	}
	
	public void run() {
		while(!this.targetReached()) {
			ArrayList<Message> messages = this.getInbox().getMessages(this);
			
			if(messages.size() == 0 || messages == null) {
				this.move();
			} else {
				try {
					for(Message message : messages) {
						switch(message.getAction()) {
							case Request:
								
								ArrayList<Square> freeNeighbors = this.grid.getFreeNeighbors(this.getCurrentSquare());
								
								if(freeNeighbors.size() > 0 ) {
									Square neighbor = getRandomNeighbor(freeNeighbors);
									
									this.free();
									Message reply = new Message(this, message.getEmitter(), Action.Move, this.getCurrentSquare());
									this.getInbox().send(reply);
									this.setCurrentSquare(neighbor);
								}
								break;
	
							case Move:
								if(this.getGrid().isSquareFree(message.getTarget())) {
									this.free();
									this.setCurrentSquare(message.getTarget());
								}
								break;
							case FreePosition: 
								break;
						}
						
						messages.remove(message);
					}
					
				} catch(Exception e) {
					
				}
			}
			
			this.update();
		}
	}
	
	/**
	 * 
	 */
	public void move() {	
		// Random
		ArrayList<Square> neighbors = this.getGrid().getNeighbors(this.getCurrentSquare());

		Square neighbor = getRandomNeighbor(neighbors);
		
		if(this.getGrid().isSquareFree(neighbor)) {
			this.free();
			this.setCurrentSquare(neighbor);
		} else {
			Message message = new Message(this, neighbor.getAgent(), Action.Request, this.getCurrentSquare());
			this.getInbox().send(message);
		}

	}
	
	/**
	 * 
	 * @param neighbors
	 * @return
	 */
	private Square getRandomNeighbor(ArrayList<Square> neighbors) {
		int index = randomGenerator.nextInt(neighbors.size());
		return neighbors.get(index);
	}
	
	/**
	 * 
	 */
	public void free() {
		this.currentSquare.setAgent(null);
	}
	
	/*
	 * 
	 */
	private boolean targetReached() {
		return currentSquare.equals(targetedSquare);
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
	 * 
	 */
	private void update() {
		// Notify update
		setChanged();
		this.getGrid().render();
		this.notifyObservers(this);
	}
}