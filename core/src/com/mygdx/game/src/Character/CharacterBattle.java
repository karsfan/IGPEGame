package com.mygdx.game.src.Character;

import com.mygdx.game.src.World.Battle;
import com.mygdx.game.src.World.Weapon;

public class CharacterBattle implements com.mygdx.game.src.World.ICollidable {

	public enum StateBattleCharacter {
		JUMPING, STANDING, RUNNINGLEFT, RUNNINGRIGHT, FIGHTING, DEFENDING
	};

	Character character;
	public StateBattleCharacter state;
	public int stateTimer;

	public CharacterBattle(Character character) {
		stateTimer = 0;
		this.character = character;
		this.character.x = 100;
		this.character.y = 100;
		this.character.width = 60;
		this.character.height = 60;
	}

	public void fight() {
	//	character.width += character.primary_weapon.
		setState(StateBattleCharacter.FIGHTING);
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

	public void jump() {
		setState(StateBattleCharacter.JUMPING);
	}

	public void setState(StateBattleCharacter state) {
		this.state = state;
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

}
