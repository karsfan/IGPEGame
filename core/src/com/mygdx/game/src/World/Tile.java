package com.mygdx.game.src.World;

import java.awt.Dimension;
import java.awt.Point;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.mygdx.game.src.Character.Character;
import com.mygdx.game.src.Character.Man;
import com.mygdx.game.src.Map.StaticObject;

public class Tile extends StaticObject implements ICollidable {

	public Tile(Point point, Element element, Dimension size) {
		shape = new RectangleMapObject((float) point.getX(), (float) point.getY(), (float) size.getWidth(),
				(float) size.getHeight());
		this.element = element;
	}

	public Tile(String elemen, Point point) {
		//this.point = point2;
		switch (elemen) {
		case "HOME":
			this.element = Element.HOME;
			shape = new RectangleMapObject((float) point.getX(), (float) point.getY(), 64, 64);
			// size = new Dimension(64, 64);
			break;
		case "BUILDING":
			this.element = Element.BUILDING;
			shape = new RectangleMapObject((float) point.getX(), (float) point.getY(), 96, 128);
			// size = new Dimension(96, 128);
			break;
		case "GROUND":
			this.element = Element.GROUND;
			shape = new RectangleMapObject((float) point.getX(), (float) point.getY(), 32, 32);
			// size = new Dimension(32, 32);
			break;
		case "TREE":
			this.element = Element.THREE;
			shape = new RectangleMapObject((float) point.getX(), (float) point.getY(), 32, 32);
			// size = new Dimension(32, 32);
			break;
		case "WATER":
			this.element = Element.WATER;
			shape = new RectangleMapObject((float) point.getX(), (float) point.getY(), 32, 32);
			// size = new Dimension(32, 32);
			break;
		case "ROCK":
			this.element = Element.ROCK;
			shape = new RectangleMapObject((float) point.getX(), (float) point.getY(), 32, 32);
			// size = new Dimension(32, 32);
			break;
		case "BIGHOME":
			this.element = Element.BIGHOME;
			shape = new RectangleMapObject((float) point.getX(), (float) point.getY(), 128, 96);
			// size = new Dimension(128, 96);
			break;
		case "FLOOR":
			this.element = Element.FLOOR;
			shape = new RectangleMapObject((float) point.getX(), (float) point.getY(), 32, 32);
			// size = new Dimension(32, 32);
			break;
		case "ROAD":
			this.element = Element.ROAD;
			shape = new RectangleMapObject((float) point.getX(), (float) point.getY(), 32, 32);
			// size = new Dimension(32, 32);
			break;
		case "FOREST1":
			this.element = Element.FOREST1;
			shape = new RectangleMapObject((float) point.getX(), (float) point.getY(), 64, 96);
			// size = new Dimension(64, 96);
			break;
		case "FOREST2":
			this.element = Element.FOREST2;
			shape = new RectangleMapObject((float) point.getX(), (float) point.getY(), 64, 96);
			// size = new Dimension(64, 96);
			break;
		default:
			break;
		}
	}

//	public Dimension getSize() {
//		return size;
//	}

	public float getHeight() {
		return shape.getRectangle().getHeight();
	}

	public float getWidht() {
		return shape.getRectangle().getWidth();
	}

	public float getX() {
		return shape.getRectangle().getX();
	}

	public float getY() {
		return shape.getRectangle().getY();
	}

//	public void setSize(Dimension size) {
//		this.size = size;
//	}

	public void setElement(Element element) {
		this.element = element;
	}

	public Element getElement() {
		return element;
	}

	public void setCod(Element element) {
		this.element = element;
	}

	//public Point getPoint() {
		//return point;
	//}

	public void setPoint(Point point) {
		shape.getRectangle().x = point.x;
		shape.getRectangle().y = point.y;
	}

	@Override
	public boolean collide(Object e) {
		if (e instanceof Character) {
			if (!((shape.getRectangle().x * 32 > ((Character) e).getX() + ((Character) e).getWidth() / 2 - 1
					|| ((Character) e).getX() > shape.getRectangle().x * 32 + shape.getRectangle().width)
					|| (shape.getRectangle().y * 32 > ((Character) e).getY() + ((Character) e).getHeight() / 2
							|| ((Character) e).getY() > shape.getRectangle().y * 32 + shape.getRectangle().height))) {
				return true;
			}
		}
		if (e instanceof Man) {
			if (!((shape.getRectangle().x * 32 > ((Man) e).getX() + ((Man) e).getWidth() / 2 - 1
					|| ((Man) e).getX() > shape.getRectangle().x * 32 + shape.getRectangle().width)
					|| (shape.getRectangle().y * 32 > ((Man) e).getY() + ((Man) e).getHeight() / 2
							|| ((Man) e).getY() > shape.getRectangle().y * 32 + shape.getRectangle().height)))
				return true;
		}
		return false;
	}

}
