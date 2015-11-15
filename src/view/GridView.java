package view;

import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;

import core.Agent;
import core.Grid;

public class GridView extends JFrame implements Observer {
	private int columns;
	private int rows;
	private Cell[][] cells;
	private Grid grid;
	
	public GridView(int rows, int columns) {
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
	}
	
	public GridView(Grid grid) {
		super("Interaction multi-agents");
		
		//this.cells = new Cell[this.getRows()][this.getColumns()];
		
		GridLayout gridLayout = new GridLayout(this.grid.getSize(), 
													this.grid.getSize()); //Create GridLayout 
		
		this.setGrid();
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(gridLayout);
		this.pack();
		this.setSize(500, 500);
		this.setLocationRelativeTo(null);
		this.setVisible(true);	
	}
	
	/**
	 * Init grid
	 */
	private void setGrid() {
//		for (int v = 0; v < this.getRows(); v++) {
//			for (int h = 0; h < this.getColumns(); h++) {
//				this.getContentPane().add(this.cells[v][h] = new Cell(v, h)); 
//			}
//		}
		
		for (int v = 0; v < this.grid.getSize(); v++) {
			for (int h = 0; h < this.grid.getSize(); h++) {
				this.getContentPane().add(this.cells[v][h] = new Cell(v, h)); 
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

	@Override
	public void update(Observable obs, Object obj) {
		// TODO Auto-generated method stub
		if(obs instanceof Agent) {
			
		}
	}
}
