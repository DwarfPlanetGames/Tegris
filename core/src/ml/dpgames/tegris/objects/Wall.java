package ml.dpgames.tegris.objects;

import ml.dpgames.tegris.GameObject;
import ml.dpgames.tegris.TextureRender;
import ml.dpgames.tegris.TextureRender.Draw;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Wall extends GameObject {
	
	public static final Texture tex = new Texture("column_1.png");
	
	public Wall(float x, float y) {
		super(x, y, 16, 16);
	}
	
	@Override
	public void render(SpriteBatch batch) {
		TextureRender.addTex(new Draw(){
			public void render(SpriteBatch batch) {
				batch.draw(tex, x, y);
			}
		}, (int) y);
	}

}
