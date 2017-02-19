import java.util.Scanner;
import static java.lang.Math.*;

public class DungeonKeep {
	

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
		public void moveUp() {}
		public void moveDown() {}
		public void moveLeft() {}
		public void moveRigth() {}
	}
	
	public class Hero extends Entity{
		public Hero(int beginX,int beginY)
		{
			x = beginX;
			y = beginY;
			entityChar = 'H';
		}
		
		public void moveUp() {
			if(map[y-1][x] != 'X')
			{
				y = y - 1;
			}
		}
		public void moveDown() {
			if(map[y+1][x] != 'X')
			{
				y = y + 1;
			}
		}
		public void moveLeft() {
			if(map[y][x-1] != 'X')
			{
				x = x - 1;
			}
		}
		public void moveRigth() {
			if(map[y][x+1] != 'X')
			{
				x = x + 1;
			}
		}
	}
	
	public class Guard extends Entity{
		public Guard(int beginX,int beginY)
		{
			x = beginX;
			y = beginY;
			entityChar = 'G';
		}
		
		public void moveUp() {
			if(map[y-1][x] != 'X')
			{
				y = y - 1;
			}
		}
		public void moveDown() {
			if(map[y+1][x] != 'X')
			{
				y = y + 1;
			}
		}
		public void moveLeft() {
			if(map[y][x-1] != 'X')
			{
				x = x - 1;
			}
		}
		public void moveRigth() {
			if(map[y][x+1] != 'X')
			{
				x = x + 1;
			}
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
	
	char map[][] = {
			{'X','X','X','X','X','X','X','X','X','X'},
			{'X',' ',' ',' ',' ',' ','X',' ',' ','X'},
			{'X','X','X',' ','X','X','X',' ',' ','X'},
			{'X',' ',' ',' ',' ',' ','X',' ',' ','X'},
			{'X','X','X',' ','X','X','X',' ',' ','X'},
			{' ',' ',' ',' ',' ',' ',' ',' ',' ','X'},
			{' ',' ',' ',' ',' ',' ',' ',' ',' ','X'},
			{'X','X','X',' ','X','X','X','X',' ','X'},
			{'X',' ',' ',' ',' ',' ','X','k',' ','X'},
			{'X','X','X','X','X','X','X','X','X','X'}
			};
	int guardMovement[] = {3,2,2,2,2,3,3,3,3,3,3,2,4,4,4,4,4,4,4,1,1,1,1,1};
	
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
	
	Entity entities[] = {guard1,hero1,door1,door2,door3,door4,door5,door6,door7,key1};
	Door doors[] = {door1,door2,door3,door4,door5,door6,door7};
	
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
			System.out.println();
		}
	}
	
	public int readCommand() {//1 - up, 2 - down, 3 - left, 4 - rigth
		while (true)
		{
			Scanner reader = new Scanner(System.in);
			String input = reader.next();
			if (input.equals("up")){
				return 1;
			} else if (input.equals("down")){
				return 2;
			} else if (input.equals("left")){
				return 3;
			} else if (input.equals("rigth")) {
				return 4;
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
	
	
	public void moveEntity(int command, Entity ent) {
		boolean moveAble = true;
		if (command == 1) {
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
		} else if (command == 2) {
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
		} else if (command == 3) {
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
		} else if (command == 4) {
			for(Door d : doors) {
				if(!d.getState() && d.getX() == ent.getX() + 1 && d.getY() == ent.getY())
				{
					moveAble = false;
					break;
				}
			}
			if (moveAble) {
				ent.moveRigth();
			}
			return;
		}
	}
	
	
	public boolean gameLoop() {
		int guardStep = 0;
		int command;
		while(true)
		{
			printMap();
			if (checkCollisionAdjacent(hero1, guard1)) {
				return false;
			}
			command = readCommand();
			moveEntity(command, hero1);
			if (guardStep == guardMovement.length) {
				guardStep = 0;
			}
			moveEntity(guardMovement[guardStep],guard1);
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
	
	
	
	
	
	
	
	public static void main(String[] args) {
		DungeonKeep dungeon = new DungeonKeep();
		if (dungeon.gameLoop()) {
			dungeon.printMap();
			System.out.print("You Won!");
		} else {
			System.out.print("Game Over");
		}
	}
	

}
