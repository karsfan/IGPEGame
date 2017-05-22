package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.src.Character.Character;
import com.mygdx.game.src.Character.Character.State;
import com.mygdx.game.src.World.Enemy;

public class LoadingImage {

	public static Texture homeImage;
	public static Texture bigHomeImage;
	private static Texture threeImage;
	private static Texture groundImage;
	private static Texture floorImage;
	private static Texture roadImage;
	private static Texture buildingImage;
	private static Texture waterImage;
	private static Texture rockImage;
	private static Texture forest1Image;
	private static Texture forest2Image;

	public static TextureAtlas atlasPlayer;
	public static Animation<TextureRegion> playerRight;
	public static Animation<TextureRegion> playerLeft;
	public static Animation<TextureRegion> playerUp;
	public static Animation<TextureRegion> playerDown;
	public Texture playerTexture;
	public static TextureAtlas atlasEnemy;
	public static Animation<TextureRegion> enemyRight;
	public static Animation<TextureRegion> enemyLeft;
	public static Animation<TextureRegion> enemyUp;
	public static Animation<TextureRegion> enemyDown;
	private static TextureRegion playerStand;
	private static TextureRegion enemyStand;
	public Texture enemyTexture;

	public LoadingImage() {

		homeImage = new Texture("res/home.png");
		bigHomeImage = new Texture("res/bigHome.png");
		threeImage = new Texture("res/three.png");
		groundImage = new Texture("res/ground.png");
		floorImage = new Texture("res/floor.png");
		roadImage = new Texture("res/road.png");
		buildingImage = new Texture("res/building.png");
		waterImage = new Texture("res/water.png");
		rockImage = new Texture("res/rock.png");
		forest1Image = new Texture("res/forest1.png");
		forest2Image = new Texture("res/forest2.png");

		playerTexture = new Texture("assets/bpj.png");
		Array<TextureRegion> frames = new Array<TextureRegion>();

		for (int i = 0; i < 9; i++) {
			frames.add(new TextureRegion(playerTexture, i * 64 / 2, 93, 60 / 2, 60 / 2));
		}
		playerRight = new Animation<TextureRegion>(0.2f, frames);
		frames.clear();

		for (int i = 8; i != 0; i--) {
			frames.add(new TextureRegion(playerTexture, i * 64 / 2, 31, 60 / 2, 60 / 2));
		}
		playerLeft = new Animation<TextureRegion>(0.2f, frames);
		frames.clear();

		for (int i = 0; i < 8; i++) {
			frames.add(new TextureRegion(playerTexture, i * 64 / 2, 62, 60 / 2, 60 / 2));
		}
		playerDown = new Animation<TextureRegion>(0.2f, frames);
		frames.clear();

		for (int i = 0; i < 8; i++) {
			frames.add(new TextureRegion(playerTexture, i * 64 / 2, 0, 60 / 2, 60 / 2));
		}
		playerUp = new Animation<TextureRegion>(0.2f, frames);
		frames.clear();

		playerStand = (TextureRegion) playerRight.getKeyFrame(0);

		enemyTexture = new Texture("assets/bpj.png");

		for (int i = 0; i < 9; i++) {
			frames.add(new TextureRegion(enemyTexture, i * 64, 200, 60, 60));
		}
		enemyRight = new Animation<TextureRegion>(0.2f, frames);
		frames.clear();

		for (int i = 8; i != 0; i--) {
			frames.add(new TextureRegion(enemyTexture, i * 64, 73, 60, 60));
		}
		enemyLeft = new Animation<TextureRegion>(0.2f, frames);
		frames.clear();

		for (int i = 0; i < 9; i++) {
			frames.add(new TextureRegion(enemyTexture, i * 64, 140, 60, 60));
		}
		enemyDown = new Animation<TextureRegion>(0.2f, frames);
		frames.clear();

		for (int i = 0; i < 9; i++) {
			frames.add(new TextureRegion(enemyTexture, i * 64, 10, 60, 60));
		}
		enemyUp = new Animation<TextureRegion>(0.2f, frames);
		frames.clear();

		enemyStand = (TextureRegion) enemyRight.getKeyFrame(0);

	}

	public static TextureRegion getFrameCharacter(State currentState) {
		TextureRegion region;
		switch (currentState) {
		case RUNNINGRIGHT:
			region = (TextureRegion) playerRight.getKeyFrame(Character.getStateTimer(), true);
			playerStand = region;
			break;
		case RUNNINGLEFT:
			region = (TextureRegion) playerLeft.getKeyFrame(Character.getStateTimer(), true);
			playerStand = region;
			break;
		case RUNNINGUP:
			region = (TextureRegion) playerUp.getKeyFrame(Character.getStateTimer(), true);
			playerStand = region;
			break;
		case RUNNINGDOWN:
			region = (TextureRegion) playerDown.getKeyFrame(Character.getStateTimer(), true);
			playerStand = region;
			break;
		case STANDING:
			region = playerStand;
			break;
		default:
			region = playerStand;
			break;
		}
		return region;
	}

	public static Texture getHomeImage() {
		return homeImage;
	}

	public static Texture getThreeImage() {
		return threeImage;
	}

	public static Texture getGroundImage() {
		return groundImage;
	}

	public static Texture getBuildingImage() {
		return buildingImage;
	}

	public static Texture getWaterImage() {
		return waterImage;
	}

	public static Texture getRockImage() {
		return rockImage;
	}

	public static Texture getForest1Image() {
		return forest1Image;
	}

	public static Texture getForest2Image() {
		return forest2Image;
	}

	public static TextureRegion getFrameEnemy(com.mygdx.game.src.World.Enemy.State currentState, Enemy enemy) {
		TextureRegion region;
		switch (currentState) {
		case RUNNINGRIGHT:
			region = (TextureRegion) enemyRight.getKeyFrame(enemy.getStateTimer(), true);
			enemyStand = region;
			break;
		case RUNNINGLEFT:
			region = (TextureRegion) enemyLeft.getKeyFrame(enemy.getStateTimer(), true);
			enemyStand = region;
			break;
		case RUNNINGUP:
			region = (TextureRegion) enemyUp.getKeyFrame(enemy.getStateTimer(), true);
			enemyStand = region;
			break;
		case RUNNINGDOWN:
			region = (TextureRegion) enemyDown.getKeyFrame(enemy.getStateTimer(), true);
			enemyStand = region;
			break;
		case STANDING:
			region = enemyStand;
			break;
		default:
			region = enemyStand;
			break;
		}
		return region;
	}

	public static Texture getFloorImage() {
		return floorImage;
	}

	public static Texture getRoadImage() {
		return roadImage;
	}

	public static Texture getBigHomeImage() {
		return bigHomeImage;
	}

	public static void setFrameDurationCharacter(float frameDuration) {
		playerLeft.setFrameDuration(frameDuration);
		playerDown.setFrameDuration(frameDuration);
		playerRight.setFrameDuration(frameDuration);
		playerUp.setFrameDuration(frameDuration);

	}

}
