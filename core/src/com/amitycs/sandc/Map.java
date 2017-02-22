package com.amitycs.sandc;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Scanner;

public class Map {
	public ArrayList<Unit> units;
	public ArrayList<Building> buildings;
	public boolean turn;
	public int turnCounter;
	public GameScreen parent;
	
	public Map(File file, GameScreen parent) throws FileNotFoundException {
		loadFromFile(file);
		this.turnCounter = 0;
		this.turn = false;
		this.parent = parent;
	}
	
	private void loadFromFile(File file) throws FileNotFoundException {
		Scanner s = new Scanner(file); {
			//loading things
		}s.close();
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
	
	public void createUnit(Unit u) {
		this.units.add(u);
	}
	/*
	public void createUnit(byte men, boolean team, UnitType type, int[] location) {
		this.units.add(new Unit(men, type, team, this, location[0], location[1]));
	}
	
	public void createUnit(byte men, boolean team, String weapon, String armor, int[] location) {
		this.units.add(new Unit(men, weapon, armor, team, this, location[0], location[1]));
	}
	
	public void createBuilding(Building b) {
		this.buildings.add(b);
	}
	*/
	public void distaintBattleMorale(boolean team) {
		for (Unit unit : units)
			if (unit.team == team)
				unit.morale += Const.DISTANT_BATTLE_WIN_MORALE_BOOST;
			else
				unit.morale -= Const.DISTANT_BATTLE_LOSS_MORALE_PENALTY;
	}
}
