package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.src.Character.CharacterBattle.StateBattleCharacter;
import com.mygdx.game.src.Character.DynamicObjects.State;
import com.mygdx.game.src.World.Enemy;
import com.mygdx.game.src.World.Enemy.StateBattleEnemy;
import com.mygdx.game.src.World.Game;

public class LoadingImage {

	private static Texture homeImage;
	private static Texture bigHomeImage;
	private static Texture threeImage;
	private static Texture groundImage;
	private static Texture floorImage;
	private static Texture roadImage;
	private static Texture buildingImage;
	private static Texture waterImage;
	private static Texture rockImage;
	private static Texture forest1Image;
	private static Texture forest2Image;
	private static Texture tableImage;

	private static Texture battleBackground;


	private static Texture battleCharacter;
	private static TextureRegion battleCharacterStand;
	public static Animation<TextureRegion>[] battleCharacterAnimation;

	public Texture texture;
	private static TextureRegion playerStand;
	public static Animation<TextureRegion>[] playerAnimation;

	public Texture enemyTexture;
	private static TextureRegion enemyStand;
	public Animation<TextureRegion>[] enemyAnimation;

	private static TextureRegion man1Stand;
	public static Animation<TextureRegion>[] man1Animation;

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
		tableImage = new Texture("res/table.png");

		battleBackground = new Texture("res/battleBg.png");


		playerAnimation = new Animation[4];
		enemyAnimation = new Animation[4];
		man1Animation = new Animation[4];
		man2Animation = new Animation[4];
		man3Animation = new Animation[4];
		woman1Animation = new Animation[4];
		woman2Animation = new Animation[4];
		woman3Animation = new Animation[4];
		battleCharacterAnimation = new Animation[4];
		texture = new Texture("assets/bpj.png");
		createFrame(texture, playerAnimation);
		playerStand = playerAnimation[0].getKeyFrame(0, true);
		texture = new Texture("assets/notPlaying.png");
		createFrame(texture, man1Animation);
		man1Stand = man1Animation[0].getKeyFrame(0, true);

		battleCharacter = new Texture("assets/lancia.png");
		createBattleFrame(battleCharacter, battleCharacterAnimation);
		battleCharacterStand = battleCharacterAnimation[0].getKeyFrame(0, true);
	}

	private void createBattleFrame(Texture texture, Animation<TextureRegion>[] arrayAnimation) {
		Array<TextureRegion> frames = new Array<TextureRegion>();
		Animation<TextureRegion> right;
		Animation<TextureRegion> left;
		Animation<TextureRegion> fightingRight;
		Animation<TextureRegion> fightingLeft;
		for (int i = 0; i < 8; i++) {
			frames.add(new TextureRegion(texture, i * 64, 65, 65, 65));
		}
		right = new Animation<TextureRegion>(0.2f, frames);
		frames.clear();

		for (int i = 7; i != 0; i--) {
			frames.add(new TextureRegion(texture, i * 64, 0, 65, 65));
		}
		left = new Animation<TextureRegion>(0.2f, frames);
		frames.clear();

		for (int i = 0; i < 8; i++) {
			frames.add(new TextureRegion(texture, i * 64, 195, 65, 65));
		}
		fightingRight = new Animation<TextureRegion>(0.2f, frames);
		frames.clear();

		for (int i = 0; i < 8; i++) {
			frames.add(new TextureRegion(texture, i * 64, 130, 65, 65));
		}
		fightingLeft = new Animation<TextureRegion>(0.2f, frames);
		frames.clear();
		arrayAnimation[0] = right;
		arrayAnimation[1] = left;
		arrayAnimation[2] = fightingRight;
		arrayAnimation[3] = fightingLeft;
		arrayAnimation[2].setFrameDuration(0.025f);
		arrayAnimation[3].setFrameDuration(0.01f);

	}
	
	public void createFrame(Texture texture, Animation<TextureRegion>[] arrayAnimation) {

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

	public static Texture getTableImage() {
		return tableImage;
	}

	public static Texture getBattleBgImage() {
		return battleBackground;
	}

	public static TextureRegion getFrameCharacter(State currentState) {
		TextureRegion region = new TextureRegion();

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
			if (Game.character.getPreviousState() == State.RUNNINGRIGHT)
				playerStand = (TextureRegion) playerAnimation[0].getKeyFrame(0, true);
			if (Game.character.getPreviousState() == State.RUNNINGLEFT)
				playerStand = (TextureRegion) playerAnimation[1].getKeyFrame(0, true);
			if (Game.character.getPreviousState() == State.RUNNINGUP)
				playerStand = (TextureRegion) playerAnimation[2].getKeyFrame(0, true);
			if (Game.character.getPreviousState() == State.RUNNINGDOWN)
				playerStand = (TextureRegion) playerAnimation[3].getKeyFrame(0, true);
			region = playerStand;
			break;
		default:
			region = playerStand;
			break;
		}
		return region;
	}

	public static TextureRegion getBattleFrameCharacter(StateBattleCharacter currentState) {
		TextureRegion region = new TextureRegion();

		switch (currentState) {
		case RUNNINGRIGHT:
			region = (TextureRegion) battleCharacterAnimation[0]
					.getKeyFrame(Game.world.battle.character.getStateTimer(), true);
			battleCharacterStand = (TextureRegion) battleCharacterAnimation[0].getKeyFrame(0, true);;
			break;
		case RUNNINGLEFT:
			region = (TextureRegion) battleCharacterAnimation[1]
					.getKeyFrame(Game.world.battle.character.getStateTimer(), true);
			battleCharacterStand = region;
			break;
		case FIGHTINGRIGHT:
			region = (TextureRegion) battleCharacterAnimation[2]
					.getKeyFrame(Game.world.battle.character.getStateTimer(),true);
			battleCharacterStand = region;
			break;
		case FIGHTINGLEFT:
			region = (TextureRegion) battleCharacterAnimation[3]
					.getKeyFrame(Game.world.battle.character.getStateTimer(), true);
			battleCharacterStand = region;
			break;
		case STANDING:
			
			if (Game.world.battle.character.getPreviousState() == StateBattleCharacter.RUNNINGRIGHT)
				battleCharacterStand = (TextureRegion) battleCharacterAnimation[0].getKeyFrame(0, true);
			if (Game.world.battle.character.getPreviousState() == StateBattleCharacter.RUNNINGLEFT)
				battleCharacterStand = (TextureRegion) battleCharacterAnimation[1].getKeyFrame(0, true);
			if (Game.world.battle.character.getPreviousState() == StateBattleCharacter.FIGHTINGRIGHT)
				battleCharacterStand = (TextureRegion) battleCharacterAnimation[2].getKeyFrame(0, true);
			if (Game.world.battle.character.getPreviousState() == StateBattleCharacter.FIGHTINGLEFT)
				battleCharacterStand = (TextureRegion) battleCharacterAnimation[3].getKeyFrame(0, true);
			region = battleCharacterStand;
			break;
		default:
			region = battleCharacterStand;
			break;
		}
		return region;
	}

	public static TextureRegion getFrameMan(com.mygdx.game.src.Character.Man.State state) {
		TextureRegion region;
		switch (state) {
		case RUNNINGRIGHT:
			region = (TextureRegion) man1Animation[0].getKeyFrame(Game.man1.getStateTimer(), true);
			man1Stand = region;
			break;
		case RUNNINGLEFT:
			region = (TextureRegion) man1Animation[1].getKeyFrame(Game.man1.getStateTimer(), true);
			man1Stand = region;
			break;
		case RUNNINGUP:
			region = (TextureRegion) man1Animation[2].getKeyFrame(Game.man1.getStateTimer(), true);
			man1Stand = region;
			break;
		case RUNNINGDOWN:
			region = (TextureRegion) man1Animation[3].getKeyFrame(Game.man1.getStateTimer(), true);
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

	public TextureRegion getFrameEnemy(StateBattleEnemy currentState, Enemy enemy) {
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
		playerAnimation[0].setFrameDuration(frameDuration);
		playerAnimation[1].setFrameDuration(frameDuration);
		playerAnimation[2].setFrameDuration(frameDuration);
		playerAnimation[3].setFrameDuration(frameDuration);

	}

}
