package com.mygdx.game.src.World;

import com.badlogic.gdx.Gdx;

public class Enemy {
	public enum StateBattleEnemy {
		JUMPING, STANDING, RUNNINGLEFT, RUNNINGRIGHT, FIGHTING, DEFENDING
	};
	public enum Level {
		EASY, MEDIUM, HARD
	};

	String name;
	float life;
	float power;
	Weapon weapon;
	Pack win_bonus;
	public int passi = 0;
	public float x;
	public float y;
	protected static float stateTimer;
	public Level level;
	public float height;
	public float width;
	public float velocity;

	private static StateBattleEnemy currentState;
	public static StateBattleEnemy previousState;

	public Enemy(String name, float life, float power, Weapon weapon, Pack win_bonus, Level level) {

		velocity = 80;
		this.name = name;
		this.life = life;
		this.power = power;
		this.weapon = weapon;
		this.win_bonus = win_bonus;
		this.level = level;
		setCurrentState(StateBattleEnemy.STANDING);
		previousState = StateBattleEnemy.STANDING;
		stateTimer = 0;
		x = (float) (Math.random() * 300);
		y = (float) (Math.random() * 400);
		height = 60;
		width = 60;
	}

	public float getHeight() {
		return height;
	}

	public float getWidth() {
		return width;
	}

	public void movesRight(float dt) {
		if (x + width < Battle.WIDTH)
			x += velocity * dt;
		setState(StateBattleEnemy.RUNNINGRIGHT);
	}

	public void movesLeft(float dt) {
		if (x - width / 2 > 0)
			x -= velocity * dt;

		setState(StateBattleEnemy.RUNNINGLEFT);
	}

	public void jump() {
		setState(StateBattleEnemy.JUMPING);
	}

	private void setStateTimer(float f) {
		stateTimer = f;
	}

	public float getStateTimer() {
		return stateTimer;
	}

	private void setState(StateBattleEnemy state) {
		previousState = currentState;
		currentState = state;

		if (previousState == currentState)
			setStateTimer(getStateTimer() + Gdx.graphics.getDeltaTime());
		else
			setStateTimer(0);
	}

	public StateBattleEnemy getState() {
		return currentState;
	}

	public static void setCurrentState(StateBattleEnemy currentState) {
		Enemy.currentState = currentState;
	}
	public float getX(){
		return x;
	}
	public float getY(){
		return y;
	}

	public void decreaseHealth(Weapon weaponCharacter) {
		life -= weaponCharacter.getDamage();
	}

	public void update(float dt) {
		
	}

}
