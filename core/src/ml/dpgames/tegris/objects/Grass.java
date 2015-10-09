package ml.dpgames.tegris.objects;

import ml.dpgames.tegris.GameObject;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Grass extends GameObject {

	public Grass(float x, float y) {
		super(x, y, 16, 16);
	}
	
	@Override
	public void render(SpriteBatch batch) {
		super.render(batch);
	}
	
}
