package Model;
import java.awt.Color;


public class Shape {
	
	private final Color[] colors = {
			Color.BLUE,
			Color.RED,
			Color.CYAN,
			Color.PINK,
			Color.MAGENTA,
			Color.ORANGE,
			Color.GREEN,
			Color.YELLOW,
	};
	
	
	private int[] piece;
	private GuiModel.shape shape;
	private int center;
	private int top;
	private int bottom;
	private int rotation;
	Color color;
	
	public Shape(GuiModel.shape shape) {
		rotation = 0;
		this.shape = shape;
		piece = new int[4];
		center = 43;
		piece[1] = center;
		
		int randI = (int)(Math.random() * 7);
		color = colors[randI];
		
		setShape();
	}
	
	public void setShape() {
		piece[1] = center;
		switch (shape) {
		case SQUARE:
			//color = Color.CYAN;
			piece[0] = center + 1;
			piece[2] = center + 12;
			piece[3] = center + 13;
			break;
		case TEE:
			//color = Color.BLUE;
			switch (rotation) {
			case 0:
				piece[0] = center - 12;
				piece[2] = center + 1;
				piece[3] = center - 1;
				break;
			case 1:
				piece[0] = center - 12;
				piece[2] = center + 1;
				piece[3] = center + 12;
				break;
			case 2:
				piece[0] = center - 1;
				piece[2] = center + 12;
				piece[3] = center + 1;
				break;
			case 3:
				piece[0] = center - 12;
				piece[2] = center - 1;
				piece[3] = center + 12;
				break;
			}
			
			break;
		case L1:
			//color = Color.YELLOW;
			switch (rotation) {
			case 0:
				piece[0] = center - 12;
				piece[2] = center + 12;
				piece[3] = center + 13;
				break;
			case 1:
				piece[0] = center - 1;
				piece[2] = center + 1;
				piece[3] = center + 11;
				break;
			case 2:
				piece[0] = center - 13;
				piece[2] = center - 12;
				piece[3] = center + 12;
				break;
			case 3:
				piece[0] = center - 11;
				piece[2] = center + 1;
				piece[3] = center - 1;
				break;
			}
			break;
		case L2:
			//color = Color.MAGENTA;
			switch (rotation) {
			case 0:
				piece[0] = center - 12;
				piece[2] = center + 12;
				piece[3] = center + 11;
				break;
			case 1:
				piece[0] = center - 1;
				piece[2] = center + 1;
				piece[3] = center - 13;
				break;
			case 2:
				piece[0] = center - 11;
				piece[2] = center - 12;
				piece[3] = center + 12;
				break;
			case 3:
				piece[0] = center + 13;
				piece[2] = center + 1;
				piece[3] = center - 1;
				break;
			}
			break;
		case LINE:
			//color = Color.ORANGE;
			switch (rotation) {
			case 0:
				piece[0] = center - 12;
				piece[2] = center + 12;
				piece[3] = center + 24;
				break;
			case 1:
				piece[0] = center - 1;
				piece[2] = center + 1;
				piece[3] = center + 2;
				break;
			case 2:
				piece[0] = center - 12;
				piece[2] = center + 12;
				piece[3] = center + 24;
				break;
			case 3:
				piece[0] = center - 1;
				piece[2] = center + 1;
				piece[3] = center + 2;
				break;
			}
			break;
		case Z1:
			//color = Color.GREEN;
			switch (rotation) {
			case 0:
				piece[0] = center - 1;
				piece[2] = center - 12;
				piece[3] = center - 11;
				break;
			case 1:
				piece[0] = center - 12;
				piece[2] = center + 1;
				piece[3] = center + 13;
				break;
			case 2:
				piece[0] = center + 11;
				piece[2] = center + 12;
				piece[3] = center + 1;
				break;
			case 3:
				piece[0] = center - 13;
				piece[2] = center - 1;
				piece[3] = center + 12;
				break;
			}
			break;
		case Z2:
			
			//color = Color.RED;
			switch (rotation) {
			case 0:
				piece[0] = center - 13;
				piece[2] = center - 12;
				piece[3] = center + 1;
				break;
			case 1:
				piece[0] = center - 11;
				piece[2] = center + 1;
				piece[3] = center + 12;
				break;
			case 2:
				piece[0] = center - 1;
				piece[2] = center + 12;
				piece[3] = center + 13;
				break;
			case 3:
				piece[0] = center - 12;
				piece[2] = center - 1;
				piece[3] = center + 11;
				break;
			}
			break;
		default:
			break;
		}
	}
	
	
	public int[] getPiece() {
		return piece;
	}
	
	public int getRotation() {
		return rotation;
	}
	
	public void setRotation(int rot) {
		rotation = rot;
	}
	
	public void setCenter(int c) {
		center = c;
	}
	
	public int getCenter() {
		return center;
	}
	
	public Color getColor() {
		return color;
	}
}
