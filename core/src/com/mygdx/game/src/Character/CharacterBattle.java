package com.mygdx.game.src.Character;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.src.Character.DynamicObjects.StateDynamicObject;
import com.mygdx.game.src.World.Battle;
import com.mygdx.game.src.World.Game;
import com.mygdx.game.src.World.ThreadCharacterBattle;
import com.mygdx.game.src.World.Weapon;

public class CharacterBattle implements com.mygdx.game.src.World.ICollidable {

	/*
	 * public enum StateBattleCharacter { JUMPING, STANDING, RUNNINGLEFT,
	 * RUNNINGRIGHT, FIGHTINGRIGHT, FIGHTINGLEFT, DEFENDING };
	 */

	Character character;
	public StateDynamicObject currentState;
	public StateDynamicObject previousState;
	public float stateTimer;
	public boolean fighting;
	public float fightingTimeCurrent;
	public float fightingTime;
	public boolean jumping;
	public float jumpingTime;
	public float jumpingTimeCurrent;
	public float force;
	public boolean right;
	public boolean left;
	ThreadCharacterBattle threadCharacter;

	public CharacterBattle(final Character character1) {
		stateTimer = 0;
		currentState = StateDynamicObject.STANDING;
		previousState = null;

		character = new Character(character1);
		character.x = 100;
		character.y = 250;
		character.width = 120;
		character.height = 120;
		fighting = false;
		fightingTimeCurrent = 0;
		fightingTime = 0.2f;
		threadCharacter = new ThreadCharacterBattle(this);
		threadCharacter.start();
		right = true;
		left = false;
	}

	public float getX() {
		return character.getX();
	}

	public float getY() {
		return character.getY();
	}

	public float getHeight() {
		return character.getHeight();
	}

	public float getWidth() {
		return character.getWidth();
	}

	@SuppressWarnings("static-access")
	public void update(float dt) {

		if (fighting && fightingTimeCurrent < fightingTime) {
			fightingTimeCurrent += 0.02;
			setState(StateDynamicObject.FIGHTINGRIGHT);
		} else if (fighting && fightingTimeCurrent > fightingTime) {
			fighting = false;
			fightingTimeCurrent = 0;
		}
		if (jumping && force > 0) {
			character.y += force;
			setForce(-Game.world.battle.gravity);
			if (right)
				character.x += 2;
			if (left)
				character.x -= 2;
			setState(StateDynamicObject.JUMPING);
		} else if (character.y - force > 250) {
			character.y -= force;
			setForce(Game.world.battle.gravity);
			if (right)
				character.x += 2;
			if (left)
				character.x -= 2;
			setState(StateDynamicObject.JUMPING);
			jumping = false;
		} else {
			character.y = 250;
			force = 0;
			//jumping = false;
			jumpingTimeCurrent = 0;
		}

	}

	public void fightRight() {
		// character.width += character.primary_weapon.width;
		// if (collide())
		// Battle.enemy.decreaseHealth(character.primary_weapon);
		// character.width -= character.primary_weapon.width;
		// threadCharacter.start();
		setState(StateDynamicObject.FIGHTINGRIGHT);
		fighting = true;

	}

	public void setForce(float force) {
		this.force += force;
	}

	public void stand() {
		if (!fighting) {
			setState(StateDynamicObject.STANDING);
			stateTimer = 0;
		}
	}

	public void movesRight(float dt) {
		right = true;
		left = false;
		if (character.x + character.width < Battle.WIDTH)
			character.x += character.velocity * dt;
		if (!fighting)
			setState(StateDynamicObject.RUNNINGRIGHT);
	}

	public void movesLeft(float dt) {
		right = false;
		left = true;
		if (character.x - character.width / 2 > 0)
			character.x -= character.velocity * dt;

		setState(StateDynamicObject.RUNNINGLEFT);
	}

	public void jump(float dt) {
		if (!jumping && character.y<500) {
			force = 70;
			setState(StateDynamicObject.JUMPING);
			jumping = true;
		}
	}

	public void setState(StateDynamicObject state) {
		previousState = currentState;
		currentState = state;
		// System.out.println("ciao");
		if (previousState == currentState && currentState != StateDynamicObject.STANDING)
			setStateTimer(getStateTimer() + Gdx.graphics.getDeltaTime());
		else
			setStateTimer(0);

	}

	private void setStateTimer(float f) {
		stateTimer = f;
	}

	@Override
	public boolean collide(Object e) {
		return false;
	}

	@Override
	public boolean collide() {
		if (!((character.x > Battle.enemy.getX() + Battle.enemy.getWidth() / 2 - 1
				|| Battle.enemy.getX() > character.x * 32 + character.width)
				|| (character.y * 32 > Battle.enemy.getY() + Battle.enemy.getHeight() / 2
						|| Battle.enemy.getY() > character.y * 32 + character.height)))
			return true;
		return false;
	}

	public Weapon getWeapon() {
		return character.getWeapon();
	}

	public float getStateTimer() {
		return stateTimer;
	}

	public StateDynamicObject getPreviousState() {
		return previousState;
	}

	public StateDynamicObject getCurrentState() {
		return currentState;
	}

	public void fightLeft() {
		setState(StateDynamicObject.FIGHTINGLEFT);
		fighting = true;
	}

}
