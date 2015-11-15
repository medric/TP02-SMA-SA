package view;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class Cell extends JPanel {
	private int gridRow;
	private int gridColumn;
	
	public Cell(int gridRow, int gridColumn) {
		this.setGridRow(gridRow);
		this.setGridColumn(gridColumn);
		
		this.setBorder(new LineBorder(Color.gray));
	}
	
	@Override
	protected void paintComponent(Graphics g) {
	    super.paintComponent(g);
	        ///g.drawImage(bgImage, 0, 0, null);
	}

	private int getGridRow() {
		return gridRow;
	}

	private void setGridRow(int gridRow) {
		this.gridRow = gridRow;
	}

	private int getGridColumn() {
		return gridColumn;
	}

	private void setGridColumn(int gridColumn) {
		this.gridColumn = gridColumn;
	}
}
