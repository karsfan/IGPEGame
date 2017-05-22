package com.mygdx.game.src.World;

import java.awt.Dimension;
import java.awt.Point;
import com.mygdx.game.src.World.World.Element;
import com.mygdx.game.src.Character.Character;

public class Tile implements ICollidable {
	private Point point;
	private Element element;
	private Dimension size;

	public Tile(Point point, Element element, Dimension size) {
		this.point = point;
		this.element = element;
		this.size = size;
	}

	public Tile(String elemen, Point point2) {
		//ciao
		this.point = point2;
		switch (elemen) {
		case "HOME":
			this.element = Element.HOME;
			size = new Dimension(64, 64);
			break;
		case "BUILDING":
			this.element = Element.BUILDING;
			size = new Dimension(96, 128);
			break;
		case "GROUND":
			this.element = Element.GROUND;
			size = new Dimension(32, 32);
			break;
		case "TREE":
			this.element = Element.THREE;
			size = new Dimension(32, 32);
			break;
		case "WATER":
			this.element = Element.WATER;
			size = new Dimension(32, 32);
			break;
		case "ROCK":
			this.element = Element.ROCK;
			size = new Dimension(32, 32);
			break;
		case "BIGHOME":
			this.element = Element.BIGHOME;
			size = new Dimension(128, 96);
			break;
		case "FLOOR":
			this.element = Element.FLOOR;
			size = new Dimension(32, 32);
			break;
		case "ROAD":
			this.element = Element.ROAD;
			size = new Dimension(32, 32);
			break;
		case "FOREST1":
			this.element = Element.FOREST1;
			size = new Dimension(64, 96);
			break;
		case "FOREST2":
			this.element = Element.FOREST2;
			size = new Dimension(64, 96);
			break;
		default:
			break;
		}
	}

	public Dimension getSize() {
		return size;
	}

	public void setSize(Dimension size) {
		this.size = size;
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

	public Point getPoint() {
		return point;
	}

	public void setPoint(Point point) {
		this.point = point;
	}

	@Override
	public boolean collide(Object e) {
		if (e instanceof Character) {
			if (!((point.x * 32 > ((Character) e).getX() + ((Character) e).getWidth() / 2 - 1
					|| ((Character) e).getX() > point.x * 32 + size.width)
					|| (point.y * 32 > ((Character) e).getY() + ((Character) e).getHeight() / 2
							|| ((Character) e).getY() > point.y * 32 + size.height))) {
				return true;
			}
		}
		return false;
	}

}
