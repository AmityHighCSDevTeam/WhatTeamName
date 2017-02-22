package com.amitycs.sandc;

public class BuildingType {
	public final String type;
	public final float productionInterval;
	public final float productionSpeed;
	
	public BuildingType(String type) {
		this.type = type;
		switch (type) {
			case "farm" :
				this.productionInterval = Const.FARM_PRODUCTION_INTERVAL;
				break;
			case "smith" :
				this.productionInterval = Const.SMITH_PRODUCTION_INTERVAL;
				break;
			default :
				this.productionInterval = -1;
				break;
		}
		productionSpeed = 1.0f;
	}
	
	@Override
	public String toString() {
		return type;
	}
}
