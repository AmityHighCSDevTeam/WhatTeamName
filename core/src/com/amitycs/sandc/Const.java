package com.amitycs.sandc;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Const {
	public static final Texture BUTTON_BASE = new Texture(Gdx.files.internal("buttonBase.png"));
	public static final Type[][] TYPES = {
			{new Type("bow", "naked"), new Type("pike", "naked"), new Type("spear", "naked"), new Type("sword", "naked")}, 
			{new Type("bow", "hide"), new Type("pike", "hide"), new Type("spear", "hide"), new Type("sword", "hide")}, 
			{new Type("bow", "metal"), new Type("pike", "metal"), new Type("spear", "metal"), new Type("sword", "metal")}
	};
	public static final int UNIT_MOVEMENT = 10;
	public static final int BARACKS_CAPACITY = 75;
	public static final int FARM_PRODUCTION_INTERVAL = 5;
	public static final int FARM_PRODUCTION_VALUE = 50;
	public static final int SMITH_PRODUCTION_INTERVAL = 10;
	public static final int SMITH_PRODUCTION_VALUE = 25;
}
