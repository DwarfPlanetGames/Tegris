package ml.dpgames.tegris;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class GameObject {

	public float x, y, width, height;

	public GameObject(float x, float y, float width, float height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	public void update(float delta) {

	}

	public void render(SpriteBatch batch) {

	}

}
