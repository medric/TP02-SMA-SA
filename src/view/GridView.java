package view;

import java.awt.Graphics;
import java.awt.GridLayout;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;

import core.Agent;
import core.Grid;
import core.Square;

public class GridView extends JFrame implements Observer {
	private int columns;
	private int rows;
	private Cell[][] cells;
	private Grid grid;
	
	/*public GridView(int rows, int columns) {
		super("Interaction multi-agents");
		this.setRows(rows);
		this.setColumns(columns);
		
		this.cells = new Cell[this.getRows()][this.getColumns()];
		
		GridLayout gridLayout = new GridLayout(this.getRows(), this.getColumns()); //Create GridLayout 
		
		this.setGrid();
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(gridLayout);
		this.pack();
		this.setSize(500, 500);
		this.setLocationRelativeTo(null);
		this.setVisible(true);	
	}*/
	
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void paintComponent(Graphics g) {
		super.paintComponents(g);
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
		for (Map.Entry<Square, Agent> entry : this.grid.getSquares().entrySet()) {
			int x = entry.getKey().getPosition().getX();
			int y = entry.getKey().getPosition().getY();
			
			if(entry.getValue() != null) {
				this.cells[x][y].setBackground(entry.getValue().getBg());
			} else {
				this.cells[x][y].setBackground(null);
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
	
	private int getColumns() {
		return columns;
	}

	private void setColumns(int columns) {
		this.columns = columns;
	}

	private int getRows() {
		return rows;
	}

	private void setRows(int rows) {
		this.rows = rows;
	}
}
