package com.mygdx.game.src.Character;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.src.World.Battle;
import com.mygdx.game.src.World.Weapon;

public class CharacterBattle implements com.mygdx.game.src.World.ICollidable {

	public enum StateBattleCharacter {
		JUMPING, STANDING, RUNNINGLEFT, RUNNINGRIGHT, FIGHTINGRIGHT, FIGHTINGLEFT, DEFENDING
	};

	Character character;
	public StateBattleCharacter currentState;
	public StateBattleCharacter previousState;
	public float stateTimer;

	public CharacterBattle(final Character character1) {
		stateTimer = 0;
		currentState = StateBattleCharacter.STANDING;
		previousState = null;
				
		character = new Character(character1);
		character.x = 100;
		character.y = 100;
		character.width = 100;
		character.height = 200;
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

	public void fight() {
		// character.width += character.primary_weapon.width;
		//if (collide())
			//Battle.enemy.decreaseHealth(character.primary_weapon);
		// character.width -= character.primary_weapon.width;
		//if (currentState == StateBattleCharacter.RUNNINGRIGHT)
			setState(StateBattleCharacter.FIGHTINGRIGHT);
		//if (currentState == StateBattleCharacter.RUNNINGLEFT)
			//setState(StateBattleCharacter.FIGHTINGLEFT);
	}

	public void movesRight(float dt) {
		if (character.x + character.width < Battle.WIDTH)
			character.x += character.velocity * dt;
		setState(StateBattleCharacter.RUNNINGRIGHT);
	}

	public void movesLeft(float dt) {
		if (character.x - character.width / 2 > 0)
			character.x -= character.velocity * dt;

		setState(StateBattleCharacter.RUNNINGLEFT);
	}

	public void jump(float dt) {
		setState(StateBattleCharacter.JUMPING);
	}

	public void setState(StateBattleCharacter state) {
		previousState = currentState;
		currentState = state;

		if (previousState == currentState)
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

	public StateBattleCharacter getPreviousState() {
		return previousState;
	}

	public StateBattleCharacter getCurrentState() {
		return currentState;
	}

}
