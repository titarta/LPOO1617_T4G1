package dkeep.gui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import dkeep.logic.Game;

public class GameEditorPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	
	private Game game;
	private BufferedImage editorFloor;
	private BufferedImage wall;
	private BufferedImage hero;
	private BufferedImage ogre;
	private BufferedImage key;
	private BufferedImage door;
	private BufferedImage weapon;
	private int tile = 32;

	public GameEditorPanel(Game game) {
		super();
		this.game = game;
		
		try {
			hero = ImageIO.read(new File("src/dkeep/gui/Images/hero.png"));
			wall = ImageIO.read(new File("src/dkeep/gui/Images/wall.png"));
			editorFloor = ImageIO.read(new File("src/dkeep/gui/Images/editorFloor.png"));
			ogre = ImageIO.read(new File("src/dkeep/gui/Images/ogre.png"));
			key = ImageIO.read(new File("src/dkeep/gui/Images/key.png"));
			door = ImageIO.read(new File("src/dkeep/gui/Images/closedDoor.png"));
			weapon = ImageIO.read(new File("src/dkeep/gui/Images/weapon.png"));
		} catch (IOException e) {
			System.out.print("Couldn't load images");
		}
	}
	
	public void setGame(Game game) {
		this.game = game;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		String gameString = game.toString();
		
		for (int i = 0; i < game.getGameMap().getY(); i++) {
			for (int j = 0; j < game.getGameMap().getX(); j++) {
				switch (gameString.getBytes()[i*game.getGameMap().getX() + j]) {
				case ' ':
					g.drawImage(editorFloor, j*tile, i*tile, tile, tile, null);
					break;
				case 'w':
					g.drawImage(weapon, j*tile, i*tile, tile, tile, null);
					break;
				case 'H':
					g.drawImage(hero, j*tile, i*tile, tile, tile, null);
					break;
				case 'k':
					g.drawImage(key, j*tile, i*tile, tile, tile, null);
					break;
				case 'I':
					g.drawImage(door, j*tile, i*tile, tile, tile, null);
					break;
				case 'O':
					g.drawImage(ogre, j*tile, i*tile, tile, tile, null);
					break;
				case 'X':
					g.drawImage(wall, j*tile, i*tile, tile, tile, null);
					break;
				}
			}
		}
		
		
	}
	
	
		
		
		

	
}
