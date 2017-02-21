package com.amitycs.sandc;

public class Unit {
	public float food;
	public float morale;
	public float armsDurability; //also serves as resource value in the case of carts
	public float armorDurability;
	public byte men;
	public float movement;
	public boolean team; // false = red, true = blue
	public UnitType type; //unit type. Includes both weapons and armor
	private Map parent;
	
	public Unit(byte men, String weapon, String armor, boolean team, Map parent) {
		setUnit(men, stringToUnitType(weapon, armor), team, parent);
		freshUnit();
	}
	
	public Unit(byte men, UnitType type, boolean team,  Map parent) {
		setUnit(men, type, team, parent);
		freshUnit();
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
}