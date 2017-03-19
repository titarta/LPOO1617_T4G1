package dkeep.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;

import org.junit.rules.DisableOnDebug;

import dkeep.logic.*;
import dkeep.logic.Generic.Coordinate;
import dkeep.logic.Generic.Generic.Direction;

import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;

public class DKeepGUI {

	private JFrame frame;
	private ArrayList<Game> game;
	private int level;
	private ActionListener disableButtons;
	private ActionListener enableButtons;
	private ActionListener winGame;
	private ActionListener loseGame;
	private int guardType;
	
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
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JTextArea gameOutput = new JTextArea();
		gameOutput.setFont(new Font("Courier New", Font.PLAIN, 13));
		gameOutput.setBounds(22, 59, 238, 166);
		frame.getContentPane().add(gameOutput);
		
		JLabel gameStatus = new JLabel("");
		gameStatus.setBounds(22, 236, 215, 14);
		frame.getContentPane().add(gameStatus);
		
		JLabel lblNumberOfOgres = new JLabel("Number of Ogres");
		lblNumberOfOgres.setFont(new Font("Courier New", Font.PLAIN, 11));
		lblNumberOfOgres.setBounds(22, 11, 110, 14);
		frame.getContentPane().add(lblNumberOfOgres);
		
		JTextField numberOfOgresTextField = new JTextField();
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
				gameStatus.setText("");
				enableButtons.actionPerformed(null);
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
				HashSet<Door> doors = new HashSet<Door>();
				Door door1 = new Door(new Coordinate(0,5));
				Door door2 = new Door(new Coordinate(0,6));
				doors.add(door1);
				doors.add(door2);
				Lever lever1 = new Lever(new Coordinate(7, 8), doors);
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
				gameOutput.setText(game.get(level) + "");
			}
		});
		btnNewGame.setFont(new Font("Courier New", Font.PLAIN, 11));
		btnNewGame.setBounds(300, 60, 90, 25);
		frame.getContentPane().add(btnNewGame);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setFont(new Font("Courier New", Font.PLAIN, 11));
		btnExit.setBounds(300, 200, 90, 25);
		frame.getContentPane().add(btnExit);
		
		JButton btnUp = new JButton("Up");
		btnUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					game.get(level).updateGame(Direction.UP);
					gameOutput.setText(game.get(level) + "");
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
		btnUp.setBounds(310, 110, 70, 15);
		frame.getContentPane().add(btnUp);
		
		JButton btnLeft = new JButton("Left");
		btnLeft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					game.get(level).updateGame(Direction.LEFT);
					gameOutput.setText(game.get(level) + "");
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
		btnLeft.setBounds(270, 130, 70, 15);
		frame.getContentPane().add(btnLeft);
		
		JButton btnRight = new JButton("Right");
		btnRight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					game.get(level).updateGame(Direction.RIGHT);
					gameOutput.setText(game.get(level) + "");
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
		btnRight.setBounds(350, 130, 70, 15);
		frame.getContentPane().add(btnRight);
		
		JButton btnDown = new JButton("Down");
		btnDown.setFont(new Font("Courier New", Font.PLAIN, 11));
		btnDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					game.get(level).updateGame(Direction.DOWN);
					gameOutput.setText(game.get(level) + "");
				} catch (GameEndException e1) {
					if (e1.getResult()) {
						winGame.actionPerformed(null);
					} else {
						loseGame.actionPerformed(null);
					}
				}
			}
		});
		btnDown.setBounds(310, 150, 70, 15);
		frame.getContentPane().add(btnDown);
		
		disableButtons = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnUp.setEnabled(false);
				btnDown.setEnabled(false);
				btnLeft.setEnabled(false);
				btnRight.setEnabled(false);
				
			}
		};
		
		enableButtons = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnUp.setEnabled(true);
				btnDown.setEnabled(true);
				btnLeft.setEnabled(true);
				btnRight.setEnabled(true);
				
			}
		};
		
		winGame = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gameOutput.setText(game.get(level) + "");
				gameStatus.setText("You won!");
				disableButtons.actionPerformed(null);
			}
		};
		
		loseGame = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gameOutput.setText(game.get(level) + "");
				gameStatus.setText("You lost...");
				disableButtons.actionPerformed(null);
			}
		};
		
	}
	
}
