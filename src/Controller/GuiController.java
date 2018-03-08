package Controller;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;
import javax.swing.JWindow;

import Model.GameConstants;
import Model.GridRow;
import Model.GridSpace;
import Model.GuiModel;
import Model.Shape;
import Model.GuiModel.shape;


public class GuiController {
	
	public GuiController() {
		
	}
	
	public void run(GuiModel model) {
		while (true) {
			delay(model.getSpeed());
			if (!model.isPaused()) {
				int width = GameConstants.GRID_WIDTH;
				int tempPos = model.getRealPos();
				if (model.getRealPos() <= 252) 
					move(model.getCurPiece(), 
							         width, 
							         model.gameGrid, 
							         model.gridRows);
				model.setRealPos(tempPos + width);
			}
			
			if (model.getRealPos() > 264) {
				
				int[] curArr = model.getCurPiece().getPiece();
				int curNum = 0;
				int row = 0;
				int col = 0;
				ArrayList<Integer> rowsToClear = new ArrayList<Integer>();
				for (int i = 0; i < 4; i++) {
					curNum = curArr[i];
					model.gameGrid[curNum].setTaken(true);
					if (curNum < model.getLowestNum())
						model.setLowestNum(curNum);
					row = curNum / GameConstants.GRID_WIDTH;
					if (row < model.getHighestRow())
						model.setHighestRow(row);
					col = curNum % GameConstants.GRID_WIDTH;
					model.gridRows[row].get(col).setColor(
											model.getCurPiece().getColor());
					model.gridRows[row].addOccupied();
					if (model.gridRows[row].isFull() && !rowsToClear.contains(row)) {
						rowsToClear.add(row);
					}
				}
				
				Collections.sort(rowsToClear);
				for (int fullRow : rowsToClear) {
					clearRow(fullRow, model);
				}
				GuiModel.shape s = GuiModel.shape.TEE;
				model.setCurPiece();
			}
		}
		
	}
	
	
	
	
	public void delay(long secs) {
		try {
			TimeUnit.MILLISECONDS.sleep(secs);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	public void flashRow(int row, GuiModel model) {
		for (int i = (row * GameConstants.GRID_WIDTH) + 1;
			     i < ((row + 1) * GameConstants.GRID_WIDTH) - 1; i++) {
			model.gameGrid[i].setTaken(false);
			model.gameGrid[i].getButton().setBackground(Color.WHITE);
		}
		
		delay(200);
		
		for (int i = (row * GameConstants.GRID_WIDTH) + 1;
			     i < ((row + 1) * GameConstants.GRID_WIDTH) - 1; i++) {
			model.gameGrid[i].getButton().setBackground(Color.BLACK);
			model.gameGrid[i].setTaken(false);
		}
	}
	
	
	public void clearRow(int row, GuiModel model) {
		flashRow(row, model);
		
		int curSpace = 0;
		int aboveSpace = 0;
		int width = GameConstants.GRID_WIDTH;
		for (int curRow = row; curRow >= model.getHighestRow(); curRow--) {
			model.gridRows[curRow].setFull(false);
			for (int j = 0; j < width; j++) {
				curSpace = (width * curRow) + j;
				aboveSpace = curSpace - width;
				Color colorAbove = 
						model.gameGrid[aboveSpace].getButton().getBackground();
				
				if (!colorAbove.equals(Color.BLACK) && 
					j != 0 && 
					j != width - 1) {
					
					model.gameGrid[curSpace].getButton().setBackground(colorAbove);
					model.gameGrid[curSpace].setTaken(true);
					model.gridRows[curRow].addOccupied();
					model.gameGrid[aboveSpace].getButton().setBackground(Color.BLACK);
					model.gameGrid[aboveSpace].setTaken(false);
					
				} // end if (!colorAbove)
				
			} // end for j
		} // end for curRow
		model.setHighestRow(model.getHighestRow() + 1);
		long speed = model.getSpeed();
		if (speed > 200)
			model.setSpeed(speed - 50);
		else if (speed > 100)
			model.setSpeed(speed - 25);
		else if (speed > 50)
			model.setSpeed(speed - 10);
		
	}
	
	
	
	public void setupKeyListener(JFrame window, GuiModel model) {
		window.addKeyListener(new GuiKeyListener(model));
	}
	
	
	
	
	public void showPiece(Shape s, GridSpace[] grid) {
		int[] piece = s.getPiece();
		for (int i = 0; i < 4; i++) {
			grid[piece[i]].getButton().setBackground(s.getColor());
		}
	}
	
	public void clearShape(Shape s, GridSpace[] grid) {
		int piece[] = s.getPiece();
		for (int i = 0; i < 4; i++) {
			grid[piece[i]].getButton().setBackground(Color.BLACK);
		}
	}
	
	
	
	
	public void rotate(Shape s, GridSpace[] grid) {
		clearShape(s,grid);
		s.setRotation((s.getRotation() + 1) % 4);
		s.setShape();
		if (valid(s.getPiece(), grid)) 
			showPiece(s, grid);
		else {
			s.setRotation((s.getRotation() - 1));
			if (s.getRotation() == -1)
				s.setRotation(3);
			s.setShape();
			showPiece(s, grid);
		}
	}
	
	
	
	public void move(Shape s, int dir, GridSpace[] grid, GridRow[] gridRows) {
		clearShape(s, grid);
		s.setCenter(s.getCenter() + dir);
		s.setShape();
		if (valid(s.getPiece(), grid)) 
			showPiece(s, grid);
		else {
			s.setCenter(s.getCenter() - dir);
			s.setShape();
			showPiece(s, grid);
		}
	}
	
	public boolean valid(int[] piece, GridSpace[] grid) {
		boolean ret = false;
		for (int i  = 0; i < 4; i++) {
			if (piece[i] % 12 == 11 ||
				piece[i] % 12 == 0 ||
				piece[i] > 252 ||
				grid[piece[i]].isTaken())
				return false;
		}
		return true;
	}
	
	
	
	// -----------------------------------
	// ------- Key Listener Class --------
	// -----------------------------------
	public class GuiKeyListener implements KeyListener {
		private GuiModel model;
		public GuiKeyListener(GuiModel model) {
			this.model = model;
		}
		
		
		final int UP_ARROW = KeyEvent.VK_UP;
		final int DOWN_ARROW = KeyEvent.VK_DOWN;
		final int RIGHT_ARROW = KeyEvent.VK_RIGHT;
		final int LEFT_ARROW = KeyEvent.VK_LEFT;
		final int SPACE = KeyEvent.VK_SPACE;
		final int PAUSE = KeyEvent.VK_P;
		

		@Override
		public void keyPressed(KeyEvent e) {
			int buttonPressed = e.getKeyCode();
			int dir = 0;
			int width = GameConstants.GRID_WIDTH;
			switch (buttonPressed) {
			
			case UP_ARROW:
				if (!model.isPaused())
					rotate(model.getCurPiece(), model.gameGrid);
				break;
			
			case DOWN_ARROW:
				model.setRealPos(model.getRealPos() + width);
				dir = width;
				break;
			
			case LEFT_ARROW:
				dir = -1;
				break;
			
			case RIGHT_ARROW:
				dir = 1;
				break;
			
			case SPACE:
				while (model.getRealPos() < 252) {
					if (!model.isPaused()) {
						move(model.getCurPiece(),
										 width, 
										 model.gameGrid,
										 model.gridRows);
						model.setRealPos(model.getRealPos() + width);
					}
				}
				break;
				
			case PAUSE:
				model.setPaused();
				break;
			default:
				break;
			}
			
			if (dir != 0 && !model.isPaused())
				move(model.getCurPiece(), 
								 dir, 
								 model.gameGrid,
								 model.gridRows);
		}

		@Override
		public void keyReleased(KeyEvent arg0) {
		}

		@Override
		public void keyTyped(KeyEvent arg0) {
		}
	}

	
	
	
	
	
	

}



