package com.mygdx.game.src.Character;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.src.Character.DynamicObjects.StateDynamicObject;
import com.mygdx.game.src.World.Battle;
import com.mygdx.game.src.World.Game;
import com.mygdx.game.src.World.Weapon;

public class CharacterBattle implements com.mygdx.game.src.World.ICollidable {

	public Character character;

	public float stateTimer;
	public boolean fighting;
	public float fightingTimeCurrent;
	public float fightingTime;
	public boolean jumping;
	public boolean doubleJumping;
	public float velocityY;
	public float velocityX;

	public CharacterBattle(final Character character1) {
		stateTimer = 0;

		character = new Character(character1);
		character.x = 100;
		character.y = 250;
		character.width = 120;
		character.height = 120;
		character.currentState = StateDynamicObject.STANDING;
		character.previousState = null;
		fighting = false;
		fightingTimeCurrent = 0;
		fightingTime = 0.2f;

		jumping = false;
		doubleJumping = false;
		velocityY = 0;
		velocityX = 10;
	}

	public float getHealth() {
		return character.getHealth();
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
			setState(getCurrentState());
		} else if (fighting && fightingTimeCurrent > fightingTime) {
			fighting = false;
			fightingTimeCurrent = 0;
		}

		if ((jumping || doubleJumping) && character.y + velocityY * dt > 250) {
			character.y += velocityY * dt;
			updateVelocityY(dt);
			setState(StateDynamicObject.JUMPING);
			if (collide() && character.x < Game.world.battle.enemy.getX())
				character.x = Game.world.battle.enemy.getX() - character.getWidth() / 2;
			else if (collide() && character.x > Game.world.battle.enemy.getX())
				character.x = Game.world.battle.enemy.getX() + Game.world.battle.enemy.getWidth() / 2;
		} else {
			jumping = false;
			doubleJumping = false;
			character.y = 250;
			velocityY = 0;
		}

	}

	public void fightRight() {
		character.width += character.primary_weapon.getWidth();
		if (collide())
			Battle.enemy.decreaseHealth(character.primary_weapon);
		character.width -= character.primary_weapon.getWidth();

		setState(StateDynamicObject.FIGHTINGRIGHT);
		fighting = true;

	}

	public void fightLeft() {
		character.x -= character.primary_weapon.getWidth();
		if (collide())
			Battle.enemy.decreaseHealth(character.primary_weapon);
		character.x += character.primary_weapon.getWidth();

		setState(StateDynamicObject.FIGHTINGLEFT);
		fighting = true;

	}

	@SuppressWarnings("static-access")
	public void updateVelocityY(float dt) {
		velocityY -= Game.world.battle.gravity * dt;
	}

	public void stand() {
		if (!fighting) {
			setState(StateDynamicObject.STANDING);
			stateTimer = 0;
		}
	}

	public void movesRight(float dt) {

		if (character.x + character.velocity * dt + character.getWidth() < 1100)
			character.x += character.velocity * dt;
		if (collide())
			character.x -= character.velocity * dt;
		if (!fighting)
			setState(StateDynamicObject.RUNNINGRIGHT);
	}

	public void movesLeft(float dt) {

		if (character.x - character.width / 2 > 0)
			character.x -= character.velocity * dt;
		if (collide())
			character.x += character.velocity * dt;
		if (!fighting)
			setState(StateDynamicObject.RUNNINGLEFT);
	}

	public void jump(float dt) {
		if (!jumping && !doubleJumping) {
			jumping = true;
			velocityY = 100;
			velocityX = 10;
			setState(StateDynamicObject.JUMPING);
		} else if (jumping && !doubleJumping) {
			jumping = false;
			doubleJumping = true;
			velocityY = 100;
			velocityX = 10;
		}
	}

	public void setState(StateDynamicObject state) {
		character.previousState = character.currentState;
		character.currentState = state;
		if (character.previousState == character.currentState && character.currentState != StateDynamicObject.STANDING)
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

	@SuppressWarnings("static-access")
	@Override
	public boolean collide() {

		if (!((character.x > Game.world.battle.enemy.getX() + Game.world.battle.enemy.getWidth() / 2
				|| Game.world.battle.enemy.getX() > character.x + character.width / 2)
				|| (character.y > Game.world.battle.enemy.getY() + Game.world.battle.enemy.getHeight() / 2
						|| Game.world.battle.enemy.getY() > character.y + character.height / 2)))
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
		return character.previousState;
	}

	public StateDynamicObject getCurrentState() {
		return character.currentState;
	}

	public void decreaseHealth(Weapon weapon) {
		character.health -= weapon.getDamage();
	}

}
