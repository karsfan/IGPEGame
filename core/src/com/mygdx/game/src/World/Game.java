package com.mygdx.game.src.World;

import java.awt.Point;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.JFileChooser;

import com.mygdx.game.src.Character.Character;
import com.mygdx.game.src.Map.Item;
import com.mygdx.game.src.Map.Item.Level;
import com.mygdx.game.src.Map.StaticObject.Element;

public class Game {
	public static JFileChooser fc;
	
	public static World world;
	public static Character character;

	public static ThreadWorld thread;
	public static Item coin;

	public static String mapPath;
	public static Item potion;

	public Game(String name) {
		potion = new Item (100, 600, Element.POTION, Level.FIRST);
		coin = new Item(100, 700, Element.COIN, Level.FIRST);
		character = new Character(name);
		fc = new JFileChooser();
		world = new World();
		readMap();
		//world.addDynamicObject();
		world.addDynamicObject();
		
		world.getListObjects().add(coin);
		world.getListObjects().add(potion);
		
		world.getListDynamicObjects().add(character);
		thread = new ThreadWorld(this);
		thread.start();
	}
	
	public Game(String path, String name) {
		potion = new Item (100, 600, Element.POTION, Level.FIRST);
		coin = new Item(100, 700, Element.COIN, Level.FIRST);
		
		character = new Character(name);
		fc = new JFileChooser();
		
		world = new World();
		world.addDynamicObject();
		openFile(path);
		world.getListObjects().add(coin);
		world.getListObjects().add(potion);
		
		world.getListDynamicObjects().add(character);
		thread = new ThreadWorld(this);
		thread.start();
	}

	public void play() {
	
	}


	public void initialize() {
		setWorld(new World());

	}

	public static void readMap() {
		openFile("res/map/newMap");
	}

	public void exit() {

	}

	public static World getWorld() {
		return world;
	}

	public static void setWorld(World world) {
		Game.world = world;
	}

	@SuppressWarnings("resource")
	public static void openFile(String fileName) {
		mapPath = fileName;
		FileReader fr = null;
		try {
			fr = new FileReader(fileName);
			try {
				Scanner input = new Scanner(System.in);
				input = new Scanner(fr);
				while (input.hasNextLine()) {
					String line = input.nextLine();
					String[] splittata = line.split(" ");

					for (int i = 0; i < splittata.length - 1; i++)
						world.addTile(splittata[i], getXY(splittata[i + 1]));
				}
				input.close();

			} catch (Exception ex) {
				ex.printStackTrace();
			}
			fr.close();
		} catch (

		IOException e) {
			e.printStackTrace();
		}
	}

	public static Point getXY(String line) {
		int x = 0;
		int i = 1;
		for (; line.charAt(i) != 'Y'; i++) {
			x = x * 10 + java.lang.Character.getNumericValue(line.charAt(i));
		}

		int y = 0;
		for (i = i + 1; line.charAt(i) != ';'; i++) {
			y = y * 10 + java.lang.Character.getNumericValue(line.charAt(i));
		}
		y = Math.abs(y - 29);
		return new Point(x, y);
	}

}
