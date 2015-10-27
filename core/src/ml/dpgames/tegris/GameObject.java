package ml.dpgames.tegris;

import java.util.Random;

import ml.dpgames.tegris.TextureRender.Draw;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class GameObject {

	public float x, y, width, height;
	public static Texture terrain;
	public TextureRegion terrainReg = new TextureRegion(terrain,0,0,16,16);
	public static final Random rand = new Random();

	public GameObject(float x, float y, float width, float height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		terrainReg.setRegion(0, rand.nextInt(2) * 16, 16, 16);
	}
	
	public Rectangle getBounds() {
		return null;
	}

	public void update(float delta) {

	}

	public void render(SpriteBatch batch) {
		TextureRender.addTex(new Draw(){
			public void render(SpriteBatch batch) {
				batch.draw(terrainReg, x, y);
			}
		}, (int) y+50);
	}

}
