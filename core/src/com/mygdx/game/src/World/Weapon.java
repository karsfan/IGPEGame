package  com.mygdx.game.src.World;

import  com.mygdx.game.src.Character.Bag;
import  com.mygdx.game.src.Tool.Potion;

public class Weapon extends Shape {
	public String name;
	float damage;
	int level;
	int powerPoints;

	public Weapon(String name, float damage, int level) {
		this.name = name;
		this.damage = damage;
		this.level = level;
		this.powerPoints = this.level * 20;
	}

	public Weapon() {
		name = new String();

	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void upgrade(Bag bag) {
		if (level < 3) {
			level++;
			powerPoints = level * 20;
			damage += 20;
			bag.deleteParchments(level);
		}
	}
	
	public void upgradePP(Bag bag, Potion potion){
		if(bag.deletePotion(potion))
			powerPoints+=10*potion.getLevel();
		else
			;//ATTENZIONE non ci sono potion per aumentare l'i PP dell'arma
	}
}
