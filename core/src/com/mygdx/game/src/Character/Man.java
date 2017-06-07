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
	public boolean collision;
	int passi;

	public Man() {
		super();
		collision = false;
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
		currentState = StateDynamicObject.STANDING;
		previousState = StateDynamicObject.STANDING;
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
				collision = true;
				x -= velocity * dt;
			}
		}
		if (passi < 500000) {
			passi++;
			setState(StateDynamicObject.RUNNINGRIGHT, dt);
		} else {
			int rand = (int) (Math.random() * 10);

			if (rand == 1) {
				passi = 0;
				setState(StateDynamicObject.RUNNINGLEFT, dt);
			} else if (rand == 2) {
				passi = 0;
				setState(StateDynamicObject.RUNNINGUP, dt);
			} else if (rand == 3) {
				passi = 0;
				setState(StateDynamicObject.RUNNINGDOWN, dt);
			} else if (rand == 4) {
				passi = 0;
				setState(StateDynamicObject.STANDING, dt);
			} else {
				passi = 0;
				setState(StateDynamicObject.RUNNINGRIGHT, dt);
			}
		}
	}

	public void movesLeft(float dt) {

		if (dt > 0.017)
			dt = (float) 0.0165;
		if (x > 5) {
			x -= velocity * dt;
			if (collide(this)) {
				collision = true;
				x += velocity * dt;
			}
		}
		if (passi < 500000) {
			passi++;
			setState(StateDynamicObject.RUNNINGLEFT, dt);
		} else {
			int rand = (int) (Math.random() * 10);

			if (rand == 1) {
				passi = 0;
				setState(StateDynamicObject.RUNNINGRIGHT, dt);
			} else if (rand == 2) {
				passi = 0;
				setState(StateDynamicObject.RUNNINGUP, dt);
			} else if (rand == 3) {
				passi = 0;
				setState(StateDynamicObject.RUNNINGDOWN, dt);
			} else if (rand == 4) {
				passi = 0;
				setState(StateDynamicObject.RUNNINGLEFT, dt);
			} else {
				passi = 0;
				setState(StateDynamicObject.STANDING, dt);
			}
		}
	}

	public void movesUp(float dt) {
		if (dt > 0.017)
			dt = (float) 0.0165;
		if (y < 960 - height - 5) {
			y += velocity * dt;
			if (collide(this)) {
				y -= velocity * dt;
				collision = true;
			}
		}

		if (passi < 500000) {
			passi++;
			setState(StateDynamicObject.RUNNINGUP, dt);
		} else {
			int rand = (int) (Math.random() * 10);

			if (rand == 1) {
				passi = 0;
				setState(StateDynamicObject.RUNNINGLEFT, dt);
			} else if (rand == 2) {
				passi = 0;
				setState(StateDynamicObject.RUNNINGRIGHT, dt);
			} else if (rand == 3) {
				passi = 0;
				setState(StateDynamicObject.RUNNINGDOWN, dt);
			} else if (rand == 4) {
				passi = 0;
				setState(StateDynamicObject.RUNNINGUP, dt);
			} else {
				passi = 0;
				setState(StateDynamicObject.STANDING, dt);
			}
		}
	}

	public void movesDown(float dt) {
		if (dt > 0.017)
			dt = (float) 0.0165;
		if (y > 0) {
			y -= velocity * dt;
			if (collide(this)) {
				y += velocity * dt;
				collision = true;
			}
		}
		if (passi < 500000) {
			passi++;
			setState(StateDynamicObject.RUNNINGDOWN, dt);
		} else {
			int rand = (int) (Math.random() * 10);

			if (rand == 1) {
				passi = 0;
				setState(StateDynamicObject.RUNNINGLEFT, dt);
			} else if (rand == 2) {
				passi = 0;
				setState(StateDynamicObject.RUNNINGUP, dt);
			} else if (rand == 3) {
				passi = 0;
				setState(StateDynamicObject.RUNNINGRIGHT, dt);
			} else if (rand == 4) {
				passi = 0;
				setState(StateDynamicObject.RUNNINGDOWN, dt);
			} else {
				passi = 0;
				setState(StateDynamicObject.STANDING, dt);
			}
		}
	}

	private void setStateTimer(float f) {
		stateTimer = f;
	}

	@Override
	public float getStateTimer() {
		return stateTimer;
	}

	private void setState(StateDynamicObject state, float dt) {
		previousState = currentState;
		currentState = state;

		if (previousState == currentState)
			setStateTimer(getStateTimer() + dt);
		else
			setStateTimer(0);
	}

	public StateDynamicObject getState() {
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
								|| ((DynamicObjects) ob).getY() > y + height / 2))) {
					collision = true;
					return true;
				}
			}

		}
		collision = false;
		return false;
	}

	public void update(float dt) {

		if (currentState == StateDynamicObject.RUNNINGLEFT)
			movesLeft(dt);
		else if (currentState == StateDynamicObject.RUNNINGRIGHT && !collide(this))
			movesRight(dt);
		else if (currentState == StateDynamicObject.RUNNINGUP && !collide(this))
			movesUp(dt);
		else if (currentState == StateDynamicObject.RUNNINGDOWN && !collide(this))
			movesDown(dt);
		else if (currentState == StateDynamicObject.STANDING) {
			if (passi < 500000) {
				passi++;
				setState(StateDynamicObject.STANDING, dt);
			} else {
				passi=0;
				int rand = (int) (Math.random() * 10);
				if (rand == 1)
					setState(StateDynamicObject.RUNNINGLEFT, dt);
				else if (rand == 2)
					setState(StateDynamicObject.RUNNINGDOWN, dt);
				else if (rand == 3)
					setState(StateDynamicObject.RUNNINGUP, dt);
				else if (rand == 4)
					setState(StateDynamicObject.RUNNINGRIGHT, dt);
				//System.out.println(getCurrentState());
			}
		}

	}

	@Override
	public boolean collide() {
		// TODO Auto-generated method stub
		return false;
	}

}
