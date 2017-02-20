package com.amitycs.sandc;

public class Unit {
	public float food;
	public float morale;
	public float armsDurability;
	public byte men;
	public float movement;
	public boolean team; // false = red, true = blue
	public UnitType type; //unit type. Includes both weapons and armor
	
	public Unit(byte men, String weapon, String armor, boolean team) {
		setUnit(men, stringToUnitType(weapon, armor), team);
	}
	
	private UnitType stringToUnitType(String weapon, String armor) {
		int w = -1, a = -1;
		switch (weapon) {
			case "bow":
				w = 0;
				break;
			case "pike":
				w = 1;
				break;
			case "spear":
				w = 2;
				break;
			case "sword":
				w = 3;
				break;
		}
		switch (armor) {
			case "naked":
				a = 0;
				break;
			case "hide":
				a = 1;
				break;
			case "metal":
				a = 2;
				break;
		}
		if (a >= 0 && w >= 0) 
			return Const.UNIT_TYPES[a][w];
		return null;
	}
	
	private void setUnit(byte men, UnitType type, boolean team) {
		this.team = team;
		this.men = men;
		this.food = 0.0f;
		this.morale = 0.5f;
		this.armsDurability = 0.0f;
		
	}
	
	public void tick() {
		this.food -= Const.MAN_FOOD_CONSUMPTION * this.men;
		this.armsDurability -= Const.WEAPON_WEAR_FACTOR;
		this.morale -= Const.MORALE_DECAY_FACTOR;
		this.movement = Const.UNIT_MOVEMENT;
	}
	
	public double battlePower() {
		return men * men;
	}
	
	public double armoreScore() {
		return type.armorResist;
	}
	
	public void die() {
		men = 0;
	}
}