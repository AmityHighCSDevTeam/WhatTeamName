package com.amitycs.sandc;

import java.util.HashMap;

public class Terrain {
	String name;
	float speedModifier;
	HashMap<String, Float> combatModifiers;
	
	public Terrain(String name, float speedModifier, float bowMod, float pikeMod, float spearMod, float swordMod) {
		HashMap<String, Float> combatModifiers = new HashMap<String, Float>(4);
		combatModifiers.put("bow", bowMod);
		combatModifiers.put("pike", pikeMod);
		combatModifiers.put("spear", spearMod);
		combatModifiers.put("sword", swordMod);
		setTerrain(name, speedModifier, combatModifiers);
	}
	
	private void setTerrain(String name, float speedModifier, HashMap<String, Float> combatModifiers) {
		this.name = name;
		this.speedModifier = speedModifier;
		this.combatModifiers = combatModifiers;
	}
}
