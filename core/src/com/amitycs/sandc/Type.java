package com.amitycs.sandc;

public class Type {
	public String weapon;
	public String armor;
	public int armorResist;
	
	public Type(String weapon, String armor) {
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
		}
	}
}
