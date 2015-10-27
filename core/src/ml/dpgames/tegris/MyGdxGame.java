package ml.dpgames.tegris;

import ml.dpgames.tegris.screens.TitleScreen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;

public class MyGdxGame extends Game {
	
	@Override
	public void create () {
		GameObject.terrain = new Texture("terrain_sheet.png");
		setScreen(new TitleScreen());
	}
	
	public static void setTheScreen(Screen screen) {
		((Game)Gdx.app.getApplicationListener()).setScreen(screen);
	}
}
