import java.util.Scanner;

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
		public void moveUp() {
			y--;
		}
		public void moveDown() {
			y++;
		}
		public void moveLeft() {
			x--;
		}
		public void moveRigth() {
			x++;
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
	
	char map[][] = {
			{'X','X','X','X','X','X','X','X','X','X'},
			{'X',' ',' ',' ','I',' ','X',' ','G','X'},
			{'X','X','X',' ','X','X','X',' ',' ','X'},
			{'X',' ','I',' ','I',' ','X',' ',' ','X'},
			{'X','X','X',' ','X','X','X',' ',' ','X'},
			{'I',' ',' ',' ',' ',' ',' ',' ',' ','X'},
			{'I',' ',' ',' ',' ',' ',' ',' ',' ','X'},
			{'X','X','X',' ','X','X','X','X',' ','X'},
			{'X',' ','I',' ','I',' ','X','k',' ','X'},
			{'X','X','X','X','X','X','X','X','X','X'}
			};
	
	Hero hero1 = new Hero(1,1);
	
	public void printMap()
	{
		for (int i = 0; i < 10; i++)
		{
			for (int j = 0; j < 10; j++)
			{
				if (j == hero1.getX() && i == hero1.getY()) {
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
	
	public static void main(String[] args) {
		DungeonKeep dungeon = new DungeonKeep();
		int command;
		while(true) { //game cycle
			dungeon.printMap();
			command = dungeon.readCommand();
			if (command == 1) {
				dungeon.hero1.moveUp();
			} else if (command == 2){
				dungeon.hero1.moveDown();
			} else if (command == 3){
				dungeon.hero1.moveLeft();
			} else if (command == 4) {
				dungeon.hero1.moveRigth();
			}
		}
	}
	

}
