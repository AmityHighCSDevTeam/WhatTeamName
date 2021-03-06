package com.amitycs.sandc;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Const {
	public static final Texture BUTTON_BASE = new Texture(Gdx.files.internal("buttonBase.png"));
	public static final Texture MISSING_TEXTURE = new Texture(Gdx.files.internal("missing.png"));
	public static final UnitType[][] UNIT_TYPES = {
			{new UnitType("bow", "naked"), new UnitType("pike", "naked"), new UnitType("spear", "naked"), new UnitType("sword", "naked")}, 
			{new UnitType("bow", "hide"), new UnitType("pike", "hide"), new UnitType("spear", "hide"), new UnitType("sword", "hide")}, 
			{new UnitType("bow", "metal"), new UnitType("pike", "metal"), new UnitType("spear", "metal"), new UnitType("sword", "metal")},
			{new UnitType("armor", "cart"), new UnitType("food", "cart"), new UnitType("bows", "cart"), new UnitType("pike", "cart"), new UnitType("spear", "cart"), new UnitType("sword", "cart")}
	};
	public static final BuildingType[] BUILDING_TYPES = {
			new BuildingType("barracks"),
			new BuildingType("castle"),
			new BuildingType("farm"),
			new BuildingType("smith"),
	};
	public static final Terrain[] TERRAINS = {
			new Terrain("water", 0.0f, 0.0f, 0.0f, 0.0f, -1.0f),
			new Terrain("field", 1.0f, 1.5f, 1.2f, 0.8f, 1.25f),
			new Terrain("hills", 1.75f, 0.8f, 1.0f, 1.0f, 0.75f),
			new Terrain("marsh", 0.5f, 0.15f, 0.5f, 0.75f, 0.25f),
			new Terrain("forest", 0.75f, 0.5f, 0.65f, 0.85f, 0.65f)
	};
	public static final int[] MAP_SIZE = {256, 128};
	public static final float[] CAMERA_START_POSITION = {128, 64};
	public static final float UNIT_MOVEMENT = 10.0f;
	public static final float MAN_FOOD_CONSUMPTION = 0.1f;
	public static final float EQUIPMENT_WEAR_FACTOR = 0.01f;
	public static final float BATTLE_PARTICIPATION_WEAR_FACTOR = 20.0f; //the wear multiplier if a unit actually participated in a battle
	public static final float MORALE_DECAY_FACTOR = 0.05f;
	public static final float BATTLE_WIN_MORALE_BOOST = 0.25f;
	public static final float DISTANT_BATTLE_WIN_MORALE_BOOST = 0.15f;
	public static final float DISTANT_BATTLE_LOSS_MORALE_PENALTY = 0.1f;
	public static final float FARM_PRODUCTION_INTERVAL = 20.0f;
	public static final float FARM_PRODUCTION_BATCH_SIZE = 25.0f;
	public static final float SMITH_PRODUCTION_INTERVAL = 20.0f;
	public static final float SMITH_PRODUCTION_BATCH_SIZE = 15.0f;
	public static final float BUILDING_GROWTH_FACTOR_SPEED = 0.15f;
	public static final float BUILDING_GROWTH_FACTOR_SIZE = 0.25f;
	public static final float CASTLE_SIEGE_HEALTH = 10.0f;
	public static final float CASTLE_HEALING_FACTOR = 0.25f;
	public static final float CASTLE_SIEGE_RATE = 1.25f;
	public static final float CAMERA_MOVE_SPEED = 1.0f;
	public static final int BARACKS_CAPACITY = 75;
	public static final int CHARACTER_HEIGHT = 50; //for font
	public static final int CHARACTER_WIDTH = 30;
	public static final int TILE_SIZE = 32; //the number of pixels each quare tile occupies
}
