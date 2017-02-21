package com.amitycs.sandc;

public class Building {
	public BuildingType type;
	public boolean team; //false = red, true = blue
	public float productionCounter;
	public float productionSpeedMultiplier;
	public float productionBatchMultiplier;
	public Map parent;
	public float health;
	
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
		this.productionCounter += type.productionSpeed * this.productionSpeedMultiplier;
		if (this.productionCounter >= type.productionInterval && type.type != "castle" && type.type != "baracks") {
			produce();
			this.productionCounter -= type.productionInterval;
			this.productionBatchMultiplier += Const.BUILDING_GROWTH_FACTOR_SIZE;
			this.productionSpeedMultiplier += Const.BUILDING_GROWTH_FACTOR_SPEED;
		}
	}
	
	public void produce() {
		int selector = -1;
		switch (type.type) {
			case "farm" :
				selector = 0;
				break;
			case "smith" :
				
				break;
		}
		parent.createUnit((byte)0, team, Const.UNIT_TYPES[3][0]);
	}
	
	//SUDOKUKUKUUUUUUUUUUUUU!!!!!!!!!!!
	public void destroy() {
		parent.buildings.remove(this);
	}
}
