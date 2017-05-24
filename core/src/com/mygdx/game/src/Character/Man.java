package com.mygdx.game.src.Character;

import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.src.World.Game;
import com.mygdx.game.src.World.ICollidable;
import com.mygdx.game.src.World.Tile;
import com.mygdx.game.src.World.World.Element;

public class Man extends DynamicObjects implements ICollidable {

	public static enum ManType {
		MAN1, MAN2, MAN3
	};

	public static float x;
	public static float y;
	public static State currentState;
	public static State previousState;
	protected static float stateTimer;
	public float height;
	public float width;
	public float velocity;
	private String name;
	public int mainX;
	public int mainY;
	private String info;

	public Man() {
		super();
		stateTimer = 0;
		width = 30;
		height = 30;
		x = 1000;
		y = 800;
		mainX = 100;
		mainY = 100;
		velocity = 80;
		name = "Ciccio";
		info = "Ciao sono Ciccio";
		currentState = State.STANDING;
		previousState = State.STANDING;
	}

	public String getInfo() {
		return info;
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public String getName() {
		return name;
	}

	public void movesRight(float dt) {
		if (dt > 0.017)
			dt = (float) 0.0165;
		if (x < 1440 - width / 2) {
			x += (int) (velocity * dt);
			if (collide(this)) {
				x -= (int) (velocity * dt);
			}
		} 
		setState(State.RUNNINGRIGHT);
	}

	public void movesLeft(float dt) {
		if (dt > 0.017)
			dt = (float) 0.0165;
		if (x > 5) {
			x -= (int) (velocity * dt);
			if (collide(this)) {
				x += (int) (velocity * dt);
			}
		}
		setState(State.RUNNINGLEFT);
	}

	public void movesUp(float dt) {
		if (dt > 0.017)
			dt = (float) 0.0165;
		if (y < 960 - height - 5) {
			y += (int) (velocity * dt);
			if (collide(this)) {
				y -= (int) (velocity * dt);
			}
		}
		setState(State.RUNNINGUP);
	}

	public void movesDown(float dt) {
		if (dt > 0.017)
			dt = (float) 0.0165;
		if (y > 0) {
			y -= (int) (velocity * dt);
			if (collide(this)) {
				y += (int) (velocity * dt);
			}
		} 
		setState(State.RUNNINGDOWN);
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

	public float getHeight() {
		return height;
	}

	public float getWidth() {
		return width;
	}

	@Override
	public boolean collide(Object e) {
		Iterator<Object> it = (Iterator<Object>) Game.world.getListObjects().iterator();
		while (it.hasNext()) {
			Object ob = (Object) it.next();
			if (ob instanceof Tile) {
				if (((Tile) ob).getElement() != Element.GROUND && ((Tile) ob).getElement() != Element.ROAD)
					if (((Tile) ob).collide(this))
						return true;
			}
			
		}
		return false;
	}

	public void update(float dt) {
		
	}

}
