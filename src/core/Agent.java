package core;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Observable;
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
	static Semaphore mutex = new Semaphore(0);
	
	public Agent(String name) {
		this.setName(name);
	}
	
	public void run() {
		while(!this.targetReached()) {
			ArrayList<Message> messages = this.getInbox().getMessages(this);
			
			if(messages.size() == 0 || messages == null) {
				this.move();
			} else {
				for(Message message : messages) {
					switch(message.getAction()) {
						case Request: 
							break;
						case Move:
							this.move();
							break;
						case FreePosition: 
							break;
					}
				}
			}
		}
	}
	
	/**
	 * 
	 */
	public void move() {
		for(Square neighbor : this.getGrid().getNeighbors(this.getCurrentSquare())) {
			if(this.getGrid().isSquareFree(neighbor)) {
				this.free();
				this.getGrid().getSquares().put(neighbor, this);
				this.setCurrentSquare(neighbor);
				break;
			} else {
				try {
					mutex.acquire();
					Message message = new Message(this, this.getGrid().getSquares().get(neighbor), Action.FreePosition, this.getCurrentSquare());
					this.getInbox().send(message);
					mutex.release();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		
		// Notify update
		setChanged();
		this.getGrid().render();
		this.notifyObservers(this);
	}
	
	/**
	 * 
	 */
	public void free() {
		this.getGrid().getSquares().put(this.currentSquare, null);
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
}