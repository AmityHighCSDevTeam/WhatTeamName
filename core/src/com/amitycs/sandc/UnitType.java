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
				armorResist = 0;
				break;
			case "hide" :
				armorResist = 2;
				break;
			case "metal" :
				armorResist = 6;
				break;
			case "cart" :
				armorResist = -1;
				break;
		}
	}

}
