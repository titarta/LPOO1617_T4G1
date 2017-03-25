package dkeep.gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;

import dkeep.logic.Game;
import dkeep.logic.Generic.Coordinate;
import dkeep.logic.*;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JPanel;

public class LevelEditor {

	private JFrame frame;
	private Game game;
	private int xValue;
	private int yValue;
	private String elementToAdd;
	private GameEditorPanel gPanel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LevelEditor window = new LevelEditor();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LevelEditor() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 620, 560);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton saveGame = new JButton("Save Game");
		saveGame.setBounds(505, 55, 90, 25);
		frame.getContentPane().add(saveGame);
		
		JButton cancel = new JButton("Cancel");
		cancel.setBounds(505, 450, 90, 25);
		frame.getContentPane().add(cancel);
		
		JButton hero = new JButton("Hero");
		hero.setBounds(505, 150, 90, 25);
		frame.getContentPane().add(hero);
		hero.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				elementToAdd = "Hero";
				gPanel.requestFocusInWindow();
			}
		});
		
		JButton door = new JButton("Door");
		door.setBounds(505, 180, 90, 25);
		frame.getContentPane().add(door);
		door.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				elementToAdd = "Door";
				gPanel.requestFocusInWindow();
			}
		});
		
		JButton wall = new JButton("Wall");
		wall.setBounds(505, 210, 90, 25);
		frame.getContentPane().add(wall);
		wall.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				elementToAdd = "Wall";
				gPanel.requestFocusInWindow();
			}
		});
		
		JButton ogre = new JButton("Ogre");
		ogre.setBounds(505, 240, 90, 25);
		frame.getContentPane().add(ogre);
		ogre.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				elementToAdd = "Ogre";
				gPanel.requestFocusInWindow();
			}
		});
		
		JButton key = new JButton("Key");
		key.setBounds(505, 270, 90, 25);
		frame.getContentPane().add(key);
		key.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				elementToAdd = "Key";
				gPanel.requestFocusInWindow();
			}
		});
		
		JLabel xValueLabel = new JLabel("X:");
		xValueLabel.setBounds(30, 20, 20, 20);
		frame.getContentPane().add(xValueLabel);
		
		JLabel yValueLabel = new JLabel("Y:");
		yValueLabel.setBounds(150, 20, 20, 20);
		frame.getContentPane().add(yValueLabel);
		
		xValue = 5;
		JComboBox xComboBox = new JComboBox();
		xComboBox.setModel(new DefaultComboBoxModel(new String[] {"5", "6", "7", "8", "9", "10", "11", "12", "13"}));
		xComboBox.setSelectedIndex(0);
		xComboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				xValue = 5 + xComboBox.getSelectedIndex();
			}
		});
		xComboBox.setBounds(50, 20, 50, 20);
		frame.getContentPane().add(xComboBox);
		
		yValue = 5;
		JComboBox yComboBox = new JComboBox();
		yComboBox.setModel(new DefaultComboBoxModel(new String[] {"5", "6", "7", "8", "9", "10", "11", "12", "13"}));
		yComboBox.setSelectedIndex(0);
		yComboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				yValue = 5 + yComboBox.getSelectedIndex();
			}
		});
		yComboBox.setBounds(170, 20, 50, 20);
		frame.getContentPane().add(yComboBox);
		
		JButton generateMap = new JButton("Generate");
		generateMap.setBounds(300, 22, 100, 20);
		frame.getContentPane().add(generateMap);
		generateMap.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				generatePanel();
				gPanel.requestFocusInWindow();
			}
		});
		
		
		
	}
	
	private void generatePanel() {
		game = new Game(xValue, yValue);
		gPanel = new GameEditorPanel(game);
		gPanel.setBounds(50, 70, 32*13, 32*13);
		frame.getContentPane().add(gPanel);
		gPanel.setVisible(true);
		gPanel.paintComponent(gPanel.getGraphics());
		gPanel.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				switch(elementToAdd) {
				case "Hero":
					game.addEntity(new Hero(new Coordinate(e.getPoint().y /32, e.getPoint().x /32)));
					break;
				case "Door":
					game.addEntity(new Door(new Coordinate(e.getPoint().y /32, e.getPoint().x /32)));
					break;
				case "Wall":
					game.addEntity(new Wall(new Coordinate(e.getPoint().y /32, e.getPoint().x /32)));
					break;
				case "Ogre":
					game.addEntity(new Ogre(new Coordinate(e.getPoint().y /32, e.getPoint().x /32),true));
					break;
				case "Key":
					game.addEntity(new Key(new Coordinate(e.getPoint().y /32, e.getPoint().x /32)));
					break;
				}
				gPanel.setGame(game);
				gPanel.paintComponent(gPanel.getGraphics());
				gPanel.requestFocusInWindow();
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
	}
}
