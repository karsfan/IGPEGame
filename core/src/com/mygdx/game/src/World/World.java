package com.mygdx.game.src.World;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;

import com.mygdx.game.src.Character.Character;
import com.mygdx.game.src.Character.Man;
import com.mygdx.game.src.Tool.Tool;

public class World {
	public enum Element {
		HOME, THREE, FOREST1, FOREST2, GROUND, BUILDING, WATER, ROCK, CHARACTER, ENEMY, PARCHMENTLEVEL1, PARCHMENTLEVEL2, PARCHMENTLEVEL3, POTION_H_L1, POTION_H_L2, POTION_H_L3, FLOOR, ROAD, BIGHOME
	};
	
	ArrayList<Tool> tools = new ArrayList<Tool>();
	Shop shop;
	public Weapon spada;
	public Weapon mazza;
	public Weapon pugnale;
	public Weapon ascia;
	public Weapon martello;
	public Weapon arco;
	public Tool parchmentLevel1;
	public Tool parchmentLevel2;
	public Tool parchmentLevel3;
	
	public Tool potionWeaponLevel1;
	private static ArrayList<Object> listObjects;

	public World() {
		setListObjects(new ArrayList<Object>());
		/*
		 * spada = new Weapon("Spada", 10, 1); mazza = new Weapon("Mazza", 5,
		 * 1); pugnale = new Weapon("Pugnale", 6, 1); ascia = new
		 * Weapon("Ascia", 12, 1); martello = new Weapon("Martello", 8, 1); arco
		 * = new Weapon("Arco", 9, 1);
		 * 
		 * parchmentLevel1 = new Parchment(1); parchmentLevel2 = new
		 * Parchment(2); parchmentLevel3 = new Parchment(3);
		 * 
		 * potionWeaponLevel1 = new Potion(Potion.Use.WEAPON, 1);
		 * 
		 * battle = new Battle();
		 */
	}

	public World(Character character) {
		// this.character = character;

		shop.weapons.add(spada);
		shop.weapons.add(mazza);
		shop.weapons.add(pugnale);
		shop.weapons.add(ascia);
		shop.weapons.add(martello);
		shop.weapons.add(arco);

		// add all parchments
		tools.add(parchmentLevel1);
		tools.add(parchmentLevel2);
		tools.add(parchmentLevel3);

		shop.tools = this.tools;

	}

	public void update(float dt) {
		// TODO Auto-generated method stub
		Iterator<Object> it = (Iterator<Object>) getListObjects().iterator();
		while (it.hasNext()) {
			Object ob = (Object) it.next();
			if (ob instanceof Man) {
				((Man) ob).update(dt);
			}
			if (ob instanceof Enemy) {
				((Enemy) ob).update(dt);
			}
		}
	}

	public ArrayList<Object> getListObjects() {
		return listObjects;
	}

	public static void setListObjects(ArrayList<Object> listObjects) {
		World.listObjects = listObjects;
	}

	public void addTile(String element, Point point) {
		Tile tile = new Tile(element, point);
		//if (element != "GROUND" && element!= "ROAD")
			tile.setPoint(new Point((int)tile.shape.getX(), (int)(tile.shape.getY()- (tile.shape.getHeight()/ 32) + 1)));
		listObjects.add(tile);
	}

	public void addComponent(World worldtmp) {
		Iterator<Object> it = (Iterator<Object>) getListObjects().iterator();
		while (it.hasNext()) {
			listObjects.add(0, it);
		}
	}

}
