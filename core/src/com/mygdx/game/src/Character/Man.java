package com.mygdx.game.src.Character;

import java.util.Iterator;

import com.mygdx.game.src.World.Game;
import com.mygdx.game.src.World.ICollidable;
import com.mygdx.game.src.World.Tile;
//import com.mygdx.game.src.World.World.Element;
import com.mygdx.game.src.Map.StaticObject.Element;

public class Man extends DynamicObjects implements ICollidable {

	public static enum ManType {
		MAN1, MAN2, MAN3
	};

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
		y = 700;
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
			x += velocity * dt;
			if (collide(this)) {
				x -= velocity * dt;
			}
		}
		setState(State.RUNNINGRIGHT, dt);
	}

	public void movesLeft(float dt) {

		if (dt > 0.017)
			dt = (float) 0.0165;
		if (x > 5) {
			x -= velocity * dt;
			if (collide(this)) {
				x += velocity * dt;
			}
		}
		setState(State.RUNNINGLEFT, dt);
	}

	public void movesUp(float dt) {
		if (dt > 0.017)
			dt = (float) 0.0165;
		if (y < 960 - height - 5) {
			y += velocity * dt;
			if (collide(this)) {
				y -= velocity * dt;
			}
		}
		setState(State.RUNNINGUP, dt);
	}

	public void movesDown(float dt) {
		if (dt > 0.017)
			dt = (float) 0.0165;
		if (y > 0) {
			y -= velocity * dt;
			if (collide(this)) {
				y += velocity * dt;
			}
		}
		setState(State.RUNNINGDOWN, dt);
	}

	private void setStateTimer(float f) {
		stateTimer = f;
	}

	public float getStateTimer() {
		return stateTimer;
	}

	private void setState(State state, float dt) {
		previousState = currentState;
		currentState = state;

		if (previousState == currentState)
			setStateTimer(getStateTimer() + dt);
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
			if (ob instanceof DynamicObjects && ob != this) {
				if (!((x > ((DynamicObjects) ob).getX() + ((DynamicObjects) ob).getWidth() / 2 - 1
						|| ((DynamicObjects) ob).getX() > x + width / 2)
						|| (y > ((DynamicObjects) ob).getY() + ((DynamicObjects) ob).getHeight() / 2
								|| ((DynamicObjects) ob).getY() > y + height / 2)))
					return true;
			}

		}
		return false;
	}

	public void update(float dt) {
		movesLeft(dt);
	}

}
