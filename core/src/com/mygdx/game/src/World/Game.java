package com.mygdx.game.src.World;

import java.awt.Point;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.JFileChooser;

import com.mygdx.game.src.Character.Character;
import com.mygdx.game.src.Character.Man;

public class Game {
	public static JFileChooser fc;
	public static World world;
	public static  Character character;
	public static Man man1;
	public static ThreadWorld thread;
	
	public Game(String name) {
		man1 = new Man();
		character = new Character(name);
		fc = new JFileChooser();
		initialize();
		readMap();
		world.getListObjects().add(man1);
		world.getListObjects().add(character);
		thread = new ThreadWorld(this);
		thread.start();
	}
	
	public Game(String path, String name) {
		man1 = new Man();
		character = new Character(name);
		fc = new JFileChooser();
		initialize();
		openFile(path);
		//world.getListObjects().add(man1);
		world.getListObjects().add(character);
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
