package com.amitycs.sandc;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Const {
	public static final Texture BUTTON_BASE = new Texture(Gdx.files.internal("buttonBase.png"));
	public static final UnitType[][] UNIT_TYPES = {
			{new UnitType("bow", "naked"), new UnitType("pike", "naked"), new UnitType("spear", "naked"), new UnitType("sword", "naked")}, 
			{new UnitType("bow", "hide"), new UnitType("pike", "hide"), new UnitType("spear", "hide"), new UnitType("sword", "hide")}, 
			{new UnitType("bow", "metal"), new UnitType("pike", "metal"), new UnitType("spear", "metal"), new UnitType("sword", "metal")}
	};
	public static final BuildingType[] BUILDING_TYPES = {
			new BuildingType("barracks"),
			new BuildingType("castle"),
			new BuildingType("farm"),
			new BuildingType("smith"),
	};
	public static final float UNIT_MOVEMENT = 10;
	public static final float MAN_FOOD_CONSUMPTION = 0.1f;
	public static final float WEAPON_WEAR_FACTOR = 0.01f;
	public static final float MORALE_DECAY_FACTOR = 0.05f;
	public static final float FARM_PRODUCTION_INTERVAL = 5.0f;
	public static final float FARM_PRODUCTION_BATCH_SIZE = 25.0f;
	public static final float SMITH_PRODUCTION_INTERVAL = 10;
	public static final float SMITH_PRODUCTION_BATCH_SIZE = 12.5f;
	public static final int BARACKS_CAPACITY = 75;
}
