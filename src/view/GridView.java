package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.lang.ref.SoftReference;
import java.util.ArrayList;

import javax.swing.JFrame;

public class GridView extends JFrame {
	private int columns;
	private int rows;
	private Cell[][] cells;
	
	public GridView(int rows, int columns) {
		super("Interaction multi-agents");
		this.setRows(rows);
		this.setColumns(columns);
		
		this.cells = new Cell[this.getRows()][this.getColumns()];
		
		GridLayout grid = new GridLayout(this.getRows(), this.getColumns()); //Create GridLayout 
		
		this.setGrid();
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(grid);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);	
	}
	
	/**
	 * Init grid
	 */
	private void setGrid() {
		for (int v = 0; v < this.getRows(); v++) {
			for (int h = 0; h < this.getColumns(); h++) {
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
}
