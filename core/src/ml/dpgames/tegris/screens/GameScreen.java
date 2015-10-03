package ml.dpgames.tegris.screens;

import java.util.LinkedList;

import ml.dpgames.tegris.GameObject;
import ml.dpgames.tegris.TextureRender;
import ml.dpgames.tegris.objects.Player;
import ml.dpgames.tegris.objects.Wall;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameScreen implements Screen {

	public static LinkedList<GameObject> objects = new LinkedList<GameObject>();
	public static OrthographicCamera camera;
	public static float SCALE = 3;
	SpriteBatch batch;

	@Override
	public void show() {
		// Set input processor
		Gdx.input.setInputProcessor(new IOP());
		// Setup test objects
		objects.add(new Wall(0,0));
		objects.add(new Player(32,0));
		// Create render batch
		batch = new SpriteBatch();
		// Create camera and add it to the batch
		camera = new OrthographicCamera(Gdx.graphics.getWidth() / SCALE,
				Gdx.graphics.getHeight() / SCALE);
		batch.setProjectionMatrix(camera.combined);
		// Initialize TextureRender
		TextureRender.init();
	}

	@Override
	public void render(float delta) {
		// Set camera to window size
		camera.setToOrtho(false, Gdx.graphics.getWidth() / SCALE, Gdx.graphics.getHeight() / SCALE);
		// Change scale if scrolled
		
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
		// Render Texture layers
		TextureRender.render(batch);
		TextureRender.clear();
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
	
	public static class IOP implements InputProcessor {

		@Override
		public boolean keyDown(int keycode) {
			return false;
		}

		@Override
		public boolean keyUp(int keycode) {
			return false;
		}

		@Override
		public boolean keyTyped(char character) {
			return false;
		}

		@Override
		public boolean touchDown(int screenX, int screenY, int pointer,
				int button) {
			return false;
		}

		@Override
		public boolean touchUp(int screenX, int screenY, int pointer, int button) {
			return false;
		}

		@Override
		public boolean touchDragged(int screenX, int screenY, int pointer) {
			return false;
		}

		@Override
		public boolean mouseMoved(int screenX, int screenY) {
			return false;
		}

		@Override
		public boolean scrolled(int amount) {
			if (amount > 0) {
				for (int i = 0; i < amount; i++) {
					SCALE *= 0.9;
				}
			} else {
				for (int i = 0; i > amount; i--) {
					SCALE *= 1.1;
				}
			}
			return true;
		}
		
	}

}
