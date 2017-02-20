package com.amitycs.sandc;

public class Building {
	public BuildingType type;
	public boolean team; //false = red, true = blue
	public float productionCounter;
	public float productionSpeedMultiplier;
	public float productionBatchMultiplier;
	public Map parent;
	
	public Building(String type, boolean team, Map parent) {
		this.parent = parent;
		this.team = team;
		switch (type) {
			case "barracks" :
				this.type = Const.BUILDING_TYPES[0];
				break;
			case "castle" :
				this.type = Const.BUILDING_TYPES[1];
				break;
			case "farm" :
				this.type = Const.BUILDING_TYPES[2];
				break;
			case "smith" :
				this.type = Const.BUILDING_TYPES[3];
				break;
		}
		productionCounter = 0.0f;
		productionSpeedMultiplier = 1.0f;
		productionBatchMultiplier = 1.0f;
	}
	
	public void tick() {
		this.productionCounter += type.
	}
}
