package ml.dpgames.tegris.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import ml.dpgames.tegris.MyGdxGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		try {
			LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
			new LwjglApplication(new MyGdxGame(), config);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
}
