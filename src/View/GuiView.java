package View;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import Controller.GuiController;
import Model.GameConstants;
import Model.GuiModel;



public class GuiView {
private JSplitPane splitPane;
	
	private JFrame window;
	private JPanel gamePanel;
	private JPanel statsPanel;
	private JPanel scorePanel;
	private JPanel timePanel;
	
	// These properties arent being used yet,
	// but will be used to display current socre
	// and current time played
	
//	private JLabel scoreLabel;
//	private int score;
//	private JLabel timeLabel;
//	private int time;
	
	
	private GuiModel model;
	private GuiController controller;
	

	public GuiView() {
		model = new GuiModel();
		controller = new GuiController();
		setupMainWindow();
		
		model.setCurPiece();
		controller.showPiece(model.getCurPiece(), model.gameGrid);
		controller.run(model);
	}
	
	
	public void setupMainWindow() {
		// Initializing the frame itself
		window = new JFrame("Austin");
		window.setSize(800, 800);
		window.setVisible(true);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		window.setLocation((d.width / 2) - 200, (d.height / 2) - 400);
		
		// Now setting up the panels inside the frame
		setupGamePanel();
		setupStatsPanel();
		
		// Adding the panels to a splitPane inside the frame
		splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, gamePanel, statsPanel);
		splitPane.setEnabled(false);
		splitPane.setDividerSize(0);
		
		// Setting up keys and packing everything into the frame
		controller.setupKeyListener(window, model);
		window.add(splitPane);
		window.pack();
	}
	
	// Helper method for bulding JPanels with just a label
	public JPanel buildPanel(String label) {
		JPanel panel = new JPanel();
		
		panel.setLayout(new FlowLayout());
		panel.add(new JLabel(label + 0));
		
		return panel;
	}
	
	public void setupStatsPanel() {
		// Initializing the main panel
		statsPanel = new JPanel();
		statsPanel.setSize(400,800);
		statsPanel.setBackground(Color.BLACK);
		statsPanel.setLayout(new BoxLayout(statsPanel, 3));

		// Adding subpanels for score and time
		scorePanel = buildPanel("Score: ");
		statsPanel.add(scorePanel);
		timePanel = buildPanel("Time: ");
		statsPanel.add(timePanel);
	}
	
	// Initializing the panel that contains the game board
	public void setupGamePanel() {
		gamePanel = new JPanel();
		gamePanel.setLayout(new GridLayout(GameConstants.GRID_LENGTH,
										   GameConstants.GRID_WIDTH,
										   1,
										   1));
		for (int i = 0; i < model.gameGrid.length; i++) {
			gamePanel.add(model.gameGrid[i].getButton());
		}
		
		gamePanel.setBackground(Color.BLACK);
	}
	
}
