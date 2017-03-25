package dkeep.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JTextField;

import org.junit.rules.DisableOnDebug;

import dkeep.logic.*;
import dkeep.logic.Generic.Coordinate;
import dkeep.logic.Generic.Generic.Direction;

import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JPanel;

public class DKeepGUI {

	private JFrame frame;
	private ArrayList<Game> game;
	private int level;
	private ActionListener disableButtons;
	private ActionListener enableButtons;
	private ActionListener winGame;
	private ActionListener loseGame;
	private ActionListener startGame;
	private int guardType;
	private int numberOgres;
	private GamePanel gamePanel;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DKeepGUI window = new DKeepGUI();
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
	public DKeepGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		game = new ArrayList<Game>();
		
		/**
		 *  SWING Interface
		 */
		
		
		frame = new JFrame();
		frame.setBounds(100, 100, 630, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		
		
		
		
		JLabel gameStatus = new JLabel("");
		gameStatus.setBounds(22, 419, 215, 14);
		frame.getContentPane().add(gameStatus);
		
		JLabel lblNumberOfOgres = new JLabel("Number of Ogres");
		lblNumberOfOgres.setFont(new Font("Courier New", Font.PLAIN, 11));
		lblNumberOfOgres.setBounds(22, 11, 110, 14);
		frame.getContentPane().add(lblNumberOfOgres);
		
		JTextField numberOfOgresTextField = new JTextField();
		numberOfOgresTextField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					numberOgres = Integer.parseInt(numberOfOgresTextField.getText());
				} catch (NumberFormatException n) {
					numberOgres = 1;
				}
				if (numberOgres > 5) { numberOgres = 5;};
				if (numberOgres < 0) { numberOgres = 0;};
			}
		});
		numberOfOgresTextField.setFont(new Font("Courier New", Font.PLAIN, 11));
		numberOfOgresTextField.setBounds(155, 7, 41, 20);
		frame.getContentPane().add(numberOfOgresTextField);
		numberOfOgresTextField.setColumns(10);
		
		JLabel lblGuardPersonality = new JLabel("Guard personality");
		lblGuardPersonality.setFont(new Font("Courier New", Font.PLAIN, 11));
		lblGuardPersonality.setBounds(22, 36, 119, 14);
		frame.getContentPane().add(lblGuardPersonality);
		
		JComboBox guardPersonalityComboBox = new JComboBox();
		guardPersonalityComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guardType = guardPersonalityComboBox.getSelectedIndex();
			}
		});
		guardPersonalityComboBox.setModel(new DefaultComboBoxModel(new String[] {"Rookie", "Suspicious", "Drunken"}));
		guardPersonalityComboBox.setSelectedIndex(0);
		guardPersonalityComboBox.setFont(new Font("Courier New", Font.PLAIN, 11));
		guardPersonalityComboBox.setBounds(155, 32, 95, 20);
		frame.getContentPane().add(guardPersonalityComboBox);
		
		JButton btnNewGame = new JButton("New Game");
		btnNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				enableButtons.actionPerformed(null);
				startGame.actionPerformed(null);
				gamePanel.setGame(game.get(level));
				gamePanel.setBounds(30, 65, 350, 385);
				gamePanel.setVisible(true);
				frame.getContentPane().add(gamePanel);
				enableButtons.actionPerformed(null);
				
				gamePanel.paintComponent(gamePanel.getGraphics());
				gamePanel.requestFocusInWindow();
				
			}
		});
		btnNewGame.setFont(new Font("Courier New", Font.PLAIN, 11));
		btnNewGame.setBounds(450, 50, 90, 25);
		frame.getContentPane().add(btnNewGame);
		
		JButton createLevel = new JButton("Create Level");
		createLevel.setBounds(410, 300, 170, 50);
		createLevel.setFont(new Font("Courier New", Font.PLAIN, 11));
		frame.getContentPane().add(createLevel);
		createLevel.addActionListener(new ActionListener() {
			
			
			@Override
			public void actionPerformed(ActionEvent e) {
				createLevelWindow();
				
			}
		});
		
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setFont(new Font("Courier New", Font.PLAIN, 11));
		btnExit.setBounds(450, 240, 90, 25);
		frame.getContentPane().add(btnExit);
		
		JButton btnUp = new JButton("Up");
		btnUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					game.get(level).updateGame(Direction.UP);
					gamePanel.paintComponent(gamePanel.getGraphics());
					gamePanel.requestFocusInWindow();
				} catch (GameEndException e1) {
					if (e1.getResult()) {
						winGame.actionPerformed(null);
					} else {
						loseGame.actionPerformed(null);
					}
				}
			}
		});
		btnUp.setFont(new Font("Courier New", Font.PLAIN, 11));
		btnUp.setBounds(460, 100, 70, 30);
		frame.getContentPane().add(btnUp);
		
		
		
		JButton btnLeft = new JButton("Left");
		btnLeft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					game.get(level).updateGame(Direction.LEFT);
					gamePanel.paintComponent(gamePanel.getGraphics());
					gamePanel.requestFocusInWindow();
				} catch (GameEndException e1) {
					if (e1.getResult()) {
						winGame.actionPerformed(null);
					} else {
						loseGame.actionPerformed(null);
					}
				}
			}
		});
		btnLeft.setFont(new Font("Courier New", Font.PLAIN, 11));
		btnLeft.setBounds(420, 140, 70, 30);
		frame.getContentPane().add(btnLeft);
		
		JButton btnRight = new JButton("Right");
		btnRight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					game.get(level).updateGame(Direction.RIGHT);
					gamePanel.paintComponent(gamePanel.getGraphics());
					gamePanel.requestFocusInWindow();
				} catch (GameEndException e1) {
					if (e1.getResult()) {
						winGame.actionPerformed(null);
					} else {
						loseGame.actionPerformed(null);
					}
				}
			}
		});
		btnRight.setFont(new Font("Courier New", Font.PLAIN, 11));
		btnRight.setBounds(500, 140, 70, 30);
		frame.getContentPane().add(btnRight);
		
		JButton btnDown = new JButton("Down");
		btnDown.setFont(new Font("Courier New", Font.PLAIN, 11));
		btnDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					game.get(level).updateGame(Direction.DOWN);
					gamePanel.paintComponent(gamePanel.getGraphics());
					gamePanel.requestFocusInWindow();
				} catch (GameEndException e1) {
					if (e1.getResult()) {
						winGame.actionPerformed(null);
					} else {
						loseGame.actionPerformed(null);
					}
				}
			}
		});
		btnDown.setBounds(460, 180, 70, 30);
		frame.getContentPane().add(btnDown);
		
		
		disableButtons = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnUp.setEnabled(false);
				btnDown.setEnabled(false);
				btnLeft.setEnabled(false);
				btnRight.setEnabled(false);
				gamePanel.setEnabled(false);
			}
		};
		
		enableButtons = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnUp.setEnabled(true);
				btnDown.setEnabled(true);
				btnLeft.setEnabled(true);
				btnRight.setEnabled(true);
				gamePanel.setEnabled(true);
			}
		};
		
		winGame = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gamePanel.paintComponent(gamePanel.getGraphics());
				if (level == game.size() - 1) {
					gameStatus.setText("You won!");
					disableButtons.actionPerformed(null);
					gamePanel.requestFocusInWindow();
				} else {
					level++;
					gamePanel.setGame(game.get(level));
					gamePanel.paintComponent(gamePanel.getGraphics());
					gameStatus.setText("");
					gamePanel.requestFocusInWindow();
				}
				
			}
		};
		
		loseGame = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gamePanel.paintComponent(gamePanel.getGraphics());
				gameStatus.setText("You lost...");
				disableButtons.actionPerformed(null);
				gamePanel.requestFocusInWindow();
			}
		};
		
		startGame = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				gameStatus.setText("");
				game.clear();
				level = 0;
				
				char map1[][] = {{'X','X','X','X','X','X','X','X','X','X'},
						{'X','H',' ',' ','I',' ','X',' ',' ','X'},
						{'X','X','X',' ','X','X','X',' ',' ','X'},
						{'X',' ','I',' ','I',' ','X',' ',' ','X'},
						{'X','X','X',' ','X','X','X',' ',' ','X'},
						{' ',' ',' ',' ',' ',' ',' ',' ',' ','X'},
						{' ',' ',' ',' ',' ',' ',' ',' ',' ','X'},
						{'X','X','X',' ','X','X','X','X',' ','X'},
						{'X',' ','I',' ','I',' ','X',' ',' ','X'},
						{'X','X','X','X','X','X','X','X','X','X'}
						};
				
				
				Game level1 = new Game(map1);
				HashSet<Door> doors1 = new HashSet<Door>();
				Door door1 = new Door(new Coordinate(0,5));
				Door door2 = new Door(new Coordinate(0,6));
				doors1.add(door1);
				doors1.add(door2);
				Lever lever1 = new Lever(new Coordinate(7, 8), doors1);
				level1.addEntity(door1);
				level1.addEntity(door2);
				level1.addEntity(lever1);
				Guard guard1;
				if (guardType == 0) {
					guard1 = new Rookie(new Coordinate(8, 1));
				} else if (guardType == 1) {
					guard1 = new Suspicious(new Coordinate(8, 1));
				} else if (guardType == 2) {
					guard1 = new Drunken(new Coordinate(8, 1));
				} else {
					guard1 = new Rookie(new Coordinate(8, 1));
				}
				Direction guardMovement[] = { Direction.LEFT, Direction.DOWN, Direction.DOWN, Direction.DOWN, Direction.DOWN,
						Direction.LEFT, Direction.LEFT, Direction.LEFT, Direction.LEFT, Direction.LEFT, Direction.LEFT,
						Direction.DOWN, Direction.RIGHT, Direction.RIGHT, Direction.RIGHT, Direction.RIGHT, Direction.RIGHT,
						Direction.RIGHT, Direction.RIGHT, Direction.UP, Direction.UP, Direction.UP, Direction.UP, Direction.UP };
				guard1.setWalkPath(guardMovement);
				level1.addEntity(guard1);
				Coordinate[] winningCoords1 = {new Coordinate(0,5),new Coordinate(0,6)};
				level1.addWinningCoords(winningCoords1);
				game.add(level1);
				
				char map2[][] = { { 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' },
						{ 'I', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' }, { 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
						{ 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' }, { 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
						{ 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' }, { 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
						{ 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' }, { 'X', 'H', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
						{ 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' } };
				
				Game level2 = new Game(map2);
				Key key1 = new Key(new Coordinate(8, 1));
				level2.addEntity(key1);
				for (int i = 0; i < numberOgres; i++) {
					Ogre ogre = new Ogre(new Coordinate(4, 1), true);
					level2.addEntity(ogre);
					level2.addEntity(ogre.getClub());
				}
				Coordinate[] winningCoords2 = {new Coordinate(0,1)};
				level2.addWinningCoords(winningCoords2);
				level2.addEntity(new Weapon(new Coordinate(2,8)));
				game.add(level2);
				
			}
		};
		
		
		startGame.actionPerformed(null);
		gamePanel = new GamePanel(game.get(level));
		gamePanel.setBounds(30, 65, 320, 320);
		gamePanel.setVisible(true);
		enableButtons.actionPerformed(null);
		
		gamePanel.requestFocusInWindow();
		
		gamePanel.addKeyListener(new KeyListener() {
				@Override
				public void keyTyped(KeyEvent e) {}
				
				@Override
				public void keyReleased(KeyEvent e) {}
				
				@Override
				public void keyPressed(KeyEvent e) {
					 switch(e.getKeyCode()){
					 
				        case KeyEvent.VK_LEFT:
				        	btnLeft.getActionListeners()[0].actionPerformed(null);
				            break;
				        case KeyEvent.VK_RIGHT:
				        	btnRight.getActionListeners()[0].actionPerformed(null);
				            break;
				        case KeyEvent.VK_UP:
				        	btnUp.getActionListeners()[0].actionPerformed(null);
				            break;
				        case KeyEvent.VK_DOWN:
				        	btnDown.getActionListeners()[0].actionPerformed(null);
				            break;
				        }
				}
			});
		
		
		
		
	}
	
	public void addGame(Game game) {
		this.game.add(game);
	}
	
	public void createLevelWindow() {
		LevelEditor levelEditor= new LevelEditor();
		levelEditor.main(null);
		levelEditor.setFrame(this);
	}
}
