package com.amitycs.sandc;

import java.util.ArrayList;

public class Map {
	public ArrayList<Unit> units;
	public ArrayList<Building> buildings;
	public boolean turn;
	
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
	
	public void createUnit(byte men, boolean team) {
		
	}
	
	public void createBuilding() {
		
	}
}
