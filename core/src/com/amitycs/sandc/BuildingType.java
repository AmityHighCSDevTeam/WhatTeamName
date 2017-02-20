package com.amitycs.sandc;

public class BuildingType {
	public String type;
	public float productionInterval;
	public BuildingType(String type) {
		this.type = type;
		switch (type) {
			case "barracks" :
				this.productionInterval = -1;
				break;
			case "castle" :
				this.productionInterval = -1;
				break;
			case "farm" :
				this.productionInterval = Const.FARM_PRODUCTION_INTERVAL;
				break;
			case "smith" :
				this.productionInterval = Const.SMITH_PRODUCTION_INTERVAL;
				break;
		}
	}
}
