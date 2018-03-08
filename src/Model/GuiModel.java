package Model;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;



public class GuiModel {
	public GridRow[] gridRows;
	public GridSpace[] gameGrid;
	
	
	private long speed;
	
	private boolean paused;
	
	private int lowestNum;
	private int highestRow;
	private Shape curPiece;
	
	private int realPos;
	private int shp;
	
	
	public enum shape {
		SQUARE, LINE, Z1, Z2, L1, L2, TEE
	};
	
	
	
	
	public GuiModel() {
		setupGridRows();
		initializeGameGrid();
		initializeRandomShape();
		
		speed = 500;
		highestRow = Integer.MAX_VALUE;
		lowestNum = Integer.MAX_VALUE;
		paused = false;
	}
	
	
	
	
	
	
	private void setupGridRows() {
		gridRows = new GridRow[GameConstants.GRID_LENGTH];
		
		for (int i = 0; i < GameConstants.GRID_LENGTH; i++) {
			gridRows[i] = new GridRow();
		}
	}
	
	
	public void initializeGameGrid() {
		int dimension = GameConstants.GRID_LENGTH * 
						GameConstants.GRID_WIDTH;
		gameGrid = new GridSpace[dimension];
		
		for (int i = 0; i < gameGrid.length; i++) {
			final JButton buttonToAdd = new JButton(" ");
			buttonToAdd.setBorder(null);
			buttonToAdd.setVisible(true);
			buttonToAdd.setPreferredSize(new Dimension(20,20));
			buttonToAdd.setMaximumSize(buttonToAdd.getPreferredSize());
			buttonToAdd.setEnabled(false);
			
			if (i < GameConstants.GRID_WIDTH - 1 || i > 251 || 
				i % GameConstants.GRID_WIDTH == 0 || 
				(i+1) % GameConstants.GRID_WIDTH == 0)
				buttonToAdd.setBackground(Color.GRAY);
			else
				buttonToAdd.setBackground(Color.BLACK);
			
			gameGrid[i] = new GridSpace(buttonToAdd);
		}
	}
	
	
	
	
	
	public long getSpeed() {
		return speed;
	}
	
	public void setSpeed(long speed) {
		this.speed = speed;
	}
	
	public boolean isPaused() {
		return paused;
	}
	
	public void setPaused() {
		paused = !paused;
	}
	
	public int getLowestNum() {
		return lowestNum;
	}
	
	public void setLowestNum(int num) {
		lowestNum = num;
	}
	
	
	public int getHighestRow() {
		return highestRow;
	}
	
	public void setHighestRow(int num) {
		highestRow = num;
	}
	
	public Shape getCurPiece() {
		return curPiece;
	}
	
	public void setCurPiece() {
		shape s = shape.TEE;
		switch (shp) {
		case 0:
			s = shape.TEE;
			break;
		case 1:
			s = shape.L2;
			break;
		case 2:
			s = shape.Z2;
			break;
		case 3: 
			s = shape.LINE;
			break;
		case 4:
			s = shape.SQUARE;
			break;
		case 5:
			s = shape.L1;
			break;
		case 6:
			s = shape.Z1;
			break;
		}
		initializeRandomShape();
		curPiece = new Shape(s);
		realPos = 36;
	}
	
	public int getRealPos() {
		return realPos;
	}
	
	public void setRealPos(int pos) {
		realPos = pos;
	}
	
	public int setShp() {
		return shp;
	}
	
	public void initializeRandomShape() {
		shp = (int)(Math.random() * 7);
	}
	
	public void setShp(int shp) {
		this.shp = shp;
	}
	
	
}
