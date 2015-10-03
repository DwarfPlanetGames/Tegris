package ml.dpgames.tegris.screens;

import java.util.LinkedList;

import ml.dpgames.tegris.GameObject;
import ml.dpgames.tegris.objects.Player;
import ml.dpgames.tegris.objects.Wall;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameScreen implements Screen {

	public static LinkedList<GameObject> objects = new LinkedList<GameObject>();
	public static OrthographicCamera camera;
	public static final int SCALE = 3;
	SpriteBatch batch;

	@Override
	public void show() {
		// Setup test objects
		objects.add(new Wall(0,0));
		objects.add(new Player(32,0));
		// Create render batch
		batch = new SpriteBatch();
		// Create camera and add it to the batch
		camera = new OrthographicCamera(Gdx.graphics.getWidth() / SCALE,
				Gdx.graphics.getHeight() / SCALE);
		batch.setProjectionMatrix(camera.combined);
	}

	@Override
	public void render(float delta) {
		// Update the objects
		for (int i = 0; i < objects.size(); i++) {
			objects.get(i).update(delta);
		}
		// Update the camera
		camera.update();
		// Clear the screen
		Color clearColor = new Color(0xd0a053ff);
		Gdx.gl.glClearColor(clearColor.r, clearColor.g, clearColor.b, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		// Set the batch to view through the new camera position
		batch.setProjectionMatrix(camera.combined);
		// Begin rendering
		batch.begin();
		// Render the objects
		for (int i = 0; i < objects.size(); i++) {
			objects.get(i).render(batch);
		}
		// Stop rendering
		batch.end();
	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void hide() {

	}

	@Override
	public void dispose() {

	}

}
