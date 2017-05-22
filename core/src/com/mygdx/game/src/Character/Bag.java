package  com.mygdx.game.src.Character;

import java.util.ArrayList;
import java.util.Iterator;
import com.mygdx.game.src.Tool.*;
//import Tool.Tool;
import  com.mygdx.game.src.World.Weapon;

public class Bag {
	int capacity;
	public ArrayList<Tool> bagTools;
	Weapon secondary_weapon;

	public Bag() {
		capacity = 50;
		secondary_weapon = null;
		bagTools = new ArrayList<Tool>();
	}

	public void addTool(Tool tool) {
		if (bagTools.size() < capacity) {
			bagTools.add(tool);
		}
	}

	public void addWeapon(Weapon weapon) {
		secondary_weapon = weapon;
	}

	public void deleteParchments(int level) {
		Iterator<Tool> itParchment = bagTools.iterator();
		int deletedParchments = 0;
		while (itParchment.hasNext()) {
			Tool tool = (Tool) itParchment.next();
			if (tool.getLevel() == level)
				deletedParchments++;
		}// it controls that the numbers is sufficient to upgrade
		
		if ((deletedParchments >= 10 && level == 1) || (deletedParchments>=20 && level == 2)) {
			itParchment = bagTools.iterator();// non sappiamo se ricomincia dall'inizio
			deletedParchments = 0;
			while (itParchment.hasNext() && deletedParchments < level*10) {
				Tool tool = (Tool) itParchment.next();
				if (tool.getLevel()==level) {
					deletedParchments++;
					itParchment.remove();
				}
			}

		}
	}
	
	public boolean deletePotion(Potion potion){
		Iterator<Tool> itPotion = bagTools.iterator();
		while (itPotion.hasNext()) {
			Tool tool = (Tool) itPotion.next();
			if (tool == potion){
				itPotion.remove();
				return true;
			}				
		}
		return false;
	}

}
