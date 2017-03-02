package com.amitycs.sandc;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Map {
	public ArrayList<Unit> units;
	public ArrayList<Building> buildings;
	public Terrain[][] terrain;
	public boolean turn;
	public int turnCounter;
	public GameScreen parent;
	private File file;
	
	public Map(File file, GameScreen parent) throws FileNotFoundException {
		terrain = new Terrain[Const.MAP_SIZE[0]][Const.MAP_SIZE[1]];
		units = new ArrayList<Unit>();
		buildings = new ArrayList<Building>();
		loadFromFile(file);		
		this.parent = parent;
	}
	
	public Map (GameScreen parent) {
		this.turn = true;
		this.turnCounter = 1;
		terrain = new Terrain[Const.MAP_SIZE[0]][Const.MAP_SIZE[1]];
	}
	
	private void loadFromFile(File file) throws FileNotFoundException {
		this.file = file;
		Scanner s = new Scanner(file); {
			turn = Boolean.getBoolean(s.nextLine());
			turnCounter = Integer.parseInt(s.nextLine());
			loadTerrainFromText(s);
			loadUnitsFromText(s);
			loadBuildingsFromText(s);
		}s.close();
	}
	
	private void loadTerrainFromText(Scanner s) {
		for (int i = 0; i < terrain.length; i++) {
			String reader = s.nextLine();
			for (int j = 0; j < terrain[i].length; j++) {
				terrain[i][j] = Const.TERRAINS[Integer.parseInt(reader.charAt(j) + "")];
			}
		}
	}
	
	private void loadUnitsFromText(Scanner s) {
		int unitCount = Integer.parseInt(s.nextLine());
		if (unitCount == 0); else
		for (int i = 0; i < unitCount; i++) {
			String[] arr = s.nextLine().split(" ");
			units.add(new Unit(arr[0], arr[1], arr[2], arr[3], arr[4], arr[5], arr[6], arr[7], arr[8], arr[9], arr[10], this));
		}
	}
	
	private void loadBuildingsFromText(Scanner s) {
		int buildingCount = Integer.parseInt(s.nextLine());
		for (int i = 0; i < buildingCount; i++) {
			String[] arr = s.nextLine().split(" ");
			buildings.add(new Building(arr[0], arr[1], arr[2], arr[3], arr[4], arr[5], arr[6], arr[7], this));
		}
		
	}
	
	public void endTurn() {
		//units.
		turn = !turn;
		if (turn) turnCounter++;
		for (Building b : buildings) {
			if (b.team == turn) {
				b.tick();
			}
		}
		for (Unit u : units) {
			if (u.team == turn) {
				u.tick();
			}
		}
	}
	
	//north,east,south,west 0,1,2,3 they the same
	public void moveUnit(Unit u, int direction){
		switch (direction) {
		case 0 :
			u.location[1] += 1;
			break;
		case 1 :
			u.location[0] += 1;
			break;
		case 2 :
			u.location[1] -= 1;
			break;
		case 3 :
			u.location[0] -= 1;
			break;
		};
		for (Unit unit : this.units) {
			if (unit.location == u.location && unit.team != u.team) {
				battle(u, unit, terrain[u.location[0]][u.location[1]]);
			}
		}
	}
	
	public void createUnit(Unit u) {
		this.units.add(u);
	}
	
	public void createBuilding(Building b) {
		this.buildings.add(b);
	}
	
	public void distaintBattleMorale(boolean team) {
		for (Unit unit : units)
			if (unit.team == team)
				unit.morale += Const.DISTANT_BATTLE_WIN_MORALE_BOOST;
			else
				unit.morale -= Const.DISTANT_BATTLE_LOSS_MORALE_PENALTY;
	}
	
	//true if it did it, falsei f it didnt
	public boolean saveGame() {
		this.file.delete();
		try {
			this.file.createNewFile();
			BufferedWriter b = new BufferedWriter(new FileWriter(file));{
				b.write(this.toString());
			}b.close();
		}catch(IOException e) {
			return false;
		}
		return true;
	}
	
	//this assumes that the units are on opposite teams, and that they are on the same tile
	public void battle(Unit a, Unit b, Terrain t) {
		double Apower = a.battlePower(t);
		double Bpower = b.battlePower(t);
		Apower /= b.type.armorResist;
		Bpower /= a.type.armorResist;
		if (Apower > Bpower) {
			a.battleWear(b.battlePower(t), b.type.armorResist);
			b.die();
			distaintBattleMorale(a.team);
		}else if (Bpower > Apower) {
			b.battleWear(a.battlePower(t), a.type.armorResist);
			a.die();	
			distaintBattleMorale(b.team);		
		}
	}
	
	public String toString() {
		String str = "";
		str += turn + "\n";
		str += turnCounter + "\n";
		
		for (Terrain[] arr : terrain) {
			for (Terrain t : arr) {
				String temp = t.toString();
				switch (temp) {
					case "water" :
						str += 0; 
						break;
					case "field" :
						str += 1;
						break;
					case "hills" :
						str += 2;
						break;
					case "marsh" :
						str += 3;
						break;
					case "forest" :
						str += 4;
						break;
				}
			}
			str += "\n";
		}
		
		str += units.size() + "\n";
		for (int i = 0; i < units.size(); i++) {
			str += units.toString() + "\n";
		}
		
		str += buildings.size() + "\n";
		for (int i = 0; i < units.size(); i++) {
			str += buildings.toString() + "\n";
		}
		
		return str;
	}
}
