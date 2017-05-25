package com.mygdx.game.src.World;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;

import com.mygdx.game.src.Character.Man;
import com.mygdx.game.src.Map.Map;
import com.mygdx.game.src.Tool.Tool;

public class World {
	
	ArrayList<Tool> tools;
	Map[] map;
	Shop shop;
	
	public Tool potionWeaponLevel1;
	private static ArrayList<Object> listObjects;

	public World() {
		setListObjects(new ArrayList<Object>());
	}


	public void update(float dt) {
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
