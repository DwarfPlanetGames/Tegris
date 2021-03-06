package ml.dpgames.tegris.objects;

import ml.dpgames.tegris.GameObject;
import ml.dpgames.tegris.TextureRender;
import ml.dpgames.tegris.TextureRender.Draw;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Wall extends GameObject {
	
	public static final Texture tex = new Texture("column_1.png");
	
	public Wall(float x, float y) {
		super(x, y, 16, 14);
	}
	
	@Override
	public Rectangle getBounds() {
		return new Rectangle(x,y,width,height);
	}
	
	@Override
	public void render(SpriteBatch batch) {
		super.render(batch);
		TextureRender.addTex(new Draw(){
			public void render(SpriteBatch batch) {
				batch.draw(tex, x, y);
			}
		}, (int) y);
	}

}
