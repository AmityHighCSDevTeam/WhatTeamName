package com.amitycs.sandc;

import java.io.File;
import java.io.FileNotFoundException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameScreen implements Screen {
	private final SupplyAndConquer game;
	OrthographicCamera cam;
	SpriteBatch batch;
	Button exitButton;
	Button endTurn;
	Map map;
	float[] cameraLocation; // [0] is x, [1] is y marks the center of the view
							// measured in terms of tiles
	float zoom;
	Texture crosshair;
	File file;
	Unit selected;
	int counter = 0; //serves to make it possible to tab thru multiple units on the same tile
	//int[] focus; // the coords being focused on/ selected

	// i pressed ctrl + shift + f and it fucked up the formatting over here...
	// oops
	final Texture[][] units = {
			{ new Texture(Gdx.files.internal("bluBow.png")), new Texture(Gdx.files.internal("bluPike.png")),
					new Texture(Gdx.files.internal("bluSpear.png")), new Texture(Gdx.files.internal("bluSword.png")) },
			{ new Texture(Gdx.files.internal("redBow.png")), new Texture(Gdx.files.internal("redPike.png")),
					new Texture(Gdx.files.internal("redSpear.png")),
					new Texture(Gdx.files.internal("redSword.png")) } }; // first
																			// array
																			// is
																			// team,
																			// 0
																			// =
																			// blu
																			// 1
																			// =
																			// red
																			// inner
																			// array
																			// is
																			// unit
																			// type,
																			// numbered
																			// like
																			// in
																			// Const

	final Texture[][] buildings = {
			{ new Texture(Gdx.files.internal("bluBarracks.png")), new Texture(Gdx.files.internal("bluCastle.png")),
					new Texture(Gdx.files.internal("bluFarm.png")), new Texture(Gdx.files.internal("bluSmith.png")) },
			{ new Texture(Gdx.files.internal("redBarracks.png")), new Texture(Gdx.files.internal("redCastle.png")),
					new Texture(Gdx.files.internal("redFarm.png")), new Texture(Gdx.files.internal("redSmith.png")) } };// same
																														// deal
																														// as
																														// units,
																														// except
																														// they
																														// arent
																														// units.

	final Texture[] armor = { new Texture(Gdx.files.internal("NoArmor.png")),
			new Texture(Gdx.files.internal("Leather.png")), Const.MISSING_TEXTURE, Const.MISSING_TEXTURE, new Texture(Gdx.files.internal("Metal.png")) }; // 0
																														// =
																														// none,
																														// 1
																														// =
																														// leather,
																														// 2
																														// =
																														// metal

	final Texture[] terrain = { new Texture(Gdx.files.internal("Water.png")),
			new Texture(Gdx.files.internal("Field.png")), new Texture(Gdx.files.internal("Hills.png")),
			new Texture(Gdx.files.internal("Marsh.png")), new Texture(Gdx.files.internal("Forest.png")) };

	public GameScreen(SupplyAndConquer game, File file) {
		this.file = file;
		this.game = game;
		cam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getWidth());
		batch = new SpriteBatch();
		try {
			map = new Map(file, this);
		} catch (FileNotFoundException e) {
			game.setScreen(new FailedToFindFileScreen(game));
		}
		exitButton = new Button(new Texture(Gdx.files.internal("ExitGame.png")));
		endTurn = new Button(new Texture(Gdx.files.internal("EndTurn.png")));
		cameraLocation = Const.CAMERA_START_POSITION;
		crosshair = new Texture(Gdx.files.internal("Crosshair.png"));
		zoom = 1.0f;
	}

	public GameScreen(SupplyAndConquer game, Map map) {
		this.game = game;
		this.map = map;
		cam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getWidth());
		batch = new SpriteBatch();
		exitButton = new Button(new Texture(Gdx.files.internal("ExitGame.png")));
		endTurn = new Button(new Texture(Gdx.files.internal("EndTurn.png")));
		cameraLocation = Const.CAMERA_START_POSITION;
		crosshair = new Texture(Gdx.files.internal("Crosshair.png"));
		zoom = 1.0f;
	}

	@Override
	public void show() {

	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		{
			int width = (int) (((Gdx.graphics.getWidth() / (Const.TILE_SIZE * zoom)) / 2) + 1); //these are actually 1/2 width and height, but im too lazy to rename them
			int height = (int) (((Gdx.graphics.getHeight() / (Const.TILE_SIZE * zoom)) / 2) + 1);// these are measured by tiles, os yey
			
			for (int i = (int) (Math.floor(cameraLocation[0]) - width); i < Math.ceil(cameraLocation[0] + width); i++) {
				for (int j = (int) (Math.floor(cameraLocation[1]) - height); j < Math.ceil(cameraLocation[1] + height); j++) {
					drawTile(i, j, batch);
				}
			}
			int crosshairSize = Math.min(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()) / 10;
			batch.draw(crosshair, (Gdx.graphics.getWidth() / 2) - (crosshairSize / 2), (Gdx.graphics.getHeight() / 2) - (crosshairSize / 2), crosshairSize,  crosshairSize);
			drawSelectedUnit(batch);
			exitButton.draw(batch);
			endTurn.draw(batch);
		}
		batch.end();
		if (Gdx.input.justTouched())
			clickEvents();	
		
		cameraMoving(delta);
		
		
		
		if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) select(counter);
	}

	@Override
	public void resize(int width, int height) {
		cam.setToOrtho(false, width, height);
		batch.setProjectionMatrix(cam.combined);
		exitButton.modifyPos(width - width / 6, height - height / 6);
		exitButton.modifySize(width / 6, height / 6);
		endTurn.modifyPos(width - width / 3, height - height / 6);
		endTurn.modifySize(width / 6, height / 6);
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		this.dispose();
	}

	@Override
	public void dispose() {
		batch.dispose();
		exitButton.dispose();
		for (Texture t : armor)
			if (t != null) t.dispose();
		for (Texture t : terrain)
			if (t != null) t.dispose();
		for (Texture[] arr : units)
			for (Texture t : arr) 
				if (t != null) t.dispose();
		for (Texture[] arr : buildings)
			for (Texture t : arr) 
				if (t != null) t.dispose();
	}

	private void clickEvents() {
		if (exitButton.mousedOver())
			exit(0);
		if (endTurn.mousedOver()) {
			map.endTurn();
		}
	}
	
	private void cameraMoving(float delta) {
		float speedMod = 1.0f;
		
		if (Gdx.input.isKeyPressed(Input.Keys.CONTROL_RIGHT)) {
			speedMod *= 5.0f;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT)) {
			speedMod *= 5.0f;
		}

		if (Gdx.input.isKeyPressed(Input.Keys.UP))
			cameraLocation[1] += Const.CAMERA_MOVE_SPEED * delta * speedMod;
		if (Gdx.input.isKeyPressed(Input.Keys.DOWN))
			cameraLocation[1] -= Const.CAMERA_MOVE_SPEED * delta * speedMod;
		if (Gdx.input.isKeyPressed(Input.Keys.LEFT))
			cameraLocation[0] -= Const.CAMERA_MOVE_SPEED * delta * speedMod;
		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT))
			cameraLocation[0] += Const.CAMERA_MOVE_SPEED * delta * speedMod;
		if (Gdx.input.isKeyPressed(Input.Keys.LEFT_BRACKET))
			zoom *= 1.005f;
		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT_BRACKET))
			zoom *= 0.995f;
		
		
		if (cameraLocation[0] < 0)
			cameraLocation[0] = 0;
		if (cameraLocation[0] > Const.MAP_SIZE[0])
			cameraLocation[0] = Const.MAP_SIZE[0];
		if (cameraLocation[1] < 0)
			cameraLocation[1] = 0;
		if (cameraLocation[1] > Const.MAP_SIZE[1])
			cameraLocation[1] = Const.MAP_SIZE[1];
		
	}

	private void drawSelectedUnit(SpriteBatch batch) {
			if (selected != null) {
				batch.draw(armor[selected.type.armorResist - 1], Gdx.graphics.getWidth() * 5 / 6, 0, Gdx.graphics.getWidth() / 6, Gdx.graphics.getHeight() / 6);
			
				int team = -1;
				int wep = -1;
				if (selected.team) {
					team = 0;
				} else {
					team = 1;
				}
				switch (selected.type.weapon) {
				case "bow":
					wep = 0;
					break;
				case "pike":
					wep = 1;
					break;
				case "spear":
					wep = 2;
					break;
				case "sword":
					wep = 3;
					break;
				}
				batch.draw(units[team][wep], Gdx.graphics.getWidth() * 5 / 6, 0, Gdx.graphics.getWidth() / 6, Gdx.graphics.getHeight() / 6);
			}else {
				
			}
	}
	
	private void select(int counter) {
		int[] focus = new int[2];
		focus[0] = (int) (cameraLocation[0] / (Const.TILE_SIZE * zoom));
		focus[1] = (int) (cameraLocation[1] / (Const.TILE_SIZE * zoom));
		for (Unit u : map.units) {
			if (u.location == focus && counter == 0) {
				selected = u;
				this.counter++;
				break;
			}else {
				counter--;
			}
		}
		if (counter > 0) {
			this.counter = 0;
			select(this.counter);
		}
	}
	
	private boolean drawTile(int x, int y, SpriteBatch batch) {
		if (x < 0 || y < 0 || x > 255 || y > 127) return false;
		float xPos = x * (Const.TILE_SIZE * zoom) - cameraLocation[0] * (Const.TILE_SIZE * zoom) + (Gdx.graphics.getWidth() / (Const.TILE_SIZE * zoom)) * (Const.TILE_SIZE * zoom) / 2;
		float yPos = y * (Const.TILE_SIZE * zoom) - cameraLocation[1] * (Const.TILE_SIZE * zoom) + (Gdx.graphics.getHeight() / (Const.TILE_SIZE * zoom)) * (Const.TILE_SIZE * zoom) / 2;

		// drawing terrain
		batch.draw(terrain[coolerSearch(Const.TERRAINS, map.terrain[x][y])], xPos, yPos, (Const.TILE_SIZE * zoom),
				(Const.TILE_SIZE * zoom));

		// drawing buildings
		for (Building b : map.buildings) {
			if (b.location[0] == x && b.location[1] == y) {
				if (b.team) {
					batch.draw(buildings[0][coolerSearch(Const.BUILDING_TYPES, b.type)], xPos, yPos,
							(Const.TILE_SIZE * zoom), (Const.TILE_SIZE * zoom));
				} else {
					batch.draw(buildings[1][coolerSearch(Const.BUILDING_TYPES, b.type)], xPos, yPos,
							(Const.TILE_SIZE * zoom), (Const.TILE_SIZE * zoom));
				}
			}
		}

		// drawing armor
		for (Unit u : map.units) {
			if (u.location[0] == x && u.location[1] == y) {
				batch.draw(armor[u.type.armorResist - 1], xPos, yPos, (Const.TILE_SIZE * zoom), (Const.TILE_SIZE * zoom));
			}
		}

		// drawing weapons
		for (Unit u : map.units) {
			if (u.location[0] == x && u.location[1] == y) {
				int team = -1;
				int wep = -1;
				if (u.team) {
					team = 0;
				} else {
					team = 1;
				}
				switch (u.type.weapon) {
				case "bow":
					wep = 0;
					break;
				case "pike":
					wep = 1;
					break;
				case "spear":
					wep = 2;
					break;
				case "sword":
					wep = 3;
					break;
				}
				batch.draw(units[team][wep], xPos, yPos, (Const.TILE_SIZE * zoom), (Const.TILE_SIZE * zoom));
			}
		}
		return true;
	}

	public int coolerSearch(Object[] a, Object o) {
		int val = -1;
		for (int i = 0; i < a.length; i++)
			if (a[i].equals(o)) {
				val = i;
				break;
			}
		return val;
	}
	
	/*
	 * conditions: 0 = user exit 1 = some kind of error #theClifegotmelike
	 */

	public void exit(int condition) {
		switch (condition) {
		case 0:
			this.game.setScreen(new SaveGameScreen(game, map, file));
			break;
		case 1:
			this.game.setScreen(new ErrorScreen(game));
			break;
		}
	}

}
