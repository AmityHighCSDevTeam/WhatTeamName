package com.amitycs.sandc;

public class Unit {
	public float food;
	public float morale;
	public float armsDurability;
	public byte men;
	public float movement;
	public boolean team; // false = red, true = blue
	public Type type; //unit type. Includes both weapons and armor
	
	public Unit(byte men, String weapon, String armor, boolean team) {
		this.team = team;
		this.men = men;
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
		switch (weapon) {
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
		setArmorAndWeapon(a, w);
		this.food = 0.0f;
		this.morale = 0.5f;
		this.armsDurability = 0.0f;
	}
	
	private void setArmorAndWeapon(int armor, int weapon) {
		if (armor >= 0 && weapon >= 0) 
			this.type = Const.TYPES[armor][weapon];
	}
	
}
