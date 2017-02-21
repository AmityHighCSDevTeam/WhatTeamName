package com.amitycs.sandc;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Map {
	public ArrayList<Unit> units;
	public ArrayList<Building> buildings;
	public boolean turn;
	public GameScreen parent;
	
	public Map(File file, GameScreen parent) throws FileNotFoundException {
		this.parent = parent;
		loadFromFile(file);
	}
	
	private void loadFromFile(File file) throws FileNotFoundException {
		Scanner s = new Scanner(file);
		s.close();
	}
	
	public void endTurn() {
		turn = !turn;
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
	
	public void createUnit(Unit u) {
		this.units.add(u);
	}
	
	public void createUnit(byte men, boolean team, UnitType type) {
		this.units.add(new Unit(men, type, team, this));
	}
	
	public void createUnit(byte men, boolean team, String weapon, String armor) {
		this.units.add(new Unit(men, weapon, armor, team, this));
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
}
