package com.mygdx.game.src.World;

import com.badlogic.gdx.Gdx;

public class Enemy implements Runnable {
	public enum State {
		STANDING, RUNNINGRIGHT, RUNNINGLEFT, RUNNINGDOWN, RUNNINGUP
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
	private static State currentState;
	public static State previousState;
	protected static float stateTimer;
	public Level level;
	public float height;
	public float width;

	public Enemy(String name, float life, float power, Weapon weapon, Pack win_bonus, Level level) {

		this.name = name;
		this.life = life;
		this.power = power;
		this.weapon = weapon;
		this.win_bonus = win_bonus;
		this.level = level;
		setCurrentState(State.STANDING);
		previousState = State.STANDING;
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

	@Override
	public void run() {
	}

	public void movesRight() {
		x++;
		setState(State.RUNNINGRIGHT);
	}

	public void movesLeft() {
		x--;
		setState(State.RUNNINGLEFT);
	}

	public void movesUp() {
		y++;
		setState(State.RUNNINGUP);
	}

	public void movesDown() {
		y--;
		setState(State.RUNNINGDOWN);
	}

	public void update(float dt) {
		run();
	}

	private void setStateTimer(float f) {
		stateTimer = f;
	}

	public float getStateTimer() {
		return stateTimer;
	}

	private void setState(State state) {
		previousState = currentState;
		currentState = state;

		if (previousState == currentState)
			setStateTimer(getStateTimer() + Gdx.graphics.getDeltaTime());
		else
			setStateTimer(0);
	}

	public State getState() {
		return currentState;
	}

	public static void setCurrentState(State currentState) {
		Enemy.currentState = currentState;
	}

}
