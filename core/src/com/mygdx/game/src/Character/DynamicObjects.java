package com.mygdx.game.src.Character;

public class DynamicObjects {
	public enum State {
		STANDING, RUNNINGRIGHT, RUNNINGLEFT, RUNNINGDOWN, RUNNINGUP
	};

	public float x;
	public float y;
	public State currentState;
	public State previousState;
	protected float stateTimer;
	public float height;
	public float width;
	public float velocity;

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public  State getCurrentState() {
		return currentState;
	}

	public State getPreviousState() {
		return previousState;
	}

	public float getStateTimer() {
		return stateTimer;
	}

	public float getHeight() {
		return height;
	}

	public float getWidth() {
		return width;
	}

	public float getVelocity() {
		return velocity;
	}

	public void setVelocity(float f) {

	}

	public void movesLeft(float dt) {

	}

	public void movesRight(float dt) {

	}

	public void movesUp(float dt) {

	}

	public void movesDown(float dt) {
	}

}
