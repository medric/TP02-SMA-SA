package view;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;

import core.Agent;
import core.Grid;
import core.Square;

public class GridView extends JFrame implements Observer {
	private Cell[][] cells;
	private Grid grid;

	public GridView(Grid grid) {
		super("Interaction multi-agents");
		
		this.grid = grid;
		GridLayout gridLayout = new GridLayout(this.grid.getSize(), 
													this.grid.getSize()); //Create GridLayout 
		
		this.cells = new Cell[this.grid.getSize()][this.grid.getSize()];
		
		this.setGrid();
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(gridLayout);
		this.pack();
		this.setSize(500, 500);
		this.setLocationRelativeTo(null);
		this.setVisible(true);	
	}
	
	@Override
	public void update(Observable obs, Object obj) {
		if(obs instanceof Agent) {			
			try {
				this.draw();
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Init grid
	 */
	private void setGrid() {
		for (int v = 0; v < this.grid.getSize(); v++) {
			for (int h = 0; h < this.grid.getSize(); h++) {
				this.getContentPane().add(this.cells[v][h] = new Cell(v, h)); 
			}
		}
	}
	
	/*
	 * 
	 */
	private void draw() {
		for (ArrayList<Square> row : this.grid.getSquares()) {
			for(Square square : row) {
				int x = square.getPosition().getX();
				int y = square.getPosition().getY();
				
				if(square.getAgent() != null) {
					this.cells[x][y].setBackground(square.getAgent().getBg());
				} else {
					this.cells[x][y].setBackground(null);
				}
			}
		}
	}
	
	/**
	 * 
	 */
	private void refresh() {
		for (int v = 0; v < this.grid.getSize(); v++) {
			for (int h = 0; h < this.grid.getSize(); h++) {
				this.cells[v][h].setBackground(null);
			}
		}
	}
}
