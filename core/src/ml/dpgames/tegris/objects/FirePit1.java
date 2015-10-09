package ml.dpgames.tegris.objects;

import java.util.LinkedList;

import ml.dpgames.tegris.GameObject;
import ml.dpgames.tegris.TextureRender;
import ml.dpgames.tegris.TextureRender.Draw;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class FirePit1 extends GameObject {

	public static final Texture tex = new Texture("fire_pit_1.png");
	public static final Texture partTex = new Texture("fire_particle.png");
	
	public int t = 0;

	public LinkedList<FireParticle> particles = new LinkedList<FireParticle>();

	public FirePit1(float x, float y) {
		super(x, y, 16, 14);
		for (int i = 0; i < 40; i++) {
			FireParticle p = new FireParticle(x,y);
			particles.add(p);
			p.size = rand.nextInt(6);
		}
	}
	
	@Override
	public void update(float delta) {
		
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, width, height);
	}

	@Override
	public void render(SpriteBatch batch) {
		super.render(batch);
		t++;
		TextureRender.addTex(new Draw() {
			public void render(SpriteBatch batch) {
				batch.draw(tex, x, y);
			}
		}, (int) y);
		for (FireParticle p : particles) {
			p.render(batch);
		}
		for (FireParticle p : particles) {
			p.update(Gdx.graphics.getDeltaTime(),particles);
		}
		
	}

	public static class FireParticle extends GameObject {
		public float size;
		public float inY;
		public float inX;

		public FireParticle(float x, float y) {
			super(x, y, 1, 1);
			size = 5;
			inY = y;
			inX = x;
		}
		
		public void update(float delta, LinkedList<FireParticle> particles) {
			y += 40 * delta;
			size *= 0.95;
			if (size < 1) {
				y = inY + 10 + rand.nextInt(3);
				x = inX + 5 + rand.nextInt(7);
				size = 5 + rand.nextInt(4);
			}
		}

		public void render(SpriteBatch batch) {
			TextureRender.addTex(new Draw() {
				public void render(SpriteBatch batch) {
					batch.draw(partTex, x - size / 2.0f, y - size / 2.0f, size, size);
				}
			}, (int) inY);
		}
	}

}
