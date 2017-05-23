package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
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

	public Texture playerTexture;
	private static TextureRegion playerStand;
	public static Animation<TextureRegion> playerRight;
	public static Animation<TextureRegion> playerLeft;
	public static Animation<TextureRegion> playerUp;
	public static Animation<TextureRegion> playerDown;

	public Texture enemyTexture;
	private static TextureRegion enemyStand;
	public static Animation<TextureRegion> enemyRight;
	public static Animation<TextureRegion> enemyLeft;
	public static Animation<TextureRegion> enemyUp;
	public static Animation<TextureRegion> enemyDown;
	
	public Texture man1Texture;
	private static TextureRegion man1Stand;
	public static Animation<TextureRegion> man1Right;
	public static Animation<TextureRegion> man1Left;
	public static Animation<TextureRegion> man1Up;
	public static Animation<TextureRegion> man1Down;
	
	public Texture man2Texture;
	private static TextureRegion man2Stand;
	public static Animation<TextureRegion> man2Right;
	public static Animation<TextureRegion> man2Left;
	public static Animation<TextureRegion> man2Up;
	public static Animation<TextureRegion> man2Down;
	
	public Texture man3Texture;
	private static TextureRegion man3Stand;
	public static Animation<TextureRegion> man3Right;
	public static Animation<TextureRegion> man3Left;
	public static Animation<TextureRegion> man3Up;
	public static Animation<TextureRegion> man3Down;
	
	public Texture woman1Texture;
	private static TextureRegion woman1Stand;
	public static Animation<TextureRegion> woman1Right;
	public static Animation<TextureRegion> woman1Left;
	public static Animation<TextureRegion> woman1Up;
	public static Animation<TextureRegion> woman1Down;
	
	public Texture woman2Texture;
	private static TextureRegion woman2Stand;
	public static Animation<TextureRegion> woman2Right;
	public static Animation<TextureRegion> woman2Left;
	public static Animation<TextureRegion> woman2Up;
	public static Animation<TextureRegion> woman2Down;
	
	public Texture woman3Texture;
	private static TextureRegion woman3Stand;
	public static Animation<TextureRegion> woman3Right;
	public static Animation<TextureRegion> woman3Left;
	public static Animation<TextureRegion> woman3Up;
	public static Animation<TextureRegion> woman3Down;

	static Integer b; 
	Integer a; 
	
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
		
		//createFrame(playerTexture, playerRight, playerLeft, playerUp, playerDown, playerStand);
		Array<TextureRegion> frames = new Array<TextureRegion>();

		for (int i = 0; i < 8; i++) {
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

		/*enemyTexture = new Texture("assets/bpj.png");
		Array<TextureRegion> frames = new Array<TextureRegion>();

		for (int i = 0; i < 9; i++) {
			frames.add(new TextureRegion(enemyTexture, i * 64 / 2, 93, 60 / 2, 60 / 2));
		}
		enemyRight = new Animation<TextureRegion>(0.2f, frames);
		frames.clear();

		for (int i = 8; i != 0; i--) {
			frames.add(new TextureRegion(enemyTexture, i * 64 / 2, 31, 60 / 2, 60 / 2));
		}
		enemyLeft = new Animation<TextureRegion>(0.2f, frames);
		frames.clear();

		for (int i = 0; i < 8; i++) {
			frames.add(new TextureRegion(enemyTexture, i * 64 / 2, 62, 60 / 2, 60 / 2));
		}
		enemyDown = new Animation<TextureRegion>(0.2f, frames);
		frames.clear();

		for (int i = 0; i < 8; i++) {
			frames.add(new TextureRegion(enemyTexture, i * 64 / 2, 0, 60 / 2, 60 / 2));
		}
		enemyUp = new Animation<TextureRegion>(0.2f, frames);

		enemyStand = (TextureRegion) enemyRight.getKeyFrame(0);*/

	}
	
	public static void createFrame (Texture texture, Animation <TextureRegion> right, Animation <TextureRegion> left,
		Animation <TextureRegion> up, Animation <TextureRegion> down, TextureRegion stand){

		Array<TextureRegion> frames = new Array<TextureRegion>();

		for (int i = 0; i < 8; i++) {
			frames.add(new TextureRegion(texture, i * 64 / 2, 93, 60 / 2, 60 / 2));
		}
		right = new Animation<TextureRegion>(0.2f, frames);
		frames.clear();

		for (int i = 8; i != 0; i--) {
			frames.add(new TextureRegion(texture, i * 64 / 2, 31, 60 / 2, 60 / 2));
		}
		left = new Animation<TextureRegion>(0.2f, frames);
		frames.clear();

		for (int i = 0; i < 8; i++) {
			frames.add(new TextureRegion(texture, i * 64 / 2, 62, 60 / 2, 60 / 2));
		}
		down = new Animation<TextureRegion>(0.2f, frames);
		frames.clear();

		for (int i = 0; i < 8; i++) {
			frames.add(new TextureRegion(texture, i * 64 / 2, 0, 60 / 2, 60 / 2));
		}
		up = new Animation<TextureRegion>(0.2f, frames);
		frames.clear();
		playerStand = (TextureRegion) right.getKeyFrame(0);
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

	public static Texture getFloorImage() {
		return floorImage;
	}

	public static Texture getRoadImage() {
		return roadImage;
	}

	public static Texture getBigHomeImage() {
		return bigHomeImage;
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
	
	
	public static void setFrameDurationCharacter(float frameDuration) {
		playerLeft.setFrameDuration(frameDuration);
		playerDown.setFrameDuration(frameDuration);
		playerRight.setFrameDuration(frameDuration);
		playerUp.setFrameDuration(frameDuration);

	}

}
