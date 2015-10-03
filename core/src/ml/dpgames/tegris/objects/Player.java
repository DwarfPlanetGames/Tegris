package ml.dpgames.tegris.objects;

import ml.dpgames.tegris.GameObject;
import ml.dpgames.tegris.screens.GameScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Player extends GameObject {

	public static final Texture tex = new Texture("player.png");
	public static float WALK_SPEED = 80f;

	public Player(float x, float y) {
		super(x, y, 9, 9);
	}

	@Override
	public void update(float delta) {
		if (Gdx.input.isKeyPressed(Keys.W) || Gdx.input.isKeyPressed(Keys.UP)) {
			y += WALK_SPEED * delta;
		}
		if (Gdx.input.isKeyPressed(Keys.S) || Gdx.input.isKeyPressed(Keys.DOWN)) {
			y -= WALK_SPEED * delta;
		}
		if (Gdx.input.isKeyPressed(Keys.D)
				|| Gdx.input.isKeyPressed(Keys.RIGHT)) {
			x += WALK_SPEED * delta;
		}
		if (Gdx.input.isKeyPressed(Keys.A) || Gdx.input.isKeyPressed(Keys.LEFT)) {
			x -= WALK_SPEED * delta;
		}
		GameScreen.camera.position.x = x + width / 2;
		GameScreen.camera.position.y = y + height / 2;
	}

	@Override
	public void render(SpriteBatch batch) {
		batch.draw(tex, x, y);
	}

}
