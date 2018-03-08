package Model;
import java.awt.Color;

import javax.swing.JButton;


public class GridSpace {
	private JButton button;
	private boolean taken;
	
	public GridSpace(JButton b) {
		button = b;
		taken = button.getBackground() == Color.BLACK ? false : true;
	}
	
	public JButton getButton() {
		return button;
	}
	
	public void setButton(JButton button) {
		this.button = button;
	}
	
	public boolean isTaken() {
		return taken;
	}
	
	public void setTaken(boolean taken) {
		this.taken = taken;
	}
}







