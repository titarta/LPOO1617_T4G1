import java.util.Scanner;
import static java.lang.Math.*;

public class DungeonKeep {
	

	public class Entity {
		int x;
		int y;
		
		public int getX() {
			return x;
		}
		public int getY() {
			return y;
		}
	}
	
	public class Hero extends Entity{
		public Hero(int beginX,int beginY)
		{
			x = beginX;
			y = beginY;
		}
		
		char heroChar = 'H';
		
		public char getChar() {
			return heroChar;
		}
		
		public void moveUp() {
			if(map[y-1][x] != 'X' && map[y-1][x] != 'I')
			{
				y = y - 1;
			}
		}
		public void moveDown() {
			if(map[y+1][x] != 'X' && map[y+1][x] != 'I')
			{
				y = y + 1;
			}
		}
		public void moveLeft() {
			if(map[y][x-1] != 'X' && map[y][x - 1] != 'I')
			{
				x = x - 1;
			}
		}
		public void moveRigth() {
			if(map[y][x+1] != 'X' && map[y][x + 1] != 'I')
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
		}

		char guardChar = 'G';
		
		public char getChar() {
			return guardChar;
		}
		
		public void moveUp() {
			if(map[y-1][x] != 'X' && map[y-1][x] != 'I')
			{
				y = y - 1;
			}
		}
		public void moveDown() {
			if(map[y+1][x] != 'X' && map[y+1][x] != 'I')
			{
				y = y + 1;
			}
		}
		public void moveLeft() {
			if(map[y][x-1] != 'X' && map[y][x - 1] != 'I')
			{
				x = x - 1;
			}
		}
		public void moveRigth() {
			if(map[y][x+1] != 'X' && map[y][x + 1] != 'I')
			{
				x = x + 1;
			}
		}
		
	}
	
	
	char map[][] = {
			{'X','X','X','X','X','X','X','X','X','X'},
			{'X',' ',' ',' ','I',' ','X',' ',' ','X'},
			{'X','X','X',' ','X','X','X',' ',' ','X'},
			{'X',' ','I',' ','I',' ','X',' ',' ','X'},
			{'X','X','X',' ','X','X','X',' ',' ','X'},
			{'I',' ',' ',' ',' ',' ',' ',' ',' ','X'},
			{'I',' ',' ',' ',' ',' ',' ',' ',' ','X'},
			{'X','X','X',' ','X','X','X','X',' ','X'},
			{'X',' ','I',' ','I',' ','X','k',' ','X'},
			{'X','X','X','X','X','X','X','X','X','X'}
			};
	int guardMovement[] = {3,2,2,2,2,3,3,3,3,3,3,2,4,4,4,4,4,4,4,1,1,1,1,1};
	
	Hero hero1 = new Hero(1,1);
	Guard guard1 = new Guard(8,1);
	
	public void printMap()
	{
		for (int i = 0; i < 10; i++)
		{
			for (int j = 0; j < 10; j++)
			{
				if (j == guard1.getX() && i == guard1.getY()) {
					System.out.print(guard1.getChar());
				} else if (j == hero1.getX() && i == hero1.getY()) {
					System.out.print(hero1.getChar());
				} else {
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
		if (distance <= 1.1) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean checkCollisionStepOver(Entity ent1, Entity ent2) {
		return ent1.getX() == ent2.getX() && ent1.getY() == ent2.getY();
	}
	
	public boolean moveHero(int command, Hero hero)
	{
		if (command == 1) {
			hero.moveUp();
			return true;
		} else if (command == 2) {
			hero.moveDown();
			return true;
		} else if (command == 3) {
			hero.moveLeft();
			return true;
		} else if (command == 4) {
			hero.moveRigth();
			return true;
		} else {
			return false;
		}
	}
	
	public boolean moveGuard(int command, Guard guard) {
		if (command == 1) {
			guard.moveUp();
			return true;
		} else if (command == 2) {
			guard.moveDown();
			return true;
		} else if (command == 3) {
			guard.moveLeft();
			return true;
		} else if (command == 4) {
			guard.moveRigth();
			return true;
		} else {
			return false;
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
			moveHero(command, hero1);
			if (guardStep == guardMovement.length) {
				guardStep = 0;
			}
			moveGuard(guardMovement[guardStep],guard1);
			guardStep++;
		}
	}
	
	
	
	
	
	
	
	public static void main(String[] args) {
		DungeonKeep dungeon = new DungeonKeep();
		if (dungeon.gameLoop()) {
			System.out.print("You Won!");
		} else {
			System.out.print("Game Over");
		}
	}
	

}
