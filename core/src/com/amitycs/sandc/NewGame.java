package com.amitycs.sandc;

public class NewGame {
	public int farms = 3;
	public int barracks = 4;
	public int smiths = 3;
	
	public int food = farms * 50;
	public int weapons = smiths * 20;
	public int maxMen = barracks * 75 + 30;
	
	public char[][] tiles = new char[5][9];
	public int morale;
	
	public NewGame(){
		if (food < maxMen){
			maxMen = food;
		}
		if(weapons < maxMen){
			maxMen = weapons;
		}
		settingUpTiles();
	}
	
	private void settingUpTiles(){
		
		//e = empty
		for(int x = 0; x < tiles.length; x++){
			for(int y = 0; y < tiles[x].length; y++){
				tiles[x][y] = 'e';
			}
		}
		//c = city/capital/castle - pick one!
		tiles[1][5] = 'c';
		//b = barracks
		tiles[0][2] = 'b';
		tiles[3][4] = 'b';
		tiles[2][5] = 'b';
		//s = smith/blacksmith
		tiles[1][3] = 's';
		tiles[3][8] = 's';
		tiles[0][0] = 's';
		//f = farm
		tiles[1][1] = 'f';
		tiles[0][8] = 'f';
		tiles[3][0] = 'f';
	}
	
}
