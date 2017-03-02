package com.amitycs.sandc;

public class UnitType {
	public String weapon;
	public String armor;
	public int armorResist;
	
	public UnitType(String weapon, String armor) {
		this.weapon = weapon;
		this.armor = armor;
		switch (armor) {
			case "naked" :
				armorResist = 1;
				break;
			case "hide" :
				armorResist = 2;
				break;
			case "metal" :
				armorResist = 5;
				break;
			case "cart" :
				armorResist = -1;
				break;
		}
	}
	
	@Override
	public String toString() {
		int w, a;
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
			default : //this should never, happen, and if it does somebody done fucked up.
				w = -3; 
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
		return weapon + " " + armor;
	}

}
