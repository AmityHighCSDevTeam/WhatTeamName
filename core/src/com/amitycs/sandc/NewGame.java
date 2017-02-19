package com.amitycs.sandc;

public class NewGame {
	public int farms = 3;
	public int barracks = 4;
	public int smiths = 3;
	
	public int food = farms * 50;
	public int weapons = smiths * 20;
	public int maxMen = barracks * 75;
	
	public char[][] tiles = new char[5][9];
	public int morale;
	
	public NewGame(){
		if (food < maxMen){
			maxMen = food;
		}
		if(weapons < maxMen){
			maxMen = weapons;
		}
		
	}
	private void settingUpTiles(){
		//c = city/capital/castle - pick one!
		tiles[1][5] = 'c';
		//b = barracks
		//s = smith/blacksmith
		//f = farm
		//e = empty
		for(int x = 0; x < tiles.length; x++){
			for(int y = 0; y < tiles[x].length; y++){
				
			}
		}
	}
}
