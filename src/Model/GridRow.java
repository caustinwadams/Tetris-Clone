package Model;
import java.awt.Color;
import java.util.ArrayList;


public class GridRow extends ArrayList<GridSpot>{
	private boolean full;
	private int numOccupied;
	
	public GridRow() {
		super();
		for (int i  = 0; i < 12; i++) {
			if (i == 0 || i == 11)
				this.add(i, new GridSpot(Color.GRAY, true));
			else
				this.add(i, new GridSpot());
		}
		
		numOccupied = 2;
	}
	
	public boolean isFull() {
		return full;
	}
	
	public void setFull(boolean f) {
		full = f;
		if (!f) {
			numOccupied = 2;
		}
	}
	
	public int getNumOccupied() {
		return numOccupied;
	}
	
	public void addOccupied() {
		numOccupied++;
		if (numOccupied == 12) {
			setFull(true);
		}
	}
	
	public void minusOccupied() {
		numOccupied--;
	}
	
	
}
