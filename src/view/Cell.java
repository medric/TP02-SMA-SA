package view;

import java.awt.Color;

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
