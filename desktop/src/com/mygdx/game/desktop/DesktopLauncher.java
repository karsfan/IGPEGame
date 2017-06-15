package it.slagyom.desktop;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import it.slagyom.GameSlagyom;

public class DesktopLauncher {
	public static void main(String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "SLAGYOM";
<<<<<<< HEAD
		config.width = 854;
		config.height = 480; 
		config.resizable = false;
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		gd.getDisplayMode().getWidth();
=======
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
		config.width = 854;
		config.height = 480;
=======
		
>>>>>>> 2c8db7dc96448855c7e66d24423185617f72eb0f
=======
		
>>>>>>> 2c8db7dc96448855c7e66d24423185617f72eb0f
=======
		
>>>>>>> 4675c4d1f931c659ea82ff291bb79348ac51c3f9
=======
		
>>>>>>> 4675c4d1f931c659ea82ff291bb79348ac51c3f9
>>>>>>> 1c31d2ebb2e096e3488d8a839652beffce0bf899
		new LwjglApplication(new GameSlagyom(), config);

	}
}
