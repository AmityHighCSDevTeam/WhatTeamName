package com.amitycs.sandc;

public class Building {
	public final BuildingType type;
	public final boolean team; //false = red, true = blue
	private final Map parent;
	public final int[] location;
	public float productionCounter;
	public float productionSpeedMultiplier;
	public float productionBatchMultiplier;
	public float health;
	
	public Building(String type, boolean team, Map parent, int[] location) {
		this.parent = parent;
		this.team = team;
		this.location = location;
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
			default : //this also should never happen. Users should never have access to this stuff.
				this.type = null; //Would it be more efficient for me to pass around Objects instead of Strings? probably. Will I? It's a bit late now tbh...
				break;
		}
		productionCounter = 0.0f;
		productionSpeedMultiplier = 1.0f;
		productionBatchMultiplier = 1.0f;
	}
	
	public Building(BuildingType type, boolean team, Map parent, int[] location) {
		this.type = type;
		this.team = team;
		this.parent = parent;
		this.location = location;
	}
	
	public Building(BuildingType type, boolean team, Map parent, float productionCounter, float productionSpeedMultiplier, float productionBatchMultiplier, float health, int[] location) {
		this.type = type;
		this.team = team;
		this.parent = parent;
		this.productionCounter = productionCounter;
		this.productionSpeedMultiplier = productionSpeedMultiplier;
		this.productionBatchMultiplier = productionBatchMultiplier;
		this.health = health;
		this.location = location;
	}
	
	public void tick() {
		this.productionCounter += type.productionSpeed * this.productionSpeedMultiplier;
		if (this.productionCounter >= type.productionInterval && type.type != "castle" && type.type != "baracks") {
			produce();
			this.productionCounter -= type.productionInterval;
			this.productionBatchMultiplier += Const.BUILDING_GROWTH_FACTOR_SIZE;
			this.productionSpeedMultiplier += Const.BUILDING_GROWTH_FACTOR_SPEED;
		}
		if (this.type == Const.BUILDING_TYPES[2]) {
			//CASTLE ONLY THINGS
			health += Const.CASTLE_SIEGE_HEALTH;
			for(Unit u :this.parent.units) {
				if (u.location[0] - 1 < u.location[0] && u.location[0] < u.location[0]) {

					if (u.location[0] -3 < u.location[1] && u.location[1] < u.location[1]) {
						
					};
				};
			}
		}
	}
	
	public void produce() {
		int selector = -1;
		switch (type.type) {
			case "farm" :
				selector = 0;
				break;
			case "smith" :
				selector = 1;
				break;
		}
		//parent.createUnit();
	}
	
	//SUDOKUKUKUpUUUUkUUpUUUUUU!!!!!!!!!!!
	public void destroy() {
		parent.buildings.remove(this);
	}
	
	public Building invertedTeam() {
		return new Building(type, !team, parent, productionCounter, productionSpeedMultiplier, productionBatchMultiplier, health, location);
	}
	
	public void swapSides() {
		this.parent.buildings.add(invertedTeam());
		destroy();
	}
	
}
