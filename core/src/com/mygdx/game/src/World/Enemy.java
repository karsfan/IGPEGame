package com.mygdx.game.src.World;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.src.Character.DynamicObjects;

public class Enemy extends DynamicObjects{
	
	public enum Level {
		EASY, MEDIUM, HARD
	};

	String name;
	float life;
	float power;
	Weapon weapon;
	Pack win_bonus;
	public Level level;
	

	public Enemy(String name, float life, float power, Weapon weapon, Pack win_bonus, Level level) {

		velocity = 80;
		this.name = name;
		this.life = life;
		this.power = power;
		this.weapon = weapon;
		this.win_bonus = win_bonus;
		this.level = level;
		
		stateTimer = 0;
		x = 700;
		y = 250;
		height = 120;
		width = 120;
		currentState = StateDynamicObject.STANDING;
		previousState = null;
	}

	public void movesRight(float dt) {
		if (x + width < Battle.WIDTH)
			x += velocity * dt;
		setState(StateDynamicObject.RUNNINGRIGHT);
	}

	public void movesLeft(float dt) {
		if (x - width / 2 > 0)
			x -= velocity * dt;

		setState(StateDynamicObject.RUNNINGLEFT);
	}

	public void jump() {
		setState(StateDynamicObject.JUMPING);
	}

	private void setStateTimer(float f) {
		stateTimer = f;
	}


	private void setState(StateDynamicObject state) {
		previousState = currentState;
		currentState = state;

		if (previousState == currentState)
			setStateTimer(getStateTimer() + Gdx.graphics.getDeltaTime());
		else
			setStateTimer(0);
	}


	public void decreaseHealth(Weapon weaponCharacter) {
		life -= weaponCharacter.getDamage();
	}

	public void update(float dt) {
		System.out.println("ciao");
	}

}
