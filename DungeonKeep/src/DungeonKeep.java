import java.util.Scanner;
import static java.lang.Math.*;
import java.util.Random;

public class DungeonKeep {
	
	public enum Direction {
		UP, DOWN, LEFT, RIGHT
	}

	public class Entity {
		int x;
		int y;
		char entityChar;
		
		public char getChar() {
			return entityChar;
		}
		public int getX() {
			return x;
		}
		public int getY() {
			return y;
		}
		public boolean moveUp() {
			if(map[y-1][x] != 'X')
			{
				y = y - 1;
				return true;
			}
			return false;
		}
		public boolean moveDown() {
			if(map[y+1][x] != 'X')
			{
				y = y + 1;
				return true;
			}
			return false;
		}
		public boolean moveLeft() {
			if(map[y][x-1] != 'X')
			{
				x = x - 1;
				return true;
			}
			return false;
		}
		public boolean moveRight() {
			if(map[y][x+1] != 'X')
			{
				x = x + 1;
				return true;
			}
			return false;
		}
	}
	
	public class Hero extends Entity{
		public Hero(int beginX,int beginY)
		{
			x = beginX;
			y = beginY;
			entityChar = 'H';
		}
	}
	
	public class Guard extends Entity{
		public Guard(int beginX,int beginY)
		{
			x = beginX;
			y = beginY;
			entityChar = 'G';
		}
	}
	
	public class Ogre extends Entity{
		public Ogre(int beginX,int beginY)
		{
			x = beginX;
			y = beginY;
			entityChar = 'O';
		}
	}
	
	public class Door extends Entity {
		boolean state;
		public Door(int beginX, int beginY, boolean stateBegin) { //true - open; false - closed
			x = beginX;
			y = beginY;
			state = stateBegin;
			if (stateBegin) {
				entityChar = 'S';
			} else {
				entityChar = 'I';
			}
		}
		public void openDoor() {
			 state = true;
			 entityChar = 'S';
		}
		public void closeDoor() {
			 state = false;
			 entityChar = 'I';
		}
		public boolean getState() {
			return state;
		}
	}
	
	public class Key extends Entity {
		public Key(int beginX, int beginY) {
			x = beginX;
			y = beginY;
			entityChar = 'k';
		}
	}
	
	public class Club extends Entity{
		public Club() {
			entityChar = '*';
			x = -1;
			y = -1;
		}
		public void setPosition(int newX,int newY) {
			x = newX;
			y = newY;
		}
	}
	
	Direction guardMovement[] = {Direction.LEFT,Direction.DOWN,Direction.DOWN,Direction.DOWN,Direction.DOWN,Direction.LEFT,Direction.LEFT,Direction.LEFT,Direction.LEFT,Direction.LEFT,Direction.LEFT,Direction.DOWN,Direction.RIGHT,Direction.RIGHT,Direction.RIGHT,Direction.RIGHT,Direction.RIGHT,Direction.RIGHT,Direction.RIGHT,Direction.UP,Direction.UP,Direction.UP,Direction.UP,Direction.UP};

//	char map[][] = {
//			{'X','X','X','X','X','X','X','X','X','X'},
//			{'X',' ',' ',' ',' ',' ','X',' ',' ','X'},
//			{'X','X','X',' ','X','X','X',' ',' ','X'},
//			{'X',' ',' ',' ',' ',' ','X',' ',' ','X'},
//			{'X','X','X',' ','X','X','X',' ',' ','X'},
//			{' ',' ',' ',' ',' ',' ',' ',' ',' ','X'},
//			{' ',' ',' ',' ',' ',' ',' ',' ',' ','X'},
//			{'X','X','X',' ','X','X','X','X',' ','X'},
//			{'X',' ',' ',' ',' ',' ','X',' ',' ','X'},
//			{'X','X','X','X','X','X','X','X','X','X'}
//			};
//	
	Hero hero1 = new Hero(1,1);
	Guard guard1 = new Guard(8,1);
	
	Door door1 = new Door(0,5,false);
	Door door2 = new Door(0,6,false);
	Door door3 = new Door(2,3,false);
	Door door4 = new Door(4,1,false);
	Door door5 = new Door(4,3,false);
	Door door6 = new Door(2,8,false);
	Door door7 = new Door(4,8,false);
	
	Key key1 = new Key(7,8);
//	
//	Entity entities[] = {guard1,hero1,door1,door2,door3,door4,door5,door6,door7,key1};
//	Door doors[] = {door1,door2,door3,door4,door5,door6,door7};
	 //-----------------Map 2 entities------------------
	char map[][] = {
			{'X','X','X','X','X','X','X','X','X','X'},
			{' ',' ',' ',' ',' ',' ',' ',' ',' ','X'},
			{'X',' ',' ',' ',' ',' ',' ',' ',' ','X'},
			{'X',' ',' ',' ',' ',' ',' ',' ',' ','X'},
			{'X',' ',' ',' ',' ',' ',' ',' ',' ','X'},
			{'X',' ',' ',' ',' ',' ',' ',' ',' ','X'},
			{'X',' ',' ',' ',' ',' ',' ',' ',' ','X'},
			{'X',' ',' ',' ',' ',' ',' ',' ',' ','X'},
			{'X',' ',' ',' ',' ',' ',' ',' ',' ','X'},
			{'X','X','X','X','X','X','X','X','X','X'}
			};
	
	Door door8 = new Door(0,1,false);
	
	Hero hero2 = new Hero(1,8);
	Ogre ogre1 = new Ogre(4,1);
	
	Key key2 = new Key(8,1);
	
	Club club1 = new Club();
	
	Entity entities[] = {ogre1,hero2,door8,club1,key2};
	Door doors[] = {door8};
	
	public void printMap()
	{
		boolean entityPos = false;
		for (int i = 0; i < 10; i++)
		{
			for (int j = 0; j < 10; j++)
			{
				entityPos = false;
				for (Entity ent : entities)
				{
					if (j == ent.getX() && i == ent.getY()) {
						System.out.print(ent.getChar());
						entityPos = true;
						break;
					}
				}
				if(!entityPos) {
					System.out.print(map[i][j]);
				}
			}
			System.out.print('\n');
		}
	}
	
	public Direction readCommand() {
		while (true)
		{
			Scanner reader = new Scanner(System.in);
			String input = reader.next();
			if (input.equals("up")){
				return Direction.UP;
			} else if (input.equals("down")){
				return Direction.DOWN;
			} else if (input.equals("left")){
				return Direction.LEFT;
			} else if (input.equals("right")) {
				return Direction.RIGHT;
			}
		}
	}
	
	public boolean checkCollisionAdjacent(Entity ent1, Entity ent2) {
		double distance = hypot((double)(ent1.getX()-ent2.getX()),(double)(ent1.getY()-ent2.getY()));
		if (distance < 1.1) {
			return true;
		} else {
			return false;
		}
	}
	public boolean checkCollisionStepOver(Entity ent1, Entity ent2) {
		return ent1.getX() == ent2.getX() && ent1.getY() == ent2.getY();
	}
	
	public void moveEntityMap(Direction command, Entity ent) {
		boolean moveAble = true;
		if (command == Direction.UP) {
			for(Door d : doors) {
				if(!d.getState() && d.getX() == ent.getX() && d.getY() == ent.getY() - 1)
				{
					moveAble = false;
					break;
				}
			}
			if (moveAble) {
				ent.moveUp();
			}
			return;
		} else if (command == Direction.DOWN) {
			for(Door d : doors) {
				if(!d.getState() && d.getX() == ent.getX() && d.getY() == ent.getY() + 1)
				{
					moveAble = false;
					break;
				}
			}
			if (moveAble) {
				ent.moveDown();
			}
			return;
		} else if (command == Direction.LEFT) {
			for(Door d : doors) {
				if(!d.getState() && d.getX() == ent.getX() - 1 && d.getY() == ent.getY())
				{
					moveAble = false;
					break;
				}
			}
			if (moveAble) {
				ent.moveLeft();
			}
			return;
		} else if (command == Direction.RIGHT) {
			for(Door d : doors) {
				if(!d.getState() && d.getX() == ent.getX() + 1 && d.getY() == ent.getY())
				{
					moveAble = false;
					break;
				}
			}
			if (moveAble) {
				ent.moveRight();
			}
			return;
		}
	}
	
	public void moveEntityRandomMap2(Entity ent) {
		Random rnd = new Random();
		Direction command;
		boolean moveAble = true;
		while(true) {
			moveAble = true;
			command = Direction.values()[rnd.nextInt(4)];
			if (command == Direction.UP) {
				if(!door8.getState() && door8.getX() == ent.getX() && door8.getY() == ent.getY() - 1 || !ent.moveUp()) {
				} else {
					return;
				}
			} else if (command == Direction.DOWN) {
				if(!door8.getState() && door8.getX() == ent.getX() && door8.getY() == ent.getY() + 1 || !ent.moveDown()) {
				} else {
					return;
				}
			} else if (command == Direction.LEFT) {
				if(!door8.getState() && door8.getX() == ent.getX() - 1 && door8.getY() == ent.getY() || !ent.moveLeft()) {
				} else {
					return;
				}
			} else if (command == Direction.RIGHT) {
				if(!door8.getState() && door8.getX() == ent.getX() + 1 && door8.getY() == ent.getY() || !ent.moveRight()) {
				} else {
					return;
				}
			}
		}
	}
	
	
	public void useClub(Ogre ogre, Club club) {
		Random rnd = new Random();
		Direction command;
		boolean moveAble = true;
		while(true) {
			moveAble = true;
			command = Direction.values()[rnd.nextInt(4)];
			if (command == Direction.UP) {
				if(!door8.getState() && door8.getX() == ogre.getX() && door8.getY() == ogre.getY() - 1 || !ogre.moveUp()) {
				} else {
					ogre.moveDown();
					club.setPosition(ogre.getX(), ogre.getY() - 1);
					return;
				}
			} else if (command == Direction.DOWN) {
				if(!door8.getState() && door8.getX() == ogre.getX() && door8.getY() == ogre.getY() + 1 || !ogre.moveDown()) {
				} else {
					ogre.moveUp();
					club.setPosition(ogre.getX(), ogre.getY() + 1);
					return;
				}
			} else if (command == Direction.LEFT) {
				if(!door8.getState() && door8.getX() == ogre.getX() - 1 && door8.getY() == ogre.getY() || !ogre.moveLeft()) {
				} else {
					ogre.moveRight();
					club.setPosition(ogre.getX() - 1, ogre.getY());
					return;
				}
			} else if (command == Direction.RIGHT) {
				if(!door8.getState() && door8.getX() == ogre.getX() + 1 && door8.getY() == ogre.getY() || !ogre.moveRight()) {
				} else {
					ogre.moveLeft();
					club.setPosition(ogre.getX() + 1, ogre.getY());
					return;
				}
			}
		}
	}
	
	public boolean game1Loop() {
		int guardStep = 0;
		Direction command;
		while(true)
		{
			printMap();
			if (checkCollisionAdjacent(hero1, guard1)) {
				return false;
			}
			command = readCommand();
			moveEntityMap(command, hero1);
			if (guardStep == guardMovement.length) {
				guardStep = 0;
			}
			moveEntityMap(guardMovement[guardStep],guard1);
			guardStep++;
			if (checkCollisionStepOver(hero1, key1)) {
				door1.openDoor();
				door2.openDoor();
			}
			if (checkCollisionStepOver(hero1, door1) || checkCollisionStepOver(hero1, door2)) {
				return true;
			}
		}
	}
	
	public boolean game2Loop() {
		Direction command;
		while(true) {
			printMap();
			if (checkCollisionAdjacent(hero2, ogre1) || checkCollisionAdjacent(hero2, club1)) {
				return false;
			}
			command = readCommand();
			moveEntityMap(command, hero2);
			moveEntityRandomMap2(ogre1);
			useClub(ogre1, club1);
			if (checkCollisionStepOver(hero2, key2)) {
				door8.openDoor();
				hero2.entityChar = 'K';
			}
			if (checkCollisionStepOver(club1, key2) || checkCollisionStepOver(ogre1, key2)) {
				key2.entityChar = '$';
			}
			if (checkCollisionStepOver(hero2, door8)) {
				return true;
			}
		}
	}
	
	
	public static void main(String[] args) {
		DungeonKeep dungeon = new DungeonKeep();
		if (dungeon.game2Loop()) {
			dungeon.printMap();
			System.out.print("You Won!");
		} else {
			System.out.print("Game Over");
		}
	}
	

}
