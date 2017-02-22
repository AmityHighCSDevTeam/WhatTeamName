package com.amitycs.sandc;

public class Unit {
	public boolean team; // false = red, true = blue
	public UnitType type; //unit type. Includes both weapons and armor
	private Map parent;
	//stuff above here should be final, but im dumb so it cant. Don't fuck with it.
	
	public float food;
	public float morale;
	public float armsDurability; //also serves as resource value in the case of carts
	public float armorDurability;
	public byte men;
	public float movement;
	public int[] location; //location[0] = x, you can figure the other one.
	
	//@SuppressWarnings 
	public Unit(byte men, String weapon, String armor, boolean team, Map parent, int x, int y) {
		setUnit(men, stringToUnitType(weapon, armor), team, parent);
		freshUnit();
		setLocation(x, y);
	}
	
	public Unit(byte men, UnitType type, boolean team,  Map parent, int x, int y) {
		setUnit(men, type, team, parent);
		freshUnit();
		setLocation(x, y);
	}
	
	public Unit(float food, float morale, float armsDurability, float armorDurability, byte men, float movement, boolean team, UnitType type, Map parent, int[] location) {
		this.food = food;
		this.morale = morale;
		this.armsDurability = armsDurability;
		this.armorDurability = armorDurability;
		this.men = men;
		this.movement = movement;
		this.team = team;
		this.type = type;
		this.parent = parent;
		this.location = location;
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
			case "armor":
				w = -2;
				break;
			case "food":
				w = -1;
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
			case "cart":
				a = 3;
				w += 2;
				break;
		}
		if (a >= 0 && w >= 0) 
			return Const.UNIT_TYPES[a][w];
		return null;
	}
	
	private void setUnit(byte men, UnitType type, boolean team, Map parent) {
		this.team = team;
		this.men = men;
		this.type = type;
		this.parent = parent;
	}
	
	private void freshUnit() {
		this.food = 0.0f;
		this.morale = 0.5f;
		this.armsDurability = 0.0f;
		this.armorDurability = 0.0f;
	}
	
	public void setLocation(int x, int y) {
		this.location = new int[2];
		location[0] = x;
		location[1] = y;
	}
	
	//intended for use with carts. Don't use if it isnt a cart thing.
	public void setResource(float amount) {
		this.armsDurability = amount;
	}
	
	public void tick() {
		this.food -= Const.MAN_FOOD_CONSUMPTION * this.men;
		this.armsDurability -= Const.EQUIPMENT_WEAR_FACTOR;
		this.armorDurability -= Const.EQUIPMENT_WEAR_FACTOR;
		this.morale -= Const.MORALE_DECAY_FACTOR;
		this.movement = Const.UNIT_MOVEMENT;
	}
	
	public void battleWear(double enemyPower, double enemyArmor) {
		this.armsDurability -= Const.EQUIPMENT_WEAR_FACTOR * Const.BATTLE_PARTICIPATION_WEAR_FACTOR * enemyArmor;
		this.armorDurability -= Const.EQUIPMENT_WEAR_FACTOR * Const.BATTLE_PARTICIPATION_WEAR_FACTOR * enemyPower;
		this.morale += Const.BATTLE_WIN_MORALE_BOOST;
	}
	
	public double battlePower(Terrain t) {
		return men * men * morale * 
				t.combatModifiers.get(type.weapon); //the combat modifier for this unit's weapon in the terrain
	}
	
	public double armoreScore() {
		return type.armorResist;
	}
	
	//ALLAHU SNACKBAR
	public void die() {
		parent.units.remove(this);
	}
	
	public Unit invertedTeam() {
		return new Unit(food, morale, armsDurability, armorDurability, men, movement, !team, type, parent, location);
	}
	
	public void swapSides() {
		this.parent.createUnit(invertedTeam());;
		this.die();
	}
	
	@Override
	public String toString() {
		return food + " " + morale + " " + armsDurability + " " + armorDurability + " " + men + " " + movement + " " + team + " " + type.toString();
	}
}