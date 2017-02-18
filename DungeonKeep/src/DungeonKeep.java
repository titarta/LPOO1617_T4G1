import java.util.Scanner;
import static java.lang.Math.*;
import java.util.Random;

public class DungeonKeep {
	

	public class Entity {
		int x;
		int y;
		char entityChar;
		public Entity(int beginX,int beginY,char entChar) {
			x = beginX;
			y = beginY;
			entityChar= entChar;
		}

		public char getChar() {
			return entityChar;
		}
		public int getX() {
			return x;
		}
		public int getY() {
			return y;
		}
		
		public void setChar(char c) {
			entityChar = c;
		}
		
		public boolean moveUp() {
			if(map1[y-1][x] != 'X')
			{
				y = y - 1;
				return true;
			}
			return false;
		}
		public boolean moveDown() {
			if(map1[y+1][x] != 'X')
			{
				y = y + 1;
				return true;
			}
			return false;
		}
		public boolean moveLeft() {
			if(map1[y][x-1] != 'X')
			{
				x = x - 1;
				return true;
			}
			return false;
		}
		public boolean moveRigth() {
			if(map1[y][x+1] != 'X')
			{
				x = x + 1;
				return true;
			}
			return false;
		}
	}
	
	char map1[][] = {
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
	
	Entity hero1 = new Entity(1,1,'H');
	Entity guard1 = new Entity(8,1,'G');
	
	Entity door1 = new Entity(0,5,'I');
	Entity door2 = new Entity(0,6,'I');
	Entity door3 = new Entity(2,3,'I');
	Entity door4 = new Entity(4,1,'I');
	Entity door5 = new Entity(4,3,'I');
	Entity door6 = new Entity(2,8,'I');
	Entity door7 = new Entity(4,8,'I');
	
	Entity key1 = new Entity(7,8,'k');
	
	Entity entitiesGame1[] = {guard1,hero1,door1,door2,door3,door4,door5,door6,door7,key1};
	Entity doorsGame1[] = {door1,door2,door3,door4,door5,door6,door7};
	
	//---Game 2----------------------
	
	char map2[][] = {
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
	
	Entity hero2 = new Entity(1,8,'H');
	
	Entity entitiesGame2[] = {hero2};
	
	public void printmap1()
	{
		boolean entityPos = false;
		for (int i = 0; i < 10; i++)
		{
			for (int j = 0; j < 10; j++)
			{
				entityPos = false;
				for (Entity ent : entitiesGame1)
				{
					if (j == ent.getX() && i == ent.getY()) {
						System.out.print(ent.getChar());
						entityPos = true;
						break;
					}
				}
				if(!entityPos) {
					System.out.print(map1[i][j]);
				}
			}
			System.out.println();
		}
	}
	
	public void printmap2() {
		boolean entityPos = false;
		for (int i = 0; i < 10; i++)
		{
			for (int j = 0; j < 10; j++)
			{
				entityPos = false;
				for (Entity ent : entitiesGame2)
				{
					if (j == ent.getX() && i == ent.getY()) {
						System.out.print(ent.getChar());
						entityPos = true;
						break;
					}
				}
				if(!entityPos) {
					System.out.print(map2[i][j]);
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
			for(Entity d : doorsGame1) {
				if(d.getChar() == 'I' && d.getX() == ent.getX() && d.getY() == ent.getY() - 1)
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
			for(Entity d : doorsGame1) {
				if(d.getChar() == 'I' && d.getX() == ent.getX() && d.getY() == ent.getY() + 1)
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
			for(Entity d : doorsGame1) {
				if(d.getChar() == 'I' && d.getX() == ent.getX() - 1 && d.getY() == ent.getY())
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
			for(Entity d : doorsGame1) {
				if(d.getChar() == 'I' && d.getX() == ent.getX() + 1 && d.getY() == ent.getY())
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
	public void moveEntityRandom(Entity ent) {
		Random randomGenerator = new Random();
		while(true) {
			int command = randomGenerator.nextInt(4) + 1;
			if(command == 1) {
				if (ent.moveUp()) {
					break;
				}
			} else if(command == 2) {
				if (ent.moveDown()) {
					break;
				}
			} else if(command == 3) {
				if (ent.moveLeft()) {
					break;
				}
			} else if(command == 4) {
				if (ent.moveRigth()) {
					break;
				}
			}
		}
	}
	
 	public boolean gameLoop1() {
		int guardStep = 0;
		int command;
		while(true)
		{
			printmap1();
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
				door1.setChar('S');
				door2.setChar('S');
			}
			if (checkCollisionStepOver(hero1, door1) || checkCollisionStepOver(hero1, door2)) {
				return true;
				
			}
		}
	}
	
	
	public boolean gameLoop2() {
		printmap2();
		return true;
	}
	
	
	
	
	public static void main(String[] args) {
		DungeonKeep dungeon = new DungeonKeep();
		dungeon.gameLoop2();
//		if (dungeon.gameLoop1()) {
//			dungeon.printmap1();
//			System.out.print("You Won!");
//		} else {
//			System.out.print("Game Over");
//		}
		
		
	}
	

}
