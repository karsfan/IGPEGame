package com.mygdx.game.src.Character;

import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.src.Tool.Potion;
import com.mygdx.game.src.Tool.Parchment;
import com.mygdx.game.src.World.Game;
import com.mygdx.game.src.World.ICollidable;
import com.mygdx.game.src.World.Tile;
import com.mygdx.game.src.World.Weapon;
import com.mygdx.game.src.World.World.Element;

public class Character extends DynamicObjects implements ICollidable {
	public static String name;
	public Bag bag;
	public Weapon primary_weapon;
	float health;
	float power;
	int coins;
	/*public static float x;
	public static float y;
	public static State currentState;
	public static State previousState;
	protected static float stateTimer;
	public float height;
	public float width;
	public float velocity;
*/
	@SuppressWarnings("static-access")
	public Character(String name) {
		super();
		this.name = name;
		bag = new Bag();
		primary_weapon = null;
		health = 100;
		power = 100;
		coins = 10;
		x = 1000;
		y = 600;
		velocity = 100;
		currentState = State.STANDING;
		previousState = State.STANDING;
		stateTimer = 0;
		height = 30;
		width = 30;
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public void setPrimary_weapon(Weapon primary_weapon) {
		this.primary_weapon = primary_weapon;
	}

	@SuppressWarnings("static-access")
	public void setName(String name) {
		this.name = name;
	}

	public void swapWeapon() {
		Weapon temporary = primary_weapon;
		primary_weapon = bag.secondary_weapon;
		bag.secondary_weapon = temporary;
	}

	public void pickParchment(Parchment parchment) {
		bag.addTool(parchment);
		// eliminata dalla mappa
		parchment.raccolta = true;
	}

	public void pickPotion(Potion potion) {
		bag.addTool(potion);
		// eliminata dalla mappa
		potion.raccolta = true;
	}

	public void upgradeWeapon(Weapon weapon) {
		weapon.upgrade(bag);
	}

	public void usePotionWeapon(Weapon weapon, Potion potion) {
		weapon.upgradePP(bag, potion);
	}

	public void usePotionHealth(Potion potion) {
		health += potion.getLevel() * 10;
		bag.deletePotion(potion);
	}

	public void movesRight(float dt) {
		if (dt > 0.017)
			dt = (float) 0.0165;
		if (x < 1440 - width / 2) {
			x += (int) (velocity * dt);
			if (collide(this))
				x -= (int) (velocity * dt);
		}
		setState(State.RUNNINGRIGHT);
	}

	public void movesLeft(float dt) {
		if (dt > 0.017)
			dt = (float) 0.0165;
		if (x > 5) {
			x -= (int) (velocity * dt);
			if (collide(this))
				x += (int) (velocity * dt);
		}
		setState(State.RUNNINGLEFT);
	}

	public void movesUp(float dt) {
		if (dt > 0.017)
			dt = (float) 0.0165;
		if (y < 960 - height - 5) {
			y += (int) (velocity * dt);
			if (collide(this))
				y -= (int) (velocity * dt);
		}
		setState(State.RUNNINGUP);
	}

	public void movesDown(float dt) {
		if (dt > 0.017)
			dt = (float) 0.0165;
		if (y > 0) {
			y -= (int) (velocity * dt);
			if (collide(this))
				y += (int) (velocity * dt);
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
			if (ob instanceof DynamicObjects && ob!=this) {
				if (!((x > ((DynamicObjects) ob).getX() + ((DynamicObjects) ob).getWidth() / 2 - 1 || ((DynamicObjects) ob).getX() > x + width/2)
						|| (y > ((DynamicObjects) ob).getY() + ((DynamicObjects) ob).getHeight() / 2 || ((DynamicObjects) ob).getY() > y + height/2)))
					return true;
			}

		}
		return false;
	}

	public void setVelocity(float velocity) {
		this.velocity = velocity;
	}

}
