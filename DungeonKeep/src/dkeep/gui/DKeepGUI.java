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
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JPanel;

public class DKeepGUI {

	private JFrame frame;
	private LevelEditor levelEditor;
	private ArrayList<Game> game;
	private ArrayList<Game> createdLevels;
	private int level;
	private int storedLevel;
	private JButton btnLeft;
	private JButton btnRight;
	private JButton btnDown;
	private JButton btnUp;
	private JButton createLevel;
	private JComboBox guardPersonalityComboBox;
	private JLabel lblNumberOfOgres;
	private JTextField numberOfOgresTextField;
	private JLabel lblGuardPersonality;
	private JButton btnNewGame;
	private JButton btnExit;
	private JButton btnSave;
	private JButton btnLoad;
	private int guardType;
	private int numberOgres;
	private GamePanel gamePanel;
	private JLabel gameStatus;
	
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
		levelEditor = new LevelEditor(this);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		initializeFrame();
		createButtons();
		createGameStatus();
		createTextField();
		createComboBox();
		createGuardPersonalityLabel();
		createNumberOgresLabel();
		createdLevels = new ArrayList<Game>();
		startGame();
		generateGamePanel();
		disableButtons();
	}
	
	private void createButtons() {
		createButtonUP();
		createButtonDOWN();
		createButtonLEFT();
		createButtonRIGHT();
		createCreateLevelButton();
		createButtonExit();
		createNewGameButton();
		createSaveStateButton();
		createLoadStateButton();
	}
	
	private void createCreateLevelButton() {
		createLevel = new JButton("Create Level");
		createLevel.setBounds(510, 300, 170, 50);
		createLevel.setFont(new Font("Courier New", Font.PLAIN, 11));
		frame.getContentPane().add(createLevel);
		createLevel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				createLevelWindow();
			}
		});
	}
	
	private void createButtonExit() {
		btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setFont(new Font("Courier New", Font.PLAIN, 11));
		btnExit.setBounds(550, 240, 90, 25);
		frame.getContentPane().add(btnExit);
	}
	
	private void createNewGameButton() {
		btnNewGame = new JButton("New Game");
		btnNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				enableButtons();
				gameStatus.setText("");
				try {
					resetGame();
				} catch (CloneNotSupportedException e1) {
					e1.printStackTrace();
				}
				gamePanel.setGame(game.get(level));
				gamePanel.setVisible(true);
				frame.getContentPane().add(gamePanel);
				enableButtons();
				gamePanel.paintComponent(gamePanel.getGraphics());
				gamePanel.requestFocusInWindow();
				
			}
		});
		btnNewGame.setFont(new Font("Courier New", Font.PLAIN, 11));
		btnNewGame.setBounds(550, 50, 90, 25);
		frame.getContentPane().add(btnNewGame);
	}
	
	private void disableButtons() {
		btnUp.setEnabled(false);
		btnDown.setEnabled(false);
		btnLeft.setEnabled(false);
		btnRight.setEnabled(false);
		gamePanel.setEnabled(false);
		btnSave.setEnabled(false);
	}
	
	private void enableButtons() {
		btnUp.setEnabled(true);
		btnDown.setEnabled(true);
		btnLeft.setEnabled(true);
		btnRight.setEnabled(true);
		gamePanel.setEnabled(true);
		btnSave.setEnabled(true);
	}
	
	private void generateGamePanel() {
		gamePanel = new GamePanel(game.get(level));
		gamePanel.setBounds(30, 65, 320, 320);
		gamePanel.setVisible(true);
		enableButtons();
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
	
	private void createComboBox() {
		guardPersonalityComboBox = new JComboBox();
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
	}
	
	private void createGameStatus() {
		gameStatus = new JLabel("");
		gameStatus.setBounds(30, 500, 200, 15);
		frame.getContentPane().add(gameStatus);
	}
	
	private void initializeFrame() {
		frame = new JFrame();
		frame.setBounds(100, 100, 700, 550);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
	}
	
	private void createNumberOgresLabel() {
		lblNumberOfOgres = new JLabel("Number of Ogres");
		lblNumberOfOgres.setFont(new Font("Courier New", Font.PLAIN, 11));
		lblNumberOfOgres.setBounds(22, 11, 110, 14);
		frame.getContentPane().add(lblNumberOfOgres);
	}
	
	private void createTextField() {
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
	}

	private void createGuardPersonalityLabel() {
		lblGuardPersonality = new JLabel("Guard personality");
		lblGuardPersonality.setFont(new Font("Courier New", Font.PLAIN, 11));
		lblGuardPersonality.setBounds(22, 36, 119, 14);
		frame.getContentPane().add(lblGuardPersonality);
	}
	
	public void addGame(Game game) {
		this.createdLevels.add(game);
	}
	
	private void createLevelWindow() {
		levelEditor.start();
	}
	
	private void loseGame() {
		gamePanel.paintComponent(gamePanel.getGraphics());
		gameStatus.setText("You lost...");
		disableButtons();
		gamePanel.requestFocusInWindow();
	}
	
	private void winGame() {
		gamePanel.paintComponent(gamePanel.getGraphics());
		if (level == game.size() - 1) {
			gameStatus.setText("You won!");
			disableButtons();
			gamePanel.requestFocusInWindow();
		} else {
			level++;
			gamePanel.setGame(game.get(level));
			gamePanel.setBounds(30, 65, 32*game.get(level).getGameMap().getX(), 32*game.get(level).getGameMap().getY());
			gamePanel.cleanBoard(gamePanel.getGraphics());
			gamePanel.paintComponent(gamePanel.getGraphics());
			gameStatus.setText("");
			gamePanel.requestFocusInWindow();
		}
	}
	
	private void startGame1() {
		char map1[][] = {{'X','X','X','X','X','X','X','X','X','X'},{'X','H',' ',' ','I',' ','X',' ',' ','X'},{'X','X','X',' ','X','X','X',' ',' ','X'},{'X',' ','I',' ','I',' ','X',' ',' ','X'},{'X','X','X',' ','X','X','X',' ',' ','X'},
				{' ',' ',' ',' ',' ',' ',' ',' ',' ','X'},{' ',' ',' ',' ',' ',' ',' ',' ',' ','X'},{'X','X','X',' ','X','X','X','X',' ','X'},{'X',' ','I',' ','I',' ','X',' ',' ','X'},{'X','X','X','X','X','X','X','X','X','X'}};
		
		Game level1 = new Game(map1);
		HashSet<Door> doors1 = new HashSet<Door>();
		Door door1 = new Door(new Coordinate(5,0));
		Door door2 = new Door(new Coordinate(6,0));
		doors1.add(door1);
		doors1.add(door2);
		Lever lever1 = new Lever(new Coordinate(8, 7), doors1);
		level1.addEntity(door1);
		level1.addEntity(door2);
		level1.addEntity(lever1);
		Guard guard1;
		if (guardType == 0) {
			guard1 = new Rookie(new Coordinate(1, 8));
		} else if (guardType == 1) {
			guard1 = new Suspicious(new Coordinate(1, 8));
		} else if (guardType == 2) {
			guard1 = new Drunken(new Coordinate(1, 8));
		} else {
			guard1 = new Rookie(new Coordinate(1, 8));
		}
		Direction guardMovement[] = { Direction.LEFT, Direction.DOWN, Direction.DOWN, Direction.DOWN, Direction.DOWN,Direction.LEFT, Direction.LEFT, Direction.LEFT, Direction.LEFT, Direction.LEFT, Direction.LEFT,Direction.DOWN, Direction.RIGHT, Direction.RIGHT, Direction.RIGHT, Direction.RIGHT, Direction.RIGHT,Direction.RIGHT, Direction.RIGHT, Direction.UP, Direction.UP, Direction.UP, Direction.UP, Direction.UP };
		guard1.setWalkPath(guardMovement);
		level1.addEntity(guard1);
		Coordinate[] winningCoords1 = {new Coordinate(5,0),new Coordinate(6,0)};
		level1.addWinningCoords(winningCoords1);
		game.add(level1);
	}
	
	private void startGame2() {
		char map2[][] = { { 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' },{ 'I', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' }, { 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },{ 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' }, { 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },{ 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' }, { 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },{ 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' }, { 'X', 'H', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },{ 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' } };
		
		Game level2 = new Game(map2);
		Key key1 = new Key(new Coordinate(1, 8));
		level2.addEntity(key1);
		for (int i = 0; i < numberOgres; i++) {
			Ogre ogre = new Ogre(new Coordinate(1, 4), true);
			level2.addEntity(ogre);
			level2.addEntity(ogre.getClub());
		}
		Coordinate[] winningCoords2 = {new Coordinate(1,0)};
		level2.addWinningCoords(winningCoords2);
		level2.addEntity(new Weapon(new Coordinate(8,2)));
		game.add(level2);
	}
	
	private void startGame() {
		game = new ArrayList<Game>();
		level = 0;
		startGame1();
		startGame2();
	}

	private void resetGame() throws CloneNotSupportedException {
		startGame();
		for (Game g : createdLevels) {
			game.add(new Game(new GameMap(g.getGameMap().getCoordToEntityMap(),g.getGameMap().getWinningCoords(),(Hero) g.getHero(),g.getGameMap().getX(),g.getGameMap().getY())));
		}
		gamePanel.setBounds(30, 65, 320, 320);
	}
	
	private void btnFunction(Direction d) {
		try {
			game.get(level).updateGame(d);
			gamePanel.paintComponent(gamePanel.getGraphics());
			gamePanel.requestFocusInWindow();
		} catch (GameEndException e1) {
			if (e1.getResult()) {
				winGame();
			} else {
				loseGame();
			}
		}
	}
	
	private void createButtonUP() {
		btnUp = new JButton("Up");
		btnUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnFunction(Direction.UP);
			}
		});
		btnUp.setFont(new Font("Courier New", Font.PLAIN, 11));
		btnUp.setBounds(560, 100, 70, 30);
		frame.getContentPane().add(btnUp);
	}
	
	private void createButtonDOWN() {
		btnDown = new JButton("Down");
		btnDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnFunction(Direction.DOWN);
			}
		});
		btnDown.setFont(new Font("Courier New", Font.PLAIN, 11));
		btnDown.setBounds(560, 180, 70, 30);
		frame.getContentPane().add(btnDown);
	}
	
	private void createButtonLEFT() {
		btnLeft = new JButton("Left");
		btnLeft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnFunction(Direction.LEFT);
			}
		});
		btnLeft.setFont(new Font("Courier New", Font.PLAIN, 11));
		btnLeft.setBounds(520, 140, 70, 30);
		frame.getContentPane().add(btnLeft);
	}
	
	private void createButtonRIGHT() {
		btnRight = new JButton("Right");
		btnRight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnFunction(Direction.RIGHT);
			}
		});
		btnRight.setFont(new Font("Courier New", Font.PLAIN, 11));
		btnRight.setBounds(600, 140, 70, 30);
		frame.getContentPane().add(btnRight);
	}

	private void createSaveStateButton() {
		btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveState();
				gamePanel.requestFocusInWindow();
			}
		});
		btnSave.setFont(new Font("Courier New", Font.PLAIN, 11));
		btnSave.setBounds(300, 10, 90, 40);
		frame.getContentPane().add(btnSave);
	}
	
	private void createLoadStateButton() {
		btnLoad = new JButton("Load");
		btnLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadStatestartGame();
				gamePanel.requestFocusInWindow();
			}
		});
		btnLoad.setFont(new Font("Courier New", Font.PLAIN, 11));
		btnLoad.setBounds(400, 10, 90, 40);
		frame.getContentPane().add(btnLoad);
	}
	
	private void saveState() {
		storedLevel = level;
		try {
			FileOutputStream fileOut =  new FileOutputStream("src/tmp/gameState.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(game);
			out.close();
			fileOut.close();
		} catch (IOException i) {
			System.out.print("Couldn't save the game");
		}
	}
	
	private void loadState() {
		try {
	         FileInputStream fileIn = new FileInputStream("src/tmp/gameState.ser");
	         ObjectInputStream in = new ObjectInputStream(fileIn);
	         game = (ArrayList<Game>)in.readObject();
	         in.close();
	         fileIn.close();
	      }catch(IOException | ClassNotFoundException i) {
	         i.printStackTrace();
	         return;
	      }
	}
	
	private void loadStatestartGame() {
		enableButtons();
		gamePanel.setBounds(30, 65, 320, 320);
		frame.getContentPane().add(gamePanel);
		gameStatus.setText("");
		game = new ArrayList<Game>();
		level = storedLevel;
		loadState();
		gamePanel.setGame(game.get(level));
		gamePanel.setVisible(true);
		gamePanel.cleanBoard(gamePanel.getGraphics());
		gamePanel.paintComponent(gamePanel.getGraphics());
	}
}
