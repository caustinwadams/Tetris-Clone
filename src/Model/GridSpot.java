package Model;

import java.awt.Color;


public class GridSpot {
	private Color color;
	private GridSpot above;
	private boolean occupied;
	
	public GridSpot() {
		new GridSpot(Color.BLACK, false);
	}
	
	public GridSpot(Color c, boolean oc) {
		color = c;
		occupied  =oc;
		above = null;
	}
	
	public Color getColor() {
		return color;
	}
	
	public void setColor(Color c) {
		color = c;
	}
	
	public GridSpot getAbove() {
		return above;
	}
	
	public void setAbove(GridSpot other) {
		above = other;
	}
	
	public boolean isOccupied() {
		return occupied;
	}
	
	public void setOccupied(boolean oc) {
		occupied = oc;
	}
}

