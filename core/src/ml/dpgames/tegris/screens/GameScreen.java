package ml.dpgames.tegris.screens;

import java.util.LinkedList;
import java.util.Random;

import ml.dpgames.tegris.GameObject;
import ml.dpgames.tegris.TextureRender;
import ml.dpgames.tegris.objects.BrickWall;
import ml.dpgames.tegris.objects.FirePit1;
import ml.dpgames.tegris.objects.Grass;
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
	public static final int mapWidth = 1000, mapHeight = 1000;
	public static GameObject[][] gridObjects = new GameObject[mapWidth][mapHeight];
	public static Player player;
	public static OrthographicCamera camera;
	public static float SCALE = 3;
	SpriteBatch batch;

	@Override
	public void show() {
		// Set input processor
		Gdx.input.setInputProcessor(new IOP());
		// Setup test objects
		Random rand = new Random();
		for (int x = 0; x < mapWidth; x++) {
			for (int y = 0; y < mapHeight; y++) {
				if (rand.nextInt(400) == 0) {
					gridObjects[x][y] = new Wall(x * 16, y * 16);
				} else if (rand.nextInt(2000) == 0) {
					gridObjects[x][y] = new FirePit1(x * 16, y * 16);
				} else {
					gridObjects[x][y] = new Grass(x * 16, y * 16);
				}
			}
		}
		loadMap(30,30,"stonehenge");
		player = new Player(32, 0);
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
		// To avoid glitches limit delta
		System.out.println(delta);
		if (delta > 0.2f) {
			delta = 0.2f;
		}
		// Set camera to window size
		camera.setToOrtho(false, Gdx.graphics.getWidth() / SCALE,
				Gdx.graphics.getHeight() / SCALE);
		// Change scale if scrolled
		// Update the player
		player.update(delta);
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
		// Render the grid
		// Set camera min and max
		int xs = (int) (player.getXOnGrid() - camera.viewportWidth / 32) - 1;
		int xe = (int) (player.getXOnGrid() + camera.viewportWidth / 26) + 1;
		int ys = (int) (player.getYOnGrid() - camera.viewportHeight / 32) - 1;
		int ye = (int) (player.getYOnGrid() + camera.viewportHeight / 26) + 1;
		if (xs < 0)
			xs = 0;
		if (ys < 0)
			ys = 0;
		if (xe > mapWidth)
			xe = mapWidth;
		if (ye > mapHeight)
			ye = mapHeight;
		// Loop for rendering
		for (int x = xs; x < xe; x++) {
			for (int y = ys; y < ye; y++) {
				try {
					// Set temporary object to current x and y
					GameObject o = gridObjects[x][y];
					if (o != null) {
						// If the object isn't null then render it
						o.render(batch);
					}
				} catch (ArrayIndexOutOfBoundsException e) {
					// Catch for when the player is close to the edge
				}
			}
		}
		// Render the objects
		for (GameObject o : objects) {
			o.render(batch);
		}
		// Render the player
		player.render(batch);
		// Render Texture layers
		TextureRender.render(batch);
		TextureRender.clear();
		// Stop rendering
		batch.end();
	}
	
	public static void loadMap(int x, int y, String path) {
		String file = Gdx.files.internal("maps/" + path + ".txt").readString();
		char[] chars = file.toCharArray();
		int xl = 0;
		for (int i = 0; i < chars.length; i++) {
			GameObject o = null;
			int xh = (x + xl) * 16;
			int yh = y * 16;
			if (chars[i] == '\n') {
				xl = 0;
				y--;
				continue;
			}
			if (chars[i] == 'B') {
				o = new BrickWall(xh, yh);
			}
			if (chars[i] == 'C') {
				o = new Wall(xh, yh);
			}
			if (chars[i] == 'F') {
				o = new FirePit1(xh, yh);
			}
			if (o == null) {
				o = new Grass(xh, yh);
			}
			gridObjects[x+xl][y] = o;
			xl++;
		}
		
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
					if (SCALE > 2)
						SCALE *= 0.9;
				}
			} else {
				for (int i = 0; i > amount; i--) {
					if (SCALE < 12)
						SCALE *= 1.1;
				}
			}
			return true;
		}

	}

}
