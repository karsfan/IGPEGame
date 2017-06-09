package com.mygdx.game.src.Character;

import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.src.Tool.Potion;
import com.mygdx.game.src.Tool.Parchment;
import com.mygdx.game.src.World.Game;
import com.mygdx.game.src.World.ICollidable;
import com.mygdx.game.src.World.Tile;
import com.mygdx.game.src.World.Weapon;
import com.mygdx.game.src.World.Weapon.Level;
import com.mygdx.game.src.World.Weapon.Type;
import com.mygdx.game.PlayScreen;
import com.mygdx.game.src.Map.Item;
import com.mygdx.game.src.Map.StaticObject.Element;

public class Character extends DynamicObjects implements ICollidable {

	public String name;
	public Bag bag;
	public Weapon primary_weapon;
	public float health;
	public float power;
	public int coins;

	public Character(String name) {
		super();
		this.name = name;
		bag = new Bag();
		primary_weapon = new Weapon("Lancia", Level.BASIC, Type.LANCIA);
		health = 100;
		power = 100;
		coins = 10;
		x = 1000;
		y = 600;
		velocity = 100;

		currentState = StateDynamicObject.STANDING;
		previousState = StateDynamicObject.STANDING;
		stateTimer = 0;
		height = 30;
		width = 30;
	}

	public Character(Character character) {
		super(character.x, character.y, character.currentState, character.previousState, character.stateTimer,
				character.width, character.height, character.velocity);
		this.bag = character.bag;
		this.primary_weapon = character.primary_weapon;
		this.health = character.health;
		this.power = character.power;
		this.coins = character.coins;
	}

	public float getHealth() {
		return health;
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

	public void setName(String name) {
		this.name = name;
		System.out.println(name);
	}

	public void swapWeapon() {
		Weapon temporary = primary_weapon;
		primary_weapon = bag.secondary_weapon;
		bag.secondary_weapon = temporary;
	}

	public void pickParchment(Item parchment) {
		bag.addTool(parchment);
		// eliminata dalla mappa
		parchment.picked = true;
	}

	public void pickPotion(Item potion) {
		bag.addTool(potion);
		// eliminata dalla mappa
		potion.picked = true;
	}

	public void upgradeWeapon(Weapon weapon) {
		// weapon.upgrade(bag);
	}

	public void usePotionHealth(Item potion) {
		switch(potion.getLevel())
		{
		case FIRST:
			health += 20;
			break;
		case SECOND:
			health += 30;
			break;
		case THIRD:
			health += 50;
			break;
		default:
			break;
		}
		bag.deletePotion(potion);
	}

	public void movesRight(float dt) {
		Iterator<Item> it = bag.bagItems.iterator();
		while (it.hasNext()) {
			Object ob = (Object) it.next();
			System.out.println(((Item) ob).getElement());
		}
		if (x < 1440 - width / 2) {
			x += (velocity * dt);
			if (collide(this))
				x -= (velocity * dt);
		}
		setState(StateDynamicObject.RUNNINGRIGHT);
	}

	public void movesLeft(float dt) {

		if (x > 5) {
			x -= (velocity * dt);
			if (collide(this))
				x += (velocity * dt);
		}
		setState(StateDynamicObject.RUNNINGLEFT);
	}

	public void movesUp(float dt) {

		if (y < 960 - height - 5) {
			y += (velocity * dt);
			if (collide(this)) {
				y -= (velocity * dt);
			}
		}
		setState(StateDynamicObject.RUNNINGUP);
	}

	public void movesDown(float dt) {

		if (y > 0) {
			y -= (velocity * dt);
			if (collide(this))
				y += (velocity * dt);
		}
		setState(StateDynamicObject.RUNNINGDOWN);
	}

	private void setStateTimer(float f) {
		stateTimer = f;
	}

	@Override
	public float getStateTimer() {
		return stateTimer;
	}

	public void setState(StateDynamicObject state) {
		previousState = currentState;
		currentState = state;

		if (previousState == currentState)
			setStateTimer(getStateTimer() + Gdx.graphics.getDeltaTime());
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
	void pickItem(Item item){
		if(item.getElement()!=Element.COIN)
			bag.addTool(item);
		else
			coins++;
		item.setPicked(true);
	}

	@Override
	public boolean collide(Object e) {
		Iterator<Object> it = (Iterator<Object>) Game.world.getListObjects().iterator();
		while (it.hasNext()) {
			Object ob = (Object) it.next();
			if (ob instanceof Tile) {
				if (((Tile) ob).getElement() != Element.GROUND && ((Tile) ob).getElement() != Element.ROAD)
					if (((Tile) ob).collide(this)) {
						if (((Tile) ob).getElement() == Element.TABLE)
							PlayScreen.hud.setDialogText(((Tile) ob).getInfo());
						return true;
					}
			}
			if (ob instanceof Item) {
				if (((Item) ob).collide(this)) {
					pickItem((Item)ob);
					// requestToPick(ob);
					return true;

				}
			}

		}
		Iterator<DynamicObjects> it1 = Game.world.getListDynamicObjects().iterator();
		while (it1.hasNext()) {
			Object ob = (Object) it1.next();
			if (ob instanceof DynamicObjects && ob != this) {
				if (!((x > ((DynamicObjects) ob).getX() + ((DynamicObjects) ob).getWidth() / 2
						|| ((DynamicObjects) ob).getX() > x + width / 2)
						|| (y > ((DynamicObjects) ob).getY() + ((DynamicObjects) ob).getHeight() / 2
								|| ((DynamicObjects) ob).getY() > y + height / 2))) {
					return true;
				}
			}
		}
		return false;
	}

	private void requestToPick(Object ob) {
		PlayScreen.hud.setDialogText("Vuoi raccogliere?");
	}

	public void setVelocity(float velocity) {
		this.velocity = velocity;
	}

	@Override
	public boolean collide() {
		// TODO Auto-generated method stub
		return false;
	}

	public Weapon getWeapon() {
		return primary_weapon;
	}

}
