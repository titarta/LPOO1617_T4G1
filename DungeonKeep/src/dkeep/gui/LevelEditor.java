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

public class LevelEditor extends JFrame{
	private static final long serialVersionUID = 1L;
	
	private JFrame frame;
	private Game game;
	private GameMap gameMap;
	private int xValue;
	private int yValue;
	private String elementToAdd;
	private GameEditorPanel gPanel;
	private JButton hero;
	private JButton door;
	private JButton ogre;
	private JButton weapon;
	private JButton key;
	private JButton wall;
	private boolean has1Hero;
	private boolean canWin;
	private boolean hasAtleast1Ogre;
	private DKeepGUI frameToSend;

	
	public LevelEditor (DKeepGUI window) {
		frameToSend = window;
		initialize();
	}
	
	public void start() {
		setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(){
		
		setSize(720, 560);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(null);
		
		
		JButton saveGame = new JButton("Save");
		saveGame.setBounds(505, 55, 90, 25);
		add(saveGame);
		saveGame.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (has1Hero && hasAtleast1Ogre && canWin) {
					frameToSend.addGame(game);
					dispose();
				}
			}
		});
		
		JButton cancel = new JButton("Cancel");
		cancel.setBounds(505, 450, 90, 25);
		add(cancel);
		
		hero = new JButton("Hero");
		hero.setBounds(505, 150, 90, 25);
		add(hero);
		hero.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				elementToAdd = "Hero";
				gPanel.requestFocusInWindow();
			}
		});
		
		door = new JButton("Door");
		door.setBounds(505, 180, 90, 25);
		add(door);
		door.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				elementToAdd = "Door";
				gPanel.requestFocusInWindow();
			}
		});
		
		wall = new JButton("Wall");
		wall.setBounds(505, 210, 90, 25);
		add(wall);
		wall.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				elementToAdd = "Wall";
				gPanel.requestFocusInWindow();
			}
		});
		
		ogre = new JButton("Ogre");
		ogre.setBounds(505, 240, 90, 25);
		add(ogre);
		ogre.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				elementToAdd = "Ogre";
				gPanel.requestFocusInWindow();
			}
		});
		
		key = new JButton("Key");
		key.setBounds(505, 270, 90, 25);
		add(key);
		key.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				elementToAdd = "Key";
				gPanel.requestFocusInWindow();
			}
		});
		
		weapon = new JButton("Weapon");
		weapon.setBounds(505, 300, 90, 25);
		add(weapon);
		weapon.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				elementToAdd = "Weapon";
				gPanel.requestFocusInWindow();
			}
		});
		
		JLabel xValueLabel = new JLabel("X:");
		xValueLabel.setBounds(30, 20, 20, 20);
		add(xValueLabel);
		
		JLabel yValueLabel = new JLabel("Y:");
		yValueLabel.setBounds(150, 20, 20, 20);
		add(yValueLabel);
		
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
		add(xComboBox);
		
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
		add(yComboBox);
		
		JButton generateMap = new JButton("Generate");
		generateMap.setBounds(300, 22, 100, 20);
		add(generateMap);
		generateMap.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				generatePanel();
				gPanel.requestFocusInWindow();
			}
		});
		
		
		
	}
	
	private void generatePanel() {
		hasAtleast1Ogre = false;
		has1Hero = false;
		canWin = false;
		hero.setEnabled(true);
		game = new Game(xValue, yValue);
		gPanel = new GameEditorPanel(game);
		gameMap = game.getGameMap();
		gPanel.setBounds(50, 70, 32*13, 32*13);
		add(gPanel);
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
					placeHero(new Coordinate(e.getPoint().y /32, e.getPoint().x /32));
					break;
				case "Door":
					placeDoor(new Coordinate(e.getPoint().y /32, e.getPoint().x /32));
					break;
				case "Wall":
					placeWall(new Coordinate(e.getPoint().y /32, e.getPoint().x /32));
					break;
				case "Ogre":
					placeOgre(new Coordinate(e.getPoint().y /32, e.getPoint().x /32));
					break;
				case "Key":
					placeKey(new Coordinate(e.getPoint().y /32, e.getPoint().x /32));
					break;
				case "Weapon":
					placeWeapon(new Coordinate(e.getPoint().y /32, e.getPoint().x /32));
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
	
	private void placeHero(Coordinate coord) {
		if (gameMap.coordHasSomething(coord)) {
			return;
		}
		has1Hero = true;
		Hero hero1 = new Hero(coord);
		game.addEntity(hero1);
		game.setHero(hero1);
		gameMap.setHero(hero1);
		hero.setEnabled(false);
		elementToAdd = "";
	}
	
	private void placeWall(Coordinate coord) {
		if (gameMap.coordHasSomething(coord)) {
			return;
		}
		game.addEntity(new Wall(coord));
	}
	
	private void placeDoor(Coordinate coord) {
		if (!gameMap.coordBlocksMovement(coord)) {
			return;
		}
		gameMap.removeCoord(coord);
		game.addEntity(new Door(coord));
		if (coord.getX() == 0 || coord.getY() == 0 || coord.getX() == yValue - 1 || coord.getY() == xValue - 1) {
			Coordinate[] c = {coord};
			game.addWinningCoords(c);
			canWin = true;
		}
	}
	
	private void placeKey(Coordinate coord) {
		if (gameMap.coordHasSomething(coord)) {
			return;
		}
		game.addEntity(new Key(coord));
	}
	
	private void placeOgre(Coordinate coord) {
		if (gameMap.coordHasSomething(coord)) {
			return;
		}
		game.addEntity(new Ogre(coord,true));
		hasAtleast1Ogre = true;
	}
	
	private void placeWeapon(Coordinate coord) {
		if (gameMap.coordHasSomething(coord)) {
			return;
		}
		game.addEntity(new Weapon(coord));
	}
	
	public Game getGame() {
		return game;
	}
	
}
