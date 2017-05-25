package com.mygdx.game.src.World;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;

import com.mygdx.game.src.Character.Character;
import com.mygdx.game.src.Character.Man;
import com.mygdx.game.src.Map.StaticObject;

public class Tile extends StaticObject implements ICollidable {

	private Rectangle door;

	public Tile(Point point, Element element, Dimension size) {
		shape = new Rectangle((int) point.getX(), (int) point.getY(), (int) size.getWidth(), (int) size.getHeight());
		this.element = element;

		door = new Rectangle();
	}

	public Tile(String elemen, Point point) {
		switch (elemen) {
		case "HOME":
			this.element = Element.HOME;
			shape = new Rectangle((int) point.getX(), (int) point.getY(), 64, 64);
			// door = new Rectangle((int) point.getX()+2, (int) point.getY(), 5
			// , 5);
			break;
		case "BUILDING":
			this.element = Element.BUILDING;
			shape = new Rectangle((int) point.getX(), (int) point.getY(), 96, 128);
			break;
		case "GROUND":
			this.element = Element.GROUND;
			shape = new Rectangle((int) point.getX(), (int) point.getY(), 32, 32);
			break;
		case "TREE":
			this.element = Element.THREE;
			shape = new Rectangle((int) point.getX(), (int) point.getY(), 32, 32);
			break;
		case "WATER":
			this.element = Element.WATER;
			shape = new Rectangle((int) point.getX(), (int) point.getY(), 32, 32);
			break;
		case "ROCK":
			this.element = Element.ROCK;
			shape = new Rectangle((int) point.getX(), (int) point.getY(), 32, 32);
			break;
		case "BIGHOME":
			this.element = Element.BIGHOME;
			shape = new Rectangle((int) point.getX(), (int) point.getY(), 128, 96);
			break;
		case "FLOOR":
			this.element = Element.FLOOR;
			shape = new Rectangle((int) point.getX(), (int) point.getY(), 32, 32);
			break;
		case "ROAD":
			this.element = Element.ROAD;
			shape = new Rectangle((int) point.getX(), (int) point.getY(), 32, 32);
			break;
		case "FOREST1":
			this.element = Element.FOREST1;
			shape = new Rectangle((int) point.getX(), (int) point.getY(), 64, 96);
			break;
		case "FOREST2":
			this.element = Element.FOREST2;
			shape = new Rectangle((int) point.getX(), (int) point.getY(), 64, 96);
			break;
		default:
			break;
		}
	}

	public float getHeight() {
		return (float) shape.getHeight();
	}

	public float getWidht() {
		return (float) shape.getWidth();
	}

	public float getX() {
		return (float) shape.getX();
	}

	public float getY() {
		return (float) shape.getY();
	}

	public void setElement(Element element) {
		this.element = element;
	}

	public Element getElement() {
		return element;
	}

	public void setCod(Element element) {
		this.element = element;
	}

	public void setPoint(Point point) {
		shape.x = point.x;
		shape.y = point.y;
		if (element == Element.HOME)
			door = new Rectangle((int) point.getX(), (int) point.getY(), 8, 5);
	}

	@Override
	public boolean collide(Object e) {
		if (this.getElement() == Element.HOME && e instanceof Character) {//5 è lo scarto di errore
			if (!((door.x * 32 + shape.getWidth() / 4 > ((Character) e).getX() + ((Character) e).getWidth() / 2 - 5
					|| ((Character) e).getX() > door.x * 32 + door.width + shape.getWidth() / 4)
					|| (door.y * 32 > ((Character) e).getY() + ((Character) e).getHeight() / 2
							|| ((Character) e).getY() > door.y * 32 + door.height))) {
				System.out.println("PORTAAA "+ (door.x * 32 + shape.getWidth() / 4)+ " "+(int)(door.x * 32 + door.width + shape.getWidth() / 4));
				System.out.println("GIOCATORE "+ ((Character) e).getX() + " "+ (((Character) e).getX() + ((Character) e).getWidth() / 2 - 5));
				return true;
			}
		}
		if (e instanceof Character) {
			if (!((shape.x * 32 > ((Character) e).getX() + ((Character) e).getWidth() / 2 - 1
					|| ((Character) e).getX() > shape.x * 32 + shape.width)
					|| (shape.y * 32 > ((Character) e).getY() + ((Character) e).getHeight() / 2
							|| ((Character) e).getY() > shape.y * 32 + shape.height))) {
				return true;
			}
		}
		if (e instanceof Man) {
			if (!((shape.x * 32 > ((Man) e).getX() + ((Man) e).getWidth() / 2 - 1
					|| ((Man) e).getX() > shape.x * 32 + shape.width)
					|| (shape.y * 32 > ((Man) e).getY() + ((Man) e).getHeight() / 2
							|| ((Man) e).getY() > shape.y * 32 + shape.height)))
				return true;
		}
		return false;
	}

}
