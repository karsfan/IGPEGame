package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.src.Character.DynamicObjects.State;
import com.mygdx.game.src.World.Enemy;
import com.mygdx.game.src.World.Game;

public class LoadingImage {

	private Texture homeImage;
	private Texture bigHomeImage;
	private Texture threeImage;
	private Texture groundImage;
	private Texture floorImage;
	private Texture roadImage;
	private Texture buildingImage;
	private Texture waterImage;
	private Texture rockImage;
	private Texture forest1Image;
	private Texture forest2Image;

	public Texture playerTexture;
	private TextureRegion playerStand;
	public Animation<TextureRegion>[] playerAnimation;

	public Texture enemyTexture;
	private static TextureRegion enemyStand;
	public Animation<TextureRegion>[] enemyAnimation;

	public Texture man1Texture;
	private static TextureRegion man1Stand;
	public static Animation<TextureRegion> man1Right;
	public static Animation<TextureRegion> man1Left;
	public static Animation<TextureRegion> man1Up;
	public static Animation<TextureRegion> man1Down;

	public Texture man2Texture;
	private static TextureRegion man2Stand;
	public Animation<TextureRegion>[] man2Animation;
	
	public Texture man3Texture;
	private static TextureRegion man3Stand;
	public Animation<TextureRegion>[] man3Animation;

	public Texture woman1Texture;
	private static TextureRegion woman1Stand;
	public Animation<TextureRegion>[] woman1Animation;

	public Texture woman2Texture;
	private static TextureRegion woman2Stand;
	public Animation<TextureRegion>[] woman2Animation;

	public Texture woman3Texture;
	private static TextureRegion woman3Stand;
	public Animation<TextureRegion>[] woman3Animation;

	static Integer b;
	Integer a;

	@SuppressWarnings("unchecked")
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

		// createFrame(playerTexture, playerRight, playerLeft, playerUp,
		// playerDown, playerStand);
		Array<TextureRegion> frames = new Array<TextureRegion>();
		playerAnimation = new Animation[4];
		createFrame(playerTexture, playerAnimation);
		/*
		 * for (int i = 0; i < 8; i++) { frames.add(new
		 * TextureRegion(playerTexture, i * 64 / 2, 93, 60 / 2, 60 / 2)); }
		 * playerRight = new Animation<TextureRegion>(0.2f, frames);
		 * frames.clear();
		 * 
		 * for (int i = 8; i != 0; i--) { frames.add(new
		 * TextureRegion(playerTexture, i * 64 / 2, 31, 60 / 2, 60 / 2)); }
		 * playerLeft = new Animation<TextureRegion>(0.2f, frames);
		 * frames.clear();
		 * 
		 * for (int i = 0; i < 8; i++) { frames.add(new
		 * TextureRegion(playerTexture, i * 64 / 2, 62, 60 / 2, 60 / 2)); }
		 * playerDown = new Animation<TextureRegion>(0.2f, frames);
		 * frames.clear();
		 * 
		 * for (int i = 0; i < 8; i++) { frames.add(new
		 * TextureRegion(playerTexture, i * 64 / 2, 0, 60 / 2, 60 / 2)); }
		 * playerUp = new Animation<TextureRegion>(0.2f, frames);
		 * frames.clear();
		 * 
		 * playerStand = (TextureRegion) playerRight.getKeyFrame(0);
		 */
		/*
		 * enemyTexture = new Texture("assets/bpj.png"); Array<TextureRegion>
		 * frames = new Array<TextureRegion>();
		 * 
		 * for (int i = 0; i < 9; i++) { frames.add(new
		 * TextureRegion(enemyTexture, i * 64 / 2, 93, 60 / 2, 60 / 2)); }
		 * enemyRight = new Animation<TextureRegion>(0.2f, frames);
		 * frames.clear();
		 * 
		 * for (int i = 8; i != 0; i--) { frames.add(new
		 * TextureRegion(enemyTexture, i * 64 / 2, 31, 60 / 2, 60 / 2)); }
		 * enemyLeft = new Animation<TextureRegion>(0.2f, frames);
		 * frames.clear();
		 * 
		 * for (int i = 0; i < 8; i++) { frames.add(new
		 * TextureRegion(enemyTexture, i * 64 / 2, 62, 60 / 2, 60 / 2)); }
		 * enemyDown = new Animation<TextureRegion>(0.2f, frames);
		 * frames.clear();
		 * 
		 * for (int i = 0; i < 8; i++) { frames.add(new
		 * TextureRegion(enemyTexture, i * 64 / 2, 0, 60 / 2, 60 / 2)); }
		 * enemyUp = new Animation<TextureRegion>(0.2f, frames);
		 * 
		 * enemyStand = (TextureRegion) enemyRight.getKeyFrame(0);
		 */
		man1Texture = new Texture("assets/notPlaying.png");

		for (int i = 0; i < 8; i++) {
			frames.add(new TextureRegion(man1Texture, i * 64 / 2, 93, 60 / 2, 60 / 2));
		}
		man1Right = new Animation<TextureRegion>(0.2f, frames);
		frames.clear();

		for (int i = 7; i != 0; i--) {
			frames.add(new TextureRegion(man1Texture, i * 64 / 2, 31, 60 / 2, 60 / 2));
		}
		man1Left = new Animation<TextureRegion>(0.2f, frames);
		frames.clear();

		for (int i = 0; i < 8; i++) {
			frames.add(new TextureRegion(man1Texture, i * 64 / 2, 62, 60 / 2, 60 / 2));
		}
		man1Down = new Animation<TextureRegion>(0.2f, frames);
		frames.clear();

		for (int i = 0; i < 8; i++) {
			frames.add(new TextureRegion(man1Texture, i * 64 / 2, 0, 60 / 2, 60 / 2));
		}
		man1Up = new Animation<TextureRegion>(0.2f, frames);
		frames.clear();
		man1Stand = (TextureRegion) man1Right.getKeyFrame(0);

	}

	public  void createFrame(Texture texture, Animation<TextureRegion>[] arrayAnimation) {

		Array<TextureRegion> frames = new Array<TextureRegion>();
		Animation<TextureRegion> right;
		Animation<TextureRegion> left;
		Animation<TextureRegion> up;
		Animation<TextureRegion> down;
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
		arrayAnimation[0] = right;
		arrayAnimation[1] = left;
		arrayAnimation[2] = up;
		arrayAnimation[3] = down;
		playerStand = (TextureRegion) arrayAnimation[0].getKeyFrame(0,true);

	}

	public Texture getHomeImage() {
		return homeImage;
	}

	public Texture getThreeImage() {
		return threeImage;
	}

	public Texture getGroundImage() {
		return groundImage;
	}

	public Texture getBuildingImage() {
		return buildingImage;
	}

	public Texture getWaterImage() {
		return waterImage;
	}

	public Texture getRockImage() {
		return rockImage;
	}

	public Texture getForest1Image() {
		return forest1Image;
	}

	public Texture getForest2Image() {
		return forest2Image;
	}

	public Texture getFloorImage() {
		return floorImage;
	}

	public Texture getRoadImage() {
		return roadImage;
	}

	public Texture getBigHomeImage() {
		return bigHomeImage;
	}

	public TextureRegion getFrameCharacter(State currentState) {
		TextureRegion region;
		switch (currentState) {
		case RUNNINGRIGHT:
			region = (TextureRegion) playerAnimation[0].getKeyFrame(Game.character.getStateTimer(), true);
			playerStand = region;
			break;
		case RUNNINGLEFT:
			region = (TextureRegion) playerAnimation[1].getKeyFrame(Game.character.getStateTimer(), true);
			playerStand = region;
			break;
		case RUNNINGUP:
			region = (TextureRegion) playerAnimation[2].getKeyFrame(Game.character.getStateTimer(), true);
			playerStand = region;
			break;
		case RUNNINGDOWN:
			region = (TextureRegion) playerAnimation[3].getKeyFrame(Game.character.getStateTimer(), true);
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

	public static TextureRegion getFrameMan(com.mygdx.game.src.Character.Man.State state) {
		TextureRegion region;
		switch (state) {
		case RUNNINGRIGHT:
			region = (TextureRegion) man1Right.getKeyFrame(Game.man1.getStateTimer(), true);
			man1Stand = region;
			break;
		case RUNNINGLEFT:
			region = (TextureRegion) man1Left.getKeyFrame(Game.man1.getStateTimer(), true);
			man1Stand = region;
			break;
		case RUNNINGUP:
			region = (TextureRegion) man1Up.getKeyFrame(Game.man1.getStateTimer(), true);
			man1Stand = region;
			break;
		case RUNNINGDOWN:
			region = (TextureRegion) man1Down.getKeyFrame(Game.man1.getStateTimer(), true);
			man1Stand = region;
			break;
		case STANDING:
			region = man1Stand;
			break;
		default:
			region = man1Stand;
			break;
		}
		return region;
	}

	public  TextureRegion getFrameEnemy(com.mygdx.game.src.World.Enemy.State currentState, Enemy enemy) {
		TextureRegion region;
		switch (currentState) {
		case RUNNINGRIGHT:
			region = (TextureRegion) enemyAnimation[0].getKeyFrame(enemy.getStateTimer(), true);
			enemyStand = region;
			break;
		case RUNNINGLEFT:
			region = (TextureRegion) enemyAnimation[1].getKeyFrame(enemy.getStateTimer(), true);
			enemyStand = region;
			break;
		case RUNNINGUP:
			region = (TextureRegion) enemyAnimation[2].getKeyFrame(enemy.getStateTimer(), true);
			enemyStand = region;
			break;
		case RUNNINGDOWN:
			region = (TextureRegion) enemyAnimation[3].getKeyFrame(enemy.getStateTimer(), true);
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

	public void setFrameDurationCharacter(float frameDuration) {
		playerAnimation[0].setFrameDuration(frameDuration);
		playerAnimation[1].setFrameDuration(frameDuration);
		playerAnimation[2].setFrameDuration(frameDuration);
		playerAnimation[3].setFrameDuration(frameDuration);

	}

}
