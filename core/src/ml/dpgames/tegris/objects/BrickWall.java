package ml.dpgames.tegris.objects;

import ml.dpgames.tegris.TextureRender;
import ml.dpgames.tegris.TextureRender.Draw;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class BrickWall extends Wall {

	public static final Texture tex = new Texture("brick_wall.png");

	public BrickWall(float x, float y) {
		super(x, y);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, 16, 16);
	}

	@Override
	public void render(SpriteBatch batch) {
		TextureRender.addTex(new Draw() {
			public void render(SpriteBatch batch) {
				batch.draw(tex, x, y, 16, 32 + 16);
			}
		}, (int) y);
	}

}
