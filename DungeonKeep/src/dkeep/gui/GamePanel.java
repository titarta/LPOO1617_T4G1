package dkeep.gui;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import dkeep.logic.Game;

public class GamePanel extends JPanel{
	private static final long serialVersionUID = 1L;
	
	int tile = 32;
	private BufferedImage floor;
	private BufferedImage armedHero;
	private BufferedImage hero;
	private BufferedImage armedHeroWithKey;
	private BufferedImage heroWithKey;
	private BufferedImage closedDoor;
	private BufferedImage openedDoor;
	private BufferedImage guard;
	private BufferedImage sleepingGuard;
	private BufferedImage weapon;
	private BufferedImage ogre;
	private BufferedImage stunnedOgre;
	private BufferedImage wall;
	private BufferedImage key;
	private BufferedImage club;
	private BufferedImage lever;
	private Game game;
	
	
	
	public GamePanel(Game game) {
		super();
		this.game = game;
		
		try {
		hero = ImageIO.read(new File("src/dkeep/gui/Images/hero.png"));
		
		armedHero = ImageIO.read(new File("src/dkeep/gui/Images/armedHero.png"));
		
		armedHeroWithKey = ImageIO.read(new File("src/dkeep/gui/Images/armedHeroWithKey.png"));
		
		heroWithKey = ImageIO.read(new File("src/dkeep/gui/Images/heroWithKey.png"));
		
		floor = ImageIO.read(new File("src/dkeep/gui/Images/floor.png"));
		
		closedDoor = ImageIO.read(new File("src/dkeep/gui/Images/closedDoor.png"));
		
		openedDoor = ImageIO.read(new File("src/dkeep/gui/Images/openDoor.png"));
		
		guard = ImageIO.read(new File("src/dkeep/gui/Images/guard.png"));
		
		sleepingGuard = ImageIO.read(new File("src/dkeep/gui/Images/guardSleeping.png"));
		
		weapon = ImageIO.read(new File("src/dkeep/gui/Images/weapon.png"));
		
		ogre = ImageIO.read(new File("src/dkeep/gui/Images/ogre.png"));
		
		stunnedOgre = ImageIO.read(new File("src/dkeep/gui/Images/ogreStunned.png"));
		
		wall = ImageIO.read(new File("src/dkeep/gui/Images/wall.png"));
		
		key = ImageIO.read(new File("src/dkeep/gui/Images/key.png"));
		
		lever = ImageIO.read(new File("src/dkeep/gui/Images/lever.png"));
		
		armedHeroWithKey = ImageIO.read(new File("src/dkeep/gui/Images/armedHeroWithKey.png"));
		
		club = ImageIO.read(new File("src/dkeep/gui/Images/club.png"));
		} catch (IOException e) {
			System.out.print("Couldn't load textures");
		}
	}
	
	public void setGame(Game game) {
		this.game = game;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		String gameString = game.toString();
		
		for (int i = 0; i < game.getGameMap().getY(); i++) {
			for (int j = 0; j < game.getGameMap().getX(); j++) {
				switch (gameString.getBytes()[i*game.getGameMap().getX() + j]) {
				case ' ':
					g.drawImage(floor, i*tile, j*tile, tile, tile, null);
					break;
				case 'w':
					g.drawImage(weapon,  i*tile,j*tile, tile, tile, null);
					break;
				case 'H':
					g.drawImage(hero,  i*tile,j*tile, tile, tile, null);
					break;
				case 'K':
					g.drawImage(heroWithKey, i*tile,j*tile, tile, tile, null);
					break;
				case 'A':
					g.drawImage(armedHero, i*tile, j*tile, tile, tile, null);
					break;
				case 'O':
					g.drawImage(ogre, i*tile, j*tile, tile, tile, null);
					break;
				case '*':
					g.drawImage(club, i*tile, j*tile, tile, tile, null);
					break;
				case 'G':
					g.drawImage(guard, i*tile, j*tile, tile, tile, null);
					break;
				case 'g':
					g.drawImage(sleepingGuard, i*tile, j*tile, tile, tile, null);
					break;
				case 'X':
					g.drawImage(wall, i*tile, j*tile, tile, tile, null);
					break;
				case 'k':
					g.drawImage(key, i*tile, j*tile, tile, tile, null);
					break;
				case 'I':
					g.drawImage(closedDoor, i*tile, j*tile, tile, tile, null);
					break;
				case 'S':
					g.drawImage(openedDoor, i*tile, j*tile, tile, tile, null);
					break;
				case '8':
					g.drawImage(stunnedOgre, i*tile, j*tile, tile, tile, null);
					break;
				case 's':
					g.drawImage(armedHeroWithKey, i*tile, j*tile, tile, tile, null);
					break;
				case 'l':
					g.drawImage(lever, i*tile, j*tile, tile, tile, null);
					break;
				}
			}
		}
		
		
	}

	
	
	
	

}
